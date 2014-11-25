package br.ufjf.gpsfleet.listeners;

import java.util.Calendar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import br.ufjf.gpsfleet.Chat;
import br.ufjf.gpsfleet.utils.RowChatInfo;

public class ChatListener {
	private Chat chat;
	
	public ChatListener(Chat a){
		this.chat = a;
	}
	
	public OnClickListener sendMessage(){
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textToSendView = ChatListener.this.chat.textToSend; 
				String textToSend = textToSendView.getText().toString();
				if(textToSend.isEmpty()){
					return;
				}
				ChatListener.this.chat.listChat.add(new RowChatInfo("Teste", "hour", textToSend));
				Log.d("teste",textToSend);
				InputMethodManager imm = (InputMethodManager)chat.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(chat.textToSend.getWindowToken(), 0);
				textToSendView.setText("");
			}
		};
	}
}
