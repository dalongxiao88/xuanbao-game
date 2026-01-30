package org.come.control;

import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.Frame.ZhuFrame;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.action.FromServerAction;

public class RichMonitorControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("2")) {
            mes = mes.substring(1);
            Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(mes, Goodstable.class);
            ZhuFrame.getZhuJpanel().creatgoodtext2(goodstable);
        }
        else if (mes.startsWith("3")) {
            mes = mes.substring(1);
            RoleSummoning pet = (RoleSummoning)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleSummoning.class);
            ZhuFrame.getZhuJpanel().creatpettext2(pet);
        }
        else if (mes.startsWith("4")) {
            mes = mes.substring(1);
            Lingbao lingbao = (Lingbao)GsonUtil.getGsonUtil().getgson().fromJson(mes, Lingbao.class);
            ZhuFrame.getZhuJpanel().creatlingtext2(lingbao);
        }
    }
}
