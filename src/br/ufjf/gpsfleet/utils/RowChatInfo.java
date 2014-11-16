package br.ufjf.gpsfleet.utils;

public class RowChatInfo {
	public String nickName;
	public String dateTime;
	public String message;
	
	public RowChatInfo(String nickName, String dateTime, String message){
		this.nickName = nickName;
		this.dateTime = dateTime;
		this.message = message;
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
