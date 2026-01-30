package com.tool.btn;

import org.come.model.PalData;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.model.Lingbao;
import org.come.until.UserMessUntil;
import org.come.Frame.PartnerJframe;
import com.tool.role.ExpUtil;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.Util;
import org.come.Frame.NPCJfram;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import com.tool.role.RoleLingFa;
import org.come.Frame.ZhuFrame;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.LingbaoJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JTextField;

public class LingbaoPagingBtn extends MoBanBtn
{
    private int btnname;
    private JTextField attributeJpanel;
    
    public LingbaoPagingBtn(String iconpath, int type, int btnname, String text) {
        super(iconpath, type);
        this.btnname = btnname;
        if (text != null) {
            this.setText(text);
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.yellow);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
    }
    
    public LingbaoPagingBtn(String iconpath, int type, Color[] colors, Font font, int btnname, String text) {
        super(iconpath, type, colors);
        this.btnname = btnname;
        if (text != null) {
            this.setText(text);
            this.setFont(font);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
    }
    
    public LingbaoPagingBtn(String iconpath, int type, int btnname, String text, Color[] colors) {
        super(iconpath, type, colors);
        this.btnname = btnname;
        if (text != null) {
            this.setText(text);
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.yellow);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
    }
    
    public LingbaoPagingBtn(String iconpath, int type, Color[] colors, Font font, int btnname, String text, JTextField attributeJpanel) {
        super(iconpath, type, colors);
        this.btnname = btnname;
        if (text != null) {
            this.setText(text);
            this.setFont(font);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
        this.attributeJpanel = attributeJpanel;
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.btnname) {
            case 999: {
                String s = LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().getLabLingName().getText();
                if (StringUtils.isBlank(s)) {
                    ZhuFrame.getZhuJpanel().addPrompt("名字太长了#46");
                    return;
                }
                if (s.length() > 6) {
                    ZhuFrame.getZhuJpanel().addPrompt("名字太长了#46");
                    return;
                }
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                lingbao.setBaoname(s);
                String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                SendMessageUntil.toServer(sendMes);
                break;
            }
            case 0: {
                RoleLingFa.getRoleLingFa().lingFan(false);
                break;
            }
            case 1: {
                RoleLingFa.getRoleLingFa().lingFan(true);
                break;
            }
            case 2: {
                RoleLingFa.getRoleLingFa().faFan(false);
                break;
            }
            case 3: {
                RoleLingFa.getRoleLingFa().faFan(true);
                break;
            }
            case 4: {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao != null) {
                    if (lingbao != null) {
                        NPCJfram.getNpcJfram().getNpcjpanel().getLingBaoKX();
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("先选中需要转换抗性的灵宝或者法宝");
                    break;
                }
            }
            case 5: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao != null) {
                    if (lingbao.getBaotype().equals("法宝")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能删除法宝");
                        return;
                    }
                    if (lingbao.getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("灵宝已加锁");
                        return;
                    }
                    if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("删除前先卸下符石");
                        break;
                    }
                    else {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingDiscatd, lingbao, "#Y确定要将该灵宝删除吗？？？");
                        break;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("先选中需要删除的灵宝或者法宝");
                    break;
                }
            }
            case 10: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao != null) {
                    int lvl = lingbao.getLingbaolvl().intValue();
                    long exp = lingbao.getLingbaoexe().longValue();
                    long maxexp = ExpUtil.LFExp(lvl);
                    if (lvl % 30 != 0 || exp < maxexp || lvl == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("还未达到突破条件");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("#Y你是否要消耗#R");
                    buffer.append(lvl / 5);
                    buffer.append("#Y个灵宝天威印进行突破");
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingtupo, lingbao, buffer.toString());
                    break;
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("先选中需要突破的灵宝或者法宝");
                    break;
                }
            }
            case 111: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选中灵宝");
                    return;
                }
                PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                if (mainJpanel.getPalDataChooseId() < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                    return;
                }
                Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
                if (pidGetPal == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选中一个伙伴");
                    return;
                }
                PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                String name = palData.getName();
                if (pidGetPal.getLingbao() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("伙伴【#G" + name + "#Y】已有灵宝，请先取回");
                    return;
                }
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("#Y你是否要将灵宝#R");
                buffer2.append(lingbao.getBaoname());
                buffer2.append("#Y装备到伙伴#G" + name);
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingToPal, lingbao, buffer2.toString());
                break;
            }
        }
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
}
