package com.project.model;

public class Fund {
	private int ID;
	private String name;
	private FundType type;

	public Fund(int ID, String name, FundType type) {
		this.ID = ID;
		this.name = name;
		this.type = type;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public FundType getType() {
		return type;
	}
}
