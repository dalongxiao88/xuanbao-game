package org.come.extInterface.sale;

import org.come.entity.UserTable;
import java.text.DateFormat;
import org.come.entity.Salegoods;
import org.come.entity.Message;
import com.gl.service.GameService;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.come.entity.Roleorder;
import org.come.action.sale.MyOrderSearchAction;
import org.come.action.monitor.MonitorUtil;
import java.util.Date;
import java.util.Calendar;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsBuy implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal saleid = new BigDecimal(message);
        Salegoods sale = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(saleid);
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if ((int)sale.getFlag() != 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("物品已经被其他玩家购买或被卖家下架"));
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sale.getUptime());
        calendar.add(11, 169);
        if (new Date().getTime() > calendar.getTime().getTime()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("物品已经下架"));
            return;
        }
        if (sale.getBuyrole() != null && sale.getBuyrole().compareTo(new BigDecimal(0)) != 0 && roleInfo.getRole_id().compareTo(sale.getBuyrole()) != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("这件物品指定了卖家，你不可以购买"));
            return;
        }
        BigDecimal price = sale.getSaleprice();
        if ((int)sale.getSaletype() == 2) {
            if (roleInfo.getCodecard().compareTo(sale.getSaleprice()) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有足够的仙玉来购买这件物品"));
                return;
            }
            roleInfo.setCodecard(roleInfo.getCodecard().subtract(sale.getSaleprice()));
            MonitorUtil.getMoney().useX(sale.getSaleprice().longValue());
        }
        else if ((int)sale.getSaletype() != 1 && sale.getSalename() != null && !"".equals(sale.getSalename())) {
            if (MyOrderSearchAction.getDate(Integer.valueOf(1)).compareTo(sale.getUptime()) == -1) {
                price = new BigDecimal(sale.getSaleprice().longValue() + sale.getSaleprice().longValue() * 20L / 100L);
            }
            if (roleInfo.getCodecard().compareTo(price) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有足够的仙玉来购买这件物品"));
                return;
            }
            roleInfo.setCodecard(roleInfo.getCodecard().subtract(price));
            MonitorUtil.getMoney().useX(price.longValue());
        }
        else {
            return;
        }
        Roleorder roleorder = new Roleorder();
        roleorder.setSaleid(sale.getSaleid());
        roleorder.setRoleid(roleInfo.getRole_id());
        roleorder.setStatus(Integer.valueOf(3));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        Date nowDate = null;
        try {
            nowDate = dateFormat.parse(nowdayTime);
            roleorder.setBuytime(nowDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        AllServiceUtil.getRoleorderService().insert(roleorder);
        sale.setFlag(Integer.valueOf(4));
        AllServiceUtil.getSalegoodsService().updateByPrimaryKey(sale);
        LoginResult role = AllServiceUtil.getRoleTableService().selectRoleID(sale.getRoleid());
        ChannelHandlerContext masterctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(role.getUserName());
        LoginResult login = (masterctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(masterctx)) : null;
        if (login != null) {
            login.setCodecard(login.getCodecard().add(sale.getSaleprice()));
            AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.CBGGET);
            assetUpdate.setMsg("你寄售的" + sale.getSalename() + "已经被玩家买走了，获得仙玉" + sale.getSaleprice().longValue());
            assetUpdate.updata("X=" + sale.getSaleprice().longValue());
            SendMessage.sendMessageByRoleName(role.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            String msg = "#Y[系统消息] #G你寄售的#R" + sale.getSalename() + "#G已经被玩家#Y" + roleInfo.getRolename() + "#G买走了，获得仙玉#R" + sale.getSaleprice().longValue();
            new GameService().sendMsgToPlayer(msg, login.getRolename());
        }
        else {
            role.setCodecard(role.getCodecard().add(sale.getSaleprice()));
            AllServiceUtil.getRoleTableService().updateRoleWhenExit(role);
            UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(role.getUser_id());
            userTable.setCodecard(userTable.getCodecard().add(sale.getSaleprice()));
            role.setCodecard(role.getCodecard().add(sale.getSaleprice()));
            AllServiceUtil.getUserTableService().updateUser(userTable);
        }
        AllServiceUtil.getRoleTableService().updateRoleWhenExit(roleInfo);
        Message message2 = new Message();
        message2.setRoleid(role.getRole_id());
        message2.setSaleid(sale.getSaleid());
        try {
            nowDate = dateFormat.parse(nowdayTime);
            message2.setGettime(nowDate);
        }
        catch (ParseException e2) {
            e2.printStackTrace();
        }
        message2.setMescontent("物品" + sale.getSalename() + "已卖出");
        AllServiceUtil.getMessageService().insert(message2);
        AssetUpdate assetUpdateBuy = new AssetUpdate();
        assetUpdateBuy.setType(AssetUpdate.CBGBUY);
        assetUpdateBuy.setMsg("你已成功购买了" + sale.getSalename() + "，请到《我的货物》中收取");
        assetUpdateBuy.updata("X=" + -price.longValue());
        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdateBuy)));
    }
}
