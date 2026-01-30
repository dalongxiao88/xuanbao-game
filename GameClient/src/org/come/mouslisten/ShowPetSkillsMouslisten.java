package org.come.mouslisten;

import java.awt.Color;
import javax.swing.BorderFactory;
import org.apache.commons.lang.StringUtils;
import javax.swing.ImageIcon;

import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import org.come.bean.PathPoint;
import org.come.until.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import org.come.entity.RoleSummoning;
import org.come.Frame.XYJframe;
import org.come.Frame.PetSkillsJframe;
import org.come.control.AssetControl;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.Stall.AssetUpdate;

import javax.swing.text.BadLocationException;
import com.tool.Document.RichDocument;
import org.come.Frame.ZhuFrame;

import java.awt.event.MouseEvent;
import org.come.Jpanel.PetSkillsJpanel;
import org.come.bean.Skill;
import java.awt.event.MouseListener;

public class ShowPetSkillsMouslisten implements MouseListener
{
    private int skillwl;
    private Skill skill;
    private int index;
    private PetSkillsJpanel petSkillsJpanel;
    private int[] xy;
    private String imgPath;
    private Boolean b;
    
    public ShowPetSkillsMouslisten(int index, PetSkillsJpanel petSkillsJpanel, int[] xy) {
        this.b = Boolean.valueOf(true);
        this.petSkillsJpanel = petSkillsJpanel;
        this.xy = xy;
        this.index = index;
    }
    
    public ShowPetSkillsMouslisten(int index, PetSkillsJpanel petSkillsJpanel, int[] xy, Boolean b) {
        this.b = Boolean.valueOf(true);
        this.petSkillsJpanel = petSkillsJpanel;
        this.xy = xy;
        this.index = index;
        this.b = b;
    }
    
    public ShowPetSkillsMouslisten(int index) {
        this.b = Boolean.valueOf(true);
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        RoleSummoning pet1 = UserMessUntil.getChosePetMes();
        if (this.skill != null) {
            if (e.getButton() == 1) {
                if (e.isShiftDown()) {
                    try {
                        JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                        ((RichDocument)SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 3, UserMessUntil.getChosePetMes().getSid(), "[" + UserMessUntil.getChosePetMes().getSummoningname() + "]", "G", null);
                    }
                    catch (BadLocationException e2) {
                        e2.printStackTrace();
                    }
                }
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    boolean f = false;
                    if (UserMessUntil.getChosePetMes().getPetSkilllock() == null) {
                        UserMessUntil.getChosePetMes().setPetSkilllock("");
                    }
                    else {
                        String[] lcnum = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|");
                        if (lcnum.length > 3) {
                            ZhuFrame.getZhuJpanel().addPrompt("最多只能锁定4个技能！");
                            return;
                        }
                    }
                    if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                        for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                            if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && this.skill.getSkillid().equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                f = true;
                            }
                        }
                    }
                    if (f) {
                        if (UserMessUntil.getChosePetMes().getPetSkilllock() != null && UserMessUntil.getChosePetMes().getPetSkilllock().contains(this.skill.getSkillid())) {
                            ZhuFrame.getZhuJpanel().addPrompt("该技能已加锁");
                        }
                        else {
                            AssetUpdate assetUpdate = new AssetUpdate();
                            if (UserMessUntil.getChosePetMes().getPetSkilllock() != null && UserMessUntil.getChosePetMes().getPetSkilllock() != "") {
                                String[] num = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|");
                                if (Util.isxy(Long.valueOf(Long.parseLong((num.length + num.length) * 2 * 50 + "")))) {
                                    String sendMes = Agreement.updateXianyu("xianyu=" + num.length + "");
                                    SendMessageUntil.toServer(sendMes);
                                }
                                else {
                                    ZhuFrame.getZhuJpanel().addPrompt("仙玉不足无法锁定！");
                                    return;
                                }
                            }
                            else {
                                assetUpdate.setData("X=-0");
                                AssetControl.asset(assetUpdate);
                                String sendMes2 = Agreement.updateXianyu("xianyu=0");
                                SendMessageUntil.toServer(sendMes2);
                                UserMessUntil.getChosePetMes().setPetSkilllock("");
                            }
                            System.out.println(UserMessUntil.getChosePetMes().getPetSkilllock());
                            UserMessUntil.getChosePetMes().setPetSkilllock(UserMessUntil.getChosePetMes().getPetSkilllock() + this.skill.getSkillid() + "|");
                            try {
                                SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            ZhuFrame.getZhuJpanel().addPrompt(" 加锁成功！");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt("只有学习的技能才可以锁定！");
                    }
                }
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11) && UserMessUntil.getChosePetMes().getPetSkilllock().contains(this.skill.getSkillid())) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    String lists = "";
                    if (UserMessUntil.getChosePetMes().getPetSkilllock() != null && UserMessUntil.getChosePetMes().getPetSkilllock() != "") {
                        String[] list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|");
                        if (list != null && list.length > 0) {
                            for (String id : list) {
                                if (!this.skill.getSkillid().equals(id)) {
                                    lists = lists + id + "|";
                                }
                            }
                        }
                    }
                    UserMessUntil.getChosePetMes().setPetSkilllock(lists);
                    try {
                        SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                    }
                    catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                }
            }
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().skillmsg(UserMessUntil.getChosePetMes(), this.skill, this.skillwl);
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().setPetskillID(this.skill.getSkillid());
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().setPetskillNO(this.index);
            if (XYJframe.getXYJframe().getXyJpanel().isVisible() && this.skill.getSkillname().equals("一念成圣")) {
                RoleSummoning pet2 = UserMessUntil.getChosePetMes();
                if (pet2.getSkill().equals("1293")) {
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("OPENXL&" + pet2.getSummoningid() + "&" + pet2.getSid()));
                }
            }
        }
        else {
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().setPetskillID("");
        }
        if (this.petSkillsJpanel != null) {
            this.petSkillsJpanel.getEffect().setBounds(this.xy[0], this.xy[1], 50, 50);
        }
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
        if (petSkillsJpanel != null) {
            for (JLabel labPetskill : this.petSkillsJpanel.getLabPetskills()) {
                labPetskill.setBorder(BorderFactory.createEmptyBorder());
            }
        }
        if(petSkillsJpanel!=null) {
            petSkillsJpanel.getEffect().setBounds(xy[0], xy[1], 50, 50);
        }
        if ((boolean)this.b) {
            jLabel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.skill == null) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD2("#R技能格未获取#r#Y开启技能格方式:#r1.召唤兽点化后使用已提炼技能的超级聚魄丹时有几率开启格子！。#r召唤兽启灵有几率开启获得（封印状态）#r3明雷战斗有几率开启", point.getX()+30, point.getY()-20);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
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
    
    public int[] getXy() {
        return this.xy;
    }
    
    public void setXy(int[] xy) {
        this.xy = xy;
    }
}
