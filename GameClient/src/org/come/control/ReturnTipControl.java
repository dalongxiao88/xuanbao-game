package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.PalacePKJframe;
import org.come.until.GsonUtil;
import org.come.bean.PalacePkBean;
import org.come.Frame.ZhuFrame;
import org.come.action.FromServerAction;

public class ReturnTipControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("tip")) {
            ZhuFrame.getZhuJpanel().addPrompt2(mes);
        }
        else if (type.equals("bookofchalg")) {
            PalacePkBean pkBean = (PalacePkBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, PalacePkBean.class);
            if (pkBean.getType() == 0) {
                PalacePKJframe.getPalacePKJframe().getPkJpanel().changeViewReception(pkBean);
            }
            else {
                PalacePKJframe.getPalacePKJframe().getPkJpanel().changeViewWinnerReception(pkBean);
            }
            FormsManagement.showForm(66);
        }
    }
}
