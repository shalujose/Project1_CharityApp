package jdbc.project1.validator;

import jdbc.project1.exception.ValidatorException;

public class DonorValidator {

	// validating age
	public static void checkAge(int age) throws ValidatorException {
		if (age == 0 || age == 'a' - 'z') {
			throw new ValidatorException("Invalid age ");
		}
	}

	// validating password
	public static void passwordValidation(String userName, String password) throws ValidatorException {
		// System.out.println("Username:" + userName + ",password:" + password);
		if (password == null) {
			throw new ValidatorException("\nPassword cannot be null ");
		}
		if (password.length() > 15 || password.length() < 6) {
			throw new ValidatorException("Password should be less than 15 and more than 6 characters in length");
		}
		if (password.indexOf(userName) > -1) {
			throw new ValidatorException("Password Should not be same as user name");
		}

		String lowerCaseChars = "(.*[a-z].*)";
		if (!password.matches(lowerCaseChars)) {
			throw new ValidatorException("Password should contain atleast one lower case alphabet");
		}
		String numbers = "(.*[0-9].*)";
		if (!password.matches(numbers)) {
			throw new ValidatorException("Password should contain atleast one number.");
		}
		String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		if (!password.matches(specialChars)) {
			throw new ValidatorException("Password should contain atleast one special character");
		}

	}

	// validating name entered by donor
	public static void validName(String name) throws ValidatorException {

		String numbers = "(.*[0-9].*)";
		if (name.matches(numbers)) {
			throw new ValidatorException("Name should not contain number.");
		}
		String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		if (name.matches(specialChars)) {
			throw new ValidatorException("Name should not contain special character");
		}

	}

	// validating mobile number
	public static void validateMobileNo(Long phoneno) throws ValidatorException {

		String num = String.valueOf(phoneno);
		if (num.length() != 10)
			throw new ValidatorException("\nInvalid Mobile Number");
	}

	public static void validateEmail(String email) throws ValidatorException {
		String mailId = "^(.+)@(.+)$";
		if (!email.matches(mailId)) {
			throw new ValidatorException("\n Not a valid email id ");
		}
	}

}
