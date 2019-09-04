package jdbc.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import jdbc.project1.Model.User;
import jdbc.project1.Services.AdminServices;
import jdbc.project1.Services.CharityClass;
import jdbc.project1.Util.ConnectionUtil;
import jdbc.project1.exception.DBException;

public class UserDAO implements IUserDAO{
	static Scanner sc=new Scanner(System.in);
	
public static void register(String name,String gender,int age,String address,String email,long phone,String password) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		String sql = "insert into donors_details(name,gender,age,address,email,phone,password) values ( ?,?,?,?,?,?,?)";
		
		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2,gender);
			pst.setInt(3,age);
			pst.setString(4,address);
			pst.setString(5,email);
			pst.setLong(6,phone);
			pst.setString(7,password);
			
			 pst.executeUpdate();
			System.out.println("\nYour registration Successfully completed");
			CharityClass.welcomePage();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert user" , e);
		}
		finally {
			ConnectionUtil.close(con, pst);
		}
				
	}

	public User findByDetails(String name,String password) throws SQLException {
	Connection con = ConnectionUtil.getConnection();
	String sql = "select name,gender,age,address,email,phone,password from donors_details where name = ? and Password = ?";
	PreparedStatement pst = con.prepareStatement(sql);
	pst.setString(1, name);
	pst.setString(2, password);
	ResultSet rs = pst.executeQuery();
	User user = null;

	if (rs.next()) {
		user = toRow(rs);
	}
	return user;
}
	private User toRow(ResultSet rs) throws SQLException {

		String name = rs.getString("name");
		String password=rs.getString("password");		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		return user;
	}
	
	public static void LoginAdmin() throws Exception
	{  
		System.out.println("Login Here");
		System.out.println("\nEnter your Name");
		String name=sc.next();
		System.out.println("Enter your Password");
		String password=sc.next();
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sqls="select name,password from admin_details where name=? and password=?";
			try {
				pst = con.prepareStatement(sqls);
				pst.setString(1,name);  
				pst.setString(2,password);
				 rs = pst.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		 try {
			if(rs.next()) 
			 { 
			 String name1=rs.getString("name");
			 String password1=rs.getString("password");
			 if(name.equals(name1) && password.equals(password1))
				{
				 AdminServices.admin_process();
				}
			 }
			 else {
				 System.out.println("Please enter username and password Carefully");
				 UserDAO.LoginAdmin();
			 }
		 
		 }
		 catch (SQLException e) {
			e.printStackTrace();
		}
		 finally {
				ConnectionUtil.close(con, pst);
			}
		 
		
	}

public static void donateFund(int fundrequest_id,int donor_id,double amount) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		String sql = "insert into transactions(fundrequest_id,donor_id,amount) values ( ?,?,?)";
		
		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1,fundrequest_id);
			pst.setInt(2,donor_id);
			pst.setDouble(3,amount);
			pst.executeUpdate();
			System.out.println("\nYour Transaction Successfully completed\n");
			} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to transfer fund" , e);
		}
		finally {
			ConnectionUtil.close(con, pst);
		}
				
	}

}
