package org.come.Jpanel;

import java.awt.Color;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.StorageJadeMouslisten;
import javax.swing.JLabel;
import org.come.entity.PartJade;
import javax.swing.JPanel;

public class StorageJadeJpanel extends JPanel
{
    public static PartJade partJade;
    public static JLabel[] labJade;
    public static StorageJadeMouslisten[] jadeMouslisten;
    private ImageIcon icon;
    
    public StorageJadeJpanel() {
        this.setPreferredSize(new Dimension(291, 171));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        for (int i = 0; i < 5; ++i) {
            StorageJadeJpanel.labJade[i] = new JLabel();
            StorageJadeJpanel.jadeMouslisten[i] = new StorageJadeMouslisten(i, 1);
            StorageJadeJpanel.labJade[i].addMouseListener(StorageJadeJpanel.jadeMouslisten[i]);
            if (i < 3) {
                StorageJadeJpanel.labJade[i].setBounds(30 + i * 90, 17, 54, 51);
            }
            else {
                StorageJadeJpanel.labJade[i].setBounds(75 + (i - 3) * 90, 97, 54, 51);
            }
            this.add(StorageJadeJpanel.labJade[i]);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/B252.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 291, 171, this);
        g.setColor(Color.white);
        g.setFont(UIUtils.nameFont);
        if (StorageJadeJpanel.partJade != null) {
            g.drawString(StorageJadeJpanel.partJade.getJade1() + "", 30, 27);
            g.drawString(StorageJadeJpanel.partJade.getJade2() + "", 120, 27);
            g.drawString(StorageJadeJpanel.partJade.getJade3() + "", 210, 27);
            g.drawString(StorageJadeJpanel.partJade.getJade4() + "", 75, 105);
            g.drawString(StorageJadeJpanel.partJade.getJade5() + "", 165, 105);
        }
    }
    
    public static JLabel[] getLabJade() {
        return StorageJadeJpanel.labJade;
    }
    
    public static void setLabJade(JLabel[] labJade) {
        StorageJadeJpanel.labJade = labJade;
    }
    
    static {
        StorageJadeJpanel.labJade = new JLabel[5];
        StorageJadeJpanel.jadeMouslisten = new StorageJadeMouslisten[5];
    }
}
