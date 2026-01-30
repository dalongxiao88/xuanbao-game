package org.come.control;

import com.google.gson.reflect.TypeToken;
import com.tool.Stall.Commodity;
import com.tool.Stall.Stall;
import com.tool.Stall.StallBean;
import com.tool.role.RoleData;
import org.come.Frame.BoothBoxJframe;
import org.come.Frame.TransJframe;
import org.come.Frame.XYDJLSJframe;
import org.come.Jpanel.TestPetJpanel;
import org.come.action.FromServerAction;
import org.come.bean.GoodTrans;
import org.come.bean.Goodsrecord;
import org.come.bean.PayvipBean;
import org.come.bean.PetResultBean;
import org.come.entity.RoleSummoning;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;
import org.come.until.UserMessUntil;

import java.util.List;

public class XYDJControl implements FromServerAction {

    @Override
    public void controlMessFromServer(String mes, String type) {

        List<Goodsrecord> goodsrecords = GsonUtil.getGsonUtil().getgson().fromJson(mes, new TypeToken<List<Goodsrecord>>() {
        }.getType());
        XYDJLSJframe.getTeamApplyJframe().getXYDJLSJpanel().showInfo(goodsrecords);
        FormsManagement.showForm(30001);
    }


}
