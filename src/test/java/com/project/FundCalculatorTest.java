package com.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void checkThatCalculatorComputeProperFundValueForGivenRecord() throws CalculatorException {
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
		CalculatedRecord calculatedRecord = calculator.splitGivenMoneyIntoFundsAccordingToChosenStyle(record);
		for(CalculatedFund calculatedFund : calculatedRecord.getCalcFundsList()) {
			if(calculatedFund.getOriginalFund() == fund1) {
				calcFundToCheck = calculatedFund;
			}
		}
		assertEquals(20, calcFundToCheck.getMoneyResourse());
	}

	@Test (expected = CalculatorException.class)
	public void calculatorShouldThrowExceptionForEmptyFundList() throws CalculatorException {
		FundCalculator calculator = new FundCalculator();
		Record record = new Record();
		record.getFundsList().clear();
		calculator.splitGivenMoneyIntoFundsAccordingToChosenStyle(record);
	}
	
	@Test
	public void calculatorShouldComputeUnusedResources()
			throws CalculatorException {
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
		CalculatedRecord calculatedRecord = calculator.splitGivenMoneyIntoFundsAccordingToChosenStyle(record);
		
		assertEquals(4, calculatedRecord.getUnusedResource());
	}
	
	@Test
	public void calculatorShouldAddGroupUnusedResources() throws CalculatorException {
		FundCalculator calculator = new FundCalculator();
		Record record = new Record();
		record.setStyle(InvestStyle.Balanced);
		record.setMoneyAmount(100);
		Fund fund1 = new Fund(1, "polish1", FundType.Polish);
		Fund fund2 = new Fund(2, "foreign1", FundType.Foreign);
		Fund fund3 = new Fund(3, "monetary1", FundType.Monetary);
		Fund fund4 = new Fund(3, "monetary2", FundType.Monetary);
		Fund fund5 = new Fund(3, "monetary3", FundType.Monetary);
		record.getFundsList().add(fund1);
		record.getFundsList().add(fund2);
		record.getFundsList().add(fund3);
		record.getFundsList().add(fund4);
		record.getFundsList().add(fund5);
		CalculatedFund calcFundToCheck = null;
		CalculatedRecord calculatedRecord = calculator.splitGivenMoneyIntoFundsAccordingToChosenStyle(record);
		List<CalculatedFund> monetaryFundsList = new ArrayList<>();
		for(CalculatedFund calculatedFund : calculatedRecord.getCalcFundsList()) {
			if(calculatedFund.getOriginalFund().getType() == FundType.Monetary) {
				monetaryFundsList.add(calculatedFund);
			}
		}
		monetaryFundsList.stream().filter(fund -> fund.getMoneyResourse() == 3).count();
		assertEquals(2, monetaryFundsList.stream().filter(fund -> fund.getMoneyResourse() == 3).count());
		assertEquals(1, monetaryFundsList.stream().filter(fund -> fund.getMoneyResourse() == 4).count());
	}
}
