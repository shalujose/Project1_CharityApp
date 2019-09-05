package jdbc.project1.UI;

import java.util.Scanner;

import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.AdminDAOImp;
import jdbc.project1.exception.DBException;

public class AdminUI {
	
	static Scanner sc=new Scanner(System.in);
	public static int val;
	public static int requestId;
	public static void adminOptions() {
		
		System.out.println("\n***************************************************************");
		System.out.println("\n1. Send fund Request      2. View donors Response  ");
		System.out.println("\n3. Close Request        4.Logout ");
		System.out.println("*****************************************************************");
		System.out.println("\nEnter Your choice :");
		 val = sc.nextInt();

	}
	public static void sendRequest() throws Exception {
		try {
			System.out.println("Enter the type of request you need");
			String request_type = sc.next();
			System.out.println("Enter the amount needed ");
			double amount = sc.nextDouble();
			AdminDAOImp admindao=new AdminDAO();
			admindao.fund_request(request_type, amount);
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert data");
		}
	}
	public static void getRequestId() {
		System.out.println("Enter the Request Id you want to close ");
		 requestId = sc.nextInt();
	}
}
