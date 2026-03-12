package org.come.MountShouHu;

import org.come.until.GoodsListFromServerUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import come.tool.JDialog.TiShiChuLi;

/**
 * 守护材料确认框回调。
 * 用户确认后会把选中的材料提交到服务端并同步刷新本地背包显示。
 */
public class ShouHuDiaglog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean confirmed, Object payload) {
        if (confirmed) {
            Goodstable goodstable = (Goodstable)payload;
            if (goodstable != null) {
                if (goodstable.getGoodlock() != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("此物品已被加锁");
                    return;
                }
                String sendmes = Agreement.getAgreement().userAgreement(goodstable.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                GoodsListFromServerUntil.shouhu(goodstable.getRgid().intValue());
                goodstable.goodxh((int)goodstable.getUsetime());
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().removeIf(item -> (int)item.getUsetime() <= 0);
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().updata();
            }
        }
    }
}
