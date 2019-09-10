package jdbc.project1.dao;

import java.sql.SQLException;
import java.util.List;
import jdbc.project1.Model.Category;

import jdbc.project1.Model.User;
import jdbc.project1.exception.DBException;

public interface IUserDAO {
	
	User findByNamePassword(String name,String password) throws SQLException, DBException;
	public void register(User user) throws DBException;
	public void LoginAdmin() throws Exception;
	public List<Category> displayCategory() throws SQLException;
}
