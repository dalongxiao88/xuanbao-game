package org.come.control;

import org.come.MountShouHu.ShouhuPackJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.DDGoodUntil;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.action.FromServerAction;

public class GetPawmControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Goodstable goodsBean = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(mes, Goodstable.class);
        if ((int)goodsBean.getStatus() == 2) {
            DDGoodUntil.addddgood(goodsBean);
        }
        else {
            GoodsListFromServerUntil.addGood(goodsBean);
            if ((long)goodsBean.getType() == 2255L) {
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().add(goodsBean);
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().updata();
            }
        }
    }
}
