package com.liu.personalblog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liu.personalblog.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByName(String name);

	@Query(value = "select count(*) from user", nativeQuery = true)
	public Integer getCount();

	// @Query(value = "select * from user where name=?1 and password=?2",
	// nativeQuery = true)
	public User findByNameAndPassword(String name, String password);

}
