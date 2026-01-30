package org.come.action.give;

import come.tool.Mixdeal.AnalysisString;
import org.come.task.MapMonsterBean;
import org.come.bean.Bbuy;
import java.util.List;
import come.tool.Role.RoleData;
import come.tool.Battle.BattleMixDeal;
import org.come.model.Robots;
import org.come.model.Lshop;
import org.come.action.monster.ClickMonsterAction;
import org.come.task.MonsterUtil;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.GiveGoodsBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GiveAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        GiveGoodsBean giveBean = (GiveGoodsBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GiveGoodsBean.class);
        if (giveBean.getType() == 0) {
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(giveBean.getOtherName());
            LoginResult otherRole = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
            if (otherRole == null || otherRole.getRole_id().compareTo(loginResult.getRole_id()) == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("对方不在线"));
                return;
            }
            RoleData roleData = RolePool.getRoleData(otherRole.getRole_id());
            if ((int)roleData.getRoleSystem().getIsGood() == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("对方关闭物品接收功能"));
                return;
            }
            Goodstable goodstable = null;
            if (giveBean.getRgid() != null) {
                goodstable = this.getGiveGood(giveBean.getRgid(), loginResult.getRole_id(), giveBean.getSum(), true);
                if (goodstable == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你给与的物品处于异常"));
                    return;
                }
            }
            if (giveBean.getGold() != null) {
                if (giveBean.getGold().longValue() == 0L) {
                    giveBean.setGold(null);
                }
                else {
                    if (giveBean.getGold().longValue() < 0L) {
                        AllServiceUtil.getRecordService().insert(new Record(5, "给与异常:角色id:" + loginResult.getRole_id() + "_" + message));
                        return;
                    }
                    if (loginResult.getGold().longValue() < giveBean.getGold().longValue()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你给与的金钱不足"));
                        return;
                    }
                }
            }
            if (roleData.isGoodFull()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("给予失败，对方的背包已满"));
                return;
            }
            AssetUpdate giveData = new AssetUpdate(AssetUpdate.USEGOOD);
            giveData.setMsg("给与物品成功");
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            StringBuffer assetBuffer = new StringBuffer();
            assetBuffer.append("#G");
            assetBuffer.append(loginResult.getRolename());
            assetBuffer.append("#Y给你");
            if (giveBean.getGold() != null) {
                assetBuffer.append(giveBean.getGold());
                assetBuffer.append("大话币,");
                loginResult.setGold(loginResult.getGold().subtract(giveBean.getGold()));
                otherRole.setGold(otherRole.getGold().add(giveBean.getGold()));
                assetUpdate.updata("D=" + giveBean.getGold().longValue());
                giveData.updata("D=-" + giveBean.getGold().longValue());
                if (MonitorUtil.isUpMoney(2, giveBean.getGold().longValue())) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("给与大宗金额流动:");
                    buffer.append(loginResult.getRole_id());
                    buffer.append("送给");
                    buffer.append(otherRole.getRole_id());
                    buffer.append("金额");
                    buffer.append(giveBean.getGold().longValue());
                    AllServiceUtil.getRecordService().insert(new Record(2, buffer.toString()));
                }
            }
            if (goodstable != null) {
                assetBuffer.append(giveBean.getSum());
                assetBuffer.append("个");
                assetBuffer.append(goodstable.getGoodsname());
                if (EquipTool.canSuper(goodstable.getType())) {
                    goodstable.goodxh(giveBean.getSum());
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
                    giveData.updata("G" + goodstable.getRgid() + "=" + goodstable.getUsetime());
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(otherRole.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + giveBean.getSum()));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                    }
                    else {
                        Goodstable good = goodstable.clone();
                        good.setRgid(null);
                        good.setStatus(Integer.valueOf(0));
                        good.setRole_id(otherRole.getRole_id());
                        good.setUsetime(Integer.valueOf(giveBean.getSum()));
                        AllServiceUtil.getGoodsTableService().insertGoods(good);
                        assetUpdate.setGood(good);
                    }
                }
                else {
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, otherRole.getRole_id(), null, null);
                    assetUpdate.setGood(goodstable);
                    giveData.updata("G" + goodstable.getRgid() + "=0");
                }
                Goodstable JLGood = goodstable.clone();
                JLGood.setRole_id(loginResult.getRole_id());
                AllServiceUtil.getGoodsrecordService().insertGoodsRecordNew(goodstable, loginResult.getRole_id(), otherRole.getRole_id(), Integer.valueOf(giveBean.getSum()), Integer.valueOf(2), loginResult.getRolename(), otherRole.getRolename());
                AllServiceUtil.getGoodsrecordService().insert(JLGood, otherRole.getRole_id(), Integer.valueOf(giveBean.getSum()), Integer.valueOf(2));
            }
            assetUpdate.setMsg(assetBuffer.toString());
            SendMessage.sendMessageToSlef(ctx2, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(giveData)));
            return;
        }
        else {
            if (giveBean.getRgid() == null) {
                return;
            }
            Goodstable goodstable2 = this.getGiveGood(giveBean.getRgid(), loginResult.getRole_id(), giveBean.getSum(), false);
            if (goodstable2 == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你给与的物品处于异常"));
                return;
            }
            if (giveBean.getType() == 1) {
                Bbuy bbuy = GameServer.getBbuy(goodstable2.getGoodsid());
                if (bbuy == null || bbuy.getPrice1() == 0L) {
                    return;
                }
                int num = giveBean.getSum();
                if (giveBean.getSum() > 25) {
                    giveBean.setSum(25);
                }
                giveBean.setSum(bbuy.addNum(giveBean.getSum()));
                if (giveBean.getSum() <= 0 || (int)goodstable2.getUsetime() < giveBean.getSum()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("今天回收的材料已经够用了,明天再来吧"));
                    return;
                }
                goodstable2.setUsetime(Integer.valueOf((int)goodstable2.getUsetime() - giveBean.getSum()));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable2);
                AssetUpdate assetUpdate2 = new AssetUpdate();
                assetUpdate2.setType(AssetUpdate.USEGOOD);
                long money = bbuy.getPrice1() * (long)giveBean.getSum();
                assetUpdate2.updata("D=" + money);
                assetUpdate2.updata("G" + goodstable2.getRgid() + "=" + goodstable2.getUsetime());
                if (num > 25) {
                    assetUpdate2.setMsg("收购获得" + money + "银两|单次收购最大数25个");
                }
                else {
                    assetUpdate2.setMsg("收购获得" + money + "银两");
                }
                loginResult.setGold(loginResult.getGold().add(new BigDecimal(money)));
                MonitorUtil.getMoney().addD(money, 3);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
            }
            else if (giveBean.getType() == 2) {
                Bbuy bbuy = GameServer.getBbuy(goodstable2.getGoodsid());
                if (bbuy == null || bbuy.getPrice2() == 0L) {
                    return;
                }
                int num = MonitorUtil.addBY(loginResult.getRole_id(), giveBean.getSum(), bbuy.getPrice2());
                if (num <= 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("超过单日师门贡献最大获取量将收购数量修改为0个"));
                    return;
                }
                goodstable2.goodxh(num);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable2);
                AssetUpdate assetUpdate2 = new AssetUpdate();
                assetUpdate2.setType(AssetUpdate.USEGOOD);
                long money = bbuy.getPrice2() * (long)num;
                assetUpdate2.updata("S=" + money);
                assetUpdate2.updata("G" + goodstable2.getRgid() + "=" + goodstable2.getUsetime());
                if (num != giveBean.getSum()) {
                    assetUpdate2.setMsg("超过单日师门贡献最大获取量将收购数量修改为" + num + "个|收购获得" + money + "师贡");
                }
                else {
                    assetUpdate2.setMsg("收购获得" + money + "师贡");
                }
                loginResult.setSavegold(loginResult.getSavegold().add(new BigDecimal(money)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
            }
            else if (giveBean.getType() == 3) {
                MapMonsterBean bean = MonsterUtil.getMonster(giveBean.getOtherID().intValue());
                if (bean == null || bean.getRobotType() != 3) {
                    SendMessage.sendMessageToSlef(ctx, ClickMonsterAction.CHECKTS2);
                    return;
                }
                Lshop lshop = (Lshop)bean.getShops().get(goodstable2.getGoodsid().toString());
                if (lshop == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不属于回收范围"));
                    return;
                }
                Robots robots = (Robots)GameServer.getAllRobot().get(bean.getRobotid() + "");
                if (robots == null) {
                    return;
                }
                if (robots.getLvls() != null) {
                    String value = BattleMixDeal.isLvl((int)loginResult.getGrade(), robots.getLvls());
                    if (value != null) {
                        SendMessage.sendMessageToSlef(ctx, value);
                        return;
                    }
                }
                String v = ClickMonsterAction.isTime20s(loginResult.getRole_id());
                if (v != null) {
                    SendMessage.sendMessageToSlef(ctx, v);
                    return;
                }
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                String msg = null;
                if (giveBean.getSum() > lshop.getlNum()) {
                    msg = "单次最大购买数量" + lshop.getlNum();
                    giveBean.setSum(lshop.getlNum());
                }
                giveBean.setSum(lshop.addNum(giveBean.getSum()));
                if (giveBean.getSum() == 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("回收的材料已经够用了"));
                    return;
                }
                goodstable2.setUsetime(Integer.valueOf((int)goodstable2.getUsetime() - giveBean.getSum()));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable2);
                assetUpdate.updata("G" + goodstable2.getRgid() + "=" + goodstable2.getUsetime());
                if (lshop.getType() == 0) {
                    long money2 = lshop.getMoney().longValue() * (long)giveBean.getSum();
                    loginResult.setGold(loginResult.getGold().add(new BigDecimal(money2)));
                    assetUpdate.updata("D=" + money2);
                    MonitorUtil.getMoney().addD(money2, 3);
                    if (msg != null) {
                        msg = msg + "|收购获得" + money2 + "银两";
                    }
                    else {
                        msg = "收购获得" + money2 + "银两";
                    }
                }
                else if (lshop.getType() == 1) {
                    long money2 = lshop.getMoney().longValue() * (long)giveBean.getSum();
                    loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money2)));
                    MonitorUtil.getMoney().addX(money2, 2);
                    assetUpdate.updata("X=" + money2);
                    if (msg != null) {
                        msg = msg + "|收购获得" + money2 + "仙玉";
                    }
                    else {
                        msg = "收购获得" + money2 + "仙玉";
                    }
                }
                assetUpdate.setMsg(msg);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            AllServiceUtil.getGoodsrecordService().insertGoodsRecordNew(goodstable2, loginResult.getRole_id(), giveBean.getOtherID(), Integer.valueOf(giveBean.getSum()), Integer.valueOf(2), loginResult.getRolename(), giveBean.getOtherName());
            AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(giveBean.getSum()), Integer.valueOf(2));
            return;
        }
    }
    
    public Goodstable getGiveGood(BigDecimal rgid, BigDecimal roleid, int sum, boolean isJY) {
        if (sum < 0) {
            AllServiceUtil.getRecordService().insert(new Record(5, "给与异常:id:" + rgid + "_角色:" + roleid + "_数量:" + sum));
            return null;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(rgid);
        if (goodstable == null) {
            AllServiceUtil.getRecordService().insert(new Record(5, "给与异常:id:" + rgid + "_角色:" + roleid + "_数量:" + sum));
            return null;
        }
        if (goodstable.getRole_id().compareTo(roleid) != 0 || (int)goodstable.getUsetime() < sum) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("给与异常:id:" + rgid + "_角色:" + roleid + "_数量:" + sum);
            buffer.append("_物品属性:");
            buffer.append(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
            AllServiceUtil.getRecordService().insert(new Record(5, buffer.toString()));
            return null;
        }
        if (isJY && AnalysisString.jiaoyi((long)goodstable.getQuality())) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("给与异常物品绑定:id:" + rgid + "_角色:" + roleid + "_数量:" + sum);
            buffer.append("_物品属性:");
            buffer.append(GsonUtil.getGsonUtil().getgson().toJson(goodstable));
            AllServiceUtil.getRecordService().insert(new Record(5, buffer.toString()));
            return null;
        }
        return goodstable;
    }
}
