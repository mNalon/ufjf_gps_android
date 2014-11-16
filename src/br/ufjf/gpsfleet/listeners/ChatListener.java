package br.ufjf.gpsfleet.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import br.ufjf.gpsfleet.Chat;

public class ChatListener {
	private Chat chat;
	
	public ChatListener(Chat a){
		this.chat = a;
	}
	
	public OnClickListener sendMessage(){
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				String textToSend = ChatListener.this.chat.textToSend.getText().toString();
				ChatListener.this.chat.listChat.add(textToSend);
				Log.d("teste",textToSend);
				InputMethodManager imm = (InputMethodManager)chat.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(chat.textToSend.getWindowToken(), 0);
			}
		};
	}
}
