package com.example.perpule_api.modals;

public class UserDBModal {
	private String username;
	private String random_string;
	private String _id;
	private String hashed_password;
	private String auth_token;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRandom_string() {
		return random_string;
	}
	public void setRandom_string(String random_string) {
		this.random_string = random_string;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getHashed_password() {
		return hashed_password;
	}
	public void setHashed_password(String hashed_password) {
		this.hashed_password = hashed_password;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
}
