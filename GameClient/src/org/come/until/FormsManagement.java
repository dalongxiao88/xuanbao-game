package org.come.until;

import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.ArrayList;
import org.come.annex.Tournaments.Frame.TournamentsJframe;
import org.come.annex.Tournaments.Frame.TournamentsResultsJframe;
import org.come.annex.Tournaments.Frame.TournamentsTheJframe;

import com.tool.fuben.FuBenJframe;
import com.tool.role.RoleProperty;
import org.come.Frame.*;
import org.come.MountShouHu.RandFJframe;
import org.come.MountShouHu.xuanzeJframe;
import org.come.Frame.spot.InputMoneyJframe;
import org.come.Frame.spot.SpotBuyBoxJframe;
import org.come.Frame.spot.SpotBoxJframe;
import org.come.XuanBao.*;
import org.come.view.TaskTCJframe;
import org.come.equipmentSwitching.EquipmenSwitchingIputJframe;
import org.come.equipmentSwitching.EquipmentSwitchingJframe;
import org.come.lianhua.AutoMaticRefiningJframe;
import org.come.action.MsgJframe2;
import org.come.tt.LadderLotteryJframe;
import org.come.tt.EventSelectionJframe;
import org.come.tt.LadderJframe;

import java.io.IOException;

import org.skill.frame.FaMenXlFrame;
import org.skill.frame.LXiulianMainFrame;
import org.come.xingpan.JframeXingBackMain;
import org.come.xingpan.StarSoulRefinedJframe;
import org.come.xingpan.JframeXingCardMain;
import org.come.strength.JframeStrengthMain;
import org.come.starcard.JframeStarTransferMain;
import org.come.starcard.JframeSoulBackMain;
import org.come.starcard.JframeStarCardMain;
import org.come.summonequip.JframeReclaimSkillMain;
import org.come.summonequip.JframeHelpMain;
import org.come.summonequip.JframeCashRewardsMain;
import org.come.summonequip.JframeSummonEquipMain;
import org.lottery.frame.LotteryIntegralMainJframe;
import org.lottery.frame.LotteryMainFrame;
import org.wing.panel.LHMainFrame;
import org.wing.panel.WingMainFrame;
import org.gemstone.panel.GemstoneOperationMainFrame;
import org.skill.frame.SkillPromoteMainFrame;
import org.skill.frame.SkillMainFrame;
import org.soaring.panel.SoaringMainFrame;
import org.cbg.frame.TraslationCommodityJFrame;
import org.cbg.frame.TrslationMainJframe;
import org.come.Jpanel.GGJpanel;
import org.come.Jpanel.GameJpanel;
import org.come.MountShouHu.LvlupJframe;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.Jpanel.WorldMapJpanel;
import com.tool.image.ImageMixDeal;

import javax.swing.JInternalFrame;
import java.awt.Cursor;
import java.util.Map;

import org.come.model.InternalForm;

import java.util.List;

public class FormsManagement {
    private static List<InternalForm> Forms;
    public static Map<Integer, Integer> map;
    private static Cursor cursor;
    private static InternalForm Privilege;
    private static String xdt1103;

    private static InternalForm getPrivilege() {
        if (FormsManagement.Privilege == null) {
            FormsManagement.Privilege = new InternalForm(-1, new FormMaskJFrame(), -1);
        }
        return FormsManagement.Privilege;
    }

    public static JInternalFrame getframe(int bh) {
        return getInternalForm(bh).getFrame();
    }

    public static void showForm(int bh) {
        if (bh != 603 && bh != 631 && bh != 633)
            System.err.println("Bh" + bh);
        if (bh == 22 && FormsManagement.xdt1103 == "true") {
            FormsManagement.xdt1103 = "false";
            WorldMapJpanel.loadMapTP(Integer.parseInt(ImageMixDeal.userimg.getRoleShow().getMapid() + ""), ImageMixDeal.userimg.getRoleShow().getX(), ImageMixDeal.userimg.getRoleShow().getY());
        }
        if (bh == 1103) {
            FormsManagement.xdt1103 = "true";
        }
//        if (bh==58) {
//            InternalForm form = getInternalForm2(711);
//            if (form != null && form.HideForm()) {
//                HidePrivilege();
//                return;
//            }
//        }
        InternalForm form = getInternalForm(bh);
        Switchinglevel(form);
        ZhuFrame.getZhuJpanel().getSendMes().requestFocus();
    }

    public static void HideForm(int bh) {
        if (bh == 1103) {
            WorldMapJpanel.loadMap(Integer.parseInt(ImageMixDeal.userimg.getRoleShow().getMapid() + ""), ImageMixDeal.userimg.getRoleShow().getX(), ImageMixDeal.userimg.getRoleShow().getY());
        }
        if (bh == 40) {//修复手指
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }

//        if (bh==711) {
//            InternalForm form = getInternalForm2(58);
//            if (form != null && form.HideForm()) {
//                HidePrivilege();
//            }
//            form = getInternalForm2(711);
//            if (form != null && form.HideForm()) {
//                HidePrivilege();
//            }
//        }
        if (bh == 2256) {
            ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong1(-1);
            ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong2(-1);
        }
        if (bh == 559) {
            MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getSixiang1().setVisible(false);
            MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getSixiang2().setVisible(false);
        }
        if (bh == 2258) {
            LvlupJframe.getLvlupJframe().getLvlupJpanel().setType(true);
        }
        if (bh == 8)
            RoleProperty.xian.clear();
        InternalForm form = getInternalForm2(bh);
        if (form != null && form.HideForm()) {
            HidePrivilege();
        }
    }

    public static void disposeForm(int bh) {
        InternalForm form = getInternalForm(bh);
        GameJpanel.getGameJpanel().remove(form.getFrame());
        form.getFrame().dispose();
        FormsManagement.Forms.remove(form);
        FormsManagement.map.clear();
        for (int i = FormsManagement.Forms.size() - 1; i >= 0; --i) {
            FormsManagement.map.put(FormsManagement.Forms.get(i).getFormid(), i);
        }
        if (form.getFormlvl() == 3 || form.getFormlvl() == 6) {
            HidePrivilege();
        }
        form = null;
    }

    public static void Switchinglevel(int bh) {
        InternalForm form = getInternalForm2(bh);
        if (form != null) {
            Switchinglevel(form);
        }
    }

    public static void Switchinglevel(InternalForm form) {
        if (form.getFormlvl() == 3 || form.getFormlvl() == 6) {
            InternalForm pForm = getPrivilege();
            ((FormMaskJFrame) pForm.getFrame()).change(ScrenceUntil.ChatFram_X + ScrenceUntil.Screen_x, ScrenceUntil.Screen_y);
            GameJpanel.getGameJpanel().add(pForm.getFrame(), 1);
            pForm.getFrame().setVisible(true);
        }
        GameJpanel.getGameJpanel().add(form.getFrame(), 1);
        form.getFrame().setVisible(true);
    }

    public static void upgradForm(int bh) {
        getInternalForm(bh).setFormlvl(6);
        showForm(bh);
    }

    public static void HidePrivilege() {
        getPrivilege().HideForm();
    }

    public static InternalForm getInternalForm(int bh) {
        Integer num = FormsManagement.map.get(bh);
        if (num != null) {
            return FormsManagement.Forms.get(num);
        }
        InternalForm form = FormLoad(bh);
        form.getFrame().setCursor(FormsManagement.cursor);
        FormsManagement.Forms.add(form);
        FormsManagement.map.put(form.getFormid(), FormsManagement.Forms.size() - 1);
        return form;
    }

    public static InternalForm getInternalFormsx(int bh) {
        InternalForm form = FormLoad(bh);
        form.getFrame().setCursor(FormsManagement.cursor);
        FormsManagement.Forms.add(form);
        FormsManagement.map.put(form.getFormid(), FormsManagement.Forms.size() - 1);
        return form;
    }

    public static InternalForm getInternalForm2(int bh) {
        Integer num = FormsManagement.map.get(bh);
        if (num != null) {
            return FormsManagement.Forms.get((int) num);
        }
        return null;
    }

    public static InternalForm getInternalForm3(int bh) {
        Integer num = FormsManagement.map.get(bh);
        if (num != null) {
            InternalForm form = FormsManagement.Forms.get((int) num);
            if (form.getFrame().isVisible()) {
                return form;
            }
        }
        return null;
    }

    public static void HiddenDisplay(int formsid) {
        if (getInternalForm(formsid).getFrame().isVisible()) {
            HideForm(formsid);
        } else {
            showForm(formsid);
        }
    }

    public static InternalForm FormLoad(int bh) {
        //System.out.println("hb---->:"+bh);
        InternalForm form = null;
        try {
            switch (bh) {
                case 0: {
                    form = jframe0();
                    break;
                }
                case 1: {
                    form = jframe1();
                    break;
                }
                case 2: {
                    form = jframe2();
                    break;
                }
                case 3: {
                    form = jframe3();
                    break;
                }
                case 4: {
                    form = jframe4();
                    break;
                }
                case 5: {
                    form = jframe5();
                    break;
                }
                case 6: {
                    form = jframe6();
                    break;
                }
                case 7: {
                    form = jframe7();
                    break;
                }
                case 1002: {
                    form = jframe1002();
                    break;
                }

                case 8: {
                    form = jframe8();
                    break;
                }
                case 9: {
                    form = jframe9();
                    break;
                }
                case 10: {
                    form = jframe10();
                    break;
                }
                case 11: {
                    form = jframe11();
                    break;
                }
                case 12: {
                    form = jframe12();
                    break;
                }
                case 13: {
                    form = jframe13();
                    break;
                }
                case 188888: {
                    form = jframe188888();
                    break;
                }
                case 178888: {
                    form = spiritualJframe();
                    break;

                }
                case 14: {
                    form = jframe14();
                    break;
                }
                case 15: {
                    form = jframe15();
                    break;
                }
                case 16: {
                    form = jframe16();
                    break;
                }
                case 17: {
                    form = jframe17();
                    break;
                }
                case 18: {
                    form = jframe18();
                    break;
                }
                case 19: {
                    form = jframe19();
                    break;
                }
                case 20: {
                    form = jframe20();
                    break;
                }
                case 21: {
                    form = jframe21();
                    break;
                }
                case 22: {
                    form = jframe22();
                    break;
                }
                case 23: {
                    form = jframe23();
                    break;
                }
                case 24: {
                    form = jframe24();
                    break;
                }
                case 25: {
                    form = jframe25();
                    break;
                }
                case 26: {
                    form = jframe26();
                    break;
                }
                case 27: {
                    form = jframe27();
                    break;
                }
                case 28: {
                    form = jframe28();
                    break;
                }
                case 29: {
                    form = jframe29();
                    break;
                }
                case 30: {
                    form = jframe30();
                    break;
                }
                case 31: {
                    form = jframe31();
                    break;
                }
                case 32: {
                    form = jframe32();
                    break;
                }
                case 33: {
                    form = jframe33();
                    break;
                }
                case 34: {
                    form = jframe34();
                    break;
                }
                case 35: {
                    form = jframe35();
                    break;
                }
                case 36: {
                    form = jframe36();
                    break;
                }
                case 700: {
                    form = jframe();
                    break;
                }
                case 37: {
                    form = jframe37();
                    break;
                }
                case 38: {
                    form = jframe38();
                    break;
                }
                case 39: {
                    form = jframe39();
                    break;
                }
                case 40: {
                    form = jframe40();
                    break;
                }
                case 41: {
                    form = jframe41();
                    break;
                }
                case 42: {
                    form = jframe42();
                    break;
                }
                case 43: {
                    form = jframe43();
                    break;
                }
                case 44: {
                    form = jframe44();
                    break;
                }
                case 45: {
                    form = jframe45();
                    break;
                }
                case 46: {
                    form = jframe46();
                    break;
                }
                case 47: {
                    form = jframe47();
                    break;
                }
                case 48: {
                    form = jframe48();
                    break;
                }
                case 49: {
                    form = jframe49();
                    break;
                }
                case 50: {
                    form = jframe50();
                    break;
                }
                case 51: {
                    form = jframe51();
                    break;
                }
                case 52: {
                    form = jframe52();
                    break;
                }
                case 53: {
                    form = jframe53();
                    break;
                }
                case 54: {
                    form = jframe54();
                    break;
                }
                case 55: {
                    form = jframe55();
                    break;
                }
                case 1000: {
                    form = jframe1000();
                    break;
                }
                case 1001: {
                    form = jframe1001();
                    break;
                }
                case 56: {
                    form = jframe56();
                    break;
                }
                case 57: {
                    form = jframe57();
                    break;
                }
                case 58: {
                    form = jframe58();
                    break;
                }
                case 59: {
                    form = jframe59();
                    break;
                }
                case 60: {
                    form = jframe60();
                    break;
                }
                case 61: {
                    form = jframe61();
                    break;
                }
                case 62: {
                    form = jframe62();
                    break;
                }
                case 621: {
                    form = jframe621();
                    break;
                }
                case 622: {
                    form = jframe622();
                    break;
                }
                case 63: {
                    form = jframe63();
                    break;
                }
                case 64: {
                    form = jframe64();
                    break;
                }
                case 65: {
                    form = jframe65();
                    break;
                }
                case 66: {
                    form = jframe66();
                    break;
                }
                case 67: {
                    form = jframe67();
                    break;
                }
                case 68: {
                    form = jframe68();
                    break;
                }
                case 69: {
                    form = jframe69();
                    break;
                }
                case 70: {
                    form = jframe70();
                    break;
                }
                case 71: {
                    form = jframe71();
                    break;
                }
                case 72: {
                    form = jframe72();
                    break;
                }
                case 73: {
                    form = jframe73();
                    break;
                }
                case 74: {
                    form = jframe74();
                    break;
                }
                case 75: {
                    form = jframe75();
                    break;
                }
                case 76: {
                    form = jframe76();
                    break;
                }
                case 77: {
                    form = jframe77();
                    break;
                }
                case 78: {
                    form = jframe78();
                    break;
                }
                case 79: {
                    form = jframe79();
                    break;
                }
                case 80: {
                    form = jframe80();
                    break;
                }
                case 81: {
                    form = jframe81();
                    break;
                }
                case 82: {
                    form = jframe82();
                    break;
                }
                case 83: {
                    form = jframe83();
                    break;
                }
                case 84: {
                    form = jframe84();
                    break;
                }
                case 85: {
                    form = jframe85();
                    break;
                }
                case 86: {
                    form = jframe86();
                    break;
                }
                case 866: {
                    form = jframe866();
                    break;
                }
                case 87: {
                    form = jframe87();
                    break;
                }
                case 88: {
                    form = jframe88();
                    break;
                }
                case 89: {
                    form = jframe89();
                    break;
                }
                case 90: {
                    form = jframe90();
                    break;
                }
                case 900: {
                    form = jframe900();
                    break;
                }
                case 901: {
                    form = jframe901();
                    break;
                }
                case 91: {
                    form = jframe91();
                    break;
                }
                case 92: {
                    form = jframe92();
                    break;
                }
                case 93: {
                    form = jframe93();
                    break;
                }
                case 94: {
                    form = jframe94();
                    break;
                }
                case 95: {
                    form = jframe95();
                    break;
                }
                case 96: {
                    form = jframe96();
                    break;
                }
                case 97: {
                    form = jframe97();
                    break;
                }
                case 98: {
                    form = jframe98();
                    break;
                }
                case 99: {
                    form = jframe99();
                    break;
                }
                case 100: {
                    form = jframe100();
                    break;
                }
                case 101: {
                    form = jframe101();
                    break;
                }
                case 102: {
                    form = jframe102();
                    break;
                }
                case 103: {
                    form = jframe103();
                    break;
                }
                case 104: {
                    form = jframe104();
                    break;
                }
                case 105: {
                    form = jframe105();
                    break;
                }
                case 106: {
                    form = jframe106();
                    break;
                }
                case 107: {
                    form = jframe107();
                    break;
                }
                case 108: {
                    form = jframe108();
                    break;
                }
                case 109: {
                    form = jframe109();
                    break;
                }
                case 110: {
                    form = jframe110();
                    break;
                }
                case 111: {
                    form = jframe111();
                    break;
                }
                case 112: {
                    form = jframe112();
                    break;
                }
                case 113: {
                    form = jframe113();
                    break;
                }
                case 120: {
                    form = jframe120();
                    break;
                }
                case 121: {
                    form = jframe121();
                    break;
                }
                case 122: {
                    form = jframe122();
                    break;
                }
                case 123: {
                    form = jframe123();
                    break;
                }
                case 127: {
                    form = jframe127();
                    break;
                }
                case 128: {
                    form = jframe128();
                    break;
                }
                case 1234: {
                    form = jframe1234();
                    break;
                }
                case 160: {
                    form = jframe160();
                    break;
                }
                case 555: {
                    form = jframe555();
                    break;
                }
                case 600: {
                    form = jframe600();
                    break;
                }
                case 601: {
                    form = jframe601();
                    break;
                }
                case 602: {
                    form = jframe602();
                    break;
                }
                case 6022: {
                    form = jframe6022();
                    break;
                }
                case 289557289: {
                    form = jframe289557289();
                    break;
                }
                case 1100: {
                    form = jframe1100();
                    break;
                }
                case 1101: {
                    form = jframe1101();
                    break;
                }
                case 1102: {
                    form = jframe1102();
                    break;
                }
                case 1103: {
                    form = jframe1103();
                    break;
                }
                case 1104: {
                    form = jframe1104();
                    break;
                }
                case 1105: {
                    form = jframe1105();
                    break;
                }
                case 2000: {
                    form = jframe2000();
                    break;
                }
                case 3000: {
                    form = jframe3000();
                    break;
                }
                case 3001: {
                    form = jframe3001();
                    break;
                }
                case 3002: {
                    form = jframe3002();
                    break;
                }
                case 3003: {
                    form = jframe3003();
                    break;
                }
                case 3004: {
                    form = jframe3004();
                    break;
                }
                case 3005: {
                    form = jframe3005();
                    break;
                }
                case 3111: {
                    form = jframe3111();
                    break;
                }
                case 119: {
                    form = jframe119();
                    break;
                }
                case 604: {
                    form = jframe604();
                    break;
                }
                case 605: {
                    form = jframe605();
                    break;
                }
                case 606: {
                    form = jframe606();
                    break;
                }
                case 631: {
                    form = jframe631();
                    break;
                }
                case 603: {
                    form = jframe603();
                    break;
                }
                case 289: {
                    form = jframe289();
                    break;
                }
                case 634: {
                    form = jframe634();
                    break;
                }
                case 114: {
                    form = jframe114();
                    break;
                }
                case 63333: {
                    form = depositJframe();
                    break;
                }
                case 635: {
                    form = jframe635();
                    break;
                }
                case 124: {
                    form = jframe124();
                    break;
                }
                case 125: {
                    form = jframe125();
                    break;
                }
                case 126: {
                    form = jframe126();
                    break;
                }
                case 628: {
                    form = jframe628();
                    break;
                }
                case 633: {
                    form = jframe633();
                    break;
                }
                case 636: {
                    form = jframe636();
                    break;
                }
                case 637: {
                    form = jframe637();
                    break;
                }
                case 638: {
                    form = jframe638();
                    break;
                }
                case 640: {
                    form = jframe640();
                    break;
                }
                case 641: {
                    form = jframe641();
                    break;
                }
                case 642: {
                    form = jframe642();
                    break;
                }
                case 643: {
                    form = jframe643();
                    break;
                }
                case 703: {
                    form = jframe703();
                    break;
                }
                case 704: {
                    form = jframe704();
                    break;
                }
                case 705: {
                    form = jframe705();
                    break;
                }
                case 706: {
                    form = jframe706();
                    break;
                }
                case 707: {
                    form = jframe707();
                    break;
                }
                case 708: {
                    form = jframe708();
                    break;
                }
                case 709: {
                    form = jframe709();
                    break;
                }
                case 710: {
                    form = jframe710();
                    break;
                }
                case 711: {
                    form = jframe711();
                    break;
                }
                case 712: {
                    form = jframe712();
                    break;
                }
                case 713: {
                    form = jframe713();
                    break;
                }
                case 714: {
                    form = jframe714();
                    break;
                }
                case 999: {
                    form = jframe999();
                    break;
                }
                case 1119: {
                    form = AutoMaticRefiningJF();
                    break;
                }
                case 100000: {
                    form = EquipmentSwitchingJframe();
                    break;
                }
                case 100001: {
                    form = EquipmentSwitchingInputJframe();
                    break;
                }
                case 6222: {
                    form = jframe6222();
                    break;
                }
                case 800: {
                    form = jframe800();
                    break;
                }
                case 801: {
                    form = jframe801();
                    break;
                }
                case 802: {
                    form = jframe802();
                    break;
                }
                case 2255: {
                    form = jframe2255();
                    break;
                }
                case 2256: {
                    form = jframe2256();
                    break;
                }
                case 2257: {
                    form = jframe2257();
                    break;
                }
                case 2258: {
                    form = jframe2258();
                    break;
                }
                case 559: {
                    form = jframe559();
                    break;
                }
                case 911: {
                    form = jframe911();
                    break;
                }
                case 912: {
                    form = jframe912();
                    break;
                }
                case 1146:
                    form = jframe1146();
                    break;
                case 3072: {
                    form = jframe3072();
                    break;
                }
                case 3073: {
                    form = jframe3073();
                    break;
                }
                //   case 3015: {
                //      form = jframe3015();
                //      break;
                //  }
                case 3075: {
                    form = jframe3075();
                    break;
                }
                case 3081: {
                    form = jframe3081();
                    break;
                }
                case 3007: {
                    form = jframe3007();
                    break;
                }
                case 3079: {
                    form = jframe3079();
                    break;
                }
                case 3015://自选礼包
                    form = jframe3015();
                    break;
                case 60888: {
                    form = jframe60888();
                    break;
                }
                case 30001: {
                    form = jframe30001();
                    break;
                } case 8031: {
                    form = jframe8031();
                    break;
                }
                case 8032: {
                    form = jframe8032();
                    break;
                }
                case 8033: {
                    form = jframe8033();
                    break;
                }
                case 8034: {
                    form = jframe8034();
                    break;
                }
                case 8035: {
                    form = jframe8035();
                    break;
                }
                case 8036: {
                    form = jframe8036();
                    break;
                }
                case 8037: {
                    form = jframe8037();
                    break;
                }
                case 8038: {
                    form = jframe8038();
                    break;
                }
                case 6333:
                    form = msgfram6e();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return form;
    }

    public static InternalForm jframe3072() throws Exception {
        return new InternalForm(3072, new AchievemJframe(), 2);
    }

    public static InternalForm jframe3073() throws Exception {
        return new InternalForm(3073, new AchievemMinJframe(), 2);
    }

    //private static InternalForm jframe3015() throws IOException {
    //    return new InternalForm(3015, new MyOptionalJframe(), 2);
    // }
    public static InternalForm jframe3075() throws Exception {
        return new InternalForm(3075, new AnswerMinJframe(), 2);
    }

    public static InternalForm jframe3079() throws Exception {
        return new InternalForm(3079, new WestboundLevelJframe(), 2);
    }

    private static InternalForm jframe3015() throws IOException {
        return new InternalForm(3015, new MyOptionalJframe(), 2);
    }

    public static InternalForm msgfram6e() throws Exception {
        return new InternalForm(6333, new MsgJframe6(), 2);
    }

    public static InternalForm jframe0() throws Exception {
        return new InternalForm(0, new Teststatejframe(), 2);
    }

    public static InternalForm jframe1() throws Exception {
        return new InternalForm(1, new TestChildJframe(), 2);
    }

    public static InternalForm jframe2() throws Exception {
        return new InternalForm(2, new TestpackJframe(), 2);
    }

    public static InternalForm jframe3() throws Exception {
        return new InternalForm(3, new TesttaskJframe(), 2);
    }

    public static InternalForm jframe4() throws Exception {
        return new InternalForm(4, new TestfriendlistJframe(), 2);
    }

    public static InternalForm jframe5() throws Exception {
        return new InternalForm(5, new PartnerArenaJframe(), 2);
    }

    public static InternalForm jframe6() throws Exception {
        return new InternalForm(6, new TestPetJframe(), 2);
    }

    public static InternalForm jframe7() throws Exception {
        return new InternalForm(7, new MountJframe(), 2);
    }

    public static InternalForm jframe1002() throws Exception {
        return new InternalForm(1002, new CarJframe(), 2);
    }

    public static InternalForm jframe8() throws Exception {
        return new InternalForm(8, new RoleResistanceJframe(), 2);
    }

    public static InternalForm jframe9() throws Exception {
        return new InternalForm(9, new DuelBoardJframe(), 2);
    }

    public static InternalForm jframe10() throws Exception {
        return new InternalForm(10, new Change_titleJframe(), 2);
    }

    public static InternalForm jframe11() throws Exception {
        return new InternalForm(11, new NewRefiningJframe(), 2);
    }

    public static InternalForm jframe12() throws Exception {
        return new InternalForm(12, new GiveJframe(), 2);
    }

    public static InternalForm jframe13() throws Exception {
        return new InternalForm(13, new TeamJframe(), 2);
    }

    public static InternalForm jframe14() throws Exception {
        return new InternalForm(14, new TransJframe(), 2);
    }

    public static InternalForm jframe15() throws Exception {
        return new InternalForm(15, new TradeJframe(), 2);
    }

    public static InternalForm jframe16() throws Exception {
        return new InternalForm(16, new BoothBoxJframe(), 2);
    }

    public static InternalForm jframe17() throws Exception {
        return new InternalForm(17, new AlchemyJframe(), 2);
    }

    public static InternalForm jframe18() throws Exception {
        return new InternalForm(18, new PetSkillsJframe(), 2);
    }

    public static InternalForm jframe19() throws Exception {
        return new InternalForm(19, new TeamPostMessageJframe(), 2);
    }

    public static InternalForm jframe20() throws Exception {
        return new InternalForm(20, new MountSkillsJframe(), 2);
    }

    public static InternalForm jframe21() throws Exception {
        return new InternalForm(21, new ChangePasswordJframe(), 2);
    }

    public static InternalForm jframe22() throws Exception {
        return new InternalForm(22, new TestsmallmapJframe(), 2);
    }

    public static InternalForm jframe23() throws Exception {
        return new InternalForm(23, new ShoppingBuyJframe(), 2);
    }

    public static InternalForm jframe24() throws Exception {
        return new InternalForm(24, new GoodsMsgJframe(), 4);
    }

    public static InternalForm jframe25() throws Exception {
        return new InternalForm(25, new CreatBangJframe(), 2);
    }

    public static InternalForm jframe26() throws Exception {
        return new InternalForm(26, new ForgeJframe(), 2);
    }

    public static InternalForm jframe27() throws Exception {
        return new InternalForm(27, new NPCJfram(), 2);
    }

    public static InternalForm jframe28() throws Exception {
        return new InternalForm(28, new JoinBangJframe(), 2);
    }

    public static InternalForm jframe29() throws Exception {
        return new InternalForm(29, new PawnJfram(), 2);
    }

    public static InternalForm jframe30() throws Exception {
        return new InternalForm(30, new TeamApplyJframe(), 2);
    }

    public static InternalForm jframe31() throws Exception {
        return new InternalForm(31, new TeamCreateJframe(), 2);
    }

    public static InternalForm jframe32() throws Exception {
        return new InternalForm(32, new SetPassJfram(), 2);
    }

    public static InternalForm jframe33() throws Exception {
        return new InternalForm(33, new UnLockJframe(), 2);
    }

    public static InternalForm jframe34() throws Exception {
        return new InternalForm(34, new SkillMsgJframe(), 2);
    }

    public static InternalForm jframe35() throws Exception {
        return new InternalForm(35, new StallBuyJframe(), 2);
    }

    public static InternalForm jframe36() throws Exception {
        return new InternalForm(36, new SummoningCompoundJframe(), 2);
    }

    public static InternalForm jframe37() throws Exception {
        return new InternalForm(37, new ApointJframe(), 2);
    }

    public static InternalForm jframe38() throws Exception {
        return new InternalForm(38, new ImageDressJframe(), 2);
    }

    public static InternalForm jframe39() throws Exception {
        return new InternalForm(39, new TaobaoCourtMainJframe(), 2);
    }

    public static InternalForm jframe40() throws Exception {
        return new InternalForm(40, new ActivityJframe(), 2);
    }

    public static InternalForm jframe41() throws Exception {
        return new InternalForm(41, new RaceChangeMainJframe(), 2);
    }

    public static InternalForm jframe42() throws Exception {
        return new InternalForm(42, new PetsMsgJframe(230), 4);
    }

    public static InternalForm jframe43() throws Exception {
        return new InternalForm(43, new LingbaoJframe(), 2);
    }

    public static InternalForm jframe44() throws Exception {
        return new InternalForm(44, new GoodDetailedJframe(), 2);
    }

    public static InternalForm jframe45() throws Exception {
        return new InternalForm(45, new LingMsgJframe(), 4);
    }

    public static InternalForm jframe46() throws Exception {
        return new InternalForm(46, new MsgJframe(), 4);
    }

    public static InternalForm jframe47() throws Exception {
        return new InternalForm(47, new NedanJframe(), 2);
    }

    public static InternalForm jframe48() throws Exception {
        return new InternalForm(48, new FactionMainJframe(), 2);
    }

    public static InternalForm jframe49() throws Exception {
        return new InternalForm(49, new FactionDetailsJframe(), 2);
    }

    public static InternalForm jframe50() throws Exception {
        return new InternalForm(50, new TestSetupJframe(), 2);
    }

    public static InternalForm jframe51() throws Exception {
        return new InternalForm(51, new TryOntxJframe(), 2);
    }

    public static InternalForm jframe52() throws Exception {
        return new InternalForm(52, new SmallExpressionJframe(), 2);
    }

    public static InternalForm jframe53() throws Exception {
        return new InternalForm(53, new GangsGuardJframe(), 2);
    }

    public static InternalForm jframe54() throws Exception {
        return new InternalForm(54, new FactionAngelJframe(), 2);
    }

    public static InternalForm jframe55() throws Exception {
        return new InternalForm(55, new TakeBackPawnGoodsJframe(), 2);
    }

    public static InternalForm jframe1000() throws Exception {
        return new InternalForm(1000, new TakeBackPawnGoodsJframe1(), 2);
    }

    public static InternalForm jframe1001() throws Exception {
        return new InternalForm(1001, new TakeBackPawnGoodsJframe2(), 2);
    }

    public static InternalForm jframe56() throws Exception {
        return new InternalForm(56, new FriendChatMessageJframe(), 2);
    }

    public static InternalForm jframe57() throws Exception {
        return new InternalForm(57, new Insideforms(57, new GGJpanel(), ScrenceUntil.Screen_x / 4 - 50, ScrenceUntil.Screen_y / 2 - 280), 4);
    }

    public static InternalForm jframe58() throws Exception {
        return new InternalForm(58, new RolePetResistanceJframe(0), 2);
    }

    public static InternalForm jframe59() throws Exception {
        return new InternalForm(59, new RewardHallJframe(), 2);
    }

    public static InternalForm jframe60() throws Exception {
        return new InternalForm(60, new RankingListJframe(), 2);
    }

    public static InternalForm jframe61() throws Exception {
        return new InternalForm(61, new WorkshopRefiningJframe(), 2);
    }

    public static InternalForm jframe62() throws Exception {
        return new InternalForm(62, new SupportListJframe(), 2);
    }

    public static InternalForm jframe621() throws Exception {
        return new InternalForm(621, new PetPrderJframe(), 2);
    }

    public static InternalForm jframe622() throws Exception {
        return new InternalForm(622, new LingHelpListJframe(), 2);
    }

    public static InternalForm jframe63() throws Exception {
        return new InternalForm(63, new ExchangeValueJframe(), 2);
    }

    public static InternalForm jframe64() throws Exception {
        return new InternalForm(64, new CollectionJadeJframe(), 2);
    }

    public static InternalForm jframe65() throws Exception {
        return new InternalForm(65, new AlreadyRecordedJframe(), 2);
    }

    public static InternalForm jframe66() throws Exception {
        return new InternalForm(66, new PalacePKJframe(), 2);
    }

    public static InternalForm jframe67() throws Exception {
        return new InternalForm(67, new PetEquipmentJframe(), 2);
    }

    public static InternalForm jframe68() throws Exception {
        return new InternalForm(68, new RookieGuideJframe(), 2);
    }

    public static InternalForm jframe69() throws Exception {
        return new InternalForm(69, new AntiPluginJframe(), 2);
    }

    public static InternalForm jframe70() throws Exception {
        return new InternalForm(70, new RuneOperateJframe(), 2);
    }

    public static InternalForm jframe71() throws Exception {
        return new InternalForm(71, new QuackGameJframe(), 2);
    }

    public static InternalForm jframe72() throws Exception {
        return new InternalForm(72, new PlayRulesJframe(), 2);
    }

    public static InternalForm jframe73() throws Exception {
        return new InternalForm(73, new ExchangeAwardJframe(), 2);
    }

    public static InternalForm jframe74() throws Exception {
        return new InternalForm(74, new SuitBaptizeJframe(), 2);
    }

    public static InternalForm jframe75() throws Exception {
        return new InternalForm(75, new AddFriendJframe(), 2);
    }

    public static InternalForm jframe76() throws Exception {
        return new InternalForm(76, new FriendMsgJframe(), 2);
    }

    public static InternalForm jframe77() throws Exception {
        return new InternalForm(77, new RoleMsgJframe(), 2);
    }

    public static InternalForm jframe78() throws Exception {
        return new InternalForm(78, new TrslationMainJframe(), 2);
    }

    public static InternalForm jframe79() throws Exception {
        return new InternalForm(79, new TraslationCommodityJFrame(), 2);
    }

    public static InternalForm jframe80() throws Exception {
        return new InternalForm(80, new PartnerArenaWarJframe(), 2);
    }

    public static InternalForm jframe81() throws Exception {
        return new InternalForm(81, new SoaringMainFrame(), 2);
    }

    public static InternalForm jframe82() throws Exception {
        return new InternalForm(82, new SkillMainFrame(), 2);
    }

    public static InternalForm jframe83() throws Exception {
        return new InternalForm(83, new SkillPromoteMainFrame(), 2);
    }

    public static InternalForm jframe84() throws Exception {
        return new InternalForm(84, new GemMakeFrame(), 2);
    }

    public static InternalForm jframe85() throws Exception {
        return new InternalForm(85, new GemstoneOperationMainFrame(), 2);
    }

    public static InternalForm jframe86() throws Exception {
        return new InternalForm(86, new WingMainFrame(), 2);
    }

    public static InternalForm jframe866() throws Exception {
        return new InternalForm(866, new LHMainFrame(), 2);
    }

    public static InternalForm jframe87() throws Exception {
        return new InternalForm(87, new LotteryMainFrame(), 2);
    }

    public static InternalForm jframe88() throws Exception {
        return new InternalForm(88, new LotteryIntegralMainJframe(), 2);
    }

    public static InternalForm jframe89() throws Exception {
        return new InternalForm(89, new SeventyTwoChangesJframe(), 2);
    }

    public static InternalForm jframe90() throws Exception {
        return new InternalForm(90, new GoodsExchangeJframe(), 2);
    }

    public static InternalForm jframe900() throws Exception {
        return new InternalForm(900, new AuctionGoodsExchangeJframe(), 2);
    }

    public static InternalForm jframe901() throws Exception {
        return new InternalForm(901, new YuekaJframe(), 2);
    }

    public static InternalForm jframe91() throws Exception {
        return new InternalForm(91, new JframeSummonEquipMain(), 2);
    }

    public static InternalForm jframe92() throws Exception {
        return new InternalForm(92, new JframeCashRewardsMain(), 2);
    }

    public static InternalForm jframe93() throws Exception {
        return new InternalForm(93, new JframeHelpMain(), 2);
    }

    public static InternalForm jframe94() throws Exception {
        return new InternalForm(94, new JframeReclaimSkillMain(), 2);
    }

    public static InternalForm jframe95() throws Exception {
        return new InternalForm(95, new JframeStarCardMain(), 2);
    }

    public static InternalForm jframe96() throws Exception {
        return new InternalForm(96, new JframeSoulBackMain(), 2);
    }

    public static InternalForm jframe97() throws Exception {
        return new InternalForm(97, new JframeStarTransferMain(), 2);
    }

    public static InternalForm jframe98() throws Exception {
        return new InternalForm(98, new JframeStrengthMain(), 2);
    }

    public static InternalForm jframe99() throws Exception {
        return new InternalForm(99, new IphoneVerifyFrame(), 2);
    }

    public static InternalForm jframe100() throws Exception {
        return new InternalForm(100, new EverydayRechargeJframe(), 2);
    }

    public static InternalForm jframe101() throws Exception {
        return new InternalForm(101, new ContinuousRechargeJframe(), 2);
    }

    public static InternalForm jframe102() throws Exception {
        return new InternalForm(102, new EveryDayOddsJframe(), 2);
    }

    public static InternalForm jframe103() throws Exception {
        return new InternalForm(103, new ImpactGradeJframe(), 2);
    }

    public static InternalForm jframe104() throws Exception {
        return new InternalForm(104, new OptionsJframe(), 3);
    }

    public static InternalForm jframe105() throws Exception {
        return new InternalForm(105, new PartnerJframe(), 2);
    }

    public static InternalForm jframe106() throws Exception {
        return new InternalForm(106, new FactionAngelPracticeJframe(), 2);
    }

    public static InternalForm jframe107() throws Exception {
        return new InternalForm(107, new PartnerArenaExchangeJframe(), 2);
    }

    public static InternalForm jframe108() throws Exception {
        return new InternalForm(108, new TeamArenaMainJframe(), 2);
    }

    public static InternalForm jframe109() throws Exception {
        return new InternalForm(109, new TrueFeedbackMainJframe(), 2);
    }

    public static InternalForm jframe110() throws Exception {
        return new InternalForm(110, new TrueFeedbackAwardJframe(), 2);
    }

    public static InternalForm jframe111() throws Exception {
        return new InternalForm(111, new DreamlandTrialMainJframe(), 2);
    }

    public static InternalForm jframe112() throws Exception {
        return new InternalForm(112, new DonationsJfram(), 2);
    }

    public static InternalForm jframe113() throws Exception {
        return new InternalForm(113, new LimitedTimeShopFrame(), 2);
    }

    public static InternalForm jframe122() throws Exception {
        return new InternalForm(122, new JframeXingCardMain(), 2);
    }

    public static InternalForm jframe120() throws Exception {
        return new InternalForm(120, new StarSoulRefinedJframe(), 2);
    }

    public static InternalForm jframe121() throws Exception {
        return new InternalForm(121, new JframeXingBackMain(), 2);
    }

    public static InternalForm jframe123() throws Exception {
        return new InternalForm(123, new RoleToggleJframe(), 2);
    }

    public static InternalForm jframe555() throws Exception {
        return new InternalForm(555, new fly(), 2);
    }

    public static InternalForm jframe600() throws Exception {
        return new InternalForm(600, new FindDropJfram(), 2);
    }

    public static InternalForm jframe601() throws Exception {
        return new InternalForm(601, new PetLxJframe(), 2);
    }

    public static InternalForm jframe602() throws Exception {
        return new InternalForm(602, new LXiulianMainFrame(), 2);
    }

    public static InternalForm jframe6022() throws Exception {
        return new InternalForm(6022, new FaMenXlFrame(), 2);
    }

    public static InternalForm jframe289557289() throws Exception {
        return new InternalForm(289557289, new WxchangeAwardJframe(), 2);
    }

    private static InternalForm jframe1100() throws IOException {
        return new InternalForm(1100, new QiandaoListJframe(), 2);
    }

    private static InternalForm jframe1101() throws IOException {
        return new InternalForm(1101, new ChaojifeiJframe(), 2);
    }

    public static InternalForm jframe1102() throws IOException {
        return new InternalForm(1102, new WorldMapJframe(), 2);
    }

    public static InternalForm jframe1103() throws IOException {
        return new InternalForm(1103, new WorldTestsmallmapJframe(), 2);
    }

    public static InternalForm jframe1104() throws IOException {
        return new InternalForm(1104, new EventCalendarJframe(), 2);
    }

    public static InternalForm jframe1105() throws IOException {
        return new InternalForm(1105, new LotteryCPJframe(), 2);
    }

    private static InternalForm jframe2000() throws IOException {
        return new InternalForm(2000, new GMshopJframe(), 2);
    }

    private static InternalForm jframe3000() throws IOException {
        return new InternalForm(3000, new BjczJframe(), 2);
    }

    private static InternalForm jframe3001() throws IOException {
        return new InternalForm(3001, new AthChartJframe(), 2);
    }

    private static InternalForm jframe3002() throws IOException {
        return new InternalForm(3002, new RechargeVIPJframe(), 2);
    }

    private static InternalForm jframe3003() throws IOException {
        return new InternalForm(3003, new ExpAddMapJframe(), 2);
    }

    private static InternalForm jframe3004() throws IOException {
        return new InternalForm(3004, new LotteryFrame(), 2);
    }

    private static InternalForm jframe3005() throws IOException {
        return new InternalForm(3005, new Lottery1Frame(), 2);
    }

    private static InternalForm jframe3111() throws IOException {
        return new InternalForm(3111, new FuBenJframe(), 2);//副本
    }

    private static InternalForm jframe119() throws Exception {
        return new InternalForm(119, new AircraftJframe(), 2);
    }

    public static InternalForm jframe() throws Exception {
        return new InternalForm(700, new experimentCompoundJframe(), 2);
    }

    public static InternalForm jframe604() throws Exception {
        return new InternalForm(604, new LadderJframe(), 2);
    }

    public static InternalForm jframe605() throws Exception {
        return new InternalForm(605, new EventSelectionJframe(), 2);
    }

    public static InternalForm jframe606() throws Exception {
        return new InternalForm(606, new LadderLotteryJframe(), 2);
    }

    public static InternalForm jframe289() throws Exception {
        return new InternalForm(289, new WxchangeAwardJframe(), 2);
    }

    public static InternalForm jframe603() throws Exception {
        return new InternalForm(603, new MsgJframe1(), 4);
    }

    public static InternalForm jframe631() throws Exception {
        return new InternalForm(631, new SkillMsgJframe1(), 2);
    }

    public static InternalForm jframe634() throws Exception {
        return new InternalForm(634, new MsgJframe5(), 2);
    }

    public static InternalForm jframe114() throws Exception {
        return new InternalForm(114, new DianKaJiaoYijframe(), 2);
    }

    public static InternalForm depositJframe() throws Exception {
        return new InternalForm(63333, new DepositListJframe(), 2);
    }

    public static InternalForm jframe635() throws Exception {
        return new InternalForm(635, new Buffstatejframe(), 2);
    }

    public static InternalForm jframe124() throws Exception {
        return new InternalForm(124, new XYJframe(), 2);
    }

    public static InternalForm jframe125() throws Exception {
        return new InternalForm(125, new XYXYDJframe(), 2);
    }

    public static InternalForm jframe126() throws Exception {
        return new InternalForm(126, new XinYuanChengShenJframe(), 2);
    }

    public static InternalForm jframe160() throws Exception {
        return new InternalForm(160, new HuangJframe(), 2);
    }

    public static InternalForm jframe628() throws Exception {
        return new InternalForm(628, new MsgJframe2(), 2);
    }

    public static InternalForm jframe633() throws Exception {
        return new InternalForm(633, new MsgJframe4(), 2);
    }

    public static InternalForm jframe636() throws Exception {
        return new InternalForm(636, new ShaoXiangJframe(230), 2);
    }

    public static InternalForm jframe637() throws Exception {
        return new InternalForm(637, new AutoTaskJframe(), 2);
    }

    public static InternalForm jframe638() throws Exception {
        return new InternalForm(638, new ChooseLiangHaoTypeJframe(), 2);
    }

    public static InternalForm jframe999() throws Exception {
        return new InternalForm(999, new FanfanleJframe(), 2);
    }

    public static InternalForm AutoMaticRefiningJF() throws Exception {
        return new InternalForm(1119, new AutoMaticRefiningJframe(), 2);
    }

    public static InternalForm EquipmentSwitchingJframe() throws Exception {
        return new InternalForm(100000, new EquipmentSwitchingJframe(), 4);
    }

    public static InternalForm EquipmentSwitchingInputJframe() throws Exception {
        return new InternalForm(100001, new EquipmenSwitchingIputJframe(), 4);
    }

    private static InternalForm jframe60888() throws IOException {
        return new InternalForm(60888, new GZframe(), 2);
    }

    public static InternalForm jframe6222() throws Exception {
        return new InternalForm(6222, new TjJframe(), 2);
    }

    public static InternalForm jframe1234() throws Exception {
        return new InternalForm(1234, new RoleBornJframe(), 2);
    }

    public static InternalForm jframe640() throws Exception {
        return new InternalForm(640, new MyLiangHaoJframe(), 2);
    }

    public static InternalForm jframe3007() throws Exception {
        return new InternalForm(3007, new WeaponGodJframe(), 2);
    }

    public static InternalForm jframe641() throws Exception {
        return new InternalForm(641, new GetLiangHaoJframe(), 2);
    }

    public static InternalForm jframe642() throws Exception {
        return new InternalForm(642, new TaskTCJframe(), 2);
    }

    public static InternalForm jframe643() throws Exception {
        return new InternalForm(643, new ZxpackJframe(), 2);
    }

    public static InternalForm jframe703() throws Exception {
        return new InternalForm(703, new AucJfram(), 2);
    }

    public static InternalForm jframe704() throws Exception {
        return new InternalForm(704, new XinJianJfram(), 2);
    }

    public static InternalForm jframe705() throws Exception {
        return new InternalForm(705, new PaintLiangHaoJframe(), 2);
    }

    public static InternalForm jframe706() throws Exception {
        return new InternalForm(706, new ContinueLiangHaoJframe(), 2);
    }

    public static InternalForm jframe707() throws Exception {
        return new InternalForm(707, new LiangHaoPreviewJframe(), 2);
    }

    public static InternalForm jframe708() throws Exception {
        return new InternalForm(708, new ChatHistoryJframe(), 2);
    }

    public static InternalForm jframe709() throws Exception {
        return new InternalForm(709, new ChatSwitchJframe(), 2);
    }

    public static InternalForm jframe710() throws Exception {
        return new InternalForm(710, new SummonJframe(), 2);
    }

    public static InternalForm jframe711() throws Exception {
        return new InternalForm(711, new RolePetResistanceJframe(1), 2);
    }

    public static InternalForm jframe712() throws Exception {
        return new InternalForm(712, new JieGuaJframe(), 2);
    }

    public static InternalForm jframe713() throws Exception {
        return new InternalForm(713, new YaZhuJframe(), 2);
    }

    public static InternalForm jframe714() throws Exception {
        return new InternalForm(714, new DuiHuanLingLiJframe(), 2);
    }

    public static InternalForm jframe800() throws Exception {
        return new InternalForm(800, new SpotBoxJframe(), 2);
    }

    public static InternalForm jframe801() throws Exception {
        return new InternalForm(801, new SpotBuyBoxJframe(), 2);
    }

    public static InternalForm jframe802() throws Exception {
        return new InternalForm(802, new InputMoneyJframe(), 2);
    }

    public static InternalForm jframe127() throws Exception {
        return new InternalForm(127, new DdianJframe(), 2);
    }

    public static InternalForm jframe128() throws Exception {
        return new InternalForm(128, new DianQJframe(), 2);
    }

    public static InternalForm jframe2255() throws Exception {
        return new InternalForm(2255, new xuanzeJframe(), 2);
    }

    public static InternalForm jframe2256() throws Exception {
        return new InternalForm(2256, new RandFJframe(), 2);
    }

    public static InternalForm jframe2257() throws Exception {
        return new InternalForm(2257, new ShouhuPackJframe(), 2);
    }

    public static InternalForm jframe2258() throws Exception {
        return new InternalForm(2258, new LvlupJframe(), 2);
    }

    public static InternalForm jframe559() throws Exception {
        return new InternalForm(559, new MountShouhuJframe(), 2);
    }

    public static InternalForm jframe911() throws Exception {
        return new InternalForm(911, new QZCQJfram(), 2);
    }

    public static InternalForm jframe912() throws Exception {
        return new InternalForm(912, new QZQQJfram(), 2);
    }

    public static InternalForm jframe1146() throws Exception {
        return new InternalForm(1146, new TournamentsJframe(), 2);
    }

    public static InternalForm jframe3081() throws Exception {
        return new InternalForm(3081, new WelcomeXyJframe(), 2);
    }


    public static InternalForm jframe188888() throws Exception {
        return new InternalForm(188888, new OpenSkillGridJframe(), 2);
    }

    public static InternalForm spiritualJframe() throws Exception {
        return new InternalForm(178888, new SpiritualJframe(), 2);
    }

    public static InternalForm jframe30001() throws Exception {
        return new InternalForm(30001, (JInternalFrame) new XYDJLSJframe(), 2);
    }

    public static InternalForm jframe8031() throws Exception {
        return new InternalForm(8031, new XuanBaoJframe(), 2);
    }

    public static InternalForm jframe8032() throws Exception {
        return new InternalForm(8032, new XuanBaoMsgJframe(), 2);
    }

    public static InternalForm jframe8033() throws Exception {
        return new InternalForm(8033, new XuanBaoXiuLianJframe(), 2);
    }

    public static InternalForm jframe8034() throws Exception {
        return new InternalForm(8034, new XuanBaoPackJframe(), 2);
    }

    public static InternalForm jframe8035() throws Exception {
        return new InternalForm(8035, new PlayRulesJframe1(), 2);
    }

    public static InternalForm jframe8036() throws Exception {
        return new InternalForm(8036, new PlayRulesJframe2(), 2);
    }

    public static InternalForm jframe8037() throws Exception {
        return new InternalForm(8037, new XuanBaoPack_xuanyunJframe(), 2);
    }

    public static InternalForm jframe8038() throws Exception {
        return new InternalForm(8038, new XuanBaoTJJframe(), 2);
    }

    public static List<InternalForm> getForms() {
        return FormsManagement.Forms;
    }

    public static void setForms(List<InternalForm> forms) {
        FormsManagement.Forms = forms;
    }

    static {
        FormsManagement.Forms = new ArrayList<>();
        FormsManagement.map = new HashMap<>();
        FormsManagement.cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("").getImage(), new Point(), null);
        getInternalForm(0);
        getInternalForm(6);
    }
}
