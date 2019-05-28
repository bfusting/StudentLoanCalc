package app.controller;

import app.StudentCalc;
import app.calcHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TableView<app.payment> table = new TableView<app.payment>();
	
	
	@FXML
	private TextField LoanAmount;

	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField addPayment;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private Label TotalInterest;
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	


	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	
	
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		

		
		
		table.setEditable(true);

		
		
		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
			
		
		LocalDate paymentStart = PaymentStartDate.getValue();
		int nbrOfYears = Integer.parseInt(this.NbrOfYears.getText());
		
		String nbrOfPayments = calcHelper.totalPayments(nbrOfYears, paymentStart);
		
		double interestRate = Double.parseDouble(InterestRate.getText());
		
		lblTotalPayemnts.setText(nbrOfPayments);
		
		LocalDate localDate = PaymentStartDate.getValue();
	 
		
		TotalInterest.setText(calcHelper.totalInterest(interestRate, dLoanAmount, Integer.parseInt(nbrOfPayments)));
		
		//
		// Setting up Data List
		//
		
		double presentValue = dLoanAmount;
		int payNumber = 1;
		double payAmount = calcHelper.paymentAmount(presentValue, interestRate, Integer.parseInt(nbrOfPayments));
		double addPay = Double.parseDouble(this.addPayment.getText());
		
		final ObservableList<app.payment> data =
				FXCollections.observableArrayList(
						new app.payment(1, paymentStart, payAmount, addPay, calcHelper.IPMT(interestRate, 1, Integer.parseInt(nbrOfPayments), presentValue),
								(payAmount - calcHelper.IPMT(interestRate, 1, Integer.parseInt(nbrOfPayments), presentValue) + addPay), (presentValue - (payAmount - calcHelper.IPMT(interestRate, 1, Integer.parseInt(nbrOfPayments), presentValue) + addPay))));
		
		presentValue = presentValue - (payAmount + addPay);
		paymentStart = paymentStart.plusMonths(1);
		payAmount = calcHelper.paymentAmount(presentValue, interestRate, Integer.parseInt(nbrOfPayments));

		
		for(payNumber = 2; payNumber <= Integer.parseInt(nbrOfPayments); payNumber++) {
			data.add(new app.payment(payNumber, paymentStart, payAmount, addPay, calcHelper.IPMT(interestRate, payNumber, Integer.parseInt(nbrOfPayments), presentValue),
					(payAmount - calcHelper.IPMT(interestRate, 1, Integer.parseInt(nbrOfPayments), presentValue) + addPay), (presentValue - ((payAmount - (calcHelper.IPMT(interestRate, 1, Integer.parseInt(nbrOfPayments), presentValue) + addPay)))))
					);
			
			presentValue = presentValue - (payAmount + addPay);
			paymentStart = paymentStart.plusMonths(1);
			payAmount = calcHelper.paymentAmount(presentValue, interestRate, Integer.parseInt(nbrOfPayments));

		}
		
		//
		// Setting Up Table
		//
		
		
		TableColumn payNumCol = new TableColumn("Payment #");
		TableColumn dueDateCol = new TableColumn("Due Date");
		TableColumn paymentAmountCol = new TableColumn("Payment");
		TableColumn additPayCol = new TableColumn("Additional Payment");
		TableColumn interCol = new TableColumn("Interest");
		TableColumn princCol = new TableColumn("Principal");
		TableColumn balCol = new TableColumn("Balance");
		
		
	   
		payNumCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("payNum"));
		
		dueDateCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("dueDate"));
		
		paymentAmountCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("payAmount"));
		
		additPayCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("addPayment"));
		
		interCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("inter"));
		
		princCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("princ"));
		
		balCol.setCellValueFactory(
                new PropertyValueFactory<app.payment, String>("balance"));
		
		
		
		
        table.setItems(data);
   	    table.getColumns().addAll(payNumCol, dueDateCol, paymentAmountCol, additPayCol, interCol, princCol, balCol);
	
		
		
		
		System.out.println(localDate);
	}
}
