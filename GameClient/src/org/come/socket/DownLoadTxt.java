package org.come.socket;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import com.tool.fuben.TaskListAll;
import org.come.model.*;
import org.come.until.AC999;

import java.net.URL;

import org.come.model.Title;

import java.util.Iterator;

import org.come.MountShouHu.AllMountShouhu;
import org.come.until.NewAESUtil;
import org.come.until.StallPurchaseUntil;
import org.come.bean.StallPurchaseBean;
import org.come.bean.AutoActiveBaseBean;
import org.come.bean.ConfigureBean;
import org.come.bean.GMshopItemBean;

import java.util.ArrayList;

import org.come.bean.QianDaoBean;
import org.come.bean.AllMeridians;
import org.come.bean.AllLianHua;
import org.come.bean.AllAchieve;
import org.come.bean.AllActive;
import org.come.model.allPetExchange;
import org.come.bean.AllPal;
import org.come.bean.AllEventModelBean;
import org.come.bean.AllTitleBean;
import org.come.bean.AllACard;

import java.util.HashMap;

import org.come.bean.AllGuide;
import org.come.bean.AllSuit;
import org.come.bean.AllTx;
import org.come.bean.AllColorScheme;
import org.come.bean.AllTalent;
import org.come.bean.AllBbuy;
import com.tool.ModerateTask.AllTask;
import org.come.bean.AllDoorBean;
import org.come.bean.AllNpcBean;
import org.come.bean.RobotsBean;
import org.come.bean.GoodsBean;
import org.come.bean.AllBabyResult;
import org.come.bean.Skill;
import org.come.bean.SkillBean;
import org.come.bean.EshopBean;
import org.come.bean.RoleExpBean;
import org.come.bean.NpcShopBean;
import org.come.bean.AllMapBean;
import org.come.model.allGoodsExchange;

import java.awt.Color;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

import org.come.model.allItemExchange;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.bean.AllAuctionGoodsExchange;

public class DownLoadTxt {
    private static DownLoadTxt downLoadTxt;
    private String PATH;
    public static String ip;
    public static int port;

    public static DownLoadTxt getDownLoadTxt() {
        if (DownLoadTxt.downLoadTxt == null) {
            DownLoadTxt.downLoadTxt = new DownLoadTxt();
        }
        return DownLoadTxt.downLoadTxt;
    }

    public DownLoadTxt() {
        String web = "GameServer";
        StringBuffer buffer = new StringBuffer();
        buffer.append("http://");
        buffer.append(DownLoadTxt.ip);
        buffer.append(":");
        buffer.append(DownLoadTxt.port);
        buffer.append("/");
        buffer.append(web);
        buffer.append("/GetTXT/");
        this.PATH = buffer.toString();
    }

    public void initMes(String filename) {
        this.deviceClassForMes(filename.split("\\.")[0], this.GetTxt(filename));
    }

    public void deviceClassForMes(String fileName, String MesStr) {
        int n = -1;
        if (fileName.equals("tasklist")) {
            TaskListAll taskList = GsonUtil.getGsonUtil().getgson().fromJson(MesStr, TaskListAll.class);
            UserMessUntil.setTaskListAll(taskList);
            return;

        } else if (fileName.equals("xuanbao")) {
            AllXuanbao allXuanbao = GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllXuanbao.class);
            UserMessUntil.setAllXuanbao(allXuanbao);
            return;
        }
        switch (fileName.hashCode()) {
            case 1782622742: {
                if (fileName.equals("auctionGoodsExchange")) {
                    n = 0;
                    break;
                } else {
                    break;
                }
            }
            case -1676760490: {
                if (fileName.equals("itemExchange")) {
                    n = 1;
                    break;
                } else {
                    break;
                }
            }
            case -1799367701: {
                if (fileName.equals("titleColor")) {
                    n = 2;
                    break;
                } else {
                    break;
                }
            }
            case 1573507993: {
                if (fileName.equals("goodsExchange")) {
                    n = 3;
                    break;
                } else {
                    break;
                }
            }
            case 107868: {
                if (fileName.equals("map")) {
                    n = 4;
                    break;
                } else {
                    break;
                }
            }
            case 2142580055: {
                if (fileName.equals("npcshop")) {
                    n = 5;
                    break;
                } else {
                    break;
                }
            }
            case 100893: {
                if (fileName.equals("exp")) {
                    n = 6;
                    break;
                } else {
                    break;
                }
            }
            case 96805083: {
                if (fileName.equals("eshop")) {
                    n = 7;
                    break;
                } else {
                    break;
                }
            }
            case 109496913: {
                if (fileName.equals("skill")) {
                    n = 8;
                    break;
                } else {
                    break;
                }
            }
            case 1063146611: {
                if (fileName.equals("babyresult")) {
                    n = 9;
                    break;
                } else {
                    break;
                }
            }
            case 98539350: {
                if (fileName.equals("goods")) {
                    n = 10;
                    break;
                } else {
                    break;
                }
            }
            case -925703351: {
                if (fileName.equals("robots")) {
                    n = 11;
                    break;
                } else {
                    break;
                }
            }
            case 109281: {
                if (fileName.equals("npc")) {
                    n = 12;
                    break;
                } else {
                    break;
                }
            }
            case 3089326: {
                if (fileName.equals("door")) {
                    n = 13;
                    break;
                } else {
                    break;
                }
            }
            case 3552645: {
                if (fileName.equals("task")) {
                    n = 14;
                    break;
                } else {
                    break;
                }
            }
            case 3017444: {
                if (fileName.equals("bbuy")) {
                    n = 15;
                    break;
                } else {
                    break;
                }
            }
            case -881086228: {
                if (fileName.equals("talent")) {
                    n = 16;
                    break;
                } else {
                    break;
                }
            }
            case 94842723: {
                if (fileName.equals("color")) {
                    n = 17;
                    break;
                } else {
                    break;
                }
            }
            case 3716: {
                if (fileName.equals("tx")) {
                    n = 18;
                    break;
                } else {
                    break;
                }
            }
            case 3541773: {
                if (fileName.equals("suit")) {
                    n = 19;
                    break;
                } else {
                    break;
                }
            }
            case 98712316: {
                if (fileName.equals("guide")) {
                    n = 20;
                    break;
                } else {
                    break;
                }
            }
            case 115326: {
                if (fileName.equals("tyc")) {
                    n = 21;
                    break;
                } else {
                    break;
                }
            }
            case 92627697: {
                if (fileName.equals("acard")) {
                    n = 22;
                    break;
                } else {
                    break;
                }
            }
            case 110371416: {
                if (fileName.equals("title")) {
                    n = 23;
                    break;
                } else {
                    break;
                }
            }
            case 96891546: {
                if (fileName.equals("event")) {
                    n = 24;
                    break;
                } else {
                    break;
                }
            }
            case 110747: {
                if (fileName.equals("pal")) {
                    n = 25;
                    break;
                } else {
                    break;
                }
            }
            case 780014690: {
                if (fileName.equals("petExchange")) {
                    n = 26;
                    break;
                } else {
                    break;
                }
            }
            case -1422950650: {
                if (fileName.equals("active")) {
                    n = 27;
                    break;
                } else {
                    break;
                }
            }
            case -1942689405: {
                if (fileName.equals("vipActive")) {
                    n = 28;
                    break;
                } else {
                    break;
                }
            }
            case -1172895151: {
                if (fileName.equals("achieve")) {
                    n = 29;
                    break;
                } else {
                    break;
                }
            }
            case 3452: {
                if (fileName.equals("lh")) {
                    n = 30;
                    break;
                } else {
                    break;
                }
            }
            case 1683986130: {
                if (fileName.equals("meridians")) {
                    n = 31;
                    break;
                } else {
                    break;
                }
            }
            case 307719469: {
                if (fileName.equals("qiandao")) {
                    n = 32;
                    break;
                } else {
                    break;
                }
            }
            case 1146757647: {
                if (fileName.equals("GMshopItem")) {
                    n = 33;
                    break;
                } else {
                    break;
                }
            }
            case -804429082: {
                if (fileName.equals("configure")) {
                    n = 34;
                    break;
                } else {
                    break;
                }
            }
            case 2497909: {
                if (fileName.equals("autoactive")) {
                    n = 35;
                    break;
                } else {
                    break;
                }
            }
            case 1743324417: {
                if (fileName.equals("purchase")) {
                    n = 36;
                    break;
                } else {
                    break;
                }
            }
            case 109417379: {
                if (fileName.equals("shssx")) {
                    n = 37;
                    break;
                } else {
                    break;
                }
            }
            case 1747619631: {
                if (fileName.equals("achievement")) {
                    n = 38;
                    break;
                } else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                AllAuctionGoodsExchange allAuctionGoodsExchange = (AllAuctionGoodsExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllAuctionGoodsExchange.class);
                UserMessUntil.setAllAuctionGoodsExchange(allAuctionGoodsExchange);
                break;
            }
            case 1: {
                allItemExchange allItemExchange = (allItemExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, allItemExchange.class);
                UserMessUntil.setAllItemExchange((allItemExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, allItemExchange.class));
                UserMessUntil.setAllItemExchange(allItemExchange);
                break;
            }
            case 2: {
                ConcurrentHashMap titleColors = (ConcurrentHashMap) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, ConcurrentHashMap.class);
                ConcurrentHashMap<String, Color> stringColorConcurrentHashMap = new ConcurrentHashMap<>();
                if (titleColors != null) {
                    titleColors.forEach((k, v)/* java.lang.Object,java.lang.Object, */ -> {
                        String v2 = (String) v;
                        if (StringUtils.isNotBlank(v2)) {
                            String[] v2s = v2.split(",");
                            Color color = new Color(Integer.parseInt(v2s[0]), Integer.parseInt(v2s[1]), Integer.parseInt(v2s[2]));
                            stringColorConcurrentHashMap.put((String) k, color);
                        }
                        return;
                    });
                }
                UserMessUntil.setTitleColors(stringColorConcurrentHashMap);
                break;
            }
            case 3: {
                allGoodsExchange allGoodsExchange = (allGoodsExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, allGoodsExchange.class);
                UserMessUntil.setAllGoodsExchange((allGoodsExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, allGoodsExchange.class));
                UserMessUntil.setAllGoodsExchange(allGoodsExchange);
                break;
            }
            case 4: {
                AllMapBean allmap = (AllMapBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllMapBean.class);
                UserMessUntil.setAllmapbean(allmap);
                break;
            }
            case 5: {
                NpcShopBean npcshop = (NpcShopBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, NpcShopBean.class);
                UserMessUntil.setNpcshop(npcshop);
                break;
            }
            case 6: {
                RoleExpBean petAndPlayerExp = (RoleExpBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, RoleExpBean.class);
                UserMessUntil.setPetAndPlayerExp(petAndPlayerExp);
                break;
            }
            case 7: {
                EshopBean eshopItem = (EshopBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, EshopBean.class);
                UserMessUntil.setEshops(eshopItem.getEshops());
                break;
            }
            case 8: {
                SkillBean skillBean = (SkillBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, SkillBean.class);
                for (String skillid : skillBean.getSkillMap().keySet()) {
                    UserMessUntil.NameforSkill.put(((Skill) skillBean.getSkillMap().get(skillid)).getSkillname(), skillBean.getSkillMap().get(skillid));
                }
                UserMessUntil.setSkillBean(skillBean);
                break;
            }
            case 9: {
                AllBabyResult allBabyResult = (AllBabyResult) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllBabyResult.class);
                UserMessUntil.setAllBabyResult(allBabyResult);
                break;
            }
            case 10: {
                GoodsBean goodsBean = (GoodsBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, GoodsBean.class);
                UserMessUntil.setGoodsBean(goodsBean);
                break;
            }
            case 11: {
                RobotsBean robotsBean = (RobotsBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, RobotsBean.class);
                UserMessUntil.setRobotBean(robotsBean);
                break;
            }
            case 12: {
                AllNpcBean allNpcBean = (AllNpcBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllNpcBean.class);
                UserMessUntil.setAllNpcBean(allNpcBean);
                break;
            }
            case 13: {
                AllDoorBean allDoorBean = (AllDoorBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllDoorBean.class);
                UserMessUntil.setAllDoorBean(allDoorBean);
                break;
            }
            case 14: {
                AllTask allTask = (AllTask) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllTask.class);
                UserMessUntil.setAllTask(allTask);
                break;
            }
            case 15: {
                AllBbuy allBbuy = (AllBbuy) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllBbuy.class);
                UserMessUntil.setAllBbuy(allBbuy);
                break;
            }
            case 16: {
                AllTalent allTalent = (AllTalent) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllTalent.class);
                UserMessUntil.setAllTalent(allTalent);
                break;
            }
            case 17: {
                AllColorScheme allColorScheme = (AllColorScheme) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllColorScheme.class);
                UserMessUntil.setAllColorScheme(allColorScheme);
                break;
            }
            case 18: {
                AllTx allTx = (AllTx) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllTx.class);
                UserMessUntil.setAllTx(allTx);
                break;
            }
            case 19: {
                AllSuit allSuit = (AllSuit) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllSuit.class);
                UserMessUntil.setAllSuit(allSuit);
                break;
            }
            case 20: {
                AllGuide allGuide = (AllGuide) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllGuide.class);
                UserMessUntil.setAllGuide(allGuide);
                break;
            }
            case 21: {
                HashMap<String, String> allTYC = (HashMap) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, HashMap.class);
                UserMessUntil.setAllTYC(allTYC);
                break;
            }
            case 22: {
                AllACard allACard = (AllACard) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllACard.class);
                UserMessUntil.setAllACard(allACard);
                break;
            }
            case 23: {
                if (MesStr != null) {
                    AllTitleBean allTitleBean = (AllTitleBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllTitleBean.class);
                    UserMessUntil.setAllTitle(allTitleBean);
                    break;
                } else {
                    AllTitleBean allTitleBean = new AllTitleBean();
                    HashMap<String, Title> hashMap = new HashMap<>();
                    allTitleBean.setTitleMap(hashMap);
                    UserMessUntil.setAllTitle(allTitleBean);
                    break;
                }
            }
            case 24: {
                AllEventModelBean allEvent = (AllEventModelBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllEventModelBean.class);
                UserMessUntil.setAllEvent(allEvent);
                break;
            }
            case 25: {
                AllPal allPal = (AllPal) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllPal.class);
                UserMessUntil.setAllPal(allPal);
                break;
            }
            case 26: {
                allPetExchange allPetExchange = (allPetExchange) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, allPetExchange.class);
                UserMessUntil.setAllPetExchange(allPetExchange);
                break;
            }
            case 27: {
                AllActive allActive = (AllActive) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllActive.class);
                UserMessUntil.setAllActive(allActive);
                break;
            }
            case 28: {
                AllActive allVipActive = (AllActive) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllActive.class);
                UserMessUntil.setAllVipActive(allVipActive);
                break;
            }
            case 29: {
                AllAchieve allAchieve = (AllAchieve) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllAchieve.class);
                UserMessUntil.setAllAchieve(allAchieve);
                break;
            }
            case 30: {
                AllLianHua allLianHua = (AllLianHua) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllLianHua.class);
                UserMessUntil.setAllLianHua(allLianHua);
                break;
            }
            case 31: {
                AllMeridians all = (AllMeridians) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllMeridians.class);
                UserMessUntil.setAllMeridians(all);
                break;
            }
            case 32: {
                QianDaoBean qianDaoBean = (QianDaoBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, QianDaoBean.class);
                UserMessUntil.setQianDaoList(new ArrayList(qianDaoBean.getAllQianDao().values()));
                break;
            }
            case 33: {
                GMshopItemBean gMshopItemBean = (GMshopItemBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, GMshopItemBean.class);
                UserMessUntil.setAllGMshopItem(gMshopItemBean);
                break;
            }
            case 34: {
                ConfigureBean configureBean = (ConfigureBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, ConfigureBean.class);
                UserMessUntil.setConfigureBean(configureBean);
                break;
            }
            case 35: {
                AutoActiveBaseBean allautobase = (AutoActiveBaseBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AutoActiveBaseBean.class);
                UserMessUntil.setAlltutoactive(allautobase);
                break;
            }
            case 36: {
                StallPurchaseBean stallPurchaseBean = (StallPurchaseBean) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, StallPurchaseBean.class);
                StallPurchaseUntil.setStallPurchaseBean(stallPurchaseBean);
                break;
            }
            case 37: {
                AllMountShouhu allMountShouhu = (AllMountShouhu) GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllMountShouhu.class);
                UserMessUntil.setAllMountShouhu(allMountShouhu);
                break;
            }
            case 38: {
                // 功绩千秋
                AllAchievement allAchievement = GsonUtil.getGsonUtil().getgson().fromJson(MesStr, AllAchievement.class);
                UserMessUntil.setAllAchievement(allAchievement);
                break;
            }
        }
    }

    public String GetTxt(String path) {
        byte[] v = null;
        try {
            v = this.downloadtxt(new URL(this.PATH + path + "?" + System.currentTimeMillis()));
            String message = new String(v, AC999.UTF_8);
            String aesjdkDncode_utf8 = NewAESUtil.AESJDKDncode(message);
            return aesjdkDncode_utf8;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] downloadtxt(URL url) {
        try {
            long x = System.currentTimeMillis();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream bis = url.openStream();
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = bis.read(buf)) != -1) {
                bos.write(buf, 0, count);
            }
            bos.close();
            bis.close();
            x = System.currentTimeMillis() - x;
            if (x > 1000L) {
                System.err.println("资源下载完毕:耗时" + x);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            System.err.println("下载资源失败 error=" + e.getMessage() + ":" + url.getPath());
            e.printStackTrace();
            return null;
        }
    }

    public String GetServerTxt(String path) {
        Object var2 = null;
        try {
            byte[] v = this.downloadtxt(new URL(this.PATH + path + "?" + System.currentTimeMillis()));
            String message = new String(v, AC999.UTF_8);
            return message;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
