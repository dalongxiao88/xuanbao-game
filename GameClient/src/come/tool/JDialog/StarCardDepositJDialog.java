package come.tool.JDialog;

import org.come.starcard.JpanelStarTransferMain;
import org.come.starcard.JpanelStarCardMain;
import org.come.mouslisten.GoodsMouslisten;
import org.come.starcard.BtnStarCard;
import org.come.starcard.JframeStarTransferMain;
import org.come.starcard.JframeStarCardMain;
import org.come.until.FormsManagement;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;

public class StarCardDepositJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            Goodstable goodstable = (Goodstable)object;
            if ((int)goodstable.getStatus() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("已参战星卡无法取出");
                return;
            }
            if ((int)goodstable.getStatus() == 0) {
                goodstable.setStatus(Integer.valueOf(4));
                GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                GoodsListFromServerUntil.addGood(goodstable);
            }
            else if ((int)goodstable.getStatus() == 4) {
                int sum = GoodsListFromServerUntil.Surplussum("" + goodstable.getType(), goodstable.getGoodsid().toString(), 1);
                if (sum <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包格数不足");
                    return;
                }
                goodstable.setStatus(Integer.valueOf(0));
                GoodsListFromServerUntil.getStarCardList().remove(goodstable);
                GoodsListFromServerUntil.addGood(goodstable);
                if (FormsManagement.getframe(95).isVisible()) {
                    JpanelStarCardMain jpanelStarCardMain = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain();
                    if (goodstable.getRgid().compareTo(jpanelStarCardMain.getChooseStarCardId()) == 0) {
                        if (jpanelStarCardMain.getBigType() == 2) {
                            jpanelStarCardMain.recoverStarCardImg();
                        }
                        else if (jpanelStarCardMain.getBigType() == 1) {
                            jpanelStarCardMain.attributeImgShow((Goodstable)null);
                        }
                    }
                }
                if (FormsManagement.getframe(97).isVisible()) {
                    JpanelStarTransferMain jpanelStarTransferMain = JframeStarTransferMain.getJframeStarTransferMain().getJpanelStarTransferMain();
                    if (jpanelStarTransferMain.getChooseOneId().compareTo(goodstable.getRgid()) == 0) {
                        String[] values = goodstable.getValue().split("\\|");
                        String[] split2 = values[3].split("&");
                        for (int j = 0; j < split2.length; ++j) {
                            if (split2[j].startsWith("星阵属性")) {
                                String[] split3 = split2[j].split("=");
                                if (BtnStarCard.isfiveElements(split3[1])) {
                                    jpanelStarTransferMain.showStarCardAttribute(0, split3, (Goodstable)null);
                                    jpanelStarTransferMain.showStarCardAttribute(1, (String[])null, (Goodstable)null);
                                }
                            }
                        }
                    }
                }
            }
            GoodsMouslisten.gooduse(goodstable, 0);
        }
    }
}
