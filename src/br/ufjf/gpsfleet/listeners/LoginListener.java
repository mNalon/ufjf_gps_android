package br.ufjf.gpsfleet.listeners;

import br.ufjf.gpsfleet.LoginActivity;
import br.ufjf.gpsfleet.R;
import br.ufjf.gpsfleet.network.LoginClient;
import android.app.Activity;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;


public class LoginListener{
		
	private LoginActivity loginActivity;
	
	
	public LoginListener(LoginActivity l) {
		loginActivity = l;
	}

	public OnClickListener verifyLogin(){
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				loginActivity.progressDialog.show();
				Thread threadTryLogin = new Thread(new RunnableLoginPost());
				threadTryLogin.start();
			}
		};
	}
	
	public OnTouchListener touchFields(){
		return new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				TextView status = (TextView)LoginListener.this.loginActivity.findViewById(R.id.labelDeniedAccess);
				status.setVisibility(View.GONE); //GONE
				return false;
			}
		};
	}
	
	class RunnableLoginPost implements Runnable{
		@Override
		public void run() {
			try {
				String email = loginActivity.userField.getText().toString();
				String password = loginActivity.passwordField.getText().toString();
				String p = ""; //must call here some sha_512 method
				loginActivity.loginClient.tryLogin(email, password, p);
				loginActivity.handler.post(new Runnable() {
					@Override
					public void run() {
						loginActivity.progressDialog.hide();
						loginActivity.launchTheMainActivity();
					}
				});
				
			} catch (Exception e) {
				loginActivity.handler.post(new Runnable() {
					
					@Override
					public void run() {
						loginActivity.progressDialog.hide();
						TextView status = (TextView)LoginListener.this.loginActivity.findViewById(R.id.labelDeniedAccess);
						status.setVisibility(View.VISIBLE); 
						
					}
				});
				
			}
			
			
		}
		
	}
	

}
