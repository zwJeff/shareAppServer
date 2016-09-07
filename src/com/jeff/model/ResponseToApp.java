package com.jeff.model;


public class ResponseToApp {

	private String status;

	private String message;

	private int total;

	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public ResponseToApp(String status, String message, int total, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.total = total;
		this.result = result;
	}

}
