package jdbc.project1.Services;

import java.util.List;

import jdbc.project1.Model.Category;
import jdbc.project1.Model.Request;
import jdbc.project1.Model.User;
import jdbc.project1.UI.UserUI;
import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.AdminDAOImp;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;
import jdbc.project1.exception.DBException;

public class UserServices {

	public static void login(String name, String password) throws Exception { // login() used for donor login process

		User user = new User();

		IUserDAO userdao = new UserDAO();
		user = userdao.findByNamePassword(name, password);
		try {

			String userName = user.getName();
			String userPassword = user.getPassword();

			if (name.equals(userName) && password.equals(userPassword)) {

				System.out.println("Log-in Succesfully ");
				System.out.println("\nWelcome  " + user.getName());
				System.out.println("\nYour account id is: "+user.getId()+" Keep your id safe");
				UserServices.donors_process();
			} else {
				System.out.println("Invalid Name and Password!!!");
				CharityClass.welcomePage();
			}
		} catch (DBException e) {
			throw new DBException("Invalid Name and Password. Enter valid one.");
		}

	}

	public static void donors_process() throws Exception { // donors_process() calls all donors features

		UserUI.displayDonorOption();
		if (UserUI.val > 0 && UserUI.val <= 4) {
			switch (UserUI.val) {
			case 1:
				AdminDAOImp request1 = new AdminDAO();
				List<Request> list = request1.viewRequest(); // Donor can view Admin requests

				for (Request requestlist : list) {
					System.out.println(requestlist);
				}
				
				donors_process();
				break;

			case 2:
				IUserDAO request2 = new UserDAO();
				List<Category> list1 = request2.displayCategory();

				for (Category response : list1) {
					System.out.println(response);
				}
				UserUI.getDonorFund();
				UserDAO.donateFund(UserUI.request_id,UserUI.cate_id, UserUI.donor_id, UserUI.amount); // Donors send fund
				donors_process();
				break;
				
			case 3:
				donors_process();
				break;

			case 4:
				CharityClass.welcomePage();
				break;
			}
		} else {
			System.out.println("Please enter valid option!!! ");
			donors_process();

		}
	}

}
