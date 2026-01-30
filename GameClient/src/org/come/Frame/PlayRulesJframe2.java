package org.come.Frame;


import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.Jpanel.PlayRulesJpanel2;
import org.come.until.FormsManagement;
import org.come.until.Music;


public class PlayRulesJframe2
        extends JInternalFrame
        implements MouseListener {
    private PlayRulesJpanel2 rulesJpanel;
    private int first_x;
    private int first_y;


    public static org.come.Frame.PlayRulesJframe2 getPlayRulesJframe() {
        return (org.come.Frame.PlayRulesJframe2) FormsManagement.getInternalForm(8036).getFrame();

    }


    public PlayRulesJframe2() {
        this.rulesJpanel = new PlayRulesJpanel2();
        setContentPane((Container) this.rulesJpanel);
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setBounds(290, 130, 510, 458);
        setOpaque(false);
        pack();
        setDefaultCloseOperation(3);
        setVisible(false);
        addMouseListener(this);
        // 添加鼠标移动监听器
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
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8036);
        } else {
            FormsManagement.Switchinglevel(8036);
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

}

