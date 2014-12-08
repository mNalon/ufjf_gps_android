package br.ufjf.gpsfleet.utils;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.gpsfleet.Chat;
import br.ufjf.gpsfleet.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatAdapter extends ArrayAdapter<RowChatInfo>{
	
	private final LayoutInflater mInflater;
	private final Context mContext;

	public ChatAdapter(Context context, int resource, ArrayList<RowChatInfo> list) {
		super(context, resource, list);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = context;
		// TODO Auto-generated constructor stub
	}
	
	public void setData(List<RowChatInfo> data) {
        clear();
        if (data != null) {
            for (RowChatInfo appEntry : data) {
                add(appEntry);
            }
        }
    }
 
    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        RowChatInfo item = getItem(position);
//        if (convertView == null) {
        	if(item.getId().equals(Chat.userId)){
        		//view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_ufjf));
        		view = mInflater.inflate(R.layout.row_chat_user, parent, false);
        	}else{
//        		view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_ufjf));
        		view = mInflater.inflate(R.layout.row_chat, parent, false);
        	}
            
//        } else {
//            view = convertView;
//        }
        
        
        TextView messageLabel = (TextView)view.findViewById(R.id.message);
        TextView timeStampLabel = (TextView)view.findViewById(R.id.time_stamp);
        
        messageLabel.setText(item.getMessage());
        timeStampLabel.setText(item.getDateTime());

        return view;
    }
	
}
