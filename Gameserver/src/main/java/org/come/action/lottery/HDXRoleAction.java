package org.come.action.lottery;

import org.come.until.GsonUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.model.HDXRole;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.time.LocalDateTime;
import org.come.model.HDXData;
import org.come.until.HDXUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class HDXRoleAction implements IAction
{
    private static String tpye;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.startsWith("HDX")) {
            this.setData(ctx, message);
        }
        if (message.startsWith("TCH")) {
            TCroleRecord(loginResult);
        }
    }
    
    public void setData(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        int mathMaxInt = 0;
        if (HDXUtil.HdxDataList.size() > 0) {
            mathMaxInt = HDXUtil.HdxDataList.<HDXData>stream().mapToInt(HDXData::getStage).max().getAsInt();
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() >= 1 && now.getHour() < 19) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("活动未开启，无法购买！！"));
            return;
        }
        if (HDXUtil.getTch() == null && HDXUtil.getTch1() == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("头奖号还没开启不能购买！！"));
            return;
        }
        for (int i = 0; i < HDXUtil.HdxRoleList.size(); ++i) {
            if (((HDXRole)HDXUtil.HdxRoleList.get(i)).getRole_id().equals(loginResult.getRole_id())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你本轮已经下押过了，休想蒙混老夫#24"));
                return;
            }
        }
        if (message == null || message.equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y请选择要下注的类型，并且输入金额！"));
            return;
        }
        HDXRole hdxRole = new HDXRole();
        String msg = message.split("=")[1];
        String[] m = msg.split("-");
        hdxRole.setPrizeNumber(m[0]);
        hdxRole.setTpye(Integer.parseInt(m[1]));
        hdxRole.setMoney(Long.parseLong(m[2]));
        hdxRole.setRole_id(loginResult.getRole_id());
        hdxRole.setStage(mathMaxInt + 1);
        HDXUtil.HdxRoleList.add(hdxRole);
        hdxRole.setIfWin("未开奖");
        HDXUtil.HdxRoleRecordList.add(hdxRole);
        String s = m[0];
        int n = -1;
        switch (s.hashCode()) {
            case 49: {
                if (s.equals("1")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 50: {
                if (s.equals("2")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 51: {
                if (s.equals("3")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 52: {
                if (s.equals("4")) {
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
                HDXRoleAction.tpye = "头彩";
                break;
            }
            case 1: {
                HDXRoleAction.tpye = "双对";
                break;
            }
            case 2: {
                HDXRoleAction.tpye = "七星";
                break;
            }
            case 3: {
                HDXRoleAction.tpye = "散星";
                break;
            }
        }
        if (m[1].equals("5")) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.updata("D=" + -Long.parseLong(m[2]));
            loginResult.setGold(loginResult.getGold().add(new BigDecimal(-Long.parseLong(m[2]))));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你投入" + Long.parseLong(m[2]) + "两下押" + HDXRoleAction.tpye + "，各位快快下押啦！"));
        }
        else if (m[1].equals("6")) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.updata("X=" + -Long.parseLong(m[2]));
            loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(-Long.parseLong(m[2]))));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你投入" + Long.parseLong(m[2]) + "两下押" + HDXRoleAction.tpye + "，各位快快下押啦！"));
        }
    }
    
    public static void TCroleRecord(LoginResult loginResult) {
        String tch = HDXUtil.getTch();
        String tch2 = HDXUtil.getTch1();
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().HDXCPAgreement("TCH=" + tch + "-" + tch2));
    }
}
