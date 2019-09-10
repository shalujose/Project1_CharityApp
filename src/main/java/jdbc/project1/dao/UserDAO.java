package jdbc.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.project1.Model.Category;
import java.util.Scanner;

import jdbc.project1.Model.User;
import jdbc.project1.Services.AdminServices;
import jdbc.project1.Services.CharityClass;
import jdbc.project1.UI.AdminUI;
import jdbc.project1.Util.ConnectionUtil;
import jdbc.project1.exception.DBException;

public class UserDAO implements IUserDAO {
	static Scanner sc = new Scanner(System.in);

	public void register(User user) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		String sql = "insert into donors_details(name,gender,age,address,email,phone,password) values ( ?,?,?,?,?,?,?)";

		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getGender());
			pst.setInt(3, user.getAge());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getEmail());
			pst.setLong(6, user.getPhone());
			pst.setString(7, user.getPassword());

			pst.executeUpdate();
			System.out.println("\nYour registration Successfully completed");
			CharityClass.welcomePage();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert donor data", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	public User findByNamePassword(String name, String password) throws DBException, SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select id,name,gender,age,address,email,phone,password from donors_details where name = ? and Password = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs = pst.executeQuery();

			User user = null;

			if (rs.next()) {
				user = toRow(rs);
			}
			return user;
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}
	}

	private User toRow(ResultSet rs) throws SQLException {

		String name = rs.getString("name");
		String password = rs.getString("password");
		int id = rs.getInt("id");
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setId(id);

		return user;
	}

	public void LoginAdmin() throws Exception {
		AdminUI.loginAadmin();
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sqls = "select name,password from admin_details where name=? and password=?";
		try {
			pst = con.prepareStatement(sqls);
			pst.setString(1, AdminUI.name);
			pst.setString(2, AdminUI.password);
			rs = pst.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (rs.next()) {
				String name1 = rs.getString("name");
				String password1 = rs.getString("password");
				if (AdminUI.name.equals(name1) && AdminUI.password.equals(password1)) {
					AdminServices.admin_process();
				}
			} else {
				System.out.println("\nPlease enter username and password Carefully");
				IUserDAO iudao = new UserDAO();
				iudao.LoginAdmin();
			}

		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Enter valid data ");
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}

	}

	public static void donateFund(int fundrequest_id, int cate_id, int donor_id, double amount) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		String sql = "insert into transactions(fundrequest_id,cate_id,donor_id,amount) values ( ?,?,?,?)";

		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, fundrequest_id);
			pst.setInt(2, cate_id);
			pst.setInt(3, donor_id);
			pst.setDouble(4, amount);
			pst.executeUpdate();
			System.out.println("\nYour Transaction Successfully completed\n");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to transfer fund", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	public List<Category> displayCategory() throws SQLException { // This method is used to display category list 

		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select category_Id,category_name from request_category";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			List<Category> list1 = new ArrayList<Category>(); // ArrayList for display the table data
			while (rs.next()) {
				Category catelist = toRowCategory(rs);
				list1.add(catelist);
			}
			return list1;
		} finally {
			ConnectionUtil.close(con, pst);
			ConnectionUtil.closeRs(rs);
		}
	}
	private Category toRowCategory(ResultSet rs) throws SQLException {

		int cateId = rs.getInt("category_id");
		String cateName = rs.getString("category_name");
		Category catelist = new Category();
		catelist.setCategory_id(cateId);
		catelist.setCategory_name(cateName);
		
		return catelist;
	}
}
