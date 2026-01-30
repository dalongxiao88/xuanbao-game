package com.tool.btn;

import org.come.Jpanel.ApointJpanel;
import org.come.until.AnalysisString;
import org.come.Frame.ApointJframe;
import javax.swing.JList;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import com.tool.role.RoleData;
import org.come.Frame.FactionAngelPracticeJframe;
import org.come.Frame.FactionAngelJframe;
import org.come.until.FormsManagement;
import org.come.entity.Gangapplytable;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.bean.LoginResult;
import org.come.Frame.ZhuFrame;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.FactionAngelPracticeJpanel;
import org.come.Jpanel.FactionAngelModelJpanel;
import org.come.Jpanel.FactionAngelJpanel;
import org.come.Jpanel.FactionDetailsJpanel;
import org.come.Jpanel.FactionWarJpanel;
import org.come.Jpanel.FactionPandectJpanel;
import org.come.Jpanel.FactionMemberJpanel;
import org.come.Jpanel.FactionMainJpanel;

public class FactionBtn extends MoBanBtn
{
    private int caozuo;
    private FactionMainJpanel factionMainJpanel;
    private FactionMemberJpanel factionMemberJpanel;
    private FactionPandectJpanel factionPandectJpanel;
    private FactionWarJpanel factionWarJpanel;
    private FactionDetailsJpanel factionDetailsJpanel;
    private FactionAngelJpanel factionAngelJpanel;
    private FactionAngelModelJpanel factionAngelModelJpanel;
    private FactionAngelPracticeJpanel factionAngelPracticeJpanel;
    
    public FactionBtn(String iconpath, int type, int caozuo, FactionPandectJpanel factionPandectJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.factionPandectJpanel = factionPandectJpanel;
    }
    
    public FactionBtn(String iconpath, int type, int caozuo, FactionAngelModelJpanel factionAngelModelJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.factionAngelModelJpanel = factionAngelModelJpanel;
    }
    
    public FactionBtn(String iconpath, int type, int caozuo, FactionMemberJpanel factionMemberJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.factionMemberJpanel = factionMemberJpanel;
    }
    
    public FactionBtn(String iconpath, int type, int caozuo, FactionAngelJpanel factionAngelJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.factionAngelJpanel = factionAngelJpanel;
    }
    
    public FactionBtn(String iconpath, int type, int caozuo, FactionMainJpanel factionMainJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.factionMainJpanel = factionMainJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionPandectJpanel factionPandectJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionPandectJpanel = factionPandectJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionMemberJpanel factionMemberJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionMemberJpanel = factionMemberJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionWarJpanel factionWarJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionWarJpanel = factionWarJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionDetailsJpanel factionDetailsJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionDetailsJpanel = factionDetailsJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionAngelJpanel factionAngelJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionAngelJpanel = factionAngelJpanel;
    }
    
    public FactionBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, FactionAngelPracticeJpanel factionAngelPracticeJpanel) {
        super(iconpath, type, colors);
        this.setFont(font);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.factionAngelPracticeJpanel = factionAngelPracticeJpanel;
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
                    this.factionMainJpanel.getBtnMenuPandect().setIcons(CutButtonImage.cuts("inkImg/button1/K16.png"));
                    this.factionMainJpanel.getBtnMenuMember().setIcons(CutButtonImage.cuts("inkImg/button1/K16.png"));
                    this.factionMainJpanel.getBtnMenuWar().setIcons(CutButtonImage.cuts("inkImg/button1/K19.png"));
                    this.factionMainJpanel.getFactionCardJpanel().getCardLayout().show(this.factionMainJpanel.getFactionCardJpanel(), "pandect");
                }
                else if (this.caozuo == 2) {
                    this.factionMainJpanel.getBtnMenuPandect().setIcons(CutButtonImage.cuts("inkImg/button1/K15.png"));
                    this.factionMainJpanel.getBtnMenuMember().setIcons(CutButtonImage.cuts("inkImg/button1/K18.png"));
                    this.factionMainJpanel.getBtnMenuWar().setIcons(CutButtonImage.cuts("inkImg/button1/K19.png"));
                    this.factionMainJpanel.getFactionCardJpanel().getCardLayout().show(this.factionMainJpanel.getFactionCardJpanel(), "member");
                }
                else {
                    if (this.caozuo == 3) {
                        ZhuFrame.getZhuJpanel().addPrompt2("暂未开放,敬请期待");
                        return;
                    }
                    if (this.caozuo >= 5 && this.caozuo <= 7) {
                        int menuType = this.factionMemberJpanel.getMenuType();
                        if (menuType == 5) {
                            this.factionMemberJpanel.getBtnMenuAll().setIcons(CutButtonImage.cuts("inkImg/button/B272.png"));
                        }
                        else if (menuType == 6) {
                            this.factionMemberJpanel.getBtnMenuCore().setIcons(CutButtonImage.cuts("inkImg/button/B274.png"));
                        }
                        else if (menuType == 7) {
                            this.factionMemberJpanel.getBtnMenuApply().setIcons(CutButtonImage.cuts("inkImg/button/B278.png"));
                        }
                        this.factionMemberJpanel.showBtn(false);
                        this.factionMemberJpanel.setMenuType(this.caozuo);
                        menuType = this.factionMemberJpanel.getMenuType();
                        if (menuType == 5) {
                            this.factionMemberJpanel.getBtnMenuAll().setIcons(CutButtonImage.cuts("inkImg/button/B273.png"));
                            this.factionMemberJpanel.setIconCoumn(null);
                        }
                        else if (menuType == 6) {
                            this.factionMemberJpanel.getBtnMenuCore().setIcons(CutButtonImage.cuts("inkImg/button/B275.png"));
                            this.factionMemberJpanel.setIconCoumn(null);
                        }
                        else if (menuType == 7) {
                            this.factionMemberJpanel.getBtnMenuApply().setIcons(CutButtonImage.cuts("inkImg/button/B279.png"));
                            this.factionMemberJpanel.setIconCoumn(CutButtonImage.getImage("inkImg/background/S170.png", -1, -1));
                        }
                        this.factionMemberJpanel.showBtn(true);
                        this.factionMemberJpanel.changeTable();
                        this.factionMemberJpanel.showMenuMessage(this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean());
                    }
                    else if (this.caozuo == 10) {
                        this.Abdicate();
                    }
                    else if (this.caozuo == 11) {
                        if ("卸任".equals(this.getText())) {
                            this.Appointment();
                        }
                        else if ("拒绝玩家".equals(this.getText())) {
                            this.RefuseJoin();
                        }
                    }
                    else if (this.caozuo == 12) {
                        if ("踢出帮派".equals(this.getText())) {
                            this.Kickout();
                        }
                        else if (!"逐出".equals(this.getText()) && "接收玩家".equals(this.getText())) {
                            this.AgreeJoin();
                        }
                    }
                    else if (this.caozuo == 13) {
                        if ("脱离帮派".equals(this.getText())) {
                            this.BreakAway();
                        }
                        else if ("清空列表".equals(this.getText())) {
                            this.clearApply();
                        }
                    }
                    else if (this.caozuo == 14) {
                        if (this.factionMemberJpanel.getMenuType() != 7) {
                            int selectedRow = this.factionMemberJpanel.getTable().getSelectedRow();
                            if (selectedRow != -1) {
                                LoginResult loginResult = (LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(selectedRow);
                                PlayerMonitor.addFriend(loginResult.getRole_id(), loginResult.getRolename());
                            }
                        }
                        else {
                            int selectedRow = this.factionMemberJpanel.getTable().getSelectedRow();
                            if (selectedRow != -1) {
                                Gangapplytable gangapplytable = (Gangapplytable)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().get(selectedRow);
                                PlayerMonitor.addFriend(gangapplytable.getRole_id(), gangapplytable.getRolename());
                            }
                        }
                    }
                    else if (this.caozuo == 15) {
                        FormsManagement.showForm(49);
                    }
                    else if (this.caozuo == 25) {
                        FormsManagement.showForm(112);
                    }
                    else if (this.caozuo == 16) {
                        this.factionAngelJpanel.changeMenuShow(1);
                    }
                    else if (this.caozuo == 17) {
                        this.factionAngelJpanel.changeMenuShow(2);
                    }
                    else if (this.caozuo == 18) {
                        FactionAngelJpanel angelJpanel = FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel();
                        if (this.factionAngelModelJpanel.getLvlChange() <= this.factionAngelModelJpanel.getLvlNow()) {
                            ZhuFrame.getZhuJpanel().addPrompt2("不可以再减少点数了");
                            return;
                        }
                        this.factionAngelModelJpanel.setLvlChange(this.factionAngelModelJpanel.getLvlChange() - 1);
                        this.factionAngelModelJpanel.refreshLvlChange();
                        angelJpanel.setTypeLvlResidue(angelJpanel.getTypeLvlResidue() + 1);
                        if (this.factionAngelModelJpanel.getLvlChange() <= this.factionAngelModelJpanel.getLvlNow()) {
                            this.factionAngelModelJpanel.getLabDegree().setForeground(Color.white);
                            this.factionAngelModelJpanel.getLabLvl().setForeground(Color.WHITE);
                        }
                    }
                    else if (this.caozuo == 19) {
                        FactionAngelJpanel angelJpanel = FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel();
                        if (angelJpanel.getTypeLvlResidue() <= 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("剩余点数不足");
                            return;
                        }
                        int typeNumMax = 20;
                        int menuType2 = angelJpanel.getMenuType();
                        if (menuType2 == 1) {
                            typeNumMax = 20;
                        }
                        else if (menuType2 == 2) {
                            typeNumMax = 30;
                        }
                        if (this.factionAngelModelJpanel.getLvlChange() >= typeNumMax) {
                            ZhuFrame.getZhuJpanel().addPrompt2("不可以再增加点数了");
                            return;
                        }
                        this.factionAngelModelJpanel.setLvlChange(this.factionAngelModelJpanel.getLvlChange() + 1);
                        this.factionAngelModelJpanel.refreshLvlChange();
                        angelJpanel.setTypeLvlResidue(angelJpanel.getTypeLvlResidue() - 1);
                        if (this.factionAngelModelJpanel.getLvlChange() > this.factionAngelModelJpanel.getLvlNow()) {
                            this.factionAngelModelJpanel.getLabDegree().setForeground(Color.GREEN);
                            this.factionAngelModelJpanel.getLabLvl().setForeground(Color.GREEN);
                        }
                    }
                    else if (this.caozuo == 20) {
                        FormsManagement.showForm(106);
                        FactionAngelPracticeJframe.getFactionAngelPracticeJframe().getFactionAngelPracticeJpanel().showPanel(this.factionAngelJpanel.getMenuType());
                    }
                    else if (this.caozuo == 21) {
                        String[] resistance = RoleData.getRoleData().getLoginResult().getResistance((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D");
                        if (resistance == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没加点怎么洗点");
                            return;
                        }
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.washPoint, 7 + ((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D"), "#W确定要花50W银两重置#G" + ((this.factionAngelJpanel.getMenuType() == 1) ? "小成修炼" : "大成修炼") + "?");
                    }
                    else if (this.caozuo == 23) {
                        JList<FactionAngelModelJpanel> list = this.factionAngelJpanel.getListFactionJpanel();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(7);
                        buffer.append((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D");
                        boolean is = true;
                        for (int i = 0, length = (this.factionAngelJpanel.getMenuType() == 1) ? 13 : 18; i < length; ++i) {
                            FactionAngelModelJpanel jpanel = (FactionAngelModelJpanel)list.getComponent(i);
                            if (jpanel.getLvlChange() != 0) {
                                if (jpanel.getLvlNow() != jpanel.getLvlChange()) {
                                    is = false;
                                }
                                if (buffer.length() > 2) {
                                    buffer.append("#");
                                }
                                buffer.append(jpanel.getLabName().getText());
                                buffer.append("=");
                                buffer.append(jpanel.getLvlValue());
                            }
                        }
                        if (is) {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还未修改");
                            return;
                        }
                        String mes = Agreement.getAgreement().rolechangeAgreement(buffer.toString());
                        SendMessageUntil.toServer(mes);
                    }
                    else if (this.caozuo == 24) {
                        LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
                        if ("大成修炼".equals(this.getText())) {
                            int extraPointInt = loginResult2.getExtraPointInt("X");
                            if (extraPointInt < 30) {
                                ZhuFrame.getZhuJpanel().addPrompt2("小成修炼尚未结束");
                                return;
                            }
                            int pointInt = loginResult2.getExtraPointInt("D");
                            if (pointInt >= 60) {
                                ZhuFrame.getZhuJpanel().addPrompt2("大成修炼已经结束");
                                return;
                            }
                            if (loginResult2.getExperience().compareTo(new BigDecimal(5000000)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("经验不足");
                                return;
                            }
                            if (loginResult2.getContribution().compareTo(new BigDecimal(1500)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("帮贡不足");
                                return;
                            }
                            if (loginResult2.getGold().compareTo(new BigDecimal(5000000)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                                return;
                            }
                            String mes = Agreement.getAgreement().rolechangeAgreement("6D");
                            SendMessageUntil.toServer(mes);
                        }
                        else if ("小成修炼".equals(this.getText())) {
                            int extraPointInt = loginResult2.getExtraPointInt("X");
                            if (extraPointInt >= 30) {
                                ZhuFrame.getZhuJpanel().addPrompt2("小成修炼已经修炼完毕");
                                return;
                            }
                            if (loginResult2.getExperience().compareTo(new BigDecimal(2000000)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("经验不足");
                                return;
                            }
                            if (loginResult2.getContribution().compareTo(new BigDecimal(300)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("帮贡不足");
                                return;
                            }
                            if (loginResult2.getGold().compareTo(new BigDecimal(2000000)) < 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                                return;
                            }
                            String mes2 = Agreement.getAgreement().rolechangeAgreement("6X");
                            SendMessageUntil.toServer(mes2);
                        }
                    }
                }
            }
            else if (this.caozuo == 1) {
                this.factionMainJpanel.getBtnMenuPandect().setIcons(CutButtonImage.cuts("img/xy2uiimg/B270.png"));
                this.factionMainJpanel.getBtnMenuMember().setIcons(CutButtonImage.cuts("img/xy2uiimg/B267.png"));
                this.factionMainJpanel.getBtnMenuWar().setIcons(CutButtonImage.cuts("img/xy2uiimg/B269.png"));
                this.factionMainJpanel.getFactionCardJpanel().getCardLayout().show(this.factionMainJpanel.getFactionCardJpanel(), "pandect");
            }
            else if (this.caozuo == 2) {
                this.factionMainJpanel.getBtnMenuPandect().setIcons(CutButtonImage.cuts("img/xy2uiimg/B271.png"));
                this.factionMainJpanel.getBtnMenuMember().setIcons(CutButtonImage.cuts("img/xy2uiimg/B266.png"));
                this.factionMainJpanel.getBtnMenuWar().setIcons(CutButtonImage.cuts("img/xy2uiimg/B269.png"));
                this.factionMainJpanel.getFactionCardJpanel().getCardLayout().show(this.factionMainJpanel.getFactionCardJpanel(), "member");
            }
            else {
                if (this.caozuo == 3) {
                    ZhuFrame.getZhuJpanel().addPrompt2("暂未开放,敬请期待");
                    return;
                }
                if (this.caozuo >= 5 && this.caozuo <= 7) {
                    int menuType = this.factionMemberJpanel.getMenuType();
                    if (menuType == 5) {
                        this.factionMemberJpanel.getBtnMenuAll().setIcons(CutButtonImage.cuts("img/xy2uiimg/B272.png"));
                    }
                    else if (menuType == 6) {
                        this.factionMemberJpanel.getBtnMenuCore().setIcons(CutButtonImage.cuts("img/xy2uiimg/B274.png"));
                    }
                    else if (menuType == 7) {
                        this.factionMemberJpanel.getBtnMenuApply().setIcons(CutButtonImage.cuts("img/xy2uiimg/V291.png"));
                    }
                    this.factionMemberJpanel.showBtn(false);
                    this.factionMemberJpanel.setMenuType(this.caozuo);
                    menuType = this.factionMemberJpanel.getMenuType();
                    if (menuType == 5) {
                        this.factionMemberJpanel.getBtnMenuAll().setIcons(CutButtonImage.cuts("img/xy2uiimg/B273.png"));
                        this.factionMemberJpanel.setIconCoumn(null);
                    }
                    else if (menuType == 6) {
                        this.factionMemberJpanel.getBtnMenuCore().setIcons(CutButtonImage.cuts("img/xy2uiimg/B275.png"));
                        this.factionMemberJpanel.setIconCoumn(null);
                    }
                    else if (menuType == 7) {
                        this.factionMemberJpanel.getBtnMenuApply().setIcons(CutButtonImage.cuts("img/xy2uiimg/V290.png"));
                        this.factionMemberJpanel.setIconCoumn(CutButtonImage.getImage("img/xy2uiimg/S170.png", -1, -1));
                    }
                    this.factionMemberJpanel.showBtn(true);
                    this.factionMemberJpanel.changeTable();
                    this.factionMemberJpanel.showMenuMessage(this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean());
                }
                else if (this.caozuo == 10) {
                    this.Abdicate();
                }
                else if (this.caozuo == 11) {
                    if ("卸任".equals(this.getText())) {
                        this.Appointment();
                    }
                    else if ("拒绝玩家".equals(this.getText())) {
                        this.RefuseJoin();
                    }
                }
                else if (this.caozuo == 12) {
                    if ("踢出帮派".equals(this.getText())) {
                        this.Kickout();
                    }
                    else if (!"逐出".equals(this.getText()) && "接收玩家".equals(this.getText())) {
                        this.AgreeJoin();
                    }
                }
                else if (this.caozuo == 13) {
                    if ("脱离帮派".equals(this.getText())) {
                        this.BreakAway();
                    }
                    else if ("清空列表".equals(this.getText())) {
                        this.clearApply();
                    }
                }
                else if (this.caozuo == 14) {
                    if (this.factionMemberJpanel.getMenuType() != 7) {
                        int selectedRow = this.factionMemberJpanel.getTable().getSelectedRow();
                        if (selectedRow != -1) {
                            LoginResult loginResult = (LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(selectedRow);
                            PlayerMonitor.addFriend(loginResult.getRole_id(), loginResult.getRolename());
                        }
                    }
                    else {
                        int selectedRow = this.factionMemberJpanel.getTable().getSelectedRow();
                        if (selectedRow != -1) {
                            Gangapplytable gangapplytable = (Gangapplytable)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().get(selectedRow);
                            PlayerMonitor.addFriend(gangapplytable.getRole_id(), gangapplytable.getRolename());
                        }
                    }
                }
                else if (this.caozuo == 15) {
                    FormsManagement.showForm(49);
                }
                else if (this.caozuo == 25) {
                    FormsManagement.showForm(112);
                }
                else if (this.caozuo == 16) {
                    this.factionAngelJpanel.changeMenuShow(1);
                }
                else if (this.caozuo == 17) {
                    this.factionAngelJpanel.changeMenuShow(2);
                }
                else if (this.caozuo == 18) {
                    FactionAngelJpanel angelJpanel = FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel();
                    if (this.factionAngelModelJpanel.getLvlChange() <= this.factionAngelModelJpanel.getLvlNow()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不可以再减少点数了");
                        return;
                    }
                    this.factionAngelModelJpanel.setLvlChange(this.factionAngelModelJpanel.getLvlChange() - 1);
                    this.factionAngelModelJpanel.refreshLvlChange();
                    angelJpanel.setTypeLvlResidue(angelJpanel.getTypeLvlResidue() + 1);
                    if (this.factionAngelModelJpanel.getLvlChange() <= this.factionAngelModelJpanel.getLvlNow()) {
                        this.factionAngelModelJpanel.getLabDegree().setForeground(Color.white);
                        this.factionAngelModelJpanel.getLabLvl().setForeground(Color.WHITE);
                    }
                }
                else if (this.caozuo == 19) {
                    FactionAngelJpanel angelJpanel = FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel();
                    if (angelJpanel.getTypeLvlResidue() <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("剩余点数不足");
                        return;
                    }
                    int typeNumMax = 20;
                    int menuType2 = angelJpanel.getMenuType();
                    if (menuType2 == 1) {
                        typeNumMax = 20;
                    }
                    else if (menuType2 == 2) {
                        typeNumMax = 30;
                    }
                    if (this.factionAngelModelJpanel.getLvlChange() >= typeNumMax) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不可以再增加点数了");
                        return;
                    }
                    this.factionAngelModelJpanel.setLvlChange(this.factionAngelModelJpanel.getLvlChange() + 1);
                    this.factionAngelModelJpanel.refreshLvlChange();
                    angelJpanel.setTypeLvlResidue(angelJpanel.getTypeLvlResidue() - 1);
                    if (this.factionAngelModelJpanel.getLvlChange() > this.factionAngelModelJpanel.getLvlNow()) {
                        this.factionAngelModelJpanel.getLabDegree().setForeground(Color.GREEN);
                        this.factionAngelModelJpanel.getLabLvl().setForeground(Color.GREEN);
                    }
                }
                else if (this.caozuo == 20) {
                    FormsManagement.showForm(106);
                    FactionAngelPracticeJframe.getFactionAngelPracticeJframe().getFactionAngelPracticeJpanel().showPanel(this.factionAngelJpanel.getMenuType());
                }
                else if (this.caozuo == 21) {
                    String[] resistance = RoleData.getRoleData().getLoginResult().getResistance((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D");
                    if (resistance == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你还没加点怎么洗点");
                        return;
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.washPoint, 7 + ((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D"), "#W确定要花50W银两重置#G" + ((this.factionAngelJpanel.getMenuType() == 1) ? "小成修炼" : "大成修炼") + "?");
                }
                else if (this.caozuo == 23) {
                    JList<FactionAngelModelJpanel> list = this.factionAngelJpanel.getListFactionJpanel();
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(7);
                    buffer.append((this.factionAngelJpanel.getMenuType() == 1) ? "X" : "D");
                    boolean is = true;
                    for (int i = 0, length = (this.factionAngelJpanel.getMenuType() == 1) ? 13 : 18; i < length; ++i) {
                        FactionAngelModelJpanel jpanel = (FactionAngelModelJpanel)list.getComponent(i);
                        if (jpanel.getLvlChange() != 0) {
                            if (jpanel.getLvlNow() != jpanel.getLvlChange()) {
                                is = false;
                            }
                            if (buffer.length() > 2) {
                                buffer.append("#");
                            }
                            buffer.append(jpanel.getLabName().getText());
                            buffer.append("=");
                            buffer.append(jpanel.getLvlValue());
                        }
                    }
                    if (is) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你还未修改");
                        return;
                    }
                    String mes = Agreement.getAgreement().rolechangeAgreement(buffer.toString());
                    SendMessageUntil.toServer(mes);
                }
                else if (this.caozuo == 24) {
                    LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
                    if ("大成修炼".equals(this.getText())) {
                        int extraPointInt = loginResult2.getExtraPointInt("X");
                        if (extraPointInt < 30) {
                            ZhuFrame.getZhuJpanel().addPrompt2("小成修炼尚未结束");
                            return;
                        }
                        int pointInt = loginResult2.getExtraPointInt("D");
                        if (pointInt >= 60) {
                            ZhuFrame.getZhuJpanel().addPrompt2("大成修炼已经结束");
                            return;
                        }
                        if (loginResult2.getExperience().compareTo(new BigDecimal(5000000)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("经验不足");
                            return;
                        }
                        if (loginResult2.getContribution().compareTo(new BigDecimal(1500)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("帮贡不足");
                            return;
                        }
                        if (loginResult2.getGold().compareTo(new BigDecimal(5000000)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                            return;
                        }
                        String mes = Agreement.getAgreement().rolechangeAgreement("6D");
                        SendMessageUntil.toServer(mes);
                    }
                    else if ("小成修炼".equals(this.getText())) {
                        int extraPointInt = loginResult2.getExtraPointInt("X");
                        if (extraPointInt >= 30) {
                            ZhuFrame.getZhuJpanel().addPrompt2("小成修炼已经修炼完毕");
                            return;
                        }
                        if (loginResult2.getExperience().compareTo(new BigDecimal(2000000)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("经验不足");
                            return;
                        }
                        if (loginResult2.getContribution().compareTo(new BigDecimal(300)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("帮贡不足");
                            return;
                        }
                        if (loginResult2.getGold().compareTo(new BigDecimal(2000000)) < 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                            return;
                        }
                        String mes2 = Agreement.getAgreement().rolechangeAgreement("6X");
                        SendMessageUntil.toServer(mes2);
                    }
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public void BreakAway() {
        if (!RoleData.getRoleData().getLoginResult().getGangpost().equals("帮主")) {
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.BreakAway, "消息", "#Y       您确定要脱离帮派吗?");
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("帮主不能退出帮派!");
        }
    }
    
    public void Kickout() {
        if (RoleData.getRoleData().getLoginResult().getGangpost().equals("帮主")) {
            int index = -1;
            LoginResult roleTable = null;
            index = this.factionMemberJpanel.getTable().getSelectedRow();
            if (index == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个帮派人员！");
                return;
            }
            roleTable = (LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index);
            String tiren = "";
            if (roleTable != null && !roleTable.getRolename().equals(RoleData.getRoleData().getLoginResult().getRolename())) {
                tiren = roleTable.getRolename();
            }
            if (tiren != null && !tiren.equals("")) {
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.KickOut, Integer.valueOf(index), "#Y  您确定要将  #G" + tiren + "    #Y踢出帮派吗?");
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("不能踢你自己!");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("只有帮主才有权利踢人!");
        }
    }
    
    public void AgreeJoin() {
        if (this.factionMemberJpanel.Important(RoleData.getRoleData().getLoginResult().getGangpost())) {
            int index = this.factionMemberJpanel.getTable().getSelectedRow();
            if (index == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个帮派人员！");
                return;
            }
            this.tongyi(index);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("只有护法以上才有权利操作!");
        }
    }
    
    public void tongyi(int index) {
        try {
            String sendMes = Agreement.GangAgreeAgreement(((Gangapplytable)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().get(index)).getRole_id().toString());
            SendMessageUntil.toServer(sendMes);
            this.factionMemberJpanel.getTableModel().removeRow(index);
            this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().remove(index);
        }
        catch (Exception ex) {}
    }
    
    public void RefuseJoin() {
        if (RoleData.getRoleData().getLoginResult().getGangpost().equals("帮主") || RoleData.getRoleData().getLoginResult().getGangpost().equals("护法")) {
            int index = this.factionMemberJpanel.getTable().getSelectedRow();
            if (index == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个帮派人员！");
                return;
            }
            this.jujue(index);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("只有护法以上才有权利操作!");
        }
    }
    
    public void jujue(int index) {
        try {
            String sendMes = Agreement.GangRefuseAgreement(((Gangapplytable)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().get(index)).getRole_id().toString());
            SendMessageUntil.toServer(sendMes);
            this.factionMemberJpanel.getTableModel().removeRow(index);
            this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().remove(index);
        }
        catch (Exception ex) {}
    }
    
    public void Abdicate() {
        if (RoleData.getRoleData().getLoginResult().getGangpost().equals("帮主")) {
            if (this.xuanzhong()) {
                FormsManagement.showForm(37);
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("只有帮主才有权利!");
        }
    }
    
    public boolean xuanzhong() {
        try {
            int index = this.factionMemberJpanel.getTable().getSelectedRow();
            if (index == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个帮派人员！");
                return false;
            }
            if (((LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index)).getRole_id().compareTo(RoleData.getRoleData().getLoginResult().getRole_id()) != 0) {
                ApointJpanel apointJpanel = ApointJframe.getApointJframe().getApointJpanel();
                apointJpanel.getLabname().setText(((LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index)).getRolename());
                apointJpanel.getLabRace().setText(((LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index)).getRace_name());
                apointJpanel.getLabLevel().setText(AnalysisString.lvl((int)((LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index)).getGrade()));
                ApointJpanel.index = index;
                return true;
            }
            ZhuFrame.getZhuJpanel().addPrompt2("不能对自己进行操作!");
        }
        catch (Exception ex) {}
        return false;
    }
    
    public void Appointment() {
        if (RoleData.getRoleData().getLoginResult().getGangpost().equals("帮主")) {
            int index = -1;
            LoginResult roleTable = null;
            try {
                index = this.factionMemberJpanel.getTable().getSelectedRow();
                if (index == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择一个帮派人员！");
                    return;
                }
                roleTable = (LoginResult)this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            String tiren = "";
            if (roleTable != null && !roleTable.getRolename().equals(RoleData.getRoleData().getLoginResult().getRolename())) {
                tiren = roleTable.getRolename();
            }
            if (tiren != null && !tiren.equals("")) {
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Abdication, Integer.valueOf(index), "#Y  您确定要退位给  #G" + tiren + "    #Y吗?");
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("不能退位给你自己!");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你不是帮主吧!");
        }
    }
    
    public void clearApply() {
        if (this.factionMemberJpanel.Important(RoleData.getRoleData().getLoginResult().getGangpost())) {
            String sendMes = Agreement.GangRefuseAgreement("");
            SendMessageUntil.toServer(sendMes);
            this.factionMemberJpanel.getTableModel().setRowCount(0);
            this.factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getGangapplytables().clear();
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你不是帮主或者护法");
        }
    }
}
