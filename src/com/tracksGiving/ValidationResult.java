package com.tracksGiving;


public class ValidationResult {

	private boolean isValid;
	private String[] codesList;
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public String[] getCodesList() {
		return codesList;
	}

	public void setCodesList(String[] codesList) {
		this.codesList = codesList;
	}
		
}
