import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import SQLHelper.SqlManager;

public class test {
	private static String addPresentTime(String a,String b){
		String[] aTemp=a.split(":");
		String[] bTemp=b.split(":");
		int a1=Integer.parseInt(aTemp[0]);
		int a2=Integer.parseInt(aTemp[1]);
		int b1=Integer.parseInt(bTemp[0]);
		int b2=Integer.parseInt(bTemp[1]);
		String result1;
		String result2;
		if(a2+b2<60){
			result2=String.valueOf(a2+b2);
			result1=String.valueOf(a1+b1);
		}
		else{
			result2=String.valueOf(a2+b2-60);
			result1=String.valueOf(a1+b1+1);
		}
		return result1+":"+result2;
	}
	
	public static double changeTimeToMinute(String presentTime){
		double result=0;
		String[] temp=presentTime.split(":");
		result=Double.parseDouble(temp[0])+Double.parseDouble(temp[1])/60.0;
		return result;
	}
	
	public static void main(String[] args) {
		String q=addPresentTime("0:0", "28:07");
		q=addPresentTime(q, "42:30");
		q=addPresentTime(q, "42:46");
		q=addPresentTime(q, "39:16");
		q=addPresentTime(q, "43:04");
		q=addPresentTime(q, "20:02");
		q=addPresentTime(q, "13:35");
		q=addPresentTime(q, "25:44");
		q=addPresentTime(q, "9:56");
		System.out.println((Double.parseDouble(q.split(":")[0])/5));
		
		System.out.println(changeTimeToMinute("42:30"));
				
	}
}
