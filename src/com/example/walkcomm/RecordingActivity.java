package com.example.walkcomm;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.library.DatabaseHandler;
import com.example.library.UserFunctions;

//import com.example.walkcomm.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
//import android.app.AlertDialog;
//import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;	
import android.provider.ContactsContract.Contacts;			
import android.util.Log;

import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.TextView;
import android.widget.Toast;


public class RecordingActivity extends Activity {

	private MediaRecorder myAudioRecorder;
	 private String outputFile = null;
	 private Button start ;
	private Button play;
	public static final int PICK_CONTACT = 1;
	private Button contact;	
	private String contactName;			
	private Uri contactURI; 			
	private TextView conText; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_call);
		
		// TODO - fill in here
		

	
		//outputFile = Environment.get
		
		outputFile = Environment.getExternalStorageDirectory().
			      getAbsolutePath() + "/myrecording.3gp";;

        //outputFile = "http://172.16.13.134:8080/walkcomm_android_connection/myrecording.3gp";
			      
			      myAudioRecorder = new MediaRecorder();
			      myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			      myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			      myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
			      myAudioRecorder.setOutputFile(outputFile);
			      
			      
		
			    //Contacts	
			      conText = (TextView)findViewById(R.id.textView2);			
			      conText.setVisibility(View.INVISIBLE);			
			      contact = (Button)findViewById(R.id.Button02);			
			      contact.setOnClickListener(new View.OnClickListener() {			
			      	                                                			
			      public void onClick(View v) {			
			      // TODO Auto-generated method stub			
			                                   			
			      Intent intent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);			
			      startActivityForResult(intent, PICK_CONTACT);			
			      	                                        			
			      //setResult(RESULT_OK, intent);			
			      }			
			      });
			      
			      
			  // Record Button    
			  start = (Button)findViewById(R.id.button1) ;
			  start.setOnTouchListener(new View.OnTouchListener() {
			
			//@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//return false;
				if(event.getAction()== MotionEvent.ACTION_DOWN)
				{//recordVoice();
					
					
					try{
						 myAudioRecorder.prepare();
						 myAudioRecorder.start();
						 
						 
						}
						catch(IllegalStateException e)
						{
						
							e.printStackTrace();
						}
						catch(IOException e)
						{
							
							e.printStackTrace();
						
						}
						Toast.makeText(getApplicationContext(), "Recording Started",
							      Toast.LENGTH_LONG).show();
					
					
					
					
					return true;
				  }
				else if(event.getAction()== MotionEvent.ACTION_UP){ 
					//stopVoice();
				
					//inserting data into message table
					String msg_type = "audio";
					String msg_path = "myrecording.3gp";
					UserFunctions msgFunction = new UserFunctions();
					JSONObject json = msgFunction.storeMessage(msg_type, msg_path);
					
					
					//inserting data into communication table
					String wifi_id = "1";
					String msg_id = "1";
					String sender = "1";
					String receiver = "2";
					UserFunctions commFunction = new UserFunctions();
					JSONObject json1 = commFunction.storeCommunication(wifi_id, msg_id, sender, receiver);
					
					
					myAudioRecorder.stop();
				    myAudioRecorder.release();
				    myAudioRecorder  = null;
				    String sentMsg = "Message sent to "+ conText.getText().toString() + " successfully!"  ; 
				    Toast.makeText(getApplicationContext(), sentMsg,
				    Toast.LENGTH_LONG).show();

					return true;
				
				}
				else
				{
					return false;
				}
				}
				
		});

		 //Play Button
		 play =(Button)findViewById(R.id.Button01);
		  
		 play.setOnClickListener(new View.OnClickListener() {
			
			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
			
			
			//}
		});
		 
		 
		 
	}
	
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        //Bcont.setText("Inside on Activity Result");
		
		switch (reqCode) {
        case (PICK_CONTACT):
               
                        contactURI = data.getData();
                        
                        // get the contact ID
                        Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
                        cursor.moveToFirst();
	                        try {
	                        	 contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
							} catch (Exception e) {
								// TODO: handle exception
								contactName = null;
							}
	                       
	                        cursor.close();
	                        
	                       
	                        conText.setVisibility(View.VISIBLE);
	                        
	                       // Log.d(TAG, "Got contactID: " + contactID);
	                        if(contactName!=null)
	                        	
	                        	conText.setText(contactName);
	                        
	                        else
	                        	//showDialog("Error", "Invalid Contact!!!");
                        
              //  }
                break;
                
            
             }
    }
	
	
	
	// Recording the voice
	
	 void recordVoice()
	 {
		try{
		 myAudioRecorder.prepare();
		 myAudioRecorder.start();
		 
		 
		}
		catch(IllegalStateException e)
		{
		
			e.printStackTrace();
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		
		}
		Toast.makeText(getApplicationContext(), "Recording Started",
			      Toast.LENGTH_LONG).show();
	}
		
	 
	 
	 //Stop Recording
	 
	 public void stopVoice(View view)
	 
	 {
		 myAudioRecorder.stop();
	      myAudioRecorder.release();
	      myAudioRecorder  = null;
	      
	      Toast.makeText(getApplicationContext(), "Audio recorded successfully",
	      Toast.LENGTH_LONG).show();

	 }
	 
	 //Listen to the recorded voice
	 public void play() throws IllegalArgumentException,   
	   SecurityException, IllegalStateException, IOException{
	   
	   MediaPlayer m = new MediaPlayer();
	   m.setDataSource(outputFile);
	   m.prepare();
	   m.start();
	   Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

	   }
 	 
	 
}
	 
	




	