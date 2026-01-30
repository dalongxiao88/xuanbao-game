package org.come.mouslisten;

import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import org.come.bean.PathPoint;
import org.come.until.FormsManagement;
import org.come.until.MessagrFlagUntil;
import java.awt.Color;
import javax.swing.BorderFactory;
import org.apache.commons.lang.StringUtils;
import javax.swing.ImageIcon;
import org.come.until.CutButtonImage;
import javax.swing.JLabel;
import org.come.Frame.PetSkillsJframe;
import java.awt.event.MouseEvent;
import org.come.Jpanel.OpenSkillGridJpanel;
import org.come.bean.Skill;
import java.awt.event.MouseListener;

public class ShowPetSkillsJpMouslisten implements MouseListener
{
    private int skillwl;
    private Skill skill;
    private int index;
    private OpenSkillGridJpanel petSkillsJpanel;
    private int[] xy;
    private String imgPath;
    
    public ShowPetSkillsJpMouslisten(int index, OpenSkillGridJpanel petSkillsJpanel, int[] xy) {
        this.petSkillsJpanel = petSkillsJpanel;
        this.xy = xy;
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.skill != null) {
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().skillmsg(this.skill, this.skillwl);
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().setPetskillID(this.skill.getSkillid());
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().setPetskillNO(this.index);
        }
        else {
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
        }
        OpenSkillGridJpanel petSkillsJpanel = this.petSkillsJpanel;
        OpenSkillGridJpanel.getEffect().setBounds(this.xy[0], this.xy[1], 50, 50);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JLabel jLabel = (JLabel)e.getSource();
        if (jLabel.getIcon() != null) {
            if (this.skill != null) {
                if (jLabel != null && jLabel.getIcon() != null) {
                    String s = "img/skill/wxs_" + this.skill.getSkillid() + ".png";
                    ImageIcon image = CutButtonImage.getImage(s, 45, 45);
                    jLabel.setIcon(image);
                }
            }
            else {
                ImageIcon icon = (ImageIcon)jLabel.getIcon();
                String description = icon.getDescription();
                if (StringUtils.isNotBlank(description)) {
                    this.imgPath = description;
                }
                ImageIcon image2 = CutButtonImage.getImage(this.imgPath, 45, 45);
                jLabel.setIcon(image2);
            }
        }
        if (this.petSkillsJpanel != null) {
            for (JLabel labPetskill : this.petSkillsJpanel.getLabPetskills()) {
                labPetskill.setBorder(BorderFactory.createEmptyBorder());
            }
            jLabel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel jLabel = (JLabel)e.getSource();
        if (jLabel.getIcon() != null) {
            if (this.skill != null) {
                if (jLabel != null && jLabel.getIcon() != null) {
                    String s = "img/skill/wxs_" + this.skill.getSkillid() + ".png";
                    ImageIcon image = CutButtonImage.getImage(s, 50, 50);
                    jLabel.setIcon(image);
                }
            }
            else {
                ImageIcon image2 = CutButtonImage.getImage(this.imgPath, 50, 50);
                image2.setDescription(this.imgPath);
                jLabel.setIcon(image2);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
        JLabel source = (JLabel)e.getSource();
        int x = (int)source.getBounds().getX();
        int y = (int)source.getBounds().getY();
        if (this.petSkillsJpanel != null) {
            this.petSkillsJpanel.getEffect2().setBounds(x, y, 51, 51);
        }
        if (this.skill == null) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD2("#R技能格未获取#r#Y开启技能格方式:#r1.召唤兽点化后使用已提炼技能的超级聚魄丹时有几率开启格子！。#r召唤兽启灵有几率开启获得（封印状态）#r3明雷战斗有几率开启", point.getX()+30, point.getY()-20);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
        if (this.petSkillsJpanel != null) {
            this.petSkillsJpanel.getEffect2().setBounds(1000, 1000, 52, 52);
        }
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
        FormsManagement.HideForm(46);
    }
    
    public Skill getSkill() {
        return this.skill;
    }
    
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    public int getSkillwl() {
        return this.skillwl;
    }
    
    public void setSkillwl(int skillwl) {
        this.skillwl = skillwl;
    }
}
