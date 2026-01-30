package come.tool.newGang;

import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.bean.NChatBean;
import org.come.bean.PathPoint;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Battle.RewardLimit;
import org.come.server.GameServer;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import org.come.model.Sghostpoint;
import org.come.model.Robots;
import org.come.model.Boos;
import org.come.task.MonsterUtil;
import java.util.HashMap;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.task.MapMonsterBean;
import java.util.List;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.come.entity.Gang;

public class GangDomain
{
    private Object object;
    private Gang gang;
    private GangGroup gangGroup;
    private ConcurrentHashMap<BigDecimal, ChannelHandlerContext> roleMap;
    private int banditsSum;
    private int banditsValue;
    private int banditsTime;
    private List<MapMonsterBean> banditsList;
    private boolean isUp;
    
    public GangDomain(Gang gang) {
        this.object = new Object();
        this.gang = gang;
        this.gangGroup = new GangGroup(gang);
        this.roleMap = new ConcurrentHashMap<>();
        this.isUp = false;
    }
    
    public Map<Integer, MonsterMoveBase> getBandits(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap) {
        synchronized (this) {
            if (this.banditsList == null || this.banditsList.size() == 0) {
                return moveMap;
            }
            for (int i = 0; i < this.banditsList.size(); ++i) {
                MapMonsterBean bean = (MapMonsterBean)this.banditsList.get(0);
                if (i == 0) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
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
                }
                if (bean.getMove() != null) {
                    if (moveMap == null) {
                        moveMap = new HashMap<>();
                    }
                    moveMap.put(Integer.valueOf(bean.getMove().getBh()), bean.getMove().getMoveBase());
                }
                MonsterUtil.monsterBuffer1(bean, buffer);
            }
            return moveMap;
        }
    }
    
    public boolean banditsOpen(Boos boos, Robots robot, int size, Sghostpoint sghostpoint) {
        synchronized (this) {
            if (this.banditsValue > 0) {
                --this.banditsValue;
                return false;
            }
            if (this.roleMap.size() < 5) {
                return false;
            }
            if ((this.banditsList == null || this.banditsList.size() == 0) && Battlefield.isV((double)(boos.getBoosgpk() - this.banditsSum * 2))) {
                this.banditsTime = 30;
                if (this.banditsList == null) {
                    this.banditsList = new ArrayList<>();
                }
                int max = sghostpoint.getPoints().length;
                int robotId = Integer.parseInt(robot.getRobotid());
                long mapId = (long)GameServer.getMapIds(boos.getBoosmapname());
                StringBuffer buffer = new StringBuffer();
                buffer.append(robot.getRobotid());
                buffer.append("#");
                buffer.append(robot.getRobotname());
                buffer.append("#");
                buffer.append(robot.getRobotskin());
                buffer.append("#");
                buffer.append(0);
                int maxtime = boos.getBoosetime();
                for (int i = 0; i < size; ++i) {
                    MapMonsterBean mapMonsterBean = new MapMonsterBean();
                    mapMonsterBean.setI(Integer.valueOf(MonsterUtil.getIncreasesum()));
                    PathPoint point = sghostpoint.getPoints()[MonsterUtil.random.nextInt(max)];
                    mapMonsterBean.setX(Integer.valueOf(point.getX() + MonsterUtil.getPY()));
                    mapMonsterBean.setY((long)(point.getY() + MonsterUtil.getPY()));
                    mapMonsterBean.setRobotid(Integer.valueOf(robotId));
                    mapMonsterBean.setRobotname(robot.getRobotname());
                    mapMonsterBean.setRobotskin(robot.getRobotskin());
                    mapMonsterBean.setRobotType(0);
                    mapMonsterBean.setMap(Long.valueOf(mapId));
                    mapMonsterBean.setMaxtime(maxtime);
                    mapMonsterBean.setGangId(this.gang.getGangid());
                    mapMonsterBean.setTsModel(robot.getTsModel());
                    this.banditsList.add(mapMonsterBean);
                    MonsterUtil.allMonster.put(mapMonsterBean.getI(), mapMonsterBean);
                    MonsterUtil.monsterBuffer1(mapMonsterBean, buffer);
                    if (i == 0) {
                        mapMonsterBean.setBoosId(RewardLimit.isBoosDrop(boos));
                    }
                }
                SendMessage.sendMessageToGangMap(this.gang.getGangid(), mapId, Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString()));
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public void banditsEnd(MapMonsterBean bean) {
        synchronized (this) {
            if (this.banditsList == null) {
                return;
            }
            if (bean != null) {
                if (this.banditsList != null) {
                    synchronized (this.banditsList) {
                        this.banditsList.remove(bean);
                        if (this.banditsList.remove(bean)) {
                            int size = this.banditsList.size();
                            if (size == 0) {
                                this.banditsDraw(true);
                            }
                            else if (size <= 5 || size % 8 == 7) {
                                this.banditsSendMsg();
                            }
                        }
                    }
                }
                return;
            }
            else if (this.banditsList.size() != 0) {
                this.banditsTime -= 5;
                if (this.banditsTime > 0) {
                    this.banditsSendMsg();
                }
                else {
                    this.banditsDraw(false);
                }
            }
        }
    }
    
    public void banditsSendMsg() {
        NChatBean chatBean = new NChatBean();
        chatBean.setId(2);
        chatBean.setMessage("剩余帮派强盗数量:#R" + this.banditsList.size());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
        SendMessage.sendMessageToGangRoles(this.gang.getGangid(), msg);
    }
    
    public void banditsDraw(boolean isV) {
        if (isV) {
            ++this.banditsSum;
            long add = 15000L;
            this.addBG(add);
            NChatBean chatBean = new NChatBean();
            chatBean.setId(2);
            chatBean.setMessage("众人齐心协力共同抗敌,极力清除帮中强盗通过变卖强盗遗留物品为帮派资金增加#R " + add);
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
            SendMessage.sendMessageToGangRoles(this.gang.getGangid(), msg);
        }
        else {
            this.banditsValue = 3;
            long add = (long)(this.banditsList.size() * 1000);
            if (add > this.gang.getBuilder().longValue()) {
                add = this.gang.getBuilder().longValue();
            }
            this.addBG(-add);
            NChatBean chatBean = new NChatBean();
            chatBean.setId(2);
            chatBean.setMessage("强盗在帮中横行霸道,导致帮派资金被抢走了#R " + add + " #W,希望下次大家齐心协力共同抗敌，与强盗势不两立#23");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
            SendMessage.sendMessageToGangRoles(this.gang.getGangid(), msg);
            long mapId = 0L;
            StringBuffer buffer = new StringBuffer("M");
            for (int i = 0; i < this.banditsList.size(); ++i) {
                MapMonsterBean bean = (MapMonsterBean)this.banditsList.get(0);
                MonsterUtil.removeMonster2(bean);
                if (mapId == 0L) {
                    mapId = (long)bean.getMap();
                }
                if (buffer.length() > 1) {
                    buffer.append("#");
                }
                buffer.append(bean.getI());
                buffer.append("^2");
            }
            this.banditsList = null;
            if (mapId != 0L) {
                SendMessage.sendMessageToGangMap(this.gang.getGangid(), mapId, Agreement.getAgreement().battleStateAgreement(buffer.toString()));
            }
        }
    }
    
    public String getMsg(int type) {
        if (type == 2022) {
            return this.gangGroup.getKJNpc();
        }
        if (type == 2023) {
            return this.gangGroup.getXYNpc();
        }
        return null;
    }
    
    public void addBG(long add) {
        synchronized (this.object) {
            this.gang.setBuilder(new BigDecimal(this.gang.getBuilder().longValue() + add));
            this.isUp = true;
        }
    }
    
    public boolean useXY() {
        synchronized (this.object) {
            int num = this.gangGroup.getXyNum();
            if (num > 0) {
                this.gangGroup.setXyNum(num - 1);
                return this.isUp = true;
            }
            return false;
        }
    }
    
    public void upXY() {
        synchronized (this.object) {
            if (this.gangGroup.addXY(1)) {
                this.isUp = true;
            }
        }
    }
    
    public String upLvl(int type) {
        synchronized (this.object) {
            int lvl = 0;
            int xh = 0;
            String name = null;
            if (type == 47) {
                lvl = this.gang.getGanggrade().intValue();
                xh = (int)(Math.pow(2.0, (double)lvl) * 5000.0);
                name = "帮派等级";
            }
            else if (type == 48) {
                lvl = this.gangGroup.getKj();
                xh = (int)(Math.pow(2.0, (double)lvl) * 2000.0);
                name = "科技等级";
            }
            else if (type == 49) {
                lvl = this.gangGroup.getXy();
                xh = (int)(Math.pow(2.0, (double)lvl) * 2000.0);
                name = "驯养师等级";
            }
            if (lvl >= 5) {
                return "等级上限5级";
            }
            if (type != 47 && lvl >= this.gang.getGanggrade().intValue()) {
                return "不能超过帮派等级";
            }
            if (this.gang.getBuilder().longValue() < (long)xh) {
                return "你的帮派资金不足#G" + xh;
            }
            this.gang.setBuilder(new BigDecimal(this.gang.getBuilder().longValue() - (long)xh));
            if (type == 47) {
                this.gang.setGanggrade(this.gang.getGanggrade().add(new BigDecimal(1)));
            }
            else if (type == 48) {
                this.gangGroup.setKj(this.gangGroup.getKj() + 1);
            }
            else if (type == 49) {
                this.gangGroup.setXy(this.gangGroup.getXy() + 1);
            }
            this.isUp = true;
            return name + "升级成功";
        }
    }
    
    public void addGangRole() {
        synchronized (this.object) {
            this.gang.setGangnumber(this.gang.getGangnumber().add(new BigDecimal(1)));
            this.isUp = true;
        }
    }
    
    public void removeGangRole() {
        synchronized (this.object) {
            this.gang.setGangnumber(this.gang.getGangnumber().subtract(new BigDecimal(1)));
            this.isUp = true;
        }
    }
    
    public void upGangMaster(String roleName) {
        synchronized (this.object) {
            this.gang.setGangbelong(roleName);
            this.isUp = true;
        }
    }
    
    public void upGang() {
        synchronized (this.object) {
            if (this.isUp) {
                this.gang.setGangTxt(this.gangGroup.getTxt());
                AllServiceUtil.getGangService().updateGang(this.gang);
                this.isUp = false;
            }
        }
    }
    
    public void upGangRole(BigDecimal roleId, ChannelHandlerContext ctx) {
        this.roleMap.put(roleId, ctx);
    }
    
    public void downGangRole(BigDecimal roleId) {
        this.roleMap.remove(roleId);
    }
    
    public Gang getGang() {
        return this.gang;
    }
    
    public GangGroup getGangGroup() {
        return this.gangGroup;
    }
    
    public ConcurrentHashMap<BigDecimal, ChannelHandlerContext> getRoleMap() {
        return this.roleMap;
    }
}
