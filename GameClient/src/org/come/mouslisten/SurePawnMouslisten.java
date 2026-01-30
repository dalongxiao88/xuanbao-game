package org.come.mouslisten;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.PawnGoodsMessBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.UserStallUntil;
import org.come.Frame.PawnJfram;
import org.come.entity.Goodstable;

public class SurePawnMouslisten
{
    private static Goodstable goodstable;
    
    public static int getqian() {
        try {
            int x = Integer.parseInt(PawnJfram.getPawnJfram().getPawnjpanel().getGoodsCount().getText());
            if (x <= 0) {
                return 0;
            }
            if ((int)SurePawnMouslisten.goodstable.getUsetime() < x) {
                x = (int)SurePawnMouslisten.goodstable.getUsetime();
            }
            return x;
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static Goodstable getGoodstable() {
        return SurePawnMouslisten.goodstable;
    }
    
    public static void setGoodstable(Goodstable goodstable) {
        SurePawnMouslisten.goodstable = goodstable;
    }
    
    public static void surePawn() {
        if (UserStallUntil.isStall()) {
            return;
        }
        if (SurePawnMouslisten.goodstable == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的物品");
            PawnJfram.getPawnJfram().getPawnjpanel().qingchu();
            return;
        }
        if (SurePawnMouslisten.goodstable.getGoodlock() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁，不可典当。。");
            return;
        }
        if (GoodsListFromServerUntil.isExist(SurePawnMouslisten.goodstable)) {
            return;
        }
        int count = getqian();
        if (count <= 0 || count > (int)SurePawnMouslisten.goodstable.getUsetime()) {
            PawnJfram.getPawnJfram().getPawnjpanel().qingchu();
            return;
        }
        PawnGoodsMessBean goodsMessBean = new PawnGoodsMessBean();
        goodsMessBean.setRgid(SurePawnMouslisten.goodstable.getRgid());
        goodsMessBean.setNumber(count);
        String mes = Agreement.getAgreement().pawnAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodsMessBean));
        SendMessageUntil.toServer(mes);
        SurePawnMouslisten.goodstable.setUsetime(Integer.valueOf((int)SurePawnMouslisten.goodstable.getUsetime() - count));
        if ((int)SurePawnMouslisten.goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(SurePawnMouslisten.goodstable.getRgid());
        }
        PawnJfram.getPawnJfram().getPawnjpanel().qingchu();
    }
}
