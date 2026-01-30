package org.come.until;

import org.come.bean.SendProcessBean;

public class ProcessSendUntil
{
    public static SendProcessBean sendProcessBean;
    public static final String MSM = "&IqcL'DHrDgYPzE'dJ>~-KDNCAxvt7DJT=PYq34Vu(xf/fIV!QVt-2G<RG@qKEF&>|ObKak7|4n!xu`Uli9cO-D.WOxk*'ASJ;Rg!-'Fhqw(W;jUphQUH.1/n/`YuisD";
    
    public static String returnClientSendMes(String proceString, String sendMes) throws Exception {
        ProcessSendUntil.sendProcessBean.setCipherText(AESUtil.AESJDKEncode(sendMes, "&IqcL'DHrDgYPzE'dJ>~-KDNCAxvt7DJT=PYq34Vu(xf/fIV!QVt-2G<RG@qKEF&>|ObKak7|4n!xu`Uli9cO-D.WOxk*'ASJ;Rg!-'Fhqw(W;jUphQUH.1/n/`YuisD"));
        ProcessSendUntil.sendProcessBean.setSendMes(proceString);
        return GsonUtil.getGsonUtil().getgson().toJson(ProcessSendUntil.sendProcessBean);
    }
    
    public static String returnServerSendMes(String proceString, String sendMes) throws Exception {
        ProcessSendUntil.sendProcessBean.setCipherText(AESUtil.AESJDKEncode(sendMes, "&IqcL'DHrDgYPzE'dJ>~-KDNCAxvt7DJT=PYq34Vu(xf/fIV!QVt-2G<RG@qKEF&>|ObKak7|4n!xu`Uli9cO-D.WOxk*'ASJ;Rg!-'Fhqw(W;jUphQUH.1/n/`YuisD"));
        ProcessSendUntil.sendProcessBean.setSendMes(proceString);
        return GsonUtil.getGsonUtil().getgson().toJson(ProcessSendUntil.sendProcessBean);
    }
    
    static {
        ProcessSendUntil.sendProcessBean = new SendProcessBean();
    }
}
