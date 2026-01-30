package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.bean.RoleTxBean;
import com.tool.role.RoleTX;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoleTxMouslisten implements MouseListener
{
    private int p;
    
    public RoleTxMouslisten(int p) {
        this.p = p;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            RoleTxBean bean = this.getTX();
            if (bean != null) {
                if (this.p < 0) {
                    RoleTX.getRoleTX().EToggle(this.p);
                }
                else {
                    RoleTX.getRoleTX().EToggle(bean.getGid());
                }
            }
            else {
                Goodstable goodstable = RoleTX.getRoleTX().getWing(this.p);
                if (goodstable != null) {
                    RoleTX.getRoleTX().EToggle(this.p);
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        RoleTxBean bean = this.getTX();
        if (bean != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(bean);
        }
        else {
            Goodstable goodstable = RoleTX.getRoleTX().getWing(this.p);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public RoleTxBean getTX() {
        return RoleTX.getRoleTX().getTx(this.p);
    }
}
