package org.skill.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.Frame.MsgJframe1;
import org.come.Jpanel.GameJpanel;
import org.come.action.MapAction;
import org.come.bean.Skill;
import org.come.until.AnalysisString;
import org.come.until.Arith;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.NpcMenuUntil;
import org.come.until.UserMessUntil;
import org.skill.frame.SkillMainFrame;

import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

public class SkillSMPanel extends JPanel
{
    private JLabel skillTitle;
    private JLabel skillSects;
    private JLabel skillAddress;
    private JLabel[] skillBar;
    private JLabel[] skillValue;
    private JLabel[] skillArt;
    private JLabel[] skillskilimg;
    private JLabel[] skillName;
    private JLabel[] skillShelter;
    private int proficiencyMax;
    private static ImageIcon icon;
    private static ImageIcon icon1;
    
    public SkillSMPanel(int py) {
        this.proficiencyMax = 0;
        class MListener extends MouseAdapter {
            private int numType;

            public MListener(int numType) {
                this.numType = numType;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!SkillSMPanel.this.skillName[this.numType].isVisible()) {
                    MapAction.npcmenuAction.get(NpcMenuUntil.SKILL).menuControl(SkillSMPanel.this.skillName[this.numType].getText());
                    SkillMainFrame.getSkillMainFrame().getSkillMainPanel().changeBtnPanel(0);
                } else {
                    GameJpanel.skill = UserMessUntil.getskill1(SkillSMPanel.this.skillName[this.numType].getText());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (SkillSMPanel.this.skillName[this.numType].isVisible()) {
                    Skill skill = UserMessUntil.getskill1(SkillSMPanel.this.skillName[this.numType].getText());
                    if (skill == null) {
                        return;
                    }
                    MsgJframe1.getJframe1().getJapnel1().SM(skill, Double.parseDouble(SkillSMPanel.this.skillValue[this.numType].getText().split("/")[0]), AnalysisString.lvlint(RoleData.getRoleData().getLoginResult().getGrade()));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FormsManagement.HideForm(603);
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(176 * py, 0, 170, 300);
            this.setOpaque(false);
            this.setLayout(null);
            HashMap<TextAttribute, Object> hm = new HashMap<>();
            hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            hm.put(TextAttribute.SIZE, Integer.valueOf(12));
            hm.put(TextAttribute.FAMILY, "Simsun");
            Font fontAddr = new Font(hm);
            this.skillTitle = new JLabel("速");
            this.skillSects = new JLabel("门派:");
            this.skillAddress = new JLabel("狮门岭");
            this.skillBar = new JLabel[5];
            this.skillValue = new JLabel[5];
            this.skillArt = new JLabel[5];
            this.skillName = new JLabel[5];
            this.skillskilimg = new JLabel[5];
            this.skillShelter = new JLabel[5];
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i] = new JLabel();
                this.skillValue[i] = new JLabel(i + "/100000");
                this.skillArt[i] = new JLabel();
                this.skillShelter[i] = new JLabel("点击学习该技能");
                this.skillName[i] = new JLabel("技能名称");
                this.skillskilimg[i] = new JLabel();
            }
            this.skillTitle.setForeground(new Color(251, 216, 51));
            this.skillSects.setForeground(new Color(251, 216, 51));
            this.skillAddress.setForeground(new Color(0, 255, 0));
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setForeground(Color.white);
                this.skillName[i].setForeground(Color.white);
                this.skillShelter[i].setForeground(Color.green);
            }
            this.skillTitle.setFont(UIUtils.TEXT_HY34);
            this.skillSects.setFont(new Font("宋体", 0, 12));
            this.skillAddress.setFont(fontAddr);
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setFont(new Font("宋体", 0, 12));
                this.skillName[i].setFont(UIUtils.TEXT_HY16);
                this.skillShelter[i].setFont(UIUtils.TEXT_HY14);
            }
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setIcon(CutButtonImage.getImage("inkImg/background/55.png", 108, 15));
                this.skillArt[i].setIcon(CutButtonImage.getImage("inkImg/button/27.png", 101, 10));
            }
            this.skillTitle.setBounds(0, 0, 170, 40);
            this.skillSects.setBounds(45, 35, 30, 15);
            this.skillAddress.setBounds(75, 35, 90, 15);
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setBounds(51, 82 + i * 48, 108, 15);
                this.skillArt[i].setBounds(55, 84 + i * 48, 101, 10);
                this.skillValue[i].setBounds(53, 82 + i * 48, 103, 12);
                this.skillName[i].setBounds(51, 62 + i * 49, 103, 14);
                this.skillShelter[i].setBounds(45, 61 + i * 49, 119, 39);
                this.skillskilimg[i].setBounds(10, 61 + i * 49, 40, 40);
            }
            this.skillTitle.setOpaque(false);
            this.skillSects.setOpaque(false);
            this.skillAddress.setOpaque(false);
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setOpaque(false);
                this.skillArt[i].setOpaque(false);
                this.skillValue[i].setOpaque(false);
                this.skillName[i].setOpaque(false);
                this.skillShelter[i].setOpaque(false);
                this.skillskilimg[i].setOpaque(false);
            }
            this.skillTitle.setHorizontalAlignment(0);
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setHorizontalAlignment(0);
                this.skillShelter[i].setHorizontalAlignment(0);
            }
            for (int i = 0; i < this.skillShelter.length; ++i) {
                this.skillShelter[i].setVisible(true);
            }
            this.add(this.skillTitle);
            this.add(this.skillSects);
            this.add(this.skillAddress);
            for (int i = 0; i < 5; ++i) {
                this.add(this.skillName[i]);
                this.add(this.skillValue[i]);
                this.add(this.skillArt[i]);
                this.add(this.skillBar[i]);
                this.add(this.skillShelter[i]);
                this.add(this.skillskilimg[i]);
            }
            this.skillAddress.addMouseListener(new MouseAdapter() {
                Cursor cursor = new Cursor(12);
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    JLabel source = (JLabel)e.getSource();
                  //  source.setCursor(this.cursor);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JLabel source = (JLabel)e.getSource();
                 //   source.setCursor(this.cursor);
                }
            });
            for (int i = 0; i < this.skillShelter.length; ++i) {
                MListener l = new MListener(i);
                this.skillShelter[i].addMouseListener(l);
                this.skillName[i].addMouseListener(l);
                this.skillBar[i].addMouseListener(l);
                this.skillArt[i].addMouseListener(l);
                this.skillValue[i].addMouseListener(l);
                this.skillskilimg[i].addMouseListener(l);
            }
        }
        else {
            this.setBounds(176 * py, 0, 170, 309);
            this.setOpaque(false);
            this.setLayout(null);
            HashMap<TextAttribute, Object> hm = new HashMap<>();
            hm.put(TextAttribute.SIZE, Integer.valueOf(12));
            hm.put(TextAttribute.FAMILY, "Simsun");
            Font fontAddr = new Font(hm);
            this.skillTitle = new JLabel("速");
            this.skillSects = new JLabel("门派:");
            this.skillAddress = new JLabel("长生殿");
            this.skillBar = new JLabel[5];
            this.skillValue = new JLabel[5];
            this.skillArt = new JLabel[5];
            this.skillName = new JLabel[5];
            this.skillShelter = new JLabel[5];
            this.skillskilimg = new JLabel[5];
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i] = new JLabel();
                this.skillValue[i] = new JLabel(i + "/100000");
                this.skillArt[i] = new JLabel();
                this.skillShelter[i] = new JLabel("点击学习该技能");
                this.skillName[i] = new JLabel("技能名称");
                this.skillskilimg[i] = new JLabel();
            }
            this.skillTitle.setForeground(new Color(251, 216, 51));
            this.skillSects.setForeground(new Color(251, 216, 51));
            this.skillAddress.setForeground(new Color(0, 255, 0));
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setForeground(Color.white);
                this.skillName[i].setForeground(Color.white);
                this.skillShelter[i].setForeground(Color.green);
            }
            this.skillTitle.setFont(UIUtils.TEXT_HY34);
            this.skillSects.setFont(new Font("宋体", 0, 12));
            this.skillAddress.setFont(fontAddr);
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setFont(new Font("宋体", 0, 12));
                this.skillName[i].setFont(fontAddr);
                this.skillShelter[i].setFont(fontAddr);
            }
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setIcon(CutButtonImage.getImage("img/soaringSkill/技能框w148,h18px.png", 108, 18));
                this.skillArt[i].setIcon(CutButtonImage.getImage("img/soaringSkill/技能条w140,h8px.png", 100, 8));
            }
            this.skillTitle.setBounds(0, 0, 170, 40);
            this.skillSects.setBounds(45, 35, 30, 15);
            this.skillAddress.setBounds(75, 35, 90, 15);
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setBounds(51, 78 + i * 49, 108, 18);
                this.skillArt[i].setBounds(54, 82 + i * 49, 101, 8);
                this.skillValue[i].setBounds(53, 79 + i * 49, 103, 12);
                this.skillName[i].setBounds(51, 60 + i * 49, 103, 14);
                this.skillShelter[i].setBounds(45, 59 + i * 49, 119, 39);
                this.skillskilimg[i].setBounds(5, 59 + i * 49, 1519, 39);
            }
            this.skillTitle.setOpaque(false);
            this.skillSects.setOpaque(false);
            this.skillAddress.setOpaque(false);
            for (int i = 0; i < 5; ++i) {
                this.skillBar[i].setOpaque(false);
                this.skillArt[i].setOpaque(false);
                this.skillValue[i].setOpaque(false);
                this.skillName[i].setOpaque(false);
                this.skillShelter[i].setOpaque(false);
                this.skillskilimg[i].setOpaque(false);
            }
            this.skillTitle.setHorizontalAlignment(0);
            for (int i = 0; i < this.skillValue.length; ++i) {
                this.skillValue[i].setHorizontalAlignment(0);
                this.skillShelter[i].setHorizontalAlignment(0);
            }
            for (int i = 0; i < this.skillShelter.length; ++i) {
                this.skillShelter[i].setVisible(true);
            }
            this.add(this.skillTitle);
            this.add(this.skillSects);
            this.add(this.skillAddress);
            for (int i = 0; i < 5; ++i) {
                this.add(this.skillName[i]);
                this.add(this.skillValue[i]);
                this.add(this.skillArt[i]);
                this.add(this.skillBar[i]);
                this.add(this.skillShelter[i]);
                this.add(this.skillskilimg[i]);
            }
            this.skillAddress.addMouseListener(new MouseAdapter() {
                Cursor cursor = new Cursor(12);
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    JLabel source = (JLabel)e.getSource();
                  //  source.setCursor(this.cursor);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JLabel source = (JLabel)e.getSource();
                  //  source.setCursor(this.cursor);
                }
            });
            for (int i = 0; i < this.skillShelter.length; ++i) {
                MListener j = new MListener(i);
                this.skillShelter[i].addMouseListener(j);
                this.skillName[i].addMouseListener(j);
                this.skillBar[i].addMouseListener(j);
                this.skillArt[i].addMouseListener(j);
                this.skillValue[i].addMouseListener(j);
                this.skillskilimg[i].addMouseListener(j);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (SkillSMPanel.icon == null) {
                SkillSMPanel.icon = CutButtonImage.getImage("inkImg/background1/B221.png", -1, -1);
            }
            g.drawImage(SkillSMPanel.icon.getImage(), 0, 0, 170, 300, this);
        }
        else {
            if (SkillSMPanel.icon1 == null) {
                SkillSMPanel.icon1 = CutButtonImage.getImage("img/soaringSkill/师门w170,h300.png", -1, -1);
            }
            g.drawImage(SkillSMPanel.icon1.getImage(), 0, 0, 170, 300, this);
        }
    }
    
    public JLabel getSkillTitle() {
        return this.skillTitle;
    }
    
    public void setSkillTitle(JLabel skillTitle) {
        this.skillTitle = skillTitle;
    }
    
    public JLabel getSkillSects() {
        return this.skillSects;
    }
    
    public void setSkillSects(JLabel skillSects) {
        this.skillSects = skillSects;
    }
    
    public JLabel getSkillAddress() {
        return this.skillAddress;
    }
    
    public void setSkillAddress(JLabel skillAddress) {
        this.skillAddress = skillAddress;
    }
    
    public JLabel[] getSkillBar() {
        return this.skillBar;
    }
    
    public void setSkillBar(JLabel[] skillBar) {
        this.skillBar = skillBar;
    }
    
    public JLabel[] getSkillValue() {
        return this.skillValue;
    }
    
    public void setSkillValue(JLabel[] skillValue) {
        this.skillValue = skillValue;
    }
    
    public JLabel[] getSkillArt() {
        return this.skillArt;
    }
    
    public void setSkillArt(JLabel[] skillArt) {
        this.skillArt = skillArt;
    }
    
    public JLabel[] getSkillName() {
        return this.skillName;
    }
    
    public void setSkillName(JLabel[] skillName) {
        this.skillName = skillName;
    }
    
    public ImageIcon getIcon() {
        return SkillSMPanel.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        SkillSMPanel.icon = icon;
    }
    
    public static void setSkillDivisionGateLeftPanel(SkillSMPanel skillDivisionGateLeftPanel) {
    }
    
    public void getSkillsName(String[] skillString, int lvltrue, String[] vs) {
        switch (lvltrue) {
            case 0: {
                this.proficiencyMax = 10000;
                break;
            }
            case 1: {
                this.proficiencyMax = 15000;
                break;
            }
            case 2: {
                this.proficiencyMax = 20000;
                break;
            }
            case 3:
            case 4: {
                this.proficiencyMax = 25000;
                break;
            }
        }
        for (int i = 0; i < skillString.length; ++i) {
            this.skillskilimg[i].setBounds(8, 58 + 49 * i, 40, 40);
            this.skillName[i].setText(skillString[i]);
            this.skillShelter[i].setVisible(true);
            this.skillskilimg[i].setVisible(true);
        }
        if (vs != null) {
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("_");
                Skill skill = UserMessUntil.getSkillId(vss[0]);
                if (skill != null) {
                    int j = 0;
                    while (j < skillString.length) {
                        if (skillString[j].equals(skill.getSkillname())) {
                            this.skillValue[j].setText(vss[1] + "/" + this.proficiencyMax);
                            int parseInt = Integer.parseInt(vss[1]);
                            int witNum = (int)Math.round(Arith.mul(Arith.div((double)Double.valueOf((double)parseInt), (double)Double.valueOf((double)this.proficiencyMax), 1), 140.0));
                            witNum = ((witNum > 0) ? witNum : 1);
                            witNum = ((witNum >= 100) ? 100 : witNum);
                            this.skillArt[j].setSize(witNum, this.skillArt[j].getHeight());
                            if (MyIsif.getStyle().equals("水墨")) {
                                this.skillArt[j].setIcon(CutButtonImage.getImage("inkImg/button/27.png", witNum, 8));
                            }
                            else {
                                this.skillArt[j].setIcon(CutButtonImage.getImage("img/soaringSkill/技能条w140,h8px.png", witNum, 8));
                            }
                            this.skillShelter[j].setVisible(false);
                            this.skillskilimg[j].setIcon(CutButtonImage.getImage("img/fighting-skill/" + UserMessUntil.getskill1(this.skillName[j].getText()).getSkillid() + ".png", 40, 40));
                            break;
                        }
                        else {
                            ++j;
                        }
                    }
                }
            }
        }
        this.changeViewTrue();
    }
    
    public void changeViewTrue() {
        for (int i = 0; i < this.skillName.length; ++i) {
            this.skillName[i].setVisible(!this.skillShelter[i].isVisible());
            this.skillValue[i].setVisible(!this.skillShelter[i].isVisible());
            this.skillArt[i].setVisible(!this.skillShelter[i].isVisible());
            this.skillBar[i].setVisible(!this.skillShelter[i].isVisible());
            this.skillskilimg[i].setVisible(!this.skillShelter[i].isVisible());
        }
    }
}
