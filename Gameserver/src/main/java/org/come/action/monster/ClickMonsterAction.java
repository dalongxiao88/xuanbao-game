package org.come.action.monster;

import org.come.protocol.Agreement;
import org.come.task.MapMonsterBean;
import come.tool.Good.DropUtil;
import come.tool.Battle.BattleMixDeal;
import org.come.model.Robots;
import org.come.handler.SendMessage;
import org.come.task.MonsterUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import org.come.action.IAction;

public class ClickMonsterAction implements IAction
{
    public static String CHECKTS1;
    public static String CHECKTS2;
    private static ConcurrentHashMap<BigDecimal, Long> clickMap;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        int I = Integer.parseInt(message);
        MapMonsterBean bean = MonsterUtil.getMonster(I);
        if (bean == null) {
            SendMessage.sendMessageToSlef(ctx, ClickMonsterAction.CHECKTS2);
            return;
        }
        if (bean.getRobotType() == 0) {
            return;
        }
        if (bean.getRobotType() == 1) {
            String vs = isTime10s(loginResult.getRole_id());
            if (vs != null) {
                SendMessage.sendMessageToSlef(ctx, vs);
                return;
            }
            MonsterUtil.removeMonster(bean, 2);
            Robots robots = (Robots)GameServer.getAllRobot().get(bean.getRobotid() + "");
            if (robots != null) {
                String value = BattleMixDeal.isLvl((int)loginResult.getGrade(), robots.getLvls());
                if (value != null) {
                    SendMessage.sendMessageToSlef(ctx, value);
                    return;
                }
                DropUtil.getDrop(loginResult, robots.getRobotreward(), robots.getUnknow(), 21, 1.0, null);
            }
        }
        else if (bean.getRobotType() == 2) {
            SendMessage.sendMessageToSlef(ctx, bean.getShopMsg());
        }
    }
    
    public static String isTime4s(BigDecimal role_id) {
        Long time = (Long)ClickMonsterAction.clickMap.get(role_id);
        long time2 = System.currentTimeMillis();
        if (time == null) {
            time = Long.valueOf(0L);
        }
        if (time2 - (long)time < 4000L) {
            return ClickMonsterAction.CHECKTS1;
        }
        ClickMonsterAction.clickMap.put(role_id, Long.valueOf(time2));
        return null;
    }
    
    public static String isTime10s(BigDecimal role_id) {
        Long time = (Long)ClickMonsterAction.clickMap.get(role_id);
        long time2 = System.currentTimeMillis();
        if (time == null) {
            time = Long.valueOf(0L);
        }
        if (time2 - (long)time < 10000L) {
            return ClickMonsterAction.CHECKTS1;
        }
        ClickMonsterAction.clickMap.put(role_id, Long.valueOf(time2));
        return null;
    }
    
    public static String isTime20s(BigDecimal role_id) {
        Long time = (Long)ClickMonsterAction.clickMap.get(role_id);
        long time2 = System.currentTimeMillis();
        if (time == null) {
            time = Long.valueOf(0L);
        }
        if (time2 - (long)time < 20000L) {
            return ClickMonsterAction.CHECKTS1;
        }
        ClickMonsterAction.clickMap.put(role_id, Long.valueOf(time2));
        return null;
    }
    
    static {
        ClickMonsterAction.CHECKTS1 = Agreement.getAgreement().PromptAgreement("你点的太快了,休息一下吧");
        ClickMonsterAction.CHECKTS2 = Agreement.getAgreement().PromptAgreement("别看了,已经消失了");
        ClickMonsterAction.clickMap = new ConcurrentHashMap<>();
    }
}
