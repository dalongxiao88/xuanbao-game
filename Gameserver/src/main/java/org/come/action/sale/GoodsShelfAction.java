package org.come.action.sale;

import java.text.DateFormat;
import org.come.entity.Lingbao;
import java.util.Iterator;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.bean.NChatBean;
import org.come.entity.Message;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.entity.RoleSummoning;
import org.come.entity.Record;
import come.tool.Mixdeal.AnalysisString;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import org.come.action.monitor.MonitorUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.entity.Salegoods;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsShelfAction implements IAction
{
    long price;
    
    public GoodsShelfAction() {
        this.price = 9999999999L;
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Salegoods salegoods = (Salegoods)GsonUtil.getGsonUtil().getgson().fromJson(message, Salegoods.class);
        BigDecimal middlePay = BigDecimal.valueOf((double)salegoods.getSaleprice().longValue() * 0.05);
        if (salegoods.getSaleprice().longValue() > this.price) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("售价不能超过99亿"));
            return;
        }
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo.getCodecard().compareTo(middlePay) >= 0) {
            roleInfo.setCodecard(roleInfo.getCodecard().subtract(middlePay));
            MonitorUtil.getMoney().useX(middlePay.longValue());
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("X=-" + middlePay.longValue());
            assetUpdate.upmsg("扣除上架手续费仙玉:#R" + middlePay.longValue());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            String contiontype = "0";
            String saleskin = "";
            String salename = "";
            if ((int)salegoods.getSaletype() == 3 || (int)salegoods.getSaletype() == 5) {
                Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(salegoods.getOtherid());
                if (goods == null || EquipTool.canSuper(goods.getType()) || goods.getRole_id().compareTo(roleInfo.getRole_id()) != 0) {
                    return;
                }
                if (AnalysisString.jiaoyi((long)goods.getQuality())) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(roleInfo.getRole_id());
                    buffer.append("欲藏宝阁上架禁交易物品:");
                    buffer.append(goods.getRgid());
                    buffer.append(":");
                    buffer.append(goods.getGoodsname());
                    AllServiceUtil.getRecordService().insert(new Record(5, buffer.toString()));
                    return;
                }
                contiontype = goods.getType() + "";
                saleskin = goods.getSkin();
                salename = goods.getGoodsname();
                AllServiceUtil.getGoodsrecordService().insert(goods, null, Integer.valueOf(1), Integer.valueOf(13));
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods, goods.getRole_id().multiply(new BigDecimal(-1)), null, null);
            }
            else if ((int)salegoods.getSaletype() == 4) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(salegoods.getOtherid());
                if (pet == null || pet.getRoleid().compareTo(roleInfo.getRole_id()) != 0) {
                    return;
                }
                contiontype = pet.getSsn();
                saleskin = pet.getSummoningskin();
                RoleSummoning roleSummoning = (RoleSummoning)GameServer.getAllPet().get(new BigDecimal(pet.getSummoningid()));
                if (roleSummoning == null) {
                    System.out.println("表格召唤兽信息为空" + pet.getSummoningid());
                    salename = pet.getSummoningname();
                }
                else {
                    salename = roleSummoning.getSummoningname();
                }
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, pet.getRoleid().multiply(new BigDecimal(-1)));
                List<BigDecimal> goods2 = pet.getGoods();
                if (goods2 != null) {
                    for (BigDecimal bigDecimal : goods2) {
                        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bigDecimal);
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, goodstable.getRole_id().multiply(new BigDecimal(-1)), null, null);
                    }
                }
            }
            else if ((int)salegoods.getSaletype() == 6) {
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(salegoods.getOtherid());
                if (lingbao == null || lingbao.getRoleid().compareTo(roleInfo.getRole_id()) != 0) {
                    return;
                }
                contiontype = lingbao.getBaotype();
                saleskin = lingbao.getSkin();
                salename = lingbao.getBaoname();
                AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, lingbao.getRoleid().multiply(new BigDecimal(-1)));
                if (lingbao.getFushis() != null && !"".equals(lingbao.getFushis())) {
                    String[] baos;
                    for (String string : baos = lingbao.getFushis().split("\\|")) {
                        Goodstable goodstable2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(string));
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable2, goodstable2.getRole_id().multiply(new BigDecimal(-1)), null, null);
                    }
                }
            }
            else {
                saleskin = "8";
                salename = "大话币";
                if (roleInfo.getGold().compareTo(salegoods.getOtherid()) >= 0) {
                    roleInfo.setGold(roleInfo.getGold().subtract(salegoods.getOtherid()));
                    AssetUpdate as = new AssetUpdate();
                    as.setData("D=-" + salegoods.getOtherid());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(as)));
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("金币不足"));
                    return;
                }
            }
            salegoods.setRoleid(roleInfo.getRole_id());
            salegoods.setContiontype(contiontype);
            salegoods.setSaleskin(saleskin);
            salegoods.setSalename(salename);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdayTime = dateFormat.format(new Date());
            Date nowDate = null;
            try {
                nowDate = dateFormat.parse(nowdayTime);
                salegoods.setUptime(nowDate);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            salegoods.setFlag(Integer.valueOf(2));
            if (salegoods.getBuyrole().compareTo(new BigDecimal(0)) != 0) {
                Message message2 = new Message();
                message2.setRoleid(salegoods.getBuyrole());
                message2.setMescontent(salename + "指定你购买");
                message2.setSaleid(salegoods.getSaleid());
                message2.setGettime(nowDate);
                AllServiceUtil.getMessageService().insert(message2);
            }
            AllServiceUtil.getSalegoodsService().insert(salegoods);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("上架成功"));
            NChatBean c = new NChatBean();
            c.setId(5);
            int type = 0;
            if ((int)salegoods.getSaletype() == 2) {
                type = 5;
            }
            else if ((int)salegoods.getSaletype() == 3) {
                type = 2;
            }
            else if ((int)salegoods.getSaletype() == 4) {
                type = 3;
            }
            else if ((int)salegoods.getSaletype() == 5) {
                type = 2;
            }
            else if ((int)salegoods.getSaletype() == 6) {
                type = 4;
            }
            c.setMessage("#R藏宝阁公告：#W玩家#G" + roleInfo.getRolename() + "#W将#V{\"type\":" + type + ",\"id\":" + salegoods.getOtherid() + ",\"name\":\"[" + salegoods.getSalename() + "]\",\"color\":\"G\"}#L#W上架了#130");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(c));
            SendMessage.sendMessageToAllRoles(msg);
        }
        else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("仙玉不足"));
        }
    }
}
