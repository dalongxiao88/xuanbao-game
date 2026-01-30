package org.come.until;

import java.util.concurrent.Executors;
import java.util.Iterator;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import org.come.action.monitor.MonitorUtil;
import java.util.concurrent.TimeUnit;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Random;
import org.come.model.HDXData;
import org.come.model.HDXRole;
import java.util.List;

public class HDXUtil
{
    public static List<HDXRole> HdxRoleList;
    public static List<HDXData> HdxDataList;
    public static List<HDXRole> HdxRoleRecordList;
    public static String[] number;
    public static Random random;
    public static int total;
    public static long totalMoney;
    public static long totalMoney1;
    private static String tch;
    private static String tch1;
    public static ScheduledExecutorService executor;
    
    public static void HDXMan() {
        String num1 = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
        String num2 = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
        sendMessage(num1, num2);
        KJ(num1, num2);
        Runnable task = ()/*  */ -> {
            try {
                NChatBean beand = new NChatBean();
                beand.setId(5);
                beand.setMessage("#W开奖啦！！头彩号码为#Y" + HDXUtil.tch + "#W和#Y" + HDXUtil.tch1 + "#W开盘号码为#Y" + num1 + "#W和#Y" + num2 + " ");
                String msgk = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(beand));
                SendMessage.sendMessageToAllRoles(msgk);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        };
        HDXUtil.executor.schedule(task, 5L, TimeUnit.SECONDS);
        HDXData hdxData = new HDXData();
        hdxData.setPrizeNumber(num1 + num2);
        hdxData.setTotal(HDXUtil.total);
        hdxData.setTotalMoney(hdxData.getTotalMoney() + HDXUtil.totalMoney);
        MonitorUtil.getHdxMoney().add(hdxData.getTotalMoney());
        MonitorUtil.getHdxMoney1().add(hdxData.getTotalMoney1());
        HDXUtil.HdxDataList.add(hdxData);
        HDXUtil.HdxRoleList = new ArrayList<>();
        HDXUtil.total = 0;
        HDXUtil.totalMoney = 0L;
    }
    
    public static void TCH() {
        if (HDXUtil.tch == null && HDXUtil.tch1 == null) {
            HDXUtil.tch = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
            HDXUtil.tch1 = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
        }
        else {
            Runnable task = ()/*  */ -> {
                try {
                    HDXUtil.tch = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
                    HDXUtil.tch1 = HDXUtil.number[HDXUtil.random.nextInt(HDXUtil.number.length)];
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            };
            HDXUtil.executor.schedule(task, 5L, TimeUnit.SECONDS);
        }
    }
    
    public static void run() {
        Runnable task = ()/*  */ -> {
            try {
                String msgk = Agreement.getAgreement().PromptAgreement("买定离手：倒计时10秒开奖，各位客官抓紧时间下押！#12");
                SendMessage.sendMessageToMapRoles(Long.valueOf(1197L), msgk);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        };
        HDXUtil.executor.schedule(task, 290L, TimeUnit.SECONDS);
    }
    
    public static void KJ(String num1, String num2) {
        if (HDXUtil.HdxRoleList.size() > 0) {
            for (HDXRole hdxRole : HDXUtil.HdxRoleList) {
                LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(hdxRole.getRole_id());
                ChannelHandlerContext ctx1 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleInfo.getRolename());
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx1);
                String rolenum = hdxRole.getPrizeNumber();
                String b = String.valueOf(identical(num1, num2));
                if (hdxRole.getTpye() == 5) {
                    if (b.equals("1") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney += hdxRole.getMoney() * 25L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("D=" + hdxRole.getMoney() * 25L);
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(hdxRole.getMoney() * 25L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("#Y头彩#W开中，赢" + hdxRole.getMoney() * 25L + "两！"));
                        NChatBean bean = new NChatBean();
                        bean.setId(5);
                        bean.setMessage("#Y头彩#W开中，庄东#Y" + roleInfo.getRolename() + "#W赢 " + hdxRole.getMoney() * 25L + "两，庄家赔");
                        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                        SendMessage.sendMessageToAllRoles(msg);
                    }
                    else if (b.equals("2") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney += hdxRole.getMoney() * 10L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("D=" + hdxRole.getMoney() * 10L);
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(hdxRole.getMoney() * 10L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y双对#W，共得到" + hdxRole.getMoney() * 10L + "两金"));
                    }
                    else if (b.equals("3") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney += hdxRole.getMoney() * 5L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("D=" + hdxRole.getMoney() * 5L);
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(hdxRole.getMoney() * 5L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y七星#W，共得到" + hdxRole.getMoney() * 5L + "两金"));
                    }
                    else if (b.equals("4") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney += hdxRole.getMoney() * 2L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("D=" + hdxRole.getMoney() * 2L);
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(hdxRole.getMoney() * 2L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y散星#W，共得到" + hdxRole.getMoney() * 2L + "两金"));
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("未投中，庄家赢！#28"));
                    }
                }
                else if (hdxRole.getTpye() == 6) {
                    if (b.equals("1") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney1 += hdxRole.getMoney() * 25L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("X=" + hdxRole.getMoney() * 25L);
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(hdxRole.getMoney() * 25L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("#Y头彩#W开中，赢" + hdxRole.getMoney() * 25L + "仙玉！"));
                        NChatBean bean = new NChatBean();
                        bean.setId(5);
                        bean.setMessage("#Y头彩#W开中，庄东#Y" + roleInfo.getRolename() + "#W赢 " + hdxRole.getMoney() * 25L + "两，庄家赔");
                        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                        SendMessage.sendMessageToAllRoles(msg);
                    }
                    else if (b.equals("2") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney1 += hdxRole.getMoney() * 10L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("X=" + hdxRole.getMoney() * 10L);
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(hdxRole.getMoney() * 10L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y双对#W，共得到" + hdxRole.getMoney() * 10L + "仙玉"));
                    }
                    else if (b.equals("3") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney1 += hdxRole.getMoney() * 5L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("X=" + hdxRole.getMoney() * 5L);
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(hdxRole.getMoney() * 5L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y七星#W，共得到" + hdxRole.getMoney() * 5L + "仙玉"));
                    }
                    else if (b.equals("4") && rolenum.equals(b)) {
                        ++HDXUtil.total;
                        HDXUtil.totalMoney1 += hdxRole.getMoney() * 2L - hdxRole.getMoney();
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.updata("X=" + hdxRole.getMoney() * 2L);
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(hdxRole.getMoney() * 2L)));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("你中#Y散星#W，共得到" + hdxRole.getMoney() * 2L + "仙玉"));
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx1, Agreement.getAgreement().PromptAgreement("未投中，庄家赢！#28"));
                    }
                }
                else {
                    continue;
                }
            }
        }
    }
    
    private static void sendMessage(String num1, String num2) {
        SendMessage.sendMessageToMapRoles(Long.valueOf(1197L), Agreement.getAgreement().PromptAgreement("H-" + num1 + "-" + num2));
    }
    
    public static int identical(String num1, String num2) {
        int number1 = Integer.parseInt(num1);
        int number2 = Integer.parseInt(num2);
        int num3 = number1 + number2;
        if (num1.equals(HDXUtil.tch) && num2.equals(HDXUtil.tch1)) {
            return 1;
        }
        if (num1.equals(num2)) {
            return 2;
        }
        if (num3 == 7) {
            return 3;
        }
        if (num3 == 3 || num3 == 5 || num3 == 9 || num3 == 11) {
            return 4;
        }
        return 0;
    }
    
    public static void delHDXRole() {
        Runnable task = ()/*  */ -> {
            try {
                HDXUtil.HdxRoleList.clear();
                HDXUtil.tch = null;
                HDXUtil.tch1 = null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        };
        HDXUtil.executor.schedule(task, 6L, TimeUnit.SECONDS);
    }
    
    public static String getTch() {
        return HDXUtil.tch;
    }
    
    public static void setTch(String tch) {
        HDXUtil.tch = tch;
    }
    
    public static String getTch1() {
        return HDXUtil.tch1;
    }
    
    public static void setTch1(String tch1) {
        HDXUtil.tch1 = tch1;
    }
    
    static {
        HDXUtil.HdxRoleList = new ArrayList<>();
        HDXUtil.HdxDataList = new ArrayList<>();
        HDXUtil.HdxRoleRecordList = new ArrayList<>();
        HDXUtil.number = new String[] { "1", "2", "3", "4", "5", "6" };
        HDXUtil.random = new Random();
        HDXUtil.total = 0;
        HDXUtil.totalMoney = 0L;
        HDXUtil.totalMoney1 = 0L;
        HDXUtil.executor = Executors.newSingleThreadScheduledExecutor();
    }
}
