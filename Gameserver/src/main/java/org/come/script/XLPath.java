package org.come.script;

import org.come.tool.SplitStringTool;
import org.come.server.GameServer;
import org.come.model.Gamemap;
import org.come.model.Npctable;
import org.come.model.Door;
import org.come.bean.PathPoint;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class XLPath
{
    private List<XXX> openList;
    private List<Integer> openInts;
    private Map<Integer, XLMap> map;
    
    public XLPath() {
        this.openList = new ArrayList<>();
        this.openInts = new ArrayList<>();
        this.map = new HashMap<>();
    }
    
    public static long getMoveTime(List<PathPoint> list, double sp) {
        long time = 0L;
        if (list.size() > 1) {
            PathPoint point = (PathPoint)list.get(0);
            PathPoint point2 = (PathPoint)list.get(1);
            int dx = point2.getX() - point.getX();
            int dy = point2.getY() - point.getY();
            time += getTime((long)dx, (long)dy, sp);
            list.remove(0);
        }
        return (long)((double)time * 1.3);
    }
    
    public static long getTime(long l, long m, double sp) {
        double move = Math.sqrt((double)(l * l + m * m));
        return (long)(move / sp);
    }
    
    public XLMap getXLMap(int mapId) {
        XLMap xlMap = (XLMap)this.map.get(Integer.valueOf(mapId));
        if (xlMap == null) {
            xlMap = new XLMap(mapId);
            this.map.put(Integer.valueOf(mapId), xlMap);
        }
        return xlMap;
    }
    
    public boolean move(List<ScriptOpen> list, int mapId, int x, int y, int newMapId, int newX, int newY) {
        this.openInts.clear();
        this.openList.clear();
        XXX termPos = null;
        if (mapId != newMapId) {
            this.openList.add(new XXX(mapId, x, y));
            if (this.openList.size()<=0) {//9-12修改
                return false;
            }
            do {
                XXX currentPos = (XXX)this.openList.get(0);
                for (int i = this.openList.size() - 1; i >= 0; --i) {
                    if (currentPos.G > ((XXX)this.openList.get(i)).G) {
                        currentPos = (XXX)this.openList.get(i);
                    }
                }
                this.openList.remove(currentPos);
                XLMap xlMap = this.getXLMap(currentPos.map);
                List<XLP> xlps = xlMap.xlpOne;
                if (xlps == null) {
                    continue;
                }
                else {
                    boolean isMap = true;
                    for (int j = xlps.size() - 1; j >= 0; --j) {
                        XLP p = (XLP)xlps.get(j);
                        if (isMap && p.map == newMapId) {
                            isMap = false;
                        }
                        if (!currentPos.isDoor(p.door) && !currentPos.isMap(p.map) && !this.openInts.contains(Integer.valueOf(p.map))) {
                            XXX tmpPos = new XXX(p, currentPos);
                            this.openList.add(tmpPos);
                        }
                    }
                    if (isMap) {
                        this.openInts.add(Integer.valueOf(currentPos.map));
                    }
                    for (int k = this.openList.size() - 1; k >= 0; --k) {
                        if (termPos != null && termPos.G <= ((XXX)this.openList.get(k)).G) {
                            this.openList.remove(k);
                        }
                        else if (this.openList.get(k).map == newMapId) {
                            if (termPos == null) {
                                termPos = (XXX)this.openList.get(k);
                            }
                            else {
                                termPos = (XXX)this.openList.get(k);
                            }
                        }
                    }
                }
            } while (this.openList.size() != 0);
        }
        else {
            termPos = new XXX(mapId, x, y);
        }
        if (termPos == null) {
            return false;
        }
        ScriptOpen scriptOpen = new ScriptOpen(newX, newY);
        list.add(scriptOpen);
        XXX end = new XXX(newMapId, newX, newY);
        end.fa = termPos;
        while (end.fa != null) {
            if (end.xlp == null) {
                if (end.map == end.fa.map) {
                    XXX xxx = this.mapShort(end.fa, end);
                    this.addScript(xxx, list);
                }
            }
            else if (end.getMap() == end.fa.getMap()) {
                XXX xxx = this.mapShort(end.fa.recordXXX(), end.recordXXX());
                this.addScript(xxx, list);
            }
            end = end.fa;
            if (end.xlp != null) {
                if (end.xlp.npc != 0) {
                    scriptOpen = new ScriptOpen(end.xlp.map, end.xlp.x, end.xlp.y);
                    list.add(scriptOpen);
                    scriptOpen = new ScriptOpen(end.xlp.dx, end.xlp.dy);
                    list.add(scriptOpen);
                }
                else if (end.xlp.door != 0) {
                    scriptOpen = new ScriptOpen(end.xlp.map, end.xlp.x, end.xlp.y);
                    list.add(scriptOpen);
                    scriptOpen = new ScriptOpen(end.xlp.dx, end.xlp.dy);
                    list.add(scriptOpen);
                }
                else {
                    scriptOpen = new ScriptOpen(end.xlp.dx, end.xlp.dy);
                    list.add(scriptOpen);
                }
            }
        }
        return true;
    }
    
    private XXX mapShort(XXX one, XXX two) {
        this.openList.clear();
        XXX termPos = null;
        this.openList.add(new XXX(one.map, one.x, one.y));
        XLMap xlMap = this.getXLMap(one.map);
        List<XLP> list = xlMap.xlpTwo;
        do {
            XXX currentPos = (XXX)this.openList.get(0);
            for (int i = this.openList.size() - 1; i >= 0; --i) {
                if (currentPos.G > ((XXX)this.openList.get(i)).G) {
                    currentPos = (XXX)this.openList.get(i);
                }
            }
            this.openList.remove(currentPos);
            if (currentPos.x != two.x || currentPos.y != two.y) {
                if (list != null) {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        XLP p = (XLP)list.get(i);
                        if (!currentPos.isDoor(p.door)) {
                            XXX tmpPos = new XXX(p, currentPos);
                            tmpPos.G += 10000;
                            this.openList.add(tmpPos);
                        }
                    }
                }
                this.openList.add(new XXX(two.map, two.x, two.y, currentPos));
            }
            for (int j = this.openList.size() - 1; j >= 0; --j) {
                if (termPos != null && termPos.G <= ((XXX)this.openList.get(j)).G) {
                    this.openList.remove(j);
                }
                else if (((XXX)this.openList.get(j)).x == two.x && ((XXX)this.openList.get(j)).y == two.y) {
                    if (termPos == null) {
                        termPos = (XXX)this.openList.get(j);
                    }
                    else {
                        termPos = (XXX)this.openList.get(j);
                    }
                }
            }
        } while (this.openList.size() != 0);
        return termPos;
    }
    
    public void addScript(XXX xxx, List<ScriptOpen> list) {
        if (xxx == null) {
            return;
        }
        ScriptOpen scriptOpen;
        for (xxx = xxx.fa; xxx.fa != null; xxx = xxx.fa) {
            if (xxx.xlp.npc != 0) {
                scriptOpen = new ScriptOpen(xxx.xlp.map, xxx.xlp.x, xxx.xlp.y);
                list.add(scriptOpen);
                scriptOpen = new ScriptOpen(xxx.xlp.dx, xxx.xlp.dy);
                list.add(scriptOpen);
            }
            else if (xxx.xlp.door != 0) {
                scriptOpen = new ScriptOpen(xxx.xlp.map, xxx.xlp.x, xxx.xlp.y);
                list.add(scriptOpen);
                scriptOpen = new ScriptOpen(xxx.xlp.dx, xxx.xlp.dy);
                list.add(scriptOpen);
            }
            else {
                scriptOpen = new ScriptOpen(xxx.xlp.dx, xxx.xlp.dy);
                list.add(scriptOpen);
            }
        }
    }
    
    private int calcD(int x, int y, int tx, int ty) {
        x -= tx;
        y -= ty;
        x *= x;
        y *= y;
        return x + y;
    }
    
    private class XXX
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
            this.G = pos.G + XLPath.this.calcD(pos.x, pos.y, xlp.dx, xlp.dy) + 10000;
        }
        
        public XXX(int map, int x, int y, XXX pos) {
            this.map = map;
            this.x = x;
            this.y = y;
            this.fa = pos;
            this.G = pos.G + XLPath.this.calcD(pos.x, pos.y, x, y);
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
    
    private class XLP
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
    }
    
    private class XLMap
    {
        private int mapid;
        private List<XLP> xlpOne;
        private List<XLP> xlpTwo;
        
        public XLMap(int mapID) {
            this.mapid = mapID;
            Gamemap gamemap = (Gamemap)GameServer.getGameMap().get(mapID + "");
            if (gamemap != null) {
                if (gamemap.getMapway() != null && !gamemap.getMapway().equals("")) {
                    List<String> doorIds = SplitStringTool.splitString(gamemap.getMapway());
                    for (int i = 0; i < doorIds.size(); ++i) {
                        Door door = GameServer.getDoor(Integer.parseInt((String)doorIds.get(i)));
                        if (door != null && door.getRects() != null) {
                            this.addXLP(new XLP(door, mapID));
                        }
                    }
                }
                if (gamemap.getMapnpc() != null && !gamemap.getMapnpc().equals("")) {
                    List<String> npcIds = SplitStringTool.splitString(gamemap.getMapnpc());
                    for (int i = 0; i < npcIds.size(); ++i) {
                        Npctable npctable = GameServer.getNpc((String)npcIds.get(i));
                        if (npctable != null && npctable.getNpctype().equals("2") && npctable.getNpcway() != null && !npctable.getNpcway().equals("")) {
                            List<String> doorIds2 = SplitStringTool.splitString(npctable.getNpcway());
                            for (int j = 0; j < doorIds2.size(); ++j) {
                                Door door2 = GameServer.getDoor(Integer.parseInt((String)doorIds2.get(j)));
                                if (door2 != null) {
                                    this.addXLP(new XLP(door2, npctable, mapID));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        public void addXLP(XLP xlp) {
            if (xlp.map != this.mapid) {
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
    }
}
