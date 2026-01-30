package come.tool.map;

import org.come.model.Npctable;
import org.come.model.Door;
import java.util.HashMap;
import com.tool.imagemonitor.ScriptEnd;
import com.tool.imagemonitor.ScriptOpen;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class XLPath
{
    static List<XXX> openList;
    static List<Integer> openInts;
    static Map<Integer, XLMap> map;
    
    public static XLMap getXLMap(int mapId) {
        XLMap xlMap = (XLMap)XLPath.map.get(Integer.valueOf(mapId));
        if (xlMap == null) {
            xlMap = new XLMap(mapId);
            XLPath.map.put(Integer.valueOf(mapId), xlMap);
        }
        return xlMap;
    }
    
    public static boolean mskpanduan(byte[][] bs, int x, int y) {
        return bs[y][x] == 0;
    }
    
    public static synchronized List<Object> ZDXL(int x, int y, int mapId, int xNew, int yNew, int mapIdNew) {
        XLPath.openInts.clear();
        XLPath.openList.clear();
        XXX termPos = null;
        if (mapId != mapIdNew) {
            XLPath.openList.add(new XXX(mapId, x, y));
            do {
                XXX currentPos = (XXX)XLPath.openList.get(0);
                for (int i = XLPath.openList.size() - 1; i >= 0; --i) {
                    if (currentPos.G > ((XXX)XLPath.openList.get(i)).G) {
                        currentPos = (XXX)XLPath.openList.get(i);
                    }
                }
                XLPath.openList.remove(currentPos);
                XLMap xlMap = getXLMap(currentPos.map);
                List<XLP> list = xlMap.getXlpOne();
                if (list == null) {
                    continue;
                }
                else {
                    boolean isMap = true;
                    for (int j = list.size() - 1; j >= 0; --j) {
                        XLP p = (XLP)list.get(j);
                        if (isMap && p.map == mapIdNew) {
                            isMap = false;
                        }
                        if (!currentPos.isDoor(p.door) && !currentPos.isMap(p.map) && !XLPath.openInts.contains(Integer.valueOf(p.map))) {
                            XXX tmpPos = new XXX(p, currentPos);
                            XLPath.openList.add(tmpPos);
                        }
                    }
                    if (isMap) {
                        XLPath.openInts.add(Integer.valueOf(currentPos.map));
                    }
                    for (int k = XLPath.openList.size() - 1; k >= 0; --k) {
                        if (termPos != null && termPos.G <= ((XXX)XLPath.openList.get(k)).G) {
                            XLPath.openList.remove(k);
                        }
                        else if (((XXX)XLPath.openList.get(k)).map == mapIdNew) {
                            if (termPos == null) {
                                termPos = (XXX)XLPath.openList.get(k);
                            }
                            else {
                                termPos = (XXX)XLPath.openList.get(k);
                            }
                        }
                    }
                }
            } while (XLPath.openList.size() != 0);
        }
        else {
            termPos = new XXX(mapId, x, y);
        }
        if (termPos == null) {
            return null;
        }
        List<Object> list2 = new ArrayList<>();
        ScriptOpen SOpen = new ScriptOpen(xNew, yNew);
        ScriptEnd SEnd = new ScriptEnd(0, mapIdNew, xNew, yNew);
        list2.add(SEnd);
        list2.add(SOpen);
        XXX end = new XXX(mapIdNew, xNew, yNew);
        end.fa = termPos;
        while (end.fa != null) {
            if (end.xlp == null) {
                if (end.map == end.fa.map) {
                    XXX xxx = MapShort(end.fa, end);
                    addScript(xxx, list2);
                }
            }
            else if (end.getMap() == end.fa.getMap()) {
                XXX xxx = MapShort(end.fa.recordXXX(), end.recordXXX());
                addScript(xxx, list2);
            }
            end = end.fa;
            if (end.xlp != null) {
                if (end.xlp.npc != 0) {
                    SEnd = new ScriptEnd(0, end.map, end.x, end.y);
                    list2.add(SEnd);
                    SOpen = new ScriptOpen(3, 0, end.xlp.door);
                    list2.add(SOpen);
                    SOpen = new ScriptOpen(1, end.xlp.npc, 0);
                    list2.add(SOpen);
                    SEnd = new ScriptEnd(0, end.xlp.dmap, end.xlp.dx, end.xlp.dy);
                    list2.add(SEnd);
                    SOpen = new ScriptOpen(end.xlp.dx, end.xlp.dy);
                    list2.add(SOpen);
                }
                else {
                    SEnd = new ScriptEnd(0, end.map, end.x, end.y);
                    list2.add(SEnd);
                    SOpen = new ScriptOpen(end.xlp.dx, end.xlp.dy);
                    list2.add(SOpen);
                }
            }
        }
        return list2;
    }
    
    public static XXX MapShort(XXX one, XXX two) {
        XLPath.openList.clear();
        XXX termPos = null;
        XLPath.openList.add(new XXX(one.map, one.x, one.y));
        XLMap xlMap = getXLMap(one.map);
        List<XLP> list = xlMap.getXlpTwo();
        do {
            XXX currentPos = (XXX)XLPath.openList.get(0);
            for (int i = XLPath.openList.size() - 1; i >= 0; --i) {
                if (currentPos.G > ((XXX)XLPath.openList.get(i)).G) {
                    currentPos = (XXX)XLPath.openList.get(i);
                }
            }
            XLPath.openList.remove(currentPos);
            if (currentPos.x != two.x || currentPos.y != two.y) {
                if (list != null) {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        XLP p = (XLP)list.get(i);
                        if (!currentPos.isDoor(p.door)) {
                            XXX tmpPos = new XXX(p, currentPos);
                            tmpPos.G += 10000;
                            XLPath.openList.add(tmpPos);
                        }
                    }
                }
                XLPath.openList.add(new XXX(two.map, two.x, two.y, currentPos));
            }
            for (int j = XLPath.openList.size() - 1; j >= 0; --j) {
                if (termPos != null && termPos.G <= ((XXX)XLPath.openList.get(j)).G) {
                    XLPath.openList.remove(j);
                }
                else if (((XXX)XLPath.openList.get(j)).x == two.x && ((XXX)XLPath.openList.get(j)).y == two.y) {
                    if (termPos == null) {
                        termPos = (XXX)XLPath.openList.get(j);
                    }
                    else {
                        termPos = (XXX)XLPath.openList.get(j);
                    }
                }
            }
        } while (XLPath.openList.size() != 0);
        return termPos;
    }
    
    public static void addScript(XXX xxx, List<Object> list) {
        if (xxx != null) {
            ScriptEnd SEnd;
            ScriptOpen SOpen;
            for (xxx = xxx.fa; xxx.fa != null; xxx = xxx.fa) {
                if (xxx.xlp.npc != 0) {
                    SEnd = new ScriptEnd(0, xxx.map, xxx.x, xxx.y);
                    list.add(SEnd);
                    SOpen = new ScriptOpen(3, 0, xxx.xlp.door);
                    list.add(SOpen);
                    SOpen = new ScriptOpen(1, xxx.xlp.npc, 0);
                    list.add(SOpen);
                    SEnd = new ScriptEnd(0, xxx.xlp.dmap, xxx.xlp.dx, xxx.xlp.dy);
                    list.add(SEnd);
                    SOpen = new ScriptOpen(xxx.xlp.dx, xxx.xlp.dy);
                    list.add(SOpen);
                }
                else {
                    SEnd = new ScriptEnd(0, xxx.map, xxx.x, xxx.y);
                    list.add(SEnd);
                    SOpen = new ScriptOpen(xxx.xlp.dx, xxx.xlp.dy);
                    list.add(SOpen);
                }
            }
        }
    }
    
    public static int calcD(int x, int y, int tx, int ty) {
        x -= tx;
        y -= ty;
        x *= x;
        y *= y;
        return x + y;
    }
    
    static {
        XLPath.openList = new ArrayList<>();
        XLPath.openInts = new ArrayList<>();
        XLPath.map = new HashMap<>();
    }
    
    static class XXX
    {
        private int map;
        private int x;
        private int y;
        private XLP xlp;
        private int G;
        private transient XXX fa;
        
        public XXX(int map, int x, int y) {
            this.map = map;
            this.x = x;
            this.y = y;
        }
        
        public XXX(XLP xlp, XXX pos) {
            this.map = xlp.map;
            this.x = xlp.x;
            this.y = xlp.y;
            this.xlp = xlp;
            this.fa = pos;
            this.G = pos.G + XLPath.calcD(pos.x, pos.y, xlp.dx, xlp.dy) + 10000;
        }
        
        public XXX(int map, int x, int y, XXX pos) {
            this.map = map;
            this.x = x;
            this.y = y;
            this.fa = pos;
            this.G = pos.G + XLPath.calcD(pos.x, pos.y, x, y);
        }
        
        public boolean isDoor(int door) {
            return this.xlp != null && (this.xlp.door == door || (this.fa != null && this.fa.isDoor(door)));
        }
        
        public boolean isMap(int map) {
            return this.xlp != null && (this.xlp.dmap == map || (this.fa != null && this.fa.isMap(map)));
        }
        
        public int getMap() {
            return (this.xlp != null) ? this.xlp.dmap : this.map;
        }
        
        public XXX recordXXX() {
            if (this.xlp != null) {
                return new XXX(this.xlp.dmap, this.xlp.dx, this.xlp.dy);
            }
            return this;
        }
    }
    
    static class XLP
    {
        private int door;
        private int npc;
        private int map;
        private int x;
        private int y;
        private int dmap;
        private int dx;
        private int dy;
        
        public XLP(Door door, int dmap) {
            this.init(door);
            int[] rect = door.getRects();
            this.dmap = dmap;
            this.dx = (rect[0] + rect[1]) / 2;
            this.dy = (rect[2] + rect[3]) / 2;
        }
        
        public XLP(Door door, Npctable npctable, int dmap) {
            this.init(door);
            this.dmap = dmap;
            this.dx = Integer.parseInt(npctable.getTx());
            this.dy = Integer.parseInt(npctable.getTy());
            this.npc = Integer.parseInt(npctable.getNpcid());
        }
        
        public void init(Door door) {
            this.door = Integer.parseInt(door.getDoorid());
            this.map = Integer.parseInt(door.getDoormap());
            String[] point = door.getDoorpoint().split("\\|");
            this.x = Integer.parseInt(point[0]);
            this.y = Integer.parseInt(point[1]);
        }
        
        public int getMap() {
            return this.map;
        }
    }
}
