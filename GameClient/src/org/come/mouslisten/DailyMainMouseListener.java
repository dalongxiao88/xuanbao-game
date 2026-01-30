package org.come.mouslisten;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DailyMainMouseListener extends MouseAdapter
{
    private String goodsId;
    
    public DailyMainMouseListener(String goodsId) {
        this.goodsId = goodsId;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.goodsId != null) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(this.goodsId));
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
