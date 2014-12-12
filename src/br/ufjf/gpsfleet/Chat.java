package br.ufjf.gpsfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.ufjf.gpsfleet.listeners.ChatListener;
import br.ufjf.gpsfleet.network.ChatClient;
import br.ufjf.gpsfleet.utils.ChatAdapter;
import br.ufjf.gpsfleet.utils.RowChatInfo;
import android.support.v4.app.ListFragment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class Chat extends ListFragment{
	
	public ListAdapter listAdapter;
	public ChatAdapter adapter; 
	public ArrayList<RowChatInfo> listChat;
	public ArrayList<RowChatInfo> downloaded;
	public Button sendButton;
	public ImageButton refreshButton;
	public EditText textToSend;
	public ChatClient client;
	public Handler handler;
	public Activity ownerActivity;
	public ProgressDialog progressDialog;
	public static String userId;
	public static String TAG_CHAT = "CHAT";
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.handler = new Handler();
		this.ownerActivity = getActivity();
		userId = Settings.Secure.getString(ownerActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
		
		this.client = new ChatClient(ownerActivity);
	    this.listChat = new ArrayList<RowChatInfo>();
	    this.downloaded = new ArrayList<RowChatInfo>();
	    this.adapter = new ChatAdapter(ownerActivity,android.R.layout.simple_list_item_1,listChat);
	    setListAdapter(adapter);
	    //this.adapter.setNotifyOnChange(true);
	   
	    ChatListener chatListener = new ChatListener(this);
	    
	    textToSend = (EditText) ownerActivity.findViewById(R.id.textToSend);
	    
	    sendButton = (Button) ownerActivity.findViewById(R.id.button_send);
	    sendButton.setOnClickListener(chatListener.sendMessage());
	    
	    refreshButton = (ImageButton) ownerActivity.findViewById(R.id.bt_refresh);
	    refreshButton.setOnClickListener(chatListener.refreshChat());
	    
	    progressDialog = new ProgressDialog(ownerActivity);
	    progressDialog.setMessage("Carregando Chat");
	    progressDialog.setTitle("Carregando...");
	}
	

	
	@Override
	public void onResume() {
		
		// TODO Auto-generated method stub
		super.onResume();
		refreshChat(false);
	}
	
	public void refreshChat(boolean alert) {
		if(alert){
			progressDialog.show();
		}
		Thread threadRefresh = new Thread(new RunnableRefreshChat());
		threadRefresh.start();
	}
//	
//	@Override
//	public void onResume() {
//		 TODO Auto-generated method stub
//		super.onResume();
//		if( threadPoll != null){
//			threadPoll.notify();
//		}
//	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
    // do something with the data
	}
	  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.chat, null);
        return view;
	}
	
	class RunnableRefreshChat implements Runnable{
		
		
		public void run() {
				
			try {
				Chat.this.downloaded = Chat.this.client.getListOfMessages();
				if(!Chat.this.downloaded.isEmpty()){
					if(Chat.this.listChat.isEmpty()){
						Log.d(TAG_CHAT,"refreshChat");
						refreshChat();
					}else{
						if(verifyIsNew()){
							Log.d(TAG_CHAT,"refreshChat");
							refreshChat();
						}
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						progressDialog.hide();
						Toast.makeText(Chat.this.ownerActivity, "Ocorreu um problema de conexão", Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
		
		private boolean verifyIsNew(){
			RowChatInfo previouslastRow = Chat.this.listChat.get(Chat.this.listChat.size() - 1);
			RowChatInfo currentlastRow = Chat.this.downloaded.get(Chat.this.downloaded.size() -1);
			if(previouslastRow.getDateTime().equals(currentlastRow.getDateTime())){
				return false;
			}else{
				return true;
			}
		}
		
		private void refreshChat(){
			Chat.this.handler.post(new Runnable() {	
				@Override
				public void run() {
					Collections.reverse(downloaded);
					Chat.this.listChat = downloaded;
					Chat.this.adapter = new ChatAdapter(ownerActivity,android.R.layout.simple_list_item_1,downloaded);
					setListAdapter(adapter);
					Chat.this.adapter.notifyDataSetChanged();
					progressDialog.hide();
				}
			});
		}
		
		
	}
	

}
