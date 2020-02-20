package com.example.springrmiclient.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrmiclient.SpringRmiClientApplication;
import com.example.springrmiclient.model.Category;
import com.example.springrmiclient.model.CategoryDTO;
import com.example.springrmiclient.model.Expense;
import com.example.springrmiclient.model.User;
import com.example.springrmiclient.service.HelloWorldRMI;


@RestController
@RequestMapping("/api")
public class ExpenseController{

	@Autowired
	HelloWorldRMI helloWorldRMI;
	
	protected ExpenseController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
//	public ExpenseController() throws RemoteException{
//		//super();
//	}
//	
	@PostMapping("/test")
	public String sayHelloRmi(@RequestBody String name) {
		//helloWorldRMI.sayHelloRmi(name);
		return helloWorldRMI.sayHelloRmi(name);
	}
	
	@GetMapping("/test")
	public String sadHelloRmi() {
		helloWorldRMI.sayHelloRmi("ana");
		return "Hello";
	}
//	
	@GetMapping("/expenses")
	public List<Expense> getExpenses() throws NumberFormatException, ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<List<String>> result = new ArrayList<>();
		List<String> cat = new ArrayList<>();
		List<Expense> finalList = new ArrayList<>();
		Expense expense;
		result = helloWorldRMI.getExpenses();
		for (int i = 0; i < result.size(); i++) {
			cat = result.get(i);
//			String[] splittedCategory = cat.get(4).split(" ");
//			String[] splittedUser = cat.get(5).split(" ");
			List<String> categoryList = helloWorldRMI.getCategory(Long.parseLong(cat.get(4)));
			Category category = new Category(Long.parseLong(categoryList.get(0)), categoryList.get(1));
			List<String> userList = helloWorldRMI.getUser(Long.parseLong(cat.get(5)));
			User user = new User(Long.parseLong(userList.get(0)), userList.get(1), userList.get(2));
			Long categoryId = Long.parseLong(cat.get(4));
			Long userId = Long.parseLong(cat.get(5));
			
			expense = new Expense(Long.parseLong(cat.get(0)), 
												df.parse(cat.get(2)),
												cat.get(1),
												cat.get(3),
												category,
												user);
			finalList.add(expense);
		}
		return finalList;
		
		//return helloWorldRMI.sayHelloRmi("hey");
	}

	@DeleteMapping("/expenses/{id}")
	public ResponseEntity<?>  deleteExpense(@PathVariable Long id){
		helloWorldRMI.deleteExpense(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/expenses")
	public Expense createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException{
		// id description expensedate location category_id user_id
		String user;
		if (expense.getUser() == null) {
			user = "1";
		}
		else {
			user = expense.getUser().getId().toString();
		}
		Random r = new Random();
		Long id = Long.valueOf(r.nextInt(1000));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<>();
		list.add(id.toString());
		list.add(expense.getDescription());
		list.add(df.format(expense.getExpensedate()));
		list.add(expense.getLocation());
		list.add(expense.getCategory().getId().toString());
		list.add(user);
		
		
		helloWorldRMI.createExpense(list);
		  return expense; 
		
		//return ResponseEntity.created(new URI("/api/expenses" + expense.getId())).body(expense);
	}
	
	
	@GetMapping("/categories")
	public List<Category> categories() {
		List<List<String>> result = new ArrayList<>();
		List<String> cat = new ArrayList<>();
		List<Category> finalList = new ArrayList<>();
		Category category;
		result = helloWorldRMI.categories();
		for (int i = 0; i < result.size(); i++) {
			cat = result.get(i);
			category = new Category(Long.parseLong(cat.get(0)), cat.get(1));
			finalList.add(category);
		}
		return finalList;
	}
//	@GetMapping("/category/{id}")
//	public ResponseEntity<?> getCategory(@PathVariable Long id) {
//		ResponseEntity<?> category = helloWorldRMI.getCategory(id);
//		return category.map(response -> ResponseEntity.ok().body(response))
//				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}
	
	@PostMapping("/category")
	public Category createCategory(@Valid @RequestBody Category category) throws URISyntaxException{
		System.out.println("Client " + category.toString());
	
		String[] arr = new String[2];
		arr[0] = category.getId().toString();
		arr[1] = category.getName();
		
	  helloWorldRMI.createCategory(arr);
	  return category; 
//	
	}
	
	
	
	@PutMapping("/category/{id}")
	public void updateCategory(@Valid @RequestBody Category category){
		helloWorldRMI.updateCategory(category);
	}
	
	
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id){
		helloWorldRMI.deleteCategory(id);
		return ResponseEntity.ok().build();
	}
}
