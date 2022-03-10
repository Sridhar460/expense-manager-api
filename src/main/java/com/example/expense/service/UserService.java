package com.example.expense.service;

import com.example.expense.user.User;
import com.example.expense.user.UserModel;

public interface UserService {
	
	User createuser(UserModel user);
	
	User readUser();
	
	User updateUser(UserModel user);
	
	void deleteUser();
	
	User getLoggedInUser();
}
