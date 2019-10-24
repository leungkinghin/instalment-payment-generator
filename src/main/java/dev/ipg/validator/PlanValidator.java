package dev.ipg.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import dev.ipg.dto.GeneratePlanRequest;
import dev.ipg.exception.ApplicationException;

@Component
public class PlanValidator {

	public void validate(GeneratePlanRequest request) {
		if (request==null) {
			throw new ApplicationException("Request is empty.");
		}
		if (request.getLoanAmount()==null) {
			throw new ApplicationException("Missing loan amount.");
		}
		if (request.getLoanAmount().compareTo(BigDecimal.ZERO)<=0) {
			throw new ApplicationException("Loan amount should be larger than zero.");
		}
		if (request.getNominalRate()==null) {
			throw new ApplicationException("Missing nominal rate.");
		}
		if (request.getNominalRate().compareTo(BigDecimal.ZERO)<=0) {
			throw new ApplicationException("Nominal rate should not be negative");
		}
		if (request.getDuration()==null) {
			throw new ApplicationException("Missing duration.");
		}
		if (request.getDuration()<=0) {
			throw new ApplicationException("Duration should be larger than zero.");
		}
		if (request.getStartDate()==null) {
			throw new ApplicationException("Missing start date.");
		}
	}
}
