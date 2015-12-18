package ch.makery.address.model;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import domain.RateDomainModel;

public class Rate extends RateDomainModel {
	
	public static double getPayment(int cdtScore, int morgatageLoan, int years)
	{
		double interestrate = RateDAL.getRate(cdtScore);
		double IntRate = interestrate/(1200);
		int months  = years*12;
		double monthlypayRate = -1*FinanceLib.pmt(IntRate,months,morgatageLoan,0,false);
			
		return monthlypayRate;
		
	}
}
