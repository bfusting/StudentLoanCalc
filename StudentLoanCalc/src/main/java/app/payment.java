package app;
import java.time.LocalDate;


public class payment {

	private String payNum;
	private String dueDate;
	private String payAmount;
	private String addPayment;
	private String inter;
	private String princ;
	private String balance;
	
	
	public payment(int p, LocalDate d, double pA, double aP, double i, double princ, double bal) {
		
		this.payNum = Integer.toString(p);
		this. dueDate = d.toString();
		this.payAmount = Double.toString(pA);
		this.addPayment = Double.toString(aP);
		this.inter = Double.toString(i);
		this.princ = Double.toString(princ);
		this.balance = Double.toString(bal);
		
	}


	public String getPayNum() {
		return payNum;
	}


	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}


	public String getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate.toString();
	}


	public String getPayAmount() {
		return payAmount;
	}


	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}


	public String getAddPayment() {
		return addPayment;
	}


	public void setAddPayment(String addPayment) {
		this.addPayment = addPayment;
	}


	public String getInter() {
		return inter;
	}


	public void setInter(String inter) {
		this.inter = inter;
	}


	public String getPrinc() {
		return princ;
	}


	public void setPrinc(String princ) {
		this.princ = princ;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}



	
}
