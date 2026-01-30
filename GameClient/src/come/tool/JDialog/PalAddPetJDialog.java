package come.tool.JDialog;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.PartnerJframe;
import com.tool.role.RoleData;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;
import org.come.entity.RoleSummoning;

public class PalAddPetJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi && object instanceof RoleSummoning) {
            RoleSummoning pet = (RoleSummoning)object;
            int petid = Integer.parseInt(pet.getSummoningid());
            if (pet.getPetlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("该召唤兽已加锁，不能分配！！！");
                return;
            }
            if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽被管制中，不可分配！！！");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(pet.getSid()) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽已在参战中！！！");
                return;
            }
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            if (mainJpanel.getPalDataChooseId() < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                return;
            }
            Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
            if (pidGetPal == null) {
                return;
            }
            String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|pet|" + pet.getSid());
            SendMessageUntil.toServer(sendmes);
            ChangeMouseSymbolMouslisten.releasePetFP(pet);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
        }
    }
}
