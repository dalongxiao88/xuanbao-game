package com.tool.btn;

import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.HuangJpanel;

public class HuangBtn extends MoBanBtn
{
    private int caozuo;
    private HuangJpanel huangJpanel;
    private String text;
    
    public HuangBtn(String iconpath, int type, int caozuo, String text, HuangJpanel huangJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.huangJpanel = huangJpanel;
        this.text = text;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setForeground(Color.white);
        this.setFont(UIUtils.TEXT_NAME_FONT);
        this.setText(text);
        this.setForeground(Color.YELLOW);
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
        if (this.huangJpanel.getType() == 0 && (this.caozuo == 1 || this.caozuo == 2 || this.caozuo == 3 || this.caozuo == 4)) {
            HuangJpanel huangJpanel = this.huangJpanel;
            HuangJpanel.getXuanyu().setVisible(false);
            HuangJpanel huangJpanel2 = this.huangJpanel;
            HuangJpanel.getJinbi().setVisible(false);
            HuangJpanel huangJpanel3 = this.huangJpanel;
            HuangJpanel.getDashuang().setVisible(false);
            HuangJpanel huangJpanel4 = this.huangJpanel;
            HuangJpanel.getDa().setVisible(false);
        }
        switch (this.caozuo) {
            case 1: {
                HuangJpanel huangJpanel5 = this.huangJpanel;
                HuangJpanel.getXuanyu().setVisible(true);
                this.huangJpanel.setType(this.caozuo);
                break;
            }
            case 2: {
                HuangJpanel huangJpanel6 = this.huangJpanel;
                HuangJpanel.getJinbi().setVisible(true);
                this.huangJpanel.setType(this.caozuo);
                break;
            }
            case 3: {
                HuangJpanel huangJpanel7 = this.huangJpanel;
                HuangJpanel.getDashuang().setVisible(true);
                this.huangJpanel.setType(this.caozuo);
                break;
            }
            case 4: {
                HuangJpanel huangJpanel8 = this.huangJpanel;
                HuangJpanel.getDa().setVisible(true);
                this.huangJpanel.setType(this.caozuo);
                break;
            }
            case 5:
            case 6: {
                if (this.caozuo == 5) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R您选择使用金币下注！！");
                    HuangJpanel huangJpanel9 = this.huangJpanel;
                    HuangJpanel.getBaozi().setVisible(false);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("#R您选择使用仙玉下注！！");
                    HuangJpanel huangJpanel10 = this.huangJpanel;
                    HuangJpanel.getXiao().setVisible(false);
                }
                this.huangJpanel.setTpye1(this.caozuo);
                break;
            }
            case 7: {
                if (this.huangJpanel.getTpye1() == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R请选择下注的类型！！");
                    return;
                }
                if (this.huangJpanel.getGold0() == 0L) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R请选择下注的金额！！");
                    return;
                }
                if (this.huangJpanel.getTpye1() == 5) {
                    if (this.huangJpanel.gold0 > RoleData.getRoleData().getLoginResult().getGold().longValue()) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R您带的现金不够哟！！");
                        HuangJpanel huangJpanel11 = this.huangJpanel;
                        HuangJpanel.getBaozi().setVisible(true);
                        return;
                    }
                }
                else if (this.huangJpanel.getTpye1() == 6 && this.huangJpanel.getGold0() > RoleData.getRoleData().getLoginResult().getCodecard().longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R您带的仙玉不够哟！！");
                    HuangJpanel huangJpanel12 = this.huangJpanel;
                    HuangJpanel.getXiao().setVisible(true);
                    return;
                }
                if (this.huangJpanel.getType() == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R请选择彩头！！");
                    return;
                }
                SendMessageUntil.toServer(Agreement.getAgreement().HDXCPAgreement("HDX=" + this.huangJpanel.getType() + "-" + this.huangJpanel.getTpye1() + "-" + this.huangJpanel.getGold0() + "-" + HuangJpanel.tou + "-" + HuangJpanel.tou1));
                this.huangJpanel.setTpye1(0);
                this.huangJpanel.setType(0);
                this.huangJpanel.setGold0(0L);
                HuangJpanel huangJpanel13 = this.huangJpanel;
                HuangJpanel.getGold().setText(null);
                HuangJpanel huangJpanel14 = this.huangJpanel;
                HuangJpanel.getXuanyu().setVisible(true);
                HuangJpanel huangJpanel15 = this.huangJpanel;
                HuangJpanel.getJinbi().setVisible(true);
                HuangJpanel huangJpanel16 = this.huangJpanel;
                HuangJpanel.getDashuang().setVisible(true);
                HuangJpanel huangJpanel17 = this.huangJpanel;
                HuangJpanel.getDa().setVisible(true);
                HuangJpanel huangJpanel18 = this.huangJpanel;
                HuangJpanel.getXiao().setVisible(true);
                HuangJpanel huangJpanel19 = this.huangJpanel;
                HuangJpanel.getBaozi().setVisible(true);
                FormsManagement.HideForm(160);
                break;
            }
            case 8: {
                this.huangJpanel.setTpye1(0);
                this.huangJpanel.setType(0);
                this.huangJpanel.setGold0(0L);
                HuangJpanel huangJpanel20 = this.huangJpanel;
                HuangJpanel.getGold().setText(null);
                HuangJpanel huangJpanel21 = this.huangJpanel;
                HuangJpanel.getXuanyu().setVisible(true);
                HuangJpanel huangJpanel22 = this.huangJpanel;
                HuangJpanel.getJinbi().setVisible(true);
                HuangJpanel huangJpanel23 = this.huangJpanel;
                HuangJpanel.getDashuang().setVisible(true);
                HuangJpanel huangJpanel24 = this.huangJpanel;
                HuangJpanel.getDa().setVisible(true);
                HuangJpanel huangJpanel25 = this.huangJpanel;
                HuangJpanel.getXiao().setVisible(true);
                HuangJpanel huangJpanel26 = this.huangJpanel;
                HuangJpanel.getBaozi().setVisible(true);
                break;
            }
        }
    }
}
