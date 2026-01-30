package org.come.action.vip;

import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import org.come.tool.EquipTool;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.ChongjipackBean;
import come.tool.Stall.AssetUpdate;
import java.util.List;
import org.come.until.VipRewardUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class DayForWeekGradeSureAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message == null || "".equals(message)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("操作错误"));
            return;
        }
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        BigDecimal dayandpayorno = roleInfo.getDayandpayorno();
        int cRecharge = VipRewardUtils.continuityRecharge(message);
        if (dayandpayorno.intValue() < cRecharge) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您当前不满足领取条件!"));
            return;
        }
        String roleVipInfo = roleInfo.getVipget();
        if (VipRewardUtils.checkYesOrNo(roleVipInfo, "3", cRecharge + "")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您已经领取该礼包!"));
            return;
        }
        StringBuffer vipInfo = new StringBuffer();
        VipRewardUtils.setVipget(roleInfo, "3", cRecharge + "");
        vipInfo.append(roleInfo.getVipget());
        List<ChongjipackBean> chongList = (List<ChongjipackBean>)GameServer.getPackgradecontrol().get(Integer.valueOf(5));
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(30);
        vipInfo.append(roleInfo.getVipget());
        assetUpdate.setVip(vipInfo.toString());
        int i = 0;
        while (i < chongList.size()) {
            if (cRecharge == (int)((ChongjipackBean)chongList.get(i)).getPackgradetype()) {
                String rewardStr = ((ChongjipackBean)chongList.get(i)).getPackgoods();
                this.chongjiPack(rewardStr, ctx, roleInfo, assetUpdate);
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public void chongjiPack(String value, ChannelHandlerContext ctx, LoginResult loginResult, AssetUpdate assetUpdate) {
        try {
            String[] v = value.split("\\|");
            StringBuffer msg = new StringBuffer();
            int i = 0;
            while (i < v.length) {
                if (v[i].startsWith("物品")) {
                    v = v[i].split("=")[1].split("\\&");
                    for (int j = 0; j < v.length; ++j) {
                        Goodstable goodstable = new Goodstable();
                        String[] v2 = v[j].split("\\$");
                        BigDecimal id = new BigDecimal(v2[0]);
                        int sum = Integer.parseInt(v2[1]);
                        Goodstable good = GameServer.getGood(id);
                        if (good != null) {
                            good.setRole_id(loginResult.getRole_id());
                            if (good.getType() == 60002L) {
                                goodstable.setUsetime(Integer.valueOf(1));
                                goodstable.setValue(good.getValue());
                                goodstable.setType(good.getType());
                            }
                            else if (EquipTool.canSuper(good.getType())) {
                                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), good.getGoodsid());
                                if (sameGoodstable.size() != 0) {
                                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum));
                                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                                    good = (Goodstable)sameGoodstable.get(0);
                                }
                                else {
                                    good.setUsetime(Integer.valueOf(sum));
                                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                                }
                                assetUpdate.setGood(good);
                                msg.append(good.getGoodsname());
                                msg.append("|");
                                AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(sum), Integer.valueOf(3));
                            }
                            else {
                                for (int k = 0; k < sum; ++k) {
                                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                                    assetUpdate.setGood(good);
                                    msg.append(good.getGoodsname());
                                    msg.append("|");
                                    AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(3));
                                }
                            }
                        }
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            assetUpdate.setMsg(msg.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
