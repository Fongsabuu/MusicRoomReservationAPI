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

import com.rest.api.model.Room;

@Repository
public class RoomDaoImpl implements RoomDao {
	
	@Autowired  
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Room> getAllRoom() {
		
		String sql = "SELECT * FROM room";
		List<Room> rooms = new ArrayList<Room>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> row : rows) {
			Room room = new Room();
			room.setId((int) row.get("id"));
			room.setName((String) row.get("name"));
			room.setType((String) row.get("type"));
			room.setPrice((String) row.get("price"));
			room.setMus_instrument((String) row.get("mus_instrument"));
			room.setDetail((String) row.get("detail"));
			room.setRoom_status(((String) row.get("room_status")).charAt(0));
			rooms.add(room);
		}

		return rooms;
	}

	@Override
	public List<Room> getRoomById(int room_id) {
		String sql = "SELECT * FROM room WHERE id = " + room_id;
		List<Room> rooms = new ArrayList<Room>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> row : rows) {
			Room room = new Room();
			room.setId((int) row.get("id"));
			room.setName((String) row.get("name"));
			room.setType((String) row.get("type"));
			room.setPrice((String) row.get("price"));
			room.setMus_instrument((String) row.get("mus_instrument"));
			room.setDetail((String) row.get("detail"));
			room.setRoom_status(((String) row.get("room_status")).charAt(0));
			rooms.add(room);
		}

		return rooms;
	}

	@Override
	public int createRoom(Room room) {
		
		String sql = "INSERT INTO room (name, type, price, mus_instrument, detail, room_status) VALUES (?, ?, ?, ?, ?, ?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int insert = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, room.getName());
				ps.setString(2, room.getType());
				ps.setString(3, room.getPrice());
				ps.setString(4, room.getMus_instrument());
				ps.setString(5, room.getDetail());
				ps.setString(6, "1");
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
	public int updateRoom(Room room) {
		String sql = "UPDATE room SET name = ?, type = ?, price = ?, mus_instrument= ?, detail = ? WHERE id = ?";
		return jdbcTemplate.update(sql, room.getName(), room.getType(), room.getPrice(), room.getMus_instrument(), room.getDetail(), room.getId());
	}

	@Override
	public int deleteRoom(int room_id) {
		String sql ="DELETE FROM room WHERE id = ?";
		return jdbcTemplate.update(sql, room_id);
	}

	@Override
	public int addImgRoom(int room_id, String name_img, String type_img) {
		String sql = "INSERT INTO room_img (room_id, type_img, name_img, alt_img) VALUES (?, ?, ?, ?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int insert = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, room_id+"");
				ps.setString(2, type_img);
				ps.setString(3, name_img);
				ps.setString(4, name_img);
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
	public List<String> getImg(int room_id, String type_img) {
		List<String> img_name = new ArrayList<String>();
		String sql = "SELECT name_img FROM room_img WHERE room_id = ? AND type_img = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, room_id, type_img);
		for (Map<String, Object> row : rows) {
			img_name.add((String) row.get("name_img"));
		}
		return img_name;
	}

	@Override
	public int deleteImgRoom(int room_id, String type_img) {
		String sql = "DELETE FROM room_img WHERE room_id = ? AND type_img = ?";
		return jdbcTemplate.update(sql, room_id, type_img);
	}

	

}
