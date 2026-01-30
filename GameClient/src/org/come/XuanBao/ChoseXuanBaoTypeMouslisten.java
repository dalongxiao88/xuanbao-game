package org.come.XuanBao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.come.XuanBao.XuanBaoCardJpanel;
import org.come.XuanBao.XuanBaoMouseListener;


public class ChoseXuanBaoTypeMouslisten
        implements MouseListener {
    private XuanBaoCardJpanel lingbaoCardJpanel;
    private String type;

    public ChoseXuanBaoTypeMouslisten(String type, XuanBaoCardJpanel lingbaoCardJpanel) {
        this.type = type;
        this.lingbaoCardJpanel = lingbaoCardJpanel;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (this.type.equals("玄宝装备")) {
            this.lingbaoCardJpanel.setPreferredSize(new Dimension(619, 582));
            this.lingbaoCardJpanel.getCar().show((Container) this.lingbaoCardJpanel, "l1");
            XuanBaoMouseListener.is = true;
        } else if (this.type.equals("玄宝属性")) {
            this.lingbaoCardJpanel.setPreferredSize(new Dimension(619, 482));
            this.lingbaoCardJpanel.getCar().show((Container) this.lingbaoCardJpanel, "l2");
            XuanBaoMouseListener.is = false;
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

