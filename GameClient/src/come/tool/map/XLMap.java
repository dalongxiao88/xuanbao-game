package come.tool.map;

import java.util.ArrayList;
import org.come.bean.NpcInfoBean;
import org.come.model.Door;
import org.come.until.SplitStringTool;
import org.come.until.UserMessUntil;
import org.come.model.Gamemap;
import java.util.List;

public class XLMap
{
    private int mapid;
    private List<XLPath.XLP> xlpOne;
    private List<XLPath.XLP> xlpTwo;
    
    public XLMap(int mapID) {
        this.mapid = mapID;
        Gamemap gamemap = (Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(mapID + "");
        if (gamemap != null) {
            if (gamemap.getMapway() != null && !gamemap.getMapway().equals("")) {
                List<String> strings = SplitStringTool.splitString(gamemap.getMapway());
                for (int i = 0; i < strings.size(); ++i) {
                    Door door = UserMessUntil.getDoor((String)strings.get(i));
                    if (door != null && door.getRects() != null) {
                        this.addXLP(new XLPath.XLP(door, mapID));
                    }
                }
            }
            if (gamemap.getMapnpc() != null && !gamemap.getMapnpc().equals("")) {
                List<String> strings = SplitStringTool.splitString(gamemap.getMapnpc());
                for (int i = 0; i < strings.size(); ++i) {
                    NpcInfoBean infoBean = UserMessUntil.getnpc((String)strings.get(i));
                    if (infoBean != null && (infoBean.getNpctable().getNpctype().equals("2") || infoBean.getNpctable().getNpctype().equals("222") || infoBean.getNpctable().getNpctype().equals("2222")) && infoBean.getNpctable().getNpcway() != null && !infoBean.getNpctable().getNpcway().equals("")) {
                        List<String> strings2 = SplitStringTool.splitString(infoBean.getNpctable().getNpcway());
                        for (int j = 0; j < strings2.size(); ++j) {
                            Door door2 = UserMessUntil.getDoor((String)strings2.get(j));
                            if (door2 != null) {
                                this.addXLP(new XLPath.XLP(door2, infoBean.getNpctable(), mapID));
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void addXLP(XLPath.XLP xlp) {
        if (xlp.getMap() != this.mapid) {
            if (this.xlpOne == null) {
                this.xlpOne = new ArrayList<>();
            }
            this.xlpOne.add(xlp);
        }
        else {
            if (this.xlpTwo == null) {
                this.xlpTwo = new ArrayList<>();
            }
            this.xlpTwo.add(xlp);
        }
    }
    
    public int getMapid() {
        return this.mapid;
    }
    
    public void setMapid(int mapid) {
        this.mapid = mapid;
    }
    
    public List<XLPath.XLP> getXlpOne() {
        return this.xlpOne;
    }
    
    public void setXlpOne(List<XLPath.XLP> xlpOne) {
        this.xlpOne = xlpOne;
    }
    
    public List<XLPath.XLP> getXlpTwo() {
        return this.xlpTwo;
    }
    
    public void setXlpTwo(List<XLPath.XLP> xlpTwo) {
        this.xlpTwo = xlpTwo;
    }
}
