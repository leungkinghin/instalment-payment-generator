package dev.ipg.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class InstalmentDetails {

	private BigDecimal borrowerPaymentAmount = BigDecimal.ZERO;
	private LocalDateTime date = LocalDateTime.now();
	private BigDecimal initialOutstandingPrincipal = BigDecimal.ZERO;
	private BigDecimal interest = BigDecimal.ZERO;
	private BigDecimal principal = BigDecimal.ZERO;
	private BigDecimal remainingOutstandingPrincipal = BigDecimal.ZERO;
	
	
	public GeneratePlanResponse responseMapper() {
		GeneratePlanResponse response = new GeneratePlanResponse();
		response.setBorrowerPaymentAmount(borrowerPaymentAmount);
		response.setDate(date);
		response.setInitialOutstandingPrincipal(initialOutstandingPrincipal);
		response.setInterest(interest);
		response.setPrincipal(principal);
		response.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);
		return response;
		
	}
	private InstalmentDetails(Builder builder) {
		this.borrowerPaymentAmount = builder.borrowerPaymentAmount;
		this.date = builder.date;
		this.initialOutstandingPrincipal = builder.initialOutstandingPrincipal;
		this.interest = builder.interest;
		this.principal  = builder.principal;
		this.remainingOutstandingPrincipal = builder.remainingOutstandingPrincipal;
	}
	public InstalmentDetails() {
	}
	
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
	
	// Following is Builder class to construct object.
	@Component
	public static class Builder {
		
		private BigDecimal borrowerPaymentAmount = BigDecimal.ZERO;
		private LocalDateTime date = LocalDateTime.now();
		private BigDecimal initialOutstandingPrincipal = BigDecimal.ZERO;
		private BigDecimal interest = BigDecimal.ZERO;
		private BigDecimal principal = BigDecimal.ZERO;
		private BigDecimal remainingOutstandingPrincipal = BigDecimal.ZERO;
		
		
		public Builder setBorrowerPaymentAmount(final BigDecimal borrowerPaymentAmount) {
			this.borrowerPaymentAmount = borrowerPaymentAmount;
			return this;
		}
		public Builder setDate(final LocalDateTime date) {
			this.date = date;
			return this;
		}
		public Builder setInitialOutstandingPrincipal(final BigDecimal initialOutstandingPrincipal) {
			this.initialOutstandingPrincipal = initialOutstandingPrincipal;
			return this;
		}
		public Builder setInterest(final BigDecimal interest) {
			this.interest = interest;
			return this;
		}
		public Builder setPrincipal(final BigDecimal principal) {
			this.principal = principal;
			return this;
		}
		public Builder setRemainingOutstandingPrincipal(final BigDecimal remainingOutstandingPrincipal) {
			this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
			return this;
		}
		public InstalmentDetails build() {
			return new InstalmentDetails(this);
		}
	}

}
