package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Graphics;
import java.util.List;
import org.come.bean.AllAchieve;
import org.come.model.Achieve;
import com.tool.ModerateTask.TaskRoleData;
import org.come.until.UserMessUntil;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class PartnerArenaExchangePanel extends JPanel
{
    private JScrollPane scrollPane;
    private JList<PartnerArenaExchangeModelPanel> partnerUnitJpanels;
    private int typeView;
    private ImageIcon icon;
    
    public PartnerArenaExchangePanel() {
        this.typeView = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(522, 450));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 107);
            offBtn.setBounds(485, 10, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.showView();
        }
        else {
            this.setPreferredSize(new Dimension(522, 450));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 107);
            offBtn.setBounds(497, 0, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.showView();
        }
    }
    
    public void showView() {
        AllAchieve allAchieve = UserMessUntil.getAllAchieve();
        if (allAchieve == null) {
            return;
        }
        int sumReceive = TaskRoleData.SumReceive(4, 2);
        if (this.typeView == -1) {
            this.typeView = 1;
            List<Achieve> achieves = allAchieve.getAchieves();
            int size = achieves.size();
            for (int i = 0; i < size; ++i) {
                PartnerArenaExchangeModelPanel modelPanel = new PartnerArenaExchangeModelPanel();
                modelPanel.setBounds(10 + i % 2 * 213, 0 + i / 2 * 75, 203, 68);
                modelPanel.showView((Achieve)achieves.get(i), sumReceive);
                this.partnerUnitJpanels.add(modelPanel);
            }
            int num = (size % 2 == 0) ? (size / 2) : (size / 2 + 1);
            this.partnerUnitJpanels.setPreferredSize(new Dimension(439, num * 75));
        }
        else {
            for (int componentCount = this.partnerUnitJpanels.getComponentCount(), j = 0; j < componentCount; ++j) {
                PartnerArenaExchangeModelPanel component2 = (PartnerArenaExchangeModelPanel)this.partnerUnitJpanels.getComponent(j);
                component2.refreshView(sumReceive);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B311.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 522, 450, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S181.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 522, 450, null);
        }
    }
    
    public JScrollPane getScrollPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPane == null) {
                (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.scrollPane.getViewport().setView(this.getPartnerUnitJpanels());
                this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
                this.scrollPane.getViewport().setOpaque(false);
                this.scrollPane.setOpaque(false);
                this.scrollPane.setBounds(47, 52, 454, 375);
                this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
                this.scrollPane.setHorizontalScrollBarPolicy(31);
                this.add(this.scrollPane);
            }
        }
        else if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getViewport().setView(this.getPartnerUnitJpanels());
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(30, 53, 450, 370);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public JList<PartnerArenaExchangeModelPanel> getPartnerUnitJpanels() {
        if (this.partnerUnitJpanels == null) {
            (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.partnerUnitJpanels.setSelectionForeground(Color.white);
            this.partnerUnitJpanels.setForeground(Color.white);
            this.partnerUnitJpanels.setFont(UIUtils.TEXT_HY16);
            this.partnerUnitJpanels.removeAll();
            this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
            this.partnerUnitJpanels.setOpaque(false);
        }
        return this.partnerUnitJpanels;
    }
    
    public void setPartnerUnitJpanels(JList<PartnerArenaExchangeModelPanel> partnerUnitJpanels) {
        this.partnerUnitJpanels = partnerUnitJpanels;
    }
}
