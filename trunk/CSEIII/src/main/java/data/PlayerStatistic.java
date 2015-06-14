package data;

import java.util.ArrayList;
import java.util.HashMap;

import vo.PlayerVO;

public class PlayerStatistic {
	private ArrayList<Double> X = new ArrayList<Double>();
	private ArrayList<Double> Y = new ArrayList<Double>();

	public static void main(String[] args) {
		PlayerStatistic playerStatistic = new PlayerStatistic();
		System.out.println(playerStatistic.start().get("GmScEfficiencyValue")
				.get(1));
		;
	}

	public ArrayList<Double> testX() {
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("13-14",
				"Playoff");
		for (int i = 0; i < players.size(); i++) {
			PlayerVO vo = players.get(i);
			double salary = (double) ps.getSalary("14-15", vo.getName());
			if (salary == 0) {
				continue;
			}
			y.add(salary);
			x.add(vo.getScore_rebound_assist());
			// System.out.println(vo.getEfficiency());
		}
		return x;
	}

	public ArrayList<Double> testY() {
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("13-14",
				"Playoff");
		for (int i = 0; i < players.size(); i++) {
			PlayerVO vo = players.get(i);
			double salary = (double) ps.getSalary("14-15", vo.getName());
			if (salary == 0) {
				continue;
			}
			y.add(salary / 1000000);
			x.add(vo.getScore_rebound_assist());
			// System.out.println(vo.getEfficiency());
		}
		return y;
	}

	/**
	 * @return hashMap中key值是"reboundNum", "efficiency", "GmScEfficiencyValue",
	 *         "score_rebound_assist" value是一个Arraylist，第一个元素是F值，第二个元素是拟合度
	 */
	public HashMap<String, ArrayList<Double>> start() {
		HashMap<String, ArrayList<Double>> result = new HashMap<String, ArrayList<Double>>();
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("13-14",
				"Playoff");
		String[] type = { "reboundNum", "efficiency", "GmScEfficiencyValue",
				"score_rebound_assist" };
		for (int index = 0; index < type.length; index++) {
			String flag = type[index];
			for (int i = 0; i < players.size(); i++) {
				PlayerVO vo = players.get(i);
				double salary = (double) ps.getSalary("14-15", vo.getName());
				if (salary == 0) {
					continue;
				}
				Y.add(salary / 1000000);
				initX(flag, vo);
			}
			ArrayList<Double> temp = lineAnalysis();
			result.put(flag, temp);
			X = new ArrayList<Double>();
			Y = new ArrayList<Double>();
		}

		return result;
	}

	public ArrayList<Double> lineAnalysis() {
		ArrayList<Double> result = new ArrayList<Double>();
		double xAvg = calculateAvg(getX());
		double yAvg = calculateAvg(getY());
		double b = calculateL(getX(), getY(), xAvg, yAvg)
				/ (double) calculateL(getX(), getX(), xAvg, xAvg);
		double a = yAvg - b * xAvg;
		double Sr = calculateSr(yAvg, a, b);
		double Se = calculateSe(a, b);
		double Vr = Sr / 1;
		double Ve = Se / (double) X.size();
		double F = calculateF(Vr, Ve);
		double r2 = Sr / (Sr + Se);
//		double Sy = calculateSy(Se, X.size());
		// System.out.println("样本容量：" + X.size());
		// System.out.println("回归偏差平方和：" + Sr);
		// System.out.println("剩余偏差平方和：" + Se);
		// System.out.println("方差：" + Vr);
		// System.out.println("方差：" + Ve);
		// System.out.println("a:"+a);
		// System.out.println("b:"+b);
		// System.out.println("F值：" + F);
		// System.out.println("拟合度：" + r2);
		// System.out.println("标准残差：" + Sy);
		result.add(F);
		result.add(r2);
		return result;
	}

	public void initX(String flag, PlayerVO vo) {
		if (flag.equals("reboundNum")) {
			X.add(vo.getReboundNum());
		} else if (flag.equals("efficiency")) {
			X.add(vo.getEfficiency());
		} else if (flag.equals("GmScEfficiencyValue")) {
			X.add(vo.getGmScEfficiencyValue());
		} else if (flag.equals("score_rebound_assist")) {
			X.add(vo.getScore_rebound_assist());
		}

	}

	public double calculateF(double Vr, double Ve) {
		return Vr / Ve;
	}

	public double calculateA(double xAvg, double yAvg, double b) {
		return yAvg - b * xAvg;
	}

	public double calculateB(double lxx, double lxy) {
		return lxy / lxx;
	}

	public double calculateAvg(ArrayList<Double> data) {
		int sum = 0;
		double avg = 0;
		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}
		avg = sum / (double) data.size();
		return avg;
	}

	public double calculateL(ArrayList<Double> X, ArrayList<Double> Y,
			double xAvg, double yAvg) {
		double result = 0;
		for (int i = 0; i < X.size(); i++) {
			result += (X.get(i) - xAvg) * (Y.get(i) - yAvg);
		}
		return result;
	}

	public static double calculateY(double a, double b, double x) {
		return (a + b * x);
	}

	public double calculateSe(double a, double b) {
		double result = 0;
		for (int i = 0; i < Y.size(); i++) {
			result += (Y.get(i) - calculateY(a, b, X.get(i)))
					* (Y.get(i) - calculateY(a, b, X.get(i)));
		}
		return result;
	}

	public double calculateSr(double yAvg, double a, double b) {
		double result = 0;
		for (int i = 0; i < X.size(); i++) {
			result += (calculateY(a, b, X.get(i)) - yAvg)
					* (calculateY(a, b, X.get(i)) - yAvg);
		}
		return result;
	}

	public double calculateSy(double Se, int n) {
		return Math.sqrt(Se / (double) (n - 2));
	}

	public ArrayList<Double> getX() {
		return X;
	}

	public ArrayList<Double> getY() {
		return Y;
	}

	public void setX(ArrayList<Double> x) {
		X = x;
	}

	public void setY(ArrayList<Double> y) {
		Y = y;
	}
}
