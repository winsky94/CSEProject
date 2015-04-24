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
		
		command="-player -total -all -n 10 -filter position.F,league.west - sort shot.desc";
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
