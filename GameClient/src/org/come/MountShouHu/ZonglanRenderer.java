package org.come.MountShouHu;

import com.tool.tcpimg.UIUtils;
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

public class ZonglanRenderer extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    
    public ZonglanRenderer(int index) {
        this.index = 67;
        this.index = index;
    }
    
    public ZonglanRenderer() {
        this.index = 67;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 25);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ZonglanRenderer mr = new ZonglanRenderer(index);
        mr.removeAll();
        if (MountShouhuJpanel.zonglanidx == index || isSelected) {
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
        g.setColor(Color.green);
        g.setFont(UIUtils.TEXT_NAME_FONT15);
        g.drawString((String)MountShouhuJpanel.zonglan.get(this.index), 5, 15);
    }
}
