package org.come.action.lottery;

import come.tool.Good.AddGoodAction;
import org.apache.commons.lang.StringUtils;
import org.come.action.suit.SuitMixdeal;
import org.come.bean.XXGDBean;
import org.come.entity.Goodsrecord;
import org.come.model.Configure;
import org.come.protocol.ParamTool;
import org.come.redis.RedisControl;
import org.come.until.AllServiceUtil;
import org.come.bean.QuackGameBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.until.GsonUtil;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.action.suit.SuitComposeAction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.come.action.monster.ClickMonsterAction;
import org.come.model.Dorp;
import org.come.model.EventModel;
import come.tool.Role.RoleData;
import come.tool.Good.DropUtil;
import org.come.handler.SendMessage;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LotteryAction implements IAction
{
    public static String CHECKTS1;
    public static String CHECKTS2;
    public static String CHECKTS4;
    public static String CHECKTS5;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message.startsWith("G")) {
            this.GGL(ctx, (message.length() <= 1) ? null : message.substring(1));
        }
        else if (message.startsWith("C")) {
            this.CJ(ctx, (message.length() <= 1) ? null : message.substring(1));
        }
        else if (message.startsWith("E")) {
            this.HD(ctx, (message.length() <= 1) ? null : message.substring(1));
        }  else if (message.startsWith("F")) {
            Integer type = Integer.parseInt(message.substring(1));
            List<Goodsrecord> goodsrecords = AllServiceUtil.getGoodsrecordService().selectGoodsRecordByType(type);
            SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().XYDJLSAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodsrecords)));
        }
        else {
            this.GGL(ctx, message);
        }
    }
    
    public void HD(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        EventModel eventModel = null;
        if (message.startsWith("G")) {
            int id = Integer.parseInt(message.substring(1));
            eventModel = GameServer.getEvent(id);
            if (eventModel == null) {
                return;
            }
            int type = data.getPackRecord().isOther(id + "");
            if (type == 0) {
                SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS4);
            }
            else if (type == 2) {
                SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS5);
            }
            else if (type == 1) {
                String Did = eventModel.getAward(loginResult.getRole_id());
                if (Did == null) {
                    return;
                }
                Dorp dorp = GameServer.getDorp(Did);
                if (dorp == null) {
                    return;
                }
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), "首杀礼包", 22, 1.0, null);
            }
            return;
        }
        else {
            int id = Integer.parseInt(message);
            eventModel = GameServer.getEvent(id);
            if (eventModel == null || eventModel.getMsg() == null) {
                return;
            }
            SendMessage.sendMessageToSlef(ctx, eventModel.getMsg());
            return;
        }
    }
    
    public void CJ(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        if (message.startsWith("C")) {
            int cid = Integer.parseInt(message.substring(1));
            Draw draw = GameServer.getDraw(cid);
            if (draw == null) {
                SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS1);
                return;
            }
            if (cid == 6) {
                SendMessage.sendMessageToSlef(ctx, draw.getText());
                return;
            }
            if (cid == 10) {
                ConcurrentHashMap<Integer, Configure> czbl = GameServer.getAllConfigure();
                Configure configure = czbl.get(10);
                String zqsld = configure.getZqsld();
                String[] v2 = zqsld.split("\\|");
                String[] g1 = v2[0].split("=");
                String[] g2 = v2[1].split("=");
                int gNumb1 = Integer.parseInt(g1[1]);
                int gNumb2 = Integer.parseInt(g2[1]);
                String vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
                if (StringUtils.isBlank(vStr)) {
                    RedisControl.insertKey(GameServer.area + "XYDJ", 1 + "", "0,0");
                    vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
                }
                String[] v = vStr.split(",");
                int max1 = Integer.parseInt(v[0]);
                int max2 = Integer.parseInt(v[1]);
                StringBuffer bufferTwo = new StringBuffer();

                if (bufferTwo.length() != 0) {
                    bufferTwo.append("|");
                }
                if(max1>gNumb1){
                    max1=gNumb1;
                }

                if(max2>gNumb2){
                    max2=gNumb2;
                }
                bufferTwo.append("Y");
                bufferTwo.append(draw.getDid());
                bufferTwo.append("-");
                bufferTwo.append(draw.getName());
                bufferTwo.append("|");
                bufferTwo.append(draw.getGoods());
                QuackGameBean bean = new QuackGameBean();
                bean.setType(3);
                if ("天梯奖池".equals(draw.getName()))
                    bean.setType(6);
                if ("幸运大奖".equals(draw.getName()))
                    bean.setType(10);
                bean.setMoney(draw.getMoney());
                bean.setPetmsg(bufferTwo.toString());
                bean.setPetmsg2(draw.getMoneyType() + "|" + draw.getGoodid());
                bean.setEndTime(draw.getEndTime());
                bean.setG1(max1 + "/" + gNumb1);
                bean.setG2(max2 + "/" + gNumb2);
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().getfivemsgAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
                return;
            }
            SendMessage.sendMessageToSlef(ctx, draw.getText());
            return;
        }
        else {
            String msg = ClickMonsterAction.isTime4s(loginResult.getRole_id());
            if (msg != null) {
                SendMessage.sendMessageToSlef(ctx, msg);
                return;
            }
            String[] vs = message.split("\\|");
            int cid2 = Integer.parseInt(vs[0]);
            int cType = Integer.parseInt(vs[1]);
            int cBc = Integer.parseInt(vs[2]);
            Draw draw2 = GameServer.getDraw(cid2);
            Draw draw = GameServer.getDraw(cid2);
            if(StringUtils.isNotBlank(draw.getEndTime())){
                // 定义要检查的日期字符串
                String dateStr = draw.getEndTime();
                // 定义日期格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                LocalDateTime targetDate = LocalDateTime.parse(dateStr, formatter);
                LocalDateTime now = LocalDateTime.now();

                if (now.isAfter(targetDate)) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("活动未开放#46"));
                    return;
                }

            }
            if (draw2 == null) {
                SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS1);
                return;
            }
            BigDecimal dj = draw2.getMoney();
            BigDecimal goodsid = draw2.getGoodid();
            List<Goodstable> goods = null;
            if (vs.length > 3) {
                List<BigDecimal> ids = new ArrayList<>();
                for (int i = 3; i < vs.length; ++i) {
                    ids.add(new BigDecimal(vs[i]));
                }
                goods = SuitComposeAction.getGoods(ids, loginResult.getRole_id(), 0);
                if (goods == null) {
                    return;
                }
                for (int i = 0; i < goods.size(); ++i) {
                    Goodstable good = (Goodstable)goods.get(i);
                    if (good.getGoodsid().compareTo(goodsid) != 0) {
                        return;
                    }
                    if ((int)good.getUsetime() <= 0) {
                        SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS2);
                        return;
                    }
                    good.goodxh(1);
                }
                if (cBc == 0 && ((cType == 0) ? 1 : 10) != goods.size()) {
                    SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS2);
                    return;
                }
            }
            else if (cBc == 0) {
                SendMessage.sendMessageToSlef(ctx, LotteryAction.CHECKTS2);
                return;
            }
            BigDecimal money = null;
            if (cBc == 1) {
                int size = ((cType == 0) ? 1 : 10) - ((goods == null) ? 0 : goods.size());
                if (size > 0) {
                    money = new BigDecimal(dj.longValue() * (long)size);
                }
            }
            if (cBc == 1 || cBc == 4) {
                int size = ((cType == 0) ? 1 : 10) - ((goods == null) ? 0 : goods.size());
                if (size > 0) {
                    money = new BigDecimal(dj.longValue() * (long)size);
                }
            }
            else if (cBc == 3) {
                int size = ((cType == 0) ? 1 : 10) - ((goods == null) ? 0 : goods.size());
                if (size > 0) {
                    money = new BigDecimal(dj.longValue() * (long)size);
                }
            }
            if(money == null&&(goods==null||goods.size()<1)){
                ParamTool.ACTION_MAP.get("accountstop").action(GameServer.getInlineUserNameMap().get(loginResult.getUserName()), loginResult.getUserName());
                return;
            }
            if (money != null) {
                if (draw2.getMoneyType() == 0 && loginResult.getCodecard().compareTo(money) < 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("仙玉不够"));
                    return;
                }
                if (draw2.getMoneyType() == 1 && loginResult.getGold().compareTo(money) < 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("大话币不够"));
                    return;
                }
                if (draw2.getMoneyType() == 2 && new BigDecimal(loginResult.getScoretype("天梯积分").toString()).compareTo(money) < 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("天梯积分不足"));
                    return;
                }
                if ((draw.getMoneyType() == 3 && new BigDecimal(loginResult.getMoney()).compareTo(money) < 0)) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("积分不足"));
                    return;
                }
                if (draw2.getMoneyType() != 0 && draw2.getMoneyType() != 1 && draw2.getMoneyType() != 2 && draw.getMoneyType() != 3) {
                    return;
                }
            }
            if (goods != null) {
                for (int i = goods.size() - 1; i >= 0; --i) {
                    Goodstable good = (Goodstable)goods.get(i);
                    int j = 0;
                    while (j < i) {
                        if (((Goodstable)goods.get(j)).getRgid().compareTo(good.getRgid()) == 0) {
                            goods.remove(i);
                            break;
                        }
                        else {
                            ++j;
                        }
                    }
                }
            }
            AssetUpdate asset = new AssetUpdate(AssetUpdate.USEGOOD);

            if (money != null) {
                if (draw2.getMoneyType() == 0) {
                    asset.updata("X=" + -money.longValue());
                    loginResult.setCodecard(loginResult.getCodecard().subtract(money));
                    MonitorUtil.getMoney().useX(money.longValue());
                }
                else if (draw2.getMoneyType() == 1) {
                    asset.updata("D=" + -money.longValue());
                    loginResult.setGold(loginResult.getGold().subtract(money));
                    MonitorUtil.getMoney().useD(money.longValue());
                }
                else if (draw2.getMoneyType() == 2) {//draw天梯奖池
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "天梯积分=" + money, 3));
                    asset.updata("TTJF=" + loginResult.getScore());
                } else if (draw.getMoneyType() == 3) {//draw幸运大奖
                    loginResult.setMoney(loginResult.getMoney() - money.intValue());
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(),"积分消耗="+ money.intValue(),2));
                    MonitorUtil.getMoney().useC(money.intValue());
                    asset.updata("积分消耗=" + money.intValue());
                    asset.updata("C=" + -money.intValue());
                }
            }
            if (goods != null) {
                for (int i = 0; i < goods.size(); i++) {
                  Goodstable good = goods.get(i);
                  BigDecimal id = good.getRgid();
                  int j = i + 1;
                  while (true) {
                    if (j < goods.size()) {
                      if (((Goodstable)goods.get(j)).getRgid().compareTo(id) == 0)
                        break; 
                      j++;
                      continue;
                    } 
                    asset.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    break;
                  } 
                } 
                SuitComposeAction.saveGoods(goods, false);
             }
            QuackGameBean bean = null;
            if (draw.getDid() != 10) {
                bean = DrawUtil.CJ(cType, draw2, asset, loginResult, data, draw2.getDrawIds());
            }else{
                String vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
                if (StringUtils.isBlank(vStr)) {
                    RedisControl.insertKey(GameServer.area + "XYDJ", 1 + "", "0,0");
                    vStr = RedisControl.getVStr(GameServer.area + "XYDJ", 1 + "", String.class);
                }
                String[] v = vStr.split(",");
                int max1 = Integer.parseInt(v[0]);
                int max2 = Integer.parseInt(v[1]);

                ConcurrentHashMap<Integer, Configure> czbl = GameServer.getAllConfigure();
                Configure configure = czbl.get(10);
                String zqsld = configure.getZqsld();
                String[] v2 = zqsld.split("\\|");
                String[] g1 = v2[0].split("=");
                String[] g2 = v2[1].split("=");
                int gNumb1 = Integer.parseInt(g1[1]);
                int gNumb2 = Integer.parseInt(g2[1]);
                List<String> filtrationGids = new ArrayList<>();
                if (max1 >= gNumb1) {
//                draw = draw.clone();
//                draw.getGoods().replace(g1[0]+"|","");
                    filtrationGids.add(g1[0]);
                }
                if (max2 >= gNumb2) {
//                draw = draw.clone();
//                draw.getGoods().replace(g2[0]+"|","");
                    filtrationGids.add(g2[0]);
                }
                bean = DrawUtil.XYCJ(cType, draw, asset, loginResult, data, g1[0], g2[0], filtrationGids);
            }
            System.out.println(GsonUtil.getGsonUtil().getgson().toJson(asset));
            if (draw.getDid() == 10) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(asset)));
                for (Goodstable good : asset.getGoods()) {
                    String rolename = null;
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("运气不错，抽到了#R"+ good.getGoodsname() + "#Y#46"));
                }
            } else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(asset)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getfivemsgAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
            }
            if (draw2.getMoneyType() == 0) {
                LaborScene.addRankValue(1, (cType == 0) ? 1 : 10, loginResult);
            }
            return;
        }
    }
    
    public void GGL(ChannelHandlerContext ctx, String message) {
        if (message == null || message.equals("")) {
            SendMessage.sendMessageToSlef(ctx, PIXIU.getPixiu().getSendAwards());
            return;
        }
        BigDecimal rgid = new BigDecimal(message);
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(rgid);
        if (goodstable == null || goodstable.getType() != 889L) {
            return;
        }
        goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() - 1));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        if ((int)goodstable.getUsetime() <= 0) {
            AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(9));
        }
        PIXIU.getPixiu().lottery(ctx);
    }
    
    static {
        LotteryAction.CHECKTS1 = Agreement.getAgreement().PromptAgreement("该奖池未开放");
        LotteryAction.CHECKTS2 = Agreement.getAgreement().PromptAgreement("抽奖卷不够");
        LotteryAction.CHECKTS4 = Agreement.getAgreement().PromptAgreement("你未有资格领取");
        LotteryAction.CHECKTS5 = Agreement.getAgreement().PromptAgreement("你已经领取");
    }
}
