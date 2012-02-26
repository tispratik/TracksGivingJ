package com.tracksGiving;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CustomerDonation {
	
	private String order_id;
	private String order_comment;
	private String order_payment_trx_details;
	private GregorianCalendar order_submission_date;
	private GregorianCalendar order_due_date;
	private String order_ipaddress = Constants.EMPTY_STRING;
	private String order_channel_code = Constants.EMPTY_STRING;
	private String token;
	private String campaign_key;
	private Float donation_amount;
	private Integer project_id;
	private String donor_name;
	private String donor_email;
	private String donor_genre;
	private String donor_pan_number;
	private String donor_tax_payer;
	private String donor_addressline;
	private String donor_country_iso2;
	private String donor_state_iso2;
	private String donor_city;
	private String donor_zipcode;
	
	
	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getOrder_comment() {
		return order_comment;
	}


	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}


	public String getOrder_payment_trx_details() {
		return order_payment_trx_details;
	}


	public void setOrder_payment_trx_details(String order_payment_trx_details) {
		this.order_payment_trx_details = order_payment_trx_details;
	}


	public GregorianCalendar getOrder_submission_date() {
		return order_submission_date;
	}


	public void setOrder_submission_date(GregorianCalendar order_submission_date) {
		this.order_submission_date = order_submission_date;
	}


	public GregorianCalendar getOrder_due_date() {
		return order_due_date;
	}


	public void setOrder_due_date(GregorianCalendar order_due_date) {
		this.order_due_date = order_due_date;
	}


	public String getOrder_ipaddress() {
		return order_ipaddress;
	}


	public void setOrder_ipaddress(String order_ipaddress) {
		this.order_ipaddress = order_ipaddress;
	}


	public String getOrder_channel_code() {
		return order_channel_code;
	}


	public void setOrder_channel_code(String order_channel_code) {
		this.order_channel_code = order_channel_code;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getCampaign_key() {
		return campaign_key;
	}


	public void setCampaign_key(String campaign_key) {
		this.campaign_key = campaign_key;
	}


	public Float getDonation_amount() {
		return donation_amount;
	}


	public void setDonation_amount(Float donation_amount) {
		this.donation_amount = donation_amount;
	}


	public Integer getProject_id() {
		return project_id;
	}


	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}


	public String getDonor_name() {
		return donor_name;
	}


	public void setDonor_name(String donor_name) {
		this.donor_name = donor_name;
	}


	public String getDonor_email() {
		return donor_email;
	}


	public void setDonor_email(String donor_email) {
		this.donor_email = donor_email;
	}


	public String getDonor_genre() {
		return donor_genre;
	}


	public void setDonor_genre(String donor_genre) {
		this.donor_genre = donor_genre;
	}


	public String getDonor_pan_number() {
		return donor_pan_number;
	}


	public void setDonor_pan_number(String donor_pan_number) {
		this.donor_pan_number = donor_pan_number;
	}


	public String getDonor_tax_payer() {
		return donor_tax_payer;
	}


	public void setDonor_tax_payer(String donor_tax_payer) {
		this.donor_tax_payer = donor_tax_payer;
	}


	public String getDonor_addressline() {
		return donor_addressline;
	}


	public void setDonor_addressline(String donor_addressline) {
		this.donor_addressline = donor_addressline;
	}


	public String getDonor_country_iso2() {
		return donor_country_iso2;
	}


	public void setDonor_country_iso2(String donor_country_iso2) {
		this.donor_country_iso2 = donor_country_iso2;
	}


	public String getDonor_state_iso2() {
		return donor_state_iso2;
	}


	public void setDonor_state_iso2(String donor_state_iso2) {
		this.donor_state_iso2 = donor_state_iso2;
	}


	public String getDonor_city() {
		return donor_city;
	}


	public void setDonor_city(String donor_city) {
		this.donor_city = donor_city;
	}


	public String getDonor_zipcode() {
		return donor_zipcode;
	}


	public void setDonor_zipcode(String donor_zipcode) {
		this.donor_zipcode = donor_zipcode;
	}


	public Response invoke(){
		
		try{
			Response response = new Response();
			ValidationResult vResult = Utils.isValidCustomerDonationRequest(this);
			
			if(vResult.isValid()){

				String custDonReq = generateRequestXml();
				if(Utils.isXMLValidAgainstXSD(custDonReq, Constants.CUSTOMER_DONATION_REQUEST_XSD_URL)){
					Map<String,String> parameters = new HashMap<String, String>();
					StringBuilder sbr = new StringBuilder(Constants.DEFAULT_BASE_URL);
					sbr.append(Constants.CUSTOMER_DONATION_URL_APPEND);
					parameters.put("token", getToken());
					parameters.put("xmlobject", custDonReq);
					String respStr = TracksGivingWebServicesCaller.callRestfulWebService(sbr.toString(), parameters);
					//response = TracksGivingResponseDigester.parseTracksGivingResponse(respStr);
					TracksGivingResponseSAXParser tgRespSAXParser = new TracksGivingResponseSAXParser();
					response = tgRespSAXParser.parseTracksGivingResponse(respStr);
				}else{
					ValidationResult vXSDResult = new ValidationResult();
					vXSDResult.setValid(false);
					vXSDResult.setCodesList(new String[] {Constants.XSD_VALIDATION_FAILURE});
					response = Utils.createErrorResponse(vXSDResult);
				}
				
			}else{
				response = Utils.createErrorResponse(vResult);
			}

			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private String generateRequestXml(){
		
		String xmlDie = Constants.CUSTOMER_DONATION_XML_PLACEHOLDER;
		String ordSubDt = Utils.getDateAsString(getOrder_submission_date());
		String ordDueDt = Utils.getDateAsString(getOrder_due_date());
		String custDonReq = String.format(xmlDie, getOrder_id(), getOrder_comment(), getOrder_payment_trx_details(), ordSubDt, ordDueDt, getOrder_ipaddress(), 
											getOrder_channel_code(), getDonor_name(), getDonor_email(),	getDonor_genre(), getDonor_pan_number(), 
											getDonor_tax_payer(), getDonor_addressline(), getDonor_country_iso2(), getDonor_state_iso2(),
											getDonor_city(), getDonor_zipcode(), getCampaign_key(), getDonation_amount().toString(), getProject_id().toString());
		return custDonReq;
	}
	
}
