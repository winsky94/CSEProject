package bl;

import java.util.Comparator;

import vo.PlayerVO;


public	class SequenceOfPlayer implements Comparator<PlayerVO> {
    public int compare(PlayerVO a1, PlayerVO a2) { 
    
         return a1.getName().compareTo(a2.getName());
    
    }
}
