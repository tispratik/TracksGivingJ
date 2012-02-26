package com.tracksGiving;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String SUCCESS_STATUS = "200";
	
	public static final String DEFAULT_BASE_URL = "https://secure.tracksgiving.com";
	
	public static final String ORDER_URL_APPEND = "/api/v1/orders/";
	
	public static final String CUSTOMER_DONATION_URL_APPEND = "/api/v1/customer_donations.xml";
	
	public static final String XML_ACCEPT_HEADER = ".xml";
	
	public static final String EMPTY_STRING = "";
	
	public static final String ADD_SPACE = " ";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String CANCEL_REQUEST = "Cancel";
	public static final String RESCHEDULE_REQUEST = "Reschedule";
	
	public static final String DELETE_METHOD = "DELETE";
	public static final String PUT_METHOD = "PUT";
	
	public static final String ID_MISSING = "1";
	public static final String TOKEN_MISSING = "2";
	public static final String COMMENT_MISSING = "3";
	public static final String DUEDATE_MISSING = "4";
	
	public static final String XSD_VALIDATION_FAILURE = "5";
	
	public static final Map<String, String> MESSAGE_MAP = new HashMap<String, String>();
    static {
    	MESSAGE_MAP.put(ID_MISSING, "Validation Failure: Order Id is missing.");
    	MESSAGE_MAP.put(TOKEN_MISSING, "Validation Failure: Token is missing.");
    	MESSAGE_MAP.put(COMMENT_MISSING, "Validation Failure: Comment is missing.");
    	MESSAGE_MAP.put(DUEDATE_MISSING, "Validation Failure: Due Date is missing.");
    	MESSAGE_MAP.put(XSD_VALIDATION_FAILURE, "Validation Failure: XSD Validation Failing");
    }
    
    public static final String CUSTOMER_DONATION_REQUEST_XSD_URL = "https://secure.tracksgiving.com/xsd/customer_donations.xsd";
    
    public static final String CUSTOMER_DONATION_XML_PLACEHOLDER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><customer_donation_request><order><id>%1$s</id><comment>%2$s</comment><payment_trx_details>%3$s</payment_trx_details><submission_date>%4$s</submission_date><due_date>%5$s</due_date><ipaddress>%6$s</ipaddress><channel_code>%7$s</channel_code></order><donor><name>%8$s</name><customer_email>%9$s</customer_email><genre>%10$s</genre><pan_number>%11$s</pan_number><tax_payer>%12$s</tax_payer><addressline>%13$s</addressline><country_iso2>%14$s</country_iso2><state_iso2>%15$s</state_iso2><city>%16$s</city><zipcode>%17$s</zipcode></donor><donation><campaign_key>%18$s</campaign_key><amount>%19$s</amount><project_id>%20$s</project_id></donation></customer_donation_request>";
}
