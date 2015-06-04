package blService;

import java.util.ArrayList;

import vo.LiveMatchDetailVO;

public interface LiveTxtBLService {
     public ArrayList<LiveMatchDetailVO> getLiveTxt(String season,String data,String teams);
}
