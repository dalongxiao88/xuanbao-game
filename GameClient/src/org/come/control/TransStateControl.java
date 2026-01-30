package org.come.control;

import org.come.Jpanel.TransJpanel;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.Frame.TransJframe;
import org.come.Frame.BoothBoxJframe;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.action.FromServerAction;

public class TransStateControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] vs = mes.split("\\|");
        int zhi = Integer.parseInt(vs[0]);
        if (zhi == 0) {
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.trans, vs[1], "#G玩家#R" + vs[1] + "#G请求交易");
        }
        else if (zhi == 1) {
            if (BoothBoxJframe.getBoothboxjframe().isVisible()) {
                BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().QXJ();
            }
            TransJframe.getTransJframe().getTransJpanel().transReset((vs.length > 2) ? new BigDecimal(vs[2]) : null, vs[1]);
        }
        else if (zhi == 2) {
            TransJframe.getTransJframe().getTransJpanel().transClose(false);
            FormsManagement.HideForm(14);
            TransJpanel.isJY = false;
        }
        else if (zhi == 3 || zhi == 4 || (zhi == 5 && vs.length == 2)) {
            TransJframe.getTransJframe().getTransJpanel().CState(zhi, vs[1]);
        }
        else if (zhi == 5) {
            TransJframe.getTransJframe().getTransJpanel().transClose(true);
            FormsManagement.HideForm(14);
            TransJpanel.isJY = false;
        }
    }
}
