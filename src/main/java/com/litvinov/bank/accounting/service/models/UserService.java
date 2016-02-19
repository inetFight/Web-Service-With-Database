package com.litvinov.bank.accounting.service.models;

import java.util.List;

import org.eclipse.persistence.annotations.OptimisticLocking;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.litvinov.bank.accounting.service.hibernate.basics.dao.DAO;

public class UserService extends DAO implements UserInterface {

	public User createUser(String username, int balance) throws Exception {
		try {
			begin();
			User user = new User();
			user.setName(username);
			user.setBalance(balance);
			getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not create user " + username, e);
		}
	}

	public User getUser(String username) throws Exception {

		try {
			begin();
			Query q = getSession().createQuery("from User where name = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
	}

	public List<User> getAllUsers() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get users " + e);
		}
	}

	public void deleteUser(User user) throws Exception {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete user " + user.getName(), e);
		}
	}

	public boolean checkUser(String username) {

		begin();
		Query q = getSession().createQuery("from User where name = :username");
		q.setString("username", username);
		List<User> user = q.list();
		if (user.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void addBalance(String username, int balance) {

		try {
			close();
			User user = getUser(username);
			begin();
			user.setBalance(balance);
			update(user);
			commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}