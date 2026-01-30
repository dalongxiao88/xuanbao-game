package com.tool.btn;

import java.util.Random;
import java.util.List;

import org.come.XuanBao.XuanBaoPackJ_xuanyunpanel;
import org.come.XuanBao.XuanBaoPackJpanel;
import org.come.bean.LoginResult;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GsonUtilsb;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.entity.Goodstable;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.MountShouHu.ShouHu;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.until.UserMessUntil;
import org.come.entity.Mount;
import org.come.MountShouHu.MyMountRenderer;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.Frame.MountShouhuJframe;
import javax.swing.ImageIcon;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcp.Sprite;
import org.come.MountShouHu.ShouhuPackJpanel;
import org.come.MountShouHu.LvlupJpanel;
import org.come.MountShouHu.RandFJpanel;
import org.come.MountShouHu.xuanzeJpanel;
import org.come.Jpanel.MountShouhuJpanel;

public class MountShouhuBtn extends MoBanBtn
{
    int caozuo;
    MountShouhuJpanel mountShouhuJpanel;
    xuanzeJpanel xuanzeJpanel;
    RandFJpanel randFJpanel;
    LvlupJpanel lvlupJpanel;
    ShouhuPackJpanel shouhuPackJpanel;
    private Sprite sprite;
    XuanBaoPackJ_xuanyunpanel xuanBaoPackJ_xuanyunpanel;
    XuanBaoPackJpanel xuanBaoPackJpanel;
    public MountShouhuBtn(String iconpath, int type, int caozuo, XuanBaoPackJpanel shouhuPackJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.xuanBaoPackJpanel = shouhuPackJpanel;
    }
    public MountShouhuBtn(String iconpath, int type, int caozuo, XuanBaoPackJ_xuanyunpanel shouhuPackJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.xuanBaoPackJ_xuanyunpanel = shouhuPackJpanel;
    }
    public MountShouhuBtn(String iconpath, int type, int caozuo, LvlupJpanel lvlupJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.lvlupJpanel = lvlupJpanel;
    }



    public MountShouhuBtn(String iconpath, int type, int caozuo, ShouhuPackJpanel shouhuPackJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.shouhuPackJpanel = shouhuPackJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, MountShouhuJpanel mountShouhuJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.mountShouhuJpanel = mountShouhuJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, MountShouhuJpanel mountShouhuJpanel, Sprite sprite, int sx, int sy, boolean is) {
        super(iconpath, type, sprite, sx, sy, is);
        this.caozuo = caozuo;
        this.mountShouhuJpanel = mountShouhuJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, xuanzeJpanel xuanzeJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.xuanzeJpanel = xuanzeJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, MountShouhuJpanel mountShouhuJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.mountShouhuJpanel = mountShouhuJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, LvlupJpanel lvlupJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.lvlupJpanel = lvlupJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, RandFJpanel randFJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.randFJpanel = randFJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type, int caozuo, RandFJpanel randFJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.randFJpanel = randFJpanel;
    }
    
    public MountShouhuBtn(String iconpath, int type) {
        super(iconpath, type);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent p0) {
        try {
            switch (this.caozuo) {
                case 0: {
                    this.mountShouhuJpanel.getQinglong().setIcons(CutButtonImage.cuts("inkImg/Client/青龙_选中.png"));
                    this.mountShouhuJpanel.getBaihu().setIcons(CutButtonImage.cuts("inkImg/Client/白虎_未选中.png"));
                    this.mountShouhuJpanel.getXuanwu().setIcons(CutButtonImage.cuts("inkImg/Client/玄武_未选中.png"));
                    this.mountShouhuJpanel.getZhuque().setIcons(CutButtonImage.cuts("inkImg/Client/朱雀_未选中.png"));
                    this.mountShouhuJpanel.getZhongtian().setIcons(CutButtonImage.cuts("inkImg/Client/中天_未选中.png"));
                    this.mountShouhuJpanel.setIco(new ImageIcon("inkImg/Client/四象背景.png"));
                    this.mountShouhuJpanel.getSixiang1().setVisible(true);
                    this.mountShouhuJpanel.getSixiang2().setVisible(true);
                    this.mountShouhuJpanel.setXuanzhong(1);
                    this.mountShouhuJpanel.shou();
                    this.mountShouhuJpanel.getQinglong().setIschoose(true);
                    this.mountShouhuJpanel.getZhongtian().setIschoose(false);
                    this.mountShouhuJpanel.getBaihu().setIschoose(false);
                    this.mountShouhuJpanel.getXuanwu().setIschoose(false);
                    this.mountShouhuJpanel.getZhuque().setIschoose(false);
                    break;
                }
                case 1: {
                    this.mountShouhuJpanel.getQinglong().setIcons(CutButtonImage.cuts("inkImg/Client/青龙_未选中.png"));
                    this.mountShouhuJpanel.getBaihu().setIcons(CutButtonImage.cuts("inkImg/Client/白虎_未选中.png"));
                    this.mountShouhuJpanel.getXuanwu().setIcons(CutButtonImage.cuts("inkImg/Client/玄武_选中.png"));
                    this.mountShouhuJpanel.getZhuque().setIcons(CutButtonImage.cuts("inkImg/Client/朱雀_未选中.png"));
                    this.mountShouhuJpanel.getZhongtian().setIcons(CutButtonImage.cuts("inkImg/Client/中天_未选中.png"));
                    this.mountShouhuJpanel.setIco(new ImageIcon("inkImg/Client/四象背景.png"));
                    this.mountShouhuJpanel.getSixiang1().setVisible(true);
                    this.mountShouhuJpanel.getSixiang2().setVisible(true);
                    this.mountShouhuJpanel.setXuanzhong(3);
                    this.mountShouhuJpanel.shou();
                    this.mountShouhuJpanel.getQinglong().setIschoose(false);
                    this.mountShouhuJpanel.getZhongtian().setIschoose(false);
                    this.mountShouhuJpanel.getBaihu().setIschoose(false);
                    this.mountShouhuJpanel.getXuanwu().setIschoose(true);
                    this.mountShouhuJpanel.getZhuque().setIschoose(false);
                    break;
                }
                case 2: {
                    this.mountShouhuJpanel.getQinglong().setIcons(CutButtonImage.cuts("inkImg/Client/青龙_未选中.png"));
                    this.mountShouhuJpanel.getBaihu().setIcons(CutButtonImage.cuts("inkImg/Client/白虎_未选中.png"));
                    this.mountShouhuJpanel.getXuanwu().setIcons(CutButtonImage.cuts("inkImg/Client/玄武_未选中.png"));
                    this.mountShouhuJpanel.getZhuque().setIcons(CutButtonImage.cuts("inkImg/Client/朱雀_选中.png"));
                    this.mountShouhuJpanel.getZhongtian().setIcons(CutButtonImage.cuts("inkImg/Client/中天_未选中.png"));
                    this.mountShouhuJpanel.setIco(new ImageIcon("inkImg/Client/四象背景.png"));
                    this.mountShouhuJpanel.getSixiang1().setVisible(true);
                    this.mountShouhuJpanel.getSixiang2().setVisible(true);
                    this.mountShouhuJpanel.setXuanzhong(4);
                    this.mountShouhuJpanel.shou();
                    this.mountShouhuJpanel.getQinglong().setIschoose(false);
                    this.mountShouhuJpanel.getZhongtian().setIschoose(false);
                    this.mountShouhuJpanel.getBaihu().setIschoose(false);
                    this.mountShouhuJpanel.getXuanwu().setIschoose(false);
                    this.mountShouhuJpanel.getZhuque().setIschoose(true);
                    break;
                }
                case 3: {
                    this.mountShouhuJpanel.getQinglong().setIcons(CutButtonImage.cuts("inkImg/Client/青龙_未选中.png"));
                    this.mountShouhuJpanel.getBaihu().setIcons(CutButtonImage.cuts("inkImg/Client/白虎_选中.png"));
                    this.mountShouhuJpanel.getXuanwu().setIcons(CutButtonImage.cuts("inkImg/Client/玄武_未选中.png"));
                    this.mountShouhuJpanel.getZhuque().setIcons(CutButtonImage.cuts("inkImg/Client/朱雀_未选中.png"));
                    this.mountShouhuJpanel.getZhongtian().setIcons(CutButtonImage.cuts("inkImg/Client/中天_未选中.png"));
                    this.mountShouhuJpanel.setIco(new ImageIcon("inkImg/Client/四象背景.png"));
                    this.mountShouhuJpanel.getSixiang1().setVisible(true);
                    this.mountShouhuJpanel.getSixiang2().setVisible(true);
                    this.mountShouhuJpanel.setXuanzhong(2);
                    this.mountShouhuJpanel.shou();
                    this.mountShouhuJpanel.getQinglong().setIschoose(false);
                    this.mountShouhuJpanel.getZhongtian().setIschoose(false);
                    this.mountShouhuJpanel.getBaihu().setIschoose(true);
                    this.mountShouhuJpanel.getXuanwu().setIschoose(false);
                    this.mountShouhuJpanel.getZhuque().setIschoose(false);
                    break;
                }
                case 4: {
                    this.mountShouhuJpanel.getQinglong().setIcons(CutButtonImage.cuts("inkImg/Client/青龙_未选中.png"));
                    this.mountShouhuJpanel.getBaihu().setIcons(CutButtonImage.cuts("inkImg/Client/白虎_未选中.png"));
                    this.mountShouhuJpanel.getXuanwu().setIcons(CutButtonImage.cuts("inkImg/Client/玄武_未选中.png"));
                    this.mountShouhuJpanel.getZhuque().setIcons(CutButtonImage.cuts("inkImg/Client/朱雀_未选中.png"));
                    this.mountShouhuJpanel.getZhongtian().setIcons(CutButtonImage.cuts("inkImg/Client/中天_选中.png"));
                    this.mountShouhuJpanel.setIco(new ImageIcon("inkImg/Client/守护背景.png"));
                    this.mountShouhuJpanel.getSixiang1().setVisible(false);
                    this.mountShouhuJpanel.getSixiang2().setVisible(false);
                    this.mountShouhuJpanel.setXuanzhong(0);
                    this.mountShouhuJpanel.shou();
                    this.mountShouhuJpanel.getQinglong().setIschoose(false);
                    this.mountShouhuJpanel.getZhongtian().setIschoose(true);
                    this.mountShouhuJpanel.getBaihu().setIschoose(false);
                    this.mountShouhuJpanel.getXuanwu().setIschoose(false);
                    this.mountShouhuJpanel.getZhuque().setIschoose(false);
                    break;
                }
                case 5: {
                    MountShouhuJpanel mountShouhuJpanel = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel();
                    if (mountShouhuJpanel == null) {
                        return;
                    }
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    if (loginResult.getGold().longValue() < 300000L) {
                        ZhuFrame.getZhuJpanel().addPrompt("金钱不足");
                        return;
                    }
                    if (MyMountRenderer.select != -1 && mountShouhuJpanel.getXuanzhong() != 0 && mountShouhuJpanel.SX != -1) {
                        String name = ((Mount)UserMessUntil.getMountlsit().get(MyMountRenderer.select)).getMountname();
                        if (loginResult.getZhongtian() == null) {
                            ZhuFrame.getZhuJpanel().addPrompt("请先升级一下等级");
                            return;
                        }
                        String[] mes2 = loginResult.getZhongtian().split("\\|")[2].split("\\*");
                        if (((Mount)UserMessUntil.getMountlsit().get(MyMountRenderer.select)).getSh() != 0) {
                            ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                            return;
                        }
                        int l = 0;
                        if (name != null) {
                            if (name.contains("一")) {
                                if ((loginResult.getZhongtian() != null && Integer.parseInt(mes2[0]) == 1) || Integer.parseInt(mes2[1]) == 1) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 1, mountShouhuJpanel.SX);
                                l = 1;
                            }
                            else if (name.contains("二")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("2")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 2, mountShouhuJpanel.SX);
                                l = 2;
                            }
                            else if (name.contains("三")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("3")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 3, mountShouhuJpanel.SX);
                                l = 3;
                            }
                            else if (name.contains("四")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("4")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 4, mountShouhuJpanel.SX);
                                l = 4;
                            }
                            else if (name.contains("五")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("5")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 5, mountShouhuJpanel.SX);
                                l = 5;
                            }
                            else if (name.contains("六")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("6")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 6, mountShouhuJpanel.SX);
                                l = 6;
                            }
                            else if (name.contains("七")) {
                                if (loginResult.getZhongtian() != null && loginResult.getZhongtian().split("\\|")[2].contains("7")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                setsixiang(mountShouhuJpanel, 7, mountShouhuJpanel.SX);
                                l = 7;
                            }
                            if (mountShouhuJpanel.SX == 1) {
                                mountShouhuJpanel.getSixiang1().setBounds(360, 142, 60, 60);
                                String[] m = loginResult.getSh().split("\\|");
                                m[mountShouhuJpanel.getXuanzhong() - 1] = m[mountShouhuJpanel.getXuanzhong() - 1].split("-")[0] + "-" + l + "-" + m[mountShouhuJpanel.getXuanzhong() - 1].split("-")[2];
                                loginResult.setSh(m[0] + "|" + m[1] + "|" + m[2] + "|" + m[3]);
                            }
                            else {
                                mountShouhuJpanel.getSixiang2().setBounds(580, 142, 60, 60);
                                String[] m = loginResult.getSh().split("\\|");
                                m[mountShouhuJpanel.getXuanzhong() - 1] = m[mountShouhuJpanel.getXuanzhong() - 1].split("-")[0] + "-" + m[mountShouhuJpanel.getXuanzhong() - 1].split("-")[1] + "-" + l;
                                loginResult.setSh(m[0] + "|" + m[1] + "|" + m[2] + "|" + m[3]);
                            }
                        }
                        if (loginResult.getZhongtian() != null) {
                            String[] mes3 = loginResult.getZhongtian().split("\\|");
                            if (mountShouhuJpanel.SX == 1) {
                                mes3[2] = l + "*" + mes3[2].split("\\*")[1];
                            }
                            else {
                                mes3[2] = mes3[2].split("\\*")[0] + "*" + l;
                            }
                            loginResult.setZhongtian(mes3[0] + "|" + mes3[1] + "|" + mes3[2]);
                        }
                        else {
                            loginResult.setZhongtian("-1|-1|" + ((mountShouhuJpanel.SX == 1) ? (l + "*-1") : ("-1*" + l)));
                        }
                        mountShouhuJpanel.shouHu.setZhongtianmount(loginResult.getZhongtian().split("\\|")[2]);
                        SendMessageUntil.toServer(Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(mountShouhuJpanel.shouHu)));
                        ((Mount)UserMessUntil.getMountlsit().get(MyMountRenderer.select)).setSh(mountShouhuJpanel.getXuanzhong());
                        String sendmes1 = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(UserMessUntil.getMountlsit().get(MyMountRenderer.select)));
                        SendMessageUntil.toServer(sendmes1);
                        sendmes1 = Agreement.getAgreement().rolechangeAgreement("SSH&" + loginResult.getSh());
                        SendMessageUntil.toServer(sendmes1);
                    }
                    else if (mountShouhuJpanel.zuoqi != -1) {
                        String[] mes4 = RoleData.getRoleData().getLoginResult().getJiesuo().split("\\|");
                        String name2 = ((Mount)UserMessUntil.getMountlsit().get(MyMountRenderer.select)).getMountname();
                        String i = null;
                        if (name2 != null) {
                            if (name2.contains("一")) {
                                if (Integer.parseInt(mes4[0]) == 1 || Integer.parseInt(mes4[1]) == 1 || Integer.parseInt(mes4[2]) == 1 || Integer.parseInt(mes4[3]) == 1 || Integer.parseInt(mes4[4]) == 1 || Integer.parseInt(mes4[5]) == 1 || Integer.parseInt(mes4[6]) == 1) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "1.png"));
                                i = "1";
                            }
                            else if (name2.contains("二")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("2")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "2.png"));
                                i = "2";
                            }
                            else if (name2.contains("三")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("3")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "3.png"));
                                i = "3";
                            }
                            else if (name2.contains("四")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("4")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "4.png"));
                                i = "4";
                            }
                            else if (name2.contains("五")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("5")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "5.png"));
                                i = "5";
                            }
                            else if (name2.contains("六")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("6")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "6.png"));
                                i = "6";
                            }
                            else if (name2.contains("七")) {
                                if (RoleData.getRoleData().getLoginResult().getJiesuo().contains("7")) {
                                    ZhuFrame.getZhuJpanel().addPrompt("该坐骑已经被守护，请选择其他坐骑");
                                    return;
                                }
                                mountShouhuJpanel.sixiang[mountShouhuJpanel.zuoqi - 1].setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + "7.png"));
                                i = "7";
                            }
                        }
                        mes4[mountShouhuJpanel.zuoqi - 1] = i;
                        RoleData.getRoleData().getLoginResult().setJiesuo(mes4[0] + "|" + mes4[1] + "|" + mes4[2] + "|" + mes4[3] + "|" + mes4[4] + "|" + mes4[5] + "|" + mes4[6]);
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Shouhu&" + RoleData.getRoleData().getLoginResult().getJiesuo()));
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("monshuanimabi&300000"));
                        loginResult.setGold(BigDecimal.valueOf(loginResult.getGold().longValue() - 300000L));
                    }
                    FormsManagement.HideForm(2255);
                    break;
                }
                case 6: {
                    FormsManagement.showForm(2258);
                    break;
                }
                case 7: {
                    FormsManagement.showForm(2256);
                    break;
                }
                case 8: {
                    this.randFJpanel.setType(1);
                    try {
                        this.randFJpanel.getXuanxiangka1().setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
                        this.randFJpanel.getXuanxiangka2().setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 9: {
                    this.randFJpanel.setType(2);
                    try {
                        this.randFJpanel.getXuanxiangka1().setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
                        this.randFJpanel.getXuanxiangka2().setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 10: {
                    int k = 0;
                    k = this.lvlupJpanel.getTishengdianshu() + 1;
                    if (k >= (this.lvlupJpanel.lvl + 1) * 6) {
                        k = (this.lvlupJpanel.lvl + 1) * 6 - this.lvlupJpanel.xiuweidian;
                    }
                    this.lvlupJpanel.setTishengdianshu(k);
                    break;
                }
                case 11: {
                    if (this.lvlupJpanel.getTishengdianshu() >= 1) {
                        this.lvlupJpanel.setTishengdianshu(this.lvlupJpanel.getTishengdianshu() - 1);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 12: {
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    if (loginResult.getGold().longValue() - 20000000L < 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt("金钱不足");
                        return;
                    }
                    if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
                        if (loginResult.getQinglong() == null || loginResult.getZhuque() == null || loginResult.getBaihu() == null || loginResult.getXuanwu() == null) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getQinglong().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getZhuque().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getBaihu().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
                        if (loginResult.getQinglong() != null && Integer.parseInt(loginResult.getQinglong().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("青龙最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
                        if (loginResult.getBaihu() != null && Integer.parseInt(loginResult.getBaihu().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("白虎最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
                        if (loginResult.getXiuwei() != null && Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("玄武最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4 && loginResult.getZhuque() != null && Integer.parseInt(loginResult.getZhuque().split("\\|")[0]) >= 24) {
                        ZhuFrame.getZhuJpanel().addPrompt("朱雀最高等级为24级");
                        return;
                    }
                    if (this.lvlupJpanel.xiuweidian >= (this.lvlupJpanel.lvl + 1) * 6) {
                        this.lvlupJpanel.type = false;
                        this.lvlupJpanel.up.setVisible(false);
                        this.lvlupJpanel.down.setVisible(false);
                        this.lvlupJpanel.yijiantisheng.setVisible(false);
                        this.lvlupJpanel.tishengxiuweidian.setVisible(false);
                        this.lvlupJpanel.tupo.setVisible(true);
                        break;
                    }
                    else {
                        int x = this.lvlupJpanel.xiuweidian + 1;
                        int l = this.lvlupJpanel.lvl;
                        ShouHu shouHu = new ShouHu();
                        if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
                            shouHu.setZhongtianxiuweidian(x);
                            shouHu.setZhongtianlvl(l);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
                            shouHu.setQinglongxiuweidian(x);
                            shouHu.setQinglonglvl(l);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
                            shouHu.setBaihuxiuweidian(x);
                            shouHu.setBaihulvl(l);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
                            shouHu.setXuanwuxiuweidian(x);
                            shouHu.setXuanwulvl(l);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4) {
                            shouHu.setZhuquexiuweidian(x);
                            shouHu.setZhuquelvl(l);
                        }
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("monshuanimabi&20000000"));
                        RoleData.getRoleData().getLoginResult().setGold(BigDecimal.valueOf(RoleData.getRoleData().getLoginResult().getGold().longValue() - 20000000L));
                        String sendmes2 = Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(shouHu));
                        SendMessageUntil.toServer(sendmes2);
                        break;
                    }
                }
                case 13: {
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    if (loginResult.getGold().longValue() - 20000000L * (long)this.lvlupJpanel.tishengdianshu < 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt("金钱不足");
                        return;
                    }
                    if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
                        if (loginResult.getQinglong() == null || loginResult.getZhuque() == null || loginResult.getBaihu() == null || loginResult.getXuanwu() == null) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getQinglong().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getZhuque().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                        if (Integer.parseInt(loginResult.getBaihu().split("\\|")[0]) <= Integer.parseInt(loginResult.getZhongtian().split("\\|")[0])) {
                            ZhuFrame.getZhuJpanel().addPrompt("中天等级不能超过四象等级的最低等级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
                        if (loginResult.getQinglong() != null && Integer.parseInt(loginResult.getQinglong().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("青龙最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
                        if (loginResult.getBaihu() != null && Integer.parseInt(loginResult.getBaihu().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("白虎最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
                        if (loginResult.getXiuwei() != null && Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]) >= 24) {
                            ZhuFrame.getZhuJpanel().addPrompt("玄武最高等级为24级");
                            return;
                        }
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4 && loginResult.getZhuque() != null && Integer.parseInt(loginResult.getZhuque().split("\\|")[0]) >= 24) {
                        ZhuFrame.getZhuJpanel().addPrompt("朱雀最高等级为24级");
                        return;
                    }
                    if (this.lvlupJpanel.xiuweidian >= (this.lvlupJpanel.lvl + 1) * 6) {
                        this.lvlupJpanel.type = false;
                        this.lvlupJpanel.up.setVisible(false);
                        this.lvlupJpanel.down.setVisible(false);
                        this.lvlupJpanel.yijiantisheng.setVisible(false);
                        this.lvlupJpanel.tishengxiuweidian.setVisible(false);
                        this.lvlupJpanel.tupo.setVisible(true);
                        break;
                    }
                    else {
                        int x = this.lvlupJpanel.xiuweidian + this.lvlupJpanel.tishengdianshu;
                        int c = 0;
                        if (x > (this.lvlupJpanel.lvl + 1) * 6) {
                            x = (this.lvlupJpanel.lvl + 1) * 6;
                            c = x - this.lvlupJpanel.xiuweidian;
                        }
                        else {
                            c = this.lvlupJpanel.tishengdianshu;
                        }
                        int j = this.lvlupJpanel.lvl;
                        ShouHu shouHu2 = new ShouHu();
                        if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
                            shouHu2.setZhongtianxiuweidian(x);
                            shouHu2.setZhongtianlvl(j);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
                            shouHu2.setQinglongxiuweidian(x);
                            shouHu2.setQinglonglvl(j);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
                            shouHu2.setBaihuxiuweidian(x);
                            shouHu2.setBaihulvl(j);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
                            shouHu2.setXuanwuxiuweidian(x);
                            shouHu2.setXuanwulvl(j);
                        }
                        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4) {
                            shouHu2.setZhuquexiuweidian(x);
                            shouHu2.setZhuquelvl(j);
                        }
                        long s = 20000000L * (long)c;
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("monshuanimabi&" + s));
                        RoleData.getRoleData().getLoginResult().setGold(BigDecimal.valueOf(loginResult.getGold().longValue() - 20000000L * (long)c));
                        String sendmes3 = Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(shouHu2));
                        SendMessageUntil.toServer(sendmes3);
                        break;
                    }
                }
                case 14: {
                    Goodstable ltrGoods1 = getGoodType(2258L);
                    int num1 = (ltrGoods1 == null) ? 0 : ((int)ltrGoods1.getUsetime());
                    if (num1 < (this.lvlupJpanel.lvl + 1) * 12 || ltrGoods1 == null) {
                        ZhuFrame.getZhuJpanel().addPrompt("灵元晶不足");
                        return;
                    }
                    ltrGoods1.goodxh((this.lvlupJpanel.lvl + 1) * 12);
                    num1 = (int)ltrGoods1.getUsetime();
                    if (num1 <= 0) {
                        GoodsListFromServerUntil.shouhu(ltrGoods1.getRgid().intValue());
                    }
                    String kk = "jiesuo&" + (this.lvlupJpanel.lvl + 1) * 12 + "&" + ltrGoods1.getRgid();
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement(kk));
                    this.lvlupJpanel.xiuweidian = 0;
                    this.lvlupJpanel.type = true;
                    this.lvlupJpanel.up.setVisible(true);
                    this.lvlupJpanel.down.setVisible(true);
                    this.lvlupJpanel.yijiantisheng.setVisible(true);
                    this.lvlupJpanel.tishengxiuweidian.setVisible(true);
                    this.lvlupJpanel.tupo.setVisible(false);
                    int x2 = this.lvlupJpanel.xiuweidian;
                    int l2 = this.lvlupJpanel.lvl + 1;
                    ShouHu shouHu3 = new ShouHu();
                    if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
                        shouHu3.setZhongtianxiuweidian(x2);
                        shouHu3.setZhongtianlvl(l2);
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
                        shouHu3.setQinglongxiuweidian(x2);
                        shouHu3.setQinglonglvl(l2);
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
                        shouHu3.setBaihuxiuweidian(x2);
                        shouHu3.setBaihulvl(l2);
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
                        shouHu3.setXuanwuxiuweidian(x2);
                        shouHu3.setXuanwulvl(l2);
                    }
                    else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4) {
                        shouHu3.setZhuquexiuweidian(x2);
                        shouHu3.setZhuquelvl(l2);
                    }
                    String sendmes3 = Agreement.getAgreement().shouhuAgreement(GsonUtil.getGsonUtil().getgson().toJson(shouHu3));
                    SendMessageUntil.toServer(sendmes3);
                    break;
                }
                case 15: {
                    int i2 = 0;
                    i2 = ((ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().page == 0) ? 1 : ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().page) * ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1();
                    if (i2 < 0 || ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().size() == 0) {
                        return;
                    }
                    Goodstable goodstable = (Goodstable)ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(i2);
                    Goodstable ltrGoods2 = getGoodType(2258L);
                    int num2 = (ltrGoods2 == null) ? 0 : ((int)ltrGoods2.getUsetime());
                    if (num2 <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt("元灵石不足");
                        return;
                    }
                    if (RoleData.getRoleData().getLoginResult().getGold().longValue() - 500000L < 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt("金钱不足");
                        return;
                    }
                    if (goodstable != null) {
                        SuitOperBean operBean = new SuitOperBean();
                        List<BigDecimal> bigDecimals = new ArrayList<>();
                        bigDecimals.add(goodstable.getRgid());
                        bigDecimals.add(ltrGoods2.getRgid());
                        operBean.setGoods(bigDecimals);
                        operBean.setType(2255);
                        ltrGoods2.goodxh(1);
                        num2 = (int)ltrGoods2.getUsetime();
                        if (num2 <= 0) {
                            GoodsListFromServerUntil.shouhu(ltrGoods2.getRgid().intValue());
                        }
                        String mm = "jiesuo&1&" + ltrGoods2.getRgid();
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement(mm));
                        String senmes = Agreement.suitOperateAgreement(GsonUtilsb.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("monshuanimabi&500000"));
                        RoleData.getRoleData().getLoginResult().setGold(BigDecimal.valueOf(RoleData.getRoleData().getLoginResult().getGold().longValue() - 500000L));
                        break;
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt("请选择守护石");
                        break;
                    }
                }
                case 16: {
                    if (this.randFJpanel.RL1 == this.randFJpanel.RL2) {
                        ZhuFrame.getZhuJpanel().addPrompt("不可选择同一个守护石");
                        return;
                    }
                    if (this.randFJpanel.RL1.getValue().equals("")) {
                        ZhuFrame.getZhuJpanel().addPrompt("主守护石无属性");
                        return;
                    }
                    if (this.randFJpanel.RL2.getValue().equals("")) {
                        ZhuFrame.getZhuJpanel().addPrompt("副守护石无属性");
                        return;
                    }
                    if (RoleData.getRoleData().getLoginResult().getShouhu() < 50) {
                        ZhuFrame.getZhuJpanel().addPrompt("守护之尘不足");
                        return;
                    }
                    if (RoleData.getRoleData().getLoginResult().getGold().longValue() - 1000000L < 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt("金钱不足");
                        return;
                    }
                    if (this.randFJpanel.RL1 != null && this.randFJpanel.RL2 != null && !this.randFJpanel.RL1.getValue().equals("") && !this.randFJpanel.RL2.getValue().equals("")) {
                        SuitOperBean operBean = new SuitOperBean();
                        List<BigDecimal> bigDecimals = new ArrayList<>();
                        bigDecimals.add(this.randFJpanel.RL1.getRgid());
                        bigDecimals.add(this.randFJpanel.RL2.getRgid());
                        operBean.setGoods(bigDecimals);
                        operBean.setType(2256);
                        RoleData.getRoleData().getLoginResult().setShouhu(RoleData.getRoleData().getLoginResult().getShouhu() - 50);
                        String senmes2 = Agreement.suitOperateAgreement(GsonUtilsb.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes2);
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong2(-1);
                        this.randFJpanel.RL2.setUsetime(Integer.valueOf(0));
                        GoodsMouslisten.gooduse(this.randFJpanel.RL2, 1);
                        GoodsListFromServerUntil.shouhu(this.randFJpanel.RL2.getRgid().intValue());
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("monshuanimabi&1000000"));
                        RoleData.getRoleData().getLoginResult().setGold(BigDecimal.valueOf(RoleData.getRoleData().getLoginResult().getGold().longValue() - 1000000L));
                        GoodsMouslisten.goodreplace(-1, -1);
                        for (int c2 = 0; c2 <= ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().size() - 1; ++c2) {
                            if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(c2) == this.randFJpanel.RL2) {
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().set(c2, null);
                            }
                        }
                    }
                    this.randFJpanel.RL2 = null;
                    break;
                }
                case 17:
                case 19:
                case 20: {
                    if (this.caozuo == 17) {
                        ShouhuPackJframe.getShouhuPackJframe().setLocation(200, 380);
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().init(1);
                    }
                    else if (this.caozuo == 19) {
                        ShouhuPackJframe.getShouhuPackJframe().setLocation(200, 300);
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().init(1);
                    }
                    else {
                        ShouhuPackJframe.getShouhuPackJframe().setLocation(500, 300);
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().init(2);
                    }
                    FormsManagement.showForm(2257);
                    break;
                }
                case 21: {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong1(-1);
                    this.randFJpanel.FST.setVisible(true);
                    this.randFJpanel.img1.setVisible(false);
                    this.randFJpanel.FL = null;
                    break;
                }
                case 23: {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong1(-1);
                    this.randFJpanel.RST1.setVisible(true);
                    this.randFJpanel.img3.setVisible(false);
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong2(-1);
                    this.randFJpanel.RST2.setVisible(true);
                    this.randFJpanel.img4.setVisible(false);
                    this.randFJpanel.RL1 = null;
                    this.randFJpanel.RL2 = null;
                    break;
                }
                case 24: {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong2(-1);
                    this.randFJpanel.RST2.setVisible(true);
                    this.randFJpanel.img4.setVisible(false);
                    this.randFJpanel.RL2 = null;
                    break;
                }
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56: {
                    int k = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianlvl;
                    int v = this.caozuo - 50;
                    if (v == 0) {
                        if (k < 3) {
                            return;
                        }
                    }
                    else if (v == 1) {
                        if (k < 6) {
                            return;
                        }
                    }
                    else if (v == 2) {
                        if (k < 9) {
                            return;
                        }
                    }
                    else if (v == 3) {
                        if (k < 12) {
                            return;
                        }
                    }
                    else if (v == 4) {
                        if (k < 16) {
                            return;
                        }
                    }
                    else if (v == 5) {
                        if (k < 20) {
                            return;
                        }
                    }
                    else if (v == 6 && k < 24) {
                        return;
                    }
                    jiesuo(p0, this.caozuo - 50);
                    break;
                }
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66: {
                    if (p0.getButton() == 3) {
                        MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouhuBtn[this.caozuo - 60].setIcons(MountShouhuJpanel.imageIcons2);
                        if (RoleData.getRoleData().getLoginResult().getJiesuo() == null) {
                            return;
                        }
                        int l2 = Integer.parseInt(RoleData.getRoleData().getLoginResult().getJiesuo().split("\\|")[this.caozuo - 60]);
                        if (l2 == -1) {
                            return;
                        }
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.SHSXX, Integer.valueOf(l2), "#W确定要将#R守护石#W从此坐骑卸下吗？");
                        return;
                    }
                    else {
                        MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().s = true;
                        MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().l = this.caozuo - 60;
                        FormsManagement.showForm(2257);
                        break;
                    }
                }
                case 70: {
                    if (this.shouhuPackJpanel.page < 4) {
                        ShouhuPackJpanel shouhuPackJpanel = this.shouhuPackJpanel;
                        ++shouhuPackJpanel.page;
                    }
                    this.shouhuPackJpanel.updata();
                    break;
                }
                case 71: {
                    if (this.shouhuPackJpanel.page > 0) {
                        ShouhuPackJpanel shouhuPackJpanel2 = this.shouhuPackJpanel;
                        --shouhuPackJpanel2.page;
                    }
                    this.shouhuPackJpanel.updata();
                    break;
                }      case 72:
                    if (xuanBaoPackJpanel.page < 4) {
                        xuanBaoPackJpanel.page += 1;
                    }
                    xuanBaoPackJpanel.updata();
                    break;
                case 73:
                    if (xuanBaoPackJpanel.page > 0) {
                        xuanBaoPackJpanel.page -= 1;
                    }
                    xuanBaoPackJpanel.updata();
                    break;
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static void jiesuo(MouseEvent p0, int i) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        Goodstable ltrGoods = getGoodType(2258L);
        int num = (ltrGoods == null) ? 0 : ((int)ltrGoods.getUsetime());
        if (p0.getButton() == 3) {
            if (loginResult.getJiesuo() == null) {
                if (i != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("请先解锁第一栏位");
                    return;
                }
                if (num < (i + 1) * 3) {
                    ZhuFrame.getZhuJpanel().addPrompt("灵元晶不足" + (i + 1) * 3 + "个，请检查");
                    return;
                }
                String kk = "jiesuo&" + (i + 1) * 3 + "&" + ltrGoods.getRgid();
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement(kk));
                ltrGoods.goodxh((i + 1) * 3);
                num = (int)ltrGoods.getUsetime();
                if (num <= 0) {
                    GoodsListFromServerUntil.shouhu(ltrGoods.getRgid().intValue());
                }
                if (isv(1.0 - (double)i * 0.15)) {
                    loginResult.setJiesuo("0|-1|-1|-1|-1|-1|-1");
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("开启失败，请再接再励！");
                    return;
                }
            }
            else if (loginResult.getJiesuo().split("\\|")[i].equals("-1")) {
                if (num == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("请先获取灵元晶");
                    return;
                }
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("jiesuo&" + (i + 1) * 3));
                ltrGoods.goodxh((i + 1) * 3);
                num = (int)ltrGoods.getUsetime();
                if (num <= 0) {
                    GoodsListFromServerUntil.shouhu(ltrGoods.getRgid().intValue());
                }
                if (isv(1.0 - (double)i * 0.15)) {
                    String[] mes = loginResult.getJiesuo().split("\\|");
                    StringBuilder m = new StringBuilder();
                    for (int k = 0; k <= mes.length - 1; ++k) {
                        if (k == i) {
                            m.append("0");
                        }
                        else {
                            m.append(mes[k]);
                        }
                        if (k < mes.length - 1) {
                            m.append("|");
                        }
                    }
                    loginResult.setJiesuo(m.toString());
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("开启失败，请再接再励！");
                    return;
                }
            }
            else {
                if (loginResult.getJiesuo().split("\\|")[i].equals("0")) {
                    ZhuFrame.getZhuJpanel().addPrompt("栏位已解锁");
                    return;
                }
                String[] mes = loginResult.getJiesuo().split("\\|");
                StringBuilder m = new StringBuilder();
                for (int k = 0; k <= mes.length - 1; ++k) {
                    if (k == i) {
                        m.append("0");
                    }
                    else {
                        m.append(mes[k]);
                    }
                    if (k < mes.length - 1) {
                        m.append("|");
                    }
                }
                loginResult.setJiesuo(m.toString());
            }
        }
        else if (p0.getButton() == 1) {
            if (loginResult.getJiesuo() == null) {
                ZhuFrame.getZhuJpanel().addPrompt("请先解锁栏位");
                return;
            }
            if (loginResult.getJiesuo().split("\\|")[i].equals("-1")) {
                ZhuFrame.getZhuJpanel().addPrompt("请先解锁当前栏位栏位");
                return;
            }
            if (loginResult.getJiesuo().split("\\|")[i].equals("0")) {
                MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().zuoqi = i + 1;
                FormsManagement.showForm(2255);
            }
        }
        else {
            return;
        }
        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Shouhu&" + loginResult.getJiesuo()));
    }
    
    public static ImageIcon getimage(int i) {
        return new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + i + ".png");
    }
    
    public static void setsixiang(MountShouhuJpanel mountShouhuJpanel, int i, int SX) {
        if (SX == 1) {
            mountShouhuJpanel.getSixiang1().setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + i + ".png"));
        }
        else if (SX == 2) {
            mountShouhuJpanel.getSixiang2().setIcon(new ImageIcon("inkImg/Client/zqhead/50x50_" + Util.getRaceSting(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) + i + ".png"));
        }
    }
    
    public static boolean isv(double successProbability) {
        Random random = new Random();
        double randomValue = random.nextDouble();
        return randomValue < successProbability;
    }
    
    public static Goodstable getGoodType(long type) {
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
        return goodstable;
    }
}
