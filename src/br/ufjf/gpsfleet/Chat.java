package br.ufjf.gpsfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufjf.gpsfleet.listeners.ChatListener;
import br.ufjf.gpsfleet.utils.ChatAdapter;
import br.ufjf.gpsfleet.utils.RowChatInfo;
import android.support.v4.app.ListFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

public class Chat extends ListFragment{
	
	public ListAdapter listAdapter;
	public ChatAdapter adapter; //esse cara tem que sair, deve usar um listAdapter para customizar
	public ArrayList<RowChatInfo> listChat;
	public Button sendButton;
	public EditText textToSend;
	public Thread threadPoll;
	public Thread threadRefresh;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Activity ownerActivity = getActivity();
	    listChat = new ArrayList<RowChatInfo>();
	    adapter = new ChatAdapter(ownerActivity,android.R.layout.simple_list_item_1,listChat);
	    setListAdapter(adapter);
	    adapter.setNotifyOnChange(true);
	    ChatListener chatListener = new ChatListener(this);
	    
	    textToSend = (EditText) ownerActivity.findViewById(R.id.textToSend);
	    
	    sendButton = (Button) ownerActivity.findViewById(R.id.button_send);
	    sendButton.setOnClickListener(chatListener.sendMessage());
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	
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
	

}
