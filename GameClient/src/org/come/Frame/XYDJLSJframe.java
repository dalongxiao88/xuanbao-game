//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.XYDJLSJpanel;
import org.come.Jpanel.XYDJLSJpanel;
import org.come.until.FormsManagement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class XYDJLSJframe extends JInternalFrame implements MouseListener {
    private XYDJLSJpanel XYDJLSJpanel = new XYDJLSJpanel();
    private int first_x;
    private int first_y;

    public static XYDJLSJframe getTeamApplyJframe() {
        return (XYDJLSJframe)FormsManagement.getInternalForm(30001).getFrame();
    }

    public XYDJLSJframe() throws Exception {
        this.getContentPane().add(this.XYDJLSJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        Rectangle bounds = ImpactGradeJframe.getImpactGradeJframe().getBounds();
        this.setBounds((int)bounds.getX()+700, (int)bounds.getY()+80, 333, 277);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                if (XYDJLSJframe.this.isVisible()) {
                    int x = e.getX() - XYDJLSJframe.this.first_x;
                    int y = e.getY() - XYDJLSJframe.this.first_y;
                    XYDJLSJframe.this.setBounds(x + XYDJLSJframe.this.getX(), y + XYDJLSJframe.this.getY(), XYDJLSJframe.this.getWidth(), XYDJLSJframe.this.getHeight());
                }

            }
        });
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        //Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(30001);
        } else {
            FormsManagement.Switchinglevel(30001);
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

    public XYDJLSJpanel getXYDJLSJpanel() {
        return this.XYDJLSJpanel;
    }

    public void setXYDJLSJpanel(XYDJLSJpanel XYDJLSJpanel) {
        this.XYDJLSJpanel = XYDJLSJpanel;
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
