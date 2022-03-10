package com.example.expense.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expense.entity.Expense;

@Repository
public interface ExpenseRepositiry extends JpaRepository<Expense, Long> {
	
	Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);
	
	Page<Expense> findByUserIdAndNameContaining(Long userId, String name, Pageable page);  //Containing keyword
	
	Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page); //Between keyword
	
	Page<Expense> findByUserId(Long userId, Pageable page);
	
	Optional<Expense> findByUserIdAndId(Long userId, Long Id); //And keyword

}
