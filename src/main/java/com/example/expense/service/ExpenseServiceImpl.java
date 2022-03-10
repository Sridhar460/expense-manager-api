package com.example.expense.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.expense.customexception.ResourceNotFoundException;
import com.example.expense.entity.Expense;
import com.example.expense.repository.ExpenseRepositiry;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepositiry expenseRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
		
	}
	@Override
	public Expense getExpenseById(Long id) {
		
		Optional<Expense> expenses = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
		if(expenses.isPresent()) {
			return expenses.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id "+id);
	}
	@Override
	public void deleteExpenseById(Long id) {
		
		Expense expense = getExpenseById(id);
		expenseRepo.delete(expense);
	}
	@Override
	public Expense createExpense(Expense expense) {
		expense.setUser(userService.getLoggedInUser());  
		System.out.println(userService.getLoggedInUser());
		return expenseRepo.save(expense);
	}
	@Override
	public Expense updateExpense(Long id, Expense expenses) {
		
	Expense existingExpense = getExpenseById(id);
	
	existingExpense.setName(expenses.getName() != null ? expenses.getName() : existingExpense.getName());
	existingExpense.setDescription(expenses.getDescription() != null ? expenses.getDescription() : existingExpense.getDescription());
	existingExpense.setCategory(expenses.getCategory() != null ? expenses.getCategory() : existingExpense.getCategory());
	existingExpense.setDate(expenses.getDate() != null ? expenses.getDate() : existingExpense.getDate());
	existingExpense.setAmount(expenses.getAmount() != null ? expenses.getAmount() : existingExpense.getAmount());
		
	return expenseRepo.save(existingExpense);
	
	}
	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
	
	}
	@Override
	public List<Expense> readByName(String name, Pageable page) {
		
		return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), name, page).toList();
	}
	@Override
	public List<Expense> readByBetweenDate(Date startDate, Date endDate, Pageable page) {
		
		if(startDate == null) {
			startDate = new Date(0);
		}
		if(endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		
		return expenseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate,endDate, page).toList();
	}

}
