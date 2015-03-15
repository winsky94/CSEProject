import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLHelper.SqlManager;

public class test {

	private static String addPresentTime(String a, String b) {
		String[] aTemp = a.split(":");
		String[] bTemp = b.split(":");
		int a1 = Integer.parseInt(aTemp[0]);
		int a2 = Integer.parseInt(aTemp[1]);
		int b1 = Integer.parseInt(bTemp[0]);
		int b2 = Integer.parseInt(bTemp[1]);
		String result1;
		String result2;
		if (a2 + b2 < 60) {
			result2 = String.valueOf(a2 + b2);
			result1 = String.valueOf(a1 + b1);
		} else {
			result2 = String.valueOf(a2 + b2 - 60);
			result1 = String.valueOf(a1 + b1 + 1);
		}
		return result1 + ":" + result2;
	}

	public static void main(String[] args) {
		String t="0:0";
		t=addPresentTime(t, "29:31");
		t=addPresentTime(t, "34:22");
		t=addPresentTime(t, "30:53");
		t=addPresentTime(t, "34:02");
		t=addPresentTime(t, "29:38");
		t=addPresentTime(t, "22:19");
		t=addPresentTime(t, "21:19");
		t=addPresentTime(t, "3:36");
		t=addPresentTime(t, "5:50");
		t=addPresentTime(t, "18:53");
		System.out.println(t);
		
		for(int i=0;i<10;i++){
			if(i==5){
				break;
			}
			else {
				System.out.println(i);
			}
		}
		
	}
}
