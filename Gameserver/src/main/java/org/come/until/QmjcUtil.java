package org.come.until;

import org.come.entity.Goodstable;
import come.tool.Role.RoleData;
import org.come.tool.WriteOut;
import come.tool.Good.AddGoodAction;
import org.come.entity.Record;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.bean.XXGDBean;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import java.util.Iterator;
import java.util.ArrayList;
import org.come.action.lottery.LotteryRoleAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.Random;
import org.come.model.LotteryData;
import org.come.model.LotteryRole;
import java.util.List;

public class QmjcUtil
{
    public static List<LotteryRole> lotteryRoleList;
    public static List<LotteryData> lotteryDataList;
    public static List<LotteryRole> lotteryRoleRecordList;
    public static String[] number;
    public static Random random;
    public static int total;
    public static int totalMoney;
    public static int multiple;
    
    public static void QmjcMan() {
        int mathMaxInt = 0;
        if (QmjcUtil.lotteryDataList.size() > 0) {
            mathMaxInt = QmjcUtil.lotteryDataList.<LotteryData>stream().mapToInt(LotteryData::getStage).max().getAsInt();
        }
        String num1 = QmjcUtil.number[QmjcUtil.random.nextInt(QmjcUtil.number.length)];
        String num2 = QmjcUtil.number[QmjcUtil.random.nextInt(QmjcUtil.number.length)];
        String num3 = QmjcUtil.number[QmjcUtil.random.nextInt(QmjcUtil.number.length)];
        int b = identical(num1, num2, num3);
        if (b == 2) {
            QmjcUtil.multiple = 100;
        }
        else if (b == 3) {
            QmjcUtil.multiple = 1000;
        }
        NChatBean beand = new NChatBean();
        beand.setId(5);
        beand.setMessage("#R全民竞猜第#G【" + (mathMaxInt + 1) + "】#R期开奖结果：#G【" + num1 + num2 + num3 + "】");
        String msgk = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(beand));
        SendMessage.sendMessageToAllRoles(msgk);
        KJ(num1, num2, num3);
        LotteryData lotteryData = new LotteryData();
        lotteryData.setStage(mathMaxInt + 1);
        lotteryData.setPrizeNumber(num1 + num2 + num3);
        lotteryData.setTotal(QmjcUtil.total);
        lotteryData.setTotalMoney(QmjcUtil.totalMoney);
        QmjcUtil.lotteryDataList.add(lotteryData);
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#R全民竞猜第#G【" + (mathMaxInt + 1) + "】#R期开奖结果：#G【" + lotteryData.getPrizeNumber() + "】#R中奖数#Y【" + lotteryData.getTotal() + "】#R中奖总金额#Y【" + lotteryData.getTotalMoney() + "】");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        if (QmjcUtil.lotteryRoleList.size() > 0) {
            for (LotteryRole lotteryRole : QmjcUtil.lotteryRoleList) {
                LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(lotteryRole.getRole_id());
                LotteryRoleAction.refreshData(roleInfo);
            }
        }
        QmjcUtil.lotteryRoleList = new ArrayList<>();
        QmjcUtil.total = 0;
        QmjcUtil.totalMoney = 0;
    }
    
    public static void KJ(String num1, String num2, String num3) {
        if (QmjcUtil.lotteryRoleList.size() > 0) {
            for (LotteryRole lotteryRole : QmjcUtil.lotteryRoleList) {
                boolean PTQZ = false;
                int zj = 0;
                boolean n1 = false;
                boolean n2 = false;
                boolean n3 = false;
                String[] rolenum = lotteryRole.getPrizeNumber().split("");
                String[] rolen = { rolenum[0], rolenum[1], rolenum[2] };
                List<String> rolent = new ArrayList<>();
                for (int i = 0; i < rolen.length; ++i) {
                    rolent.add(rolen[i]);
                }
                int i = 0;
                while (i < rolent.size()) {
                    if (num1.equals(rolent.get(i))) {
                        n1 = true;
                        ++zj;
                        rolent.remove(i);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                i = 0;
                while (i < rolent.size()) {
                    if (num2.equals(rolent.get(i))) {
                        n2 = true;
                        ++zj;
                        rolent.remove(i);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                for (i = 0; i < rolent.size(); ++i) {
                    if (num3.equals(rolent.get(i))) {
                        n3 = true;
                        ++zj;
                    }
                }
                if (n1 && n2 && n3) {
                    PTQZ = true;
                }
                if (PTQZ) {
                    LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(lotteryRole.getRole_id());
                    ++QmjcUtil.total;
                    QmjcUtil.totalMoney += lotteryRole.getMoney() * QmjcUtil.multiple;
                    AssetUpdate assetUpdate = new AssetUpdate();
                    assetUpdate.updata("D=" + lotteryRole.getMoney() * QmjcUtil.multiple);
                    if (roleInfo != null) {
                        sendReward(roleInfo, lotteryRole.getMoney() * QmjcUtil.multiple + "", lotteryRole.getStage());
                        ChannelHandlerContext ctx1 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename());
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("恭喜您：本期中奖金额" + lotteryRole.getMoney() * QmjcUtil.multiple));
                        NChatBean bean = new NChatBean();
                        bean.setId(5);
                        bean.setMessage("#R恭喜玩家#Y【" + roleInfo.getRolename() + "】在全民竞猜第#G【" + lotteryRole.getStage() + "】#G期获得#Y" + lotteryRole.getMoney() * QmjcUtil.multiple + "两银子真是可喜可贺！！！");
                        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                        SendMessage.sendMessageToAllRoles(msg);
                    }
                }
                else if (zj > 0) {
                    if (zj == 1) {
                        LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(lotteryRole.getRole_id());
                        int zjm = lotteryRole.getMoney() / 2;
                        ++QmjcUtil.total;
                        QmjcUtil.totalMoney += zjm;
                        if (roleInfo != null) {
                            sendReward(roleInfo, zjm + "", lotteryRole.getStage());
                            ChannelHandlerContext ctx1 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename());
                            SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("恭喜您：本期中奖金额" + zjm));
                        }
                    }
                    else if (zj == 2) {
                        LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(lotteryRole.getRole_id());
                        ++QmjcUtil.total;
                        QmjcUtil.totalMoney += lotteryRole.getMoney();
                        if (roleInfo != null) {
                            sendReward(roleInfo, lotteryRole.getMoney() + "", lotteryRole.getStage());
                            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename());
                            SendMessage.sendMessageToSlef(ctx2, Agreement.getAgreement().PromptAgreement("恭喜您：本期中奖金额" + lotteryRole.getMoney()));
                        }
                    }
                }
                else {
                    LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(lotteryRole.getRole_id());
                    if (roleInfo != null) {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename());
                        SendMessage.sendMessageToSlef(ctx2, Agreement.getAgreement().PromptAgreement("很遗憾您未能猜中！"));
                    }
                }
                for (LotteryRole roleRecord : QmjcUtil.lotteryRoleRecordList) {
                    if (roleRecord.getStage() == lotteryRole.getStage() && roleRecord.getPrizeNumber().equals(lotteryRole.getPrizeNumber())) {
                        if (zj > 0 || PTQZ) {
                            roleRecord.setIfWin("已中奖");
                        }
                        else {
                            roleRecord.setIfWin("未中奖");
                        }
                    }
                }
            }
        }
    }
    
    public static int identical(String num1, String num2, String num3) {
        if (num1.equals(num2) && num2.equals(num3)) {
            return 3;
        }
        if (num1.equals(num2) || num2.equals(num3) || num1.equals(num3)) {
            return 2;
        }
        return 0;
    }
    
    public static boolean sendReward(LoginResult loginResult, String money, int num) {
        String ab = ":2126|9938888|1|#AAFFFO";
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        XXGDBean bean = new XXGDBean();
        bean.setId("9938888");
        bean.setSum(1);
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        AssetUpdate assetUpdate = new AssetUpdate(2126);
        BigDecimal id = new BigDecimal(bean.getId());
        Goodstable goodstable = GameServer.getGood(id);
        if (goodstable == null) {
            return true;
        }
        goodstable.setGoodsname("全民竞猜金票");
        goodstable.setInstruction("#Y全民竞猜金票第#G【" + num + "】#Y期中奖获得，右击使用获得对应的银两！");
        goodstable.setSkin("9036");
        goodstable.setValue("钱=" + money);
        StringBuffer buffer = new StringBuffer();
        buffer.append("全民竞猜中奖金票:");
        buffer.append(id);
        buffer.append(",");
        buffer.append(bean.getSum() + "个" + goodstable.getGoodsname());
        buffer.append(",玩家:");
        buffer.append(loginResult.getRole_id());
        buffer.append("_");
        buffer.append(loginResult.getRolename());
        AllServiceUtil.getRecordService().insert(new Record(4, buffer.toString()));
        AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, assetUpdate.getType());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        String msg = "dh:" + ab + ((loginResult != null) ? loginResult.getRole_id() : null);
        WriteOut.addtxt(msg, 9999L);
        return true;
    }
    
    static {
        QmjcUtil.lotteryRoleList = new ArrayList<>();
        QmjcUtil.lotteryDataList = new ArrayList<>();
        QmjcUtil.lotteryRoleRecordList = new ArrayList<>();
        QmjcUtil.number = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
        QmjcUtil.random = new Random();
        QmjcUtil.total = 0;
        QmjcUtil.totalMoney = 0;
        QmjcUtil.multiple = 50;
    }
}
