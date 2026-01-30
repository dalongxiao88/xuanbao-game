package org.come.mouslisten;

import javax.swing.ImageIcon;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import java.io.File;
import java.util.List;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import come.tool.Fighting.FightingMixDeal;
import org.come.Jpanel.ZhuJpanel;
import org.come.entity.Mount;
import org.come.Frame.MountJframe;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import org.come.Jpanel.mountJPanel;
import javax.swing.JList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MountMouslisten implements MouseListener, MouseMotionListener
{
    private JList<String> listpet;
    private mountJPanel oum;
    private int p;
    
    public MountMouslisten(JList<String> listpet, mountJPanel oum) {
        this.listpet = listpet;
        this.oum = oum;
    }
    
    public MountMouslisten(int p, mountJPanel oum) {
        this.p = p;
        this.oum = oum;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(6);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(289);
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        if (UserMessUntil.getPetListTable().size() != 0) {
            if (this.listpet.getSelectedIndex() != -1) {
                UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex()));
            }
            if (e.getButton() == 3) {
                UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(mountJPanel.idx));
                RoleSummoning roleSummoning = UserMessUntil.getChosePetMes();
                int index1 = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
                Mount mount = (Mount)ZhuJpanel.getListMount().get(index1);
                if (roleSummoning != null && FightingMixDeal.State == 0) {
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.ZQGZ, this, "#W确定要将#R" + roleSummoning.getSummoningname() + "#W被坐骑#G" + mount.getMountname() + "#W管制吗？");
                }
                return;
            }
            else {
                if (e.getButton() == 1 && UserMessUntil.getChosePetMes() != null) {
                    if (e.isShiftDown()) {}
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        if (UserMessUntil.getChosePetMes().getPetlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt("该召唤兽已加锁");
                        }
                        else {
                            UserMessUntil.getChosePetMes().setPetlock(1);
                            try {
                                SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                            }
                            catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                        }
                    }
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11) && UserMessUntil.getChosePetMes().getPetlock() == 1) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        UserMessUntil.getChosePetMes().setPetlock(0);
                        try {
                            SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                    }
                }
                if (this.listpet.getSelectedIndex() == -1) {
                    return;
                }
                if (((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getInnerGoods() != null && !UserMessUntil.getChosePetMes().getInnerGoods().equals("")) {
                    String[] strings = ((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getInnerGoods().split("\\|");
                    for (int i = 0; i < 4; ++i) {}
                    if (strings.length > 0) {
                        for (int i = 0; i < strings.length; ++i) {}
                    }
                }
                if (FormsManagement.getframe(18).isVisible()) {
                    ChosePetSkillsMouslisten.refreshPetSkills();
                }
                if (FormsManagement.getframe(601).isVisible()) {
                    ChosePetLxMouslisten.refreshPetSkills();
                }
                if (FormsManagement.getframe(602).isVisible()) {
                    FormsManagement.HideForm(602);
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().pettext();
        mountJPanel.idx = -1;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getY() / 24 < this.listpet.getModel().getSize()) {
            mountJPanel.idx = this.listpet.locationToIndex(e.getPoint());
        }
    }
    
    public void controlPet(RoleSummoning pet) {
        if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedValue() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择坐骑！");
            return;
        }
        int index1 = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        Mount mount = (Mount)ZhuJpanel.getListMount().get(index1);
        Mount mount2 = ZhuJpanel.getPetMount(pet.getSid());
        if (mount2 != null && mount != mount2) {
            ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽已被其他坐骑管制");
            return;
        }
        int type = 0;
        if (mount.getSid() != null && pet.getSid().compareTo(mount.getSid()) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请点击召唤兽头像解除管制");
            return;
        }
        if (mount.getOthrersid() != null && pet.getSid().compareTo(mount.getOthrersid()) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请点击召唤兽头像解除管制");
            return;
        }
        if (mount.getSid3() != null && pet.getSid().compareTo(mount.getSid3()) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请点击召唤兽头像解除管制");
            return;
        }
        if (mount.getSid() == null) {
            mount.setSid(pet.getSid());
            String path = "img/head/p" + pet.getSummoningskin() + ".png";
            File file = new File(path);
            if (file.exists()) {
                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                this.oum.getPetlist()[0].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(0), pet);
            }
            else {
                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                this.oum.getPetlist()[0].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(0), pet);
            }
            type = 1;
        }
        else if (mount.getOthrersid() == null) {
            mount.setOthrersid(pet.getSid());
            String path = "img/head/p" + pet.getSummoningskin() + ".png";
            File file = new File(path);
            if (file.exists()) {
                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                this.oum.getPetlist()[1].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(1), pet);
            }
            else {
                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                this.oum.getPetlist()[1].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(1), pet);
            }
            type = 2;
        }
        else if ((int)mount.getMountlvl() > 100 && mount.getSid3() == null) {
            mount.setSid3(pet.getSid());
            String path = "img/head/p" + pet.getSummoningskin() + ".png";
            File file = new File(path);
            if (file.exists()) {
                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                this.oum.getPetlist()[2].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(2), pet);
            }
            else {
                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                this.oum.getPetlist()[2].setIcon(icon2);
                this.oum.getP().put(Integer.valueOf(2), pet);
            }
            type = 3;
        }
        if (type == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("目前坐骑管制上限!");
            return;
        }
        if (type < 0) {
            ChangeMouseSymbolMouslisten.clearPropertie(pet);
        }
        else {
            ChangeMouseSymbolMouslisten.addProperties(pet, mount);
        }
        String sendmes = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(mount));
        SendMessageUntil.toServer(sendmes);
    }
}
