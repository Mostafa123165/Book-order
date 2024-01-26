package com.amazon.book_store.Exc;

@SuppressWarnings("serial")
public class NotFoundExc extends RuntimeException {

	public NotFoundExc() {
		super();
	}
	
	public NotFoundExc(String message) {
		super(message);
	}
	
	public NotFoundExc(String message, Throwable cause) {
		super(message, cause);
	}
	
}
