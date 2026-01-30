
package org.come.XuanBao;


import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.until.Music;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class XuanBaoTJJframe
        extends JInternalFrame
        implements MouseListener {
    private int first_x;
    private XuanBaoTJJpanel xuanBaoTJJpaneln = new XuanBaoTJJpanel(this);
    private int first_y;

    public static XuanBaoTJJframe getXuanBaoJTJframe() {
        return (XuanBaoTJJframe) FormsManagement.getInternalForm(8038).getFrame();

    }


    public XuanBaoTJJframe() throws Exception {
        getContentPane().add((Component) this.xuanBaoTJJpaneln);
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setBounds(190, 140, 690, 588);
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
            FormsManagement.disposeForm(8038);
        } else {
            FormsManagement.Switchinglevel(8038);

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

    public XuanBaoTJJpanel getXuanBaoTJJpaneln() {
        return xuanBaoTJJpaneln;
    }

    public void setXuanBaoTJJpaneln(XuanBaoTJJpanel xuanBaoTJJpaneln) {
        this.xuanBaoTJJpaneln = xuanBaoTJJpaneln;
    }
}
