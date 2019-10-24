package dev.ipg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ipg.dto.GeneratePlanCriteria;
import dev.ipg.dto.InstalmentDetails;

public interface PlanService{
	List<InstalmentDetails> generateRepaymentPlan(GeneratePlanCriteria requestDto);
}
