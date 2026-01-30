package org.come.action.sys;

import org.come.bean.AllNpcBean;
import come.tool.Scene.Scene;
import come.tool.BangBattle.BangFight;
import java.util.Iterator;
import come.tool.Role.RoleShow;
import org.come.task.MapMonsterBean;
import org.come.bean.TtModel;
import org.come.service.TtModelService;
import come.tool.newTeam.TeamBean;
import java.math.BigDecimal;
import org.come.readUtil.ReadMapUtil;
import org.come.model.Npctable;
import come.tool.Good.UseRoleAction;
import java.util.concurrent.ConcurrentHashMap;
import org.come.bean.NChatBean;
import come.tool.Scene.LTS.LTSScene;
import come.tool.Stall.StallPool;
import come.tool.Scene.SceneUtil;
import come.tool.BangBattle.BangBattlePool;
import org.come.bean.GetClientUserMesageBean;
import java.util.ArrayList;
import java.util.Map;
import org.come.task.MonsterUtil;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.List;
import org.come.until.AllServiceUtil;
import come.tool.teamArena.LadderArenaUtil;
import org.come.task.RefreshMonsterTask;
import java.time.LocalTime;
import org.come.model.Configure;
import come.tool.newTeam.TeamUtil;
import org.apache.commons.lang.StringUtils;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.bean.ChangeMapBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ChangeMapAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ChangeMapBean changeMapBean = (ChangeMapBean)GsonUtil.getGsonUtil().getgson().fromJson(message, ChangeMapBean.class);
        ChangeMap(changeMapBean, ctx);
    }
    
    public static void ChangeMap(ChangeMapBean changeMapBean, ChannelHandlerContext ctx) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        long oldMapId = (long)roleInfo.getMapid();
        long mapid = Long.parseLong(changeMapBean.getMapid());
        String[] roles = roleInfo.getTeam().split("\\|");
        if (changeMapBean.getType() == 4) {
            BigDecimal gangid = roleInfo.getGang_id();
            if (gangid.intValue() == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有帮派"));
                return;
            }
            for (int i = 1; i < roles.length; ++i) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roles[i]);
                LoginResult changRole = (ctx2 != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx2)) : null;
                if (changRole != null && gangid.compareTo(changRole.getGang_id()) != 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不能携带非本帮派成员进入"));
                    return;
                }
            }
        }
        else if (changeMapBean.getType() == 5) {
            if (StringUtils.isBlank(roleInfo.getTTJIANGLI())) {
                roleInfo.setTTJIANGLI("0|0|0|0|0|0");
            }
            String[] ttAward = roleInfo.getTTJIANGLI().split("\\|");
            if (ttAward.length < 4) {
                roleInfo.setTTJIANGLI(ttAward[0] + "|" + ttAward[1] + "|" + ttAward[2] + "|0");
            }
            if (ttAward.length < 5) {
                roleInfo.setTTJIANGLI(ttAward[0] + "|" + ttAward[1] + "|" + ttAward[2] + "|" + ttAward[3] + "|0|0");
            }
            TeamBean bean = TeamUtil.getTeam(roleInfo.getTroop_id());
            if (bean == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还没有队伍无法参与"));
                return;
            }
            ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
            Configure configure = (Configure)s.get(Integer.valueOf(7));
            String ttzq = configure.getZqsld();
            LocalTime ttks = LocalTime.parse(configure.getZqjnsx());
            LocalTime ttjs = LocalTime.parse(configure.getJumpurl());
            LocalTime dateTime = LocalTime.of(RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
            if (LadderArenaUtil.teamArenaThread == null) {
                TtModelService ttModelService = AllServiceUtil.getTtModelService();
                List<TtModel> ttConfig = ttModelService.getTtConfig();
                List<TtModel> openTT = (List)ttConfig.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 1).collect(Collectors.toList());
                if (openTT.size() == 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前赛季已经关闭#32"));
                    return;
                }
                String time = configure.getZqjnsx() + " - " + configure.getJumpurl();
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R开放时间为:" + time));
                return;
            }
            else {
                TtModelService ttModelService = AllServiceUtil.getTtModelService();
                List<TtModel> ttConfig = ttModelService.getTtConfig();
                List<TtModel> openTT = (List)ttConfig.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 1).collect(Collectors.toList());
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(11);
                if (!ttks.isBefore(dateTime) && !dateTime.isBefore(ttjs)) {
                    String time2 = configure.getZqjnsx() + " - " + configure.getJumpurl();
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("天梯比武未开启!!!#R开放时间为:" + time2));
                    return;
                }
            }
        }
        MapMonsterBean monsterBean = MonsterUtil.getFollowMonster(roles);
        if (monsterBean != null && changeMapBean.getType() == 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("飞行棋使用限制"));
            return;
        }
        Map<String, ChannelHandlerContext> mapRoleMap = (Map<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(roleInfo.getMapid());
        boolean ismap = mapid != oldMapId;
        if (ismap) {
            for (int j = 0; j < roles.length; ++j) {
                mapRoleMap.remove(roles[j]);
            }
            SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), Agreement.getAgreement().UserRetreatAgreement(roleInfo.getTeam()));
            if (monsterBean != null) {
                SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), Agreement.getAgreement().battleStateAgreement("M" + monsterBean.getI() + "^2"));
            }
        }
        List<RoleShow> roleShows = new ArrayList<>();
        for (int k = 0; k < roles.length; ++k) {
            ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roles[k]);
            if (ctx3 != null) {
                LoginResult changRole2 = (LoginResult)GameServer.getAllLoginRole().get(ctx3);
                if (changRole2 != null) {
                    changRole2.setMapid(Long.valueOf(mapid));
                    changRole2.setX(new Long((long)changeMapBean.getMapx()));
                    changRole2.setY(new Long((long)changeMapBean.getMapy()));
                    changRole2.getRoleShow().getPlayer_Paths().clear();
                    roleShows.add(changRole2.getRoleShow());
                }
            }
        }
        String mes2 = null;
        if (monsterBean != null) {
            monsterBean.setX(Integer.valueOf(changeMapBean.getMapx()));
            monsterBean.setY((long)changeMapBean.getMapy());
            if (ismap) {
                MonsterUtil.getList((long)monsterBean.getMap(), (int)monsterBean.getRobotid()).remove(monsterBean);
                monsterBean.setMap(Long.valueOf(mapid));
                MonsterUtil.getList((long)monsterBean.getMap(), (int)monsterBean.getRobotid()).add(monsterBean);
                StringBuffer buffer = new StringBuffer();
                buffer.append(monsterBean.getRobotid());
                buffer.append("#");
                buffer.append(monsterBean.getRobotname());
                buffer.append("#");
                buffer.append(monsterBean.getRobotskin());
                buffer.append("#");
                buffer.append(monsterBean.getRobotType());
                buffer.append("#");
                buffer.append(monsterBean.getI());
                buffer.append("^");
                buffer.append(monsterBean.getX());
                buffer.append("^");
                buffer.append(monsterBean.getY());
                if (monsterBean.getFollow() != null && monsterBean.getFollow().getFollowID() != null) {
                    buffer.append("^G");
                    buffer.append(monsterBean.getFollow().getFollowID());
                }
                mes2 = Agreement.getAgreement().MonsterRefreshAgreement(buffer.toString());
            }
        }
        mapRoleMap = GameServer.getMapRolesMap().get(Long.valueOf(mapid));
        GetClientUserMesageBean getClientUserMesageBean = new GetClientUserMesageBean();
        getClientUserMesageBean.setRoleShows(roleShows);
        String mes3 = Agreement.getAgreement().intogameAgreement(GsonUtil.getGsonUtil().getgson().toJson(getClientUserMesageBean));
        Iterator<Map.Entry<String, ChannelHandlerContext>> entries = mapRoleMap.entrySet().iterator();
        BigDecimal gang_id = null;
        if (mapid == 3000L) {
            gang_id = roleInfo.getGang_id();
        }
        BangFight bangFight = null;
        if (mapid == 3315L) {
            bangFight = BangBattlePool.getBangBattlePool().getBangFight(roleInfo.getGang_id());
        }
        while (entries.hasNext()) {
            Map.Entry<String, ChannelHandlerContext> entrys = (Map.Entry<String, ChannelHandlerContext>)entries.next();
            ChannelHandlerContext value = (ChannelHandlerContext)entrys.getValue();
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(value);
            if (value != null) {
                if (loginResult == null) {
                    continue;
                }
                else if (gang_id != null && gang_id.compareTo(loginResult.getGang_id()) != 0) {
                    continue;
                }
                else if (bangFight != null && bangFight.getMap(loginResult.getGang_id()) == null) {
                    continue;
                }
                else {
                    SendMessage.sendMessageToSlef(value, mes3);
                    if (ismap) {
                        roleShows.add(loginResult.getRoleShow());
                        if (mes2 != null) {
                            SendMessage.sendMessageToSlef(value, mes2);
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
        if (ismap) {
            boolean isScene = SceneUtil.isSceneMsg(oldMapId, mapid);
            getClientUserMesageBean.setIsmap(1);
            getClientUserMesageBean.setMonster(MonsterUtil.getMapMonster(mapid, roleInfo.getGang_id()));
            getClientUserMesageBean.setStallBeans(StallPool.getPool().getmap(roleInfo.getMapid().intValue()));
            if (!isScene) {
                mes3 = Agreement.getAgreement().intogameAgreement(GsonUtil.getGsonUtil().getgson().toJson(getClientUserMesageBean));
            }
            for (int l = 0; l < roles.length; ++l) {
                ChannelHandlerContext ctx4 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roles[l]);
                if (ctx4 != null) {
                    mapRoleMap.put(roles[l], ctx4);
                    if (isScene) {
                        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx4);
                        if (login != null) {
                            getClientUserMesageBean.setSceneMsg(SceneUtil.getSceneMsg(login, oldMapId, mapid));
                        }
                        else {
                            getClientUserMesageBean.setSceneMsg(null);
                        }
                        mes3 = Agreement.getAgreement().intogameAgreement(GsonUtil.getGsonUtil().getgson().toJson(getClientUserMesageBean));
                    }
                    SendMessage.sendMessageToSlef(ctx4, mes3);
                }
            }
            if (mapid == 3336L) {
                Scene scene = SceneUtil.getScene(1008);
                if (scene != null) {
                    LTSScene ltsScene = (LTSScene)scene;
                    mes3 = Agreement.getAgreement().duelBoradDataAgreement(ltsScene.getRanking());
                    for (int m = 0; m < roles.length; ++m) {
                        SendMessage.sendMessageByRoleName(roles[m], mes3);
                    }
                }
            }
        }
        if (mapid == 3344L) {
            NChatBean bean2 = new NChatBean();
            bean2.setId(5);
            bean2.setMessage("#Y神豪玩家#G【" + roleInfo.getRolename() + "】#Y进入了仙玉泡点地图！将获得海量经验，3转飞升将指日可待！");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
            SendMessage.sendMessageToAllRoles(msg);
        }
        if (mapid == 3342L) {
            NChatBean bean2 = new NChatBean();
            bean2.setId(5);
            bean2.setMessage("#Y土豪玩家#G【" + roleInfo.getRolename() + "】#Y进入了金币泡点地图！将获得海量经验，等级提升将用时将大大缩短！");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
            SendMessage.sendMessageToAllRoles(msg);
        }
        ConcurrentHashMap<String, List<Npctable>> stringListConcurrentHashMap = (ConcurrentHashMap<String, List<Npctable>>)UseRoleAction.zhNpc.get(roleInfo.getRole_id());
        if (stringListConcurrentHashMap != null) {
            List<Npctable> npctables = (List<Npctable>)stringListConcurrentHashMap.get(mapid + "");
            List<Npctable> delNpctables = new ArrayList<>();
            if (npctables != null) {
                for (Npctable npctable : npctables) {
                    long l2 = (long)(int)npctable.getFz() * 60000L;
                    if (System.currentTimeMillis() - ((long)npctable.getExTime() + l2) > 0L) {
                        delNpctables.add(npctable);
                    }
                    else {
                        AllNpcBean txtNpc1 = ReadMapUtil.createTxtNpc1(npctable);
                        String json = GsonUtil.getGsonUtil().getgson().toJson(txtNpc1);
                        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().addTimeNpcAgreement(json));
                    }
                }
                for (Npctable delNpctable : delNpctables) {
                    npctables.remove(delNpctable);
                }
                stringListConcurrentHashMap.put(mapid + "", npctables);
                UseRoleAction.zhNpc.put(roleInfo.getRole_id(), stringListConcurrentHashMap);
            }
        }
    }
}
