package br.ufjf.gpsfleet.listeners;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import br.ufjf.gpsfleet.Chat;
import br.ufjf.gpsfleet.network.ChatClient;
import br.ufjf.gpsfleet.utils.ChatAdapter;
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
				final String textToSend = textToSendView.getText().toString();
				if(textToSend.isEmpty()){
					return;
				}
				Thread threadToSendMessage = new Thread(new Runnable() {
					@Override
					public void run() {
						if(ChatListener.this.chat.client.postMessage(textToSend,Chat.userId)){
							ChatListener.this.chat.refreshChat(false);
						}else{
							ChatListener.this.chat.handler.post(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(ChatListener.this.chat.ownerActivity, "Sem conexão!",Toast.LENGTH_SHORT).show();
									
								}
							});
							
						}
					}
				});
				threadToSendMessage.start();
				
				//ChatListener.this.chat.listChat.add(new RowChatInfo("", "", textToSend,Chat.userId));
				InputMethodManager imm = (InputMethodManager)chat.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(chat.textToSend.getWindowToken(), 0);
				textToSendView.setText("");
			}
		};
	}
	
	public OnClickListener refreshChat(){
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChatListener.this.chat.refreshChat(true);
				
			}
		};
	}
	
	
}
