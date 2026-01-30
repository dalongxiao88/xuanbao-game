package org.come.mouslisten;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.BuyShopBean;
import com.tool.role.RoleData;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import org.come.model.Eshop;
import javax.swing.JLabel;
import java.awt.event.MouseListener;

public class ShopingOnlineBuyBtnForXianYuMouslisten implements MouseListener
{
    private JLabel jButton;
    private Eshop eshop;
    
    public ShopingOnlineBuyBtnForXianYuMouslisten(Eshop eshop, JLabel jButton) {
        this.eshop = eshop;
        this.jButton = jButton;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (!Util.isM()) {
            if (!Util.canBuyOrno) {
                ZhuFrame.getZhuJpanel().addPrompt2("#G背包没有解锁!!");
                FormsManagement.showForm(33);
            }
            else if (!GoodsListFromServerUntil.sureCanBuyOrNo(this.eshop.getEshopiid())) {
                ZhuFrame.getZhuJpanel().addPrompt2("背包空间不足!");
            }
            else {
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                int jg = Integer.parseInt(this.eshop.getEshopprice());
                if ((int)loginResult.getMoney() < jg) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有足够的积分!");
                }
                else {
                    try {
                        BuyShopBean bean = new BuyShopBean();
                        bean.setAte(0);
                        bean.setCd(this.eshop.getEshopid());
                        bean.setSum(1);
                        String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                        SendMessageUntil.toServer(senmes);
                    }
                    catch (Exception var6) {
                        var6.printStackTrace();
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.jButton.setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.jButton.setBorder((Border)null);
    }
}
