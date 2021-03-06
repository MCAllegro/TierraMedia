package services;

import java.util.List;


import model.User;

import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class UserService {

	public List<User> list() {
		return DAOFactory.getUserDAO().findAll();
	}

	public User create(String username, String password, Integer coins, Double time) {
		User user = new User(-1, username, password, coins, time, false);
		user.setPassword(password);

		if (user.isValid()) {
			DAOFactory.getUserDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}
	
	public User find(Integer id) {
		return DAOFactory.getUserDAO().find(id);
	}
	
	public User update(Integer id, String username, String password, Integer coins, Double time) {

		UserDAO userDAO = DAOFactory.getUserDAO();
		User user = userDAO.find(id);

		user.setUsername(username);
		user.setPassword(password);
		user.setCoins(coins);
		user.setTime(time);
		

		if (user.isValid()) {
			userDAO.update(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}
	
	public void erase(Integer id) {
		

		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.erase(id);
	}

}
