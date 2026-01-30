package com.tool.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.Jpanel.TransferJpanel;
import org.come.Jpanel.JadeUpJpanel;
import javax.swing.Icon;
import org.come.entity.Goodstable;
import org.come.Jpanel.WashJpanel;
import org.come.Jpanel.SynthesisJpanel;
import org.come.Jpanel.UpgradeJpanel;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.WorkshopRefiningCardJpanel;
import org.come.Jpanel.SetsynthesisJpanel;
import org.come.Jpanel.WorkshopRefiningJpanel;

public class WorkshopRefiningBtn extends MoBanBtn
{
    private int caozuo;
    private WorkshopRefiningJpanel refiningJpanel;
    private SetsynthesisJpanel setsynthesisJpanel;
    private WorkshopRefiningCardJpanel cardJpanel;
    private WorkshopRefiningJpanel finingJpanel;
    
    public WorkshopRefiningBtn(String iconpath, int type, int caozuo, WorkshopRefiningJpanel refiningJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.refiningJpanel = refiningJpanel;
    }
    
    public WorkshopRefiningBtn(String iconpath, int type, int caozuo, WorkshopRefiningCardJpanel cardJpanel, WorkshopRefiningJpanel finingJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.cardJpanel = cardJpanel;
        this.finingJpanel = finingJpanel;
    }
    
    public WorkshopRefiningBtn(String iconpath, int type, int caozuo, SetsynthesisJpanel setsynthesisJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setsynthesisJpanel = setsynthesisJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                switch (this.caozuo) {
                    case 1: {
                        this.finingJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("inkImg/button1/K30.png"));
                        if (!configure.getLzjskg().equals("3")) {
                            this.finingJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("inkImg/button1/K31.png"));
                            this.finingJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("inkImg/button1/K33.png"));
                            this.finingJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K35.png"));
                            if (configure.getBsqhkg().equals("开")) {
                                this.finingJpanel.getBtnGemRefinery().setIcons(CutButtonImage.cuts("inkImg/button1/K47.png"));
                            }
                            this.cardJpanel.getEquiJpanel().changeFrom(this.caozuo);
                            this.cardJpanel.getCar().show(this.cardJpanel, "l1");
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        this.finingJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("inkImg/button1/K29.png"));
                        if (!configure.getLzjskg().equals("3")) {
                            this.finingJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("inkImg/button1/K32.png"));
                            this.finingJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("inkImg/button1/K33.png"));
                            this.finingJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K35.png"));
                            if (configure.getBsqhkg().equals("开")) {
                                this.finingJpanel.getBtnGemRefinery().setIcons(CutButtonImage.cuts("inkImg/button1/K47.png"));
                            }
                            this.cardJpanel.getEquiJpanel().changeFrom(this.caozuo);
                            this.cardJpanel.getCar().show(this.cardJpanel, "l1");
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        this.finingJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("inkImg/button1/K29.png"));
                        if (!configure.getLzjskg().equals("3")) {
                            this.finingJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("inkImg/button1/K31.png"));
                            this.finingJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("inkImg/button1/K34.png"));
                            this.finingJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K35.png"));
                            if (configure.getBsqhkg().equals("开")) {
                                this.finingJpanel.getBtnGemRefinery().setIcons(CutButtonImage.cuts("inkImg/button1/K47.png"));
                            }
                            this.cardJpanel.getEquiJpanel().changeFrom(this.caozuo);
                            this.cardJpanel.getCar().show(this.cardJpanel, "l3");
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        this.finingJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("inkImg/button1/K29.png"));
                        if (!configure.getLzjskg().equals("3")) {
                            this.finingJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("inkImg/button1/K31.png"));
                            this.finingJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("inkImg/button1/K33.png"));
                            this.finingJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K36.png"));
                            if (configure.getBsqhkg().equals("开")) {
                                this.finingJpanel.getBtnGemRefinery().setIcons(CutButtonImage.cuts("inkImg/button1/K47.png"));
                            }
                            this.cardJpanel.getCar().show(this.cardJpanel, "l4");
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        this.finingJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("inkImg/button1/K29.png"));
                        if (!configure.getLzjskg().equals("3")) {
                            this.finingJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("inkImg/button1/K31.png"));
                            this.finingJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("inkImg/button1/K33.png"));
                            this.finingJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K35.png"));
                            if (configure.getBsqhkg().equals("开")) {
                                this.finingJpanel.getBtnGemRefinery().setIcons(CutButtonImage.cuts("inkImg/button1/K48.png"));
                            }
                            this.cardJpanel.getCar().show(this.cardJpanel, "l5");
                            this.cardJpanel.getGemRefineryMainJpanel().initView(-1);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 10: {
                        UpgradeJpanel.clearInterface();
                        this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K38.png"));
                        this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("inkImg/button1/K39.png"));
                        this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("inkImg/button1/K41.png"));
                        this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("inkImg/button1/K43.png"));
                        this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("inkImg/button1/K45.png"));
                        this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l1");
                        break;
                    }
                    case 11: {
                        UpgradeJpanel.clearInterface();
                        this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("inkImg/button/B100.png"));
                        this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("inkImg/button/B105.png"));
                        this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("inkImg/button/B102.png"));
                        this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("inkImg/button/B106.png"));
                        this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("inkImg/button/B98.png"));
                        this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l2");
                        break;
                    }
                    case 12: {
                        UpgradeJpanel.clearInterface();
                        this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K37.png"));
                        this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("inkImg/button1/K39.png"));
                        this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("inkImg/button1/K42.png"));
                        this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("inkImg/button1/K43.png"));
                        this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("inkImg/button1/K45.png"));
                        this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l3");
                        break;
                    }
                    case 13: {
                        UpgradeJpanel.clearInterface();
                        this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K37.png"));
                        this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("inkImg/button1/K39.png"));
                        this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("inkImg/button1/K41.png"));
                        this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("inkImg/button1/K44.png"));
                        this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("inkImg/button1/K45.png"));
                        UpgradeJpanel.setGoodstable(null);
                        UpgradeJpanel.getLabtz2().setIcon(null);
                        this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l4");
                        break;
                    }
                    case 14: {
                        UpgradeJpanel.clearInterface();
                        this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("inkImg/button1/K37.png"));
                        this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("inkImg/button1/K39.png"));
                        this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("inkImg/button1/K41.png"));
                        this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("inkImg/button1/K43.png"));
                        this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("inkImg/button1/K46.png"));
                        this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l5");
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
                if (this.caozuo == 1) {
                    this.refiningJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("img/xy2uiimg/refinEquipMenu.png"));
                    if (!configure.getLzjskg().equals("3")) {
                        this.refiningJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinAccMenu.png"));
                        if (configure.getBsqhkg().equals("开")) {
                            this.refiningJpanel.getBtnMenuGem().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemMenu.png"));
                        }
                        this.refiningJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinWareMenu.png"));
                        this.refiningJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/unSuitComMenu.png"));
                        this.refiningJpanel.getCardJpanel().getCar().show(this.refiningJpanel.getCardJpanel(), "l1");
                        this.refiningJpanel.getCardJpanel().getEquiJpanel().changeFrom(this.caozuo);
                    }
                }
                else if (this.caozuo == 2) {
                    this.refiningJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinEquipMenu.png"));
                    if (!configure.getLzjskg().equals("3")) {
                        this.refiningJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("img/xy2uiimg/refinAccMenu.png"));
                        this.refiningJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinWareMenu.png"));
                        this.refiningJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/unSuitComMenu.png"));
                        if (configure.getBsqhkg().equals("开")) {
                            this.refiningJpanel.getBtnMenuGem().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemMenu.png"));
                        }
                        this.refiningJpanel.getCardJpanel().getCar().show(this.refiningJpanel.getCardJpanel(), "l1");
                        this.refiningJpanel.getCardJpanel().getEquiJpanel().changeFrom(this.caozuo);
                    }
                }
                else if (this.caozuo == 3) {
                    this.refiningJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinEquipMenu.png"));
                    if (!configure.getLzjskg().equals("3")) {
                        this.refiningJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinAccMenu.png"));
                        this.refiningJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("img/xy2uiimg/refinWareMenu.png"));
                        this.refiningJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/unSuitComMenu.png"));
                        if (configure.getBsqhkg().equals("开")) {
                            this.refiningJpanel.getBtnMenuGem().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemMenu.png"));
                        }
                        this.refiningJpanel.getCardJpanel().getEquiJpanel().changeFrom(this.caozuo);
                        this.refiningJpanel.getCardJpanel().getCar().show(this.refiningJpanel.getCardJpanel(), "l3");
                    }
                }
                else if (this.caozuo == 4) {
                    this.refiningJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinEquipMenu.png"));
                    if (!configure.getLzjskg().equals("3")) {
                        this.refiningJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinAccMenu.png"));
                        this.refiningJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinWareMenu.png"));
                        this.refiningJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/suitComMenu.png"));
                        if (configure.getBsqhkg().equals("开")) {
                            this.refiningJpanel.getBtnMenuGem().setIcons(CutButtonImage.cuts("img/xy2uiimg/unGemMenu.png"));
                        }
                        this.refiningJpanel.getCardJpanel().getCar().show(this.refiningJpanel.getCardJpanel(), "l4");
                    }
                }
                else if (this.caozuo == 5) {
                    this.refiningJpanel.getBtnEqui().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinEquipMenu.png"));
                    if (!configure.getLzjskg().equals("3")) {
                        this.refiningJpanel.getBtnAcc().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinAccMenu.png"));
                        this.refiningJpanel.getBtnRefiners().setIcons(CutButtonImage.cuts("img/xy2uiimg/unRefinWareMenu.png"));
                        this.refiningJpanel.getBtnSetsynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/unSuitComMenu.png"));
                        if (configure.getBsqhkg().equals("开")) {
                            this.refiningJpanel.getBtnMenuGem().setIcons(CutButtonImage.cuts("img/xy2uiimg/gemMenu.png"));
                        }
                        this.refiningJpanel.getCardJpanel().getCar().show(this.refiningJpanel.getCardJpanel(), "l5");
                        this.refiningJpanel.getCardJpanel().getGemRefineryMainJpanel().initView(-1);
                    }
                }
                else if (this.caozuo == 10) {
                    SynthesisJpanel.clearInterface();
                    this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l1");
                    this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_1.png"));
                    this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_2.png"));
                    this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_4.png"));
                    this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_6.png"));
                    this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_8.png"));
                }
                else if (this.caozuo == 11) {
                    WashJpanel.clearInterface();
                    this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l2");
                    this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                    this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_3.png"));
                    this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_4.png"));
                    this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_6.png"));
                    this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_8.png"));
                }
                else if (this.caozuo == 12) {
                    UpgradeJpanel.clearInterface();
                    UpgradeJpanel.setGoodstable((Goodstable)null);
                    UpgradeJpanel.getLabtz2().setIcon((Icon)null);
                    this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l3");
                    this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                    this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_2.png"));
                    this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_5.png"));
                    this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_6.png"));
                    this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_8.png"));
                }
                else if (this.caozuo == 13) {
                    JadeUpJpanel.clearInterface();
                    this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l4");
                    this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                    this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_2.png"));
                    this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_4.png"));
                    this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_7.png"));
                    this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_8.png"));
                }
                else if (this.caozuo == 14) {
                    TransferJpanel.clearInterface();
                    this.setsynthesisJpanel.getCardJpanel().getCar().show(this.setsynthesisJpanel.getCardJpanel(), "l5");
                    this.setsynthesisJpanel.getLabSynthesis().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_0.png"));
                    this.setsynthesisJpanel.getLabWash().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_2.png"));
                    this.setsynthesisJpanel.getLabUpgrade().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_4.png"));
                    this.setsynthesisJpanel.getLabJadeUp().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_6.png"));
                    this.setsynthesisJpanel.getLabTransfer().setIcons(CutButtonImage.cuts("img/xy2uiimg/setsynthesis_9.png"));
                }
            }
            catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }
}
