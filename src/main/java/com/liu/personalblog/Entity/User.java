package com.liu.personalblog.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotEmpty(message = "用户名不能为空")
	@Length(max = 32, message = "用户名太长")
	@Column(unique = true)
	private String name;

	@NotEmpty(message = "密码不能为空")
	@Length(max = 16, message = "密码不能超过16位！")
	private String password;

	String avatar;

	// private String salt;
	// private String headUrl;
	// private String role;

	public User() {
	}

	public User(String name) {
		this.name = name;
		this.password = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
