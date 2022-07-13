package com.ezen.demo.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaTestController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	public String jpaTest() {
		return "JPA Test";
	}
	
	@GetMapping("/save")
	public ResponseEntity<User> saveUser() {
		User user = new User();
		user.setUname("smith");
		user.setEmail("smith@gmail.com");
		
		User savedUser = userRepository.save(user); // 레코드 추가
		boolean saved = user.getUname().equals(savedUser.getUname());
		
		logger.debug("num={}, uname={}, email={}",
				savedUser.getNum(), savedUser.getUname(), savedUser.getEmail());
		
		return new ResponseEntity<>(savedUser, HttpStatus.OK); // HTTP 헤더에 넣어주는 것
		//OK(정상처리), NOT_FOUND(찾을 수 없다), INTERNAL_SERVER_ERROR(서버 로직에서 문제=>화면에 제대로 보여줄 수 없을 때)
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> list() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/findbyid/{num}")
	public ResponseEntity<User> findById(@PathVariable("num") int num) {
		Optional<User> op = userRepository.findById(num);
		
		if(op.isEmpty()) {
			//검색실패 메세지
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(op.get(), HttpStatus.OK);
		
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> findByUname(@PathVariable("name") String name) {
		return new ResponseEntity<>(userRepository.findByUname(name), HttpStatus.OK);
	}
	
	@GetMapping("/name/and/email/{name}/{email}")
	public ResponseEntity<List<User>> findByNameAndEmail(@PathVariable("name") String name,
														@PathVariable("email") String email) {
		return new ResponseEntity<>(userRepository.findByUnameAndEmail(name, email), HttpStatus.OK);
	}
	
	@GetMapping("/update/{num}/{name}/{email}")
	public ResponseEntity<User> updateUser(@PathVariable("num") int num,
											@PathVariable("name") String name,
											@PathVariable("email") String email) {
		User user = new User();
		user.setNum(num);
		user.setUname(name);
		user.setEmail(email);
		
		User updated = userRepository.save(user); // 레코드 추가
		return new ResponseEntity<>(updated, HttpStatus.OK); // HTTP 헤더에 넣어주는 것
		//OK(정상처리), NOT_FOUND(찾을 수 없다), INTERNAL_SERVER_ERROR(서버 로직에서 문제=>화면에 제대로 보여줄 수 없을 때)
	}
	
	@GetMapping("/delete/{num}")
	public String deleteUser(@PathVariable("num") int num) {
		Optional<User> op = userRepository.findById(num);
		
		if(op.isPresent()) {
			userRepository.deleteById(num);
			return "삭제 성공";
		} else {
			return "삭제 실패";
		}
	}
	
	@GetMapping("/jpql/{start}/{end}")
	public ResponseEntity<List<User>> getAllBetJPQL(
							@PathVariable("start") int start,
							@PathVariable("end") int end) {
		List<User> list = userRepository.findAllNumBetJPQL(start, end);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/jpql2/{start}/{end}")
	public ResponseEntity<List<User>> getAllBetJPQL2(
							@PathVariable("start") int start,
							@PathVariable("end") int end) {
		List<User> list = userRepository.findAllNumBetJPQL2(start, end);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/sql/{start}/{end}")
	public ResponseEntity<List<User>> getAllBetSQL(
							@PathVariable("start") int start,
							@PathVariable("end") int end) {
		List<User> list = userRepository.findAllNumBetSQL(start, end);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
