package jdbc.project1.UI;

import java.util.Scanner;

import jdbc.project1.Model.User;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class donorRegistration {
static Scanner sc = new Scanner(System.in);
	
	public static void register() throws DBException // register() used for donor registration process
	{
		System.out.println("Enter Your Name");
		String name = sc.next();
		System.out.println("Enter Your gender  Male/Female");
		String gender = sc.next();
		System.out.println("Enter your age");
		int age = sc.nextInt();
		System.out.println("Enter your address");
		String address = sc.next();
		System.out.println("Enter your email-id");
		String email = sc.next();
		System.out.println("Enter your Phone number");
		long phone = sc.nextLong();
		System.out.println("Enter Your password");
		String password = sc.next();

		User user =new User();
		IUserDAO userdao=new UserDAO();   //user object is created 
		user.setName(name);
		user.setGender(gender);
		user.setAge(age);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		userdao.register(user);
	}


}
