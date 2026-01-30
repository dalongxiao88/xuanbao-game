package org.come.good;

import org.come.bean.LoginResult;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.mouslisten.GoodsMouslisten;
import com.tool.PlayerKill.PKSys;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.PackGiftBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.EquipTool;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import java.util.ArrayList;
import org.come.entity.Goodstable;

public class Box
{
    public static void Novice(Goodstable goodstable) {
        List<Goodstable> goodList = new ArrayList<>();
        String[] v = goodstable.getValue().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals("等级要求")) {
                if (!AnalysisString.lvlfull((int)ImageMixDeal.userimg.getRoleShow().getGrade(), v2[1])) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你等级太低");
                    return;
                }
            }
            else if (v2[0].equals("物品")) {
                String[] v3 = v2[1].split("\\&");
                for (int j = 0; j < v3.length; ++j) {
                    String[] v4 = v3[j].split("\\$");
                    Goodstable addgood = UserMessUntil.getgoodstable(new BigDecimal(v4[0]));
                    if (addgood != null) {
                        if (EquipTool.isEquip(addgood.getType())) {
                            for (int vs = Integer.parseInt(v4[1]), k = 0; k < vs; ++k) {
                                addgood.setRole_id(ImageMixDeal.userimg.getRoleShow().getRole_id());
                                addgood.setUsetime(Integer.valueOf(1));
                                goodList.add(addgood);
                            }
                        }
                        else {
                            addgood.setRole_id(ImageMixDeal.userimg.getRoleShow().getRole_id());
                            addgood.setUsetime(Integer.valueOf(Integer.parseInt(v4[1])));
                            goodList.add(addgood);
                        }
                    }
                }
            }
        }
        if (goodList.size() == 0) {
            return;
        }
        if (GoodsListFromServerUntil.Surplussum("-1", "-1", goodList.size()) != goodList.size()) {
            ZhuFrame.getZhuJpanel().addPrompt2("你的背包不够");
            return;
        }
        goodstable.setUsetime(Integer.valueOf(0));
        PackGiftBean bean = new PackGiftBean();
        List<String> goods = new ArrayList<>();
        for (int l = 0; l < goodList.size(); ++l) {
            Goodstable good = (Goodstable)goodList.get(l);
            if ((long)good.getType() == 60001L || (long)good.getType() == 60002L) {
                goodstable.setUsetime(Integer.valueOf(1));
                goodstable.setValue(good.getValue());
                goodstable.setType(good.getType());
            }
            else {
                goods.add(good.getGoodsid() + "|" + good.getUsetime());
            }
            good.setUsetime(Integer.valueOf(1));
        }
        ZhuFrame.getZhuJpanel().addPrompt2("你使用了" + goodstable.getGoodsname());
        bean.setGoodstable(goodstable);
        bean.setGoods(goods);
        String mes = Agreement.getAgreement().packgiftAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessageUntil.toServer(mes);
    }
    
    public static void tesheling(Goodstable goodstable) {
        PKSys pkSys = PKSys.getPkSys();
        if (pkSys.getPk1() > 0) {
            pkSys.expiation();
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你身上没有PK点,就不要瞎捣乱,特赦令就不还给你了");
        }
        goodstable.goodxh(1);
        GoodsMouslisten.gooduse(goodstable, 1);
    }
    
    public static void xms(Goodstable goodstable) {
        Goodstable[] Goodstables = GoodsListFromServerUntil.getChoseGoodsList();
        for (int i = 0; i < Goodstables.length; ++i) {
            if (Goodstables[i] != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请脱下装备");
                return;
            }
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = AnalysisString.lvlint((int)loginResult.getGrade());
        loginResult.setBone(Integer.valueOf(lvl));
        loginResult.setSpir(Integer.valueOf(lvl));
        loginResult.setPower(Integer.valueOf(lvl));
        loginResult.setSpeed(Integer.valueOf(lvl));
        if (loginResult.getTurnAround() >= 4) {
            loginResult.setCalm(Integer.valueOf(lvl));
        }
        loginResult.setHp(new BigDecimal(RoleProperty.getHp(loginResult)));
        loginResult.setMp(new BigDecimal(RoleProperty.getMp(loginResult)));
        ZhuFrame.getZhuJpanel().addPrompt2("你使用了" + goodstable.getGoodsname());
        goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() - 1));
        GoodsMouslisten.gooduse(goodstable, 1);
        PetAddPointMouslisten.getplayerValue();
        String mes = Agreement.getAgreement().rolechangeAgreement("D" + loginResult.getBone() + "=" + loginResult.getSpir() + "=" + loginResult.getPower() + "=" + loginResult.getSpeed() + "=" + loginResult.getCalm());
        SendMessageUntil.toServer(mes);
    }
}
