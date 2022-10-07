package com.suvidhabank.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suvidhabank.project.dto.Loandto;
import com.suvidhabank.project.entity.Collateral;
import com.suvidhabank.project.entity.Customer;
import com.suvidhabank.project.entity.Loan;
import com.suvidhabank.project.repository.CollateralRepository;
import com.suvidhabank.project.service.CustomerService;
import com.suvidhabank.project.service.LoanService;

@RestController
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CollateralRepository collateralRepository;
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/customers")
	public String home() {
		return "home";
	}
	
	@PostMapping("/customer")
	public boolean addCustomer(@RequestBody Customer customer) {
		return this.customerService.insertCustomer(customer);
	}
	
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable String id) {
		System.out.println(id);
	return this.customerService.findCustomerById(id);
	}
	
	@GetMapping("/loan/{id}")
	public Loan getLoanById(@PathVariable String id) {
		return this.loanService.findLoanById(id);
	}
	

	@PostMapping("/loan")
	public Loan applyForLoan(@RequestBody Loandto loandto) throws Exception 
	{	
		
		return this.loanService.applyForLoan(loandto.getType(),loandto.getAmount(),loandto.getPeriod(),loandto.getIdentity());
	}
	@PostMapping("/collateral/{cid}")
	public boolean addCollat(@RequestBody Collateral collateral,@PathVariable String cid) {
		this.collateralRepository.save(collateral);
		return this.loanService.updateLoan(cid);
		
	}
	
}
