package com.tool.btn;

import org.come.entity.Goodstable;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.SummonPetBean;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import org.come.model.petExchange;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.SummoningCompoundJpanel;

public class SummoningCompoundBtn extends MoBanBtn
{
    private int caozuo;
    private SummoningCompoundJpanel compoundJpanel;
    
    public SummoningCompoundBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, SummoningCompoundJpanel compoundJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        new petExchange();
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.compoundJpanel = compoundJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                return;
            }
            petExchange petExchange = this.compoundJpanel.getPetExchange();
            if (petExchange == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中召唤兽");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.compoundJpanel.getMoney()) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("金额不足");
                return;
            }
            String consume = petExchange.getConsume();
            if (consume != null && !"".equals(consume)) {
                String[] split = consume.split("\\|");
                for (int i = 0; i < split.length; ++i) {
                    if (split[i].startsWith("G")) {
                        String[] arrMoney = split[i].split("=");
                        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(arrMoney[1]));
                        int needMum = Integer.parseInt(arrMoney[2]);
                        int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                        if (needMum > goodNum) {
                            ZhuFrame.getZhuJpanel().addPrompt2("合成材料不足");
                            return;
                        }
                    }
                }
            }
            SummonPetBean summonPetBean = new SummonPetBean();
            summonPetBean.setOpertype(2);
            summonPetBean.setPetid(new BigDecimal(petExchange.geteId()));
            String mes = Agreement.getAgreement().summonpetAgreement(GsonUtil.getGsonUtil().getgson().toJson(summonPetBean));
            SendMessageUntil.toServer(mes);
        }
    }
}
