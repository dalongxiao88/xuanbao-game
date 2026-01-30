package org.come.equipmentSwitching;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class EquipmentSwitchingInputJpanel extends JPanel
{
    private EquitmentSwitchingInputBtn codeBtn1;
    private EquitmentSwitchingInputBtn codeBtn2;
    private JTextField textCode;
    private JLabel titleJLabel;
    private JLabel MrLin;
    private int caozuo;
    private int type;
    private BigDecimal rgid;
    String xz2;
    String xz3;
    String xz4;
    private ImageIcon icon;
    private ImageIcon s;
    private ImageIcon h;
    
    public EquipmentSwitchingInputJpanel() {
        this.caozuo = 0;
        this.xz2 = "";
        this.xz3 = "";
        this.xz4 = "";
        this.setPreferredSize(new Dimension(250, 170));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.textCode = new JTextField()).setBounds(47, 58, 161, 25);
        this.textCode.setFont(UIUtils.TEXT_FONT1);
        this.textCode.setOpaque(false);
        this.textCode.setBorder(BorderFactory.createEmptyBorder());
        this.textCode.setCaretColor(Color.white);
        this.textCode.setForeground(Color.white);
        this.textCode.addFocusListener(new JTextFieldHintListener("请输入套装名称", this.textCode));
        this.add(this.textCode);
        (this.codeBtn1 = new EquitmentSwitchingInputBtn("inkImg/hongmu/6026.png", 1, "保存", this)).setBounds(100, 110, 60, 26);
        this.add(this.codeBtn1);
        (this.codeBtn2 = new EquitmentSwitchingInputBtn("inkImg/hongmu/60_26.png", 1, "取消", this, 1)).setBounds(120, 90, 60, 26);
        (this.titleJLabel = new JLabel()).setForeground(Color.YELLOW);
        this.titleJLabel.setFont(UIUtils.TEXT_FONT);
        this.titleJLabel.setVerticalTextPosition(0);
        this.titleJLabel.setBounds(200, 10, 134, 20);
        this.add(this.titleJLabel);
        (this.MrLin = new JLabel()).setForeground(Color.YELLOW);
        this.MrLin.setFont(UIUtils.TEXT_FONT);
        this.MrLin.setVisible(false);
        this.MrLin.setVerticalTextPosition(0);
        this.MrLin.setBounds(200, 10, 134, 20);
        this.add(this.MrLin);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!MyIsif.getStyle().equals("水墨")) {
            if (this.s == null) {
                this.s = new ImageIcon("inkimg/hongmu/tzgm.png");
            }
            g.drawImage(this.s.getImage(), 0, 0, 250, 170, this);
        }
        else {
            if (this.h == null) {
                this.h = new ImageIcon("inkImg/background/tzhm.png");
            }
            g.drawImage(this.h.getImage(), 0, 0, 250, 170, this);
        }
        if (this.xz2 != null) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.WHITE);
            g.drawString(this.xz2, 5, 40);
            if (this.xz3 != null) {
                g.drawString(this.xz3, 5, 60);
            }
        }
    }
    
    public JTextField getTextCode() {
        return this.textCode;
    }
    
    public void setTextCode(JTextField textCode) {
        this.textCode = textCode;
    }
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public JLabel getTitleJLabel() {
        return this.titleJLabel;
    }
    
    public void setTitleJLabel(JLabel titleJLabel) {
        this.titleJLabel = titleJLabel;
    }
    
    public EquitmentSwitchingInputBtn getCodeBtn1() {
        return this.codeBtn1;
    }
    
    public void setCodeBtn1(EquitmentSwitchingInputBtn codeBtn1) {
        this.codeBtn1 = codeBtn1;
    }
    
    public EquitmentSwitchingInputBtn getCodeBtn2() {
        return this.codeBtn2;
    }
    
    public void setCodeBtn2(EquitmentSwitchingInputBtn codeBtn2) {
        this.codeBtn2 = codeBtn2;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getXz2() {
        return this.xz2;
    }
    
    public void setXz2(String xz2) {
        this.xz2 = xz2;
    }
    
    public String getXz3() {
        return this.xz3;
    }
    
    public void setXz3(String xz3) {
        this.xz3 = xz3;
    }
    
    public String getXz4() {
        return this.xz4;
    }
    
    public void setXz4(String xz4) {
        this.xz4 = xz4;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JLabel getMrLin() {
        return this.MrLin;
    }
    
    public void setMrLin(JLabel mrLin) {
        this.MrLin = mrLin;
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    static class JTextFieldHintListener implements FocusListener
    {
        private String mHintText;
        private JTextField mTextField;
        
        public JTextFieldHintListener(String hintText, JTextField textField) {
            this.mHintText = hintText;
            this.mTextField = textField;
        }
        
        @Override
        public void focusGained(FocusEvent e) {
            String temp = this.mTextField.getText();
            if (temp.equals(this.mHintText)) {
                this.mTextField.setText("");
                this.mTextField.setForeground(Color.white);
            }
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            String temp = this.mTextField.getText();
            if (temp.equals("")) {
                this.mTextField.setForeground(Color.GRAY);
                this.mTextField.setText(this.mHintText);
            }
        }
    }
}
