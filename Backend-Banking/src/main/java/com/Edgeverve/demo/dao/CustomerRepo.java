package com.Edgeverve.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.Edgeverve.demo.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer, String> {

	Optional<Customer> findByAcid(String acid);
	//Customer findByAcid(String acid);

}
