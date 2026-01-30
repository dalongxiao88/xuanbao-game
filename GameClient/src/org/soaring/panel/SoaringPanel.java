package org.soaring.panel;

import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.until.AnalysisString;
import java.math.RoundingMode;
import java.awt.Color;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import javax.swing.JLabel;
import org.soaring.btn.SoaringBtn;
import javax.swing.JPanel;

public class SoaringPanel extends JPanel
{
    private SoaringBtn transformation;
    private SoaringBtn upgrade;
    private SoaringBtn exchange;
    private JLabel strip;
    private JLabel experience;
    private JLabel havePoint;
    private JLabel level;
    private JLabel upPoint;
    private JLabel convertibilityPoints;
    private JLabel exchangePoint;
    private BigDecimal maxExprience;
    private ImageIcon icon;
    
    public SoaringPanel() {
        this.maxExprience = new BigDecimal("10000000000");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(332, 407));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 81);
            offBtn.setBounds(295, 10, 25, 25);
            this.add(offBtn);
            this.transformation = new SoaringBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "转换", 1, this);
            this.upgrade = new SoaringBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "升级", 2, this);
            this.exchange = new SoaringBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "兑换", 3, this);
            this.strip = new JLabel();
            this.experience = new JLabel("123132132");
            this.havePoint = new JLabel("2/30");
            this.level = new JLabel("150级");
            this.upPoint = new JLabel("6");
            this.convertibilityPoints = new JLabel("10/60");
            this.exchangePoint = new JLabel("10");
            Font f = new Font("宋体", 0, 15);
            this.transformation.setBorder(null);
            this.upgrade.setBorder(null);
            this.exchange.setBorder(null);
            this.transformation.setOpaque(false);
            this.upgrade.setOpaque(false);
            this.exchange.setOpaque(false);
            this.strip.setOpaque(false);
            this.havePoint.setOpaque(false);
            this.level.setOpaque(false);
            this.upPoint.setOpaque(false);
            this.convertibilityPoints.setOpaque(false);
            this.exchangePoint.setOpaque(false);
            this.transformation.setBackground(null);
            this.strip.setIcon(new ImageIcon("img/soaring/经验w152,h13px.png"));
            this.experience.setHorizontalAlignment(0);
            this.experience.setForeground(Color.white);
            this.experience.setFont(f);
            this.havePoint.setForeground(Color.white);
            this.havePoint.setFont(f);
            this.level.setForeground(Color.white);
            this.level.setFont(f);
            this.upPoint.setForeground(Color.white);
            this.upPoint.setFont(f);
            this.exchangePoint.setForeground(Color.white);
            this.exchangePoint.setFont(f);
            this.convertibilityPoints.setForeground(Color.white);
            this.convertibilityPoints.setFont(f);
            this.transformation.setBounds(230, 66, 59, 24);
            this.upgrade.setBounds(150, 186, 59, 24);
            this.exchange.setBounds(150, 336, 59, 24);
            this.strip.setBounds(134, 34, 152, 23);
            this.experience.setBounds(134, 34, 152, 19);
            this.havePoint.setBounds(148, 66, 70, 19);
            this.level.setBounds(195, 131, 80, 19);
            this.upPoint.setBounds(195, 155, 80, 19);
            this.convertibilityPoints.setBounds(208, 282, 50, 19);
            this.exchangePoint.setBounds(208, 308, 50, 19);
            this.add(this.transformation);
            this.add(this.upgrade);
            this.add(this.exchange);
            this.add(this.experience);
            this.add(this.strip);
            this.add(this.havePoint);
            this.add(this.level);
            this.add(this.upPoint);
            this.add(this.convertibilityPoints);
            this.add(this.exchangePoint);
        }
        else {
            this.setPreferredSize(new Dimension(302, 405));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 81);
            offBtn.setBounds(282, 0, 25, 25);
            this.add(offBtn);
            this.transformation = new SoaringBtn("img/soaring/转化w60,h78px.png", 1, UIUtils.COLOR_BTNPUTONG, "", 1, this);
            this.upgrade = new SoaringBtn("img/soaring/升级w60,h78px.png", 1, UIUtils.COLOR_BTNPUTONG, "", 2, this);
            this.exchange = new SoaringBtn("img/soaring/兑换w60,h78px.png", 1, UIUtils.COLOR_BTNPUTONG, "", 3, this);
            this.strip = new JLabel();
            this.experience = new JLabel("123132132");
            this.havePoint = new JLabel("2/30");
            this.level = new JLabel("150级");
            this.upPoint = new JLabel("6");
            this.convertibilityPoints = new JLabel("10/60");
            this.exchangePoint = new JLabel("10");
            Font f = new Font("宋体", 0, 15);
            this.transformation.setBorder(null);
            this.upgrade.setBorder(null);
            this.exchange.setBorder(null);
            this.transformation.setOpaque(false);
            this.upgrade.setOpaque(false);
            this.exchange.setOpaque(false);
            this.strip.setOpaque(false);
            this.havePoint.setOpaque(false);
            this.level.setOpaque(false);
            this.upPoint.setOpaque(false);
            this.convertibilityPoints.setOpaque(false);
            this.exchangePoint.setOpaque(false);
            this.transformation.setBackground(null);
            this.strip.setIcon(new ImageIcon("img/soaring/经验w152,h13px.png"));
            this.experience.setHorizontalAlignment(0);
            this.experience.setForeground(Color.white);
            this.experience.setFont(f);
            this.havePoint.setForeground(Color.white);
            this.havePoint.setFont(f);
            this.level.setForeground(Color.white);
            this.level.setFont(f);
            this.upPoint.setForeground(Color.white);
            this.upPoint.setFont(f);
            this.exchangePoint.setForeground(Color.white);
            this.exchangePoint.setFont(f);
            this.convertibilityPoints.setForeground(Color.white);
            this.convertibilityPoints.setFont(f);
            this.transformation.setBounds(205, 80, 60, 26);
            this.upgrade.setBounds(125, 200, 60, 26);
            this.exchange.setBounds(125, 330, 60, 26);
            this.strip.setBounds(109, 46, 152, 19);
            this.experience.setBounds(109, 46, 152, 19);
            this.havePoint.setBounds(123, 80, 70, 19);
            this.level.setBounds(170, 145, 80, 19);
            this.upPoint.setBounds(170, 169, 80, 19);
            this.convertibilityPoints.setBounds(186, 276, 50, 19);
            this.exchangePoint.setBounds(186, 300, 50, 19);
            this.add(this.transformation);
            this.add(this.upgrade);
            this.add(this.exchange);
            this.add(this.experience);
            this.add(this.strip);
            this.add(this.havePoint);
            this.add(this.level);
            this.add(this.upPoint);
            this.add(this.convertibilityPoints);
            this.add(this.exchangePoint);
        }
    }
    
    public void setWieth(BigDecimal decimal) {
        BigDecimal divide = decimal.divide(this.maxExprience, 2, RoundingMode.HALF_DOWN);
        BigDecimal multiply = divide.multiply(new BigDecimal("152"));
        if (divide.compareTo(new BigDecimal(0)) <= 0) {
            this.getStrip().setSize(1, 19);
        }
        else if (divide.compareTo(new BigDecimal(1)) <= 0) {
            this.getStrip().setSize(multiply.intValue(), 19);
        }
        else {
            this.getStrip().setSize(152, 19);
        }
    }
    
    public void SoaringExprience(int level, BigDecimal experience, int convertibilityPoints, int havePoint) {
        this.setWieth(experience);
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getExperience().setText(experience + "");
        int lvlint = AnalysisString.lvlint(level);
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getLevel().setText(lvlint + "级");
        int need = 0;
        if (lvlint < 150) {
            lvlint = 30;
            need = 6;
        }
        else {
            need = lvlint - 144;
            lvlint = (lvlint - 144) * 5;
        }
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getUpPoint().setText(need + "");
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getHavePoint().setText(havePoint + "/" + lvlint);
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getConvertibilityPoints().setText(convertibilityPoints + "/" + 60);
        SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().getExchangePoint().setText(convertibilityPoints + 1 + "");
        FormsManagement.showForm(81);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintChildren(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S41.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 332, 407, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/soaring/转换经验w307,h407px.png");
            }
            g.drawImage(this.icon.getImage(), -3, 0, 307, 407, this);
        }
    }
    
    public SoaringBtn getTransformation() {
        return this.transformation;
    }
    
    public void setTransformation(SoaringBtn transformation) {
        this.transformation = transformation;
    }
    
    public SoaringBtn getUpgrade() {
        return this.upgrade;
    }
    
    public void setUpgrade(SoaringBtn upgrade) {
        this.upgrade = upgrade;
    }
    
    public SoaringBtn getExchange() {
        return this.exchange;
    }
    
    public void setExchange(SoaringBtn exchange) {
        this.exchange = exchange;
    }
    
    public JLabel getStrip() {
        return this.strip;
    }
    
    public void setStrip(JLabel strip) {
        this.strip = strip;
    }
    
    public JLabel getExperience() {
        return this.experience;
    }
    
    public void setExperience(JLabel experience) {
        this.experience = experience;
    }
    
    public JLabel getHavePoint() {
        return this.havePoint;
    }
    
    public void setHavePoint(JLabel havePoint) {
        this.havePoint = havePoint;
    }
    
    public JLabel getLevel() {
        return this.level;
    }
    
    public void setLevel(JLabel level) {
        this.level = level;
    }
    
    public JLabel getUpPoint() {
        return this.upPoint;
    }
    
    public void setUpPoint(JLabel upPoint) {
        this.upPoint = upPoint;
    }
    
    public JLabel getConvertibilityPoints() {
        return this.convertibilityPoints;
    }
    
    public void setConvertibilityPoints(JLabel convertibilityPoints) {
        this.convertibilityPoints = convertibilityPoints;
    }
    
    public JLabel getExchangePoint() {
        return this.exchangePoint;
    }
    
    public void setExchangePoint(JLabel exchangePoint) {
        this.exchangePoint = exchangePoint;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
