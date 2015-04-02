package SQLHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {
	public static Connection getConnection() throws SQLException,java.lang.ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/nba";
        Class.forName("com.mysql.jdbc.Driver");
        String userName = "root";
        String password = "12345a";
        Connection con = DriverManager.getConnection(url,userName,password);
        return con;
  }
	 
}
