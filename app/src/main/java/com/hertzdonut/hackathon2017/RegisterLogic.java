package com.hertzdonut.hackathon2017;

import com.hertzdonut.hackathon2017.Customer;

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

import static android.R.attr.password;

public class RegisterLogic {

	public Profile Register(String firstName, String lastName, String birthDate, String email, String password) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/customer/create/?first_name=" + firstName + "&last_name=" + lastName + "&birth_date=" + birthDate + "&email=" + email + "&password=" + password;
		
		String json = request(url);
		boolean success = parseResponse(json);
		
		if(success) {
			url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/customer/get/?email=" + email + "&password=" + password;
			json = request(url);
			success = parseResponse(json);
			
			if(success) {
				url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/profile/get/?email=" + email;
				json = request(url);
				Profile profile = parseProfile(json);
				return profile;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean submitReservation(int customer_id) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/profile/submit/?id=" + customer_id;
		String json = request(url);
		boolean success = parseResponse(json);

		return success;
	}

	public boolean updateProfile(int customer_id, int profile_id, String firstname, String lastname, String birthdate, String email, String password, String dl_num, String dl_state) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/profile/update/?customer_id = " + customer_id + "&" +
				"profile_id = " + profile_id + "&" +
				"firstName = " + firstname + "&" +
				"lastName = " + lastname + "&" +
				"birthDate = " + birthdate + "&" +
				"email = " + email + "&" +
				"password = " + password + "&" +
				"driversLicenseNumber = " + dl_num + "&" +
				"driversLicenseState = " + dl_state + "&" +
				"waiversSigned = " + true + "&";

		String json = request(url);
		boolean success = parseResponse(json);

		return success;
	}

	public Profile getProfile(int id) {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/profile/getSingle/?id=" + id;
		String json = request(url);
		Profile profile = parseProfile(json);
		return profile;
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
			customer.setId((int) customerObject.get("Id"));
			customer.setFirstName((String) customerObject.get("FirstName"));
			customer.setLastName((String) customerObject.get("LastName"));
			customer.setBirthDate((String)customerObject.get("BirthDate"));
			customer.setEmail((String) customerObject.get("Email"));
			customer.setPassword((String) customerObject.get("Password"));
			
			profile.customer = customer;
			profile.id = (int) object.get("id");
			Object o = object.get("DriversLicenseNumber");
			if(o instanceof String) {
				profile.licenseNum = (String) object.get("DriversLicenseNumber");
			}
			
			o = object.get("DriversLicenseState");
			if(o instanceof String) {
				profile.licenseState = (String) object.get("DriversLicenseState");
			}
			
			o = object.get("WaiversSigned");
			if(o instanceof Boolean) {
				profile.termsAccepted = (Boolean) object.get("WaiversSigned");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;
	}
	
	public boolean parseResponse(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
		Boolean success = (Boolean) object.get("success");
		return success;
	}
}
