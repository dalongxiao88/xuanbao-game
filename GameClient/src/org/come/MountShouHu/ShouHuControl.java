package org.come.MountShouHu;

import org.come.until.Music;
import org.come.until.FormsManagement;
import org.come.until.CutButtonImage;
import com.tool.btn.MountPanelBtn;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.Frame.MountShouhuJframe;
import org.come.action.FromServerAction;

public class ShouHuControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String message, String type) {
        MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu = (ShouHu)GsonUtil.getGsonUtil().getgson().fromJson(message, ShouHu.class);
        MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shou();
        ShouHu shouHu = MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu;
        if (!MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.is) {
            RoleData.getRoleData().getLoginResult().setZhongtian(shouHu.getZhongtianlvl() + "|" + shouHu.getZhongtianxiuweidian() + "|" + shouHu.getZhongtianmount());
            RoleData.getRoleData().getLoginResult().setQinglong(shouHu.getQinglonglvl() + "|" + shouHu.getQinglongxiuweidian() + "|" + shouHu.getQinglongmount());
            RoleData.getRoleData().getLoginResult().setBaihu(shouHu.getBaihulvl() + "|" + shouHu.getBaihuxiuweidian() + "|" + shouHu.getBaihumount());
            RoleData.getRoleData().getLoginResult().setXuanwu(shouHu.getXuanwulvl() + "|" + shouHu.getXuanwuxiuweidian() + "|" + shouHu.getXuanwumount());
            RoleData.getRoleData().getLoginResult().setZhuque(shouHu.getZhuquelvl() + "|" + shouHu.getZhuquexiuweidian() + "|" + shouHu.getZhuquemount());
            return;
        }
        try {
            ((MountPanelBtn)MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getShouhu().get(0)).setIcons(CutButtonImage.cuts("inkImg/Client/未选中按钮.png"));
            ((MountPanelBtn)MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getShouhu().get(1)).setIcons(CutButtonImage.cuts("inkImg/Client/选中按钮.png"));
            MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shou();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FormsManagement.showForm(559);
        Music.addyinxiao("开关窗口.mp3");
    }
}
