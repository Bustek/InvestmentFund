package com.project.model;

import java.util.ArrayList;
import java.util.List;

public class RecordInProgress {
	
	private List<Fund> polishFunds = new ArrayList();
	private List<Fund> foreignFunds = new ArrayList();
	private List<Fund> monetaryFunds = new ArrayList();
	
	private List<CalculatedFund> calculatedFundsList = new ArrayList();
	
	public List<CalculatedFund> getCalculatedFundsList() {
		return calculatedFundsList;
	}
	private int polishResource;
	private int foreignResource;
	private int monetaryResource;
	
	private int unusedResource;
	private int usedResource;
	
	public List<Fund> getPolishFunds() {
		return polishFunds;
	}
	public List<Fund> getForeignFunds() {
		return foreignFunds;
	}
	public List<Fund> getMonetaryFunds() {
		return monetaryFunds;
	}
	public int getPolishResource() {
		return polishResource;
	}
	public void setPolishResource(int polishResource) {
		this.polishResource = polishResource;
	}
	public int getForeignResource() {
		return foreignResource;
	}
	public void setForeignResource(int foreignResource) {
		this.foreignResource = foreignResource;
	}
	public int getMonetaryResource() {
		return monetaryResource;
	}
	public void setMonetaryResource(int monetaryResource) {
		this.monetaryResource = monetaryResource;
	}
	public int getUnusedResource() {
		return unusedResource;
	}
	public void setUnusedResource(int unusedResource) {
		this.unusedResource = unusedResource;
	}
	public int getUsedResource() {
		return usedResource;
	}
	public void setUsedResource(int usedResource) {
		this.usedResource = usedResource;
	}
}
