package com.merrill.users.model;

public interface RegisteredUser extends User {
	public String getFirstName();
	public void setFirstName(String firstName);
	
	public String getLastName();
	public void setLastName(String lastName);
	
	public String getCompany();
	public void setCompany(String company);
	
	public String getOrganizationType();
	public void setOrganizationType(String organizationType);
	
	public String getPhone();
	public void setPhone(String phone);
	
	public String getCity();
	public void setCity(String city);
	
	public String getState();
	public void setState(String state);
	
	public String getZipCode();
	public void setZipCode(String zipCode);
	
	public String getCountry();
	public void setCountry(String country);
	
	public boolean isDisclaimerAccepted();
	public void setDisclaimerAccepted(boolean disclaimerAccepted);
}
