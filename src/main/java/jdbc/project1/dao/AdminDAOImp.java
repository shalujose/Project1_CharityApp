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
	public void fund_request(String request_type,double amount) throws Exception;
}
