package com.ezen.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UploadVO 
{
	private int num;
	private String writer;
	private java.sql.Date udate;
	private String description;
	private String fpath;
//	private List<String> fnames = new ArrayList<>(); //첨부파일명
	private List<AttachVO> attach = new ArrayList<>(); //첨부파일명

	
	@Override
	public boolean equals(Object obj) {
		UploadVO other = (UploadVO) obj;
		return this.num == other.num;
	}
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s", num, writer, udate);
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
	
//	public List<String> getFnames() {
//		return fnames;
//	}
//	public void setFnames(List<String> fnames) {
//		this.fnames = fnames;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public List<AttachVO> getAttach() {
		return attach;
	}
	public void setAttach(List<AttachVO> attach) {
		this.attach = attach;
	}
	
}
