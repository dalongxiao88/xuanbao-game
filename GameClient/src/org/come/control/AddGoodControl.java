package org.come.control;

import org.come.Jpanel.RuneOperateJpanel;
import org.come.Jpanel.ForgeJpanel;
import org.come.lianhua.AutoMaticRefiningJframe;
import org.come.Frame.RuneOperateJframe;
import org.come.mouslisten.TransmuteMouslisten;
import org.come.Frame.ForgeJframe;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ImageMixDeal;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.action.FromServerAction;

public class AddGoodControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(mes, Goodstable.class);
        if (goodstable.getRole_id().compareTo(ImageMixDeal.userimg.getRoleShow().getRole_id()) != 0) {
            return;
        }
        if (goodstable.getUsetime() == null) {
            goodstable.setUsetime(Integer.valueOf(1));
        }
        goodstable.setStatus(Integer.valueOf(0));
        GoodsListFromServerUntil.addGood(goodstable);
        if (ForgeJframe.getForgejframe().isVisible() && TransmuteMouslisten.goods2[0] == null) {
            ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
            forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
            TransmuteMouslisten.goods2[0] = goodstable;
        }
        RuneOperateJframe runeOperateJframe = RuneOperateJframe.getRuneOperateJframe();
        if (RuneOperateJframe.getRuneOperateJframe().isVisible() && TransmuteMouslisten.goods3[0] == null) {
            RuneOperateJpanel operateJpanel = runeOperateJframe.getOperateJpanel();
            operateJpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
            TransmuteMouslisten.goods3[0] = goodstable;
            if (AutoMaticRefiningJframe.getAssistantJframe().isVisible()) {
                AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().runeOperateProperties1(goodstable);
            }
            return;
        }
        else {
            if (AutoMaticRefiningJframe.getAssistantJframe().isVisible()) {
                AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().petShidetectionProperties(goodstable);
            }
            return;
        }
    }
}
