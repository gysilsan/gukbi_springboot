package com.ezen.demo.model;

import java.util.Objects;

public class Upload_backup {
	private int num;
	private String writer;
	private java.sql.Date udate;
	private String comments;
	
	public Upload_backup() {}
	public Upload_backup(int num, String writer, java.sql.Date udate, String comments) {
		this.num = num;
		this.writer = writer;
		this.udate = udate;
		this.comments = comments;
	}
	public Upload_backup(String[] token) {
		this(Integer.valueOf(token[0]),token[1],java.sql.Date.valueOf(token[2]),token[3]);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(num);
	}
	@Override
	public boolean equals(Object obj) {
		Upload_backup upload = (Upload_backup) obj;
		return this.num == upload.num;
	}
	@Override
	public String toString() {
		return String.format("%d|%s|%s|%s", num, writer, udate, comments);
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public java.sql.Date getUdate() {
		return udate;
	}
	public void setUdate(java.sql.Date udate) {
		this.udate = udate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
