package com.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.project.controller.FundCalculator;
import com.project.model.CalculatedFund;
import com.project.model.CalculatedRecord;
import com.project.model.CalculatorException;
import com.project.model.Fund;
import com.project.model.FundType;
import com.project.model.InvestStyle;
import com.project.model.Record;
import com.project.utils.Utils;

public class FundCalculatorTest {

	@Test (expected = CalculatorException.class)
	public void emptyFundsListInRecordShouldThrowException() throws CalculatorException {
		FundCalculator calculator = new FundCalculator();
		Record record = new Record();
		record.getFundsList().clear();
		calculator.calculateFunds(record);
	}
	
	@Test
	public void calculatorShouldCalculateUnusedResource() throws CalculatorException {
		FundCalculator calculator = new FundCalculator();
		Record record = new Record();
		record.setStyle(InvestStyle.Secure);
		record.setMoneyAmount(1004);
		Fund fund1 = new Fund(1, "polish1", FundType.Polish);
		Fund fund2 = new Fund(2, "foreign1", FundType.Foreign);
		Fund fund3 = new Fund(3, "monetary1", FundType.Monetary);
		record.getFundsList().add(fund1);
		record.getFundsList().add(fund2);
		record.getFundsList().add(fund3);
		CalculatedRecord calculatedRecord = calculator.calculateFunds(record);
		assertEquals(4, calculatedRecord.getUnusedResource());
	}
	
	@Test
	public void calculatorShouldCalculateProperValuesForGivenStyle() throws CalculatorException {
		FundCalculator calculator = new FundCalculator();
		Record record = new Record();
		record.setStyle(InvestStyle.Secure);
		record.setMoneyAmount(100);
		Fund fund1 = new Fund(1, "polish1", FundType.Polish);
		Fund fund2 = new Fund(2, "foreign1", FundType.Foreign);
		Fund fund3 = new Fund(3, "monetary1", FundType.Monetary);
		record.getFundsList().add(fund1);
		record.getFundsList().add(fund2);
		record.getFundsList().add(fund3);
		CalculatedFund calcFundToCheck = null;
		CalculatedRecord calculatedRecord = calculator.calculateFunds(record);
		for(CalculatedFund calculatedFund : calculatedRecord.getCalcFundsList()) {
			if(calculatedFund.getOriginalFund() == fund1) {
				calcFundToCheck = calculatedFund;
			}
		}
		assertNotEquals(null, calcFundToCheck);
		assertEquals(20, calcFundToCheck.getMoneyResourse());
	}
}
