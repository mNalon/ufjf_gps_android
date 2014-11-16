package br.ufjf.gpsfleet.utils;

import java.util.List;

import br.ufjf.gpsfleet.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatAdapter extends ArrayAdapter<RowChatInfo>{
	
	private final LayoutInflater mInflater;

	public ChatAdapter(Context context, int resource) {
		super(context, resource);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
 
        if (convertView == null) {
            view = mInflater.inflate(R.layout.row_chat, parent, false);
            
        } else {
            view = convertView;
        }
        RowChatInfo item = getItem(position);
        TextView messageLabel = (TextView)view.findViewById(R.id.message);
        TextView timeStampLabel = (TextView)view.findViewById(R.id.time_stamp);
        
        messageLabel.setText(item.getMessage());
        timeStampLabel.setText(item.getDateTime());
 
        return view;
    }
	
}
