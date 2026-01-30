package org.come.MountShouHu;

import javax.swing.ImageIcon;
import org.come.until.Util;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import org.come.entity.Mount;
import org.come.until.UserMessUntil;
import java.util.HashMap;
import com.tool.image.ImageMixDeal;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class MyMountRenderer extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    public static int select;
    
    public MyMountRenderer(int index) {
        this.index = 67;
        this.index = index;
    }
    
    public MyMountRenderer() {
        this.index = 67;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 28);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MyMountRenderer mr = new MyMountRenderer(index);
        mr.removeAll();
        if (xuanzeJpanel.idx == index || isSelected) {
            mr.setBackground(Color.GRAY);
            MyMountRenderer.select = index;
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
                try {
                    if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
                        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {
                            {
                                Iterator<Mount> iterator = UserMessUntil.getMountlsit().listIterator();
                                while (iterator.hasNext()) {
                                    Mount roleSummoning = (Mount)iterator.next();
                                    this.put(roleSummoning.getMountname(), Integer.valueOf(roleSummoning.getMid().intValue()));
                                }
                                if (isSelected) {
                                    mr.setBackground(Color.GRAY);
                                }
                                else {
                                    mr.setBackground(Color.black);
                                }
                            }
                        };
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
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
        g2d.setColor(Color.green);
        g2d.setFont(UIUtils.TEXT_NAME_FONT15);
        String name = ((Mount)UserMessUntil.getMountlsit().get(this.index)).getMountname();
        String sexSting = Util.getSexSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
        int n = -1;
        switch (sexSting.hashCode()) {
            case 30007: {
                if (sexSting.equals("男")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 22899: {
                if (sexSting.equals("女")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0:
            case 1: {
                String raceSting = Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
                int n2 = -1;
                switch (raceSting.hashCode()) {
                    case 20154: {
                        if (raceSting.equals("人")) {
                            n2 = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 39764: {
                        if (raceSting.equals("魔")) {
                            n2 = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 40857: {
                        if (raceSting.equals("龙")) {
                            n2 = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 20185: {
                        if (raceSting.equals("仙")) {
                            n2 = 3;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 39740: {
                        if (raceSting.equals("鬼")) {
                            n2 = 4;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n2) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        if (name.contains("四")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "4.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("一")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "1.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("二")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "2.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("三")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "3.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("五")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "5.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("六")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "6.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else if (name.contains("七")) {
                            g2d.drawImage(new ImageIcon("inkImg/Client/zqhead/26x26_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "7.png").getImage(), 0, 0, 26, 26, null);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                break;
            }
        }
        g2d.drawString(MyMountRenderer.vs = ((Mount)UserMessUntil.getMountlsit().get(this.index)).getMountname(), 30, 20);
    }
    
    static {
        MyMountRenderer.select = -1;
    }
}
