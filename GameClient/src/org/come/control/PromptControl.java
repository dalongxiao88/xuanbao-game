package org.come.control;

import org.come.xingpan.StarSoulRefinedJpane;
import org.come.xingpan.JpanelXingBackMain;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.xingpan.StarSoulRefinedJframe;
import org.come.xingpan.JframeXingBackMain;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.action.FromServerAction;

public class PromptControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getSfdkms().equals("1") && mes.contains("您的游戏点数不足请及时补充！当前点数")) {
            dkbz();
        }
        ZhuFrame.getZhuJpanel().addPrompt2(mes);
        if (FormsManagement.getInternalForm2(121) != null) {
            JpanelXingBackMain jpanelXingBackMain = JframeXingBackMain.getJframeSummonEquipMain().getJpanelXingBackMain();
            jpanelXingBackMain.init();
        }
        if (FormsManagement.getInternalForm2(120) != null) {
            StarSoulRefinedJpane starSoulRefinedJpane = StarSoulRefinedJframe.getStarSoulRefinedJframe().getStarSoulRefinedJpane();
            starSoulRefinedJpane.init();
        }
        if (mes != null && (mes.equals("您今日已领取过该奖励，请明日再来领取！")||mes.equals("您未激活月卡，无法领取该礼包！请联系群主购买月卡！"))) {

            FormsManagement.HideForm(3004);

            FormsManagement.HideForm(3005);
        }
    }
    
    public static void dkbz() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ZhuFrame.getZhuJpanel().addPrompt2("您的游戏点数不足，5秒后退出游戏！请及时补充！！！");
                }
                catch (Exception ex) {}
                try {
                    Thread.sleep(5000L);
                    System.exit(0);
                }
                catch (Exception ex2) {}
            }
        }).start();
    }
}
