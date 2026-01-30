package org.gemstone.panel;

import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.gemstone.mouseListener.CancelMouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import org.gemstone.btn.GemstoneOperationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GemstoneOperationSynthesisPanel extends JPanel
{
    private JLabel synthesisGemstone;
    private GemstoneOperationBtn synthesisBtn;
    private JLabel[] gemstoneNumber;
    private ImageIcon iconBack;
    private BigDecimal b;
    
    public GemstoneOperationSynthesisPanel() {
        this.b = new BigDecimal("500000");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(310, 260));
            this.setOpaque(false);
            this.setLayout(null);
            this.synthesisBtn = new GemstoneOperationBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "合成", UIUtils.TEXT_HY16, 7);
            this.synthesisGemstone = new JLabel();
            this.gemstoneNumber = new JLabel[2];
            for (int i = 0; i < this.gemstoneNumber.length; ++i) {
                (this.gemstoneNumber[i] = new JLabel()).setOpaque(false);
                this.gemstoneNumber[i].setBounds(84 + i * 79, 30, 53, 51);
                this.gemstoneNumber[i].addMouseListener(new CancelMouseListener(i));
                this.add(this.gemstoneNumber[i]);
            }
            this.synthesisGemstone.setOpaque(false);
            this.synthesisBtn.setBtn(-1);
            this.synthesisBtn.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
            this.synthesisGemstone.setBounds(125, 107, 53, 51);
            this.synthesisBtn.setBounds(125, 228, 59, 24);
            this.add(this.synthesisGemstone);
            this.add(this.synthesisBtn);
        }
        else {
            this.setPreferredSize(new Dimension(310, 260));
            this.setOpaque(false);
            this.setLayout(null);
            this.synthesisBtn = new GemstoneOperationBtn("img/gemstone/合成w60,h78.png", 1, 7);
            this.synthesisGemstone = new JLabel();
            this.gemstoneNumber = new JLabel[2];
            for (int i = 0; i < this.gemstoneNumber.length; ++i) {
                (this.gemstoneNumber[i] = new JLabel()).setOpaque(false);
                this.gemstoneNumber[i].setBounds(84 + i * 79, 30, 53, 51);
                this.gemstoneNumber[i].addMouseListener(new CancelMouseListener(i));
                this.add(this.gemstoneNumber[i]);
            }
            this.synthesisGemstone.setOpaque(false);
            this.synthesisBtn.setBtn(-1);
            this.synthesisBtn.setIcon(CutButtonImage.getImage("img/gemstone/合成w60,h26.png", -1, -1));
            this.synthesisGemstone.setBounds(125, 107, 53, 51);
            this.synthesisBtn.setBounds(125, 228, 60, 26);
            this.add(this.synthesisGemstone);
            this.add(this.synthesisBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B266.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 310, 260, this);
            Util.drawMoney(g, 125, 220);
            Util.drawPrice(g, this.b, 125, 200);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/gemstone/宝石_合成w310,h260，left22，top57px.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 310, 260, this);
            Util.drawMoney(g, 125, 220);
            Util.drawPrice(g, this.b, 125, 200);
        }
    }
    
    public JLabel[] getGemstoneNumber() {
        return this.gemstoneNumber;
    }
    
    public void setGemstoneNumber(JLabel[] gemstoneNumber) {
        this.gemstoneNumber = gemstoneNumber;
    }
    
    public JLabel getSynthesisGemstone() {
        return this.synthesisGemstone;
    }
    
    public void setSynthesisGemstone(JLabel synthesisGemstone) {
        this.synthesisGemstone = synthesisGemstone;
    }
    
    public GemstoneOperationBtn getSynthesisBtn() {
        return this.synthesisBtn;
    }
    
    public void setSynthesisBtn(GemstoneOperationBtn synthesisBtn) {
        this.synthesisBtn = synthesisBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public BigDecimal getB() {
        return this.b;
    }
    
    public void setB(BigDecimal b) {
        this.b = b;
    }
}
