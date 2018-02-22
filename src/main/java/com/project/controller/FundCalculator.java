package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.project.model.CalculatedFund;
import com.project.model.CalculatedRecord;
import com.project.model.CalculatorException;
import com.project.model.Fund;
import com.project.model.FundType;
import com.project.model.InvestStyle;
import com.project.model.Record;

public class FundCalculator {

	private int DIVIDER = 100;

	/**
	 * Split money declared in record between Funds provided in record according to given style.
	 *
	 * @param  record created using Form in Calculator.html
	 * @return      calculatedRecord which contains calculatedFunds
	 */
	public CalculatedRecord splitMoney(Record record) throws CalculatorException {
		CalculatedRecord calculatedRecord = new CalculatedRecord();
		calculatedRecord.setUnusedResource(record.getMoneyAmount() % DIVIDER);
		calculatedRecord.setComittedResource(record.getMoneyAmount() - calculatedRecord.getUnusedResource());
		calculatedRecord.setCalcFundsList(getCalculatedFunds(record, calculatedRecord.getComittedResource()));

		return calculatedRecord;
	}

	private List<CalculatedFund> getCalculatedFunds(Record record, int comittedResource) throws CalculatorException {
		List<CalculatedFund> calculatedFundsList = new ArrayList<>();
		Map<FundType, List<Fund>> fundsByType = getGroupedFundsByType(record);
		for (FundType type : FundType.values()) {
			calculatedFundsList
					.addAll(getComputedFundsForGroup(fundsByType.get(type), comittedResource, record.getStyle(), type));
		}
		return calculatedFundsList;
	}

	private Map<FundType, List<Fund>> getGroupedFundsByType(Record record) throws CalculatorException {
		Map<FundType, List<Fund>> fundsByType = new HashMap<>();
		List<Fund> chosenFunds = record.getFundsList();
		if (!chosenFunds.isEmpty()) {
			for (FundType type : FundType.values()) {
				List<Fund> groupedByType = chosenFunds.stream().filter(fund -> fund.getType() == type)
						.collect(Collectors.toList());
				fundsByType.put(type, groupedByType);
			}
		} else {
			throw new CalculatorException("Please choose at least 1 Fund");
		}
		return fundsByType;
	}

	private List<CalculatedFund> getComputedFundsForGroup(List<Fund> groupedFundsList, int comittedResource,
			InvestStyle style, FundType type) throws CalculatorException {
		int resourceForGroup = (int) (comittedResource * getTypePercentage(type, style));
		return getCalculatedFunds(groupedFundsList, resourceForGroup);
	}

	private double getTypePercentage(FundType type, InvestStyle style) throws CalculatorException {
		switch (type) {
		case Polish:
			return style.getPolishFundPart();
		case Foreign:
			return style.getForeignFundPart();
		case Monetary:
			return style.getMonetaryFundPart();
		default:
			throw new CalculatorException("Couldn't resolve type Fund type: " + type.toString());
		}
	}

	private List<CalculatedFund> getCalculatedFunds(List<Fund> fundsList, int resource) {
		List<CalculatedFund> calculatedList = new ArrayList<>();
		int evenAmountForEachFund = (int) resource / fundsList.size();
		fundsList.forEach(fund -> calculatedList.add(new CalculatedFund(fund, evenAmountForEachFund)));
		checkAndAddUnusedResourceForListIfNeeded(calculatedList, resource);
		return calculatedList;
	}

	private void checkAndAddUnusedResourceForListIfNeeded(List<CalculatedFund> calculatedList, int resource) {
		int sum = calculatedList.stream().mapToInt(CalculatedFund::getMoneyResourse).sum();
		if (sum != resource) {
			int toAdd = resource - sum;
			CalculatedFund firstFund = calculatedList.get(0);
			firstFund.setMoneyResourse(firstFund.getMoneyResourse() + toAdd);
		}
	}
}
