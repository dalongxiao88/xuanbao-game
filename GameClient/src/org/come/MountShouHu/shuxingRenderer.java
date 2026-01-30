package org.come.MountShouHu;

import org.come.Frame.MountShouhuJframe;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.tool.image.ImageMixDeal;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Color;
import org.come.Jpanel.MountShouhuJpanel;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class shuxingRenderer extends JPanel implements ListCellRenderer
{
    public static String vs;
    private int index;
    
    public shuxingRenderer(int index) {
        this.index = index;
    }
    
    public shuxingRenderer() {
        this.index = 67;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 25);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        shuxingRenderer mr = new shuxingRenderer(index);
        this.index = index;
        mr.removeAll();
        if (MountShouhuJpanel.shuxingidx == index || isSelected) {
            mr.setBackground(new Color(0, 0, 0, 0));
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
                        if (isSelected) {
                            mr.setBackground(Color.GRAY);
                        }
                        else {
                            mr.setBackground(Color.black);
                        }
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
        if (MountShouhuJpanel.shuxing.size() > 0) {
            String[] m = ((String)MountShouhuJpanel.shuxing.get(this.index)).split("级");
            int xuan = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong();
            int k = 0;
            switch (xuan) {
                case 0: {
                    k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianlvl;
                    break;
                }
                case 1: {
                    k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.qinglonglvl;
                    break;
                }
                case 2: {
                    k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.baihulvl;
                    break;
                }
                case 3: {
                    k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.xuanwulvl;
                    break;
                }
                case 4: {
                    k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhuquelvl;
                    break;
                }
            }
            if (Integer.parseInt(m[0]) <= k) {
                g2d.setColor(new Color(11645009));
            }
            else {
                g2d.setColor(Color.GRAY);
            }
            g2d.drawString((String)MountShouhuJpanel.shuxing.get(this.index), 5, 15);
        }
    }
}
