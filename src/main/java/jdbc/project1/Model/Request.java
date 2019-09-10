package jdbc.project1.Model;

public class Request {
	private int requestId;
	private String charityName;
	private String dateOfRequest;
	private double amount;
	private String status;
	
	
	@Override
	public String toString() {
		return " [Request Id= " + requestId + "   Charity Name= " + charityName + "   Request Date= " + dateOfRequest
				+ "   Amount= " + amount + "   Request Status= " + status + "]";
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getCharityName() {
		return charityName;
	}
	public void setCharityName(String charityName) {
		this.charityName = charityName;
	}
	public String getDateOfRequest() {
		return dateOfRequest;
	}
	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
