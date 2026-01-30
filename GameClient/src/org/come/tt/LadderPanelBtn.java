package org.come.tt;

import org.come.model.Shop;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ShoppingBuyJframe;
import org.come.until.UserMessUntil;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.npc.TP;
import java.awt.event.MouseEvent;
import org.come.bean.LoginResult;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class LadderPanelBtn extends MoBanBtn
{
    private int num;
    
    public LadderPanelBtn(String iconpath, int type, int num) {
        super(iconpath, type);
        this.num = num;
    }
    
    public LadderPanelBtn(String iconpath, int type, String text, Color[] colors, Font font, int num) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.num = num;
    }
    
    @Override
    public void chooseyes() {
    }
    
    public void btnjt() {
    }
    
    @Override
    public void chooseno() {
        this.btnjt();
    }
    
    private void enterTheLadder() {
        String[] v = ImageMixDeal.userimg.getTeams();
        if (v != null) {
            int palSum = 0;
            LoginResult login = RoleData.getRoleData().getLoginResult();
            if (login.getPals() != null && !login.getPals().equals("")) {
                palSum = login.getPals().split("\\|").length;
            }
            if (v.length + palSum < 5) {
                ZhuFrame.getZhuJpanel().addPrompt2("队伍人数不够5个人,先凑齐人数在来吧");
                return;
            }
        }
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.num) {
            case 1: {
                TP.tp(TP.scdoor("4444", 840, 500), 5);
                FormsManagement.HideForm(604);
                break;
            }
            case 2: {
                List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("2029");
                ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop, "2029", null);
                FormsManagement.showForm(23);
                break;
            }
            case 3: {
                String sendmes = Agreement.getAgreement().laddArenaAgreement("7");
                SendMessageUntil.toServer(sendmes);
                break;
            }
            case 4: {
                String sendmes = Agreement.getAgreement().laddArenaAgreement("4");
                SendMessageUntil.toServer(sendmes);
                break;
            }
            case 5: {
                String sendmes = Agreement.getAgreement().laddArenaAgreement("5");
                SendMessageUntil.toServer(sendmes);
                break;
            }
            case 6: {
                String sendmes = Agreement.getAgreement().laddArenaAgreement("6");
                SendMessageUntil.toServer(sendmes);
                break;
            }
        }
    }
}
