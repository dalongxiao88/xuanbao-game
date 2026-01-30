package org.come.equipmentSwitching;

import org.come.until.GsonUtil;
import org.come.bean.BuyShopBean;
import org.come.until.Util;
import org.come.model.Eshop;
import java.util.Iterator;
import java.awt.Rectangle;
import java.util.List;
import org.come.starcard.JframeStarCardMain;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.Jpanel.GameJpanel;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import com.updateNew.MyIsif;
import org.come.Jpanel.TestpackJapnel;
import org.come.Frame.TestpackJframe;
import java.awt.event.MouseEvent;
import org.come.Jpanel.TaobaoCourtSplendidJpanel;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class EquipmentSwitchingBtn extends MoBanBtn
{
    private int caozuo;
    private EquipmentSwitchingJpanel equipmentSwitchingJpanel;
    private String tzName;
    
    public EquipmentSwitchingBtn(String iconpath, int type, Color[] colors, String text, int caozuo) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = caozuo;
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, Color[] colors, String text, int caozuo, int num) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = caozuo;
        this.setFont(UIUtils.TEXT_FONT_17);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, Color[] colors, String text, int caozuo, String sm) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = caozuo;
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, Color[] colors, String text, int caozuo, Font font, EquipmentSwitchingJpanel equipmentSwitchingJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.tzName = text;
        text = "";
        this.caozuo = caozuo;
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.equipmentSwitchingJpanel = equipmentSwitchingJpanel;
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, Color[] colors, String text, int caozuo, Font font, EquipmentSwitchingJpanel equipmentSwitchingJpanel, Boolean b) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.caozuo = caozuo;
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.equipmentSwitchingJpanel = equipmentSwitchingJpanel;
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, String text, int caozuo, TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public EquipmentSwitchingBtn(String iconpath, int type, String text, int caozuo, Color[] colors, TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(new Font("隶书", 0, 19));
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
        if (this.caozuo < 10) {
            this.equipmentSwitchingJpanel.showEquipment(Integer.valueOf(this.caozuo));
        }
        else if (this.caozuo == 998) {
            TestpackJframe.getTestpackJframe().getJpac();
            EquipmentSwitchingBtn replacement = TestpackJapnel.getReplacement();
            TestpackJframe.getTestpackJframe().getJpac();
            List<EquipmentSwitchingBtn> replacementList = TestpackJapnel.getReplacementList();
            EquipmentSwitchingBtn specificBtn = (EquipmentSwitchingBtn)replacementList.get(replacementList.size() - 1);
            if (!specificBtn.isVisible()) {
                Rectangle bounds = replacement.getBounds();
                for (int i = 0; i < replacementList.size(); ++i) {
                    if (MyIsif.getStyle().equals("水墨")) {
                        ((EquipmentSwitchingBtn)replacementList.get(i)).setBounds((int)bounds.getX() - 5, (int)bounds.getY() + 7 + (i + 1) * 17, 68, 17);
                    }
                    else {
                        ((EquipmentSwitchingBtn)replacementList.get(i)).setBounds((int)bounds.getX() - 3, (int)bounds.getY() + 8 + (i + 1) * 17, 68, 17);
                    }
                    ((EquipmentSwitchingBtn)replacementList.get(i)).setVisible(true);
                }
            }
            else {
                for (EquipmentSwitchingBtn specificBtn2 : replacementList) {
                    specificBtn2.setVisible(false);
                }
            }
        }
        else if (this.caozuo == 997) {
            for (int j = 0; j < 12; ++j) {
                if (j == 13) {
                    if (GoodsListFromServerUntil.getChoseGoodsList()[13] != null) {
                        Goodstable goodstable = GoodsListFromServerUntil.getChoseGoodsList()[13];
                        goodstable.setStatus(Integer.valueOf(4));
                        GoodsMouslisten.gooduse(goodstable, 0);
                        GoodsListFromServerUntil.getChoseGoodsList()[13] = null;
                    }
                }
                else {
                    try {
                        GoodsListFromServerUntil.mutualChange(j, -1);
                    }
                    catch (Exception ex) {}
                }
            }
            TestpackJframe.getTestpackJframe().getJpac();
            List<EquipmentSwitchingBtn> replacementList2 = TestpackJapnel.getReplacementList();
            for (EquipmentSwitchingBtn specificBtn3 : replacementList2) {
                specificBtn3.setVisible(false);
            }
        }
        else if (this.caozuo == 999) {
            EquipmentSwitchingJpanel assistantJpanel = EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel();
            if (MyIsif.getStyle().equals("水墨")) {
                assistantJpanel.smEquipmentSwint();
            }
            else {
                assistantJpanel.hmEquipmentSwint();
            }
            if (FormsManagement.getframe(100000).isVisible()) {
                FormsManagement.HideForm(100000);
            }
            else {
                FormsManagement.showForm(100000);
            }
            TestpackJframe.getTestpackJframe().getJpac();
            List<EquipmentSwitchingBtn> replacementList = TestpackJapnel.getReplacementList();
            for (EquipmentSwitchingBtn specificBtn4 : replacementList) {
                specificBtn4.setVisible(false);
            }
        }
        else if (this.caozuo == 2001) {
            GoodsListFromServerUntil.saveCurrentEquipmentScheme(-1, null);
            this.equipmentSwitchingJpanel.showEquipment(null);
        }
        else if (this.caozuo == 2002) {
            String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
            if (StringUtils.isNotBlank(equipments)) {
                String[] v = equipments.split("\\$");
                String[] split = v[1].split("&");
                String s = split[EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getSelectIndex()].split("#")[0];
                split[EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getSelectIndex()] = s;
                String join = StringUtils.join(split, "&");
                equipments = v[0] + "$" + join;
                RoleData.getRoleData().getLoginResult().setEquipments(equipments);
                String mes = Agreement.getAgreement().rolechangeAgreement("switchEquip@" + RoleData.getRoleData().getLoginResult().getEquipments());
                SendMessageUntil.toServer(mes);
                ZhuFrame.getZhuJpanel().addPrompt("清空当前套装成功#51");
                EquipmentSwitchingBtn equipmentSwitchingBtn = (EquipmentSwitchingBtn)this.equipmentSwitchingJpanel.getEquipmentSwitchingMenus().get(this.equipmentSwitchingJpanel.getSelectIndex());
                equipmentSwitchingBtn.nochoose(null);
            }
        }
        else if (this.caozuo == 2003) {
            if (FormsManagement.getframe(100001).isVisible()) {
                FormsManagement.HideForm(100001);
            }
            else {
                FormsManagement.showForm(100001);
                if (!MyIsif.getStyle().equals("水墨")) {
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setIcons(CutButtonImage.cuts("inkImg/hongmu/6026.png"));
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setColors(UIUtils.COLOR_BTNPUTONG);
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setBounds(100, 110, 60, 26);
                }
                else {
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setIcons(CutButtonImage.cuts("inkImg/button/B59.png"));
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setColors(UIUtils.COLOR_BLACK);
                    EquipmenSwitchingIputJframe.getExchangeAwardJframe().getEquipmentSwitchingInputJpanel().getCodeBtn1().setBounds(100, 110, 68, 26);
                }
            }
        }
        else if (this.caozuo >= 1000) {
            GameJpanel.alpha = 0.1f;
            String s2 = new String(this.caozuo + "");
            String substring = s2.substring(3);
            int k = Integer.parseInt(substring);
            TestpackJapnel.tzIndex = Integer.valueOf(k);
            String equipments2 = RoleData.getRoleData().getLoginResult().getEquipments();
            EquipmentSwitchingJpanel equipmentSwitchingJpanel = EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel();
            if (k == equipmentSwitchingJpanel.getSelectIndex()) {
                equipmentSwitchingJpanel.setGoodstables(new Goodstable[22]);
            }
            String[] v2 = equipments2.split("\\$");
            String[] split2 = v2[1].split("&");
            String[] split3 = split2[k].split("#");
            for (int l = 0; l < 15; ++l) {
                if (l == 13) {
                    if (GoodsListFromServerUntil.getChoseGoodsList()[13] != null) {
                        Goodstable goodstable2 = GoodsListFromServerUntil.getChoseGoodsList()[13];
                        goodstable2.setStatus(Integer.valueOf(4));
                        GoodsMouslisten.gooduse(goodstable2, 0);
                        GoodsListFromServerUntil.getChoseGoodsList()[13] = null;
                    }
                }
                else {
                    try {
                        GoodsListFromServerUntil.mutualChange(l, null);
                    }
                    catch (Exception ex2) {}
                }
            }
            int m = 0;
            if (split3.length == 2) {
                String[] split4 = split3[1].split("\\|");
                for (int j2 = 0; j2 < 12; ++j2) {
                    try {
                        String[] split5 = split4[j2].split("=");
                        if (split5.length == 2) {
                            BigDecimal rgid = new BigDecimal(split5[1]);
                            Goodstable goods = GoodsListFromServerUntil.getEquipmentByRgid(rgid);
                            equipmentSwitchingJpanel.updateGoodstables(goods, Integer.parseInt(split5[0]));
                            if (m == 13 && StringUtils.isNotBlank(split4[j2]) && rgid != null) {
                                JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getJoinBtn().setText("参战");
                                JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().setChooseStarCardId(goods.getRgid());
                                JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getJoinBtn().nochoose(null);
                            }
                            try {
                                GoodsListFromServerUntil.mutualChange(Integer.parseInt(split5[0]), rgid);
                            }
                            catch (Exception ex3) {}
                        }
                    }
                    catch (Exception e2) {
                        continue;
                    }
                    ++m;
                }
            }
            TestpackJframe.getTestpackJframe().getJpac();
            List<EquipmentSwitchingBtn> replacementList3 = TestpackJapnel.getReplacementList();
            for (EquipmentSwitchingBtn equipmentSwitchingBtn2 : replacementList3) {
                equipmentSwitchingBtn2.setVisible(false);
            }
            String equipment = RoleData.getRoleData().getLoginResult().getEquipments().substring(1);
            equipment = k + equipment;
            String mes2 = Agreement.getAgreement().rolechangeAgreement("switchEquip@" + equipment);
            SendMessageUntil.toServer(mes2);
            RoleData.getRoleData().getLoginResult().setEquipments(equipment);
        }
        else if (this.caozuo == 999) {
            if (FormsManagement.getframe(100000).isVisible()) {
                FormsManagement.HideForm(100000);
            }
            else {
                FormsManagement.showForm(100000);
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
            String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessageUntil.toServer(senmes);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("没有足够的仙玉!");
        }
    }
    
    public String getTzName() {
        return this.tzName;
    }
    
    public void setTzName(String tzName) {
        this.tzName = tzName;
    }
}
