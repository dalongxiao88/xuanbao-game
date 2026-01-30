package org.skill.panel;

import java.awt.FontMetrics;
import java.awt.Font;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import org.skill.btn.SkillPromoteBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SkillPromoteMainPanel extends JPanel
{
    private JLabel currentPoin;
    private JLabel surplusPoint;
    private JLabel characterLevel;
    private JLabel characterState;
    private JLabel trainingProgress;
    private JLabel needExperience;
    private JLabel currentExperience;
    private JLabel needMoney;
    private JLabel teacherGong;
    private SkillPromoteBtn promoteBtn;
    private SkillPromoteBtn exchangeBtn;
    private BigDecimal needMoneyNum;
    private BigDecimal needExperienceNum;
    private ImageIcon icon;
    
    public SkillPromoteMainPanel() {
        this.needMoneyNum = new BigDecimal("2000000");
        this.needExperienceNum = new BigDecimal("5000000");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(402, 465));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 83);
            offBtn.setBounds(339, 10, 25, 25);
            this.add(offBtn);
            this.currentPoin = new JLabel("30");
            this.surplusPoint = new JLabel("30");
            this.characterLevel = new JLabel("3转140级");
            this.characterState = new JLabel("金丹期");
            this.trainingProgress = new JLabel("瓶颈");
            this.needExperience = new JLabel(this.needExperienceNum + "");
            this.currentExperience = new JLabel("1321321321");
            this.needMoney = new JLabel(this.needMoneyNum + "");
            this.teacherGong = new JLabel("- -");
            this.promoteBtn = new SkillPromoteBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "提升修炼", 1, this);
            this.exchangeBtn = new SkillPromoteBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "一键兑换", 2, this);
            this.currentPoin.setBounds(137, 50, 33, 16);
            this.surplusPoint.setBounds(310, 51, 33, 16);
            this.characterLevel.setBounds(173, 127, 141, 16);
            this.characterState.setBounds(173, 157, 141, 16);
            this.trainingProgress.setBounds(173, 188, 141, 17);
            this.needExperience.setBounds(173, 258, 141, 16);
            this.currentExperience.setBounds(173, 289, 141, 16);
            this.needMoney.setBounds(173, 320, 141, 16);
            this.teacherGong.setBounds(173, 382, 141, 16);
            this.promoteBtn.setBounds(200, 415, 100, 26);
            this.exchangeBtn.setBounds(64, 415, 100, 26);
            this.currentPoin.setHorizontalAlignment(0);
            this.surplusPoint.setHorizontalAlignment(0);
            this.characterLevel.setHorizontalAlignment(0);
            this.characterState.setHorizontalAlignment(0);
            this.trainingProgress.setHorizontalAlignment(0);
            this.needExperience.setHorizontalAlignment(0);
            this.currentExperience.setHorizontalAlignment(0);
            this.needMoney.setHorizontalAlignment(0);
            this.teacherGong.setHorizontalAlignment(0);
            this.currentPoin.setForeground(Color.white);
            this.surplusPoint.setForeground(Color.white);
            this.characterLevel.setForeground(Color.white);
            this.characterState.setForeground(Color.white);
            this.trainingProgress.setForeground(Color.white);
            this.needExperience.setForeground(Color.white);
            this.currentExperience.setForeground(Color.white);
            this.needMoney.setForeground(Color.white);
            this.teacherGong.setForeground(Color.white);
            this.currentPoin.setFont(UIUtils.TEXT_FONT1);
            this.surplusPoint.setFont(UIUtils.TEXT_FONT1);
            this.characterLevel.setFont(UIUtils.TEXT_FONT1);
            this.characterState.setFont(UIUtils.TEXT_FONT1);
            this.trainingProgress.setFont(UIUtils.TEXT_FONT1);
            this.needExperience.setFont(UIUtils.TEXT_FONT1);
            this.currentExperience.setFont(UIUtils.TEXT_FONT1);
            this.needMoney.setFont(UIUtils.TEXT_FONT1);
            this.teacherGong.setFont(UIUtils.TEXT_FONT1);
            this.currentPoin.setOpaque(false);
            this.surplusPoint.setOpaque(false);
            this.characterLevel.setOpaque(false);
            this.characterState.setOpaque(false);
            this.trainingProgress.setOpaque(false);
            this.needExperience.setOpaque(false);
            this.currentExperience.setOpaque(false);
            this.needMoney.setOpaque(false);
            this.teacherGong.setOpaque(false);
            this.exchangeBtn.setOpaque(false);
            this.promoteBtn.setOpaque(false);
            this.add(this.currentPoin);
            this.add(this.surplusPoint);
            this.add(this.characterLevel);
            this.add(this.characterState);
            this.add(this.trainingProgress);
            this.add(this.needExperience);
            this.add(this.currentExperience);
            this.add(this.needMoney);
            this.add(this.teacherGong);
            this.add(this.exchangeBtn);
            this.add(this.promoteBtn);
        }
        else {
            this.setPreferredSize(new Dimension(376, 466));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 83);
            offBtn.setBounds(353, 0, 23, 23);
            this.add(offBtn);
            this.currentPoin = new JLabel("30");
            this.surplusPoint = new JLabel("30");
            this.characterLevel = new JLabel("3转140级");
            this.characterState = new JLabel("金丹期");
            this.trainingProgress = new JLabel("瓶颈");
            this.needExperience = new JLabel(this.needExperienceNum + "");
            this.currentExperience = new JLabel("1321321321");
            this.needMoney = new JLabel(this.needMoneyNum + "");
            this.teacherGong = new JLabel("- -");
            this.promoteBtn = new SkillPromoteBtn("img/soaringSkill/提示修炼点w120,h78.png", 1, UIUtils.COLOR_BTNPUTONG, "", 1, this);
            this.exchangeBtn = new SkillPromoteBtn("img/soaringSkill/一键兑换w100,h78.png", 1, UIUtils.COLOR_BTNPUTONG, "", 2, this);
            this.currentPoin.setBounds(122, 62, 35, 18);
            this.surplusPoint.setBounds(308, 63, 35, 18);
            this.characterLevel.setBounds(162, 139, 150, 18);
            this.characterState.setBounds(162, 169, 150, 18);
            this.trainingProgress.setBounds(162, 199, 150, 18);
            this.needExperience.setBounds(162, 268, 150, 18);
            this.currentExperience.setBounds(162, 299, 150, 18);
            this.needMoney.setBounds(162, 330, 150, 18);
            this.promoteBtn.setBounds(180, 400, 120, 26);
            this.exchangeBtn.setBounds(64, 400, 100, 26);
            this.currentPoin.setHorizontalAlignment(0);
            this.surplusPoint.setHorizontalAlignment(0);
            this.characterLevel.setHorizontalAlignment(0);
            this.characterState.setHorizontalAlignment(0);
            this.trainingProgress.setHorizontalAlignment(0);
            this.needExperience.setHorizontalAlignment(0);
            this.currentExperience.setHorizontalAlignment(0);
            this.needMoney.setHorizontalAlignment(0);
            this.teacherGong.setHorizontalAlignment(0);
            this.currentPoin.setForeground(Color.white);
            this.surplusPoint.setForeground(Color.white);
            this.characterLevel.setForeground(Color.white);
            this.characterState.setForeground(Color.white);
            this.trainingProgress.setForeground(Color.white);
            this.needExperience.setForeground(Color.white);
            this.currentExperience.setForeground(Color.white);
            this.needMoney.setForeground(Color.white);
            this.teacherGong.setForeground(Color.white);
            this.currentPoin.setFont(UIUtils.TEXT_FONT1);
            this.surplusPoint.setFont(UIUtils.TEXT_FONT1);
            this.characterLevel.setFont(UIUtils.TEXT_FONT1);
            this.characterState.setFont(UIUtils.TEXT_FONT1);
            this.trainingProgress.setFont(UIUtils.TEXT_FONT1);
            this.needExperience.setFont(UIUtils.TEXT_FONT1);
            this.currentExperience.setFont(UIUtils.TEXT_FONT1);
            this.needMoney.setFont(UIUtils.TEXT_FONT1);
            this.teacherGong.setFont(UIUtils.TEXT_FONT1);
            this.currentPoin.setOpaque(false);
            this.surplusPoint.setOpaque(false);
            this.characterLevel.setOpaque(false);
            this.characterState.setOpaque(false);
            this.trainingProgress.setOpaque(false);
            this.needExperience.setOpaque(false);
            this.currentExperience.setOpaque(false);
            this.needMoney.setOpaque(false);
            this.teacherGong.setOpaque(false);
            this.exchangeBtn.setOpaque(false);
            this.promoteBtn.setOpaque(false);
            this.add(this.currentPoin);
            this.add(this.surplusPoint);
            this.add(this.characterLevel);
            this.add(this.characterState);
            this.add(this.trainingProgress);
            this.add(this.needExperience);
            this.add(this.currentExperience);
            this.add(this.needMoney);
            this.add(this.teacherGong);
            this.add(this.exchangeBtn);
            this.add(this.promoteBtn);
        }
    }
    
    public void panelGetData(BigDecimal experience, int tsx, int tsExp, String lvl, String raceConfirm, int tsp, int realmMaxTSP) {
        this.getCurrentExperience().setText(experience.toString());
        this.getTrainingProgress().setText(tsx + "/" + tsExp);
        this.getCharacterLevel().setText(lvl);
        this.getCharacterState().setText((raceConfirm == null) ? "- -" : raceConfirm);
        this.getCurrentPoin().setText(tsp + "");
        this.getSurplusPoint().setText(realmMaxTSP - tsp + "");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S38.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 376, 466, this);
            Util.drawMoney(g, this.drawCenteredString(g, RoleData.getRoleData().getLoginResult().getGold().toString(), 141, 162, UIUtils.TEXT_FONT1), 365);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/soaringSkill/提升修炼点w376，h466.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 376, 466, this);
            Util.drawMoney(g, this.drawCenteredString(g, RoleData.getRoleData().getLoginResult().getGold().toString(), 141, 162, UIUtils.TEXT_FONT1), 375);
        }
    }
    
    public int drawCenteredString(Graphics g, String text, int getX, int lonX, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = lonX + (getX - metrics.stringWidth(text)) / 2;
        return x;
    }
    
    public JLabel getCurrentPoin() {
        return this.currentPoin;
    }
    
    public void setCurrentPoin(JLabel currentPoin) {
        this.currentPoin = currentPoin;
    }
    
    public JLabel getSurplusPoint() {
        return this.surplusPoint;
    }
    
    public void setSurplusPoint(JLabel surplusPoint) {
        this.surplusPoint = surplusPoint;
    }
    
    public JLabel getCharacterLevel() {
        return this.characterLevel;
    }
    
    public void setCharacterLevel(JLabel characterLevel) {
        this.characterLevel = characterLevel;
    }
    
    public JLabel getCharacterState() {
        return this.characterState;
    }
    
    public void setCharacterState(JLabel characterState) {
        this.characterState = characterState;
    }
    
    public JLabel getTrainingProgress() {
        return this.trainingProgress;
    }
    
    public void setTrainingProgress(JLabel trainingProgress) {
        this.trainingProgress = trainingProgress;
    }
    
    public JLabel getNeedExperience() {
        return this.needExperience;
    }
    
    public void setNeedExperience(JLabel needExperience) {
        this.needExperience = needExperience;
    }
    
    public JLabel getCurrentExperience() {
        return this.currentExperience;
    }
    
    public void setCurrentExperience(JLabel currentExperience) {
        this.currentExperience = currentExperience;
    }
    
    public JLabel getNeedMoney() {
        return this.needMoney;
    }
    
    public void setNeedMoney(JLabel needMoney) {
        this.needMoney = needMoney;
    }
    
    public SkillPromoteBtn getPromoteBtn() {
        return this.promoteBtn;
    }
    
    public void setPromoteBtn(SkillPromoteBtn promoteBtn) {
        this.promoteBtn = promoteBtn;
    }
    
    public SkillPromoteBtn getExchangeBtn() {
        return this.exchangeBtn;
    }
    
    public void setExchangeBtn(SkillPromoteBtn exchangeBtn) {
        this.exchangeBtn = exchangeBtn;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public BigDecimal getNeedMoneyNum() {
        return this.needMoneyNum;
    }
    
    public void setNeedMoneyNum(BigDecimal needMoneyNum) {
        this.needMoneyNum = needMoneyNum;
    }
    
    public BigDecimal getNeedExperienceNum() {
        return this.needExperienceNum;
    }
    
    public void setNeedExperienceNum(BigDecimal needExperienceNum) {
        this.needExperienceNum = needExperienceNum;
    }
    
    public JLabel getTeacherGong() {
        return this.teacherGong;
    }
    
    public void setTeacherGong(JLabel teacherGong) {
        this.teacherGong = teacherGong;
    }
}
