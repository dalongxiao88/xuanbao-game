package come.tool.JDialog;

import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.UserData;
import org.come.Frame.LingbaoJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.model.Lingbao;
import java.util.Map;

public class LingbaoOut implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            Map<String, Object> object2 = (Map)object;
            Lingbao lingbao = (Lingbao)object2.get("lingbao");
            Goodstable goodstable = (Goodstable)object2.get("g");
            if (GoodsListFromServerUntil.newgood(goodstable)) {
                lingbao.fashijihe(goodstable.getRgid().toString());
                LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
                UserData.upling(lingbao);
                GoodsMouslisten.gooduse(goodstable, 0);
                FormsManagement.HideForm(20);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("背包已经满了");
            }
        }
    }
}
