package br.ufjf.gpsfleet.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufjf.gpsfleet.R;
import android.content.Context;
import android.util.Log;

public class ChatClient {
	private final String host;
	private final String pathPostMessage;
	private final String pathGetAllMessages;
	private String TAG_STATUS = "Status_Request";
	DefaultHttpClient httpclient;
	
	public ChatClient(Context context) {
		this.host = context.getString(R.string.url_chat_host);
		this.pathPostMessage = context.getString(R.string.path_post_message);
		this.pathGetAllMessages = context.getString(R.string.path_get_all_messages);
		this.httpclient = new DefaultHttpClient();
	}
	
	/**
	 * Envia a mensagem para a rota de mensagem.
	 * Caso a mensagem seja entregue com sucesso retorna true
	 * @throws UnsupportedEncodingException 
	 */
	public boolean postMessage(String message){
		String url = this.host + this.pathPostMessage;
		HttpPost httpost = new HttpPost(url);
		JSONObject holder = new JSONObject();
		StringEntity se = null;
		try {
			holder.put("message", message);
			se = new StringEntity(holder.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //passes the results to a string builder/entity
	    //sets the post request as the resulting string
	    httpost.setEntity(se);
	    //sets a request header so the page receving the request
	    //will know what to do with it
	    httpost.setHeader("Accept", "application/json");
	    httpost.setHeader("Content-type", "application/json");

	    try {
			HttpResponse httpResponse = httpclient.execute(httpost);
			int status = httpResponse.getStatusLine().getStatusCode();
			if(status!=HttpStatus.SC_OK){
				Log.i(TAG_STATUS,String.valueOf(status));
				throw new Exception(httpResponse.getStatusLine().getReasonPhrase());	
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

}
