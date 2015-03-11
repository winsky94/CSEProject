package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.PlayerPO;

public class SqlManager {
	public static Connection getConnection() throws SQLException,java.lang.ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.jdbc.Driver");
        String userName = "root";
        String password = "12345a";
        Connection con = DriverManager.getConnection(url,userName,password);
        return con;
  }
  public static void main(String[] args) {
	  FileManager fm=new FileManager();
	  ArrayList<PlayerPO> players=fm.readAllPlayers();
     try{
         Connection con = getConnection();
         Statement sql = con.createStatement();
         sql.execute("drop table if exists players");
         sql.execute("create table players(id int not null auto_increment,name varchar(40) not null default 'name',"
         		+ "number int not null default 0,position varchar(20) not null default 'null',"
         		+ "height varchar(20) not null default 'null',weight int not null default 0,"
         		+ "brith varchar(20) not null default 'null',age int not null default 0,exp int not null default 0,"
         		+ "school varchar(40)not null default 'null',primary key(id));");
         int count=1;
         for(PlayerPO player:players){
   //     	 sql.execute("insert players values("+(count++)+",'Lucy',1,'F','1-1',1,'1111',1,1,'11')");
         sql.execute("insert players values("+(count++)+",'"+player.getName()+"',"+player.getNumber()+
        		 ",'"+player.getPosition()+"','"+player.getHeight()+"',"+player.getWeight()+",'"+
        		 player.getBirth()+"',"+player.getAge()+","+player.getExp()+",'"+player.getSchool()+"')");
             
   
        	 System.out.println(count);
         }
         String query = "select * from players";
         ResultSet result = sql.executeQuery(query);
         System.out.println("players表数据如下：");
         System.out.println("---------------------------------");
         System.out.println("学号"+" "+"姓名"+" "+"数学成绩");
         System.out.println("---------------------------------");
         int number;
         String name;
         int math;
         while(result.next()){
           number = result.getInt("id");
           name = result.getString("name");
           math = result.getInt("number");
           System.out.println(number + " " + name + " " + math);
         }
         sql.close();
         con.close();
    }catch(java.lang.ClassNotFoundException e){
         System.err.println("ClassNotFoundException:" + e.getMessage());
    }catch(SQLException ex){
         System.err.println("SQLException:" + ex.getMessage());
    }
  }
}
