package org.come.XuanBao;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.XuanBao.XuanBaoMsgJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;


public class XuanBaoMsgJframe
        extends JInternalFrame
        implements MouseListener {
    private XuanBaoMsgJpanel jpanel = new XuanBaoMsgJpanel();


    public static org.come.XuanBao.XuanBaoMsgJframe getXuanBaoMsgJframe() {
        return (org.come.XuanBao.XuanBaoMsgJframe) FormsManagement.getInternalForm(8032).getFrame();

    }


    public XuanBaoMsgJframe() {
        add(this.jpanel);
        setBackground(new Color(0, 0, 0, 0));
        setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) getUI()).setNorthPane(null);
        setBounds(0, 0, 470, 460);
        pack();
        setVisible(false);
        setDefaultCloseOperation(3);
        addMouseListener(this);

    }


    public void mouseClicked(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(8032);
        } else {
            FormsManagement.Switchinglevel(8032);
        }
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }


    public XuanBaoMsgJpanel getJpanel() {
        return this.jpanel;

    }


    public void setJpanel(XuanBaoMsgJpanel jpanel) {
        this.jpanel = jpanel;

    }

}

