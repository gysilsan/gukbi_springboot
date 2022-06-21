package com.ezen.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.demo.model.Board;
import com.ezen.demo.model.Emp;

@Repository
public class JdbcBoardDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Board> getList() {
		String sql = "SELECT num, LPAD('└ ', (LEVEL-1)*3, '　')||title AS title, "
				+ "contents, author, wdate, pcode FROM board "
				+ "START WITH pcode=0 "
				+ "CONNECT BY PRIOR num=pcode "
				+ "ORDER SIBLINGS BY wdate DESC";
		List<Board> list = jdbcTemplate.query(sql, (rs, i) -> {
			Board board = new Board();
			board.setNum(rs.getInt("NUM"));
			board.setTitle(rs.getString("TITLE"));
			board.setContents(rs.getString("CONTENTS"));
			board.setWdate(rs.getDate("WDATE"));
			board.setPcode(rs.getInt("PCODE"));
			board.setAuthor(rs.getString("AUTHOR"));
			return board;
		});
		return list;
	}
	
	public Board getBoardByNum(int num) {
		String sql = "SELECT * FROM board WHERE num=?";
		List<Board> list = jdbcTemplate.query(sql, (rs, i)-> {
			Board board = new Board();
			board.setNum(rs.getInt("NUM"));
			board.setTitle(rs.getString("TITLE"));
			board.setContents(rs.getString("CONTENTS"));
			board.setWdate(rs.getDate("WDATE"));
			board.setPcode(rs.getInt("PCODE"));
			board.setAuthor(rs.getString("AUTHOR"));
			return board;
		}, num);
		return list.get(0);
	}
	
	public boolean add(Board board) {
		String sql = "INSERT INTO board VALUES(BOARD_NUM.nextval, ?, ?, ?, SYSDATE, ?)";
		int n = jdbcTemplate.update(sql,
				board.getTitle(), board.getContents(), "TEST", board.getPcode());
		return n>0;
	}
	
	public boolean update(Board board) {
		String sql = "UPDATE board SET title=?, contents=? WHERE num=?";
		int n = jdbcTemplate.update(sql, board.getTitle(), board.getContents(), board.getNum());
		return n>0;
	}
	
	public boolean delete(int num) {
		String sql = "DELETE FROM board WHERE num=?";
		int n = jdbcTemplate.update(sql, num);
		return n>0;
	}
}
