package com.ezen.demo.model;

import java.util.Objects;

public class Attach {
	private int num;
	private String fname;
	private String fpath;
	
	public Attach() {}
	public Attach(String[] token) {
		this(Integer.valueOf(token[0]), token[1], token[2]);
	}
	public Attach(int num, String fname, String fpath) {
		this.num = num;
		this.fname = fname;
		this.fpath = fpath;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(num);
	}
	@Override
	public boolean equals(Object obj) {
		Attach other = (Attach) obj;
		return this.num == other.num;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d|%s|%s", num, fname, fpath);
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
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	
	
}
