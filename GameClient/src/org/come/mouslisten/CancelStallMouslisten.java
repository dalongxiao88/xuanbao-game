package org.come.mouslisten;

import java.util.ArrayList;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import com.tool.Stall.Commodity;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.StallBuyJpanel;
import java.awt.event.MouseListener;

public class CancelStallMouslisten implements MouseListener
{
    private int type;
    private int index;
    private StallBuyJpanel buyJpanel;
    public static List<String> list;
    
    public CancelStallMouslisten(int index, int type, StallBuyJpanel buyJpanel) {
        this.index = index;
        this.type = type;
        this.buyJpanel = buyJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Commodity commodity = this.buyJpanel.getStall().getCommodity(this.type, this.index);
        if (commodity != null) {
            this.buyJpanel.reset(commodity);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Commodity commodity = this.buyJpanel.getStall().getCommodity(this.type, this.index);
        if (commodity != null) {
            Goodstable good = commodity.getGood();
            RoleSummoning pet = commodity.getPet();
            if (good != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(good);
            }
            else if (pet != null) {
                ZhuFrame.getZhuJpanel().creatpettext(pet);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.type == 0) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        else if (this.type == 1) {
            ZhuFrame.getZhuJpanel().pettext();
        }
    }
    
    static {
        CancelStallMouslisten.list = new ArrayList<>();
    }
}
