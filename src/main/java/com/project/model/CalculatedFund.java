package com.project.model;

public class CalculatedFund {
	final Fund originalFund;
	int moneyResourse;
	
	public CalculatedFund(Fund originalFund, int moneyResourse) {
		this.originalFund = originalFund;
		this.moneyResourse = moneyResourse;
	}

	public Fund getOriginalFund() {
		return originalFund;
	}

	public int getMoneyResourse() {
		return moneyResourse;
	}

	public void setMoneyResourse(int moneyResourse) {
		this.moneyResourse = moneyResourse;
	}
}
