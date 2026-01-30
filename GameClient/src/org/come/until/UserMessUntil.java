package org.come.until;

import com.tool.fuben.TaskListAll;
import com.tool.tcp.NewPart;
import java.util.HashMap;

import org.come.model.*;
import com.tool.ModerateTask.TaskSet;
import com.tool.ModerateTask.TaskData;
import org.come.bean.RoleTxBean;
import org.come.bean.RoleSuitBean;
import org.come.bean.BabyResult;
import java.util.ArrayList;
import org.come.bean.ColorScheme;
import org.come.bean.Talent;
import org.come.bean.Bbuy;
import org.come.bean.NpcInfoBean;
import org.come.entity.Goodstable;
import java.util.TreeMap;
import java.util.Comparator;
import org.come.Jpanel.TestPetJpanel;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.come.bean.AutoActiveBase;
import org.come.bean.AutoActiveReidsBase;
import java.math.BigDecimal;
import org.come.socket.DownLoadTxt;
import org.come.bean.AllAuctionGoodsExchange;
import java.awt.Color;
import org.come.bean.AutoActiveRedisBaseBean;
import org.come.MountShouHu.AllMountShouhu;
import org.come.bean.Skill;
import org.come.entity.Mount;
import org.come.bean.AutoActiveBaseBean;
import org.come.bean.ShaoXiangLimit;
import java.util.concurrent.ConcurrentHashMap;
import org.come.bean.QianDao;
import org.come.bean.ConfigureBean;
import org.come.bean.GMshopItemBean;
import org.come.bean.AllMeridians;
import org.come.bean.AllLianHua;
import org.come.bean.AllAchieve;
import org.come.bean.AllActive;
import org.come.bean.AllPal;
import com.tool.ModerateTask.AllTask;
import org.come.bean.AllEventModelBean;
import org.come.bean.AllTitleBean;
import org.come.bean.AllACard;
import java.util.Map;
import org.come.bean.AllGuide;
import org.come.bean.AllSuit;
import org.come.bean.AllTx;
import org.come.bean.AllColorScheme;
import org.come.bean.AllTalent;
import org.come.bean.AllBbuy;
import org.come.bean.AllDoorBean;
import org.come.bean.AllNpcBean;
import org.come.bean.RobotsBean;
import org.come.bean.GoodsBean;
import org.come.bean.AllBabyResult;
import org.come.bean.SkillBean;
import org.come.bean.RoleExpBean;
import org.come.bean.NpcShopBean;
import org.come.bean.AllMapBean;
import org.come.entity.Baby;
import org.come.entity.RoleSummoning;
import org.come.entity.Friendtable;
import org.come.entity.RewardHall;
import java.util.List;

public class UserMessUntil
{
    private static List<RewardHall> RewardHallArr;
    private static List<Friendtable> friendtables;
    private static List<RoleSummoning> petListTable;
    private static RoleSummoning ChosePetMes;
    private static List<String> petSkillslock;
    private static List<Baby> MyListBaby;
    private static String chatFriendName;
    private static AllMapBean allmapbean;
    private static NpcShopBean npcshop;
    private static RoleExpBean petAndPlayerExp;
    private static List<Eshop> eshops;
    private static SkillBean skillBean;
    private static AllBabyResult allBabyResult;
    private static GoodsBean goodsBean;
    private static RobotsBean robotBean;
    private static AllNpcBean allNpcBean;
    private static AllDoorBean allDoorBean;
    private static AllBbuy allBbuy;
    private static AllTalent allTalent;
    private static AllColorScheme allColorScheme;
    private static AllTx allTx;
    public static AllSuit allSuit;
    public static AllGuide allGuide;
    public static Map<String, String> allTYC;
    private static AllACard allACard;
    private static AllTitleBean allTitle;
    private static AllEventModelBean allEvent;
    private static AllTask allTask;
    private static AllPal allPal;
    private static allPetExchange allPetExchange;
    private static AllActive allActive;
    private static AllActive allVipActive;
    private static AllAchieve allAchieve;
    private static AllLianHua allLianHua;
    private static AllMeridians allMeridians;
    private static allGoodsExchange allGoodsExchange;
    private static allItemExchange allItemExchange;
    private static GMshopItemBean allGMshopItem;
    private static ConfigureBean allConfigure;
    private static List<QianDao> qianDaoList;
    private static ConcurrentHashMap<String, ShaoXiangLimit> allshaoxianglimit;
    private static List<String> allshaoxiang;
    private static AutoActiveBaseBean alltutoactive;
    private static List<Mount> mountList;

    private static AllAchievement allAchievement;
    public static Map<String, Skill> NameforSkill;
    private static AllMountShouhu allMountShouhu;
    private static AutoActiveRedisBaseBean autoActiveRedisBaseBean;
    private static ConcurrentHashMap<String, Color> titleColors;
    private static AllAuctionGoodsExchange allAuctionGoodsExchange;
    private static List<RoleSummoning> depositPetListTable;

    private static AllXuanbao xuanbaoAll;

    public static AllXuanbao getAllXuanbao() {

        if (xuanbaoAll == null) {
            DownLoadTxt.getDownLoadTxt().initMes("xuanbao.txt");
        }
        return xuanbaoAll;
    }

    public static void setAllXuanbao(AllXuanbao xuanbao) {
        UserMessUntil.xuanbaoAll = xuanbao;
    }
    public static Skill getskillnameforskillid(String name) {
        return (Skill)UserMessUntil.NameforSkill.get(name);
    }
    
    public static List<Mount> getMountlsit() {
        return UserMessUntil.mountList;
    }
    
    public static void setMountList(List<Mount> list) {
        UserMessUntil.mountList = list;
    }
    
    public static AllMountShouhu getAllMountShouhu() {
        if (UserMessUntil.allMountShouhu == null) {
            DownLoadTxt.getDownLoadTxt().initMes("shssx.txt");
        }
        return UserMessUntil.allMountShouhu;
    }
    
    public static void setAllMountShouhu(AllMountShouhu allMountShouhu) {
        UserMessUntil.allMountShouhu = allMountShouhu;
    }
    
    public static RoleSummoning removePetToRgid(BigDecimal rgid) {
        if (rgid == null) {
            return (RoleSummoning)UserMessUntil.petListTable.get(0);
        }
        for (int i = 0; i < UserMessUntil.petListTable.size(); ++i) {
            if (((RoleSummoning)UserMessUntil.petListTable.get(i)).getSid().compareTo(rgid) == 0) {
                return (RoleSummoning)UserMessUntil.petListTable.remove(i);
            }
        }
        return null;
    }
    
    public static AllAuctionGoodsExchange getAllAuctionGoodsExchange() {
        DownLoadTxt.getDownLoadTxt().initMes("auctionGoodsExchange.txt");
        return UserMessUntil.allAuctionGoodsExchange;
    }
    
    public static void setAllAuctionGoodsExchange(AllAuctionGoodsExchange allAuctionGoodsExchange) {
        UserMessUntil.allAuctionGoodsExchange = allAuctionGoodsExchange;
    }
    
    public static ConcurrentHashMap<String, Color> getTitleColors() {
        if (UserMessUntil.titleColors == null) {
            DownLoadTxt.getDownLoadTxt().initMes("titleColor.txt");
        }
        return UserMessUntil.titleColors;
    }
    
    public static Color getTitleColorsByName(String titleName) {
        return (Color)getTitleColors().get(titleName);
    }
    
    public static void setTitleColors(ConcurrentHashMap<String, Color> titleColors) {
        UserMessUntil.titleColors = titleColors;
    }
    
    public static AutoActiveRedisBaseBean getAutoActiveRedisBaseBean() {
        return UserMessUntil.autoActiveRedisBaseBean;
    }
    
    public static void setAutoActiveRedisBaseBean(AutoActiveRedisBaseBean autoActiveRedisBaseBean) {
        UserMessUntil.autoActiveRedisBaseBean = autoActiveRedisBaseBean;
    }
    
    public static AutoActiveBaseBean getAlltutoactive() {
        return UserMessUntil.alltutoactive;
    }
    
    public static void setAlltutoactive(AutoActiveBaseBean alltutoactive) {
        UserMessUntil.alltutoactive = alltutoactive;
    }
    
    public static void addAlltutoactive(AutoActiveReidsBase aarb) {
        if (UserMessUntil.alltutoactive != null && UserMessUntil.alltutoactive.getAllautobase() != null && UserMessUntil.alltutoactive.getAllautobase().size() > 0) {
            for (AutoActiveBase aab : UserMessUntil.alltutoactive.getAllautobase()) {
                if (aab.getGuide() != null) {
                    String guide = aab.getGuide();
                    String[] v = guide.split("\\|");
                    if (v.length >= 5 && Integer.parseInt(v[3]) == 0 && StringUtils.isNotBlank(v[4]) && v[4].startsWith("系统野怪设置")) {
                        String[] yg = v[4].split("=");
                        if (yg.length == 5 && aarb.getId() == aab.getId()) {
                            aab.setComNum(aarb.getComNum());
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
        }
    }
    
    public static List<String> getAllshaoxiang() {
        return UserMessUntil.allshaoxiang;
    }
    
    public static void setAllshaoxiang(List<String> allshaoxiang) {
        UserMessUntil.allshaoxiang = allshaoxiang;
    }
    
    public static ConcurrentHashMap<String, ShaoXiangLimit> getAllshaoxianglimit() {
        return UserMessUntil.allshaoxianglimit;
    }
    
    public static void setAllshaoxianglimit(ConcurrentHashMap<String, ShaoXiangLimit> allshaoxianglimit) {
        UserMessUntil.allshaoxianglimit = allshaoxianglimit;
    }
    
    public static Skill getskill1(String tmp) {
        for (String key : getSkillBean().getSkillMap().keySet()) {
            if (((Skill)getSkillBean().getSkillMap().get(key)).getSkillname().equals(tmp)) {
                return (Skill)getSkillBean().getSkillMap().get(key);
            }
        }
        return null;
    }
    
    public static void setQianDaoList(List<QianDao> qianDaoList) {
        UserMessUntil.qianDaoList = qianDaoList;
    }
    
    public static List<QianDao> getQiandaoBean() {
        if (UserMessUntil.qianDaoList == null) {
            DownLoadTxt.getDownLoadTxt().initMes("qiandao.txt");
        }
        return UserMessUntil.qianDaoList;
    }
    
    public static AllNpcBean getNpctable() {
        if (UserMessUntil.allNpcBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("npc.txt");
        }
        return UserMessUntil.allNpcBean;
    }
    
    public static String getChatFriendName() {
        return UserMessUntil.chatFriendName;
    }
    
    public static void setChatFriendName(String chatFriendName) {
        UserMessUntil.chatFriendName = chatFriendName;
    }
    
    public static RoleExpBean getPetAndPlayerExp() {
        return UserMessUntil.petAndPlayerExp;
    }
    
    public static RoleSummoning getChosePetMes() {
        return UserMessUntil.ChosePetMes;
    }
    
    public static Skill getSkillId(String id) {
        return (Skill)getSkillBean().getSkillMap().get(id);
    }
    
    public static SkillBean getSkillBean() {
        if (UserMessUntil.skillBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("skill.txt");
        }
        return UserMessUntil.skillBean;
    }
    
    public static void setChosePetMes(RoleSummoning chosePetMes) {
        UserMessUntil.ChosePetMes = chosePetMes;
        TestPetJpanel.part = null;
    }
    
    public static void unSetChosePetMes() {
        UserMessUntil.ChosePetMes = null;
        TestPetJpanel.part = null;
    }
    
    public static List<Eshop> getEshops() {
        if (UserMessUntil.eshops == null) {
            DownLoadTxt.getDownLoadTxt().initMes("eshop.txt");
        }
        return UserMessUntil.eshops;
    }
    
    public static void setEshops(List<Eshop> eshops) {
        UserMessUntil.eshops = eshops;
    }
    
    public static AllMapBean getAllmapbean() {
        if (UserMessUntil.allmapbean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("map.txt");
        }
        return UserMessUntil.allmapbean;
    }
    
    public static void setAllmapbean(AllMapBean allmapbean) {
        UserMessUntil.allmapbean = allmapbean;
    }
    
    public static NpcShopBean getNpcshop() {
        if (UserMessUntil.npcshop == null) {
            DownLoadTxt.getDownLoadTxt().initMes("npcshop.txt");
        }
        return UserMessUntil.npcshop;
    }
    
    public static void setNpcshop(NpcShopBean npcshop) {
        UserMessUntil.npcshop = npcshop;
    }
    
    public static RoleExpBean getExp() {
        if (UserMessUntil.petAndPlayerExp == null) {
            DownLoadTxt.getDownLoadTxt().initMes("exp.txt");
        }
        return UserMessUntil.petAndPlayerExp;
    }
    
    public static void setPetAndPlayerExp(RoleExpBean petAndPlayerExp) {
        UserMessUntil.petAndPlayerExp = petAndPlayerExp;
    }
    
    public static List<Friendtable> getFriendtables() {
        return UserMessUntil.friendtables;
    }
    
    public static void setFriendtables(List<Friendtable> friendtables) {
        UserMessUntil.friendtables = friendtables;
    }
    
    public static List<RoleSummoning> getPetListTable() {
        return UserMessUntil.petListTable;
    }
    
    public static RoleSummoning getPetRgid(BigDecimal rgid) {
        if (rgid == null) {
            return (RoleSummoning)UserMessUntil.petListTable.get(0);
        }
        for (int i = 0; i < UserMessUntil.petListTable.size(); ++i) {
            if (((RoleSummoning)UserMessUntil.petListTable.get(i)).getSid().compareTo(rgid) == 0) {
                return (RoleSummoning)UserMessUntil.petListTable.get(i);
            }
        }
        return null;
    }
    
    public static void setPetListTable(List<RoleSummoning> petListTable) {
        UserMessUntil.petListTable = petListTable;
    }
    
    public static Baby getbaby(BigDecimal babyid) {
        if (UserMessUntil.MyListBaby == null) {
            return null;
        }
        if (babyid == null) {
            return null;
        }
        for (int i = UserMessUntil.MyListBaby.size() - 1; i >= 0; --i) {
            if (((Baby)UserMessUntil.MyListBaby.get(i)).getBabyid().compareTo(babyid) == 0) {
                return (Baby)UserMessUntil.MyListBaby.get(i);
            }
        }
        return null;
    }
    
    public static List<Baby> getMyListBaby() {
        return UserMessUntil.MyListBaby;
    }
    
    public static void setMyListBaby(List<Baby> myListBaby) {
        UserMessUntil.MyListBaby = myListBaby;
    }
    
    public static GoodsBean getGoodsBean() {
        if (UserMessUntil.goodsBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("goods.txt");
        }
        return UserMessUntil.goodsBean;
    }
    
    public static void main(String[] args) {
        Map<BigDecimal, Goodstable> map = getGoodsBean().getAllGoodsMap();
        Map<BigDecimal, Goodstable> sortMap = new TreeMap(new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal str1, BigDecimal str2) {
                return str1.compareTo(str2);
            }
        });
        sortMap.putAll(map);
        sortMap.forEach((key, value)/* java.math.BigDecimal,org.come.entity.Goodstable, */ -> {
            if (key.compareTo(new BigDecimal(745)) == 0) {
                System.out.println(1111);
            }
            if (key.compareTo(new BigDecimal(10080)) == 0) {
                System.out.println(1111);
            }
            System.out.println(key + "-" + value.getGoodsname() + "-");
            return;
        });
    }
    
    public static Goodstable getgoodstable(BigDecimal bigDecimal) {
        getGoodsBean();
        Goodstable goodstable = (Goodstable)UserMessUntil.goodsBean.getAllGoodsMap().get(bigDecimal);
        return goodstable;
    }
    
    public static void setGoodsBean(GoodsBean goodsBean) {
        UserMessUntil.goodsBean = goodsBean;
    }
    
    public static void setSkillBean(SkillBean skillBean) {
        UserMessUntil.skillBean = skillBean;
    }
    
    public static RobotsBean getRobotBean() {
        if (UserMessUntil.robotBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("robots.txt");
        }
        return UserMessUntil.robotBean;
    }
    
    public static Robots getRobot(String type) {
        getRobotBean();
        return (Robots)UserMessUntil.robotBean.getRobotsMap().get(type);
    }
    
    public static void setRobotBean(RobotsBean robotBean) {
        UserMessUntil.robotBean = robotBean;
    }
    
    public static NpcInfoBean getnpc(String type) {
        getAllNpcBean();
        return (NpcInfoBean)UserMessUntil.allNpcBean.getAllNpcInfo().get(type);
    }
    
    public static AllNpcBean getAllNpcBean() {
        if (UserMessUntil.allNpcBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("npc.txt");
        }
        return UserMessUntil.allNpcBean;
    }
    
    public static void setAllNpcBean(AllNpcBean allNpcBean) {
        UserMessUntil.allNpcBean = allNpcBean;
    }
    
    public static Door getDoor(String type) {
        getAllDoorBean();
        return (Door)UserMessUntil.allDoorBean.getAlldoor().get(type);
    }
    
    public static AllDoorBean getAllDoorBean() {
        if (UserMessUntil.allDoorBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("door.txt");
        }
        return UserMessUntil.allDoorBean;
    }
    
    public static void setAllDoorBean(AllDoorBean allDoorBean) {
        UserMessUntil.allDoorBean = allDoorBean;
    }
    
    public static Bbuy getBbuy(BigDecimal goodsid) {
        getAllBbuy();
        return (Bbuy)UserMessUntil.allBbuy.getAllbbuy().get(Integer.valueOf(goodsid.intValue()));
    }
    
    public static AllBbuy getAllBbuy() {
        if (UserMessUntil.allBbuy == null) {
            DownLoadTxt.getDownLoadTxt().initMes("bbuy.txt");
        }
        return UserMessUntil.allBbuy;
    }
    
    public static void setAllBbuy(AllBbuy allBbuy) {
        UserMessUntil.allBbuy = allBbuy;
    }
    
    public static Talent getTalent(int id) {
        getAllTalent();
        return (Talent)UserMessUntil.allTalent.getAllTalent().get(Integer.valueOf(id));
    }
    
    public static AllTalent getAllTalent() {
        if (UserMessUntil.allTalent == null) {
            DownLoadTxt.getDownLoadTxt().initMes("talent.txt");
        }
        return UserMessUntil.allTalent;
    }
    
    public static void setAllTalent(AllTalent allTalent) {
        UserMessUntil.allTalent = allTalent;
    }
    
    public static ColorScheme getColor(int id) {
        getAllColorScheme();
        return (ColorScheme)UserMessUntil.allColorScheme.getAllMap().get(Integer.valueOf(id));
    }
    
    public static ColorScheme getColor(String color) {
        getAllColorScheme();
        for (ColorScheme value : UserMessUntil.allColorScheme.getAllMap().values()) {
            if (value.getName().equals(color)) {
                return value;
            }
        }
        return null;
    }
    
    public static ColorScheme getColors(int type) {
        getAllColorScheme();
        List<Integer> a = new ArrayList<>();
        for (ColorScheme value : UserMessUntil.allColorScheme.getAllMap().values()) {
            if (value.getZid() == 0 || value.getZid() == type) {
                a.add(Integer.valueOf(value.getId()));
            }
        }
        if (a.size() == 0) {
            return null;
        }
        return (ColorScheme)UserMessUntil.allColorScheme.getAllMap().get(a.get(Util.random.nextInt(a.size())));
    }
    
    public static AllColorScheme getAllColorScheme() {
        if (UserMessUntil.allColorScheme == null) {
            DownLoadTxt.getDownLoadTxt().initMes("color.txt");
        }
        return UserMessUntil.allColorScheme;
    }
    
    public static void setAllColorScheme(AllColorScheme allColorScheme) {
        UserMessUntil.allColorScheme = allColorScheme;
    }
    
    public static BabyResult getBabyResult(String name) {
        getAllBabyResult();
        for (int i = UserMessUntil.allBabyResult.getAllBabyResults().size() - 1; i >= 0; --i) {
            BabyResult babyResult = (BabyResult)UserMessUntil.allBabyResult.getAllBabyResults().get(i);
            if (babyResult.getNan().equals(name)) {
                return babyResult;
            }
            if (babyResult.getNv().equals(name)) {
                return babyResult;
            }
        }
        return (BabyResult)UserMessUntil.allBabyResult.getAllBabyResults().get(0);
    }
    
    public static AllBabyResult getAllBabyResult() {
        if (UserMessUntil.allBabyResult == null) {
            DownLoadTxt.getDownLoadTxt().initMes("babyresult.txt");
        }
        return UserMessUntil.allBabyResult;
    }
    
    public static void setAllBabyResult(AllBabyResult allBabyResult) {
        UserMessUntil.allBabyResult = allBabyResult;
    }
    
    public static List<RewardHall> getRewardHallArr() {
        return UserMessUntil.RewardHallArr;
    }
    
    public static void setRewardHallArr(List<RewardHall> rewardHallArr) {
        UserMessUntil.RewardHallArr = rewardHallArr;
    }
    
    public static AllSuit getAllSuit() {
        if (UserMessUntil.allSuit == null) {
            DownLoadTxt.getDownLoadTxt().initMes("suit.txt");
        }
        return UserMessUntil.allSuit;
    }
    
    public static RoleSuitBean getSuit(int suid) {
        getAllSuit();
        return (RoleSuitBean)UserMessUntil.allSuit.getRolesuit().get(Integer.valueOf(suid));
    }
    
    public static void setAllSuit(AllSuit allSuit) {
        UserMessUntil.allSuit = allSuit;
    }
    
    public static AllGuide getAllGuide() {
        if (UserMessUntil.allGuide == null) {
            DownLoadTxt.getDownLoadTxt().initMes("guide.txt");
        }
        return UserMessUntil.allGuide;
    }
    
    public static void setAllGuide(AllGuide allGuide) {
        UserMessUntil.allGuide = allGuide;
    }
    
    public static RoleTxBean getTxBean(int id) {
        getAllTx();
        return (RoleTxBean)UserMessUntil.allTx.getTxmap().get(Integer.valueOf(id));
    }
    
    public static AllTx getAllTx() {
        if (UserMessUntil.allTx == null) {
            DownLoadTxt.getDownLoadTxt().initMes("tx.txt");
        }
        return UserMessUntil.allTx;
    }
    
    public static void setAllTx(AllTx allTx) {
        UserMessUntil.allTx = allTx;
    }
    
    public static String getTYC(String type) {
        return (String)getAllTYC().get(type);
    }
    
    public static Map<String, String> getAllTYC() {
        if (UserMessUntil.allTYC == null) {
            DownLoadTxt.getDownLoadTxt().initMes("tyc.txt");
        }
        return UserMessUntil.allTYC;
    }
    
    public static void setAllTYC(Map<String, String> allTYC) {
        UserMessUntil.allTYC = allTYC;
    }
    
    public static aCard getACard(int id) {
        return (aCard)getaCardMap().get(Integer.valueOf(id));
    }
    
    public static Map<Integer, aCard> getaCardMap() {
        if (UserMessUntil.allACard == null) {
            DownLoadTxt.getDownLoadTxt().initMes("acard.txt");
        }
        return UserMessUntil.allACard.getaMap();
    }
    
    public static void setAllACard(AllACard allACard) {
        UserMessUntil.allACard = allACard;
    }
    
    public static Title getTitle(String titleName) {
        return (Title)getAllTitle().getTitleMap().get(titleName);
    }
    
    public static AllTitleBean getAllTitle() {
        if (UserMessUntil.allTitle == null) {
            DownLoadTxt.getDownLoadTxt().initMes("title.txt");
        }
        return UserMessUntil.allTitle;
    }
    
    public static void setAllTitle(AllTitleBean allTitle) {
        UserMessUntil.allTitle = allTitle;
    }
    
    public static AllEventModelBean getAllEventMap() {
        if (UserMessUntil.allEvent == null) {
            DownLoadTxt.getDownLoadTxt().initMes("event.txt");
        }
        return UserMessUntil.allEvent;
    }
    
    public static EventModel getEventModel(Integer gId) {
        return (EventModel)getAllEventMap().getAllEventModelMap().get(gId);
    }
    
    public static void setAllEvent(AllEventModelBean allEvent) {
        UserMessUntil.allEvent = allEvent;
    }
    
    public static TaskData getTaskData(int taskId) {
        if (UserMessUntil.allTask == null) {
            DownLoadTxt.getDownLoadTxt().initMes("task.txt");
        }
        return (TaskData)UserMessUntil.allTask.getAllTaskData().get(Integer.valueOf(taskId));
    }
    
    public static TaskSet getTaskSet(int taskSetId) {
        if (UserMessUntil.allTask == null) {
            DownLoadTxt.getDownLoadTxt().initMes("task.txt");
        }
        return (TaskSet)UserMessUntil.allTask.getAllTaskSet().get(Integer.valueOf(taskSetId));
    }
    
    public static AllTask getAllTask() {
        if (UserMessUntil.allTask == null) {
            DownLoadTxt.getDownLoadTxt().initMes("task.txt");
        }
        return UserMessUntil.allTask;
    }
    
    public static void setAllTask(AllTask allTask) {
        UserMessUntil.allTask = allTask;
    }
    
    public static PalData getPalData(int id) {
        return (PalData)getAllPal().getAllPalData().get(Integer.valueOf(id));
    }
    
    public static AllPal getAllPal() {
        if (UserMessUntil.allPal == null) {
            DownLoadTxt.getDownLoadTxt().initMes("pal.txt");
        }
        return UserMessUntil.allPal;
    }
    
    public static void setAllPal(AllPal allPal) {
        UserMessUntil.allPal = allPal;
    }
    
    public static allPetExchange getAllPetExchange() {
        if (UserMessUntil.allPetExchange == null) {
            DownLoadTxt.getDownLoadTxt().initMes("petExchange.txt");
        }
        return UserMessUntil.allPetExchange;
    }
    
    public static petExchange getPetExchange(Integer eid) {
        return (petExchange)getAllPetExchange().getAllPetExchange().get(eid);
    }
    
    public static void setAllPetExchange(allPetExchange allPetExchange) {
        UserMessUntil.allPetExchange = allPetExchange;
    }
    
    public static allItemExchange getAllItemExchange() {
        DownLoadTxt.getDownLoadTxt().initMes("itemExchange.txt");
        return UserMessUntil.allItemExchange;
    }
    
    public static ItemExchange getItemExchange(Integer eid) {
        return (ItemExchange)getAllItemExchange().getAllItemExchange().get(eid);
    }
    
    public static void setAllItemExchange(allItemExchange allItemExchange) {
        UserMessUntil.allItemExchange = allItemExchange;
    }
    
    public static allGoodsExchange getAllGoodsExchange() {
        if (UserMessUntil.allPetExchange == null) {
            DownLoadTxt.getDownLoadTxt().initMes("goodsExchange.txt");
        }
        return UserMessUntil.allGoodsExchange;
    }
    
    public static GoodsExchange getGoodsExchange(Integer eid) {
        return (GoodsExchange)getAllGoodsExchange().getAllGoodsExchange().get(eid);
    }
    
    public static void setAllGoodsExchange(allGoodsExchange allGoodsExchange) {
        UserMessUntil.allGoodsExchange = allGoodsExchange;
    }
    
    public static AllActive getAllActive() {
        if (UserMessUntil.allActive == null) {
            DownLoadTxt.getDownLoadTxt().initMes("active.txt");
        }
        return UserMessUntil.allActive;
    }
    
    public static void setAllActive(AllActive allActive) {
        UserMessUntil.allActive = allActive;
    }
    
    public static AllAchieve getAllAchieve() {
        if (UserMessUntil.allAchieve == null) {
            DownLoadTxt.getDownLoadTxt().initMes("achieve.txt");
        }
        return UserMessUntil.allAchieve;
    }
    
    public static AllActive getAllVipActive() {
        if (UserMessUntil.allVipActive == null) {
            DownLoadTxt.getDownLoadTxt().initMes("vipActive.txt");
        }
        return UserMessUntil.allVipActive;
    }
    
    public static void setAllVipActive(AllActive allVipActive) {
        UserMessUntil.allVipActive = allVipActive;
    }
    
    public static void setAllAchieve(AllAchieve allAchieve) {
        UserMessUntil.allAchieve = allAchieve;
    }
    
    public static AllLianHua getAllLianHua() {
        if (UserMessUntil.allLianHua == null) {
            DownLoadTxt.getDownLoadTxt().initMes("lh.txt");
        }
        return UserMessUntil.allLianHua;
    }
    
    public static void setAllLianHua(AllLianHua all) {
        UserMessUntil.allLianHua = all;
    }
    
    public static AllMeridians getAllMeridians() {
        if (UserMessUntil.allMeridians == null) {
            DownLoadTxt.getDownLoadTxt().initMes("meridians.txt");
        }
        return UserMessUntil.allMeridians;
    }
    
    public static void setAllMeridians(AllMeridians allMeridians) {
        UserMessUntil.allMeridians = allMeridians;
    }
    
    public static GMshopItemBean getAllGMshopItem() {
        if (UserMessUntil.allGMshopItem == null) {
            DownLoadTxt.getDownLoadTxt().initMes("GMshopItem.txt");
        }
        GMshopItemBean bean = UserMessUntil.allGMshopItem;
        return bean;
    }
    
    public static void setAllGMshopItem(GMshopItemBean allGMshopItem) {
        UserMessUntil.allGMshopItem = allGMshopItem;
    }
    
    public static ConfigureBean getConfigureBean() {
        if (UserMessUntil.allConfigure == null) {
            DownLoadTxt.getDownLoadTxt().initMes("configure.txt");
        }
        ConfigureBean bean = UserMessUntil.allConfigure;
        return bean;
    }
    
    public static AutoActiveBaseBean getAllAutoActive() {
        if (UserMessUntil.alltutoactive == null) {
            DownLoadTxt.getDownLoadTxt().initMes("autoactive.txt");
        }
        return UserMessUntil.alltutoactive;
    }
    
    public static void setConfigureBean(ConfigureBean allConfigure) {
        UserMessUntil.allConfigure = allConfigure;
    }
    
    public static List<String> getPetSkillslock() {
        return UserMessUntil.petSkillslock;
    }
    
    public static void setPetSkillslock(List<String> petSkillslock) {
        UserMessUntil.petSkillslock = petSkillslock;
    }
    
    public static Skill getSkillById(String tmp) {
        for (String key : getSkillBean().getSkillMap().keySet()) {
            if (((Skill)getSkillBean().getSkillMap().get(key)).getSkillid().equals(tmp)) {
                return (Skill)getSkillBean().getSkillMap().get(key);
            }
        }
        return null;
    }
    
    public static List<RoleSummoning> getDepositPetListTable() {
        return UserMessUntil.depositPetListTable;
    }
    
    public static void setDepositPetListTable(List<RoleSummoning> depositPetListTable) {
        UserMessUntil.depositPetListTable = depositPetListTable;
    }

    public static Achievement getAchievement(int id) {
        return UserMessUntil.allAchievement.getAllAchievement().get(id);
    }
    public static AllAchievement getAllAchievement() {
        if (UserMessUntil.allAchievement == null) {
            DownLoadTxt.getDownLoadTxt().initMes("achievement.txt");
        }
        return UserMessUntil.allAchievement;
    }
    public static void setAllAchievement(final AllAchievement allAchievement) {
        UserMessUntil.allAchievement = allAchievement;
    }

    static {
        UserMessUntil.mountList = new ArrayList<>();
        UserMessUntil.NameforSkill = new HashMap<>();
        UserMessUntil.depositPetListTable = new ArrayList<>();
        UserMessUntil.RewardHallArr = new ArrayList<>();
        UserMessUntil.friendtables = new ArrayList<>();
        UserMessUntil.petListTable = new ArrayList<>();
    }
    private static TaskListAll taskListAll;
    public static TaskListAll getTaskListAll() {
        if (taskListAll == null) {
            DownLoadTxt.getDownLoadTxt().initMes("tasklist.txt");
//            FuBenJframe.getFuBenJframe().getFuBenJpane().init();
        }
        return taskListAll;
    }
    public static void setTaskListAll(TaskListAll taskListAll) {
        UserMessUntil.taskListAll = taskListAll;
    }

    public static Skill getskillformname(String skillnema) {
        if (skillBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("skill.txt");
        }
        for (Skill key : skillBean.getSkillMap().values()) {
            if (!key.getSkillname().equals(skillnema)) {
                continue;
            }
            return key;
        }
        return null;
    }
}
