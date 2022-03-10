package com.example.expense.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.expense.entity.Expense;

public interface ExpenseService {
	
	Page<Expense> getAllExpenses(Pageable page); 
	
	Expense getExpenseById(Long id);
	
	void deleteExpenseById(Long id);

	Expense createExpense(Expense expenses);
	
	Expense updateExpense(Long id, Expense expenses);
	
	List<Expense> readByCategory(String category, Pageable page);
	
	List<Expense> readByName(String name, Pageable page);
	
	List<Expense> readByBetweenDate(Date startDate, Date endDate, Pageable page);


}
