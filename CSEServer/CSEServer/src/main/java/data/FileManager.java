package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.PlayerPO;

public class FileManager {

	public PlayerPO readFromPlayerFile(String fileName) {
		int count = 0;
		PlayerPO player;
		String mid;
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

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(5);
		nf.setGroupingUsed(false);
		mid = nf.format(count);
		mname = content[0];
		mnumber = DirtyDataManager.checkNum(fileName, content[1]);
		mposition = content[2];
		mheight = content[3];
		mweight = Integer.parseInt(content[4]);
		mbirth = content[5];
		mage = Integer.parseInt(content[6]);
		mexp = DirtyDataManager.checkExp(fileName, content[7]);
		mschool = content[8];
		player = new PlayerPO(mid, mname, mnumber, mposition, mheight, mweight,
				mbirth, mage, mexp, mschool);
		return player;
	}

	public ArrayList<PlayerPO> readAllPlayers() {
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		try {
			FileList fl = new FileList("src/迭代一数据/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				players.add(readFromPlayerFile(name));
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

}
