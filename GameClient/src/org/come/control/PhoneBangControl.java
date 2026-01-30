package org.come.control;

import org.come.Frame.IphoneVerifyFrame;
import org.come.Frame.ZhuFrame;
import org.come.action.FromServerAction;

public class PhoneBangControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if ("1".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("安全码错误");
        }
        else if ("2".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("该账户已绑定手机号");
        }
        else if ("3".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("绑定成功");
            IphoneVerifyFrame.getIphoneVerifyFrame().getIphoneVerifyPanel().cleanText();
        }
        else if ("-1".equals(mes)) {
            ZhuFrame.getZhuJpanel().addPrompt2("该手机已经绑定了5个账号了，请换个手机号吧");
        }
    }
}
