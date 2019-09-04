package jdbc.project1.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Request;
import jdbc.project1.Model.Transaction;

public interface IRequest {
	
	List<Request> viewRequest() throws SQLException;
	List<Transaction> viewResponse() throws SQLException;
	List<Amount> matchAmount() throws SQLException;
}
