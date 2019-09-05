package jdbc.project1.Services;

import java.util.List;
import java.util.Scanner;

import jdbc.project1.Model.Amount;
import jdbc.project1.Model.Transaction;
import jdbc.project1.UI.AdminUI;
import jdbc.project1.dao.AdminDAO;
import jdbc.project1.dao.AdminDAOImp;

public class AdminServices {

	static Scanner sc = new Scanner(System.in);

	public static void admin_process() throws Exception {

		AdminUI.adminOptions();
		if (AdminUI.val > 0 && AdminUI.val <= 4) {
			switch (AdminUI.val) {
			case 1:
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
				AdminDAOImp request3 = new AdminDAO();
				List<Amount> list1 = request3.matchAmount();

				for (Amount displayAmount : list1) {
					System.out.println(displayAmount);
				}

				AdminUI.getRequestId();
				AdminDAO.closeRequest(AdminUI.requestId);

				admin_process();
				break;

			case 4:
				CharityClass.welcomePage();
			}
		} else {
			System.out.println("Please enter valid option!!! ");
			admin_process();

		}
	}

}
