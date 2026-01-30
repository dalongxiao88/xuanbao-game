package org.come.server;

import java.util.stream.Collectors;
import java.util.Arrays;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import org.come.entity.*;
import org.come.model.*;

import org.come.redis.*;
import sun.misc.BASE64Decoder;
import java.security.PublicKey;
import java.util.Scanner;
//import org.come.model.GoodsExchange;
//import org.come.model.ActiveAward;
//import org.come.model.ActiveBase;
import come.tool.Role.RoleCard;
import org.come.action.lottery.EventRanking;
import java.util.HashMap;
import java.util.ArrayList;
//import org.come.model.GemBase;
import org.come.bean.PathPoint;
import org.come.action.suit.SuitPalEquip;
import org.come.action.suit.StarCard;
import org.come.action.suit.SuitPetEquip;
import org.come.tool.Goodtype;
import org.come.action.suit.WingCompose;
import org.come.action.suit.SuitComposeAction;
import org.come.until.SplitFushiValue;
import come.tool.FightingData.Battlefield;
import org.come.tool.SplitStringTool;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamBean;
import come.tool.BangBattle.Member;
import come.tool.BangBattle.BangFight;
import java.util.Date;
//import org.come.entity.UserTable;
import org.come.until.TimeUntil;
import come.tool.newTeam.TeamUtil;
import come.tool.BangBattle.Build;
import come.tool.newTrans.TransUtil;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.action.IAction;
import org.come.bean.ChangeMapBean;
import come.tool.newGang.GangDomain;
import org.come.action.exchange.ExchangeUtil;
import come.tool.FightingDataAction.PhyAttack;
import org.come.protocol.ParamTool;
import org.come.until.ReadTxtUtil;
//import org.come.entity.RewardHallExample;
import org.come.readUtil.ReadPoolUtil;
import org.come.until.AllServiceUtil;
import come.tool.Scene.Scene;
import come.tool.Role.RoleData;
import java.util.Iterator;
import come.tool.Scene.JP.JPScene;
import come.tool.Scene.PKLS.lsteamBean;
import come.tool.Scene.PKLS.PKLSScene;
import come.tool.Scene.RC.RCScene;
import org.come.until.CreateTextUtil;
import org.come.action.festival.HatchvalueAction;
import org.come.tool.ReadExelTool;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.tool.WriteOut;
import org.come.thread.RedisEqualWithSqlThread;
import org.come.handler.SendMessage;
import come.tool.Role.RolePool;
import come.tool.newGang.GangUtil;
import come.tool.BangBattle.BangFileSystem;
import org.come.action.monitor.MonitorUtil;
import come.tool.Scene.LTS.LTSUtil;
import come.tool.Stall.StallPool;
import java.io.InputStream;
import come.tool.Scene.SceneUtil;
import org.come.task.RefreshMonsterTask;
import come.tool.BangBattle.BangBattlePool;
import java.io.File;
import org.come.handler.MainServerHandler;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import java.time.LocalDateTime;
import org.apache.commons.lang.StringUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import org.come.serverInitializer.MainServerInitializer;
import java.net.InetSocketAddress;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
//import org.come.model.GolemActive;
//import org.come.model.GolemDraw;
//import org.come.model.Configure;
import java.util.Random;
//import org.come.model.GMshopItem;
//import org.come.model.QianDao;
import org.come.readUtil.MountShouhu;
//import org.come.model.ShaoXiang;
import org.come.bean.StallPurchase;
import org.come.readBean.AllMeridians;
import org.come.readBean.AllLianHua;
//import org.come.model.Achieve;
import org.come.readBean.AllActive;
//import org.come.model.WitchComposeAttr;
//import org.come.model.PetExchange;
//import org.come.model.PalEquip;
//import org.come.model.PalData;
import come.tool.Scene.DNTG.DNTGAward;
//import org.come.entity.PayvipBean;
//import org.come.entity.StarPalace;
//import org.come.entity.WingTraining;
//import org.come.model.EventModel;
//import org.come.model.LotteryItemBasetwo;
//import org.come.model.LotteryItemBase;
//import org.come.model.SellLiangHaoBase;
//import org.come.model.LiangHaoBase;
//import org.come.model.Title;
//import org.come.model.aCard;
import org.come.action.lottery.Draw;
//import org.come.model.Lshop;
//import org.come.model.ItemExchange;
//import org.come.model.Talent;
//import org.come.model.ColorScheme;
//import org.come.model.TeJiLH;
//import org.come.entity.Keju;
//import org.come.entity.Fly;
//import org.come.entity.Mount;
//import org.come.model.Gem;
//import org.come.entity.Present;
import org.come.bean.RoleTxBean;
//import org.come.entity.Suit;
import org.come.bean.Bbuy;
//import org.come.model.Shop;
//import org.come.model.Eshop;
//import org.come.entity.RewardHall;
import java.util.concurrent.CopyOnWriteArrayList;
import org.come.model.Boos;
import java.util.concurrent.atomic.AtomicInteger;
//import org.come.model.Dorp;
//import org.come.entity.Lingbao;
//import org.come.entity.RoleSummoning;
//import org.come.model.GodStone;
//import org.come.model.TaskSet;
//import org.come.model.TaskData;
//import org.come.model.Skill;
//import org.come.model.Xianqi;
//import org.come.model.Alchemy;
//import org.come.model.Newequip;
//import org.come.model.Robots;
//import org.come.model.Sghostpoint;
//import org.come.model.Decorate;
//import org.come.model.Monster;
//import org.come.model.Door;
//import org.come.model.Npctable;
//import org.come.model.Gamemap;
//import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.readBean.AllAuctionGoodsExchange;
import org.come.action.wqx.KJexamQuestions;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Map;
//import org.come.entity.ChongjipackBean;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import javax.servlet.ServletContextListener;

@Service
public class GameServer implements ServletContextListener
{
    public static String time;
    public static String signNum;
    private static int qh;
    private static String gameServerPay;
    private static int portNumber;
    private static ConcurrentHashMap<Integer, List<ChongjipackBean>> packgradecontrol;
    private static ConcurrentHashMap<Integer, ChongjipackBean> packgradecontrols;
    public static int registerOnOff;
    public static int redisReset;
    public static int lianhua;
    public static String redisIp;
    public static int redisPort;
    public static String QZ;
    public static boolean isCode;
    public static String tablePath;
    public static String zhifu;
    public static Map<String, String> tableZone;
    private static final long serialVersionUID = 1L;
    private static ConcurrentHashMap<ChannelHandlerContext, LoginResult> allLoginRole;
    private static ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions;
    private static ConcurrentHashMap<Long, ConcurrentHashMap<String, ChannelHandlerContext>> mapRolesMap;
    private static ConcurrentHashMap<String, ChannelHandlerContext> roleNameMap;
    private static ConcurrentHashMap<String, ChannelHandlerContext> inlineUserNameMap;
    private static ConcurrentHashMap<ChannelHandlerContext, String> socketUserNameMap;
    private static AllAuctionGoodsExchange allAuctionGoodsExchange;
    private static ConcurrentHashMap<BigDecimal, Goodstable> allitem;
    private static ConcurrentHashMap<String, Gamemap> gameMap;
    private static ConcurrentHashMap<String, Npctable> npcMap;
    private static ConcurrentHashMap<Integer, Door> doorMap;
    private static ConcurrentHashMap<String, Monster> monsterMap;
    private static ConcurrentHashMap<BigDecimal, Goodstable> allGoodsMap;
    private static ConcurrentHashMap<String, Goodstable> getGoods;
    private static ConcurrentHashMap<String, List<Decorate>> allDecorate;
    private static ConcurrentHashMap<String, List<Sghostpoint>> monsterpointMap;
    private static ConcurrentHashMap<String, Robots> allRobot;
    private static ConcurrentHashMap<String, List<Newequip>> sameNewequipMap;
    private static ConcurrentHashMap<String, List<Newequip>> witchNewequipMap;
    private static ConcurrentHashMap<String, List<Alchemy>> allAlchemy;
    private static ConcurrentHashMap<String, List<Xianqi>> getAllXianqi;
    private static ConcurrentHashMap<String, List<String>> XianqiTypeValue;
    private static ConcurrentHashMap<BigDecimal, Goodstable> allLingbaoFushi;
    private static ConcurrentHashMap<String, Skill> getSkill;
    private static ConcurrentHashMap<Integer, TaskData> allTaskData;
    private static ConcurrentHashMap<Integer, TaskSet> allTaskSet;
    public static List<TaskData> taskData;
    private static ConcurrentHashMap<String, List<GodStone>> godMap;
    private static ConcurrentHashMap<BigDecimal, RoleSummoning> allPet;
    private static ConcurrentHashMap<String, Lingbao> allLingbao;
    private static ConcurrentHashMap<String, Dorp> allDorp;
    public static AtomicInteger inlineNum;
    public static int inlineMax;
    private static String TXTPATH;
    public static ConcurrentHashMap<String, Boos> boosesMap;
    public static CopyOnWriteArrayList<RewardHall> rewardList;
    public static ConcurrentHashMap<Integer, List<LoginResult>> allBangList;
    private static ConcurrentHashMap<String, Eshop> allEshopGoods;
    private static ConcurrentHashMap<String, Shop> allShopGoods;
    private static ConcurrentHashMap<Integer, Bbuy> allBbuys;
    private static List<Bbuy> bbuyList;
    private static ConcurrentHashMap<Integer, Suit> allSuits;
    private static ConcurrentHashMap<Integer, RoleTxBean> allTXs;
    private static List<Present> presents;
    private static ConcurrentHashMap<String, Gem> gems;
    private static ConcurrentHashMap<Integer, Long> expMap;
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Mount>> allMount;
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Car>> allCar;
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Fly>> allFly;
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Keju>> allkeju;
    private static ConcurrentHashMap<Integer, TeJiLH> alllhtj;
    private static ConcurrentHashMap<String, ColorScheme> allColor;
    private static List<ColorScheme> allListColor;
    private static ConcurrentHashMap<Integer, Talent> alltalent;
    private static ConcurrentHashMap<Integer, ItemExchange> allItemExchange;
    private static ConcurrentHashMap<String, Lshop> allLShopGoods;
    private static ConcurrentHashMap<Integer, Draw> allDraws;
    private static ConcurrentHashMap<Integer, aCard> allACard;
    private static ConcurrentHashMap<String, Title> alltitle;
    private static ConcurrentHashMap<String, String> alltitleColor;
    private static List<Title> moneyTitles;
    private static List<LiangHaoBase> lianghaos;
    private static List<SellLiangHaoBase> selllianghaos;
    private static List<SellLiangHaoBase> upselllianghaos;
    private static List<LotteryItemBase> lotteryitems;
    private static List<LotteryItemBasetwo> lotteryitemstwo;
    private static ConcurrentHashMap<Integer, EventModel> allevent;
    private static ConcurrentHashMap<Long, WingTraining> allWingTraining;
    private static ConcurrentHashMap<String, StarPalace> allStarPalace;
    private static List<PayvipBean> payvipList;
    private static String loginCheck;
    private static ConcurrentHashMap<Integer, DNTGAward> allDntg;
    private static ConcurrentHashMap<Integer, PalData> allPalData;
    private static ConcurrentHashMap<Long, PalEquip> allPalEquip;
    private static ConcurrentHashMap<Integer, PetExchange> allPetExchange;
    private static ConcurrentHashMap<String, List<WitchComposeAttr>> allSwitchAttr;
    private static AllActive allActive;
    private static ConcurrentHashMap allGoodsExchange;
    private static ConcurrentHashMap<Integer, Achieve> allAchieve;
    private static AllLianHua allLianHua;
    private static String[] allStarPalaceKey;
    private static AllMeridians allMeridians;
    private static ConcurrentHashMap<Integer, Achievement> allAchievement;
    private static ConcurrentHashMap<String, List<String>> goodsByRobot;
    private static ConcurrentHashMap<BigDecimal, List<String>> applyList;
    private static BigDecimal teamID;
    public static AtomicInteger phoneinlineNum;
    public static Map<String, String> xjDate;
    public static Map<String, AtomicInteger> atPeopleNum;
    public static ConcurrentHashMap<Integer, List<String>> matchTeams;
    private static ConcurrentHashMap<BigDecimal, StallPurchase> stallPurchases;
    private static ConcurrentHashMap<String, ShaoXiang> allShaoXiang;
    private static List<MountShouhu> allmountshouhu;
    public static String area;
    public static GameServer gameServer;
    public static GolemServer golemServer;
    private static ConcurrentHashMap<Integer, QianDao> qianDaoMap;
    private static ConcurrentHashMap<Integer, GMshopItem> gMshopItemMap;
    public static boolean OPEN;
    public static Object userLock;
    public static Random random;
    private static ConcurrentHashMap<Integer, Configure> allConfigure;
    static String[] gemns;
    public static final String publicKeyStr = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwA5bvsBpZkn6uNFgBdxT\r\ng939ldFNJ3B+K7WqhXxWVsLZj64cUKDAnm7WPkRgdrfqp7rfgfdCeGHAaSd2/j4P\r\nx4ywotMCuVIcjxIZTwS+pSZMaNLt9JC5qygYoiPXV4U2GsOA2UfMiLcoO04yfAV4\r\n/vACGNNLhonZ2Y/0q58g32F3VDmNmD7jSrcHREWB3n87qGgxgGCh8LCXaVjpEFoo\r\nfO3j/MsMlyXbs0gZseFzS8JjDB+v6VImL1eu6amW82bfEVXt6eKcrWNpPRXFAac5\r\n031X9ppcrCXh0W2OB+4qtp8Fuu8Z8aEtA91BFmne2reDcrfJEnRmj0S1CK+tzYwG\r\nDdhlY5bzPMkvuta6Lpt8wF/N3yhI6Sgs9P6K5rlBJCrI6iIQsSchyZJEjKir1lWv\r\n11Icu575q0q36JhDwmtT9Op/5xhAZykvh28eNPigd/VcxkwykE0ekN06ElsDDs+X\r\nk+uzO0Uo/ReX7j3kyUGla6h9L5bpS+7M4gx/aQcYCbBXPqxv6+eeHErj2AoG5H8R\r\nv3UmlKX3kvLpLYV2UG6ub5B9nCRJPC98S/RTZN8Jes8tUg54N6/9PdJndwdCQsUv\r\ne1hDvynUKu9/J6YNGqiS2efvHOCL+HFk3l6VAywZRtUxuzDh8gY+U2MiCrMqlRA/\r\n8/Ad1wL8I+LO2E+EF9zPgcMCAwEAAQ==";
    private static ConcurrentHashMap<String, List<GolemDraw>> allGolemDraw;
    private static List<GolemActive> golemActives;
    private static GolemConfig golemConfig;
    
    public static ConcurrentHashMap<Integer, KJexamQuestions> getGetAllKJexamQuestions() {
        return GameServer.getAllKJexamQuestions;
    }
    
    public static void setGetAllKJexamQuestions(ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions) {
        GameServer.getAllKJexamQuestions = getAllKJexamQuestions;
    }
    
    public static ConcurrentHashMap<String, ShaoXiang> getAllShaoXiang() {
        return GameServer.allShaoXiang;
    }
    
    public static void setAllShaoXiang(ConcurrentHashMap<String, ShaoXiang> allShaoXiang) {
        GameServer.allShaoXiang = allShaoXiang;
    }
    
    public static ConcurrentHashMap<String, List<String>> getGoodsByRobot() {
        return GameServer.goodsByRobot;
    }
    
    public static void setGoodsByRobot(ConcurrentHashMap<String, List<String>> goodsByRobot) {
        GameServer.goodsByRobot = goodsByRobot;
    }
    
    public static ConcurrentHashMap<BigDecimal, StallPurchase> getStallPurchases() {
        return GameServer.stallPurchases;
    }
    
    public static void setStallPurchases(ConcurrentHashMap<BigDecimal, StallPurchase> stallPurchases) {
        GameServer.stallPurchases = stallPurchases;
    }
    
    public static List<MountShouhu> getAllmountshouhu() {
        return GameServer.allmountshouhu;
    }
    
    public static void setAllmountshouhu(List<MountShouhu> allmountshouhu) {
        GameServer.allmountshouhu = allmountshouhu;
    }
    
    public GameServer() {
        GameServer.allLoginRole = new ConcurrentHashMap<>();
        GameServer.roleNameMap = new ConcurrentHashMap<>();
        GameServer.inlineUserNameMap = new ConcurrentHashMap<>();
        GameServer.socketUserNameMap = new ConcurrentHashMap<>();
        GameServer.inlineNum = new AtomicInteger();
    }
    
    public static ConcurrentHashMap<Integer, QianDao> getQianDaoMap() {
        return GameServer.qianDaoMap;
    }
    
    public static void setQianDaoMap(ConcurrentHashMap<Integer, QianDao> qianDaoMap) {
        GameServer.qianDaoMap = qianDaoMap;
    }
    
    public static ConcurrentHashMap<Integer, GMshopItem> getGMshopItemMap() {
        return GameServer.gMshopItemMap;
    }
    
    public static void setGMshopItemMap(ConcurrentHashMap<Integer, GMshopItem> gMshopItemMap) {
        GameServer.gMshopItemMap = gMshopItemMap;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(new InetSocketAddress(GameServer.portNumber));
            b.childHandler(new MainServerInitializer());
            b.childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true));
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.option(ChannelOption.SO_BACKLOG, Integer.valueOf(1024));
            b.option(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
            b.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
            ChannelFuture f = b.bind(GameServer.portNumber).sync();
            f.channel().closeFuture().sync();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    public Boolean isGQ() {
        if (StringUtils.isBlank(GameServer.time)) {
            return Boolean.valueOf(false);
        }
        String[] v = GameServer.time.split("-");
        LocalDateTime customDateTime = LocalDateTime.of(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]), Integer.parseInt(v[3]), Integer.parseInt(v[4]), Integer.parseInt(v[5]));
        LocalDateTime now = LocalDateTime.now();
        if (customDateTime.isBefore(now)) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        if ((boolean)this.isGQ()) {
            return;
        }
        Properties properties = new Properties();
        try {
            InputStream in = GameServer.class.getClassLoader().getResourceAsStream("important.properties");
            properties.load(in);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        GameServer.portNumber = Integer.parseInt(properties.getProperty("server.port"));
        GameServer.registerOnOff = Integer.parseInt(properties.getProperty("server.register"));
        GameServer.area = properties.getProperty("server.area");
        GameServer.gameServerPay = properties.getProperty("server.pay");
        GameServer.redisReset = Integer.parseInt(properties.getProperty("server.redis"));
        GameServer.lianhua = Integer.parseInt(properties.getProperty("server.lianhua"));
        GameServer.redisIp = properties.getProperty("server.redisip");
        GameServer.redisPort = Integer.parseInt(properties.getProperty("server.redisport"));
        GameServer.QZ = properties.getProperty("server.QZ");
        GameServer.qh = Integer.parseInt(properties.getProperty("server.qh"));
        GameServer.zhifu = properties.getProperty("zhifu");
        GameServer.tablePath = properties.getProperty("server.tablePath");
        GameServer.tableZone = (Map)GsonUtil.getGsonUtil().getgson().fromJson(properties.getProperty("server.tableZone"), Map.class);
        String version = properties.getProperty("server.version");
        if (GameServer.lianhua == 1) {
            version += "L1";
        }
        MainServerHandler.VS = Agreement.getAgreement().VersionAgreement(version);
        GameServer.TXTPATH = this.getClass().getClassLoader().getResource("/").getPath() + "GetTXT/";
        File file = new File(GameServer.TXTPATH);
        file.mkdirs();
        System.err.println(GameServer.portNumber + " 当前端口,正在启动主服务器...");
        GameServer.gameServer = new GameServer();
        System.err.println(GameServer.portNumber + " 当前端口,正在初始化数据...");
        try {
            GameServer.gameServer.loadResource();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        System.err.println(GameServer.portNumber + " 当前端口,初始化数据完毕");
        new Thread() {
            @Override
            public void run() {
                try {
                    GameServer.gameServer.start();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.err.println(GameServer.portNumber + " 当前端口,主服务器启动完毕");
        BangBattlePool.getBangBattlePool();
        RefreshMonsterTask activityRunnable = new RefreshMonsterTask();
        Thread t0 = new Thread(activityRunnable);
        t0.start();
        SceneUtil.init();
        GameServer.golemServer = GolemServer.initAIServer();
        GolemConfig config = GameServer.getGolemConfig();
        if (config.get("摆摊开关")!=null&&config.get("摆摊开关").equals("开")) {
            new Thread(new StallBotTask()).start();//机器人摆摊
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        GameServer.OPEN = true;
        System.err.println("开始关闭服务器,准备保存数据，请不要直接关闭Tomcat");
        try {
            Thread.sleep(2000L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.err.println("开始处理摆摊物品");
            StallPool.getPool().guanbi();
            System.err.println("开始保存擂台赛积分数据");
            LTSUtil.getLtsUtil().BCLts();
            MonitorUtil.HdxtoSting();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BangFileSystem.getBangFileSystem().DataSaving(BangBattlePool.getBangBattlePool());
            GangUtil.upGangs(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("开始备份玩家数据");
        for (Map.Entry<ChannelHandlerContext, LoginResult> entrys : getAllLoginRole().entrySet()) {
            LoginResult loginResult = (LoginResult)entrys.getValue();
            if (loginResult == null) {
                continue;
            }
            else {
                try {
                    loginResult.setUptime(System.currentTimeMillis() + "");
                    RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                    roleData.roleRecover(loginResult);
                    RedisControl.addUpDate(loginResult, roleData.getPackRecord());
                }
                catch (Exception e2) {
                    System.err.println("处理玩家备份失败" + loginResult.getRolename());
                    e2.printStackTrace();
                }
            }
        }
        SendMessage.sendMessageToAllRoles(Agreement.getAgreement().serverstopAgreement());
        RedisEqualWithSqlThread.AllToDataRole();
        try {
            Thread.sleep(10000L);
            RedisEqualWithSqlThread.AllToDatabase();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        if (WriteOut.buffer != null) {
            WriteOut.writeTxtFile(WriteOut.buffer.toString());
        }
        try {
            LaborScene.Save(true);
            CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "hatch.txt", HatchvalueAction.hatch.toString().getBytes());
            saveEventRoles();
            Scene scene = SceneUtil.getScene(1009);
            if (scene != null) {
                RCScene rcScene = (RCScene)scene;
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "bbRecord.txt", GsonUtil.getGsonUtil().getgson().toJson(rcScene.getBbRecord()).getBytes());
            }
            scene = SceneUtil.getScene(1010);
            if (scene != null) {
                PKLSScene pklsScene = (PKLSScene)scene;
                lsteamBean lsteamBean = new lsteamBean();
                lsteamBean.setLSTeams(pklsScene.getLSTeams());
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "lsteam.txt", GsonUtil.getGsonUtil().getgson().toJson(lsteamBean).getBytes());
            }
            try {
                JPScene jpScene = (JPScene)SceneUtil.getScene(1015);
                if (jpScene != null) {
                    jpScene.accident();
                }
            }
            catch (Exception e4) {
                System.out.printf("竞拍退款失败！", new Object[0]);
            }
            CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "money.txt", GsonUtil.getGsonUtil().getgson().toJson(MonitorUtil.getMoney()).getBytes());
            RefreshMonsterTask.upBuyCount(-1, false);
        }
        catch (IOException e5) {
            e5.printStackTrace();
        }
        try {
            Thread.sleep(5000L);
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        System.exit(0);
    }
    
    public void loadResource() {
        AllServiceUtil.initServices();
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        while (i < 74) {
            if (!ReadPoolUtil.readTypeTwo(buffer, i)) {
                System.out.println(buffer.toString());
                try {
                    Thread.sleep(999999999L);
                }
                catch (Exception ex) {}
                break;
            }
            else {
                ++i;
            }
        }
        GangUtil.init();
        RewardHallExample example = new RewardHallExample();
        GameServer.rewardList = AllServiceUtil.getRewardHallMallService().selectByExample(example);
        bangLists();
        LTSUtil.getLtsUtil();
        String hatch = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "hatch.txt");
        if (hatch != null) {
            HatchvalueAction.hatch.set(Integer.parseInt(hatch));
        }
        ParamTool.handles();
        BangFileSystem.getBangFileSystem();
        PhyAttack.initSkill();
        ExchangeUtil.init();
        RedisPoolUntil.init();
        RedisGoodPoolUntil.init();
        new RedisCacheUtil().databaseToCache();
    }
    
    public static void bangLists() {
        (GameServer.allBangList = new ConcurrentHashMap<>()).put(Integer.valueOf(1), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(1)));
        GameServer.allBangList.put(Integer.valueOf(2), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(2)));
        GameServer.allBangList.put(Integer.valueOf(3), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(3)));
        GameServer.allBangList.put(Integer.valueOf(6), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(6)));
        GameServer.allBangList.put(Integer.valueOf(7), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(7)));
        GameServer.allBangList.put(Integer.valueOf(8), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(8)));
    }
    
    public static synchronized void addOuts(ChannelHandlerContext ctx, LoginResult loginResult) {
        GameServer.allLoginRole.put(ctx, loginResult);
        GameServer.roleNameMap.put(loginResult.getRolename(), ctx);
        GameServer.inlineUserNameMap.put(loginResult.getUserName(), ctx);
        GameServer.socketUserNameMap.put(ctx, loginResult.getUserName());
        if (loginResult.getGang_id() != null) {
            GangDomain gangDomain = GangUtil.getGangDomain(loginResult.getGang_id());
            if (gangDomain != null) {
                gangDomain.upGangRole(loginResult.getRole_id(), ctx);
            }
        }
    }
    
    public static void userDown(ChannelHandlerContext ctx) {
        synchronized (GameServer.userLock) {
            userDownTwos(ctx);
        }
    }
    
    public static void userDownTwos(ChannelHandlerContext ctx) {
        if (GameServer.OPEN) {
            return;
        }
        LoginResult exitUser = (LoginResult)getAllLoginRole().get(ctx);
        if (exitUser == null) {
            return;
        }
        if ((long)exitUser.getMapid() == 4444L) {
            ChangeMapBean change = new ChangeMapBean("1207", 4380, 2960);
            ((IAction)ParamTool.ACTION_MAP.get("changemap")).action(ctx, GsonUtil.getGsonUtil().getgson().toJson(change));
        }
        ConcurrentHashMap<Integer, Configure> s = getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        LoginResult loginResult = (LoginResult)getAllLoginRole().get(ctx);
        if (loginResult.getBooth_id() != null && !configure.getLxbtkg().equals("开")) {
            StallPool.getPool().RetreatStall(Integer.parseInt(loginResult.getBooth_id().toString()));
            loginResult.setBooth_id(null);
        }
        try {
            BattleData battle = (BattleData)BattleThreadPool.BattleDatas.get(exitUser.getFighting());
            if (battle != null) {
                battle.getParticipantlist().remove(exitUser.getRolename());
            }
            StallPool.getPool().updateState(exitUser.getBooth_id(), StallPool.MANAGE, exitUser.getRole_id());
            TransUtil.roleDown(exitUser.getRolename());
            BangFight bangFight = BangBattlePool.getBangBattlePool().getBangFight(exitUser.getGang_id());
            if (bangFight != null) {
                Build door = bangFight.getBuild(exitUser.getRolename());
                if (door != null) {
                    door.setRoleName(null);
                    door.setState(Build.IDLE);
                    door.setTime(0L);
                }
                Member member = bangFight.getMember(exitUser.getRolename(), exitUser.getGang_id());
                if (member != null) {
                    if (bangFight.Launch == member) {
                        bangFight.Launch = null;
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("#G ");
                        buffer.append(exitUser.getRolename());
                        buffer.append(" #Y灰溜溜离开挑战台");
                        bangFight.BattleNews(buffer.toString());
                    }
                    member.setState(-1);
                }
            }
            String message = Agreement.getAgreement().UserRetreatAgreement(exitUser.getRolename());
            SendMessage.sendMessageToMapRoles(exitUser.getMapid(), message);
            TeamBean teamBean = TeamUtil.getTeam(exitUser.getTroop_id());
            TeamRole teamRole = (teamBean != null) ? teamBean.getTeamRole(exitUser.getRole_id()) : null;
            if (teamRole != null) {
                teamBean.stateLeave(teamRole, -2);
            }
            if (exitUser.getHavebaby() != null && !exitUser.getHavebaby().equals("")) {
                exitUser.setHavebaby(Integer.valueOf((int)exitUser.getHavebaby() - (int)(System.currentTimeMillis() - exitUser.getMakeloveTime()) / 1000));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        exitUser.setUptime(System.currentTimeMillis() + "");
        RoleData roleData = RolePool.deleteRoleData(exitUser.getRole_id());
        if (roleData == null) {
            roleData = exitUser.getRoleData();
        }
        String TIME = TimeUntil.getPastDate();
        String IP = (roleData != null) ? roleData.getIP() : null;
        UserTable userTable = new UserTable();
        userTable.setUSERLASTLOGIN(TIME);
        userTable.setLoginip(IP);
        userTable.setCodecard(exitUser.getCodecard());
        userTable.setMoney(exitUser.getMoney());
        userTable.setUsername(exitUser.getUserName());
        if (configure.getSfdkms().equals("1")) {
            LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(exitUser.getRole_id());
            Long cd = Long.valueOf(new Date().getTime());
            Long xh = Long.valueOf((long)cd - Long.parseLong(exitUser.getGameStartTime()));
            Long second = Long.valueOf((long)xh / 1000L);
            Long dk = Long.valueOf((long)second / 60L);
            exitUser.setGameTimeRemaining(Integer.valueOf(Integer.parseInt((long)(int)roleInfo.getGameTimeRemaining() - (long)dk + "")));
        }
        try {
            AllServiceUtil.getUserTableService().updateUser(userTable);
        }
        catch (Exception e2) {
            WriteOut.addtxt("人物数据保存报错UserTable:" + GsonUtil.getGsonUtil().getgson().toJson(userTable), 9999L);
            ctx.close();
        }
        if (roleData != null) {
            try {
                AllServiceUtil.getPackRecordService().updateByPrimaryKeySelective(roleData.getPackRecord());
            }
            catch (Exception e2) {
                WriteOut.addtxt("人物数据保存报错PackRecord:" + GsonUtil.getGsonUtil().getgson().toJson(roleData.getPackRecord()), 9999L);
                ctx.close();
            }
            try {
                roleData.roleRecover(exitUser);
            }
            catch (Exception e2) {
                WriteOut.addtxt("人物数据转换报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
                ctx.close();
            }
        }
        try {
            AllServiceUtil.getRoleTableService().updateRoleWhenExit(exitUser);
        }
        catch (Exception e2) {
            ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)getInlineUserNameMap().get(loginResult.getUserName()), loginResult.getUserName());
            WriteOut.addtxt("人物数据保存报错:" + GsonUtil.getGsonUtil().getgson().toJson(exitUser), 9999L);
            ctx.close();
            e2.printStackTrace();
        }
        LogIn(IP, exitUser.getRolename(), false);
        try {
            getAllLoginRole().remove(ctx);
            getRoleNameMap().remove(exitUser.getRolename());
            getInlineUserNameMap().remove(exitUser.getUserName());
            getSocketUserNameMap().remove(ctx);
            ((ConcurrentHashMap<String, ChannelHandlerContext>)getMapRolesMap().get(exitUser.getMapid())).remove(exitUser.getRolename());
            GangDomain gangDomain = GangUtil.getGangDomain(exitUser.getGang_id());
            if (gangDomain != null) {
                gangDomain.downGangRole(exitUser.getRole_id());
            }
        }
        catch (Exception e2) {
            WriteOut.addtxt("人物退出报错，清除缓存对象" + e2.toString(), 9999L);
            ctx.close();
            e2.printStackTrace();
        }
    }
    
    public static void LogIn(String IP, String roleName, boolean is) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("时间:");
        buffer.append(TimeUntil.getPastDate());
        buffer.append("__IP__");
        buffer.append(IP);
        buffer.append("__");
        buffer.append(roleName);
        if (is) {
            buffer.append(":玩家上线:");
            buffer.append(GameServer.inlineNum.incrementAndGet());
        }
        else {
            buffer.append(":玩家下线:");
            buffer.append(GameServer.inlineNum.decrementAndGet());
        }
        System.err.println(buffer.toString());
    }

    //获得小树苗道具
    public static Goodstable getTreeGood(Goodstable goods) {
        ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
        Npctable npctable = map.get(99+""+goods.getRole_id().toString());
        if(npctable!=null){
            StringBuffer buffer = new StringBuffer();
            buffer.append("小树苗=");
            buffer.append("1193");
            buffer.append(",");
            buffer.append(npctable.getTx());
            buffer.append(",");
            buffer.append(npctable.getTy());
            goods.setValue(buffer.toString());
        }
        return goods;
    }
    // 获取物品
    public static Goodstable getGood(BigDecimal id) {
        Goodstable goodstable = null;
        if (id.longValue() < 0L) {
            RoleTxBean txBean = getTxBean(-id.intValue());
            if (txBean != null) {
                goodstable = new Goodstable();
                goodstable.setGoodsname(txBean.getRdName());
                goodstable.setGoodsid(id);
                goodstable.setType(-1L);
            }
            return goodstable;
        }
        else {
            if (id.longValue() <= 25L) {
                goodstable = GameServer.allLingbaoFushi.get(id);
            }
            else {
                goodstable = getAllGoodsMap().get(id);
            }
            if (goodstable == null) {
                return null;
            }
            long type = goodstable.getType();
            if (type == 1000L) {
                id = SplitStringTool.GoodRandomId(goodstable.getValue());
                return getGood(id);
            }
            goodstable = goodstable.clone();
            if (type == 2051L || type == 2052L || type == 1007L || type == 2057L) {
                Sghostpoint sghostpoint = getSghostpoint("藏宝图");
                PathPoint point = sghostpoint.getPoints()[Battlefield.random.nextInt(sghostpoint.getPoints().length)];
                StringBuffer buffer = new StringBuffer();
                buffer.append("宝图=");
                buffer.append(sghostpoint.getPointkey());
                buffer.append(",");
                buffer.append(sghostpoint.getPointmap());
                buffer.append(",");
                buffer.append(point.getX());
                buffer.append(",");
                buffer.append(point.getY());
                buffer.append(",");
                buffer.append(type);
                goodstable.setValue(buffer.toString());
            }
            else if (type == 2122L) {
                Sghostpoint sghostpoint = getSghostpoint("藏宝图");
                PathPoint point = sghostpoint.getPoints()[Battlefield.random.nextInt(sghostpoint.getPoints().length)];
                StringBuffer buffer = new StringBuffer();
                buffer.append("宝图=");
                buffer.append(sghostpoint.getPointkey());
                buffer.append(",");
                buffer.append(sghostpoint.getPointmap());
                buffer.append(",");
                buffer.append(point.getX());
                buffer.append(",");
                buffer.append(point.getY());
                buffer.append(",");
                buffer.append(type);
                goodstable.setValue(buffer.toString());
            }
            else if (id.longValue() <= 25L) {
                goodstable.setQuality(new Long(0L));
                goodstable.setValue(SplitFushiValue.splitFushiValue(goodstable.getValue()));
            }
            else if (type == 80156L) {
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("耐久=20");
                buffer2.append(",");
                buffer2.append(type);
                goodstable.setValue(buffer2.toString());
            }
            else if (type == 729L) {
                if (goodstable.getValue() == null || goodstable.getValue().equals("")) {
                    StringBuffer buffer2 = new StringBuffer();
                    int v = GameServer.random.nextInt(100);
                    if (v < 3) {
                        buffer2.append("根骨=1|灵性=1|力量=1|敏捷=1");
                    }
                    else if (v < 10) {
                        buffer2.append("根骨=1|力量=1|敏捷=1");
                    }
                    else if (v < 20) {
                        buffer2.append("根骨=1|力量=1");
                    }
                    else {
                        buffer2.append("根骨=1");
                    }
                    buffer2.append("|品质=1");
                    goodstable.setValue(buffer2.toString());
                }
            }
            else if (type == 825L) {
                int s = GameServer.random.nextInt(100);
                int g = 0;
                String[] vs = goodstable.getValue().split("\\^");
                goodstable.setValue("");
                int i = 0;
                while (i < vs.length) {
                    String[] vs2 = vs[i].split("&");
                    g += Integer.parseInt(vs2[0]);
                    if (g > s) {
                        int pz = Integer.parseInt(vs2[1]);
                        int tzid = SplitStringTool.RandomId(vs2[2]).intValue();
                        Suit suit = getSuit(tzid);
                        if (suit != null) {
                            int part = suit.getParts()[GameServer.random.nextInt(suit.getParts().length)];
                            StringBuffer buffer3 = new StringBuffer();
                            buffer3.append(tzid);
                            buffer3.append("|");
                            buffer3.append(part);
                            buffer3.append("|");
                            buffer3.append(pz);
                            goodstable.setValue(buffer3.toString());
                            buffer3.setLength(0);
                            buffer3.append(suit.getSname());
                            buffer3.append("-");
                            buffer3.append(getPartsName(part));
                            buffer3.append("-");
                            buffer3.append(getPZ(pz));
                            goodstable.setGoodsname(buffer3.toString());
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        ++i;
                    }
                }
            }
            else if (type == 751L) {
                int lvl = 1;
                String[] vs3 = goodstable.getValue().split("\\|");
                lvl = Integer.parseInt(vs3[0].split("=")[1]);
                Gem gem = getGem(null);
                GemBase base = gem.getGemBase(null);
                long quality = (long)goodstable.getQuality();
                goodstable = getGood(new BigDecimal(gem.getBid()));
                if (lvl >= 7) {
                    goodstable.setSkin(Integer.parseInt(goodstable.getSkin()) + 2 + "");
                }
                else if (lvl >= 4) {
                    goodstable.setSkin(Integer.parseInt(goodstable.getSkin()) + 1 + "");
                }
                goodstable.setValue(base.getGemValue(lvl, 95 + SuitComposeAction.random.nextInt(9)));
                goodstable.setQuality(Long.valueOf(quality));
            }
            else if (type == 8888L) {
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("品质=把玩|星级=0|经验=0|颜色=无");
                int[] arr = new int[4];
                int size = 0;
                int gl = GameServer.random.nextInt(100);
                if (gl <= 15) {
                    size += GameServer.random.nextInt(9) + 2;
                }
                else if (gl <= 75) {
                    size += GameServer.random.nextInt(6) + 2;
                }
                else {
                    size += GameServer.random.nextInt(4) + 2;
                }
                WingCompose.addWingQuality(arr, size, GameServer.random.nextInt(4));
                for (int j = 0; j < arr.length; ++j) {
                    if (arr[j] != 0) {
                        if (buffer2.length() != 0) {
                            buffer2.append("|");
                        }
                        if (j == 0) {
                            buffer2.append("根骨=");
                        }
                        else if (j == 1) {
                            buffer2.append("力量=");
                        }
                        else if (j == 2) {
                            buffer2.append("灵性=");
                        }
                        else if (j == 3) {
                            buffer2.append("敏捷=");
                        }
                        buffer2.append(arr[j]);
                    }
                }
                goodstable.setValue(buffer2.toString());
            }
            else if (Goodtype.isSummonEquip(type)) {
                if (goodstable.getValue().split("\\|").length == 1) {
                    StringBuffer buffer2 = new StringBuffer();
                    buffer2.append(goodstable.getValue());
                    buffer2.append("|装备部位=");
                    buffer2.append((type == 510L) ? "兽环" : ((type == 511L) ? "兽铃" : "兽甲"));
                    buffer2.append("|");
                    buffer2.append("等级需求=0转100级|");
                    int v = GameServer.random.nextInt(4);
                    buffer2.append((v == 0) ? "根骨" : ((v == 1) ? "灵性" : ((v == 2) ? "力量" : "敏捷")));
                    buffer2.append("=1|品质=");
                    int pz2 = 80 + GameServer.random.nextInt(21);
                    buffer2.append(80 + GameServer.random.nextInt(21));
                    buffer2.append("|通灵=0|");
                    buffer2.append(SuitPetEquip.petAlchemy(type, pz2, 1, 0));
                    goodstable.setValue(buffer2.toString());
                }
            }
            else if (type == 520L) {
                String[] vs=goodstable.getValue().split("\\|");//星卡可以带属性
                if (vs.length>1&&goodstable.getValue().contains("/")) {

                }else {
                    Integer lvlNow = Integer.valueOf(Integer.parseInt(goodstable.getValue().split("=")[1]));
                    StringBuffer buffer4 = new StringBuffer();
                    buffer4.append(goodstable.getValue());
                    buffer4.append("/");
                    int uplvl = (int) lvlNow + GameServer.random.nextInt(4);
                    if (uplvl > 12) {
                        uplvl = 12;
                    }
                    if ((int) lvlNow > uplvl) {
                        uplvl = (int) lvlNow;
                    }
                    buffer4.append(uplvl);
                    buffer4.append("|神力=0|战力=100|");
                    List<Alchemy> list = (List<Alchemy>) getAllAlchemy().get("星卡");
                    Alchemy alchemy1 = (Alchemy) list.get(GameServer.random.nextInt(list.size()));
                    Alchemy alchemy2 = (Alchemy) list.get(GameServer.random.nextInt(list.size()));
                    int aptitude = GameServer.random.nextInt(31) + 70;
                    buffer4.append(StarCard.anewRefining((int) lvlNow, aptitude, alchemy1, alchemy2, 0));
                    buffer4.append("|");
                    buffer4.append(StarCard.anewFiveElements());
                    goodstable.setValue(buffer4.toString());
                }
            }
            else if (Goodtype.isPalEquip(type)) {
                String[] vs4 = goodstable.getValue().split("\\|");
                if (vs4.length == 1) {
                    int lvl2 = Integer.parseInt(vs4[0].split("=")[1]);
                    SuitPalEquip.PalEquipValue(goodstable, type, lvl2, null, 0);
                }
            }
            return goodstable;
        }
    }
    
    public static String getPartsName(int id) {
        switch (id) {
            case 1: {
                return "帽子";
            }
            case 2: {
                return "项链";
            }
            case 3: {
                return "衣服";
            }
            case 6: {
                return "面具";
            }
            case 7: {
                return "腰带";
            }
            case 8: {
                return "披风";
            }
            case 9: {
                return "挂件";
            }
            case 10: {
                return "左戒指";
            }
            case 11: {
                return "右戒指";
            }
            default: {
                return null;
            }
        }
    }
    
    public List<LoginResult> getUserRole(BigDecimal userId) {
        List<LoginResult> loginResults = null;
        for (ChannelHandlerContext key : GameServer.allLoginRole.keySet()) {
            LoginResult loginResult = (LoginResult)GameServer.allLoginRole.get(key);
            if (loginResult.getUser_id().equals(userId)) {
                if (loginResults == null) {
                    loginResults = new ArrayList<>();
                }
                loginResults.add(loginResult);
            }
        }
        return loginResults;
    }
    
    public static String getPZ(int pz) {
        switch (pz) {
            case 1: {
                return "把玩";
            }
            case 2: {
                return "贴身";
            }
            case 3: {
                return "珍藏";
            }
            case 4: {
                return "无价";
            }
            case 5: {
                return "传世";
            }
            default: {
                return null;
            }
        }
    }
    
    public static Sghostpoint getSghostpoint(String type) {
        List<Sghostpoint> sghostpoints = (List<Sghostpoint>)GameServer.monsterpointMap.get(type);
        if (sghostpoints != null && sghostpoints.size() != 0) {
            return (Sghostpoint)sghostpoints.get(Battlefield.random.nextInt(sghostpoints.size()));
        }
        return null;
    }
    
    public static Long getMapIds(String mapname) {
        Gamemap gamemap = (Gamemap)GameServer.gameMap.get(mapname);
        if (gamemap != null) {
            return new Long(gamemap.getMapid());
        }
        return new Long(0L);
    }
    
    public static String getMapName(String key) {
        Gamemap gamemap = (Gamemap)GameServer.gameMap.get(key);
        if (gamemap != null) {
            return gamemap.getMapname();
        }
        return "未知地图";
    }
    
    public static Gamemap getMap(String key) {
        return (Gamemap)GameServer.gameMap.get(key);
    }
    
    public static int getMapNpc(String NPCId) {
        for (Gamemap gamemap : GameServer.gameMap.values()) {
            if (gamemap.getNpcs() != null && gamemap.getNpcs().contains(NPCId)) {
                return Integer.parseInt(gamemap.getMapid());
            }
        }
        return 0;
    }
    
    public static TaskData getTaskName(String taskname) {
        for (Integer key : GameServer.allTaskData.keySet()) {
            if (((TaskData)GameServer.allTaskData.get(key)).getTaskName().equals(taskname)) {
                return (TaskData)GameServer.allTaskData.get(key);
            }
        }
        return null;
    }
    
    public static TaskData getTaskData(int id) {
        return (TaskData)GameServer.allTaskData.get(Integer.valueOf(id));
    }
    
    public static TaskSet getTaskSet(int id) {
        return (TaskSet)GameServer.allTaskSet.get(Integer.valueOf(id));
    }
    
    public static TaskSet getRobotTaskSet(int robotID) {
        return (TaskSet)GameServer.allTaskSet.get(Integer.valueOf(-robotID));
    }
    
    public static ConcurrentHashMap<Integer, TaskData> getAllTaskData() {
        return GameServer.allTaskData;
    }
    
    public static void setAllTaskData(ConcurrentHashMap<Integer, TaskData> allTaskData) {
        GameServer.allTaskData = allTaskData;
    }
    
    public static ConcurrentHashMap<Integer, TaskSet> getAllTaskSet() {
        return GameServer.allTaskSet;
    }
    
    public static void setAllTaskSet(ConcurrentHashMap<Integer, TaskSet> allTaskSet) {
        GameServer.allTaskSet = allTaskSet;
    }
    
    public static GodStone getGodStone(String name, String[] vs) {
        List<GodStone> godStones = (List<GodStone>)GameServer.godMap.get(name);
        if (godStones == null || godStones.size() == 0) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            map.put(vss[0], Integer.valueOf(1));
        }
        int sum = 0;
        for (int j = godStones.size() - 1; j >= 0; --j) {
            sum = 0;
            GodStone godStone = (GodStone)godStones.get(j);
            String type = godStone.getType();
            int n = -1;
            switch (type.hashCode()) {
                case 703706: {
                    if (type.equals("反震")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1167975: {
                    if (type.equals("速度")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1220110: {
                    if (type.equals("附毒")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 30509553: {
                    if (type.equals("破物理")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -896861679: {
                    if (type.equals("双忽视仙法")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 664218690: {
                    if (type.equals("双抗水火")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 664582516: {
                    if (type.equals("双抗风雷")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 663156425: {
                    if (type.equals("双强人法")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 24907314: {
                    if (type.equals("抗人法")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1214068: {
                    if (type.equals("附吸")) {
                        n = 9;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 803969: {
                    if (type.equals("抗吸")) {
                        n = 10;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 24687059: {
                    if (type.equals("强鬼法")) {
                        n = 11;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 25514480: {
                    if (type.equals("抗鬼法")) {
                        n = 12;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1238746: {
                    if (type.equals("霹雳")) {
                        n = 13;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 807057: {
                    if (type.equals("扶摇")) {
                        n = 14;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    if (map.get("反震率") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 1: {
                    if (map.get(godStone.getType()) != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 2: {
                    if (map.get("加强中毒") != null) {
                        return godStone;
                    }
                    if (map.get("加强毒伤害") != null) {
                        return godStone;
                    }
                    if (map.get("附加毒法攻击") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 3: {
                    if (map.get("忽视防御程度") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 4: {
                    if (map.get("忽视抗风") != null) {
                        ++sum;
                    }
                    if (map.get("忽视抗火") != null) {
                        ++sum;
                    }
                    if (map.get("忽视抗水") != null) {
                        ++sum;
                    }
                    if (map.get("忽视抗雷") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 5: {
                    if (map.get("抗水") != null) {
                        ++sum;
                    }
                    if (map.get("抗火") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 6: {
                    if (map.get("抗风") != null) {
                        ++sum;
                    }
                    if (map.get("抗雷") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 7: {
                    if (map.get("加强混乱") != null) {
                        ++sum;
                    }
                    if (map.get("加强昏睡") != null) {
                        ++sum;
                    }
                    if (map.get("加强封印") != null) {
                        ++sum;
                    }
                    if (map.get("加强毒伤害") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 8: {
                    if (map.get("抗混乱") != null) {
                        ++sum;
                    }
                    if (map.get("抗昏睡") != null) {
                        ++sum;
                    }
                    if (map.get("抗封印") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 9: {
                    if (map.get("加强震慑") != null) {
                        return godStone;
                    }
                    if (map.get("附加震慑攻击") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 10: {
                    if (map.get("抗震慑") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 11: {
                    if (map.get("加强鬼火") != null) {
                        ++sum;
                    }
                    if (map.get("忽视鬼火") != null) {
                        ++sum;
                    }
                    if (map.get("加强遗忘") != null) {
                        ++sum;
                    }
                    if (map.get("加强三尸虫") != null) {
                        ++sum;
                    }
                    if (map.get("加强三尸虫回血程度") != null) {
                        ++sum;
                    }
                    if (map.get("加强魅惑") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 12: {
                    if (map.get("抗鬼火") != null) {
                        ++sum;
                    }
                    if (map.get("抗遗忘") != null) {
                        ++sum;
                    }
                    if (map.get("抗三尸虫") != null || map.get("抗三尸") != null) {
                        ++sum;
                    }
                    if (sum >= 2) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 13: {
                    if (map.get("加强霹雳效果") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
                case 14: {
                    if (map.get("加强甘霖回血程度") != null) {
                        return godStone;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return (GodStone)godStones.get(0);
    }
    
    public static RoleSummoning getPet(BigDecimal id) {
        RoleSummoning pet = (RoleSummoning)GameServer.allPet.get(id);
        if (pet != null) {
            return pet.clone();
        }
        return pet;
    }
    
    public static Lingbao getLingbao(String baoname) {
        Lingbao lingbao = (Lingbao)GameServer.allLingbao.get(baoname);
        if (lingbao != null) {
            return lingbao.clone();
        }
        return lingbao;
    }
    
    public static String getTXTPATH() {
        return GameServer.TXTPATH;
    }
    
    public static void setTXTPATH(String tXTPATH) {
        GameServer.TXTPATH = tXTPATH;
    }
    
    public static int getPortNumber() {
        return GameServer.portNumber;
    }
    
    public static void setPortNumber(int portNumber) {
        GameServer.portNumber = portNumber;
    }
    
    public static ConcurrentHashMap<ChannelHandlerContext, LoginResult> getAllLoginRole() {
        return GameServer.allLoginRole;
    }
    
    public static void setAllLoginRole(ConcurrentHashMap<ChannelHandlerContext, LoginResult> allLoginRole) {
        GameServer.allLoginRole = allLoginRole;
    }
    
    public static ConcurrentHashMap<String, Gamemap> getGameMap() {
        return GameServer.gameMap;
    }
    
    public static void setGameMap(ConcurrentHashMap<String, Gamemap> gameMap) {
        if (GameServer.mapRolesMap == null) {
            GameServer.mapRolesMap = new ConcurrentHashMap<>();
        }
        for (Gamemap map : gameMap.values()) {
            ConcurrentHashMap<String, ChannelHandlerContext> hashMap = (ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.mapRolesMap.get(Long.valueOf(Long.parseLong(map.getMapid())));
            if (hashMap == null) {
                hashMap = new ConcurrentHashMap<>();
                GameServer.mapRolesMap.put(Long.valueOf(Long.parseLong(map.getMapid())), hashMap);
            }
        }
        GameServer.gameMap = gameMap;
    }
    
    public static ConcurrentHashMap<String, Monster> getMonsterMap() {
        return GameServer.monsterMap;
    }
    
    public static void setMonsterMap(ConcurrentHashMap<String, Monster> monsterMap) {
        GameServer.monsterMap = monsterMap;
    }
    
    public static ConcurrentHashMap<BigDecimal, Goodstable> getAllGoodsMap() {
        return GameServer.allGoodsMap;
    }
    
    public static void setAllGoodsMap(ConcurrentHashMap<BigDecimal, Goodstable> allGoodsMap) {
        GameServer.allGoodsMap = allGoodsMap;
    }
    
    public static ConcurrentHashMap<String, Goodstable> getGetGoods() {
        return GameServer.getGoods;
    }
    
    public static void setGetGoods(ConcurrentHashMap<String, Goodstable> getGoods) {
        GameServer.getGoods = getGoods;
    }
    
    public static ConcurrentHashMap<String, List<Decorate>> getAllDecorate() {
        return GameServer.allDecorate;
    }
    
    public static void setAllDecorate(ConcurrentHashMap<String, List<Decorate>> allDecorate) {
        GameServer.allDecorate = allDecorate;
    }
    
    public static ConcurrentHashMap<String, List<Sghostpoint>> getMonsterpointMap() {
        return GameServer.monsterpointMap;
    }
    
    public static void setMonsterpointMap(ConcurrentHashMap<String, List<Sghostpoint>> monsterpointMap) {
        GameServer.monsterpointMap = monsterpointMap;
    }
    
    public static ConcurrentHashMap<String, Robots> getAllRobot() {
        return GameServer.allRobot;
    }
    
    public static void setAllRobot(ConcurrentHashMap<String, Robots> allRobot) {
        GameServer.allRobot = allRobot;
    }
    
    public static ConcurrentHashMap<String, List<Newequip>> getWitchNewequipMap() {
        return GameServer.witchNewequipMap;
    }
    
    public static void setWitchNewequipMap(ConcurrentHashMap<String, List<Newequip>> witchNewequipMap) {
        GameServer.witchNewequipMap = witchNewequipMap;
    }
    
    public static ConcurrentHashMap<String, List<Newequip>> getSameNewequipMap() {
        return GameServer.sameNewequipMap;
    }
    
    public static void setSameNewequipMap(ConcurrentHashMap<String, List<Newequip>> sameNewequipMap) {
        GameServer.sameNewequipMap = sameNewequipMap;
    }
    
    public static ConcurrentHashMap<String, List<Alchemy>> getAllAlchemy() {
        return GameServer.allAlchemy;
    }
    
    public static void setAllAlchemy(ConcurrentHashMap<String, List<Alchemy>> allAlchemy) {
        GameServer.allAlchemy = allAlchemy;
    }
    
    public static ConcurrentHashMap<String, List<Xianqi>> getGetAllXianqi() {
        return GameServer.getAllXianqi;
    }
    
    public static void setGetAllXianqi(ConcurrentHashMap<String, List<Xianqi>> getAllXianqi) {
        GameServer.getAllXianqi = getAllXianqi;
    }
    
    public static ConcurrentHashMap<String, List<String>> getXianqiTypeValue() {
        return GameServer.XianqiTypeValue;
    }
    
    public static void setXianqiTypeValue(ConcurrentHashMap<String, List<String>> xianqiTypeValue) {
        GameServer.XianqiTypeValue = xianqiTypeValue;
    }
    
    public static ConcurrentHashMap<BigDecimal, Goodstable> getAllLingbaoFushi() {
        return GameServer.allLingbaoFushi;
    }
    
    public static void setAllLingbaoFushi(ConcurrentHashMap<BigDecimal, Goodstable> allLingbaoFushi) {
        GameServer.allLingbaoFushi = allLingbaoFushi;
    }
    
    public static ConcurrentHashMap<Long, ConcurrentHashMap<String, ChannelHandlerContext>> getMapRolesMap() {
        return GameServer.mapRolesMap;
    }
    
    public static void setMapRolesMap(ConcurrentHashMap<Long, ConcurrentHashMap<String, ChannelHandlerContext>> mapRolesMap) {
        GameServer.mapRolesMap = mapRolesMap;
    }
    
    public static ConcurrentHashMap<String, ChannelHandlerContext> getRoleNameMap() {
        return GameServer.roleNameMap;
    }
    
    public static void setRoleNameMap(ConcurrentHashMap<String, ChannelHandlerContext> roleNameMap) {
        GameServer.roleNameMap = roleNameMap;
    }
    
    public static ConcurrentHashMap<String, ChannelHandlerContext> getInlineUserNameMap() {
        return GameServer.inlineUserNameMap;
    }
    
    public static void setInlineUserNameMap(ConcurrentHashMap<String, ChannelHandlerContext> inlineUserNameMap) {
        GameServer.inlineUserNameMap = inlineUserNameMap;
    }
    
    public static ConcurrentHashMap<ChannelHandlerContext, String> getSocketUserNameMap() {
        return GameServer.socketUserNameMap;
    }
    
    public static void setSocketUserNameMap(ConcurrentHashMap<ChannelHandlerContext, String> socketUserNameMap) {
        GameServer.socketUserNameMap = socketUserNameMap;
    }
    
    public static ConcurrentHashMap<String, Shop> getAllShopGoods() {
        return GameServer.allShopGoods;
    }
    
    public static void setAllShopGoods(ConcurrentHashMap<String, Shop> allShopGoods) {
        GameServer.allShopGoods = allShopGoods;
    }
    
    public static Skill getSkill(String key) {
        return (Skill)GameServer.getSkill.get(key);
    }
    
    public static ConcurrentHashMap<String, Skill> getGetSkill() {
        return GameServer.getSkill;
    }
    
    public static void setGetSkill(ConcurrentHashMap<String, Skill> getSkill) {
        GameServer.getSkill = getSkill;
    }
    
    public static ConcurrentHashMap<String, List<GodStone>> getGodMap() {
        return GameServer.godMap;
    }
    
    public static void setGodMap(ConcurrentHashMap<String, List<GodStone>> godMap) {
        GameServer.godMap = godMap;
    }
    
    public static ConcurrentHashMap<BigDecimal, RoleSummoning> getAllPet() {
        return GameServer.allPet;
    }
    
    public static void setAllPet(ConcurrentHashMap<BigDecimal, RoleSummoning> allPet) {
        GameServer.allPet = allPet;
    }
    
    public static ConcurrentHashMap<String, Lingbao> getAllLingbao() {
        return GameServer.allLingbao;
    }
    
    public static void setAllLingbao(ConcurrentHashMap<String, Lingbao> allLingbao) {
        GameServer.allLingbao = allLingbao;
    }
    
    public static ConcurrentHashMap<String, Eshop> getAllEshopGoods() {
        return GameServer.allEshopGoods;
    }
    
    public static void setAllEshopGoods(ConcurrentHashMap<String, Eshop> allEshopGoods) {
        GameServer.allEshopGoods = allEshopGoods;
    }
    
    public static boolean isOPEN() {
        return GameServer.OPEN;
    }
    
    public static void setOPEN(boolean oPEN) {
        GameServer.OPEN = oPEN;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
    
    public static Bbuy getBbuy(BigDecimal goodsid) {
        return (Bbuy)GameServer.allBbuys.get(Integer.valueOf(goodsid.intValue()));
    }
    
    public static List<Bbuy> getBbuyList() {
        return GameServer.bbuyList;
    }
    
    public static void setBbuyList(List<Bbuy> bbuyList) {
        GameServer.bbuyList = bbuyList;
    }
    
    public static void resetBbuy() {
        for (Map.Entry<Integer, Bbuy> entrys : GameServer.allBbuys.entrySet()) {
            ((Bbuy)entrys.getValue()).setNum(0);
        }
    }
    
    public static void setAllBbuys(ConcurrentHashMap<Integer, Bbuy> allBbuys) {
        GameServer.allBbuys = allBbuys;
    }
    
    public static ConcurrentHashMap<Integer, Configure> getAllConfigure() {
        return GameServer.allConfigure;
    }
    
    public static void setAllConfigure(ConcurrentHashMap<Integer, Configure> allConfigure) {
        GameServer.allConfigure = allConfigure;
    }
    
    public static RoleTxBean getTxBean(int gid) {
        return (RoleTxBean)GameServer.allTXs.get(Integer.valueOf(gid));
    }
    
    public static void setAllTXs(ConcurrentHashMap<Integer, RoleTxBean> allTXs) {
        GameServer.allTXs = allTXs;
    }
    
    public static Suit getSuit(int id) {
        return (Suit)GameServer.allSuits.get(Integer.valueOf(id));
    }
    
    public static void setAllSuits(ConcurrentHashMap<Integer, Suit> allSuits) {
        GameServer.allSuits = allSuits;
    }
    
    public static String getGameServerPay() {
        return GameServer.gameServerPay;
    }
    
    public static void setGameServerPay(String gameServerPay) {
        GameServer.gameServerPay = gameServerPay;
    }
    
    public static Dorp getDorp(String id) {
        return (Dorp)GameServer.allDorp.get(id);
    }
    
    public static void setAllDorp(ConcurrentHashMap<String, Dorp> allDorp) {
        GameServer.allDorp = allDorp;
    }
    
    public static List<Present> getPresents() {
        return GameServer.presents;
    }
    
    public static void setPresents(List<Present> presents) {
        GameServer.presents = presents;
    }
    
    public static Gem getGem(String name) {
        if (name == null) {
            return (Gem)GameServer.gems.get(GameServer.gemns[GameServer.random.nextInt(GameServer.gemns.length)]);
        }
        return (Gem)GameServer.gems.get(name);
    }
    
    public static void setGems(ConcurrentHashMap<String, Gem> gems) {
        GameServer.gems = gems;
    }
    
    public static long getExp(int lvl) {
        if (lvl > 299) {
            lvl = 299;
        }
        Long exp = (Long)GameServer.expMap.get(Integer.valueOf(lvl));
        return (long)exp;
    }
    
    public static void setExpMap(ConcurrentHashMap<Integer, Long> expMap) {
        GameServer.expMap = expMap;
    }
    
    public static Mount getMount(int raceid, int lvl) {
        ConcurrentHashMap<Integer, Mount> map = (ConcurrentHashMap<Integer, Mount>)GameServer.allMount.get(Integer.valueOf(raceid));
        if (map == null) {
            return null;
        }
        Mount mount = (Mount)map.get(Integer.valueOf(lvl));
        if (mount != null) {
            mount = mount.clone();
        }
        return mount;
    }
    public static Car getCar(int raceid, int lvl) {
        ConcurrentHashMap<Integer, Car> map = (ConcurrentHashMap<Integer, Car>)GameServer.allCar.get(Integer.valueOf(raceid));
        if (map == null) {
            return null;
        }
        Car mount = (Car)map.get(Integer.valueOf(lvl));
        if (mount != null) {
            mount = mount.clone();
        }
        return mount;
    }

    public static void setAllCar(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Car>> allCar) {
        GameServer.allCar = allCar;
    }

    public static void setAllMount(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Mount>> allMount) {
        GameServer.allMount = allMount;
    }
    
    public static Fly getFly(int raceid, int id) {
        ConcurrentHashMap<Integer, Fly> map = (ConcurrentHashMap<Integer, Fly>)GameServer.allFly.get(Integer.valueOf(raceid));
        if (map == null) {
            return null;
        }
        Fly fly = (Fly)map.get(Integer.valueOf(id));
        if (fly != null) {
            fly = fly.clone();
        }
        return fly;
    }
    
    public static Keju getkeju(int racid, int id) {
        ConcurrentHashMap<Integer, Keju> map = (ConcurrentHashMap<Integer, Keju>)GameServer.allkeju.get(Integer.valueOf(racid));
        if (map == null) {
            return null;
        }
        Keju keju = (Keju)map.get(Integer.valueOf(id));
        if (keju != null) {
            keju = keju.clone();
        }
        return keju;
    }
    
    public static void setAllFly(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Fly>> allFly) {
        GameServer.allFly = allFly;
    }
    
    public static void setAllKeju(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Keju>> allkeju) {
        GameServer.allkeju = allkeju;
    }
    
    public static void setAlllhtj(ConcurrentHashMap<Integer, TeJiLH> alllhtj) {
        GameServer.alllhtj = alllhtj;
    }
    
    public static ConcurrentHashMap<Integer, TeJiLH> getAlllhtj() {
        return GameServer.alllhtj;
    }
    
    public static ColorScheme getColors(int type) {
        List<Integer> a = new ArrayList<>();
        for (ColorScheme value : GameServer.allColor.values()) {
            if (value.getZid() == 0 || value.getZid() == type) {
                a.add(Integer.valueOf(value.getId()));
            }
        }
        if (a.size() == 0) {
            return null;
        }
        return (ColorScheme)GameServer.allColor.get(a.get(GameServer.random.nextInt(a.size())) + "");
    }
    
    public static ColorScheme getColor(String color) {
        return (ColorScheme)GameServer.allColor.get(color);
    }
    
    public static ColorScheme getColor(int id) {
        return (ColorScheme)GameServer.allColor.get(Integer.valueOf(id));
    }
    
    public static void setAllColor(ConcurrentHashMap<String, ColorScheme> allColor) {
        GameServer.allColor = allColor;
    }
    
    public static Talent getTalent(int talentid) {
        return (Talent)GameServer.alltalent.get(Integer.valueOf(talentid));
    }
    
    public static void setAlltalent(ConcurrentHashMap<Integer, Talent> alltalent) {
        GameServer.alltalent = alltalent;
    }
    
    public static Lshop getLshop(String id) {
        Lshop lshop = (Lshop)GameServer.allLShopGoods.get(id);
        if (lshop == null) {
            return null;
        }
        return lshop.clone();
    }
    
    public static ConcurrentHashMap<String, Lshop> getAllLShopGoods() {
        return GameServer.allLShopGoods;
    }
    
    public static void setAllLShopGoods(ConcurrentHashMap<String, Lshop> allLShopGoods) {
        GameServer.allLShopGoods = allLShopGoods;
    }
    
    public static Draw getDraw(int id) {
        return (Draw)GameServer.allDraws.get(Integer.valueOf(id));
    }
    
    public static void setAllDraws(ConcurrentHashMap<Integer, Draw> allDraws) {
        GameServer.allDraws = allDraws;
    }
    
    public static aCard getCard(int id) {
        return (aCard)GameServer.allACard.get(Integer.valueOf(id));
    }
    
    public static void setAllACard(ConcurrentHashMap<Integer, aCard> allACard) {
        GameServer.allACard = allACard;
    }
    
    public static Title getTitle(String id) {
        if (id == null || id.equals("")) {
            return null;
        }
        return (Title)GameServer.alltitle.get(id);
    }
    
    public static String getTitleColor(String titleName) {
        if (titleName == null || titleName.equals("")) {
            return "";
        }
        return (String)GameServer.alltitleColor.get(titleName);
    }
    
    public static ConcurrentHashMap<String, String> getTitleColors() {
        return GameServer.alltitleColor;
    }
    
    public static void setAlltitle(ConcurrentHashMap<String, Title> alltitle) {
        GameServer.alltitle = alltitle;
    }
    
    public static List<Title> getMoneyTitles() {
        return GameServer.moneyTitles;
    }
    
    public static void setMoneyTitles(List<Title> moneyTitles) {
        GameServer.moneyTitles = moneyTitles;
    }
    
    public static EventModel getEvent(int id) {
        return (EventModel)GameServer.allevent.get(Integer.valueOf(id));
    }
    
    public static void setAllevent(ConcurrentHashMap<Integer, EventModel> allevent) {
        GameServer.allevent = allevent;
    }
    
    public static WingTraining getWingTraining(long type) {
        return (WingTraining)GameServer.allWingTraining.get(Long.valueOf(type));
    }
    
    public static void setAllWingTraining(ConcurrentHashMap<Long, WingTraining> allWingTraining) {
        GameServer.allWingTraining = allWingTraining;
    }
    
    public static StarPalace getStarPalace(String type) {
        return (StarPalace)GameServer.allStarPalace.get(type);
    }
    
    public static String randomStarPalace() {
        return GameServer.allStarPalaceKey[GameServer.random.nextInt(GameServer.allStarPalaceKey.length)];
    }
    
    public static void setAllStarPalace(ConcurrentHashMap<String, StarPalace> allStarPalace) {
        GameServer.allStarPalace = allStarPalace;
    }
    
    public static void setAllStarPalaceKey(String[] allStarPalaceKey) {
        GameServer.allStarPalaceKey = allStarPalaceKey;
    }
    
    public static Npctable getNpc(String npcId) {
        return (Npctable)GameServer.npcMap.get(npcId);
    }
    
    public static ConcurrentHashMap<String, Npctable> getNpcMap() {
        return GameServer.npcMap;
    }
    
    public static void setNpcMap(ConcurrentHashMap<String, Npctable> npcMap) {
        GameServer.npcMap = npcMap;
    }
    
    public static Door getDoor(int doorId) {
        return GameServer.doorMap.get(Integer.valueOf(doorId));
    }
    
    public static ConcurrentHashMap<Integer, Door> getDoorMap() {
        return GameServer.doorMap;
    }
    
    public static void setDoorMap(ConcurrentHashMap<Integer, Door> doorMap) {
        GameServer.doorMap = doorMap;
    }
    
    public static PayvipBean getVIP(long moeny) {
        for (int i = GameServer.payvipList.size() - 1; i >= 0; --i) {
            PayvipBean vip = (PayvipBean)GameServer.payvipList.get(i);
            if ((long)(int)vip.getPaynum() <= moeny) {
                return vip;
            }
        }
        return null;
    }
    
    public static DNTGAward getAllDntg(int id) {
        return (DNTGAward)GameServer.allDntg.get(Integer.valueOf(id));
    }
    
    public static void setAllDntg(ConcurrentHashMap<Integer, DNTGAward> allDntg) {
        GameServer.allDntg = allDntg;
    }
    
    public static PalData getPalData(int id) {
        return (PalData)GameServer.allPalData.get(Integer.valueOf(id));
    }
    
    public static void setAllPalData(ConcurrentHashMap<Integer, PalData> allPalData) {
        GameServer.allPalData = allPalData;
    }
    
    public static PalEquip getPalEquip(long type) {
        return (PalEquip)GameServer.allPalEquip.get(Long.valueOf(type));
    }
    
    public static void setAllPalEquip(ConcurrentHashMap<Long, PalEquip> allPalEquip) {
        GameServer.allPalEquip = allPalEquip;
    }
    
    public static PetExchange getPetExchange(int id) {
        return (PetExchange)GameServer.allPetExchange.get(Integer.valueOf(id));
    }
    
    public static void setAllPetExchange(ConcurrentHashMap<Integer, PetExchange> allPetExchange) {
        GameServer.allPetExchange = allPetExchange;
    }
    
    public static ConcurrentHashMap<Integer, PetExchange> getAllPetExchange() {
        return GameServer.allPetExchange;
    }
    
    public static void saveEventRoles() {
        EventRanking eventRanking = new EventRanking();
        Map<Integer, RoleCard[]> map = new HashMap<>();
        for (Map.Entry<Integer, EventModel> entrys : GameServer.allevent.entrySet()) {
            EventModel value = (EventModel)entrys.getValue();
            if (value.getRoles() != null) {
                map.put(Integer.valueOf(value.getgId()), value.getRoles());
            }
        }
        eventRanking.setMap(map);
        try {
            CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "event.txt", GsonUtil.getGsonUtil().getgson().toJson(eventRanking).getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<Integer, List<ChongjipackBean>> getPackgradecontrol() {
        return GameServer.packgradecontrol;
    }
    
    public static void setPackgradecontrol(ConcurrentHashMap<Integer, List<ChongjipackBean>> packgradecontrol) {
        GameServer.packgradecontrol = packgradecontrol;
    }
    
    public static ChongjipackBean getChongjipackBean(Integer id) {
        if (GameServer.packgradecontrols != null) {
            return (ChongjipackBean)GameServer.packgradecontrols.get(id);
        }
        return null;
    }
    
    public static ConcurrentHashMap<Integer, ChongjipackBean> getPackgradecontrols() {
        return GameServer.packgradecontrols;
    }
    
    public static void setPackgradecontrols(ConcurrentHashMap<Integer, ChongjipackBean> packgradecontrols) {
        GameServer.packgradecontrols = packgradecontrols;
    }
    
    public static void setPayvipList(List<PayvipBean> payvipList) {
        GameServer.payvipList = payvipList;
    }
    
    public static List<PayvipBean> getPayvipList() {
        return GameServer.payvipList;
    }
    
    public static void refreshBean() {
        List<ChongjipackBean> chongjipackBeans = AllServiceUtil.getChongjipackServeice().selectAllPack();
        GameServer.packgradecontrol = new ConcurrentHashMap<>();
        for (int i = 0; i < chongjipackBeans.size(); ++i) {
            ChongjipackBean bean = (ChongjipackBean)chongjipackBeans.get(i);
            List<ChongjipackBean> packs = (List<ChongjipackBean>)getPackgradecontrol().get(bean.getPacktype());
            if (packs == null) {
                packs = new ArrayList<>();
                getPackgradecontrol().put(bean.getPacktype(), packs);
            }
            packs.add(bean);
        }
        GameServer.payvipList = AllServiceUtil.getPayvipBeanServer().selectAllVip();
    }
    
    public static int getQh() {
        return GameServer.qh;
    }
    
    public static int getActiveValue(RoleData roleData) {
        int value = 0;
        for (int i = 0; i < GameServer.allActive.getBases().length; ++i) {
            ActiveBase activeBase = GameServer.allActive.getBases()[i];
            int num = roleData.getTaskWC(activeBase.getSid());
            if (num > activeBase.getNum()) {
                num = activeBase.getNum();
            }
            value += num * activeBase.getValue();
        }
        return value;
    }
    
    public static ActiveAward getActiveAward(int i) {
        if (i < 0) {
            return null;
        }
        if (i < GameServer.allActive.getAwards().length) {
            return GameServer.allActive.getAwards()[i];
        }
        return null;
    }
    
    public static void setAllActive(AllActive allActive) {
        GameServer.allActive = allActive;
    }
    
    public static Achieve getAchieve(int id) {
        return (Achieve)GameServer.allAchieve.get(Integer.valueOf(id));
    }
    
    public static void setAllAchieve(ConcurrentHashMap<Integer, Achieve> allAchieve) {
        GameServer.allAchieve = allAchieve;
    }
    
    public static AllLianHua getAllLianHua() {
        return GameServer.allLianHua;
    }
    
    public static void setAllLianHua(AllLianHua all) {
        GameServer.allLianHua = all;
    }
    
    public static AllMeridians getAllMeridians() {
        return GameServer.allMeridians;
    }
    
    public static void setAllMeridians(AllMeridians allMeridians) {
        GameServer.allMeridians = allMeridians;
    }
    
    public static Goodstable getgoods(BigDecimal id) {
        Goodstable goods = (Goodstable)GameServer.allitem.get(id);
        if (goods != null) {
            return goods.clone();
        }
        return goods;
    }
    
    public static ItemExchange getItemExchange(int id) {
        return (ItemExchange)GameServer.allItemExchange.get(Integer.valueOf(id));
    }
    
    public static void setAllItemExchange(ConcurrentHashMap<Integer, ItemExchange> allItemExchange) {
        GameServer.allItemExchange = allItemExchange;
    }
    
    public static ConcurrentHashMap<BigDecimal, Goodstable> getAllitem() {
        return GameServer.allitem;
    }
    
    public static void setAllitem(ConcurrentHashMap<BigDecimal, Goodstable> allitem) {
        GameServer.allitem = allitem;
    }
    
    public static GoodsExchange getGoodsExchange(int id) {
        return (GoodsExchange)GameServer.allGoodsExchange.get(Integer.valueOf(id));
    }
    
    public static void setAllGoodsExchange(ConcurrentHashMap<Integer, GoodsExchange> allGoodsExchange) {
        GameServer.allGoodsExchange = allGoodsExchange;
    }
    
    public static String getProperty() {
        String serial = "";
        try {
            long start = System.currentTimeMillis();
            Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            serial = sc.next();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return serial;
    }
    
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
    
    public static String getLoginCheck() {
        return GameServer.loginCheck;
    }
    
    public static String decryptByPublicKey(String content) throws Exception {
        PublicKey publicKey = getPublicKey("MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwA5bvsBpZkn6uNFgBdxT\r\ng939ldFNJ3B+K7WqhXxWVsLZj64cUKDAnm7WPkRgdrfqp7rfgfdCeGHAaSd2/j4P\r\nx4ywotMCuVIcjxIZTwS+pSZMaNLt9JC5qygYoiPXV4U2GsOA2UfMiLcoO04yfAV4\r\n/vACGNNLhonZ2Y/0q58g32F3VDmNmD7jSrcHREWB3n87qGgxgGCh8LCXaVjpEFoo\r\nfO3j/MsMlyXbs0gZseFzS8JjDB+v6VImL1eu6amW82bfEVXt6eKcrWNpPRXFAac5\r\n031X9ppcrCXh0W2OB+4qtp8Fuu8Z8aEtA91BFmne2reDcrfJEnRmj0S1CK+tzYwG\r\nDdhlY5bzPMkvuta6Lpt8wF/N3yhI6Sgs9P6K5rlBJCrI6iIQsSchyZJEjKir1lWv\r\n11Icu575q0q36JhDwmtT9Op/5xhAZykvh28eNPigd/VcxkwykE0ekN06ElsDDs+X\r\nk+uzO0Uo/ReX7j3kyUGla6h9L5bpS+7M4gx/aQcYCbBXPqxv6+eeHErj2AoG5H8R\r\nv3UmlKX3kvLpLYV2UG6ub5B9nCRJPC98S/RTZN8Jes8tUg54N6/9PdJndwdCQsUv\r\ne1hDvynUKu9/J6YNGqiS2efvHOCL+HFk3l6VAywZRtUxuzDh8gY+U2MiCrMqlRA/\r\n8/Ad1wL8I+LO2E+EF9zPgcMCAwEAAQ==");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, publicKey);
        byte[] cipherText = new BASE64Decoder().decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }
    
    public static ConcurrentHashMap<String, List<WitchComposeAttr>> getAllSwitchAttr() {
        return GameServer.allSwitchAttr;
    }
    
    public static void setAllSwitchAttr(ConcurrentHashMap<String, List<WitchComposeAttr>> allSwitchAttr) {
        GameServer.allSwitchAttr = allSwitchAttr;
    }
    
    public static List<LiangHaoBase> getLianghaos() {
        return GameServer.lianghaos;
    }
    
    public static void setLianghaos(List<LiangHaoBase> lianghaos) {
        GameServer.lianghaos = lianghaos;
    }
    
    public static List<SellLiangHaoBase> getSelllianghaos() {
        return GameServer.selllianghaos;
    }
    
    public static void setSelllianghaos(List<SellLiangHaoBase> selllianghaos) {
        GameServer.selllianghaos = selllianghaos;
    }
    
    public static List<SellLiangHaoBase> getUpselllianghaos() {
        return GameServer.upselllianghaos;
    }
    
    public static void setUpselllianghaos(List<SellLiangHaoBase> upselllianghaos) {
        GameServer.upselllianghaos = upselllianghaos;
    }
    
    public static ConcurrentHashMap<String, String> getAlltitleColor() {
        if (GameServer.alltitleColor == null) {
            GameServer.alltitleColor = new ConcurrentHashMap<>();
        }
        return GameServer.alltitleColor;
    }
    
    public static void setAlltitleColor(ConcurrentHashMap<String, String> alltitleColor) {
        GameServer.alltitleColor = alltitleColor;
    }
    
    public static List<LotteryItemBase> getLotteryitems() {
        return GameServer.lotteryitems;
    }
    
    public static void setLotteryitems(List<LotteryItemBase> lotteryitems) {
        GameServer.lotteryitems = lotteryitems;
    }
    
    public static List<LotteryItemBasetwo> getLotteryitemstwo() {
        return GameServer.lotteryitemstwo;
    }
    
    public static void setLotteryitemstwo(List<LotteryItemBasetwo> lotteryitemstwo) {
        GameServer.lotteryitemstwo = lotteryitemstwo;
    }
    
    public static AllAuctionGoodsExchange getAllAuctionGoodsExchange() {
        return GameServer.allAuctionGoodsExchange;
    }
    
    public static void setAllAuctionGoodsExchange(AllAuctionGoodsExchange allAuctionGoodsExchange) {
        GameServer.allAuctionGoodsExchange = allAuctionGoodsExchange;
    }
    
    public static Map<String, String> getXjDate() {
        return GameServer.xjDate;
    }
    
    public static void setXjDate(Map<String, String> xjDate) {
        GameServer.xjDate = xjDate;
    }
    
    public static ConcurrentHashMap<String, List<GolemDraw>> getAllGolemDraw() {
        return GameServer.allGolemDraw;
    }
    
    public static void setAllGolemDraw(ConcurrentHashMap<String, List<GolemDraw>> allGolemDraw) {
        GameServer.allGolemDraw = allGolemDraw;
    }
    
    public static List<GolemActive> getGolemActives(Integer... types) {
        return (List)GameServer.golemActives.stream().filter(t/* org.come.model.GolemActive, */ -> Arrays.asList(types).contains(Integer.valueOf(t.getType()))).collect(Collectors.toList());
    }
    
    public static List<GolemActive> getGolemActives() {
        return GameServer.golemActives;
    }
    
    public static void setGolemActives(List<GolemActive> golemActives) {
        GameServer.golemActives = golemActives;
    }
    
    public static GolemConfig getGolemConfig() {
        if (GameServer.golemConfig == null) {
            GameServer.golemConfig = new GolemConfig();
        }
        return GameServer.golemConfig;
    }
    
    public static List<ColorScheme> getAllListColor() {
        if (GameServer.allListColor == null) {
            GameServer.allListColor = new ArrayList<>();
        }
        return GameServer.allListColor;
    }

    public static void setAllListColor(List<ColorScheme> allListColor) {
        GameServer.allListColor = allListColor;
    }
    /**
     * 自动机器人
     */
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> allBaiTan;
    public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> getAllBaiTan() {
        return allBaiTan;
    }

    public static void setAllBaiTan(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> allBaiTan) {
        GameServer.allBaiTan = allBaiTan;
    }
    public static void setAllAchievement(ConcurrentHashMap<Integer, Achievement> allAchievement) {
        GameServer.allAchievement = allAchievement;
    }


    static {
        GameServer.time = "2028-04-01-0-0-0";//授权时间
        GameServer.signNum = "ae82a5a093ef80266dc4fe0f5c70e98a";
        GameServer.redisIp = "127.0.0.1";
        GameServer.redisPort = 6379;
        GameServer.isCode = false;
        GameServer.getAllKJexamQuestions = new ConcurrentHashMap<>();
        GameServer.OPEN = false;
        GameServer.userLock = new Object();
        GameServer.random = new Random();
        GameServer.gemns = new String[] { "赤焰石", "芙蓉石", "寒山石", "孔雀石", "琉璃石", "落星石", "沐阳石", "紫烟石" };
    }
    private static TaskListAll  TASK_LIST;
    public static TaskListAll getTASK_LIST() {
        return TASK_LIST;
    }
    public static void setTaskList(TaskListAll taskList) {
        TASK_LIST = taskList;
    }
    public static ConcurrentHashMap<Integer, Achievement> getAchievementAll() {
        return allAchievement;
    }
    private static ConcurrentHashMap<Integer, XuanBao> allxuanbaos;

    public static ConcurrentHashMap<Integer, XuanBao> getAllxuanbaos() {
        return allxuanbaos;
    }

    public static void setAllxuanbaos(ConcurrentHashMap<Integer, XuanBao> allxuanbaos) {
        GameServer.allxuanbaos = allxuanbaos;
    }
    public static Goodstable getGood_xuanbao(int id, int type) {
        Goodstable goodstables = getGood(new BigDecimal(type));
        if (goodstables == null) {
            return null;
        }
        Goodstable goodstable = goodstables.clone();
        String[] v = goodstable.getValue().split("\\|");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            if (v[i].startsWith("等级")) {
                buffer.append("等级=").append(id).append("|");
            } else {
                buffer.append(v[i].split("=")[0]).append("=").append(Double.parseDouble(v[i].split("=")[1]) * id).append("|");
            }
        }
        goodstable.setValue(buffer.toString());
        return goodstable;
    }
}
