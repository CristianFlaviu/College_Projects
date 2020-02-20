package connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/sys";
	private static final String USER = "root";
	private static final String PASS = "rectilinie1";

	private static ConnectionFactory singleInstance = new ConnectionFactory();

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
	
	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection();
		 String findStatementString = "SELECT * FROM new_table1";
		 String createStatementString="INSERT INTO Client "+"VALUES('16', 'viorel', '16', 'danesti','email')";
				 
	        PreparedStatement findStatement = null;
	        PreparedStatement createStatement=null;
	        ResultSet rs = null;
	        ResultSetMetaData rsmd = null;

	        try {
	            findStatement = connection.prepareStatement(findStatementString);
	            createStatement=connection.prepareStatement(createStatementString);
	           
	           int a=createStatement.executeUpdate();
	            rs = findStatement.executeQuery();
	            rsmd = (ResultSetMetaData) rs.getMetaData();
	            

	            for(int i = 1; i <= rsmd.getColumnCount(); i++) {
	                System.out.println(rsmd.getColumnName(i));
	            }

	            while(rs.next()) {
//	                int id = rs.getInt("idclient");
//	                String name = rs.getString("name");
//	                String address = rs.getString("address");
//	                String email = rs.getString("email");
//	                System.out.println(rs.getRow());
//	                rs.getRow();
	                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
	                    System.out.print(rs.getObject(i).toString() + " |2 ");
	                }
	                System.out.println();
	                // System.out.println(id + " | " + name + " | " + address + " | " + email);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	}
	}}

