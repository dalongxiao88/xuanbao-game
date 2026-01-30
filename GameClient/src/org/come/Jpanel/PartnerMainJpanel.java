package org.come.Jpanel;

import org.come.bean.LoginResult;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import org.come.entity.Pal;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.model.PalData;
import java.util.Map;
import org.come.until.AnalysisString;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.PartnerBtn;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class PartnerMainJpanel extends JPanel
{
    private JScrollPane scrollPane;
    private PartnerCardJpanel partnerCardJpanel;
    private PartnerBtn btnTeam;
    private PartnerBtn btnSkill;
    private PartnerBtn btnEquip;
    private JList<PartnerUnitJpanel> partnerUnitJpanels;
    private DefaultListModel<PartnerUnitJpanel> defaultListModel;
    private int palDataChooseId;
    private int palLvl;
    int type;
    private ImageIcon iconBack;
    
    public PartnerMainJpanel() {
        this.type = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(563, 383));
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 105);
            offBtn.setBounds(526, 10, 25, 25);
            this.add(offBtn);
            (this.partnerCardJpanel = new PartnerCardJpanel()).setBounds(205, 70, 356, 301);
            this.add(this.partnerCardJpanel);
        }
        else {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(563, 383));
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 105);
            offBtn.setBounds(524, 5, 25, 25);
            this.add(offBtn);
            (this.partnerCardJpanel = new PartnerCardJpanel()).setBounds(205, 70, 356, 301);
            this.add(this.partnerCardJpanel);
        }
        this.getBtnTeam();
        this.getBtnSkill();
        this.getBtnEquip();
        this.getScrollPane();
    }
    
    public void addPartnerUnit() {
        if (this.type == -1) {
            List<Pal> pals = RoleData.getRoleData().getPals();
            if (pals == null) {
                return;
            }
            this.type = 1;
            String loPals = RoleData.getRoleData().getLoginResult().getPals();
            String[] palsArr = null;
            if (loPals != null) {
                palsArr = loPals.split("\\|");
            }
            Set<Map.Entry<Integer, PalData>> entrySet = UserMessUntil.getAllPal().getAllPalData().entrySet();
            Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
            String lvl = null;
            if (this.palLvl != (int)grade) {
                this.palLvl = (int)grade;
                lvl = AnalysisString.lvl(this.palLvl);
            }
        LOOP:
            for (Map.Entry<Integer, PalData> entry : entrySet) {
                PalData palData = (PalData)entry.getValue();
                PartnerUnitJpanel partnerUnitJpanel = new PartnerUnitJpanel();
                partnerUnitJpanel.showPal(palData, this.pidGetPal(palData.getPalId()), palsArr, lvl);
                for (int i = 0, length = this.defaultListModel.size(); i < length; ++i) {
                    PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)this.defaultListModel.get(i);
                    if (partnerUnitJpanel.getState() == -1) {
                        if (unitJpanel.getState() == -1 && partnerUnitJpanel.getPid() < unitJpanel.getPid()) {
                            this.defaultListModel.add(i, partnerUnitJpanel);
                            continue LOOP;
                        }
                    }
                    else if (partnerUnitJpanel.getState() == 0) {
                        if (unitJpanel.getState() == -1) {
                            this.defaultListModel.add(i, partnerUnitJpanel);
                            continue LOOP;
                        }
                        else if (unitJpanel.getState() == 0 && partnerUnitJpanel.getPid() < unitJpanel.getPid()) {
                            this.defaultListModel.add(i, partnerUnitJpanel);
                            continue LOOP;
                        }
                    }
                    else if (unitJpanel.getState() == -1 || unitJpanel.getState() == 0) {
                        this.defaultListModel.add(i, partnerUnitJpanel);
                        continue LOOP;
                    }
                    else if (partnerUnitJpanel.getState() < unitJpanel.getState()) {
                        this.defaultListModel.add(i, partnerUnitJpanel);
                        continue LOOP;
                    }
                }
                this.defaultListModel.addElement(partnerUnitJpanel);
            }
            this.partnerUnitJpanels.setPreferredSize(new Dimension(137, 50 * entrySet.size()));
        }
        else {
            this.refreshLvl();
        }
        FormsManagement.showForm(105);
    }
    
    public void refreshLvl() {
        Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
        if ((int)grade == this.palLvl) {
            return;
        }
        this.palLvl = (int)grade;
        String lvl = AnalysisString.lvl(this.palLvl);
        String lvlStr = lvl + "级";
        for (int i = 0, length = this.defaultListModel.size(); i < length; ++i) {
            PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)this.defaultListModel.get(i);
            unitJpanel.getLabLvl().setText(lvlStr);
        }
        if (this.palDataChooseId != 0) {
            this.partnerCardJpanel.getPartnerTeamJpanel().getLabMessage()[2].setText(lvlStr);
        }
    }
    
    public PartnerUnitJpanel getPidJpanel(int pid) {
        for (int i = 0, length = this.defaultListModel.size(); i < length; ++i) {
            PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)this.defaultListModel.get(i);
            if (pid == unitJpanel.getPid()) {
                return unitJpanel;
            }
        }
        return null;
    }
    
    public void refreshPals(List<Pal> palList) {
        String loPals = RoleData.getRoleData().getLoginResult().getPals();
        String[] palsArr = null;
        if (loPals != null) {
            palsArr = loPals.split("\\|");
        }
        Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
        String lvl = null;
        if ((int)grade != this.palLvl) {
            this.palLvl = (int)grade;
            lvl = AnalysisString.lvl((int)grade);
        }
        for (int i = 0, length = palList.size(); i < length; ++i) {
            Pal pal = (Pal)palList.get(i);
            PalData palData = UserMessUntil.getPalData(pal.getpId());
            PartnerUnitJpanel partnerUnitJpanel = this.getPidJpanel(pal.getpId());
            int oState = partnerUnitJpanel.getState();
            int nState = partnerUnitJpanel.changePalState(this.pidGetPal(palData.getPalId()), palsArr, lvl);
            partnerUnitJpanel.setState(nState);
            if (oState != nState) {
                this.defaultListModel.removeElement(partnerUnitJpanel);
                boolean is = true;
                for (int i2 = 0, length2 = this.defaultListModel.size(); i2 < length2; ++i2) {
                    PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)this.defaultListModel.get(i2);
                    if (partnerUnitJpanel.getState() == -1) {
                        if (unitJpanel.getState() == -1 && partnerUnitJpanel.getPid() < unitJpanel.getPid()) {
                            this.defaultListModel.add(i2, partnerUnitJpanel);
                            is = false;
                            break;
                        }
                    }
                    else if (partnerUnitJpanel.getState() == 0) {
                        if (unitJpanel.getState() == -1) {
                            this.defaultListModel.add(i2, partnerUnitJpanel);
                            is = false;
                            break;
                        }
                        else if (unitJpanel.getState() == 0 && partnerUnitJpanel.getPid() < unitJpanel.getPid()) {
                            this.defaultListModel.add(i2, partnerUnitJpanel);
                            is = false;
                            break;
                        }
                    }
                    else if (unitJpanel.getState() == -1 || unitJpanel.getState() == 0) {
                        this.defaultListModel.add(i2, partnerUnitJpanel);
                        is = false;
                        break;
                    }
                    else if (partnerUnitJpanel.getState() < unitJpanel.getState()) {
                        this.defaultListModel.add(i2, partnerUnitJpanel);
                        is = false;
                        break;
                    }
                }
                if (is) {
                    this.defaultListModel.addElement(partnerUnitJpanel);
                }
            }
            if (this.palDataChooseId == pal.getpId()) {
                this.partnerCardJpanel.getPartnerEquipJpanel().ShowEquipArr(pal);
                this.partnerCardJpanel.getPartnerTeamJpanel().showPalMessage(pal, palData);
                partnerUnitJpanel.changeWarBtn();
            }
        }
    }
    
    public Pal pidGetPal(int chooseId) {
        List<Pal> pals = RoleData.getRoleData().getPals();
        for (int i = 0; i < pals.size(); ++i) {
            Pal pal = (Pal)pals.get(i);
            if (pal.getpId() == chooseId) {
                return pal;
            }
        }
        return null;
    }
    
    public Pal idGetPal(BigDecimal id) {
        List<Pal> pals = RoleData.getRoleData().getPals();
        for (int i = 0; i < pals.size(); ++i) {
            Pal pal = (Pal)pals.get(i);
            if (pal.getId().compareTo(id) == 0) {
                return pal;
            }
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B303.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 563, 383, this);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/S149.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 563, 383, this);
        }
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getViewport().setView(this.getPartnerUnitJpanels());
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scrollPane.setBounds(46, 52, 152, 312);
            }
            else {
                this.scrollPane.setBounds(47, 66, 148, 280);
            }
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public PartnerCardJpanel getPartnerCardJpanel() {
        return this.partnerCardJpanel;
    }
    
    public void setPartnerCardJpanel(PartnerCardJpanel partnerCardJpanel) {
        this.partnerCardJpanel = partnerCardJpanel;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public PartnerBtn getBtnTeam() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnTeam == null) {
                (this.btnTeam = new PartnerBtn("inkImg/button1/K78.png", 1, 1, this)).setBounds(215, 26, 75, 33);
                this.add(this.btnTeam);
            }
        }
        else if (this.btnTeam == null) {
            (this.btnTeam = new PartnerBtn("img/xy2uiimg/B229.png", 1, 1, this)).setBounds(215, 25, 75, 35);
            this.add(this.btnTeam);
        }
        return this.btnTeam;
    }
    
    public void setBtnTeam(PartnerBtn btnTeam) {
        this.btnTeam = btnTeam;
    }
    
    public PartnerBtn getBtnSkill() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnSkill == null) {
                (this.btnSkill = new PartnerBtn("inkImg/button1/K79.png", 1, 2, this)).setBounds(293, 26, 75, 33);
                this.add(this.btnSkill);
            }
        }
        else if (this.btnSkill == null) {
            (this.btnSkill = new PartnerBtn("img/xy2uiimg/B232.png", 1, 2, this)).setBounds(287, 25, 75, 35);
            this.add(this.btnSkill);
        }
        return this.btnSkill;
    }
    
    public void setBtnSkill(PartnerBtn btnSkill) {
        this.btnSkill = btnSkill;
    }
    
    public PartnerBtn getBtnEquip() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnEquip == null) {
                (this.btnEquip = new PartnerBtn("inkImg/button1/K81.png", 1, 3, this)).setBounds(371, 26, 75, 33);
                this.add(this.btnEquip);
            }
        }
        else if (this.btnEquip == null) {
            (this.btnEquip = new PartnerBtn("img/xy2uiimg/B234.png", 1, 3, this)).setBounds(359, 25, 75, 35);
            this.add(this.btnEquip);
        }
        return this.btnEquip;
    }
    
    public void setBtnEquip(PartnerBtn btnEquip) {
        this.btnEquip = btnEquip;
    }
    
    public JList<PartnerUnitJpanel> getPartnerUnitJpanels() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.partnerUnitJpanels == null) {
                (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
                this.partnerUnitJpanels.setSelectionForeground(Color.white);
                this.partnerUnitJpanels.setForeground(Color.white);
                this.partnerUnitJpanels.setFont(UIUtils.TEXT_HY16);
                this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
                DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if (value instanceof PartnerUnitJpanel) {
                            PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)value;
                            return (Component)unitJpanel;
                        }
                        return this;
                    }
                };
                this.partnerUnitJpanels.setCellRenderer(cellRenderer);
                this.partnerUnitJpanels.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)PartnerMainJpanel.this.partnerUnitJpanels.getSelectedValue();
                        if (unitJpanel == null) {
                            return;
                        }
                        PartnerMainJpanel.this.palDataChooseId = unitJpanel.getPid();
                        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                        Integer grade = loginResult.getGrade();
                        String lvl = null;
                        if ((int)grade != PartnerMainJpanel.this.palLvl) {
                            PartnerMainJpanel.this.palLvl = (int)grade;
                            lvl = AnalysisString.lvl(PartnerMainJpanel.this.palLvl);
                        }
                        String pals = loginResult.getPals();
                        String[] palsArr = null;
                        if (pals != null && !"".equals(pals)) {
                            palsArr = pals.split("\\|");
                        }
                        Pal pidGetPal = PartnerMainJpanel.this.pidGetPal(PartnerMainJpanel.this.palDataChooseId);
                        unitJpanel.setState(unitJpanel.changePalState(pidGetPal, palsArr, lvl));
                        unitJpanel.changeWarBtn();
                        PartnerMainJpanel.this.partnerCardJpanel.getPartnerTeamJpanel().hideBtnArr();
                        PartnerMainJpanel.this.partnerCardJpanel.getPartnerTeamJpanel().showPalMessage(pidGetPal, UserMessUntil.getPalData(PartnerMainJpanel.this.palDataChooseId));
                        PartnerMainJpanel.this.partnerCardJpanel.getPartnerSkillJpanel().showBornSkill(PartnerMainJpanel.this.palDataChooseId);
                        PartnerMainJpanel.this.partnerCardJpanel.getPartnerEquipJpanel().ShowEquipArr(pidGetPal);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                });
                this.partnerUnitJpanels.setOpaque(false);
                this.partnerUnitJpanels.setModel(this.getDefaultListModel());
            }
        }
        else if (this.partnerUnitJpanels == null) {
            (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.partnerUnitJpanels.setSelectionForeground(Color.white);
            this.partnerUnitJpanels.setForeground(Color.white);
            this.partnerUnitJpanels.setFont(UIUtils.TEXT_HY16);
            this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof PartnerUnitJpanel) {
                        PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)value;
                        return (Component)unitJpanel;
                    }
                    return this;
                }
            };
            this.partnerUnitJpanels.setCellRenderer(cellRenderer);
            this.partnerUnitJpanels.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    PartnerUnitJpanel unitJpanel = (PartnerUnitJpanel)PartnerMainJpanel.this.partnerUnitJpanels.getSelectedValue();
                    if (unitJpanel == null) {
                        return;
                    }
                    PartnerMainJpanel.this.palDataChooseId = unitJpanel.getPid();
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    Integer grade = loginResult.getGrade();
                    String lvl = null;
                    if ((int)grade != PartnerMainJpanel.this.palLvl) {
                        PartnerMainJpanel.this.palLvl = (int)grade;
                        lvl = AnalysisString.lvl(PartnerMainJpanel.this.palLvl);
                    }
                    String pals = loginResult.getPals();
                    String[] palsArr = null;
                    if (pals != null && !"".equals(pals)) {
                        palsArr = pals.split("\\|");
                    }
                    Pal pidGetPal = PartnerMainJpanel.this.pidGetPal(PartnerMainJpanel.this.palDataChooseId);
                    unitJpanel.setState(unitJpanel.changePalState(pidGetPal, palsArr, lvl));
                    unitJpanel.changeWarBtn();
                    PartnerMainJpanel.this.partnerCardJpanel.getPartnerTeamJpanel().hideBtnArr();
                    PartnerMainJpanel.this.partnerCardJpanel.getPartnerTeamJpanel().showPalMessage(pidGetPal, UserMessUntil.getPalData(PartnerMainJpanel.this.palDataChooseId));
                    PartnerMainJpanel.this.partnerCardJpanel.getPartnerSkillJpanel().showBornSkill(PartnerMainJpanel.this.palDataChooseId);
                    PartnerMainJpanel.this.partnerCardJpanel.getPartnerEquipJpanel().ShowEquipArr(pidGetPal);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.partnerUnitJpanels.setOpaque(false);
            this.partnerUnitJpanels.setModel(this.getDefaultListModel());
        }
        return this.partnerUnitJpanels;
    }
    
    public void setPartnerUnitJpanels(JList<PartnerUnitJpanel> partnerUnitJpanels) {
        this.partnerUnitJpanels = partnerUnitJpanels;
    }
    
    public int getPalDataChooseId() {
        return this.palDataChooseId;
    }
    
    public void setPalDataChooseId(int palDataChooseId) {
        this.palDataChooseId = palDataChooseId;
    }
    
    public DefaultListModel<PartnerUnitJpanel> getDefaultListModel() {
        if (this.defaultListModel == null) {
            this.defaultListModel = new DefaultListModel<>();
        }
        return this.defaultListModel;
    }
    
    public void setDefaultListModel(DefaultListModel<PartnerUnitJpanel> defaultListModel) {
        this.defaultListModel = defaultListModel;
    }
}
