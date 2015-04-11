package bl;

import java.io.PrintStream;
import java.util.ArrayList;

public class CommandTest {
	private String[] option_datatype={"-avg","-total"};//0位为默认
	private String[] player_hot_field={"score","rebound","assist"};
	private String[] team_hot_field={"score","rebound","assist","blockShot","steal","foul","fault","shot","three","penalty",
			"defendRebound","offendRebound"};
	private String[] option_mtype={"-all","-hot field","-king field -season"
			,"-king field -daily"};//需分开
	private String[] other_option={"-n","-high"};
	private int[] n_default={50,30};
	private String[] player_action={"filter","sort"};
	private String[] player_position={"All","F","G","C"};//position.All
	private String[] player_league={"All","West","East"};// league.West
	private String[] player_age={"All","<=22","22<X<=25","25<X<=30",">30"};//age.xx
	private String[] sortType={"desc","asc"};
	private String[] player_sort_base={"score","point","rebound","assist","blockShot","steal","foul","fault","minute","efficient","shot",
			"three","penalty","doubleTwo"};
	private String[] player_sort_high={"realShot","GmSc","shotEfficient","reboundEfficient","offendReboundEfficient","defendReboundEfficient"
			,"assistEfficent","stealEfficient","blockShotEfficient","faultEfficient","frequency"};
	private String[] team_sort_base={"score","point","rebound","assist","blockShot","steal","foul","fault","shot",
			"three","penalty","defendRebound","offendRebound"};
	private String[] team_sort_high={"winRate","offendRound","offendEfficient","defendEfficient","offendReboundEfficient","stealEfficeint","assistEfficient"};
	
	private boolean isPTotal=false;
	private boolean isTTotal=false;
	private String playerhotField="";
	private String teamhotField="";
	private String playerkingField="";
	private boolean isSeason=false;
	private int teamNum=30;
	private int playerNum=50;
	private boolean isPHigh=false;//是否返回高阶数据
	private boolean isTHigh=false;
	private ArrayList<String> playerSort=new ArrayList<String>();//aas.dsec ddd.asc 邮件及
	private ArrayList<String> sort=new ArrayList<String>();
	private ArrayList<String> playerFilter=new ArrayList<String>();//优先级 field.value
	private ArrayList<String> teamSort=new ArrayList<String>();//aas.dsec ddd.asc 邮件及
	
	//调用更新
	
	
	
	
	
	public void excute(PrintStream out,String[] args){
		ArrayList<String> command=new ArrayList<String>();
		for(int i=1;i<args.length;i++)
			command.add(args[i]);
		if(args[0].equals("-palyer")){
			//球队命令解析
			if(command.size()==0)
				return;//返回得分前50的球员的场均比赛数据 调用
			int i=0;
			if((i=command.indexOf("-n"))>=0)
				playerNum=Integer.parseInt(command.get(i+1));
			else
				playerNum=50;
				
			if((i=command.indexOf("-hot"))>=0){
				playerhotField=command.get(i+1);
				
						//调用 player hot 方法
			}
			else if((i=command.indexOf("-king"))>=0){
				playerkingField=command.get(i+1);
				if(command.get(i+2).equals("-season"))
					isSeason=true;	
				else
					isSeason=false;
				
				//调用返回数据王信息
			}else{
				//contain all
				if((i=command.indexOf("-sort"))>=0){
					playerSort.clear();
					i++;
					while(i<command.size()&&!(command.get(i).contains("-"))){
						playerSort.add(command.get(i));
						i++;
					}
				}
				if(command.indexOf("-high")>=0){
					isPHigh=true;
					//返回高阶数据的 sort方法  其他返回均未基本数据
				}
				else
					isPHigh=false;
				
				
				if(command.indexOf("-total")>=0)
				//返回的是总和数据
					isPTotal=true;
				else
					isPTotal=false;
				
				if((i=command.indexOf("-filter"))>0){
					
					playerFilter.clear();
					i++;
					while(i<command.size()&&!(command.get(i).contains("-"))){
						playerFilter.add(command.get(i));
						i++;
					}
					//调用sort+filter方法
				}else{
					//调用sort方法
				}
			
		
			}
			
		}else if(args[0].equals("-team")){
			//球员命令解析
			if(command.size()==0)
				return;//返回得分前30的球队的场均比赛数据
			int i=0;
			if((i=command.indexOf("-n"))>=0)
				teamNum=Integer.parseInt(command.get(i+1));
			else
				teamNum=50;
				
			if((i=command.indexOf("-hot"))>=0){
				teamhotField=command.get(i+1);
				
						//调用 team hot 方法
			}else{
				//contain all
				if((i=command.indexOf("-sort"))>=0){
					teamSort.clear();
					i++;
					while(i<command.size()&&!(command.get(i).contains("-"))){
						teamSort.add(command.get(i));
						i++;
					}
				}
				if(command.indexOf("-high")>=0){
					isTHigh=true;
					//返回高阶数据的 sort方法  其他返回均未基本数据
				}
				else
					isTHigh=false;
				
				
				if(command.indexOf("-total")>=0)
				//返回的是总和数据
					isTTotal=true;
				else
					isTTotal=false;
				
					//调用  sort方法
			
		
			}
		}else{
			//set
		}
		
		
	}
	
	


}
