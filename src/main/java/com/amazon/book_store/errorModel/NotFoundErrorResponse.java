package com.amazon.book_store.errorModel;

public class NotFoundErrorResponse {

	private int statuse;
	private String message ;
	
	
	public NotFoundErrorResponse() {
		
	}
	
	public NotFoundErrorResponse(int statuse, String message) {
		this.statuse = statuse;
		this.message = message;
	}
	
	public int getStatuse() {
		return statuse;
	}
	public void setStatuse(int statuse) {
		this.statuse = statuse;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
