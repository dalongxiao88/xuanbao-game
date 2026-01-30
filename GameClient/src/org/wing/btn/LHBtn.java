package org.wing.btn;

import org.come.entity.PartJade;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.Frame.ZhuFrame;
import org.wing.mouseListener.WingUpStarMouseListener;
import com.tool.role.RoleProperty;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.wing.panel.LHMainPanel;
import com.tool.btn.MoBanBtn;

public class LHBtn extends MoBanBtn
{
    private int caozuo;
    private LHMainPanel LHMainPanel;
    
    public LHBtn(String iconpath, int type, String text, int caozuo, LHMainPanel LHMainPanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.LHMainPanel = LHMainPanel;
    }
    
    public LHBtn(String iconpath, int type, String text, int caozuo, LHMainPanel LHMainPanel, String sm) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.LHMainPanel = LHMainPanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.caozuo) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8: {
                this.changeMenu(this.caozuo);
                break;
            }
            case 10: {
                this.leftBtnCaozuo();
                break;
            }
            case 11: {
                this.rightBtnCaozuo();
                break;
            }
            case 18: {
                if (this.LHMainPanel.getLeftType() == 3) {
                    this.upStarBtnCaozuo();
                    break;
                }
                else if (this.LHMainPanel.getLeftType() == 2) {
                    this.upgradeBtnCaozuo();
                    break;
                }
                else if (this.LHMainPanel.getLeftType() == 4) {
                    this.qualityBtnCaozuo();
                    break;
                }
                else {
                    break;
                }
            }
            case 19: {
                this.RecastBtnCaozuo();
                break;
            }
            case 20: {
                this.refiningBtnCaozuo();
                break;
            }
            case 21: {
                if (this.LHMainPanel.getRightType() == 5) {
                    if (GoodsListFromServerUntil.getWingGoodsList().size() < this.LHMainPanel.getPageNumber() * 25) {
                        return;
                    }
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getPageNumber() + 1);
                    break;
                }
                else if (this.LHMainPanel.getRightType() == 2) {
                    if (this.LHMainPanel.getPageNumber() >= GoodsListFromServerUntil.getGoodslist().length / 15 + 1) {
                        return;
                    }
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getPageNumber() + 1);
                    break;
                }
                else {
                    break;
                }
            }
            case 22: {
                if (this.LHMainPanel.getPageNumber() <= 1) {
                    return;
                }
                this.LHMainPanel.setPageNumber(this.LHMainPanel.getPageNumber() - 1);
                break;
            }
        }
    }
    
    public void changeMenu(int type) {
        this.LHMainPanel.changLeftType(type);
        String mes = Agreement.getAgreement().rolechangeAgreement("LHSX=" + type);
        SendMessageUntil.toServer(mes);
        if (StringUtils.isNotBlank(RoleData.getRoleData().getLoginResult().getLianghaoValue())) {
            String[] split1 = RoleData.getRoleData().getLoginResult().getLianghaoValue().split("@");
            split1[0] = type + "";
            String join = StringUtils.join(split1, "@");
            RoleData.getRoleData().getLoginResult().setLianghaoValue(join);
        }
        RoleProperty.getRoleProperty().equipWearOff();
    }
    
    public void upStarBtnCaozuo() {
        if (this.LHMainPanel.getChosegoods() != null) {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.LHMainPanel.getWingGoods());
            if (goodstable != null) {
                String[] split = goodstable.getValue().split("\\|");
                String goodsQuality = this.LHMainPanel.getGoodsValue(split, "品质");
                String qualityNumber = WingUpStarMouseListener.getGoodsQuality(goodsQuality);
                if (qualityNumber != null) {
                    if ((int)this.LHMainPanel.getChosegoods().getUsetime() < Integer.parseInt(qualityNumber)) {
                        ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                    }
                    else {
                        Integer goodsValue = Integer.valueOf(Integer.parseInt(this.LHMainPanel.getGoodsValue(split, "星级")));
                        BigDecimal goodsTrue = WingUpStarMouseListener.getGoodsTrue(this.LHMainPanel.getChosegoods(), goodsValue);
                        if (goodsTrue == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("不符合星级");
                        }
                        else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(goodsTrue) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                        }
                        else {
                            SuitOperBean operBean = new SuitOperBean();
                            operBean.setType(32);
                            operBean.setGoods(new ArrayList<>());
                            operBean.getGoods().add(this.LHMainPanel.getWingGoods());
                            for (int i = 0; i < Integer.parseInt(qualityNumber); ++i) {
                                operBean.getGoods().add(this.LHMainPanel.getChosegoods().getRgid());
                            }
                            this.deductGoods(this.LHMainPanel.getChosegoods(), Integer.parseInt(qualityNumber));
                            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                            SendMessageUntil.toServer(senmes);
                        }
                    }
                }
            }
        }
    }
    
    public void upgradeBtnCaozuo() {
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.LHMainPanel.getWingGoods());
            if (goodstable != null) {
                LHMainPanel lhMainPanel = this.LHMainPanel;
                int wingLevel = org.wing.panel.LHMainPanel.getWingLevel(this.LHMainPanel.getGoodsValue(goodstable.getValue().split("\\|"), "经验"));
                if (wingLevel >= 200) {
                    ZhuFrame.getZhuJpanel().addPrompt2("翅膀等级上限");
                }
                else if (this.LHMainPanel.getChosegoods() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有要对应的物品");
                }
                else {
                    int parseInt = Integer.parseInt(this.LHMainPanel.getTextNumber().getText());
                    if (parseInt <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入要培养的物品的数量");
                    }
                    else if ((int)this.LHMainPanel.getChosegoods().getUsetime() < parseInt) {
                        ZhuFrame.getZhuJpanel().addPrompt2("所选的物品数量不足");
                    }
                    else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.LHMainPanel.getMoney()) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    }
                    else {
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(31);
                        operBean.setGoods(new ArrayList<>());
                        operBean.getGoods().add(this.LHMainPanel.getWingGoods());
                        PartJade jade = new PartJade(this.LHMainPanel.getChosegoods().getRgid().intValue(), parseInt);
                        operBean.setJade(jade);
                        this.deductGoods(this.LHMainPanel.getChosegoods(), parseInt);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void qualityBtnCaozuo() {
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.LHMainPanel.getWingGoods());
            if (goodstable != null) {
                int parseInt = Integer.parseInt(this.LHMainPanel.getNumberLab().getText());
                if (parseInt <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("材料不能为0");
                }
                else {
                    Integer goodsValue = Integer.valueOf(Integer.parseInt(this.LHMainPanel.getGoodsValue(goodstable.getValue().split("\\|"), "星级")));
                    if ((int)goodsValue != 15) {
                        ZhuFrame.getZhuJpanel().addPrompt2("当前翅膀星级不足");
                    }
                    else if (this.LHMainPanel.getChosegoods() == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有当前升级品质的物品");
                    }
                    else if ((int)this.LHMainPanel.getChosegoods().getUsetime() < parseInt) {
                        ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                    }
                    else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(10000000)) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    }
                    else {
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(33);
                        operBean.setGoods(new ArrayList<>());
                        operBean.getGoods().add(this.LHMainPanel.getWingGoods());
                        for (int i = 0; i < parseInt; ++i) {
                            operBean.getGoods().add(this.LHMainPanel.getChosegoods().getRgid());
                        }
                        this.deductGoods(this.LHMainPanel.getChosegoods(), parseInt);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void RecastBtnCaozuo() {
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要重铸的翅膀");
        }
        else if (this.LHMainPanel.getChosegoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择物品");
        }
        else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.LHMainPanel.getMoney()) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
        }
        else if ((int)this.LHMainPanel.getChosegoods().getUsetime() <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("重铸物品数量不足");
        }
        else {
            SuitOperBean operBean = new SuitOperBean();
            operBean.setType(34);
            operBean.setGoods(new ArrayList<>());
            operBean.getGoods().add(this.LHMainPanel.getWingGoods());
            operBean.getGoods().add(this.LHMainPanel.getChosegoods().getRgid());
            this.deductGoods(this.LHMainPanel.getChosegoods(), 1);
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
        }
    }
    
    public void leftBtnCaozuo() {
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else if (this.LHMainPanel.getWingGoodsType() > 0) {
            if (this.LHMainPanel.getLeftType() == 1) {
                if (this.LHMainPanel.getWingGoodsType() / 25 + 1 != this.LHMainPanel.getPageNumber()) {
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getWingGoodsType() / 25 + 1);
                }
                if (this.LHMainPanel.getWingGoodsType() % 25 == 0) {
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getPageNumber() - 1);
                }
            }
            this.LHMainPanel.changeChooseWingGoods((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.LHMainPanel.getWingGoodsType() - 1), this.LHMainPanel.getWingGoodsType() - 1);
        }
    }
    
    public void rightBtnCaozuo() {
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else if (this.LHMainPanel.getWingGoodsType() < GoodsListFromServerUntil.getWingGoodsList().size() - 1) {
            if (this.LHMainPanel.getLeftType() == 1) {
                if (this.LHMainPanel.getWingGoodsType() / 25 + 1 != this.LHMainPanel.getPageNumber()) {
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getWingGoodsType() / 25 + 1);
                }
                if (this.LHMainPanel.getWingGoodsType() % 25 == 24) {
                    this.LHMainPanel.setPageNumber(this.LHMainPanel.getPageNumber() + 1);
                }
            }
            this.LHMainPanel.changeChooseWingGoods((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.LHMainPanel.getWingGoodsType() + 1), this.LHMainPanel.getWingGoodsType() + 1);
        }
    }
    
    public void refiningBtnCaozuo() {
        String liangHao = RoleData.getRoleData().getLoginResult().getLiangHao();
        if (StringUtils.isNotBlank(liangHao)) {
            if (this.LHMainPanel.getChosegoods() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                return;
            }
            int parseInt = Integer.parseInt(this.LHMainPanel.getNumberLab().getText());
            if ((int)this.LHMainPanel.getChosegoods().getUsetime() < parseInt) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
            }
            else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.LHMainPanel.getMoney()) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
            }
            else {
                int getlock = this.LHMainPanel.getlock();
                int num = getlock % 10;
                if (num > 3) {
                    ZhuFrame.getZhuJpanel().addPrompt2("勾选锁定只能3个");
                }
                else {
                    int refiningMoney = this.LHMainPanel.getRefiningMoney(num);
                    if (RoleData.getRoleData().getLoginResult().getCodecard().compareTo(new BigDecimal(refiningMoney)) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("仙玉不足");
                    }
                    else {
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(355 + this.LHMainPanel.getRightType());
                        operBean.setGoods(new ArrayList<>());
                        operBean.setJade(new PartJade(getlock / 10, 0));
                        operBean.getGoods().add(this.LHMainPanel.getChosegoods().getRgid());
                        this.deductGoods(this.LHMainPanel.getChosegoods(), parseInt);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void deductGoods(Goodstable goodstable, int num) {
        goodstable.goodxh(num);
        if ((int)goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
        }
    }
}
