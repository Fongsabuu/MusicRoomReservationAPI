package com.rest.api.service;

import java.util.List;

import com.rest.api.model.Room;

public interface RoomService {
	
	public List<Room> getAllRoom();
	
	public List<Room> getRoomById(int room_id);
	
	public int createRoom(Room room);
	
	public int updateRoom(Room room);
	
	public int deleteRoom(int room_id);
	
	public int addImgRoom(int room_id, String name_img, String type_img);
	
	public List<String> getImg(int room_id, String type_img);
	
	public int deleteImgRoom(int room_id, String type_img);

}
