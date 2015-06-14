package data;

import java.util.ArrayList;

import vo.PlayerVO;

public class PlayerStatistic {
	private ArrayList<Double> X = new ArrayList<Double>();
	private ArrayList<Double> Y = new ArrayList<Double>();

	public PlayerStatistic() {

	}

	public PlayerStatistic(ArrayList<Double> X, ArrayList<Double> Y) {
		this.X = X;
		this.Y = Y;
	}

	public static void main(String[] args) {
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("14-15",
				"Playoff");
		for (int i = 0; i < players.size(); i++) {
			PlayerVO vo = players.get(i);
			double salary = (double) ps.getSalary(vo.getName());
			if (salary == 0) {
				continue;
			}
			y.add(salary);
			x.add(vo.getScore());
			// System.out.println(vo.getEfficiency());
		}
		PlayerStatistic playerStatistic = new PlayerStatistic(x, y);
		double xAvg = playerStatistic.calculateAvg(playerStatistic.getX());
		double yAvg = playerStatistic.calculateAvg(playerStatistic.getY());
		double b = playerStatistic.calculateL(playerStatistic.getX(),
				playerStatistic.getY(), xAvg, yAvg)
				/ (double) playerStatistic.calculateL(playerStatistic.getX(),
						playerStatistic.getX(), xAvg, xAvg);
		double a = yAvg - b * xAvg;
		double Sr = playerStatistic.calculateSr(yAvg, a, b);
		double Se = playerStatistic.calculateSe(a, b);
		double Vr = Sr / 1;
		double Ve = Se / (double) x.size();
		double F = Vr / Ve;
		double r2 = Sr / (Sr + Se);
		double Sy = playerStatistic.calculateSy(Se, x.size());
		System.out.println("样本容量：" + x.size());
		System.out.println("回归偏差平方和：" + Sr);
		System.out.println("剩余偏差平方和：" + Se);
		System.out.println("方差：" + Vr);
		System.out.println("方差：" + Ve);
		System.out.println("F值：" + F);
		System.out.println("拟合度：" + r2);
		System.out.println("标准残差：" + Sy);

	}

	public ArrayList<Double> testX() {
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("14-15",
				"Playoff");
		for (int i = 0; i < players.size(); i++) {
			PlayerVO vo = players.get(i);
			double salary = (double) ps.getSalary(vo.getName());
			if (salary == 0) {
				continue;
			}
			y.add(salary);
			x.add(vo.getScore());
			// System.out.println(vo.getEfficiency());
		}
		return x;
	}
	
	public ArrayList<Double> testY() {
		PlayerData player = new PlayerData();
		PlayerSalaryData ps = new PlayerSalaryData();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<PlayerVO> players = player.getPlayerAverageInfo("14-15",
				"Playoff");
		for (int i = 0; i < players.size(); i++) {
			PlayerVO vo = players.get(i);
			double salary = (double) ps.getSalary(vo.getName());
			if (salary == 0) {
				continue;
			}
			y.add(salary);
			x.add(vo.getScore());
			// System.out.println(vo.getEfficiency());
		}
		return y;
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
		System.err.println(n - 2);
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
