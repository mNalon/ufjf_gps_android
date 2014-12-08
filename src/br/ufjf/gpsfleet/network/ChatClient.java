package br.ufjf.gpsfleet.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufjf.gpsfleet.R;
import br.ufjf.gpsfleet.utils.RowChatInfo;
import android.content.Context;
import android.util.Log;
import android.view.View;

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
	public boolean postMessage(String message, String userId){
		releaseConnections();
		String url = this.host + this.pathPostMessage;
		HttpPost httpost = new HttpPost(url);
		JSONObject holder = new JSONObject();
		StringEntity se = null;
		try {
			holder.put("message", message);
			holder.put("user_id", userId);
			se = new StringEntity(holder.toString(),"UTF-8");
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
	    httpost.setHeader("Content-type", "application/json; charset=UTF-8");
	    httpost.setHeader("Accept-Encoding", "UTF-8");
	    BasicHttpParams basicHttpParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(basicHttpParams, 3000);
	    HttpConnectionParams.setSoTimeout(basicHttpParams, 3000);

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
	
	public ArrayList<RowChatInfo> getListOfMessages(){
		releaseConnections();
		String url = this.host + this.pathGetAllMessages;
		HttpGet request = new HttpGet(url);
		ArrayList<RowChatInfo> listMessages = new ArrayList<>();
		HttpResponse response;
		try {
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

	        if (entity != null) {

	            // A Simple JSON Response Read
	            InputStream instream = entity.getContent();
	            String result = convertStreamToString(instream);
	            // now you have the string representation of the HTML request
	            System.out.println("RESPONSE: " + result);
	            instream.close();
	            JSONObject myObject = new JSONObject(result);
	            JSONArray messages = myObject.getJSONArray("messages");
	            for(int i=0; i<messages.length(); i++){
	            	JSONObject messageInfo = messages.getJSONObject(i);
	            	String message = messageInfo.getString("message");
	            	String timestamp = messageInfo.getString("time_stamp");
	            	String userId = messageInfo.getString("user_id");
	            	RowChatInfo rowChatInfo = new RowChatInfo("", timestamp, message,userId);
	            	listMessages.add(rowChatInfo);
	            }
	        }
	        // Headers
//	        org.apache.http.Header[] headers = response.getAllHeaders();
//	        for (int i = 0; i < headers.length; i++) {
//	            System.out.println(headers[i]);
//	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
//			if(response != null){
//				EntityUtils.consume(response.getEntity());
//			}
			
		}
			 
		

		return listMessages;
	}
	
	
	
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	private void releaseConnections(){
		httpclient.getConnectionManager().closeExpiredConnections(); 
		httpclient.getConnectionManager().closeIdleConnections(0, TimeUnit.NANOSECONDS);
	}
	

}
