//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.SpiritualJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SpiritualJframe extends JInternalFrame implements MouseListener {
    private SpiritualJpanel spiritualJpanel = new SpiritualJpanel();
    private int first_x;
    private int first_y;

    public static SpiritualJframe getSpiritualJframe() {
        return (SpiritualJframe)FormsManagement.getInternalForm(178888).getFrame();
    }

    public SpiritualJframe() throws Exception {
        this.setContentPane(this.spiritualJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(300, 80, 485, 457);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                if (SpiritualJframe.this.isVisible()) {
                    int x = e.getX() - SpiritualJframe.this.first_x;
                    int y = e.getY() - SpiritualJframe.this.first_y;
                    SpiritualJframe.this.setBounds(x + SpiritualJframe.this.getX(), y + SpiritualJframe.this.getY(), SpiritualJframe.this.getWidth(), SpiritualJframe.this.getHeight());
                }

            }
        });
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(178888);
        } else {
            FormsManagement.Switchinglevel(178888);
        }

        Music.addyinxiao("关闭窗口.mp3");
        this.first_x = e.getX();
        this.first_y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

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

    public SpiritualJpanel getSpiritualJpanel() {
        return spiritualJpanel;
    }

    public void setSpiritualJpanel(SpiritualJpanel spiritualJpanel) {
        this.spiritualJpanel = spiritualJpanel;
    }
}
