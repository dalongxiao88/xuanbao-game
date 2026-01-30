package org.gemstone.btn;

import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.PartJade;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.gemstone.panel.GemstoneOperationMainFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.gemstone.panel.GemstoneOperationMainPanel;
import com.tool.btn.MoBanBtn;

public class GemstoneOperationBtn extends MoBanBtn
{
    int typeBtn;
    private GemstoneOperationMainPanel transJpanel;
    
    public GemstoneOperationBtn(String iconpath, int type, int typeBtn) {
        super(iconpath, type);
        this.typeBtn = typeBtn;
    }
    
    public GemstoneOperationBtn(String iconpath, int type, Color[] colors, String text, Font font, int typeBtn) {
        super(iconpath, type, colors);
        this.typeBtn = typeBtn;
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    public GemstoneOperationBtn(String iconpath, int type, int typeBtn, GemstoneOperationMainPanel transJpanel) {
        super(iconpath, type);
        this.typeBtn = typeBtn;
        this.transJpanel = transJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        GemstoneOperationMainPanel gemstoneOperationMainPanel = GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel();
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                switch (this.typeBtn) {
                    case 2: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K54.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K57.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(1);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "synthesis");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 4: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K53.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K57.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K56.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(2);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "appraisalAndRecast");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 6: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K53.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K58.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("inkImg/button1/K55.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(3);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "appraisalAndRecast");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 7: {
                        this.upServerInfo(17, gemstoneOperationMainPanel);
                        break;
                    }
                    case 8: {
                        this.upServerInfo(18, gemstoneOperationMainPanel);
                        break;
                    }
                    case 9: {
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().setVisible(!gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().isVisible());
                        break;
                    }
                    case 10: {
                        this.upServerInfo(19, gemstoneOperationMainPanel);
                        break;
                    }
                    case 11: {
                        this.transJpanel.lingFan(false);
                        break;
                    }
                    case 12: {
                        this.transJpanel.lingFan(true);
                        break;
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        else {
            try {
                switch (this.typeBtn) {
                    case 2: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/合成w63,h78px.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/鉴定未w63,h78px.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/重铸未w63,h78px.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(1);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "synthesis");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 4: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/合成未w63,h78px.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/鉴定未w63,h78px.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/重铸w63,h78px.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(2);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "appraisalAndRecast");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 6: {
                        gemstoneOperationMainPanel.getSyntherisCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/合成未w63,h78px.png"));
                        gemstoneOperationMainPanel.getAppraisalCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/鉴定w63,h78px.png"));
                        gemstoneOperationMainPanel.getRecastCheckBtn().setIcons(CutButtonImage.cuts("img/gemstone/重铸未w63,h78px.png"));
                        gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                        gemstoneOperationMainPanel.setTypePanel(3);
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getCardLayout().show(gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel(), "appraisalAndRecast");
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                        break;
                    }
                    case 7: {
                        this.upServerInfo(17, gemstoneOperationMainPanel);
                        break;
                    }
                    case 8: {
                        this.upServerInfo(18, gemstoneOperationMainPanel);
                        break;
                    }
                    case 9: {
                        gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().setVisible(!gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().isVisible());
                        break;
                    }
                    case 10: {
                        this.upServerInfo(19, gemstoneOperationMainPanel);
                        break;
                    }
                    case 11: {
                        this.transJpanel.lingFan(false);
                        break;
                    }
                    case 12: {
                        this.transJpanel.lingFan(true);
                        break;
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public int getTypeBtn() {
        return this.typeBtn;
    }
    
    public void setTypeBtn(int typeBtn) {
        this.typeBtn = typeBtn;
    }
    
    public static void gemstoneOperation(int typePanel, GemstoneOperationMainPanel gemstoneOperationMainPanel) {
        if (MyIsif.getStyle().equals("水墨")) {
            switch (typePanel) {
                case 1: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.changeIcon(gemstoneOperationMainPanel.getGoodsLabes()[i] = null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                    }
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(-1);
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                    break;
                }
                case 2: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.getGoodsLabes()[i] = null;
                        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().changeIcon(null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.getGoodsLabes()[i] = null;
                        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().changeIcon(null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                    }
                    break;
                }
            }
        }
        else {
            switch (typePanel) {
                case 1: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.changeIcon(gemstoneOperationMainPanel.getGoodsLabes()[i] = null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                    }
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(-1);
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("img/gemstone/合成w60,h26.png", -1, -1));
                    break;
                }
                case 2: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.getGoodsLabes()[i] = null;
                        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().changeIcon(null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
                        gemstoneOperationMainPanel.getGoodsLabes()[i] = null;
                        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().changeIcon(null, i, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                    }
                    break;
                }
            }
        }
    }
    
    public void upServerInfo(int typePanel, GemstoneOperationMainPanel gemstoneOperationMainPanel) {
        SuitOperBean suitOperBean = new SuitOperBean();
        suitOperBean.setGoods(new ArrayList<>());
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal("5000000")) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金额不足");
            return;
        }
        for (int i = 0; i < gemstoneOperationMainPanel.getGoodsLabes().length; ++i) {
            if (gemstoneOperationMainPanel.getGoodsLabes()[i] != null) {
                Goodstable goodstableBack = gemstoneOperationMainPanel.getGoodsLabes()[i];
                if (goodstableBack.getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                    return;
                }
                if (GoodsListFromServerUntil.isExist(goodstableBack)) {
                    return;
                }
                int sum = 1;
                for (int j = 0; j < suitOperBean.getGoods().size(); ++j) {
                    if (goodstableBack.getRgid().compareTo((BigDecimal)suitOperBean.getGoods().get(j)) == 0) {
                        ++sum;
                    }
                }
                if (sum > (int)goodstableBack.getUsetime()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                    return;
                }
                suitOperBean.getGoods().add(goodstableBack.getRgid());
            }
        }
        suitOperBean.setType(typePanel);
        if (typePanel == 18) {
            Goodstable reelectGoods = gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getReelectGoods();
            suitOperBean.setJade(new PartJade(reelectGoods.getType().intValue(), 0));
        }
        int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
        if (packNumber <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("背包空间不足");
            return;
        }
        String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
        SendMessageUntil.toServer(sendmes);
        this.qk(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
    }
    
    public void qk(int typeBtn, GemstoneOperationMainPanel gemstoneOperationMainPanel) {
        try {
            switch (typeBtn) {
                case 1: {
                    gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                    gemstoneOperationMainPanel.setTypePanel(1);
                    break;
                }
                case 2: {
                    gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                    break;
                }
                case 3: {
                    gemstoneOperation(gemstoneOperationMainPanel.getTypePanel(), gemstoneOperationMainPanel);
                    gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().changBtnIcon();
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
