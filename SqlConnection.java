package application;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SqlConnection {

	private static final String DATABASE_URL = "jdbc:mysql://localhost/newhotel";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "";
	 public Connection getConnection() throws SQLException
	    {
	       
	        return DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
	    }

	    public void showErrorMessage(SQLException exception)
	    {
	        System.out.println("Error : "+exception.getMessage());
	        System.out.println("Error code : "+exception.getErrorCode());
	    }
	
		
		
		
	}