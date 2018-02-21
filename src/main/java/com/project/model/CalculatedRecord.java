package com.project.model;

import java.util.List;

public class CalculatedRecord {
	
	final List<CalculatedFund> calcFundsList;
	final Record originalRecord;
	final int comittedResource;
	final int unusedResource;
	
	public CalculatedRecord(Record originalRecord, RecordInProgress recordInProgress) {
		this.originalRecord = originalRecord;
		this.calcFundsList = recordInProgress.getCalculatedFundsList();
		this.comittedResource = recordInProgress.getUsedResource();
		this.unusedResource = recordInProgress.getUnusedResource();
	}

	public List<CalculatedFund> getCalcFundsList() {
		return calcFundsList;
	}

	public Record getOriginalRecord() {
		return originalRecord;
	}

	public int getComittedResource() {
		return comittedResource;
	}

	public int getUnusedResource() {
		return unusedResource;
	}
}
