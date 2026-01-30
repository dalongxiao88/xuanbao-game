package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.ScrollUI;
import org.come.until.CutButtonImage;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.IncludedSuitMouslisten;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.JadeorGoodstableBean;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.come.mouslisten.IncludedPartsMpuslisten;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.tool.btn.WorkshopBtn;
import java.math.BigDecimal;
import com.tool.btn.SwitchPageBtn;
import javax.swing.JPanel;

public class AlreadyRecordedJpanel extends JPanel
{
    public static int suitNum;
    private SwitchPageBtn addNumBtn;
    private BigDecimal money;
    private BigDecimal sxlxz;
    private WorkshopBtn workshopBtn1;
    private WorkshopBtn workshopBtn2;
    private WorkshopBtn workshopBtn3;
    private WorkshopBtn workshopBtn4;
    private JTextField textField;
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private int suitid;
    private JLabel[] labSuitParts;
    private IncludedPartsMpuslisten[] mpuslisten;
    private WorkshopBtn labAct;
    public JLabel labgundong;
    private JList<String> listSuit;
    private DefaultListModel<String> listModel;
    private JadeorGoodstableBean goodstableBean;
    private ImageIcon icon;
    
    public AlreadyRecordedJpanel() {
        this.labSuitParts = new JLabel[9];
        this.mpuslisten = new IncludedPartsMpuslisten[9];
        this.goodstableBean = new JadeorGoodstableBean();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(552, 435));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 65);
            offBtn.setBounds(515, 10, 25, 25);
            this.add(offBtn);
            this.listModel = new DefaultListModel<>();
            (this.listSuit = new JList<>()).setOpaque(false);
            this.listSuit.setSelectionBackground(UIUtils.Color_BACK);
            this.listSuit.setSelectionForeground(Color.green);
            this.listSuit.setForeground(Color.white);
            this.listSuit.setFont(new Font("微软雅黑", 1, 14));
            this.listSuit.setBackground(UIUtils.Color_BACK);
            this.listSuit.setModel(this.listModel);
            this.listSuit.addMouseListener(new IncludedSuitMouslisten(this));
            (this.jScrollPane = new JScrollPane(this.listSuit)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(47, 55, 166, 298);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            for (int i = 0, size = this.labSuitParts.length; i < size; ++i) {
                this.labSuitParts[i] = new JLabel();
                if (i > 3) {
                    this.labSuitParts[i].setBounds(245 + (i - 4) * 65, 136, 56, 55);
                }
                else if (i < 8) {
                    this.labSuitParts[i].setBounds(231 + i * 64, 68, 56, 55);
                }
                else {
                    this.labSuitParts[i].setBounds(231 + i * 64, 68, 56, 55);
                    this.labSuitParts[i].setVisible(false);
                }
                this.mpuslisten[i] = new IncludedPartsMpuslisten(i, this);
                this.labSuitParts[i].addMouseListener(this.mpuslisten[i]);
                this.add(this.labSuitParts[i]);
            }
            (this.addNumBtn = new SwitchPageBtn("inkImg/button/16.png", 1, 11)).setBounds(194, 360, 18, 18);
            this.add(this.addNumBtn);
            (this.textField = new JTextField()).setBounds(328, 267, 141, 16);
            this.textField.setFont(UIUtils.TEXT_FONT1);
            this.textField.setOpaque(false);
            this.textField.setBorder(BorderFactory.createEmptyBorder());
            this.textField.setCaretColor(Color.white);
            this.textField.setForeground(Color.white);
            this.textField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                        return;
                    }
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str.length() == 0) {
                        AlreadyRecordedJpanel.this.textField.setText("");
                    }
                    if (str.length() == 1 && Long.parseLong(str) == 0L) {
                        AlreadyRecordedJpanel.this.textField.setText("");
                    }
                    if (str.length() >= 3) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
            });
            this.add(this.textField);
            (this.workshopBtn1 = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "删除", 9, this)).setBounds(53, 383, 59, 24);
            this.add(this.workshopBtn1);
            (this.workshopBtn2 = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "收录", 10, this)).setBounds(129, 383, 59, 24);
            this.add(this.workshopBtn2);
            (this.workshopBtn3 = new WorkshopBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "获得", 11, this)).setBounds(435, 357, 34, 17);
            this.add(this.workshopBtn3);
            (this.workshopBtn4 = new WorkshopBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "生成玉符", 13, this)).setBounds(325, 381, 99, 24);
            this.add(this.workshopBtn4);
            (this.labAct = new WorkshopBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "激活", 15, this)).setBounds(451, 207, 34, 17);
            this.labAct.setBtn(-1);
            this.labAct.setForeground(Color.GRAY);
            this.labAct.setIcon(CutButtonImage.getImage("inkImg/button/36.png", -1, -1));
            this.add(this.labAct);
        }
        else {
            this.setPreferredSize(new Dimension(526, 475));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 65);
            offBtn.setBounds(500, 0, 25, 25);
            this.add(offBtn);
            this.listModel = new DefaultListModel<>();
            (this.listSuit = new JList<>()).setOpaque(false);
            this.listSuit.setSelectionBackground(UIUtils.Color_BACK);
            this.listSuit.setSelectionForeground(Color.green);
            this.listSuit.setForeground(Color.white);
            this.listSuit.setFont(new Font("微软雅黑", 1, 14));
            this.listSuit.setBackground(UIUtils.Color_BACK);
            this.listSuit.setModel(this.listModel);
            this.listSuit.addMouseListener(new IncludedSuitMouslisten(this));
            (this.jScrollPane = new JScrollPane(this.listSuit)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(24, 69, 158, 304);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            for (int i = 0, size = this.labSuitParts.length; i < size; ++i) {
                this.labSuitParts[i] = new JLabel();
                if (i > 4) {
                    this.labSuitParts[i].setBounds(223 + (i - 5) * 65, 153, 56, 57);
                }
                else {
                    this.labSuitParts[i].setBounds(197 + i * 57, 82, 53, 52);
                }
                this.mpuslisten[i] = new IncludedPartsMpuslisten(i, this);
                this.labSuitParts[i].addMouseListener(this.mpuslisten[i]);
                this.add(this.labSuitParts[i]);
            }
            (this.addNumBtn = new SwitchPageBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 11)).setBounds(166, 367, 19, 60);
            this.add(this.addNumBtn);
            (this.textField = new JTextField()).setBounds(308, 292, 134, 16);
            this.textField.setFont(UIUtils.TEXT_FONT1);
            this.textField.setOpaque(false);
            this.textField.setBorder(BorderFactory.createEmptyBorder());
            this.textField.setCaretColor(Color.white);
            this.textField.setForeground(Color.white);
            this.textField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                        return;
                    }
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str.length() == 0) {
                        AlreadyRecordedJpanel.this.textField.setText("");
                    }
                    if (str.length() == 1 && Long.parseLong(str) == 0L) {
                        AlreadyRecordedJpanel.this.textField.setText("");
                    }
                    if (str.length() >= 3) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    String str = AlreadyRecordedJpanel.this.textField.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            AlreadyRecordedJpanel.this.money = new BigDecimal(Long.parseLong(str) * 10000000L);
                            AlreadyRecordedJpanel.this.sxlxz = new BigDecimal(Long.parseLong(str) * 10L);
                        }
                    }
                    else {
                        AlreadyRecordedJpanel.this.money = null;
                        AlreadyRecordedJpanel.this.sxlxz = null;
                    }
                }
            });
            this.add(this.textField);
            (this.workshopBtn1 = new WorkshopBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "删除", 9, this)).setBounds(32, 410, 60, 26);
            this.add(this.workshopBtn1);
            (this.workshopBtn2 = new WorkshopBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "收录", 10, this)).setBounds(108, 410, 60, 26);
            this.add(this.workshopBtn2);
            (this.workshopBtn3 = new WorkshopBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "获得", 11, this)).setBounds(449, 385, 34, 17);
            this.add(this.workshopBtn3);
            (this.workshopBtn4 = new WorkshopBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "生成玉符", 13, this)).setBounds(315, 415, 80, 26);
            this.add(this.workshopBtn4);
            (this.labAct = new WorkshopBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "激活", 15, this)).setBounds(430, 229, 34, 17);
            this.labAct.setBtn(-1);
            this.labAct.setForeground(Color.GRAY);
            this.labAct.setIcon(CutButtonImage.getImage("img/xy2uiimg/notactivated.png", -1, -1));
            this.add(this.labAct);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S99.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 435, this);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 328, 310);
            }
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (this.sxlxz != null) {
                g.drawString(this.sxlxz.toString(), 328, 340);
            }
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 328, 370);
            }
            if (AlreadyRecordedJpanel.suitNum >= 0) {
                g.drawString(AlreadyRecordedJpanel.suitNum + "/" + RoleData.getRoleData().getPackRecord().getSuitNum(), 155, 374);
            }
            GoodsListFromServerUntil.drawIncludedSuit(g, 232, 68, this.suitid);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/alreadyrecorded.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 526, 475, this);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 308, 335);
            }
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (this.sxlxz != null) {
                g.drawString(this.sxlxz.toString(), 308, 366);
            }
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 308, 398);
            }
            if (AlreadyRecordedJpanel.suitNum >= 0) {
                g.drawString(AlreadyRecordedJpanel.suitNum + "/" + RoleData.getRoleData().getPackRecord().getSuitNum(), 115, 402);
            }
            GoodsListFromServerUntil.drawIncludedSuit(g, 206, 78, this.suitid);
            if (this.listModel.size() > 10) {
                this.remove(this.labgundong);
            }
        }
    }
    
    public void clearInterface() {
        this.textField.setText("");
        this.money = null;
        this.sxlxz = null;
        this.goodstableBean.setType(0);
        this.goodstableBean.setPartJade(null);
    }
    
    public JList<String> getListSuit() {
        return this.listSuit;
    }
    
    public void setListSuit(JList<String> listSuit) {
        this.listSuit = listSuit;
    }
    
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    
    public int getSuitid() {
        return this.suitid;
    }
    
    public void setSuitid(int suitid) {
        this.suitid = suitid;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public BigDecimal getSxlxz() {
        return this.sxlxz;
    }
    
    public void setSxlxz(BigDecimal sxlxz) {
        this.sxlxz = sxlxz;
    }
    
    public JTextField getTextField() {
        return this.textField;
    }
    
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
    
    public JLabel[] getLabSuitParts() {
        return this.labSuitParts;
    }
    
    public void setLabSuitParts(JLabel[] labSuitParts) {
        this.labSuitParts = labSuitParts;
    }
    
    public IncludedPartsMpuslisten[] getMpuslisten() {
        return this.mpuslisten;
    }
    
    public void setMpuslisten(IncludedPartsMpuslisten[] mpuslisten) {
        this.mpuslisten = mpuslisten;
    }
    
    public WorkshopBtn getLabAct() {
        return this.labAct;
    }
    
    public void setLabAct(WorkshopBtn labAct) {
        this.labAct = labAct;
    }
    
    public JadeorGoodstableBean getGoodstableBean() {
        return this.goodstableBean;
    }
    
    public void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        this.goodstableBean = goodstableBean;
    }
}
