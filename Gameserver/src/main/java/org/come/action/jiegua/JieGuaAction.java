package org.come.action.jiegua;

import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Scene.JieGuaScene;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class JieGuaAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (!JieGuaScene.isStart()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已关闭解卦！！"));
            return;
        }
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        String[] vals = message.split("=");
        String s = vals[0];
        int n = -1;
        switch (s.hashCode()) {
            case 48: {
                if (s.equals("0")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 49: {
                if (s.equals("1")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 50: {
                if (s.equals("2")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 51: {
                if (s.equals("3")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                JieGuaScene.getJieGuaScene().cancelParticipate(loginResult.getRolename());
                break;
            }
            case 1: {
                JieGuaScene.getJieGuaScene().participate(ctx, loginResult.getRolename());
                break;
            }
            case 2: {
                JieGuaScene.getJieGuaScene().pushnote(ctx, loginResult, vals[1]);
                break;
            }
            case 3: {
                JieGuaScene.getJieGuaScene().push(loginResult.getRolename(), 3);
                break;
            }
        }
    }
}
