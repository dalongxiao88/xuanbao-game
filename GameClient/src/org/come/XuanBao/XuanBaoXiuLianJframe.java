package org.come.XuanBao;

import com.tool.tcpimg.UIUtils;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.XuanBao.XuanBaoXiuLianJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;


public class XuanBaoXiuLianJframe extends JInternalFrame implements MouseListener {
    public XuanBaoXiuLianJpanel xuanBaoXiuLianJpanel = new XuanBaoXiuLianJpanel();
    private int first_x;
    private int first_y;


    public static org.come.XuanBao.XuanBaoXiuLianJframe getXuanBaoXiuLianJframe() {
        return (org.come.XuanBao.XuanBaoXiuLianJframe) FormsManagement.getInternalForm(8033).getFrame();

    }


    public XuanBaoXiuLianJframe() throws Exception {
        getContentPane().add(this.xuanBaoXiuLianJpanel);
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setBounds(380, 150, 347, 392);
        pack();
        setDefaultCloseOperation(3);
        setVisible(false);
        setBackground(UIUtils.Color_BACK);
        addMouseListener(this);
//        addMouseMotionListener((MouseMotionListener) new Object(this));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isVisible()) {
                    int x = e.getX() - first_x;
                    int y = e.getY() - first_y;
                    setBounds(x + getX(), y + getY(), getWidth(), getHeight());
                }
            }
        });
    }


    public void mouseClicked(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8033);

        } else {
            FormsManagement.Switchinglevel(8033);

        }
        this.first_x = e.getX();
        this.first_y = e.getY();

    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public XuanBaoXiuLianJpanel getXuanBaoXiuLianJpanel() {
        return this.xuanBaoXiuLianJpanel;

    }


    public void setXuanBaoXiuLianJpanel(XuanBaoXiuLianJpanel xuanBaoXiuLianJpanel) {
        this.xuanBaoXiuLianJpanel = xuanBaoXiuLianJpanel;

    }


    public void mouseExited(MouseEvent e) {
    }

}


