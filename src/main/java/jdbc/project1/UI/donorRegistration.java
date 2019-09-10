package jdbc.project1.UI;

import java.util.Scanner;

import jdbc.project1.Model.User;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;
import jdbc.project1.exception.ValidatorException;
import jdbc.project1.validator.DonorValidator;

public class DonorRegistration {
	static Scanner sc = new Scanner(System.in);

	public static void register() throws DBException // register() used for donor registration process
	{
		boolean b = false;
		System.out.println("Enter Your Name");
		String name = sc.next();
		while (b == false) {
			try {
				DonorValidator.validName(name);
				b = true;
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				System.out.println("Re-Enter Your Name");
				name = sc.next();
			}
		}
		System.out.println("Enter Your gender  M/F");
		String gender = sc.next();

		System.out.println("Enter your age");
		int age = sc.nextInt();
		boolean b2 = false;
		while (b2 == false) {
			try {
				DonorValidator.checkAge(age);
				b2 = true;
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter your age");
				age = sc.nextInt();
			}
		}
		System.out.println("Enter your address");
		String address = sc.next();
		System.out.println("Enter your email-id");
		String email = sc.next();
		boolean b4 = false;
		while (b4 == false) {
			try {
				DonorValidator.validateEmail(email);
				b4 = true;
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter your email-id");
				email = sc.next();
			}
		}
		System.out.println("Enter your Phone number");
		long phone = sc.nextLong();
		boolean b3 = false;
		while (b3 == false) {
			try {
				DonorValidator.validateMobileNo(phone);
				b3 = true;
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter your Phone number");
				phone = sc.nextLong();
			}
		}
		System.out.println("Enter Your password");
		String password = sc.next();
		boolean b1 = false;
		while (b1 == false) {
			try {
				DonorValidator.passwordValidation(name, password);
				b1 = true;

			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				System.out.println("Re-Enter Your password");
				password = sc.next();
			}

			User user = new User();
			IUserDAO userdao = new UserDAO(); // user object created
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

}
