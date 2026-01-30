package org.come.control;

import java.util.concurrent.ConcurrentHashMap;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.bean.ShaoXiangLimit;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.bean.ShaoXiangLimitResultBean;
import org.come.action.FromServerAction;

public class ShaoXiangControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("GETLIMIT")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                ShaoXiangLimitResultBean sa = (ShaoXiangLimitResultBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], ShaoXiangLimitResultBean.class);
                UserMessUntil.setAllshaoxianglimit(sa.getAllshaoxianglimit());
                UserMessUntil.setAllshaoxiang(sa.getAllshaoxiang());
            }
        }
        else if (mes.startsWith("UPDATELIMIT")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                ShaoXiangLimit shlimit = (ShaoXiangLimit)GsonUtil.getGsonUtil().getgson().fromJson(result[1], ShaoXiangLimit.class);
                ConcurrentHashMap<String, ShaoXiangLimit> allshlimit = UserMessUntil.getAllshaoxianglimit();
                ShaoXiangLimit oshlimit = (ShaoXiangLimit)allshlimit.get(shlimit.getName());
                if (oshlimit == null) {
                    UserMessUntil.getAllshaoxianglimit().put(shlimit.getName(), shlimit);
                }
                else {
                    oshlimit.setNum(shlimit.getNum());
                }
            }
        }
        else if (mes.startsWith("MODGOODS")) {
            String[] result = mes.split("\\@");
            if (result[1] != null) {
                Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(result[1], Goodstable.class);
                if (GoodsListFromServerUntil.getChoseGoodsList() != null && GoodsListFromServerUntil.getChoseGoodsList().length > 0) {
                    for (int i = 0; i < GoodsListFromServerUntil.getChoseGoodsList().length; ++i) {
                        if (GoodsListFromServerUntil.getChoseGoodsList()[i] != null && GoodsListFromServerUntil.getChoseGoodsList()[i].getRgid() != null && goodstable.getRgid().compareTo(GoodsListFromServerUntil.getChoseGoodsList()[i].getRgid()) == 0) {
                            GoodsListFromServerUntil.getChoseGoodsList()[i] = goodstable;
                        }
                    }
                }
            }
        }
    }
}
