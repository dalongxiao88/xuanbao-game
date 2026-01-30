package come.tool.hjsl;

import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.until.GsonUtil;
import org.come.bean.ConfirmBean;
import come.tool.Role.RoleData;
import come.tool.newTask.TaskRecord;
import come.tool.Battle.BattleThreadPool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Role.RolePool;
import org.come.model.Robots;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class HjslAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        if (message.startsWith("P")) {
            Integer index = Integer.valueOf(Integer.parseInt(message.substring(1)) + 9000);
            Robots robots = this.getRobots(String.valueOf(index));
            hjtz(ctx, roleInfo, robots, Integer.parseInt(message.substring(1)));
        }
        else if (message.equals("R")) {
            addNumHjsl(ctx, roleInfo, null);
        }
    }
    
    public Robots getRobots(String advance) {
        return (Robots)GameServer.getAllRobot().get(advance);
    }
    
    public static void hjtz(ChannelHandlerContext ctx, LoginResult roleInfo, Robots robots, int max) {
        RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
        if (roleData == null) {
            return;
        }
        String[] teams = roleInfo.getTeam().split("\\|");
        int num = 5;
        TaskRecord taskRecord = roleData.getTaskRecord(6);
        if (taskRecord != null) {
            num += taskRecord.getrSum();
            num -= taskRecord.getcSum();
        }
        if (num <= 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("今日挑战次数已用完"));
            return;
        }
        boolean value = BattleThreadPool.HJSL(roleInfo, teams, robots, 1);
        if (!value) {
            SendMessage.sendMessageToSlef(ctx, "数据加载失败!");
            return;
        }
        if (taskRecord == null) {
            taskRecord = new TaskRecord(6);
            roleData.addTaskRecord(taskRecord);
        }
    }
    
    public static void addNumHjsl(ChannelHandlerContext ctx, LoginResult roleInfo, Object object) {
        RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
        if (roleData == null) {
            return;
        }
        TaskRecord taskRecord = roleData.getTaskRecord(6);
        if (taskRecord != null && taskRecord.getrSum() >= 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("达到每日购买上限"));
            return;
        }
        int moeny = (((taskRecord != null) ? taskRecord.getrSum() : 0) + 1) * 20000;
        if (roleInfo.getCodecard().longValue() < (long)moeny) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的仙玉不足" + moeny));
            return;
        }
        if (object == null) {
            ConfirmBean confirmBean = new ConfirmBean();
            confirmBean.setMSG("你是否要消耗#G" + moeny + "仙玉#W购买挑战次数");
            confirmBean.setType(103);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().confirmAgreement(GsonUtil.getGsonUtil().getgson().toJson(confirmBean)));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        roleInfo.setCodecard(new BigDecimal(roleInfo.getCodecard().longValue() - (long)moeny));
        assetUpdate.updata("X=-" + moeny);
        MonitorUtil.getMoney().useX((long)moeny);
        if (taskRecord == null) {
            taskRecord = new TaskRecord(6);
            roleData.addTaskRecord(taskRecord);
        }
        taskRecord.addRSum(1);
        assetUpdate.setTask("C6=R");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
}
