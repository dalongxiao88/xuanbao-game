package com.gl.util;

import org.come.model.Dorp;
import java.util.Iterator;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import come.tool.Good.DropUtil;
import org.come.server.GameServer;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.until.AllServiceUtil;
import come.tool.Role.RoleData;
import java.util.List;
import java.math.BigDecimal;

public class EggUtil
{
    public static final BigDecimal EGG_ID;
    private static final String[] MESSAGE;
    private static final String CONTENT = "#Y玩家#G{角色名}#Y成功孵化#R元气蛋#Y，获得了灵兽大仙奖励的#G{物品名}#Y。";
    
    public static void success(List<RoleData> list) {
        for (RoleData role : list) {
            List<Goodstable> goodsList = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(role.getLoginResult().getRole_id(), EggUtil.EGG_ID);
            if (goodsList.size() > 0) {
                Goodstable goods = (Goodstable)goodsList.get(0);
                String[] values = goods.getValue().split("=");
                if (values.length == 2) {
                    int sence = (int)Integer.valueOf(values[1]) + 1;
                    if (sence == 100) {
                        if (role.isGoodFull()) {
                            SendMessage.sendMessageByRoleName(role.getLoginResult().getRolename(), Agreement.getAgreement().PromptAgreement("元气蛋吸收了足够的天地精华，蛋内的小东西已经快要破壳而出了，但是你的物品栏已经满了，请至少保留两个空位"));
                        }
                        else {
                            AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(goods.getRgid());
                            Dorp dorp = GameServer.getDorp("10001");
                            if (dorp == null) {
                                return;
                            }
                            DropUtil.getDrop(role.getLoginResult(), dorp.getDorpValue(), "#Y玩家#G{角色名}#Y成功孵化#R元气蛋#Y，获得了灵兽大仙奖励的#G{物品名}#Y。", 25, 1.0, null);
                            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                            assetUpdate.updata("G" + goods.getRgid() + "=" + 0);
                            assetUpdate.setMsg("#G元气蛋成功孵化了，新出生的灵兽化成一股青烟飞走了");
                            SendMessage.sendMessageByRoleName(role.getLoginResult().getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        }
                    }
                    else {
                        goods.setValue("场数=" + sence);
                        AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                        SendMessage.sendMessageByRoleName(role.getLoginResult().getRolename(), Agreement.getAgreement().PromptAgreement(EggUtil.MESSAGE[sence / 20]));
                    }
                }
                else {
                    continue;
                }
            }
        }
    }
    
    static {
        EGG_ID = new BigDecimal(80500);
        MESSAGE = new String[] { "#Y元气蛋吸收了天地精华后毫无动静", "#Y元气蛋吸收了天地精华后好像有了一些动静", "#G元气蛋中传出了微微波动", "#G元气蛋的蛋壳中出现了一道小小的裂纹", "#R元气蛋的蛋壳出现了更多的裂纹似乎快要孵化了" };
    }
}
