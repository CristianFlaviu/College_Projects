package com.example.springrmiserver.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springrmiserver.model.Category;
import com.example.springrmiserver.model.Expense;
import com.example.springrmiserver.repository.CategoryRepository;
import com.example.springrmiserver.repository.ExpenseRepository;
import com.example.springrmiserver.repository.UserRepository;



public class HelloWorldRMIimpl implements HelloWorldRMI {

	@Override
    public String sayHelloRmi(String msg) {
        System.out.println("================Server Side ========================");
        System.out.println("Inside Rmi IMPL - Incoming msg : " + msg);
        return "Hello " + msg + " :: Response time - > " + new Date(0);
    }
	
	
	
	private ExpenseRepository expenseRepository;
	
	
	private CategoryRepository categoryRepository;// = new SimpleJpaRepository<Category, Serializable>(Category.class, entityManager);
	
	
	private UserRepository userRepository;
	
	
	
	@Autowired
	public HelloWorldRMIimpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		super();
		this.expenseRepository = expenseRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}
	
	
	private Connection connection;

	public HelloWorldRMIimpl() throws RemoteException{
		
	}

	public Connection getConnection() {
		if (connection == null) {

			try {
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection("jdbc:h2://localhost:8081/", "Expense", "");
				return connection;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public HelloWorldRMIimpl(CategoryRepository categoryRepository, ExpenseRepository expenseRepository) throws RemoteException{
		//super();
		this.categoryRepository = categoryRepository;
		this.expenseRepository = expenseRepository;
	}
	
	public List<List<String>> getExpenses(){
		
		List<List<String>> list = new ArrayList<>();
		Connection conn = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from expense");
			while (rs.next()) {
				//Category cat = new Category((Long) rs.getObject(1), (String) rs.getObject(2));
				List<String> smolList = new ArrayList<>();
				
				smolList.add(String.valueOf(rs.getObject(1)));
				smolList.add(String.valueOf(rs.getObject(2)));
				smolList.add(String.valueOf(rs.getObject(3)));
				smolList.add(String.valueOf(rs.getObject(4)));
				smolList.add(String.valueOf(rs.getObject(5)));
				smolList.add(String.valueOf(rs.getObject(6)));
				list.add(smolList);
				
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void deleteExpense(@PathVariable Long id){
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate("delete from expense where id=" + id);
			conn.close();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense){
//		Expense result= expenseRepository.save(expense);
//		try {
//			return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok().build();
//	}
	public Expense createExpense(List<String> expense) {
		// id description expensedate location category_id user_id
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			//Statement stmt = conn.createStatement();
			//int rs = stmt.executeUpdate("insert into category values (" category[0] + ",' + " + category[1] + "')");
			PreparedStatement stm = conn.prepareStatement("insert into expense values (?, ?, ?, ?, ?, ?)");
			stm.setInt(1, Integer.parseInt(expense.get(0)));
			stm.setString(2, expense.get(1));
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(expense.get(2));
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			stm.setDate(3, sqlDate);
			stm.setString(4, expense.get(3));
			stm.setInt(5, Integer.parseInt(expense.get(4)));
			stm.setInt(6, Integer.parseInt(expense.get(5)));
			stm.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<List<String>> categories() {
		//return categoryRepository.findAll();
		List<List<String>> list = new ArrayList<>();
		Connection conn = null;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from category");
			while (rs.next()) {
				//Category cat = new Category((Long) rs.getObject(1), (String) rs.getObject(2));
				List<String> smolList = new ArrayList<>();
				smolList.add(String.valueOf(rs.getObject(1)));
				smolList.add((String) rs.getObject(2));
				list.add(smolList);
				
				
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	
	
	public Category createCategory(String[] category){
	  //Category result= categoryRepository.save(category);
	//	Category result = new Category(Long.parseLong(category[0]), category[1]);
//		System.out.println("Server received " + result.toString());
//		categoryRepository.save(result);
//		System.out.println("!!!!!!!!!!!!!!!!!");
//		return result;
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			//Statement stmt = conn.createStatement();
			//int rs = stmt.executeUpdate("insert into category values (" category[0] + ",' + " + category[1] + "')");
			PreparedStatement stm = conn.prepareStatement("insert into category values (?, ?)");
			stm.setInt(1, Integer.parseInt(category[0]));
			stm.setString(2, category[1]);
			stm.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category){
		Category result= categoryRepository.save(category);
		return ResponseEntity.ok().body(result);
	}
	
	
	public void deleteCategory(@PathVariable Long id){
		//categoryRepository.deleteById(id);
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate("delete from category where id=" + id);
			conn.close();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public List<String> getCategory(Long id) {
		
		List<String> smolList = new ArrayList<>();
		Connection conn = null;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from category where id=" + id);
			while (rs.next()) {
				//Category cat = new Category((Long) rs.getObject(1), (String) rs.getObject(2));
				smolList.add(String.valueOf(rs.getObject(1)));
				smolList.add((String) rs.getObject(2));
				
				
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return smolList;
	}
	
	public List<String> getUser(Long id) {
		List<String> smolList = new ArrayList<>();
		Connection conn = null;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:~/test","sa","");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where id=" + id);
			while (rs.next()) {
				//Category cat = new Category((Long) rs.getObject(1), (String) rs.getObject(2));
				smolList.add(String.valueOf(rs.getObject(1)));
				smolList.add((String) rs.getObject(2));
				smolList.add((String) rs.getObject(3));
				
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return smolList;
			
	}
}
