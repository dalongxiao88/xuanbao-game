package org.come.action.rich;

import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.handler.SendMessage;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RichMonitorAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        InputBean InputBean = (InputBean)GsonUtil.getGsonUtil().getgson().fromJson(message, InputBean.class);
        if (InputBean.getType() == 2) {
            Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(InputBean.getId());
            if (goodstable != null) {
                String messages = null;
                LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                if (roleInfo != null && roleInfo.getRole_id() != null && roleInfo.getRole_id().compareTo(goodstable.getRole_id()) == 0) {
                    messages = Agreement.getAgreement().richMAgreement(InputBean.getType(), GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                }
                else {
                    String value = goodstable.getValue();
                    if (StringUtils.isNotEmpty(value)) {
                        String[] v = value.split("\\|");
                        for (int i = 0; i < v.length; ++i) {
                            if (v[i].startsWith("宝石属性")) {
                                List<String> goodList = new ArrayList<>();
                                String[] v2 = v[i].split("\\&");
                                if (v2.length > 0) {
                                    for (String goodsIdStr : v2) {
                                        if (!goodsIdStr.equals("宝石属性")) {
                                            Goodstable gtable = AllServiceUtil.getGoodsTableService().getGoodsByHashKey(goodsIdStr);
                                            if (gtable != null) {
                                                goodList.add(goodsIdStr + "&" + GsonUtil.getGsonUtil().getgson().toJson(gtable));
                                            }
                                        }
                                    }
                                }
                                goodstable.setOtherInfo(goodList);
                            }
                        }
                    }
                    messages = Agreement.getAgreement().richMAgreement(InputBean.getType(), GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                }
                SendMessage.sendMessageToSlef(ctx, messages);
            }
        }
        else if (InputBean.getType() == 3) {
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(InputBean.getId());
            if (pet != null) {
                LoginResult roleInfo2 = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                String messages2 = null;
                String stye = pet.getStye();
                String innerGoods = pet.getInnerGoods();
                List<String> goodList2 = new ArrayList<>();
                if (StringUtils.isNotEmpty(stye)) {
                    String[] v3 = stye.split("\\|");
                    for (int j = 1; j < v3.length; ++j) {
                        String[] vs = v3[j].split("-");
                        if (vs.length >= 2) {
                            Goodstable gtable2 = AllServiceUtil.getGoodsTableService().getGoodsByHashKey(vs[1]);
                            goodList2.add(vs[1] + "&" + GsonUtil.getGsonUtil().getgson().toJson(gtable2));
                        }
                    }
                }
                if (StringUtils.isNotEmpty(innerGoods)) {
                    String[] v3 = innerGoods.split("\\|");
                    for (int j = 0; j < v3.length; ++j) {
                        Goodstable gtable3 = AllServiceUtil.getGoodsTableService().getGoodsByHashKey(v3[j]);
                        if (gtable3 != null) {
                            goodList2.add(v3[j] + "&" + GsonUtil.getGsonUtil().getgson().toJson(gtable3));
                        }
                    }
                }
                pet.setOtherInfo(goodList2);
                messages2 = Agreement.getAgreement().richMAgreement(InputBean.getType(), GsonUtil.getGsonUtil().getgson().toJson(pet));
                SendMessage.sendMessageToSlef(ctx, messages2);
            }
        }
        else if (InputBean.getType() == 4) {
            Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(InputBean.getId());
            if (lingbao != null) {
                String messages = Agreement.getAgreement().richMAgreement(InputBean.getType(), GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                SendMessage.sendMessageToSlef(ctx, messages);
            }
        }
    }
}
