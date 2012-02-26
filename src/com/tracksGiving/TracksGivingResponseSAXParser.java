package com.tracksGiving;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TracksGivingResponseSAXParser extends DefaultHandler{
	
	private String tempVal;
	
	private Response respObj;
	
	public Response parseTracksGivingResponse(String response) {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			SAXParser sp = spf.newSAXParser();
			byte[] byteArray = response.getBytes("UTF-8");
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
			sp.parse(inputStream, this);
			return this.respObj;
			
		}catch(SAXException se) {
			se.printStackTrace();
			return null;
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
			return null;
		}catch (IOException ie) {
			ie.printStackTrace();
			return null;
		}
	}


	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tempVal = "";
		if(qName.equalsIgnoreCase("hash")) {
			respObj = new Response();
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("error")) {
			respObj.setError(tempVal);
		}else if (qName.equalsIgnoreCase("tracking-url")) {
			respObj.setTracking_url(tempVal);
		}else if (qName.equalsIgnoreCase("status")) {
			respObj.setStatus(tempVal);
		}else if (qName.equalsIgnoreCase("token-for-url")) {
			respObj.setToken_for_url(tempVal);
		}else if (qName.equalsIgnoreCase("universal-tracking-url")) {
			respObj.setUniversal_tracking_url(tempVal);
		}
		
	}
	
	/*public static void main(String[] args){
		TracksGivingResponseSAXParser spe = new TracksGivingResponseSAXParser();
		Response response = spe.parseTracksGivingResponse("<hash><error>crap</error><tracking-url>http://localhost:8080/tracks/502</tracking-url><status type=\"integer\">200</status><token-for-url>2e9d2580-41f3-012f-e557-442c03154814</token-for-url></hash>");
		System.out.println("Error: " + response.getError());
		System.out.println("Tracking_URL: " + response.getTracking_url());
		System.out.println("Status: " + response.getStatus());
		System.out.println("Token_For_URL: " + response.getToken_for_url());
		System.out.println("Universal_Tracking_URL: " + response.getUniversal_tracking_url());
	}*/

}
