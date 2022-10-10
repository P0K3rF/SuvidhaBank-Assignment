package com.suvidhabank.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suvidhabank.project.entity.Collateral;

public interface CollateralRepository extends CrudRepository<Collateral, String> {
	
	@Query(value = "select * from collateral where loanId=:loanId",nativeQuery = true)
	public List<Collateral> getAllColl(String loanId);
	
}
