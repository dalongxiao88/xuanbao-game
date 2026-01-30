package org.come.Jpanel;

import org.come.until.JmSum;
import org.come.bean.LoginResult;
import com.tool.role.RoleProperty;
import org.come.bean.RoleAttribute;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.AnalysisString;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.math.RoundingMode;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.Frame.RoleBornJframe;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import org.soaring.btn.CharacterBtn;
import com.tool.btn.JpanelBornlBtn;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class RoleBornJpanel extends JPanel implements MouseListener
{
    private final long serialVersionUID = 1L;
    private int expsize;
    private JLabel label;
    private int level;
    private JLabel explain;
    private JLabel state1;
    private JLabel state2;
    private JLabel labrolename1;
    private JLabel labrolename2;
    private JLabel labrootbone1;
    private JLabel labintelligence1;
    private JLabel labpower1;
    private JLabel labspeed1;
    private JLabel labability1;
    private JLabel labpointnumber1;
    private JLabel labrootbone2;
    private JLabel labintelligence2;
    private JLabel labpower2;
    private JLabel labspeed2;
    private JLabel labability2;
    private JLabel labpointnumber2;
    private JpanelBornlBtn btnsure1;
    private JpanelBornlBtn btnsure2;
    private JpanelBornlBtn switchbtn1;
    private JpanelBornlBtn switchbtn2;
    private JpanelBornlBtn resetbtn1;
    private JpanelBornlBtn resetbtn2;
    private CharacterBtn nameBtn;
    private CharacterBtn idBtn;
    private CharacterBtn labappellationBtn;
    private CharacterBtn attributeBtn;
    private CharacterBtn transformationBtn;
    private CharacterBtn[] dians1;
    private CharacterBtn[] dians2;
    private static JList<String> listpet;
    private JScrollPane jScrollPane;
    private static DefaultListModel<String> listModel;
    private static JList<String> listpet1;
    private JScrollPane jScrollPane1;
    private static DefaultListModel<String> listModel1;
    private Color fontcolor;
    private ImageIcon icon;
    private String style;
    
    public RoleBornJpanel(RoleBornJframe roleBornJframe) throws Exception {
        this.dians1 = new CharacterBtn[10];
        this.dians2 = new CharacterBtn[10];
        this.style = MyIsif.getStyle();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(406, 365));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1234);
            offBtn.setBounds(373, 5, 25, 25);
            this.add(offBtn);
            RoleBornJpanel.listModel = new DefaultListModel<>();
            (RoleBornJpanel.listpet = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.white;
            RoleBornJpanel.listpet.setSelectionForeground(this.fontcolor);
            RoleBornJpanel.listpet.setForeground(this.fontcolor);
            RoleBornJpanel.listpet.setFont(new Font("微软雅体", 14, 12));
            RoleBornJpanel.listpet.setBackground(UIUtils.Color_BACK);
            RoleBornJpanel.listpet.setModel(RoleBornJpanel.listModel);
            RoleBornJpanel.listpet.setSelectionMode(0);
            (this.jScrollPane = new JScrollPane(RoleBornJpanel.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(51, 135, 154, 155);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            RoleBornJpanel.listModel1 = new DefaultListModel<>();
            (RoleBornJpanel.listpet1 = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.white;
            RoleBornJpanel.listpet1.setSelectionForeground(this.fontcolor);
            RoleBornJpanel.listpet1.setForeground(this.fontcolor);
            RoleBornJpanel.listpet1.setFont(new Font("微软雅体", 14, 12));
            RoleBornJpanel.listpet1.setBackground(UIUtils.Color_BACK);
            RoleBornJpanel.listpet1.setModel(RoleBornJpanel.listModel1);
            RoleBornJpanel.listpet1.setSelectionMode(0);
            (this.jScrollPane1 = new JScrollPane(RoleBornJpanel.listpet1)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(223, 135, 154, 155);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            (this.labrolename1 = new JLabel()).setBounds(150, 67, 120, 15);
            this.labrolename1.setForeground(Color.black);
            this.labrolename1.setFont(new Font("微软雅黑", 1, 14));
            this.labrolename1.setText("");
            this.add(this.labrolename1);
            (this.labrolename2 = new JLabel()).setBounds(327, 67, 120, 15);
            this.labrolename2.setForeground(Color.black);
            this.labrolename2.setFont(new Font("微软雅黑", 1, 14));
            this.labrolename2.setText("");
            this.add(this.labrolename2);
            (this.state1 = new JLabel()).setBounds(70, 95, 120, 15);
            this.state1.setForeground(Color.GREEN);
            this.state1.setFont(new Font("楷体", 1, 14));
            this.state1.setText("正在使用");
            this.add(this.state1);
            (this.state2 = new JLabel()).setBounds(244, 95, 120, 15);
            this.state2.setForeground(Color.WHITE);
            this.state2.setFont(new Font("楷体", 1, 14));
            this.state2.setText("未启用");
            this.add(this.state2);
            (this.explain = new JLabel()).setBounds(30, 320, 320, 30);
            this.explain.setForeground(Color.red);
            this.explain.setFont(new Font("楷体", 1, 14));
            this.add(this.explain);
            Font font = new Font("宋体", 0, 12);
            (this.btnsure1 = new JpanelBornlBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, 11, "切换", this)).setBounds(300, 63, 68, 17);
            this.btnsure1.setFont(font);
            this.add(this.btnsure1);
        }
        else {
            this.setPreferredSize(new Dimension(350, 311));
            this.setOpaque(false);
            this.setLayout(null);
            (this.state1 = new JLabel()).setBounds(30, 65, 120, 15);
            this.state1.setForeground(Color.ORANGE);
            this.state1.setFont(new Font("楷体", 1, 14));
            this.state1.setText("已启用");
            this.add(this.state1);
            (this.state2 = new JLabel()).setBounds(185, 65, 120, 15);
            this.state2.setForeground(Color.RED);
            this.state2.setFont(new Font("楷体", 1, 14));
            this.state2.setText("未启用");
            this.add(this.state2);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 123);
            offBtn.setBounds(325, 0, 23, 23);
            this.add(offBtn);
            Font font = new Font("楷体", 0, 14);
            RoleBornJpanel.listModel = new DefaultListModel<>();
            (RoleBornJpanel.listpet = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.white;
            RoleBornJpanel.listpet.setSelectionForeground(this.fontcolor);
            RoleBornJpanel.listpet.setForeground(this.fontcolor);
            RoleBornJpanel.listpet.setFont(new Font("微软雅体", 14, 12));
            RoleBornJpanel.listpet.setBackground(UIUtils.Color_BACK);
            RoleBornJpanel.listpet.setModel(RoleBornJpanel.listModel);
            RoleBornJpanel.listpet.setSelectionMode(0);
            (this.jScrollPane = new JScrollPane(RoleBornJpanel.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            RoleBornJpanel.listModel1 = new DefaultListModel<>();
            (RoleBornJpanel.listpet1 = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.white;
            RoleBornJpanel.listpet1.setSelectionForeground(this.fontcolor);
            RoleBornJpanel.listpet1.setForeground(this.fontcolor);
            RoleBornJpanel.listpet1.setFont(new Font("微软雅体", 14, 12));
            RoleBornJpanel.listpet1.setBackground(UIUtils.Color_BACK);
            RoleBornJpanel.listpet1.setModel(RoleBornJpanel.listModel1);
            RoleBornJpanel.listpet1.setSelectionMode(0);
            (this.jScrollPane1 = new JScrollPane(RoleBornJpanel.listpet1)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            this.jScrollPane1.setBounds(189, 110, 120, 155);
            this.jScrollPane.setBounds(35, 110, 120, 155);
            (this.btnsure1 = new JpanelBornlBtn("inkImg/hongmu/B32h.png", 1, UIUtils.COLOR_BTNPUTONG, 11, "切换", this, UIUtils.TEXT_FONT2)).setBounds(280, 32, 51, 17);
            this.add(this.btnsure1);
        }
    }
    
    public void showStar() {
        String born = RoleData.getRoleData().getPrivateData().getBorn();
        RoleBornJpanel.listModel.removeAllElements();
        if (StringUtils.isNotBlank(born)) {
            String[] v = born.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                String[] n = v[i].split("=");
                RoleBornJpanel.listModel.add(i, n[0] + new BigDecimal(n[1]).setScale(2, RoundingMode.HALF_UP).toString());
            }
        }
        String born2 = RoleData.getRoleData().getPrivateData().getBorn1();
        RoleBornJpanel.listModel1.removeAllElements();
        if (StringUtils.isNotBlank(born2)) {
            String[] v2 = born2.split("\\|");
            for (int j = 0; j < v2.length; ++j) {
                String[] n2 = v2[j].split("=");
                RoleBornJpanel.listModel1.add(j, n2[0] + new BigDecimal(n2[1]).setScale(2, RoundingMode.HALF_UP).toString());
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.style.equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S31111.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 406, 365, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/xzqh.png");
            g.drawImage(this.icon.getImage(), 0, 0, 350, 311, this);
        }
    }
    
    public int getdian1(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone1.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence1.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower1.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed1.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability1.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(this.labpointnumber1.getText());
            }
        }
        catch (Exception e) {
            type = 0;
        }
        return type;
    }
    
    public int getdian2(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone2.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence2.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower2.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed2.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability2.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(this.labpointnumber2.getText());
            }
        }
        catch (Exception e) {
            type = 0;
        }
        return type;
    }
    
    public void adddian1(int type, int point) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone1.getText());
                this.labrootbone1.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence1.getText());
                this.labintelligence1.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower1.getText());
                this.labpower1.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed1.getText());
                this.labspeed1.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability1.getText());
                this.labability1.setText(type + point + "");
            }
            type = Integer.parseInt(this.labpointnumber1.getText());
            this.labpointnumber1.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    public void adddian2(int type, int point) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone2.getText());
                this.labrootbone2.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence2.getText());
                this.labintelligence2.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower2.getText());
                this.labpower2.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed2.getText());
                this.labspeed2.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability2.getText());
                this.labability2.setText(type + point + "");
            }
            type = Integer.parseInt(this.labpointnumber2.getText());
            this.labpointnumber2.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    public void changeSoaringPanel(int lvltrue) {
        this.level = lvltrue;
        if (lvltrue <= 3) {
            this.dians1[8].setVisible(false);
            this.dians2[8].setVisible(false);
            this.dians1[9].setVisible(false);
            this.dians2[9].setVisible(false);
            this.labability1.setVisible(false);
            this.labability2.setVisible(false);
        }
        else {
            this.dians1[8].setVisible(true);
            this.dians2[8].setVisible(true);
            this.dians1[9].setVisible(true);
            this.dians2[9].setVisible(true);
            this.labability1.setVisible(true);
            this.labability2.setVisible(true);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        FormsManagement.HideForm(0);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public JLabel getLabel() {
        return this.label;
    }
    
    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public JLabel getLabrootbone1() {
        return this.labrootbone1;
    }
    
    public void setLabrootbone1(JLabel labrootbone1) {
        this.labrootbone1 = labrootbone1;
    }
    
    public JLabel getLabintelligence1() {
        return this.labintelligence1;
    }
    
    public void setLabintelligence1(JLabel labintelligence1) {
        this.labintelligence1 = labintelligence1;
    }
    
    public JLabel getLabpower1() {
        return this.labpower1;
    }
    
    public void setLabpower1(JLabel labpower1) {
        this.labpower1 = labpower1;
    }
    
    public JLabel getLabspeed1() {
        return this.labspeed1;
    }
    
    public void setLabspeed1(JLabel labspeed1) {
        this.labspeed1 = labspeed1;
    }
    
    public JLabel getLabpointnumber1() {
        return this.labpointnumber1;
    }
    
    public void setLabpointnumber1(JLabel labpointnumber1) {
        this.labpointnumber1 = labpointnumber1;
    }
    
    public int getExpsize() {
        return this.expsize;
    }
    
    public void setExpsize(int expsize) {
        this.expsize = expsize;
    }
    
    public JLabel getLabability1() {
        return this.labability1;
    }
    
    public void setLabability1(JLabel labability1) {
        this.labability1 = labability1;
    }
    
    public JpanelBornlBtn getBtnsure1() {
        return this.btnsure1;
    }
    
    public void setBtnsure1(JpanelBornlBtn btnsure1) {
        this.btnsure1 = btnsure1;
    }
    
    public JpanelBornlBtn getBtnsure2() {
        return this.btnsure2;
    }
    
    public void setBtnsure2(JpanelBornlBtn btnsure2) {
        this.btnsure2 = btnsure2;
    }
    
    public CharacterBtn getNameBtn() {
        return this.nameBtn;
    }
    
    public void setNameBtn(CharacterBtn nameBtn) {
        this.nameBtn = nameBtn;
    }
    
    public CharacterBtn getIdBtn() {
        return this.idBtn;
    }
    
    public void setIdBtn(CharacterBtn idBtn) {
        this.idBtn = idBtn;
    }
    
    public CharacterBtn getLabappellationBtn() {
        return this.labappellationBtn;
    }
    
    public void setLabappellationBtn(CharacterBtn labappellationBtn) {
        this.labappellationBtn = labappellationBtn;
    }
    
    public CharacterBtn getAttributeBtn() {
        return this.attributeBtn;
    }
    
    public void setAttributeBtn(CharacterBtn attributeBtn) {
        this.attributeBtn = attributeBtn;
    }
    
    public CharacterBtn getTransformationBtn() {
        return this.transformationBtn;
    }
    
    public void setTransformationBtn(CharacterBtn transformationBtn) {
        this.transformationBtn = transformationBtn;
    }
    
    public CharacterBtn[] getDians1() {
        return this.dians1;
    }
    
    public void setDians1(CharacterBtn[] dians1) {
        this.dians1 = dians1;
    }
    
    public CharacterBtn[] getDians2() {
        return this.dians2;
    }
    
    public void setDians2(CharacterBtn[] dians2) {
        this.dians2 = dians2;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public long getSerialversionuid() {
        return 1L;
    }
    
    public JLabel getLabrootbone2() {
        return this.labrootbone2;
    }
    
    public void setLabrootbone2(JLabel labrootbone2) {
        this.labrootbone2 = labrootbone2;
    }
    
    public JLabel getLabintelligence2() {
        return this.labintelligence2;
    }
    
    public void setLabintelligence2(JLabel labintelligence2) {
        this.labintelligence2 = labintelligence2;
    }
    
    public JLabel getLabpower2() {
        return this.labpower2;
    }
    
    public void setLabpower2(JLabel labpower2) {
        this.labpower2 = labpower2;
    }
    
    public JLabel getLabspeed2() {
        return this.labspeed2;
    }
    
    public void setLabspeed2(JLabel labspeed2) {
        this.labspeed2 = labspeed2;
    }
    
    public JLabel getLabability2() {
        return this.labability2;
    }
    
    public void setLabability2(JLabel labability2) {
        this.labability2 = labability2;
    }
    
    public JLabel getLabpointnumber2() {
        return this.labpointnumber2;
    }
    
    public void setLabpointnumber2(JLabel labpointnumber2) {
        this.labpointnumber2 = labpointnumber2;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public JLabel getState1() {
        return this.state1;
    }
    
    public void setState1(JLabel state1) {
        this.state1 = state1;
    }
    
    public JLabel getState2() {
        return this.state2;
    }
    
    public void setState2(JLabel state2) {
        this.state2 = state2;
    }
    
    public JLabel getLabrolename1() {
        return this.labrolename1;
    }
    
    public void setLabrolename1(JLabel labrolename1) {
        this.labrolename1 = labrolename1;
    }
    
    public JLabel getLabrolename2() {
        return this.labrolename2;
    }
    
    public void setLabrolename2(JLabel labrolename2) {
        this.labrolename2 = labrolename2;
    }
    
    public void getplayerValue() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getCurrentattribute() != null && (int)loginResult.getCurrentattribute() != 0) {
            if ((int)loginResult.getCurrentattribute() == 1) {
                this.state1.setForeground(Color.green);
                this.state1.setText("当前启用中");
            }
            else if ((int)loginResult.getCurrentattribute() == 2) {
                this.state2.setForeground(Color.green);
                this.state2.setText("当前启用中");
            }
        }
        int lvltrue = AnalysisString.lvltrue((int)loginResult.getGrade());
        this.changeSoaringPanel(lvltrue);
        RolePanelShow.changeGrade((int)loginResult.getGrade());
        RoleAttribute roleAttribute = RoleData.getRoleData().getRoleAttribute();
        int lvl = AnalysisString.lvlint((int)loginResult.getGrade());
        if (roleAttribute == null) {
            roleAttribute = new RoleAttribute();
        }
        if (roleAttribute.getBONEONE() == null) {
            roleAttribute.setBONEONE(Integer.valueOf(lvl));
            roleAttribute.setSPIRONE(Integer.valueOf(lvl));
            roleAttribute.setPOWERONE(Integer.valueOf(lvl));
            roleAttribute.setSPEEDONE(Integer.valueOf(lvl));
            if (loginResult.getTurnAround() >= 4) {
                roleAttribute.setCALMONE(Integer.valueOf(lvl));
            }
            else {
                roleAttribute.setCALMONE(Integer.valueOf(0));
            }
        }
        if (roleAttribute.getBONETWO() == null) {
            roleAttribute.setBONETWO(Integer.valueOf(lvl));
            roleAttribute.setSPIRTWO(Integer.valueOf(lvl));
            roleAttribute.setPOWERTWO(Integer.valueOf(lvl));
            roleAttribute.setSPEEDTWO(Integer.valueOf(lvl));
            if (loginResult.getTurnAround() >= 4) {
                roleAttribute.setCALMTWO(Integer.valueOf(lvl));
            }
            else {
                roleAttribute.setCALMTWO(Integer.valueOf(0));
            }
        }
        this.getLabpointnumber1().setText(getCanpoint1(roleAttribute).toString());
        RoleProperty property1 = RoleProperty.property;
        if (property1 == null) {
            return;
        }
        this.getLabrootbone1().setText(roleAttribute.getBONEONE() + "");
        this.getLabintelligence1().setText(roleAttribute.getSPIRONE() + "");
        this.getLabpower1().setText(roleAttribute.getPOWERONE() + "");
        this.getLabspeed1().setText(roleAttribute.getSPEEDONE() + "");
        this.getLabability1().setText(roleAttribute.getCALMONE() + "");
        this.getLabpointnumber2().setText(getCanpoint2(roleAttribute).toString());
        RoleProperty property2 = RoleProperty.property;
        if (property2 == null) {
            return;
        }
        this.getLabrootbone2().setText(roleAttribute.getBONETWO() + "");
        this.getLabintelligence2().setText(roleAttribute.getSPIRTWO() + "");
        this.getLabpower2().setText(roleAttribute.getPOWERTWO() + "");
        this.getLabspeed2().setText(roleAttribute.getSPEEDTWO() + "");
        this.getLabability2().setText(roleAttribute.getCALMTWO() + "");
    }
    
    public static Integer getCanpoint1(RoleAttribute roleAttribute) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = (int)loginResult.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int canpoint = lvl * 8;
        canpoint += loginResult.getExtraPointInt();
        if (Turn < 4) {
            canpoint += Turn * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        int v = (int)roleAttribute.getBONEONE();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getSPIRONE();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getPOWERONE();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getSPEEDONE();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getCALMONE();
        canpoint -= v;
        if (v < 0 || (Turn >= 4 && v < lvl)) {
            JmSum.xiugaiqi();
        }
        if (canpoint < 0) {
            JmSum.xiugaiqi();
        }
        return Integer.valueOf(canpoint);
    }
    
    public static Integer getCanpoint2(RoleAttribute roleAttribute) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = (int)loginResult.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int canpoint = lvl * 8;
        canpoint += loginResult.getExtraPointInt();
        if (Turn < 4) {
            canpoint += Turn * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        int v = (int)roleAttribute.getBONETWO();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getSPIRTWO();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getPOWERTWO();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getSPEEDTWO();
        canpoint -= v;
        if (v < lvl) {
            JmSum.xiugaiqi();
        }
        v = (int)roleAttribute.getCALMTWO();
        canpoint -= v;
        if (v < 0 || (Turn >= 4 && v < lvl)) {
            JmSum.xiugaiqi();
        }
        if (canpoint < 0) {
            JmSum.xiugaiqi();
        }
        return Integer.valueOf(canpoint);
    }
    
    public static JList<String> getListpet1() {
        return RoleBornJpanel.listpet1;
    }
    
    public static void setListpet1(JList<String> listpet1) {
        RoleBornJpanel.listpet1 = listpet1;
    }
    
    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }
    
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public static DefaultListModel<String> getListModel1() {
        return RoleBornJpanel.listModel1;
    }
    
    public static void setListModel1(DefaultListModel<String> listModel1) {
        RoleBornJpanel.listModel1 = listModel1;
    }
}
