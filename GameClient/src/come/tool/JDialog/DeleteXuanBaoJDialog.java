package come.tool.JDialog;

import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.bean.XuanBao;
import org.come.until.UserData;

public class DeleteXuanBaoJDialog implements TiShiChuLi {
    public DeleteXuanBaoJDialog() {
    }

    @Override
    public void tipBox(boolean tishi, Object object) {
        XuanBao xuanBao = (XuanBao) object;
        if (tishi) {
            // 用户确认删除
            deleteXuanBao(xuanBao);
        }
    }

    /**
     * 删除当前选中的玄宝
     */
    private void deleteXuanBao(XuanBao xuanBao) {
        if (xuanBao == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择一个玄宝");
            return;
        }

        // 检查玄宝是否正在使用中（装备状态）
        if (xuanBao.getEquipment() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt2("该玄宝正在使用中，请先卸下后再删除");
            return;
        }
        xuanBao.setOperation("删除");
        // 直接通过UserData发送到服务器进行删除
        UserData.upling(xuanBao);
        // 客户端本地删除
        RoleXuanBao.getRoleXuanBao().deleteling(xuanBao);
        RoleXuanBao.getRoleXuanBao().choseBao = null;

        ZhuFrame.getZhuJpanel().addPrompt2("玄宝已成功删除");
    }
}
