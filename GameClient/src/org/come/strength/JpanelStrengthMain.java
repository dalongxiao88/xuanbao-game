package org.come.strength;

import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import javax.swing.JLabel;
import come.tool.Scene.DNTGScene;
import java.awt.Color;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import com.tool.btn.FormsOnOffBtn;
import java.awt.LayoutManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JpanelStrengthMain extends JPanel
{
    private SkillLabel[] skillLabels;
    private ImageIcon iconBack;
    
    public JpanelStrengthMain() {
        this.setPreferredSize(new Dimension(546, 395));
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 98);
        offBtn.setBounds(509, 10, 25, 25);
        this.add(offBtn);
        this.getSkillLabels();
        this.showSkills();
    }
    
    public void showSkills() {
        for (int i = 0; i < 8; ++i) {
            Skill skill = UserMessUntil.getSkillId("" + (10001 + i));
            this.skillLabels[i].showSkill(skill);
        }
    }
    
    public void refreshSkills(String value) {
        this.clearSkill();
        if (value != null && !"".equals(value)) {
            String[] skills = value.split("&");
            for (int i = 0; i < skills.length; ++i) {
                String[] split = skills[i].split("\\$");
                for (int j = 0; j < this.skillLabels.length; ++j) {
                    if (split[0].equals(this.skillLabels[j].getSkill().getSkillid())) {
                        this.skillLabels[j].showLevel(Integer.parseInt(split[1]));
                    }
                }
            }
        }
    }
    
    public void clearSkill() {
        for (int j = 0; j < this.skillLabels.length; ++j) {
            this.skillLabels[j].showLevel(0);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/S18.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 546, 395, this);
        if (ImageMixDeal.scene != null && ImageMixDeal.scene.getSceneId() == 1011) {
            g.setColor(Color.WHITE);
            DNTGScene scene = (DNTGScene)ImageMixDeal.scene;
            g.drawString(scene.getDN_JB().toString(), 113, 40);
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public SkillLabel[] getSkillLabels() {
        if (this.skillLabels == null) {
            this.skillLabels = new SkillLabel[8];
            for (int i = 0; i < this.skillLabels.length; ++i) {
                (this.skillLabels[i] = new SkillLabel()).setBounds(52 + i % 2 * 235, 64 + i / 2 * 76 + ((i <= 1) ? 1 : 0), 220, 64);
                this.add(this.skillLabels[i]);
            }
        }
        return this.skillLabels;
    }
    
    public void setSkillLabels(SkillLabel[] skillLabels) {
        this.skillLabels = skillLabels;
    }
    
    class SkillLabel extends JPanel
    {
        private JLabel imgLab;
        private JLabel expNumLab;
        private JLabel nameLab;
        private BtnStrengthMain upgradeBtn;
        private Skill skill;
        private int level;
        private int expNum;
        private int base;
        private MouseListenerStrengthMain strengthMain;
        private ImageIcon expImg;
        
        public SkillLabel() {
            this.setPreferredSize(new Dimension(220, 64));
            this.setOpaque(false);
            this.setLayout((LayoutManager)null);
            this.getImgLab();
            this.getExpNumLab();
            this.getNameLab();
            this.getUpgradeBtn();
        }
        
        public void showLevel(int level) {
            this.level = level;
            this.getExpNumLab().setText(level + "/20");
            if (this.skill != null) {
                int intValue = new BigDecimal(level).divide(new BigDecimal(this.skill.getSkilllevel()), 3, 1).multiply(new BigDecimal(98)).intValue();
                this.expNum = ((intValue > 98) ? 98 : intValue);
            }
        }
        
        public void showSkill(Skill skill) {
            if (skill != null) {
                this.skill = skill;
                this.base = (int)Double.parseDouble(skill.getDielectric());
                this.getNameLab().setText(skill.getSkillname());
                this.getExpNumLab().setText(skill.getSkilllevel());
                this.getImgLab().setIcon(CutButtonImage.getImage("img/skill/" + skill.getSkillid() + ".png", -1, -1));
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.skill != null) {
                g.setColor(Color.white);
                g.setFont(UIUtils.TEXT_FONT);
                g.drawString((this.level + 1) * this.base + "金币", 153, 30);
            }
            if (this.expImg == null) {
                this.expImg = CutButtonImage.getImage("inkImg/background/S28.png", -1, -1);
            }
            g.drawImage(this.expImg.getImage(), 73, 42, this.expNum, 12, this);
        }
        
        public JLabel getImgLab() {
            if (this.imgLab == null) {
                (this.imgLab = new JLabel()).setBounds(9, 7, 48, 48);
                this.strengthMain = new MouseListenerStrengthMain(this);
                this.imgLab.addMouseListener(this.strengthMain);
                this.add(this.imgLab);
            }
            return this.imgLab;
        }
        
        public void setImgLab(JLabel imgLab) {
            this.imgLab = imgLab;
        }
        
        public JLabel getExpNumLab() {
            if (this.expNumLab == null) {
                (this.expNumLab = new JLabel("0/20", 0)).setBounds(73, 42, 98, 12);
                this.expNumLab.setForeground(Color.white);
                this.expNumLab.setFont(UIUtils.TEXT_FONT);
                this.add(this.expNumLab);
            }
            return this.expNumLab;
        }
        
        public void setExpNumLab(JLabel expNumLab) {
            this.expNumLab = expNumLab;
        }
        
        public JLabel getNameLab() {
            if (this.nameLab == null) {
                (this.nameLab = new JLabel("安身立命", 0)).setBounds(74, 13, 74, 25);
                this.nameLab.setForeground(Color.white);
                this.nameLab.setFont(UIUtils.TEXT_FONT1);
                this.add(this.nameLab);
            }
            return this.nameLab;
        }
        
        public void setNameLab(JLabel nameLab) {
            this.nameLab = nameLab;
        }
        
        public BtnStrengthMain getUpgradeBtn() {
            if (this.upgradeBtn == null) {
                (this.upgradeBtn = new BtnStrengthMain("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 0, "升级", this)).setBounds(177, 40, 34, 17);
                this.add(this.upgradeBtn);
            }
            return this.upgradeBtn;
        }
        
        public void setUpgradeBtn(BtnStrengthMain upgradeBtn) {
            this.upgradeBtn = upgradeBtn;
        }
        
        public Skill getSkill() {
            return this.skill;
        }
        
        public void setSkill(Skill skill) {
            this.skill = skill;
        }
        
        public int getLevel() {
            return this.level;
        }
        
        public void setLevel(int level) {
            this.level = level;
        }
    }
}
