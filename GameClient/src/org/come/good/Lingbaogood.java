package org.come.good;

import org.come.until.GoodsListFromServerUntil;
import org.come.model.Lingbao;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.UserData;
import org.come.Frame.LingbaoJframe;
import org.come.until.ExpIncreaseUntil;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleLingFa;
import org.come.entity.Goodstable;

public class Lingbaogood
{
    public static void baoExp(Goodstable goodstable, int goodPlace) {
        Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
        if (lingbao == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的灵宝");
        }
        else {
            try {
                int addvalue = Integer.parseInt(goodstable.getValue().split("=")[1]);
                ExpIncreaseUntil.IncreaseLFExp(lingbao, (long)addvalue);
                LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().sj(lingbao);
                UserData.upling(lingbao);
                goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() - 1));
                GoodsMouslisten.gooduse(goodstable, 1);
            }
            catch (Exception var4) {
                var4.printStackTrace();
            }
        }
    }
    
    public static void fushi(Goodstable goodstable, int goodPlace) {
        Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
        if (lingbao == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的灵宝");
        }
        else if (lingbao.getBaotype().equals("法宝")) {
            ZhuFrame.getZhuJpanel().addPrompt2("法宝不能打符石");
        }
        else {
            String[] fuids = null;
            if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                fuids = lingbao.getFushis().split("\\|");
            }
            if ((fuids == null && lingbao.getFusum() > 0) || (fuids != null && fuids.length < lingbao.getFusum())) {
                if (RoleLingFa.getRoleLingFa().fushity(fuids, goodstable.getGoodsname())) {
                    RoleLingFa.getRoleLingFa().fushichange(lingbao, goodstable, true);
                    GoodsListFromServerUntil.Deleted(goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("你已经穿戴了同类型的符石");
                }
            }
        }
    }
}
