package org.come.action.suit;

import java.util.Iterator;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.bean.UseCardBean;
import come.tool.Role.RoleData;
import java.util.ArrayList;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Role.RolePool;
import org.come.until.GsonUtil;
import org.come.bean.QualityClBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class QualityCAciton implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        QualityClBean clBean = (QualityClBean)GsonUtil.getGsonUtil().getgson().fromJson(message, QualityClBean.class);
        clBean = QualityCPool.getcPool().getExtra(clBean.getType(), clBean.getRgid());
        if (clBean == null) {
            return;
        }
        if (clBean.getType() >= 70 && clBean.getType() <= 79) {
            RoleData roleData = RolePool.getRoleData(clBean.getRgid());
            if (roleData == null) {
                return;
            }
            int lvl = clBean.getType() - 70;
            UseCardBean cardBean = roleData.getLimit("单人竞技场");
            if (cardBean == null || cardBean.getQls().length < lvl) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还未解锁对应的称谓"));
                return;
            }
            cardBean.upValue(clBean.getNewAttr(), lvl - 1);
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.setUseCard(cardBean);
            assetUpdate.upmsg("替换属性成功");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            AllServiceUtil.getGoodsrecordService().insertGoodsrecord(clBean.getRgid(), null, 50201, clBean.getRgid(), "单人竞技场属性", cardBean.getValue(), 1);
        }
        else if (clBean.getType() == 8 || clBean.getType() == 9) {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(clBean.getRgid());
            if (good == null) {
                return;
            }
            String[] value = good.getValue().split("\\|");
            List<String> value2 = new ArrayList<>();
            for (String s : value) {
                if (!s.startsWith("点粹属性") && !s.startsWith("炼化属性&特技") && !s.startsWith("点翠属性")) {
                    value2.add(s);
                }
            }
            value2.removeIf(bb/* java.lang.String, */ -> bb.equals(""));
            StringBuilder mes = null;
            for (String vv : value2) {
                if (vv.startsWith("培养")) {
                    assert mes != null;
                    mes.append("|");
                    mes.append(clBean.getNewAttr());
                    mes.append("|");
                }
                if (mes == null) {
                    mes = new StringBuilder(vv);
                }
                else {
                    mes.append("|").append(vv);
                }
            }
            assert mes != null;
            good.setValue(mes.toString());
            AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(13));
        }
        else {
            Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(clBean.getRgid());
            if (goodstable == null) {
                return;
            }
            goodstable.setValue(SuitComposeAction.newExtra(goodstable.getValue().split("\\|"), clBean.getType() - 1, clBean.getNewAttr()));
            AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(13));
        }
    }
}
