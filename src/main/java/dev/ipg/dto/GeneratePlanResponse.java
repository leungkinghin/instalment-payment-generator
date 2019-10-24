package dev.ipg.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class GeneratePlanResponse {
	
	private BigDecimal borrowerPaymentAmount = BigDecimal.ZERO;
	private LocalDateTime date = LocalDateTime.now();
	private BigDecimal initialOutstandingPrincipal = BigDecimal.ZERO;
	private BigDecimal interest = BigDecimal.ZERO;
	private BigDecimal principal = BigDecimal.ZERO;
	private BigDecimal remainingOutstandingPrincipal = BigDecimal.ZERO;
	public BigDecimal getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}
	public void setBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public BigDecimal getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}
	public void setInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}
	public void setRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}
	
	
	
}
