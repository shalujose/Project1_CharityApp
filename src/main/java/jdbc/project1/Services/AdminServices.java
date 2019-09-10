package jdbc.project1.Services;

import java.util.List;
import java.util.Scanner;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Category;
import jdbc.project1.Model.Transaction;
import jdbc.project1.UI.AdminUI;
import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.AdminDAOImp;
import jdbc.project1.dao.IUserDAO;
import jdbc.project1.dao.UserDAO;

public class AdminServices {

	static Scanner sc = new Scanner(System.in);
	static AdminDAO admindao=new AdminDAO();

	public static void admin_process() throws Exception {

		AdminUI.adminOptions();
		if (AdminUI.val > 0 && AdminUI.val <= 6) {
			switch (AdminUI.val) {
			case 1:
				IUserDAO request3 = new UserDAO();
				List<Category> list2 = request3.displayCategory();

				for (Category response : list2) {
					System.out.println(response);
					//displayDonor(response));
				}
				AdminUI.sendRequest(); // Admin send request to donors
				admin_process();
				break;
			case 2:
				AdminDAOImp request2 = new AdminDAO();
				List<Transaction> list = request2.viewResponse();

				for (Transaction response : list) {
					System.out.println(response);
				}
				admin_process();
				break;
			case 3:
				AdminUI.changePsw();
				admindao.resetPasword(AdminUI.password);
				admin_process();
				break;
			case 4:
				AdminUI.getCategory();
				admindao.addCategory(AdminUI.categoryName);
				admin_process();
				break;
			case 5:
				AdminDAOImp request4 = new AdminDAO();
				List<Amount> list1 = request4.matchAmount();

				for (Amount displayAmount : list1) {
					System.out.println(displayAmount);
				}

				AdminUI.getRequestId();
				AdminDAO.closeRequest(AdminUI.requestId);

				admin_process();
				break;

			case 6:
				CharityClass.welcomePage();
			}
		} else {
			System.out.println("Please enter valid option!!! ");
			admin_process();

		}
	}
	private static void displayDonor(List<Category> list) {
	       StringBuilder content=new StringBuilder();
	       content.append("\nCategory Id\tCategory Name\t\n");
	       for (Category category : list) {
	           content.append(category.getCategory_id()).append("\t");
	           content.append(category.getCategory_name()).append("\t");
	           
	           content.append("\n");
	       }
	       System.out.println(content);
	}

}
