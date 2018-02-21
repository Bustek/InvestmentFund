package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.model.CalculatedRecord;
import com.project.model.CalculatorException;
import com.project.model.Fund;
import com.project.model.FundType;
import com.project.model.Record;
import com.project.utils.Utils;

@Controller
public class AppController {
	
	@GetMapping("/calculator")
    public String greetingForm(Record record) {
        return "calculator";
    }

    @PostMapping("/calculator")
    public String greetingSubmit(@Valid Record record, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "calculator";
    	}
    	FundCalculator calculator = new FundCalculator();
    	record.getFundsList().addAll(Utils.generateDefaultFunds());
    	try {
    		CalculatedRecord calculatedRecord = calculator.calculateFunds(record);
    		model.addAttribute("calcRecord", calculatedRecord);
    	} catch (CalculatorException e) {
    		// Return error page
    	}
        return "result";
    }
}
