package dev.ipg.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GeneratePlanRequest {

	private BigDecimal loanAmount = BigDecimal.ZERO;
	private BigDecimal nominalRate = BigDecimal.ZERO;
	private Integer duration = Integer.valueOf(0);
	private LocalDateTime startDate = LocalDateTime.now();
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
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
}
