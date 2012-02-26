package com.tracksGiving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class TracksGivingWebServicesCaller {

	private static String buildWebQuery(Map<String, String> parameters) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = URLEncoder.encode(entry.getKey(), "UTF-8");
			String value = URLEncoder.encode(entry.getValue(), "UTF-8");
			sb.append(key).append("=").append(value).append("&");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	public static String callRestfulWebService(String address,Map<String, String> parameters) throws Exception {
		return callRestfulWebService(address, parameters, null, 0);
	}
	
	public static String callRestfulWebService(String address,Map<String, String> parameters, String proxy, int port) throws Exception {
		Proxy proxyObject = null;
		if (!Utils.isEmptyString(proxy) && port > 0) {
			InetSocketAddress proxyAddress = new InetSocketAddress(proxy, port);
			proxyObject = new Proxy(Proxy.Type.HTTP, proxyAddress);
		}
		String response = "";
		String query = buildWebQuery(parameters);
		URL url = new URL(address);
		// make post mode connection
		URLConnection urlConnection = null;
		if (proxyObject == null) {
			urlConnection = url.openConnection();
		} else {
			urlConnection = url.openConnection(proxyObject);
		}
		urlConnection.setDoOutput(true);
		urlConnection.setAllowUserInteraction(false);
		// send query
		PrintStream printStream = new PrintStream(urlConnection.getOutputStream());
		printStream.print(query);
		printStream.close();
		// retrieve result
		BufferedReader readInputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = readInputStream.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		readInputStream.close();
		response = sb.toString();
		return response;
	}
	
	
	
}
