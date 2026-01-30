package org.come.action;

import org.come.until.MessagrFlagUntil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.come.until.CutButtonImage;
import com.tool.role.SkillUtil;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import org.skill.panel.CopyOfSkillTYCPanel;
import java.awt.event.MouseListener;

public class CopyOfSkillTYCMuse implements MouseListener
{
    public String value;
    public String sename;
    public int index;
    public int bmw;
    public int x;
    public CopyOfSkillTYCPanel copy;
    public Timer timer;
    public boolean fof;
    
    public CopyOfSkillTYCMuse(String value, int index, CopyOfSkillTYCPanel copy, String senamem, int bmw) {
        this.x = 1;
        this.fof = false;
        this.value = value;
        this.index = index;
        this.copy = copy;
        this.bmw = bmw;
        this.sename = senamem;
    }
    
    public CopyOfSkillTYCMuse() {
        this.x = 1;
        this.fof = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        String seName = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
        if (seName == null) {
            return;
        }
        this.copy.sum = 0;
        if (this.bmw > 166) {
            this.bmw = 166;
        }
        if (this.copy.fof) {
            if (this.copy.typeBtn == 1) {
                this.copy.jLabel.setIcon(CutButtonImage.Zoom(this.copy.names, 4));
                this.copy.jLabel1.setIcon(CutButtonImage.Zoom(this.copy.names, 5));
                this.copy.jLabel2.setIcon(CutButtonImage.Zoom(this.copy.names, 6));
            }
            else {
                this.copy.jLabel.setIcon(CutButtonImage.Zoom(seName, 4));
                this.copy.jLabel1.setIcon(CutButtonImage.Zoom(seName, 5));
                this.copy.jLabel2.setIcon(CutButtonImage.Zoom(seName, 6));
            }
            (this.timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CopyOfSkillTYCMuse.this.copy.jLabel.setBounds(302, 163 + CopyOfSkillTYCMuse.this.bmw, 44, 59);
                    CopyOfSkillTYCMuse.this.copy.jLabel1.setBounds(355, 163 + CopyOfSkillTYCMuse.this.bmw, 44, 59);
                    CopyOfSkillTYCMuse.this.copy.jLabel2.setBounds(320, 214 + CopyOfSkillTYCMuse.this.bmw, 63, 47);
                    CopyOfSkillTYCMuse.this.copy.type1.setBounds(334, 163 + CopyOfSkillTYCMuse.this.bmw, 33, 33);
                    CopyOfSkillTYCMuse.this.copy.type2.setBounds(308, 211 + CopyOfSkillTYCMuse.this.bmw, 33, 33);
                    CopyOfSkillTYCMuse.this.copy.type3.setBounds(362, 211 + CopyOfSkillTYCMuse.this.bmw, 33, 33);
                    CopyOfSkillTYCMuse this$0 = CopyOfSkillTYCMuse.this;
                    CopyOfSkillTYCMuse.this.bmw += 6;
                    if (CopyOfSkillTYCMuse.this.bmw >= 166) {
                        CopyOfSkillTYCMuse.this.bmw = 1;
                        CopyOfSkillTYCMuse.this.timer.stop();
                        if (CopyOfSkillTYCMuse.this.copy.typeBtn == 1) {
                            CopyOfSkillTYCMuse.this.copy.skills.setShow(true, CopyOfSkillTYCMuse.this.copy.names);
                        }
                        else {
                            CopyOfSkillTYCMuse.this.copy.skills.setShow(true, seName);
                        }
                    }
                    CopyOfSkillTYCMuse.this.copy.fof = false;
                }
            })).start();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        String vi = "";
        if (this.copy.typeBtn == 1) {
            String[] vs = SkillUtil.getSepcieswas(this.copy.names);
            switch (this.index) {
                case 1: {
                    vi = vs[0];
                    break;
                }
                case 2: {
                    vi = vs[1];
                    break;
                }
                case 3: {
                    vi = vs[2];
                    break;
                }
            }
            this.copy.DownName(vi);
        }
        else {
            this.copy.DownName(this.value);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        String seName = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
        if (seName == null) {
            return;
        }
        if (this.copy.sum == 0) {
            if (this.copy.typeBtn == 1) {
                switch (this.index) {
                    case 1: {
                        this.copy.jLabel.setIcon(CutButtonImage.ZoomJr(this.copy.names, 4));
                        break;
                    }
                    case 2: {
                        this.copy.jLabel1.setIcon(CutButtonImage.ZoomJr(this.copy.names, 5));
                        break;
                    }
                }
            }
            else {
                switch (this.index) {
                    case 1: {
                        this.copy.jLabel.setIcon(CutButtonImage.ZoomJr(seName, 4));
                        break;
                    }
                    case 2: {
                        this.copy.jLabel1.setIcon(CutButtonImage.ZoomJr(seName, 5));
                        break;
                    }
                }
            }
        }
        else if (this.copy.typeBtn == 1) {
            switch (this.index) {
                case 1: {
                    this.copy.jLabel.setIcon(CutButtonImage.XZ2(this.copy.names, 4));
                    break;
                }
                case 2: {
                    this.copy.jLabel1.setIcon(CutButtonImage.XZ2(this.copy.names, 5));
                    break;
                }
                case 3: {
                    this.copy.jLabel2.setIcon(CutButtonImage.XZ2(this.copy.names, 6));
                    break;
                }
            }
        }
        else {
            switch (this.index) {
                case 1: {
                    this.copy.jLabel.setIcon(CutButtonImage.XZ2(seName, 4));
                    break;
                }
                case 2: {
                    this.copy.jLabel1.setIcon(CutButtonImage.XZ2(seName, 5));
                    break;
                }
                case 3: {
                    this.copy.jLabel2.setIcon(CutButtonImage.XZ2(seName, 6));
                    break;
                }
            }
        }
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        String seName = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
        if (seName == null) {
            return;
        }
        if (this.copy.sum == 0) {
            if (this.copy.typeBtn == 1) {
                this.copy.jLabel.setIcon(CutButtonImage.Zoom(this.copy.names, 1));
                this.copy.jLabel1.setIcon(CutButtonImage.Zoom(this.copy.names, 2));
            }
            else {
                this.copy.jLabel.setIcon(CutButtonImage.Zoom(seName, 1));
                this.copy.jLabel1.setIcon(CutButtonImage.Zoom(seName, 2));
            }
        }
        else if (this.copy.typeBtn == 1) {
            this.copy.jLabel.setIcon(CutButtonImage.XZ1(this.copy.names, 1));
            this.copy.jLabel1.setIcon(CutButtonImage.XZ1(this.copy.names, 2));
            this.copy.jLabel2.setIcon(CutButtonImage.XZ1(this.copy.names, 3));
        }
        else {
            this.copy.jLabel.setIcon(CutButtonImage.XZ1(seName, 1));
            this.copy.jLabel1.setIcon(CutButtonImage.XZ1(seName, 2));
            this.copy.jLabel2.setIcon(CutButtonImage.XZ1(seName, 3));
        }
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    public static void getVales() {
    }
}
