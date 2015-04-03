package SQLHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {
	public static Connection getConnection() throws SQLException,java.lang.ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/nba";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("无法加载jdbc");
		}
        
        String userName = "root";
        String password = "12345678";
        
        Connection c = DriverManager.getConnection(url,"root",password);
        return c;
  }
	 
}
