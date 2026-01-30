package come.tool.JDialog;

import org.come.bean.Bbuy;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.GiveGoodsBean;
import org.come.until.UserMessUntil;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.AccessSuitMsgUntil;
import com.tool.btn.BaptizeBtn;
import org.come.until.Goodtype;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;

public class SellAllGoodJDialog implements TiShiChuLi
{
    private static boolean flag;
    
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
                if (good != null && good.getGoodlock() != 1 && (Goodtype.EquipmentType((long)good.getType()) == -1 || AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) == null)) {
                    this.giveshipolan(2, (int)good.getUsetime(), good);
                }
            }
            if (!SellAllGoodJDialog.flag) {
                ZhuFrame.getZhuJpanel().addPrompt("当前背包页没有可回收的道具！");
            }
        }
    }
    
    public void giveshipolan(int type, int count, Goodstable giveGood) {
        Bbuy bbuy = UserMessUntil.getBbuy(giveGood.getGoodsid());
        if (bbuy == null || (bbuy.getPrice1() == 0L && type == 1) || (bbuy.getPrice2() == 0L && type == 2)) {
            return;
        }
        SellAllGoodJDialog.flag = true;
        GiveGoodsBean givebean = new GiveGoodsBean();
        givebean.setType(type);
        givebean.setRgid(giveGood.getRgid());
        givebean.setSum(count);
        String senmes = Agreement.getAgreement().giveAgreement(GsonUtil.getGsonUtil().getgson().toJson(givebean));
        SendMessageUntil.toServer(senmes);
        ZhuFrame.getZhuJpanel().addPrompt("物品#G【" + giveGood.getGoodsname() + "】#Y数量#G【" + giveGood.getUsetime() + "】#Y回收成功！");
        SellAllGoodJDialog.flag = false;
    }
    
    static {
        SellAllGoodJDialog.flag = false;
    }
}
