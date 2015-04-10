package bl;

import java.util.Comparator;

import vo.PlayerVO;


public	class SequenceOfPlayer implements Comparator<PlayerVO> {
	
    String sortBy;
    public SequenceOfPlayer(){
		this("name");
	}

	public SequenceOfPlayer(String s){
		super();
		sortBy=s;
	}
	
    public int compare(PlayerVO a1, PlayerVO a2) { 
        if(sortBy.equals("name"))
        	return compareByName(a1, a2);
        
        else 
			
		    return -1;
    
    }
    
    public int compareByName(PlayerVO a,PlayerVO b){
    	 return a.getName().compareTo(b.getName());
    }
}
