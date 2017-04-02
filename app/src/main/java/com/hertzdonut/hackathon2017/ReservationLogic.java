package com.hertzdonut.hackathon2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;

public class ReservationLogic {
	
	public Object[] loadCustomerReservations(int customerId) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/reservation/getList/?customer_id=" + customerId;
		String json = request(url);
		
		Object[] reservations = parseLoadCustomerReservations(json);
		
		
		return reservations;
	}
	
	public Object[] parseLoadCustomerReservations(String json) throws JSONException {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		JSONArray jsonArray = new JSONArray(json);
		
		for(int i = 0; i < jsonArray.length(); i++) {
			Reservation reservation = new Reservation();
			JSONObject reservationObj = (JSONObject) jsonArray.get(i);
			int id = (int) reservationObj.get("Id");
			String location = (String) reservationObj.get("Location");
			String carClass = (String) reservationObj.get("CarClass");
			String startDate = (String) reservationObj.get("Start");
			String returnDate = (String) reservationObj.get("Return");
			
			reservation.id = id;
			reservation.location = Integer.parseInt(location);
			reservation.carClass = carClass;
			reservation.StartDate = startDate;
			reservation.ReturnDate = returnDate;
			
			reservations.add(reservation);
		}
		
		return (Object[]) reservations.toArray();
	}
	
	public Reservation loadSingleReservation(int id) throws JSONException {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/reservation/get/?id=" + id;
		String json = request(url);
		
		Reservation reservation = parseLoadSingleReservation(json);
		
		return reservation;
	}
	
	public Reservation parseLoadSingleReservation(String json) throws JSONException {
		
		Reservation reservation = new Reservation();
		JSONArray jsonArray = new JSONArray(json);
		JSONObject reservationObj = (JSONObject) jsonArray.get(0);
		int id = (int) reservationObj.get("Id");
		String location = (String) reservationObj.get("Location");
		String carClass = (String) reservationObj.get("CarClass");
		String startDate = (String) reservationObj.get("Start");
		String returnDate = (String) reservationObj.get("Return");
		
		reservation.id = id;
		reservation.location = Integer.parseInt(location);
		reservation.carClass = carClass;
		reservation.StartDate = startDate;
		reservation.ReturnDate = returnDate;
		
		return reservation;
	}
	
	public boolean createReservation(int location, int customerId, String carClass, String startDate, String returnDate) {
		String url = "http://hertzapi-dev.us-west-2.elasticbeanstalk.com/api/reservation/create/?location=" + location + "&customer_id=" + customerId + "&car_class=" + carClass + "&start_date=" + startDate + "&return_date=" + returnDate;
		String json = request(url);

		try {
			boolean result = parseResponse(json);
			if(result) {
				return true;
			} else {
				return false;
			}
		} catch (JSONException e) {
			return false;
		}

	}

	public boolean parseResponse(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
		boolean success = (boolean) object.get("success");
		return success;
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
