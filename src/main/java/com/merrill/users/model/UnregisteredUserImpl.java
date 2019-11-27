package com.merrill.users.model;

public class UnregisteredUserImpl extends UserImpl implements UnregisteredUser {
	private String registrationId;
	private String registrationIdGeneratedTime;
	
	public UnregisteredUserImpl() {
		super();
	}
	
	protected UnregisteredUserImpl(UnregisteredUser otherUser) {
		super(otherUser);
		
		this.registrationId = otherUser.getRegistrationId();
		this.registrationIdGeneratedTime = otherUser.getRegistrationIdGeneratedTime();
	}

	@Override
	public String getRegistrationId() {
		return registrationId;
	}

	@Override
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public String getRegistrationIdGeneratedTime() {
		return registrationIdGeneratedTime;
	}

	@Override
	public void setRegistrationIdGeneratedTime(String registrationIdGeneratedTime) {
		this.registrationIdGeneratedTime = registrationIdGeneratedTime;
	}
	
	@Override
	public User copy() {
		return new UnregisteredUserImpl((UnregisteredUserImpl) this);
	}
}
