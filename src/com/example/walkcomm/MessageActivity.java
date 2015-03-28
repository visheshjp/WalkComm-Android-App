/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.walkcomm;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.walkcomm.R;
import com.example.library.UserFunctions;

public class MessageActivity extends Activity {
	UserFunctions userFunctions;
	Button play_audio;
	Button btnLogout;
	String outputFile = "http://172.16.13.134:8080/walkcomm_android_connection/myrecording.3gp";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	setContentView(R.layout.messages);
        	btnLogout = (Button)findViewById(R.id.btnLogout);
        	btnLogout.setOnClickListener(new View.OnClickListener() {

        		public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});

        	
        	play_audio = (Button) findViewById(R.id.view_audio_message);
        	play_audio.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				
    				 try	
    			     {
    			    	 
    			    	 MediaPlayer m = new MediaPlayer();
    			    	 
    			    	
    			  	   m.setDataSource(outputFile);
    			  	   m.prepare();
    			  	   m.start();
    			  	   Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
    			  	   
    			     }
    			     
    			     catch(IllegalArgumentException e){
    			     e.printStackTrace();
    			     }
    			     
    			     catch(SecurityException e){
    				     e.printStackTrace();
    				     }
    			     
    			     catch(IllegalStateException e){
    				     e.printStackTrace();
    				     }
    			     
    			     catch(IOException e){
    				     e.printStackTrace();
    				     }
    			}
    		});
        	
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
        
    }
    
}