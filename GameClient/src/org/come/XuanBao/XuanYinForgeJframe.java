package org.come.XuanBao;

import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.come.Jpanel.XuanYinForgeJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

public class XuanYinForgeJframe extends JInternalFrame implements MouseListener {
    private XuanYinForgeJpanel forgejpanel = new XuanYinForgeJpanel();
    private int first_x;
    private int first_y;

    public static XuanYinForgeJframe getXuanYinForgeJframe() {
        return (XuanYinForgeJframe)FormsManagement.getInternalForm(8039).getFrame(); // 使用新的窗口ID
    }

    public XuanYinForgeJframe() throws Exception {
        this.getContentPane().add(this.forgejpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(600, 200, 330, 450);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (XuanYinForgeJframe.this.isVisible()) {
                    int x = e.getX() - XuanYinForgeJframe.this.first_x;
                    int y = e.getY() - XuanYinForgeJframe.this.first_y;
                    XuanYinForgeJframe.this.setBounds(x + XuanYinForgeJframe.this.getX(), y + XuanYinForgeJframe.this.getY(), XuanYinForgeJframe.this.getWidth(), XuanYinForgeJframe.this.getHeight());
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
            FormsManagement.HideForm(8039); // 使用新的窗口ID
        } else {
            FormsManagement.Switchinglevel(8039); // 使用新的窗口ID
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

    public XuanYinForgeJpanel getJpanel() {
        return this.forgejpanel;
    }

    public void setforgeJframe(XuanYinForgeJpanel forgejpanel) {
        this.forgejpanel = forgejpanel;
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

    public XuanYinForgeJpanel getForgejpanel() {
        return this.forgejpanel;
    }

    public void setForgejpanel(XuanYinForgeJpanel forgejpanel) {
        this.forgejpanel = forgejpanel;
    }
}
