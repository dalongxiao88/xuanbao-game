package org.come.XuanBao;

import com.tool.tcpimg.UIUtils;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.XuanBao.XuanBaoPackJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

public class XuanBaoPackJframe extends JInternalFrame implements MouseListener {
    private int first_x;
    private int first_y;
    private XuanBaoPackJpanel shouhuPackJpanel;

    public static org.come.XuanBao.XuanBaoPackJframe getXuanBaoPackJframe() {
        return (org.come.XuanBao.XuanBaoPackJframe) FormsManagement.getInternalForm(8034).getFrame();
    }

    public XuanBaoPackJframe() throws Exception {
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setContentPane((Container) (this.shouhuPackJpanel = new XuanBaoPackJpanel()));
        setBackground(UIUtils.Color_BACK);
        setBounds(60, 60, 454, 451);
        pack();
        setDefaultCloseOperation(3);
        setVisible(false);
        addMouseListener(this);
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
        FormsManagement.showForm(8034);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8034);
        } else {
            FormsManagement.Switchinglevel(8034);
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


    public XuanBaoPackJpanel getXuanBaoPackJpanel() {
        return this.shouhuPackJpanel;
    }

    public void setShouhuPackJpanel(XuanBaoPackJpanel shouhuPackJpanel) {
        this.shouhuPackJpanel = shouhuPackJpanel;
    }
}
