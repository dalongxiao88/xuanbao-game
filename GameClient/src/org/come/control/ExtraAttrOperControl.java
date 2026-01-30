package org.come.control;

import java.util.List;
import org.come.until.AccessSuitMsgUntil;
import org.come.Frame.SuitBaptizeJframe;
import org.come.Jpanel.NewRefiningJpanel;
import java.math.BigDecimal;
import org.come.summonequip.JpanelSummonEquipMain;
import org.come.entity.Goodstable;
import org.come.starcard.JframeStarCardMain;
import org.come.MountShouHu.ShouhuPackJframe;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.summonequip.JframeReclaimSkillMain;
import org.come.summonequip.JframeSummonEquipMain;
import org.come.Frame.DianQJframe;
import org.come.Frame.DdianJframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleProperty;
import org.come.Frame.GemMakeFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.Jpanel.WashJpanel;
import com.tool.btn.BaptizeBtn;
import org.come.Frame.NewRefiningJframe;
import org.come.lianhua.AutoMaticRefiningJframe;
import org.apache.commons.lang.StringUtils;
import org.come.until.GsonUtil;
import org.come.bean.QualityClBean;
import org.come.action.FromServerAction;

public class ExtraAttrOperControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("extrattroper")) {
            QualityClBean clBean = (QualityClBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, QualityClBean.class);
            if (clBean == null) {
                return;
            }
            if (StringUtils.isNotBlank(clBean.getNewAttr())) {
                AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().detectionProperties(clBean);
            }
            switch (clBean.getType()) {
                case 1: {
                    NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().ew(clBean);
                    break;
                }
                case -2:
                case 2: {
                    NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().ew(clBean);
                    break;
                }
                case 4: {
                    this.suitXl(clBean.getNewAttr());
                    BaptizeBtn.setNewEx(clBean.getNewAttr());
                    break;
                }
                case -4: {
                    this.changeAttr(clBean.getNewAttr());
                    if (clBean.getNewAttr() != null && clBean.getNewAttr() != "") {
                        Goodstable goodstable = WashJpanel.getGoodstableBean().getGoodstable();
                        if (goodstable == null) {
                            return;
                        }
                        String[] ss = goodstable.getValue().split("\\|");
                        String value = BaptizeBtn.newExtra(ss, 3, clBean.getNewAttr());
                        goodstable.setValue(value);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 5: {
                    Goodstable good = GoodsListFromServerUntil.getRgid(clBean.getRgid());
                    if (good != null) {
                        String value2 = BaptizeBtn.newExtra(good.getValue().split("\\|"), 4, clBean.getNewAttr());
                        good.setValue(value2);
                        GemMakeFrame.getGemMakeFrame().getJpanel().optionClick(good);
                        if ((int)good.getStatus() == 1) {
                            RoleProperty.getRoleProperty().equipWearOff();
                        }
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 8: {
                    if (clBean.getNewAttr() == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无默契配饰不可点粹");
                        return;
                    }
                    DdianJframe.getDdianJframe().getDianJpanel().ew(clBean);
                    break;
                }
                case 9: {
                    DianQJframe.getDdianQJframe().getDianJpanel().ew(clBean);
                    break;
                }
                case 44: {
                    if (JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getChooseEquip() == null) {
                        return;
                    }
                    Goodstable chooseEquip = GoodsListFromServerUntil.getRgid(JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getChooseEquip());
                    if (chooseEquip == null) {
                        return;
                    }
                    if (clBean.getRgid().compareTo(chooseEquip.getRgid()) == 0) {
                        String valuesMessage = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(chooseEquip.getValue().split("\\|"), "觉醒技");
                        JframeReclaimSkillMain.getJframeReclaimSkillMain().getJpanelReclaimSkillMain().washSkillViewgetMessage(valuesMessage, 0);
                        JframeReclaimSkillMain.getJframeReclaimSkillMain().getJpanelReclaimSkillMain().washSkillViewgetMessage(clBean.getNewAttr(), 1);
                        JframeReclaimSkillMain.getJframeReclaimSkillMain().getJpanelReclaimSkillMain().setQualityClBean(clBean);
                        AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().petEquiDetectionProperties(clBean);
                        if (!JframeReclaimSkillMain.getJframeReclaimSkillMain().isVisible()) {
                            FormsManagement.showForm(94);
                        }
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 46: {
                    JpanelSummonEquipMain jpanelSummonEquipMain = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain();
                    if (jpanelSummonEquipMain.getChooseEquip() == null) {
                        return;
                    }
                    Goodstable chooseEquip2 = GoodsListFromServerUntil.getRgid(JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getChooseEquip());
                    if (chooseEquip2 == null) {
                        return;
                    }
                    chooseEquip2.setValue(BaptizeBtn.newExtra(chooseEquip2.getValue().split("\\|"), 5, clBean.getNewAttr()));
                    String valuesMessage2 = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(chooseEquip2.getValue().split("\\|"), "觉醒技");
                    jpanelSummonEquipMain.skillMessage(valuesMessage2.split("&"));
                    jpanelSummonEquipMain.getNowNumLab().setText("<html><body>(拥有:<font color=#FFFF00>" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章").toString() + "</font>)</body><html>");
                    break;
                }
                case 47: {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().ew(clBean);
                    break;
                }
                case 53: {
                    BigDecimal chooseStarCardId = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getChooseStarCardId();
                    if (chooseStarCardId == null) {
                        return;
                    }
                    Goodstable goodstable2 = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    if (goodstable2 == null) {
                        return;
                    }
                    String value3 = goodstable2.getValue().split("\\|")[3];
                    NewRefiningJpanel refiningJpanel = NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel();
                    if (!NewRefiningJframe.getNewRefiningJframe().isVisible()) {
                        refiningJpanel.show(value3, 2, false);
                    }
                    refiningJpanel.ew(clBean);
                    break;
                }
                case 54: {
                    BigDecimal chooseStarCardId2 = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getChooseStarCardId();
                    if (chooseStarCardId2 == null) {
                        return;
                    }
                    Goodstable goodstable3 = GoodsListFromServerUntil.getRgid(chooseStarCardId2);
                    if (goodstable3 == null) {
                        return;
                    }
                    String value4 = goodstable3.getValue().split("\\|")[4];
                    NewRefiningJpanel refiningJpanel2 = NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel();
                    if (!NewRefiningJframe.getNewRefiningJframe().isVisible()) {
                        refiningJpanel2.show(value4, 3, false);
                    }
                    NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().ew(clBean);
                    break;
                }
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76: {
                    NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().ew(clBean);
                    break;
                }
            }
        }
    }
    
    public void suitXl(String newAttr) {
        for (int i = 0; i < 4; ++i) {
            SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getNewAttr()[i].setText("");
            WashJpanel.getLabsx()[i].setText("");
        }
        List<String> attr = AccessSuitMsgUntil.getSuitAttr(newAttr);
        if (attr != null) {
            for (int index = (attr.size() >= 4) ? 4 : attr.size(), j = 0; j < index; ++j) {
                SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getNewAttr()[j].setText((String)attr.get(j));
                WashJpanel.getLabsx()[j].setText((String)attr.get(j));
            }
        }
    }
    
    public void changeAttr(String newAttr) {
        for (int i = 0; i < 4; ++i) {
            SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getOldAttr()[i].setText("");
            SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getNewAttr()[i].setText("");
            WashJpanel.getLabsx()[i].setText("");
        }
        List<String> attr = AccessSuitMsgUntil.getSuitAttr(newAttr);
        if (attr != null) {
            for (int index = (attr.size() >= 4) ? 4 : attr.size(), j = 0; j < index; ++j) {
                SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getOldAttr()[j].setText((String)attr.get(j));
                WashJpanel.getLabsx()[j].setText((String)attr.get(j));
            }
        }
    }
}
