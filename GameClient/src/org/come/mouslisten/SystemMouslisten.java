package org.come.mouslisten;

import java.io.FileOutputStream;
import java.io.IOException;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.apache.commons.lang.StringUtils;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.File;
import java.util.Objects;
import java.util.Properties;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.TestSetupJpanel;
import java.awt.Font;
import org.come.Jpanel.TeststateJpanel;
import org.come.Jpanel.WorldTestsmallmapJpanel;
import org.come.Jpanel.TestsmallmapJpanel;
import com.tool.image.ManimgAttribute;
import org.come.Jpanel.GameJpanel;
import com.tool.tcp.GetTcpPath;
import com.tool.image.ImageMixDeal;
import org.come.until.Util;
import org.come.until.Music;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.ScrenceUntil;
import org.come.Frame.TestSetupJframe;
import com.tool.role.RoleSystem;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;

public class SystemMouslisten implements MouseListener
{
    public static final ImageIcon icon;
    public static final ImageIcon icon1;
    private int type;
    
    public static ImageIcon getIcon() {
        if (MyIsif.getStyle().equals("水墨")) {
            return SystemMouslisten.icon;
        }
        return SystemMouslisten.icon1;
    }
    
    public SystemMouslisten() {
    }
    
    public SystemMouslisten(int type) {
        this.type = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.type) {
            case 0: {
                type0();
                break;
            }
            case 1: {
                type1();
                break;
            }
            case 101: {
                type101();
                break;
            }
            case 2: {
                type2();
                break;
            }
            case 3: {
                type3();
                break;
            }
            case 4: {
                type4();
                RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
                String senmes = Agreement.getAgreement().roleSystemAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSystem));
                SendMessageUntil.toServer(senmes);
                break;
            }
            case 5: {
                type5();
                break;
            }
            case 6: {
                type6();
                break;
            }
            case 7: {
                type7();
                break;
            }
            case 8: {
                type8();
                RoleSystem roleSystem2 = RoleData.getRoleData().getRoleSystem();
                String senmes2 = Agreement.getAgreement().roleSystemAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSystem2));
                SendMessageUntil.toServer(senmes2);
                break;
            }
            case 9: {
                type9();
                break;
            }
            case 10: {
                type10();
                break;
            }
            case 11: {
                type11();
                break;
            }
            case 15: {
                ZhuFrame.getZhuJpanel().addPrompt2("低性能模式，合适多开模式");
                type15();
                break;
            }
            case 16: {
                ZhuFrame.getZhuJpanel().addPrompt2("标准模式，流畅模式");
                type16();
                break;
            }
            case 17: {
                ZhuFrame.getZhuJpanel().addPrompt2("高性能模式，流畅高画质模式，合适高性能电脑");
                type17();
                break;
            }
            case 18: {
                type18();
                break;
            }
            case 27: {
                type27();
                break;
            }
            case 28: {
                type28();
                break;
            }
            case 29: {
                type29();
                break;
            }
            case 30: {
                type30();
                break;
            }
            case 31: {
                type31();
                break;
            }
            case 32: {
                type32();
                break;
            }
            case 33: {
                type33();
                break;
            }
            case 34: {
                type34();
                break;
            }
            case 35: {
                type35();
                break;
            }
            case 36: {
                type36();
                break;
            }
            case 37: {
                type37();
                break;
            }
            case 100: {
                type100();
                break;
            }
        }
        if (this.type != 4 && this.type != 8) {
            writeTxt();
        }
    }
    
    public static void type0() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("800x600");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(null);
            ScrenceUntil.ScreceChange(0);
            FightingMixDeal.changepath();
        }
    }
    
    public static void type1() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("1024x768");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(null);
            ScrenceUntil.ScreceChange(1);
            FightingMixDeal.changepath();
        }
    }
    
    public static void type101() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("1366x768");
            ScrenceUntil.ScreceChange(2);
            FightingMixDeal.changepath();
        }
    }
    
    public static void type2() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusic().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusic().setIcon(SystemMouslisten.icon);
            Music.kz1 = true;
            Music.addbeijing(Util.mapmodel.getGamemap().getMusic() + ".mp3");
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusic().setIcon(null);
            Music.beijing(Music.kz1 = false);
        }
    }
    
    public static void type3() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().setIcon(SystemMouslisten.icon);
            Music.kz2 = true;
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().setIcon(null);
            Music.yinxiao(Music.kz2 = false);
        }
    }
    
    public static void type4() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabPlayswitch().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabPlayswitch().setIcon(SystemMouslisten.icon);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsPk(Integer.valueOf(1));
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabPlayswitch().setIcon(null);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsPk(Integer.valueOf(0));
        }
    }
    
    public static void type5() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().setIcon(SystemMouslisten.icon);
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().setIcon(null);
        }
    }
    
    public static void type6() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().setIcon(SystemMouslisten.icon);
            ImageMixDeal.isShadow = true;
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().setIcon(null);
            ImageMixDeal.isShadow = false;
        }
    }
    
    public static void type7() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabJoinfriends().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabJoinfriends().setIcon(SystemMouslisten.icon);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsFriend(Integer.valueOf(1));
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabJoinfriends().setIcon(null);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsFriend(Integer.valueOf(0));
        }
    }
    
    public static void type8() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabReceiveitems().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabReceiveitems().setIcon(SystemMouslisten.icon);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsGood(Integer.valueOf(1));
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabReceiveitems().setIcon(null);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsGood(Integer.valueOf(0));
        }
    }
    
    public static void type9() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabAcceptteam().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabAcceptteam().setIcon(SystemMouslisten.icon);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsTeam(Integer.valueOf(1));
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabAcceptteam().setIcon(null);
            RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
            roleSystem.setIsTeam(Integer.valueOf(0));
        }
    }
    
    public static void type10() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFull().getIcon() == null) {
            if (Objects.equals(GetTcpPath.STRTMPXJ, "1")){
                ZhuFrame.getZhuJpanel().addPrompt2("新版法术不支持全屏，请关闭新版后重试");
            }else {
                TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFull().setIcon(SystemMouslisten.icon);
                GetTcpPath.STRTMP = "2";
            }
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFull().setIcon(null);
            GetTcpPath.STRTMP = "1";
        }
    }
    
    public static void type11() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().setIcon(SystemMouslisten.icon);
            Music.MusicNew = "1";
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().setIcon(null);
            Music.MusicNew = "2";
        }
    }
    
    public static void type15() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("低画质");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(null);
            GameJpanel.XNMSXZ = "1";
            ManimgAttribute.XNMSXZSXF = "1";
        }
    }
    
    public static void type16() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("标准画质");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(null);
            GameJpanel.XNMSXZ = "2";
            ManimgAttribute.XNMSXZSXF = "2";
        }
    }
    
    public static void type17() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("超高画质");
            GameJpanel.XNMSXZ = "3";
            ManimgAttribute.XNMSXZSXF = "3";
        }
    }
    
    public static void type18() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFullXJ().getIcon() == null) {
            if (Objects.equals(GetTcpPath.STRTMP, "2")){
                ZhuFrame.getZhuJpanel().addPrompt2("新版法术不支持全屏，请关闭新版后重试");
            }else {
                TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFullXJ().setIcon(SystemMouslisten.icon);
                GetTcpPath.STRTMPXJ = "1";
            }
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSkillFullXJ().setIcon(null);
            GetTcpPath.STRTMPXJ = "2";
        }
    }
    
    public static void type27() {
        if (TestsmallmapJpanel.getLabNoC().getIcon() == null) {
            TestsmallmapJpanel.getLabNoC().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getLabNoC().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    public static void type28() {
        if (TestsmallmapJpanel.getMove().getIcon() == null) {
            TestsmallmapJpanel.getMove().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getMove().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    public static void type29() {
        if (TestsmallmapJpanel.getRwnpc().getIcon() == null) {
            TestsmallmapJpanel.getRwnpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getRwnpc().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    public static void type30() {
        if (TestsmallmapJpanel.getSynpc().getIcon() == null) {
            TestsmallmapJpanel.getSynpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getSynpc().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    public static void type31() {
        if (TestsmallmapJpanel.getQbnpx().getIcon() == null) {
            TestsmallmapJpanel.getQbnpx().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getSynpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getRwnpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getMove().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getLabNoC().setIcon(new ImageIcon("inkImg/button/13.png"));
        }
        else {
            TestsmallmapJpanel.getQbnpx().setIcon(null);
            TestsmallmapJpanel.getSynpc().setIcon(null);
            TestsmallmapJpanel.getRwnpc().setIcon(null);
            TestsmallmapJpanel.getMove().setIcon(null);
            TestsmallmapJpanel.getLabNoC().setIcon(null);
        }
    }
    
    public static void type32() {
        if (WorldTestsmallmapJpanel.getLabNoCWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    public static void type33() {
        if (WorldTestsmallmapJpanel.getMoveWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    public static void type34() {
        if (WorldTestsmallmapJpanel.getRwnpcWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    public static void type35() {
        if (WorldTestsmallmapJpanel.getSynpcWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    public static void type36() {
        if (WorldTestsmallmapJpanel.getQbnpxWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
        }
        else {
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(null);
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(null);
        }
    }
    
    public static void type37() {
        if (TeststateJpanel.getQhnum().equals("1")) {
            TeststateJpanel.setQhnum("2");
        }
        else {
            TeststateJpanel.setQhnum("1");
        }
    }
    
    public static void type100() {
        TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getFontsizetext().setText("楷体");
        TestSetupJpanel.nameFont = new Font("楷体", 0, 18);
    }
    
    public static void typelushu() {
        TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getFontsizetext().setText("隶书");
        TestSetupJpanel.nameFont = UIUtils.TEXT_shuimo2;
    }
    
    public static void typemoren() {
        TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getFontsizetext().setText("默认");
        TestSetupJpanel.nameFont = UIUtils.TXT_lishud;
    }
    
    public static void Systeminitial() {
        type0();
        type1();
        type101();
        TestSetupJpanel setupJpanel = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        setupJpanel.getLabMusic().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabMusicNew().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabSound().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabPlayswitch().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabRefusemsg().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabLetter().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabJoinfriends().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabReceiveitems().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabAcceptteam().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabSkillFull().setIcon(SystemMouslisten.icon);
        setupJpanel.getLabSkillFullXJ().setIcon(SystemMouslisten.icon);
    }
    
    public static void readSysteminit() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(new File("resource/other/systemInit.txt"));
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            properties.load(isr);
            String showView = properties.getProperty("showView");
            String music = properties.getProperty("music");
            String musicNew = properties.getProperty("musicNew");
            String musicSound = properties.getProperty("musicSound");
            String stranger = properties.getProperty("stranger");
            String shadow = properties.getProperty("shadow");
            String addFriend = properties.getProperty("addFriend");
            String acceptTeam = properties.getProperty("acceptTeam");
            String skilFull = properties.getProperty("skilFull");
            String skilFullXJ = properties.getProperty("skilFullXJ");
            String qhnum = properties.getProperty("qhnum");
            String kaitizi = properties.getProperty("kaitizi");
            String chatSwitch = properties.getProperty("chatSwitch", "0,1,2,3,4,5,6");
            TestSetupJpanel setupJpanel = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
            if (showView != null) {
                if ("0".equals(showView)) {
                    type0();
                }
                else if ("2".equals(showView)) {
                    type101();
                }
                else {
                    type1();
                }
            }
            else {
                type1();
            }
            if (music != null) {
                if ("off".equals(music)) {
                    setupJpanel.getLabMusic().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabMusic().setIcon(null);
                }
                type2();
            }
            else {
                setupJpanel.getLabMusic().setIcon(SystemMouslisten.icon);
            }
            if (musicNew != null) {
                if ("off".equals(musicNew)) {
                    setupJpanel.getLabMusicNew().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabMusicNew().setIcon(null);
                }
                type11();
            }
            else {
                setupJpanel.getLabMusicNew().setIcon(SystemMouslisten.icon);
            }
            if (musicSound != null) {
                if ("off".equals(musicSound)) {
                    setupJpanel.getLabSound().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabSound().setIcon(null);
                }
                type3();
            }
            else {
                setupJpanel.getLabSound().setIcon(SystemMouslisten.icon);
            }
            if (stranger != null) {
                if ("off".equals(stranger)) {
                    setupJpanel.getLabRefusemsg().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabRefusemsg().setIcon(null);
                }
                type5();
            }
            else {
                setupJpanel.getLabRefusemsg().setIcon(SystemMouslisten.icon);
            }
            if (shadow != null) {
                if ("off".equals(shadow)) {
                    setupJpanel.getLabLetter().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabLetter().setIcon(null);
                }
                type6();
            }
            else {
                setupJpanel.getLabLetter().setIcon(SystemMouslisten.icon);
            }
            if (addFriend != null) {
                if ("off".equals(addFriend)) {
                    setupJpanel.getLabJoinfriends().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabJoinfriends().setIcon(null);
                }
                type7();
            }
            else {
                setupJpanel.getLabJoinfriends().setIcon(SystemMouslisten.icon);
            }
            if (acceptTeam != null) {
                if ("off".equals(acceptTeam)) {
                    setupJpanel.getLabAcceptteam().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabAcceptteam().setIcon(null);
                }
                type9();
            }
            else {
                setupJpanel.getLabAcceptteam().setIcon(SystemMouslisten.icon);
            }
            if (skilFull != null) {
                if ("off".equals(skilFull)) {
                    setupJpanel.getLabSkillFull().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabSkillFull().setIcon(null);
                }
                type10();
            }
            else {
                setupJpanel.getLabSkillFull().setIcon(SystemMouslisten.icon);
            }
            if (skilFullXJ != null) {
                if ("off".equals(skilFullXJ)) {
                    setupJpanel.getLabSkillFullXJ().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabSkillFullXJ().setIcon(null);
                }
                type18();
            }
            else {
                setupJpanel.getLabSkillFullXJ().setIcon(SystemMouslisten.icon);
            }
            if (kaitizi != null) {
                if ("onk".equals(kaitizi)) {
                    type100();
                }
                else if ("onl".equals(kaitizi)) {
                    typelushu();
                }
                else if ("on".equals(kaitizi)) {
                    typemoren();
                }
            }
            setupJpanel.getLabPlayswitch().setIcon(SystemMouslisten.icon);
            setupJpanel.getLabReceiveitems().setIcon(SystemMouslisten.icon);
            if (StringUtils.isNotBlank(chatSwitch)) {
                String[] vas = chatSwitch.split(",");
                for (int i = 0; i < vas.length; ++i) {
                    int index = Integer.parseInt(vas[i]);
                    if (index < FrameMessageChangeJpanel.getChatSwitch().length) {
                        FrameMessageChangeJpanel.setChatSwitch(index, true);
                    }
                }
            }
            if (qhnum != null) {
                TeststateJpanel.setQhnum(qhnum);
            }
        }
        catch (IOException var23) {
            Systeminitial();
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException var24) {
                var24.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException var25) {
                var25.printStackTrace();
            }
        }
    }
    
    public static String readSysteminit(String type) {
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String res = "";
        try {
            fis = new FileInputStream(new File("resource/other/systemInit.txt"));
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            properties.load(isr);
            res = properties.getProperty(type);
        }
        catch (Exception e) {
            e.printStackTrace();
            return res;
        }
        return res;
    }
    
    public static void writeTxt() {
        FileOutputStream outputStream = null;
        Properties properties = new Properties();
        try {
            outputStream = new FileOutputStream("resource/other/systemInit.txt");
            TestSetupJpanel setupJpanel = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
            if (setupJpanel.getResolutiontext().getText().equals("1024x768")) {
                properties.setProperty("showView", "1");
            }
            else if (setupJpanel.getResolutiontext().getText().equals("800x600")) {
                properties.setProperty("showView", "0");
            }
            else if (setupJpanel.getResolution1366().getText().equals("1366x768")) {
                properties.setProperty("showView", "2");
            }
            if (setupJpanel.getInterfacialtext().getText().equals("低画质")) {
                properties.setProperty("xNms", "1");
            }
            else if (setupJpanel.getInterfacialtext().getText().equals("标准画质")) {
                properties.setProperty("xNms", "2");
            }
            else if (setupJpanel.getInterfacialtext().getText().equals("超高画质")) {
                properties.setProperty("xNms", "3");
            }
            if (setupJpanel.getFontsizetext().getText().equals("默认")) {
                properties.setProperty("kaitizi", "on");
            }
            else if (setupJpanel.getFontsizetext().getText().equals("楷体")) {
                properties.setProperty("kaitizi", "onk");
            }
            else if (setupJpanel.getFontsizetext().getText().equals("隶书")) {
                properties.setProperty("kaitizi", "onl");
            }
            if (setupJpanel.getLabMusic().getIcon() != null) {
                properties.setProperty("music", "on");
            }
            else {
                properties.setProperty("music", "off");
            }
            if (setupJpanel.getLabMusicNew().getIcon() != null) {
                properties.setProperty("musicNew", "on");
            }
            else {
                properties.setProperty("musicNew", "off");
            }
            if (setupJpanel.getLabSound().getIcon() != null) {
                properties.setProperty("musicSound", "on");
            }
            else {
                properties.setProperty("musicSound", "off");
            }
            if (setupJpanel.getLabRefusemsg().getIcon() != null) {
                properties.setProperty("stranger", "on");
            }
            else {
                properties.setProperty("stranger", "off");
            }
            if (setupJpanel.getLabLetter().getIcon() != null) {
                properties.setProperty("shadow", "on");
            }
            else {
                properties.setProperty("shadow", "off");
            }
            if (setupJpanel.getLabJoinfriends().getIcon() != null) {
                properties.setProperty("addFriend", "on");
            }
            else {
                properties.setProperty("addFriend", "off");
            }
            if (setupJpanel.getLabAcceptteam().getIcon() != null) {
                properties.setProperty("acceptTeam", "on");
            }
            else {
                properties.setProperty("acceptTeam", "off");
            }
            if (setupJpanel.getLabSkillFull().getIcon() != null) {
                properties.setProperty("skilFull", "on");
            }
            else {
                properties.setProperty("skilFull", "off");
            }
            if (setupJpanel.getLabSkillFullXJ().getIcon() != null) {
                properties.setProperty("skilFullXJ", "on");
            }
            else {
                properties.setProperty("skilFullXJ", "off");
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < FrameMessageChangeJpanel.getChatSwitch().length; ++i) {
                if (FrameMessageChangeJpanel.getChatSwitch()[i]) {
                    if (buffer.length() != 0) {
                        buffer.append(",");
                    }
                    buffer.append(i);
                }
            }
            if (TeststateJpanel.getQhnum().equals("2")) {
                properties.setProperty("qhnum", "2");
            }
            else {
                properties.setProperty("qhnum", "1");
            }
            properties.setProperty("chatSwitch", buffer.toString());
            properties.store(outputStream, null);
        }
        catch (IOException var12) {
            var12.printStackTrace();
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException var13) {
                    var13.printStackTrace();
                }
            }
        }
    }
    
    public static void writeTxt(String name, String val) {
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("resource/other/systemInit.txt");
            fis = new FileInputStream(new File("resource/other/systemInit.txt"));
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            properties.load(isr);
            properties.setProperty(name, val);
            properties.store(outputStream, null);
        }
        catch (IOException var12) {
            var12.printStackTrace();
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public static void run(String type) {
        try {
            ZhuFrame.getZhuJpanel().addPrompt2(type);
        }
        catch (Exception ex) {}
    }
    
    static {
        icon = new ImageIcon("inkImg/button/B88.png");
        icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
    }
}
