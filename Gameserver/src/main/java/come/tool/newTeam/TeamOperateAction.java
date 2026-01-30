package come.tool.newTeam;

import come.tool.Scene.LTS.LTSArena;
import come.tool.PK.PkMatch;
import come.tool.Scene.DNTG.DNTGRole;
import come.tool.Scene.Scene;
import come.tool.BangBattle.Member;
import come.tool.BangBattle.BangFight;
import come.tool.Scene.LTS.LTSScene;
import come.tool.PK.PKPool;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.SceneUtil;
import come.tool.BangBattle.BangBattlePool;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TeamOperateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        TeamBean bean = TeamUtil.getTeam(loginResult.getTroop_id());
        if (bean == null) {
            return;
        }
        boolean is = bean.isCaptian(loginResult.getRole_id());
        if (is) {
            if (message.startsWith("A")) {
                this.addTeam(bean, loginResult, new BigDecimal(message.substring(1)), ctx);
            }
            else if (message.startsWith("R")) {
                bean.getApply(new BigDecimal(message.substring(1)));
            }
            else if (message.startsWith("E")) {
                bean.removeApply();
            }
            else if (message.startsWith("K")) {
                this.kickOutTeam(bean, new BigDecimal(message.substring(1)));
            }
            else if (message.startsWith("S")) {
                this.setCaptainTeam(bean, loginResult, new BigDecimal(message.substring(1)), ctx);
            }
            else if (message.startsWith("D")) {
                bean.dismissTeam();
            }
        }
        else if (message.startsWith("D")) {
            bean.removeTeamRole(loginResult.getRole_id());
        }
    }
    
    public void addTeam(TeamBean bean, LoginResult loginResult, BigDecimal applyId, ChannelHandlerContext ctx) {
        if (bean.getTeamSize() >= 5) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("队伍已满"));
            return;
        }
        TeamRole teamRole = bean.getApply(applyId);
        if (teamRole == null) {
            return;
        }
        LoginResult login = RolePool.getLoginResult(teamRole.getRoleId());
        if (login == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玩家已经离线"));
            return;
        }
        TeamBean teamBean = TeamUtil.getTeam(login.getTroop_id());
        if (teamBean != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玩家已经有队伍了"));
            return;
        }
        if ((long)login.getMapid() == 3315L) {
            BangFight bangFight = BangBattlePool.getBangBattlePool().getBangFight(login.getGang_id());
            if (bangFight != null) {
                Member member = bangFight.getrole(login.getRolename());
                if (member != null) {
                    if (member.getState() != 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("申请人处于忙碌状态"));
                        return;
                    }
                    Member member2 = bangFight.getrole(loginResult.getRolename());
                    if (member2 != null && member2.getCamp().compareTo(member.getCamp()) != 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("申请人和你不是同个阵营"));
                        return;
                    }
                }
            }
        }
        else if ((long)login.getMapid() == 3336L || (long)login.getMapid() == 3201L || (long)loginResult.getMapid() == 3336L || (long)loginResult.getMapid() == 3201L) {
            Scene scene = SceneUtil.getScene(1011);
            if (scene != null) {
                DNTGScene dntgScene = (DNTGScene)scene;
                DNTGRole role1 = dntgScene.getRole(loginResult.getRole_id());
                DNTGRole role2 = dntgScene.getRole(login.getRole_id());
                if (role1 == null || role2 == null || role1.getCamp() != role2.getCamp()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("申请人和你不是同个阵营"));
                    return;
                }
            }
        }
        else if (((long)loginResult.getMapid() >= 3329L && (long)loginResult.getMapid() <= 3332L) || ((long)login.getMapid() >= 3329L && (long)login.getMapid() <= 3332L)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("禁止组队"));
            return;
        }
        PkMatch match = PKPool.getPkPool().seekPkMatch(login.getRole_id());
        if (match != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("申请人是携带战书没法加入队伍"));
            return;
        }
        match = PKPool.getPkPool().seekPkMatch(loginResult.getRole_id());
        if (match != null && match.getType() == 11) {
            Scene scene2 = SceneUtil.getScene(1008);
            if (scene2 != null) {
                LTSScene ltsScene = (LTSScene)scene2;
                LTSArena arena = ltsScene.getLZ(loginResult.getRole_id());
                if (arena != null && (int)login.getGrade() > arena.getMaxLvl()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("申请人超过当前擂台等级限制"));
                    return;
                }
            }
        }
        teamRole = login.getTeamRole();
        login.initTeamRole(teamRole);
        bean.addTeamRole(teamRole, ((long)loginResult.getMapid() == (long)login.getMapid()) ? 0 : -1);
    }
    
    public void kickOutTeam(TeamBean bean, BigDecimal applyId) {
        bean.removeTeamRole(applyId);
    }
    
    public void setCaptainTeam(TeamBean bean, LoginResult loginResult, BigDecimal applyId, ChannelHandlerContext ctx) {
        LoginResult login = RolePool.getLoginResult(applyId);
        if (login == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玩家已经离线"));
            return;
        }
        if ((long)loginResult.getMapid() == 3315L) {
            BangFight bangFight = BangBattlePool.getBangBattlePool().getBangFight(loginResult.getGang_id());
            if (bangFight != null) {
                Member member = bangFight.getrole(loginResult.getRolename());
                if (member != null && member.getState() != 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前状态不能换队长"));
                    return;
                }
            }
        }
        PkMatch match = PKPool.getPkPool().seekPkMatch(loginResult.getRole_id());
        if (match != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你身上携带战书必须当带头的"));
            return;
        }
        String msg = bean.setCaptain(login);
        if (msg != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
        }
    }
}
