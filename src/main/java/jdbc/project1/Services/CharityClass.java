package jdbc.project1.Services;

import java.util.Scanner;

import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class CharityClass {
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		
		CharityClass.userType();
	}
	public static void userType(){		//userType() check the user is admin or donor
		
		System.out.println("GIVE A HELPING HAND TO THOSE WHO NEED IT");
		System.out.println("****************************************");
		System.out.println("\n1. Admin");
		System.out.println("2. Donor");
		System.out.println("\nSelect Your User type!!!");
		int type=sc.nextInt();
		if(type==1)
		{
			try {
				UserDAO.LoginAdmin();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(type==2) {
		CharityClass.welcomePage();
		}
		else {
			System.out.println("Select Valid User Type");
			userType();
		}
	}
		public static void welcomePage() {		//welcomepage() used for donors functionalities
				
		System.out.println("     The Charity     ");
		System.out.println("**********************");
		System.out.println("\nDonate 4 a better World");
		System.out.println("1. New Registration");
		System.out.println("2. Donate Now / Login");
		System.out.println("3. Exit");
		System.out.println("\nSelect Your Option");
		int op=sc.nextInt();
		
		switch(op)
		{
		case 1:
			try {
				UserServices.register();
			} catch (DBException e) {
				e.printStackTrace();
			}
			
			break;
		case 2:
			System.out.println("Enter your Name :");
			String name=sc.next();
			System.out.println("Enter your password :");
			String password=sc.next();
			try {
				UserServices.login(name, password);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Visit Again Thank you!!!");
			break;
		}

	}
		

}
