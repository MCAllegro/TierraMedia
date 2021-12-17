package services;

import model.User;
import model.nullobjects.NullUser;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public User login(String username, String password) {
		UserDAO userDao = DAOFactory.getUserDAO();
    	User currentUser = userDao.findByUsername(username);
    	
    	if (currentUser.isNull() || !currentUser.checkPassword(password)) {
    		currentUser = NullUser.build();
    	}
    	return currentUser;
	}
	
}
