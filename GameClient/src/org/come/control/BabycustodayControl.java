package org.come.control;

import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.action.FromServerAction;

public class BabycustodayControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.childRearing, null, "孩子已经出生，您是否选择抚养这个孩子！");
    }
}
