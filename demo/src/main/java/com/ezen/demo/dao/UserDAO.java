package com.ezen.demo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.ezen.demo.model.User;

@Repository
public class UserDAO {
	public static final String path="C:/test/users.txt";
	
	public boolean login(User user) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			while( ( line = br.readLine() )!=null) {
				User newUser = new User(line.split("\\|"));
				if(user.equals(newUser)) {
					br.close();
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
