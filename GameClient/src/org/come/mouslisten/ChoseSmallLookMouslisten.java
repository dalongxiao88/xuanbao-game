package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.Jpanel.SmallExpressionJpanel;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoseSmallLookMouslisten implements MouseListener
{
    private int number;
    
    public ChoseSmallLookMouslisten(int number) {
        this.number = number;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(52);
        }
        else if ((SmallExpressionJpanel.getIndex() - 1) * 40 + this.number <= 168) {
            ZhuFrame.getZhuJpanel().getSendMes().setText(ZhuFrame.getZhuJpanel().getSendMes().getText() + "#" + ((SmallExpressionJpanel.getIndex() - 1) * 40 + this.number));
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
    }
}
