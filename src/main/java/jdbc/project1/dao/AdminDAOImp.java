package jdbc.project1.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Request;
import jdbc.project1.Model.Transaction;
import jdbc.project1.exception.DBException;

public interface AdminDAOImp {
	
	List<Request> viewRequest() throws SQLException;
	List<Transaction> viewResponse() throws SQLException, DBException;
	List<Amount> matchAmount() throws SQLException;
	public void fund_request(int category_Id,double amount) throws Exception;
	public void resetPasword(String password) throws Exception;
	public void addCategory(String category_name) throws Exception;
}
