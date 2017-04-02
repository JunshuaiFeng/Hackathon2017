package com.hertzdonut.hackathon2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationLogic {
	
	public Object[] getLocations() throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/location/get";
		String json = request(url);
		
		Object[] locations = parseLocations(json);
		
		
		return locations;
	}
	
	public Object[] parseLocations(String json) throws JSONException {
		ArrayList<Location> locations = new ArrayList<Location>();
		
		JSONArray jsonArray = new JSONArray(json);
		
		for(int i = 0; i < jsonArray.length(); i++) {
			Location location = new Location();
			JSONObject reservationObj = (JSONObject) jsonArray.get(i);
			int id = (int) reservationObj.get("Id");
			String address = (String) reservationObj.get("Address");
			String city = (String) reservationObj.get("City");
			String state = (String) reservationObj.get("State");
			
			location.id = id;
			location.address = address;
			location.city = city;
			location.state = state;
			
			locations.add(location);
		}
		
		return (Object[]) locations.toArray();
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
}
