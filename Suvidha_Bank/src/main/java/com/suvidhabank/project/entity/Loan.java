package com.suvidhabank.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Loan {
	@Id
	private String loanId;
	private String loanType;
	private double loanAmount;
	private double interestRate;
	private double period;
	private boolean isApproved;
	private String remarks;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_Id")
	private Customer customer;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_Id")
	private Employee employee;
	
	@Embedded
	private List<Collateral> collaterals;

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanType=" + loanType + ", loanAmount=" + loanAmount + ", interestRate="
				+ interestRate + ", period=" + period + ", isApproved=" + isApproved + ", remarks=" + remarks
				+ ", employee=" + employee + ", collaterals=" + collaterals + "]";
	}
	
	
	public static double calculateInterest(double period) {
		if(period <= 2)
			return 0.05;
		else if(period >2 && period <5)
			return 0.06;
		else if(period >=5 && period < 8)
			return 0.08;
		else
			return 0.085;
	}
	
	
}
