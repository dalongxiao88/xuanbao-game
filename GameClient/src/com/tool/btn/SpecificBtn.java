package com.tool.btn;

import org.come.Jpanel.TeststateJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.BuyShopBean;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import java.util.List;
import org.come.Frame.NPCJfram;
import org.come.until.CutButtonImage;
import com.tool.role.RoleTX;
import org.come.until.FormsManagement;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.TaobaoCourtSplendidJpanel;
import org.come.model.Eshop;

public class SpecificBtn extends MoBanBtn
{
    private int caozuo;
    private Eshop eshop;
    private TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel;
    
    public SpecificBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        if (caozuo >= 3 && caozuo <= 5) {
            this.setForeground(Color.yellow);
        }
        else {
            this.setForeground(Color.white);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public SpecificBtn(String iconpath, int type, Color[] colors, String text, int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public SpecificBtn(String iconpath, int type, String text, int caozuo, TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.taobaoCourtSplendidJpanel = taobaoCourtSplendidJpanel;
        this.setText(text);
        if (caozuo > 18 && caozuo < 22) {
            this.setFont(UIUtils.TEXT_HY16);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT2);
            this.setForeground(Color.white);
        }
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
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.caozuo == 1) {
                FormsManagement.showForm(38);
            }else if(caozuo == 666){
                FormsManagement.showForm(95);
            }
            else if (this.caozuo == 2) {
                RoleTX.getRoleTX().EToggle(-1);
                RoleTX.getRoleTX().EToggle(-2);
                RoleTX.getRoleTX().EToggle(-3);
                RoleTX.getRoleTX().EToggle(-4);
                RoleTX.getRoleTX().EToggle(-5);
            }
            else if (this.caozuo == 3) {
                RoleTX.getRoleTX().BCXX();
            }
            else if (this.caozuo != 4) {
                if (this.caozuo == 5) {
                    this.taobaoCourtSplendidJpanel.showTaobao();
                }
                else if (this.caozuo == 6) {
                    RoleTX.getRoleTX().upDir(0, true);
                }
                else if (this.caozuo == 7) {
                    RoleTX.getRoleTX().upDir(0, false);
                }
                else if (this.caozuo == 8) {
                    RoleTX.getRoleTX().Toggle(-1, 0);
                }
                else if (this.caozuo == 9) {
                    RoleTX.getRoleTX().Toggle(-1, 1);
                }
                else if (this.caozuo == 10) {
                    if (this.eshop == null) {
                        return;
                    }
                    this.BUY(this.eshop);
                }
                else if (this.caozuo == 11) {
                    List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                    if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                        return;
                    }
                    this.taobaoCourtSplendidJpanel.setNowpage(1);
                    this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
                }
                else if (this.caozuo == 12) {
                    List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                    if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                        return;
                    }
                    this.taobaoCourtSplendidJpanel.setNowpage((this.taobaoCourtSplendidJpanel.getNowpage() - 1 <= 1) ? 1 : (this.taobaoCourtSplendidJpanel.getNowpage() - 1));
                    this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
                }
                else if (this.caozuo == 13) {
                    List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                    if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                        return;
                    }
                    int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
                    this.taobaoCourtSplendidJpanel.setNowpage((this.taobaoCourtSplendidJpanel.getNowpage() + 1 >= totalpage) ? totalpage : (this.taobaoCourtSplendidJpanel.getNowpage() + 1));
                    this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
                }
                else if (this.caozuo == 14) {
                    List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                    if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                        return;
                    }
                    int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
                    this.taobaoCourtSplendidJpanel.setNowpage(totalpage);
                    this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
                }
                else if (this.caozuo == 15) {
                    RoleTX.getRoleTX().upDir(1, true);
                }
                else if (this.caozuo == 16) {
                    RoleTX.getRoleTX().upDir(1, false);
                }
                else if (this.caozuo == 17) {
                    if (this.taobaoCourtSplendidJpanel.getEshops() == null) {
                        return;
                    }
                    for (int i = 0; i < this.taobaoCourtSplendidJpanel.getEshops().length; ++i) {
                        if (this.taobaoCourtSplendidJpanel.getEshops()[i] != null) {
                            if (this.taobaoCourtSplendidJpanel.getEshops()[3] != null) {
                                TaobaoCourtSplendidJpanel.removeTXK(1, -Integer.parseInt(this.taobaoCourtSplendidJpanel.getEshops()[3].getEshopiid()));
                                this.taobaoCourtSplendidJpanel.getEshops()[3] = null;
                            }
                            else {
                                RoleTX.getRoleTX().removeTX(1, -Integer.parseInt(this.taobaoCourtSplendidJpanel.getEshops()[i].getEshopiid()));
                                this.taobaoCourtSplendidJpanel.getEshops()[i] = null;
                            }
                        }
                    }
                    this.taobaoCourtSplendidJpanel.setZbs(0);
                    if (FormsManagement.getframe(51).isVisible()) {
                        this.taobaoCourtSplendidJpanel.showTxMsg();
                    }
                }
                else if (this.caozuo == 18) {
                    if (FormsManagement.getframe(51).isVisible()) {
                        FormsManagement.HideForm(51);
                    }
                    else {
                        this.taobaoCourtSplendidJpanel.showTxMsg();
                        FormsManagement.showForm(51);
                    }
                }
                else if (this.caozuo >= 19 && this.caozuo <= 22) {
                    if (this.caozuo - 8 != this.taobaoCourtSplendidJpanel.getType()) {
                        try {
                            if (this.taobaoCourtSplendidJpanel.getType() == 11) {
                                this.taobaoCourtSplendidJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B119.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 12) {
                                this.taobaoCourtSplendidJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B121.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 13) {
                                this.taobaoCourtSplendidJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B123.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 14) {
                                this.taobaoCourtSplendidJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk.png"));
                            }
                            this.taobaoCourtSplendidJpanel.setType(this.caozuo - 8);
                            if (this.taobaoCourtSplendidJpanel.getType() == 11) {
                                this.taobaoCourtSplendidJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B120.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 12) {
                                this.taobaoCourtSplendidJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B122.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 13) {
                                this.taobaoCourtSplendidJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B124.png"));
                            }
                            else if (this.taobaoCourtSplendidJpanel.getType() == 14) {
                                this.taobaoCourtSplendidJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk1.png"));
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    this.taobaoCourtSplendidJpanel.refresGoodsSplendid();
                }
                else if (this.caozuo == 29) {
                    NPCJfram.getNpcJfram().getNpcjpanel().moneyCQ();
                }
            }
        }
        else if (this.caozuo == 1) {
            FormsManagement.showForm(38);
        }
        else if (this.caozuo == 2) {
            RoleTX.getRoleTX().EToggle(-1);
            RoleTX.getRoleTX().EToggle(-2);
            RoleTX.getRoleTX().EToggle(-3);
            RoleTX.getRoleTX().EToggle(-4);
            RoleTX.getRoleTX().EToggle(-5);
        }
        else if (this.caozuo == 3) {
            RoleTX.getRoleTX().BCXX();
        }
        else if (this.caozuo != 4) {
            if (this.caozuo == 5) {
                this.taobaoCourtSplendidJpanel.showTaobao();
            }
            else if (this.caozuo == 6) {
                RoleTX.getRoleTX().upDir(0, true);
            }
            else if (this.caozuo == 7) {
                RoleTX.getRoleTX().upDir(0, false);
            }
            else if (this.caozuo == 8) {
                RoleTX.getRoleTX().Toggle(-1, 0);
            }
            else if (this.caozuo == 9) {
                RoleTX.getRoleTX().Toggle(-1, 1);
            }
            else if (this.caozuo == 10) {
                if (this.eshop == null) {
                    return;
                }
                this.BUY(this.eshop);
            }
            else if (this.caozuo == 11) {
                List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                    return;
                }
                this.taobaoCourtSplendidJpanel.setNowpage(1);
                this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
            }
            else if (this.caozuo == 12) {
                List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                    return;
                }
                this.taobaoCourtSplendidJpanel.setNowpage((this.taobaoCourtSplendidJpanel.getNowpage() - 1 <= 1) ? 1 : (this.taobaoCourtSplendidJpanel.getNowpage() - 1));
                this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
            }
            else if (this.caozuo == 13) {
                List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                    return;
                }
                int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
                this.taobaoCourtSplendidJpanel.setNowpage((this.taobaoCourtSplendidJpanel.getNowpage() + 1 >= totalpage) ? totalpage : (this.taobaoCourtSplendidJpanel.getNowpage() + 1));
                this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
            }
            else if (this.caozuo == 14) {
                List<Eshop> lEshops = this.taobaoCourtSplendidJpanel.returnlEshops(this.taobaoCourtSplendidJpanel.getType());
                if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                    return;
                }
                int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
                this.taobaoCourtSplendidJpanel.setNowpage(totalpage);
                this.taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, this.taobaoCourtSplendidJpanel.getNowpage());
            }
            else if (this.caozuo == 15) {
                RoleTX.getRoleTX().upDir(1, true);
            }
            else if (this.caozuo == 16) {
                RoleTX.getRoleTX().upDir(1, false);
            }
            else if (this.caozuo == 17) {
                if (this.taobaoCourtSplendidJpanel.getEshops() == null) {
                    return;
                }
                for (int i = 0; i < this.taobaoCourtSplendidJpanel.getEshops().length; ++i) {
                    if (this.taobaoCourtSplendidJpanel.getEshops()[i] != null) {
                        if (this.taobaoCourtSplendidJpanel.getEshops()[3] != null) {
                            TaobaoCourtSplendidJpanel.removeTXK(1, -Integer.parseInt(this.taobaoCourtSplendidJpanel.getEshops()[3].getEshopiid()));
                            this.taobaoCourtSplendidJpanel.getEshops()[3] = null;
                        }
                        else {
                            RoleTX.getRoleTX().removeTX(1, -Integer.parseInt(this.taobaoCourtSplendidJpanel.getEshops()[i].getEshopiid()));
                            this.taobaoCourtSplendidJpanel.getEshops()[i] = null;
                        }
                    }
                }
                this.taobaoCourtSplendidJpanel.setZbs(0);
                if (FormsManagement.getframe(51).isVisible()) {
                    this.taobaoCourtSplendidJpanel.showTxMsg();
                }
            }
            else if (this.caozuo == 18) {
                if (FormsManagement.getframe(51).isVisible()) {
                    FormsManagement.HideForm(51);
                }
                else {
                    this.taobaoCourtSplendidJpanel.showTxMsg();
                    FormsManagement.showForm(51);
                }
            }
            else if (this.caozuo >= 19 && this.caozuo <= 22) {
                if (this.caozuo - 8 != this.taobaoCourtSplendidJpanel.getType()) {
                    try {
                        if (this.taobaoCourtSplendidJpanel.getType() == 11) {
                            this.taobaoCourtSplendidJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_0.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 12) {
                            this.taobaoCourtSplendidJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_2.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 13) {
                            this.taobaoCourtSplendidJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_4.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 14) {
                            this.taobaoCourtSplendidJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_8.png"));
                        }
                        this.taobaoCourtSplendidJpanel.setType(this.caozuo - 8);
                        if (this.taobaoCourtSplendidJpanel.getType() == 11) {
                            this.taobaoCourtSplendidJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_1.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 12) {
                            this.taobaoCourtSplendidJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_3.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 13) {
                            this.taobaoCourtSplendidJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_5.png"));
                        }
                        else if (this.taobaoCourtSplendidJpanel.getType() == 14) {
                            this.taobaoCourtSplendidJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_9.png"));
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                this.taobaoCourtSplendidJpanel.refresGoodsSplendid();
            }
            else if (this.caozuo == 29) {
                NPCJfram.getNpcJfram().getNpcjpanel().moneyCQ();
            }
        }
    }
    
    public void BUY(Eshop eshop) {
        if (!Util.canBuyOrno) {
            ZhuFrame.getZhuJpanel().addPrompt2("背包没有解锁!");
            return;
        }
        if (RoleData.getRoleData().getPackRecord().isTX(-Integer.parseInt(eshop.getEshopiid()) + "")) {
            ZhuFrame.getZhuJpanel().addPrompt2("你已拥有该特效");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getCodecard().compareTo(new BigDecimal(eshop.getEshopprice())) >= 0) {
            BuyShopBean bean = new BuyShopBean();
            bean.setAte(0);
            bean.setCd(eshop.getEshopid());
            bean.setSum(1);
            bean.setPrice(Long.parseLong(eshop.getEshopprice()));
            String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessageUntil.toServer(senmes);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("没有足够的仙玉!");
        }
    }
    
    public Eshop getEshop() {
        return this.eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
}
