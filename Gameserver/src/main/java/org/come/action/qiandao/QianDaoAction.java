package org.come.action.qiandao;

import java.util.Calendar;
import come.tool.Good.ExpUtil;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;
import org.come.model.QianDao;
import java.util.HashSet;
import java.time.LocalDate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class QianDaoAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.contains("open")) {
            String qianDao = loginResult.getQianDao();
            if (StringUtils.isBlank(qianDao)) {
                loginResult.setQianDao("ED=&LQ=");
            }
            String msg = Agreement.getAgreement().QDAgreement("open=" + loginResult.getQianDao());
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
            return;
        }
        else if (message.contains("qd")) {
            Integer dayOfMonth = Integer.valueOf(LocalDate.now().getDayOfMonth());
            HashSet<Integer> ed = loginResult.getQianDaoObject().getEd();
            if (ed.contains(dayOfMonth)) {
                String msg2 = Agreement.getAgreement().QDAgreement("qd=fail&#24您今天已经签到过了！！！");
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg2);
            }
            else {
                Long jy = Long.valueOf(5000L);
                if (loginResult.getGrade() != null && (int)loginResult.getGrade() != 0) {
                    jy = Long.valueOf(5000L * Long.parseLong(loginResult.getGrade().toString()));
                }
                exp(loginResult, jy);
                ed.add(dayOfMonth);
                loginResult.getQianDaoObject().setEd(ed);
                loginResult.saveQiandao();
                String msg3 = Agreement.getAgreement().QDAgreement("qd=succ&" + dayOfMonth);
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg3);
                if (loginResult.getQianDaoObject().getEd().size() == getCurrentMonthDay()) {
                    this.giveGoodByType(ctx, loginResult, 32);
                }
            }
            return;
        }
        else {
            if (message.contains("select")) {
                String[] split = message.split("=");
                int lqGoodType = Integer.parseInt(split[1]);
                HashSet<Integer> lq = loginResult.getQianDaoObject().getLq();
                String mes;
                if (loginResult.getQianDaoObject().getEd().size() < lqGoodType) {
                    mes = "select=fail&请签到达到" + lqGoodType + "天时再领取" + lqGoodType + "天奖励！！！";
                }
                else if (lq.contains(Integer.valueOf(lqGoodType))) {
                    mes = "select=fail&该奖励已经领取过了！！！";
                }
                else {
                    this.giveGoodByType(ctx, loginResult, lqGoodType);
                    lq.add(Integer.valueOf(lqGoodType));
                    loginResult.getQianDaoObject().setLq(lq);
                    loginResult.saveQiandao();
                    mes = "select=succ&" + lqGoodType;
                }
                String msg4 = Agreement.getAgreement().QDAgreement(mes);
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg4);
            }
            return;
        }
    }
    
    private void giveGoodByType(ChannelHandlerContext ctx, LoginResult loginResult, int lqGoodType) {
        QianDao qianDao1 = (QianDao)GameServer.getQianDaoMap().get(Integer.valueOf(lqGoodType));
        System.out.println(qianDao1.getDay());
        QianDao qianDao2 = (QianDao)((List)((Map)GameServer.getQianDaoMap().values().stream().collect(Collectors.groupingBy(QianDao::getDay))).get(Integer.valueOf(lqGoodType))).get(0);
        String[] v = qianDao2.getGoods().split("=")[1].split("&");
        for (int j = 0; j < v.length; ++j) {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            String[] v2 = v[j].split("\\$");
            BigDecimal id = new BigDecimal(v2[0]);
            int sum = Integer.parseInt(v2[1]);
            Goodstable good = GameServer.getGood(id);
            if (good != null) {
                good.setRole_id(loginResult.getRole_id());
                good.setGoodsname(good.getGoodsname());
                good.setUsetime(Integer.valueOf(sum));
                good.setValue(good.getValue());
                good.setType(good.getType());
                good.setInstruction(good.getInstruction());
                assetUpdate.setMsg("恭喜你，签到获取 " + good.getGoodsname() + sum + "个");
                assetUpdate.setType(100);
                assetUpdate.setGood(good);
                AllServiceUtil.getGoodsTableService().insertGoods(good);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
        }
    }
    
    public static void exp(LoginResult loginResult, Long value) {
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        AssetUpdate assetUpdate = new AssetUpdate(25);
        ExpUtil.RoleExp(loginResult, (long)value);
        assetUpdate.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience() + "=" + loginResult.getHp() + "=" + loginResult.getMp());
        StringBuffer buffer = new StringBuffer();
        buffer.append("本日签到成功！获得经验");
        buffer.append(value);
        assetUpdate.setMsg(buffer.toString());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }
}
