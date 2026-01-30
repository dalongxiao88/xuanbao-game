package org.come.Jpanel;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import java.awt.Color;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import com.tool.ModerateTask.TaskRoleData;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.bean.OneArenaNotes;
import java.util.List;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import com.tool.tcpimg.RichLabel;
import javax.swing.JPanel;

public class PartnerArenaWarPanel extends JPanel
{
    private RichLabel rabRecord;
    private JScrollPane scrollPane;
    private JList<PartnerArenaModelPanel> partnerUnitJpanels;
    private DefaultListModel<PartnerArenaModelPanel> model;
    private ImageIcon icon;
    
    public PartnerArenaWarPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(427, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 80);
            offBtn.setBounds(390, 10, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.getRabRecord();
        }
        else {
            this.setPreferredSize(new Dimension(427, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 80);
            offBtn.setBounds(402, 5, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.getRabRecord();
        }
    }
    
    public void showView(List<OneArenaNotes> arenaNotes) {
        if (arenaNotes == null) {
            return;
        }
        int componentCount = this.model.getSize();
        int size = arenaNotes.size();
        BigDecimal role_id = RoleData.getRoleData().getLoginResult().getRole_id();
        for (int i = 0; i < size; ++i) {
            if (componentCount < size) {
                PartnerArenaModelPanel modelPanel = new PartnerArenaModelPanel(1);
                if (componentCount == 0) {
                    this.model.addElement(modelPanel);
                }
                else {
                    this.model.add(componentCount, modelPanel);
                }
                ++componentCount;
            }
            else if (componentCount > size) {
                this.model.remove(componentCount);
                --componentCount;
            }
            PartnerArenaModelPanel modelPanel = (PartnerArenaModelPanel)this.model.get(i);
            modelPanel.showWarView((OneArenaNotes)arenaNotes.get(i), role_id);
        }
        this.partnerUnitJpanels.setPreferredSize(new Dimension(343, 43 * size));
        this.refreshRecord();
    }
    
    public void refreshOneNoets(OneArenaNotes notes) {
        int componentCount = this.model.getSize();
        PartnerArenaModelPanel modelPanel = new PartnerArenaModelPanel(1);
        modelPanel.showWarView(notes, RoleData.getRoleData().getLoginResult().getRole_id());
        if (componentCount == 0) {
            this.model.addElement(modelPanel);
        }
        else {
            this.model.add(0, modelPanel);
        }
    }
    
    public void refreshRecord() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#W当前胜利#R");
        int sumReceive = TaskRoleData.SumReceive(4, 2);
        buffer.append(sumReceive);
        buffer.append("#W场，负#R");
        buffer.append(TaskRoleData.SumReceive(4, 1) - sumReceive);
        buffer.append("#W场");
        this.rabRecord.setText(buffer.toString());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S180.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 427, 375, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S180.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 427, 375, null);
        }
    }
    
    public RichLabel getRabRecord() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.rabRecord == null) {
                (this.rabRecord = new RichLabel("#K当前胜利#R17#K场，负#R6#K场", UIUtils.TEXT_FONT1)).setBounds(50, 16, 300, 20);
                this.add(this.rabRecord);
            }
        }
        else if (this.rabRecord == null) {
            (this.rabRecord = new RichLabel("#W当前胜利#R17#W场，负#R6#W场", UIUtils.TEXT_FONT1)).setBounds(50, 25, 300, 20);
            this.add(this.rabRecord);
        }
        return this.rabRecord;
    }
    
    public void setRabRecord(RichLabel rabRecord) {
        this.rabRecord = rabRecord;
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
                this.scrollPane.setBounds(47, 44, 359, 306);
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
            this.scrollPane.setBounds(34, 60, 366, 287);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public JList<PartnerArenaModelPanel> getPartnerUnitJpanels() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.partnerUnitJpanels == null) {
                (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
                this.partnerUnitJpanels.setSelectionForeground(Color.white);
                this.partnerUnitJpanels.removeAll();
                this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
                DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if (value instanceof PartnerArenaModelPanel) {
                            PartnerArenaModelPanel unitJpanel = (PartnerArenaModelPanel)value;
                            return (Component)unitJpanel;
                        }
                        return this;
                    }
                };
                this.partnerUnitJpanels.setCellRenderer(cellRenderer);
                this.partnerUnitJpanels.setOpaque(false);
                this.model = new DefaultListModel<>();
                this.partnerUnitJpanels.setModel(this.getModel());
            }
        }
        else if (this.partnerUnitJpanels == null) {
            (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.partnerUnitJpanels.setSelectionForeground(Color.white);
            this.partnerUnitJpanels.removeAll();
            this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof PartnerArenaModelPanel) {
                        PartnerArenaModelPanel unitJpanel = (PartnerArenaModelPanel)value;
                        return (Component)unitJpanel;
                    }
                    return this;
                }
            };
            this.partnerUnitJpanels.setCellRenderer(cellRenderer);
            this.partnerUnitJpanels.setOpaque(false);
            this.model = new DefaultListModel<>();
            this.partnerUnitJpanels.setModel(this.getModel());
        }
        return this.partnerUnitJpanels;
    }
    
    public void setPartnerUnitJpanels(JList<PartnerArenaModelPanel> partnerUnitJpanels) {
        this.partnerUnitJpanels = partnerUnitJpanels;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public DefaultListModel<PartnerArenaModelPanel> getModel() {
        if (this.model == null) {
            this.model = new DefaultListModel<>();
        }
        return this.model;
    }
    
    public void setModel(DefaultListModel<PartnerArenaModelPanel> model) {
        this.model = model;
    }
}
