package jdbc.project1.Model;

public class Amount {
	private int requestId;
	@Override
	public String toString() {
		return " [RequestId= " + requestId + "   Request Amount= " + requestAmount + "   Donated Amount= " + totalAmount
				+ "]";
	}
	private Double requestAmount;
	private Double totalAmount;
	public Double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(Double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

}
