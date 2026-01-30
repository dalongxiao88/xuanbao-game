package org.come.Jpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.mouslisten.PkPutLabMouseListener;
import java.math.BigDecimal;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import org.come.bean.PalacePkBean;
import org.come.until.LimitNumTextArea;
import com.tool.btn.WorkshopBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class PalacePKJpanel extends JPanel
{
    private JTextField textName;
    private JTextField textGold;
    private boolean[] putChooseType;
    private int putTextType;
    private JLabel[] putLab;
    private JLabel[] putFundLab;
    private JLabel remarkLab;
    private JLabel putIntoLab;
    private JLabel challengeLab;
    private JLabel sendBellLab;
    private boolean putIntoBool;
    private boolean challengeBool;
    private boolean sendBellBool;
    private WorkshopBtn workshopBtn;
    private WorkshopBtn cancelBtn;
    private JLabel putUnit;
    private String[] unitString;
    private String[] fundString;
    private LimitNumTextArea sendBelTextArea;
    private int winnerType;
    private PalacePkBean palacePkBean;
    private ImageIcon icon;
    private ImageIcon iconLeft;
    private ImageIcon pkNameImg;
    
    public PalacePKJpanel() {
        this.putIntoBool = true;
        this.challengeBool = true;
        this.sendBellBool = true;
        this.unitString = new String[] { "两", "仙玉", "经验", "消耗金钱：", "消耗仙玉：", "消耗经验：" };
        this.winnerType = 0;
        this.icon = new ImageIcon("inkImg/background/S119.png");
        this.iconLeft = CutButtonImage.getImage("inkImg/background/S120.png", -1, -1);
        this.setPreferredSize(new Dimension(442, 385));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 66);
        offBtn.setBounds(405, 10, 25, 25);
        this.add(offBtn);
        this.getPutLab();
        this.add(this.getCancelBtn());
        this.add(this.getPutUnit());
        this.add(this.getSendBelTextArea());
        this.getPutChooseType();
        this.getFundString();
        this.getRemarkLab();
        (this.textName = new JTextField()).setBounds(205, 29, 98, 16);
        this.textName.setCaretColor(Color.white);
        this.textName.setOpaque(false);
        this.textName.setBorder(BorderFactory.createEmptyBorder());
        this.textName.setForeground(Color.white);
        this.textName.setFont(UIUtils.TEXT_FONT1);
        this.add(this.textName);
        (this.textGold = new JTextField()).setBounds(87, 112, 101, 16);
        this.textGold.setCaretColor(Color.white);
        this.textGold.setOpaque(false);
        this.textGold.setBorder(BorderFactory.createEmptyBorder());
        this.textGold.setForeground(Color.white);
        this.textGold.setFont(UIUtils.TEXT_FONT1);
        this.textGold.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (PalacePKJpanel.this.putChooseType[PalacePKJpanel.this.putTextType]) {
                    PalacePKJpanel.this.fundString[PalacePKJpanel.this.putTextType] = (PalacePKJpanel.this.textGold.getText().equals("") ? "0" : PalacePKJpanel.this.textGold.getText());
                    PalacePKJpanel.this.putFundLab[PalacePKJpanel.this.putTextType].setText(PalacePKJpanel.this.unitString[PalacePKJpanel.this.putTextType + 3] + PalacePKJpanel.this.fundString[PalacePKJpanel.this.putTextType] + PalacePKJpanel.this.unitString[PalacePKJpanel.this.putTextType]);
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (PalacePKJpanel.this.putChooseType[PalacePKJpanel.this.putTextType]) {
                    PalacePKJpanel.this.fundString[PalacePKJpanel.this.putTextType] = (PalacePKJpanel.this.textGold.getText().equals("") ? "0" : PalacePKJpanel.this.textGold.getText());
                    PalacePKJpanel.this.putFundLab[PalacePKJpanel.this.putTextType].setText(PalacePKJpanel.this.unitString[PalacePKJpanel.this.putTextType + 3] + PalacePKJpanel.this.fundString[PalacePKJpanel.this.putTextType] + PalacePKJpanel.this.unitString[PalacePKJpanel.this.putTextType]);
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.textGold.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int charstr = e.getKeyChar();
                if (charstr < 48 || charstr > 57) {
                    e.consume();
                    return;
                }
                String str = PalacePKJpanel.this.textGold.getText();
                if (str.length() == 0) {
                    PalacePKJpanel.this.textGold.setText("");
                }
                if (str.length() == 1 && Long.parseLong(str) == 0L) {
                    PalacePKJpanel.this.textGold.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.add(this.textGold);
        this.add(this.getPutIntoLab());
        this.add(this.getChallengeLab());
        this.add(this.getSendBellLab());
        this.getPutFundLab();
        (this.workshopBtn = new WorkshopBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "确定", 12, this)).setBounds(101, 346, 99, 24);
        this.add(this.workshopBtn);
        this.changeView(0);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, 442, 385, this);
        g.drawImage(this.iconLeft.getImage(), 0, 0, 43, 339, this);
        if (this.winnerType == 0) {
            if (this.pkNameImg == null) {
                this.pkNameImg = CutButtonImage.getImage("inkImg/background/S122.png", 132, 19);
            }
            g.drawImage(this.pkNameImg.getImage(), 203, 27, 132, 19, this);
        }
    }
    
    public void changeView(int type) {
        this.winnerType = type;
        this.clearViewData();
        if (this.winnerType == 0) {
            this.changeViewPalaceReception(false);
            this.changeViewWinnerReception(false);
            this.changeViewWinner(false);
            this.changeViewPalace(true);
        }
        else if (this.winnerType == 1) {
            this.changeViewPalace(false);
            this.changeViewPalaceReception(false);
            this.changeViewWinnerReception(false);
            this.changeViewWinner(true);
        }
        else if (this.winnerType == 2) {
            this.changeViewPalace(false);
            this.changeViewWinner(false);
            this.changeViewWinnerReception(false);
            this.changeViewPalaceReception(true);
        }
        else if (this.winnerType == 3) {
            this.changeViewPalace(false);
            this.changeViewWinner(false);
            this.changeViewPalaceReception(false);
            this.changeViewWinnerReception(true);
        }
    }
    
    public void changeViewPalace(boolean type) {
        if (type) {
            this.iconLeft = CutButtonImage.getImage("inkImg/background/S120.png", -1, -1);
            this.remarkLab.setText("输入决斗对象名字:");
            this.workshopBtn.setText("确认");
            this.cancelBtn.setText("取消");
            for (int i = 0; i < this.putFundLab.length; ++i) {
                this.putFundLab[i].setText(this.unitString[i + 3] + this.fundString[i] + this.unitString[i]);
            }
        }
        this.textName.setVisible(type);
        this.textGold.setEditable(type);
    }
    
    public void changeViewPalaceReception(boolean type) {
        if (type) {
            this.iconLeft = CutButtonImage.getImage("inkImg/background/S120.png", -1, -1);
            this.workshopBtn.setText("接受挑战");
            this.cancelBtn.setText("认怂投降");
            this.putFundLab[0].setText("下注金钱：" + this.fundString[0] + "金钱");
            this.putFundLab[1].setText("下注仙玉：" + this.fundString[1] + "仙玉");
            this.putFundLab[2].setText("下注经验：" + this.fundString[2] + "经验");
            this.putIntoLab.setIcon(null);
            this.challengeLab.setIcon(null);
            this.sendBellLab.setIcon(null);
            this.putIntoBool = !type;
            this.challengeBool = !type;
            this.sendBellBool = !type;
        }
        this.textGold.setEditable(!type);
        this.textName.setVisible(!type);
    }
    
    public void changeViewWinner(boolean type) {
        if (type) {
            this.iconLeft = CutButtonImage.getImage("inkImg/background/S121.png", -1, -1);
            this.remarkLab.setText("挑战成功者升为擂主");
            this.workshopBtn.setText("确认");
            this.cancelBtn.setText("取消");
            for (int i = 0; i < this.putFundLab.length; ++i) {
                this.putFundLab[i].setText(this.unitString[i + 3] + this.fundString[i] + this.unitString[i]);
            }
        }
        this.textName.setVisible(!type);
        this.textGold.setEditable(type);
    }
    
    public void changeViewWinnerReception(boolean type) {
        if (type) {
            this.iconLeft = CutButtonImage.getImage("inkImg/background/S121.png", -1, -1);
            this.remarkLab.setText("挑战成功者升为擂主");
            this.workshopBtn.setText("接受挑战");
            this.cancelBtn.setText("认怂投降");
            this.putFundLab[0].setText("下注金钱：" + this.fundString[0] + "金钱");
            this.putFundLab[1].setText("下注仙玉：" + this.fundString[1] + "仙玉");
            this.putFundLab[2].setText("下注经验：" + this.fundString[2] + "经验");
            this.putIntoLab.setIcon(null);
            this.challengeLab.setIcon(null);
            this.sendBellLab.setIcon(null);
            this.putIntoBool = !type;
            this.challengeBool = !type;
            this.sendBellBool = !type;
        }
        this.textName.setVisible(!type);
        this.textGold.setEditable(!type);
    }
    
    public void clearViewData() {
        for (int i = 0; i < this.fundString.length; ++i) {
            this.fundString[i] = "0";
        }
        this.sendBelTextArea.setText("");
        this.textGold.setText("");
        this.textName.setText("");
        for (int i = 0; i < this.putLab.length; ++i) {
            this.putLab[i].setIcon(null);
            this.putChooseType[i] = false;
        }
    }
    
    public void changeViewReception(PalacePkBean palacePkBean) {
        this.palacePkBean = palacePkBean;
        this.changeView(2);
        String username = palacePkBean.getUsername();
        BigDecimal gold = palacePkBean.getGold();
        BigDecimal xianyu = palacePkBean.getXianyu();
        BigDecimal exp = palacePkBean.getExp();
        if (gold == null) {
            gold = new BigDecimal(0);
        }
        if (xianyu == null) {
            xianyu = new BigDecimal(0);
        }
        if (exp == null) {
            exp = new BigDecimal(0);
        }
        this.remarkLab.setText("【" + username + "】玩家向您下达了战书");
        this.putFundLab[0].setText("下注金钱:" + gold + "两");
        this.fundString[0] = gold.toString();
        if (gold.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[0].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[0] = true;
        }
        else {
            this.putLab[0].setIcon(null);
            this.putChooseType[0] = false;
        }
        this.putFundLab[1].setText("下注仙玉:" + xianyu + "仙玉");
        this.fundString[1] = xianyu.toString();
        if (xianyu.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[1].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[1] = true;
        }
        else {
            this.putLab[1].setIcon(null);
            this.putChooseType[2] = false;
        }
        this.putFundLab[2].setText("下注经验:" + exp + "经验");
        this.fundString[2] = exp.toString();
        if (exp.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[2].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[1] = true;
        }
        else {
            this.putLab[2].setIcon(null);
            this.putChooseType[2] = false;
        }
        for (int i = 0; i < this.putChooseType.length; ++i) {
            if (this.putChooseType[i]) {
                this.putIntoBool = true;
                this.putIntoLab.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
            }
        }
    }
    
    public void changeViewWinnerReception(PalacePkBean palacePkBean) {
        this.palacePkBean = palacePkBean;
        this.changeView(3);
        String username = palacePkBean.getUsername();
        BigDecimal gold = palacePkBean.getGold();
        BigDecimal xianyu = palacePkBean.getXianyu();
        BigDecimal exp = palacePkBean.getExp();
        if (gold == null) {
            gold = new BigDecimal(0);
        }
        if (xianyu == null) {
            xianyu = new BigDecimal(0);
        }
        if (exp == null) {
            exp = new BigDecimal(0);
        }
        this.putFundLab[0].setText("下注金钱:" + gold + "两");
        this.fundString[0] = gold.toString();
        if (gold.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[0].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[0] = true;
        }
        else {
            this.putLab[0].setIcon(null);
            this.putChooseType[0] = false;
        }
        this.putFundLab[1].setText("下注仙玉:" + xianyu + "仙玉");
        this.fundString[1] = xianyu.toString();
        if (xianyu.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[1].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[1] = true;
        }
        else {
            this.putLab[1].setIcon(null);
            this.putChooseType[2] = false;
        }
        this.putFundLab[2].setText("下注经验:" + exp + "经验");
        this.fundString[2] = exp.toString();
        if (exp.compareTo(new BigDecimal(0)) > 0) {
            this.putLab[2].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            this.putChooseType[1] = true;
        }
        else {
            this.putLab[2].setIcon(null);
            this.putChooseType[2] = false;
        }
        for (int i = 0; i < this.putChooseType.length; ++i) {
            if (this.putChooseType[i]) {
                this.putIntoBool = true;
                this.putIntoLab.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
            }
        }
    }
    
    public JTextField getTextName() {
        return this.textName;
    }
    
    public void setTextName(JTextField textName) {
        this.textName = textName;
    }
    
    public JTextField getTextGold() {
        return this.textGold;
    }
    
    public void setTextGold(JTextField textGold) {
        this.textGold = textGold;
    }
    
    public WorkshopBtn getWorkshopBtn() {
        return this.workshopBtn;
    }
    
    public void setWorkshopBtn(WorkshopBtn workshopBtn) {
        this.workshopBtn = workshopBtn;
    }
    
    public JLabel[] getPutLab() {
        if (this.putLab == null) {
            this.putLab = new JLabel[3];
            for (int i = 0; i < this.putLab.length; ++i) {
                (this.putLab[i] = new JLabel("", 0)).setBounds(86 + i % 3 * 81 + ((i == 1) ? -1 : 0), 88, 17, 17);
                this.putLab[i].addMouseListener(new PkPutLabMouseListener(i, this));
                this.add(this.putLab[i]);
            }
        }
        return this.putLab;
    }
    
    public void setPutLab(JLabel[] putLab) {
        this.putLab = putLab;
    }
    
    public WorkshopBtn getCancelBtn() {
        if (this.cancelBtn == null) {
            (this.cancelBtn = new WorkshopBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "取消", 14, this)).setBounds(241, 346, 99, 24);
        }
        return this.cancelBtn;
    }
    
    public void setCancelBtn(WorkshopBtn cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
    
    public JLabel getPutIntoLab() {
        if (this.putIntoLab == null) {
            (this.putIntoLab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
            this.putIntoLab.setBounds(68, 66, 15, 15);
            this.putIntoLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (PalacePKJpanel.this.winnerType == 2 || PalacePKJpanel.this.winnerType == 3) {
                        return;
                    }
                    if (PalacePKJpanel.this.putIntoBool) {
                        PalacePKJpanel.this.putIntoLab.setIcon(null);
                        for (int i = 0; i < PalacePKJpanel.this.putLab.length; ++i) {
                            PalacePKJpanel.this.putLab[i].setIcon(null);
                            PalacePKJpanel.this.putChooseType[i] = false;
                            PalacePKJpanel.this.fundString[i] = "0";
                            PalacePKJpanel.this.putFundLab[i].setText(PalacePKJpanel.this.unitString[i + 3] + PalacePKJpanel.this.fundString[i] + PalacePKJpanel.this.unitString[i]);
                        }
                        PalacePKJpanel.this.textGold.setText("");
                        PalacePKJpanel.this.textGold.setEditable(false);
                    }
                    else {
                        PalacePKJpanel.this.putIntoLab.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
                        PalacePKJpanel.this.textGold.setEditable(true);
                    }
                    PalacePKJpanel.this.putIntoBool = !PalacePKJpanel.this.putIntoBool;
                }
            });
        }
        return this.putIntoLab;
    }
    
    public void setPutIntoLab(JLabel putIntoLab) {
        this.putIntoLab = putIntoLab;
    }
    
    public JLabel getChallengeLab() {
        if (this.challengeLab == null) {
            (this.challengeLab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
            this.challengeLab.setBounds(68, 159, 15, 15);
            this.challengeLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (PalacePKJpanel.this.challengeBool) {
                        PalacePKJpanel.this.challengeLab.setIcon(null);
                    }
                    else {
                        PalacePKJpanel.this.challengeLab.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
                    }
                    PalacePKJpanel.this.challengeBool = !PalacePKJpanel.this.challengeBool;
                }
            });
        }
        return this.challengeLab;
    }
    
    public void setChallengeLab(JLabel challengeLab) {
        this.challengeLab = challengeLab;
    }
    
    public JLabel getSendBellLab() {
        if (this.sendBellLab == null) {
            (this.sendBellLab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
            this.sendBellLab.setBounds(68, 182, 15, 15);
            this.sendBellLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (PalacePKJpanel.this.sendBellBool) {
                        PalacePKJpanel.this.sendBellLab.setIcon(null);
                    }
                    else {
                        PalacePKJpanel.this.sendBellLab.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", 15, 15));
                    }
                    PalacePKJpanel.this.sendBellBool = !PalacePKJpanel.this.sendBellBool;
                }
            });
        }
        return this.sendBellLab;
    }
    
    public void setSendBellLab(JLabel sendBellLab) {
        this.sendBellLab = sendBellLab;
    }
    
    public JLabel[] getPutFundLab() {
        if (this.putFundLab == null) {
            this.putFundLab = new JLabel[3];
            for (int i = 0; i < this.putFundLab.length; ++i) {
                (this.putFundLab[i] = new JLabel()).setBounds(232, 108 + i % 3 * 17, 175, 17);
                this.putFundLab[i].setFont(UIUtils.TEXT_FONT);
                this.putFundLab[i].setForeground(Color.red);
                this.add(this.putFundLab[i]);
                if (i == 0) {
                    this.putFundLab[i].setText("消耗金钱：0两");
                }
                if (i == 1) {
                    this.putFundLab[i].setText("消耗仙玉：0仙玉");
                }
                if (i == 2) {
                    this.putFundLab[i].setText("消耗经验：0经验");
                }
            }
        }
        return this.putFundLab;
    }
    
    public void setPutFundLab(JLabel[] putFundLab) {
        this.putFundLab = putFundLab;
    }
    
    public boolean[] getPutChooseType() {
        if (this.putChooseType == null) {
            this.putChooseType = new boolean[3];
        }
        return this.putChooseType;
    }
    
    public void setPutChooseType(boolean[] putChooseType) {
        this.putChooseType = putChooseType;
    }
    
    public int getPutTextType() {
        return this.putTextType;
    }
    
    public void setPutTextType(int putTextType) {
        this.putTextType = putTextType;
    }
    
    public JLabel getPutUnit() {
        if (this.putUnit == null) {
            (this.putUnit = new JLabel()).setOpaque(false);
            this.putUnit.setBounds(191, 111, 60, 17);
            this.putUnit.setForeground(Color.black);
        }
        return this.putUnit;
    }
    
    public void setPutUnit(JLabel putUnit) {
        this.putUnit = putUnit;
    }
    
    public String[] getUnitString() {
        return this.unitString;
    }
    
    public void setUnitString(String[] unitString) {
        this.unitString = unitString;
    }
    
    public String[] getFundString() {
        if (this.fundString == null) {
            this.fundString = new String[3];
            for (int i = 0; i < this.fundString.length; ++i) {
                this.fundString[i] = "0";
            }
        }
        return this.fundString;
    }
    
    public void setFundString(String[] fundString) {
        this.fundString = fundString;
    }
    
    public LimitNumTextArea getSendBelTextArea() {
        if (this.sendBelTextArea == null) {
            (this.sendBelTextArea = new LimitNumTextArea(50)).setLineWrap(true);
            this.sendBelTextArea.setOpaque(false);
            this.sendBelTextArea.setForeground(Color.white);
            this.sendBelTextArea.setBounds(78, 204, 321, 46);
            this.sendBelTextArea.setCaretColor(Color.white);
        }
        return this.sendBelTextArea;
    }
    
    public void setSendBelTextArea(LimitNumTextArea sendBelTextArea) {
        this.sendBelTextArea = sendBelTextArea;
    }
    
    public boolean isPutIntoBool() {
        return this.putIntoBool;
    }
    
    public void setPutIntoBool(boolean putIntoBool) {
        this.putIntoBool = putIntoBool;
    }
    
    public boolean isChallengeBool() {
        return this.challengeBool;
    }
    
    public void setChallengeBool(boolean challengeBool) {
        this.challengeBool = challengeBool;
    }
    
    public boolean isSendBellBool() {
        return this.sendBellBool;
    }
    
    public void setSendBellBool(boolean sendBellBool) {
        this.sendBellBool = sendBellBool;
    }
    
    public int getWinnerType() {
        return this.winnerType;
    }
    
    public void setWinnerType(int winnerType) {
        this.winnerType = winnerType;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JLabel getRemarkLab() {
        if (this.remarkLab == null) {
            (this.remarkLab = new JLabel()).setBounds(56, 26, 350, 16);
            this.remarkLab.setForeground(Color.black);
            this.remarkLab.setFont(UIUtils.TEXT_HY16);
            this.add(this.remarkLab);
        }
        return this.remarkLab;
    }
    
    public void setRemarkLab(JLabel remarkLab) {
        this.remarkLab = remarkLab;
    }
    
    public ImageIcon getPkNameImg() {
        return this.pkNameImg;
    }
    
    public void setPkNameImg(ImageIcon pkNameImg) {
        this.pkNameImg = pkNameImg;
    }
    
    public PalacePkBean getPalacePkBean() {
        return this.palacePkBean;
    }
    
    public void setPalacePkBean(PalacePkBean palacePkBean) {
        this.palacePkBean = palacePkBean;
    }
}
