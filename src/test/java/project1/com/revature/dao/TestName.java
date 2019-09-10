package project1.com.revature.dao;

import org.junit.Test;

import jdbc.project1.validator.DonorValidator;

public class TestName {

	@Test
	public void test() {
		String name="shalu";
		try {
			DonorValidator.validName(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	  public void test1() { 
		  String name="shalu123"; 
		  try {
	  DonorValidator.validName(name); 
	  } catch (Exception e) {
	  System.out.println(e.getMessage()); 
	  } 
		  }
	 
}
