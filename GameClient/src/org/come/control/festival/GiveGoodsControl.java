package org.come.control.festival;

import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ImageMixDeal;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.action.FromServerAction;

public class GiveGoodsControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(mes, Goodstable.class);
        if (goodstable.getRole_id().compareTo(ImageMixDeal.userimg.getRoleShow().getRole_id()) != 0) {
            return;
        }
        int sum = GoodsListFromServerUntil.Surplussum((goodstable != null) ? (goodstable.getType() + "") : "6500", goodstable.getGoodsid().toString(), 1);
        if (sum > 0) {
            if (goodstable.getUsetime() == null) {
                goodstable.setUsetime(Integer.valueOf(1));
            }
            goodstable.setStatus(Integer.valueOf(0));
            GoodsListFromServerUntil.addGood(goodstable);
            ZhuFrame.getZhuJpanel().addPrompt2("鍏冩棪瀛佃泲鎴愬姛锛屾伃鍠滀綘鑾峰緱" + goodstable.getGoodsname() + "锛侊紒锛�");
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("鑳屽寘涓嶈冻锛岃嚜鍔ㄤ涪寮�" + goodstable.getGoodsname() + "锛侊紒锛�");
        }
    }
}
