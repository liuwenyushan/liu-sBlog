package com.liu.personalblog.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.personalblog.Entity.LoginTicket;
import com.liu.personalblog.Repository.LoginTicketRepository;

@Component
public class LoginTicketDao {

	@Autowired
	LoginTicketRepository loginTicketRespository;

	public void insertLoginTicket(LoginTicket loginTicket) {
		loginTicketRespository.save(loginTicket);
	}

	public LoginTicket selectById(int id) {
		return loginTicketRespository.findOne(id);
	}

	public LoginTicket selectByTicket(String ticket) {
		return null;
	}

	public void updateStatus(String ticket, int status) {

	}

	public void deleteById(int id) {
		loginTicketRespository.delete(id);
	}
}
