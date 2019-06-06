package com.rest.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rest.api.model.Reservation;

@Repository
public class ReservationDaoImpl implements ReservationDao{
	
	@Autowired  
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getAllReservation() {
		String sql = "SELECT reservation.*,room.name AS room_name,user.firstname AS user_name FROM reservation "
				+ "INNER JOIN room ON reservation.room_id = room.id "
				+ "INNER JOIN user ON reservation.user_id = user.id "
				+ "ORDER BY reservation.date DESC";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		return rows;
	}

	@Override
	public List<Map<String, Object>> getReservationByDate(String date) {
		String sql = "SELECT reservation.*, room.name FROM reservation LEFT JOIN room ON reservation.room_id = room.id "
				+ "WHERE reservation.date >= ? ORDER BY reservation.date DESC";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { date });

		return rows;
	}

	@Override
	public int updateReservation(Reservation reservation) {
		String sql = "UPDATE reservation SET reserve_status = "+ reservation.getReserve_status() +" WHERE id = " + reservation.getId();
		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}

	@Override
	public int insertReservation(Reservation reservation) {
		
		String sql = "INSERT INTO reservation (date, room_id, time, hours, totalprice, user_id, reserve_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int insert = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, reservation.getDate());
				ps.setInt(2, reservation.getRoom_id());
				ps.setString(3, reservation.getTime());
				ps.setString(4, reservation.getHours());
				ps.setString(5, reservation.getTotalprice());
				ps.setInt(6, reservation.getUser_id());
				ps.setString(7, "0");
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

	@Override
	public List<Map<String, Object>> getReservationForUser(String date, int user_id) {
		String sql = "SELECT reservation.*, room.name FROM reservation LEFT JOIN room ON reservation.room_id = room.id "
				+ "WHERE reservation.date >= ? AND reservation.user_id = ? ORDER BY reservation.date DESC";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,	new Object[] { date, user_id });

		return rows;
	}

	@Override
	public List<Map<String, Object>> getReservationByUserId(int user_id) {
		String sql = "SELECT reservation.*,room.name AS room_name,user.firstname AS user_name FROM reservation "
				+ "INNER JOIN room ON reservation.room_id = room.id "
				+ "INNER JOIN user ON reservation.user_id = user.id "
				+ "WHERE reservation.user_id = ? "
				+ "ORDER BY reservation.date DESC";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { user_id });

		return rows;
	}

	@Override
	public List<Reservation> getReservation(String date, int room_id) {
		String sql = "SELECT * FROM reservation WHERE date = ? AND room_id = ?";
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] { date, room_id });
		
		for (Map<String, Object> row : rows) {
			Reservation reservationTemp = new Reservation();
			reservationTemp.setId((int) row.get("id"));
			reservationTemp.setDate((String) row.get("date"));
			reservationTemp.setRoom_id((int) row.get("room_id"));
			reservationTemp.setTime((String) row.get("time"));
			reservationTemp.setHours((String) row.get("hours"));
			reservationTemp.setTotalprice((String) row.get("totalprice"));
			reservationTemp.setUser_id((int) row.get("user_id"));
			reservationTemp.setReserve_status(((String) row.get("reserve_status")).charAt(0));


			reservations.add(reservationTemp);
		}

		return reservations;
	}

	@Override
	public List<Map<String, Object>> getReservationById(int id) {
		String sql = "SELECT reservation.*,user.email,user.firstname,user.tel,user.address,room.name AS room_name FROM reservation "
				+ "INNER JOIN room ON reservation.room_id = room.id "
				+ "INNER JOIN user ON reservation.user_id = user.id "
				+ "WHERE reservation.id = ? "
				+ "ORDER BY reservation.date DESC";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {id });

		return rows;
	}

}
