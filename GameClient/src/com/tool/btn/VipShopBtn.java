package com.tool.btn;

import org.come.Jpanel.ZhuJpanel;
import javax.swing.JLabel;
import java.util.List;
import org.come.until.CutButtonImage;
import org.come.until.ScrenceUntil;
import com.updateNew.MyIsif;
import com.tool.time.TimeLiTXT;
import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import org.come.Frame.ActivityJframe;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.LimitedTimeShopFrame;
import org.come.Frame.EveryDayOddsJframe;
import org.come.Frame.EverydayRechargeJframe;
import org.come.Frame.ContinuousRechargeJframe;
import org.come.Frame.ImpactGradeJframe;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.ImageIcon;
import org.come.Jpanel.ImpactGradeGoodsJpanel;
import org.come.Jpanel.ImpactGradeJpanel;
import org.come.Jpanel.EveryDayOddsJpanel;
import org.come.Jpanel.EverydayRechargeGoodsJpanel;
import org.come.Jpanel.ContinuousRechargeGoodsJpanel;
import org.come.Jpanel.RechargeVIPGoodsJpanel;
import org.come.Jpanel.VipGoodsJpanel;

public class VipShopBtn extends MoBanBtn
{
    private int formsid;
    private int caozuo;
    private VipGoodsJpanel vipGoodsJpanel;
    private RechargeVIPGoodsJpanel rechargeVIPGoodsJpanel;
    private String vicon;
    boolean is;
    private ContinuousRechargeGoodsJpanel continuousRechargeGoodsJpanel;
    private EverydayRechargeGoodsJpanel everydayRechargeGoodsJpanel;
    private EveryDayOddsJpanel everyDayOddsJpanel;
    private ImpactGradeJpanel impactGradeJpanel;
    private ImpactGradeGoodsJpanel impactGradeGoodsJpanel;
    public static ImageIcon[] icons;
    
    public VipShopBtn(String iconpath, int type, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, String vicon) {
        super(iconpath, type);
        this.vicon = vicon;
        this.caozuo = caozuo;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, boolean is) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.is = is;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, VipGoodsJpanel vipGoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.vipGoodsJpanel = vipGoodsJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, RechargeVIPGoodsJpanel rechargeVIPGoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.rechargeVIPGoodsJpanel = rechargeVIPGoodsJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, ContinuousRechargeGoodsJpanel continuousRechargeGoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.continuousRechargeGoodsJpanel = continuousRechargeGoodsJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, EverydayRechargeGoodsJpanel everydayRechargeGoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.everydayRechargeGoodsJpanel = everydayRechargeGoodsJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, String text, EveryDayOddsJpanel everyDayOddsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setForeground(Color.white);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.everyDayOddsJpanel = everyDayOddsJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, ImpactGradeJpanel impactGradeJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.impactGradeJpanel = impactGradeJpanel;
    }
    
    public VipShopBtn(String iconpath, int type, int caozuo, ImpactGradeGoodsJpanel impactGradeGoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.impactGradeGoodsJpanel = impactGradeGoodsJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1) {
            this.vipGoodsJpanel.exchangeGoods();
        }
        else if (this.caozuo == 2) {
            this.continuousRechargeGoodsJpanel.exchangeGoods();
        }
        else if (this.caozuo == 3) {
            this.everydayRechargeGoodsJpanel.exchangeGoods();
        }
        else if (this.caozuo == 4) {
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) < 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包不够");
                return;
            }
            this.everyDayOddsJpanel.exchangeGoods();
        }
        else if (this.caozuo >= 5 && this.caozuo <= 7 || this.caozuo == 13) {
            this.impactGradeJpanel.changeMenuBtn(this.caozuo - 4);
        }
        else if (this.caozuo == 9 || this.caozuo == 10 || this.caozuo == 12) {
            this.impactGradeJpanel.changeMenuBtn(this.caozuo - 5);
        }

        else if (this.caozuo == 8) {
            this.impactGradeGoodsJpanel.exchangeGoods();
        }
        else if (this.caozuo == 50) {
            ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().changeMenuBtn(-1);
        }
        else if (this.caozuo == 51) {
            ContinuousRechargeJframe.getContinuousRechargeJframe().getContinuousRechargeJpanel().getGoods();
        }
        else if (this.caozuo == 52) {
            EverydayRechargeJframe.getEverydayRechargeJframe().getEverydayRechargeJpanel().getGoods();
        }
        else if (this.caozuo == 53) {
            EveryDayOddsJframe.getEveryDayOddsJframe().getEveryDayOddsJpanel().getGoods();
        }
        else if (this.caozuo == 54) {
            this.changeVie();
        }
        else if (this.caozuo == 61) {
            LimitedTimeShopFrame.getLimitedTimeShopFrame().getLimitedTimeShopJpanel().getGoods();
        }
        else if (this.caozuo == 60) {
            changeIconVie(null);
        }
        else if (this.caozuo == 62) {
            this.changeVieY();
        }
        else if (this.caozuo == 56) {
            this.caidan();
        }
        else if (this.caozuo == 55) {
            if (this.vicon == null) {
                return;
            }
            String sendmes = Agreement.getAgreement().viconAgreement(this.vicon);
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 40) {
            this.huodong();
        }
        else if (this.caozuo == 57) {
            this.rechargeVIPGoodsJpanel.exchangeGoods();
        }
    }
    
    public void huodong() {
        if (FormsManagement.getInternalForm2(40) != null) {
            ActivityJframe.getActivityJframe().getActivityJpanel().refreshView();
        }
        FormsManagement.showForm(this.formsid);
    }
    
    public static void changeIconVie() {
        changeIconVie(Boolean.valueOf(TimeLimit.getLimits().limits.size() > 0));
    }
    
    public static void changeIconVie(Boolean isVisible) {
        List<Limit> limits = TimeLimit.getLimits().limits;
        for (int i = 0; i < limits.size(); ++i) {
            JLabel jLabel = ((Limit)limits.get(i)).getjLabel();
            if (isVisible == null) {
                isVisible = Boolean.valueOf(!jLabel.isVisible());
            }
            jLabel.setVisible((boolean)isVisible);
        }
        limits = TimeLiTXT.getTimeLiTXT().limits;
        for (int i = 0; i < limits.size(); ++i) {
            JLabel jLabel = ((Limit)limits.get(i)).getTXT();
            if (isVisible == null) {
                isVisible = Boolean.valueOf(!jLabel.isVisible());
            }
            jLabel.setVisible((boolean)isVisible);
        }
        if (isVisible == null) {
            ZhuFrame.getZhuJpanel().getShowIconBtn().setVisible(false);
        }
        else {
            ZhuFrame.getZhuJpanel().getShowIconBtn().setVisible(true);
            if (MyIsif.getStyle().equals("水墨")) {
                if ((boolean)isVisible) {
                    ZhuFrame.getZhuJpanel().getShowIconBtn().setBounds(ScrenceUntil.Screen_x - 22 - 14 - (limits.size() - 1) * 25, 64, 13, 53);
                    ZhuFrame.getZhuJpanel().getShowIconBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdk.png"));
                }
                else {
                    ZhuFrame.getZhuJpanel().getShowIconBtn().setBounds(ScrenceUntil.Screen_x - 14, 64, 13, 53);
                    ZhuFrame.getZhuJpanel().getShowIconBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgb.png"));
                }
            }
            else if ((boolean)isVisible) {
                ZhuFrame.getZhuJpanel().getShowIconBtn().setBounds(ScrenceUntil.Screen_x - 22 - 14 - (limits.size() - 1) * 25, 64, 13, 53);
                ZhuFrame.getZhuJpanel().getShowIconBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdkh.png"));
            }
            else {
                ZhuFrame.getZhuJpanel().getShowIconBtn().setBounds(ScrenceUntil.Screen_x - 14, 64, 13, 53);
                ZhuFrame.getZhuJpanel().getShowIconBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgbh.png"));
            }
        }
    }
    
    public void changeVie() {
        if (ZhuFrame.getZhuJpanel().getcontinuousBtn() != null) {
            ZhuFrame.getZhuJpanel().getcontinuousBtn().setVisible(this.is);
        }
        if (ZhuFrame.getZhuJpanel().getOddsBtn() != null) {
            ZhuFrame.getZhuJpanel().getOddsBtn().setVisible(this.is);
        }
        if (ZhuFrame.getZhuJpanel().getRechargeBtn() != null) {
            ZhuFrame.getZhuJpanel().getRechargeBtn().setVisible(this.is);
        }
        if (ZhuJpanel.getQianDaoMenu() != null) {
            ZhuJpanel.getQianDaoMenu().setVisible(this.is);
        }
        if (ZhuJpanel.getZhsbd() != null) {
            ZhuJpanel.getZhsbd().setVisible(this.is);
        }
        if (ZhuJpanel.getTtBtn() != null) {
            ZhuJpanel.getTtBtn().setVisible(this.is);
        }
        List<VipShopBtn> btnListVicon = ZhuFrame.getZhuJpanel().getBtnListVicon();
        if (btnListVicon != null) {
            for (int i = 0; i < btnListVicon.size(); ++i) {
                ((VipShopBtn)btnListVicon.get(i)).setVisible(this.is);
            }
        }
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.is) {
                    ZhuFrame.getZhuJpanel().getShowVipBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgb.png"));
                }
                else {
                    ZhuFrame.getZhuJpanel().getShowVipBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdk.png"));
                }
            }
            else if (this.is) {
                ZhuFrame.getZhuJpanel().getShowVipBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgbh.png"));
            }
            else {
                ZhuFrame.getZhuJpanel().getShowVipBtn().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdkh.png"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.is = !this.is;
    }
    
    public void changeVieY() {
        if (ZhuJpanel.getGGBtn() != null) {
            ZhuJpanel.getGGBtn().setVisible(this.is);
        }
        List<VipShopBtn> btnListVicon = ZhuFrame.getZhuJpanel().getBtnListVicon();
        if (btnListVicon != null) {
            for (int i = 0; i < btnListVicon.size(); ++i) {
                ((VipShopBtn)btnListVicon.get(i)).setVisible(this.is);
            }
        }
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.is) {
                    ZhuFrame.getZhuJpanel().getShowIconBtnY().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdk.png"));
                }
                else {
                    ZhuFrame.getZhuJpanel().getShowIconBtnY().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgb.png"));
                }
            }
            else if (this.is) {
                ZhuFrame.getZhuJpanel().getShowIconBtnY().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdkh.png"));
            }
            else {
                ZhuFrame.getZhuJpanel().getShowIconBtnY().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgbh.png"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.is = !this.is;
    }
    
    public void caidan() {
        FormsManagement.HideForm(46);
        if (ZhuFrame.getZhuJpanel().getShowcaidan1() != null) {
            ZhuFrame.getZhuJpanel().getShowcaidan1().setVisible(this.is);
        }
        if (ZhuJpanel.getZhsbd() != null) {
            ZhuJpanel.getZhsbd().setVisible(this.is);
        }
        if (ZhuJpanel.getTtBtn() != null) {
            ZhuJpanel.getTtBtn().setVisible(this.is);
        }
        if (ZhuJpanel.getPdBtn() != null) {
            ZhuJpanel.getPdBtn().setVisible(this.is);
        }
        if (ZhuJpanel.getBjczBtn() != null) {
            ZhuJpanel.getBjczBtn().setVisible(this.is);
        }
        if (ZhuJpanel.getbsynBtn() != null) {
            ZhuJpanel.getbsynBtn().setVisible(this.is);
        }
//        if (ZhuJpanel.getGZBtn() != null) {
//            ZhuJpanel.getGZBtn().setVisible(this.is);
//        }

        if (ZhuFrame.getZhuJpanel().getLimitShopBtn() != null) {
            ZhuFrame.getZhuJpanel().getLimitShopBtn().setVisible(this.is);
        }
        if (ZhuFrame.getZhuJpanel().getchongjiBtn() != null) {
            ZhuFrame.getZhuJpanel().getchongjiBtn().setVisible(this.is);
        }
        ZhuFrame.getZhuJpanel();
        if (ZhuJpanel.getCzlbBtn() != null) {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getCzlbBtn().setVisible(this.is);
        }
        List<VipShopBtn> btnListCaidan = ZhuFrame.getZhuJpanel().getBtnListVicon();
        if (btnListCaidan != null) {
            for (int w = 0; w < btnListCaidan.size(); ++w) {
                ((VipShopBtn)btnListCaidan.get(w)).setVisible(this.is);
            }
        }
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.is) {
                    ZhuFrame.getZhuJpanel().getcaidan().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgb.png"));
                }
                else {
                    ZhuFrame.getZhuJpanel().getcaidan().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdk.png"));
                }
            }
            else if (this.is) {
                ZhuFrame.getZhuJpanel().getcaidan().setIcons(CutButtonImage.cuts("inkImg/button1/sjajgbh.png"));
            }
            else {
                ZhuFrame.getZhuJpanel().getcaidan().setIcons(CutButtonImage.cuts("inkImg/button1/sjajdkh.png"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.is = !this.is;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public boolean isIs() {
        return this.is;
    }
    
    public void setIs(boolean is) {
        this.is = is;
    }
    
    public String getVicon() {
        return this.vicon;
    }
    
    public void setVicon(String vicon) {
        this.vicon = vicon;
    }
    
    static {
        VipShopBtn.icons = null;
    }
}
