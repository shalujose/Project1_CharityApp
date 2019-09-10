package jdbc.project1.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserUI {
	static Scanner sc = new Scanner(System.in);
	public static int val = 0;
	public static int op=0;
	public static int type;
	public static int request_id;
	public static int cate_id;
	public static int donor_id;
	public static double amount;
	public static String name;
	public static String password;

	public static void displayDonorOption() {
		System.out.println("\n***************************************************************");
		System.out.println("1. View Requests          2. Donate Fund      ");
		System.out.println("\n3.View Transactions     4. Logout          ");
		System.out.println("*****************************************************************");
		System.out.println("\nEnter Your Choice :");
		val = sc.nextInt();
	}

	public static void getDonorFund() {
		System.out.println("Enter the Request id you want to donate :");
		try {
			request_id = sc.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid input");
		}
		System.out.println("Enter the category Id");
		cate_id=sc.nextInt();
		System.out.println("Enter your donor id :");
		donor_id = sc.nextInt();
		System.out.println("Enter the amount you want to donate");
		amount = sc.nextDouble();
	}
	public static void getDonorLogin() {
		System.out.println("Enter your Name :");
		name=sc.next();
		System.out.println("Enter your password :");
		password=sc.next();
	}
	public static void displayRegisLogin() {
		System.out.println("     \nThe Charity     ");
		System.out.println("**********************");
		System.out.println("\nDonate 4 a better World");
		System.out.println("\n1. New Registration");
		System.out.println("2. Donate Now / Login");
		System.out.println("3. Exit");
		System.out.println("\nSelect Your Option");
		 op=sc.nextInt();
	}
	public static void firstPage() {
		
		System.out.println("\nGIVE A HELPING HAND TO THOSE WHO NEED IT");
		System.out.println("****************************************");
		System.out.println("\n1. Admin");
		System.out.println("2. Donor");
		System.out.println("\nSelect Your User type!!!");
		try {
		type = sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("\nPlease enter valid option");
			}
		}
	
	
}
