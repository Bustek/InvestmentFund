package com.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.NumberFormat;

public class Record {

	@Min(100)
	@Max(2147483647)
	private int moneyAmount;
	private InvestStyle style;
	private List<Fund> fundsList = new ArrayList<>();

	public Record() {
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public InvestStyle getStyle() {
		return style;
	}

	public void setStyle(InvestStyle style) {
		this.style = style;
	}

	public List<Fund> getFundsList() {
		return fundsList;
	}

	public void setFundsList(List<Fund> fundsList) {
		this.fundsList = fundsList;
	}
}
