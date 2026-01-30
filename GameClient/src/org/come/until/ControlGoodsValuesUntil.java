package org.come.until;

import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.bean.SummonPetBean;
import org.come.entity.Goodstable;

public class ControlGoodsValuesUntil
{
    public static void getPetFromItem(Goodstable goodstable) {
        SummonPetBean summonPetBean = new SummonPetBean();
        summonPetBean.setPetid(new BigDecimal(goodstable.getValue()));
        String mes = Agreement.getAgreement().summonpetAgreement(GsonUtil.getGsonUtil().getgson().toJson(summonPetBean));
        SendMessageUntil.toServer(mes);
        FrameMessageChangeJpanel.addtext(6, "恭喜您获得了" + goodstable.getGoodsname() + "召唤兽", (BigDecimal)null, (String)null);
        goodstable.goodxh(1);
    }
}
