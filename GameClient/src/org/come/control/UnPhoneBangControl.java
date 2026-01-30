package org.come.control;

import org.come.Frame.IphoneVerifyFrame;
import org.come.Frame.ZhuFrame;
import org.come.action.FromServerAction;

public class UnPhoneBangControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if ("1".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("安全码错误");
        }
        else if ("2".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("解绑的手机号不正确");
        }
        else if ("3".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("解绑成功");
            IphoneVerifyFrame.getIphoneVerifyFrame().getIphoneVerifyPanel().cleanText();
        }
    }
}
