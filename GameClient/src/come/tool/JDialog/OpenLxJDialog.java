package come.tool.JDialog;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GoodsListFromServerUntil;

public class OpenLxJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
                Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                if (goodstable != null && (long)goodstable.getType() == 119L) {
                    String sendmes = Agreement.getAgreement().LingXiAgreement("K&" + object);
                    SendMessageUntil.toServer(sendmes);
                    return;
                }
            }
            ZhuFrame.getZhuJpanel().addPrompt2("你的灵犀丹不够开启技能格所需的数量");
        }
    }
}
