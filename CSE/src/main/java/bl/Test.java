package bl;

public class Test {
	public static void main(String[] args) {
	/*	int count = 0;
		for (int i = 0; i < 100; i++) {
			long t1 = System.currentTimeMillis();
			Team team = new Team();
			team.getTeamAverageInfo();
			long t2 = System.currentTimeMillis();
			NewTeam newTeam = new NewTeam();
			newTeam.getTeamAverageInfo();
			long t3 = System.currentTimeMillis();
			System.out.println(t2 - t1);
			System.out.println(t3 - t2);
			if ((t2 - t1) >= (t3 - t2)) {
				count++;
			}
		}
		System.out.println("调用getTeamAverageInfo，运行100次，有" + count
				+ "次用map的方法快");*/

		// new Thread() {
		// int count = 0;
		//
		// public void run() {
		// while (count < 100) {
		// System.out.println("get data");
		// Match match = new Match();
		// ArrayList<MatchVO> result = match.getMatchData("全部", "全部",
		// "全部", "全部");
		// System.out.println(result.size());
		// count++;
		// try {
		// this.sleep(5000);// 话说 能不能不睡
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		// }.start();

		int equal = 0;
		int count = 0;

		for (int i = 0; i < 100; i++) {
//			long t1 = System.currentTimeMillis();
//			NewNewPlayer newNewPlayer = new NewNewPlayer();
//			newNewPlayer.getPlayerAverageInfo();
//			long t2 = System.currentTimeMillis();
//			NewPlayer newPlayer = new NewPlayer(3);
//			newPlayer.getPlayerAverageInfo();
//			long t3 = System.currentTimeMillis();
//			if ((t3 - t2) > (t2 - t1)) {
//				count++;
//			}
//			if ((t3 - t2) == (t2 - t1)) {
//				equal++;
//			}
//			System.out.println("------------------------------");
//			System.out.println("newNewPlayer比newPlayer快"+count+"次");
//			System.out.println(equal+"次二者相等");		
			NewNewPlayer newNewPlayer = new NewNewPlayer();
			newNewPlayer.getPlayerSeasonInfo("13-14","Andre Drummond");
			newNewPlayer.getPlayerSeasonInfo("13-14", "Andre Drummond");
			long t1 = System.currentTimeMillis();
			newNewPlayer.getPlayerSeasonInfo("13-14", "Andre Drummond");
			long t2 = System.currentTimeMillis();
			newNewPlayer.getPlayerSeasonInfo("13-14","Andre Drummond");
			
			long t3 = System.currentTimeMillis();
			if ((t3 - t2) < (t2 - t1)) {
				count++;
			}
			if ((t3 - t2) == (t2 - t1)) {
				equal++;
			}
			System.out.println(t2-t1+"ms");
			System.out.println(t3-t2+"ms");
				
		}

		System.out.println("------------------------------");
		System.out.println("1比2快"+count+"次");
		System.out.println(equal+"次二者相等");		
	}
}
