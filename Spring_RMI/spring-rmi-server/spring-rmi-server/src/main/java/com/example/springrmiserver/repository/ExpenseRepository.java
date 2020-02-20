package com.example.springrmiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrmiserver.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>  {
	
}
