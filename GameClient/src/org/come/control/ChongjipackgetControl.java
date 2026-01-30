package org.come.control;

import org.come.Frame.ImpactGradeJframe;
import org.come.bean.ChongjipackBean;
import com.google.gson.reflect.TypeToken;
import org.come.until.GsonUtil;
import java.util.List;
import org.come.action.FromServerAction;

public class ChongjipackgetControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        List<ChongjipackBean> chongjipackBeans = (List)GsonUtil.getGsonUtil().getgson().fromJson(mes, new TypeToken<List<ChongjipackBean>>() {}.getType());
        ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().showGoods(chongjipackBeans);
    }
}
