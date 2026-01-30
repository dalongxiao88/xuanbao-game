package come.tool.newTrans;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import org.come.entity.RoleSummoning;
import java.util.Date;
import org.come.entity.Goodsrecord;
import org.come.entity.Record;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.entity.Goodstable;
import java.util.HashMap;
import org.come.server.GameServer;
import org.come.protocol.Agreement;
import org.come.handler.SendMessage;
import org.come.bean.LoginResult;
import java.util.concurrent.ConcurrentHashMap;

public class TransUtil
{
    public static int increasesum;
    private static ConcurrentHashMap<Integer, TransRoom> transMap;
    private static ConcurrentHashMap<String, Integer> transIntMap;
    static String MSGREMOVE;
    
    public static synchronized int getIncreasesum() {
        ++TransUtil.increasesum;
        if (TransUtil.increasesum > 99999999) {
            TransUtil.increasesum = 1;
        }
        return TransUtil.increasesum;
    }
    
    public static boolean isTrans(String roleName) {
        Integer transId = (Integer)TransUtil.transIntMap.get(roleName);
        if (transId != null) {
            TransRoom room = (TransRoom)TransUtil.transMap.get(transId);
            return room != null;
        }
        return false;
    }
    
    public static void launchTrans(LoginResult role1, LoginResult role2) {
        Integer transId = Integer.valueOf(getIncreasesum());
        TransRole transRole1 = new TransRole(role1.getRole_id(), role1.getRolename());
        TransRole transRole2 = new TransRole(role2.getRole_id(), role2.getRolename());
        TransRoom room = new TransRoom((int)transId, transRole1, transRole2);
        TransUtil.transMap.put(transId, room);
        TransUtil.transIntMap.put(role1.getRolename(), transId);
        TransUtil.transIntMap.put(role2.getRolename(), transId);
    }
    
    public static void removeTrans(Integer transId) {
        TransRoom transRoom = (TransRoom)TransUtil.transMap.remove(transId);
        if (transRoom != null) {
            TransUtil.transIntMap.remove(transRoom.getRole1().getRolename());
            TransUtil.transIntMap.remove(transRoom.getRole2().getRolename());
        }
    }
    
    public static void roleDown(String roleName) {
        Integer transId = (Integer)TransUtil.transIntMap.get(roleName);
        if (transId != null) {
            TransRoom transRoom = (TransRoom)TransUtil.transMap.remove(transId);
            if (transRoom != null) {
                if (!transRoom.getRole1().getRolename().equals(roleName)) {
                    SendMessage.sendMessageByRoleName(transRoom.getRole1().getRolename(), TransUtil.MSGREMOVE);
                }
                if (!transRoom.getRole2().getRolename().equals(roleName)) {
                    SendMessage.sendMessageByRoleName(transRoom.getRole2().getRolename(), TransUtil.MSGREMOVE);
                }
                TransUtil.transIntMap.remove(transRoom.getRole1().getRolename());
                TransUtil.transIntMap.remove(transRoom.getRole2().getRolename());
            }
        }
    }
    
    public static void removeTrans(String roleName) {
        Integer transId = (Integer)TransUtil.transIntMap.get(roleName);
        if (transId != null) {
            TransRoom transRoom = (TransRoom)TransUtil.transMap.remove(transId);
            if (transRoom != null) {
                SendMessage.sendMessageByRoleName(transRoom.getRole1().getRolename(), TransUtil.MSGREMOVE);
                SendMessage.sendMessageByRoleName(transRoom.getRole2().getRolename(), TransUtil.MSGREMOVE);
                TransUtil.transIntMap.remove(transRoom.getRole1().getRolename());
                TransUtil.transIntMap.remove(transRoom.getRole2().getRolename());
            }
        }
        SendMessage.sendMessageByRoleName(roleName, TransUtil.MSGREMOVE);
    }
    
    public static void lockingTrans(String roleName, int type) {
        Integer transId = (Integer)TransUtil.transIntMap.get(roleName);
        if (transId != null) {
            TransRoom room = (TransRoom)TransUtil.transMap.get(transId);
            if (room == null) {
                return;
            }
            synchronized (room) {
                TransRole role1 = null;
                TransRole role2 = null;
                if (room.getRole1().getRolename().equals(roleName)) {
                    role1 = room.getRole1();
                    role2 = room.getRole2();
                }
                else if (room.getRole2().getRolename().equals(roleName)) {
                    role1 = room.getRole2();
                    role2 = room.getRole1();
                }
                else {
                    return;
                }
                if (type == 3) {
                    if (role1.getState() != 0) {
                        return;
                    }
                    role1.setState(1);
                    String sendMessage = Agreement.getAgreement().TransStateAgreement("3|" + role1.getRolename());
                    SendMessage.sendMessageByRoleName(role1.getRolename(), sendMessage);
                    SendMessage.sendMessageByRoleName(role2.getRolename(), sendMessage);
                }
                else if (type == 4) {
                    if (role1.getState() == 0) {
                        return;
                    }
                    role1.setState(0);
                    if (role2.getState() == 2) {
                        role2.setState(1);
                    }
                    String sendMessage = Agreement.getAgreement().TransStateAgreement("4|" + role1.getRolename());
                    SendMessage.sendMessageByRoleName(role1.getRolename(), sendMessage);
                    SendMessage.sendMessageByRoleName(role2.getRolename(), sendMessage);
                }
                else {
                    if (role1.getState() == 0 || role2.getState() == 0) {
                        return;
                    }
                    role1.setState(2);
                    String sendMessage = Agreement.getAgreement().TransStateAgreement("5|" + role1.getRolename());
                    SendMessage.sendMessageByRoleName(role1.getRolename(), sendMessage);
                    SendMessage.sendMessageByRoleName(role2.getRolename(), sendMessage);
                    if (role2.getState() == 2) {
                        startTrans(room);
                        removeTrans(transId);
                    }
                }
            }
        }
    }
    
    public static void startTrans(TransRoom room) {
        LoginResult login1 = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(room.getRole1().getRolename()));
        LoginResult login2 = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(room.getRole2().getRolename()));
        if (login1 == null || login2 == null) {
            return;
        }
        GoodTrans goodTrans1 = room.getRole1().getGoodTrans();
        GoodTrans goodTrans2 = room.getRole2().getGoodTrans();
        long money = 0L;
        if (goodTrans1.getMoney() != null) {
            money -= goodTrans1.getMoney().longValue();
        }
        if (goodTrans2.getMoney() != null) {
            money += goodTrans2.getMoney().longValue();
        }
        if (money != 0L) {
            if (money < 0L) {
                if (login1.getGold().longValue() < Math.abs(money)) {
                    return;
                }
            }
            else if (login2.getGold().longValue() < Math.abs(money)) {
                return;
            }
        }
        Map<BigDecimal, Goodstable> map = new HashMap<>();
        if (!goodTrans1.check(map, login1.getRole_id())) {
            return;
        }
        if (!goodTrans2.check(map, login2.getRole_id())) {
            return;
        }
        for (Goodstable value : map.values()) {
            AllServiceUtil.getGoodsTableService().updateGoodRedis(value);
        }
        String sendMessage = Agreement.getAgreement().TransStateAgreement("5");
        SendMessage.sendMessageByRoleName(login1.getRolename(), sendMessage);
        SendMessage.sendMessageByRoleName(login2.getRolename(), sendMessage);
        AssetUpdate asset1 = null;
        AssetUpdate asset2 = null;
        if (money != 0L) {
            asset1 = new AssetUpdate();
            asset1.updata("D=" + money);
            login1.setGold(login1.getGold().add(new BigDecimal(money)));
            asset2 = new AssetUpdate();
            asset2.updata("D=" + -money);
            login2.setGold(login2.getGold().subtract(new BigDecimal(money)));
            if (MonitorUtil.isUpMoney(1, money)) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("交易大宗金额流动:");
                buffer.append(login1.getRole_id());
                buffer.append("交易");
                buffer.append(login2.getRole_id());
                buffer.append("金额");
                buffer.append(money);
                AllServiceUtil.getRecordService().insert(new Record(1, buffer.toString()));
            }
        }
        if (goodTrans1 != null) {
            if (goodTrans1.getGoods() != null && goodTrans1.getGoods().size() > 0) {
                for (Goodstable goodstable : goodTrans1.getGoods()) {
                    if ((long)goodstable.getQuality() > 1L) {
                        Goodsrecord record = new Goodsrecord();
                        record.setRoleid(login1.getRole_id());
                        record.setRolename(login1.getRolename());
                        record.setOtherrole(login2.getRole_id());
                        record.setOthername(login2.getRolename());
                        record.setRecordtime(new Date());
                        record.setGoods(goodstable.getGoodsid() + "," + goodstable.getGoodsname());
                        record.setGoodsnum(goodstable.getUsetime());
                        record.setRecordtype(Integer.valueOf(5));
                        AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
                    }
                }
            }
            if (goodTrans1.getPets() != null && goodTrans1.getPets().size() > 0) {
                for (RoleSummoning summoning : goodTrans1.getPets()) {
                    Goodsrecord record = new Goodsrecord();
                    record.setRoleid(login1.getRole_id());
                    record.setRolename(login1.getRolename());
                    record.setOtherrole(login2.getRole_id());
                    record.setOthername(login2.getRolename());
                    record.setRecordtime(new Date());
                    record.setGoods(summoning.getSummoningid() + "," + summoning.getSummoningname());
                    record.setGoodsnum(Integer.valueOf(1));
                    record.setRecordtype(Integer.valueOf(5));
                    AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
                }
            }
        }
        if (goodTrans2 != null) {
            if (goodTrans2.getGoods() != null && goodTrans2.getGoods().size() > 0) {
                for (Goodstable goodstable : goodTrans2.getGoods()) {
                    if ((long)goodstable.getQuality() > 1L) {
                        Goodsrecord record = new Goodsrecord();
                        record.setRoleid(login2.getRole_id());
                        record.setRolename(login2.getRolename());
                        record.setOtherrole(login1.getRole_id());
                        record.setOthername(login1.getRolename());
                        record.setRecordtime(new Date());
                        record.setGoods(goodstable.getGoodsid() + "," + goodstable.getGoodsname());
                        record.setGoodsnum(goodstable.getUsetime());
                        record.setRecordtype(Integer.valueOf(5));
                        AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
                    }
                }
            }
            if (goodTrans2.getPets() != null && goodTrans2.getPets().size() > 0) {
                for (RoleSummoning summoning : goodTrans2.getPets()) {
                    Goodsrecord record = new Goodsrecord();
                    record.setRoleid(login2.getRole_id());
                    record.setRolename(login2.getRolename());
                    record.setOtherrole(login1.getRole_id());
                    record.setOthername(login1.getRolename());
                    record.setRecordtime(new Date());
                    record.setGoods(summoning.getSummoningid() + "," + summoning.getSummoningname());
                    record.setGoodsnum(Integer.valueOf(1));
                    record.setRecordtype(Integer.valueOf(5));
                    AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
                }
            }
        }
        asset2 = goodTrans1.goTrans(asset2, map, login2.getRole_id());
        asset1 = goodTrans2.goTrans(asset1, map, login1.getRole_id());
        if (asset1 != null) {
            asset1.setType(AssetUpdate.DEAL);
            asset1.reset();
            String send1 = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(asset1));
            SendMessage.sendMessageByRoleName(login1.getRolename(), send1);
        }
        if (asset2 != null) {
            asset2.setType(AssetUpdate.DEAL);
            asset2.reset();
            String send2 = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(asset2));
            SendMessage.sendMessageByRoleName(login2.getRolename(), send2);
        }
    }
    
    public static void TransGood(String roleName, GoodTrans2 goodTrans2) {
        Integer transId = (Integer)TransUtil.transIntMap.get(roleName);
        if (transId != null) {
            TransRoom room = (TransRoom)TransUtil.transMap.get(transId);
            if (room != null) {
                TransRole role1 = null;
                TransRole role2 = null;
                if (room.getRole1().getRolename().equals(roleName)) {
                    role1 = room.getRole1();
                    role2 = room.getRole2();
                }
                else if (room.getRole2().getRolename().equals(roleName)) {
                    role1 = room.getRole2();
                    role2 = room.getRole1();
                }
                else {
                    return;
                }
                if (role1.getState() != 0) {
                    return;
                }
                role1.getGoodTrans().updateGood(goodTrans2);
                String send = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
                SendMessage.sendMessageByRoleName(role2.getRolename(), send);
            }
        }
    }
    
    static {
        TransUtil.transMap = new ConcurrentHashMap<>();
        TransUtil.transIntMap = new ConcurrentHashMap<>();
        TransUtil.MSGREMOVE = Agreement.getAgreement().TransStateAgreement("2");
    }
}
