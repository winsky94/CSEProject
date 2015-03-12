package data;

public class DirtyDataManager {
	/**
	 * 处理字符串中含有'导致数据录入数据库过程中导致的数据库语句错误的问题
	 * 
	 * @param fileName
	 *            数据文件名
	 * @param string
	 *            待检查的字符串
	 * @return 如果字符串含有单引号，就用两个单引号来代替一个单引号
	 */
	public static String checkString(String fileName, String string) {

		if (string.indexOf("'") != -1) {// 判断字符串是否含有单引号
			string = string.replace("'", "''"); // SQL是用两个单引号来代替一个单引号的
		}

		return string;
	}

	/**
	 * 判断球龄是否符合要求的方法
	 * 
	 * @param fileName
	 *            数据文件名
	 * @param data
	 *            文件记录中的String类型的数据
	 * @return 如果数据符合要求，直接返回int型;如果数据记录是R即NBA新秀，返回0，如果数据超过合理范围（0-100），返回-1
	 */
	public static int checkExp(String fileName, String data) {
		int result = -1;
		try {
			result = Integer.parseInt(data);
			if (result <= 0 || result >= 100) {
				// System.out.println("fileName为" + fileName + "：球龄范围越界");
				result = -1;
			}
		} catch (NumberFormatException e) {
			if (data.equals("R")) {
				// System.out.println("fileName为：" + fileName + "：NBA新秀");
				result = 0;
			} else {
				// System.out.println("其他错误");
			}

		}
		return result;
	}

	/**
	 * 
	 * @param fileName
	 *            数据文件名
	 * @param data
	 *            文件记录中的String类型的数据
	 * @return 如果球衣号码是N/A，返回-1
	 */
	public static int checkNum(String fileName, String data) {
		int result = -1;
		try {
			result = Integer.parseInt(data);
		} catch (NumberFormatException e) {
			if (data.equals("N/A")) {
				String[] tp = fileName.split("info\\\\");
				fileName = tp[1].substring(1);
				// System.out.println("fileName为：" + fileName + "：球衣号码为非数字");
			} else {
				// System.out.println("其他错误");
			}

		}
		return result;
	}

	/**
	 * 检查每场比赛每位球员的得分是否合理的方法 1、判断得分是否是数字 2、判断得分计算是否准确
	 * 
	 * @param fileName
	 *            数据文件名
	 * @param data
	 *            个人得分数据
	 * @param allData
	 *            这一行的所有数据
	 * @return 如果得分合理，返回int型得分，否则根据球员该场比赛的表现计算出相应的得分并返回
	 */
	public static int checkPersonScore(String fileName, String data,
			String allData) {
		int result = -1;
		String[] line = allData.split(";");
		int shootHitNum = Integer.parseInt(line[3]);// 投篮命中数
		int threeHitNum = Integer.parseInt(line[5]);// 三分命中数
		int freeThrowHitNum = Integer.parseInt(line[7]);// 罚球命中数
		try {
			result = Integer.parseInt(data);
			// 检查个人得分是否计算错误
			int realScore = (shootHitNum - threeHitNum) * 2 + threeHitNum * 3
					+ freeThrowHitNum * 1;
			if (realScore == result) {
				return result;
			} else {
				// System.out.println(fileName + " 个人得分计算错误");
				return realScore;
			}
		} catch (NumberFormatException e) {
			result = (shootHitNum - threeHitNum) * 2 + threeHitNum * 3
					+ freeThrowHitNum * 1;// 个人得分
		}
		return result;
	}

	/**
	 * 检查出手次数是否大于等于命中次数的方法
	 * 
	 * @param fileName
	 *            数据文件名
	 * @param attemptNumNum
	 *            出手次数
	 * @param hitNum
	 *            命中次数
	 * @return true表明出手次数大于等于命中次数， false表明出手次数小于命中次数
	 */
	public static boolean checkShootAndHitNum(String fileName, int attemptNumNum,
			int hitNum) {
		if (attemptNumNum >= hitNum) {
			return true;
		} else {
			System.out.println(fileName+" 出手次数小于命中次数");
			return false;
		}
	}

	/**
	 * 
	 * @param fileName 数据文件名
	 * @param offenReboundNum 前场篮板数
	 * @param defenReboundNum 后场篮板数
	 * @param reboundNum 总篮板数
	 * @return true表明前场篮板数与后场篮板数之和等于总篮板数 false表明篮板数与后场篮板数之和不等于总篮板数
	 */
	public static boolean checkReboundNum(String fileName, int offenReboundNum,
			int defenReboundNum, int reboundNum) {
		if ((offenReboundNum + defenReboundNum) == reboundNum) {
			return true;
		} else {
			return false;
		}
	}
}
