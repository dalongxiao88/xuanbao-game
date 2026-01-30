package com.tool.btn;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.PartJade;
import org.come.Frame.ZhuFrame;
import java.util.ArrayList;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.bean.SuitOperBean;
import org.come.Jpanel.GemRefineryMainJpanel;

public class GemRefineryBtn extends MoBanBtn
{
    private int caozuo;
    private GemRefineryMainJpanel gemRefineryMainJpanel;
    private SuitOperBean suitOperBean;
    
    public GemRefineryBtn(String icon, int type, int caozuo, GemRefineryMainJpanel gemRefineryMainJpanel) {
        super(icon, type);
        this.caozuo = caozuo;
        this.gemRefineryMainJpanel = gemRefineryMainJpanel;
    }
    
    public GemRefineryBtn(String icon, int type, int caozuo, String text, Color[] colors, Font font, GemRefineryMainJpanel gemRefineryMainJpanel) {
        super(icon, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.gemRefineryMainJpanel = gemRefineryMainJpanel;
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
                if (this.caozuo == 0) {
                    this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("inkImg/button1/K38.png"));
                    this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("inkImg/button1/K49.png"));
                    this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("inkImg/button1/K51.png"));
                    this.gemRefineryMainJpanel.initView(this.caozuo);
                }
                else if (this.caozuo == 1) {
                    this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("inkImg/button1/K37.png"));
                    this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("inkImg/button1/K50.png"));
                    this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("inkImg/button1/K51.png"));
                    this.gemRefineryMainJpanel.initView(this.caozuo);
                }
                else if (this.caozuo == 2) {
                    this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("inkImg/button1/K37.png"));
                    this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("inkImg/button1/K49.png"));
                    this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("inkImg/button1/K52.png"));
                    this.gemRefineryMainJpanel.initView(this.caozuo);
                }
                else if (this.caozuo == 3) {
                    if (this.gemRefineryMainJpanel.getPage() <= 0) {
                        return;
                    }
                    this.gemRefineryMainJpanel.setPage(this.gemRefineryMainJpanel.getPage() - 1);
                    if (this.gemRefineryMainJpanel.getPage() <= 0) {
                        this.setBtn(-1);
                        this.setIcon(CutButtonImage.getImage("inkImg/button/61.png", -1, -1));
                    }
                    if (this.gemRefineryMainJpanel.getBtnNextPage().getBtn() == -1) {
                        this.gemRefineryMainJpanel.getBtnNextPage().setBtn(1);
                        this.gemRefineryMainJpanel.getBtnNextPage().setIcons(CutButtonImage.cuts("inkImg/button/9.png"));
                    }
                }
                else if (this.caozuo == 4) {
                    if (this.gemRefineryMainJpanel.getChooseGoods(21) == null) {
                        return;
                    }
                    this.gemRefineryMainJpanel.setPage(this.gemRefineryMainJpanel.getPage() + 1);
                    if (this.gemRefineryMainJpanel.getChooseGoods(21) == null) {
                        this.setIcon(CutButtonImage.getImage("inkImg/button/60.png", -1, -1));
                        this.setBtn(-1);
                    }
                    if (this.gemRefineryMainJpanel.getBtnLastPage().getBtn() == -1) {
                        this.gemRefineryMainJpanel.getBtnLastPage().setBtn(1);
                        this.gemRefineryMainJpanel.getBtnLastPage().setIcons(CutButtonImage.cuts("inkImg/button/10.png"));
                    }
                }
                else if (this.caozuo == 7) {
                    if (this.suitOperBean == null) {
                        this.suitOperBean = new SuitOperBean();
                        if (this.suitOperBean.getGoods() == null) {
                            this.suitOperBean.setGoods(new ArrayList<>());
                        }
                    }
                    if (this.getText().equals("合成")) {
                        Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                        if (chooseGoodstable == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请选择要合成的宝石");
                            return;
                        }
                        this.suitOperBean.setType(101);
                        List<BigDecimal> goods = this.suitOperBean.getGoods();
                        goods.clear();
                        goods.add(chooseGoodstable.getRgid());
                        String text2 = this.gemRefineryMainJpanel.getTextNum().getText();
                        if (text2 != null && !"".equals(text2)) {
                            this.suitOperBean.setJade(new PartJade(Integer.parseInt(text2), 0));
                        }
                    }
                    else if (this.getText().equals("镶嵌")) {
                        Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                        if (chooseGoodstable == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请选择要镶嵌的武器");
                            return;
                        }
                        if (this.gemRefineryMainJpanel.getGemBackNum() <= 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("选择的武器并没有开孔");
                            return;
                        }
                        if (this.gemRefineryMainJpanel.getGemBackNum() <= this.gemRefineryMainJpanel.getGemNum()) {
                            ZhuFrame.getZhuJpanel().addPrompt2("选择的武器并没有剩余的孔可以镶嵌宝石了");
                            return;
                        }
                        Goodstable twoGoodstable = this.gemRefineryMainJpanel.getTwoGoodstable();
                        if (twoGoodstable == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("宝石没有了");
                            return;
                        }
                        this.suitOperBean.setType(103);
                        List<BigDecimal> goods2 = this.suitOperBean.getGoods();
                        goods2.clear();
                        goods2.add(chooseGoodstable.getRgid());
                        goods2.add(twoGoodstable.getRgid());
                    }
                    else if (this.getText().equals("开孔")) {
                        Goodstable twoGoodstable2 = this.gemRefineryMainJpanel.getTwoGoodstable();
                        if (twoGoodstable2 == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("开孔材料没有了");
                            return;
                        }
                        Goodstable chooseGoodstable2 = this.gemRefineryMainJpanel.getChooseGoodstable();
                        if (chooseGoodstable2 == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请选择要开孔的武器");
                            return;
                        }
                        this.suitOperBean.setType(104);
                        List<BigDecimal> goods2 = this.suitOperBean.getGoods();
                        goods2.clear();
                        goods2.add(chooseGoodstable2.getRgid());
                        goods2.add(twoGoodstable2.getRgid());
                    }
                    String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.suitOperBean));
                    SendMessageUntil.toServer(sendmes);
                }
                else if (this.caozuo == 8) {
                    if (this.suitOperBean == null) {
                        this.suitOperBean = new SuitOperBean();
                        if (this.suitOperBean.getGoods() == null) {
                            this.suitOperBean.setGoods(new ArrayList<>());
                        }
                    }
                    Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                    if (chooseGoodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要拆卸的武器");
                        return;
                    }
                    this.suitOperBean.setType(102);
                    List<BigDecimal> goods = this.suitOperBean.getGoods();
                    goods.clear();
                    goods.add(chooseGoodstable.getRgid());
                    String sendmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.suitOperBean));
                    SendMessageUntil.toServer(sendmes2);
                }
            }
            else if (this.caozuo == 0) {
                this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_1.png"));
                this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemInlay.png"));
                this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemTrapper.png"));
                this.gemRefineryMainJpanel.initView(this.caozuo);
            }
            else if (this.caozuo == 1) {
                this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("img/xy2uiimg/gemInlay.png"));
                this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemTrapper.png"));
                this.gemRefineryMainJpanel.initView(this.caozuo);
            }
            else if (this.caozuo == 2) {
                this.gemRefineryMainJpanel.getBtnCompound().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                this.gemRefineryMainJpanel.getBtnDisassembly().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemInlay.png"));
                this.gemRefineryMainJpanel.getBtnTrepanning().setIcons(CutButtonImage.cuts("img/xy2uiimg/gemTrapper.png"));
                this.gemRefineryMainJpanel.initView(this.caozuo);
            }
            else if (this.caozuo == 3) {
                if (this.gemRefineryMainJpanel.getPage() <= 0) {
                    return;
                }
                this.gemRefineryMainJpanel.setPage(this.gemRefineryMainJpanel.getPage() - 1);
                if (this.gemRefineryMainJpanel.getPage() <= 0) {
                    this.setBtn(-1);
                    this.setIcon(CutButtonImage.getImage("img/xy2uiimg/30_png.button.btn_8.png", -1, -1));
                }
                if (this.gemRefineryMainJpanel.getBtnNextPage().getBtn() == -1) {
                    this.gemRefineryMainJpanel.getBtnNextPage().setBtn(1);
                    this.gemRefineryMainJpanel.getBtnNextPage().setIcons(CutButtonImage.cuts("img/xy2uiimg/36_png.button.btn_7.png"));
                }
            }
            else if (this.caozuo == 4) {
                if (this.gemRefineryMainJpanel.getChooseGoods(21) == null) {
                    return;
                }
                this.gemRefineryMainJpanel.setPage(this.gemRefineryMainJpanel.getPage() + 1);
                if (this.gemRefineryMainJpanel.getChooseGoods(21) == null) {
                    this.setIcon(CutButtonImage.getImage("img/xy2uiimg/36_png.button.btn_7.png", -1, -1));
                    this.setBtn(-1);
                }
                if (this.gemRefineryMainJpanel.getBtnLastPage().getBtn() == -1) {
                    this.gemRefineryMainJpanel.getBtnLastPage().setBtn(1);
                    this.gemRefineryMainJpanel.getBtnLastPage().setIcons(CutButtonImage.cuts("img/xy2uiimg/30_png.button.btn_8.png"));
                }
            }
            else if (this.caozuo == 7) {
                if (this.suitOperBean == null) {
                    this.suitOperBean = new SuitOperBean();
                    if (this.suitOperBean.getGoods() == null) {
                        this.suitOperBean.setGoods(new ArrayList<>());
                    }
                }
                if (this.getText().equals("合成")) {
                    Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                    if (chooseGoodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要合成的宝石");
                        return;
                    }
                    this.suitOperBean.setType(101);
                    List<BigDecimal> goods = this.suitOperBean.getGoods();
                    goods.clear();
                    goods.add(chooseGoodstable.getRgid());
                    String text2 = this.gemRefineryMainJpanel.getTextNum().getText();
                    if (text2 != null && !"".equals(text2)) {
                        this.suitOperBean.setJade(new PartJade(Integer.parseInt(text2), 0));
                    }
                }
                else if (this.getText().equals("镶嵌")) {
                    Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                    if (chooseGoodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要镶嵌的武器");
                        return;
                    }
                    if (this.gemRefineryMainJpanel.getGemBackNum() <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("选择的武器并没有开孔");
                        return;
                    }
                    if (this.gemRefineryMainJpanel.getGemBackNum() <= this.gemRefineryMainJpanel.getGemNum()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("选择的武器并没有剩余的孔可以镶嵌宝石了");
                        return;
                    }
                    Goodstable twoGoodstable = this.gemRefineryMainJpanel.getTwoGoodstable();
                    if (twoGoodstable == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("宝石没有了");
                        return;
                    }
                    this.suitOperBean.setType(103);
                    List<BigDecimal> goods2 = this.suitOperBean.getGoods();
                    goods2.clear();
                    goods2.add(chooseGoodstable.getRgid());
                    goods2.add(twoGoodstable.getRgid());
                }
                else if (this.getText().equals("开孔")) {
                    Goodstable twoGoodstable2 = this.gemRefineryMainJpanel.getTwoGoodstable();
                    if (twoGoodstable2 == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("开孔材料没有了");
                        return;
                    }
                    Goodstable chooseGoodstable2 = this.gemRefineryMainJpanel.getChooseGoodstable();
                    if (chooseGoodstable2 == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要开孔的武器");
                        return;
                    }
                    this.suitOperBean.setType(104);
                    List<BigDecimal> goods2 = this.suitOperBean.getGoods();
                    goods2.clear();
                    goods2.add(chooseGoodstable2.getRgid());
                    goods2.add(twoGoodstable2.getRgid());
                }
                String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.suitOperBean));
                SendMessageUntil.toServer(sendmes);
            }
            else if (this.caozuo == 8) {
                if (this.suitOperBean == null) {
                    this.suitOperBean = new SuitOperBean();
                    if (this.suitOperBean.getGoods() == null) {
                        this.suitOperBean.setGoods(new ArrayList<>());
                    }
                }
                Goodstable chooseGoodstable = this.gemRefineryMainJpanel.getChooseGoodstable();
                if (chooseGoodstable == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择要拆卸的武器");
                    return;
                }
                this.suitOperBean.setType(102);
                List<BigDecimal> goods = this.suitOperBean.getGoods();
                goods.clear();
                goods.add(chooseGoodstable.getRgid());
                String sendmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.suitOperBean));
                SendMessageUntil.toServer(sendmes2);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
