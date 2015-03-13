package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.PlayerPO;
import dataservice.PlayerDataService;

public class Player extends UnicastRemoteObject implements PlayerDataService {
	private static final long serialVersionUID = 1L;

	public Player() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<PlayerPO> getPlayerList() {
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			ResultSet resultSet = sql.executeQuery("select * from players");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");// 编号
				String name = resultSet.getString("name");// 球员名称
				int number = resultSet.getInt("number");// 球衣号码
				String position = resultSet.getString("position");// 位置
				String height = resultSet.getString("height");// 身高（英尺-英存）
				int weight = resultSet.getInt("weight");// 体重（英镑）
				String birth = resultSet.getString("birth");// （月 日，年）
				int age = resultSet.getInt("age");// 年龄
				int exp = resultSet.getInt("exp");// 球龄
				String school = resultSet.getString("school");// 毕业学校

				PlayerPO playerPO = new PlayerPO(id, name, number, position,
						height, weight, birth, age, exp, school);
				players.add(playerPO);
			}
			resultSet.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return players;
	}

	public String getActionPhotoPath(String name) {
		return "src/迭代一数据/players/action/" + name + ".png";
	}

	public String getPortraitPhotoPath(String name) {
		return "src/迭代一数据/players/protrait/" + name + ".png";
	}

	public static void main(String[] args) {
		Player player;
		try {
			player = new Player();
			player.getPlayerList();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
