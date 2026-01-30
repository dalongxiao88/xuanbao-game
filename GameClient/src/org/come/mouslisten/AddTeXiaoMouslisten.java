package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import org.come.Jpanel.TaobaoCourtSplendidJpanel;
import com.tool.role.RoleTX;
import org.come.Frame.TaobaoCourtMainJframe;
import java.awt.event.MouseEvent;
import org.come.model.Eshop;
import java.awt.event.MouseListener;

public class AddTeXiaoMouslisten implements MouseListener
{
    private Eshop eshop;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.eshop != null) {
            TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel = TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getTaobaoCourtSplendidJpanel();
            if (taobaoCourtSplendidJpanel.getEshops() != null) {
                if (this.eshop.getEshoptype().equals("11")) {
                    if (taobaoCourtSplendidJpanel.getEshops()[0] != null) {
                        RoleTX.getRoleTX().removeTX(1, -Integer.parseInt(taobaoCourtSplendidJpanel.getEshops()[0].getEshopiid()));
                    }
                    else {
                        taobaoCourtSplendidJpanel.setZbs(taobaoCourtSplendidJpanel.getZbs() + 1);
                    }
                    taobaoCourtSplendidJpanel.getEshops()[0] = this.eshop;
                    RoleTX.getRoleTX().addTX(1, Math.abs(Integer.parseInt(this.eshop.getEshopiid())));
                }
                else if (this.eshop.getEshoptype().equals("12")) {
                    if (taobaoCourtSplendidJpanel.getEshops()[1] != null) {
                        RoleTX.getRoleTX().removeTX(1, -Integer.parseInt(taobaoCourtSplendidJpanel.getEshops()[1].getEshopiid()));
                    }
                    else {
                        taobaoCourtSplendidJpanel.setZbs(taobaoCourtSplendidJpanel.getZbs() + 1);
                    }
                    taobaoCourtSplendidJpanel.getEshops()[1] = this.eshop;
                    RoleTX.getRoleTX().addTX(1, Math.abs(Integer.parseInt(this.eshop.getEshopiid())));
                }
                else if (this.eshop.getEshoptype().equals("13")) {
                    if (taobaoCourtSplendidJpanel.getEshops()[2] != null) {
                        RoleTX.getRoleTX().removeTX(1, -Integer.parseInt(taobaoCourtSplendidJpanel.getEshops()[2].getEshopiid()));
                    }
                    else {
                        taobaoCourtSplendidJpanel.setZbs(taobaoCourtSplendidJpanel.getZbs() + 1);
                    }
                    taobaoCourtSplendidJpanel.getEshops()[2] = this.eshop;
                    RoleTX.getRoleTX().addTX(1, Math.abs(Integer.parseInt(this.eshop.getEshopiid())));
                }
                else if ("14".equals(this.eshop.getEshoptype())) {
                    if (taobaoCourtSplendidJpanel.getEshops()[3] != null) {
                        TaobaoCourtSplendidJpanel.removeTXK(1, -Integer.parseInt(taobaoCourtSplendidJpanel.getEshops()[3].getEshopiid()));
                    }
                    else {
                        taobaoCourtSplendidJpanel.setZbs(taobaoCourtSplendidJpanel.getZbs() + 1);
                    }
                    taobaoCourtSplendidJpanel.getEshops()[3] = this.eshop;
                    TaobaoCourtSplendidJpanel.addTXK(1, Math.abs(Integer.parseInt(this.eshop.getEshopiid())));
                }
                if (FormsManagement.getframe(51).isVisible()) {
                    TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getTaobaoCourtSplendidJpanel().showTxMsg();
                }
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.eshop != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.eshop);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public Eshop getEshop() {
        return this.eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
}
