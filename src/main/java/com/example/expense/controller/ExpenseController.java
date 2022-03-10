package com.example.expense.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense.entity.Expense;
import com.example.expense.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		
		return expenseService.getAllExpenses(page).toList();
	}
	
	@GetMapping("/expenses/{id}")
	public  Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
		
	}
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		return expenseService.createExpense(expense);
		
	}
	
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
		
		return expenseService.updateExpense(id, expense);
		
	}
	@GetMapping("/expenses/category")
	public List<Expense> getExpenseByCategory(@RequestParam String category, Pageable page){

	return expenseService.readByCategory(category, page);
	}
	@GetMapping("/expenses/name")
	public List<Expense> getExpenseByName(@RequestParam String name, Pageable page){

	return expenseService.readByName(name,page);
	
	}
	@GetMapping("/expenses/date")
	public List<Expense> getExpensesByDate(@RequestParam(required = false) Date startDate,
			@RequestParam(required=false) Date endDate, Pageable page){
		return expenseService.readByBetweenDate(startDate, endDate, page);
	}
}

