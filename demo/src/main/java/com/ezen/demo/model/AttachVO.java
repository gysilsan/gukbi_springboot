package com.ezen.demo.model;

import org.springframework.stereotype.Component;

@Component
public class AttachVO {
	private int num;
	private int pnum;
	private String fname;
	private String fpath;
	
	
	@Override
	public boolean equals(Object obj) {
		AttachVO other = (AttachVO) obj;
		return this.num == other.num;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s", num, fname);
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	
	
}
