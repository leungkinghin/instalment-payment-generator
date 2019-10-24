package dev.ipg.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.ipg.constant.ArithmeticConstant;
import dev.ipg.dto.GeneratePlanCriteria;
import dev.ipg.dto.InstalmentDetails;
import dev.ipg.service.PlanService;
import dev.ipg.util.Utility;

@Service
public class PlanServiceImpl implements PlanService {

	private final MathContext infinityDpContext = new MathContext(ArithmeticConstant.INFINITY_DP_SCALE_SIZE, RoundingMode.HALF_UP); 
	
	@Override
	public List<InstalmentDetails> generateRepaymentPlan(GeneratePlanCriteria generatePlanCriteria) {
		int duration = generatePlanCriteria.getDuration();
		
		generatePlanCriteria = calculateRatePerPeriod(generatePlanCriteria);
		BigDecimal annunityPayment = calculateAnnunityPayment(generatePlanCriteria);
		LocalDateTime startDate = generatePlanCriteria.getStartDate();
		List<InstalmentDetails> installmentResultList = new ArrayList<InstalmentDetails>();
	
		for (int numberOfMonth = 0; numberOfMonth < duration; ++numberOfMonth) {
			BigDecimal initialPrincipal = generatePlanCriteria.getLoanAmount();
			BigDecimal interest = calculateInterest(generatePlanCriteria);
			
			// Following is to handle condition when remained principal has become fewer than estimated annunity.
			if (annunityPayment.compareTo(initialPrincipal)>0) {
				annunityPayment = initialPrincipal.add(interest);
			}
			
			BigDecimal remainingOutstandingPrincipal = initialPrincipal.subtract(annunityPayment.subtract(interest));
			
			InstalmentDetails installmentDetails = new InstalmentDetails.Builder()
					.setBorrowerPaymentAmount(annunityPayment)
					// numberOfMonth could be used to deal with starting date at month-end
					// e.g. When starting date is 2019-01-31
					//			1st month payment date : 2019-02-28
					//			2nd month payment date : 2019-03-31 (Not 2019-03-28)
					.setDate(startDate.plusMonths(1*numberOfMonth))
					.setInitialOutstandingPrincipal(initialPrincipal)
					.setInterest(interest)
					.setPrincipal(annunityPayment.subtract(interest))
					.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal)
					.build();
			
			// Assign the loan amount as remaining outstanding principal for the next installment calculation.
			generatePlanCriteria.setLoanAmount(installmentDetails.getRemainingOutstandingPrincipal());
			
			installmentResultList.add(installmentDetails);
		}
		return installmentResultList;
	}
	
	//To convert nominal rate into periodic rate in decimal value. Assign the result in criteria object and return it.
	private GeneratePlanCriteria calculateRatePerPeriod(GeneratePlanCriteria planCriteria) {
		final int daysInMonth = Utility.getDaysInMonth(planCriteria.getStartDate());
		final int daysInYear = Utility.getDaysInYear(planCriteria.getStartDate());
		BigDecimal nominalRateInDecimal = planCriteria.getNominalRate().divide(BigDecimal.valueOf(100));
		BigDecimal ratePerPeriod = nominalRateInDecimal.multiply(BigDecimal.valueOf(daysInMonth)).divide(BigDecimal.valueOf(daysInYear), infinityDpContext);
		planCriteria.setRateInDecimalPerPeriod(ratePerPeriod);
		return planCriteria;
	}
	private BigDecimal calculateAnnunityPayment(GeneratePlanCriteria criteria) {
		//To guarantee ratePerPeriod is calculated already in criteria object.
		if (criteria.getRateInDecimalPerPeriod()==null || criteria.getRateInDecimalPerPeriod() == BigDecimal.ZERO) {
			criteria = this.calculateRatePerPeriod(criteria);
		}
		BigDecimal ratePerPeriod = criteria.getRateInDecimalPerPeriod();
		BigDecimal presentValue = criteria.getLoanAmount();
		BigDecimal totalCompoundRate = ratePerPeriod.add(BigDecimal.ONE).pow(criteria.getDuration());
		BigDecimal annunityPayment = presentValue.multiply(ratePerPeriod).divide(
				BigDecimal.ONE.subtract(BigDecimal.ONE.divide(totalCompoundRate, infinityDpContext)), infinityDpContext);
		return annunityPayment.setScale(ArithmeticConstant.PAYMENT_SCALE_SIZE, RoundingMode.HALF_UP);
		
	}
	private BigDecimal calculateInterest(GeneratePlanCriteria criteria) {
		//To guarantee ratePerPeriod is calculated already in criteria object.
		if (criteria.getRateInDecimalPerPeriod()==null || criteria.getRateInDecimalPerPeriod() == BigDecimal.ZERO) {
			criteria = this.calculateRatePerPeriod(criteria);
		}
		BigDecimal ratePerPeriod = criteria.getRateInDecimalPerPeriod();
		BigDecimal interest = criteria.getLoanAmount().multiply(ratePerPeriod);
		return interest.setScale(ArithmeticConstant.PAYMENT_SCALE_SIZE, RoundingMode.HALF_UP) ;
	}
	
}
