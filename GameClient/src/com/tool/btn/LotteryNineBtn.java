package com.tool.btn;

import org.come.until.FormsManagement;
import org.come.until.LotteryFromServerUntil;
import org.apache.commons.lang.StringUtils;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.FanfanleJpanel;

public class LotteryNineBtn extends MoBanBtn
{
    private FanfanleJpanel fanfanleJpanel;
    public static boolean isXIPAI;
    
    public LotteryNineBtn(String iconpath, int type, String text, FanfanleJpanel fanfanleJpanel) {
        super(iconpath, type, UIUtils.COLOR_WHITE1);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.fanfanleJpanel = fanfanleJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        String txt = this.getText();
        if (StringUtils.isNotEmpty(txt)) {
            if (txt.equals("洗牌")) {
                LotteryNineBtn.isXIPAI = true;
                LotteryFromServerUntil.startLottery();
            }
            else if (txt.equals("放弃")) {
                FormsManagement.HideForm(999);
                LotteryFromServerUntil.drop();
            }
        }
    }
    
    static {
        LotteryNineBtn.isXIPAI = false;
    }
}
