package com.rest.api.model;

public class User {
	private int id;
	private char role;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String gender;
	private String birthday;
	private String address;
	private String tel;
	private String img_user;
	private char user_status;
	
//	user_status; 
//	1 หมายถึง พร้อมใช้งาน รอการล็อคอิน
//	2 หมายถึง กำลังล็อคอินอยู่
//	0 หมายถึง ยกเลิกการใช้งาน
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getRole() {
		return role;
	}
	public void setRole(char role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImg_user() {
		return img_user;
	}
	public void setImg_user(String img_user) {
		this.img_user = img_user;
	}
	public char getUser_status() {
		return user_status;
	}
	public void setUser_status(char user_status) {
		this.user_status = user_status;
	}
}
