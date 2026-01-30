package com.tool.fuben;

import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.until.Music;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class FuBenJframe extends JInternalFrame implements MouseListener {

    private FuBenJpanel fuBenJpanel = new FuBenJpanel();
    private int first_x;
    private int first_y;

    public static FuBenJframe getFuBenJframe() {
        return (FuBenJframe)FormsManagement.getInternalForm(3111).getFrame();
    }

    public FuBenJframe() {
        this.add(this.fuBenJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(210, 170, 629, 398);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                if (FuBenJframe.this.isVisible()) {
                    int x = e.getX() - FuBenJframe.this.first_x;
                    int y = e.getY() - FuBenJframe.this.first_y;
                    FuBenJframe.this.setBounds(x + FuBenJframe.this.getX(), y + FuBenJframe.this.getY(), FuBenJframe.this.getWidth(), FuBenJframe.this.getHeight());
                }

            }
        });
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(3111);
            Music.addyinxiao("关闭窗口.mp3");
        } else {
            FormsManagement.Switchinglevel(3111);
        }

        this.first_x = e.getX();
        this.first_y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public FuBenJpanel getFuBenJpane() {
        return this.fuBenJpanel;
    }

    public void setFuBenJpane(FuBenJpanel fuBenJpanel) {
        this.fuBenJpanel = fuBenJpanel;
    }
}