package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.TrueFeedbackBtn;
import javax.swing.JPanel;

public class TrueFeedbackMainJpanel extends JPanel
{
    private TrueFeedbackCardJPanel trueFeedbackCardJPanel;
    private TrueFeedbackBtn btnAddRecharge;
    private TrueFeedbackBtn btntimeSummon;
    private TrueFeedbackBtn btnLottey;
    private ImageIcon icon;
    
    public TrueFeedbackMainJpanel() {
        this.setPreferredSize(new Dimension(502, 519));
        this.setOpaque(false);
        this.setLayout(null);
        (this.trueFeedbackCardJPanel = new TrueFeedbackCardJPanel()).setBounds(48, 63, 430, 450);
        this.add(this.trueFeedbackCardJPanel);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 109);
        offBtn.setBounds(465, 10, 25, 25);
        this.add(offBtn);
        this.getBtnAddRecharge();
        this.getBtnLottey();
        this.getBtntimeSummon();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S193.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, null);
    }
    
    public TrueFeedbackCardJPanel getTrueFeedbackCardJPanel() {
        return this.trueFeedbackCardJPanel;
    }
    
    public void setTrueFeedbackCardJPanel(TrueFeedbackCardJPanel trueFeedbackCardJPanel) {
        this.trueFeedbackCardJPanel = trueFeedbackCardJPanel;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public TrueFeedbackBtn getBtnAddRecharge() {
        if (this.btnAddRecharge == null) {
            (this.btnAddRecharge = new TrueFeedbackBtn("inkImg/button/B318.png", 1, 0, this)).setBounds(54, 27, 100, 35);
            this.add(this.btnAddRecharge);
        }
        return this.btnAddRecharge;
    }
    
    public void setBtnAddRecharge(TrueFeedbackBtn btnAddRecharge) {
        this.btnAddRecharge = btnAddRecharge;
    }
    
    public TrueFeedbackBtn getBtntimeSummon() {
        if (this.btntimeSummon == null) {
            (this.btntimeSummon = new TrueFeedbackBtn("inkImg/button/B321.png", 1, 1, this)).setBounds(156, 27, 100, 35);
            this.add(this.btntimeSummon);
        }
        return this.btntimeSummon;
    }
    
    public void setBtntimeSummon(TrueFeedbackBtn btntimeSummon) {
        this.btntimeSummon = btntimeSummon;
    }
    
    public TrueFeedbackBtn getBtnLottey() {
        if (this.btnLottey == null) {
            (this.btnLottey = new TrueFeedbackBtn("inkImg/button/B319.png", 1, 2, this)).setBounds(258, 27, 100, 35);
            this.add(this.btnLottey);
        }
        return this.btnLottey;
    }
    
    public void setBtnLottey(TrueFeedbackBtn btnLottey) {
        this.btnLottey = btnLottey;
    }
}
