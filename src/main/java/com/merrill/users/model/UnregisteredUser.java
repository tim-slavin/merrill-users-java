package com.merrill.users.model;

public interface UnregisteredUser extends User {
	public String getRegistrationId();
	public void setRegistrationId(String registrationId);
	
	public String getRegistrationIdGeneratedTime();
	public void setRegistrationIdGeneratedTime(String registrationIdGeneratedTime);
}
