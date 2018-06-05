package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BAIDUSuggest {

	public static String getRes(String urlText) {
		HttpURLConnection conn = null;
		String text = null;
		try {
			URL url = new URL(urlText);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.getResponseCode();
			text = readResponseBody(conn.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJSON(text);

	}

	private static String toJSON(String text) {
		System.out.println(text);
		if (text == null) {
			return null;
		}
		int s = 0, e = text.length() - 1;
		while (text.charAt(s) != '[')
			s++;
		while (text.charAt(e) != ']')
			e--;
		text = text.substring(s, e + 1);
		text = "{\"res\":" + text + "}";
		System.out.println(text);
		return text;
	}

	private static String readResponseBody(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

}
