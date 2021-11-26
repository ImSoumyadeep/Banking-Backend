package com.Edgeverve.demo.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Edgeverve.demo.model.Transactions;


public interface TransactionRepo extends JpaRepository<Transactions, String>{
	
	@Query("from Transactions where debit_acid=?1 or credit_acid=?1")
	List<Transactions> findByAcid(String acid);
}
