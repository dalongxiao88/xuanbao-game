package com.tool.btn;

import com.tool.role.RoleData;
import com.tool.role.RoleProperty;
import org.come.Frame.*;
import org.come.Jpanel.*;
import org.come.entity.Car;
import org.come.entity.MountSkill;
import come.tool.JDialog.TiShiUtil;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.Vector;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import org.come.bean.RoleShow;
import org.come.until.ExpIncreaseUntil;
import org.come.entity.Mount;
import com.tool.image.ImageMixDeal;
import org.come.until.Music;
import org.come.until.FormsManagement;
import org.come.until.CutButtonImage;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Util;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

public class MountPanelBtn extends MoBanBtn
{
    private mountJPanel mountJPanel;
    private mountJPanel untJPanel;
    private carJPanel carjpanel;
    private int caozuo;
    private MountShouhuJpanel shouhuJpanel;
    
    public MountPanelBtn(String iconpath, int type, String text, mountJPanel mountJPanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.mountJPanel = mountJPanel;
    }
    
    public MountPanelBtn(String iconpath, int type, String text, mountJPanel mountJPanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.mountJPanel = mountJPanel;
    }
    
    public MountPanelBtn(String iconpath, int type, String text, mountJPanel untJPanel, int caozuo, Color[] color) {
        super(iconpath, type, color);
        this.untJPanel = untJPanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY19);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.untJPanel = untJPanel;
        this.caozuo = caozuo;
    }
    
    public MountPanelBtn(String iconpath, int type, String text, mountJPanel untJPanel, int caozuo) {
        super(iconpath, type, UIUtils.Black);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.untJPanel = untJPanel;
        this.caozuo = caozuo;
    }

    public MountPanelBtn(String iconpath, int type, String text, carJPanel carjpanel, int caozuo) {
        super(iconpath, type, UIUtils.Black);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.carjpanel = carjpanel;
        this.caozuo = caozuo;
    }

    public MountPanelBtn(String iconpath, int type, String text, MountShouhuJpanel untJPanel, int caozuo, Color[] color) {
        super(iconpath, type, color);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY19);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.shouhuJpanel = untJPanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1 && FightingMixDeal.State == 0) {
            this.rideOrReset();
        }
        else if (this.caozuo == 2 && FightingMixDeal.State == 0) {
            this.rideOrReset();
        }
        else if (this.caozuo == 3 && FightingMixDeal.State == 0) {
            this.controlPet();
        }
        else if (this.caozuo == 4 && FightingMixDeal.State == 0) {
            this.feedMount();
        }
        else if (this.caozuo == 5 && FightingMixDeal.State == 0) {
            this.mountSkill();
        }
        else if (this.caozuo == 6 && FightingMixDeal.State == 0) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            this.releaseMount();
        }
        else if (this.caozuo == 7 && FightingMixDeal.State == 0) {
            String sendmes = Agreement.getAgreement().MountAgreement();
            SendMessageUntil.toServer(sendmes);
            try {
                ((MountPanelBtn)this.untJPanel.getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
                ((MountPanelBtn)this.untJPanel.getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            FormsManagement.HideForm(559);
        }
        else if (this.caozuo == 8 && FightingMixDeal.State == 0) {
            try {
                String sendmes = Agreement.getAgreement().shouhuAgreement("open");
                SendMessageUntil.toServer(sendmes);
            }
            catch (Exception exception2) {
                exception2.printStackTrace();
            }
            FormsManagement.HideForm(7);
        }
        else if (this.caozuo == 9 && FightingMixDeal.State == 0) {
            String sendmes = Agreement.getAgreement().MountAgreement();
            SendMessageUntil.toServer(sendmes);
            try {
                ((MountPanelBtn)this.shouhuJpanel.getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
                ((MountPanelBtn)this.shouhuJpanel.getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            FormsManagement.HideForm(559);
        }
        else if (this.caozuo == 10 && FightingMixDeal.State == 0) {
            if (!FormsManagement.getframe(559).isVisible()) {
                FormsManagement.showForm(559);
                Music.addyinxiao("开关窗口.mp3");
            }
            try {
                ((MountPanelBtn)this.shouhuJpanel.getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
                ((MountPanelBtn)this.shouhuJpanel.getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
            }
            catch (Exception exception2) {
                exception2.printStackTrace();
            }
            FormsManagement.HideForm(7);
        }
        else if (this.caozuo == 11 && FightingMixDeal.State == 0) {
            rideOrResetCar();
        }

    }
    
    public void rideOrReset() {
        mountJPanel mountJPanel = MountJframe.getMountjframe().getMountjpanel();
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以乘骑坐骑！");
            return;
        }
        if (roleShow.getMapid().intValue() == 1732 || roleShow.getMapid().intValue() == 1761) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前地图不可以乘骑坐骑");
            return;
        }
        if (mountJPanel.getListmount().getSelectedValue() != null) {
            int index = mountJPanel.getListmount().getSelectedIndex();
            if (mountJPanel.getBtnRiding().getText().equals("骑乘")) {
                if (roleShow.getMount_id() != 0) {
                    mountJPanel.getModelmount().set(index, "( * ) " + (String)mountJPanel.getModelmount().get(index));
                    int i = 0;
                    while (i < ZhuJpanel.getListMount().size()) {
                        if ((int)((Mount)ZhuJpanel.getListMount().get(i)).getMountid() == roleShow.getMount_id()) {
                            mountJPanel.getModelmount().set(i, ((Mount)ZhuJpanel.getListMount().get(i)).getMountname());
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                    roleShow.setMount_id((int)((Mount)ZhuJpanel.getListMount().get(index)).getMountid());
                }
                else {
                    mountJPanel.getModelmount().set(index, "( * ) " + (String)mountJPanel.getModelmount().get(index));
                    roleShow.setMount_id((int)((Mount)ZhuJpanel.getListMount().get(index)).getMountid());
                }
                ExpIncreaseUntil.showMountValue((Mount)ZhuJpanel.getListMount().get(index));
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("M" + roleShow.getMount_id() + "=" + ((Mount)ZhuJpanel.getListMount().get(index)).getMid());
                SendMessageUntil.toServer(mes);
                MountJframe.getMountjframe().getMountjpanel().getBtnRiding().setText("休息");
            }
            else if (MountJframe.getMountjframe().getMountjpanel().getBtnRiding().getText().equals("休息")) {
                MountJframe.getMountjframe().getMountjpanel().getModelmount().set(index, ((Mount)ZhuJpanel.getListMount().get(index)).getMountname());
                roleShow.setMount_id(0);
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("M");
                SendMessageUntil.toServer(mes);
                MountJframe.getMountjframe().getMountjpanel().getBtnRiding().setText("骑乘");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择您要骑乘/休息的坐骑！");
        }
    }
    
    public void controlPet() {
        if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedValue() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择坐骑！");
            return;
        }
        mountJPanel mountJPanel = this.mountJPanel;
        if (org.come.Jpanel.mountJPanel.getListpet().getSelectedIndex() == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择你要管制的召唤兽！");
            return;
        }
        int index1 = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        mountJPanel mountJPanel2 = this.mountJPanel;
        int index2 = org.come.Jpanel.mountJPanel.getListpet().getSelectedIndex();
        RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(index2);
        Mount mount = (Mount)ZhuJpanel.getListMount().get(index1);
        Mount mount2 = ZhuJpanel.getPetMount(pet.getSid());
        if (mount2 != null && mount != mount2) {
            ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽已被其他坐骑管制");
            return;
        }
        int type = 0;
        if (mount.getSid() != null && pet.getSid().compareTo(mount.getSid()) == 0) {
            type = -1;
            mount.setSid(null);
        }
        else if (mount.getOthrersid() != null && pet.getSid().compareTo(mount.getOthrersid()) == 0) {
            type = -2;
            mount.setOthrersid(null);
        }
        else if (mount.getSid3() != null && pet.getSid().compareTo(mount.getSid3()) == 0) {
            type = -3;
            mount.setSid3(null);
        }
        else if (mount.getSid() == null) {
            mount.setSid(pet.getSid());
            type = 1;
        }
        else if (mount.getOthrersid() == null) {
            mount.setOthrersid(pet.getSid());
            type = 2;
        }
        else if ((int)mount.getMountlvl() > 100 && mount.getSid3() == null) {
            mount.setSid3(pet.getSid());
            type = 3;
        }
        if (type == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("管制召唤兽上限");
            return;
        }
        if (type < 0) {
            ChangeMouseSymbolMouslisten.clearPropertie(pet);
            Vector<String> list = (Vector<String>)MountJframe.getMountjframe().getMountjpanel().getVerVectors().get(index2);
            list.set(1, "");
        }
        else {
            ChangeMouseSymbolMouslisten.addProperties(pet, mount);
            Vector<String> list = (Vector<String>)MountJframe.getMountjframe().getMountjpanel().getVerVectors().get(index2);
            list.set(1, mount.getMountname().split("]")[1]);
        }
        String sendmes = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(mount));
        SendMessageUntil.toServer(sendmes);
    }
    
    public void feedMount() {
        ZhuJpanel.setUseGoodsType(2);
        TestpackJframe.getTestpackJframe().setLocation(500, 80);
        FormsManagement.upgradForm(2);
    }
    
    public void mountSkill() {
        if (ZhuJpanel.getListMount() != null) {
            if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex() != -1) {
                int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
                MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getLabProficiency().setText(((Mount)ZhuJpanel.getListMount().get(index)).getProficiency() + "");
                if (((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().size() != 0) {
                    refreshMountSkills(index);
                }
                else {
                    MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().showSkillMsg(null);
                    MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getModelmountskill().removeAllElements();
                    ZhuFrame.getZhuJpanel().addPrompt2("该坐骑还没有技能，赶紧去学习吧！");
                }
                FormsManagement.showForm(20);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("您还没有坐骑，赶快去获取吧！");
            }
        }
    }
    
    public void releaseMount() {
        if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex() == -1) {
            ZhuFrame.getZhuJpanel().addPrompt("请选择您要放生的坐骑！");
            return;
        }
        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        Mount mount = (Mount)ZhuJpanel.getListMount().get(index);
        if (ImageMixDeal.userimg.getRoleShow().getMount_id() != 0 && ImageMixDeal.userimg.getRoleShow().getMount_id() == (int)mount.getMountid()) {
            ZhuFrame.getZhuJpanel().addPrompt("您的坐骑还被您骑着呢！");
            return;
        }
        if (mount.getSid() != null || mount.getOthrersid() != null || mount.getSid3() != null || mount.getSid4() != null || mount.getSid5() != null) {
            ZhuFrame.getZhuJpanel().addPrompt("您的坐骑管制着召唤兽呢！");
            return;
        }
        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Release, mount, "#W确定要将坐骑:#G" + mount.getMountname() + "#W放生吗?");
    }
    
    public static void refreshMountSkills(int index) {
        MountSkillsJpanel jpanel = MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel();
        jpanel.getModelmountskill().removeAllElements();
        jpanel.getListmountskill().removeAll();
        for (int i = 0; i < ((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().size(); ++i) {
            jpanel.getModelmountskill().add(i, ((MountSkill)((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().get(i)).getSkillname());
        }
        jpanel.getListmountskill().setSelectedIndex(0);
        jpanel.showSkillMsg((Mount)ZhuJpanel.getListMount().get(index), 0);
    }

    public void rideOrResetCar() {
        carJPanel carjpanel1 = CarJframe.getMountjframe().getCarjpanel();
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以乘骑坐驾！");
            return;
        }
        if (roleShow.getMapid().intValue() == 1732 || roleShow.getMapid().intValue() == 1761) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前地图不可以乘骑坐驾");
            return;
        }
        if (carjpanel1.getListmount().getSelectedValue() != null) {
            int index = carjpanel1.getListmount().getSelectedIndex();
            Car car1 = null;
            if (carjpanel1.getBtnRiding().getText().equals("骑乘")) {
                if (roleShow.getCar_id() != 0) {
                    carjpanel1.getModelmount().set(index, "( * ) " + (String)carjpanel1.getModelmount().get(index));
                    int i = 0;
                    while (i < ZhuJpanel.getListCar().size()) {
                        if ((int)((Car)ZhuJpanel.getListCar().get(i)).getMountid() == roleShow.getCar_id()) {
                            carjpanel1.getModelmount().set(i, ((Car)ZhuJpanel.getListCar().get(i)).getMountname());
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                    roleShow.setCar_id((int)((Car)ZhuJpanel.getListCar().get(index)).getMountid());
                }
                else {
                    carjpanel1.getModelmount().set(index, "( * ) " + (String)carjpanel1.getModelmount().get(index));
                    roleShow.setCar_id((int)((Car)ZhuJpanel.getListCar().get(index)).getMountid());
                }
                ExpIncreaseUntil.showCarValue((Car)ZhuJpanel.getListCar().get(index));
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("CAR" + roleShow.getCar_id() + "=" + ((Car)ZhuJpanel.getListCar().get(index)).getMid());
                SendMessageUntil.toServer(mes);
                CarJframe.getMountjframe().getCarjpanel().getBtnRiding().setText("休息");
                car1 = ZhuJpanel.getListCar().get(index);
            }
            else if (CarJframe.getMountjframe().getCarjpanel().getBtnRiding().getText().equals("休息")) {
                CarJframe.getMountjframe().getCarjpanel().getModelmount().set(index, ((Car)ZhuJpanel.getListCar().get(index)).getMountname());
                roleShow.setCar_id(0);
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("CAR");
                SendMessageUntil.toServer(mes);
                CarJframe.getMountjframe().getCarjpanel().getBtnRiding().setText("骑乘");
            }
            if (car1 == null) {
                RoleData.getRoleData().getLoginResult().setCar_Value(null);
            } else {
                StringBuffer SB = new StringBuffer();
                if (car1.getBone() != null && car1.getBone() != 0) {
                    SB.append("根骨="+car1.getBone());
                    SB.append("|");
                }
                if (car1.getSpri() != null && car1.getSpri() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("灵性="+car1.getSpri());
                }
                if (car1.getPower() != null && car1.getPower() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("力量="+car1.getPower());
                }
                if (car1.getGradeexp() != null && car1.getGradeexp() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("敏捷="+car1.getGradeexp());
                }
                if (SB != null && SB.length()>0) {
                    RoleData.getRoleData().getLoginResult().setCar_Value(SB.toString());
                }
            }
            RoleProperty.getRoleProperty().equipWearOff();
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择您要骑乘/休息的坐驾！");
        }
    }
}
