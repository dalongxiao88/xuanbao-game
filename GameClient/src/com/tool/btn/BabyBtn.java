package com.tool.btn;

import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.Jpanel.TestChildAttributeJpanel;
import org.come.model.PalData;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.bean.LoginResult;
import org.come.entity.Baby;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleProperty;
import org.come.Frame.PartnerJframe;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.Util;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.Frame.TestChildJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

public class BabyBtn extends MoBanBtn
{
    private int caozuo;
    
    public BabyBtn(String iconpath, int type, String text) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public BabyBtn(String iconpath, int type) {
        super(iconpath, type);
    }
    
    public BabyBtn(String iconpath, int type, Color[] colors, Font font, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
        if (baby == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的孩子");
        }
        else {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if (this.getText().equals("放生")) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Babyase, baby, "#W您确定要将:#G" + baby.getBabyname() + " #W放生吗?  #R放生后无法找回三思而后行");
            }
            if (this.getText().equals("寄养")) {
                if (loginResult.getBabyId() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("宝宝已参战不能寄养！");
                }
                else if (baby.getState() == null || !baby.getState().equals("1")) {
                    if (Util.isCanBuyOrno()) {
                        return;
                    }
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    if (mainJpanel.getPalDataChooseId() < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                        return;
                    }
                    Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
                    if (pidGetPal == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选中一个伙伴");
                        return;
                    }
                    PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                    String name = palData.getName();
                    if (pidGetPal.getBaby() != null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("伙伴【#G" + name + "#Y】已寄养了孩子，请先取回");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("#Y你是否要将孩子#R");
                    buffer.append(baby.getBabyname());
                    buffer.append("#Y寄养给伙伴#G" + name);
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palToBaby, baby, buffer.toString());
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("孩子已被寄养，请先取回");
                }
            }
            else if (this.getText().equals("取回")) {
                PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                int chooseId = mainJpanel.getPalDataChooseId();
                if (chooseId > 0) {
                    Pal pidGetPal2 = mainJpanel.pidGetPal(chooseId);
                    if (pidGetPal2.getBaby() != null) {
                        if (pidGetPal2.getBaby() != null) {
                            if (Util.isCanBuyOrno()) {
                                return;
                            }
                            PalData palData2 = UserMessUntil.getPalData(pidGetPal2.getpId());
                            String name2 = palData2.getName();
                            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palGetBaby, pidGetPal2.getBaby(), "#W确定要将给伙伴" + name2 + "寄养的BABY:#G" + pidGetPal2.getBaby().getBabyname() + "#W领回吗?");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有寄养给该伙伴BABY！");
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                }
            }
            else if (this.getText().equals("跟随")) {
                this.setText("取消");
            }
            else if (this.getText().equals("取消")) {
                this.setText("跟随");
            }
            else if (this.getText().equals("改")) {
                this.changname();
            }
            else if (this.getText().equals("待机")) {
                loginResult.setBabyId(null);
                RoleProperty.ResetBaby(null);
                this.setText("参战");
                String mes = Agreement.getAgreement().rolechangeAgreement("B");
                SendMessageUntil.toServer(mes);
            }
            else if (this.getText().equals("参战")) {
                RoleProperty.ResetBaby(baby);
                loginResult.setBabyId(baby.getBabyid());
                this.setText("待机");
                String mes = Agreement.getAgreement().rolechangeAgreement("B" + baby.getBabyid());
                SendMessageUntil.toServer(mes);
            }
        }
    }
    
    public void changname() {
        Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
        BigDecimal[] bigs = (BigDecimal[])((baby != null) ? baby.getpartAll() : null);
        try {
            if (UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid()).getpartAll() != null) {
                String lastname = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid()).getBabyname();
                TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel();
                if (TestChildAttributeJpanel.getLabPetname() != null) {
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel();
                    if (!TestChildAttributeJpanel.getLabPetname().getText().equals("")) {
                        TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel();
                        if (TestChildAttributeJpanel.getLabPetname().getText().length() > 7) {
                            ZhuFrame.getZhuJpanel().addPrompt("长?那是一种专属");
                            return;
                        }
                        Baby getbaby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
                        TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel();
                        getbaby.setBabyname(TestChildAttributeJpanel.getLabPetname().getText().trim());
                        String mes = Agreement.getAgreement().updababy(GsonUtil.getGsonUtil().getgson().toJson(UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid())));
                        SendMessageUntil.toServer(mes);
                        ZhuFrame.getZhuJpanel().addPrompt2("您的孩子 " + lastname + "成功改名为:" + UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid()).getBabyname());
                        TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel().showBaby(baby, bigs);
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt("请输入宝宝" + lastname + "的新名字！");
                return;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("请选择你要改名的宝宝！");
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
