package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.Field;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import connection.ConnectionFactory;
import model.Client;
import model.Orders;
import model.Product;

public class GeneralDao {

	protected static final Logger LOGGER = Logger.getLogger(GeneralDao.class.getName());
	
			
	private static String insertStatementString = "INSERT INTO ";		
	private static String findStatementString="SELECT * FROM";	
	private static String deleteStatementString = "DELETE FROM ";	
	private static String getAllStratementString = "SELECT * FROM ";	
	private static String updateStatementString = "UPDATE ";

	
	public static Object findById(int id,String ClassName) {
		Object toReturn = null;		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		findStatementString="SELECT * FROM";
		findStatementString=findStatementString.concat(" "+ClassName+" WHERE ");
		
		try {
			
			if(ClassName.equals("Client"))
				
			{findStatementString=findStatementString.concat(" IdClient=?");
			System.out.println(findStatementString);
				findStatement = dbConnection.prepareStatement(findStatementString);	
				findStatement.setInt(1, id);
				rs = findStatement.executeQuery();		
				rs.next();
			String name = rs.getString("name");
			String address = rs.getString("adress");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			toReturn = new Client(id, name, address, email, age);
			}
			else if(ClassName.equals("Product"))
			{findStatementString=findStatementString.concat(" idProduct=?");
			System.out.println(findStatementString);
			findStatement = dbConnection.prepareStatement(findStatementString);	
			findStatement.setInt(1, id);
			rs = findStatement.executeQuery();		
			rs.next();
				System.out.println("looking for a product");
				String productName=rs.getString("productName");
				int productId=rs.getInt("idProduct");
				int price =rs.getInt("price");
				int quantity=rs.getInt("quantity");
				toReturn=new Product(productId,productName,quantity,price);
			}
			else if(ClassName.equals("Orders"))
			{
				findStatementString=findStatementString.concat(" OrderId=?");
				System.out.println(findStatementString);
				findStatement = dbConnection.prepareStatement(findStatementString);	
				findStatement.setInt(1, id);
				rs = findStatement.executeQuery();		
				rs.next();
				
			 int idclient=rs.getInt("IdClient");
			 int idProduct=rs.getInt("IdProduct");
			 int quantityOrdered=rs.getInt("quantityOrdered");
			 int OrderId=rs.getInt("OrderId");
			 toReturn=new Orders(idclient,idProduct,quantityOrdered,OrderId);
			}
			
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
			
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static int insert(Object aObject) {
		Connection dbConnection = ConnectionFactory.getConnection();
		int insertedId = -1;
		PreparedStatement insertStatement = null;
		insertStatementString = "INSERT INTO ";
		insertStatementString = insertStatementString.concat(aObject.getClass().getSimpleName() + " (");
		java.lang.reflect.Field[] attributes = aObject.getClass().getDeclaredFields();
		for (int i = 0; i < attributes.length - 1; i++) {
			System.out.println("ATTRIBUTE NAME: " + attributes[i].getName());
			insertStatementString = insertStatementString.concat("`" + attributes[i].getName() + "`,");

		}
		insertStatementString = insertStatementString.substring(0, insertStatementString.length() - 1)
				.concat(") VALUES(");

		for (java.lang.reflect.Field field : attributes) {
			insertStatementString = insertStatementString.concat("?,");

		}
		insertStatementString = insertStatementString.substring(0, insertStatementString.length() - 1 - 2).concat(")");

		System.out.println(insertStatementString);

		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			if (aObject.getClass().getSimpleName().equals("Client")) {
				System.out.println("here is a client");

				insertStatement.setString(1, ((Client) aObject).getName());
				insertStatement.setInt(2, ((Client) aObject).getAge());
				insertStatement.setString(3, ((Client) aObject).getAdress());
				insertStatement.setString(4, ((Client) aObject).getEmail());

			}

			else {
				if (aObject.getClass().getSimpleName().equals("Product")) {
					System.out.println("here is a Product");

					insertStatement.setString(1, ((Product) aObject).getProductName());
					insertStatement.setInt(2, ((Product) aObject).getQuantity());
					insertStatement.setInt(3, ((Product) aObject).getPrice());

				} else {
					System.out.println("here is a order");

					if (aObject.getClass().getSimpleName().equals("Orders")) {
						insertStatement.setInt(1, ((Orders) aObject).getIdClient());
						insertStatement.setInt(2, ((Orders) aObject).getIdProduct());
						insertStatement.setInt(3, ((Orders) aObject).getQuantityOrdered());
					}
				}
			}
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;

	}

	public static int deleteByID(int id, String ClassName) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		deleteStatementString = "DELETE FROM ";	
		deleteStatementString = deleteStatementString.concat(ClassName + " Where");
		
		if (ClassName.equals("Client")) {
			System.out.println("trying to delete a client");
			deleteStatementString = deleteStatementString.concat(" idClient=?");
		} else {
			if (ClassName.equals("Product"))

			{
				deleteStatementString = deleteStatementString.concat(" idProduct=?");
			}
			else
			{
				if(ClassName.equals("Orders"))
				{
					deleteStatementString = deleteStatementString.concat(" OrderId=?");
				}
			}
		}
		System.out.println(deleteStatementString);

		try {

			findStatement = dbConnection.prepareStatement(deleteStatementString);
			findStatement.setInt(1, id);
			return findStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

		return -1;

	}

	public static String[][] getAllRows(String ClassName) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String[][] Data = new String[100][100];
		int row = 0;
		getAllStratementString = "SELECT * FROM ";
		getAllStratementString = getAllStratementString.concat(ClassName);
		System.out.println(getAllStratementString);
		try {
			findStatement = connection.prepareStatement(getAllStratementString);
			rs = findStatement.executeQuery();
			rsmd = (ResultSetMetaData) rs.getMetaData();

			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					
					Data[row][i] = rs.getObject(i + 1).toString();

				}
				row++;
			
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return Data;
	}

	public static void update(Object objectType) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		 updateStatementString = "UPDATE ";
		updateStatementString = updateStatementString.concat(objectType.getClass().getSimpleName() + " SET ");

		java.lang.reflect.Field[] attributes = objectType.getClass().getDeclaredFields();
		for (int i = 0; i < attributes.length - 1; i++) {
		
			updateStatementString = updateStatementString.concat("`" + attributes[i].getName() + "`=?,");
		}
		updateStatementString = updateStatementString.substring(0, updateStatementString.length() - 1)
				.concat(" WHERE (`" + attributes[attributes.length - 1].getName() + "`=?)");
		System.out.println(updateStatementString);
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			if (objectType.getClass().getSimpleName().equals("Client")) {
				updateStatement.setString(1, ((Client) objectType).getName());
				updateStatement.setInt(2, ((Client) objectType).getAge());
				updateStatement.setString(3, ((Client) objectType).getAdress());
				updateStatement.setString(4, ((Client) objectType).getEmail());
				updateStatement.setInt(5, ((Client) objectType).getIdClient());
			} else if (objectType.getClass().getSimpleName().equals("Product")) {
				updateStatement.setString(1, ((Product) objectType).getProductName());
				updateStatement.setInt(2, ((Product) objectType).getQuantity());
				updateStatement.setInt(3, ((Product) objectType).getPrice());
				updateStatement.setInt(4, ((Product) objectType).getIdProduct());				
			}
			else if(objectType.getClass().getSimpleName().equals("Orders"))
			{
				updateStatement.setInt(1,((Orders)objectType).getIdClient());
				updateStatement.setInt(2,((Orders)objectType).getIdProduct());			
				updateStatement.setInt(3,((Orders)objectType).getQuantityOrdered());
				updateStatement.setInt(4,((Orders)objectType).getOrderId());
			}
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	public static int getNrRows(String ClassName)	
	{
		Connection connection=ConnectionFactory.getConnection();
	    PreparedStatement findStatement = null;
		ResultSet rs = null;
	    ResultSetMetaData rsmd = null;
	    getAllStratementString = "SELECT * FROM ";
	    getAllStratementString = "SELECT * FROM "+ClassName;   
	    String[][] clientsString=new String[100][100];
	    int row=0;
	    
	    try {
			findStatement=connection.prepareStatement(getAllStratementString);
			rs=findStatement.executeQuery();
			rsmd=(ResultSetMetaData) rs.getMetaData();
	    	
		while(rs.next())
		{row++;
	
			
				
						    
		}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		
	    return row;
	}
	
	public static  ArrayList<Object> selectALL(Object o) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd;
		
		ArrayList<Object> objectsArray = new ArrayList<Object>();
		Statement stmt;
		ResultSet res=null;
		getAllStratementString="SELECT * FROM "+o.getClass().getSimpleName();
		System.out.println(getAllStratementString);
		try {
			findStatement=dbConnection.prepareStatement(getAllStratementString);
			
			 rs=findStatement.executeQuery();
			 rsmd = (ResultSetMetaData) rs.getMetaData();
			 
				while(rs.next())
				{	Object aObject=Class.forName(o.getClass().getName()).newInstance();				
				
					for(java.lang.reflect.Field field: o.getClass().getDeclaredFields()) {
						
							Object value=rs.getObject(field.getName());
						
						field.setAccessible(true);
						
						PropertyDescriptor prop=new PropertyDescriptor(field.getName(),aObject.getClass());
						Method method= prop.getWriteMethod();
						if(method==null)
							System.out.println("error");
					
						method.invoke(aObject,value);
						
						
					}		
					System.out.println(aObject);
					objectsArray.add(aObject);
				
				}
				
					
		

		} catch (SQLException | IllegalArgumentException |  SecurityException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return objectsArray;
		
		
	}
	public static void main(String[] args) {
	
		
	GeneralDao.selectALL(new Orders());
	System.out.println(GeneralDao.getAllRows("Orders")[0][0]);
		
	}
}
