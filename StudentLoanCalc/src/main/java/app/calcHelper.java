package app;

import java.time.LocalDate;

import org.apache.poi.ss.formula.functions.Finance;;

public class calcHelper {

	
	
 public static String totalPayments(int term, LocalDate date) {
		String result;
		result = Integer.toString(((term - (date.getYear() - 2019)) * 12) - date.getMonthValue());
		
		return result;	
}
 
 public static String totalInterest(double rate, double principle, int nbrOfPayments) {
 
 String result;
 result = Double.toString((calcHelper.paymentAmount(principle, rate, nbrOfPayments) * nbrOfPayments) - principle);
 
 return result;
 

}
 
 
 public static double paymentAmount(double presentValue, double rate, int nbrOfPayments) {
	 
	return Finance.pmt(rate, nbrOfPayments, presentValue) * (-1);
 }
 
 
 public static double IPMT(double rate, int period, int nbrOfPayments, double presentValue) {
	return Finance.ipmt(rate, period, nbrOfPayments, presentValue) * (-1);
 }
 
 
 public static double PPMT(double rate, int period, int nbrOfPayments, double presentValue) {
	return Finance.ppmt(rate, period, nbrOfPayments, presentValue) * (-1);
 }
}