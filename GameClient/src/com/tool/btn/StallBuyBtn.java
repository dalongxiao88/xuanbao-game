package com.tool.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.bean.LoginResult;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.Stall.StallBuy;
import com.tool.role.RoleData;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.StallBuyJpanel;
import com.tool.Stall.Commodity;

public class StallBuyBtn extends MoBanBtn
{
    private Commodity commodity;
    private StallBuyJpanel buyJpanel;
    
    public StallBuyBtn(String path, int type, Color[] colors, Font font, String text, StallBuyJpanel buyJpanel) {
        super(path, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.buyJpanel = buyJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (e.getButton() == 1) {
            if (this.commodity == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你还没有选中商品");
            }
            else {
                int sum = Integer.parseInt(this.buyJpanel.getTextNumber().getText());
                if (sum <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你购买数量为0");
                }
                else {
                    Goodstable good = this.commodity.getGood();
                    RoleSummoning pet = this.commodity.getPet();
                    if (good != null) {
                        if ((int)good.getUsetime() < sum) {
                            ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                            return;
                        }
                        sum = GoodsListFromServerUntil.Surplussum("" + good.getType(), "" + good.getGoodsid(), sum);
                        if (sum <= 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
                            return;
                        }
                        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                        if (loginResult.getGold().longValue() < this.commodity.getMoney() * (long)sum) {
                            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                            return;
                        }
                        StallBuy stallBuy = new StallBuy();
                        stallBuy.setId(this.buyJpanel.getId());
                        stallBuy.setRoleid(loginResult.getRole_id());
                        stallBuy.setType(0);
                        stallBuy.setBuyid(good.getRgid());
                        stallBuy.setSum(sum);
                        String sendMes = Agreement.getAgreement().stallbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(stallBuy));
                        SendMessageUntil.toServer(sendMes);
                    }
                    else if (pet != null) {
                        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                        Configure configure = (Configure)item.get(new BigDecimal(1));
                        if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                            ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                            return;
                        }
                        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                        if (loginResult.getGold().longValue() < this.commodity.getMoney()) {
                            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                            return;
                        }
                        StallBuy stallBuy = new StallBuy();
                        stallBuy.setId(this.buyJpanel.getId());
                        stallBuy.setRoleid(loginResult.getRole_id());
                        stallBuy.setType(1);
                        stallBuy.setBuyid(pet.getSid());
                        stallBuy.setSum(1);
                        String sendMes = Agreement.getAgreement().stallbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(stallBuy));
                        SendMessageUntil.toServer(sendMes);
                    }
                }
            }
        }
    }
    
    public Commodity getCommodity() {
        return this.commodity;
    }
    
    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
