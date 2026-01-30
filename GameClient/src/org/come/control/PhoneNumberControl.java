package org.come.control;

import org.come.test.Main;
import org.come.action.FromServerAction;

public class PhoneNumberControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] split = mes.split(",");
        if ("error".equals(split[0])) {
            Main.frame.getLoginJpanel().getLoginView().getRegisterView().getLabMsgTip().setText("手机号不正确");
        }
        else {
            Main.frame.getLoginJpanel().getLoginView().getRegisterView();
        }
    }
}
