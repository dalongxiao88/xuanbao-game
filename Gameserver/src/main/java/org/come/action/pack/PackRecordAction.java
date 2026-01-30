package org.come.action.pack;

import java.util.List;
import org.come.entity.PackRecord;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Role.Hang;
import java.math.BigDecimal;
import org.come.entity.Lingbao;
import java.util.ArrayList;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PackRecordAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        PackRecord packRecord = roleData.getPackRecord();
        String cd = message.substring(0, 1);
        String ab = message.substring(1);
        int type = Integer.parseInt(cd);
        if (type == 0) {
            packRecord.setRecord(ab);
        }
        else if (type == 1) {
            packRecord.setHelpBb(ab);
        }
        else if (type == 2) {
            packRecord.setHelpLing(ab);
            Hang ls = roleData.getLs();
            List<Hang> hangs = new ArrayList<>();
            for (String s : ab.split("\\|")) {
                if (ls == null || !ls.getId().toString().equals(s)) {
                    Lingbao lingbao = new Lingbao();
                    lingbao.setBaoid(new BigDecimal(s));
                    Hang h = new Hang(lingbao, 0);
                    hangs.add(h);
                }
            }
            roleData.setHelpFs(hangs);
        }
        else if (type == 3) {
            packRecord.setSuitNum(Integer.parseInt(ab));
        }
        else if (type != 4) {
            if (type == 5) {
                boolean isP = false;
                StringBuffer buffer = new StringBuffer();
                String[] abs = ab.split("\\|");
                for (int i = 0; i < abs.length; ++i) {
                    if (abs[i].startsWith("E")) {
                        isP = true;
                        packRecord.putTX(abs[i].substring(1).split("_"));
                    }
                    else {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(abs[i]);
                    }
                }
                if (!isP) {
                    packRecord.putTX(new String[] { "0" });
                }
                loginResult.setSkin(buffer.toString());
                String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow()));
                SendMessage.sendMessageToMapRoles(loginResult.getMapid(), sendMes);
            }
            else if (type == 6) {
                packRecord.setPetOrder(ab);
            }
            else if (type == 7) {
                packRecord.putTXK(new String[] { ab });
            }
        }
    }
}
