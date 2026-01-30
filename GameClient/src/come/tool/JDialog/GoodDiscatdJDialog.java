package come.tool.JDialog;

import org.come.entity.Goodstable;
import org.come.until.AccessSuitMsgUntil;
import com.tool.btn.BaptizeBtn;
import org.come.until.Goodtype;
import org.come.Frame.ZhuFrame;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;

public class GoodDiscatdJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            Goodstable good = GoodsListFromServerUntil.getGoodslist()[GoodsMouslisten.replace];
            if (good.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁，不可丢弃");
                return;
            }
            if (Goodtype.EquipmentType((long)good.getType()) != -1 && AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("#W已镶嵌宝石无法丢弃");
                return;
            }
            good.setUsetime(Integer.valueOf(0));
            GoodsMouslisten.gooduse(good, 1);
            GoodsListFromServerUntil.Delete(GoodsMouslisten.replace);
            GoodsMouslisten.goodreplace(-1, -1);
        }
        else {
            GoodsMouslisten.goodreplace(-1, -1);
        }
    }
    
    public void clearAllBack() {
        for (int len = GoodsListFromServerUntil.getGoodslist().length, i = 0; i < len; ++i) {
            Goodstable good = GoodsListFromServerUntil.getGoodslist()[i];
            if (good != null) {
                if (good.getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁，不可丢弃。。");
                }
                else if (Goodtype.EquipmentType((long)good.getType()) != -1 && AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("#W已镶嵌宝石无法丢弃");
                }
                else if ((long)good.getType() == 2010L) {
                    good.setUsetime(Integer.valueOf(0));
                    GoodsMouslisten.gooduse(good, 1);
                    GoodsListFromServerUntil.Delete(i);
                    GoodsMouslisten.goodreplace(-1, -1);
                }
            }
        }
    }
}
