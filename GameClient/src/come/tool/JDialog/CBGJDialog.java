package come.tool.JDialog;

import org.come.model.Lingbao;
import java.util.List;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.cbg.entity.Salegoods;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.TestPetJpanel;
import org.come.control.PetControl;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.cbg.panel.TraslationWantSendJishoushangpinJpanel;

public class CBGJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            TraslationWantSendJishoushangpinJpanel jpanel1 = (TraslationWantSendJishoushangpinJpanel)object;
            Salegoods salegoods = jpanel1.getSalegoods();
            if ((int)salegoods.getSaletype() == 3 || (int)salegoods.getSaletype() == 5) {
                Goodstable goodstable = GoodsListFromServerUntil.czGBG(salegoods.getOtherid());
                if (goodstable == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("所选物品不符合上架条件");
                    return;
                }
                goodstable.setUsetime(Integer.valueOf(0));
                GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                String sendmes = Agreement.getAgreement().searchShelfGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(salegoods));
                SendMessageUntil.toServer(sendmes);
                jpanel1.thGood(null);
            }
            else if ((int)salegoods.getSaletype() == 4) {
                RoleSummoning pet = UserMessUntil.getPetRgid(salegoods.getOtherid());
                if (pet == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已不符合上架条件");
                    return;
                }
                if (pet.getQuality() != null) {
                    if (!pet.getQuality().equals("2")) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G【" + pet.getSummoningname() + "】#Y禁止交易！！！");
                        return;
                    }
                }
                else {
                    if (pet.getPetlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽已加锁");
                        return;
                    }
                    if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(pet.getSid()) == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽已参战");
                        return;
                    }
                }
                if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽已管制");
                    return;
                }
                List<RoleSummoning> pets = UserMessUntil.getPetListTable();
                pets.remove(pet);
                UserMessUntil.setPetListTable(PetControl.qc(pets));
                if (pet == UserMessUntil.getChosePetMes()) {
                    UserMessUntil.setChosePetMes(null);
                }
                TestPetJpanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
                jpanel1.qkPet(pet);
                String sendmes2 = Agreement.getAgreement().searchShelfGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(salegoods));
                SendMessageUntil.toServer(sendmes2);
                jpanel1.thGood(null);
            }
            else if ((int)salegoods.getSaletype() == 6) {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().czGBG(salegoods.getOtherid());
                if (lingbao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已不符合上架条件");
                    return;
                }
                RoleLingFa.getRoleLingFa().deleteling(lingbao);
                String sendmes = Agreement.getAgreement().searchShelfGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(salegoods));
                SendMessageUntil.toServer(sendmes);
                jpanel1.thGood(null);
            }
        }
    }
}
