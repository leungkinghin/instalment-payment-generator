package dev.ipg.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.ipg.dto.GeneratePlanCriteria;
import dev.ipg.dto.GeneratePlanRequest;
import dev.ipg.dto.GeneratePlanResponse;
import dev.ipg.dto.InstalmentDetails;
import dev.ipg.service.PlanService;
import dev.ipg.validator.PlanValidator;
import io.swagger.annotations.ApiOperation;

@RestController
@ApiOperation(value = "Repayment Plan Generation")
public class PlanController {
	
	private PlanValidator validator;
	
	private PlanService planService;
	
	@Autowired
	PlanController(PlanValidator validator, PlanService planService) {
		this.validator = validator;
		this.planService = planService;
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<GeneratePlanResponse>> generatePlans(@RequestBody GeneratePlanRequest request) {
		validator.validate(request);
		
		GeneratePlanCriteria generatePlanDto = new GeneratePlanCriteria(request);
		
		List<InstalmentDetails> result = planService.generateRepaymentPlan(generatePlanDto);
		List<GeneratePlanResponse> responseList = result.stream().map(item -> item.responseMapper()).collect(Collectors.toList());
		return new ResponseEntity<List<GeneratePlanResponse>>(responseList, HttpStatus.OK);
		
	}
}
