package jdbc.project1.Model;

public class Transaction {
	
	private int transaction_id;
	private String date_of_transaction;
	private int fundrequest_id;
	private int donor_id;
	private double amount;
	public int getTransaction_id() {
		return transaction_id;
	}
	@Override
	public String toString() {
		return "Transaction [Transaction Id= " + transaction_id  + "  Date= " +  date_of_transaction
				+ "  Request Id= " + fundrequest_id  + "  Donor Id= " + donor_id  + "  Amount= " + amount + "]";
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getDate_of_transaction() {
		return date_of_transaction;
	}
	public void setDate_of_transaction(String date_of_transaction) {
		this.date_of_transaction = date_of_transaction;
	}
	public int getFundrequest_id() {
		return fundrequest_id;
	}
	public void setFundrequest_id(int fundrequest_id) {
		this.fundrequest_id = fundrequest_id;
	}
	public int getDonor_id() {
		return donor_id;
	}
	public void setDonor_id(int donor_id) {
		this.donor_id = donor_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
