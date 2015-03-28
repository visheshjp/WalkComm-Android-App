/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.walkcomm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.walkcomm.R;
import com.example.library.UserFunctions;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;
	Button btnMsgView;
	Button btnAudioMsg;
	Button btnAboutUS;
	Button btnContactUS;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	setContentView(R.layout.dashboard);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
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
        	
        	btnAboutUS = (Button) findViewById(R.id.btn_aboutUS);
    		btnContactUS = (Button)findViewById (R.id.btn_contactUS);
    		btnAboutUS.setOnClickListener(new View.OnClickListener() {

    			public void onClick(View view) {
    				aboutUS();
    			}
    		});

    		btnContactUS.setOnClickListener(new View.OnClickListener() {

    			public void onClick(View view) {
    				contactUS();
    			}
    		});
    		
        	btnMsgView = (Button) findViewById(R.id.view_messages);
        	btnMsgView.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View arg0) {
    				setContentView(R.layout.messages);
    				// TODO Auto-generated method stub
    			//	userFunctions.logoutUser(getApplicationContext());
    				Intent message = new Intent(getApplicationContext(), MessageActivity.class);
    	        	message.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(message);
    	        	// Closing dashboard screen
    	        	/*finish();*/
    			}
    				
    		});
        	
        	
        	btnAudioMsg = (Button) findViewById(R.id.record_audio);
        	btnAudioMsg.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View arg0) {
    				setContentView(R.layout.voice_call);
    				// TODO Auto-generated method stub
    			//	userFunctions.logoutUser(getApplicationContext());
    				Intent recording = new Intent(getApplicationContext(), RecordingActivity.class);
    				recording.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(recording);
    	        	// Closing dashboard screen
    	        	/*finish();*/
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
    
    public void aboutUS()    
    {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("About US");
        alertBuilder.setMessage("WalkComm is an Android walkie talkie cum video messenger designed by group of enthusiastic NYU Poly students" +"\n"+"\n"+
                "This app allows hasle free communication between friends using walkie talkie feature as well send instantenous voice video messages" +"\n"+"\n"+
                "Sign UP today to use the app, Happy Communication!!")
                .setCancelable(false)
                .setNegativeButton("Click here to continue!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
 
                // create alert dialog
                AlertDialog alertDialog = alertBuilder.create();
 
                // show it
                alertDialog.show();
    }
	
	public void contactUS()
    {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Contact US");
        alertBuilder.setMessage("Vishesh Pathak: Core Functionality Developer" +"\n"+"\n"+
                "Rahul Kumar: UI Designer" +"\n"+"\n"+
                "Avanish Upadhyay: Back-End Developer")
                .setCancelable(false)
                .setNegativeButton("Click here to continue!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
 
                // create alert dialog
                AlertDialog alertDialog = alertBuilder.create();
 
                // show it
                alertDialog.show();
    }
}