package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.tool.btn.TextBtn;
import com.tool.btn.CommonBtn;
import com.tool.btn.OptionUncheckBtn;
import javax.swing.JPanel;

public class FundBuyJpanel extends JPanel
{
    private OptionUncheckBtn thridFund;
    private OptionUncheckBtn sixthFund;
    private OptionUncheckBtn ninethFund;
    private CommonBtn upPage;
    private CommonBtn downPage;
    private TextBtn shouYe;
    private TextBtn moYe;
    private String type;
    private int maxPage;
    private int nowpage;
    private JLabel labpage;
    private JTextArea fundRule;
    private String fundRuleText;
    private FundBuyGoodsJpanel[] detail;
    private ImageIcon iconBack;
    
    public FundBuyJpanel() {
        this.type = "30";
        this.maxPage = 1;
        this.nowpage = 1;
        this.fundRuleText = "基金规则:基金规则说明基金规则说明基金规则说明基金规则说明基金规则说明基金规则说明基金规则说明基金规则说明基金规则说\r\n明基金规则说明基金规则说明基金规则说明";
        this.detail = new FundBuyGoodsJpanel[12];
        this.setPreferredSize(new Dimension(600, 390));
        this.setLayout(null);
        this.setOpaque(false);
        (this.labpage = new JLabel(this.nowpage + "/" + this.maxPage, 0)).setForeground(Color.white);
        this.labpage.setBounds(335, 378, 58, 17);
        this.labpage.setFont(UIUtils.TEXT_FONT1);
        this.add(this.labpage);
        (this.thridFund = new OptionUncheckBtn("inkImg/button/21.png", 1, "￥30 基金", "30基金", this)).setBounds(52, 9, 100, 26);
        (this.sixthFund = new OptionUncheckBtn("inkImg/button/20.png", 1, "￥60 基金", "60基金", this)).setBounds(152, 9, 100, 26);
        (this.ninethFund = new OptionUncheckBtn("inkImg/button/20.png", 1, "￥90 基金", "90基金", this)).setBounds(252, 9, 100, 26);
        (this.upPage = new CommonBtn("inkImg/button/10.png", 1, "", 4)).setBounds(315, 379, 18, 18);
        (this.downPage = new CommonBtn("inkImg/button/9.png", 1, "", 5)).setBounds(397, 379, 18, 18);
        (this.shouYe = new TextBtn("inkImg/button/2.png", 1, "首页", 1)).setBounds(280, 379, 34, 17);
        (this.moYe = new TextBtn("inkImg/button/2.png", 1, "末页", 2)).setBounds(415, 379, 34, 17);
        (this.fundRule = new JTextArea()).setBounds(70, 40, 550, 76);
        this.fundRule.setText(this.fundRuleText);
        this.fundRule.setOpaque(false);
        this.fundRule.setFont(UIUtils.TEXT_HY19);
        for (int i = 0; i < this.detail.length; ++i) {
            int row = i % 3;
            int col = i / 3;
            (this.detail[i] = new FundBuyGoodsJpanel()).setBounds(77 + row * 182, 143 + col * 52, 178, 48);
            this.add(this.detail[i]);
        }
        this.add(this.fundRule);
        this.add(this.upPage);
        this.add(this.downPage);
        this.add(this.shouYe);
        this.add(this.moYe);
        this.add(this.thridFund);
        this.add(this.sixthFund);
        this.add(this.ninethFund);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/48.png");
        }
        g.drawImage(this.iconBack.getImage(), 53, 32, 600, 340, this);
    }
    
    public OptionUncheckBtn getThridFund() {
        return this.thridFund;
    }
    
    public void setThridFund(OptionUncheckBtn thridFund) {
        this.thridFund = thridFund;
    }
    
    public OptionUncheckBtn getSixthFund() {
        return this.sixthFund;
    }
    
    public void setSixthFund(OptionUncheckBtn sixthFund) {
        this.sixthFund = sixthFund;
    }
    
    public OptionUncheckBtn getNinethFund() {
        return this.ninethFund;
    }
    
    public void setNinethFund(OptionUncheckBtn ninethFund) {
        this.ninethFund = ninethFund;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
