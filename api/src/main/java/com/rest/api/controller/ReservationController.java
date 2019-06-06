package com.rest.api.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.model.Payment;
import com.rest.api.model.Reservation;
import com.rest.api.service.CreatePDFBill;
import com.rest.api.service.ReservationService;
import com.rest.api.service.StorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private CreatePDFBill createpdf;
	
	@Autowired  
    private JdbcTemplate jdbcTemplate;
   

	@PostMapping("/")
	private int insertReservation(@RequestBody Reservation reservation) {
		try {
			// check time before insert here
			List<Reservation> reservations = new ArrayList<Reservation>();
			List<String> reserve_byuser = new ArrayList<String>();
			List<String> reserve_bysystem = new ArrayList<String>();
			reservations = reservationService.getReservation(reservation.getDate(), reservation.getRoom_id());
//			List<Map<String, Object>> rows = reservationService.getReservationByDate(reservation.getDate());
//			for (Map<String, Object> row : rows) {
//				Reservation reservationTemp = new Reservation();
//				reservationTemp.setId((int) row.get("id"));
//				reservationTemp.setDate((String) row.get("date"));
//				reservationTemp.setRoom_id((int) row.get("room_id"));
//				reservationTemp.setTime((String) row.get("time"));
//				reservationTemp.setHours((String) row.get("hours"));
//				reservationTemp.setTotalprice((String) row.get("totalprice"));
//				reservationTemp.setUser_id((int) row.get("user_id"));
//				reservationTemp.setReserve_status(((String) row.get("reserve_status")).charAt(0));
//				reservations.add(reservationTemp);
//			}
			System.out.println(reservations.size());
			if (reservations.size() > 0) {
				for (int i = 0; i < Integer.parseInt(reservation.getHours().substring(0, 1)); i++) {
					String nowhours = (Integer.parseInt(reservation.getTime().substring(0, 2)) + i) + ".30";
					String nexthours = (Integer.parseInt(reservation.getTime().substring(0, 2)) + i + 1) + ".30";
					String hours = nowhours + "-" + nexthours;
					reserve_byuser.add(hours);
				}
				reservations.forEach((date) -> {
					for (int i = 0; i < Integer.parseInt(date.getHours().substring(0, 1)); i++) {
						String nowhours = (Integer.parseInt(date.getTime().substring(0, 2)) + i) + ".30";
						String nexthours = (Integer.parseInt(date.getTime().substring(0, 2)) + i + 1) + ".30";
						String hours = nowhours + "-" + nexthours;
						reserve_bysystem.add(hours);
					}
				});
				System.out.println(reserve_bysystem);
				System.out.println(reserve_byuser);

				for (int index = 0; index < reserve_bysystem.size(); index++) {
					for (int i = 0; i < reserve_byuser.size(); i++) {
						if (reserve_byuser.get(i) == reserve_bysystem.get(index)) {
							// ตรวจสอบเวลาที่จองว่า ชนกับเวลาที่มีคนจองไว้ไหม ถ้าไม่มี foundtime = 0
							// ถ้ามีให้ foundtime = 1 เเละให้เเจ้งตืนกับบล็อคปุ่มจอง
							return 0;
						}
					}
				}
			}
			return reservationService.insertReservation(reservation);
		} catch (Exception e) {
			System.out.println("InsertReservation !Error"+ e);
			return 0;
		}
	}

	@PutMapping("/")
	private int updateReservation(@RequestBody Reservation reservation) {
		int update_status = reservationService.updateReservation(reservation);
		if(reservation.getReserve_status() == '2') {
			List<Map<String, Object>> list= reservationService.getReservationById(reservation.getId());
			createpdf.generatePDF(list);
		}
		return update_status;
	}

	@GetMapping("/")
	private List<Map<String, Object>> getAllReservation() {
		return reservationService.getAllReservation();
	}
	
	@PostMapping("/get/{id}")
	private List<Reservation> getReservation(@RequestBody String date, @PathVariable("id") int room_id) {
		return reservationService.getReservation(date, room_id);
	}

	@PostMapping("/date/")
	private List<Map<String, Object>> getReservationByDate(@RequestBody String date) {
		return reservationService.getReservationByDate(date);
	}
	
	@PostMapping("/foruser/{id}")
	private List<Map<String, Object>> getReservationForUser(@RequestBody String date, @PathVariable("id") int user_id) {
		return reservationService.getReservationForUser(date, user_id);
	}
	
	@GetMapping("/user/{id}")
	private List<Map<String, Object>> getReservationByUserId(@PathVariable("id") int user_id) {
		return reservationService.getReservationByUserId(user_id);
	}
	
	@PostMapping("/payment/get/")
	private List<Map<String, Object>> getPayment(@RequestBody Payment payment) {
		String sql = "SELECT * FROM payment WHERE date = ? AND user_id = ? AND reserve_id = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] { payment.getDate(), payment.getUser_id(), payment.getReserve_id() });
		return rows;
	}
	
	@PostMapping("/payment/")
	public int handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("date") String date
			,@RequestParam("user_id") String user_id, @RequestParam("reserve_id") String reserve_id) {
		try {
			storageService.storeImgPayment(file);;
			int insert_status = insertPayment(file.getOriginalFilename(), date, Integer.parseInt(user_id), Integer.parseInt(reserve_id));
			return insert_status;
		} catch (Exception e) {
			return 0;
		}
	}
    
    @GetMapping("/payment/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadImgPayment(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
    
    @GetMapping("/testpdf/{id}")
	private int testPdf(@PathVariable int id){
    	List<Map<String, Object>> list = reservationService.getReservationById(id);
		return createpdf.generatePDF(list);
	}
    
    @GetMapping("/bill/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getBill(@PathVariable String filename) {
		Resource file = storageService.loadBill(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
    
    
    
    public int insertPayment(String filename, String date, int user_id, int reserve_id){
    	String sql = "INSERT INTO payment (date, user_id, reserve_id, payment_img) VALUES (?, ?, ?, ?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int insert = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, date);
				ps.setInt(2, user_id);
				ps.setInt(3, reserve_id);
				ps.setString(4, filename);
				return ps;
			}
		}, keyHolder);

		if (insert == 1) {
			//get id ของ room ที่เพิ่งสร้าง
			return keyHolder.getKey().intValue();
		} else {
			return 0;
		}
    }
}
