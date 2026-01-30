package org.come.task;

import org.come.model.LotteryRole;
import org.come.model.LotteryData;
import org.come.until.DataUtil;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import org.come.redis.RedisParameterUtil;
import org.come.redis.RedisPoolUntil;
import org.come.server.GolemServer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import org.come.model.Lshop;
import org.come.model.Eshop;
import org.come.model.Shop;
import org.come.entity.UserxyandroledhbcrEntity;
import org.come.entity.GoodssaledayrecordEntity;
import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.UserTable;
import java.util.Random;
import org.come.model.TaskSet;
import come.tool.newTask.TaskRecord;
import come.tool.Role.PrivateData;
import come.tool.Role.RoleData;
import come.tool.newTask.TaskUtil;
import org.come.bean.Middle;
import come.tool.teamArena.LadderArenaAction;
import org.come.until.QmjcUtil;
import java.util.ArrayList;
import come.tool.Scene.SceneUtil;
import come.tool.PK.PKPool;
import come.tool.newGang.GangUtil;
import come.tool.teamArena.TeamArenaUtil;
import come.tool.oneArena.OneArenaAction;
import java.time.LocalDateTime;
import org.apache.commons.lang.StringUtils;
import java.util.concurrent.ConcurrentHashMap;
import org.come.service.TtModelService;
import org.come.action.reward.DrawnitemsAction;
import java.math.BigDecimal;
import java.util.Map;
import java.time.temporal.ChronoUnit;
import come.tool.teamArena.LadderArenaUtil;
import java.time.LocalTime;
import org.come.model.Configure;
import org.come.bean.TtModel;
import java.util.stream.Collectors;
import org.come.thread.RedisEqualWithSqlThread;
import come.tool.Scene.LTS.LTSUtil;
import come.tool.BangBattle.BangBattlePool;
import org.come.redis.RedisCacheUtil;
import org.come.action.buy.BuyUtil;
import come.tool.Battle.RewardLimit;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.LoginResult;
import java.util.Iterator;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.model.LiangHaoInfo;
import come.tool.Stall.AssetUpdate;
import org.come.entity.RoleTable;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Role.RolePool;
import org.come.entity.SellLianghaoAuc;
import org.come.until.AllServiceUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.handler.MainServerHandler;
import org.come.tool.WriteOut;
import java.util.Locale;
import org.come.server.GameServer;
import come.tool.Scene.LaborDay.LaborScene;
import java.util.Calendar;

public class RefreshMonsterTask implements Runnable
{
    private static String[] str;
    public static int day;
    public static int second;
    public static int minute;
    public static int hour;
    public static Calendar rightNow;
    
    public RefreshMonsterTask() {
        MonsterUtil.init();
    }
    
    @Override
    public void run() {
        LaborScene.init();
        while (true) {
            if (this.handleTime()) {
                try {
                    Thread.sleep(60000L);
                }
                catch (Exception ex) {}
            }
            else if (GameServer.OPEN) {
                break;
            }
            else {
                this.handleRole();
                this.handleGolem();
                this.handleEvent();
                this.handleOther();
                this.handleLiangHaoAuc();
                handleTt();
                if ((boolean)this.isGQ()) {
                    GameServer.gameServer.contextDestroyed(null);
                }
                else {
                    continue;
                }
                if(hour == 0 && minute == 0) {
                    try {
                        updateAllresetLevel();
                    } catch (Exception e) {
                        System.err.println("处理八十一难错误！！");
                    }
                }
            }
        }
    }
    
    public boolean handleTime() {
        try {
            RefreshMonsterTask.rightNow = Calendar.getInstance(Locale.CHINA);
            RefreshMonsterTask.second = RefreshMonsterTask.rightNow.get(13);
            RefreshMonsterTask.minute = RefreshMonsterTask.rightNow.get(12);
            Thread.sleep((long)((60 - RefreshMonsterTask.second + (4 - RefreshMonsterTask.minute % 5) * 60) * 1000));
            RefreshMonsterTask.rightNow = Calendar.getInstance(Locale.CHINA);
            RefreshMonsterTask.day = RefreshMonsterTask.rightNow.get(7);
            RefreshMonsterTask.hour = RefreshMonsterTask.rightNow.get(11);
            RefreshMonsterTask.minute = RefreshMonsterTask.rightNow.get(12);
            RefreshMonsterTask.second = RefreshMonsterTask.rightNow.get(13);
            if (RefreshMonsterTask.minute % 5 != 0) {
                RefreshMonsterTask.minute /= 5;
                RefreshMonsterTask.minute *= 5;
                WriteOut.addtxt("时间不对:" + RefreshMonsterTask.minute, 9999L);
            }
        }
        catch (Exception e) {
            WriteOut.addtxt("刷新线程:" + MainServerHandler.getErrorMessage(e), 9999L);
            return true;
        }
        return false;
    }
    
    public void handleLiangHaoAuc() {
        if (RefreshMonsterTask.hour == 0 && RefreshMonsterTask.minute == 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String todayStr = sdf.format(new Date());
                List<SellLianghaoAuc> records = AllServiceUtil.getSellLianghaoAucService().selectAllByToday(todayStr, Short.valueOf((short)1));
                if (records != null && records.size() > 0) {
                    List<String> clhs = AllServiceUtil.getRoleTableService().allLiangHao();
                    for (SellLianghaoAuc item : records) {
                        LoginResult login = RolePool.getLoginResult(item.getBuyRoleId());
                        ChannelHandlerContext tCtx = (login != null) ? ((ChannelHandlerContext)GameServer.getRoleNameMap().get(login.getRolename())) : null;
                        RoleTable roleTable = new RoleTable();
                        roleTable.setLiangHao(item.getLianghao());
                        roleTable.setRole_id(item.getBuyRoleId());
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(6, 30);
                        Date nextMonth = calendar.getTime();
                        String expireStr = sdf.format(nextMonth);
                        roleTable.setLianghaoexpire(expireStr);
                        roleTable.setContinueprice(item.getOriginalprice());
                        if (login.getLianghaotype() == null) {
                            roleTable.setLianghaotype(Integer.valueOf(5));
                        }
                        else {
                            roleTable.setLianghaotype(login.getLianghaotype());
                        }
                        AllServiceUtil.getRoleTableService().getLiangHao(roleTable);
                        if (tCtx != null) {
                            login.setLiangHao(item.getLianghao());
                            AssetUpdate assetUpdate2 = new AssetUpdate();
                            LiangHaoInfo liangHaoInfo = new LiangHaoInfo();
                            liangHaoInfo.setLianghao(item.getLianghao());
                            liangHaoInfo.setType((int)roleTable.getLianghaotype());
                            liangHaoInfo.setExpTime(roleTable.getLianghaoexpire());
                            assetUpdate2.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo));
                            SendMessage.sendMessageToSlef(tCtx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        }
                    }
                }
                List<RoleTable> expLhs = AllServiceUtil.getRoleTableService().selectExpLh();
                if (expLhs != null && expLhs.size() > 0) {
                    for (RoleTable item2 : expLhs) {
                        String lh = item2.getLiangHao();
                        item2.setLianghaotype(null);
                        item2.setLiangHao(null);
                        item2.setLianghaoexpire(null);
                        item2.setContinueprice(null);
                        AllServiceUtil.getRoleTableService().dropLiangHao(item2);
                        LoginResult login2 = RolePool.getLoginResult(item2.getRole_id());
                        ChannelHandlerContext tCtx2 = (login2 != null) ? ((ChannelHandlerContext)GameServer.getRoleNameMap().get(login2.getRolename())) : null;
                        if (tCtx2 != null) {
                            login2.setLiangHao(null);
                            login2.setLianghaotype(null);
                            login2.setContinueprice(null);
                            login2.setLianghaoexpire(null);
                            String msg2 = Agreement.getAgreement().searchSellLiangHao("EXPLIANGHAO|" + lh);
                            SendMessage.sendMessageToSlef(tCtx2, msg2);
                        }
                    }
                }
            }
            catch (Exception e) {
                String abc = "0点靓号批处理:" + MainServerHandler.getErrorMessage(e);
                System.out.println(abc);
                WriteOut.addtxt(abc, 9999L);
            }
        }
    }
    
    public void handleOther() {
        try {
            if (RefreshMonsterTask.minute == 0) {
                MonitorUtil.toSting();
            }
            if (RefreshMonsterTask.hour == 0 && RefreshMonsterTask.minute == 0) {
                RewardLimit.reset();
                BuyUtil.reset();
                MonitorUtil.reset();
                MonitorUtil.HdxtoSting();
                RedisCacheUtil.resetOneArenaTime();
                if (RefreshMonsterTask.day == 2) {
                    BangBattlePool.getBangBattlePool().NewWeek();
                }
                shangChengGoodsSum();
                userRoleXianyuSum();
            }
            if (RefreshMonsterTask.minute % 10 == 0) {
                RedisCacheUtil.reset();
                RolePool.checkRoleData();
            }
            if (RefreshMonsterTask.hour == 5 && RefreshMonsterTask.minute == 0) {
                saveRoleInfo();
                GameServer.bangLists();
                GameServer.resetBbuy();
                MonitorUtil.ASSize();
                LTSUtil.getLtsUtil().bangList(RefreshMonsterTask.str[RefreshMonsterTask.day - 1]);
            }
            if ((RefreshMonsterTask.hour == 2 || RefreshMonsterTask.hour == 6 || RefreshMonsterTask.hour == 10 || RefreshMonsterTask.hour == 14 || RefreshMonsterTask.hour == 18 || RefreshMonsterTask.hour == 22) && RefreshMonsterTask.minute == (GameServer.getPortNumber() - 7100) * 5) {
                RedisEqualWithSqlThread.AllToDatabase();
            }
            if (RefreshMonsterTask.hour == 5 && RefreshMonsterTask.minute == 30) {
                importantGoodsSum();
            }
            if (RefreshMonsterTask.hour != 23 || RefreshMonsterTask.minute == 55) {}
        }
        catch (Exception e) {
            WriteOut.addtxt("刷新线程:日志类" + MainServerHandler.getErrorMessage(e), 9999L);
        }
    }
    
    public static void handleTt() {
        TtModelService ttModelService = AllServiceUtil.getTtModelService();
        List<TtModel> ttConfig = ttModelService.getTtConfig();
        List<TtModel> openTT = (List)ttConfig.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 1).collect(Collectors.toList());
        List<TtModel> openTT2 = (List)ttConfig.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 3).collect(Collectors.toList());
        TtModel ttModel = null;
        if (openTT.size() != 0) {
            ttModel = openTT.get(0);
        }
        else if (openTT2.size() != 0) {
            ttModel = openTT2.get(0);
        }
        if (ttModel == null) {
            return;
        }
        Calendar startInstance = Calendar.getInstance();
        startInstance.setTime(ttModel.getSeasonStartTime());
        Calendar endInstance = Calendar.getInstance();
        endInstance.setTime(ttModel.getSeasonEndTime());
        Calendar currInstance = Calendar.getInstance(Locale.CHINA);
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(7));
        String ttzq = configure.getZqsld();
        LocalTime ttks = LocalTime.parse(configure.getZqjnsx());
        LocalTime ttjs = LocalTime.parse(configure.getJumpurl());
        LocalTime dateTime = LocalTime.of(RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
        if (ttModel.getIsOpen() == 1 && ttzq.contains((RefreshMonsterTask.day-1) + "") && ttks.equals(dateTime)) {
            LadderArenaUtil.teamArenaOpen();
        }
        if (currInstance.after(endInstance) && ttModel.getIsOpen() == 1) {
            ttModel.setIsOpen(Integer.valueOf(3));
            ttModelService.updateTtConfig(ttModel);
        }
        if (currInstance.after(endInstance) && (int)ttModel.getIsOpen() == 3) {
            long daysDifference = ChronoUnit.DAYS.between(endInstance.toInstant(), currInstance.toInstant());
            if (daysDifference > 3L) {
                try {
                    for (Map.Entry<ChannelHandlerContext, LoginResult> entrys : GameServer.getAllLoginRole().entrySet()) {
                        LoginResult value = (LoginResult)entrys.getValue();
                        if ((int)value.getTtFail() == 0 && (int)value.getTtVictory() == 0 && value.getTTJIANGLI().equals("0|0|0|0|0|0") && value.getTtRecord().equals(new BigDecimal(0))) {
                            continue;
                        }
                        else {
                            AllServiceUtil.getRoleTableService().upTTJJ(value.getRole_id());
                            value.setTTJIANGLI("0|0|0|0|0|0");
                            BigDecimal ttScore = value.getScoretype("天梯积分");
                            value.setScore(DrawnitemsAction.Splice(value.getScore(), "天梯积分=" + ttScore.intValue(), 3));
                            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                            assetUpdate.setTtVictory(value.getTtVictory());
                            assetUpdate.setTtFail(value.getTtFail());
                            SendMessage.sendMessageByRoleName(value.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
    
    public void handleEvent() {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        Configure configure2 = (Configure)s.get(Integer.valueOf(7));
        String BZSJ = configure.getBzsjsz();
        String ttzq = configure2.getZqsld();
        LocalTime ttks = LocalTime.parse(configure2.getZqjnsx());
        try {
            LaborScene.upLaborScene(RefreshMonsterTask.hour, RefreshMonsterTask.minute);
            LocalTime dateTime = LocalTime.of(RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
            LocalTime BZSJSZ = configure.getBzsjsz1();
            if (BZSJ.contains(RefreshMonsterTask.day - 1 + "") && dateTime.equals(BZSJSZ)) {
                BangBattlePool.getBangBattlePool().FightOpenClose();
            }
            if (RefreshMonsterTask.hour == 21 && RefreshMonsterTask.minute == 0) {
                OneArenaAction.OneArenaReset();
            }
            if (RefreshMonsterTask.hour == 20 && RefreshMonsterTask.minute == 0) {
                TeamArenaUtil.teamArenaOpen();
            }
            if (RefreshMonsterTask.minute == 5) {
                upBuyCount(RefreshMonsterTask.day, RefreshMonsterTask.hour == 0);
            }
            if (RefreshMonsterTask.minute == 10) {
                GangUtil.upGangs(true);
            }
            PKPool.getPkPool().OVERTIME();
            SceneUtil.activityOpen(RefreshMonsterTask.str[RefreshMonsterTask.day - 1], RefreshMonsterTask.day, RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
            MonsterUtil.refurbishMonster(RefreshMonsterTask.str[RefreshMonsterTask.day - 1], RefreshMonsterTask.day, RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
        }
        catch (Exception e) {
            WriteOut.addtxt("刷新线程:" + MainServerHandler.getErrorMessage(e), 9999L);
        }
    }
    
    public void handleRole() {
        try {
            if (RefreshMonsterTask.hour == 0 && RefreshMonsterTask.minute == 0) {
                QmjcUtil.lotteryDataList = new ArrayList<>();
                QmjcUtil.lotteryRoleRecordList = new ArrayList<>();
                int type = (RefreshMonsterTask.day == 2) ? 2 : 1;
                Iterator<Map.Entry<ChannelHandlerContext, LoginResult>> reset = GameServer.getAllLoginRole().entrySet().iterator();
                while (reset.hasNext()) {
                    try {
                        Map.Entry<ChannelHandlerContext, LoginResult> entrys = (Map.Entry<ChannelHandlerContext, LoginResult>)reset.next();
                        taskReset((ChannelHandlerContext)entrys.getKey(), (LoginResult)entrys.getValue(), type);
                    }
                    catch (Exception e) {
                        String abc = "0点刷新线程:" + MainServerHandler.getErrorMessage(e);
                        System.out.println(abc);
                        WriteOut.addtxt(abc, 9999L);
                    }
                }
                GameServer.golemServer.reset();
                deleteKeyByKname();
            }
        }
        catch (Exception e2) {
            WriteOut.addtxt("刷新线程:日志类" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
    }
    
    public static void taskReset(ChannelHandlerContext ctx, LoginResult loginResult, int type) {
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        PrivateData data = roleData.getPrivateData();
        boolean is = ResetTask(loginResult,roleData.getTaskRecordMap(), type);
        int DBExp = 0;
        BigDecimal Dayandpayorno = loginResult.getDayandpayorno();
        int Dayfirstinorno = loginResult.getDayfirstinorno();
        BigDecimal Daypaysum = loginResult.getDaypaysum();
        BigDecimal Daygetorno = loginResult.getDaygetorno();
        String Vipget = loginResult.getVipget();
        if (Dayfirstinorno == 0 || loginResult.getDayandpayorno().longValue() >= 7L) {
            loginResult.setDayandpayorno(new BigDecimal(0));
            loginResult.removeVipget("3");
        }
        loginResult.setDayfirstinorno(0);
        loginResult.setDaypaysum(new BigDecimal(0));
        loginResult.setDaygetorno(new BigDecimal(2));
//        loginResult.setFuben(null);
        AllServiceUtil.getRoleTableService().updateDAYDRAW("");
        loginResult.setDayDraw("");
        loginResult.removeVipget("2");
        LadderArenaAction.restTTJL(loginResult);
        String Vipget2 = loginResult.getVipget();
        if (Vipget == null) {
            Vipget = "";
        }
        if (Vipget2 == null) {
            Vipget2 = "";
        }
        if (!is && (int)data.getDBExp() == DBExp && loginResult.getDayandpayorno().compareTo(Dayandpayorno) == 0 && loginResult.getDaypaysum().compareTo(Daypaysum) == 0 && loginResult.getDaygetorno().compareTo(Daygetorno) == 0 && Vipget.equals(Vipget2) && loginResult.getDayfirstinorno() == Dayfirstinorno) {
            return;
        }
        Middle middle = new Middle();
        data.setDBExp(Integer.valueOf(DBExp));
        String taskComplete = is ? TaskUtil.toTaskRecord(roleData.getTaskRecordMap()) : null;
        if (is) {
            data.setTaskComplete(taskComplete);
            middle.setTaskComplete(taskComplete);
        }
        middle.setRolename(loginResult.getRolename());
        middle.setTaskDaily(loginResult.getTaskDaily());
        middle.setDaypaysum(loginResult.getDaypaysum());
        middle.setDaygetorno(loginResult.getDaygetorno());
        middle.setDayandpayorno(loginResult.getDayandpayorno());
        middle.setVipget(loginResult.getVipget());
        middle.setDayfirstinorno(loginResult.getDayfirstinorno());
        String mes = Agreement.getAgreement().MiddleAgreement(GsonUtil.getGsonUtil().getgson().toJson(middle));
        SendMessage.sendMessageToSlef(ctx, mes);
    }
    
    public static boolean ResetTask(LoginResult loginResult,ConcurrentHashMap<Integer, TaskRecord> map, int type) {
        boolean is = false;
        String fube= loginResult.getFuben();
        String[] fuben = new String[0];
        if (fube != null) {
            fuben=fube.split("\\|");
        }
        loginResult.setFuben(null);
        for (Map.Entry<Integer, TaskRecord> item : map.entrySet()) {
            TaskRecord record = (TaskRecord)item.getValue();
            TaskSet taskSet = GameServer.getTaskSet(record.getTaskId());
            if (GameServer.boosesMap.get(Integer.valueOf(record.getTaskId())) != null) {
                map.remove(item.getKey());
                is = true;
            }
            else if (GameServer.getAllRobot().get(record.getTaskId() + "") != null) {
                map.remove(item.getKey());
                is = true;
            }
            else if (taskSet != null) {
                if (taskSet.getResetcycle() == 0) {
                    continue;
                }
                else if (type >= taskSet.getResetcycle()) {
                    try {
                        if (fuben != null && fuben.length > 0) {
                            for (int i = 0; i < fuben.length; i++) {
                                if (fuben[i] != null && fuben[i].equals(String.valueOf(taskSet.getTaskSetID()))) {
                                    fuben[i] = null;
                                }
                            }
                        }
                        map.remove(item.getKey());
                        is = true;
                    } catch (Exception e) {
//                        throw new RuntimeException(e);
                    }
                }
                else {
                    continue;
                }
            }
            else {
                continue;
            }
        }
        if (fuben != null&&fuben.length>0) {
            for (int i = 0; i < fuben.length; i++) {
                if (fuben[i]!=null) {
                    loginResult.addfuben(fuben[i]);
                }
            }
        }
        return is;
    }
    
    public static String ResetTask(String record, int type) {
        if (record == null || record.equals("")) {
            return record;
        }
        StringBuffer buffer = new StringBuffer();
        String[] vs = record.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] rs = vs[i].split("-");
            TaskSet taskSet = GameServer.getTaskSet(Integer.parseInt(rs[0]));
            if (taskSet == null || taskSet.getResetcycle() == 0 || type < taskSet.getResetcycle()) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(vs[i]);
            }
        }
        return (buffer.length() != 0) ? buffer.toString() : null;
    }
    
    public static List<Integer> randomArray(int max, int n) {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int ran = random.nextInt(max);
            result.add(Integer.valueOf(ran));
        }
        return result;
    }
    
    public static void saveRoleInfo() {
        for (Map.Entry<ChannelHandlerContext, LoginResult> entrys : GameServer.getAllLoginRole().entrySet()) {
            LoginResult loginResult = (LoginResult)entrys.getValue();
            if (loginResult == null) {
                continue;
            }
            else {
                try {
                    UserTable userTable = new UserTable();
                    userTable.setCodecard(loginResult.getCodecard());
                    userTable.setMoney(loginResult.getMoney());
                    userTable.setUsername(loginResult.getUserName());
                    AllServiceUtil.getUserTableService().updateUser(userTable);
                    RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                    loginResult.setUptime(System.currentTimeMillis() + "");
                    roleData.roleRecover(loginResult);
                    AllServiceUtil.getRoleTableService().updateRoleWhenExit(loginResult);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("保存玩家信息失败：" + loginResult.getRolename());
                }
            }
        }
    }
    
    public static void importantGoodsSum() {
        List<ImportantgoodssumrecordEntity> selectImportantGoods = AllServiceUtil.getImportantgoodtrcordService().selectImportantGoods();
        for (int i = 0; i < selectImportantGoods.size(); ++i) {
            try {
                int addImporatantGoodsSum = AllServiceUtil.getImportantgoodtrcordService().addImporatantGoodsSum((ImportantgoodssumrecordEntity)selectImportantGoods.get(i));
                if (addImporatantGoodsSum < 0) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("统计重要物资数量记录失败，物品ID：" + ((ImportantgoodssumrecordEntity)selectImportantGoods.get(i)).getGid());
            }
        }
    }
    
    public static void shangChengGoodsSum() {
        List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList = AllServiceUtil.getGoodsTableService().selectGoodsBuyRecordSumList();
        for (int i = 0; i < selectGoodsBuyRecordSumList.size(); ++i) {
            try {
                int addImporatantGoodsSum = AllServiceUtil.getGoodsTableService().addGoodssaledayrecord((GoodssaledayrecordEntity)selectGoodsBuyRecordSumList.get(i));
                if (addImporatantGoodsSum < 0) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("统计商城购买记录失败，物品ID：" + ((GoodssaledayrecordEntity)selectGoodsBuyRecordSumList.get(i)).getGid());
            }
        }
    }
    
    public static void userRoleXianyuSum() {
        List<UserxyandroledhbcrEntity> selectUserRoleXianyuDahuabiList = AllServiceUtil.getUserTableService().selectUserRoleXianyuDahuabiList();
        for (int i = 0; i < selectUserRoleXianyuDahuabiList.size(); ++i) {
            try {
                int addImporatantGoodsSum = AllServiceUtil.getUserTableService().addUserRoleXianyuDahuabi((UserxyandroledhbcrEntity)selectUserRoleXianyuDahuabiList.get(i));
                if (addImporatantGoodsSum < 0) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("玩家/角色大话币仙玉操作统计记录，用户ID：" + ((UserxyandroledhbcrEntity)selectUserRoleXianyuDahuabiList.get(i)).getUserid() + "角色ID：" + ((UserxyandroledhbcrEntity)selectUserRoleXianyuDahuabiList.get(i)).getRoleid());
            }
        }
    }
    
    public static void upBuyCount(int day, boolean isReset) {
        ConcurrentHashMap<String, Shop> shopMap = GameServer.getAllShopGoods();
        if (shopMap != null) {
            for (Shop value : shopMap.values()) {
                value.getBuyCount().upData();
                if (isReset) {
                    value.getBuyCount().Reset(day);
                }
            }
        }
        ConcurrentHashMap<String, Eshop> eShopMap = GameServer.getAllEshopGoods();
        if (eShopMap != null) {
            for (Eshop value2 : eShopMap.values()) {
                value2.getBuyCount().upData();
                if (isReset) {
                    value2.getBuyCount().Reset(day);
                }
            }
        }
        ConcurrentHashMap<String, Lshop> lShopMap = GameServer.getAllLShopGoods();
        if (lShopMap != null) {
            for (Lshop value3 : lShopMap.values()) {
                value3.getBuyCount().upData();
                if (isReset) {
                    value3.getBuyCount().Reset(day);
                }
            }
        }
    }
    
    public static void createTableSplace(int num) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.add(5, num);
        Date temp = cld.getTime();
        String nextDay = formatter.format(temp);
        Set<Map.Entry<String, String>> entrySet = GameServer.tableZone.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String tableName = (String)entry.getKey();
            String tableSpace = (String)entry.getValue();
            String tableSpaceDay = tableSpace + nextDay;
            int selectTableSapce = AllServiceUtil.getImportantgoodtrcordService().selectTableSapce(tableSpaceDay);
            if (selectTableSapce == 0) {
                AllServiceUtil.getImportantgoodtrcordService().addImporatantGoodsLuTableSpace(nextDay, tableSpaceDay, GameServer.tablePath);
            }
            int selectTablePartition = AllServiceUtil.getImportantgoodtrcordService().selectTablePartition(tableSpaceDay, tableName);
            if (selectTablePartition == 0) {
                AllServiceUtil.getImportantgoodtrcordService().addTableImporatantGoodsLuTableSpace(nextDay, tableSpaceDay, tableName);
            }
        }
    }
    
    public static String getWeek() {
        return RefreshMonsterTask.str[RefreshMonsterTask.day - 1];
    }
    
    public void handleGolem() {
        if (!GameServer.OPEN && GolemServer.OPEN) {
            GameServer.golemServer.assignGolem();
            GameServer.golemServer.otherOperation();
        }
    }
    
    public static void deleteKeyByKname() {
        Jedis jedis = RedisPoolUntil.getJedis();
        String pattern = RedisParameterUtil.ROBOT + "";
        ScanParams scanParams = new ScanParams().match(pattern).count(Integer.valueOf(100));
        String cursor = "0";
        do {
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
            cursor = scanResult.getCursor() + "";
            List<String> keys = scanResult.getResult();
            if (!keys.isEmpty()) {
                String[] array = (String[])keys.toArray(new String[keys.size()]);
                jedis.del(array);
            }
        } while (!"0".equals(cursor));
    }
    
    static {
        RefreshMonsterTask.str = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        RefreshMonsterTask.day = 0;
        RefreshMonsterTask.second = 0;
        RefreshMonsterTask.minute = 0;
        RefreshMonsterTask.hour = 0;
        RefreshMonsterTask.rightNow = Calendar.getInstance(Locale.CHINA);
    }
    public static void updateAllresetLevel() {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(7);
        if (DataUtil.isEmpty(configure) || DataUtil.isEmpty(configure.getLywsx())) {
            return;
        }
        String rqi = configure.getLywsx();

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        if (rqi.equals(dayOfWeek + "")) {
            List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findAllUserRoles();
            if (DataUtil.isNotEmpty(loginResults)) {
                for (LoginResult loginResult : loginResults) {
                    ChannelHandlerContext gangCtx = GameServer.getRoleNameMap().get(loginResult.getRolename());
                    //在线
                    if (DataUtil.isNotEmpty(gangCtx)) {
                        //重置
                        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                        assetUpdate.setDifficultLevel("重置");
                        assetUpdate.setDifficultrecord("重置");
                        loginResult.setDifficultLevel(0);
                        loginResult.setDifficultrecord(null);
                        SendMessage.sendMessageToSlef(gangCtx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    } else {
                        AllServiceUtil.getRoleTableService().updateDifficult(loginResult.getRole_id());
                    }
                }
            }
        }
    }
}
