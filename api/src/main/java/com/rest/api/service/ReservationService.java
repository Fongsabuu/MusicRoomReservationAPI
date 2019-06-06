package com.rest.api.service;

import java.util.List;
import java.util.Map;

import com.rest.api.model.Reservation;

public interface ReservationService {
	
	public List<Map<String, Object>> getAllReservation();
	
	public List<Reservation> getReservation(String date, int room_id);
	
	public List<Map<String, Object>> getReservationById(int id);

	public List<Map<String, Object>> getReservationByDate(String date);
	
	public List<Map<String, Object>> getReservationForUser(String date, int user_id);
	
	public List<Map<String, Object>> getReservationByUserId(int user_id);

	public int updateReservation(Reservation reservation);

	public int insertReservation(Reservation reservation);

}
