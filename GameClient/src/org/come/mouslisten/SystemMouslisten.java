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
                switchToWindow800x600();
                break;
            }
            case 1: {
                switchToFullscreen1024x768();
                break;
            }
            case 101: {
                switchToFullscreen1366x768();
                break;
            }
            case 2: {
                toggleBackgroundMusic();
                break;
            }
            case 3: {
                toggleSoundEffect();
                break;
            }
            case 4: {
                togglePkMode();
                RoleSystem roleSystem = RoleData.getRoleData().getRoleSystem();
                String senmes = Agreement.getAgreement().roleSystemAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSystem));
                SendMessageUntil.toServer(senmes);
                break;
            }
            case 5: {
                toggleRefuseMessage();
                break;
            }
            case 6: {
                toggleShadowEffect();
                break;
            }
            case 7: {
                toggleJoinFriendPrompt();
                break;
            }
            case 8: {
                toggleJoinTeamPrompt();
                RoleSystem roleSystem2 = RoleData.getRoleData().getRoleSystem();
                String senmes2 = Agreement.getAgreement().roleSystemAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSystem2));
                SendMessageUntil.toServer(senmes2);
                break;
            }
            case 9: {
                toggleTradePrompt();
                break;
            }
            case 10: {
                toggleDuelPrompt();
                break;
            }
            case 11: {
                toggleWindowShake();
                break;
            }
            case 15: {
                ZhuFrame.getZhuJpanel().addPrompt2("低性能模式，合适多开模式");
                useLowGraphicsQuality();
                break;
            }
            case 16: {
                ZhuFrame.getZhuJpanel().addPrompt2("标准模式，流畅模式");
                useStandardGraphicsQuality();
                break;
            }
            case 17: {
                ZhuFrame.getZhuJpanel().addPrompt2("高性能模式，流畅高画质模式，合适高性能电脑");
                useHighGraphicsQuality();
                break;
            }
            case 18: {
                toggleNewSpellFullscreen();
                break;
            }
            case 27: {
                toggleMiniMapHidePlayer();
                break;
            }
            case 28: {
                toggleMiniMapMovePoint();
                break;
            }
            case 29: {
                toggleMiniMapTaskNpc();
                break;
            }
            case 30: {
                toggleMiniMapBusinessNpc();
                break;
            }
            case 31: {
                toggleMiniMapAllNpc();
                break;
            }
            case 32: {
                toggleWorldMapHidePlayer();
                break;
            }
            case 33: {
                toggleWorldMapMovePoint();
                break;
            }
            case 34: {
                toggleWorldMapTaskNpc();
                break;
            }
            case 35: {
                toggleWorldMapBusinessNpc();
                break;
            }
            case 36: {
                toggleWorldMapAllNpc();
                break;
            }
            case 37: {
                toggleTitleDisplayMode();
                break;
            }
            case 100: {
                useKaiTiFont();
                break;
            }
        }
        if (this.type != 4 && this.type != 8) {
            writeTxt();
        }
    }
    
    /** 切换到 800x600 窗口模式。 */
    public static void switchToWindow800x600() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("800x600");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(null);
            ScrenceUntil.ScreceChange(0);
            FightingMixDeal.changepath();
        }
    }
    
    /** 切换到 1024x768 全屏模式。 */
    public static void switchToFullscreen1024x768() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("1024x768");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(null);
            ScrenceUntil.ScreceChange(1);
            FightingMixDeal.changepath();
        }
    }
    
    /** 切换到 1366x768 全屏模式。 */
    public static void switchToFullscreen1366x768() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabWindow().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabFullscreen1().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getResolutiontext().setText("1366x768");
            ScrenceUntil.ScreceChange(2);
            FightingMixDeal.changepath();
        }
    }
    
    /** 开关背景音乐。 */
    public static void toggleBackgroundMusic() {
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
    
    /** 开关音效。 */
    public static void toggleSoundEffect() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().setIcon(SystemMouslisten.icon);
            Music.kz2 = true;
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabSound().setIcon(null);
            Music.yinxiao(Music.kz2 = false);
        }
    }
    
    /** 开关玩家 PK 模式。 */
    public static void togglePkMode() {
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
    
    /** 开关拒收消息。 */
    public static void toggleRefuseMessage() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().setIcon(SystemMouslisten.icon);
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabRefusemsg().setIcon(null);
        }
    }
    
    /** 开关阴影显示。 */
    public static void toggleShadowEffect() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().setIcon(SystemMouslisten.icon);
            ImageMixDeal.isShadow = true;
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabLetter().setIcon(null);
            ImageMixDeal.isShadow = false;
        }
    }
    
    /** 开关加入好友提醒。 */
    public static void toggleJoinFriendPrompt() {
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
    
    /** 开关组队邀请提醒。 */
    public static void toggleJoinTeamPrompt() {
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
    
    /** 开关交易邀请提醒。 */
    public static void toggleTradePrompt() {
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
    
    /** 开关切磋邀请提醒。 */
    public static void toggleDuelPrompt() {
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
    
    /** 开关窗口抖动效果。 */
    public static void toggleWindowShake() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().setIcon(SystemMouslisten.icon);
            Music.MusicNew = "1";
        }
        else {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getLabMusicNew().setIcon(null);
            Music.MusicNew = "2";
        }
    }
    
    /** 切换到低画质模式。 */
    public static void useLowGraphicsQuality() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("低画质");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(null);
            GameJpanel.XNMSXZ = "1";
            ManimgAttribute.XNMSXZSXF = "1";
        }
    }
    
    /** 切换到标准画质模式。 */
    public static void useStandardGraphicsQuality() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("标准画质");
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(null);
            GameJpanel.XNMSXZ = "2";
            ManimgAttribute.XNMSXZSXF = "2";
        }
    }
    
    /** 切换到超高画质模式。 */
    public static void useHighGraphicsQuality() {
        if (TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().getIcon() == null) {
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNms().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsZ().setIcon(null);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getXNmsG().setIcon(SystemMouslisten.icon);
            TestSetupJframe.getTestSetupJframe().getTestSetupJpanel().getInterfacialtext().setText("超高画质");
            GameJpanel.XNMSXZ = "3";
            ManimgAttribute.XNMSXZSXF = "3";
        }
    }
    
    /** 开关新版法术全屏效果。 */
    public static void toggleNewSpellFullscreen() {
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
    
    /** 开关小地图隐藏玩家。 */
    public static void toggleMiniMapHidePlayer() {
        if (TestsmallmapJpanel.getLabNoC().getIcon() == null) {
            TestsmallmapJpanel.getLabNoC().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getLabNoC().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    /** 开关小地图移动点显示。 */
    public static void toggleMiniMapMovePoint() {
        if (TestsmallmapJpanel.getMove().getIcon() == null) {
            TestsmallmapJpanel.getMove().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getMove().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    /** 开关小地图任务 NPC 显示。 */
    public static void toggleMiniMapTaskNpc() {
        if (TestsmallmapJpanel.getRwnpc().getIcon() == null) {
            TestsmallmapJpanel.getRwnpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getRwnpc().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    /** 开关小地图商业 NPC 显示。 */
    public static void toggleMiniMapBusinessNpc() {
        if (TestsmallmapJpanel.getSynpc().getIcon() == null) {
            TestsmallmapJpanel.getSynpc().setIcon(new ImageIcon("inkImg/button/13.png"));
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
        else {
            TestsmallmapJpanel.getSynpc().setIcon(null);
            TestsmallmapJpanel.getQbnpx().setIcon(null);
        }
    }
    
    /** 开关小地图 NPC 总显示。 */
    public static void toggleMiniMapAllNpc() {
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
    
    /** 开关世界地图隐藏玩家。 */
    public static void toggleWorldMapHidePlayer() {
        if (WorldTestsmallmapJpanel.getLabNoCWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getLabNoCWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    /** 开关世界地图移动点显示。 */
    public static void toggleWorldMapMovePoint() {
        if (WorldTestsmallmapJpanel.getMoveWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getMoveWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    /** 开关世界地图任务 NPC 显示。 */
    public static void toggleWorldMapTaskNpc() {
        if (WorldTestsmallmapJpanel.getRwnpcWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getRwnpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    /** 开关世界地图商业 NPC 显示。 */
    public static void toggleWorldMapBusinessNpc() {
        if (WorldTestsmallmapJpanel.getSynpcWorld().getIcon() == null) {
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(new ImageIcon("inkImg/button/13.png"));
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
        else {
            WorldTestsmallmapJpanel.getSynpcWorld().setIcon(null);
            WorldTestsmallmapJpanel.getQbnpxWorld().setIcon(null);
        }
    }
    
    /** 开关世界地图全部 NPC 显示。 */
    public static void toggleWorldMapAllNpc() {
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
    
    /** 切换称号显示模式。 */
    public static void toggleTitleDisplayMode() {
        if (TeststateJpanel.getQhnum().equals("1")) {
            TeststateJpanel.setQhnum("2");
        }
        else {
            TeststateJpanel.setQhnum("1");
        }
    }
    
    /** 切换字体到楷体。 */
    public static void useKaiTiFont() {
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
        switchToWindow800x600();
        switchToFullscreen1024x768();
        switchToFullscreen1366x768();
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
                    switchToWindow800x600();
                }
                else if ("2".equals(showView)) {
                    switchToFullscreen1366x768();
                }
                else {
                    switchToFullscreen1024x768();
                }
            }
            else {
                switchToFullscreen1024x768();
            }
            if (music != null) {
                if ("off".equals(music)) {
                    setupJpanel.getLabMusic().setIcon(SystemMouslisten.icon);
                }
                else {
                    setupJpanel.getLabMusic().setIcon(null);
                }
                toggleBackgroundMusic();
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
                toggleWindowShake();
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
                toggleSoundEffect();
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
                toggleRefuseMessage();
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
                toggleShadowEffect();
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
                toggleJoinFriendPrompt();
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
                toggleTradePrompt();
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
                toggleDuelPrompt();
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
                toggleNewSpellFullscreen();
            }
            else {
                setupJpanel.getLabSkillFullXJ().setIcon(SystemMouslisten.icon);
            }
            if (kaitizi != null) {
                if ("onk".equals(kaitizi)) {
                    useKaiTiFont();
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


