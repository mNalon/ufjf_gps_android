package br.ufjf.gpsfleet.listeners;

import br.ufjf.gpsfleet.LoginActivity;
import br.ufjf.gpsfleet.R;
import android.app.Activity;
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
				Boolean auth = true;
				if(auth){
					LoginListener.this.loginActivity.launchTheMainActivity();
				}else{
					TextView status = (TextView)LoginListener.this.loginActivity.findViewById(R.id.labelDeniedAccess);
					status.setText("Acesso negado.");
					status.setVisibility(0); //VISIBLE
				}
			}
		};
	}
	
	public OnTouchListener touchFields(){
		return new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				TextView status = (TextView)LoginListener.this.loginActivity.findViewById(R.id.labelDeniedAccess);
				status.setVisibility(4); //INVISIBLE
				return false;
			}
		};
	}
	

}
