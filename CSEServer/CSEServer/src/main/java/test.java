import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import data.SqlManager;

public class test {
	public static void main(String[] args) {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists test");

			sql.execute("create table test(id int not null auto_increment,"
					+ "matchID int not null default -1,"
					+ "part int not null default -1,"
					+ "score varchar(20) not null default 'null',"
					+ "haha varchar(20) not null default 'null',"
					+ "primary key(id));");
			String dataString = ";haha";
			String[] data = dataString.split(";");
			System.out.println(data[0]);
			int count = 1;
			sql.execute("insert test values(" + (count++) + ",1,1,'" + data[0]
					+ "','" + data[1] + "')");
			System.out.println(count);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
