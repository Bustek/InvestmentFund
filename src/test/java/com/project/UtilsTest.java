package com.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import com.project.model.Fund;
import com.project.utils.Utils;

public class UtilsTest {
	
	@Test
	public void generateDefaultFundsShouldReturnFundsList() {
		List<Fund> fundsList = Utils.generateDefaultFunds();
		
		assertFalse(fundsList.isEmpty());
		assertEquals(5, fundsList.size());
	}
	
}
