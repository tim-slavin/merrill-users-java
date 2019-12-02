package com.merrill.users.model;

public interface User {
	public String getId();
	public void setId(String id);
	
	public String getEmailAddress();
	public void setEmailAddress(String emailAddress);
	
	public String getLanguageCode();
	public void setLanguageCode(String languageCode);
	
	public String[] getProjectIds();
	public void setProjectIds(String[] projectIds);
	
	public User copy();
}
