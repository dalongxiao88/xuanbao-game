package com.tool.btn;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.BarProgress;
import org.come.Frame.ShaoXiangJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.ShaoXiangJpanel;

public class ShaoXiangBtn extends MoBanBtn
{
    private int BtnId;
    private ShaoXiangJpanel shaoXiangJpanel;
    
    public ShaoXiangBtn(String iconpath, int type, Color[] colors, int BtnId, String labelName, ShaoXiangJpanel shaoXiangJpanel) {
        super(iconpath, type, colors);
        this.BtnId = BtnId;
        this.shaoXiangJpanel = shaoXiangJpanel;
        this.setText(labelName);
        if (BtnId == 0) {
            this.setFont(UIUtils.TEXT_FONT);
        }
        else {
            this.setFont(UIUtils.TEXT_HY88);
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
        switch (this.BtnId) {
            case 1: {
                Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(90002));
                Goodstable goodstable2 = UserMessUntil.getgoodstable(new BigDecimal(90006));
                int xianghuoNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                int xianghuoNum2 = GoodsListFromServerUntil.getGoodNum(goodstable2.getGoodsid());
                String getname = (String)ShaoXiangJframe.getShaoXiangJframe().getShaoXiangJpanel().getOptionJpanel().getJlist().getSelectedValue();
                if (getname != null) {
                    if (getname.startsWith("装备二特技")) {
                        if (xianghuoNum2 > 0) {
                            if (this.shaoXiangJpanel.getBtnmount().isEnabled()) {
                                new BarProgress(this.shaoXiangJpanel.getjProgressBar(), this.shaoXiangJpanel.getBtnmount(), this.shaoXiangJpanel).start();
                                break;
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("没有香火，怎么供奉？#54");
                            break;
                        }
                    }
                    else if (xianghuoNum > 0) {
                        if (this.shaoXiangJpanel.getBtnmount().isEnabled()) {
                            new BarProgress(this.shaoXiangJpanel.getjProgressBar(), this.shaoXiangJpanel.getBtnmount(), this.shaoXiangJpanel).start();
                            break;
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有香火，怎么供奉？#54");
                        break;
                    }
                }
            }
        }
    }
}
