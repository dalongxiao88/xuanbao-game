package org.gemstone.mouseListener;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.come.entity.Goodstable;
import java.awt.event.MouseAdapter;

public class GemstoneMakeGoodsMouseListener extends MouseAdapter
{
    private Goodstable goods;
    
    public GemstoneMakeGoodsMouseListener(Goodstable goods) {
        this.goods = goods;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.goods != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.goods);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
