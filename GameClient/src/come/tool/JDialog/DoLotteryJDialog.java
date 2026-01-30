package come.tool.JDialog;

import java.util.Iterator;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.util.ArrayList;
import org.come.until.LotteryFromServerUntil;
import org.come.entity.LotteryGood;

public class DoLotteryJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            int goodPlace = (int)object;
            if (goodPlace >= 0) {
                LotteryGood goodstable = (LotteryGood)LotteryFromServerUntil.getGoodslist().get(goodPlace);
                if (goodstable.getLotterystep() == 1) {
                    LotteryFromServerUntil.upRecord();
                    goodstable.setLotterystep(2);
                    if (goodstable.getLotterytype() == 1) {
                        int total = 0;
                        if (LotteryFromServerUntil.getShowlist() != null && LotteryFromServerUntil.getShowlist().size() > 0) {
                            for (LotteryGood lotteryGood : LotteryFromServerUntil.getShowlist()) {
                                if (lotteryGood.getLotterytype() == 2) {
                                    total += lotteryGood.getLotterymultiple();
                                }
                            }
                        }
                        else {
                            total = 1;
                        }
                        LotteryFromServerUntil.setShowlist(new ArrayList<>());
                        String sendmes = Agreement.getAgreement().getLotteryGoods("ADD|" + total + "|" + goodstable.getGoodsid() + "|" + LotteryFromServerUntil.getRecord());
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        String sendmes2 = Agreement.getAgreement().getLotteryGoods("PAYXY|1|" + goodstable.getGoodsid() + "|" + LotteryFromServerUntil.getRecord());
                        SendMessageUntil.toServer(sendmes2);
                        LotteryFromServerUntil.getShowlist().add(goodstable);
                    }
                }
            }
            FormsManagement.HideForm(20);
        }
    }
}
