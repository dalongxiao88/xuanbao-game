package org.wing.btn;

import org.come.entity.PartJade;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.wing.mouseListener.WingUpStarMouseListener;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.wing.panel.WingMainPanel;
import com.tool.btn.MoBanBtn;

public class WingBtn extends MoBanBtn
{
    private int caozuo;
    private WingMainPanel wingMainPanel;
    
    public WingBtn(String iconpath, int type, String text, int caozuo, WingMainPanel wingMainPanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.wingMainPanel = wingMainPanel;
    }
    
    public WingBtn(String iconpath, int type, String text, int caozuo, WingMainPanel wingMainPanel, String sm) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.wingMainPanel = wingMainPanel;
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
            case 6: {
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
                if (this.wingMainPanel.getLeftType() == 3) {
                    this.upStarBtnCaozuo();
                    break;
                }
                else if (this.wingMainPanel.getLeftType() == 2) {
                    this.upgradeBtnCaozuo();
                    break;
                }
                else if (this.wingMainPanel.getLeftType() == 4) {
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
                if (this.wingMainPanel.getRightType() == 5) {
                    if (GoodsListFromServerUntil.getWingGoodsList().size() < this.wingMainPanel.getPageNumber() * 25) {
                        return;
                    }
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getPageNumber() + 1);
                    break;
                }
                else if (this.wingMainPanel.getRightType() == 2) {
                    if (this.wingMainPanel.getPageNumber() >= GoodsListFromServerUntil.getGoodslist().length / 15 + 1) {
                        return;
                    }
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getPageNumber() + 1);
                    break;
                }
                else {
                    break;
                }
            }
            case 22: {
                if (this.wingMainPanel.getPageNumber() <= 1) {
                    return;
                }
                this.wingMainPanel.setPageNumber(this.wingMainPanel.getPageNumber() - 1);
                break;
            }
        }
    }
    
    public void changeMenu(int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                switch (type) {
                    case 1: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K64.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K65.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K67.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K69.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K71.png"));
                        break;
                    }
                    case 2: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K63.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K66.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K67.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K69.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K71.png"));
                        break;
                    }
                    case 3: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K63.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K65.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K68.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K69.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K71.png"));
                        break;
                    }
                    case 4: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K63.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K65.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K67.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K70.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K71.png"));
                        break;
                    }
                    case 5: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K63.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K65.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K67.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K69.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K56.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K71.png"));
                        break;
                    }
                    case 6: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K63.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K65.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K67.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K69.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K72.png"));
                        break;
                    }
                }
                this.wingMainPanel.changLeftType(type);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                switch (type) {
                    case 1: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_未选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_未选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_未选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_未选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_未选中_w80,h120.png"));
                        break;
                    }
                    case 2: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_未选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_未选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_未选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_未选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_未选中_w80,h120.png"));
                        break;
                    }
                    case 3: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_未选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_未选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_未选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_未选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_未选中_w80,h120.png"));
                        break;
                    }
                    case 4: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_未选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_未选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_未选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_未选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_未选中_w80,h120.png"));
                        break;
                    }
                    case 5: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_未选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_未选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_未选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_未选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_未选中_w80,h120.png"));
                        break;
                    }
                    case 6: {
                        this.wingMainPanel.getWingMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_翅膀_未选中_w80,h120.png"));
                        this.wingMainPanel.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升级_未选中_w80,h120.png"));
                        this.wingMainPanel.getAmtyuranusBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_升星_未选中_w80,h120.png"));
                        this.wingMainPanel.getQualityBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_品质_未选中_w80,h120.png"));
                        this.wingMainPanel.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_重铸_未选中_w80,h120.png"));
                        this.wingMainPanel.getRefineryMenuBtn().setIcons(CutButtonImage.cuts("img/wing/一级选项卡_炼化_选中_w80,h120.png"));
                        break;
                    }
                }
                this.wingMainPanel.changLeftType(type);
            }
            catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }
    
    public void upStarBtnCaozuo() {
        if (this.wingMainPanel.getChosegoods() != null) {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingMainPanel.getWingGoods());
            if (goodstable != null) {
                String[] split = goodstable.getValue().split("\\|");
                String goodsQuality = this.wingMainPanel.getGoodsValue(split, "品质");
                String qualityNumber = WingUpStarMouseListener.getGoodsQuality(goodsQuality);
                if (qualityNumber != null) {
                    if ((int)this.wingMainPanel.getChosegoods().getUsetime() < Integer.parseInt(qualityNumber)) {
                        ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                    }
                    else {
                        Integer goodsValue = Integer.valueOf(Integer.parseInt(this.wingMainPanel.getGoodsValue(split, "星级")));
                        BigDecimal goodsTrue = WingUpStarMouseListener.getGoodsTrue(this.wingMainPanel.getChosegoods(), goodsValue);
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
                            operBean.getGoods().add(this.wingMainPanel.getWingGoods());
                            for (int i = 0; i < Integer.parseInt(qualityNumber); ++i) {
                                operBean.getGoods().add(this.wingMainPanel.getChosegoods().getRgid());
                            }
                            this.deductGoods(this.wingMainPanel.getChosegoods(), Integer.parseInt(qualityNumber));
                            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                            SendMessageUntil.toServer(senmes);
                        }
                    }
                }
            }
        }
    }
    
    public void upgradeBtnCaozuo() {
        if (this.wingMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingMainPanel.getWingGoods());
            if (goodstable != null) {
                int wingLevel = WingMainPanel.getWingLevel(this.wingMainPanel.getGoodsValue(goodstable.getValue().split("\\|"), "经验"));
                if (wingLevel >= 200) {
                    ZhuFrame.getZhuJpanel().addPrompt2("翅膀等级上限");
                }
                else if (this.wingMainPanel.getChosegoods() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有要对应的物品");
                }
                else {
                    int parseInt = Integer.parseInt(this.wingMainPanel.getTextNumber().getText());
                    if (parseInt <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入要培养的物品的数量");
                    }
                    else if ((int)this.wingMainPanel.getChosegoods().getUsetime() < parseInt) {
                        ZhuFrame.getZhuJpanel().addPrompt2("所选的物品数量不足");
                    }
                    else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.wingMainPanel.getMoney()) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    }
                    else {
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(31);
                        operBean.setGoods(new ArrayList<>());
                        operBean.getGoods().add(this.wingMainPanel.getWingGoods());
                        PartJade jade = new PartJade(this.wingMainPanel.getChosegoods().getRgid().intValue(), parseInt);
                        operBean.setJade(jade);
                        this.deductGoods(this.wingMainPanel.getChosegoods(), parseInt);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void qualityBtnCaozuo() {
        if (this.wingMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else {
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingMainPanel.getWingGoods());
            if (goodstable != null) {
                int parseInt = Integer.parseInt(this.wingMainPanel.getNumberLab().getText());
                if (parseInt <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("材料不能为0");
                }
                else {
                    Integer goodsValue = Integer.valueOf(Integer.parseInt(this.wingMainPanel.getGoodsValue(goodstable.getValue().split("\\|"), "星级")));
                    if ((int)goodsValue != 15) {
                        ZhuFrame.getZhuJpanel().addPrompt2("当前翅膀星级不足");
                    }
                    else if (this.wingMainPanel.getChosegoods() == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你没有当前升级品质的物品");
                    }
                    else if ((int)this.wingMainPanel.getChosegoods().getUsetime() < parseInt) {
                        ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                    }
                    else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(10000000)) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    }
                    else {
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(33);
                        operBean.setGoods(new ArrayList<>());
                        operBean.getGoods().add(this.wingMainPanel.getWingGoods());
                        for (int i = 0; i < parseInt; ++i) {
                            operBean.getGoods().add(this.wingMainPanel.getChosegoods().getRgid());
                        }
                        this.deductGoods(this.wingMainPanel.getChosegoods(), parseInt);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void RecastBtnCaozuo() {
        if (this.wingMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要重铸的翅膀");
        }
        else if (this.wingMainPanel.getChosegoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择物品");
        }
        else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.wingMainPanel.getMoney()) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
        }
        else if ((int)this.wingMainPanel.getChosegoods().getUsetime() <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("重铸物品数量不足");
        }
        else {
            SuitOperBean operBean = new SuitOperBean();
            operBean.setType(34);
            operBean.setGoods(new ArrayList<>());
            operBean.getGoods().add(this.wingMainPanel.getWingGoods());
            operBean.getGoods().add(this.wingMainPanel.getChosegoods().getRgid());
            this.deductGoods(this.wingMainPanel.getChosegoods(), 1);
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
        }
    }
    
    public void leftBtnCaozuo() {
        if (this.wingMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else if (this.wingMainPanel.getWingGoodsType() > 0) {
            if (this.wingMainPanel.getLeftType() == 1) {
                if (this.wingMainPanel.getWingGoodsType() / 25 + 1 != this.wingMainPanel.getPageNumber()) {
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getWingGoodsType() / 25 + 1);
                }
                if (this.wingMainPanel.getWingGoodsType() % 25 == 0) {
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getPageNumber() - 1);
                }
            }
            this.wingMainPanel.changeChooseWingGoods((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.wingMainPanel.getWingGoodsType() - 1), this.wingMainPanel.getWingGoodsType() - 1);
        }
    }
    
    public void rightBtnCaozuo() {
        if (this.wingMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选择要升级的翅膀");
        }
        else if (this.wingMainPanel.getWingGoodsType() < GoodsListFromServerUntil.getWingGoodsList().size() - 1) {
            if (this.wingMainPanel.getLeftType() == 1) {
                if (this.wingMainPanel.getWingGoodsType() / 25 + 1 != this.wingMainPanel.getPageNumber()) {
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getWingGoodsType() / 25 + 1);
                }
                if (this.wingMainPanel.getWingGoodsType() % 25 == 24) {
                    this.wingMainPanel.setPageNumber(this.wingMainPanel.getPageNumber() + 1);
                }
            }
            this.wingMainPanel.changeChooseWingGoods((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(this.wingMainPanel.getWingGoodsType() + 1), this.wingMainPanel.getWingGoodsType() + 1);
        }
    }
    
    public void refiningBtnCaozuo() {
        if (this.wingMainPanel.getChosegoods() != null) {
            if (this.wingMainPanel.getWingGoods() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选择要炼化的翅膀");
            }
            else {
                int parseInt = Integer.parseInt(this.wingMainPanel.getNumberLab().getText());
                if ((int)this.wingMainPanel.getChosegoods().getUsetime() < parseInt) {
                    ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
                }
                else if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.wingMainPanel.getMoney()) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                }
                else {
                    int getlock = this.wingMainPanel.getlock();
                    int num = getlock % 10;
                    if (num > 3) {
                        ZhuFrame.getZhuJpanel().addPrompt2("勾选锁定只能3个");
                    }
                    else {
                        int refiningMoney = this.wingMainPanel.getRefiningMoney(num);
                        if (RoleData.getRoleData().getLoginResult().getCodecard().compareTo(new BigDecimal(refiningMoney)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("仙玉不足");
                        }
                        else {
                            SuitOperBean operBean = new SuitOperBean();
                            operBean.setType(35);
                            operBean.setGoods(new ArrayList<>());
                            operBean.getGoods().add(this.wingMainPanel.getWingGoods());
                            operBean.setJade(new PartJade(getlock / 10, 0));
                            for (int i = 0; i < parseInt; ++i) {
                                operBean.getGoods().add(this.wingMainPanel.getChosegoods().getRgid());
                            }
                            this.deductGoods(this.wingMainPanel.getChosegoods(), parseInt);
                            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                            SendMessageUntil.toServer(senmes);
                        }
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
