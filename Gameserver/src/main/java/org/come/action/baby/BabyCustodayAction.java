package org.come.action.baby;

import org.come.entity.RoleTable;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.entity.Baby;
import org.come.bean.BabyListBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import org.come.action.IAction;

public class BabyCustodayAction implements IAction
{
    private Random random;
    
    public BabyCustodayAction() {
        this.random = new Random();
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BabyListBean babyListBean = new BabyListBean();
        Baby baby = new Baby();
        baby.setChildSex(Integer.valueOf(this.random.nextInt(2)));
        if (message.equals("yes")) {
            baby.setBabyname(loginResult.getRolename() + "的宝宝");
            baby.setRoleid(loginResult.getRole_id());
            AllServiceUtil.getBabyService().createBaby(baby);
            List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(loginResult.getRole_id());
            babyListBean.setBabyList(babys);
            String msg = Agreement.getAgreement().getBaby(GsonUtil.getGsonUtil().getgson().toJson(babyListBean));
            SendMessage.sendMessageToSlef(ctx, msg);
            AchievemUtil.detectionAchievem(loginResult, "拥有一个孩子");
        }
        else {
            baby.setBabyname(loginResult.getMarryObject() + "的宝宝");
            RoleTable roleTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(loginResult.getMarryObject());
            if (roleTable == null) {
                return;
            }
            baby.setRoleid(roleTable.getRole_id());
            AllServiceUtil.getBabyService().createBaby(baby);
            if (GameServer.getRoleNameMap().get(loginResult.getMarryObject()) != null) {
                List<Baby> babys2 = AllServiceUtil.getBabyService().selectBabyByRolename(roleTable.getRole_id());
                babyListBean.setBabyList(babys2);
                String msg2 = Agreement.getAgreement().getBaby(GsonUtil.getGsonUtil().getgson().toJson(babyListBean));
                SendMessage.sendMessageByRoleName(loginResult.getMarryObject(), msg2);
            }
        }
    }
}
