package jdbc.project1.Services;

import java.util.List;
import java.util.Scanner;

import jdbc.project1.Model.Request;
import jdbc.project1.Model.User;
import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.IRequest;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class UserServices {
	static Scanner sc=new Scanner(System.in);
	
	public static void register() throws DBException	//register() used for donor registration process
	{
	System.out.println("Enter Your Name");
	String name=sc.next();
	System.out.println("Enter Your gender  Male/Female");
	String gender=sc.next();
	System.out.println("Enter your age");
	int age=sc.nextInt();
	System.out.println("Enter your address");
	String address=sc.next();
	System.out.println("Enter your email-id");
	String email=sc.next();
	System.out.println("Enter your Phone number");
	long phone=sc.nextLong();
	System.out.println("Enter Your password");
	String password=sc.next();
	try {
		UserDAO.register(name, gender, age, address, email, phone, password);
	} 
	catch (DBException e) {
		e.printStackTrace();
		throw new DBException("Unable to insert data");
	}
	}
		
		public static void login(String name,String password) throws Exception {	//login() used for donor login process
			
			User user = new User();
			
			IUserDAO userdao=new UserDAO();
			user=userdao.findByDetails(name,password);
			try {
				
				String userName=user.getName();
				String userPassword=user.getPassword();
				
				if(name.equals(userName) && password.equals(userPassword)) {
					
					System.out.println("Log-in Succesfully ");
					System.out.println("\nYour NAME: "+user.getName());	
					UserServices.donors_process();
				}
				else {
					System.out.println("Invalid Name and Password!!!");
					CharityClass.welcomePage();
				}
			} 
			catch (RuntimeException e) {
				throw new RuntimeException("Invalid Account number and Password. Enter valid one.");
			}	
			
			}
		
		public static void donors_process() throws Exception {	//donors_process() is used for performing donors features
			System.out.println("\n***************************************************************");
			System.out.println("1. View Requests   2. Donate Fund      ");
			System.out.println("\n3. Logout          ");
			System.out.println("*****************************************************************");
			System.out.println("\nEnter Your Choice :");
			int val=sc.nextInt();
	
			if(val>0 && val<=3) {
				switch (val) {
				case 1:
					IRequest request1=new AdminDAO();
					List<Request> list =request1.viewRequest();	//Donor can view Admin requests
					
					for (Request translist : list) {
						System.out.println(translist);
					}
					donors_process();
					break;
					
				case 2:
					System.out.println("Enter the Request id you want to donate :");
					int request_id=sc.nextInt();
					System.out.println("Enter your donor id :");
					int donor_id=sc.nextInt();
					System.out.println("Enter the amount you want to donate");
					double amount=sc.nextDouble();
					UserDAO.donateFund(request_id,donor_id,amount);		//Donors send fund for requested trust
					donors_process();
					break;
				
				case 3:
					CharityClass.welcomePage();
					break;
				}
			}
			else {
				System.out.println("Please enter valid option!!! ");
				donors_process();
				
			}
		}

}
