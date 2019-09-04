package jdbc.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Request;
import jdbc.project1.Model.Transaction;
import jdbc.project1.Services.AdminServices;
import jdbc.project1.Util.ConnectionUtil;
import jdbc.project1.exception.DBException;

public class AdminDAO implements IRequest{	//implementation of IRequest interface for AdminDAO
	
	public static void fund_request(String charity_name,double amount) throws Exception {	//This method is used for generate fund request 
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into fund_request(charity_name,amount) values (?,?)";
		try {
			
			pst = con.prepareStatement(sql);
			pst.setString(1,charity_name);
			pst.setDouble(2,amount);
			pst.executeUpdate();
			System.out.println("\nYour Request has been successfully sended");
				AdminServices.admin_process();
		}	
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert user" , e);
		}
		finally {
			ConnectionUtil.close(con, pst);
		}
				
	}

	public List<Request> viewRequest() throws SQLException {	//This method is used to display fund request from the table

	Connection con = ConnectionUtil.getConnection();
	
	String sql = "select request_id,charity_name,date_of_request,amount,status from fund_request where status='Open' order by date_of_request";
	PreparedStatement pst = con.prepareStatement(sql);
	ResultSet rs = pst.executeQuery();
	List<Request> list = new ArrayList<Request>();	//ArrayList for display the table data
	while (rs.next()) {
		Request translist = toRow1(rs);
		list.add(translist);
	}
	return list;
	}

	private static Request toRow1(ResultSet rs) throws SQLException {

	int requestId = rs.getInt("request_id");
	String charityName = rs.getString("charity_name");
	String dateOfRequest = rs.getString("date_of_request");
	double amount = rs.getInt("amount");
	String status = rs.getString("status");
	
	Request translist = new Request();
	translist.setRequestId(requestId);
	translist.setCharityName(charityName);
	translist.setDateOfRequest(dateOfRequest);
	translist.setAmount(amount);
	translist.setStatus(status);

	return translist;
	}

	public List<Transaction> viewResponse() throws SQLException {

	Connection con = ConnectionUtil.getConnection();
	
	String sql = "select transaction_id,date_of_transaction,fundrequest_id,donor_id,amount from transactions order by fundrequest_id desc limit 5";
	PreparedStatement pst = con.prepareStatement(sql);
	ResultSet rs = pst.executeQuery();
	List<Transaction> list = new ArrayList<Transaction>();	//ArrayList for display the transaction data
	while (rs.next()) {
		Transaction translist1 = toRow2(rs);
		list.add(translist1);
	}
	
	return list;


}
	private static Transaction toRow2(ResultSet rs) throws SQLException {

		int transactionId = rs.getInt("transaction_id");
		String dateOfTransaction = rs.getString("date_of_transaction");
		int fundRequestId = rs.getInt("fundrequest_id");
		int donorId = rs.getInt("donor_id");
		double amount = rs.getInt("amount");
		
		Transaction translist1 = new Transaction(); 
		translist1.setTransaction_id(transactionId);
		translist1.setDate_of_transaction(dateOfTransaction);
		translist1.setFundrequest_id(fundRequestId);
		translist1.setDonor_id(donorId);
		translist1.setAmount(amount);
		return translist1;
	}
	public List<Amount> matchAmount() throws SQLException {
					
		Connection con = ConnectionUtil.getConnection();
		
		//joins two tables to display Request amount and donated amount
		String sql = "select r.request_id,r.amount,sum(t.amount) as Total from fund_request r inner join transactions t where r.request_id=t.fundrequest_id";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Amount> list = new ArrayList<Amount>();	//ArrayList for display the amounts 
		while (rs.next()) {
			Amount translist2 = toRow3(rs);
			list.add(translist2);
		}
		
		return list;
		
		}
	private static Amount toRow3(ResultSet rs) throws SQLException {

		int fundRequestId = rs.getInt("request_id");
		double ramount = rs.getInt("amount");
		double tamount = rs.getDouble("Total");
	
		Amount translist2 = new Amount();
		translist2.setRequestId(fundRequestId);
		translist2.setRequestAmount(ramount);
		translist2.setTotalAmount(tamount);
		
		return translist2;
	}
	
	public static void closeRequest(int request_id) throws Exception {	//This method is used for generate fund request 
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update fund_request set status='Closed' where request_id=?";
		try {
			
			pst = con.prepareStatement(sql);
			pst.setInt(1,request_id);
			
			pst.executeUpdate();
			System.out.println("\nYour Request is Closed!!! ");		
		}	
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to close request" , e);
		}
		finally {
			ConnectionUtil.close(con, pst);
		}
				
	}

	
}
