package libraryManagementWithJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/library_db";
	private static final String user = "root";
    private static final String password = "Admin"; 
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

	
}
