package org.come.XuanBao;


import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoJframe;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;

public class XuanBaogood {
    public static void fushi(Goodstable goodstable) {
        XuanBao lingbao = (RoleXuanBao.getRoleXuanBao()).zhuangbei;
        if (lingbao == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的玄宝");
        } else {
            String[] fuids = null;
            if (lingbao.getFushiIds() != null && !lingbao.getFushiIds().equals("")) {
                fuids = lingbao.getFushiIds().split("\\|");

            }
            if ((fuids == null && lingbao.getNum() > 0) || (fuids != null && fuids.length < lingbao.getNum())) {
                if (lingbao.getFushiIds() != null && !lingbao.getFushiIds().equals("") && lingbao.getFushiIds().equals(goodstable.getRgid())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请勿重复使用符石");
                    return;

                }
                RoleXuanBao.getRoleXuanBao().fushichange(lingbao, goodstable, true);
                GoodsListFromServerUntil.Deleted_good(goodstable);
                (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel().update(lingbao);
                (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(lingbao);

            } else {
                ZhuFrame.getZhuJpanel().addPrompt2("请先开启孔位或孔位已满");
            }
        }
    }
}



