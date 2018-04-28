package sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class sqlConnection {
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	
	public void readDatabase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/hsinlin?"
                    + "user=hsinlin&password=110408271&useSSL=false");
		} catch (Exception excep) {
			throw excep;	
		}	
	}
	
	public Connection getConnected() {
		return connection;
	}

	public void close() {
		try {
			if(resultset != null)
				resultset.close();
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}catch (Exception excep) {

		}
		
	}
	
}
