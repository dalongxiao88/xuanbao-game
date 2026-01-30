package org.come.Jpanel;

import java.math.BigDecimal;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import org.come.Frame.LingHelpListJframe;
import org.come.model.Lingbao;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class ZyLBMyRenderer extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    private static JList<String> listpet;
    private ImageIcon petHead;
    public static ImageIcon dikuang;
    private JLabel headlab;
    private Lingbao lingbao;
    public static Lingbao lingbao1;
    
    public ZyLBMyRenderer() {
    }
    
    public ZyLBMyRenderer(int index) {
        this.index = index;
        this.lingbao = (Lingbao)LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().getListModel().get(this.index);
        this.petHead = CutButtonImage.getImage("img/lingbao/" + this.lingbao.getSkin() + ".png", -1, -1);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 36);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ZyLBMyRenderer mr = new ZyLBMyRenderer(index);
        mr.removeAll();
        if (isSelected) {
            mr.setBackground(Color.GRAY);
        }
        else {
            mr.setOpaque(false);
        }
        return (Component)mr;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if ("水墨".equals(MyIsif.getStyle())) {
            ZyLBMyRenderer.dikuang = CutButtonImage.getImage("img/background/spBox.png", -1, -1);
        }
        else {
            ZyLBMyRenderer.dikuang = CutButtonImage.getImage("img/background/spBox-h.png", -1, -1);
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        for (int i = 0; i < LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().getListModel().size(); ++i) {
            BigDecimal id = this.lingbao.getBaoid();
            for (int j = LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().getListModel().size() - 1; j >= 0; --j) {
                if (((Lingbao)LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().getListModel().get(j)).getBaoid().compareTo(id) == 0) {
                    ImageIcon peiimg = null;
                    g2d.setFont(UIUtils.TEXT_FONT2);
                    if ("水墨".equals(MyIsif.getStyle())) {
                        peiimg = CutButtonImage.getImage("inkImg/button/ss507.png", -1, -1);
                        g2d.setColor(Color.white);
                    }
                    else {
                        peiimg = CutButtonImage.getImage("inkimg/hongmu/ss544.png", -1, -1);
                        g2d.setColor(UIUtils.COLOR_cbg9);
                    }
                    g.drawImage(peiimg.getImage(), 150, 12, 17, 17, null);
                    if (this.index >= 9) {
                        g2d.drawString(this.index + 1 + "", 152, 25);
                    }
                    else {
                        g2d.drawString(this.index + 1 + "", 155, 25);
                    }
                }
            }
        }
        Font font = new Font("宋体", 0, 14);
        g2d.setFont(font);
        g2d.setColor(Color.white);
        if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
                g2d.setColor(new Color(255, 0, 0));
            }
        }
        else if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
            g2d.setColor(new Color(187, 165, 75));
        }
        g2d.drawString(ZyLBMyRenderer.vs = this.lingbao.getBaoname(), 45, 23);
        g2d.drawString(ZyLBMyRenderer.vs, 45, 23);
        g.drawImage(ZyLBMyRenderer.dikuang.getImage(), 3, 1, 35, 35, null);
        if (this.lingbao == ZyLBMyRenderer.lingbao1) {
            g.drawImage(this.petHead.getImage(), 4, 2, 30, 30, null);
        }
        else {
            g.drawImage(this.petHead.getImage(), 5, 3, 30, 30, null);
        }
    }
}
