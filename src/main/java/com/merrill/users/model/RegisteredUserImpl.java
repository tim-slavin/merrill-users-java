package com.merrill.users.model;

public class RegisteredUserImpl extends UserImpl implements RegisteredUser {
	private String firstName;
	private String lastName;
	private String company;
	private String organizationType;
	private String phone;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private boolean disclaimerAccepted;

	public RegisteredUserImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getCompany() {
		return company;
	}

	@Override
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String getOrganizationType() {
		return organizationType;
	}

	@Override
	public void setOrganizationType(String organizationType) {	
		this.organizationType = organizationType;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public boolean isDisclaimerAccepted() {
		return disclaimerAccepted;
	}

	@Override
	public void setDisclaimerAccepted(boolean disclaimerAccepted) {
		this.disclaimerAccepted = disclaimerAccepted;
	}

}
