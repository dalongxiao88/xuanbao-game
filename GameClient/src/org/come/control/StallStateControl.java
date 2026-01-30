package org.come.control;

import com.tool.image.ImageMixDeal;
import org.come.until.GsonUtil;
import com.tool.Stall.StallBean;
import org.come.action.FromServerAction;

public class StallStateControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        StallBean bean = (StallBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, StallBean.class);
        ImageMixDeal.stall(bean);
    }
}
