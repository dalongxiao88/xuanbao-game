package org.come.XuanBao;

import com.tool.tcpimg.UIUtils;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.XuanBao.XuanBaoPackJ_xuanyunpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;


public class XuanBaoPack_xuanyunJframe extends JInternalFrame implements MouseListener {
    private int first_x;

    public static org.come.XuanBao.XuanBaoPack_xuanyunJframe getXuanBaoPack_xuanyunJframe() {
        return (org.come.XuanBao.XuanBaoPack_xuanyunJframe) FormsManagement.getInternalForm(8037).getFrame();

    }

    private int first_y;
    private XuanBaoPackJ_xuanyunpanel shouhuPackJpanel;


    public XuanBaoPack_xuanyunJframe() throws Exception {
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setContentPane((Container) (this.shouhuPackJpanel = new XuanBaoPackJ_xuanyunpanel()));
        setBackground(UIUtils.Color_BACK);
        setBounds(100, 140, 390, 238);
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
        FormsManagement.showForm(8037);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8037);
        } else {
            FormsManagement.Switchinglevel(8037);
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


    public XuanBaoPackJ_xuanyunpanel getXuanBaoPackJpanel() {
        /* 81 */
        return this.shouhuPackJpanel;

    }


    public void setShouhuPackJpanel(XuanBaoPackJ_xuanyunpanel shouhuPackJpanel) {
        /* 85 */
        this.shouhuPackJpanel = shouhuPackJpanel;

    }

}


/* Location:              C:\Users\Administrator\Desktop\3.zip!\3\d3b7c35f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */