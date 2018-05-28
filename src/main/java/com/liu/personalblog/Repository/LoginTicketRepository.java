package com.liu.personalblog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liu.personalblog.Entity.LoginTicket;

public interface LoginTicketRepository extends
		JpaRepository<LoginTicket, Integer> {

}
