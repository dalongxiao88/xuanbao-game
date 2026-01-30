package org.come.Frame;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ChooseLiangHaoTypeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ChooseLiangHaoTypeJframe extends JInternalFrame implements MouseListener
{
    private ChooseLiangHaoTypeJpanel chooseLiangHaoTypeJpanel;
    private int first_x;
    private int first_y;
    
    public static ChooseLiangHaoTypeJframe getChooseLiangHaoTypeJframe() {
        return (ChooseLiangHaoTypeJframe)FormsManagement.getInternalForm(638).getFrame();
    }
    
    public ChooseLiangHaoTypeJframe() {
        this.chooseLiangHaoTypeJpanel = new ChooseLiangHaoTypeJpanel();
        this.getContentPane().add(this.chooseLiangHaoTypeJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 230, 320);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ChooseLiangHaoTypeJframe.this.isVisible()) {
                    int x = e.getX() - ChooseLiangHaoTypeJframe.this.first_x;
                    int y = e.getY() - ChooseLiangHaoTypeJframe.this.first_y;
                    ChooseLiangHaoTypeJframe.this.setBounds(x + ChooseLiangHaoTypeJframe.this.getX(), y + ChooseLiangHaoTypeJframe.this.getY(), ChooseLiangHaoTypeJframe.this.getWidth(), ChooseLiangHaoTypeJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(638);
        }
        else {
            FormsManagement.Switchinglevel(638);
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
    
    public ChooseLiangHaoTypeJpanel getChooseLiangHaoTypeJpanel() {
        return this.chooseLiangHaoTypeJpanel;
    }
    
    public void setChooseLiangHaoTypeJpanel(ChooseLiangHaoTypeJpanel chooseLiangHaoTypeJpanel) {
        this.chooseLiangHaoTypeJpanel = chooseLiangHaoTypeJpanel;
    }
}
