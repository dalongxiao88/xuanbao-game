package org.come.action.Keju;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.until.GsonUtil;
import come.tool.FightingData.Battlefield;
import org.come.entity.Keju;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import org.come.action.IAction;

public class KejuAction implements IAction
{
    public static boolean iskeju;
    public static List<String[]> role;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (!KejuAction.iskeju) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R请先在魏征处领取科举任务"));
        }
        String[] m = message.split("-");
        if (KejuAction.role.size() == 0) {
            String[] v = KejuhuidaAction.vs = Keju.result[Battlefield.random.nextInt(Keju.result.length - 1)];
            String msg = Agreement.getAgreement().KejuAgreement(GsonUtil.getGsonUtil().getgson().toJson(v));
            SendMessage.sendMessageToSlef(ctx, msg);
            KejuAction.role.add(0, v);
            ((String[])KejuAction.role.get(0))[1] = String.valueOf(Integer.parseInt(((String[])KejuAction.role.get(0))[1]) + 1);
            ((String[])KejuAction.role.get(0))[2] = v[6];
            if (((String[])KejuAction.role.get(0))[5].equals("0")) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH-mm-ss");
                String hehe = dateFormat.format(now);
                ((String[])KejuAction.role.get(0))[5] = hehe;
            }
            return;
        }
        else {
            int i = 0;
            while (i <= KejuAction.role.size()) {
                if (((String[])KejuAction.role.get(i))[0].equals(m[1])) {
                    if (!((String[])KejuAction.role.get(i))[1].equals(m[1])) {
                        if (m[1].equals("10019")) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R本次乡试答题完毕，共耗时" + ((String[])KejuAction.role.get(i))[5] + "秒！"));
                        }
                        else {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R请前往下一个考官出继续答题！"));
                        }
                        return;
                    }
                    else {
                        String[] v2 = KejuhuidaAction.vs = Keju.result[Battlefield.random.nextInt(Keju.result.length - 1)];
                        String msg2 = Agreement.getAgreement().KejuAgreement(GsonUtil.getGsonUtil().getgson().toJson(v2));
                        SendMessage.sendMessageToSlef(ctx, msg2);
                        ((String[])KejuAction.role.get(0))[1] = String.valueOf(Integer.parseInt(((String[])KejuAction.role.get(0))[1]) + 1);
                        ((String[])KejuAction.role.get(i))[2] = v2[6];
                        return;
                    }
                }
                else {
                    ++i;
                }
            }
            String[] v = KejuhuidaAction.vs = Keju.result[Battlefield.random.nextInt(Keju.result.length - 1)];
            String msg = Agreement.getAgreement().KejuAgreement(GsonUtil.getGsonUtil().getgson().toJson(v));
            SendMessage.sendMessageToSlef(ctx, msg);
            KejuAction.role.add(KejuAction.role.size(), m);
            ((String[])KejuAction.role.get(KejuAction.role.size()))[2] = v[6];
            ((String[])KejuAction.role.get(KejuAction.role.size() - 1))[1] = String.valueOf(Integer.parseInt(((String[])KejuAction.role.get(KejuAction.role.size() - 1))[1]) + 1);
            if (((String[])KejuAction.role.get(KejuAction.role.size() - 1))[5].equals("0")) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH-mm-ss");
                String hehe = dateFormat.format(now);
                ((String[])KejuAction.role.get(KejuAction.role.size() - 1))[5] = hehe;
            }
            return;
        }
    }
    
    static {
        KejuAction.iskeju = false;
        KejuAction.role = new ArrayList<>();
    }
}
