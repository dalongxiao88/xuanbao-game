package org.come.task;

import come.tool.Role.RolePool;
import come.tool.Good.TSModel;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Battle.BattleData;
import org.come.bean.NChatBean;
import org.come.model.Lshop;
import org.come.model.Gamemap;
import come.tool.Battle.RewardLimit;
import org.come.until.GsonUtil;
import org.come.model.Shop;
import org.come.entity.Goodstable;
import org.come.tool.SplitStringTool;
import org.come.bean.ShopGoodsBean;
import come.tool.Good.FYModel;
import org.come.bean.PathPoint;
import org.come.server.GameServer;
import org.come.model.Sghostpoint;
import org.come.model.Robots;
import org.come.entity.Gang;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Scene.Scene;
import come.tool.Scene.RC.RCScene;
import come.tool.newGang.GangDomain;
import java.util.Iterator;
import come.tool.Scene.SceneUtil;
import come.tool.newGang.GangUtil;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import come.tool.Scene.RC.BBBrush;
import org.come.model.Boos;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MonsterUtil
{
    public static ConcurrentHashMap<Integer, MapMonsterBean> allMonster;
    private static ConcurrentHashMap<Long, ConcurrentHashMap<Integer, List<MapMonsterBean>>> allMapMonsterMap;
    private static List<MapMonsterBean> followList;
    public static int increasesum;
    public static Random random;
    private static final int JG = 5;
    public static List<Boos> booses;
    private static BBBrush bbBrush;
    private static MonsterMatchThread matchThread;
    public static List<MapMonsterBean> matchList;
    
    public static void init() {
        MonsterUtil.allMonster = new ConcurrentHashMap<>();
        MonsterUtil.allMapMonsterMap = new ConcurrentHashMap<>();
        MonsterUtil.followList = new ArrayList<>();
        MonsterUtil.matchList = new ArrayList<>();
        (MonsterUtil.matchThread = new MonsterMatchThread()).start();
    }
    
    public static List<MapMonsterBean> getList(long mapId, int robotId) {
        ConcurrentHashMap<Integer, List<MapMonsterBean>> map = (ConcurrentHashMap<Integer, List<MapMonsterBean>>)MonsterUtil.allMapMonsterMap.get(Long.valueOf(mapId));
        if (map == null) {
            map = new ConcurrentHashMap<>();
            MonsterUtil.allMapMonsterMap.put(Long.valueOf(mapId), map);
        }
        List<MapMonsterBean> lists = (List<MapMonsterBean>)map.get(Integer.valueOf(robotId));
        if (lists == null) {
            lists = new ArrayList<>();
            map.put(Integer.valueOf(robotId), lists);
        }
        return lists;
    }
    
    public static String getMapMonster(long mapId, BigDecimal gangID) {
        StringBuffer buffer = new StringBuffer();
        Map<Integer, MonsterMoveBase> moveMap = null;
        ConcurrentHashMap<Integer, List<MapMonsterBean>> value = (ConcurrentHashMap<Integer, List<MapMonsterBean>>)MonsterUtil.allMapMonsterMap.get(Long.valueOf(mapId));
        if (value != null) {
            for (Map.Entry<Integer, List<MapMonsterBean>> entrys : value.entrySet()) {
                List<MapMonsterBean> list = (List<MapMonsterBean>)entrys.getValue();
                if (list.size() != 0) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    MapMonsterBean bean = (MapMonsterBean)list.get(0);
                    buffer.append(bean.getRobotid());
                    buffer.append("#");
                    buffer.append(bean.getRobotname());
                    if (bean.getRobottitle() != null && !bean.getRobottitle().equals("")) {
                        buffer.append("$");
                        buffer.append(bean.getRobottitle());
                    }
                    buffer.append("#");
                    buffer.append(bean.getRobotskin());
                    buffer.append("#");
                    buffer.append(bean.getRobotType());
                    buffer.append("#");
                    buffer.append(bean.getIsMove());
                    if (StringUtils.isNotBlank(bean.getEwParam())) {
                        buffer.append("#");
                        buffer.append(bean.getEwParam());
                    }
                    for (int i = list.size() - 1; i >= 0; --i) {
                        bean = (MapMonsterBean)list.get(i);
                        if (bean.getMove() != null) {
                            if (moveMap == null) {
                                moveMap = new HashMap<>();
                            }
                            moveMap.put(Integer.valueOf(bean.getMove().getBh()), bean.getMove().getMoveBase());
                        }
                        monsterBuffer1(bean, buffer);
                    }
                }
            }
        }
        if (mapId == 3000L && gangID != null && gangID.intValue() != 0) {
            GangDomain gangDomain = GangUtil.getGangDomain(gangID);
            if (gangDomain != null) {
                moveMap = gangDomain.getBandits(buffer, moveMap);
            }
        }
        moveMap = SceneUtil.getMapMonster(buffer, moveMap, mapId);
        if (buffer.length() == 0) {
            return null;
        }
        if (moveMap != null && moveMap.size() != 0) {
            StringBuffer moveBuffer = new StringBuffer();
            moveBuffer.append("M");
            for (MonsterMoveBase move : moveMap.values()) {
                if (moveBuffer.length() > 1) {
                    moveBuffer.append("#");
                }
                moveBuffer.append(move.getMoveMsg());
            }
            moveBuffer.append("|");
            buffer = moveBuffer.append(buffer);
        }
        return buffer.toString();
    }
    
    public static void refurbishMonster(String week, int day, int hour, int minute, int second) {
        clearMonsters();
        for (int i = 0; i < MonsterUtil.booses.size(); ++i) {
            Boos boos = (Boos)MonsterUtil.booses.get(i);
            if (!boos.getBoosid().equals("") && week(boos.getBoosweekday(), week) && (time(boos.getBoosstime(), boos.getBoosetime(), hour) && Refresh(boos.getBoosstime(), boos.getBoosrtime(), hour, minute))) {
                refreshMonsters(boos, null, null, null);
            }
        }
        if (minute == 0 && hour == 22 && day == 6) {
            Scene scene = SceneUtil.getScene(1009);
            if (scene != null) {
                RCScene rcScene = (RCScene)scene;
                MonsterUtil.bbBrush = rcScene.BBOpen();
            }
        }
        if (MonsterUtil.bbBrush != null) {
            MonsterUtil.bbBrush.setD(MonsterUtil.bbBrush.getD() + 1);
            Boos boos2 = MonsterUtil.bbBrush.getBoos();
            if (boos2 != null) {
                refreshMonsters(boos2, null, "当前第" + MonsterUtil.bbBrush.getD() + "波,总计" + MonsterUtil.bbBrush.getZ() + "波", null);
            }
            if (MonsterUtil.bbBrush.getD() >= MonsterUtil.bbBrush.getZ()) {
                MonsterUtil.bbBrush = null;
            }
        }
    }
    
    public static void clearMonsters() {
        for (Map.Entry<Long, ConcurrentHashMap<Integer, List<MapMonsterBean>>> entrys : MonsterUtil.allMapMonsterMap.entrySet()) {
            clearMonsters((long)entrys.getKey(), (ConcurrentHashMap<Integer, List<MapMonsterBean>>)entrys.getValue());
        }
        timeGang();
    }
    
    public static void clearMonsters(long mapid, ConcurrentHashMap<Integer, List<MapMonsterBean>> value) {
        Iterator<Map.Entry<Integer, List<MapMonsterBean>>> iterator = value.entrySet().iterator();
        StringBuffer buffer = new StringBuffer();
        buffer.append("M");
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<MapMonsterBean>> entrys = (Map.Entry<Integer, List<MapMonsterBean>>)iterator.next();
            List<MapMonsterBean> list = (List<MapMonsterBean>)entrys.getValue();
            if (list.size() == 0) {
                iterator.remove();
            }
            else {
                for (int i = list.size() - 1; i >= 0; --i) {
                    MapMonsterBean bean = (MapMonsterBean)list.get(i);
                    if (bean.isMaxtime(5)) {
                        if (buffer.length() > 1) {
                            buffer.append("#");
                        }
                        list.remove(i);
                        MonsterUtil.allMonster.remove(bean.getI());
                        if (bean.getFollow() != null) {
                            MonsterUtil.followList.remove(bean);
                        }
                        buffer.append(bean.getI());
                        buffer.append("^2");
                    }
                }
            }
        }
        if (buffer.length() > 1) {
            SendMessage.sendMessageToMapRoles(Long.valueOf(mapid), Agreement.getAgreement().battleStateAgreement(buffer.toString()));
        }
    }
    
    public static void timeGang() {
        List<Gang> gangs = GangUtil.getGangs();
        for (int i = gangs.size() - 1; i >= 0; --i) {
            Gang gang = (Gang)gangs.get(i);
            GangDomain gangDomain = GangUtil.getGangDomain(gang.getGangid());
            if (gangDomain != null) {
                gangDomain.banditsEnd(null);
            }
        }
    }
    
    public static List<BigDecimal> refreshMonstersGang(Boos boos, Robots robot, int size, Sghostpoint sghostpoint) {
        List<BigDecimal> ids = new ArrayList<>();
        List<Gang> gangs = GangUtil.getGangs();
        for (int i = gangs.size() - 1; i >= 0; --i) {
            Gang gang = (Gang)gangs.get(i);
            GangDomain gangDomain = GangUtil.getGangDomain(gang.getGangid());
            if (gangDomain != null && gangDomain.banditsOpen(boos, robot, size, sghostpoint)) {
                ids.add(gang.getGangid());
            }
        }
        return ids;
    }
    
    public static void refreshMonsters(Boos boos, BigDecimal roleID, String name, int robotType, int size, int SX, MapZB mapZB) {
        Sghostpoint sghostpoint = null;
        if (mapZB == null) {
            if (boos.getBoosmapname().startsWith("指定位置")) {
                String[] v = boos.getBoosmapname().split("=");
                Gamemap map = GameServer.getMap(v[1]);
                sghostpoint = GameServer.getSghostpoint(map.getMapname());
                Sghostpoint sghostpoint2 = new Sghostpoint();
                sghostpoint2.setPointkey(sghostpoint.getPointkey());
                sghostpoint2.setPointmap(sghostpoint.getPointmap());
                sghostpoint2.setPointtype(sghostpoint.getPointtype());
                sghostpoint2.setPointidString(sghostpoint.getPointidString());
                sghostpoint2.setPointpoint(v[2] + "," + v[3]);
                PathPoint[] pathPointArr = { null };
                PathPoint pathPoint = new PathPoint();
                pathPoint.setY(Integer.parseInt(v[3]));
                pathPoint.setX(Integer.parseInt(v[2]));
                pathPointArr[0] = pathPoint;
                sghostpoint2.setPoints(pathPointArr);
                sghostpoint = sghostpoint2;
                mapZB = new MapZB(map.getMapname(), pathPoint.getX(), (long)pathPoint.getY());
            }
            else {
                sghostpoint = GameServer.getSghostpoint(boos.getBoosmapname());
                if (sghostpoint == null || sghostpoint.getPoints().length == 0) {
                    System.out.println(boos.getBoosmapname() + "_没有对应刷怪点");
                    return;
                }
            }
        }
        Robots robot = (Robots)GameServer.getAllRobot().get(boos.getBoosrobot());
        if (robot == null) {
            System.out.println(boos.getBoosrobot() + "_没有对应robot");
            return;
        }
        FYModel fyModel = (roleID != null && boos.getFyDrop() != null && !boos.getFyDrop().equals("")) ? new FYModel(roleID, boos.getFyDrop()) : null;
        String zb = null;
        long mapId = (long)GameServer.getMapIds((mapZB != null) ? mapZB.getMap() : boos.getBoosmapname());
        ConcurrentHashMap<Integer, List<MapMonsterBean>> map2 = (ConcurrentHashMap<Integer, List<MapMonsterBean>>)MonsterUtil.allMapMonsterMap.get(Long.valueOf(mapId));
        if (map2 == null) {
            map2 = new ConcurrentHashMap<>();
            MonsterUtil.allMapMonsterMap.put(Long.valueOf(mapId), map2);
        }
        int robotId = Integer.parseInt(robot.getRobotid());
        List<MapMonsterBean> lists = (List<MapMonsterBean>)map2.get(Integer.valueOf(robotId));
        if (lists == null) {
            lists = new ArrayList<>();
            map2.put(Integer.valueOf(robotId), lists);
        }
        if (size == 0) {
            size = boos.getBoosnum();
        }
        if (robotType == 0) {
            robotType = robot.getRobotType();
        }
        int max = (sghostpoint != null) ? sghostpoint.getPoints().length : 1;
        List<BigDecimal> gangIDs = null;
        if (boos.getBoosgpk() != 0) {
            gangIDs = refreshMonstersGang(boos, robot, size, sghostpoint);
        }
        else {
            StringBuffer buffer = new StringBuffer();
            buffer.append(robot.getRobotid());
            buffer.append("#");
            buffer.append(robot.getRobotname());
            buffer.append("#");
            buffer.append(robot.getRobotskin());
            buffer.append("#");
            buffer.append(robotType);
            buffer.append("#");
            if (StringUtils.isNotBlank(boos.getIsMove())) {
                buffer.append(1);
            }
            else {
                buffer.append(0);
            }
            if (StringUtils.isNotBlank(robot.getEwParam())) {
                buffer.append("#");
                buffer.append(robot.getEwParam());
            }
            int maxtime = boos.getBoosetime();
            for (int i = 0; i < size; ++i) {
                MapMonsterBean mapMonsterBean = new MapMonsterBean();
                if (mapZB != null) {
                    mapMonsterBean.setX(Integer.valueOf(mapZB.getX() + getPYTwo()));
                    mapMonsterBean.setY(mapZB.getY() + (long)getPYTwo());
                }
                else {
                    PathPoint point = sghostpoint.getPoints()[MonsterUtil.random.nextInt(max)];
                    mapMonsterBean.setX(Integer.valueOf(point.getX() + getPY()));
                    mapMonsterBean.setY((long)(point.getY() + getPY()));
                }
                mapMonsterBean.setRobotid(Integer.valueOf(robotId));
                mapMonsterBean.setRobotname(robot.getRobotname());
                mapMonsterBean.setRobotskin(robot.getRobotskin());
                mapMonsterBean.setRobotType(robotType);
                mapMonsterBean.setI(Integer.valueOf(getIncreasesum()));
                mapMonsterBean.setMap(Long.valueOf(mapId));
                mapMonsterBean.setMaxtime(robot.getRobottime());
                mapMonsterBean.setSX(SX);
                mapMonsterBean.setGoodId(robot.getGoodId());
                if (StringUtils.isNotBlank(boos.getIsMove())) {
                    mapMonsterBean.setIsMove("1");
                }
                else {
                    mapMonsterBean.setIsMove("0");
                }
                if (StringUtils.isNotBlank(robot.getEwParam())) {
                    mapMonsterBean.setEwParam(robot.getEwParam());
                }
                mapMonsterBean.setTsModel(robot.getTsModel());
                mapMonsterBean.setFyModel(fyModel);
                if (robot.getHpSet() != null) {
                    mapMonsterBean.setHpSet(robot.getHpSet());
                }
                if (robotType == 2) {
                    ShopGoodsBean shopGoodsBean = new ShopGoodsBean();
                    shopGoodsBean.setnId(mapMonsterBean.getI());
                    shopGoodsBean.setType(0);
                    ConcurrentHashMap<String, Lshop> shops = new ConcurrentHashMap<>();
                    List<Shop> shopList = new ArrayList<>();
                    List<String> ids = SplitStringTool.Randoms(robot.getRobotreward());
                    for (int j = 0; j < ids.size(); ++j) {
                        Lshop lshop = GameServer.getLshop((String)ids.get(j));
                        if (lshop != null) {
                            Goodstable good = (Goodstable)GameServer.getAllGoodsMap().get(lshop.getGid());
                            if (good != null) {
                                shops.put(lshop.getId() + "", lshop);
                                Shop shop = new Shop();
                                shop.setShopid(lshop.getId() + "");
                                shop.setShopiid(lshop.getGid());
                                shop.setShopname(good.getGoodsname());
                                shop.setShoptype(lshop.getType());
                                shop.setShopprice(lshop.getMoney().longValue());
                                shop.setShopskin(good.getSkin());
                                shop.setShoptext(good.getInstruction());
                                shopList.add(shop);
                            }
                        }
                    }
                    shopGoodsBean.setShopList(shopList);
                    mapMonsterBean.setShops(shops);
                    mapMonsterBean.setShopMsg(Agreement.getAgreement().BuyShopGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(shopGoodsBean)));
                    zb = "(" + (int)mapMonsterBean.getX() / 20 + "," + mapMonsterBean.getY() / 20L + ")";
                }
                else if (robotType == 3) {
                    ConcurrentHashMap<String, Lshop> shops2 = new ConcurrentHashMap<>();
                    List<String> ids2 = SplitStringTool.Randoms(robot.getRobotreward());
                    for (int k = 0; k < ids2.size(); ++k) {
                        Lshop lshop2 = GameServer.getLshop((String)ids2.get(k));
                        if (lshop2 != null) {
                            Goodstable good2 = (Goodstable)GameServer.getAllGoodsMap().get(lshop2.getGid());
                            if (good2 != null) {
                                shops2.put(lshop2.getGid().toString(), lshop2);
                            }
                        }
                    }
                    mapMonsterBean.setShops(shops2);
                    zb = "(" + (int)mapMonsterBean.getX() / 20 + "," + mapMonsterBean.getY() / 20L + ")";
                }
                else if (robotType == 4 || robotType == 132 || robotType == 66) {
                    if (robotType == 132 || robotType == 66) {
                        mapMonsterBean.setCreateTime(0L);
                    }
                    else {
                        mapMonsterBean.setCreateTime(System.currentTimeMillis());
                    }
                    MonsterMatch match = new MonsterMatch();
                    mapMonsterBean.setMatch(match);
                    MonsterFollow follow = new MonsterFollow(sghostpoint.getPoints());
                    mapMonsterBean.setFollow(follow);
                    MonsterUtil.followList.add(mapMonsterBean);
                    zb = "(" + (int)mapMonsterBean.getX() / 20 + "," + mapMonsterBean.getY() / 20L + ")";
                }
                lists.add(mapMonsterBean);
                MonsterUtil.allMonster.put(mapMonsterBean.getI(), mapMonsterBean);
                monsterBuffer1(mapMonsterBean, buffer);
                if (i == 0) {
                    mapMonsterBean.setBoosId(RewardLimit.isBoosDrop(boos));
                }
                if (GameServer.random.nextInt(3) == 0) {
                    GameServer.golemServer.assignedRobot(mapMonsterBean);
                }
            }
            SendMessage.sendMessageToMapRoles(Long.valueOf(mapId), Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
        }
        boosChat(boos, name, zb, gangIDs);
    }
    
    public static void refreshMonsters(Boos boos, BigDecimal roleID, String name, MapZB mapZB) {
        refreshMonsters(boos, roleID, name, 0, 0, 0, mapZB);
    }
    
    public static Map<Integer, MonsterMoveBase> monsterBuffer(MapMonsterBean bean, StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap) {
        if (bean.getMove() != null) {
            if (moveMap == null) {
                moveMap = new HashMap<>();
            }
            moveMap.put(Integer.valueOf(bean.getMove().getBh()), bean.getMove().getMoveBase());
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(bean.getRobotid());
        buffer.append("#");
        buffer.append(bean.getRobotname());
        buffer.append("#");
        buffer.append(bean.getRobotskin());
        buffer.append("#");
        buffer.append(bean.getRobotType());
        monsterBuffer1(bean, buffer);
        return moveMap;
    }
    
    public static void monsterBuffer1(MapMonsterBean bean, StringBuffer buffer) {
        buffer.append("#");
        buffer.append(bean.getI());
        buffer.append("^");
        buffer.append(bean.getX());
        buffer.append("^");
        buffer.append(bean.getY());
        if (bean.getType() == 1 && bean.getMove() != null) {
            buffer.append("^S1");
        }
        if (bean.getFollow() != null && bean.getFollow().getFollowID() != null) {
            buffer.append("^G");
            buffer.append(bean.getFollow().getFollowID());
        }
        if (bean.getHp() != null) {
            buffer.append("^H");
            buffer.append(bean.getHp().getHp());
            buffer.append(",");
            buffer.append(bean.getHp().getHpMax());
        }
        if (bean.getMove() != null) {
            buffer.append("^L");
            buffer.append(bean.getMove().getBh());
            buffer.append(",");
            buffer.append(bean.getMove().getTime());
        }
    }
    
    public static void sendMsg(String msg, List<BigDecimal> gangIDs) {
        if (gangIDs != null) {
            for (int i = 0; i < gangIDs.size(); ++i) {
                SendMessage.sendMessageToGangRoles((BigDecimal)gangIDs.get(i), msg);
            }
            return;
        }
        else {
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
    }
    
    public static void boosChat(Boos boos, String name, String zb, List<BigDecimal> gangIDs) {
        String v1 = boos.getBoostext();
        String v2 = boos.getBoosGGtext();
        if (v1 != null && v2 != null && v1.equals(v2)) {
            if (!v1.equals("")) {
                NChatBean bean = new NChatBean();
                bean.setId(7);
                if (name != null) {
                    v1 = v1.replace("{角色名}", name);
                }
                if (zb != null) {
                    v1 = v1.replace("{坐标}", zb);
                }
                bean.setMessage(v1);
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                sendMsg(msg, gangIDs);
            }
            return;
        }
        else {
            if (v1 != null && !v1.equals("")) {
                NChatBean bean = new NChatBean();
                bean.setId(5);
                if (name != null) {
                    v1 = v1.replace("{角色名}", name);
                }
                if (zb != null) {
                    v1 = v1.replace("{坐标}", zb);
                }
                bean.setMessage(v1);
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                sendMsg(msg, gangIDs);
            }
            if (v2 != null && !v2.equals("")) {
                NChatBean bean = new NChatBean();
                bean.setId(9);
                if (name != null) {
                    v2 = v2.replace("{角色名}", name);
                }
                if (zb != null) {
                    v2 = v2.replace("{坐标}", zb);
                }
                bean.setMessage(v2);
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                sendMsg(msg, gangIDs);
            }
            return;
        }
    }
    
    public static int getPY() {
        return (MonsterUtil.random.nextInt(15) - 7) * 7;
    }
    
    public static int getPYTwo() {
        return (MonsterUtil.random.nextInt(21) - 10) * 7;
    }
    
    public static boolean week(String weeks, String week) {
        String[] names = weeks.split("\\|");
        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(week) || names[i].equals("")) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean time(int start, int end, int current) {
        return (start == 0 && end == 24) || (current >= start && current < end);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 12; ++j) {
                if (Refresh(0, 45, i, j * 5)) {
                    System.out.println(i + ":" + j * 5 + ":" + Refresh(0, 45, i, j * 5));
                }
            }
        }
    }
    
    public static boolean Refresh(int start, int interval, int current_Hour, int current_minute) {
        current_Hour = current_Hour * 60 + current_minute;
        return (current_Hour - interval * 60) % interval == 0;
    }
    
    public static MapMonsterBean getMonster(int I) {
        return (MapMonsterBean)MonsterUtil.allMonster.get(Integer.valueOf(I));
    }
    
    public static void addMonster(MapMonsterBean bean) {
        MonsterUtil.allMonster.put(bean.getI(), bean);
        getList((long)bean.getMap(), (int)bean.getRobotid()).add(bean);
    }
    
    public static void addEMonster(MapMonsterBean bean) {
        MonsterUtil.allMonster.put(bean.getI(), bean);
    }
    
    public static void removeMonster(MapMonsterBean bean, int type) {
        MonsterUtil.allMonster.remove(bean.getI());
        getList((long)bean.getMap(), (int)bean.getRobotid()).remove(bean);
        SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().battleStateAgreement("M" + bean.getI() + "^2"));
    }
    
    public static String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        MapMonsterBean bean = battleData.getMonsterBean();
        if (bean.getSX() != 0 && bean.getSX() != 1) {
            Scene scene = SceneUtil.getScene(bean.getSX());
            if (scene != null) {
                return scene.UPMonster(battleData, teams, type, buffer);
            }
        }
        if (type == 1) {
            if (bean.getHp() == null || !bean.getHp().isMuch()) {
                bean.setType(1);
            }
            if (bean.getMove() != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^");
                buffer.append(bean.getType());
            }
        }
        else if (type == 2) {
            if (bean.getFollow() != null) {
                if (bean.getFollow().getTime() == 0L) {
                    bean.getFollow().setTime(1L);
                }
                else {
                    bean.getFollow().setTime(System.currentTimeMillis());
                }
                bean.setType(0);
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[0]);
                LoginResult log2 = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
                if (log2 != null) {
                    bean.getFollow().setFollowID(log2.getRole_id());
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^");
                    buffer.append(bean.getType());
                    buffer.append("^G");
                    buffer.append(log2.getRole_id());
                    StringBuffer bufferTwo = new StringBuffer();
                    bufferTwo.append("#R");
                    bufferTwo.append(log2.getRolename());
                    bufferTwo.append("#Y在");
                    bufferTwo.append(GameServer.getMapName(log2.getMapid().toString()));
                    bufferTwo.append("(");
                    bufferTwo.append((long)log2.getX() / 20L);
                    bufferTwo.append(",");
                    bufferTwo.append((long)log2.getY() / 20L);
                    bufferTwo.append(")抢到#R");
                    bufferTwo.append(bean.getRobotname());
                    NChatBean chatBean = new NChatBean();
                    chatBean.setId(9);
                    chatBean.setMessage(bufferTwo.toString());
                    SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean)));
                }
            }
            else if (bean.getHp() != null) {
                bean.getHp().addHp(-1L);
                if (bean.getHp().getHp() <= 0L && bean.getTsModel() != null) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^2");
                    MonsterUtil.allMonster.remove(bean.getI());
                    getList((long)bean.getMap(), (int)bean.getRobotid()).remove(bean);
                    TSModel tsModel = bean.getDieTsModel();
                    if (tsModel != null) {
                        tsModel.die(bean, teams);
                    }
                    FYModel fyModel = bean.getDieFyModel();
                    if (fyModel != null) {
                        fyModel.die(bean, teams);
                    }
                }
                else {
                    if (bean.getHp().getHp() <= 0L) {
                        MonsterUtil.allMonster.remove(bean.getI());
                        getList((long)bean.getMap(), (int)bean.getRobotid()).remove(bean);
                        StringBuffer bufferTwo2 = new StringBuffer();
                        bufferTwo2.append(bean.getRobotname());
                        bufferTwo2.append("已被#R");
                        bufferTwo2.append(teams[0]);
                        bufferTwo2.append("#Y所带领的队伍完成最后一击");
                        NChatBean chatBean2 = new NChatBean();
                        chatBean2.setId(4);
                        chatBean2.setMessage(bufferTwo2.toString());
                        SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean2)));
                    }
                    else {
                        bean.setType(0);
                    }
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("M");
                    buffer.append(bean.getI());
                    buffer.append("^");
                    buffer.append(bean.getType());
                    buffer.append("^H");
                    buffer.append(bean.getHp().getHp());
                    buffer.append(",");
                    buffer.append(bean.getHp().getHpMax());
                }
            }
            else {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^2");
                MonsterUtil.allMonster.remove(bean.getI());
                getList((long)bean.getMap(), (int)bean.getRobotid()).remove(bean);
                TSModel tsModel = bean.getDieTsModel();
                if (tsModel != null) {
                    tsModel.die(bean, teams);
                }
                FYModel fyModel = bean.getDieFyModel();
                if (fyModel != null) {
                    fyModel.die(bean, teams);
                }
            }
        }
        else if (type == 0) {
            bean.setType(0);
            if (bean.getExp() != null && teams != null && teams.length >= 1) {
                NChatBean chatBean3 = new NChatBean();
                chatBean3.setId(4);
                chatBean3.setMessage(bean.getExp().addEXP(bean, teams[0]));
                SendMessage.sendMessageToAllRoles(Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean3)));
            }
            if (bean.getFollow() != null && bean.getFollow().getFollowID() == null) {
                PathPoint point = bean.getFollow().getPoints()[MonsterUtil.random.nextInt(bean.getFollow().getPoints().length)];
                bean.setX(Integer.valueOf(point.getX()));
                bean.setY((long)point.getY());
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^0^Z");
                buffer.append(bean.getX());
                buffer.append(",");
                buffer.append(bean.getY());
                StringBuffer bufferTwo3 = new StringBuffer();
                bufferTwo3.append(bean.getRobotname());
                bufferTwo3.append("重新出现在#R(");
                bufferTwo3.append(point.getX() / 20);
                bufferTwo3.append(",");
                bufferTwo3.append(point.getY() / 20);
                bufferTwo3.append(")");
                NChatBean chatBean4 = new NChatBean();
                chatBean4.setId(4);
                chatBean4.setMessage(bufferTwo3.toString());
                SendMessage.sendMessageToMapRoles(bean.getMap(), Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean4)));
            }
            else if (bean.getMove() != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("M");
                buffer.append(bean.getI());
                buffer.append("^0");
            }
        }
        return null;
    }
    
    public static void removeMonster2(MapMonsterBean bean) {
        MonsterUtil.allMonster.remove(bean.getI());
        if (bean.getFollow() != null) {
            MonsterUtil.followList.remove(bean);
        }
    }
    
    public static synchronized int getIncreasesum() {
        ++MonsterUtil.increasesum;
        if (MonsterUtil.increasesum > 99999999) {
            MonsterUtil.increasesum = 1;
        }
        return MonsterUtil.increasesum;
    }
    
    public static MapMonsterBean getFollowMonster(String... names) {
        for (int i = 0; i < MonsterUtil.followList.size(); ++i) {
            MapMonsterBean bean = (MapMonsterBean)MonsterUtil.followList.get(i);
            if (bean.getFollow() != null && bean.getFollow().getFollowID() != null) {
                LoginResult loginResult = RolePool.getLoginResult(bean.getFollow().getFollowID());
                if (loginResult == null) {
                    bean.getFollow().setFollowID(null);
                }
                else {
                    String follow = loginResult.getRolename();
                    for (int j = 0; j < names.length; ++j) {
                        if (names[j].equals(follow)) {
                            return bean;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static void addMatch(LoginResult loginResult, MapMonsterBean monsterBean) {
        if (loginResult == null) {
            return;
        }
        if (monsterBean.getMatch().addMatch(loginResult.getRolename())) {
            String TS = (monsterBean.getRobotType() == 4) ? MonsterUtil.matchThread.MSG : ((monsterBean.getRobotType() == 132) ? MonsterUtil.matchThread.MSGTwo : Agreement.getAgreement().PromptAgreement(monsterBean.getRobotname() + "正在挑选顺眼的玩家…… 请等待#24"));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), TS);
            if (!MonsterUtil.matchList.contains(monsterBean)) {
                MonsterUtil.matchList.add(monsterBean);
                MonsterUtil.matchThread.addMatch();
            }
        }
    }
    
    public static boolean isMatch() {
        return MonsterUtil.matchList.size() != 0;
    }
    
    public static List<Boos> getBooses() {
        return MonsterUtil.booses;
    }
    
    public static void setBooses(List<Boos> booses) {
        MonsterUtil.booses = booses;
    }
    
    static {
        MonsterUtil.random = new Random();
    }
}
