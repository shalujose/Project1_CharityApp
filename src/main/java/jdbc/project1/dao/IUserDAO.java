package jdbc.project1.dao;

import java.sql.SQLException;
import jdbc.project1.Model.User;

public interface IUserDAO {
	
	User findByDetails(String name,String password) throws SQLException;
	
}
