package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
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

	public PlayerPO readFromFile(String fileName) {
		PlayerPO player;
		String mname;
		int mnumber;
		String mposition;
		String mheight;
		int mweight;
		String mbirth;
		int mage;
		int mexp;
		String mschool;
		String[] content = new String[9];
		try {

			// File file=new File("13-14_01-01_CHA-LAC");
			File file = new File(fileName);
			if (!file.exists()) {

				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			int i = 0;
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				// System.out.println(temp);
				if (temp.contains("│")) {
					String[] it = temp.split("│");
					String[] nit = it[1].split("║");
					content[i++] = nit[0].trim();
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mname = DirtyDataManager.checkString(fileName, content[0]);
		mnumber = DirtyDataManager.checkNum(fileName, content[1]);
		mposition = content[2];
		mheight = content[3];
		mweight = Integer.parseInt(content[4]);
		mbirth = content[5];
		mage = Integer.parseInt(content[6]);
		mexp = DirtyDataManager.checkExp(fileName, content[7]);
		mschool = DirtyDataManager.checkString(fileName, content[8]);
		player = new PlayerPO(0, mname, mnumber, mposition, mheight, mweight,
				mbirth, mage, mexp, mschool);
		return player;
	}

	public ArrayList<PlayerPO> getPlayerList() {
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		try {
			FileList fl = new FileList("src/迭代一数据/players/info");
			// FileList fl=new
			// FileList("D:/LUCY/Documents/软件工程与计算III/data/迭代一数据/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				players.add(readFromFile(name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}

	public void exportToSql() {
		ArrayList<PlayerPO> players = getPlayerList();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists players");
			sql.execute("create table players(id int not null auto_increment,name varchar(40) not null default 'null',"
					+ "number int not null default 0,position varchar(20) not null default 'null',"
					+ "height varchar(20) not null default 'null',weight int not null default 0,"
					+ "brith varchar(20) not null default 'null',age int not null default 0,exp int not null default 0,"
					+ "school varchar(40)not null default 'null',primary key(id));");
			int count = 1;
			for (PlayerPO player : players) {
				sql.execute("insert players values(" + (count++) + ",'"
						+ player.getName() + "'," + player.getNumber() + ",'"
						+ player.getPosition() + "','" + player.getHeight()
						+ "'," + player.getWeight() + ",'" + player.getBirth()
						+ "'," + player.getAge() + "," + player.getExp() + ",'"
						+ player.getSchool() + "')");

				System.out.println(count);
			}

			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException:" + ex.getMessage());
		}

	}

	public String getActionPhotoPath(String name) {
		// return
		// "D:/LUCY/Documents/软件工程与计算III/data/迭代一数据/players/action/"+name+".png";
		return "src/迭代一数据/players/action/" + name + ".png";
	}

	public String getPortraitPhotoPath(String name) {
		// return
		// "D:/LUCY/Documents/软件工程与计算III/data/迭代一数据/players/protrait/"+name+".png";
		return "src/迭代一数据/players/protrait/" + name + ".png";
	}

	public static void main(String[] args) {
		Player player;
		try {
			player = new Player();
			player.exportToSql();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
