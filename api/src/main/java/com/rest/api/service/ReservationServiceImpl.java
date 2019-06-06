package com.rest.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dao.ReservationDao;
import com.rest.api.model.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDao reservationDao;

	@Override
	public List<Map<String, Object>> getAllReservation() {
		return reservationDao.getAllReservation();
	}

	@Override
	public List<Map<String, Object>> getReservationByDate(String date) {
		return reservationDao.getReservationByDate(date);
	}

	@Override
	public int updateReservation(Reservation reservation) {
		return reservationDao.updateReservation(reservation);
	}

	@Override
	public int insertReservation(Reservation reservation) {
		return reservationDao.insertReservation(reservation);
	}

	@Override
	public List<Map<String, Object>> getReservationForUser(String date, int user_id) {
		return reservationDao.getReservationForUser(date, user_id);
	}

	@Override
	public List<Map<String, Object>> getReservationByUserId(int user_id) {
		return reservationDao.getReservationByUserId(user_id);
	}

	@Override
	public List<Reservation> getReservation(String date, int room_id) {
		return reservationDao.getReservation(date, room_id);
	}

	@Override
	public List<Map<String, Object>> getReservationById(int id) {
		return reservationDao.getReservationById(id);
	}

}
