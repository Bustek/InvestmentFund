package com.project.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatedRecord {

	private List<CalculatedFund> calcFundsList = new ArrayList<>();
	private int comittedResource = 0;
	private int unusedResource = 0;

	public CalculatedRecord() {
	};

	public List<CalculatedFund> getCalcFundsList() {
		return calcFundsList;
	}

	public void setCalcFundsList(List<CalculatedFund> calcFundsList) {
		this.calcFundsList = calcFundsList;
	}

	public int getComittedResource() {
		return comittedResource;
	}

	public void setComittedResource(int comittedResource) {
		this.comittedResource = comittedResource;
	}

	public int getUnusedResource() {
		return unusedResource;
	}

	public void setUnusedResource(int unusedResource) {
		this.unusedResource = unusedResource;
	}
}
