package org.come.Jpanel;

import java.awt.Font;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.Frame.TestSetupJframe;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Map;
import come.tool.Fighting.Fightingimage;
import java.util.Iterator;
import come.tool.Fighting.TypeState;
import java.util.List;
import java.util.HashMap;
import come.tool.Fighting.FightingMixDeal;
import com.tool.image.ImageMixDeal;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class ZyMyRenderer extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    private static JList<String> listpet;
    private ImageIcon petHead;
    public static ImageIcon dikuang;
    private JLabel headlab;
    private RoleSummoning roleSummoning;
    public static RoleSummoning roleSummoning1;
    
    public ZyMyRenderer() {
    }
    
    public ZyMyRenderer(int index) {
        this.index = index;
        this.roleSummoning = UserMessUntil.getPetRgid((BigDecimal)RoleData.getRoleData().getHelpBb().get(this.index));
        this.petHead = new ImageIcon("img/head/p" + this.roleSummoning.getSummoningskin() + ".png");
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 36);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ZyMyRenderer mr = new ZyMyRenderer(index);
        mr.removeAll();
        if (isSelected) {
            mr.setBackground(Color.GRAY);
        }
        else {
            mr.setOpaque(false);
        }
        mr.setLayout(new FlowLayout(0));
        Box box = Box.createHorizontalBox();
        box.add(new JLabel() {
            {
                this.setForeground(Color.BLACK);
                this.setText("");
                if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
                    Fightingimage fightingimage = FightingMixDeal.getdata(0);
                    List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                    Map<String, Integer> map = new HashMap<String, Integer>() {
                        {
                            for (RoleSummoning roleSummoning : UserMessUntil.getPetListTable()) {
                                for (TypeState datum : data) {
                                    if (datum.getType().equals(roleSummoning.getSid().toString())) {
                                        this.put(roleSummoning.getSummoningname(), Integer.valueOf(datum.getState()));
                                    }
                                }
                            }
                        }
                    };
                    int sid = UserMessUntil.getChosePetMes().getSid().intValue();
                    for (int i = 0; i < data.size(); ++i) {
                        if (UserMessUntil.getChosePetMes() == null) {
                            if ((int)map.get(value.toString()) != 0) {
                                this.setForeground(Color.red);
                            }
                            else if (sid == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                                this.setForeground(Color.white);
                            }
                        }
                    }
                }
            }
        });
        mr.add(box);
        return (Component)mr;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            BigDecimal id = this.roleSummoning.getSid();
            for (int j = RoleData.getRoleData().getHelpBb().size() - 1; j >= 0; --j) {
                if (((BigDecimal)RoleData.getRoleData().getHelpBb().get(j)).compareTo(id) == 0) {
                    if (MyIsif.getStyle().equals("水墨")) {
                        ImageIcon peiimg = CutButtonImage.getImage("inkImg/button/ss507.png", -1, -1);
                        g.drawImage(peiimg.getImage(), 150, 12, 17, 17, null);
                        g.setColor(Color.white);
                        if (this.index >= 9) {
                            g2d.drawString(this.index + 1 + "", 152, 25);
                        }
                        else {
                            g2d.drawString(this.index + 1 + "", 155, 25);
                        }
                        Font fon1t = new Font("宋体", 0, 13);
                        g2d.setFont(fon1t);
                    }
                    else {
                        ImageIcon peiimg = CutButtonImage.getImage("inkimg/hongmu/ss544.png", -1, -1);
                        g.drawImage(peiimg.getImage(), 150, 12, 17, 17, null);
                        g.setColor(new Color(187, 165, 75));
                        if (this.index >= 9) {
                            g2d.drawString(this.index + 1 + "", 152, 25);
                        }
                        else {
                            g2d.drawString(this.index + 1 + "", 155, 25);
                        }
                        Font o = new Font("宋体", 0, 13);
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
        g2d.drawString(ZyMyRenderer.vs = this.roleSummoning.getSummoningname(), 45, 23);
        g2d.drawString(ZyMyRenderer.vs, 45, 23);
        if (MyIsif.getStyle().equals("水墨")) {
            if (ZyMyRenderer.dikuang == null) {
                ZyMyRenderer.dikuang = new ImageIcon("img/background/spBox.png");
            }
            g.drawImage(ZyMyRenderer.dikuang.getImage(), 3, 1, 35, 35, null);
        }
        else {
            if (ZyMyRenderer.dikuang == null) {
                ZyMyRenderer.dikuang = new ImageIcon("img/background/spBox-h.png");
            }
            g.drawImage(ZyMyRenderer.dikuang.getImage(), 3, 1, 35, 35, null);
        }
        if (this.roleSummoning == ZyMyRenderer.roleSummoning1) {
            g.drawImage(this.petHead.getImage(), 4, 2, 30, 30, null);
        }
        else {
            g.drawImage(this.petHead.getImage(), 5, 3, 30, 30, null);
        }
    }
}
