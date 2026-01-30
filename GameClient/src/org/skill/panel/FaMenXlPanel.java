package org.skill.panel;

import org.come.bean.Skill;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.awt.Color;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.skill.btn.SkillTYCBtn5;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class FaMenXlPanel extends JPanel
{
    private BigDecimal exp;
    private BigDecimal money;
    private SkillTYCBtn5 yjxl;
    private SkillTYCBtn5 xl;
    private ImageIcon backImg;
    private SkillSMGatePanel2.FaMenItemView faMenItemView;
    private Integer type;
    
    public FaMenXlPanel() {
        this.exp = new BigDecimal("45000000");
        this.money = new BigDecimal("9000000");
        this.setPreferredSize(new Dimension(388, 374));
        this.setOpaque(false);
        this.setLayout(null);
        this.add(this.yjxl = new SkillTYCBtn5("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "一键修炼", UIUtils.TEXT_HY16, 55, this));
        this.add(this.xl = new SkillTYCBtn5("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "修炼", UIUtils.TEXT_HY16, 56, this));
        this.yjxl.setBounds(230, 300, 99, 24);
        this.xl.setBounds(70, 300, 59, 24);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backImg == null) {
            this.backImg = new ImageIcon("inkImg/background/S289.png");
        }
        g.drawImage(this.backImg.getImage(), 0, 0, 388, 374, this);
        g.setColor(Color.white);
        g.setFont(UIUtils.TEXT_FONT15);
        if (this.faMenItemView != null) {
            if ((int)this.type == 998) {
                Skill skill1 = this.faMenItemView.getSkill1();
                g.drawString(skill1.getSkillname(), 225, 58);
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("法门1").toString() + "/10000", 225, 83);
                Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getExperience().toString()), 207, 203);
                Util.drawPrice(g, new BigDecimal(this.exp.longValue()), 225, 180);
                Util.drawPrice(g, new BigDecimal(this.money.longValue()), 225, 229);
                Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getGold().toString()), 207, 253);
            }
            else if ((int)this.type == 999) {
                Skill skill1 = this.faMenItemView.getSkill2();
                g.drawString(skill1.getSkillname(), 225, 58);
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("法门2").toString() + "/10000", 225, 83);
                Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getExperience().toString()), 207, 203);
                Util.drawPrice(g, new BigDecimal(this.exp.longValue()), 225, 180);
                Util.drawPrice(g, new BigDecimal(this.money.longValue()), 225, 229);
                Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getGold().toString()), 207, 253);
            }
        }
        Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getExperience().toString()), 207, 203);
        Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getGold().toString()), 207, 253);
    }
    
    public void refresh(SkillSMGatePanel2.FaMenItemView faMenItemView, Integer caozuo) {
        this.faMenItemView = faMenItemView;
        this.type = caozuo;
    }
    
    public BigDecimal getExp() {
        return this.exp;
    }
    
    public void setExp(BigDecimal exp) {
        this.exp = exp;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public SkillTYCBtn5 getYjxl() {
        return this.yjxl;
    }
    
    public void setYjxl(SkillTYCBtn5 yjxl) {
        this.yjxl = yjxl;
    }
    
    public SkillTYCBtn5 getXl() {
        return this.xl;
    }
    
    public void setXl(SkillTYCBtn5 xl) {
        this.xl = xl;
    }
    
    public ImageIcon getBackImg() {
        return this.backImg;
    }
    
    public void setBackImg(ImageIcon backImg) {
        this.backImg = backImg;
    }
    
    public SkillSMGatePanel2.FaMenItemView getFaMenItemView() {
        return this.faMenItemView;
    }
    
    public void setFaMenItemView(SkillSMGatePanel2.FaMenItemView faMenItemView) {
        this.faMenItemView = faMenItemView;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
