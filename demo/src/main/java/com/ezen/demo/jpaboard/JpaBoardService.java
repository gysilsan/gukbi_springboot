package com.ezen.demo.jpaboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaBoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public void insert(Board board) {
		board.setWdate(new java.sql.Date(System.currentTimeMillis()));
		boardRepository.save(board);
	}
	
	public List<Board> findAll() {
		return boardRepository.findAll();
	}
	
	public Optional<Board> findById(int num) {
		return boardRepository.findById(num);
	}
	
}
