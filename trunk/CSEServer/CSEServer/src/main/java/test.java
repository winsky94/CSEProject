import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLHelper.SqlManager;

public class test {

	public void a() {
		try {

			ResultSet resultSet = c();
			for (int i = 0; i < 3; i++) {
				System.out.println("第" + i + "次循环");
				while (resultSet.next()) {
					b(resultSet);
				}
				resultSet.beforeFirst();
			}

			resultSet.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void b(ResultSet resultSet) {
		try {
			System.out.println(resultSet.getString("teamName"));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public ResultSet c() {
		try {
			Connection connection = SqlManager.getConnection();
			Statement sql = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = sql.executeQuery("select * from teams");
			sql.close();
			connection.close();
			return resultSet;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		test test = new test();
		test.a();

	}
}
