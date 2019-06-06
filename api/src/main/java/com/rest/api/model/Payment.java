package com.rest.api.model;

public class Payment {
	
	private int id;
	private String date;
	private int user_id;
	private int reserve_id;
	private String payment_img;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(int reserve_id) {
		this.reserve_id = reserve_id;
	}
	public String getPayment_img() {
		return payment_img;
	}
	public void setPayment_img(String payment_img) {
		this.payment_img = payment_img;
	}

}
