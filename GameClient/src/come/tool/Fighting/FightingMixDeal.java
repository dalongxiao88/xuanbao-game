package come.tool.Fighting;

import java.util.HashMap;
import org.come.bean.PrivateData;
import com.tool.time.Limit;
import org.come.bean.NpcCureBean;
import org.come.until.Util;
import org.come.bean.FightOperation;
import com.tool.imagemonitor.FightingMonitor;
import org.come.Frame.MsgJframe1;
import org.come.Frame.MsgJframe;
import java.util.Iterator;
import org.come.thread.TimeControlRunnable;
import com.tool.tcp.SpriteFactory;
import com.tool.image.ManimgAttribute;
import org.come.entity.Fly;
import java.io.IOException;
import org.come.mouslisten.PetAddPointMouslisten;
import java.math.BigDecimal;
import com.tool.btn.PetPanelBtn;
import com.tool.role.RoleProperty;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import org.come.Frame.PartnerArenaJframe;
import com.tool.ModerateTask.Hero;
import org.come.control.AssetControl;
import com.tool.PlayerKill.PKSys;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import come.tool.FightingData.FightingEndD;
import come.tool.FightingData.BattleEnd;
import com.tool.image.ImageMixDeal;
import org.come.until.UserMessUntil;
import org.come.model.Gamemap;
import org.come.until.Music;
import org.come.Jpanel.GameJpanel;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;
import com.tool.tcp.GetTcpPath;
import come.tool.FightingEffect.EffectType;
import java.util.ArrayList;
import org.come.entity.RoleSummoning;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.pet.PetProperty;
import org.come.until.Article;
import com.tool.time.TimeLimit;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Color;
import org.come.until.ScrenceUntil;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import come.tool.handle.HandleType;
import java.awt.Image;
import org.come.control.RoleSumminCloseControl;
import java.util.List;
import java.util.Map;

public class FightingMixDeal
{
    public static final int ROUND_TIME = 60000;
    public static final int AUTOMATION_PLAYER = 3000;
    public static final int AUTOMATION_PET = 300;
    public static int BattleType;
    public static int FightingNumber;
    public static int CurrentRound;
    public static long CorrectTime;
    public static long roundType;
    public static long time;
    public static int State;
    public static Map<Integer, Statelist> BattlefieldPlay;
    public static List<SkillSpell> skills;
    public static List<Fightingimage> CurrentData;
    public static List<ManStateData> mansState;
    public static long systime;
    public static long cha;
    public static BuffUtil buffUtil;
    public static int camp;
    public static int man;
    public RoleSumminCloseControl roleSumminCloseControl;
    public static int zdhh;
    public static String Music1;
    private static int MAX;
    private static int bjid;
    private static Image bj;
    private static Image yq;
    public static String yqz;
    private static Image nq;
    public static Image xy;
    public static String nqz;
    public static String xyz;
    private static String di;
    private static String bout;
    private static String se;
    private static int seInt;
    public static Boolean b;
    public static int x;
    public static int y;
    
    public static void changepath() {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            ((Fightingimage)FightingMixDeal.CurrentData.get(i)).tiaoz(FightingMixDeal.camp);
        }
        FightingMixDeal.buffUtil.ViewPath(FightingMixDeal.camp);
    }
    
    public static synchronized void PalyProgress(long pass) {
        FightingMixDeal.time += pass;
        if (pass == 0L) {
            return;
        }
        try {
            for (int i = 0, size = FightingMixDeal.CurrentData.size(); i < size; ++i) {
                FightingMixDeal.CurrentData.get(i).addTime(pass);
            }
            long DieTime = (long)((double)pass * 1.6);
            for (int j = FightingMixDeal.skills.size() - 1; j >= 0; --j) {
                if (FightingMixDeal.skills.get(j).getDietime(DieTime)) {
                    FightingMixDeal.skills.remove(j);
                }
            }
            HandleType.getHandleById(FightingMixDeal.State).handle(pass);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void CgImg(int id) {
        if (id != FightingMixDeal.bjid) {
            FightingMixDeal.bjid = id;
            FightingMixDeal.bj = new ImageIcon("resource/map/" + FightingMixDeal.bjid + ".png").getImage();
        }
        if (FightingMixDeal.yq == null || FightingMixDeal.nq == null|| FightingMixDeal.xy == null) {
            FightingMixDeal.yq = new ImageIcon("resource/map/yq.png").getImage();
            FightingMixDeal.nq = new ImageIcon("resource/map/nq.png").getImage();
            FightingMixDeal.xy = new ImageIcon("resource/map/xuanbao.png").getImage();
        }
    }
    
    public static void Drawing(Graphics2D g, long dietime) {
        try {
            if ((boolean)FightingMixDeal.b) {
                g.drawImage(FightingMixDeal.bj, 0, 0, ScrenceUntil.Screen_x, ScrenceUntil.Screen_y, null);
            }
            for (int i = 0, size = FightingMixDeal.CurrentData.size(); i < size; ++i) {
                g.setColor(Color.yellow);
                FightingMixDeal.CurrentData.get(i).Drawing(g, dietime);
                if (FightingMixDeal.State <= 3) {
                    FightingMixDeal.CurrentData.get(i).drawOperationTime(g);
                }
            }
            for (int i = 0; i < FightingMixDeal.skills.size(); ++i) {
                FightingMixDeal.skills.get(i).draw(g);
            }
            for (int i = 0, size = FightingMixDeal.CurrentData.size(); i < size; ++i) {
                FightingMixDeal.CurrentData.get(i).Drawing2(g, dietime);
            }
            for (int i = 0, size = FightingMixDeal.CurrentData.size(); i < size; ++i) {
                FightingMixDeal.CurrentData.get(i).drawSay(g);
            }
            FightingMixDeal.buffUtil.draw(g);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            if (FightingMixDeal.State <= 2) {
                g.setFont(new Font("隶书", 1, 64));
                long surplus = 60L - FightingMixDeal.time / 1000L;
                String times = (surplus < 0L) ? "0" : (surplus + "");
                g.setColor(UIUtils.COLOR_FightingRound_Black);
                g.drawString(times, ScrenceUntil.Screen_x / 2 + 1, 111);
                g.setColor(Color.RED);
                g.drawString(times, ScrenceUntil.Screen_x / 2, 110);
            }
            g.setFont(UIUtils.TEXT_HYK20);
            g.setColor(UIUtils.COLOR_FightingRound_Black);
            g.drawString(FightingMixDeal.di, 30, 85);
            g.drawString(FightingMixDeal.bout, 50, 85);
            g.setColor(UIUtils.COLOR_FightingRound);
            g.drawString(FightingMixDeal.di, 30, 84);
            g.drawString(FightingMixDeal.bout, 50, 85);
            if (FightingMixDeal.seInt != FightingMixDeal.CurrentRound) {
                FightingMixDeal.seInt = FightingMixDeal.CurrentRound;
                FightingMixDeal.se = Integer.toString(FightingMixDeal.CurrentRound);
            }
            g.setFont(UIUtils.TEXT_FONT3);
            g.setColor(UIUtils.COLOR_FightingRound_Black);
            g.drawString(FightingMixDeal.se, 51, 84);
            g.setColor(Color.yellow);
            g.drawString(FightingMixDeal.se, 51, 85);
            g.setFont(UIUtils.TEXT_FONT221);
            g.setColor(UIUtils.COLOR_NAME4_PET);
            if (FightingMixDeal.yqz != null) {
                g.drawImage(FightingMixDeal.yq, ScrenceUntil.Screen_x / 2 - 50, 15, null);
                g.drawString(FightingMixDeal.yqz, ScrenceUntil.Screen_x / 2 - 27, 33);
            }
            g.setFont(UIUtils.TEXT_FONT221);
            g.setColor(UIUtils.COLOR_NAME3);
            if (FightingMixDeal.nqz != null) {
                g.drawImage(FightingMixDeal.nq, ScrenceUntil.Screen_x / 2 + 30, 15, null);
                g.drawString(FightingMixDeal.nqz, ScrenceUntil.Screen_x / 2 + 54, 33);
            }
            g.setFont(UIUtils.TEXT_FONT221);
            g.setColor(UIUtils.COLOR_NAME3);
            if (FightingMixDeal.nqz != null) {
                g.drawImage(FightingMixDeal.xy, ScrenceUntil.Screen_x / 2 + 110, 11, null);
                g.drawString(FightingMixDeal.xyz, ScrenceUntil.Screen_x / 2 + 145, 30);
            }
        }
        catch (Exception ex) {}
    }
    
    public static void changeState(int state) {
        if (state == 6 || state == 1 || state == 2 || state == 3) {
            FightingMixDeal.CorrectTime = 0L;
        }
        FightingMixDeal.State = state;
        if (FightingMixDeal.State == 1) {
            for (int i = 0, size = FightingMixDeal.CurrentData.size(); i < size; ++i) {
                ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setOperation(false);
            }
        }
    }
    
    public static void qingchusiwang() {
        int bbman = myman() + 5;
        for (int i = FightingMixDeal.CurrentData.size() - 1; i >= 0; --i) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (data.getHp_Current() <= 0 && data.getType() != 0 && data.getType() != 2) {
                if (isFightType() && data.getType() == 1 && FightingMixDeal.camp == data.getCamp() && bbman == data.getMan() && TimeLimit.getLimits().getLimit("VIP") == null && TimeLimit.getLimits().getLimit("JVIP") == null) {
                    RoleSummoning bb = Article.bb(data.getId());
                    if (bb != null) {
                        bb.setBasishp((long)PetProperty.getPetHMASp(bb)[0]);
                        bb.setBasismp((long)PetProperty.getPetHMASp(bb)[1]);
                        bb.addFaithful(Integer.valueOf(-10));
                        Article.souxie(bb);
                        try {
                            SendRoleAndRolesummingUntil.sendRoleSumming(bb);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                FightingMixDeal.CurrentData.remove(i);
            }
        }
    }
    
    public static List<StateProgress> AnalysisFightingEvents(FightingEvents fightingEvents, int JD) {
        List<StateProgress> States = new ArrayList<>();
        try {
            FightingState Originator = fightingEvents.getOriginator();
            if (Originator != null) {
                String type = Originator.getStartState();
                if (type.startsWith("召唤") || type.equals("召回") || type.equals("闪现")) {
                    StateProgress progress = EffectType.getEffectById(3).analysisAction(Originator, -1);
                    if (progress != null) {
                        if (fightingEvents.getSp() != null) {
                            progress.setSp(Double.valueOf(0.3));
                        }
                        States.add(progress);
                    }
                }
                else if (type.equals("发动灵宝合击")) {
                    FightingMixDeal.b = Boolean.valueOf(false);
                }
                else if (type.equals("结束灵宝合击")) {
                    FightingMixDeal.b = Boolean.valueOf(true);
                }
                else if (type.equals("逃跑成功") || type.equals("逃跑失败")) {
                    int path = CurrentData(Originator.getCamp(), Originator.getMan());
                    if (path != -1) {
                        StateProgress progress2 = EffectType.getEffectById(4).analysisAction(Originator, path);
                        SkillSpell skillSpell = new SkillSpell();
                        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(path);
                        if (type.equals("逃跑成功")) {
                            skillSpell = new SkillSpell(GetTcpPath.getSkillTcp("成功"), fightingimage.getX(), fightingimage.getY());
                        }
                        else if (type.equals("逃跑失败")) {
                            skillSpell = new SkillSpell(GetTcpPath.getSkillTcp("失败"), fightingimage.getX(), fightingimage.getY());
                        }
                        progress2.setSpell(skillSpell);
                        if (progress2 != null) {
                            if (fightingEvents.getSp() != null) {
                                progress2.setSp(Double.valueOf(0.3));
                            }
                            States.add(progress2);
                        }
                    }
                }
                else if (type.equals("捕捉失败") || type.equals("捕捉成功")) {
                    int path = CurrentData(Originator.getCamp(), Originator.getMan());
                    if (path != -1) {
                        StateProgress progress2 = EffectType.getEffectById(5).analysisAction(Originator, path);
                        if (progress2 != null) {
                            if (fightingEvents.getSp() != null) {
                                progress2.setSp(Double.valueOf(0.3));
                            }
                            States.add(progress2);
                        }
                    }
                }
            }
            else {
                System.err.println(fightingEvents.getAccepterlist().size());
                List<FightingState> list = fightingEvents.getAccepterlist();
                for (int i = 0; i < fightingEvents.getAccepterlist().size(); ++i) {
                    FightingState fightingState = (FightingState)fightingEvents.getAccepterlist().get(i);
                    String type2 = fightingState.getStartState();
                    StateProgress progress3 = null;
                    if (type2.startsWith("召唤") || type2.equals("召回") || type2.equals("闪现")) {
                        progress3 = EffectType.getEffectById(3).analysisAction(fightingState, -1);
                        if (fightingEvents.getSp() != null) {
                            progress3.setSp(Double.valueOf(0.3));
                        }
                    }
                    else {
                        int path2 = CurrentData(fightingState.getCamp(), fightingState.getMan());
                        if (path2 != -1) {
                            if (type2.equals("移动") || type2.equals("瞬移") || type2.equals("遁地")) {
                                progress3 = EffectType.getEffectById(1).analysisAction(fightingState, path2);
                                if (fightingEvents.getSp() != null) {
                                    progress3.setSp(Double.valueOf(0.3));
                                }
                            }
                            else if (type2.equals("技能移动")) {
                                progress3 = EffectType.getEffectById(6).analysisAction(fightingState, path2);
                                if (fightingEvents.getSp() != null) {
                                    progress3.setSp(Double.valueOf(0.3));
                                }
                            }
                            else {
                                progress3 = EffectType.getEffectById(2).analysisAction(fightingState, path2);
                            }
                        }
                    }
                    if (progress3 != null) {
                        if (progress3.getSpell() != null) {
                            progress3.getSpell().setRound(JD);
                        }
                        States.add(progress3);
                        if (fightingEvents.getSp() != null) {
                            progress3.setSp(Double.valueOf(0.3));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.err.println(GsonUtil.getGsonUtil().getgson().toJson(fightingEvents));
            e.printStackTrace();
        }
        return States;
    }
    
    public static void depath(int mycamp, int myman) {
        int bbman = myman() + 5;
        int i = 0;
        while (i < FightingMixDeal.CurrentData.size()) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (data.getCamp() == mycamp && data.getMan() == myman) {
                if (FightingMixDeal.camp == data.getCamp() && bbman == data.getMan()) {
                    RoleSummoning bb = Article.bb(data.getId());
                    if (bb != null && isFightType() && TimeLimit.getLimits().getLimit("VIP") == null && TimeLimit.getLimits().getLimit("JVIP") == null) {
                        if (data.getHp_Current() <= 0) {
                            bb.setBasishp((long)PetProperty.getPetHMASp(bb)[0]);
                            bb.setBasismp((long)PetProperty.getPetHMASp(bb)[1]);
                            bb.addFaithful(Integer.valueOf(-10));
                            Article.souxie(bb);
                        }
                        SendRoleAndRolesummingUntil.sendRoleSumming(bb);
                    }
                }
                FightingMixDeal.CurrentData.remove(i);
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public static void RoundFighting() {
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(34);
        FormsManagement.HideForm(631);
        FormsManagement.HideForm(603);
        FormsManagement.HideForm(6);
        ZhuFrame.getZhuJpanel().HideBeastBtn();
    }
    
    public static void RoundDecision() {
        FightingMixDeal.time = 0L;
        ++FightingMixDeal.CurrentRound;
        ZhuFrame.getZhuJpanel();
        if (ZhuJpanel.getZidong().getText().equals("取消") && FightingMixDeal.zdhh-- <= 0) {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("自动");
        }
        ZhuFrame.getZhuJpanel().HideBeastBtn();
        if (FightingMixDeal.camp != -1) {
            ZhuFrame.getZhuJpanel().ShowManBtn(isLL());
            changeState(FightingMixDeal.State = 1);
        }
        else {
            changeState(FightingMixDeal.State = 3);
        }
        for (int i = FightingMixDeal.CurrentData.size() - 1; i >= 0; --i) {
            ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getmsg();
        }
    }
    
    public static void FightingEnd() {
        GameJpanel.alpha = 0.3f;
        changeState(FightingMixDeal.State = 0);
        FightingMixDeal.BattlefieldPlay.clear();
        FightingMixDeal.time = 0L;
        FightingMixDeal.CorrectTime = 0L;
        FightingMixDeal.CurrentRound = 0;
        if (Music.MusicNew != null) {
            if (Music.MusicNew.equals("1")) {
                Music.addbeijing(((Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(ImageMixDeal.userimg.getRoleShow().getMapid() + "")).getMusic() + "o.mp3");
            }
            else if (Music.MusicNew.equals("2")) {
                Music.addbeijing(((Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(ImageMixDeal.userimg.getRoleShow().getMapid() + "")).getMusic() + ".mp3");
            }
        }
        OutFighting();
    }
    
    public static void FightingEnd(BattleEnd battleEnd) {
        GameJpanel.alpha = 0.3f;
        changeState(FightingMixDeal.State = 0);
        FightingMixDeal.BattlefieldPlay.clear();
        FightingMixDeal.time = 0L;
        FightingMixDeal.CorrectTime = 0L;
        FightingMixDeal.CurrentRound = 0;
        Music.addbeijing(((Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(ImageMixDeal.userimg.getRoleShow().getMapid() + "")).getMusic() + ".mp3");
        if (battleEnd == null) {
            FightingEndD fightingEndD = new FightingEndD();
            fightingEndD.setFightingsumber(FightingMixDeal.FightingNumber);
            String sendMes = Agreement.FightingendAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingEndD));
            SendMessageUntil.toServer(sendMes);
        }
        else if (FightingMixDeal.camp != -1) {
            FrameMessageChangeJpanel.addtext(6, (battleEnd.getCamp() == FightingMixDeal.camp) ? "战斗胜利" : "战斗失败", null, null);
            if (battleEnd.getTaskDaily() != null) {
                PKSys.getPkSys().upPK(battleEnd.getTaskDaily());
            }
            AssetControl.asset(battleEnd.getAssetUpdate());
            Hero.getHero().addTask(battleEnd.getTaskn());
            if (battleEnd.getMsg() != null && !battleEnd.getMsg().equals("")) {
                String[] vs = battleEnd.getMsg().split("\\|");
                for (int i = 0; i < vs.length; ++i) {
                    FrameMessageChangeJpanel.addtext(6, vs[i], null, null);
                }
            }
            if (battleEnd.getArenaBean() != null) {
                PartnerArenaJframe.getPartnerArenaJframe().getPartnerArenaMainPanel().showView(battleEnd.getArenaBean());
            }
        }
        OutFighting();
    }
    
    public static int myman() {
        return FightingMixDeal.man;
    }
    
    public static Fightingimage getdata(int type) {
        return CurrentDataImage(FightingMixDeal.camp, myman() + type * 5);
    }
    
    public static boolean MyBeastLifeAndDeath() {
        return CurrentDataImage(FightingMixDeal.camp, myman() + 5) != null;
    }
    
    public static boolean isLL() {
        Fightingimage fightingimage = CurrentDataImage(FightingMixDeal.camp, FightingMixDeal.man + 5);
        if (fightingimage == null || fightingimage.getFightingManData().getHp_Current() <= 0) {
            fightingimage = CurrentDataImage(FightingMixDeal.camp, FightingMixDeal.man);
            if (fightingimage == null || fightingimage.getFightingManData().getHp_Current() <= 0) {
                return true;
            }
        }
        return false;
    }
    
    public static int CurrentData(int camp, int man) {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getCamp() == camp && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getMan() == man) {
                return i;
            }
        }
        return -1;
    }
    
    public static Fightingimage CurrentDataImage(int camp, int man) {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(i);
            if (fightingimage.getFightingManData().getCamp() == camp && fightingimage.getFightingManData().getMan() == man) {
                return (Fightingimage)FightingMixDeal.CurrentData.get(i);
            }
        }
        return null;
    }
    
    public static FightingState FightingState(String type, int type1) {
        if (type1 == 0) {
            return new FightingState(FightingMixDeal.camp, myman(), type);
        }
        return new FightingState(FightingMixDeal.camp, myman() + 5, type);
    }
    
    public static void CurrentData(List<FightingManData> fightingManDatas, String buff, FightingManData mydata) {
        FightingMixDeal.buffUtil.buffs.clear();
        int my_camp = -1;
        int my_man = -1;
        if (mydata != null) {
            my_camp = mydata.getCamp();
            my_man = mydata.getMan();
            FightingMixDeal.nqz = mydata.getNqz() + "";
            FightingMixDeal.yqz = mydata.getYqz() + "";
            FightingMixDeal.xyz = mydata.getXy() + "";
            FightingMixDeal.buffUtil.SXSkill(mydata.cxxx("技能"));
        }
        else {
            FightingMixDeal.buffUtil.skills.clear();
        }
        FightingMixDeal.buffUtil.CS(buff, my_camp);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        Gamemap gamemap = UserMessUntil.getAllmapbean().getAllMap().get(loginResult.getMapid() + "");
        CgImg(Integer.valueOf(gamemap.getBackground()));
        FightingMixDeal.camp = my_camp;
        FightingMixDeal.man = my_man;
        if (FightingMixDeal.CurrentData == null) {
            FightingMixDeal.CurrentData = new ArrayList<>();
        }
        else {
            FightingMixDeal.CurrentData.clear();
        }
        int xz = 15;
        while (fightingManDatas.size() != 0) {
            for (int i = fightingManDatas.size() - 1; i >= 0; --i) {
                if (fightingManDatas.get(i).getMan() >= xz) {
                    FightingMixDeal.CurrentData.add(new Fightingimage(fightingManDatas.remove(i), my_camp));
                }
            }
            if (xz > 10) {
                xz -= 5;
            }
            else {
                --xz;
            }
        }
        if (FightingMixDeal.camp == -1) {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("离开");
        }
        else if (FightingMixDeal.zdhh > 0) {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("取消");
        }
        else {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("自动");
        }
        ZhuFrame.getZhuJpanel().intoFighting();
        if (FightingMixDeal.BattleType == 101) {
            if (FormsManagement.getInternalForm2(5) != null && FormsManagement.getframe(5).isVisible()) {
                FormsManagement.HideForm(5);
            }
        }
        else if (FightingMixDeal.BattleType == 103 && FormsManagement.getInternalForm2(111) != null && FormsManagement.getframe(111).isVisible()) {
            FormsManagement.HideForm(111);
        }
    }
    
    public static void OutFighting() {
        int hpz = RoleProperty.getHp(RoleData.getRoleData().getLoginResult());
        int mpz = RoleProperty.getMp(RoleData.getRoleData().getLoginResult());
        for (RoleSummoning roleSummoning : UserMessUntil.getPetListTable()) {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && roleSummoning.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                UserMessUntil.setChosePetMes(roleSummoning);
                PetPanelBtn.canzhan1();
                break;
            }
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (getMp().contains("玉枢返虚丸")) {
            try {
                addPetFathifulMP(UserMessUntil.getChosePetMes());
                loginResult.setHp(new BigDecimal(RoleProperty.getHp(loginResult)));
                loginResult.setMp(new BigDecimal(RoleProperty.getMp(loginResult)));
                String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
                SendMessageUntil.toServer(mes);
                PetAddPointMouslisten.getplayerValue();
                loginResult.setBskvalue(null);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            PetAddPointMouslisten.getplayerValue();
            loginResult.setBskvalue(null);
        }
        if (getHp().contains("六脉化神丸")) {
            try {
                addPetFathifulHP(UserMessUntil.getChosePetMes());
                loginResult.setHp(new BigDecimal(RoleProperty.getHp(loginResult)));
                loginResult.setMp(new BigDecimal(RoleProperty.getMp(loginResult)));
                String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
                SendMessageUntil.toServer(mes);
                PetAddPointMouslisten.getplayerValue();
                loginResult.setBskvalue(null);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            PetAddPointMouslisten.getplayerValue();
            loginResult.setBskvalue(null);
        }
        ImageMixDeal.userimg.getRoleShow().getPlayer_Paths().clear();
        if (ImageMixDeal.userimg.getRoleShow().getFly_id() > 0 && ZhuJpanel.getListFly() != null && ZhuJpanel.getListFly().size() > 0) {
            for (Fly fly : ZhuJpanel.getListFly()) {
                if ((int)fly.getFlytid() == ImageMixDeal.userimg.getRoleShow().getFly_id()) {
                    System.out.println(fly.getFuel());
                    if ((long)fly.getFuel() > 0L) {
                        String sendMesa = Agreement.useflyAgreement("flyxh|" + fly.getMid());
                        SendMessageUntil.toServer(sendMesa);
                        ZhuFrame.getZhuJpanel().addPrompt2("消耗了10点燃灵值!");
                    }
                    if ((long)fly.getFuel() <= 0L) {
                        ZhuFrame.getZhuJpanel().addPrompt2("燃灵值不足已无法飞行，请及补充!");
                        ImageMixDeal.userimg.getRoleShow().setFly_id(0);
                        ManimgAttribute.isdaiji = true;
                        ImageMixDeal.userimg.changeskin(null);
                        ManimgAttribute.ISFLY = false;
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        FightingMixDeal.buffUtil.isMcqh = false;
        FightingMixDeal.CurrentData.clear();
        FightingMixDeal.BattlefieldPlay.clear();
        FightingMixDeal.skills.clear();
        FightingMixDeal.Music1 = "";
        FightingMixDeal.FightingNumber = -1;
        SpriteFactory.ResetAndRemove();
        FightingMixDeal.nqz = null;
        FightingMixDeal.yqz = null;
        ZhuFrame.getZhuJpanel().outFighting();
        FightingMixDeal.buffUtil.buffs.clear();
        TimeControlRunnable.addAllTask(null);
    }
    
    public static void nameColor(int x, int y) {
        FightingMixDeal.x = x;
        FightingMixDeal.y = y;
        if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2 || FightingMixDeal.State == 5) {
            for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
                if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() < 3) {
                    if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).isContains(x, y)) {
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getPart().setGl(Boolean.valueOf(true));
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setisHigh(true);
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setNameColor(UIUtils.COLOR_NPC_SELECT);
                        MousePosUtil.setRoundInfo(FightingMixDeal.CurrentRound, ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getCamp(), ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getMan());
                    }
                    else {
                        if (FormsManagement.getframe(635).isVisible()) {
                            FormsManagement.HideForm(635);
                        }
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getPart().setGl(Boolean.valueOf(false));
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setisHigh(false);
                        ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setDefaultNameColor();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
                ((Fightingimage)FightingMixDeal.CurrentData.get(i)).setDefaultNameColor();
            }
        }
    }
    
    public static void status() {
        if (FightingMixDeal.x == -1 || FightingMixDeal.y == -1) {
            return;
        }
        if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2) {
            for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
                if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() != 3 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() != 4 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).isContains(FightingMixDeal.x, FightingMixDeal.y)) {
                    MsgJframe.getJframe().getJapnel().battleState(((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData());
                }
            }
        }
    }
    
    public static void mouseEnteredMonitor(int x, int y) {
        SkillTx skillTx = FightingMixDeal.buffUtil.MonitorBuff(x, y);
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(603);
        if (skillTx != null) {
            int id = skillTx.getId();
            if (id >= 22000 && id <= 23000) {
                MsgJframe1.getJframe1().getJapnel1().smx(skillTx.getSkill(), 1.0, 200);
            }
            else if (id >= 23000 && id < 23010) {
                MsgJframe.getJframe().getJapnel().SM2(skillTx.getSkill());
            }
            else if (id >= 9001 && id <= 10166) {
                MsgJframe.getJframe().getJapnel().SM3(skillTx.getSkill());
            }
        }
    }
    
    public static void MonitorTrigger(int x, int y) {
        if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2) {
            for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
                if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() != 3 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() != 4 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).isContains(x, y)) {
                    FightingMonitor.Fighting((Fightingimage)FightingMixDeal.CurrentData.get(i));
                    return;
                }
            }
            SkillTx skillTx = FightingMixDeal.buffUtil.MonitorBuff(x, y);
            if (skillTx != null) {
                if (skillTx.isIs()) {
                    return;
                }
                if (FightingMixDeal.State != 1) {
                    return;
                }
                Fightingimage fightingimage = getdata(0);
                if (fightingimage == null) {
                    return;
                }
                if (skillTx.getSkill().getSkillname().equals("天罡八卦") && FightingMixDeal.CurrentRound < 4) {
                    ZhuFrame.getZhuJpanel().addPrompt2("前3回合无法释放!" + skillTx.getSkill().getSkillname());
                    return;
                }
                if (skillTx.getId() >= 9001 && skillTx.getId() <= 9500 && fightingimage.getFightingManData().getNqz() < 100) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你怒气值还未达到100,无法释放" + skillTx.getSkill().getSkillname());
                    return;
                }
                FightOperation operation = new FightOperation();
                operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), 1, skillTx.getSkill().getSkillname());
                FightingEvents events = FightingMonitor.AttackGenerate(operation);
                FightingMonitor.FightingOperation(events);
                skillTx.setIs(true);
                ZhuFrame.getZhuJpanel().addPrompt2("你释放了" + skillTx.getSkill().getSkillname());
                return;
            }
        }
    }
    
    public static void LoadMusic(String yinyue) {
        if (yinyue != null && !yinyue.equals("") && !FightingMixDeal.Music1.equals(yinyue)) {
            FightingMixDeal.Music1 = yinyue;
            Music.addyinxiao(yinyue + ".mp3");
        }
    }
    
    public static Fightingimage Randomdata(boolean similar, boolean my, int man) {
        int cp = similar ? FightingMixDeal.camp : EnemyCamp(FightingMixDeal.camp);
        Fightingimage fightingimage = null;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (cp == data.getCamp() && (!similar || !my || man != data.getMan())) {
                a.add(Integer.valueOf(i));
            }
        }
        if (a.size() != 0) {
            fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get((int)a.get(Util.random.nextInt(a.size())));
        }
        return fightingimage;
    }
    
    public static int EnemyCamp(int camp) {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getCamp() != camp) {
                return ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getCamp();
            }
        }
        return -1;
    }
    
    public static void Dialogue(String name, String text) {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() == 0 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getManname().equals(name)) {
                ((Fightingimage)FightingMixDeal.CurrentData.get(i)).Dialogue(text);
                return;
            }
        }
    }
    
    public static int zyd(int camp) {
        int size = 0;
        for (int i = FightingMixDeal.CurrentData.size() - 1; i >= 0; --i) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (camp == data.getCamp() && data.getType() == 0 && data.getHp_Current() <= 0) {
                ++size;
            }
        }
        return size;
    }
    
    public static List<String> dwd(int camp) {
        List<String> buffer = new ArrayList<>();
        int i = FightingMixDeal.CurrentData.size() - 1;
        while (i >= 0) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (camp == data.getCamp() && data.getType() == 0 && data.getMan() == 2) {
                buffer.add(data.getManname());
                break;
            }
            else {
                --i;
            }
        }
        for (i = FightingMixDeal.CurrentData.size() - 1; i >= 0; --i) {
            FightingManData data = ((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData();
            if (camp == data.getCamp() && data.getType() == 0 && data.getMan() != 2) {
                buffer.add(data.getManname());
            }
        }
        return buffer;
    }
    
    public static boolean isFightType() {
        return FightingMixDeal.BattleType != 5 && FightingMixDeal.BattleType != 11 && FightingMixDeal.BattleType != 12;
    }
    
    public static void addPetFathifulHP(RoleSummoning roleSummoning) throws IOException {
        int[] pets = PetProperty.getPetHMASp(roleSummoning);
        if (roleSummoning != null) {
            long petHp = roleSummoning.getBasishp();
            long sumhp = (long)pets[0];
            if (petHp < sumhp) {
                roleSummoning.setBasishp(sumhp);
                NpcCureBean npcCureBean = new NpcCureBean();
                npcCureBean.setRoleSummoning(roleSummoning);
                UserMessUntil.setChosePetMes(roleSummoning);
                String messString = Agreement.npccureAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcCureBean));
                SendMessageUntil.toServer(messString);
                Article.souxie(UserMessUntil.getChosePetMes());
                PetAddPointMouslisten.showPetValue();
            }
        }
    }
    
    public static void addPetFathifulMP(RoleSummoning roleSummoning) throws IOException {
        int[] pets = PetProperty.getPetHMASp(roleSummoning);
        if (roleSummoning != null) {
            long petMp = roleSummoning.getBasismp();
            int summp = pets[1];
            if (petMp < (long)summp) {
                roleSummoning.setBasismp((long)summp);
                NpcCureBean npcCureBean = new NpcCureBean();
                npcCureBean.setRoleSummoning(roleSummoning);
                UserMessUntil.setChosePetMes(roleSummoning);
                String messString = Agreement.npccureAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcCureBean));
                SendMessageUntil.toServer(messString);
                Article.souxie(UserMessUntil.getChosePetMes());
                PetAddPointMouslisten.showPetValue();
            }
        }
    }
    
    public static String getMp() {
        String msg = "";
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String v = data.getTimingGood();
        if (v == null || v.equals("")) {
            return "X";
        }
        String[] vs = v.split("\\^");
        int i = 0;
        while (i < vs.length) {
            String[] vss = vs[i].split("#");
            Limit limit = new Limit();
            for (int j = 0; j < vss.length; ++j) {
                if (j == 0) {
                    limit.setName(vss[j]);
                }
                else if (j == 1) {
                    limit.setType(vss[j]);
                }
                else if (j == 2) {
                    limit.setSkin(vss[j]);
                }
                else if (j == 3) {
                    long time = Long.parseLong(vss[j]);
                    if (time < 100000000L) {
                        time *= 60000L;
                    }
                    else if (time < 2000000000L) {
                        time *= 1000L;
                    }
                    limit.setTime(time);
                }
                else if (j == 4) {
                    limit.setValue(vss[j]);
                }
            }
            if (limit.getType().contains("玉枢返虚丸")) {
                msg = limit.getType();
                break;
            }
            else {
                ++i;
            }
        }
        return msg;
    }
    
    public static String getHp() {
        String msg = "";
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String v = data.getTimingGood();
        if (v == null || v.equals("")) {
            return "X";
        }
        String[] vs = v.split("\\^");
        int i = 0;
        while (i < vs.length) {
            String[] vss = vs[i].split("#");
            Limit limit = new Limit();
            for (int j = 0; j < vss.length; ++j) {
                if (j == 0) {
                    limit.setName(vss[j]);
                }
                else if (j == 1) {
                    limit.setType(vss[j]);
                }
                else if (j == 2) {
                    limit.setSkin(vss[j]);
                }
                else if (j == 3) {
                    long time = Long.parseLong(vss[j]);
                    if (time < 100000000L) {
                        time *= 60000L;
                    }
                    else if (time < 2000000000L) {
                        time *= 1000L;
                    }
                    limit.setTime(time);
                }
                else if (j == 4) {
                    limit.setValue(vss[j]);
                }
            }
            if (limit.getType().contains("六脉化神丸")) {
                msg = limit.getType();
                break;
            }
            else {
                ++i;
            }
        }
        return msg;
    }
    
    public static List<AddState> getManBuffs(int camp, int man) {
        ArrayList<AddState> result = new ArrayList<>();
        if (FightingMixDeal.mansState != null && FightingMixDeal.mansState.size() > 0) {
            for (ManStateData msd : FightingMixDeal.mansState) {
                if (msd.getCamp() == camp && msd.getMan() == man && msd.getAddStates() != null && msd.getAddStates().size() > 0) {
                    for (AddState addState : msd.getAddStates()) {
                        if (addState.getSkills() != null && addState.getSkills().size() > 0 && ((FightingSkill)addState.getSkills().get(0)).getSkillid() >= 11001 && ((FightingSkill)addState.getSkills().get(0)).getSkillid() <= 11041) {
                            continue;
                        }
                        else if (!addState.getStatename().equals("mj3") && !addState.getStatename().equals("tj3")) {
                            if (addState.getStatename().equals("rj3")) {
                                continue;
                            }
                            else {
                                result.add(addState);
                            }
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
        }
        return (List)result;
    }
    
    static {
        FightingMixDeal.CorrectTime = 0L;
        FightingMixDeal.roundType = 0L;
        FightingMixDeal.State = 0;
        FightingMixDeal.BattlefieldPlay = new HashMap<>();
        FightingMixDeal.skills = new ArrayList<>();
        FightingMixDeal.CurrentData = new ArrayList<>();
        FightingMixDeal.mansState = new ArrayList<>();
        FightingMixDeal.systime = System.currentTimeMillis();
        FightingMixDeal.cha = 0L;
        FightingMixDeal.buffUtil = new BuffUtil();
        FightingMixDeal.camp = 1;
        FightingMixDeal.man = -1;
        FightingMixDeal.zdhh = 0;
        FightingMixDeal.Music1 = "";
        FightingMixDeal.MAX = 5;
        FightingMixDeal.di = "第  ";
        FightingMixDeal.bout = "  回合";
        FightingMixDeal.se = Integer.toString(FightingMixDeal.CurrentRound);
        FightingMixDeal.seInt = FightingMixDeal.CurrentRound;
        FightingMixDeal.b = Boolean.valueOf(true);
        FightingMixDeal.x = -1;
        FightingMixDeal.y = -1;
    }
}
