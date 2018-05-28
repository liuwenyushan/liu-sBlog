package com.liu.personalblog.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.personalblog.Entity.User;
import com.liu.personalblog.Repository.UserRepository;

@Component
public class UserDAO {

	@Autowired
	private UserRepository userRepository;

	public void insertUser(User user) {
		userRepository.save(user);
	}

	public User selectById(int id) {
		return userRepository.findOne(id);
	}

	public User selectByName(String name) {
		return userRepository.findByName(name);
	}

	public void deleteById(int id) {
		userRepository.delete(id);
	}

	// 获取用户数量
	public Integer getCount() {
		return userRepository.getCount();
	}

	public User findUser(String username, String password) {
		return userRepository.findByNameAndPassword(username, password);

	}
}
