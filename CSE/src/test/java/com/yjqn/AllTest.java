package com.yjqn;

import java.util.ArrayList;

import vo.MatchVO;
import vo.PlayerVO;
import vo.TeamVO;
import bl.match.Match;
import bl.player.Player;
import bl.team.Team;
import blservice.AgeEnum;
import junit.framework.TestCase;

public class AllTest extends TestCase{
Player player;
Team team;
Match match;
	
	public void setUp() throws Exception{
		player=new Player();
		team=new Team();
		match=new Match();
	}
	

	public void test_1(){
		ArrayList<PlayerVO> players=player.getPlayerSeasonInfo("13-14");
		String name=players.get(0).getName();	
		assertEquals(name, "Aaron Brooks");				
	}
	
	//TUS1-2 成功删除账户
		public void test_2(){
			ArrayList<PlayerVO> players=player.getPlayerSeasonInfo("14-15");
			String name=players.get(0).getName();	
			assertEquals(name, "Aaron Brooks");	
		}
		
	//TUS1-3 成功修改账户
		public void test_3(){
			ArrayList<PlayerVO> players=player.getPlayerAverageInfo();
			String name=players.get(0).getName();	
			assertEquals(name, "Aaron Brooks");				
		}
				
	//TUS1-4 成功查询账户
		public void test_4(){
			PlayerVO players=player.getPlayerSeasonInfo("13-14", "Aaron Brooks");
			String name=players.getName();	
			assertEquals(name, "Aaron Brooks");	
		}
		
	//TUS2-1
	    public void test_5(){
	    	PlayerVO players=player.getPlayerAverageInfo("Aaron Brooks");
			String name=players.getName();	
			assertEquals(name, "Aaron Brooks");	
		}
	    
	//TUS2-2
	    public void test_6(){
	    	PlayerVO players=player.getPlayerAverageInfo("2333");
			assertEquals(players, null);	
		}
	    
	//TUS2-3
	    public void test_7(){
	    	ArrayList<PlayerVO> players;
			players = player.getOrderedPlayersBySeason("13-14", "score", "desc", 5);
			String name=players.get(0).getName();	
			assertEquals(name, "Kevin Durant");
					
		}
	    
	//TUS3-1
	    public void test_8(){
	    	ArrayList<PlayerVO> players;
			players = player.getOrderedPlayersByAverage("score", "desc", 5);
			String name=players.get(0).getName();	
			assertEquals(name, "Kevin Durant");	
		}
	    
	//TUS3-2
	    public void test_9(){
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersBySeason("13-14", "all", "all", AgeEnum.ALL, "score", "desc", 5);
			String name=players.get(0).getName();	
			assertEquals(name, "Kevin Durant");			
		}
	    
	//TUS3-3
	    public void test_10(){
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersBySeason("13-14", "G", "W", AgeEnum.LE22, "score", "desc", 5);
		}
	    
	  //TUS3-3
	    public void test_11(){
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersBySeason("13-14", "C", "E", AgeEnum.M22_LE25, "reboundNum", "desc", 5);
		}
	    
	  //TUS3-3
	    public void test_12(){
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersBySeason("13-14", "F", "E", AgeEnum.M25_LE30, "reboundNum", "desc", 5);
		}
	    
	  //TUS3-3
	    public void test_13(){
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersBySeason("13-14", "F", "W", AgeEnum.M30, "assistNum", "desc", 5);
		}
	    
	  //TUS3-2
	    public void test_14(){	
	    	ArrayList<PlayerVO> players;
			players = player.selectPlayersByAverage("all", "all", AgeEnum.ALL, "score", "desc", 5);
			players = player.selectPlayersByAverage("G", "W", AgeEnum.LE22, "score", "desc", 5);
			players = player.selectPlayersByAverage("C", "E", AgeEnum.M22_LE25, "reboundNum", "desc", 5);
			players = player.selectPlayersByAverage("F", "E", AgeEnum.M25_LE30, "reboundNum", "desc", 5);
			players = player.selectPlayersByAverage("F", "W", AgeEnum.M30, "assistNum", "desc", 5);
//			String name=players.get(0).getName();	
//			assertEquals(name, "Kevin Durant");			
		}
	    
	    //TUS3-3
	    public void test_19(){
	    	player.getPlayerPortraitImage("Aaron Brooks");
		}	    
	    //TUS3-3
	    public void test_20(){
	    	player.getPlayerActionImage("Aaron Brooks");
		}
	  //TUS3-3
	    public void test_21(){
	    	ArrayList<PlayerVO> players;
			players = player.getDayHotPlayer("score", 5);
		}
	    //TUS3-3
	    public void test_22(){
	    	ArrayList<PlayerVO> players;
			players = player.getSeasonHotPlayer("13-14", "score", 5);
		}
	    //TUS3-3
	    public void test_23(){
	    	ArrayList<PlayerVO> players;
			players = player.getBestImprovedPlayer("recentFiveMatchesScoreUpRate", 5);
		}
	    //TUS3-3
	    public void test_24(){
	    	ArrayList<PlayerVO> players;
			players = player.getPlayersByInitialName('A');
			
		}
	    //TUS3-3
	    public void test_25(){
	    	ArrayList<MatchVO> matches;
			matches = player.getRecentMatches("Aaron Brooks", 5);			
		}
	    //TUS3-3
	    public void test_26(){
	    	ArrayList<MatchVO> matches;
			matches = player.getMatches("Aaron Brooks");			
		}
	    //TUS3-3
	    public void test_27(){
	    	ArrayList<PlayerVO> players;
			players = player.getPlayersByTeam("ATL");			
		}
	    //TUS3-3
	    public void test_28(){
	    	ArrayList<PlayerVO> players;
			players = player.getPlayerBaseInfo();			
		}
	    //TUS3-3
	    public void test_29(){
	    	ArrayList<PlayerVO> players;
			players = player.getPlayerBaseInfo("Aaron Brooks");			
		}
	    
	    public void test_30(){
			Team.getTeamsPartition();			
		}
		
		//TUS1-2 成功删除账户
			public void test_31(){
				ArrayList<TeamVO> teams=team.getTeamBaseInfo();
				teams.get(0);
			}
			
		//TUS1-3 成功修改账户
			public void test_32(){
				ArrayList<TeamVO> teams=team.getTeamSeasonInfo("13-14");
				teams.get(0);
			}
					
		//TUS1-4 成功查询账户
			public void test_33(){
				team.getTeamSeasonInfo("14-15");
			}
			
		//TUS2-1
		    public void test_34(){
		    	ArrayList<TeamVO> teams=team.getTeamAverageInfo();
		    	teams.get(0);
			}
		    
		//TUS2-2
		    public void test_35(){
		    	ArrayList<TeamVO> teams=team.getTeamBaseInfo("ATL");
		    	teams.get(0);
			}
		    
		//TUS2-3
		    public void test_36(){
		    	ArrayList<TeamVO> teams=team.getTeamBaseInfo("NOP");
		    	teams.get(0);	
			}
		    
		//TUS3-1
		    public void test_37(){
		    	ArrayList<TeamVO> teams=team.getTeamBaseInfo("a");
		    	teams.get(0);
			}
		    
		//TUS3-2
		    public void test_38(){
		    	ArrayList<TeamVO> teams=team.getTeamSeasonInfo("13-14", "ATL");	
		    	teams.get(0);
			}
		    
		//TUS3-3
		    public void test_39(){
		    	team.getTeamSeasonInfo("14-15", "ATL");	
			}
		    
		  //TUS3-3
		    public void test_40(){
		    	ArrayList<TeamVO> teams=team.getTeamSeasonInfo("13-14", "NOP");	
		    	teams.get(0);
			}
		    
		  //TUS3-3
		    public void test_41(){
		    	ArrayList<TeamVO> teams=team.getTeamAverageInfo("ATL");
		    	teams.get(0);
			}
		    
		  //TUS3-3
		    public void test_42(){
		    	ArrayList<TeamVO> teams=team.getTeamAverageInfo("NOP");
		    	teams.get(0);
			}
		    
		  //TUS3-2
		    public void test_43(){
		    	ArrayList<TeamVO> teams=team.getOrderedTeamsBySeason("13-14", "score", "desc", -1);	
		    	teams.get(0);
			}
		    
		//TUS3-3
		    public void test_44(){
		    	ArrayList<TeamVO> teams=team.getOrderedTeamsBySeason("13-14", "score", "null", 5);
		    	teams.get(0);
			}
		    
		  //TUS3-3
		    public void test_45(){
		    	team.getTeamImage("ATL");
			}
		    
		  //TUS3-3
		    public void test_46(){
		    	ArrayList<MatchVO> matches=team.getRecentMatches("ATL");
		    	matches.get(0);
			}
		    
		  //TUS3-3
		    public void test_47(){
		    	ArrayList<MatchVO> matches=team.getMatches("ATL");
		    	matches.get(0);
			}
		    //TUS3-3
		    public void test_48(){
		    	ArrayList<TeamVO> teams=team.getSeasonHotTeam("13-14", "score", 5);
		    	teams.get(0);
			}	    
		    //TUS3-3
		    public void test_49(){
		    	ArrayList<TeamVO> teams=team.getSeasonHotTeam("13-14", "score", -1);
		    	teams.get(0);
			}
		  //TUS3-3
		    public void test_50(){
		    	Team.changeTeamNameCHToEN("湖人");
			}
		    //TUS3-3
		    public void test_51(){
		    	Team.changeTeamNameCHToEN("人");
			}
		    //TUS3-3
		    public void test_52(){
		    	Team.changeTeamNameENToCH("ATL");
			}
		    //TUS3-3
		    public void test_53(){
		    	Team.changeTeamNameENToCH("A");		
			}
			public void test_54(){
				match.getAllMatches();		
			}
			
			//TUS1-2 成功删除账户
				public void test_55(){
					ArrayList<MatchVO> matches=match.getMatchData("13-14", "01-01", "LAC", "CHA");
					matches.get(0);
				}
				
			//TUS1-3 成功修改账户
				public void test_56(){
					ArrayList<MatchVO> matches=match.getMatchData("全部", "全部", "全部", "全部");
					matches.get(0);		
				}
						
			//TUS1-4 成功查询账户
				public void test_57(){
					ArrayList<MatchVO> matches=match.getMatchData("14-15", "全部", "全部", "全部");
				}
				//TUS1-4 成功查询账户
				public void test_58(){
					
				}   
	
}
