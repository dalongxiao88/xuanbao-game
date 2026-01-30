package org.come.control;

import java.util.List;
import org.come.entity.Goodstable;
import org.come.starcard.JpanelStarCardMain;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.PartJade;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.starcard.JframeStarCardMain;
import org.come.action.NpcMenuAction;

public class StarCardSupplement implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        JpanelStarCardMain jpanelStarCardMain = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain();
        BigDecimal chooseStarCardId = jpanelStarCardMain.getChooseStarCardId();
        if (chooseStarCardId == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
        if (goodstable == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
            return;
        }
        SuitOperBean suitOperBean = new SuitOperBean();
        List<BigDecimal> goods = new ArrayList<>();
        goods.add(chooseStarCardId);
        suitOperBean.setType(59);
        PartJade jade = null;
        suitOperBean.setGoods(goods);
        if ("星芒恢复(100点=1000点战力)".equals(type)) {
            if (RoleData.getRoleData().getLoginResult().getScoretype("星芒").compareTo(new BigDecimal(100)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("星芒不足");
                return;
            }
            jade = new PartJade(1, 0);
        }
        else if ("金币恢复(5000W=1000点战力)".equals(type)) {
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(50000000)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                return;
            }
            jade = new PartJade(0, 0);
        }
        if (jade == null) {
            return;
        }
        suitOperBean.setJade(jade);
        String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
        SendMessageUntil.toServer(sendmes);
    }
}
