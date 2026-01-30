
package org.come.XuanBao;


import com.tool.tcpimg.UIUtils;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.XuanBao.XuanBaoJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

public class XuanBaoJframe
        extends JInternalFrame
        implements MouseListener {
    private int first_x;
    private XuanBaoJpanel xuanBaoJpanel = new XuanBaoJpanel(this);
    private int first_y;

    public static org.come.XuanBao.XuanBaoJframe getXuanBaoJframe() {
        return (org.come.XuanBao.XuanBaoJframe) FormsManagement.getInternalForm(8031).getFrame();

    }


    public XuanBaoJframe() throws Exception {
        getContentPane().add((Component) this.xuanBaoJpanel);
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setBounds(190, 140, 626, 588);
        pack();
        setBackground(UIUtils.Color_BACK);
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
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8031);
        } else {
            FormsManagement.Switchinglevel(8031);

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


    public XuanBaoJpanel getXuanBaoJpanel() {
        return this.xuanBaoJpanel;
    }

    public void setXuanBaoJpanel(XuanBaoJpanel xuanBaoJpanel) {
        this.xuanBaoJpanel = xuanBaoJpanel;

    }

}
