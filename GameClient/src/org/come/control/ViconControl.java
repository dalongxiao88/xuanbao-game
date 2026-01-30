package org.come.control;

import java.util.List;
import java.awt.Component;
import com.tool.btn.VipShopBtn;
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.action.FromServerAction;

public class ViconControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] split = mes.split("\\|");
        List<VipShopBtn> btnListVicon = ZhuFrame.getZhuJpanel().getBtnListVicon();
        if (btnListVicon == null) {
            btnListVicon = new ArrayList<>();
            ZhuFrame.getZhuJpanel().setBtnListVicon(btnListVicon);
        }
        int i = 0;
    LOOP:
        while (i < split.length) {
            String substring = split[i].substring(1);
            int j = 0;
            while (j < btnListVicon.size()) {
                if (((VipShopBtn)btnListVicon.get(j)).getVicon().equals(substring)) {
                    if (split[i].startsWith("R")) {
                        btnListVicon.remove(j);
                        ZhuFrame.getZhuJpanel().remove((Component)btnListVicon.get(j));
                    }
                    ++i;
                    continue LOOP;
                }
                else {
                    ++j;
                }
            }
            if (split[i].startsWith("A")) {}
        }
        if (ZhuFrame.getZhuJpanel().getShowVipBtn() != null) {
            boolean is = ZhuFrame.getZhuJpanel().getShowVipBtn().isIs();
            for (int k = 0; k < btnListVicon.size(); ++k) {
                VipShopBtn vipShopBtn = (VipShopBtn)btnListVicon.get(k);
                vipShopBtn.setBounds(19 + 60 * ((2 + k) % 3), 72 + (53 - vipShopBtn.getIcon().getIconHeight()) / 2 + (k + 2) / 3 * 55, vipShopBtn.getIcon().getIconWidth(), vipShopBtn.getIcon().getIconHeight());
                vipShopBtn.setVisible(!is);
            }
        }
    }
}
