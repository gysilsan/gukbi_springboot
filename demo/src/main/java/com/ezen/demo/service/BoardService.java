package com.ezen.demo.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.model.Board;
import com.ezen.demo.model.BoardDAO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BoardService() {}
	
	public BoardService(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	@Autowired
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public boolean save(Board board) {
		return dao.save(board);
	}
}
