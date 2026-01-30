package org.come.readUtil;

import org.come.entity.*;
import org.come.model.*;
import org.come.until.CreateTextUtil;
import org.come.tool.NewAESUtil;
import org.come.model.GolemDraw;
import org.come.model.GolemActive;
import org.come.action.wqx.KJexamQuestions;
import org.come.bean.StallPurchaseBean;
import org.come.model.AuctionExchange;
import org.come.model.LotteryItemBasetwo;
import org.come.model.LotteryItemBase;
import org.come.entity.PayvipBean;
import org.come.model.LiangHaoBase;
import org.come.model.ShaoXiang;
import org.come.model.WitchComposeAttr;
import org.come.model.TeJiLH;
import org.come.entity.Fly;
import org.come.model.GMshopItem;
import org.come.model.GoodsExchange;
import org.come.model.ItemExchange;
import org.come.model.QianDao;
import org.come.readBean.AllMeridians;
import org.come.readBean.AllLianHua;
import org.come.readBean.AllAchieve;
import org.come.readBean.AllActive;
import org.come.readBean.RookieGuideBean;
import org.come.readBean.BabyResult;
import org.come.entity.StarPalace;
import org.come.entity.WingTraining;
import org.come.model.EventModel;
import org.come.model.Title;
import org.come.model.aCard;
import org.come.action.lottery.Draw;
import org.come.model.Talent;
import org.come.model.ColorScheme;
import org.come.entity.Mount;
import org.come.entity.Present;
import org.come.bean.RoleTxBean;
import org.come.entity.Suit;
import org.come.bean.Bbuy;
import come.tool.Scene.DNTG.DNTGAward;
import org.come.model.Skill;
import org.come.model.Gem;
import org.come.entity.Lingbao;
import org.come.model.Xianqi;
import org.come.model.Sghostpoint;
import org.come.model.Lshop;
import org.come.model.Eshop;
import org.come.model.Shop;
import org.come.model.GodStone;
import org.come.model.Decorate;
import org.come.model.Alchemy;
import org.come.model.NewequipMapObject;
import java.util.Iterator;
import java.util.Map;
import org.come.model.Monster;
import org.come.model.Boos;
import org.come.model.PalData;
import org.come.model.TaskData;
import org.come.model.TaskSet;
import org.come.model.Door;
import org.come.model.Npctable;
import org.come.model.Gamemap;
import org.come.model.PetExchange;
import org.come.model.PalEquip;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import org.come.model.SellLiangHaoBase;
import org.come.readBean.AllAuctionGoodsExchange;
import java.util.List;
import org.come.entity.ChongjipackBean;
import org.come.model.Configure;
import org.come.model.Achieve;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import come.tool.BangBattle.BangFight;
import org.come.model.Dorp;
import org.come.bean.NpcShopBean;
import org.come.task.RefreshMonsterTask;
import org.come.model.Robots;
import java.util.HashMap;
import org.come.bean.RobotsBean;
import org.come.task.MonsterUtil;
import org.come.readBean.AllPal;
import org.come.until.GsonUtil;
import org.come.readBean.AllTask;
import org.come.server.GameServer;

public class ReadPoolUtil
{
    public static boolean readTypeTwo(StringBuffer buffer, int type) {
        if (type == 0) {
            System.out.println("正在读取：pet.xls");
            ConcurrentHashMap<BigDecimal, RoleSummoning> map = ReadPetUtil.allPetId("pet", buffer);
            if (map != null) {
                GameServer.setAllPet(map);
            }
            System.out.println("正在读取：item.xls");
            ConcurrentHashMap<BigDecimal, Goodstable> map2 = ReadItemUtil.allitemId("item", buffer);
            if (map2 != null) {
                GameServer.setAllitem(map2);
            }
            return map != null && map2 != null;
        }
        else if (type == 1) {
            System.out.println("正在读取：palEquip.xls");
            ConcurrentHashMap<Long, PalEquip> map3 = ReadPetUtil.allPalEquip("palEquip", buffer);
            if (map3 != null) {
                GameServer.setAllPalEquip(map3);
            }
            return map3 != null;
        }
        else if (type == 2) {
            System.out.println("正在读取：petExchange.xls");
            ConcurrentHashMap<Integer, PetExchange> map4 = ReadPetUtil.allPetExchangeMap("petExchange", buffer);
            if (map4 != null) {
                GameServer.setAllPetExchange(map4);
                String msg = ReadPetUtil.createTxtPetExchange(map4);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\petExchange.txt", msg);
            }
            return map4 != null;
        }
        else if (type == 3) {
            System.out.println("正在读取：map.xls");
            ConcurrentHashMap<String, Gamemap> map5 = ReadMapUtil.selectAllMap("map", buffer);
            if (map5 != null) {
                GameServer.setGameMap(map5);
                String msg = ReadMapUtil.createTxtMap(map5);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\map.txt", msg);
            }
            return map5 != null;
        }
        else if (type == 4) {
            System.out.println("正在读取：npc.xls");
            ConcurrentHashMap<String, Npctable> map6 = ReadMapUtil.selectallNpc("npc", buffer);
            if (map6 != null) {
                GameServer.setNpcMap(map6);
                String msg = ReadMapUtil.createTxtNpc(map6);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\npc.txt", msg);
            }
            return map6 != null;
        }
        else if (type == 5) {
            System.out.println("正在读取：door.xls");
            ConcurrentHashMap<Integer, Door> map7 = ReadMapUtil.selectDoors("door", buffer);
            if (map7 != null) {
                GameServer.setDoorMap(map7);
                String msg = ReadMapUtil.createTxtDoor(map7);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\door.txt", msg);
            }
            return map7 != null;
        }
        else if (type == 6) {
            System.out.println("正在读取：taskSet.xls");
            ConcurrentHashMap<Integer, TaskSet> allTaskSet = ReadTaskSetUtil.selectTaskSet("taskSet", buffer);
            if (allTaskSet == null) {
                return false;
            }
            GameServer.setAllTaskSet(allTaskSet);
            System.out.println("正在读取：taskData.xls");
            ConcurrentHashMap<Integer, TaskData> allTaskData = ReadTaskSetUtil.selectTaskData("taskData", buffer);
            if (allTaskData == null) {
                return false;
            }
            GameServer.setAllTaskData(allTaskData);
            AllTask allTask = new AllTask();
            allTask.setAllTaskData(allTaskData);
            allTask.setAllTaskSet(allTaskSet);
            String msg2 = GsonUtil.getGsonUtil().getgson().toJson(allTask);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\task.txt", msg2);
            return true;
        }
        else if (type == 7) {
            System.out.println("正在读取：palData.xls");
            ConcurrentHashMap<Integer, PalData> allPalData = ReadPalDataUtil.selectPalData("palData", buffer);
            if (allPalData == null) {
                return false;
            }
            GameServer.setAllPalData(allPalData);
            AllPal allPal = new AllPal();
            allPal.setAllPalData(allPalData);
            String msg3 = GsonUtil.getGsonUtil().getgson().toJson(allPal);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\pal.txt", msg3);
            return true;
        }
        else if (type == 8) {
            System.out.println("正在读取：boos.xls");
            List<Boos> map8 = ReadBoosUtil.selectBoos("boos", buffer);
            if (map8 == null) {
                return false;
            }
            MonsterUtil.setBooses(map8);
            GameServer.boosesMap = ReadBoosUtil.boosesMap(map8);
            return true;
        }
        else if (type == 9) {
            System.out.println("正在读取：monster.xls");
            ConcurrentHashMap<String, Monster> map9 = ReadBoosUtil.getMonster("monster", buffer);
            if (map9 != null) {
                GameServer.setMonsterMap(map9);
            }
            return map9 != null;
        }
        else if (type == 10) {
            System.out.println("正在读取：robots.xls");
            ConcurrentHashMap<String, Robots> map10 = ReadBoosUtil.getRobot("robots", buffer);
            if (map10 == null) {
                return false;
            }
            GameServer.setAllRobot(map10);
            RobotsBean robotsBean = new RobotsBean();
            Map<String, Robots> getShop = new HashMap<>();
            for (Robots robots : map10.values()) {
                getShop.put(robots.getRobotid(), robots);
            }
            robotsBean.setRobotsMap(getShop);
            String msg2 = GsonUtil.getGsonUtil().getgson().toJson(robotsBean);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\robots.txt", msg2);
            return true;
        }
        else if (type == 11) {
            System.out.println("正在读取：item.xls");
            ConcurrentHashMap<BigDecimal, Goodstable> map11 = ReadGoodsUtil.getAllGoodsMap("item", buffer);
            if (map11 != null) {
                GameServer.setAllGoodsMap(map11);
                String msg = ReadGoodsUtil.createGoods(map11);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\goods.txt", msg);
            }
            return map11 != null;
        }
        else if (type == 12) {
            System.out.println("正在读取：newequip.xls");
            NewequipMapObject map12 = ReadNewequipUtil.getAllNewequip("newequip", buffer);
            if (map12 != null && map12.getSameNewequipMap() != null) {
                GameServer.setSameNewequipMap(map12.getSameNewequipMap());
            }
            if (map12 != null && map12.getWitchNewequipMap() != null) {
                GameServer.setWitchNewequipMap(map12.getWitchNewequipMap());
            }
            return map12 != null;
        }
        else if (type == 13) {
            System.out.println("正在读取：alchemy.xls");
            ConcurrentHashMap<String, List<Alchemy>> map13 = ReadNewequipUtil.getAllAlchemy("alchemy", buffer);
            if (map13 != null) {
                GameServer.setAllAlchemy(map13);
            }
            return map13 != null;
        }
        else if (type == 14) {
            System.out.println("正在读取：decorate.xls");
            ConcurrentHashMap<String, List<Decorate>> map14 = ReadNewequipUtil.getAllDecorate("decorate", buffer);
            if (map14 != null) {
                GameServer.setAllDecorate(map14);
            }
            return map14 != null;
        }
        else if (type == 15) {
            System.out.println("正在读取：godstone.xls");
            ConcurrentHashMap<String, List<GodStone>> map15 = ReadNewequipUtil.selectGodStones("godstone", buffer);
            if (map15 != null) {
                GameServer.setGodMap(map15);
            }
            return map15 != null;
        }
        else if (type == 16) {
            System.out.println("正在读取：palEquip.xls");
            ConcurrentHashMap<Long, PalEquip> map3 = ReadPalDataUtil.selectPalEquip("palEquip", buffer);
            if (map3 != null) {
                GameServer.setAllPalEquip(map3);
            }
            return map3 != null;
        }
        else if (type == 17) {
            System.out.println("正在读取：shop.xls");
            RefreshMonsterTask.upBuyCount(-1, false);
            ConcurrentHashMap<String, Shop> map16 = ReadShopUtil.getAllShop("shop", buffer);
            if (map16 == null) {
                return false;
            }
            GameServer.setAllShopGoods(map16);
            Map<String, List<Shop>> allShopList = ReadShopUtil.getShop(map16);
            NpcShopBean bean = new NpcShopBean();
            bean.setNpcShopMap(allShopList);
            String msg2 = GsonUtil.getGsonUtil().getgson().toJson(bean);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\npcshop.txt", msg2);
            return true;
        }
        else if (type == 18) {
            System.out.println("正在读取：eshop.xls");
            RefreshMonsterTask.upBuyCount(-1, false);
            ConcurrentHashMap<String, Eshop> map17 = ReadShopUtil.getAllEshopGoods("eshop", buffer);
            if (map17 == null) {
                return false;
            }
            GameServer.setAllEshopGoods(map17);
            String msg = ReadShopUtil.getEShop(map17);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\eshop.txt", msg);
            return true;
        }
        else if (type == 19) {
            System.out.println("正在读取：lShop.xls");
            RefreshMonsterTask.upBuyCount(-1, false);
            ConcurrentHashMap<String, Lshop> map18 = ReadShopUtil.selectLShops("lShop", buffer);
            if (map18 == null) {
                return false;
            }
            GameServer.setAllLShopGoods(map18);
            return true;
        }
        else if (type == 20) {
            System.out.println("正在读取：sghostpoint.xls");
            ConcurrentHashMap<String, List<Sghostpoint>> map19 = ReadSghostpointUtil.getMonsterTask("sghostpoint", buffer);
            if (map19 == null) {
                return false;
            }
            GameServer.setMonsterpointMap(map19);
            return true;
        }
        else if (type == 21) {
            System.out.println("正在读取：xianqi.xls");
            ConcurrentHashMap<String, List<Xianqi>> map20 = ReadXianqiUtil.getAllXianqi("xianqi", buffer);
            if (map20 == null) {
                return false;
            }
            GameServer.setGetAllXianqi(map20);
            GameServer.setXianqiTypeValue(ReadXianqiUtil.getXianqiType(map20));
            return true;
        }
        else if (type == 22) {
            System.out.println("正在读取：lingbao.xls");
            ConcurrentHashMap<String, Lingbao> map21 = ReadLingbaoUtil.getAllLingbao("lingbao", buffer);
            if (map21 == null) {
                return false;
            }
            GameServer.setAllLingbao(map21);
            return true;
        }
        else if (type == 23) {
            System.out.println("正在读取：lingbaofushi.xls");
            ConcurrentHashMap<BigDecimal, Goodstable> map11 = ReadLingbaoUtil.getAllLingbaoFushi("lingbaofushi", buffer);
            if (map11 == null) {
                return false;
            }
            GameServer.setAllLingbaoFushi(map11);
            return true;
        }
        else if (type == 24) {
            System.out.println("正在读取：gem.xls");
            ConcurrentHashMap<String, Gem> map22 = ReadGemUtil.selectGem("gem", buffer);
            if (map22 == null) {
                return false;
            }
            GameServer.setGems(map22);
            return true;
        }
        else if (type == 25) {
            System.out.println("正在读取：skill.xls");
            ConcurrentHashMap<String, Skill> map23 = ReadSkillUtil.getSkill("skill", buffer);
            if (map23 == null) {
                return false;
            }
            GameServer.setGetSkill(map23);
            String msg = ReadSkillUtil.createSkill(map23);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\skill.txt", msg);
            return true;
        }
        else if (type == 26) {
            System.out.println("正在读取：drop.xls");
            ConcurrentHashMap<String, Dorp> map24 = ReadDorpUtil.allDorpInfoByID("drop", buffer);
            if (map24 == null) {
                return false;
            }
            GameServer.setAllDorp(map24);
            Dorp dorp = (Dorp)map24.get("2053");
            if (dorp != null) {
                BangFight.SLEXP = dorp.getDorpValue();
            }
            dorp = (Dorp)map24.get("2054");
            if (dorp != null) {
                BangFight.SLJL = dorp.getDorpValue();
            }
            dorp = (Dorp)map24.get("2055");
            if (dorp != null) {
                BangFight.SBEXP = dorp.getDorpValue();
            }
            dorp = (Dorp)map24.get("2056");
            if (dorp != null) {
                BangFight.SBJL = dorp.getDorpValue();
            }
            return true;
        }
        else if (type == 27) {
            System.out.println("正在读取：dntg.xls");
            ConcurrentHashMap<Integer, DNTGAward> map25 = ReadDorpUtil.selectDNTGAwards("dntg", buffer);
            if (map25 == null) {
                return false;
            }
            GameServer.setAllDntg(map25);
            return true;
        }
        else if (type == 28) {
            System.out.println("正在读取：bbuy.xls");
            ConcurrentHashMap<Integer, Bbuy> map26 = ReadBbuyUtil.selecBbuys("bbuy", buffer);
            if (map26 == null) {
                return false;
            }
            GameServer.setAllBbuys(map26);
            String msg = ReadBbuyUtil.createBbuy(map26);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\bbuy.txt", msg);
            return true;
        }
        else if (type == 29) {
            System.out.println("正在读取：suit.xls");
            ConcurrentHashMap<Integer, Suit> map27 = ReadSuitUtil.selecSuits("suit", buffer);
            if (map27 == null) {
                return false;
            }
            GameServer.setAllSuits(map27);
            String msg = ReadSuitUtil.createSkill(map27);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\suit.txt", msg);
            return true;
        }
        else if (type == 30) {
            System.out.println("正在读取：tx.xls");
            ConcurrentHashMap<Integer, RoleTxBean> map28 = ReadTxUtil.selectDecoration("tx", buffer);
            if (map28 == null) {
                return false;
            }
            GameServer.setAllTXs(map28);
            String msg = ReadTxUtil.createTX(map28);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\tx.txt", msg);
            return true;
        }
        else if (type == 31) {
            System.out.println("正在读取：present.xls");
            List<Present> map29 = ReadPresentUtil.selectPresents("present", buffer);
            if (map29 == null) {
                return false;
            }
            GameServer.setPresents(map29);
            return true;
        }
        else if (type == 32) {
            System.out.println("正在读取：exp.xls");
            ConcurrentHashMap<Integer, Long> map30 = ReadExpUtil.getExp("exp", buffer);
            if (map30 == null) {
                return false;
            }
            GameServer.setExpMap(map30);
            String msg = ReadExpUtil.createExp(map30);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\exp.txt", msg);
            return true;
        }
        else if (type == 33) {
            System.out.println("正在读取：mount.xls");
            ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Mount>> map31 = ReadMountUtil.getAllMount("mount", buffer);
            if (map31 == null) {
                return false;
            }
            GameServer.setAllMount(map31);
            return true;
        }
        else if (type == 34) {
            System.out.println("正在读取：color.xls");
            ConcurrentHashMap<String, ColorScheme> map32 = ReadColorUtil.selectcolors("color", buffer);
            if (map32 == null) {
                return false;
            }
            GameServer.setAllColor(map32);
            String msg = ReadColorUtil.createcolor(map32);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\color.txt", msg);
            return true;
        }
        else if (type == 35) {
            System.out.println("正在读取：child.xls");
            ConcurrentHashMap<Integer, Talent> map33 = ReadTalentsUtil.selectTalents("child", buffer);
            if (map33 == null) {
                return false;
            }
            GameServer.setAlltalent(map33);
            String msg = ReadTalentsUtil.createTalent(map33);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\talent.txt", msg);
            return true;
        }
        else if (type == 36) {
            System.out.println("正在读取：draw.xls");
            ConcurrentHashMap<Integer, Draw> map34 = ReadDrawUtil.selectDraw("draw", buffer);
            if (map34 == null) {
                return false;
            }
            GameServer.setAllDraws(map34);
            return true;
        }
        else if (type == 37) {
            System.out.println("正在读取：acard.xls");
            ConcurrentHashMap<Integer, aCard> map35 = ReadACardUtil.selectACards("acard", buffer);
            if (map35 == null) {
                return false;
            }
            GameServer.setAllACard(map35);
            String msg = ReadACardUtil.createACards(map35);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\acard.txt", msg);
            return true;
        }
        else if (type == 38) {
            System.out.println("正在读取：title.xls");
            List<Title> map36 = ReadTitleUtil.selectTitles("title", buffer);
            if (map36 == null) {
                return false;
            }
            String msg = ReadTitleUtil.createTitle(map36);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\title.txt", msg);
            GameServer.setAlltitle(ReadTitleUtil.getTitle2(map36));
            String msgString = GsonUtil.getGsonUtil().getgson().toJson(GameServer.getTitleColors());
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\titleColor.txt", msgString);
            return true;
        }
        else if (type == 39) {
            System.out.println("正在读取：event.xls");
            ConcurrentHashMap<Integer, EventModel> map37 = ReadEventUtil.selectEvents("event", buffer);
            if (map37 == null) {
                return false;
            }
            GameServer.setAllevent(map37);
            String msg = ReadEventUtil.createEvent(map37);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\event.txt", msg);
            return true;
        }
        else if (type == 40) {
            System.out.println("正在读取：wingTraining.xls");
            ConcurrentHashMap<Long, WingTraining> map38 = ReadWingTrainingUtil.selectWingTraining("wingTraining", buffer);
            if (map38 == null) {
                return false;
            }
            GameServer.setAllWingTraining(map38);
            return true;
        }
        else if (type == 41) {
            System.out.println("正在读取：starPalace.xls");
            ConcurrentHashMap<String, StarPalace> map39 = ReadStarPalaceUtil.selectStarPalace("starPalace", buffer);
            if (map39 == null) {
                return false;
            }
            GameServer.setAllStarPalace(map39);
            String[] allKey = new String[map39.size()];
            allKey = (String[])map39.keySet().toArray(allKey);
            String[] allStarPalaceKey = new String[map39.size() - 9];
            int v = 0;
            List<String> list = new ArrayList<>();
            list.add("朱雀");
            list.add("青龙");
            list.add("白虎");
            list.add("玄武");
            list.add("金牛");
            list.add("苍狼");
            list.add("赤马");
            list.add("黄鹤");
            list.add("火猿");
            System.out.println(allStarPalaceKey.length + ":" + list.size() + ":" + allKey.length);
            for (int i = 1; i < allStarPalaceKey.length; ++i) {
                ++v;
                if (list.contains(allKey[v])) {
                    --i;
                }
                else {
                    allStarPalaceKey[i] = allKey[v];
                }
            }
            GameServer.setAllStarPalaceKey(allStarPalaceKey);
            return true;
        }
        else if (type == 42) {
            System.out.println("正在读取：tyc.xls");
            Map<String, String> map40 = ReadTYCUtil.selectDecoration("tyc", buffer);
            if (map40 == null) {
                return false;
            }
            String msg = ReadTYCUtil.createTX(map40);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\tyc.txt", msg);
            return true;
        }
        else if (type == 43) {
            System.out.println("正在读取：babyresult.xls");
            List<BabyResult> map41 = ReadBabyResultUtil.selectBabyResult("babyresult", buffer);
            if (map41 == null) {
                return false;
            }
            String msg = ReadBabyResultUtil.creatbabyresult(map41);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\babyresult.txt", msg);
            return true;
        }
        else if (type == 44) {
            System.out.println("正在读取：guide.xls");
            Map<Integer, RookieGuideBean> map42 = ReadGuideUtil.selectSkills("guide", buffer);
            if (map42 == null) {
                return false;
            }
            String msg = ReadGuideUtil.createSkill(map42);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\guide.txt", msg);
            return true;
        }
        else if (type == 45) {
            System.out.println("正在读取：active.xls");
            AllActive allActive = ReadActiveUtil.selectActives("active", buffer);
            if (allActive == null) {
                return false;
            }
            GameServer.setAllActive(allActive);
            String msg = GsonUtil.getGsonUtil().getgson().toJson(allActive);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\active.txt", msg);
            return true;
        }
        else if (type == 46) {
            System.out.println("正在读取：achieve.xls");
            AllAchieve allAchieve = ReadAchieveUtil.selectAchieves("achieve", buffer);
            if (allAchieve == null) {
                return false;
            }
            ConcurrentHashMap<Integer, Achieve> map43 = new ConcurrentHashMap<>();
            for (int j = 0; j < allAchieve.getAchieves().size(); ++j) {
                map43.put(Integer.valueOf(((Achieve)allAchieve.getAchieves().get(j)).getId()), allAchieve.getAchieves().get(j));
            }
            GameServer.setAllAchieve(map43);
            String msg3 = GsonUtil.getGsonUtil().getgson().toJson(allAchieve);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\achieve.txt", msg3);
            return true;
        }
        else if (type == 47) {
            System.out.println("正在读取：lh.xls");
            ConcurrentHashMap<String, List<String>> goodsByRobot = ReadBoosUtil.getRobotByGoods(GameServer.getAllRobot());
            ReadBoosUtil.setDrop(goodsByRobot, GameServer.getDorp("2051").getDorpValue(), "藏宝图");
            ReadBoosUtil.setDrop(goodsByRobot, GameServer.getDorp("2052").getDorpValue(), "高级藏宝图");
            ReadBoosUtil.setDrop(goodsByRobot, GameServer.getDorp("1007").getDorpValue(), "超级藏宝图");
            ReadBoosUtil.setDrop(goodsByRobot, GameServer.getDorp("10001").getDorpValue(), "元气蛋孵化");
            ReadTaskSetUtil.getTaskDrop(goodsByRobot);
            GameServer.setGoodsByRobot(goodsByRobot);
            AllLianHua all = ReadLianHuaUtil.selectLianHuas("lh", buffer);
            if (all == null) {
                return false;
            }
            GameServer.setAllLianHua(all);
            String msg3 = GsonUtil.getGsonUtil().getgson().toJson(all);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\lh.txt", msg3);
            return true;
        }
        else if (type == 48) {
            System.out.println("正在读取：meridians.xls");
            AllMeridians list2 = ReadMeridiansUtil.selectMeridians("meridians", buffer);
            if (list2 == null) {
                return false;
            }
            GameServer.setAllMeridians(list2);
            String msg = GsonUtil.getGsonUtil().getgson().toJson(list2);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\meridians.txt", msg);
            System.out.println("正在读取：qiandao.xls");
            ConcurrentHashMap<Integer, QianDao> qianDaoConcurrentHashMap = ReadQianDaoUtil.selectQianDao("qiandao", buffer);
            if (qianDaoConcurrentHashMap != null) {
                GameServer.setQianDaoMap(qianDaoConcurrentHashMap);
                String msgs = ReadQianDaoUtil.createQianDao(qianDaoConcurrentHashMap);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\qiandao.txt", msgs);
            }
            else {
                System.out.println("kongo=======================================");
                System.exit(0);
            }
            System.out.println("正在读取：itemExchange.xls");
            ConcurrentHashMap<Integer, ItemExchange> map44 = ReadItemUtil.allPetExchangeMap("itemExchange", buffer);
            if (map44 != null) {
                GameServer.setAllItemExchange(map44);
                String msga = ReadItemUtil.createTxtPetExchange(map44);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\itemExchange.txt", msga);
            }
            return true;
        }
        else if (type == 49) {
            System.out.println("正在读取：goodsExchange.xls");
            ConcurrentHashMap<Integer, GoodsExchange> map45 = ReadGoodsUtil.allGoodsExchangeMap("goodsExchange", buffer);
            if (map45 != null) {
                GameServer.setAllGoodsExchange(map45);
                String msg = ReadGoodsUtil.createTxtGoodsExchange(map45);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\goodsExchange.txt", msg);
            }
            return true;
        }
        else if (type == 50) {
            System.out.println("正在读取：itemExchange.xls");
            ConcurrentHashMap<Integer, ItemExchange> map46 = ReadItemUtil.allPetExchangeMap("itemExchange", buffer);
            if (map46 != null) {
                GameServer.setAllItemExchange(map46);
                String msg = ReadItemUtil.createTxtPetExchange(map46);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\itemExchange.txt", msg);
            }
            System.out.println("正在读取：configure.xls");
            ConcurrentHashMap<Integer, Configure> maps = ReadConfigureUtil.selectConfigure("configure", buffer);
            if (maps == null) {
                return false;
            }
            System.err.println("加载配置493:" + ((Configure)maps.get(Integer.valueOf(1))).getLywsx());
            GameServer.setAllConfigure(maps);
            String msgs2 = ReadConfigureUtil.createConfigure(maps);
            text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\configure.txt", msgs2);
            return map46 != null;
        }
        else if (type == 51) {
            System.out.println("正在读取：GMshopItem.xls");
            ConcurrentHashMap<Integer, GMshopItem> gMshopItemConcurrentHashMap = ReadGMshopItemUtil.selectGMshopItem("GMshopItem", buffer);
            if (gMshopItemConcurrentHashMap != null) {
                GameServer.setGMshopItemMap(gMshopItemConcurrentHashMap);
                String msg = ReadGMshopItemUtil.createGMshopItem(gMshopItemConcurrentHashMap);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\GMshopItem.txt", msg);
            }
            return true;
        }
        else {
            if (type == 51) {
                ConcurrentHashMap<Integer, GMshopItem> gMshopItemConcurrentHashMap = ReadGMshopItemUtil.selectGMshopItem("GMshopItem", buffer);
                GameServer.setGMshopItemMap(gMshopItemConcurrentHashMap);
                String msg = ReadGMshopItemUtil.createGMshopItem(gMshopItemConcurrentHashMap);
                System.out.println("更新txt内容：" + msg);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\GMshopItem.txt", msg);
                return true;
            }
            if (type == 52) {
                System.out.println("正在读取：configure.xls");
                ConcurrentHashMap<Integer, Configure> map47 = ReadConfigureUtil.selectConfigure("configure", buffer);
                if (map47 == null) {
                    return false;
                }
                GameServer.setAllConfigure(map47);
                String msg = ReadConfigureUtil.createConfigure(map47);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\configure.txt", msg);
                return true;
            }
            else if (type == 53) {
                System.out.println("正在读取：fly.xls");
                ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Fly>> map48 = ReadFlyUtil.getAllFly("fly", buffer);
                if (map48 == null) {
                    return false;
                }
                GameServer.setAllFly(map48);
                return true;
            }
            else if (type == 54) {
                System.out.println("正在读取：lhtj.xls");
                ConcurrentHashMap<Integer, TeJiLH> map49 = ReadTeJiLHUtil.getAlllhtj("lhtj", buffer);
                if (map49 == null) {
                    return false;
                }
                GameServer.setAlllhtj(map49);
                return true;
            }
            else if (type == 55) {
                System.out.println("正在读取：wuzhu.xls");
                ConcurrentHashMap<String, List<WitchComposeAttr>> map50 = ReadSwitchComposeAttrUtil.getAllData("wuzhu", buffer);
                if (map50 == null) {
                    return false;
                }
                GameServer.setAllSwitchAttr(map50);
                return true;
            }
            else if (type == 56) {
                System.out.println("正在读取：shaoxiang.xls");
                ConcurrentHashMap<String, ShaoXiang> map51 = ReadBoosUtil.getShaoXiang("shaoxiang", buffer);
                if (map51 == null) {
                    return false;
                }
                GameServer.setAllShaoXiang(map51);
                return true;
            }
            else if (type == 57) {
                System.out.println("正在读取：vipActive.xls");
                AllActive allActive = ReadActiveUtil.selectVipActives("vipActive", buffer);
                if (allActive == null) {
                    return false;
                }
                String msg = GsonUtil.getGsonUtil().getgson().toJson(allActive);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\vipActive.txt", msg);
                return true;
            }
            else if (type == 58) {
                System.out.println("正在读取：lianghao.xls");
                List<LiangHaoBase> alllianghao = ReadLiangHaoUtil.selectLianghao("lianghao", buffer);
                if (alllianghao == null) {
                    return false;
                }
                GameServer.setLianghaos(alllianghao);
                return true;
            }
            else if (type == 59) {
                System.out.println("正在读取：VipPal.xls");
                List<PayvipBean> payvipBeanList = ReadVipPlayUtil.selectVipPlay("payvip", buffer);
                if (payvipBeanList == null) {
                    return false;
                }
                GameServer.setPayvipList(payvipBeanList);
                return true;
            }
            else if (type == 60) {
                System.out.println("正在读取：chongjipack.xls");
                List<ChongjipackBean> chongjipackBeans = ReadChongJiUtil.selectChongJi("chongjipack", buffer);
                if (chongjipackBeans == null) {
                    return false;
                }
                ConcurrentHashMap<Integer, List<ChongjipackBean>> packgradecontrol = new ConcurrentHashMap<>();
                ConcurrentHashMap<Integer, ChongjipackBean> packgradecontrols = new ConcurrentHashMap<>();
                for (int k = 0; k < chongjipackBeans.size(); ++k) {
                    ChongjipackBean bean2 = (ChongjipackBean)chongjipackBeans.get(k);
                    List<ChongjipackBean> packs = (List<ChongjipackBean>)packgradecontrol.get(bean2.getPacktype());
                    if (packs == null) {
                        packs = new ArrayList<>();
                        packgradecontrol.put(bean2.getPacktype(), packs);
                    }
                    packs.add(bean2);
                    packgradecontrols.put(bean2.getId(), bean2);
                }
                GameServer.setPackgradecontrol(packgradecontrol);
                GameServer.setPackgradecontrols(packgradecontrols);
                return true;
            }
            else if (type == 61) {
                System.out.println("正在读取：lotteryitem.xls");
                List<LotteryItemBase> lotteryitembases = ReadLotteryItemUtil.selectLotterys("lotteryitem", buffer);
                List<LotteryItemBasetwo> lotteryitembasestwo = ReadLotteryItemUtil.selectLotterystwo("lotteryitem", buffer);
                if (lotteryitembases == null) {
                    return false;
                }
                if (lotteryitembasestwo == null) {
                    return false;
                }
                GameServer.setLotteryitems(lotteryitembases);
                GameServer.setLotteryitemstwo(lotteryitembasestwo);
                return true;
            }
            else if (type == 62) {
                ConcurrentHashMap<Integer, AuctionExchange> auctionGoods = ReadAuctionUtil.allAuctionGoodsExchangeMap("auctionGoods", buffer);
                AllAuctionGoodsExchange allAuctionGoodsExchange = new AllAuctionGoodsExchange();
                allAuctionGoodsExchange.setAllAuctionAuctionExchange(auctionGoods);
                if (auctionGoods != null) {
                    GameServer.setAllAuctionGoodsExchange(allAuctionGoodsExchange);
                    String msg3 = GsonUtil.getGsonUtil().getgson().toJson(allAuctionGoodsExchange);
                    text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\auctionGoodsExchange.txt", msg3);
                }
                return true;
            }
            else if (type == 63) {
                System.out.println("正在读取：selllianghao.xls");
                List<SellLiangHaoBase> alllianghao2 = ReadSellLiangHaoUtil.selectLianghao("selllianghao", buffer);
                List<SellLiangHaoBase> alllianghao3 = new ArrayList<>();
                List<SellLiangHaoBase> alllianghao4 = new ArrayList<>();
                if (alllianghao2 != null && alllianghao2.size() > 0) {
                    for (SellLiangHaoBase item : alllianghao2) {
                        if (item.getType() == 1) {
                            alllianghao3.add(item);
                        }
                        else {
                            alllianghao4.add(item);
                        }
                    }
                }
                if (alllianghao2 == null) {
                    return false;
                }
                GameServer.setSelllianghaos(alllianghao3);
                GameServer.setUpselllianghaos(alllianghao4);
                return true;
            }
            else if (type == 64) {
                System.out.println("读取摆摊 bot： purchase.xls");
                StallPurchaseBean purchaseBean = ReadStallPurchaseUtil.getStallPurchaseBean("purchase", buffer);
                if (purchaseBean == null) {
                    return false;
                }
                purchaseBean.initStallPurchaseMap();
                GameServer.setStallPurchases(purchaseBean.getStallPurchaseMap());
                String msg = GsonUtil.getGsonUtil().getgson().toJson(purchaseBean);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\purchase.txt", msg);
                return true;
            }
            else if (type == 65) {
                System.out.println("读取坐骑守护 bot： shssx.xls");
                List<MountShouhu> map52 = ReadMountShouhuUtil.selectmountshouhu("shssx", buffer);
                GameServer.setAllmountshouhu(map52);
                if (map52 == null) {
                    return false;
                }
                String msg = ReadMountShouhuUtil.creatMountShouhu(map52);
                text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\shssx.txt", msg);
                return true;
            }
            else if (type == 66) {
                ConcurrentHashMap<Integer, KJexamQuestions> kj = ReadKJUtil.getAllKJ("wenquxing", buffer);
                if (kj != null) {
                    GameServer.setGetAllKJexamQuestions(kj);
                }
                return true;
            }
            else if (type == 67) {
                System.out.println("读取机器人任务： golemActive.xls");
                List<GolemActive> list3 = ReadGolemActive.getGolemActive("golemActive", buffer);
                if (list3 != null) {
                    GameServer.setGolemActives(list3);
                }
                return list3 != null;
            }
            else if (type == 68) {
                System.out.println("读取机器人等级物资： golemDraw.xls");
                ConcurrentHashMap<String, List<GolemDraw>> map53 = ReadGolemDraw.getGolemDraw("golemDraw", buffer);
                if (map53 != null) {
                    GameServer.setAllGolemDraw(map53);
                }
                return map53 != null;
            }
            else {
                if (type == 69) {
                    System.out.println("读取机器人配置： golemConfig.xls");
                    ReadGolemConfig.getGolemConfig("golemConfig", buffer);
                    return true;
                }
                if (type == 70) {
                    System.out.println("读取机器人摆摊： golemStall.xls");
                    ReadGolemConfig.getGolemStall("golemStall", buffer);
                    ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> map = ReadBtUtil.getAllBt("bt", buffer);
                    if (map == null) {
                        return false;
                    }
                    GameServer.setAllBaiTan(map);
                    ConcurrentHashMap<Integer, Achievement> map1 = ReadAchievementUtil.allAchievementMap("achievement", buffer);
                    if (map1!=null) {
                        GameServer.setAllAchievement(map1);
                        String msg=ReadAchievementUtil.createTxtAchievement(map1);//生成txt
                       text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+ "GetTXT\\achievement.txt", msg);
                   }
                    return true;
                }
                if (type == 71) {
                    System.out.println("正在读取：taskList.xls");
                    TaskListAll taskListAll = ReadTaskListUtil.getAllData("taskList", buffer);
                    GameServer.setTaskList(taskListAll);
                    String msg = GsonUtil.getGsonUtil().getgson().toJson(taskListAll);
                    text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\tasklist.txt", msg);
                    return true;
                }
                if (type == 72) {
                    System.out.println("正在读取：car.xls");
                    ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Car>> map31 = ReadCarUtil.getAllCar("car", buffer);
                    if (map31 == null) {
                        return false;
                    }
                    GameServer.setAllCar(map31);
                    return true;
                }else if (type == 73) {
                    System.out.println("正在读取：玄宝.xls");
                    ConcurrentHashMap<Integer, XuanBao> map = ReadXuanBaoUtil.getallXuanbao("xuanbao", buffer);
                    if (map == null) return false;
                    GameServer.setAllxuanbaos(map);
                    String msg = ReadXuanBaoUtil.createBbuy(map);
                    text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\xuanbao.txt", msg);
                    return true;
                }
                return false;
            }
        }
    }
    
    public static void text(String path, String msg) {
        try {
            String vvvStr = NewAESUtil.AESJDKEncode(msg);
            vvvStr = vvvStr.substring(0, vvvStr.length() - 1);
            byte[] vvv = vvvStr.getBytes();
            CreateTextUtil.createFile(path, vvv);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
