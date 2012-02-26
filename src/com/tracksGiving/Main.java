package com.tracksGiving;

import java.util.GregorianCalendar;

public class Main {

	public static void main (String [] args){
		
		/*Order ord = new Order();
		ord.setId("10001");
		ord.setToken("vHgYNS7dbotHjziuPCZQ");
		//ord.setComment("10000 ko cancel");
		ord.setDue_date(new GregorianCalendar(2012, 02, 28));
		//Response response = ord.cancel();
		Response response = ord.reschedule();*/
		
		CustomerDonation custDon = new CustomerDonation();
		custDon.setToken("vHgYNS7dbotHjziuPCZQ");
		custDon.setOrder_id("111222335");
		custDon.setOrder_comment("Ordered 2 Books");
		custDon.setOrder_payment_trx_details("VISA trx #2394092049");
		custDon.setOrder_submission_date(new GregorianCalendar(2012, 01, 27));
		custDon.setOrder_due_date(new GregorianCalendar(2012, 01, 29));
		custDon.setOrder_ipaddress("129.203.20.20");
		custDon.setOrder_channel_code("CHWEB");
		custDon.setCampaign_key("8c98a1d0-247e-012f-e4a3-442c03154814");
		custDon.setDonation_amount(305.0f);
		custDon.setProject_id(2);
		custDon.setDonor_name("Captain Jack Sparrow");
		custDon.setDonor_email("jack.sparrow@abc.com");
		custDon.setDonor_genre("UG_P");
		custDon.setDonor_pan_number("");
		custDon.setDonor_tax_payer("TP_RI");
		custDon.setDonor_addressline("random islands");
		custDon.setDonor_country_iso2("IN");
		custDon.setDonor_state_iso2("MH");
		custDon.setDonor_city("Pune");
		custDon.setDonor_zipcode("411028");
		
		Response response = custDon.invoke();
		
		System.out.println("Error: " + response.getError());
		System.out.println("Tracking_URL: " + response.getTracking_url());
		System.out.println("Status: " + response.getStatus());
		System.out.println("Token_For_URL: " + response.getToken_for_url());
		System.out.println("Universal_Tracking_URL: " + response.getUniversal_tracking_url());
	}
}
