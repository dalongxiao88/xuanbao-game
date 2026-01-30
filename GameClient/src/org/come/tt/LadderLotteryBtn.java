package org.come.tt;

import org.come.until.Util;
import java.awt.event.MouseEvent;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.MoBanBtn;

public class LadderLotteryBtn extends MoBanBtn
{
    private int caozuo;
    private LadderLotteryJpanel ladderLotteryJpanel;
    
    public LadderLotteryBtn(String iconpath, int type, String text, int caozuo, LadderLotteryJpanel ladderLotteryJpanel) {
        super(iconpath, type, (text.equals("积分兑换") || text.equals("?")) ? UIUtils.COLOR_BTNTEXT : UIUtils.COLOR_BTNPUTONG);
        this.caozuo = caozuo;
        this.ladderLotteryJpanel = ladderLotteryJpanel;
        this.setText(text);
        if (text.equals("积分兑换") || text.equals("?")) {
            this.setFont(UIUtils.TEXT_FONT);
        }
        else {
            this.setFont(UIUtils.TEXT_HY16);
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
    
    public void lottery(int type) {
        String sendMes = Agreement.getFiveMsgAgreement("C6|0|4");
        SendMessageUntil.toServer(sendMes);
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo >= 1 && this.caozuo <= 6) {
            this.ladderLotteryJpanel.getlotteryTypeGoods(this.caozuo);
        }
        else if (this.caozuo == 10 || this.caozuo == 11) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            this.lottery(this.caozuo);
        }
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
}
