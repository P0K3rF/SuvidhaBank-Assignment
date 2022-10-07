package com.suvidhabank.project.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suvidhabank.project.entity.Collateral;
import com.suvidhabank.project.entity.Customer;
import com.suvidhabank.project.entity.Employee;
import com.suvidhabank.project.entity.Loan;
import com.suvidhabank.project.repository.CollateralRepository;
import com.suvidhabank.project.repository.CustomerRepository;
import com.suvidhabank.project.repository.EmployeeRepository;
import com.suvidhabank.project.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CollateralRepository collateralRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Loan findLoanById(String customerId) {
		Customer c=this.customerRepository.findById(customerId).get();			
		String loanid=c.getLoan().getLoanId();
		Optional<Loan> optl = this.loanRepository.findById(loanid);
		return optl.orElseThrow(() -> new EntityNotFoundException("Loan with specified id not found"));
	}

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public List<Loan> getLoanByEmployeeId(String empId) {
		return this.loanRepository.findByEmployeeEmployeeId(empId);
	}

	public Employee getEmployeeById(String empId) {
		return this.employeeRepository.findById(empId).get();
	}
	
	public boolean updateLoan(String customerId) {
		
			Customer c=this.customerRepository.findById(customerId).get();			
			String loanid=c.getLoan().getLoanId();
			Loan l=this.loanRepository.findById(loanid).get();
			if(l.getRemarks().equals("No collateral submitted"))
			{				
				l.setRemarks("Approved");
				this.loanRepository.save(l);
				return true;
			}
			return false;
		
	}
	
	public Loan applyForLoan(String loanType, double loanAmount, double period, String customerIdentity) throws Exception {
		Loan l = new Loan();
		
		l.setLoanAmount(loanAmount);
		int i=generate(10, 1000);
		l.setLoanId(i+ "");
		l.setLoanType(loanType);
		l.setPeriod(period);
		String s=Integer.valueOf(generate(111, 119)).toString();
		l.setInterestRate(Loan.calculateInterest(period));
		
		l.setEmployee(getEmployeeById(s));
		l.setLoanType(loanType);
		l.setLoanAmount(loanAmount);
		l.setPeriod(period);
		l.setCustomer(this.customerRepository.findById(customerIdentity).get());
		
		this.loanRepository.save(l);
		this.employeeService.approveLoan(s);
		return l;

	}

//	public boolean uploadCollateral(String loanId, List<Collateral> collaterals) {
//
//		Loan l = this.loanRepository.findById(loanId).get();
//		if (collaterals.size() > 0) {
//			for (Collateral c : collaterals) {
////				c.setLoan(l);
////				l.setCollaterals(collaterals);
//			}
//			return true;
//		}
//		return false;
//	}

}
