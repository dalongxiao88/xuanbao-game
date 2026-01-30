package org.come.action.sys;

import org.come.bean.RoleTxBean;
import org.come.entity.*;
import org.come.model.*;
import org.come.task.RefreshMonsterTask;
import come.tool.teamArena.LadderArenaAction;

import java.util.Calendar;
import come.tool.Title.TitleUtil;
import java.util.Timer;
import java.util.TimerTask;

import come.tool.Role.RoleShow;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamBean;

import java.util.Iterator;

import come.tool.Scene.Scene;
import come.tool.BangBattle.BangFight;
import come.tool.Role.RoleData;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Stall.AssetUpdate;
import come.tool.Mixdeal.AnalysisString;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.action.rich.InputBean;
import org.come.bean.NChatBean;
import com.github.pagehelper.util.StringUtil;
import org.come.protocol.ParamTool;
import org.come.task.MonsterUtil;
import come.tool.Stall.Stall;
import org.come.bean.enterGameBean;
import java.util.Map;
import org.come.until.GsonUtil;
import org.come.bean.GetClientUserMesageBean;
import come.tool.newTeam.TeamUtil;
import come.tool.FightingData.Sepcies_MixDeal;

import java.util.stream.Collectors;
import java.util.List;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import come.tool.Stall.StallPool;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.SceneUtil;
import come.tool.BangBattle.BangBattlePool;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.WriteOut;
import org.come.until.AllServiceUtil;
import org.come.bean.LoginResult;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import org.come.action.IAction;

public class enterGameAction implements IAction
{
    static BigDecimal ZERO;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        String IP = LoginAction.getIP(ctx);
        String[] ms = message.split("\\|");
        BigDecimal role_id = new BigDecimal(ms[0]);
        LoginResult roleInfo = null;
        synchronized (GameServer.userLock) {
            RoleData data = RolePool.getRoleData(role_id);
            if (data != null) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(data.getLoginResult().getRolename());
                if (ctx2 != null) {
                    roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                    GameServer.userDownTwos(ctx2);
                }
            }
            if (roleInfo == null) {
                roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(role_id);
            }
        }
        if (roleInfo == null) {
            return;
        }
        if (ms.length == 1) {
            ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(roleInfo.getUserName());
            if (ctx3 == null || ctx3 != ctx) {
                WriteOut.addtxt("冒名顶替:" + (String)GameServer.getSocketUserNameMap().get(ctx), 9999L);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().inlineLoginAgreement());
                return;
            }
        }
        else {
            String userName = ms[1];
            String userPwd = null;
            for (int i = 2; i < ms.length; ++i) {
                if (i != 2) {
                    userPwd += "|";
                    userPwd += ms[i];
                }
                else {
                    userPwd = ms[i];
                }
            }
            if (userPwd == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().inlineLoginAgreement());
                return;
            }
            UserTable userTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userName, userPwd);
            if (userTable == null || userTable.getUser_id().compareTo(roleInfo.getUser_id()) != 0) {
                WriteOut.addtxt("冒名顶替:" + message, 9999L);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().inlineLoginAgreement());
                return;
            }
        }
        roleInfo.setMeridians("");
        String lastDownTime = roleInfo.getUptime();
        Reset(roleInfo, System.currentTimeMillis());
        roleInfo.setUserPwd(null);
        if ((int)roleInfo.getFighting() != 0) {
            BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(roleInfo.getFighting());
            if (battleData == null) {
                roleInfo.setFighting(Integer.valueOf(0));
            }
        }
        BangFight bangFight = null;
        if ((long)roleInfo.getMapid() == 3315L) {
            roleInfo.setTitle(roleInfo.getGangname() + "帮" + roleInfo.getGangpost());
            bangFight = BangBattlePool.getBangBattlePool().getBangFight(roleInfo.getGang_id());
        }
        else if ((long)roleInfo.getMapid() == 3201L || (long)roleInfo.getMapid() == 3336L) {
            Scene scene = SceneUtil.getScene(1011);
            if (scene == null || !scene.isEnd() || ((DNTGScene)scene).getRole(roleInfo.getRole_id()) == null) {
                roleInfo.setMapid(Long.valueOf(1207L));
                roleInfo.setX(Long.valueOf(4294L));
                roleInfo.setY(Long.valueOf(2887L));
            }
        }
        if (!StallPool.getPool().updateState(roleInfo.getBooth_id(), StallPool.NO, roleInfo.getRole_id())) {
            roleInfo.setBooth_id(null);
        }
        List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(roleInfo.getRole_id());
        if (goods != null && roleInfo.getBooth_id() == null) {
            goods.forEach(goodstable/* org.come.entity.Goodstable, */ -> goodstable.setCommodityId(null));
        }
        List<RoleSummoning> petss = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleInfo.getRole_id());
        List<RoleSummoning> pets = new ArrayList<>();
        List<BigDecimal> petid = new ArrayList<>();
        if (petss != null && petss.size() > 0) {
            for (RoleSummoning summoning : petss) {
//                if (summoning .getLingxi()!=null) {//ceshi
//                    summoning.setLingxi(null);//ceshi
//                }//ceshi
                try {
                    if (StringUtils.isNotBlank(summoning.getLingxi()) && summoning.getLingxi().split("&").length != 5) {
                        summoning.setLingxi(null);
                    }
                }
                catch (Exception e) {
                    summoning.setLingxi(null);
                }
                if (summoning.getQuality() != null) {
                    if (summoning.getQuality().equals("1")) {
                        if (summoning.getSurplusTimes() != null) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date date = simpleDateFormat.parse(summoning.getSurplusTimes());
                                Long sy = Long.valueOf(date.getTime());
                                Long cd = Long.valueOf(new Date().getTime());
                                if ((long)sy > (long)cd) {
                                    pets.add(summoning);
                                }
                                else {
                                    petid.add(summoning.getSid());
                                    AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySid(summoning.getSid());
                                }
                            }
                            catch (ParseException e2) {
                                e2.printStackTrace();
                            }
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        pets.add(summoning);
                    }
                }
                else {
                    pets.add(summoning);
                }
            }
        }
        List<RoleSummoning> petAll = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleInfo.getRole_id());
        pets = (List)petAll.stream().filter(item/* org.come.entity.RoleSummoning, */ -> item.getDeposit() == null || (int)item.getDeposit() == 0).collect(Collectors.toList());
        List<RoleSummoning> depositPets = (List)petAll.stream().filter(item/* org.come.entity.RoleSummoning, */ -> item.getDeposit() != null && (int)item.getDeposit() == 1).collect(Collectors.toList());
        if (petid != null && petid.size() > 0) {
            AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidList(petid);
        }
        List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(roleInfo.getRole_id());
        List<XuanBao> xuanBaos = AllServiceUtil.getXuanBaoService().selectLingbaoByRoleID(roleInfo.getRole_id());
        List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(roleInfo.getRole_id());
        List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleInfo.getRole_id());
        for (int j = 0; j < mounts.size(); ++j) {
            if (!((Mount)mounts.get(j)).getMountname().contains("[")) {
                LoginResult login = AllServiceUtil.getRoleTableService().selectRoleByRoleId(role_id);
                Mount mount = GameServer.getMount(Sepcies_MixDeal.getRace(login.getSpecies_id()), (int)((Mount)mounts.get(j)).getMountid());
                if (mount != null) {
                    ((Mount)mounts.get(j)).setMountname(mount.getMountname());
                    if (mount.getMountlvl()!=null&&mount.getMountlvl() >200) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您坐骑异常，20秒后封号！！！"));
                        forceExiteGame(roleInfo);
                    } else if (mount.getProficiency()!=null&&mount.getProficiency()>150000) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您坐骑异常，20秒后封号！！！"));
                        forceExiteGame(roleInfo);
                    } else if (mount.getMountskill()!=null&&mount.getMountskill().size()>4) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您坐骑异常，20秒后封号！！！"));
                        forceExiteGame(roleInfo);
                    }
                }
                AllServiceUtil.getMountService().updateMountRedis((Mount)mounts.get(j));
            }
        }
        List<Car> cars = AllServiceUtil.getCarService().selectMountsByRoleID(roleInfo.getRole_id());

        List<Pal> pals = AllServiceUtil.getPalService().selectPalByRoleID(roleInfo.getRole_id());
        List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(roleInfo.getRole_id());
        if (StringUtils.isNotBlank(roleInfo.getTitle())) {
            Boolean isTitle = Boolean.valueOf(false);
            List<Titletable> titletables = AllServiceUtil.getTitletableService().selectRoleAllTitle(roleInfo.getRole_id());
            if (titletables.size() == 0) {
                isTitle = Boolean.valueOf(true);
            }
            for (Titletable titletable : titletables) {
                if (titletable.getTitlename().equals(roleInfo.getTitle()) && titletable.getRecordtime() != null) {
                    isTitle = Boolean.valueOf(true);
                    break;
                }
            }
            if (!(boolean)isTitle) {
                roleInfo.setTitle("");
            }
        }
        RoleAttribute roleAttribute = AllServiceUtil.getRoleTableService().selectRoleAttributeRoleId(roleInfo.getRole_id());
        RoleData roleData = null;
        synchronized (GameServer.userLock) {
            roleData = RolePool.addRoleData(roleInfo, goods, pets, lingbaos, babys, mounts, flys);
            roleData.setIP(IP);
            GameServer.addOuts(ctx, roleInfo);
        }
        TeamBean teamBean = TeamUtil.getTeam(roleInfo.getTroop_id());
        TeamRole teamRole = (teamBean != null) ? teamBean.getTeamRole(roleInfo.getRole_id()) : null;
        roleInfo.setTeamInfo(null);
        roleInfo.setTroop_id(null);
        if (teamRole != null) {
            roleInfo.setTroop_id(teamBean.getTeamId());
            roleInfo.initTeamRole(teamRole);
            if (teamRole.getState() == -2) {
                LoginResult login2 = null;
                if (teamBean.isCaptian(roleInfo.getRole_id())) {
                    login2 = roleInfo;
                }
                else {
                    String teamName = teamBean.getTeamName();
                    ChannelHandlerContext tCtx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teamName);
                    login2 = ((tCtx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(tCtx)) : null);
                }
                if (login2 != null && (long)login2.getMapid() == (long)roleInfo.getMapid()) {
                    teamBean.stateCome(teamRole);
                    roleInfo.setTeamInfo(teamBean.getTeamInfo());
                }
            }
        }
        roleInfo.setGameStartTime(new Date().getTime() + "");
        GameServer.LogIn(IP, roleInfo.getRolename(), true);
        if (GameServer.inlineNum.get() > GameServer.inlineMax) {
            GameServer.inlineMax = GameServer.inlineNum.get();
        }
        if (StringUtils.isNotBlank(roleInfo.getLianghaoValue()) && !roleInfo.getLianghaoValue().contains("@")) {
            String[] lhlh = { "6", roleInfo.getLianghaoValue().replace("|", ""), "炼化属性" };
            String join = StringUtils.join(lhlh, "@");
            roleInfo.setLianghaoValue(join);
        }
        List<RoleShow> roleShows = new ArrayList<>();
        roleShows.add(roleInfo.getRoleShow());
        GetClientUserMesageBean getClientUserMesageBean = new GetClientUserMesageBean();
        getClientUserMesageBean.setRoleShows(roleShows);
        String mes = Agreement.getAgreement().intogameAgreement(GsonUtil.getGsonUtil().getgson().toJson(getClientUserMesageBean));
        roleShows.clear();
        BigDecimal gang_id = null;
        long mapid = (long)roleInfo.getMapid();
        Map<String, ChannelHandlerContext> mapRoleMap = (Map<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(roleInfo.getMapid());
        if (mapRoleMap == null) {
            roleInfo.setMapid(Long.valueOf(1207L));
            mapid = 1207L;
            mapRoleMap = (Map<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(Long.valueOf(mapid));
        }
        Iterator<Map.Entry<String, ChannelHandlerContext>> entries = mapRoleMap.entrySet().iterator();
        if (mapid == 3000L) {
            gang_id = roleInfo.getGang_id();
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
                    SendMessage.sendMessageToSlef(value, mes);
                    roleShows.add(loginResult.getRoleShow());
                }
            }
        }
        mapRoleMap.put(roleInfo.getRolename(), ctx);
        enterGameBean gameBean = new enterGameBean();
        gameBean.setLoginResult(roleInfo);
        gameBean.setPrivateData(roleData.getPrivateData());
        if (roleShows.isEmpty()) {
            roleShows.add(roleInfo.getRoleShow());
        }
        gameBean.setRoleShows(roleShows);
        gameBean.setList(goods);
        gameBean.setRoleSummonings(pets);
        gameBean.setMounts(mounts);
        gameBean.setFlys(flys);
        gameBean.setRoleDepositSummonings(depositPets);
        gameBean.setLingbaos(lingbaos);
        gameBean.setBabys(babys);
        gameBean.setXuanBaos(xuanBaos);
        gameBean.setPals(pals);
        gameBean.setCars(cars);
        gameBean.setRoleAttribute(roleAttribute);
        gameBean.setStallBeans(StallPool.getPool().getmap(roleInfo.getMapid().intValue()));
        if (roleInfo.getBooth_id() != null) {
            gameBean.setStall((Stall)StallPool.getPool().getAllStall().get(Integer.valueOf(roleInfo.getBooth_id().intValue())));
        }
        gameBean.setMonster(MonsterUtil.getMapMonster(mapid, roleInfo.getGang_id()));
        gameBean.setPackRecord(roleData.getPackRecord());
        gameBean.setRoleSystem(roleData.getRoleSystem());
        gameBean.setSceneMsg(SceneUtil.getSceneMsg(roleInfo, 0L, (long)roleInfo.getMapid()));
        String messages = Agreement.getAgreement().enterGameAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameBean));
        SendMessage.sendMessageToSlef(ctx, messages);
        ((IAction)ParamTool.ACTION_MAP.get("friend")).action(ctx, roleInfo.getRole_id().toString());
        if (bangFight != null && bangFight.BangState == 1) {
            bangFight.addMember(roleInfo.getRolename(), roleInfo.getGang_id());
            bangFight.getzk(roleInfo.getRolename(), true);
        }
        if ((int)roleInfo.getFighting() != 0) {
            BattleThreadPool.addConnection(ctx, (int)roleInfo.getFighting(), roleInfo.getRolename());
        }
        else if (StringUtil.isNotEmpty(lastDownTime) && ms.length == 1) {
            UserTable userTable2 = AllServiceUtil.getUserTableService().selectByPrimaryKey(roleInfo.getUser_id());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String sd = sdf.format(new Date(Long.parseLong(lastDownTime)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y上次的离开时间#G【" + sd + "#G】，#Y上次的登录的IP:#G【" + userTable2.getLoginip() + "#G】,#Y您当前登陆的IP:#G【" + IP + "#G】。"));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("退出战斗"));
            NChatBean bean = new NChatBean();
            bean.setId(5);
            if ((int)roleInfo.getMoney() > 8888) {
                if (StringUtils.isNotEmpty(roleInfo.getLiangHao())) {
                    InputBean ib = new InputBean();
                    ib.setName("[" + roleInfo.getRolename());
                    ib.setGoodNum(roleInfo.getLiangHao());
                    ib.setGoodNumType(String.valueOf(roleInfo.getLianghaotype()));
                    ib.setId(roleInfo.getRole_id());
                    ib.setColor("G");
                    ib.setType(5);
                    bean.setMessage("#R#V" + GsonUtil.getGsonUtil().getgson().toJson(ib) + "#W上线了！");
                }
                else {
                    bean.setMessage("#R[" + roleInfo.getRolename() + "]#W上线了！");
                }
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
            }
            if (configure.getSfdkms().equals("1")) {
                if (roleInfo.getGameTimeRemaining() != null) {
                    int times = (int)roleInfo.getGameTimeRemaining();
                    if (times <= 60 && times > 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的游戏点数不足60请及时补充！！！剩余【#R" + times + "#Y】点！"));
                    }
                    else if (times > 60) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的游戏点数剩余#G" + times + "#Y点！"));
                    }
                    else if (times <= 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的游戏点数不足请及时补充！当前点数【#R" + times + "#Y】"));
                    }
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的游戏点数不足请及时补充！当前点数【#R0#Y】"));
                }
            }
            if (petss != null && petss.size() > 0) {
                for (RoleSummoning summoning2 : petss) {
                    summoning2.setShow(false);
                    if (summoning2.getQuality() != null && summoning2.getQuality().equals("1") && summoning2.getSurplusTimes() != null) {
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date2 = simpleDateFormat2.parse(summoning2.getSurplusTimes());
                            Long sy2 = Long.valueOf(date2.getTime());
                            Long cd2 = Long.valueOf(new Date().getTime());
                            if ((long)sy2 < (long)cd2) {
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R提示：您的召唤兽#G【" + summoning2.getSummoningname() + "】#Y已过期！"));
                            }
                            else {
                                continue;
                            }
                        }
                        catch (ParseException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        }
        StringBuffer buffer = null;
        if (teamRole != null) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(Agreement.getAgreement().team1Agreement(GsonUtil.getGsonUtil().getgson().toJson(teamBean)));
        }
        if (LaborScene.I != 0) {
            LaborScene.bindRole(roleInfo);
        }
        if (buffer != null) {
            SendMessage.sendMessageToSlef(ctx, buffer.toString());
        }
        AllServiceUtil.getSellXianYuOrderService().calSelfDeposit(ctx);
        AllServiceUtil.getSellLianghaoAucService().calSelfBuyLh(ctx);
        if (pets != null && pets.size() > 0) {
            for (RoleSummoning roleSummoning : pets) {
                int errorCount = 0;
                String[] lys = null;
                String lyschecked = "";
                double l = 0.0;
                if (roleSummoning.getLyk() != null && !roleSummoning.getLyk().equals("")) {
                    lys = roleSummoning.getLyk().split("\\|");
                    if (lys != null) {
                        for (int j2 = 0; j2 < lys.length; ++j2) {
                            l = Double.parseDouble(lys[j2].split("=")[1]);
                            if (l >= 75.0) {
                                ++errorCount;
                            }
                        }
                    }
                }
                if (errorCount >= 5) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽属性异常，20秒后封号！！！"));
                    forceExiteGame(roleInfo);
                }
            }
        }
        String resistance = roleInfo.getResistance();
        String[] v;
        for (String s2 : v = resistance.split("\\|")) {
            String[] v2;
            for (String string : v2 = s2.split("#")) {
                String[] split = string.split("=");
                if (split.length == 2) {
                    double d = Double.parseDouble(split[1]);
                    if (d > 40.0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("检测到你的帮派抗性异常，30秒后退出游戏！！！"));
                        ExiteGame(roleInfo);
                        return;
                    }
                }
            }
        }
        if (pets != null && pets.size() > 0) {
            for (RoleSummoning roleSummoning2 : pets) {
                int lvl = AnalysisString.petLvlint((int)roleSummoning2.getGrade());
                int Turn = AnalysisString.petTurnRount((int)roleSummoning2.getGrade());
                if (Turn < 4) {
                    if ((int)roleSummoning2.getBone() < lvl || (int)roleSummoning2.getSpir() < lvl || (int)roleSummoning2.getPower() < lvl || (int)roleSummoning2.getSpeed() < lvl) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning2.getSummoningname() + "]属性点异常，请尽快使用混元丹，30秒后退出游戏！！！"));
                        ExiteGame(roleInfo);
                    }
                }
                else if ((int)roleSummoning2.getBone() < lvl || (int)roleSummoning2.getSpir() < lvl || (int)roleSummoning2.getPower() < lvl || (int)roleSummoning2.getSpeed() < lvl || (int)roleSummoning2.getCalm() < lvl) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning2.getSummoningname() + "]属性点异常，请尽快使用混元丹，30秒后退出游戏！！！"));
                    ExiteGame(roleInfo);
                }
                if (roleSummoning2.getCanpoint() < 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您召唤兽[" + roleSummoning2.getSummoningname() + "]属性点异常，请尽快使用混元丹，20秒后退出游戏！！！"));
                    ExiteGame(roleInfo);
                }
            }
        }
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
        DiceReidsBase diceReidsBase = AllServiceUtil.getDiceService().selectByID(String.valueOf(roleInfo.getRole_id()));
        if (diceReidsBase.getTime() != null) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.updata("Dice=" + roleInfo.getRole_id() + "=" + diceReidsBase.getTime());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        if (roleInfo.getCalm()<0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("系统检测到您点数，20秒后封号！！！"));
            forceExiteGame(roleInfo);
        }
    }
    
    public static void forceExiteGame(LoginResult roleInfo) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000L);
                    ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename()), roleInfo.getUserName());
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0L);
    }
    
    public static void ExiteGame(LoginResult roleInfo) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000L);
                    if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().serverstopAgreement());
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0L);
    }
    
    public static void Reset(LoginResult loginResult, long time2) {
        try {
            Title title = GameServer.getTitle(loginResult.getTitle());
            if (title != null) {
                if (title.getExist() != null && !title.getExist().equals("")) {
                    if (title.getExist().startsWith("称谓任务")) {
                        int value = Integer.parseInt(title.getExist().split("=")[1]);
                        int max = loginResult.getJQId("S");
                        if (max < value) {
                            loginResult.setTitle(null);
                        }
                    }
                    else if (title.getExist().startsWith("击杀煞星")) {
                        int value = Integer.parseInt(title.getExist().split("=")[1]);
                        double max2 = loginResult.getKilltype("击杀煞星");
                        if (max2 < (double)value) {
                            loginResult.setTitle(null);
                        }
                    }
                    else if (title.getExist().startsWith("充值")) {
                        if (AllServiceUtil.getTitletableService().selectRoleTitle(loginResult.getRole_id(), title.getTitlename()) == null) {
                            loginResult.setTitle(null);
                        }
                    }
                    else if (!TitleUtil.isTitle(loginResult.getTitle(), loginResult.getRole_id())) {
                        loginResult.setTitle(null);
                    }
                }
                else if (AllServiceUtil.getTitletableService().selectRoleTitle(loginResult.getRole_id(), title.getTitlename()) == null) {
                    loginResult.setTitle(null);
                }
            }
            if ((long)loginResult.getMapid() >= 3329L && (long)loginResult.getMapid() <= 3332L) {
                loginResult.setMapid(new Long(1207L));
                loginResult.setX(new Long(4294L));
                loginResult.setY(new Long(2887L));
            }
            int type = 0;
            int type2 = 0;
            long time3 = 0L;
            if (loginResult.getUptime() != null && !loginResult.getUptime().equals("")) {
                time3 = Long.parseLong(loginResult.getUptime());
            }
            Calendar hc = Calendar.getInstance();
            hc.setTimeInMillis(time2);
            hc.set(11, 0);
            hc.set(12, 0);
            hc.set(13, 0);
            hc.set(14, 0);
            Calendar qc = Calendar.getInstance();
            qc.setTimeInMillis(time3);
            qc.set(11, 0);
            qc.set(12, 0);
            qc.set(13, 0);
            qc.set(14, 0);
            long cha = (hc.getTime().getTime() - qc.getTime().getTime()) / 86400000L;
            if (cha > 0L) {
                type = 1;
                if (cha > 1L) {
                    type2 = 1;
                }
                int week = qc.get(7);
                for (int i = 0; (long)i < cha; ++i) {
                    if (++week > 7) {
                        week = 1;
                    }
                    else if (week == 2) {
                        type = 2;
                        break;
                    }
                }
            }
            taskReset(loginResult, type, type2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        loginResult.setUptime(time2 + "");
    }
    
    public static void taskReset(LoginResult loginResult, int type, int type2) {
        if (type == 0) {
            return;
        }
        if (type2 == 0) {
            if (loginResult.getDayfirstinorno() == 0 || loginResult.getDayandpayorno().longValue() >= 7L) {
                loginResult.setDayandpayorno(new BigDecimal(0));
                loginResult.removeVipget("3");
            }
        }
        else {
            loginResult.setDayandpayorno(new BigDecimal(0));
            loginResult.removeVipget("3");
        }
        loginResult.setDayfirstinorno(0);
        loginResult.setDaypaysum(new BigDecimal(0));
        loginResult.setDaygetorno(new BigDecimal(2));
        loginResult.removeVipget("2");
        LadderArenaAction.restTTJL(loginResult);
        loginResult.setTaskComplete(RefreshMonsterTask.ResetTask(loginResult.getTaskComplete(), type));

        String fube =loginResult.getFuben();
        String[] fuben = new String[0];
        if (fube != null) {
            fuben=fube.split("\\|");
        }
        TaskListAll taskListAll=GameServer.getTASK_LIST();
        for (int i = 0; i < fuben.length; i++) {
            TaskList taskList=taskListAll.getTaskBysetID(Integer.parseInt(fuben[i]));
            if (taskList.getResetcycle() == type || taskList.getResetcycle() == type2) {
                fuben[i]=null;
            }
        }
        loginResult.setFuben(null);
        for (int i = 0; i < fuben.length; i++) {
            if (fuben[i] != null) {
                loginResult.addfuben(fuben[i]);
            }
        }

        loginResult.setDBExp(Integer.valueOf(0));
    }
    
    public static String getskin(String skin, List<String> txs, String title, String cb) {
        StringBuffer buffer = new StringBuffer();
        if (skin != null && !skin.equals("")) {
            buffer.append("S");
            buffer.append(skin);
        }
        if (txs != null) {
            for (int i = 0; i < txs.size(); ++i) {
                int id = Integer.parseInt((String)txs.get(i));
                RoleTxBean bean = GameServer.getTxBean(id);
                if (bean != null) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    if (bean.getRdType() == 1) {
                        buffer.append("X");
                    }
                    else if (bean.getRdType() == 2) {
                        buffer.append("P");
                    }
                    else {
                        buffer.append("J");
                    }
                    buffer.append(bean.getRdId());
                    if (bean.getRdType() == 1 || bean.getRdType() == 2) {
                        buffer.append("_");
                        buffer.append(bean.getRdStatues() - bean.getRdType());
                    }
                }
            }
        }
        if (title != null) {
            Title te = GameServer.getTitle(title);
            if (te != null && te.getSkin() != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("C");
                buffer.append(te.getSkin());
            }
        }
        if (cb != null) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("B");
            buffer.append(cb);
        }
        if (buffer.length() == 0) {
            return null;
        }
        return buffer.toString();
    }

    public static String getskin1(String skin,List<String> txs,String title,String cb,int ystx){
//		S:皮肤 X:特效 P:装饰品 J:足迹
//		S1231|X1230_1|P123_1|J12312
        StringBuffer buffer=new StringBuffer();
        if (skin!=null&&!skin.equals("")) {
            buffer.append("S");
            buffer.append(skin);
        }
        if (txs!=null) {
            for (int i = 0; i < txs.size(); i++) {
                int id=Integer.parseInt(txs.get(i));
                RoleTxBean bean=GameServer.getTxBean(id);
                if (bean!=null) {
                    if (buffer.length()!=0) {buffer.append("|");}
//					/**类型（1特效2装饰品3足迹）*/
                    if (bean.getRdType()==1) {
                        buffer.append("X");
                    }else if (bean.getRdType()==2) {
                        buffer.append("P");
                    }else {
                        buffer.append("J");
                    }
                    buffer.append(bean.getRdId());
                    if (bean.getRdType()==1||bean.getRdType()==2) {
                        buffer.append("_");
                        buffer.append(bean.getRdStatues()-bean.getRdType());
                    }
                }
            }
        }
        if (title!=null) {
            Title te=GameServer.getTitle(title);
            if (te!=null&&te.getSkin()!=null) {
                if (buffer.length()!=0) {buffer.append("|");}
                buffer.append("C");
                buffer.append(te.getSkin());
            }
        }
        if (cb!=null) {
            if (buffer.length()!=0) {buffer.append("|");}
            buffer.append("B");
            buffer.append(cb);
        }

        if (ystx!=0){
            if (buffer.length()!=0) {buffer.append("|");}
            buffer.append("Y");
            buffer.append("YYS"+ystx);
        }
        if (buffer.length()==0) {
            return null;
        }
        return buffer.toString();
    }
    static {
        enterGameAction.ZERO = new BigDecimal(0);
    }
}
