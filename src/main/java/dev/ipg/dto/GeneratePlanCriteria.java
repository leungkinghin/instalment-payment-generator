package dev.ipg.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GeneratePlanCriteria {

	private BigDecimal loanAmount = BigDecimal.ZERO;
	private BigDecimal nominalRate = BigDecimal.ZERO;
	private Integer duration = Integer.valueOf(0);
	private BigDecimal rateInDecimalPerPeriod = BigDecimal.ZERO;
	private LocalDateTime startDate = LocalDateTime.now();
	
	public GeneratePlanCriteria() {
		
	}
	public GeneratePlanCriteria(GeneratePlanRequest request) {
		setLoanAmount(request.getLoanAmount());
		setNominalRate(request.getNominalRate());
		setDuration(request.getDuration());
		setStartDate(request.getStartDate());
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(BigDecimal nominalRate) {
		this.nominalRate = nominalRate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public BigDecimal getRateInDecimalPerPeriod() {
		return rateInDecimalPerPeriod;
	}
	public void setRateInDecimalPerPeriod(BigDecimal rateInDecimalPerPeriod) {
		this.rateInDecimalPerPeriod = rateInDecimalPerPeriod;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	
	
}
