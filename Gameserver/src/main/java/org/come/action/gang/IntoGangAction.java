package org.come.action.gang;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.come.entity.Gang;
import come.tool.newGang.GangDomain;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.GangResultBean;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import come.tool.newGang.GangUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class IntoGangAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal gangid = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getGang_id();
        if (gangid.intValue() == 0) {
            return;
        }
        GangDomain domain = GangUtil.getGangDomain(gangid);
        Gang gang = domain.getGang();
        List<LoginResult> members = new ArrayList<>();
        List<LoginResult> roleTables = AllServiceUtil.getRoleTableService().findGangManberByGangID(gangid);
        for (int i = 0, length = roleTables.size(); i < length; ++i) {
            LoginResult loginResult = (LoginResult)roleTables.get(i);
            ChannelHandlerContext gangCtx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            LoginResult result = (gangCtx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(gangCtx)) : null;
            if (result != null) {
                loginResult.setRolename(result.getRolename());
                loginResult.setGangpost(result.getGangpost());
                loginResult.setRace_name(result.getRace_name());
                loginResult.setGrade(result.getGrade());
                loginResult.setAchievement(result.getAchievement());
                loginResult.setContribution(result.getContribution());
                loginResult.setUptime("在线");
                members.add(0, loginResult);
            }
            else {
                members.add(loginResult);
            }
        }
        List<Gangapplytable> gangapplytables = AllServiceUtil.getGangapplyService().getGangapplytables(gangid);
        GangResultBean gangResultBean = new GangResultBean();
        gangResultBean.setGang(gang);
        gangResultBean.setGangGroup(domain.getGangGroup());
        gangResultBean.setRoleTables(members);
        gangResultBean.setGangapplytables(gangapplytables);
        String msg = Agreement.getAgreement().IntogangAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangResultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
