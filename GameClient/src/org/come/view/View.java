package org.come.view;

import org.come.until.Music;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.JComponent;

public class View extends JComponent implements MouseInputListener
{
    private int formId;
    private int level;
    private int first_x;
    private int first_y;
    
    public View() {
    }
    
    public View(int i) {
        this.formId = i;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.formId != -1) {
            if (e.isMetaDown()) {
                FormsManagement.HideForm(this.formId);
                Music.addyinxiao("关闭窗口.mp3");
            }
            else {
                FormsManagement.Switchinglevel(this.formId);
            }
        }
        else if (e.isMetaDown()) {
            this.setVisible(false);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
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
    
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX() - this.first_x;
        int y = e.getY() - this.first_y;
        this.setBounds(x + this.getX(), y + this.getY(), this.getWidth(), this.getHeight());
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public int getFormId() {
        return this.formId;
    }
    
    public void setFormId(int formId) {
        this.formId = formId;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
}
