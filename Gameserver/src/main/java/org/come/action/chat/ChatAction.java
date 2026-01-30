package org.come.action.chat;

import java.util.*;

import come.tool.BangBattle.BangBattlePool;
import come.tool.Battle.BattleData;
import come.tool.Stall.StallPool;
import come.tool.teamArena.LadderArenaUtil;
import org.come.entity.Mount;
import org.come.handler.MainServerHandler;
import org.come.jiaren.People;
import org.come.model.PalData;
import come.tool.Role.RoleData;
import org.come.entity.RoleSummoning;

import org.come.redis.*;
import org.come.server.GolemServer;
import org.come.tool.WriteOut;
import org.come.until.AchievemUtil;
import redis.clients.jedis.Jedis;
import org.come.protocol.ParamTool;
import org.come.servlet.UserControlServlet;
import org.come.task.RefreshMonsterTask;
import org.come.entity.Pal;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import org.come.readUtil.ReadPoolUtil;
import org.come.model.Boos;
import org.come.task.MonsterUtil;
import come.tool.Battle.BattleThreadPool;
import org.apache.commons.lang.StringUtils;
import come.tool.Scene.SceneUtil;
import org.come.action.wqx.WenQuXingScene;
import org.come.until.GsonUtil;
import org.come.bean.NChatBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.come.action.IAction;

public class ChatAction implements IAction
{
    public static String MSG;
    public static ConcurrentHashMap<BigDecimal, Integer> mapSize;
    public static List<String> ggs;
    //指令
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        NChatBean nChatBean = (NChatBean)GsonUtil.getGsonUtil().getgson().fromJson(message, NChatBean.class);
        //手动刷新幸运大奖数量
        if (nChatBean.getMessage().startsWith("@刷新幸运")) {
            RedisControl.insertKey(GameServer.area + "XYDJ", 1 + "", "0,0");
            return;
        }
        if (nChatBean.getId() == 0 && nChatBean.getMessage() != null && nChatBean.getMessage().startsWith("#重置坐骑管制")) {
            String[] split = nChatBean.getMessage().split("@");
            if (split.length == 1) {
                this.change(null, ctx);
            }
            else {
                this.change(split[1], ctx);
            }
            return;
        }
        else {
            WenQuXingScene scene = (WenQuXingScene)SceneUtil.getScene(1017);
            if (scene != null && scene.getI() == 1 && nChatBean.getId() == 3) {
                scene.verification(ctx, nChatBean.getMessage());
            }
            if (StringUtils.isNotBlank(nChatBean.getMessage()) && nChatBean.getMessage().startsWith("#帅哥踢他战斗")) {
                String[] split2 = nChatBean.getMessage().split("@");
                BattleThreadPool.BattleDatas.forEach((key, itme)/* java.lang.Integer,come.tool.Battle.BattleData, */ -> {
                    String[] team1 = itme.getTeam1();
                    String[] team2 = itme.getTeam2();
                    int length = team1.length;
                    int n = 0;
                    while (n < length) {
                        String s = team1[n];
                        if (s.equals(split2[1])) {
                            BattleThreadPool.removeBattleData(itme);
                            break;
                        }
                        else {
                            ++n;
                        }
                    }
                    int length2 = team2.length;
                    int n2 = 0;
                    while (n2 < length2) {
                        String s2 = team2[n2];
                        if (s2.equals(split2[1])) {
                            BattleThreadPool.removeBattleData(itme);
                            break;
                        }
                        else {
                            ++n2;
                        }
                    }
                    return;
                });
                return;
            }
            if (nChatBean.getMessage().startsWith("@我要多刷点怪")) {
                for (int i = 0; i < MonsterUtil.booses.size(); ++i) {
                    Boos boos = (Boos)MonsterUtil.booses.get(i);
                    if (boos.getBoosname().equals("天女散花")) {
                        boos.setBoosnum(20);
                        MonsterUtil.refreshMonsters(boos, null, null, null);
                    }
                }
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人上线")) {
//                try {
                    RefreshMonsterTask.rightNow = Calendar.getInstance(Locale.CHINA);
                    RefreshMonsterTask.second = RefreshMonsterTask.rightNow.get(13);
                    RefreshMonsterTask.minute = RefreshMonsterTask.rightNow.get(12);
//                    Thread.sleep((long)((60 - RefreshMonsterTask.second + (4 - RefreshMonsterTask.minute % 5) * 60) * 1000));
                    RefreshMonsterTask.rightNow = Calendar.getInstance(Locale.CHINA);
                    RefreshMonsterTask.day = RefreshMonsterTask.rightNow.get(7);
                    RefreshMonsterTask.hour = RefreshMonsterTask.rightNow.get(11);
                    RefreshMonsterTask.minute = RefreshMonsterTask.rightNow.get(12);
                    RefreshMonsterTask.second = RefreshMonsterTask.rightNow.get(13);
//                    if (RefreshMonsterTask.minute % 5 != 0) {
//                        RefreshMonsterTask.minute /= 5;
//                        RefreshMonsterTask.minute *= 5;
//                        WriteOut.addtxt("时间不对:" + RefreshMonsterTask.minute, 9999L);
//                    }
//                }
//                catch (Exception e) {
//                    WriteOut.addtxt("刷新线程:" + MainServerHandler.getErrorMessage(e), 9999L);
//                }
                GolemServer.OPEN=true;
                GameServer.getGolemConfig().setSwitch("开关", "开");
                GameServer.golemServer = GolemServer.initAIServer();//机器人配置
                if (!GameServer.OPEN && GolemServer.OPEN) {
                    // 更新机器人数量
                    GameServer.golemServer.assignGolem();
                    GameServer.golemServer.otherOperation();
                }
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人下线")) {
                if (GameServer.golemServer != null && GameServer.golemServer.isAlive()) {

                    GameServer.getGolemConfig().setSwitch("开关", "关");
                    GolemServer.OPEN=false;
                    GolemServer.stopGolemsThread(); // 设置 isRunning 为 false
                    GameServer.golemServer.interrupt(); // 中断线程
                    try {
                        GameServer.golemServer.join(); // 等待线程终止
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                GameServer.golemServer.AllGolemDown();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人摆摊喊话")) {
                StallSendMessage.start();
                return;
            }

            if (nChatBean.getMessage().startsWith("@机器人停止摆摊喊话")) {
                StallSendMessage.stop();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人世界喊话")) {
                ShiJieSendMessage.start();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人停止世界喊话")) {
                ShiJieSendMessage.stop();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人建帮")) {
                GolemServer.CreatBang();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人加帮")) {
                GolemServer.AddBang();
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人报名帮战")) {
                GolemServer.addbangfight();
                return;
            }

            if (nChatBean.getMessage().startsWith("@机器人第一套装备")) {
                GolemServer.ChangeEquid(1);
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人第二套装备")) {
                GolemServer.ChangeEquid(2);
                return;
            }
            if (nChatBean.getMessage().startsWith("@机器人第三套装备")) {
                GolemServer.ChangeEquid(3);
                return;
            }


            if (nChatBean.getMessage().startsWith("@假人摆摊开启")) {
                new Thread(new StallBotTask()).start();
                return;
            }

            if (nChatBean.getMessage().startsWith("@清除假人摊位")) {
                if (People.SELL_BOT != null) {
                    for (Integer key : People.SELL_BOT.keySet()) {
                        // 这里只处理键，不需要访问值
                        StallPool.getPool().RetreatStall(key);
                    }
                    People.SELL_BOT=null;
                    People.BOT_RID=null;
                }
                return;
            }
            if (nChatBean.getMessage().startsWith("@假人摊位刷新")) {
                if (People.SELL_BOT != null) {
                    for (Integer key : People.SELL_BOT.keySet()) {
                        // 这里只处理键，不需要访问值
                        StallPool.getPool().RetreatStall(key);
                    }
                    People.SELL_BOT = null;
                    People.BOT_RID = null;
                }
                new Thread(new StallBotTask()).start();
                return;
            }





            if (nChatBean.getMessage().startsWith("@帮战开启")) {
                BangBattlePool.getBangBattlePool();
                BangBattlePool.getBangBattlePool().FightOpenClose();
                return;
            }
            if (nChatBean.getMessage().startsWith("@水陆开启")) {
                SceneUtil.activityOpen("测试水陆",0,0,0,0);
                return;
            }
            if (nChatBean.getMessage().startsWith("@大闹开启")) {
                SceneUtil.activityOpen("测试大闹",0,0,0,0);
                return;
            }
            if (nChatBean.getMessage().startsWith("@天天梯开启")) {
                LadderArenaUtil.teamArenaOpen();
                return;
            }
            else if (nChatBean.getMessage().startsWith("@加载")) {
                for (int i = 0; i < MonsterUtil.booses.size(); ++i) {
                    Boos boos = (Boos)MonsterUtil.booses.get(i);
                    if (boos.getBoosname().equals("天女散花")) {
                        boos.setBoosnum(20);
                        MonsterUtil.refreshMonsters(boos, null, null, null);
                    }
                }
                return;
            }
            else if (StringUtils.isNotBlank(nChatBean.getMessage()) && nChatBean.getMessage().startsWith("#读取表格")) {
                StringBuffer buffer = new StringBuffer();
                int j = 0;
                while (j < 73) {
                    if (!ReadPoolUtil.readTypeTwo(buffer, j)) {
                        System.out.println(buffer.toString());
                        try {
                            Thread.sleep(999999999L);
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    else {
                        ++j;
                    }
                }
                return;
            }
            else {
                if (StringUtils.isNotBlank(nChatBean.getMessage()) && nChatBean.getMessage().startsWith("#躲闪")) {
                    BattleThreadPool.BattleDatas.forEach((key, itme)/* java.lang.Integer,come.tool.Battle.BattleData, */ -> {
                        String[] team3 = itme.getTeam1();
                        String[] team4 = itme.getTeam2();
                        int length3 = team3.length;
                        int n3 = 0;
                        while (n3 < length3) {
                            String s3 = team3[n3];
                            if (s3.equals(roleInfo.getRolename())) {
                                BattleThreadPool.removeBattleData(itme);
                                break;
                            }
                            else {
                                ++n3;
                            }
                        }
                        int length4 = team4.length;
                        int n4 = 0;
                        while (n4 < length4) {
                            String s4 = team4[n4];
                            if (s4.equals(roleInfo.getRolename())) {
                                BattleThreadPool.removeBattleData(itme);
                                break;
                            }
                            else {
                                ++n4;
                            }
                        }
                        return;
                    });
                    return;
                }
                if (nChatBean.getMessage().startsWith("@摊位看不见了回复一下帅老G")) {
                    String roleId = "";
                    if (nChatBean.getMessage().contains("|")) {
                        roleId = nChatBean.getMessage().split("\\|")[1];
                    }
                    Jedis jedis = RedisPoolUntil.getJedis();
                    Set<String> keys = jedis.keys("0001GOODSID_-" + roleId + "*");
                    for (String key : keys) {
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.setType(AssetUpdate.STALLRET);
                        String[] split3 = key.split("_");
                        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(new BigDecimal(Math.abs(Integer.parseInt(split3[1]))));
                        Set<String> members = jedis.smembers(key);
                        for (String member : members) {
                            try {
                                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(member));
                                if (EquipTool.canSuper(good.getType())) {
                                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), good.getGoodsid());
                                    if (sameGoodstable.size() != 0) {
                                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + (int)good.getUsetime()));
                                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                                        AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(good.getRgid());
                                        good = (Goodstable)sameGoodstable.get(0);
                                    }
                                    else {
                                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, loginResult.getRole_id(), null, null);
                                    }
                                }
                                else {
                                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, loginResult.getRole_id(), null, null);
                                }
                                assetUpdate.setGood(good);
                                assetUpdate.setMsg("摆摊物品归还");
                                ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                                if (channelHandlerContext != null) {
                                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                }
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("找回玩家#R" + loginResult.getRolename() + "#W物品#G" + good.getGoodsname()));
                            }
                            catch (Exception e) {}
                        }
                    }
                    jedis = RedisPoolUntil.getJedis();
                    keys = jedis.keys("0001PET_-" + roleId + "*");
                    for (String key : keys) {
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.setType(AssetUpdate.STALLRET);
                        String[] split3 = key.split("_");
                        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(new BigDecimal(Math.abs(Integer.parseInt(split3[1]))));
                        Set<String> members = jedis.smembers(key);
                        for (String member : members) {
                            try {
                                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(member));
                                AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, loginResult.getRole_id());
                                assetUpdate.setPet(pet);
                                assetUpdate.setMsg("摆摊物品归还");
                                ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                                if (channelHandlerContext != null) {
                                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                }
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("找回玩家#R" + loginResult.getRolename() + "#W宠物#G" + pet.getSummoningname()));
                            }
                            catch (Exception e) {}
                        }
                    }
                    return;
                }
                else if (StringUtils.isNotBlank(nChatBean.getMessage()) && nChatBean.getMessage().startsWith("#我要恢复1看不见的伙伴2装备")) {
                    String[] split2 = nChatBean.getMessage().split("@");
                    LoginResult role = AllServiceUtil.getRoleTableService().selectRoleName(split2[1]);
                    List<Pal> pals = AllServiceUtil.getPalService().selectPalByRoleID(role.getRole_id());
                    RoleData roleData = RolePool.getRoleData(role.getRole_id());
                    for (Pal huoban : pals) {
                        PalData palData = GameServer.getPalData(huoban.getpId());
                        if (roleData.isGoodFull()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("背包已满,先清空背包"));
                            return;
                        }
                        if (huoban.getParts() == null) {
                            continue;
                        }
                        else {
                            String[] v = huoban.getParts().split("\\|");
                            for (int k = 0; k < v.length; ++k) {
                                String[] vs = v[k].split("=");
                                Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
                                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods, null, null, Integer.valueOf(0));
                                huoban.setParts(null);
                                AllServiceUtil.getPalService().updatePal(huoban);
                                AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
                                assetUpdate2.setPal(huoban);
                                assetUpdate2.setGood(goods);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                            }
                        }
                    }
                    return;
                }
                else if (StringUtils.isNotBlank(nChatBean.getMessage()) && nChatBean.getMessage().startsWith("#我要重置角8色活跃任#务")) {
                    String[] split2 = nChatBean.getMessage().split("@");
                    for (Map.Entry<ChannelHandlerContext, LoginResult> entry : GameServer.getAllLoginRole().entrySet()) {
                        LoginResult roleinfo = (LoginResult)entry.getValue();
                        if (split2[1].equals(roleinfo.getRolename())) {
                            RefreshMonsterTask.taskReset(ctx, roleinfo, 1);
                        }
                    }
                    return;
                }
                else {
                    if (nChatBean.getId() == 5) {
                        String msg = Agreement.getAgreement().chatAgreement(message);
                        SendMessage.sendMessageToAllRoles(msg);
                        return;
                    }
                    if (UserControlServlet.isNoTalk(ctx)) {
                        return;
                    }
                    if ((int)roleInfo.getGrade() < 98) {
                        SendMessage.sendMessageToSlef(ctx, ChatAction.MSG);
                        return;
                    }
                    nChatBean.setRoleId(roleInfo.getRole_id());
                    nChatBean.setRole(roleInfo.getRolename());
                    String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean));
                    int j = ChatAction.ggs.size() - 1;
                    while (j >= 0) {
                        if (nChatBean.getMessage().indexOf((String)ChatAction.ggs.get(j)) != -1) {
                            Integer size = (Integer)ChatAction.mapSize.get(roleInfo.getRole_id());
                            if (size == null) {
                                size = Integer.valueOf(0);
                            }
                            if ((int)size >= 1200) {
                                if (GameServer.random.nextInt(150) == 0) {
                                    ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action(ctx, roleInfo.getUserName());
                                    return;
                                }
                            }
                            else {
                                size = Integer.valueOf((int)size + 1);
                                ChatAction.mapSize.put(roleInfo.getRole_id(), size);
                            }
                            SendMessage.sendMessageToSlef(ctx, msg);
                            return;
                        }
                        else {
                            --j;
                        }
                    }
                    if (nChatBean.getId() == 3 || nChatBean.getId() == 4) {//世界和喇叭
                        SendMessage.sendMessageToAllRoles(msg);
                        AchievemUtil.detectionAchievem(roleInfo, "世界频道发言");
                    }
                    else if (nChatBean.getId() == 0) {//0当前
                        SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), msg);
                    }
                    else if (nChatBean.getId() == 1) {//1队伍
                        String[] teams = roleInfo.getTeam().split("\\|");
                        for (int l = 0; l < teams.length; ++l) {
                            SendMessage.sendMessageByRoleName(teams[l], msg);
                        }
                    }
                    else if (nChatBean.getId() == 2 && roleInfo.getGang_id() != null) {//2帮派
                        SendMessage.sendMessageToGangRoles(roleInfo.getGang_id(), msg);
                        if (nChatBean.getId() == 2) {
                            AchievemUtil.detectionAchievem(roleInfo, "帮派频道发言");
                        }
                    }
                    return;
                }
            }
        }
    }
    
    public void change(String mountName, ChannelHandlerContext ctx) {
        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        List<Mount> mountList = AllServiceUtil.getMountService().selectMountsByRoleID(loginResult.getRole_id());
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (mountList == null || data == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑管制修改失败"));
            return;
        }
        boolean found = false;
        for (Mount mount : mountList) {
            Mount mountRedis = AllServiceUtil.getMountService().selectMountsByMID(mount.getMid());
            if (mountRedis == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑管制修改失败"));
                return;
            }
            if (mountName == null || mountRedis.getMountname().contains(mountName)) {
                mountRedis.setSid3(null);
                mountRedis.setOthrersid(null);
                mountRedis.setSid(null);
                AllServiceUtil.getMountService().updateMountRedis(mountRedis);
                data.MPet(mount, true);
                found = true;
            }
        }
        if (!found && mountName != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑管制修改失败"));
            return;
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑管制修改成功"));
    }
    
    static {
        ChatAction.MSG = Agreement.getAgreement().PromptAgreement("未转不能发言");
        ChatAction.mapSize = new ConcurrentHashMap<>();
        (ChatAction.ggs = new ArrayList<>()).add("群48");
        ChatAction.ggs.add("10万元宝");
    }
}
