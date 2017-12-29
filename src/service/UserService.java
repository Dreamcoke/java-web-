package service;

import dao.UserDao;
import model.User;

public class UserService
{
     UserDao UserDao=new UserDao();

	public User find(String name) {
		
		return UserDao.find(name);
	}
}