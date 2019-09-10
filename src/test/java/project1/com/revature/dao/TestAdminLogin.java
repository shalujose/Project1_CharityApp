package project1.com.revature.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import jdbc.project1.Model.User;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class TestAdminLogin {

	@Test
	public void TestAdmin() {

		IUserDAO userobj = new UserDAO();
		String name = "";
		String password = "";
		User user = null;
		try {
			user = userobj.findByNamePassword(name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNull(user);
	}

	public void TestInvalidAdmin() {

		IUserDAO userobj = new UserDAO();
		String name = "admin";
		String password = "pass123";
		User user = null;
		try {
			user = userobj.findByNamePassword(name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(user);
	}

	public void TestValidAdmin() {

		IUserDAO userobj = new UserDAO();
		String name = "admin";
		String password = "admin@123";
		User user = null;
		try {
			user = userobj.findByNamePassword(name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(user);
	}
}
