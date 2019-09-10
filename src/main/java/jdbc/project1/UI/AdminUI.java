package jdbc.project1.UI;

import java.util.Scanner;

import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.AdminDAOImp;
import jdbc.project1.exception.DBException;

public class AdminUI {
	
	static Scanner sc=new Scanner(System.in);
	public static int val;
	public static int requestId;
	public static String name;
	public static String password;
	public static String categoryName;
	public static void adminOptions() {
		
		System.out.println("\n***************************************************************");
		System.out.println("\n1. Send fund Request      2. View donors Response  ");
		System.out.println("\n3. Change Password        4. Add Categories");
		System.out.println("\n5. Close Request          6.Logout ");
		System.out.println("*****************************************************************");
		System.out.println("\nEnter Your choice :");
		 val = sc.nextInt();

	}
	public static void sendRequest() throws Exception {
		try {
			System.out.println("Enter the category Id you need");
			int request_typeId = sc.nextInt();
			System.out.println("Enter the amount needed ");
			double amount = sc.nextDouble();
			AdminDAOImp admindao=new AdminDAO();
			admindao.fund_request(request_typeId, amount);
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert data");
		}
	}
	public static void getRequestId() {
		System.out.println("Enter the Request Id you want to close ");
		 requestId = sc.nextInt();
	}
	public static void loginAadmin() {
		System.out.println("Login Here");
		System.out.println("\nEnter your Name");
		name = sc.next();
		System.out.println("Enter your Password");
		password = sc.next();
	}
	public static String changePsw() {
		
		System.out.println("\nEnter Your new Password");
		password=sc.next();
		return password;
	}
	public static void getCategory() {
		System.out.println("\nEnter the category name ");
		categoryName=sc.next();
	}
	
}
