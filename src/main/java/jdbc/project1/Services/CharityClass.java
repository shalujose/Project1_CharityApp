package jdbc.project1.Services;

import java.util.Scanner;

import jdbc.project1.UI.DonorRegistration;
import jdbc.project1.UI.UserUI;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class CharityClass {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		CharityClass.userType();
	}

	public static void userType() { // userType() check the user is admin or donor
		
		UserUI.firstPage();
		if (UserUI.type == 1) {
			try {
				IUserDAO iu = new UserDAO();
				iu.LoginAdmin();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (UserUI.type == 2) {
			CharityClass.welcomePage();
		} else {
			System.out.println("Select Valid User Type");
			userType();
		}
	}

	public static void welcomePage() { // welcomepage() used makes selection of donor functionalities
		
		UserUI.displayRegisLogin();
		switch (UserUI.op) {
		case 1:
			try {
				DonorRegistration.register();
			} catch (DBException e) {
				e.printStackTrace();
			}

			break;
		case 2:

			try {
				UserUI.getDonorLogin();
				UserServices.login(UserUI.name, UserUI.password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Visit Again Thank you!!!");
			break;

		default:
			System.out.println("\nPlease Enter valid option!!!");
			welcomePage();
		}

	}

}
