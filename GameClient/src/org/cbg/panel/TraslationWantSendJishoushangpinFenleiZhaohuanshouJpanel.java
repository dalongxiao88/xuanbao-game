package org.cbg.panel;

import java.awt.Graphics;
import java.util.ArrayList;
import org.come.entity.RoleSummoning;
import org.cbg.until.TraslationTableZhaohuanshouUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private List<TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel> lists;
    private ImageIcon icon;
    
    public TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(310, 259));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setBounds(0, 20, 312, 237);
            this.jPanel = TraslationTableZhaohuanshouUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.add(this.jScrollPane);
        }
        else {
            this.setPreferredSize(new Dimension(310, 259));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setBounds(0, 20, 304, 232);
            this.jPanel = TraslationTableZhaohuanshouUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.add(this.jScrollPane);
        }
    }
    
    public void chang(RoleSummoning pet, boolean is) {
        if (this.lists == null) {
            this.lists = new ArrayList<>();
        }
        int i = 0;
        while (i < this.lists.size()) {
            TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel modelJpanel = (TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel)this.lists.get(i);
            if (modelJpanel.getRgid().compareTo(pet.getSid()) == 0) {
                if (is) {
                    modelJpanel.chang(pet);
                    return;
                }
                this.lists.remove(modelJpanel);
                this.jPanel.remove(modelJpanel);
                this.tz();
                return;
            }
            else {
                ++i;
            }
        }
        if (is) {
            TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel modelJpanel2 = new TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel(pet);
            this.lists.add(modelJpanel2);
            this.jPanel.add(modelJpanel2);
            this.tz();
            return;
        }
    }
    
    public void tz() {
        for (int i = 0; i < this.lists.size(); ++i) {
            TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel modelJpanel = (TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel)this.lists.get(i);
            modelJpanel.setBounds(0, i * 50, 304, 50);
        }
        this.jPanel.setPreferredSize(new Dimension(565, 50 * this.lists.size()));
        this.jScrollPane.getViewport().setViewSize(this.jPanel.getPreferredSize());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/171.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 310, 259, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/召唤兽w310,h259px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 310, 259, this);
        }
    }
}
