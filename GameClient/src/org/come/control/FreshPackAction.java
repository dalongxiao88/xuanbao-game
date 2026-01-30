package org.come.control;

import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.FreshPackBean;
import org.come.action.FromServerAction;

public class FreshPackAction implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        FreshPackBean freshPackBean = (FreshPackBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, FreshPackBean.class);
        if (freshPackBean != null && freshPackBean.getGoods() != null) {
            GoodsListFromServerUntil.Classification(RoleData.getRoleData().getLoginResult(), freshPackBean.getGoods(), freshPackBean.getPackRecord().getRecord());
        }
    }
}
