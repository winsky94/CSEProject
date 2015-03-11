import java.util.ArrayList;

import po.PlayerPO;
import data.FileManager;

public class test {
	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		ArrayList<PlayerPO> result = fileManager.readAllPlayers();
		System.out.println("success!");
	}
}
