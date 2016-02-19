package com.litvinov.bank.accounting.service.models;

import java.util.List;

public interface UserInterface {
	public User createUser(String username, int balance) throws Exception;
	public User getUser(String username) throws Exception;
	public List<User> getAllUsers() throws Exception;
	public void deleteUser(User user) throws Exception;
	public boolean checkUser(String username);
	public void addBalance(String username, int balance);
}
