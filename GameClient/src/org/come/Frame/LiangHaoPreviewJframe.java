package org.come.Frame;

import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.LiangHaoPreviewJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LiangHaoPreviewJframe extends JInternalFrame implements MouseListener
{
    private LiangHaoPreviewJpanel liangHaoPreviewJpanel;
    private int first_x;
    private int first_y;
    
    public static LiangHaoPreviewJframe getLiangHaoPreviewJframe() {
        return (LiangHaoPreviewJframe)FormsManagement.getInternalForm(707).getFrame();
    }
    
    public LiangHaoPreviewJframe() {
        this.add(this.liangHaoPreviewJpanel = new LiangHaoPreviewJpanel());
        this.addMouseListener(this);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LiangHaoPreviewJframe.this.isVisible()) {
                    int x = e.getX() - LiangHaoPreviewJframe.this.first_x;
                    int y = e.getY() - LiangHaoPreviewJframe.this.first_y;
                    LiangHaoPreviewJframe.this.setBounds(x + LiangHaoPreviewJframe.this.getX(), y + LiangHaoPreviewJframe.this.getY(), LiangHaoPreviewJframe.this.getWidth(), LiangHaoPreviewJframe.this.getHeight());
                }
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(248, 300, 400, 150);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public LiangHaoPreviewJpanel getLiangHaoPreviewJpanel() {
        return this.liangHaoPreviewJpanel;
    }
    
    public void setLiangHaoPreviewJpanel(LiangHaoPreviewJpanel liangHaoPreviewJpanel) {
        this.liangHaoPreviewJpanel = liangHaoPreviewJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(707);
        }
        else {
            FormsManagement.Switchinglevel(707);
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
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
}
