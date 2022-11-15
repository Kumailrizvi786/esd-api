package com.code.develop.model;

import java.util.Objects;

public class SignInData {

	private String email;
	private String password; // new password
	
	

	//get and set email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	//get and set current password.
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignInData other = (SignInData) obj;
		return Objects.equals(email, other.email);
	}
	
	
}