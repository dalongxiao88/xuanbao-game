package org.come.action.Keju;

import come.tool.Good.DropUtil;
import come.tool.Battle.BattleMixDeal;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import org.come.model.Robots;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class KejuhuidaAction implements IAction
{
    public static String[] vs;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        String[] m = message.split("-");
        int time = 0;
        int i = 0;
        while (i <= KejuAction.role.size() - 1) {
            if (((String[])KejuAction.role.get(i))[0].equals(m[1])) {
                if (((String[])KejuAction.role.get(i))[2].equals(m[0])) {
                    reward(ctx);
                    ((String[])KejuAction.role.get(i))[3] = String.valueOf(Integer.parseInt(((String[])KejuAction.role.get(i))[3]) + 1);
                    if (Integer.parseInt(((String[])KejuAction.role.get(i))[3]) + Integer.parseInt(((String[])KejuAction.role.get(i))[4]) == 20) {
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH-mm-ss");
                        String hehe = dateFormat.format(now);
                        String[] k = hehe.split("-");
                        String[] l = ((String[])KejuAction.role.get(i))[5].split("-");
                        time = (Integer.parseInt(k[1]) - Integer.parseInt(l[1])) * 60 * 60 + (Integer.parseInt(k[2]) - Integer.parseInt(l[2])) * 60 + (Integer.parseInt(k[3]) - Integer.parseInt(l[3]));
                        ((String[])KejuAction.role.get(i))[5] = String.valueOf(time);
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#G恭喜你回答正确！#Y本次乡试答题完毕！共用时" + time + "秒！"));
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#G恭喜你回答正确！#Y请前往下一个官员处继续答题"));
                    }
                    return;
                }
                else {
                    ((String[])KejuAction.role.get(i))[4] = String.valueOf(Integer.parseInt(((String[])KejuAction.role.get(i))[4]) + 1);
                    if (Integer.parseInt(((String[])KejuAction.role.get(i))[3]) + Integer.parseInt(((String[])KejuAction.role.get(i))[4]) == 20) {
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH-mm-ss");
                        String hehe = dateFormat.format(now);
                        String[] k = hehe.split("-");
                        String[] l = ((String[])KejuAction.role.get(i))[5].split("-");
                        time = (Integer.parseInt(k[1]) - Integer.parseInt(l[1])) * 60 * 60 + (Integer.parseInt(k[2]) - Integer.parseInt(l[2])) * 60 + (Integer.parseInt(k[3]) - Integer.parseInt(l[3]));
                        ((String[])KejuAction.role.get(i))[5] = String.valueOf(time);
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#G回答失败！#Y本次乡试答题完毕！共用时" + time + "秒！"));
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R回答失败！#Y请前往下一个官员处继续答题"));
                    }
                    return;
                }
            }
            else {
                ++i;
            }
        }
    }
    
    public static void reward(ChannelHandlerContext ctx) {
        Robots robots = (Robots)GameServer.getAllRobot().get("10003");
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (robots != null) {
            String value = BattleMixDeal.isLvl((int)loginResult.getGrade(), robots.getLvls());
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, value);
                return;
            }
            DropUtil.getDrop(loginResult, robots.getRobotreward(), robots.getUnknow(), 21, 1.0, null);
        }
    }
    
    static {
        KejuhuidaAction.vs = null;
    }
}
