package com.ezen.demo.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	public static final String path = "C:/test/board.txt";
	
	public boolean save(Board board) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path, true));
			pw.printf("%s|%s\n", board.getTitle(),board.getContents());
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
