package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.model.CalculatedFund;
import com.project.model.CalculatedRecord;
import com.project.model.CalculatorException;
import com.project.model.Fund;
import com.project.model.FundType;
import com.project.model.InvestStyle;
import com.project.model.Record;
import com.project.model.RecordInProgress;

public class FundCalculator {
		
	static int DIVIDER = 100;

	public CalculatedRecord calculateFunds(Record record) throws CalculatorException {
		RecordInProgress recordInProgress = new RecordInProgress();
		groupFundsByType(record, recordInProgress);
		int unusedResource = record.getMoneyAmount()%DIVIDER;
		recordInProgress.setUnusedResource(unusedResource);
		calculateAvailableResourceForGroups(record, recordInProgress);
		List<CalculatedFund> calculatedFundsList = new ArrayList<>();
		generateCalulatedFundsAndAddTo(recordInProgress);
		
		return  new CalculatedRecord(record, recordInProgress);
	}

	private void groupFundsByType(Record record, RecordInProgress recordInProgress) throws CalculatorException {
		List<Fund> chosenFunds = record.getFundsList();
		if(!chosenFunds.isEmpty()) {
			chosenFunds.forEach(fund -> addToRecordInProgress(fund, recordInProgress));
		} else {
			throw new CalculatorException("Please choose at least 1 Fund");
		}
	}

	private void addToRecordInProgress(Fund fund, RecordInProgress recordInProgress) {
		FundType type = fund.getType();
		switch (type) {
		case Polish:
			recordInProgress.getPolishFunds().add(fund);
			break;
		case Foreign:
			recordInProgress.getForeignFunds().add(fund);
			break;
		case Monetary:
			recordInProgress.getMonetaryFunds().add(fund);
			break;
		}
	}
	

	private void calculateAvailableResourceForGroups(Record record, RecordInProgress recordInProgress) {
		final int availableResource = record.getMoneyAmount() - recordInProgress.getUnusedResource();
		recordInProgress.setUsedResource(availableResource);
		InvestStyle style = record.getStyle();
		recordInProgress.setPolishResource((int)(availableResource*style.getPolishFundPart()));
		recordInProgress.setForeignResource((int)(availableResource*style.getForeignFundPart()));
		recordInProgress.setMonetaryResource((int)(availableResource*style.getMonetaryFundPart()));
	}
	
	private void generateCalulatedFundsAndAddTo(RecordInProgress recordInProgress) {
		List<CalculatedFund> calculatedFundsList = recordInProgress.getCalculatedFundsList();
		calculateFundForList(recordInProgress.getPolishFunds(), recordInProgress.getPolishResource(), calculatedFundsList);
		calculateFundForList(recordInProgress.getForeignFunds(), recordInProgress.getForeignResource(), calculatedFundsList);
		calculateFundForList(recordInProgress.getMonetaryFunds(), recordInProgress.getMonetaryResource(), calculatedFundsList);
	}

	private void calculateFundForList(List<Fund> fundsList, int resource, List<CalculatedFund> calculatedFundsList) {
		List<CalculatedFund> calculatedList = new ArrayList();
		int evenAmountForEachFund = (int) resource/fundsList.size();
		fundsList.forEach(fund -> calculatedList.add(new CalculatedFund(fund, evenAmountForEachFund)));
		checkAndAddUnusedResourceForListIfNeeded(calculatedList, resource);
		calculatedFundsList.addAll(calculatedList);
	}

	private void checkAndAddUnusedResourceForListIfNeeded(List<CalculatedFund> calculatedList, int resource) {
		int sum = 0;
		for(CalculatedFund fund : calculatedList) {
			sum += fund.getMoneyResourse();
		}
		if(sum != resource) {
			int toAdd = resource - sum;
			CalculatedFund firstFund = calculatedList.get(0);
			firstFund.setMoneyResourse(firstFund.getMoneyResourse() + toAdd);
		}
	}
}
