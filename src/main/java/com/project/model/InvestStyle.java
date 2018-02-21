package com.project.model;

public enum InvestStyle {
	Secure(0.2, 0.75, 0.05),
	Balanced(0.30, 0.60, 0.10),
	Agresive(0.40, 0.20, 0.40);
	
	private final double polishFundPart;
	private final double foreignFundPart;
	private final double monetaryFundPart;
	
	private InvestStyle(double polishFundPart, double foreignFundPart, double monetaryFundPart) {
		this.polishFundPart = polishFundPart;
		this.foreignFundPart = foreignFundPart;
		this.monetaryFundPart = monetaryFundPart;
	}
	
	public double getPolishFundPart() {
		return polishFundPart;
	}
	
	public double getForeignFundPart() {
		return foreignFundPart;
	}
	
	public double getMonetaryFundPart() {
		return monetaryFundPart;
	}
}
