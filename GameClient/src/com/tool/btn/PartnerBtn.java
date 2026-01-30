package com.tool.btn;

import java.math.BigDecimal;
import org.come.model.PalData;
import java.util.List;
import java.util.Iterator;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerTeamJpanel;
import com.tool.tcpimg.UIUtils;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.UserMessUntil;
import org.come.until.Util;
import org.come.Jpanel.ZhuJpanel;
import java.util.ArrayList;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Jpanel.PartnerSkillJpanel;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import com.tool.pet.PetProperty;
import com.tool.role.RoleData;
import org.come.Frame.PartnerJframe;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.PartnerMainJpanel;

public class PartnerBtn extends MoBanBtn
{
    private int caozuo;
    private PartnerMainJpanel partnerMainJpanel;
    
    public PartnerBtn(String iconpath, int type, int caozuo, PartnerMainJpanel partnerMainJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.partnerMainJpanel = partnerMainJpanel;
    }
    
    public PartnerBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
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
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                if (this.caozuo == 1) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("inkImg/button1/K78.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("inkImg/button1/K79.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("inkImg/button1/K81.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "team");
                }
                else if (this.caozuo == 2) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("inkImg/button1/K77.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("inkImg/button1/K80.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("inkImg/button1/K81.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "skill");
                }
                else if (this.caozuo == 3) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("inkImg/button1/K77.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("inkImg/button1/K79.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("inkImg/button1/K82.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "equip");
                }
                else if (this.caozuo == 4) {
                    if ("参战".equals(this.getText())) {
                        if (PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPalDataChooseId() < 0) {
                            return;
                        }
                        PartnerTeamJpanel partnerTeamJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel();
                        if (partnerTeamJpanel.hideBtnArr()) {
                            return;
                        }
                        PartnerBtn[] btnArrAck = partnerTeamJpanel.getBtnArrAck();
                        String[] btnArrStr = partnerTeamJpanel.getBtnArrStr();
                        for (int i = 0; i < btnArrAck.length; ++i) {
                            btnArrAck[3 - i].setText(btnArrStr[i]);
                        }
                        partnerTeamJpanel.showBtnArr(true);
                    }
                    else if ("激活".equals(this.getText())) {
                        this.activatePal();
                    }
                    else if ("调整".equals(this.getText())) {
                        if (PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPalDataChooseId() < 0) {
                            return;
                        }
                        PartnerMainJpanel partnerMainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        PartnerTeamJpanel partnerTeamJpanel2 = partnerMainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel();
                        if (partnerTeamJpanel2.hideBtnArr()) {
                            return;
                        }
                        PartnerBtn[] btnArrAck2 = partnerTeamJpanel2.getBtnArrAck();
                        String[] btnArrStr2 = partnerTeamJpanel2.getBtnArrStr();
                        String pals = RoleData.getRoleData().getLoginResult().getPals();
                        if (pals != null) {
                            int num = 0;
                            String[] palsArr = pals.split("\\|");
                            for (int j = 0; j < palsArr.length; ++j) {
                                if (palsArr[j].equals(partnerMainJpanel.pidGetPal(partnerMainJpanel.getPalDataChooseId()).getId() + "")) {
                                    num = j;
                                }
                            }
                            int sx = 0;
                            for (int k = 0; k < btnArrAck2.length; ++k) {
                                if (num != k) {
                                    btnArrAck2[3 - sx].setText(btnArrStr2[k]);
                                    ++sx;
                                }
                            }
                            btnArrAck2[0].setText(btnArrStr2[4]);
                        }
                        partnerTeamJpanel2.showBtnArr(true);
                    }
                }
                else if (this.caozuo == 5) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            PetProperty.ShowQl(pidGetPal);
                            FormsManagement.showForm(58);
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else if (this.caozuo == 6) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            if (PartnerSkillJpanel.AIdataList != null) {
                                pidGetPal.setPalSkillAI(PartnerSkillJpanel.AIdataList);
                                String skills = "";
                                for (String v : PartnerSkillJpanel.AIdataList) {
                                    skills = skills + v + ",";
                                }
                                String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|addSkillAI|" + skills);
                                SendMessageUntil.toServer(sendmes);
                                ZhuFrame.getZhuJpanel().addPrompt2("方案已保存！");
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("这个伙伴还没有设置方案！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else if (this.caozuo == 7) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            if (PartnerSkillJpanel.AIdataList != null && pidGetPal.getPalSkillAI() != null) {
                                PartnerSkillJpanel.AIdataList = new ArrayList<>();
                                for (int i = 0; i < 7; ++i) {
                                    PartnerSkillJpanel.labSkill[i].setText("第" + (i + 1) + "回合：");
                                    PartnerSkillJpanel.AIdataList.add("");
                                }
                                pidGetPal.setPalSkillAI(null);
                                ZhuFrame.getZhuJpanel().addPrompt2("方案已清空");
                                String sendmes2 = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|deleteSkillAI");
                                SendMessageUntil.toServer(sendmes2);
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("该伙伴还没有施法方案");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else {
                    if (this.caozuo == 8) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该道具已经失效");
                        return;
                    }
                    if (this.caozuo == 9) {
                        ZhuJpanel.setUseGoodsType(0);
                        FormsManagement.showForm(2);
                    }
                    else if (this.caozuo == 10) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        String[] btnArrStr3 = mainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel().getBtnArrStr();
                        List<Pal> pals2 = null;
                        if (btnArrStr3[0].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(1, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[1].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(2, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[2].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(3, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[3].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(4, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[4].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(-1, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        mainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel().hideBtnArr();
                        mainJpanel.refreshPals(pals2);
                    }
                    else if (this.caozuo == 11) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getSummoning() != null) {
                                if (pidGetPal.getSummoning() != null) {
                                    ZhuFrame.getZhuJpanel().creatpettext(pidGetPal.getSummoning());
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有召唤兽请前往召唤兽面板添加！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 12) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getSummoning() != null) {
                                if (pidGetPal.getSummoning() != null) {
                                    if (Util.isCanBuyOrno()) {
                                        return;
                                    }
                                    PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                                    String name = palData.getName();
                                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palGetPet, pidGetPal, "#W确定要将伙伴" + name + "的召唤兽:#G" + pidGetPal.getSummoning().getSummoningname() + "#W取回吗?");
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有召唤兽！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 13) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getLingbao() != null) {
                                if (pidGetPal.getLingbao() != null) {
                                    ZhuFrame.getZhuJpanel().creatlingtext(pidGetPal.getLingbao());
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有装备灵宝请前往灵宝面板添加！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 14) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getLingbao() != null) {
                                if (pidGetPal.getLingbao() != null) {
                                    if (Util.isCanBuyOrno()) {
                                        return;
                                    }
                                    PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                                    String name = palData.getName();
                                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palGetLingbao, pidGetPal, "#W确定要将伙伴" + name + "的灵宝:#G" + pidGetPal.getLingbao().getBaoname() + "#W取回吗?");
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有灵宝！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo >= 2024) {
                        int ix = 0;
                        if (PartnerSkillJpanel.AIdataList != null) {
                            for (String s : PartnerSkillJpanel.AIdataList) {
                                if (s != null && !s.equals("")) {
                                    ++ix;
                                }
                            }
                        }
                        if (this.caozuo - 2024 > ix) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先设置上一个回合的技能");
                            return;
                        }
                        int ys = (this.caozuo - 2024) * 30;
                        PartnerSkillJpanel.x = this.caozuo - 2024;
                        for (int l = 0; l < 5; ++l) {
                            PartnerSkillJpanel.labSkillName[l].setFont(new Font("楷体", 1, 17));
                            PartnerSkillJpanel.labSkillName[l].setForeground(UIUtils.COLOR_WHITE[0]);
                            PartnerSkillJpanel.labSkillName[l].setBounds(80, ys + l * 22, 72, 25);
                            PartnerSkillJpanel.labSkillName[l].setVisible(true);
                        }
                        if (e.getButton() == 3) {
                            for (int l = 0; l < 5; ++l) {
                                PartnerSkillJpanel.labSkillName[l].setVisible(false);
                            }
                        }
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        else {
            try {
                if (this.caozuo == 1) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("img/xy2uiimg/B229.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("img/xy2uiimg/B232.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "team");
                }
                else if (this.caozuo == 2) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("img/xy2uiimg/B230.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("img/xy2uiimg/B231.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "skill");
                }
                else if (this.caozuo == 3) {
                    this.partnerMainJpanel.getBtnTeam().setIcons(CutButtonImage.cuts("img/xy2uiimg/B230.png"));
                    this.partnerMainJpanel.getBtnSkill().setIcons(CutButtonImage.cuts("img/xy2uiimg/B232.png"));
                    this.partnerMainJpanel.getBtnEquip().setIcons(CutButtonImage.cuts("img/xy2uiimg/B233.png"));
                    this.partnerMainJpanel.getPartnerCardJpanel().getCardLayout().show(this.partnerMainJpanel.getPartnerCardJpanel(), "equip");
                }
                else if (this.caozuo == 4) {
                    if ("参战".equals(this.getText())) {
                        if (PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPalDataChooseId() < 0) {
                            return;
                        }
                        PartnerTeamJpanel partnerTeamJpanel2 = PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel();
                        if (partnerTeamJpanel2.hideBtnArr()) {
                            return;
                        }
                        PartnerBtn[] btnArrAck2 = partnerTeamJpanel2.getBtnArrAck();
                        String[] btnArrStr2 = partnerTeamJpanel2.getBtnArrStr();
                        for (int m = 0; m < btnArrAck2.length; ++m) {
                            btnArrAck2[3 - m].setText(btnArrStr2[m]);
                        }
                        partnerTeamJpanel2.showBtnArr(true);
                    }
                    else if ("激活".equals(this.getText())) {
                        this.activatePal();
                    }
                    else if ("调整".equals(this.getText())) {
                        if (PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPalDataChooseId() < 0) {
                            return;
                        }
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        PartnerTeamJpanel partnerTeamJpanel2 = mainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel();
                        if (partnerTeamJpanel2.hideBtnArr()) {
                            return;
                        }
                        PartnerBtn[] btnArrAck2 = partnerTeamJpanel2.getBtnArrAck();
                        String[] btnArrStr2 = partnerTeamJpanel2.getBtnArrStr();
                        String pals = RoleData.getRoleData().getLoginResult().getPals();
                        if (pals != null) {
                            int num = 0;
                            String[] palsArr = pals.split("\\|");
                            for (int sx = 0; sx < palsArr.length; ++sx) {
                                if (palsArr[sx].equals("" + mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId()).getId())) {
                                    num = sx;
                                }
                            }
                            int sx = 0;
                            for (int k = 0; k < btnArrAck2.length; ++k) {
                                if (num != k) {
                                    btnArrAck2[3 - sx].setText(btnArrStr2[k]);
                                    ++sx;
                                }
                            }
                            btnArrAck2[0].setText(btnArrStr2[4]);
                        }
                        partnerTeamJpanel2.showBtnArr(true);
                    }
                }
                else if (this.caozuo == 5) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            PetProperty.ShowQl(pidGetPal);
                            FormsManagement.showForm(58);
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else if (this.caozuo == 6) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            if (PartnerSkillJpanel.AIdataList != null) {
                                pidGetPal.setPalSkillAI(PartnerSkillJpanel.AIdataList);
                                String skills = "";
                                for (String v : PartnerSkillJpanel.AIdataList) {
                                    skills = skills + v + ",";
                                }
                                String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|addSkillAI|" + skills);
                                SendMessageUntil.toServer(sendmes);
                                ZhuFrame.getZhuJpanel().addPrompt2("方案已保存！");
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("这个伙伴还没有设置方案！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else if (this.caozuo == 7) {
                    PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                    int chooseId = mainJpanel.getPalDataChooseId();
                    if (chooseId > 0) {
                        Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                        if (pidGetPal != null) {
                            if (PartnerSkillJpanel.AIdataList != null && pidGetPal.getPalSkillAI() != null) {
                                PartnerSkillJpanel.AIdataList = new ArrayList<>();
                                for (int i = 0; i < 7; ++i) {
                                    PartnerSkillJpanel.labSkill[i].setText("第" + (i + 1) + "回合：");
                                    PartnerSkillJpanel.AIdataList.add("");
                                }
                                pidGetPal.setPalSkillAI(null);
                                ZhuFrame.getZhuJpanel().addPrompt2("方案已清空");
                                String sendmes2 = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|deleteSkillAI");
                                SendMessageUntil.toServer(sendmes2);
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("该伙伴还没有施法方案");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你还没有激活当前伙伴");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                    }
                }
                else {
                    if (this.caozuo == 8) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该道具已经失效");
                        return;
                    }
                    if (this.caozuo == 9) {
                        ZhuJpanel.setUseGoodsType(0);
                        FormsManagement.showForm(2);
                    }
                    else if (this.caozuo == 10) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        String[] btnArrStr3 = mainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel().getBtnArrStr();
                        List<Pal> pals2 = null;
                        if (btnArrStr3[0].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(1, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[1].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(2, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[2].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(3, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[3].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(4, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        else if (btnArrStr3[4].equals(this.getText())) {
                            pals2 = this.changeLogignResultPals(-1, mainJpanel.getPalDataChooseId(), mainJpanel);
                        }
                        mainJpanel.getPartnerCardJpanel().getPartnerTeamJpanel().hideBtnArr();
                        mainJpanel.refreshPals(pals2);
                    }
                    else if (this.caozuo == 11) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getSummoning() != null) {
                                if (pidGetPal.getSummoning() != null) {
                                    ZhuFrame.getZhuJpanel().creatpettext(pidGetPal.getSummoning());
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有召唤兽请前往召唤兽面板添加！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 12) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getSummoning() != null) {
                                if (pidGetPal.getSummoning() != null) {
                                    if (Util.isCanBuyOrno()) {
                                        return;
                                    }
                                    PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                                    String name = palData.getName();
                                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palGetPet, pidGetPal, "#W确定要将伙伴" + name + "的召唤兽:#G" + pidGetPal.getSummoning().getSummoningname() + "#W取回吗?");
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有召唤兽！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 13) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getLingbao() != null) {
                                if (pidGetPal.getLingbao() != null) {
                                    ZhuFrame.getZhuJpanel().creatlingtext(pidGetPal.getLingbao());
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有装备灵宝请前往灵宝面板添加！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo == 14) {
                        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                        int chooseId = mainJpanel.getPalDataChooseId();
                        if (chooseId > 0) {
                            Pal pidGetPal = mainJpanel.pidGetPal(chooseId);
                            if (pidGetPal.getLingbao() != null) {
                                if (pidGetPal.getLingbao() != null) {
                                    if (Util.isCanBuyOrno()) {
                                        return;
                                    }
                                    PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                                    String name = palData.getName();
                                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palGetLingbao, pidGetPal, "#W确定要将伙伴" + name + "的灵宝:#G" + pidGetPal.getLingbao().getBaoname() + "#W取回吗?");
                                }
                            }
                            else {
                                ZhuFrame.getZhuJpanel().addPrompt2("伙伴还没有灵宝！");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
                        }
                    }
                    else if (this.caozuo >= 2024) {
                        int ix2 = 0;
                        if (PartnerSkillJpanel.AIdataList != null) {
                            for (String s2 : PartnerSkillJpanel.AIdataList) {
                                if (s2 != null && !s2.equals("")) {
                                    ++ix2;
                                }
                            }
                        }
                        if (this.caozuo - 2024 > ix2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请先设置上一个回合的技能");
                            return;
                        }
                        int ys2 = (this.caozuo - 2024) * 30;
                        PartnerSkillJpanel.x = this.caozuo - 2024;
                        for (int i = 0; i < 5; ++i) {
                            PartnerSkillJpanel.labSkillName[i].setFont(new Font("楷体", 1, 17));
                            PartnerSkillJpanel.labSkillName[i].setForeground(UIUtils.COLOR_WHITE[0]);
                            PartnerSkillJpanel.labSkillName[i].setBounds(80, ys2 + i * 22, 72, 25);
                            PartnerSkillJpanel.labSkillName[i].setVisible(true);
                        }
                        if (e.getButton() == 3) {
                            for (int i = 0; i < 5; ++i) {
                                PartnerSkillJpanel.labSkillName[i].setVisible(false);
                            }
                        }
                    }
                }
            }
            catch (Exception var11) {
                var11.printStackTrace();
            }
        }
    }
    
    public List<Pal> changeLogignResultPals(int type, int pid, PartnerMainJpanel mainJpanel) {
        List<Pal> palsList = new ArrayList<>();
        String pals = RoleData.getRoleData().getLoginResult().getPals();
        StringBuffer mesBuf = new StringBuffer();
        mesBuf.append("P");
        Pal pidGetPal = mainJpanel.pidGetPal(pid);
        BigDecimal id = pidGetPal.getId();
        mesBuf.append(id);
        palsList.add(mainJpanel.idGetPal(id));
        if (pals != null && !"".equals(pals)) {
            StringBuffer buffer = new StringBuffer();
            String[] palsArr = pals.split("\\|");
            if (palsArr.length < type) {
                for (int i = 0; i < palsArr.length; ++i) {
                    if (!palsArr[i].equals("" + id)) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(palsArr[i]);
                        palsList.add(mainJpanel.idGetPal(new BigDecimal(palsArr[i])));
                    }
                }
                buffer.append("|");
                buffer.append(id);
            }
            else {
                String lastPid = (type > 0) ? palsArr[type - 1] : null;
                if (lastPid != null) {
                    palsList.add(mainJpanel.idGetPal(new BigDecimal(lastPid)));
                }
                mesBuf.append("|");
                mesBuf.append((type > 0) ? (type - 1) : -1);
                for (int j = 0; j < palsArr.length; ++j) {
                    if (palsArr[j].equals("" + id)) {
                        palsArr[j] = lastPid;
                    }
                    else if (type - 1 == j) {
                        palsArr[j] = "" + id;
                    }
                }
                for (int j = 0; j < palsArr.length; ++j) {
                    if (palsArr[j] != null && palsArr[j] != "" && !palsArr[j].isEmpty()) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(palsArr[j]);
                        palsList.add(mainJpanel.idGetPal(new BigDecimal(palsArr[j])));
                    }
                }
            }
            RoleData.getRoleData().getLoginResult().setPals(buffer.toString());
        }
        else {
            RoleData.getRoleData().getLoginResult().setPals("" + id);
        }
        String sendmes = Agreement.getAgreement().userpalAgreement(mesBuf.toString());
        SendMessageUntil.toServer(sendmes);
        return palsList;
    }
    
    public void activatePal() {
        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
        int chooseId = mainJpanel.getPalDataChooseId();
        if (chooseId < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
        }
        else {
            PalData palData = UserMessUntil.getPalData(chooseId);
            if (palData == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选中一个伙伴");
            }
            else {
                String xh = palData.getXh();
                StringBuffer buffer = new StringBuffer();
                buffer.append("#W确定要激活#G");
                buffer.append(palData.getName());
                if (xh != null && !"".equals(xh)) {
                    buffer.append("#W吗?消耗:#R");
                    buffer.append(xh.substring(1, xh.length()));
                    if (xh.startsWith("D")) {
                        buffer.append("大话币#W。");
                    }
                    else if (xh.startsWith("X")) {
                        buffer.append("仙玉#W。");
                    }
                }
                else {
                    buffer.append("吗?消耗:#R无消耗#W。");
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.PalKey, palData, buffer.toString());
            }
        }
    }
}
