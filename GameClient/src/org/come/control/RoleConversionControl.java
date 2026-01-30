package org.come.control;

import javax.swing.ImageIcon;
import java.util.Map;

import org.come.Frame.*;
import org.come.Jpanel.carJPanel;
import org.come.bean.ConfigureBean;
import org.come.bean.LoginResult;
import org.come.entity.Car;
import org.come.until.*;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.Jpanel.ZhuJpanel;

import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.bean.RoleTransBean;
import org.come.action.FromServerAction;

public class RoleConversionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleTransBean roleTransBean = (RoleTransBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleTransBean.class);
        LoginResult loginResult = roleTransBean.getLoginResult();
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            ImageIcon icon = CutButtonImage.getImage("img/head/s" + loginResult.getSpecies_id() + "-1.png", 58, 58);
            ZhuJpanel.getLabroleimg().setIcon(icon);
        }
        else {
            ImageIcon icon = CutButtonImage.getImage("img/head/s" + loginResult.getSpecies_id() + ".png", 58, 58);
            ZhuJpanel.getLabroleimg().setIcon(icon);
        }
        RoleData.getRoleData().setPrivateData(roleTransBean.getPrivateData());
        RoleProperty.Resetgrade((int)loginResult.getGrade(), loginResult.getRace_id());
        RoleProperty.Resetborn(null, roleTransBean.getPrivateData().getBorn());
        loginResult.setHp(new BigDecimal(RoleProperty.getHp(loginResult)));
        loginResult.setMp(new BigDecimal(RoleProperty.getMp(loginResult)));
        RoleData.getRoleData().setLoginResult(loginResult);
        ImageMixDeal.userimg.getRoleShow().init(loginResult);
        ImageMixDeal.userimg.changeskin(null);
        PetAddPointMouslisten.getplayerValue();
        GoodsListFromServerUntil.GoodExpansion(loginResult.getTurnAround(), loginResult.getAttachPack());
        FormsManagement.HideForm(41);
        FormsManagement.HideForm(7);
        RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel().cleanView();
        if (roleTransBean.getMounts() != null) {
            ZhuJpanel.setListMount(roleTransBean.getMounts());
        }
        if (roleTransBean.getCars() != null) {
            ZhuJpanel.setListCar(roleTransBean.getCars());
        }

        ZhuFrame.getZhuJpanel().clearHotkeySkill();
        if (FormsManagement.getframe(1234).isVisible()) {
            RoleBornJframe.getRoleToggleJframe().getRoleBornJpanel().showStar();
        }
        if (roleTransBean.getRoleAttribute() != null) {
            RoleData.getRoleData().setRoleAttribute(roleTransBean.getRoleAttribute());
            RoleToggleJframe.getRoleToggleJframe().getRoleToggleJpanel().getplayerValue();
        }
        if (roleTransBean.getCars()!=null&&roleTransBean.getCars().size()>0) {
            try{
                carJPanel carjpanel = CarJframe.getMountjframe().getCarjpanel();
                carjpanel.getModelmount().removeAllElements();
                int index = -1;
                ZhuJpanel.getListCar().clear();
                for (int i = 0; i < roleTransBean.getCars().size(); ++i) {
                    Car car = (Car)roleTransBean.getCars().get(i);
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
                    ExpIncreaseUntil.showCarValue((Car)roleTransBean.getCars().get(index));
                    carjpanel.getBtnRiding().setText("休息");
//                    if (((Car)mountResult.getCars().get(index)).getMoveGrade() != null) {
//                        Util.moveGrade = (int)((Car)mountResult.getCars().get(index)).getMoveGrade();
//                    }
                }
                else {
                    if (roleTransBean.getCars() != null && roleTransBean.getCars().size()>0) {
                        carjpanel.getListmount().setSelectedIndex(0);
                        ExpIncreaseUntil.showCarValue((Car) roleTransBean.getCars().get(0));
                        carjpanel.getBtnRiding().setText("骑乘");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public void setRoleAttribute(LoginResult loginResult) {
        if (RoleData.getRoleData().getRoleAttribute() != null) {
            RoleData.getRoleData().getRoleAttribute().setBONEONE(loginResult.getBone());
            RoleData.getRoleData().getRoleAttribute().setSPIRONE(loginResult.getSpir());
            RoleData.getRoleData().getRoleAttribute().setPOWERONE(loginResult.getPower());
            RoleData.getRoleData().getRoleAttribute().setSPEEDONE(loginResult.getSpeed());
            RoleData.getRoleData().getRoleAttribute().setCALMONE(loginResult.getCalm());
            RoleData.getRoleData().getRoleAttribute().setBONETWO(loginResult.getBone());
            RoleData.getRoleData().getRoleAttribute().setSPIRTWO(loginResult.getSpir());
            RoleData.getRoleData().getRoleAttribute().setPOWERTWO(loginResult.getPower());
            RoleData.getRoleData().getRoleAttribute().setSPEEDTWO(loginResult.getSpeed());
            RoleData.getRoleData().getRoleAttribute().setCALMTWO(loginResult.getCalm());
        }
    }
}
