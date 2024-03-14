package com.example.demo.dto;

public class EmailDTO {
	public  String to;
	public	String subject;
	public	String content;
	public	boolean isMultipart;
	public	boolean isHtml;
	public	String path;
	public	String newPass;
	
	public  String getTo() {
		return to;
	} 
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isMultipart() {
		return isMultipart;
	}
	public void setMultipart(boolean isMultipart) {
		this.isMultipart = isMultipart;
	}
	public boolean isHtml() {
		return isHtml;
	}
	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	

}
