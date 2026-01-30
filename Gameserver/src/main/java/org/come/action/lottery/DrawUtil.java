package org.come.action.lottery;

import org.come.entity.Goodsrecord;
import org.come.entity.Goodstable;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.suit.SuitMixdeal;
import come.tool.Good.AddGoodAction;
import org.come.bean.XXGDBean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.come.redis.RedisControl;
import org.come.server.GameServer;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.QuackGameBean;
import come.tool.Role.RoleData;
import org.come.bean.LoginResult;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;

public class DrawUtil
{
    public static QuackGameBean CJ(int type, Draw draw, AssetUpdate asset, LoginResult loginResult, RoleData data, String drawIds) {
        StringBuffer buffer = new StringBuffer();
        QuackGameBean bean = new QuackGameBean();
        bean.setType((type == 7) ? type : 4);
        if (type == 0) {
            MonitorUtil.addCJ(draw.getDid(), 1);
            int path = GameServer.random.nextInt(12);
            for (int i = 0; i < 12; ++i) {
                DrawBase base = null;
                if (path == i) {
                    base = draw.rDrawBase();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    int id = base.getId();
                    buffer.append(id);
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(1);
                    BigDecimal goodsid = new BigDecimal(id);
                    Goodstable goodstable = GameServer.getGood(goodsid);
                    if ((id >= 0 || !data.getPackRecord().isTX(-id + "")) && goodstable != null) {
                        XXGDBean xxgdBean = new XXGDBean();
                        xxgdBean.setId(id + "");
                        xxgdBean.setSum(base.getSum());
                        AddGoodAction.addGood(asset, goodstable, loginResult, data, xxgdBean, 15);
                    }
                }
                else {
                    base = draw.rDrawBase2();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(base.getId());
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(0);
                }
            }
        }
        else if (type == 1) {
            MonitorUtil.addCJ(draw.getDid(), 10);
            for (int j = 0; j < 12; ++j) {
                DrawBase base2 = draw.rDrawBase();
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                int id2 = base2.getId();
                buffer.append(id2);
                buffer.append("_");
                buffer.append(base2.getSum());
                buffer.append("_");
                buffer.append(1);
                BigDecimal goodsid2 = new BigDecimal(id2);
                Goodstable goodstable2 = GameServer.getGood(goodsid2);
                if ((id2 >= 0 || !data.getPackRecord().isTX(-id2 + "")) && goodstable2 != null) {
                    XXGDBean xxgdBean2 = new XXGDBean();
                    xxgdBean2.setId(id2 + "");
                    xxgdBean2.setSum(base2.getSum());
                    AddGoodAction.addGood(asset, goodstable2, loginResult, data, xxgdBean2, 15);
                    SuitMixdeal.ZP(goodstable2, draw.getName(), loginResult.getRolename(), drawIds);
                }
            }
        }
        else if (type == 7) {
            MonitorUtil.addCJ(draw.getDid(), 1);
            int path = GameServer.random.nextInt(12);
            for (int i = 0; i < 12; ++i) {
                DrawBase base = null;
                if (path == i) {
                    base = draw.rDrawBase();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    int id = base.getId();
                    buffer.append(id);
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(1);
                    BigDecimal goodsid = new BigDecimal(id);
                    Goodstable goodstable = GameServer.getGood(goodsid);
                    if (id >= 0 || !data.getPackRecord().isTX(-id + "")) {
                        if (goodstable == null) {
                            asset.setMsg("抽到个寂寞#35再接再厉#90");
                        }
                        else {
                            XXGDBean xxgdBean = new XXGDBean();
                            xxgdBean.setId(id + "");
                            xxgdBean.setSum(base.getSum());
                            AddGoodAction.addGood(asset, goodstable, loginResult, data, xxgdBean, 15);
                        }
                    }
                }
                else {
                    base = draw.rDrawBase2();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(base.getId());
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(0);
                }
            }
        }
        else {
            return bean;
        }
        String jf = draw.getName() + "积分=" + ((type == 0) ? draw.getIntegral() : (draw.getIntegral() * 10));
        asset.updata(jf);
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), jf, 2));
        if (draw.getIntegral() != 0) {
            asset.setMsg("你获得" + draw.getName() + "积分" + ((type == 0) ? draw.getIntegral() : (draw.getIntegral() * 10)));
        }
        bean.setPetmsg(buffer.toString());
        bean.setMoney(loginResult.getScoretype(draw.getName() + "积分"));
        if (type == 7) {
            bean.setMoney(draw.getMoney());
            bean.setPetmsg2("");
        }
        return bean;
    }
    /**
     * 抽奖
     */
    public static QuackGameBean XYCJ(int type, Draw draw, AssetUpdate asset, LoginResult loginResult, RoleData data, String s, String s1, List<String> filtrationGid) {
        StringBuffer buffer = new StringBuffer();
        QuackGameBean bean = new QuackGameBean();
        bean.setType(type == 7 ? type : 4);
        if (type == 0) {
            MonitorUtil.addCJ(draw.getDid(), 1);
            int path = GameServer.random.nextInt(12);
            for (int i = 0; i < 12; i++) {
                DrawBase base = null;
                if (path == i) {
                    base = draw.rDrawBase();

                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }

                    int id = base.getId();
                    buffer.append(id);
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(1);

                    BigDecimal goodsid = new BigDecimal(id);
                    Goodstable goodstable = GameServer.getGood(goodsid);
                    //特效物品判断是拥有特效
                    if (id < 0 && data.getPackRecord().isTX(-id + "")) {
                        continue;
                    }
                    if (goodstable == null) {
                        continue;
                    }
                    String s2 = addXYDJNum(goodstable, s, s1, loginResult);
                    if (!filtrationGids(base.getId() + "", filtrationGid)) {
                        goodstable = GameServer.getGood(new BigDecimal("206"));
                    }
                    XXGDBean xxgdBean = new XXGDBean();
                    xxgdBean.setId(id + "");
                    xxgdBean.setSum(base.getSum());
                    asset.updata(s2);
                    AddGoodAction.addGood(asset, goodstable, loginResult, data, xxgdBean, 15);
                    //TODO 抽奖喊话太烦人了去掉它
                    if (goodstable.getQuality() == 2) {
                        SuitMixdeal.ZP(goodstable, draw.getName(), loginResult.getRolename());
                    }
                } else {
                    base = draw.rDrawBase2();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(base.getId());
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(0);
                }
            }
        } else if (type == 1) {
            MonitorUtil.addCJ(draw.getDid(), 10);
            for (int i = 0; i < 12; i++) {
                DrawBase base = draw.rDrawBase();

                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                int id = base.getId();
                buffer.append(id);
                buffer.append("_");
                buffer.append(base.getSum());
                buffer.append("_");
                buffer.append(1);

                BigDecimal goodsid = new BigDecimal(id);
                Goodstable goodstable = GameServer.getGood(goodsid);

                //特效物品判断是拥有特效
                if (id < 0 && data.getPackRecord().isTX(-id + "")) {
                    continue;
                }
                if (goodstable == null) {
                    continue;
                }
                String s2 = addXYDJNum(goodstable, s, s1, loginResult);
                if (!filtrationGids(base.getId() + "", filtrationGid)) {
                    goodstable = GameServer.getGood(new BigDecimal("206"));
                }
                XXGDBean xxgdBean = new XXGDBean();
                xxgdBean.setId(id + "");
                xxgdBean.setSum(base.getSum());
                asset.updata(s2);
                AddGoodAction.addGood(asset, goodstable, loginResult, data, xxgdBean, 15);
                //TODO 抽奖喊话太烦人了去掉它2
                SuitMixdeal.ZP(goodstable, draw.getName(), loginResult.getRolename());
            }
        } else if (type == 7) {
            MonitorUtil.addCJ(draw.getDid(), 1);
            int path = GameServer.random.nextInt(12);
            for (int i = 0; i < 12; i++) {
                DrawBase base = null;
                if (path == i) {
                    base = draw.rDrawBase();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    int id = base.getId();
                    buffer.append(id);
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(1);

                    BigDecimal goodsid = new BigDecimal(id);
                    Goodstable goodstable = GameServer.getGood(goodsid);
                    //特效物品判断是拥有特效
                    if (id < 0 && data.getPackRecord().isTX(-id + "")) {
                        continue;
                    }
                    if (goodstable == null) {
                        asset.setMsg("抽到个寂寞#35再接再厉#90");
                        continue;
                    }
                    if (!filtrationGids(base.getId() + "", filtrationGid)) {
                        goodstable = GameServer.getGood(new BigDecimal("206"));
                    }
                    XXGDBean xxgdBean = new XXGDBean();
                    xxgdBean.setId(id + "");
                    xxgdBean.setSum(base.getSum());
                    AddGoodAction.addGood(asset, goodstable, loginResult, data, xxgdBean, 15);
                    //TODO 抽奖喊话太烦人了去掉它
//					SuitMixdeal.ZP(goodstable, draw.getName(), loginResult.getRolename());
                } else {
                    base = draw.rDrawBase2();
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(base.getId());
                    buffer.append("_");
                    buffer.append(base.getSum());
                    buffer.append("_");
                    buffer.append(0);
                }
            }
        } else {
            return bean;
        }
        String jf = draw.getName() + "积分=" + (type == 0 ? draw.getIntegral() : draw.getIntegral() * 10);
        asset.updata(jf);
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), jf, 2));
        if (draw.getIntegral() != 0)
            asset.setMsg("你获得" + draw.getName() + "积分" + (type == 0 ? draw.getIntegral() : draw.getIntegral() * 10));
        bean.setPetmsg(buffer.toString());

        bean.setMoney(loginResult.getScoretype(draw.getName() + "积分"));
        if (type == 7) {
            bean.setMoney(draw.getMoney());
            bean.setPetmsg2("");
        }
        return bean;
    }
    private static String addXYDJNum(Goodstable goodstable, String gid1, String gid2, LoginResult loginResult) {
        String gid = goodstable.getGoodsid().toString();
        if (gid.equals(gid1)) {
            String vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
            String[] v = vStr.split(",");
            int max1 = Integer.parseInt(v[0]);
            max1 += 1;
            vStr = max1 + "," + v[1];
            RedisControl.insertKey(GameServer.area + "XYDJ", 1 + "", vStr);
            AllServiceUtil.getGoodsrecordService().insertGoodsrecord(goodstable.getGoodsid(), loginResult.getRole_id(), 999999, goodstable.getRgid(), goodstable.getGoodsname(), goodstable.getValue(), 1);
            Goodsrecord record = new Goodsrecord();
            record.setRoleid(loginResult.getRole_id());
            record.setRolename(loginResult.getRolename());
            record.setRecordtime(new Date());
            record.setGoods(goodstable.getGoodsname());
            record.setGoodsnum(1);
            record.setRecordtype(99999);
            AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
            return "XYDJ";
        } else if (gid.equals(gid2)) {
            String vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
            String[] v = vStr.split(",");
            int max2 = Integer.parseInt(v[1]);
            max2 += 1;
            vStr = v[0] + "," + max2;
            if(max2>=10){
                System.out.printf("1111111111111");
            }
            RedisControl.insertKey(GameServer.area + "XYDJ", 1 + "", vStr);
            AllServiceUtil.getGoodsrecordService().insertGoodsrecord(goodstable.getGoodsid(), loginResult.getRole_id(), 999999, goodstable.getRgid(), goodstable.getGoodsname(), goodstable.getValue(), 1);
            Goodsrecord record = new Goodsrecord();
            record.setRoleid(loginResult.getRole_id());
            record.setRolename(loginResult.getRolename());
            record.setRecordtime(new Date());
            record.setGoods(goodstable.getGoodsname());
            record.setGoodsnum(1);
            record.setRecordtype(99999);
            AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(record);
            return "XYDJ";
        }
        return "";
    }

    private static Boolean filtrationGids(String bid, List<String> filtrationGid) {
        for (String s : filtrationGid) {
            if (s.equals(bid)) {
                return false;
            }
        }
        return true;

    }
}
