package org.come.Jpanel;

import java.util.Iterator;
import java.util.List;
import come.tool.Fighting.Fightingimage;
import org.come.model.Lingbao;
import javax.swing.ImageIcon;
import java.awt.Font;
import come.tool.Fighting.TypeState;
import come.tool.Fighting.FightingMixDeal;
import com.tool.role.RoleLingFa;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class SummonRenderer extends JPanel implements ListCellRenderer
{
    private int type;
    private int index;
    private Color color;
    
    public SummonRenderer(int type, int index) {
        this.color = Color.white;
        this.type = type;
        this.index = index;
    }
    
    public SummonRenderer(int type) {
        this.color = Color.white;
        this.type = type;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 30);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        SummonRenderer mr = new SummonRenderer(this.type, index);
        mr.removeAll();
        if (isSelected) {
            mr.setBackground(Color.gray);
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
            }
        });
        mr.add(box);
        return (Component)mr;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (MyIsif.getStyle().equals("水墨")) {
            ImageIcon backImg = CutButtonImage.getImage("inkImg/button1/s602.png", 28, 28);
            g.drawImage(backImg.getImage(), 1, 1, 28, 28, null);
        }
        else {
            ImageIcon backImg = CutButtonImage.getImage("img/xy2uiimg/23_png.xy2uiimg.ibg.png", 28, 28);
            g.drawImage(backImg.getImage(), 1, 1, 28, 28, null);
        }
        if (this.type == 0) {
            RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(this.index);
            ImageIcon peiImg = CutButtonImage.getImage("img/head/p" + pet.getSummoningskin() + ".png", 24, 24);
            g.drawImage(peiImg.getImage(), 3, 3, 24, 24, null);
        }
        else {
            Lingbao lingBao = RoleLingFa.getRoleLingFa().getLingBao(this.index);
            ImageIcon peiImg = CutButtonImage.getImage("img/lingbao/" + lingBao.getSkin() + ".png", 24, 24);
            g.drawImage(peiImg.getImage(), 3, 3, 24, 24, null);
        }
        Fightingimage fightingimage = FightingMixDeal.getdata(0);
        if (fightingimage != null) {
            List<TypeState> data;
            if (this.type == 0) {
                data = fightingimage.getFightingManData().cxxx("召唤兽");
            }
            else {
                data = fightingimage.getFightingManData().cxxx("灵宝");
            }
            for (TypeState datum : data) {
                boolean is = false;
                if (this.type == 0) {
                    RoleSummoning roleSummoning = (RoleSummoning)UserMessUntil.getPetListTable().get(this.index);
                    if (roleSummoning.getSid().toString().equals(datum.getType())) {
                        is = true;
                    }
                }
                else {
                    Lingbao lingBao2 = RoleLingFa.getRoleLingFa().getLingBao(this.index);
                    if (lingBao2.getBaoid().toString().equals(datum.getType())) {
                        is = true;
                    }
                }
                if (is) {
                    if (datum.getState() == 1) {
                        this.color = new Color(187, 165, 75);
                        this.setForeground(Color.orange);
                        break;
                    }
                    else if (datum.getState() == 2) {
                        this.color = Color.red;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        Font font = new Font("宋体", 0, 16);
        g2d.setFont(font);
        g2d.setColor(this.color);
        if (this.type == 0) {
            g2d.drawString(((RoleSummoning)UserMessUntil.getPetListTable().get(this.index)).getSummoningname(), 35, 20);
        }
        else {
            g2d.drawString(RoleLingFa.getRoleLingFa().getLingBao(this.index).getBaoname(), 35, 20);
        }
    }
}
