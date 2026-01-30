package org.come.control;

import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.MountShouHu.xuanzeJframe;
import org.come.until.CutButtonImage;
import com.tool.btn.MountPanelBtn;
import org.come.Jpanel.mountJPanel;
import com.tool.role.RoleData;
import org.come.until.ExpIncreaseUntil;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import org.come.until.UserMessUntil;
import org.come.Frame.MountJframe;
import org.come.until.GsonUtil;
import org.come.bean.MountResult;
import org.come.action.FromServerAction;

public class MountFunctionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        MountResult mountResult = (MountResult)GsonUtil.getGsonUtil().getgson().fromJson(mes, MountResult.class);
        mountJPanel mountJPanel = MountJframe.getMountjframe().getMountjpanel();
        UserMessUntil.setMountList(mountResult.getMounts());
        mountJPanel.getModelmount().removeAllElements();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        try {
            if (mountResult.getMounts().size() != 0) {
                int index = -1;
                ZhuJpanel.getListMount().clear();
                for (int i = 0; i < mountResult.getMounts().size(); ++i) {
                    Mount mount = (Mount)mountResult.getMounts().get(i);
                    ZhuJpanel.getListMount().add(mount);
                    if ((int)mount.getMountid() == ImageMixDeal.userimg.getRoleShow().getMount_id()) {
                        index = i;
                        mountJPanel.getModelmount().addElement("( * ) " + mount.getMountname().split("]")[1]);
                        if (mount.getMoveGrade() != null) {
                            Util.moveGrade = (int)mount.getMoveGrade();
                        }
                    }
                    else {
                        mountJPanel.getModelmount().addElement(mount.getMountname().split("]")[1]);
                    }
                }
                if (index != -1) {
                    mountJPanel.getListmount().setSelectedIndex(index);
                    ExpIncreaseUntil.showMountValue((Mount)mountResult.getMounts().get(index));
                    mountJPanel.getBtnRiding().setText("休息");
                    if (((Mount)mountResult.getMounts().get(index)).getMoveGrade() != null) {
                        Util.moveGrade = (int)((Mount)mountResult.getMounts().get(index)).getMoveGrade();
                    }
                }
                else {
                    mountJPanel.getListmount().setSelectedIndex(0);
                    ExpIncreaseUntil.showMountValue((Mount)mountResult.getMounts().get(0));
                    mountJPanel.getBtnRiding().setText("骑乘");
                }
            }
            else {
                mountJPanel.getLabmountlevel().setText("");
                mountJPanel.getLabmounttili().setText("");
                mountJPanel.getLabmountintelligence().setText("");
                mountJPanel.getLabmountpower().setText("");
                mountJPanel.getLabmountRootbone().setText("");
                mountJPanel.getLabmountexp().setText("");
            }
            org.come.Jpanel.mountJPanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
            try {
                ((MountPanelBtn)mountJPanel.getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
                ((MountPanelBtn)mountJPanel.getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
                xuanzeJframe.getxuanzeJframe().getXuanzeJpanel().shoumount(mountResult.getMounts());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            FormsManagement.showForm(7);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
