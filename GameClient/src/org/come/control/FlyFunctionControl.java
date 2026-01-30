package org.come.control;

import org.come.Jpanel.AircraftJPanel;
import org.come.until.FormsManagement;
import org.come.until.ExpIncreaseUntil;
import com.tool.image.ImageMixDeal;
import org.come.entity.Fly;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.AircraftJframe;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.bean.FlyResult;
import org.come.action.FromServerAction;

public class FlyFunctionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        FlyResult flyResult = (FlyResult)GsonUtil.getGsonUtil().getgson().fromJson(mes, FlyResult.class);
        LoginResult loginResult = new LoginResult();
        AircraftJPanel FlyJPanel = AircraftJframe.getAircraftJframe().getaircraftJPanel();
        FlyJPanel.getmodelfly().removeAllElements();
        if (flyResult.getFlys().size() != 0) {
            int index = -1;
            ZhuJpanel.getListFly().clear();
            for (int i = 0; i < flyResult.getFlys().size(); ++i) {
                Fly fly = (Fly)flyResult.getFlys().get(i);
                ZhuJpanel.getListFly().add(fly);
                if ((int)fly.getFlytid() == ImageMixDeal.userimg.getRoleShow().getFly_id()) {
                    index = i;
                    FlyJPanel.getmodelfly().addElement("*" + fly.getFlyname());
                    FlyJPanel.setSkin(fly.getSkin());
                    FlyJPanel.getLabStage().setText(fly.getFlytid().toString());
                    FlyJPanel.getLabName().setText(fly.getFlyname().toString());
                }
                else {
                    FlyJPanel.getmodelfly().addElement(fly.getFlyname());
                    FlyJPanel.setSkin(fly.getSkin());
                    FlyJPanel.getLabStage().setText(fly.getFlystate().toString());
                    FlyJPanel.getLabName().setText(fly.getFlyname().toString());
                }
            }
            if (index != -1) {
                FlyJPanel.getlistfly().setSelectedIndex(index);
                ExpIncreaseUntil.ShouFlyValue((Fly)flyResult.getFlys().get(index));
                FlyJPanel.getBtnFight().setText("待机");
            }
            else {
                FlyJPanel.getlistfly().setSelectedIndex(0);
                ExpIncreaseUntil.ShouFlyValue((Fly)flyResult.getFlys().get(0));
                FlyJPanel.getBtnFight().setText("飞行");
            }
        }
        FormsManagement.showForm(119);
    }
}
