package com.revature.daos;

import com.revature.beans.User;

public interface UserDao {
	public static final UserDao currentUserDao = new UserDaoJdbc();
	
	/**
	 * Takes in a user object and will persist that user
	 * 
	 * @param u
	 */
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	User getCurrentUser();
}