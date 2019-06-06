package com.rest.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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

import com.rest.api.model.Room;
import com.rest.api.service.RoomService;
import com.rest.api.service.StorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	StorageService storageService;
	
	@Autowired  
    private JdbcTemplate jdbcTemplate;

	@PostMapping("/")
	private int createRoom(@RequestBody Room room) {
		return roomService.createRoom(room);
	}

	@GetMapping("/")
	private List<Room> getAllRoom() {
		return roomService.getAllRoom();
	}

	@GetMapping("/{id}")
	private List<Room> getRoomById(@PathVariable("id") int room_id) {
		return roomService.getRoomById(room_id);
	}

	@PutMapping("/")
	private int updateRoom(@RequestBody Room room) {
		return roomService.updateRoom(room);
	}

	@GetMapping("/delete/{id}")
	private int delete(@PathVariable("id") int id) {
		
		int deleteRS = deleteImgRoom(id);
		if(deleteRS == 0) {
			return 0;
		}else {
			return roomService.deleteRoom(id);
		}
//		if (deleteRS == 1) {
//			System.out.println(roomService.deleteImgRoom(id, "b"));
//			System.out.println(roomService.deleteImgRoom(id, "i"));
//		}
		
	}

	@PostMapping("/imgadd/{id}/{type}")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@PathVariable("id") int id ,@PathVariable("type") String type) {
		String message = "";
		try {
			int uploadsuccess = roomService.addImgRoom(id, file.getOriginalFilename(),type);
			storageService.storeImgRoom(file);
			message = "You successfully update img_user " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to update img_user " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping("/img/{id}/{type}")
	private int deleteFile(@PathVariable("id") int id ,@PathVariable("type") String type) {
		return roomService.deleteImgRoom(id, type);
	}
	
	@GetMapping("/img/filename/{id}/{type}")
	private List<Map<String, Object>> getImgName(@PathVariable("id") int id ,@PathVariable("type") String type) {
		String sql = "SELECT * FROM room_img WHERE room_id = ? AND type_img = ?";
		return jdbcTemplate.queryForList(sql, id, type);
	}

	@GetMapping("/img/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Thread t = new Thread();
		t.start();
		Resource file = storageService.loadImgRoom(filename);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	public int deleteImgRoom(int room_id) {
		String sql = "DELETE FROM room_img WHERE room_id = ?";
		return jdbcTemplate.update(sql, room_id);
	}

}
