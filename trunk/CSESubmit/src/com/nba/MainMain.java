package com.nba;

public class MainMain {
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String command="-team -all -n 10";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player -all -n 10";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player -high -n 10 -sort frequency.desc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player -hot assist -n 5";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player -king score -season";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-player -avg -n 5 -filter position.F";
		System.out.println(command);
		Main.main(command.split(" ")); 
		//小小黄注意
		//多重筛选  pdf文档中league为West East console里传的是West/East 如需修改 请修改Console -PalyerFilterChange()
		command="-player -filter position.F,age.All,league.West";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		
		command="-player -total -all -n 10 -filter position.F,league.West -sort shot.desc,score.desc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		//多重排序
		command="-player -n 20 -sort rebound.desc,assist.asc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team -all -n 10";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team -hot assist -n 5";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team -avg -n 5 -sort shot.asc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team -total -all -n 10 -sort shot.desc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		command="-team -high -n 5 -sort stealEfficient.asc";
		System.out.println(command);
		Main.main(command.split(" ")); 
		
		
	}
}
