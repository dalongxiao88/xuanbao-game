package org.come.MountShouHu;

import org.come.until.GoodsListFromServerUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import come.tool.JDialog.TiShiChuLi;

public class ShouHuDiaglog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean p0, Object p1) {
        if (p0) {
            Goodstable goodstable = (Goodstable)p1;
            if (goodstable != null) {
                if (goodstable.getGoodlock() != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("此物品已被加锁");
                    return;
                }
                String sendmes = Agreement.getAgreement().userAgreement(goodstable.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                GoodsListFromServerUntil.shouhu(goodstable.getRgid().intValue());
                goodstable.goodxh((int)goodstable.getUsetime());
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().removeIf(p/* org.come.entity.Goodstable, */ -> (int)p.getUsetime() <= 0);
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().updata();
            }
        }
    }
}
