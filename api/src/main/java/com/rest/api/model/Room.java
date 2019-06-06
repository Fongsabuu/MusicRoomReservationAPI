package com.rest.api.model;

public class Room {
	private int id;
	private String name;
	private String type;
	private String price;
	private String mus_instrument;
	private String detail;
	private char room_status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMus_instrument() {
		return mus_instrument;
	}
	public void setMus_instrument(String mus_instrument) {
		this.mus_instrument = mus_instrument;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public char getRoom_status() {
		return room_status;
	}
	public void setRoom_status(char room_status) {
		this.room_status = room_status;
	}

}
