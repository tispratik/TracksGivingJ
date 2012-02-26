package com.tracksGiving;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private String id;
	private GregorianCalendar due_date;
	private String comment;
	private String token;
	
	private String requestType; 
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public GregorianCalendar getDue_date() {
		return due_date;
	}
	
	public void setDue_date(GregorianCalendar due_date) {
		this.due_date = due_date;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public Response cancel(){
		
		try{
			Response response = new Response();
			setRequestType(Constants.CANCEL_REQUEST);
			ValidationResult vResult = Utils.isValidOrderRequest(this);
			if(vResult.isValid()){

				Map<String,String> parameters = new HashMap<String, String>();
				StringBuilder sbr = new StringBuilder(Constants.DEFAULT_BASE_URL);
				sbr.append(Constants.ORDER_URL_APPEND);
				sbr.append(getId());
				sbr.append(Constants.XML_ACCEPT_HEADER);
				String _method = Utils.getAppropriateMethod(this);
				parameters.put("_method", _method);
				parameters.put("token", getToken());
				parameters.put("comment", getComment());
				String respStr = TracksGivingWebServicesCaller.callRestfulWebService(sbr.toString(), parameters);
				//response = TracksGivingResponseDigester.parseTracksGivingResponse(respStr);
				TracksGivingResponseSAXParser tgRespSAXParser = new TracksGivingResponseSAXParser();
				response = tgRespSAXParser.parseTracksGivingResponse(respStr);
			}else{
				response = Utils.createErrorResponse(vResult);
			}

			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Response reschedule(){
		
		try{
			Response response = new Response();
			setRequestType(Constants.RESCHEDULE_REQUEST);
			ValidationResult vResult = Utils.isValidOrderRequest(this);
			if(vResult.isValid()){

				Map<String,String> parameters = new HashMap<String, String>();
				StringBuilder sbr = new StringBuilder(Constants.DEFAULT_BASE_URL);
				sbr.append(Constants.ORDER_URL_APPEND);
				sbr.append(getId());
				sbr.append(Constants.XML_ACCEPT_HEADER);
				String _method = Utils.getAppropriateMethod(this);
				parameters.put("_method", _method);
				parameters.put("token", getToken());
				String due_date = Utils.getDateAsString(getDue_date());
				parameters.put("due_date", due_date);
				String respStr = TracksGivingWebServicesCaller.callRestfulWebService(sbr.toString(), parameters);
				//response = TracksGivingResponseDigester.parseTracksGivingResponse(respStr);
				TracksGivingResponseSAXParser tgRespSAXParser = new TracksGivingResponseSAXParser();
				response = tgRespSAXParser.parseTracksGivingResponse(respStr);
			}else{
				response = Utils.createErrorResponse(vResult);
			}
			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
}
