package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.LingMsgJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LingMsgJframe extends JInternalFrame implements MouseListener
{
    private LingMsgJpanel jpanel;
    
    public static LingMsgJframe getLingMsgJpanel() {
        return (LingMsgJframe)FormsManagement.getInternalForm(45).getFrame();
    }
    
    public LingMsgJframe() {
        this.add(this.jpanel = new LingMsgJpanel());
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(45);
        }
        else {
            FormsManagement.Switchinglevel(45);
        }
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
    
    public LingMsgJpanel getJpanel() {
        return this.jpanel;
    }
    
    public void setJpanel(LingMsgJpanel jpanel) {
        this.jpanel = jpanel;
    }
}
