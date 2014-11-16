package br.ufjf.gpsfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufjf.gpsfleet.listeners.ChatListener;
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
	public ArrayAdapter<String> adapter; //esse cara tem que sair, deve usar um listAdapter para customizar
	public List<String> listChat;
	public Button sendButton;
	public EditText textToSend;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Activity ownerActivity = getActivity();
	    listChat = new ArrayList<String>(Arrays.asList(new String[] {"Não há nenhuma mensagem"}));
	    adapter = new ArrayAdapter<String>(ownerActivity,
	        android.R.layout.simple_list_item_1, listChat);
	    setListAdapter(adapter);
	    adapter.setNotifyOnChange(true);
	    ChatListener chatListener = new ChatListener(this);
	    
	    textToSend = (EditText) ownerActivity.findViewById(R.id.textToSend);
	    
	    sendButton = (Button) ownerActivity.findViewById(R.id.button_send);
	    sendButton.setOnClickListener(chatListener.sendMessage());
	    
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
