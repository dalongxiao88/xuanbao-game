package come.tool.newTrans;

import come.tool.Role.RoleData;
import org.come.protocol.Agreement;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class TransStateAction implements IAction
{
    static String MSG1;
    static String MSG2;
    static String MSG3;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            return;
        }
        String[] v = message.split("\\|");
        int zhi1 = Integer.parseInt(v[0]);
        if (zhi1 == 0 || zhi1 == 1) {
            if (login.getBooth_id() != null || (int)login.getFighting() != 0 || TransUtil.isTrans(login.getRolename())) {
                SendMessage.sendMessageByRoleName(login.getRolename(), TransStateAction.MSG1);
                return;
            }
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(v[1]);
            if (ctx2 == null) {
                return;
            }
            LoginResult login2 = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
            if (login2 == null) {
                return;
            }
            if (zhi1 == 0) {
                RoleData roleData = RolePool.getRoleData(login2.getRole_id());
                if ((int)roleData.getRoleSystem().getIsGood() == 0) {
                    SendMessage.sendMessageByRoleName(login.getRolename(), TransStateAction.MSG3);
                    return;
                }
            }
            if (login2.getBooth_id() != null || (int)login2.getFighting() != 0 || TransUtil.isTrans(login2.getRolename())) {
                SendMessage.sendMessageByRoleName(login2.getRolename(), TransStateAction.MSG2);
                return;
            }
            String sendMessage = Agreement.getAgreement().TransStateAgreement(zhi1 + "|" + login.getRolename() + "|" + login.getRole_id());
            SendMessage.sendMessageByRoleName(login2.getRolename(), sendMessage);
            if (zhi1 == 1) {
                sendMessage = Agreement.getAgreement().TransStateAgreement(zhi1 + "|" + login2.getRolename() + "|" + login2.getRole_id());
                SendMessage.sendMessageByRoleName(login.getRolename(), sendMessage);
            }
            if (zhi1 == 1) {
                TransUtil.launchTrans(login, login2);
            }
        }
        else if (zhi1 == 2) {
            TransUtil.removeTrans(login.getRolename());
        }
        else if (zhi1 == 3 || zhi1 == 4 || zhi1 == 5) {
            TransUtil.lockingTrans(login.getRolename(), zhi1);
        }
    }
    
    static {
        TransStateAction.MSG1 = Agreement.getAgreement().PromptAgreement("你处于繁忙状态");
        TransStateAction.MSG2 = Agreement.getAgreement().PromptAgreement("对方处于繁忙状态");
        TransStateAction.MSG3 = Agreement.getAgreement().PromptAgreement("对方未开启交易");
    }
}
