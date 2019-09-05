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
import jdbc.project1.Util.ConnectionUtil;
import jdbc.project1.exception.DBException;

public class AdminDAO implements AdminDAOImp { // implementation of AdminDAOImp interface for AdminDAO

	public void fund_request(String request_type, double amount) throws Exception { // This method is used for generate
																					// fund request

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into fund_request(request_type,amount) values (?,?)";
		try {

			pst = con.prepareStatement(sql);
			pst.setString(1, request_type);
			pst.setDouble(2, amount);
			pst.executeUpdate();
			System.out.println("\nYour Request has been successfully sended");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to make fund request ", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	public List<Request> viewRequest() throws SQLException { // This method is used to display fund request from the
																// table

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select request_id,request_type,date_of_request,amount,status from fund_request where status='Open' order by date_of_request";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			List<Request> list = new ArrayList<Request>(); // ArrayList for display the table data
			while (rs.next()) {
				Request requestlist = toRow1(rs);
				list.add(requestlist);
			}
			return list;
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}
	}

	private static Request toRow1(ResultSet rs) throws SQLException {

		int requestId = rs.getInt("request_id");
		String requestName = rs.getString("request_type");
		String dateOfRequest = rs.getString("date_of_request");
		double amount = rs.getInt("amount");
		String status = rs.getString("status");

		Request requestlist = new Request();
		requestlist.setRequestId(requestId);
		requestlist.setCharityName(requestName);
		requestlist.setDateOfRequest(dateOfRequest);
		requestlist.setAmount(amount);
		requestlist.setStatus(status);

		return requestlist;
	}

	public List<Transaction> viewResponse() throws DBException, SQLException {

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select transaction_id,date_of_transaction,fundrequest_id,donor_id,amount from transactions order by fundrequest_id desc limit 5";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			List<Transaction> list = new ArrayList<Transaction>(); // ArrayList for display the transaction data
			while (rs.next()) {
				Transaction response = toRow2(rs);
				list.add(response);
			}

			return list;
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}

	}

	private static Transaction toRow2(ResultSet rs) throws SQLException {

		int transactionId = rs.getInt("transaction_id");
		String dateOfTransaction = rs.getString("date_of_transaction");
		int fundRequestId = rs.getInt("fundrequest_id");
		int donorId = rs.getInt("donor_id");
		double amount = rs.getInt("amount");

		Transaction response = new Transaction();
		response.setTransaction_id(transactionId);
		response.setDate_of_transaction(dateOfTransaction);
		response.setFundrequest_id(fundRequestId);
		response.setDonor_id(donorId);
		response.setAmount(amount);
		return response;
	}

	public List<Amount> matchAmount() throws SQLException {

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			// joins two tables to display Request amount and donated amount
			String sql = "select r.request_id,r.amount,sum(t.amount) as Total from fund_request r inner join transactions t where r.request_id=t.fundrequest_id";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			List<Amount> list = new ArrayList<Amount>(); // ArrayList for display the amounts
			while (rs.next()) {
				Amount displayAmount = toRow3(rs);
				list.add(displayAmount);
			}

			return list;
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}

	}

	private static Amount toRow3(ResultSet rs) throws SQLException {

		int fundRequestId = rs.getInt("request_id");
		double ramount = rs.getInt("amount");
		double tamount = rs.getDouble("Total");

		Amount displayAmount = new Amount();
		displayAmount.setRequestId(fundRequestId);
		displayAmount.setRequestAmount(ramount);
		displayAmount.setTotalAmount(tamount);

		return displayAmount;
	}

	public static void closeRequest(int request_id) throws Exception { // This method is used for generate fund request

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update fund_request set status='Closed' where request_id=?";
		try {

			pst = con.prepareStatement(sql);
			pst.setInt(1, request_id);

			pst.executeUpdate();
			System.out.println("\nYour Request is Closed!!! ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to close request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

}
