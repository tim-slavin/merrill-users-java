package com.merrill.users.model;

public abstract class UserImpl implements User {
	private String id;
	private String emailAddress;
	private String languageCode;
	private String[] projectIds;
	
	public UserImpl() {
		super();
	}
	
	protected UserImpl(User otherUser) {
		super();
		
		this.id = otherUser.getId();
		this.emailAddress = otherUser.getEmailAdddress();
		this.languageCode = otherUser.getLanguageCode();
		
		this.projectIds = otherUser.getProjectIds();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getEmailAdddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String getLanguageCode() {
		return languageCode;
	}

	@Override
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
	public String[] getProjectIds() {
		return projectIds;
	}

	@Override
	public void setProjectIds(String[] projectIds) {
		this.projectIds = projectIds;
	}
	
	public abstract User copy();

}
