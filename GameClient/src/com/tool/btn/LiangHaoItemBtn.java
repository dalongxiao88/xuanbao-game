package com.tool.btn;

import org.come.bean.SearchSellLiangHaoResultBean;
import java.util.Iterator;
import java.util.List;
import org.come.bean.LoginResult;
import org.come.Frame.GetLiangHaoJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.AucJfram;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.ContinueLiangHaoJpanel;
import org.come.Jpanel.PaintLiangHaoJpanel;
import org.come.Jpanel.GetLiangHaoTwoJpanel;
import org.come.entity.SellLianghaoAuc;
import org.come.entity.SellLiangHaoBase;
import org.come.Jpanel.XinJianJpanel;
import org.come.Jpanel.AucJpanel;

public class LiangHaoItemBtn extends MoBanBtn
{
    private int caozuo;
    private AucJpanel aucJpanel;
    private XinJianJpanel xinJianJpanel;
    private SellLiangHaoBase lianghaoItem;
    private SellLianghaoAuc sellLianghaoAuc;
    private GetLiangHaoTwoJpanel getLiangHaoTwoJpanel;
    private PaintLiangHaoJpanel paintLiangHaoJpanel;
    private ContinueLiangHaoJpanel continueLiangHaoJpanel;
    
    public LiangHaoItemBtn(String iconpath, int type, SellLiangHaoBase liangHaoItem, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.lianghaoItem = liangHaoItem;
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, SellLianghaoAuc sellLianghaoAuc, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.sellLianghaoAuc = sellLianghaoAuc;
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, ContinueLiangHaoJpanel continueLiangHaoJpanel, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.caozuo = caozuo;
        this.continueLiangHaoJpanel = continueLiangHaoJpanel;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, int caozuo, String text, Color[] colors) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(UIUtils.TXT_hyzjt18);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, AucJpanel aucJpanel, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.aucJpanel = aucJpanel;
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, PaintLiangHaoJpanel paintLiangHaoJpanel, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.paintLiangHaoJpanel = paintLiangHaoJpanel;
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, XinJianJpanel xinJianJpanel, int caozuo, String text) {
        super(iconpath, type);
        this.setText(text);
        this.xinJianJpanel = xinJianJpanel;
        this.caozuo = caozuo;
    }
    
    public LiangHaoItemBtn(String iconpath, int type, String text, int caozuo, GetLiangHaoTwoJpanel getLiangHaoTwoJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.getLiangHaoTwoJpanel = getLiangHaoTwoJpanel;
        this.caozuo = caozuo;
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
            String mes = null;
            switch (this.caozuo) {
                case 1: {
                    LoginResult login = RoleData.getRoleData().getLoginResult();
                    if (StringUtils.isNotEmpty(login.getLiangHao())) {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.buylianghao, this.lianghaoItem, "#W您确定购买靓号:#G" + this.lianghaoItem.getLianghao() + " #W吗?  #原靓号:#G" + login.getLiangHao() + "#W将会被覆盖。");
                        break;
                    }
                    else {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.buylianghao, this.lianghaoItem, "#W您确定购买靓号:#G" + this.lianghaoItem.getLianghao() + " #W吗?  #原靓号将会被覆盖。");
                        break;
                    }
                }
                case 2: {
                    LocalDate expDate = LocalDate.parse(this.lianghaoItem.getAucEndTime());
                    LocalDate nowDate = LocalDate.now();
                    long daysBetween = ChronoUnit.DAYS.between(nowDate, expDate);
                    if (daysBetween <= 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt2("还未到竞拍时间！");
                        return;
                    }
                    FormsManagement.showForm(703);
                    AucJfram.getAucJfram().getAucjpanel().setReRuc(false);
                    AucJfram.getAucJfram().getAucjpanel().setLiangHaoItem(this.lianghaoItem);
                    break;
                }
                case 3: {
                    String aucPrice = this.aucJpanel.getAucPrice().getText();
                    if (!aucPrice.matches("\\d+")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入竞拍价格！");
                        return;
                    }
                    int aucPriceInt = Integer.parseInt(aucPrice);
                    SellLiangHaoBase oldSellLiangHaoBase = this.aucJpanel.getLiangHaoItem();
                    if (!this.aucJpanel.isReRuc() && oldSellLiangHaoBase != null) {
                        int oldPrice = oldSellLiangHaoBase.getPrice();
                        if (aucPriceInt <= oldPrice) {
                            ZhuFrame.getZhuJpanel().addPrompt2("出价不能低于当前价格");
                        }
                        else {
                            SellLiangHaoBase newSellLiangHaoBase = new SellLiangHaoBase();
                            newSellLiangHaoBase.setAucPrice(aucPriceInt);
                            newSellLiangHaoBase.setType(oldSellLiangHaoBase.getType());
                            newSellLiangHaoBase.setLianghao(oldSellLiangHaoBase.getLianghao());
                            newSellLiangHaoBase.setPrice(oldSellLiangHaoBase.getPrice());
                            newSellLiangHaoBase.setAucEndTime(oldSellLiangHaoBase.getAucEndTime());
                            newSellLiangHaoBase.setAucStartTime(oldSellLiangHaoBase.getAucStartTime());
                            mes = Agreement.getAgreement().selllianghaoAgreement("BUYAUC|" + GsonUtil.getGsonUtil().getgson().toJson(newSellLiangHaoBase));
                            SendMessageUntil.toServer(mes);
                        }
                    }
                    else if (this.getAucJpanel().getSelllianghaoAuc() != null) {
                        int oldPrice = this.getAucJpanel().getSelllianghaoAuc().getAucPoint().intValue();
                        if (aucPriceInt <= oldPrice) {
                            ZhuFrame.getZhuJpanel().addPrompt2("出价不能低于当前价格");
                        }
                        else {
                            List<SellLiangHaoBase> list = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getnLhList();
                            if (list != null && list.size() > 0) {
                                for (SellLiangHaoBase item : list) {
                                    if (item.getLianghao().equals(this.getAucJpanel().getSelllianghaoAuc().getLianghao())) {
                                        SellLiangHaoBase newSellLiangHaoBase2 = new SellLiangHaoBase();
                                        newSellLiangHaoBase2.setAucPrice(aucPriceInt);
                                        newSellLiangHaoBase2.setType(item.getType());
                                        newSellLiangHaoBase2.setLianghao(item.getLianghao());
                                        newSellLiangHaoBase2.setPrice(item.getPrice());
                                        newSellLiangHaoBase2.setAucEndTime(item.getAucEndTime());
                                        newSellLiangHaoBase2.setAucStartTime(item.getAucStartTime());
                                        mes = Agreement.getAgreement().selllianghaoAgreement("BUYAUC|" + GsonUtil.getGsonUtil().getgson().toJson(newSellLiangHaoBase2));
                                        FormsManagement.HideForm(703);
                                        SendMessageUntil.toServer(mes);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    FormsManagement.HideForm(703);
                    break;
                }
                case 4: {
                    if (!this.getLiangHaoTwoJpanel.isMylist()) {
                        SearchSellLiangHaoResultBean lhBean = this.getLiangHaoTwoJpanel.getAlb();
                        if (lhBean == null || lhBean.getSellLiangHaos() == null || lhBean.getSellLiangHaos().size() <= 0) {
                            return;
                        }
                        this.getLiangHaoTwoJpanel.setNowpage((this.getLiangHaoTwoJpanel.getNowpage() - 1 <= 1) ? 1 : (this.getLiangHaoTwoJpanel.getNowpage() - 1));
                        int totalpage = lhBean.getSellLiangHaos().size() / 6 + ((lhBean.getSellLiangHaos().size() % 6 == 0) ? 0 : 1);
                        this.getLiangHaoTwoJpanel.refreshSellList(lhBean.getSellLiangHaos(), this.getLiangHaoTwoJpanel.getNowpage());
                        break;
                    }
                    else {
                        List<SellLianghaoAuc> aucList = this.getLiangHaoTwoJpanel.getMyLiangHaoAucBean().getSellLianghaoAucs();
                        if (aucList == null || aucList.size() <= 0) {
                            return;
                        }
                        this.getLiangHaoTwoJpanel.setMy_nowpage((this.getLiangHaoTwoJpanel.getMy_nowpage() - 1 <= 1) ? 1 : (this.getLiangHaoTwoJpanel.getMy_nowpage() - 1));
                        int totalpage = aucList.size() / 8 + ((aucList.size() % 8 == 0) ? 0 : 1);
                        this.getLiangHaoTwoJpanel.refreshMyAucList(aucList, this.getLiangHaoTwoJpanel.getMy_nowpage());
                        break;
                    }
                }
                case 5: {
                    if (!this.getLiangHaoTwoJpanel.isMylist()) {
                        SearchSellLiangHaoResultBean lhBean = this.getLiangHaoTwoJpanel.getAlb();
                        if (lhBean == null || lhBean.getSellLiangHaos() == null || lhBean.getSellLiangHaos().size() <= 0) {
                            return;
                        }
                        int totalpage = lhBean.getSellLiangHaos().size() / 6 + ((lhBean.getSellLiangHaos().size() % 6 == 0) ? 0 : 1);
                        this.getLiangHaoTwoJpanel.setNowpage((this.getLiangHaoTwoJpanel.getNowpage() + 1 >= totalpage) ? totalpage : (this.getLiangHaoTwoJpanel.getNowpage() + 1));
                        this.getLiangHaoTwoJpanel.refreshSellList(lhBean.getSellLiangHaos(), this.getLiangHaoTwoJpanel.getNowpage());
                        break;
                    }
                    else {
                        List<SellLianghaoAuc> aucList = this.getLiangHaoTwoJpanel.getMyLiangHaoAucBean().getSellLianghaoAucs();
                        if (aucList == null || aucList.size() <= 0) {
                            return;
                        }
                        int totalpage = aucList.size() / 8 + ((aucList.size() % 8 == 0) ? 0 : 1);
                        this.getLiangHaoTwoJpanel.setMy_nowpage((this.getLiangHaoTwoJpanel.getMy_nowpage() + 1 >= totalpage) ? totalpage : (this.getLiangHaoTwoJpanel.getMy_nowpage() + 1));
                        this.getLiangHaoTwoJpanel.refreshMyAucList(aucList, this.getLiangHaoTwoJpanel.getMy_nowpage());
                        break;
                    }
                }
                case 6: {
                    String xjlianghao = this.xinJianJpanel.getxNum().getText();
                    SellLianghaoAuc selllianghaoauc = new SellLianghaoAuc();
                    selllianghaoauc.setLianghao(xjlianghao);
                    selllianghaoauc.setOriginalprice(Integer.valueOf(19999));
                    if (xjlianghao == null || xjlianghao.length() < 4 || xjlianghao.length() > 6) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入4到6位靓号！");
                        return;
                    }
                    mes = Agreement.getAgreement().selllianghaoAgreement("XJLIANGHAO|" + GsonUtil.getGsonUtil().getgson().toJson(selllianghaoauc));
                    SendMessageUntil.toServer(mes);
                    FormsManagement.HideForm(704);
                    break;
                }
                case 7: {
                    GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().setPreViewLiangHao(this.lianghaoItem.getLianghao());
                    FormsManagement.showForm(707);
                    break;
                }
                case 8: {
                    if (this.sellLianghaoAuc != null) {
                        int status = (short)this.sellLianghaoAuc.getStatus();
                        if (status == 1) {
                            mes = Agreement.getAgreement().selllianghaoAgreement("DELAUC|" + GsonUtil.getGsonUtil().getgson().toJson(this.sellLianghaoAuc));
                            SendMessageUntil.toServer(mes);
                        }
                        else if (status != 2 && (status == 3 || status == 4 || status == 5)) {
                            AucJfram.getAucJfram().getAucjpanel().setSelllianghaoAuc(this.sellLianghaoAuc);
                            AucJfram.getAucJfram().getAucjpanel().setReRuc(true);
                            FormsManagement.showForm(703);
                        }
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 9: {
                    FormsManagement.HideForm(704);
                    break;
                }
                case 10: {
                    FormsManagement.HideForm(703);
                    break;
                }
                case 50: {
                    this.paintLiangHaoJpanel.setSelectType(1);
                    break;
                }
                case 51: {
                    this.paintLiangHaoJpanel.setSelectType(2);
                    break;
                }
                case 52: {
                    this.paintLiangHaoJpanel.setSelectType(3);
                    break;
                }
                case 53: {
                    this.paintLiangHaoJpanel.setSelectType(4);
                    break;
                }
                case 70: {
                    String mes2 = Agreement.getAgreement().selllianghaoAgreement("ADDLHTIME|1");
                    SendMessageUntil.toServer(mes2);
                    break;
                }
                case 71: {
                    FormsManagement.HideForm(706);
                    break;
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public AucJpanel getAucJpanel() {
        return this.aucJpanel;
    }
    
    public void setAucJpanel(AucJpanel aucJpanel) {
        this.aucJpanel = aucJpanel;
    }
    
    public SellLiangHaoBase getLianghaoItem() {
        return this.lianghaoItem;
    }
    
    public void setLianghaoItem(SellLiangHaoBase lianghaoItem) {
        this.lianghaoItem = lianghaoItem;
    }
    
    public SellLianghaoAuc getSellLianghaoAuc() {
        return this.sellLianghaoAuc;
    }
    
    public void setSellLianghaoAuc(SellLianghaoAuc sellLianghaoAuc) {
        this.sellLianghaoAuc = sellLianghaoAuc;
    }
}
