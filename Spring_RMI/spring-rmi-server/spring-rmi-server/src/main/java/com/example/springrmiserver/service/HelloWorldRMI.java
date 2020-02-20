package com.example.springrmiserver.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.springrmiserver.model.Category;
import com.example.springrmiserver.model.Expense;


public interface HelloWorldRMI{

	public String sayHelloRmi(String name) throws RemoteException;
//	
	public List<List<String>> getExpenses() throws RemoteException;
	
	public void deleteExpense(Long id) throws RemoteException;
	
	public Expense createExpense(List<String> expense) throws RemoteException;
	
	public List<List<String>> categories() throws RemoteException;
	
	//public ResponseEntity<?> getCategory(Long id);
	
	public Category createCategory(String[] category) throws RemoteException;
	
	public ResponseEntity<Category> updateCategory(Category category) throws RemoteException;
	
	public void deleteCategory(Long id) throws RemoteException;
	public List<String> getCategory(Long id);
	public List<String> getUser(Long id);
}
