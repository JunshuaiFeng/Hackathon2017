package com.hertzdonut.hackathon2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.*;

public class LoginLogic {
	
	public Profile login(String email, String password) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/customer/get/?email=" + email + "&password=" + password;
		
		String json = request(url);
		boolean success = parseLogin(json);
		
		if(success) {
			url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/profile/get/?email=" + email;
			json = request(url);
			Profile profile = parseProfile(json);
			return profile;
		} else {
			return null;
		}
	}
	
	public String request(String url) {
		String json = null;
		try {
			URL urlObject = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			json = response.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public Profile parseProfile(String json) {
		Profile profile = new Profile();
		
		try {
			Customer customer = new Customer();
			JSONObject object = new JSONObject(json);
			JSONObject customerObject = (JSONObject) object.get("Customer");
			customer.setFirstName((String) customerObject.get("FirstName"));
			customer.setLastName((String) customerObject.get("LastName"));
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
			customer.setBirthDate(df.parse((String)customerObject.get("BirthDate")));
			customer.setEmail((String) customerObject.get("Email"));
			customer.setPassword((String) customerObject.get("Password"));
			
			profile.customer = customer;
			profile.licenseNum = (String) object.get("DriversLicenseNumber");
			profile.licenseState = (String) object.get("DriversLicenseNumber");
			profile.termsAccepted = (Boolean) object.get("WaiversSigned");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;
	}
	
	public boolean parseLogin(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
		Boolean success = (Boolean) object.get("success");
		return success;
	}
}
