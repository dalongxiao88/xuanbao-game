package org.come.action.buy;

import java.util.*;

import come.tool.Role.PartJade;
import org.come.entity.*;
import org.come.task.MapMonsterBean;
import come.tool.Scene.DNTG.DNTGRole;
import come.tool.Scene.Scene;
import org.come.bean.PathPoint;
import come.tool.Scene.ZZS.ZZSRole;
import come.tool.Scene.ZZS.ZZSScene;
import come.tool.Role.RoleData;
import org.come.action.suit.SuitMixdeal;
import org.come.tool.EquipTool;
import org.come.until.AchievemUtil;
import org.come.until.SplitLingbaoValue;
import java.text.ParseException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import org.come.model.Lshop;
import come.tool.Battle.BattleMixDeal;
import org.come.model.Robots;
import org.come.action.monster.ClickMonsterAction;
import org.come.task.MonsterUtil;
import org.come.until.AllServiceUtil;
import org.come.action.reward.DrawnitemsAction;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.LTS.LTSUtil;
import come.tool.Scene.SceneUtil;
import org.come.model.Shop;
import org.come.action.monitor.MonitorUtil;
import org.come.model.Eshop;
import java.math.BigDecimal;

import come.tool.Stall.AssetUpdate;
import org.come.until.GsonUtil;
import org.come.bean.BuyShopBean;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import cn.hutool.json.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class BuyShopAction implements IAction
{
    static final String ST = "师徒积分";
    static final String BY = "师贡";
    static final String TT = "天庭积分";
    static final String BZ = "帮战积分";
    static final String DYT = "大雁塔积分";
    static final String DG = "地宫积分";
    static final String XF = "寻芳积分";
    static final String MK = "木魁积分";
    static final String SL = "水陆积分";
    static final String ZZS = "种族赛积分";
    static final String BDJZ = "比斗奖章";
    static final String XMZ = "星芒";
    static final String DNTG = "大闹天宫积分";
    static final String QMJJ = "竞技积分";
    static final String YZF = "勇者积分";
    static final String WSS = "孤竹城积分";
    public static final String JGLL = "解卦灵力";
    public static String[] CJS;
    static final String LTS = "擂台赛积分";
    static final String TTJF = "天梯积分";
    static final String DXCMJF = "定心除魔积分";
    static final String CJJF = "吃鸡积分";// 8901
    static final String YHJF = "烟花积分";// 99129
    static final String gjjf="功绩积分";//7120
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.size() < 4 || !jsonObject.containsKey("price")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
            return;
        }
        int buyType = 0;
        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        BuyShopBean buyShopBean = GsonUtil.getGsonUtil().getgson().fromJson(message, BuyShopBean.class);
        if (buyShopBean.getSum() <= 0) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        Shop shop = null;
        BigDecimal goodid = null;
        boolean isSX = false;
        GoodsbuyrecordEntity goodsBuy = new GoodsbuyrecordEntity();
        goodsBuy.setRoleid(loginResult.getRole_id());
        goodsBuy.setUserid(loginResult.getUser_id());
        goodsBuy.setSid(new BigDecimal(0));
        String JJF=null;
        if (buyShopBean.getAte() == 0) {
            Eshop eshop = GameServer.getAllEshopGoods().get(buyShopBean.getCd());
            if (eshop == null) {
                return;
            }
            String value = BuyUtil.isLimit(loginResult, eshop, buyShopBean);
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, value);
                return;
            }
            goodid = eshop.getEshopiid();
            if (goodid.longValue() < 0L && roleData.getPackRecord().isTX(-goodid.longValue() + "")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已拥有该特效"));
                return;
            }
            long jg = eshop.getEshopprice() * (long)buyShopBean.getSum();
            if (buyShopBean.getPrice() != jg) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
                return;
            }
            goodsBuy.setGid(goodid);
            goodsBuy.setPrice(new BigDecimal(eshop.getEshopprice()));
            goodsBuy.setGoodnumber(new BigDecimal(buyShopBean.getSum()));
            goodsBuy.setNumbermoney(new BigDecimal(jg));
            if (jg <= 0L) {
                return;
            }
            if (eshop.getEshoptype().equals("5")) {// 充值积分购买
                if ((long)(int)loginResult.getMoney() < jg) {
                    return;
                }
                loginResult.setMoney((int) ((long) (int) loginResult.getMoney() - jg));
                MonitorUtil.getMoney().useC(jg);
                assetUpdate.setData("C=" + -jg);
                buyType = 18;
                goodsBuy.setBuytype(new BigDecimal(3));
                AchievemUtil.detectionAchievem(loginResult, "积分购买一次道具");
                if (jg>=88) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花88积分");
                }
                if (jg>=288) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花288积分");
                }
                if (jg>=588) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花588积分");
                }
            }
            else if (eshop.getEshoptype().equals("9")) {//转区币
                if (loginResult.getTransfergold().longValue() < jg) {
                    return;
                }
                loginResult.setTransfergold(new BigDecimal(loginResult.getTransfergold().longValue() - jg));
                MonitorUtil.getMoney().useZ(jg);
                assetUpdate.setData("Z=" + -jg);
                buyType = 21;
                goodsBuy.setBuytype(new BigDecimal(9));
            }
            else {
                if (loginResult.getCodecard().longValue() < jg) {
                    return;
                }
                loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - jg));
                MonitorUtil.getMoney().useX(jg);
                assetUpdate.setData("X=" + -jg);
                buyType = 17;
                goodsBuy.setBuytype(new BigDecimal(2));
                AchievemUtil.detectionAchievem(loginResult, "仙玉购买一次道具");
                if (jg>=1888) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花1888仙玉");
                }
                if (jg>=16888) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花16888仙玉");
                }
                if (jg>=58888) {
                    AchievemUtil.detectionAchievem(loginResult, "一次花58888仙玉");
                }
            }
            eshop.addPrice(buyShopBean.getSum(), jg);
        }
        else if (buyShopBean.getAte() == 1) {
            shop = (Shop)GameServer.getAllShopGoods().get(buyShopBean.getCd());
            if (shop == null) {
                return;
            }
            String value = BuyUtil.isLimit(loginResult, shop, buyShopBean);
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, value);
                return;
            }
            goodid = shop.getShopiid();
            if (goodid.longValue() < 0L && roleData.getPackRecord().isTX(-goodid.longValue() + "")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已拥有该特效"));
                return;
            }
            long jg = shop.getPrice(buyShopBean.getSum());
            if (jg <= 0L) {
                return;
            }
            if (shop.getPriceNum() == 0 && buyShopBean.getPrice() != jg) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
                return;
            }
            goodsBuy.setGid(goodid);
            goodsBuy.setPrice(new BigDecimal(shop.getShopprice()));
            goodsBuy.setGoodnumber(new BigDecimal(buyShopBean.getSum()));
            goodsBuy.setNumbermoney(new BigDecimal(jg));
            String jf = null;
            if (BuyShopAction.CJS != null && shop.getShoptype() >= 500 && shop.getShoptype() < 500 + BuyShopAction.CJS.length) {
                jf = BuyShopAction.CJS[shop.getShoptype() - 500];
            }
            else if (shop.getShoptype() == 45) {
                jf = "师徒积分";
            }
            else if (shop.getShoptype() == 10) {
                jf = "天庭积分";
            }
            else if (shop.getShoptype() == 61) {
                jf = "帮战积分";
            }
            else if (shop.getShoptype() == 120) {
                jf = "大雁塔积分";
            }
            else if (shop.getShoptype() == 121) {
                jf = "地宫积分";
            }
            else if (shop.getShoptype() == 123) {
                jf = "寻芳积分";
            }
            else if (shop.getShoptype() == 124) {
                jf = "木魁积分";
            }
            else if (shop.getShoptype() == 126) {
                jf = "水陆积分";
            }
            else if (shop.getShoptype() == 1106) {
                jf = "种族赛积分";
            }
            else if (shop.getShoptype() == 515) {
                jf = "擂台赛积分";
            }
            else if (shop.getShoptype() == 600) {
                jf = "比斗奖章";
            }
            else if (shop.getShoptype() == 601) {
                jf = "星芒";
            }
            else if (shop.getShoptype() == 602) {
                jf = "勇者积分";
            }
            else if (shop.getShoptype() == 605) {
                jf = "大闹天宫积分";
            }
            else if (shop.getShoptype() == 887) {
                jf = "孤竹城积分";
            }
            else if (shop.getShoptype() == 2027) {
                jf = "竞技积分";
            }
            else if (shop.getShoptype() == 2029) {
                jf = "天梯积分";
            }
            else if (shop.getShoptype() == 8900) {
                jf = "副本积分";
            }
            else if (shop.getShoptype() == 8901) {
                jf = "吃鸡积分";
            }
            else if (shop.getShoptype() == 7120) {
                jf = gjjf;//功绩积分
            }
            else if (shop.getShoptype() == 99129) {
                jf = YHJF;//烟花积分
            }
            else if (shop.getShoptype() == 89) {
                if (loginResult.getSavegold().longValue() < jg) {
                    return;
                }
                loginResult.setSavegold(new BigDecimal(loginResult.getSavegold().longValue() - jg));
                assetUpdate.setData("S=" + -jg);
                buyType = 19;
                goodsBuy.setBuytype(new BigDecimal(5));
            }
            else {
                if (loginResult.getGold().longValue() < jg) {
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - jg));
                assetUpdate.setData("D=" + -jg);
                MonitorUtil.getMoney().useD(jg);
                buyType = 20;
                goodsBuy.setBuytype(new BigDecimal(1));
            }
            JJF=jf;
            if (jf != null) {
                if (jf.equals("种族赛积分")) {
                    ZZSScene zzsScene = SceneUtil.getZZS(loginResult);
                    if (zzsScene == null) {
                        return;
                    }
                    if (zzsScene.isEnd()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("种族赛结束后才能兑换"));
                        return;
                    }
                    ZZSRole role = zzsScene.getRole(loginResult);
                    if (role == null) {
                        return;
                    }
                    if ((long)role.getJf() < jg) {
                        return;
                    }
                    role.setJf((int)((long)role.getJf() - jg));
                }
                else if (jf.equals("擂台赛积分")) {
                    PathPoint point = LTSUtil.getLtsUtil().getJF(loginResult.getRole_id());
                    if (point == null) {
                        return;
                    }
                    if ((long)point.getY() < jg) {
                        return;
                    }
                    point.setY((int)((long)point.getY() - jg));
                }
                else if (jf.equals("大闹天宫积分")) {
                    Scene scene = SceneUtil.getScene(1011);
                    if (scene == null) {
                        return;
                    }
                    DNTGScene dntgScene = (DNTGScene)scene;
                    if (dntgScene.isEnd()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("大闹天宫结束后才能兑换"));
                        return;
                    }
                    DNTGRole role2 = dntgScene.getRole(loginResult.getRole_id());
                    if (role2 == null) {
                        return;
                    }
                    if ((long)role2.getUseDNJF() < jg) {
                        return;
                    }
                    role2.setUseDNJF((int)((long)role2.getUseDNJF() - jg));
                }
                else {
                    if (loginResult.getScoretype(jf).longValue() < jg) {
                        return;
                    }
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), jf + "=" + jg, 3));
                    assetUpdate.setData(jf + "=" + -jg);
                }
                buyType = 19;
                goodsBuy.setBuytype(new BigDecimal(5));
            }
            isSX = shop.addPrice(buyShopBean.getSum(), jg);
        }
        else if (buyShopBean.getAte() == 2) {
            shop = (Shop)GameServer.getAllShopGoods().get(buyShopBean.getCd());
            if (shop == null) {
                return;
            }
            String value = BuyUtil.isLimit(loginResult, shop, buyShopBean);
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, value);
                return;
            }
            Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(buyShopBean.getGoodsId());
            if (goods == null || goods.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("兑换物品不存在!"));
                return;
            }
            int sum = buyShopBean.getSum();
            if (sum > (int)goods.getUsetime()) {
                sum = (int)goods.getUsetime();
            }
            if (sum <= 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("兑换物品不存在!"));
                return;
            }
            long jg = shop.getPrice(sum);
            if (buyShopBean.getPrice() != jg) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
                return;
            }
            if (jg <= 0L) {
                return;
            }
            goods.setUsetime((int) goods.getUsetime() - sum);
            assetUpdate.updata("G" + goods.getRgid() + "=" + goods.getUsetime());
            AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
            String jf2 = null;
            if (shop.getShoptype() == 900) {
                jf2 = "解卦灵力";
            }
            String jf = null;
            if (shop.getShoptype() == 900) {
                jf = JGLL;
            }
            isSX = shop.addPrice(buyShopBean.getSum(), jg);
            if (jf2 != null) {
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), jf2 + "=" + jg, 2));
                assetUpdate.updata(jf2 + "=" + jg);
            }
            assetUpdate.setMsg("兑换成功");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            if (isSX) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().ShopPriceAgreement("1|1|" + shop.getShopid() + "|" + shop.getPrice()));
            }
            return;
        }
        else if (buyShopBean.getAte() == 3) {
            if (buyShopBean.getnId() == null) {
                return;
            }
            MapMonsterBean bean = MonsterUtil.getMonster((int)buyShopBean.getnId());
            if (bean == null || bean.getRobotType() != 2) {
                SendMessage.sendMessageToSlef(ctx, ClickMonsterAction.CHECKTS2);
                return;
            }
            Robots robots = GameServer.getAllRobot().get(bean.getRobotid() + "");
            if (robots == null) {
                return;
            }
            if (robots.getLvls() != null) {
                String value2 = BattleMixDeal.isLvl((int)loginResult.getGrade(), robots.getLvls());
                if (value2 != null) {
                    SendMessage.sendMessageToSlef(ctx, value2);
                    return;
                }
            }
            Lshop lshop = (Lshop)bean.getShops().get(buyShopBean.getCd());
            if (lshop == null) {
                return;
            }
            String v = ClickMonsterAction.isTime20s(loginResult.getRole_id());
            if (v != null) {
                SendMessage.sendMessageToSlef(ctx, v);
                return;
            }
            goodid = lshop.getGid();
            if (goodid.longValue() < 0L && roleData.getPackRecord().isTX(-goodid.longValue() + "")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已拥有该特效"));
                return;
            }
            if (buyShopBean.getSum() > lshop.getlNum()) {
                assetUpdate.setMsg("单次最大购买数量" + lshop.getlNum());
                buyShopBean.setSum(lshop.getlNum());
            }
            buyShopBean.setSum(lshop.addNum(buyShopBean.getSum()));
            if (buyShopBean.getSum() == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该物品已售完"));
                return;
            }
            long jg = lshop.getMoney().longValue() * (long)buyShopBean.getSum();
            if (buyShopBean.getPrice() != jg) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
                return;
            }
            goodsBuy.setGid(goodid);
            goodsBuy.setPrice(lshop.getMoney());
            goodsBuy.setGoodnumber(new BigDecimal(buyShopBean.getSum()));
            goodsBuy.setNumbermoney(new BigDecimal(jg));
            if (lshop.getType() == 0) {
                if (loginResult.getGold().longValue() < jg) {
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - jg));
                assetUpdate.setData("D=" + -jg);
                MonitorUtil.getMoney().useD(jg);
                buyType = 20;
                goodsBuy.setBuytype(new BigDecimal(1));
            }
            else if (lshop.getType() == 1) {
                if (loginResult.getCodecard().longValue() < jg) {
                    return;
                }
                loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - jg));
                MonitorUtil.getMoney().useX(jg);
                assetUpdate.setData("X=" + -jg);
                buyType = 17;
                goodsBuy.setBuytype(new BigDecimal(2));
            }
            else {
                return;
            }
            lshop.addPrice(buyShopBean.getSum(), jg);
        }
        else if (buyShopBean.getAte() == 4) {
            if (buyShopBean.getnId() == null) {
                return;
            }
            ChongjipackBean chongjipackBean = GameServer.getChongjipackBean(buyShopBean.getnId());
            if (chongjipackBean == null) {
                return;
            }
            String v2 = ClickMonsterAction.isTime4s(loginResult.getRole_id());
            if (v2 != null) {
                SendMessage.sendMessageToSlef(ctx, v2);
                return;
            }
            Lshop lshop = (Lshop)chongjipackBean.getLimitedTimeShop().get(Integer.valueOf(buyShopBean.getCd()));
            if (lshop == null) {
                return;
            }
            int expireTimeInSeconds = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date currentDate = new Date();
                Date startTime = sdf.parse(chongjipackBean.getHuitime());
                Date endTime = sdf.parse(chongjipackBean.getEndtime());
                if (currentDate.getTime() < startTime.getTime() && currentDate.getTime() > endTime.getTime()) {
                    return;
                }
                expireTimeInSeconds = (int)TimeUnit.MILLISECONDS.toSeconds(endTime.getTime() - currentDate.getTime());
            }
            catch (ParseException e) {
                return;
            }
            Lshop roleLshop = AllServiceUtil.getLimitedTimeLshopService().selectByID(loginResult.getRole_id(), buyShopBean.getnId() + "", lshop.getId());
            if (roleLshop == null) {
                roleLshop = lshop.clone();
            }
            goodid = roleLshop.getGid();
            if (goodid.longValue() < 0L && roleData.getPackRecord().isTX(-goodid.longValue() + "")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已拥有该特效"));
                return;
            }
            if (roleLshop != null && roleLshop.getNum() >= lshop.getMaxNum()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("物品已达到最大购买次数！"));
                return;
            }
            if (buyShopBean.getSum() > roleLshop.getlNum()) {
                assetUpdate.setMsg("单次最大购买数量" + roleLshop.getlNum());
                buyShopBean.setSum(roleLshop.getlNum());
            }
            long jg = roleLshop.getMoney().longValue() * (long)buyShopBean.getSum();
            if (buyShopBean.getPrice() != jg) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("买慢点,太快了我反应不过来,或者看看的货币够不够#55"));
                return;
            }
            goodsBuy.setGid(goodid);
            goodsBuy.setPrice(roleLshop.getMoney());
            goodsBuy.setGoodnumber(new BigDecimal(buyShopBean.getSum()));
            goodsBuy.setNumbermoney(new BigDecimal(jg));
            if (roleLshop.getType() == 0) {
                if (loginResult.getGold().longValue() < jg) {
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - jg));
                assetUpdate.setData("D=" + -jg);
                MonitorUtil.getMoney().useD(jg);
                buyType = 20;
                goodsBuy.setBuytype(new BigDecimal(1));
            }
            else if (roleLshop.getType() == 1) {
                if (loginResult.getCodecard().longValue() < jg) {
                    return;
                }
                loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - jg));
                MonitorUtil.getMoney().useX(jg);
                assetUpdate.setData("X=" + -jg);
                buyType = 17;
                goodsBuy.setBuytype(new BigDecimal(2));
            }
            else if (roleLshop.getType() == 2) {
                if ((long)(int)loginResult.getMoney() < jg) {
                    return;
                }
                loginResult.setMoney(Integer.valueOf((int)((long)(int)loginResult.getMoney() - jg)));
                MonitorUtil.getMoney().useC(jg);
                assetUpdate.setData("C=" + -jg);
                buyType = 17;
                goodsBuy.setBuytype(new BigDecimal(3));
            }
            else if (roleLshop.getType() == 3) {
                if (loginResult.getTransfergold().longValue() < jg) {
                    return;
                }
                loginResult.setTransfergold(new BigDecimal(loginResult.getTransfergold().longValue() - jg));
                MonitorUtil.getMoney().useZ(jg);
                assetUpdate.setData("Z=" + -jg);
                buyType = 17;
                goodsBuy.setBuytype(new BigDecimal(9));
            }
            else {
                return;
            }
            if (roleLshop != null) {
                roleLshop.setNum(roleLshop.getNum() + buyShopBean.getSum());
            }
            AllServiceUtil.getLimitedTimeLshopService().addReidsLimit(loginResult.getRole_id(), buyShopBean.getnId() + "", roleLshop, expireTimeInSeconds);
        }
        if (goodid == null) {
            return;
        }
        Goodstable goodstable = GameServer.getGood(goodid);
        if (goodstable == null) {
            return;
        }


        if (buyShopBean.getAte() == 3 || buyShopBean.getAte() == 4) {
            if (assetUpdate.getMsg() != null) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(assetUpdate.getMsg());
                buffer.append("|你购买了");
                buffer.append(buyShopBean.getSum());
                buffer.append("个");
                buffer.append(goodstable.getGoodsname());
                assetUpdate.setMsg(buffer.toString());
                assetUpdate.setType(AssetUpdate.USEGOOD);
            }
            else {
                assetUpdate.setMsg("你购买了" + buyShopBean.getSum() + "个" + goodstable.getGoodsname());
            }
        }
        else {
            if (goodstable .getType()!=1010)
            assetUpdate.setMsg(buyShopBean.getSum() + "个" + goodstable.getGoodsname());
        }
        goodstable.setRole_id(loginResult.getRole_id());
        long yid = goodid.longValue();
        for (int i = 0; i < buyShopBean.getSum(); ++i) {
            if (i != 0) {
                goodstable = GameServer.getGood(goodid);
            }
            goodstable.setRole_id(loginResult.getRole_id());
            long sid = goodstable.getGoodsid().longValue();
            if (sid >= 70001L && sid <= 70030L) {
                Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
            }else if (sid >= 81619 && sid <= 81656) {
                XuanBao lingbao = SplitLingbaoValue.addxuan(goodstable.getValue(), loginResult.getRole_id());
                if (lingbao == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("同类玄宝只能获得一个"));
                    return;
                }
                assetUpdate.setXuanBao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, buyShopBean.getSum(), buyType);
            }
            else if (sid >= 69001L && sid <= 69015L) {
                Lingbao lingbao = SplitLingbaoValue.addfa(sid, loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
            }
            else if (goodstable.getType() == 825L) {
                if (!goodstable.getValue().equals("")) {
                    String[] v3 = goodstable.getValue().split("\\|");
                    int suitid = Integer.parseInt(v3[0]);
                    int part = Integer.parseInt(v3[1]);
                    int pz = Integer.parseInt(v3[2]);
                    PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                    assetUpdate.setJade(jade);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
                }
            }
            else if (goodstable.getType() == -1L) {
                roleData.getPackRecord().addTX(-sid + "");
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(buyType));
            }
            else if (EquipTool.canSuper(goodstable.getType())) {
                int sum2 = (yid == sid) ? buyShopBean.getSum() : 1;
                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable.getGoodsid());
                if (sameGoodstable.size() != 0) {
                    int uses = (int) sameGoodstable.get(0).getUsetime() + sum2;
                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                    assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                    AllServiceUtil.getGoodsrecordService().insert((Goodstable)sameGoodstable.get(0), null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
                }
                else {
                    goodstable.setUsetime(Integer.valueOf(sum2));
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
                }
                if (yid == sid) {
                    break;
                }
            } else  if (goodstable.getType() == 1010L) {
                AssetUpdate getmoney = new AssetUpdate();

                String[] s = goodstable.getValue().split("=")[1].split("&");
                List<RandomLong> randomNums = new ArrayList<>();
                for (String string : s) {
                    String[] m = string.split("\\$");
                    RandomLong randomNum = new RandomLong();
                    randomNum.setProbability(Integer.parseInt(m[0]));
                    String[] n = m[1].split("-");
                    randomNum.setMin(Long.parseLong(n[0]));
                    randomNum.setMax(Long.parseLong(n[1]));
                    randomNums.add(randomNum);
                }


//                Collections.sort(randomNums, new Comparator<RandomNum>() {
//                    @Override
//                    public int compare(RandomNum p1, RandomNum p2) {
//                        return Integer.compare(p1.getProbability(), p2.getProbability());
//                    }
//                });
                Boolean b = Boolean.valueOf(false);
                BigDecimal number = BigDecimal.ZERO;
                for (RandomLong randomNum2 : randomNums) {
                    if (randomNum2.getProbability() > new Random().nextInt(100)) {
                        b = Boolean.valueOf(true);
                        long money = randomNum2.getMin() + ThreadLocalRandom.current().nextLong(randomNum2.getMax() - randomNum2.getMin());
                        number = new BigDecimal(money + "");
                        break;
                    }
                }
                if (number.longValue() == 0L) {
                    long min = ((RandomLong)randomNums.get(randomNums.size() - 1)).getMin();
                    number = new BigDecimal(min + "");
                }
                StringBuffer buffer = new StringBuffer();
                if (goodstable.getValue().startsWith("金钱")) {
                    long money2 = number.longValue();
                    if (money2 != 0L) {
                        getmoney.updata("D=" + money2);
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(money2)));

                        MonitorUtil.getMoney().addD(money2, 1);
                        MonitorUtil.getDropQM2().add(money2);

                        buffer.append("你获得金钱");
                        buffer.append(money2);
                    }
                }
                else if (goodstable.getValue().startsWith("仙玉")) {
                    long money2 = number.longValue();
                    if (money2 != 0L) {
                        getmoney.updata("X=" + money2);
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money2)));
                        MonitorUtil.getMoney().addX(money2, 1);
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你获得仙玉#Y");
                        buffer.append(money2);
                    }
                }
                getmoney.setMsg(buffer.toString());
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(getmoney)));
            } else {
                goodstable.setUsetime(Integer.valueOf(1));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(buyShopBean.getSum()), Integer.valueOf(buyType));
            }
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        if (isSX && shop != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().ShopPriceAgreement("0|1|" + shop.getShopid() + "|" + shop.getPrice()));
        }
        AllServiceUtil.getGoodsTableService().addGoodsBuyRecord(goodsBuy);

        if (JJF!=null) {
            if (JJF.equals(DYT)) {
                AchievemUtil.detectionAchievem(loginResult, "大雁塔兑换一次物品");
            }
//            if (JJF.equals(DYT)) {
//                AchievemUtil.detectionAchievem(loginResult, "大雁塔兑换十次物品");
//            }
//            if (JJF.equals(DYT)) {
//                AchievemUtil.detectionAchievem(loginResult, "大雁塔兑换五十次物品");
//            }
            if (JJF.equals(DG)) {
                AchievemUtil.detectionAchievem(loginResult, "地宫兑换一次物品");
            }
            if (JJF.equals(XF)) {
                AchievemUtil.detectionAchievem(loginResult, "寻访兑换一次物品");
            }
        }

        if (goodsBuy.getGid().intValue() == 80677 && (long)goodstable.getQuality() >= 2L) {
            StringBuffer sb = new StringBuffer();
            sb.append("#R");
            sb.append(loginResult.getRolename());
            sb.append("#c00FFFF兑换功绩时犹豫不定，干脆听天由命！功绩官见");
            sb.append("#G[");
            sb.append(goodstable.getGoodsname());
            sb.append("#G]#c00FFFF还多，就顺手塞给他一个！#24");
            SuitMixdeal.jpdC(sb.toString());
        }
        else if (goodsBuy.getGid().intValue() == 32645 && (long)goodstable.getQuality() >= 2L) {
            StringBuffer sb = new StringBuffer();
            sb.append("#R");
            sb.append(loginResult.getRolename());
            sb.append("#c00FFFF高喊着：一旦拥有，别无所求！爽快的用功绩换取了一个");
            sb.append("#G[");
            sb.append(goodstable.getGoodsname());
            sb.append("#G]#44");
            SuitMixdeal.jpdC(sb.toString());
        }
    }
}
