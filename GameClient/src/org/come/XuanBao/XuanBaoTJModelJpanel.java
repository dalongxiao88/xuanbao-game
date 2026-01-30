package org.come.XuanBao;

import com.tool.ModerateTask.TaskRoleData;
import com.tool.tcpimg.UIUtils;
import org.come.bean.Skill;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.entity.XuanBaoSkill;
import org.come.model.ActiveBase;
import org.come.model.Goods;
import org.come.mouslisten.Mouselistener;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.come.XuanBao.XuanBaoAttributeJpanel.xuanBaoSkillMap;

public class XuanBaoTJModelJpanel extends JPanel {

    private XuanBaoTJJpanel xuanBaoTJJpanel;
    private XuanBao xuanBao;
    private XuanBao extXuanBao;
    private Boolean isSelect = false;

    public XuanBaoTJModelJpanel(XuanBaoTJJpanel xuanBaoTJJpanel, XuanBao xuanBao) {
        setPreferredSize(new Dimension(78, 93));
        setOpaque(false);
        setLayout(null);
        this.xuanBaoTJJpanel = xuanBaoTJJpanel;
        this.xuanBao = xuanBao;
        extXuanBao = RoleXuanBao.getRoleXuanBao().getXuanBaoByName(xuanBao.getName());
        this.addMouseListener(new Mouselistener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                isSelect = true;
                xuanBaoTJJpanel.showXuanBaoInfo(xuanBao, extXuanBao);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xuanBaoTJJpanel.changeSelect();

            }
        });

    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    private static ImageIcon iconBack0 = CutButtonImage.getImage("img/xuan/tj/tjbox0.png", -1, -1);
    private static ImageIcon iconBack1 = CutButtonImage.getImage("img/xuan/tj/tjbox1.png", -1, -1);
    private static ImageIcon dian = CutButtonImage.getImage("img/xuan/tj/th_hd.png", -1, -1);
    private static ImageIcon select = CutButtonImage.getImage("img/xuan/tj/selectBox.png", -1, -1);


    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.setColor(new Color(70, 53, 33));
        if (extXuanBao == null) {
            g.drawImage(iconBack0.getImage(), 0, 0, null);
            ImageIcon image = CutButtonImage.getImage("img/xuan/" + xuanBao.getMp() + "玄宝/" + xuanBao.getName() + "A.png", 62, 62);
            g.drawImage(image.getImage(), 4, 4, null);
            FontMetrics fontMetrics = g.getFontMetrics();
            int i = fontMetrics.stringWidth(xuanBao.getName());
            g.drawString(xuanBao.getName(), (getWidth() - i) / 2, 81);
            g.drawImage(dian.getImage(), getWidth() - 15, 1, null);
        } else {
            g.drawImage(iconBack1.getImage(), 0, 0, null);
            ImageIcon image = CutButtonImage.getImage("img/xuan/" + xuanBao.getMp() + "玄宝/" + xuanBao.getName() + ".png", 62, 62);
            g.drawImage(image.getImage(), 4, 4, null);
            FontMetrics fontMetrics = g.getFontMetrics();
            int i = fontMetrics.stringWidth(xuanBao.getName());
            g.drawString(xuanBao.getName(), (getWidth() - i) / 2, 81);
        }
        if (isSelect) {
            g.drawImage(select.getImage(), 0, 0, null);
        }
    }


}
