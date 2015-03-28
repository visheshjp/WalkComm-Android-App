/**
 * Author: Walkcomm
 * */
package com.example.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	private static String loginURL = "http://172.16.13.134:8080/walkcomm_android_connection/";
	private static String registerURL = "http://172.16.13.134:8080/walkcomm_android_connection/";
	private static String messageURL = "http://172.16.13.134:8080/walkcomm_android_connection/";
	private static String communicationURL = "http://172.16.13.134:8080/walkcomm_android_connection/";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	private static String message_tag = "message";
	private static String communication_tag = "communication";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String name, String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}
	
	
	/**
	 * function insert message data
	 * */
	public JSONObject storeMessage(String msg_type, String msg_path){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", message_tag));
		params.add(new BasicNameValuePair("msg_type", msg_type));
		params.add(new BasicNameValuePair("msg_path", msg_path));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(messageURL, params);
		// return json
		return json;
	}
	
	
	/**
	 * function insert communication data
	 * */
	public JSONObject storeCommunication(String wifi_id, String msg_id, String sender, String receiver){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", communication_tag));
		params.add(new BasicNameValuePair("wifi_id", wifi_id));
		params.add(new BasicNameValuePair("msg_id", msg_id));
		params.add(new BasicNameValuePair("sender", sender));
		params.add(new BasicNameValuePair("receiver", receiver));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(communicationURL, params);
		// return json
		return json;
	}
	
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
