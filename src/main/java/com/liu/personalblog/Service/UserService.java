package com.liu.personalblog.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.liu.personalblog.DAO.LoginTicketDao;
import com.liu.personalblog.DAO.UserDAO;
import com.liu.personalblog.Entity.User;

@Component
@Service
public class UserService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private LoginTicketDao loginTicketDao;

	public User getUser(int userId) {
		return userDao.selectById(userId);
	}

	public User getUserByName(String name) {
		return userDao.selectByName(name);
	}

	public void addUser(User user) {
		userDao.insertUser(user);
	}

	public void deleteUser(Integer id) {
		userDao.deleteById(id);
	}

	public Integer getCount() {
		return userDao.getCount();
	}

	public Map<String, Object> login(String username, String password) {
		User user = userDao.findUser(username, password);
		if (user == null) {
			return null;
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			return map;
		}
	}

	public void register() {

	}

	public void logout() {

	}
}
