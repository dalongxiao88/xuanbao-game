package org.come.starcard;

import javax.swing.JLabel;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.entity.PartJade;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.Frame.NPCJfram;
import org.come.summonequip.JframeCashRewardsMain;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import com.tool.role.RoleProperty;
import org.come.entity.Goodstable;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import org.come.until.MessagrFlagUntil;
import org.come.until.Util;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class BtnStarCard extends MoBanBtn
{
    private int caozuo;
    private JpanelStarCardMain jpanelStarCardMain;
    private JpanelSoulBackMain jpanelSoulBackMain;
    private JpanelStarTransferMain jpanelStarTransferMain;
    
    public BtnStarCard(String iconpath, int type, String text, int caozuo, JpanelStarCardMain jpanelStarCardMain) {
        super(iconpath, type);
        this.caozuo = caozuo;
        if (caozuo >= 20) {
            this.setText(text);
            this.setHorizontalTextPosition(0);
            this.setVerticalTextPosition(0);
            this.setForeground(Color.yellow);
            this.setFont(UIUtils.TEXT_HY17);
        }
        this.jpanelStarCardMain = jpanelStarCardMain;
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelStarCardMain jpanelStarCardMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.jpanelStarCardMain = jpanelStarCardMain;
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelSoulBackMain jpanelSoulBackMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.jpanelSoulBackMain = jpanelSoulBackMain;
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelStarTransferMain jpanelStarTransferMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.jpanelStarTransferMain = jpanelStarTransferMain;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.caozuo == 1) {
                    this.jpanelStarCardMain.getAttributeMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B198.png"));
                    this.jpanelStarCardMain.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B199.png"));
                    this.jpanelStarCardMain.changeBigMenuLeftView(1);
                }
                else if (this.caozuo == 2) {
                    if (Util.isCanBuyOrno()) {
                        return;
                    }
                    this.jpanelStarCardMain.getAttributeMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B197.png"));
                    this.jpanelStarCardMain.getRecastMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B200.png"));
                    this.jpanelStarCardMain.changeBigMenuLeftView(2);
                }
                else if (this.caozuo == 3) {
                    this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("inkImg/button/B204.png"));
                    this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("inkImg/button/B201.png"));
                    this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button/B205.png"));
                    this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("inkImg/button/B207.png"));
                    this.jpanelStarCardMain.changeSmallMenuLeftView(1);
                }
                else if (this.caozuo == 4) {
                    this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("inkImg/button/B203.png"));
                    this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("inkImg/button/B201.png"));
                    this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button/B206.png"));
                    this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("inkImg/button/B207.png"));
                    this.jpanelStarCardMain.changeSmallMenuLeftView(2);
                }
                else if (this.caozuo == 5) {
                    this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("inkImg/button/B203.png"));
                    this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("inkImg/button/B201.png"));
                    this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button/B205.png"));
                    this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("inkImg/button/B208.png"));
                    this.jpanelStarCardMain.changeSmallMenuLeftView(3);
                }
                else if (this.caozuo == 6) {
                    this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("inkImg/button/B203.png"));
                    this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("inkImg/button/B202.png"));
                    this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("inkImg/button/B205.png"));
                    this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("inkImg/button/B207.png"));
                    this.jpanelStarCardMain.changeSmallMenuLeftView(4);
                }
                else if (this.caozuo == 11) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE10);
                }
                else if (this.caozuo == 12) {
                    if (Util.canBuyOrno) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE11);
                    }
                    else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                        FormsManagement.showForm(32);
                    }
                    else {
                        FormsManagement.showForm(33);
                    }
                }
                else if (this.caozuo == 20) {
                    this.jpanelStarCardMain.caoZuoStarCard();
                }
                else if (this.caozuo == 21) {
                    BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                    if (chooseStarCardId == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    if (goodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                        return;
                    }
                    int parseInt = Integer.parseInt(goodstable.getValue().split("\\|")[2].split("=")[1]);
                    if (parseInt <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("战力不足无法参战");
                        return;
                    }
                    if ((int)goodstable.getStatus() == 1) {
                        goodstable.setStatus(Integer.valueOf(4));
                        GoodsMouslisten.gooduse(goodstable, 0);
                        GoodsListFromServerUntil.getChoseGoodsList()[13] = null;
                        this.jpanelStarCardMain.getJoinBtn().setText("参 战");
                    }
                    else {
                        if (GoodsListFromServerUntil.getChoseGoodsList()[13] == null) {
                            goodstable.setStatus(Integer.valueOf(1));
                            GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[13] = goodstable, 0);
                        }
                        else {
                            Goodstable goodstable2 = GoodsListFromServerUntil.getChoseGoodsList()[13];
                            List<Goodstable> starCardList = GoodsListFromServerUntil.getStarCardList();
                            for (int i = 0; i < starCardList.size(); ++i) {
                                Goodstable goodstable3 = (Goodstable)starCardList.get(i);
                                if (goodstable3.getRgid().compareTo(goodstable2.getRgid()) == 0) {
                                    goodstable3.setStatus(Integer.valueOf(4));
                                    GoodsMouslisten.gooduse(goodstable3, 0);
                                }
                            }
                            goodstable.setStatus(Integer.valueOf(1));
                            GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[13] = goodstable, 0);
                        }
                        this.jpanelStarCardMain.getJoinBtn().setText("待 机");
                    }
                    RoleProperty.getRoleProperty().equipWearOff();
                }
                else if (this.caozuo == 22) {
                    if (Util.isCanBuyOrno()) {
                        return;
                    }
                    BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                    if (chooseStarCardId == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    if (goodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                        return;
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCardDeposit, goodstable, "真的要取出该星卡吗?");
                }
                else if (this.caozuo == 23) {
                    if (Util.isCanBuyOrno()) {
                        return;
                    }
                    FormsManagement.showForm(96);
                }
                else if (this.caozuo == 24) {
                    if (Util.isCanBuyOrno()) {
                        return;
                    }
                    JframeCashRewardsMain.getJframeCashRewardsMain().getJpanelCashRewardsMain().setGoodsType(2);
                    FormsManagement.showForm(92);
                }
                else if (this.caozuo == 25) {
                    BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                    if (chooseStarCardId == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    if (goodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
                        return;
                    }
                    NPCJfram.getNpcJfram().getNpcjpanel().showStarCardSupplement(goodstable);
                }
                else if (this.caozuo == 26) {
                    BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                    if (chooseStarCardId == null) {
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    if (goodstable == null) {
                        return;
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCard, goodstable, "真的要删除该星卡的星阵吗？删除后不可恢复");
                }
                else if (this.caozuo == 27) {
                    if (this.jpanelStarCardMain.getChooseStarCardId() == null) {
                        return;
                    }
                    Goodstable goodstable4 = GoodsListFromServerUntil.getRgid(this.jpanelStarCardMain.getChooseStarCardId());
                    if (goodstable4 == null) {
                        return;
                    }
                    String[] values = goodstable4.getValue().split("\\|");
                    String[] split2 = values[3].split("&");
                    for (int j = 0; j < split2.length; ++j) {
                        if (split2[j].startsWith("星阵属性")) {
                            String[] split3 = split2[j].split("=");
                            if (!isfiveElements(split3[1])) {
                                ZhuFrame.getZhuJpanel().addPrompt2("不是四神兽星阵无法转移");
                                return;
                            }
                            JframeStarTransferMain.getJframeStarTransferMain().getJpanelStarTransferMain().showStarCardAttribute(0, split3, goodstable4);
                            JframeStarTransferMain.getJframeStarTransferMain().getJpanelStarTransferMain().showStarCardAttribute(1, null, null);
                        }
                    }
                    FormsManagement.showForm(97);
                }
                else if (this.caozuo == 30) {
                    ArrayList<BigDecimal> starCardList2 = this.jpanelSoulBackMain.getStarCardList();
                    if (starCardList2 == null) {
                        return;
                    }
                    if (starCardList2.size() == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择至少一个要魂归的星卡");
                        return;
                    }
                    SuitOperBean suitOperBean = new SuitOperBean();
                    suitOperBean.setGoods(starCardList2);
                    suitOperBean.setType(57);
                    String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
                    SendMessageUntil.toServer(sendmes);
                    starCardList2.clear();
                }
                else if (this.caozuo == 31) {
                    FormsManagement.HideForm(96);
                    this.jpanelSoulBackMain.getStarCardList().clear();
                }
                else if (this.caozuo == 40) {
                    BigDecimal chooseOneId = this.jpanelStarTransferMain.getChooseOneId();
                    BigDecimal chooseTwoId = this.jpanelStarTransferMain.getChooseTwoId();
                    if (chooseOneId == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡");
                        return;
                    }
                    if (chooseTwoId == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡");
                        return;
                    }
                    Goodstable chooseOneGoods = GoodsListFromServerUntil.getRgid(chooseOneId);
                    if (chooseOneGoods == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡");
                        return;
                    }
                    Goodstable chooseTwoGoods = GoodsListFromServerUntil.getRgid(chooseTwoId);
                    if (chooseTwoGoods == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡");
                        return;
                    }
                    if (chooseOneId.compareTo(chooseTwoId) == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能选择一样的星卡");
                        return;
                    }
                    int suitId = -1;
                    int i = 0;
                    while (i < this.jpanelStarTransferMain.getRadioLabLeft().length) {
                        JLabel jLabel = this.jpanelStarTransferMain.getRadioLabLeft()[i];
                        if (jLabel.getIcon() != null) {
                            suitId = i;
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                    if (suitId == -1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡要被替换的星阵宫");
                        return;
                    }
                    int partId = -1;
                    int k = 0;
                    while (k < this.jpanelStarTransferMain.getRadioLabRight().length) {
                        JLabel jLabel2 = this.jpanelStarTransferMain.getRadioLabRight()[k];
                        if (jLabel2.getIcon() != null) {
                            partId = k;
                            break;
                        }
                        else {
                            ++k;
                        }
                    }
                    if (partId == -1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡要替换的星阵宫");
                        return;
                    }
                    PartJade partJade = new PartJade(suitId, partId);
                    SuitOperBean operBean = new SuitOperBean();
                    List<BigDecimal> goods = new ArrayList<>();
                    goods.add(chooseOneId);
                    goods.add(chooseTwoId);
                    operBean.setGoods(goods);
                    operBean.setType(56);
                    operBean.setJade(partJade);
                    String sendmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                    SendMessageUntil.toServer(sendmes2);
                    JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().deductGoods(chooseTwoGoods, 1);
                }
            }
            else if (this.caozuo == 1) {
                this.jpanelStarCardMain.getAttributeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/属性按钮w63,h78.png"));
                this.jpanelStarCardMain.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重铸未w63,h78px.png"));
                this.jpanelStarCardMain.changeBigMenuLeftView(1);
            }
            else if (this.caozuo == 2) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                this.jpanelStarCardMain.getAttributeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/属性按钮2-w63,h78.png"));
                this.jpanelStarCardMain.getRecastMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重铸w63,h78px.png"));
                this.jpanelStarCardMain.changeBigMenuLeftView(2);
            }
            else if (this.caozuo == 3) {
                this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/培养按钮2-W：30-H：138.png"));
                this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/练星按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.changeSmallMenuLeftView(1);
            }
            else if (this.caozuo == 4) {
                this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/培养按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/练星按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮2-W：30-H：138.png"));
                this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.changeSmallMenuLeftView(2);
            }
            else if (this.caozuo == 5) {
                this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/培养按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/练星按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮2-W：30-H：138.png"));
                this.jpanelStarCardMain.changeSmallMenuLeftView(3);
            }
            else if (this.caozuo == 6) {
                this.jpanelStarCardMain.getCultivateBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/培养按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getLianStarBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/练星按钮2-W：30-H：138.png"));
                this.jpanelStarCardMain.getUpgradeBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.getRedoBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮-W：30-H：138.png"));
                this.jpanelStarCardMain.changeSmallMenuLeftView(4);
            }
            else if (this.caozuo == 11) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE10);
            }
            else if (this.caozuo == 12) {
                if (Util.canBuyOrno) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE11);
                }
                else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                    FormsManagement.showForm(32);
                }
                else {
                    FormsManagement.showForm(33);
                }
            }
            else if (this.caozuo == 20) {
                this.jpanelStarCardMain.caoZuoStarCard();
            }
            else if (this.caozuo == 21) {
                BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                if (chooseStarCardId == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                    return;
                }
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                if (goodstable == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                    return;
                }
                int parseInt = Integer.parseInt(goodstable.getValue().split("\\|")[2].split("=")[1]);
                if (parseInt <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("战力不足无法参战");
                    return;
                }
                if ((int)goodstable.getStatus() == 1) {
                    goodstable.setStatus(Integer.valueOf(4));
                    GoodsMouslisten.gooduse(goodstable, 0);
                    GoodsListFromServerUntil.getChoseGoodsList()[13] = null;
                    this.jpanelStarCardMain.getJoinBtn().setText("参 战");
                }
                else {
                    if (GoodsListFromServerUntil.getChoseGoodsList()[13] == null) {
                        goodstable.setStatus(Integer.valueOf(1));
                        GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[13] = goodstable, 0);
                    }
                    else {
                        Goodstable goodstable2 = GoodsListFromServerUntil.getChoseGoodsList()[13];
                        List<Goodstable> starCardList = GoodsListFromServerUntil.getStarCardList();
                        for (int i = 0; i < starCardList.size(); ++i) {
                            Goodstable goodstable3 = (Goodstable)starCardList.get(i);
                            if (goodstable3.getRgid().compareTo(goodstable2.getRgid()) == 0) {
                                goodstable3.setStatus(Integer.valueOf(4));
                                GoodsMouslisten.gooduse(goodstable3, 0);
                            }
                        }
                        goodstable.setStatus(Integer.valueOf(1));
                        GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[13] = goodstable, 0);
                    }
                    this.jpanelStarCardMain.getJoinBtn().setText("待 机");
                }
                RoleProperty.getRoleProperty().equipWearOff();
            }
            else if (this.caozuo == 22) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                if (chooseStarCardId == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                    return;
                }
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                if (goodstable == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
                    return;
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCardDeposit, goodstable, "真的要取出该星卡吗?");
            }
            else if (this.caozuo == 23) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                FormsManagement.showForm(96);
            }
            else if (this.caozuo == 24) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                JframeCashRewardsMain.getJframeCashRewardsMain().getJpanelCashRewardsMain().setGoodsType(2);
                FormsManagement.showForm(92);
            }
            else if (this.caozuo == 25) {
                BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                if (chooseStarCardId == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
                    return;
                }
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                if (goodstable == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要补充的星卡");
                    return;
                }
                NPCJfram.getNpcJfram().getNpcjpanel().showStarCardSupplement(goodstable);
            }
            else if (this.caozuo == 26) {
                BigDecimal chooseStarCardId = this.jpanelStarCardMain.getChooseStarCardId();
                if (chooseStarCardId == null) {
                    return;
                }
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                if (goodstable == null) {
                    return;
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCard, goodstable, "真的要删除该星卡的星阵吗？删除后不可恢复");
            }
            else if (this.caozuo == 27) {
                if (this.jpanelStarCardMain.getChooseStarCardId() == null) {
                    return;
                }
                Goodstable goodstable4 = GoodsListFromServerUntil.getRgid(this.jpanelStarCardMain.getChooseStarCardId());
                if (goodstable4 == null) {
                    return;
                }
                String[] values = goodstable4.getValue().split("\\|");
                String[] split2 = values[3].split("&");
                for (int j = 0; j < split2.length; ++j) {
                    if (split2[j].startsWith("星阵属性")) {
                        String[] split3 = split2[j].split("=");
                        if (!isfiveElements(split3[1])) {
                            ZhuFrame.getZhuJpanel().addPrompt2("不是四神兽星阵无法转移");
                            return;
                        }
                        JframeStarTransferMain.getJframeStarTransferMain().getJpanelStarTransferMain().showStarCardAttribute(0, split3, goodstable4);
                        JframeStarTransferMain.getJframeStarTransferMain().getJpanelStarTransferMain().showStarCardAttribute(1, null, null);
                    }
                }
                FormsManagement.showForm(97);
            }
            else if (this.caozuo == 30) {
                ArrayList<BigDecimal> starCardList2 = this.jpanelSoulBackMain.getStarCardList();
                if (starCardList2 == null) {
                    return;
                }
                if (starCardList2.size() == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择至少一个要魂归的星卡");
                    return;
                }
                SuitOperBean suitOperBean = new SuitOperBean();
                suitOperBean.setGoods(starCardList2);
                suitOperBean.setType(57);
                String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
                SendMessageUntil.toServer(sendmes);
                starCardList2.clear();
            }
            else if (this.caozuo == 31) {
                FormsManagement.HideForm(96);
                this.jpanelSoulBackMain.getStarCardList().clear();
            }
            else if (this.caozuo == 40) {
                BigDecimal chooseOneId = this.jpanelStarTransferMain.getChooseOneId();
                BigDecimal chooseTwoId = this.jpanelStarTransferMain.getChooseTwoId();
                if (chooseOneId == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡");
                    return;
                }
                if (chooseTwoId == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡");
                    return;
                }
                Goodstable chooseOneGoods = GoodsListFromServerUntil.getRgid(chooseOneId);
                if (chooseOneGoods == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡");
                    return;
                }
                Goodstable chooseTwoGoods = GoodsListFromServerUntil.getRgid(chooseTwoId);
                if (chooseTwoGoods == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡");
                    return;
                }
                if (chooseOneId.compareTo(chooseTwoId) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("不能选择一样的星卡");
                    return;
                }
                int suitId = -1;
                int i = 0;
                while (i < this.jpanelStarTransferMain.getRadioLabLeft().length) {
                    JLabel jLabel = this.jpanelStarTransferMain.getRadioLabLeft()[i];
                    if (jLabel.getIcon() != null) {
                        suitId = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (suitId == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择主卡要被替换的星阵宫");
                    return;
                }
                int partId = -1;
                int k = 0;
                while (k < this.jpanelStarTransferMain.getRadioLabRight().length) {
                    JLabel jLabel2 = this.jpanelStarTransferMain.getRadioLabRight()[k];
                    if (jLabel2.getIcon() != null) {
                        partId = k;
                        break;
                    }
                    else {
                        ++k;
                    }
                }
                if (partId == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择副卡要替换的星阵宫");
                    return;
                }
                PartJade partJade = new PartJade(suitId, partId);
                SuitOperBean operBean = new SuitOperBean();
                List<BigDecimal> goods = new ArrayList<>();
                goods.add(chooseOneId);
                goods.add(chooseTwoId);
                operBean.setGoods(goods);
                operBean.setType(56);
                operBean.setJade(partJade);
                String sendmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                SendMessageUntil.toServer(sendmes2);
                JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().deductGoods(chooseTwoGoods, 1);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static boolean isfiveElements(String value) {
        return value.equals("朱雀") || value.equals("玄武") || value.equals("白虎") || value.equals("青龙");
    }
}
