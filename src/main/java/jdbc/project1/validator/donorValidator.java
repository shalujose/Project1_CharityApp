package jdbc.project1.validator;

import jdbc.project1.exception.ValidatorException;

public class donorValidator {

	public void checkName(String name) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			System.out.println("Reenter Your name");
			//throw new ValidatorException("Invalid Name");
			
		}
	}
	public void checkAge(int age) {
		if(age==0|| age=='a'-'z') {
			System.out.println("Reenter your name");
		}
	}
	
}