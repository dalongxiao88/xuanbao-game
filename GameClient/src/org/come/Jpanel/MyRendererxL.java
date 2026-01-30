package org.come.Jpanel;

import org.come.entity.Mount;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import org.come.Frame.TestSetupJframe;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Map;
import come.tool.Fighting.Fightingimage;
import java.util.Iterator;
import come.tool.Fighting.TypeState;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import java.util.List;
import java.util.HashMap;
import come.tool.Fighting.FightingMixDeal;
import com.tool.image.ImageMixDeal;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

class MyRendererxL extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    private static JList<String> listpet;
    
    public MyRendererxL() {
    }
    
    public MyRendererxL(int index) {
        this.index = index;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 24);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MyRendererxL mr = new MyRendererxL(index);
        mr.removeAll();
        if (mountJPanel.idx == index || isSelected) {
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
        if (MyIsif.getStyle().equals("水墨")) {
            ImageIcon peiimg = new ImageIcon("inkImg/button1/K159.png");
            g.drawImage(peiimg.getImage(), 1, 3, 17, 17, null);
            Font fon1t = new Font("宋体", 0, 13);
            g2d.setFont(fon1t);
            g2d.setColor(new Color(255, 255, 255));
        }
        else {
            ImageIcon peiimg = new ImageIcon("inkImg/hongmu/ss544.png");
            g.drawImage(peiimg.getImage(), 1, 5, 17, 17, null);
            Font fon1t = new Font("宋体", 0, 13);
            g2d.setFont(fon1t);
            g2d.setColor(new Color(187, 165, 75));
        }
        if (this.index >= 9) {
            g2d.drawString(this.index + 1 + "", 2, 15);
        }
        else {
            g2d.drawString(this.index + 1 + "", 5, 16);
        }
        if (this.index <= 1) {
            g2d.drawString(this.index + 1 + "", 5, 16);
        }
        Font font = new Font("宋体", 0, 14);
        g2d.setFont(font);
        g2d.setColor(Color.white);
        RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(this.index);
        Mount mount2 = ZhuJpanel.getPetMount(pet.getSid());
        if (mount2 != null) {
            g2d.setColor(Color.yellow);
            g2d.setFont(font);
            g2d.drawString(mount2.getMountname(), 265, 17);
            this.setBackground(Color.red);
        }
        String summoningname = ((RoleSummoning)UserMessUntil.getPetListTable().get(this.index)).getSummoningname();
        if (summoningname.length() >= 7) {
            summoningname = summoningname.substring(0, 7);
            summoningname += "..";
        }
        MyRendererxL.vs = ((RoleSummoning)UserMessUntil.getPetListTable().get(this.index)).getSummoningname();
        g2d.drawString(summoningname, 20, 17);
    }
}
