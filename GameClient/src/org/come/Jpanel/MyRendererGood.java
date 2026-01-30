package org.come.Jpanel;

import org.come.bean.Skill;
import javax.swing.DefaultListModel;
import org.come.until.UserMessUntil;
import java.awt.Font;
import org.come.entity.Goodstable;
import org.come.Frame.OpenSkillGridJframe;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

class MyRendererGood extends JPanel implements ListCellRenderer
{
    private int index;
    
    public MyRendererGood() {
    }
    
    public MyRendererGood(int index) {
        this.index = index;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 25);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MyRendererGood mr = new MyRendererGood(index);
        if (isSelected) {
            mr.setBackground(Color.GRAY);
        }
        else if (OpenSkillGridJpanel.idx == index) {
            mr.setBackground(new Color(48, 102, 90));
        }
        else {
            mr.setOpaque(false);
        }
        mr.setLayout(null);
        return (Component)mr;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
        DefaultListModel<Goodstable> listGooosModel = OpenSkillGridJpanel.getListGooosModel();
        Goodstable goodstable = (Goodstable)listGooosModel.get(this.index);
        g.setColor(Color.white);
        Font font = new Font("宋体", 0, 17);
        g2d.setFont(font);
        if (goodstable != null) {
            String value = goodstable.getValue();
            String skillId = value.split("\\|")[0].split("=")[1];
            Skill skill = UserMessUntil.getskill1(skillId);
            g2d.drawString(skill.getSkillname(), 5, 19);
        }
    }
}
