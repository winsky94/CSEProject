package data;

public class DirtyDataManager {
	/**
	 * 判断球龄是否符合要求的方法
	 * 
	 * @param fileName
	 *            :数据所属的文件名
	 * @param data
	 *            文件记录中的String类型的数据
	 * @return 如果数据符合要求，直接返回int型;如果数据记录是R即NBA新秀，返回0，如果数据超过合理范围（0-100），返回-1
	 */
	public static String checkName(String fileName,String name){
		if(name.contains("'")){
			String[] it=name.split("\\'");
			name=it[0]+it[1];
		}
		
		return name;
	}
	
	public static int checkExp(String fileName, String data) {
		int result = -1;
		try {
			result = Integer.parseInt(data);
			if (result <= 0 || result >= 100) {
//				System.out.println("fileName为" + fileName + "：球龄范围越界");
				result = -1;
			}
		} catch (NumberFormatException e) {
			if (data.equals("R")) {
//				System.out.println("fileName为：" + fileName + "：NBA新秀");
				result = 0;
			} else {
//				System.out.println("其他错误");
			}

		}
		return result;
	}

	/**
	 * 
	 * @param fileName
	 *            数据所属的文件名
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
				System.out.println("fileName为：" + fileName + "：球衣号码为非数字");
			} else {
				System.out.println("其他错误");
			}

		}
		return result;
	}
	
	public static String checkSchool(String fileName,String school){
		if(school.contains("'")){
			String[] it=school.split("\\'");
			school=it[0]+it[1];
		}
		
		return school;
	}
}
