package com.tracksGiving;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Utils {

	public static boolean isEmptyString(String str){
		if(str == null || Constants.EMPTY_STRING.equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	public static ValidationResult isValidCustomerDonationRequest (CustomerDonation custDon){
		
		ValidationResult vRes = new ValidationResult();
		vRes.setValid(true);
		return vRes;
	}
	
	public static ValidationResult isValidOrderRequest(Order order){
		
		String requestType = order.getRequestType();
		ValidationResult vResult = new ValidationResult();
		List<String> codes = new ArrayList<String>();
		boolean isValid = true;
		
		if(Constants.CANCEL_REQUEST.equals(requestType)){
			
			if(Utils.isEmptyString(order.getId())){
				isValid = false;
				codes.add(Constants.ID_MISSING);
			}
			if(Utils.isEmptyString(order.getToken())){
				isValid = false;
				codes.add(Constants.TOKEN_MISSING);
			}
			if(Utils.isEmptyString(order.getComment())){
				isValid = false;
				codes.add(Constants.COMMENT_MISSING);
			}
			if(isValid){
				vResult.setValid(true);
				return vResult;
			}else{
				vResult.setValid(false);
				vResult.setCodesList((String[])codes.toArray());
				return vResult;
			}
		}else if (Constants.RESCHEDULE_REQUEST.equals(requestType)){
			
			if(Utils.isEmptyString(order.getId())){
				isValid = false;
				codes.add(Constants.ID_MISSING);
			}
			if(Utils.isEmptyString(order.getToken())){
				isValid = false;
				codes.add(Constants.TOKEN_MISSING);
			}
			if(order.getDue_date() == null){
				isValid = false;
				codes.add(Constants.DUEDATE_MISSING);
			}
			if(isValid){
				vResult.setValid(true);
				return vResult;
			}else{
				vResult.setValid(false);
				vResult.setCodesList((String[])codes.toArray());
				return vResult;
			}
		}else{
			return null;
		}
	}

	
	public static Response createErrorResponse(ValidationResult vResult){
		Response res = new Response();
		if(vResult.isValid()){
			return res;
		}else{
			res.setStatus(ErrorCodes.E_400);
			res.setError(generateValidationErrorMessage(vResult));
		}
		return res;
	}
	
	public static String generateValidationErrorMessage(ValidationResult vResult){
		if(vResult == null || vResult.isValid() || vResult.getCodesList() == null){
			return Constants.EMPTY_STRING;
		}
		StringBuilder msgStrBldr = new StringBuilder();
		String[] codeList = vResult.getCodesList();
		for(int count = 0; count < codeList.length; count++){
			if(!isEmptyString(Constants.MESSAGE_MAP.get(codeList[count]))){
				msgStrBldr.append(Constants.MESSAGE_MAP.get(codeList[count]));
				msgStrBldr.append(Constants.ADD_SPACE);
			}
		}
		return msgStrBldr.toString();
	}
	
	public static String getAppropriateMethod(Order order){
		if(Constants.CANCEL_REQUEST.equals(order.getRequestType())){
			return Constants.DELETE_METHOD;
		}else if (Constants.RESCHEDULE_REQUEST.equals(order.getRequestType())){
			return Constants.PUT_METHOD;
		}else{
			return Constants.EMPTY_STRING;
		}
	}
	
	public static String getDateAsString(GregorianCalendar date){
		String DATE_FORMAT = Constants.DATE_FORMAT;
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    return sdf.format(date.getTime());
	}
	
	public static boolean isXMLValidAgainstXSD (String xmlStr, String xsdUrl){
		
		try {
			URL schemaFile = new URL(xsdUrl);
			byte[] byteArray = xmlStr.getBytes("UTF-8");
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
			Source xmlFile = new StreamSource(inputStream);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println("XML is valid");
			return true;
		} catch (SAXException e) {
			System.out.println("XML is NOT valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
			return false;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
