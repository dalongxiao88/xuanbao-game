package come.tool.JDialog;

import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.util.ArrayList;
import org.come.until.ZxpackFromServerUntil;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.mouslisten.GoodsMouslisten;

public class ZxpackJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (GoodsMouslisten.zxGoodsId == null) {
                ZhuFrame.getZhuJpanel().addPrompt("自选礼包已经失效了#23");
                return;
            }
            int goodPlace = (int)object;
            if (goodPlace >= 0) {
                Goodstable goodstable = (Goodstable)ZxpackFromServerUntil.getGoodslist().get(goodPlace);
                ZxpackFromServerUntil.upRecord();
                int total = 1;
                ZxpackFromServerUntil.setShowlist(new ArrayList<>());
                String sendmes = Agreement.getAgreement().getZxpackAgreement("ADD|" + total + "|" + goodstable.getGoodsid() + "|" + ZxpackFromServerUntil.getRecord() + "|" + GoodsMouslisten.zxGoodsId);
                SendMessageUntil.toServer(sendmes);
                GoodsMouslisten.zxGoodsId = null;
            }
            FormsManagement.HideForm(643);
            ZxpackFromServerUntil.drop();
        }
    }
}
