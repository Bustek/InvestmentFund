package com.project.utils;

import java.util.ArrayList;
import java.util.List;

import com.project.model.Fund;
import com.project.model.FundType;

public class Utils {
	
	public static List<Fund> generateDefaultFunds(){
		List<Fund> defaultFundsList = new ArrayList();
		Fund fund1 = new Fund(1, "polish1", FundType.Polish);
    	Fund fund2 = new Fund(2, "polish2", FundType.Polish);
    	Fund fund3 = new Fund(3, "foreign1", FundType.Foreign);
    	Fund fund4 = new Fund(4, "monetary1", FundType.Monetary);
    	Fund fund5 = new Fund(5, "monetary2", FundType.Monetary);
    	defaultFundsList.add(fund1);
    	defaultFundsList.add(fund2);
    	defaultFundsList.add(fund3);
    	defaultFundsList.add(fund4);
    	defaultFundsList.add(fund5);
    	
    	return defaultFundsList;
	}
}
