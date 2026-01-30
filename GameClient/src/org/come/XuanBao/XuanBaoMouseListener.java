
package org.come.XuanBao;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoJframe;
import org.come.bean.XuanBao;
import org.come.until.FormsManagement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class XuanBaoMouseListener
        implements MouseListener {
    public int id;
    public static boolean is = true;

    public XuanBaoMouseListener(int id) {
        this.id = id;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        XuanBao lingbao = this.getLingbao();
        if (lingbao == null) {
            return;
        }
        if (e.getButton() == 3) {
            if (is) {
                RoleXuanBao.getRoleXuanBao().choseuse(lingbao, true);
            }
        } else if (e.getButton() == 1) {
            XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel().xuanBaoCardJpanel.getEquipmentJpanel().update(lingbao);
            RoleXuanBao.getRoleXuanBao().choseuse_fa(lingbao);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        XuanBao xuanBao = this.getLingbao();
        if (xuanBao == null) {
            return;
        }
        ZhuFrame.getZhuJpanel().creatgoodtext(xuanBao);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm((int)24);
    }

    public XuanBao getLingbao() {
        XuanBao lingbao = null;
        lingbao = null;
        lingbao = is ? RoleXuanBao.getRoleXuanBao().getlingbao(this.id) : RoleXuanBao.getRoleXuanBao().getfa(this.id);
        return lingbao;
    }
}
