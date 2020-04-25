package com.example.springrmiclient.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springrmiclient.model.Category;
import com.example.springrmiclient.model.CategoryDTO;
import com.example.springrmiclient.model.Expense;

public interface HelloWorldRMI {
	
	public String sayHelloRmi(String name);
	
	public List<String> getCategory(Long id);
	
	public List<String> getUser(Long id);
	
	public List<List<String>> getExpenses();
	
	
	public Expense createExpense(List<String> expense);
	
	public Category createCategory(String[] list);
	
	
	public void  deleteExpense(Long id);
	
	public void deleteCategory(Long id);
	
	public List<List<String>> categories();
	
	public ResponseEntity<Category> updateCategory(Category category);
	
	//public ResponseEntity<?> getCategory(Long id);
	
	
	
	
}
