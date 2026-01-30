package org.come.npc;

import org.come.model.Shop;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.Frame.ShoppingBuyJframe;
import org.come.until.UserMessUntil;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class NpcShop implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (NPCJfram.getNpcJfram().getNpcjpanel().getMapMonsterBean() != null && NPCJfram.getNpcJfram().getNpcjpanel().getMapMonsterBean().getRobottype() == 2) {
            String npctype = Agreement.getAgreement().clickMonstersAgreement("" + NPCJfram.getNpcJfram().getNpcjpanel().getMapMonsterBean().getI());
            SendMessageUntil.toServer(npctype);
        }
        else {
            String npctype = NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean().getNpctable().getNpctype();
            if (!npctype.equals("1106") && !npctype.equals("515") && !npctype.equals("605")) {
                List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get(npctype);
                ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop, npctype, (BigDecimal)null);
                FormsManagement.showForm(23);
            }
            else {
                String senmes = Agreement.getAgreement().BuyNPCGoodsAgreement(npctype);
                SendMessageUntil.toServer(senmes);
            }
        }
    }
}
