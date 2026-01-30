package org.come.control;

import com.tool.btn.MountPanelBtn;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.role.RoleProperty;
import org.come.Frame.CarJframe;
import org.come.Frame.MountJframe;
import org.come.Jpanel.ZhuJpanel;
import org.come.Jpanel.carJPanel;
import org.come.Jpanel.mountJPanel;
import org.come.MountShouHu.xuanzeJframe;
import org.come.action.FromServerAction;
import org.come.bean.CarResult;
import org.come.bean.MountResult;
import org.come.entity.Car;
import org.come.entity.Mount;
import org.come.entity.RoleSummoning;
import org.come.until.*;

import java.util.List;

public class CarFunctionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        CarResult mountResult = (CarResult)GsonUtil.getGsonUtil().getgson().fromJson(mes, CarResult.class);
        carJPanel carjpanel = CarJframe.getMountjframe().getCarjpanel();
//        UserMessUntil.setMountList(mountResult.getMounts());
        carjpanel.getModelmount().removeAllElements();
//        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        try {
            if (mountResult.getCars().size() != 0) {
                int index = -1;
                ZhuJpanel.getListCar().clear();
                for (int i = 0; i < mountResult.getCars().size(); ++i) {
                    Car car = (Car)mountResult.getCars().get(i);
                    ZhuJpanel.getListCar().add(car);
                    if ((int)car.getMountid() == ImageMixDeal.userimg.getRoleShow().getCar_id()) {
                        index = i;
                        carjpanel.getModelmount().addElement("( * ) " + car.getMountname());
//                        if (car.getMoveGrade() != null) {
//                            Util.moveGrade = (int)car.getMoveGrade();
//                        }
                    }
                    else {
                        carjpanel.getModelmount().addElement(car.getMountname());
                    }
                }
                if (index != -1) {
                    carjpanel.getListmount().setSelectedIndex(index);
                    ExpIncreaseUntil.showCarValue((Car)mountResult.getCars().get(index));
                    carjpanel.getBtnRiding().setText("休息");
//                    if (((Car)mountResult.getCars().get(index)).getMoveGrade() != null) {
//                        Util.moveGrade = (int)((Car)mountResult.getCars().get(index)).getMoveGrade();
//                    }
                }
                else {
                    carjpanel.getListmount().setSelectedIndex(0);
                    ExpIncreaseUntil.showCarValue((Car) mountResult.getCars().get(0));
                    carjpanel.getBtnRiding().setText("骑乘");
                }
            }
            else {
//                carjpanel.getLabmountlevel().setText("");
//                carjpanel.getLabmounttili().setText("");
                carjpanel.getLabmountintelligence().setText("");
                carjpanel.getLabmountpower().setText("");
                carjpanel.getLabmountRootbone().setText("");
                carjpanel.getLabmountexp().setText("");
            }
//            org.come.Jpanel.mountJPanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
//            try {
//                ((MountPanelBtn)carjpanel.getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
//                ((MountPanelBtn)carjpanel.getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
//                xuanzeJframe.getxuanzeJframe().getXuanzeJpanel().shoumount(mountResult.getMounts());
//            }
//            catch (Exception exception) {
//                exception.printStackTrace();
//            }
            FormsManagement.showForm(1002);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
