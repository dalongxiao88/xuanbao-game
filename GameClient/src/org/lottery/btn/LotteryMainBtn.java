package org.lottery.btn;

import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.model.Shop;
import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import org.lottery.frame.LotteryIntegralMainJframe;
import org.come.until.UserMessUntil;
import java.util.List;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.lottery.panel.LotteryMainPanel;
import com.tool.btn.MoBanBtn;

public class LotteryMainBtn extends MoBanBtn
{
    private int caozuo;
    private LotteryMainPanel lotteryMainPanel;
    
    public LotteryMainBtn(String iconpath, int type, String text, int caozuo, LotteryMainPanel lotteryMainPanel) {
        super(iconpath, type, (!text.equals("积分兑换") && !text.equals("?")) ? UIUtils.COLOR_BTNPUTONG : UIUtils.COLOR_BTNTEXT);
        this.caozuo = caozuo;
        this.lotteryMainPanel = lotteryMainPanel;
        this.setText(text);
        if (!text.equals("积分兑换") && !text.equals("?")) {
            this.setFont(UIUtils.TEXT_HY99);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public LotteryMainBtn(String iconpath, int type, String text, int caozuo, LotteryMainPanel lotteryMainPanel, String sm) {
        super(iconpath, type, (!text.equals("积分兑换") && !text.equals("?")) ? UIUtils.COLOR_BLACK : UIUtils.COLOR_BTNTEXT);
        this.caozuo = caozuo;
        this.lotteryMainPanel = lotteryMainPanel;
        this.setText(text);
        if (!text.equals("积分兑换") && !text.equals("?")) {
            this.setFont(UIUtils.TEXT_HY99);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo >= 1 && this.caozuo <= 6) {
            this.lotteryMainPanel.getlotteryTypeGoods(this.caozuo);
        }
        else if (this.caozuo != 10 && this.caozuo != 11) {
            if (this.caozuo == 21) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get(499 + this.lotteryMainPanel.getLotteryType() + "");
                if (npcshop != null) {
                    LotteryIntegralMainJframe.getLotteryIntegralMainJframe().getLotteryIntegralMainPanel().getShopIntegralData(npcshop, this.lotteryMainPanel.getDrawName(), RoleData.getRoleData().getLoginResult().getScoretype(this.lotteryMainPanel.getDrawName()).toString());
                    FormsManagement.showForm(88);
                }
            }
        }
        else {
            if (Util.isCanBuyOrno()) {
                return;
            }
            this.lottery(this.caozuo);
        }
    }
    
    public void lottery(int type) {
        if (this.lotteryMainPanel.getDrawGoods() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("上一次奖都还没有抽完，请等一下吧！！");
        }
        else {
            this.lotteryMainPanel.clearView();
            type %= 10;
            Goodstable good = null;
            if (this.lotteryMainPanel.getGoodid() != null) {
                BigDecimal goodsid = this.lotteryMainPanel.getGoodid();
                int i = 0;
                while (i < GoodsListFromServerUntil.getGoodslist().length) {
                    Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    if (goodstable != null && goodstable.getGoodsid().compareTo(goodsid) == 0) {
                        good = goodstable;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
            if (this.lotteryMainPanel.isHintType()) {
                this.lotteryMainPanel.getXianyuDraw();
                int sum = (type == 0) ? 1 : 10;
                sum -= ((good != null) ? ((int)good.getUsetime()) : 0);
                if (sum > 0) {
                    BigDecimal money = new BigDecimal(this.lotteryMainPanel.getXianyuDraw().longValue() * (long)sum);
                    if (this.lotteryMainPanel.getMoenyType() == 0) {
                        if (RoleData.getRoleData().getLoginResult().getCodecard().compareTo(money) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt("你没有足够的仙玉了！！");
                            return;
                        }
                    }
                    else if (this.lotteryMainPanel.getMoenyType() == 1 && RoleData.getRoleData().getLoginResult().getGold().compareTo(money) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt("你没有足够的大话币了！！");
                        return;
                    }
                }
            }
            else if (good == null || (int)good.getUsetime() < ((type == 0) ? 1 : 10)) {
                ZhuFrame.getZhuJpanel().addPrompt("你没有抽奖券，快去购买吧！！");
                return;
            }
            int sum = GoodsListFromServerUntil.Surplussum("-1", "-1", (type == 0) ? 1 : 12);
            if (sum < ((type == 0) ? 1 : 12)) {
                ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
            }
            else {
                StringBuffer buffer = new StringBuffer();
                buffer.append("C");
                buffer.append(this.lotteryMainPanel.getLotteryType());
                buffer.append("|" + type);
                buffer.append("|" + (this.lotteryMainPanel.isHintType() ? 1 : 0));
                if (good != null) {
                    int size = 0;
                    for (int j = 0; j < (int)good.getUsetime() && j < ((type == 0) ? 1 : 10); ++j) {
                        ++size;
                        buffer.append("|" + good.getRgid());
                    }
                    this.lotteryMainPanel.getConsumeGoodsNumber().setText(String.valueOf((int)good.getUsetime() - size));
                }
                String sendMes = Agreement.getFiveMsgAgreement(buffer.toString());
                SendMessageUntil.toServer(sendMes);
                this.lotteryMainPanel.setDrawLottery(true);
            }
        }
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
}
