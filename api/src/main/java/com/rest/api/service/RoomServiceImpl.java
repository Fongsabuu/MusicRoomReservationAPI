package com.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dao.RoomDao;
import com.rest.api.model.Room;

@Service  
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomDao roomDao;

	@Override
	public List<Room> getAllRoom() {
		return roomDao.getAllRoom();
	}

	@Override
	public List<Room> getRoomById(int room_id) {
		return roomDao.getRoomById(room_id);
	}

	@Override
	public int createRoom(Room room) {
		return roomDao.createRoom(room);
	}

	@Override
	public int updateRoom(Room room) {
		return roomDao.updateRoom(room);
	}

	@Override
	public int deleteRoom(int room_id) {
		// TODO Auto-generated method stub
		return roomDao.deleteRoom(room_id);
	}

	@Override
	public int addImgRoom(int room_id, String name_img, String type_img) {
		// TODO Auto-generated method stub
		return roomDao.addImgRoom(room_id, name_img, type_img);
	}

	@Override
	public List<String> getImg(int room_id, String type_img) {
		// TODO Auto-generated method stub
		return roomDao.getImg(room_id, type_img);
	}

	@Override
	public int deleteImgRoom(int room_id, String type_img) {
		// TODO Auto-generated method stub
		return roomDao.deleteImgRoom(room_id, type_img);
	}

}
