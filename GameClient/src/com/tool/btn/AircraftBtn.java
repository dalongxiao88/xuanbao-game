package com.tool.btn;

import org.come.entity.Goodstable;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.bean.RoleShow;
import org.come.until.FormsManagement;
import com.tool.image.ManimgAttribute;
import org.come.until.ExpIncreaseUntil;
import org.come.entity.Fly;
import org.come.Frame.AircraftJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import org.come.Jpanel.ZhuJpanel;
import org.come.Jpanel.AircraftJPanel;

public class AircraftBtn extends MoBanBtn
{
    private int caozuo;
    private AircraftJPanel aircraftJPanel;
    private ZhuJpanel zhuJpanel;
    
    public AircraftBtn(String iconpath, int type, int caozuo, AircraftJPanel aircraftJPanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.aircraftJPanel = aircraftJPanel;
    }
    
    public AircraftBtn(String iconpath, int type, String text, Font font, Color[] colors, int caozuo, AircraftJPanel aircraftJPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.aircraftJPanel = aircraftJPanel;
    }
    
    public AircraftBtn(String iconpath, int type, String text, Font font, Color[] colors, int caozuo, ZhuJpanel zhuJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.zhuJpanel = zhuJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 0) {
            this.getGoodType(1101L);
        }
        else if (this.caozuo == 1) {
            this.getGoodType(1102L);
        }
        else if (this.caozuo == 2) {
            this.getGoodType(1103L);
        }
        else if (this.caozuo == 3) {
            if (this.aircraftJPanel.getFlyId() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个飞行器");
                return;
            }
            if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
                return;
            }
            if (Util.mapmodel.getGamemap().getFlyFlag().equals("0")) {
                ZhuFrame.getZhuJpanel().addPrompt2("当前地图无法飞行！");
                return;
            }
            this.rideOrReset();
        }
        else if (this.caozuo == 4) {
            this.getGoodType(1104L);
        }
        else {
            if (this.caozuo == 5) {
                ZhuFrame.getZhuJpanel().addPrompt2("暂未开放！");
                return;
            }
            if (this.caozuo == 10) {
                String sendmes = null;
                try {
                    sendmes = Agreement.getAgreement().MountAgreement();
                }
                catch (Exception var7) {
                    var7.printStackTrace();
                }
                sendmes = Agreement.getAgreement().MountAgreement();
                SendMessageUntil.toServer(sendmes);
                ZhuFrame.getZhuJpanel().showflybtn(true, 1);
                AircraftBtn[] aircraftBtns = this.zhuJpanel.getAircraftBtns();
                for (int i = 0; i < aircraftBtns.length; ++i) {
                    aircraftBtns[i].setVisible(false);
                }
            }
            else if (this.caozuo == 11) {
                String sendmes = null;
                sendmes = Agreement.getAgreement().FlyAgreement();
                SendMessageUntil.toServer(sendmes);
                ZhuFrame.getZhuJpanel().showflybtn(true, 1);
                AircraftBtn[] aircraftBtns = this.zhuJpanel.getAircraftBtns();
                for (int i = 0; i < aircraftBtns.length; ++i) {
                    aircraftBtns[i].setVisible(false);
                }
            }
            else if (this.caozuo == 12) {
                if (!FormsManagement.getframe(1002).isVisible()) {
                    FormsManagement.showForm(1002);
                    String sendmes111 = Agreement.getAgreement().CarAgreement();
                    SendMessageUntil.toServer(sendmes111);
                }
                else {
                    FormsManagement.HideForm(1002);
                }

                ZhuFrame.getZhuJpanel().showflybtn(true, 1);
                AircraftBtn[] aircraftBtns = this.zhuJpanel.getAircraftBtns();
                for (int i = 0; i < aircraftBtns.length; ++i) {
                    aircraftBtns[i].setVisible(false);
                }
            }
        }
    }
    
    public void rideOrReset() {
        AircraftJPanel aircraftJPanel = AircraftJframe.getAircraftJframe().getAircraftJPanel();
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getMapid().intValue() == 1732 || roleShow.getMapid().intValue() == 1761) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前地图不可以飞行");
            return;
        }
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
            return;
        }
        if (roleShow.getTroop_id() == null) {
            if (aircraftJPanel.getlistfly().getSelectedValue() != null) {
                int index = aircraftJPanel.getlistfly().getSelectedIndex();
                Fly fly = (Fly)ZhuJpanel.getListFly().get(index);
                if ((long)fly.getFuel() <= 0L) {
                    ZhuFrame.getZhuJpanel().addPrompt2("燃灵值不足，无法飞行，请补充！！！");
                    return;
                }
                if (aircraftJPanel.getBtnFight().getText().equals("飞行")) {
                    if (roleShow.getFly_id() != 0) {
                        aircraftJPanel.getmodelfly().set(index, "( * ) " + (String)aircraftJPanel.getmodelfly().get(index));
                        int i = 0;
                        while (i < ZhuJpanel.getListFly().size()) {
                            if ((int)((Fly)ZhuJpanel.getListFly().get(i)).getFlytid() == roleShow.getFly_id()) {
                                aircraftJPanel.getmodelfly().set(i, ((Fly)ZhuJpanel.getListFly().get(i)).getFlyname());
                                break;
                            }
                            else {
                                ++i;
                            }
                        }
                        roleShow.setFly_id((int)((Fly)ZhuJpanel.getListFly().get(index)).getFlytid());
                    }
                    else {
                        aircraftJPanel.getmodelfly().set(index, "( * ) " + (String)aircraftJPanel.getmodelfly().get(index));
                        roleShow.setFly_id((int)((Fly)ZhuJpanel.getListFly().get(index)).getFlytid());
                    }
                    ExpIncreaseUntil.ShouFlyValue((Fly)ZhuJpanel.getListFly().get(index));
                    ManimgAttribute.flyskin = this.aircraftJPanel.getSkin();
                    ManimgAttribute.ISFLY = true;
                    ManimgAttribute.IS = true;
                    ImageMixDeal.userimg.changeskin(null);
                    Util.oldFly_id = roleShow.getFly_id();
                    Util.oldFlyIndex = index;
                    String mes = Agreement.getAgreement().rolechangeAgreement("F" + roleShow.getFly_id() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getMid() + "=" + this.aircraftJPanel.getSkin());
                    SendMessageUntil.toServer(mes);
                    AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("待机");
                    FormsManagement.HideForm(119);
                }
                else if (AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().getText().equals("待机")) {
                    AircraftJframe.getAircraftJframe().getaircraftJPanel().getmodelfly().set(index, ((Fly)ZhuJpanel.getListFly().get(index)).getFlyname());
                    roleShow.setFly_id(0);
                    ManimgAttribute.isdaiji = true;
                    ImageMixDeal.userimg.changeskin(null);
                    String mes = Agreement.getAgreement().rolechangeAgreement("F");
                    SendMessageUntil.toServer(mes);
                    AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("飞行");
                    ManimgAttribute.ISFLY = false;
                    FormsManagement.HideForm(119);
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择您要飞行/待机的飞行器！");
            }
        }
        else {
            if (roleShow.getCaptian() != 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("您不是队长，不能使用飞行器！");
                return;
            }
            if (aircraftJPanel.getlistfly().getSelectedValue() != null) {
                int index = aircraftJPanel.getlistfly().getSelectedIndex();
                if (aircraftJPanel.getBtnFight().getText().equals("飞行")) {
                    String mes2 = Agreement.getAgreement().rolechangeAgreement("F" + roleShow.getFly_id() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getMid() + "=" + this.aircraftJPanel.getSkin());
                    SendMessageUntil.toServer(mes2);
                    FormsManagement.HideForm(119);
                }
                else if (AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().getText().equals("待机")) {
                    String mes2 = Agreement.getAgreement().rolechangeAgreement("F");
                    SendMessageUntil.toServer(mes2);
                    FormsManagement.HideForm(119);
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择您要飞行/待机的飞行器！");
            }
        }
    }
    
    public static void quickFlyZD() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getTroop_id() != null) {
            if (roleShow.getFly_id() != 0 && roleShow.getFlyskin() != null) {
                ManimgAttribute.flyskin = roleShow.getFlyskin();
                ManimgAttribute.ISFLY = true;
                ManimgAttribute.IS = true;
                ImageMixDeal.userimg.changeskin(null);
                Util.oldFly_id = roleShow.getFly_id();
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("待机");
            }
            else {
                roleShow.setFly_id(0);
                ManimgAttribute.isdaiji = true;
                ManimgAttribute.ISFLY = false;
                ImageMixDeal.userimg.changeskin(null);
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("飞行");
            }
        }
    }
    
    public void getGoodType(long type) {
        Goodstable[] goodslist = GoodsListFromServerUntil.getGoodslist();
        Goodstable goodstable = null;
        int i = 0;
        int len = goodslist.length;
        while (i < len) {
            if (goodslist[i] != null && (long)goodslist[i].getType() == type) {
                goodstable = goodslist[i];
                break;
            }
            else {
                ++i;
            }
        }
        if (goodstable == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("背包里没有对应物品,请先获取");
        }
        else {
            for (int l = ZhuJpanel.getListFly().size() - 1; l >= 0; --l) {
                Fly fly = (Fly)ZhuJpanel.getListFly().get(l);
                String flyname = "";
                if (fly.getFlyname() != null) {
                    flyname = fly.getFlyname() + "(" + AircraftJPanel.getJieshu((int)fly.getFlystate()) + "阶)";
                }
                if (flyname.equals(this.aircraftJPanel.getLabName().getText())) {
                    GoodsMouslisten.useFly(goodstable, fly);
                    ExpIncreaseUntil.ShouFlyValue(fly);
                }
            }
        }
    }
    
    public static void quickFly() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        int index = Util.oldFlyIndex;
        if (ZhuJpanel.getListFly().isEmpty()) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先购买飞行器！");
            return;
        }
        Fly fly = (Fly)ZhuJpanel.getListFly().get(index);
        if ((long)fly.getFuel() <= 0L) {
            ZhuFrame.getZhuJpanel().addPrompt2("燃灵值不足，无法飞行，请补充！！！");
            return;
        }
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
            return;
        }
        String stste = AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().getText();
        if (roleShow.getTroop_id() == null) {
            if (stste.equals("飞行")) {
                roleShow.setFly_id(Util.oldFly_id);
                if (roleShow.getFly_id() != 0) {
                    roleShow.setFly_id((int)((Fly)ZhuJpanel.getListFly().get(index)).getFlytid());
                }
                else {
                    roleShow.setFly_id((int)((Fly)ZhuJpanel.getListFly().get(index)).getFlytid());
                }
                ExpIncreaseUntil.ShouFlyValue((Fly)ZhuJpanel.getListFly().get(index));
                ManimgAttribute.flyskin = ((Fly)ZhuJpanel.getListFly().get(index)).getSkin();
                ManimgAttribute.ISFLY = true;
                ManimgAttribute.IS = true;
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("F" + roleShow.getFly_id() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getMid() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getSkin());
                SendMessageUntil.toServer(mes);
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("待机");
            }
            else if (stste.equals("待机")) {
                roleShow.setFly_id(0);
                ManimgAttribute.isdaiji = true;
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("F");
                SendMessageUntil.toServer(mes);
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("飞行");
                ManimgAttribute.ISFLY = false;
            }
        }
        else {
            if (roleShow.getCaptian() != 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("您不是队长，不能使用飞行器！");
                return;
            }
            if (stste.equals("飞行")) {
                String mes = Agreement.getAgreement().rolechangeAgreement("F" + roleShow.getFly_id() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getMid() + "=" + ((Fly)ZhuJpanel.getListFly().get(index)).getSkin());
                SendMessageUntil.toServer(mes);
            }
            else if (stste.equals("待机")) {
                String mes = Agreement.getAgreement().rolechangeAgreement("F");
                SendMessageUntil.toServer(mes);
            }
        }
    }
}
