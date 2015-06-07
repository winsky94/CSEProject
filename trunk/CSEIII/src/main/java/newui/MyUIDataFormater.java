package newui;

import java.text.DecimalFormat;

public class MyUIDataFormater {

	public static void main(String[] args) {
		System.out.println(formatTo0(1.2));
	}
	
	/**
	 * 将传入的double型小数转为特定的位数并作为一个string返回
	 * @param originalNum 原始的double型小数
	 * @return
	 */
	public static String formatTo0(double originalNum) {
		DecimalFormat dec = new DecimalFormat("0");
		return dec.format(originalNum);
	}
	
	/**
	 * 将传入的double型小数转为特定的位数并作为一个string返回
	 * @param originalNum 原始的double型小数
	 * @return
	 */
	public static String formatTo3(double originalNum) {
		DecimalFormat dec = new DecimalFormat("0.000");
		return dec.format(originalNum);
	}
	
}
