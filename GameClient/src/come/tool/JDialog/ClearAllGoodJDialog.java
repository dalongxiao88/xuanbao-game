package come.tool.JDialog;

import org.come.entity.Goodstable;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.AccessSuitMsgUntil;
import com.tool.btn.BaptizeBtn;
import org.come.until.Goodtype;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;

public class ClearAllGoodJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            this.clearAllBack();
        }
    }
    
    public void clearAllBack() {
        if (!Util.isCanBuyOrno()) {
            int page = GoodsListFromServerUntil.Pagenumber;
            for (int i = 0; i < 24; ++i) {
                Goodstable good = GoodsListFromServerUntil.getGoodslist()[i + page * 24];
                if (good != null) {
                    if (good.getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt("物品#G" + good.getGoodsname() + "#Y已加锁，不可丢弃");
                    }
                    else if (Goodtype.EquipmentType((long)good.getType()) != -1 && AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) != null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("物品#G" + good.getGoodsname() + "#Y已镶嵌宝石无法丢弃");
                    }
                    else {
                        good.setUsetime(Integer.valueOf(0));
                        GoodsMouslisten.gooduse(good, 1);
                        GoodsListFromServerUntil.Delete(i + page * 24);
                        GoodsMouslisten.goodreplace(-1, -1);
                    }
                }
            }
        }
    }
}
