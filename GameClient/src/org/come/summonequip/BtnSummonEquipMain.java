package org.come.summonequip;

import org.come.bean.QualityClBean;
import org.come.model.Shop;
import java.util.List;
import org.come.entity.Goodstable;
import com.tool.btn.BaptizeBtn;
import org.come.bean.BuyShopBean;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.PartJade;
import java.math.BigDecimal;
import java.util.ArrayList;
import com.tool.role.RoleData;
import org.come.bean.SuitOperBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class BtnSummonEquipMain extends MoBanBtn
{
    private int caozuo;
    private JpanelSummonEquipMain jpanelSummonEquipMain;
    private JpanelNowEquip jpanelNowEquip;
    private JpanelCashRewardsMain jpanelCashRewardsMain;
    private JpanelReclaimSkillMain jpanelReclaimSkillMain;
    
    public BtnSummonEquipMain(String iconpath, int type, String text, int caozuo, JpanelSummonEquipMain jpanelSummonEquipMain) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
    }
    
    public BtnSummonEquipMain(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelSummonEquipMain jpanelSummonEquipMain) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
    }
    
    public BtnSummonEquipMain(String iconpath, int type, String text, int caozuo, JpanelNowEquip jpanelNowEquip) {
        super(iconpath, type);
        this.setText(text);
        this.caozuo = caozuo;
        this.jpanelNowEquip = jpanelNowEquip;
    }
    
    public BtnSummonEquipMain(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelCashRewardsMain jpanelCashRewardsMain) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.jpanelCashRewardsMain = jpanelCashRewardsMain;
    }
    
    public BtnSummonEquipMain(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelReclaimSkillMain jpanelReclaimSkillMain) {
        super(iconpath, type, colors);
        this.setText(text);
        this.caozuo = caozuo;
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanelReclaimSkillMain = jpanelReclaimSkillMain;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo <= 10) {
            this.jpanelSummonEquipMain.changeMenuView(this.caozuo);
        }
        else if (this.caozuo == 11) {
            if (!this.jpanelSummonEquipMain.getJpanelNowEquip().isVisible()) {
                this.jpanelSummonEquipMain.getpaneMessage();
            }
            this.jpanelSummonEquipMain.getJpanelNowEquip().setVisible(!this.jpanelSummonEquipMain.getJpanelNowEquip().isVisible());
            this.jpanelSummonEquipMain.getScrollPane().setVisible(false);
        }
        else if (this.caozuo == 12) {
            if (this.jpanelSummonEquipMain.getChooseEquip() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择召唤兽装备");
                return;
            }
            Goodstable chooseEquip = GoodsListFromServerUntil.getRgid(this.jpanelSummonEquipMain.getChooseEquip());
            if (chooseEquip == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中召唤兽装备");
                return;
            }
            String[] values = chooseEquip.getValue().split("\\|");
            SuitOperBean suitOperBean = new SuitOperBean();
            if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 1 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                if (this.jpanelSummonEquipMain.getChooseGoods() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选择千年魂石");
                    return;
                }
                Goodstable chooseGoods = GoodsListFromServerUntil.getRgid(this.jpanelSummonEquipMain.getChooseGoods());
                if (chooseGoods == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选中玄铁晶石");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.jpanelSummonEquipMain.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足100万");
                    return;
                }
                int valuesMessage = Integer.parseInt(this.jpanelSummonEquipMain.getValuesMessage(values, "通灵"));
                if (valuesMessage >= 6000) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该装备的通灵值已经抵达上限，无法在培养");
                    return;
                }
                suitOperBean.setType(41);
                List<BigDecimal> goods = new ArrayList<>();
                goods.add(chooseEquip.getRgid());
                goods.add(chooseGoods.getRgid());
                suitOperBean.setGoods(goods);
                this.deductGoods(chooseGoods, 1);
                if ((int)chooseGoods.getUsetime() <= 0) {
                    this.jpanelSummonEquipMain.recoverEquipImgTwo();
                }
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 1 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                suitOperBean.setType(42);
                List<BigDecimal> goods2 = new ArrayList<>();
                goods2.add(chooseEquip.getRgid());
                this.deductGoods(chooseEquip, 1);
                suitOperBean.setGoods(goods2);
                this.jpanelSummonEquipMain.recoverEquipImgOne();
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 2 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.jpanelSummonEquipMain.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足100万");
                    return;
                }
                suitOperBean.setType(43);
                List<BigDecimal> goods2 = new ArrayList<>();
                goods2.add(chooseEquip.getRgid());
                Goodstable[] chooseWashGoods = this.jpanelSummonEquipMain.getChooseWashGoods();
                int i = 0;
                while (i < chooseWashGoods.length) {
                    if (chooseWashGoods[i] == null) {
                        if (i == 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请放入玄铁晶石");
                        }
                        else if (i == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请放入内丹精华");
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("材料不齐全");
                        }
                        return;
                    }
                    else {
                        goods2.add(chooseWashGoods[i].getRgid());
                        ++i;
                    }
                }
                suitOperBean.setGoods(goods2);
                for (i = 0; i < chooseWashGoods.length; ++i) {
                    this.deductGoods(chooseWashGoods[i], 1);
                }
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 2 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.jpanelSummonEquipMain.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足500万");
                    return;
                }
                if (this.jpanelSummonEquipMain.getChooseGoods() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选择千年魂石");
                    return;
                }
                Goodstable chooseGoods = GoodsListFromServerUntil.getRgid(this.jpanelSummonEquipMain.getChooseGoods());
                if (chooseGoods == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选中隐月神石");
                    return;
                }
                if (this.jpanelSummonEquipMain.getValuesMessage(values, "觉醒技") == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该装备没有觉醒技");
                    return;
                }
                suitOperBean.setType(44);
                List<BigDecimal> goods3 = new ArrayList<>();
                goods3.add(chooseEquip.getRgid());
                goods3.add(chooseGoods.getRgid());
                suitOperBean.setGoods(goods3);
                this.deductGoods(chooseGoods, 1);
                if ((int)chooseGoods.getUsetime() <= 0) {
                    this.jpanelSummonEquipMain.recoverEquipImgTwo();
                }
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 3 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                long num = Long.parseLong(this.jpanelSummonEquipMain.getNumText().getText());
                if (num <= 0L) {
                    return;
                }
                String valuesMessage2 = this.jpanelSummonEquipMain.getValuesMessage(values, "觉醒技");
                if (valuesMessage2 == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该装备没有觉醒技");
                    return;
                }
                if (new BigDecimal(num).compareTo(RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章")) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("比斗奖章数量不足");
                    return;
                }
                String[] split = valuesMessage2.split("&");
                long exp = Long.parseLong(split[3]);
                if (JpanelSummonEquipMain.expChangeLevel(exp) >= 20L) {
                    ZhuFrame.getZhuJpanel().addPrompt2("等级已经达到最大值");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.jpanelSummonEquipMain.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    return;
                }
                suitOperBean.setType(46);
                suitOperBean.setJade(new PartJade((int)num, 0));
                List<BigDecimal> goods4 = new ArrayList<>();
                goods4.add(chooseEquip.getRgid());
                suitOperBean.setGoods(goods4);
                this.jpanelSummonEquipMain.recoverEquipImgTwo();
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 3 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                if (this.jpanelSummonEquipMain.getValuesMessage(values, "觉醒技") != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该装备已有觉醒技");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.jpanelSummonEquipMain.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    return;
                }
                if (this.jpanelSummonEquipMain.getChooseGoods() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选择千年魂石");
                    return;
                }
                Goodstable chooseGoods = GoodsListFromServerUntil.getRgid(this.jpanelSummonEquipMain.getChooseGoods());
                if (chooseGoods == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选择千年魂石");
                    return;
                }
                suitOperBean.setType(45);
                List<BigDecimal> goods3 = new ArrayList<>();
                goods3.add(chooseEquip.getRgid());
                goods3.add(chooseGoods.getRgid());
                suitOperBean.setGoods(goods3);
                this.deductGoods(chooseGoods, 1);
                if ((int)chooseGoods.getUsetime() <= 0) {
                    this.jpanelSummonEquipMain.recoverEquipImgTwo();
                }
            }
            String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 13) {
            JframeCashRewardsMain.getJframeCashRewardsMain().getJpanelCashRewardsMain().setGoodsType(1);
            FormsManagement.showForm(92);
        }
        else if (this.caozuo == 14) {
            BigDecimal sum = RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章");
            if (sum.compareTo(BigDecimal.valueOf(48450L)) > 0) {
                sum = BigDecimal.valueOf(48450L);
            }
            this.jpanelSummonEquipMain.getNumText().setText(sum.toString());
        }
        else if (this.caozuo == 15) {
            FormsManagement.showForm(93);
        }
        else if (this.caozuo == 20) {
            this.jpanelSummonEquipMain.getScrollPane().setVisible(!this.jpanelSummonEquipMain.getScrollPane().isVisible());
        }
        else if (this.caozuo == 21) {
            if (this.jpanelNowEquip.getNowPage() <= 1) {
                return;
            }
            this.jpanelNowEquip.setNowPage(this.jpanelNowEquip.getNowPage() - 1);
        }
        else if (this.caozuo == 22) {
            if (this.jpanelNowEquip.getNowPage() * 12 > this.jpanelNowEquip.getSummonEquipList().size()) {
                return;
            }
            this.jpanelNowEquip.setNowPage(this.jpanelNowEquip.getNowPage() + 1);
        }
        else if (this.caozuo == 30) {
            Shop chooseShop = this.jpanelCashRewardsMain.getChooseShop();
            if (chooseShop == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择要兑换的物品");
                return;
            }
            int parseInt = Integer.parseInt((this.jpanelCashRewardsMain.getTextNum().getText() == "") ? "0" : this.jpanelCashRewardsMain.getTextNum().getText());
            if (parseInt <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("兑换数量不能为0");
                return;
            }
            int sum2 = GoodsListFromServerUntil.Surplussum(chooseShop.getShoptype() + "", chooseShop.getShopid(), parseInt);
            if (sum2 <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("背包空间不足");
                return;
            }
            BigDecimal shopPrice = this.jpanelCashRewardsMain.getShopPrice();
            if (this.jpanelCashRewardsMain.getGoodsType() == 1) {
                if (shopPrice.compareTo(RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章")) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("比斗奖章不足");
                    return;
                }
            }
            else if (this.jpanelCashRewardsMain.getGoodsType() == 2) {
                if (shopPrice.compareTo(RoleData.getRoleData().getLoginResult().getScoretype("星芒")) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("星芒不足");
                    return;
                }
            }
            else if (this.jpanelCashRewardsMain.getGoodsType() == 3 && shopPrice.compareTo(RoleData.getRoleData().getLoginResult().getScoretype("勇者积分")) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("勇者积分不足");
                return;
            }
            BuyShopBean bean = new BuyShopBean();
            bean.setAte(1);
            bean.setCd(chooseShop.getShopid());
            bean.setSum(parseInt);
            bean.setPrice((long)shopPrice.intValue());
            String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessageUntil.toServer(senmes);
        }
        else if (this.caozuo == 40) {
            FormsManagement.HideForm(94);
            this.jpanelReclaimSkillMain.setQualityClBean(null);
        }
        else if (this.caozuo == 41) {
            QualityClBean qualityClBean = this.jpanelReclaimSkillMain.getQualityClBean();
            if (qualityClBean == null) {
                return;
            }
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getChooseEquip());
            if (goodstable == null) {
                return;
            }
            goodstable.setValue(BaptizeBtn.newExtra(goodstable.getValue().split("\\|"), 5, qualityClBean.getNewAttr()));
            String senmes2 = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(qualityClBean));
            SendMessageUntil.toServer(senmes2);
            this.jpanelReclaimSkillMain.setQualityClBean(null);
            FormsManagement.HideForm(94);
        }
    }
    
    public void deductGoods(Goodstable goodstable, int num) {
        goodstable.goodxh(num);
        if ((int)goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
        }
    }
}
