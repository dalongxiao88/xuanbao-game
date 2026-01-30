package org.come.server;

import come.tool.BangBattle.*;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.FightingForesee;
import come.tool.FightingData.Battlefield;
import come.tool.PK.PKPool;
import come.tool.PK.PkMatch;
import come.tool.Role.RolePool;
import come.tool.Scene.DNTG.DNTGRole;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.LTS.LTSArena;
import come.tool.Scene.LTS.LTSScene;
import come.tool.Scene.Scene;
import come.tool.Scene.SceneUtil;
import come.tool.newTeam.TeamBean;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.bean.LoginResult;
import org.come.bean.PathPoint;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.protocol.ParamTool;
import org.come.server.GameServer;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.come.action.gang.GangBattleAction.*;

public class GolemBangFight {

    public static int[] XYHOME_Left=new int[]{276,1703};
    public static int[] XYHOME_Right=new int[]{2122,367};
    public static int[] XYGAOSHOU=new int[]{2309,1687};
    public static int[] XYZHANC_Left=new int[]{345,1422};
    public static int[] XYZHANC_Right=new int[]{2085,616};
    public static int[] XYDAPAO=new int[]{220,240};//大炮 23  18
    public static int[] XYTA_Left=new int[]{260,1020};//左塔 point.setX(260+Battlefield.random.nextInt(800));//23 point.setY(1020+Battlefield.random.nextInt(400));//51
    public static int[] XYTA_Right=new int[]{1280,620};//右塔 point.setX(64+Battlefield.random.nextInt(700));//64 point.setY(31+Battlefield.random.nextInt(400));//31

    /**
     * 获取建筑坐标
     * @param bh 0大炮   1左门   2，3，4，5左塔    11右门    12，13，14，15右塔
     * @return
     */
    public static PathPoint getBuild(int bh){
        int x,y;
        switch (bh) {
            case 0:
                x= Battlefield.random.nextInt(140)+425;
                y= Battlefield.random.nextInt(70)+360;
                break;
            case 1:
                x= Battlefield.random.nextInt(230)+370;
                y= Battlefield.random.nextInt(140)+1260;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                x= Battlefield.random.nextInt(600)+600;
                y= Battlefield.random.nextInt(280)+1070;
                break;
            case 11:
                x = Battlefield.random.nextInt(200)+1830;
                y = Battlefield.random.nextInt(150)+550;
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                x = Battlefield.random.nextInt(500)+1400;
                y = Battlefield.random.nextInt(350)+650;
                break;
            default:
                x = Battlefield.random.nextInt(1600)+400;
                y = Battlefield.random.nextInt(300)+830;
                break;
        }
        return new PathPoint(x,y);
    }

    /**
     * 机器人组队
     * @param golemBeans
     * @return
     */
    public static void CreatTeam(List<GolemBean> golemBeans) {
        LoginResult[] roles = new LoginResult[golemBeans.size()];
        for (int i = 0; i < golemBeans.size(); i++) {
            roles[i] = golemBeans.get(i).getLoginResult();
        }
        LoginResult Captain = roles[0];
        TeamBean teamBean= TeamUtil.getTeam(Captain.getTroop_id());
        if (teamBean==null){
            teamBean= TeamUtil.addTeam(Captain.getTeamRole());//队长创建队伍
            Captain.setTroop_id(teamBean.getTeamId());
            Captain.setTeamInfo(teamBean.getTeamInfo());
        }
        StringBuffer buffer1=new StringBuffer();
        for (int i = 0; i < roles.length; i++) {
            if (roles[i] != null) {
                if (roles[i] != null&&i!=0) {
                    teamBean.applyTeam(roles[i].getTeamRole());
                    addteam (teamBean, roles[i], roles[i].getRole_id (), null);
                }
                roles[i].setTroop_id(teamBean.getTeamId());
                buffer1.append(roles[i].getRolename());
                if (i != roles.length-1) {
                    buffer1.append("|");
                }
            }
        }
        for (int i = 0; i < roles.length; i++) {
            if (roles[i] != null) {
                roles[i].setTeamInfo(buffer1.toString());
            }

//            if (Battlefield.random.nextInt (10) >= 5) {//假人变造型
//            if (roles[i] != null) {
//                roles[i].setSkin (Objects.requireNonNull (JiaRenShow (roles[i]))[0]);
//            }
//            }
            //发送给地图
            StringBuffer buffer=new StringBuffer();
            buffer.append(teamBean.getTeamId());
            buffer.append("#");
            buffer.append(teamBean.getTeamInfo());
            String msg= Agreement.getAgreement().team3Agreement(buffer.toString());
            SendMessage.sendMessageToMapRoles(roles[i].getMapid(), msg);
            //发送给自己
            SendMessage.sendMessageByRoleName(roles[i].getRolename(), Agreement.getAgreement().team1Agreement(GsonUtil.getGsonUtil().getgson().toJson(teamBean)));
        }
    }
    public static void addteam(TeamBean bean, LoginResult loginResult, BigDecimal applyId, ChannelHandlerContext ctx){

        if (bean.getTeamSize () >= 5) {
            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("队伍已满"));
            return;
        }
        TeamRole teamRole = bean.getApply (applyId);
        if (teamRole == null) {
            return;
        }
        LoginResult login = RolePool.getLoginResult (teamRole.getRoleId ());
//        if (login == null) {
//            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("玩家已经离线"));
//            return;
//        }
        TeamBean teamBean = TeamUtil.getTeam (login.getTroop_id ());
        if (teamBean != null) {
            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("玩家已经有队伍了"));
            return;
        }
        if (login.getMapid () == 3315) {
            BangFight bangFight = BangBattlePool.getBangBattlePool ().getBangFight (login.getGang_id ());
            if (bangFight != null) {
                Member member = bangFight.getrole (login.getRolename ());
                if (member != null) {
                    if (member.getState () != 0) {
                        SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("申请人处于忙碌状态"));
                        return;
                    }
                    Member member2 = bangFight.getrole (loginResult.getRolename ());
                    if (member2 != null) {
                        if (member2.getCamp ().compareTo (member.getCamp ()) != 0) {
                            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("申请人和你不是同个阵营"));
                            return;
                        }
                    }
                }
            }
        } else if (login.getMapid () == DNTGScene.mapIdF || login.getMapid () == DNTGScene.mapIdZ
                || loginResult.getMapid () == DNTGScene.mapIdF || loginResult.getMapid () == DNTGScene.mapIdZ) {
            Scene scene = SceneUtil.getScene (SceneUtil.DNTGID);
            if (scene != null) {
                DNTGScene dntgScene = (DNTGScene) scene;
                DNTGRole role1 = dntgScene.getRole (loginResult.getRole_id ());
                DNTGRole role2 = dntgScene.getRole (login.getRole_id ());
                if (role1 == null || role2 == null || role1.getCamp () != role2.getCamp ()) {
                    SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("申请人和你不是同个阵营"));
                    return;
                }
            }
        } else if ((loginResult.getMapid () >= 3329 && loginResult.getMapid () <= 3332) || (login.getMapid () >= 3329 && login.getMapid () <= 3332)) {
            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("禁止组队"));
            return;
        }
        PkMatch match = PKPool.getPkPool ().seekPkMatch (login.getRole_id ());
        if (match != null) {
            SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("申请人是携带战书没法加入队伍"));
            return;
        }
//        match = PKPool.getPkPool ().seekPkMatch (loginResult.getRole_id ());
//        if (match != null && match.getType () == 11) {
//            Scene scene = SceneUtil.getScene (SceneUtil.LTSID);
//            if (scene != null) {
//                LTSScene ltsScene = (LTSScene) scene;
//                LTSArena arena = ltsScene.getLZ (loginResult.getRole_id ());
//                if (arena != null) {
//                    if (login.getGrade () > arena.getMaxLvl ()) {
//                        SendMessage.sendMessageToSlef (ctx, Agreement.getAgreement ().PromptAgreement ("申请人超过当前擂台等级限制"));
//                        return;
//                    }
//                }
//            }
//        }
        teamRole = login.getTeamRole ();
        bean.addTeamRole (teamRole, loginResult.getMapid ().longValue () == login.getMapid ().longValue () ? 0 : -1);
    }
    /**
     * 参加帮战
     * @param golemBean
     */
    public static void intobang(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        // 获得人物信息
        LoginResult roleInfo = golemBean.getLoginResult();
        if (roleInfo.getGang_id().intValue() == 0) {
            System.err.println("机器人没有帮");
            golemBean.setBangtime(88);
            return;
        }
//        if (roleInfo.getGrade()<31) {
//            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("怎么也得有30级吧"));
//            return;
//        }
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null) {
            if (roleInfo.getGang_id().longValue()==0) {
                System.err.println("机器人还没有加入帮战");
                return;
            }
            BangPoints bangPoints=pool.getBangPoints(roleInfo.getGang_id());
            if (bangPoints==null) {
//                System.err.println("机器人帮派还没报名帮战");
                return;
            }
            if (bangPoints.getRank()!=-1) {
                System.err.println("机器人帮派在本周取得第"+bangPoints.getRank()+"名次");
                return;
            }
            System.err.println("机器人帮战还未开启");
        }else {
            if (bangFight.BangState!=1) {
                System.err.println("机器人帮战还未开启");
                return;
            }
            if (bangFight.addMember(roleInfo.getRolename(), roleInfo.getGang_id())) {
                //送进帮战
                roleInfo.setTitle(roleInfo.getGangname()+"帮"+roleInfo.getGangpost());
                bangFight.getzk(roleInfo.getRolename(), true);
                IAction action= ParamTool.ACTION_MAP.get("changemap");
                action.action(ctx,bangFight.iscamp(roleInfo.getGang_id())?HOME_Left:HOME_Right);
            }
        }
    }

    /**
     * 回到营地
     * @param ctx
     */
    public static void gohome(ChannelHandlerContext ctx){
        LoginResult roleInfo = GolemServer.loginGolems.get(ctx).getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1){
            IAction action=ParamTool.ACTION_MAP.get("changemap");
            action.action(ctx,HOME_Left);
            return;
        }
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null){
            IAction action=ParamTool.ACTION_MAP.get("changemap");
            action.action(ctx,bangFight.iscamp(roleInfo.getGang_id())?HOME_Left:HOME_Right);
            return;
        }
//        if (member.getState()!=0) {//随风——修改后对面不来人也可以打帮战
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        // 队伍中所有角色(队伍传送)
        String[] roles = roleInfo.getTeam().split("\\|");
        for (int i = 0; i < roles.length; i++) {
            Member role=bangFight.getMember(roles[i], roleInfo.getGang_id());
            if (role!=null){
                role.setState(1);
                role.setTime2(5);//60
            }
        }
        IAction action=ParamTool.ACTION_MAP.get("changemap");
        action.action(ctx,bangFight.iscamp(roleInfo.getGang_id())?HOME_Left:HOME_Right);
    }

    //类型4 我要进入战场
    public static void intoBattle(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        // 获得人物信息
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
//        if (member.getState()!=0) {//随风——修改后对面不来人也可以打帮战
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        IAction action=ParamTool.ACTION_MAP.get("changemap");
        action.action(ctx,bangFight.iscamp(roleInfo.getGang_id())?ZHANC_Left:ZHANC_Right);
    }

    //类型2 我要离开帮战
    public static void outbang(GolemBean golemBean){
        try {
            ChannelHandlerContext ctx=golemBean.getCtx();
            LoginResult roleInfo = golemBean.getLoginResult();
//        if (roleInfo.getTeamInfo() != null && !roleInfo.getTeamInfo().equals("")) {
//            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("离开后在组队"));
//            return;
//        }
            BangBattlePool pool=BangBattlePool.getBangBattlePool();
            BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
            if (bangFight!=null)
                bangFight.removeMember(roleInfo.getRolename(), roleInfo.getGang_id());
            //离开帮战地图
            IAction action=ParamTool.ACTION_MAP.get("changemap");
            action.action(ctx, OUTBANG);
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

    }
    //类型6 我要挑战
    public static void ytz(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
//        if (member.getState()!=0) {//随风——修改后对面不来人也可以打帮战
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        if (roleInfo.getTeam().split("\\|").length<MINSUM) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最少人数"+MINSUM));
            return;
        }
        if (bangFight.Launch!=null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已经有人在叫战了"));
            return;
        }else if (bangFight.PKstate!=0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("本轮挑战赛已经开始了"));
            return;
        }else {
            member.setState(3);
            bangFight.getzk(roleInfo.getRolename(),false);
            //补充
            StringBuffer buffer=new StringBuffer();
            buffer.append("#G");
            buffer.append(roleInfo.getRolename());
            buffer.append("#Y说在座各位都是垃圾");
            bangFight.BattleNews(buffer.toString());
            bangFight.Launch=member;
            golemBean.setBangtime(6);
        }
    }
    //类型7 我要取消挑战
    public static void qxtz(ChannelHandlerContext ctx){
        LoginResult roleInfo = GameServer.getAllLoginRole().get(ctx);
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
        if (bangFight.Launch!=null) {
            if (bangFight.Launch.getKey().equals(roleInfo.getRolename())) {
                member.setState(0);
                bangFight.getzk(roleInfo.getRolename(),false);
                bangFight.Launch=null;
                //补充
                StringBuffer buffer=new StringBuffer();
                buffer.append("#G ");
                buffer.append(roleInfo.getRolename());
                buffer.append(" #Y灰溜溜离开挑战台");
                bangFight.BattleNews(buffer.toString());
            }
        }
    }
    //类型8 我要应战
    public static void jstz(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
//        if (member.getState()!=0) {//随风——修改后对面不来人也可以打帮战
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        if (roleInfo.getTeam().split("\\|").length<MINSUM) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最少人数"+MINSUM));
            return;
        }
//        if (bangFight.Launch==null) {
//            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有人在叫战"));
//        }else {
//            if (bangFight.Launch.getCamp().compareTo(roleInfo.getGang_id())==0) {
//                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前在叫战的是自己人"));
//            }else {
        //↑ 机器人不用判定了
        String launame=bangFight.Launch.getKey();
//				StringBuffer buffer=new StringBuffer();
//				buffer.append("#G");
//				buffer.append(roleInfo.getRolename());
//				buffer.append("#Y受不了#G ");
//				buffer.append(launame);
//				buffer.append("#Y的挑衅打起来");
//				bangFight.BattleNews(buffer.toString());
//				StringBuffer buffer=new StringBuffer();
//				buffer.append(BangFight.TIME_GAO);
//				buffer.append("|");
//				for (int i = 0; i < array.length; i++) {
//					ChannelHandlerContext ctxx=GameServer.getRoleNameMap().get(launame);
//					if (ctxx==null) {
//						continue;
//					}
//					LoginResult loginResult=GameServer.getAllLoginRole().get(ctx);
//				}
//				bangFight.Msg(buffer.toString());
        //补充
        bangFight.daduan2(bangFight.Launch.getKey(),1);
        FightingForesee foresee=new FightingForesee();
        foresee.setYidui(roleInfo.getTeam());
        LoginResult loginResult=GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(launame));
        if (loginResult == null) {
            System.err.println("机器人没找到对手应战1");
        }
        foresee.setErdui(loginResult.getTeam());
        foresee.setType(12);
        BattleThreadPool.addBattle(ctx, foresee);
        golemBean.setBangtime(6);
//            }
//        }
    }
    //类型9 我要给塔充能
    public static void cn(GolemBean golemBean,int v){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        Build build=bangFight.getBuild(v);
        if (roleInfo.getTeam().split("\\|").length<MINSUM) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最少人数"+MINSUM));
            return;
        }
//        if (build.getState()!=Build.IDLE) {
//            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("塔处于繁忙状态"));
//        }
//		else if (member.getState()!=0) {
//			SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//		}
//        else {
        //↑ 机器人不用判定了
        if (build.getHp()<=0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("塔已经损坏了"));
            return;
        }
        if (build.getType()==Build.TOWER_LONG) {
            build.setState(Build.ATTACK);
        }else {
            build.setState(Build.ENERGY);
        }
        build.setRoleName(roleInfo.getRolename());
        StringBuffer buffer=new StringBuffer();
        buffer.append("#G ");
        buffer.append(roleInfo.getRolename());
        buffer.append(" #Y正在给#G ");
        buffer.append(build.getName());
        buffer.append(" #Y充能");
        bangFight.BattleNews(buffer.toString());
        //补充
        member.setState(4);
        bangFight.getzk(roleInfo.getRolename(),false);
        golemBean.setBangtime(6);
//        }
    }
    //类型10 我要攻击塔
    public static void gjt(GolemBean golemBean,int v){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        Build build=bangFight.getBuild(v);
        if (roleInfo.getTeam().split("\\|").length<MINSUM) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最少人数"+MINSUM));
            return;
        }
        if (build.getState()!=Build.IDLE) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("塔处于繁忙状态"));
        }
//		else if (member.getState()!=0) {
//			SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//		}
        else {
            if (build.getHp()<=0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("塔已经损坏了"));
                return;
            }
            if (build.getType()==Build.TOWER_LONG) {
                build.setState(Build.ATTACK);
            }else {
                build.setState(Build.BEATEN);
            }
            build.setRoleName(roleInfo.getRolename());
            StringBuffer buffer=new StringBuffer();
            buffer.append("#G ");
            buffer.append(roleInfo.getRolename());
            buffer.append(" #Y正在攻击#G ");
            buffer.append(build.getName());
            bangFight.BattleNews(buffer.toString());
            //补充
            member.setState(4);
            bangFight.getzk(roleInfo.getRolename(),false);
            golemBean.setBangtime(6);
        }
    }
    //类型11 我要取消操作
    public static void qx(ChannelHandlerContext ctx,int v){
        LoginResult roleInfo = GameServer.getAllLoginRole().get(ctx);
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        Build build=bangFight.getBuild(v);
        if (build.getState()!=Build.IDLE) {
            if (build.getRoleName().equals(roleInfo.getRolename())) {
                build.setState(Build.IDLE);
                build.setRoleName(null);
                //补充
                member.setState(0);
                bangFight.getzk(roleInfo.getRolename(),false);
            }
        }
    }
    //类型12 我要掐断炮火
    public static void qdph(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
//        if (member.getState()!=0) {
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        if (roleInfo.getTeam().split("\\|").length<MINSUM) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最少人数"+MINSUM));
            return;
        }
        Build build=bangFight.getBuild(0);
        if (build.getState()!=Build.ATTACK) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没人在点火"));
            return;
        }
        String launame=build.getRoleName();
        ChannelHandlerContext laun=GameServer.getRoleNameMap().get(launame);
        LoginResult loginResult;
        if (laun == null) {
            loginResult=GolemServer.loginGolems.get(launame).getLoginResult();
        }else {
            loginResult=GameServer.getAllLoginRole().get(laun);
        }
        if (loginResult==null) {
            build.setState(Build.IDLE);
            build.setTime(0);
            build.setRoleName(null);
            return;
        }
        if (loginResult.getGang_id().compareTo(roleInfo.getGang_id())==0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("自家兄弟？"));
            return;
        }
        StringBuffer buffer=new StringBuffer();
        buffer.append("#G");
        buffer.append(roleInfo.getRolename());
        buffer.append("#Y掐断炮火和#G");
        buffer.append(launame);
        buffer.append("#Y打起来");
        bangFight.BattleNews(buffer.toString());
        //补充
        bangFight.daduan2(launame,3);
        FightingForesee foresee=new FightingForesee();
        foresee.setYidui(roleInfo.getTeam());
        foresee.setErdui(loginResult.getTeam());
        foresee.setType(11);
        BattleThreadPool.addBattle(ctx, foresee);
        golemBean.setBangtime(6);
    }
    //类型3 我要进入高手挑战赛
    public static void intomaster(GolemBean golemBean){
        ChannelHandlerContext ctx=golemBean.getCtx();
        LoginResult roleInfo = golemBean.getLoginResult();
        BangBattlePool pool=BangBattlePool.getBangBattlePool();
        BangFight bangFight=pool.getBangFight(roleInfo.getGang_id());
        if (bangFight==null||bangFight.BangState!=1)return;
        Member member=bangFight.getMember(roleInfo.getRolename(), roleInfo.getGang_id());
        if (member==null)return;
//        if (member.getState()!=0) {//随风——修改后对面不来人也可以打帮战
//        	SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态无法操作"));
//			return;
//		}
        IAction action=ParamTool.ACTION_MAP.get("changemap");
        action.action(ctx,GAOSHOU);
        golemBean.setBangtime(6);
    }
}
