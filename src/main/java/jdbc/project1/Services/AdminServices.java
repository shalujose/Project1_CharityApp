package jdbc.project1.Services;

import java.util.List;
import java.util.Scanner;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Transaction;
import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.IRequest;
import jdbc.project1.exception.DBException;

public class AdminServices {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void admin_process() throws Exception {
		System.out.println("\n=================================================================");
		System.out.println("1. Send fund Request     | 2. View donors Response  ");
		System.out.println("\n3. Close Request  | 4.Logout ");
		System.out.println("=================================================================");
		System.out.println("\nEnter Your choice :");
		int val=sc.nextInt();
		
		if(val>0 && val<=4) {
			switch (val) {
			case 1:
				AdminServices.sendRequest();	//Admin send request to donors
				break;
			case 2:
				IRequest request2=new AdminDAO();
				List<Transaction> list =request2.viewResponse();
				
				for (Transaction translist1 : list) {
					System.out.println(translist1);
				}
				admin_process();
				break;
			case 3:
				IRequest request3=new AdminDAO();
				List<Amount> list1 =request3.matchAmount();
				
				for (Amount translist2 : list1) {
					System.out.println(translist2);
				}
				
				System.out.println("Enter the Request Id you want to close ");
				int requestId=sc.nextInt();
				AdminDAO.closeRequest(requestId);
				
					admin_process();
				break;
			
			
			case 4:
				CharityClass.welcomePage();
			}
		}else {
			System.out.println("Please enter valid option!!! ");
			admin_process();
			
		}
	}
	
	public static void sendRequest() throws Exception{
		try {
		System.out.println("Enter the charity trust name ");
		String charity_name=sc.next();
		System.out.println("Enter the amount needed ");
		double amount=sc.nextDouble();
			AdminDAO.fund_request(charity_name, amount);
		} 
		catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert data");
		}
	}
}
