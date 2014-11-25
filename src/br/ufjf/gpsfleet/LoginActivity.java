package br.ufjf.gpsfleet;

import br.ufjf.gpsfleet.listeners.LoginListener;
import br.ufjf.gpsfleet.network.ChatClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	Button buttonLogin;
	EditText userField;
	EditText passwordField;
	TextView labelDeniedAccess;
	LoginListener loginListener;
	Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginListener = new LoginListener(this);
		
	    buttonLogin = (Button) findViewById(R.id.buttonLogin);
	    buttonLogin.setOnClickListener(loginListener.verifyLogin());
	    
	    userField = (EditText) findViewById(R.id.fieldUser);
	    passwordField = (EditText) findViewById(R.id.fieldPassword);
	    userField.setOnTouchListener(loginListener.touchFields());
	    passwordField.setOnTouchListener(loginListener.touchFields());
	    
	    labelDeniedAccess = (TextView) findViewById(R.id.labelDeniedAccess);
	}
	
	@Override
	protected void onStart() {
		super.onStart();;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("DENIED_ACCESS_VISIBILITY", labelDeniedAccess.getVisibility());
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		labelDeniedAccess.setVisibility(savedInstanceState.getInt("DENIED_ACCESS_VISIBILITY"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return false; //don't consume this event, allowing other system actions occur 
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
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public void launchTheMainActivity(){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
