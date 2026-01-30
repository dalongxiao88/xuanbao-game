package org.come.control;

import org.come.Frame.IphoneVerifyFrame;
import org.come.until.GsonUtil;
import org.come.bean.PhoneNumberSGBean;
import org.come.action.FromServerAction;

public class PhoneNumberIsNoGetControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        PhoneNumberSGBean numberSGBean = (PhoneNumberSGBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, PhoneNumberSGBean.class);
        IphoneVerifyFrame.getIphoneVerifyFrame().getIphoneVerifyPanel().judgePhone(numberSGBean);
    }
}
