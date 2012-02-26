package com.tracksGiving;

public class Response {

	private String status;
	private String error;
	private String tracking_url;
	private String universal_tracking_url;
	private String token_for_url;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTracking_url() {
		return tracking_url;
	}
	public void setTracking_url(String tracking_url) {
		this.tracking_url = tracking_url;
	}
	public String getUniversal_tracking_url() {
		return universal_tracking_url;
	}
	public void setUniversal_tracking_url(String universal_tracking_url) {
		this.universal_tracking_url = universal_tracking_url;
	}
	public String getToken_for_url() {
		return token_for_url;
	}
	public void setToken_for_url(String token_for_url) {
		this.token_for_url = token_for_url;
	}
	
	public boolean isSuccess(){
		if (Constants.SUCCESS_STATUS.equals(getStatus())){
			return true;
		}else{
			return false;
		}
	}
	
}
