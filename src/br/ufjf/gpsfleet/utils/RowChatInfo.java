package br.ufjf.gpsfleet.utils;

public class RowChatInfo {
	public String nickName;
	public String dateTime;
	public String message;
	public String id;
	
	

	public RowChatInfo(String nickName, String dateTime, String message, String id){
		this.nickName = nickName;
		this.dateTime = dateTime;
		this.message = message;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
