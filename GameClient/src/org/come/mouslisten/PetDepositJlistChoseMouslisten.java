package org.come.mouslisten;

import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import org.come.gltools.RendererTools;
import java.awt.Color;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import org.come.Jpanel.DepositListJpanel;
import javax.swing.JList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class PetDepositJlistChoseMouslisten implements MouseListener, MouseMotionListener
{
    private JList<String> listpet;
    private DepositListJpanel testPetJpanel;
    
    public PetDepositJlistChoseMouslisten(JList<String> listpet, DepositListJpanel testPetJpanel) {
        this.listpet = listpet;
        this.testPetJpanel = testPetJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(6);
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
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().pettext();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getY() / 20 < this.listpet.getModel().getSize()) {
            int idx = this.listpet.locationToIndex(e.getPoint());
            this.listpet.setCellRenderer(new RendererTools(idx, new Color(135, 135, 135, 100)));
            ZhuFrame.getZhuJpanel().creatpettext((RoleSummoning)UserMessUntil.getDepositPetListTable().get(idx));
        }
        else {
            ZhuFrame.getZhuJpanel().pettext();
        }
    }
}
