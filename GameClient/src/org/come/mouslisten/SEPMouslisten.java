package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.Jpanel.SmallExpressionJpanel;
import java.awt.event.MouseListener;

public class SEPMouslisten implements MouseListener
{
    private SmallExpressionJpanel jpanel;
    private int i;
    
    public SEPMouslisten(SmallExpressionJpanel jpanel, int i) {
        this.jpanel = jpanel;
        this.i = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        SmallExpressionJpanel.setIndex(this.i);
        this.jpanel.getbqs();
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
    }
}
