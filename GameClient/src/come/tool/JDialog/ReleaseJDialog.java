package come.tool.JDialog;

import org.come.until.FormsManagement;
import org.come.until.ExpIncreaseUntil;
import com.tool.image.ImageMixDeal;
import org.come.Frame.MountJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.entity.Mount;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import com.tool.role.RoleData;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;
import org.come.entity.RoleSummoning;

public class ReleaseJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (object instanceof RoleSummoning) {
                RoleSummoning pet = (RoleSummoning)object;
                if (pet.getPetlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该召唤兽已加锁，不能放生。。");
                    return;
                }
                if (pet.getGoods() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt("该召唤兽携带着装备");
                    return;
                }
                if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽被管制中，不可放生！！！");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(pet.getSid()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽已在参战中！！！");
                    return;
                }
                ChangeMouseSymbolMouslisten.releasePet(pet);
            }
            else if (object instanceof Mount) {
                Mount mount = (Mount)object;
                String sendmes = Agreement.getAgreement().mountreleaseAgreement("" + mount.getMid());
                SendMessageUntil.toServer(sendmes);
                int index = -1;
                int exi = 0;
                while (exi < ZhuJpanel.getListMount().size()) {
                    if (mount == ZhuJpanel.getListMount().get(exi)) {
                        index = exi;
                        ZhuJpanel.getListMount().remove(exi);
                        break;
                    }
                    else {
                        ++exi;
                    }
                }
                MountJframe.getMountjframe().getMountjpanel().getModelmount().remove(index);
                if (ZhuJpanel.getListMount().size() <= 0) {
                    MountJframe.getMountjframe().getMountjpanel().getLabmountlevel().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getLabmounttili().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getLabmountintelligence().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getLabmountpower().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getLabmountRootbone().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getLabmountexp().setText("");
                    MountJframe.getMountjframe().getMountjpanel().getListmount().setSelectedIndex(-1);
                }
                else if (ImageMixDeal.userimg.getRoleShow().getMount_id() == 0) {
                    ExpIncreaseUntil.showMountValue((Mount)ZhuJpanel.getListMount().get(0));
                    MountJframe.getMountjframe().getMountjpanel().getListmount().setSelectedIndex(0);
                }
                else {
                    exi = 0;
                    for (int i = 0; i < ZhuJpanel.getListMount().size(); ++i) {
                        if ((int)((Mount)ZhuJpanel.getListMount().get(i)).getMountid() == ImageMixDeal.userimg.getRoleShow().getMount_id()) {
                            exi = i;
                        }
                    }
                    ExpIncreaseUntil.showMountValue((Mount)ZhuJpanel.getListMount().get(exi));
                    MountJframe.getMountjframe().getMountjpanel().getListmount().setSelectedIndex(exi);
                }
                FormsManagement.HideForm(20);
            }
        }
    }
}
