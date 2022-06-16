package com.ezen.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
	private HttpServletRequest request;
	
	public IndexService() {}
	
	public IndexService(HttpServletRequest request) {
		this.request = request;
	}
	
	@Autowired	/* Dependency Injection, Setter Injection */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public List<String> getGugu(int dan) {
		if(dan<2) dan=2;
		List<String> list = new ArrayList<>();
		
		for(int i=1; i<=9; i++) {
			list.add(String.format("%d * %d = %d", dan, i, dan * i));
		}
		return list;
	}
}
