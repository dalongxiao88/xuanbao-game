package com.tool.image;

import org.come.until.ScrenceUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.AircraftJframe;
import javax.swing.ImageIcon;
import come.tool.Fighting.FightingMixDeal;
import java.awt.FontMetrics;
import io.netty.util.internal.StringUtil;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import org.come.model.Door;
import org.come.npc.TP;
import java.util.Iterator;
import come.tool.Fighting.FightingMove2;
import org.come.entity.Fly;
import org.come.Jpanel.ZhuJpanel;
import org.come.thread.TimeControlRunnable;
import com.tool.tcp.Sprite;
import com.tool.tcp.GetTcpPath;
import come.tool.Fighting.Fightingimage;
import java.awt.Graphics;
import org.come.until.Util;
import org.come.bean.PathPoint;
import org.come.model.Robots;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;
import com.tool.tcpimg.UIUtils;
import java.util.Random;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.bean.LoginResult;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.come.until.AccessTeamInfoUntil;
import java.util.ArrayList;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.TxZJ;
import org.come.bean.DivineWalk;
import come.tool.Fighting.sidian;
import com.tool.tcpimg.flywalktcp;
import com.tool.tcpimg.Flyruntcp;
import com.tool.tcpimg.flytcp;
import java.awt.Color;
import org.come.entity.RoleSummoning;
import com.tool.tcpimg.Effects;
import com.tool.tcpimg.FloatPanel;
import java.util.List;
import java.awt.Image;
import com.tool.tcp.NewPart;
import com.tool.ModerateTask.TaskProgress;
import org.come.bean.MapMonsterBean;
import org.come.bean.NpcInfoBean;
import org.come.bean.RoleShow;

public class ManimgAttribute
{
    public static boolean ISTCP;
    public static boolean ISNAME;
    private RoleShow roleShow;
    private NpcInfoBean npc;
    private MapMonsterBean mapMonsterBean;
    private TaskProgress taskdata;
    private int leixing;
    private long time;
    private int dir;
    private NewPart part;
    private Image image;
    private int x;
    private int y;
    private List<FloatPanel> chatPanels;
    private String name;
    private String[] names;
    private String[] appellation;
    private String[] taskIds;
    private static List<Effects> Effectslist;
    private RoleSummoning roleSummoning;
    private Color nameColor;
    public static boolean isdaiji;
    public static boolean ISFLY;
    public static boolean IS;
    public static String flyskin;
    private flytcp flytcp;
    private Flyruntcp flyruntcp;
    private flywalktcp flywalktcp;
    public static int yys;
    public static int lx;
    private int flyX;
    private int flyY;
    public static String XNMSXZSXF;
    public boolean isHigh;
    public boolean exit;
    public static NewPart part3;
    public static NewPart part4;
    public static NewPart part5;
    public static List<NewPart> partList;
    boolean m;
    public static boolean start;
    public static String zhongjiang;
    public static String[] dianshu;
    private int divineWalkCount;
    private long movejiange;
    private List<sidian> moves;
    private List<DivineWalk> list;
    private String[] teams;
    private TxZJ txZJ;
    public static Image[] hzImgs;
    int size1;
    
    public Color getNameColor() {
        return this.nameColor;
    }
    
    public void setNameColor(Color nameColor) {
        this.nameColor = nameColor;
    }
    
    public void setDefaultNameColor() {
        this.nameColor = null;
    }
    
    public void changeskin(String skin) {
        boolean isZJ = false;
        this.part = null;
        if (this.roleShow != null && this.roleShow.getSkin() != null && !this.roleShow.getSkin().equals("")) {
            String[] vs = this.roleShow.getSkin().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if ((vs[i].startsWith("GW") || vs[i].startsWith("SGW")) && this.roleShow.getMount_id() == 0 && this.roleShow.getCar_id() == 0) {
                    try {
                        String[] ts = vs[i].substring(vs[i].startsWith("GW") ? 2 : 3).split("_");
                        if (ts != null) {
                            String color = null;
                            if (ts[1].contains("#")) {
                                String[] v = ts[1].split("#");
                                ts[1] = v[0];
                                color = v[1].replace("^", "|");
                            }
                            NewPart newPart = SpriteFactory.createPart(Long.parseLong(ts[1]), 2, 2, color);
                            if (this.part == null) {
                                this.part = newPart;
                            }
                            else {
                                this.part = this.part.addPart(newPart);
                            }
                        }
                    }
                    catch (Exception e) {}
                }
                else if (vs[i].startsWith("X") || vs[i].startsWith("P")) {
                    String[] ts = vs[i].substring(1).split("_");
                    NewPart newPart2 = SpriteFactory.createPart("tx/tx" + ts[0], -2, Integer.parseInt(ts[1]), null);
                    if (this.part == null) {
                        this.part = newPart2;
                    }
                    else {
                        this.part = this.part.addPart(newPart2);
                    }
                }
                else if (vs[i].startsWith("J")) {
                    isZJ = true;
                    if (this.txZJ == null) {
                        this.txZJ = new TxZJ("tx/tx" + vs[i].substring(1));
                    }
                    else {
                        this.txZJ.setSkin("tx/tx" + vs[i].substring(1));
                    }
                }
                else if (vs[i].startsWith("S")) {
                    skin = vs[i].substring(1);
                }
                else if (vs[i].startsWith("C")) {
                    NewPart newPart3 = SpriteFactory.createPart("tx/" + vs[i].substring(1), -2, 6, null);
                    if (this.part == null) {
                        this.part = newPart3;
                    }
                    else {
                        this.part = this.part.addPart(newPart3);
                    }
                }
                else if (vs[i].startsWith("B")) {
                    String cb = vs[i].substring(1);
                    NewPart newPart2 = SpriteFactory.createPart("tx/" + cb + "0", -2, -5, null);
                    if (this.part == null) {
                        this.part = newPart2;
                    }
                    else {
                        this.part = this.part.addPart(newPart2);
                    }
                    newPart2 = SpriteFactory.createPart("tx/" + cb + "1", -2, 5, null);
                    this.part = this.part.addPart(newPart2);
                }
            }
        }
        if (skin != null && (this.roleShow == null || this.roleShow.getMount_id() == 0 || this.roleShow.getCar_id() == 0 || skin.length() < 10)) {
            if (this.roleShow != null) {
                if (this.roleShow.getFly_id() != 0) {
                    String roleskin = null;
                    String roleskin2 = this.roleShow.getSpecies_id().toString();
                    String[] vs2 = { "23002", "20001", "21001", "21002", "21003", "20004", "20005", "20006", "20007", "20010", "21004", "21005", "21006", "22001", "22002", "22004", "22006", "23005", "24001", "24002", "24003", "24004", "24005", "24006", "20008" };
                    String[] vs3 = { "2300205", "2000102", "2100112", "2100210", "2100310", "2000408", "2000503", "2000610", "2000701", "2001001", "2100409", "2100501", "2100608", "2200103", "2200201", "2200405", "2200811", "2300511", "2400101", "2400210", "2400311", "2400403", "2400512", "2400601", "2000801" };
                    int j = vs2.length - 1;
                    while (j >= 0) {
                        if (roleskin2.equals(vs2[j])) {
                            roleskin = vs3[j];
                            break;
                        }
                        else {
                            roleskin = this.roleShow.getSpecies_id().toString();
                            --j;
                        }
                    }
                    Mythread mythread = new Mythread();
                    if (this.roleShow.getSkin() != null) {
                        if (this.roleShow.getSkin().split("\\|").length > 1) {
                            roleskin2 = this.roleShow.getSkin().split("\\|")[0].replace("S", "");
                        }
                        else {
                            roleskin2 = this.roleShow.getSkin().replace("S", "");
                        }
                    }
                    NewPart newPart4 = SpriteFactory.createPart(roleskin2, 2, 1, null);
                    newPart4.setFly(ManimgAttribute.flyskin, 2, Boolean.valueOf(true));
                    if (ManimgAttribute.IS) {
                        mythread.start();
                    }
                    if (this.part == null) {
                        this.part = newPart4;
                    }
                    else {
                        this.part = this.part.addPart(newPart4);
                    }
                }
                else {
                    NewPart newPart5 = SpriteFactory.createPart(skin, 2, 1, null);
                    if (this.part == null) {
                        this.part = newPart5;
                    }
                    else {
                        this.part = this.part.addPart(newPart5);
                    }
                }
            }
            else {
                NewPart newPart5 = SpriteFactory.createPart(skin, 2, 1, null);
                if (this.part == null) {
                    this.part = newPart5;
                }
                else {
                    this.part = this.part.addPart(newPart5);
                }
            }
        }
        else if (this.roleShow.getMount_id() != 0 && this.roleShow.getFly_id() == 0 && this.roleShow.getCar_id() == 0 ) {
            NewPart newPart5 = SpriteFactory.createPart((long)this.roleShow.getMount_id() << 40 | this.roleShow.getSpecies_id().longValue(), 2, 1, null);
            if (this.part == null) {
                this.part = newPart5;
            }
            else {
                this.part = this.part.addPart(newPart5);
            }
        }
        else if (this.roleShow.getFly_id() != 0 && this.roleShow.getCar_id() == 0 ) {
            String roleskin = null;
            String roleskin2 = this.roleShow.getSpecies_id().toString();
            String[] vs2 = { "23002", "20001", "21001", "21002", "21003", "20004", "20005", "20006", "20007", "20010", "21004", "21005", "21006", "22001", "22002", "22004", "22006", "23005", "24001", "24002", "24003", "24004", "24005", "24006", "20008" };
            String[] vs3 = { "2300205", "2000102", "2100112", "2100210", "2100310", "2000408", "2000503", "2000610", "2000701", "2001001", "2100409", "2100501", "2100608", "2200103", "2200201", "2200405", "2200811", "2300511", "2400101", "2400210", "2400311", "2400403", "2400512", "2400601", "2000801" };
            int j = vs2.length - 1;
            while (j >= 0) {
                if (roleskin2.equals(vs2[j])) {
                    roleskin = vs3[j];
                    break;
                }
                else {
                    roleskin = this.roleShow.getSpecies_id().toString();
                    --j;
                }
            }
            Mythread mythread = new Mythread();
            NewPart newPart4 = SpriteFactory.createPart(roleskin2, 2, 1, null);
            newPart4.setFly(ManimgAttribute.flyskin, 2, Boolean.valueOf(true));
            if (ManimgAttribute.IS) {
                mythread.start();
            }
            if (this.part == null) {
                this.part = newPart4;
            }
            else {
                this.part = this.part.addPart(newPart4);
            }
        }

        else {
            NewPart newPart5 = SpriteFactory.createPart(this.roleShow.getSpecies_id().longValue(), 2, 1, null);
            if (this.part == null) {
                this.part = newPart5;
            }
            else {
                this.part = this.part.addPart(newPart5);
            }
        }
        if (this.roleShow!=null&&this.roleShow.getCar_id() != 0 ) {
            long skin111 = Long.parseLong(this.roleShow.getSpecies_id().longValue()+"999"+ this.roleShow.getCar_id());
            NewPart newPart5 = SpriteFactory.createPart(skin111, 2, 1, null);
            this.part = newPart5;
        }
        Mythread mythread2 = new Mythread();
        if (ManimgAttribute.isdaiji && !ManimgAttribute.IS) {
            mythread2.start();
        }
        if (!isZJ) {
            this.txZJ = null;
        }
    }
    
    public ManimgAttribute(RoleShow roleShow, int leixing) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.name = roleShow.getRolename();
            this.names = AccessTeamInfoUntil.getss(this.name);
            this.appellation = AccessTeamInfoUntil.getss(roleShow.getTitle());
            this.leixing = leixing;
            this.roleShow = roleShow;
            this.initTeam();
            this.changeskin(null);
            if (CollectionUtils.isNotEmpty(roleShow.getShowRoleSummoningList())) {
                ImageMixDeal.petMap.put(roleShow.getRolename(), roleShow.getShowRoleSummoningList().stream().filter(e/* org.come.entity.RoleSummoning, */ -> e.isShow()).map(e/* org.come.entity.RoleSummoning, */ -> new ManimgAttribute(e, 1)).collect(Collectors.toList()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void movePet(List<ManimgAttribute> pets) {
        ManimgAttribute my = this;
        for (int i = 0; i < pets.size(); ++i) {
            ManimgAttribute manimgAttribute = pets.get(i);
            if (manimgAttribute != null) {
                manimgAttribute.bh(my.getmove());
                my = manimgAttribute;
            }
        }
    }
    
    public ManimgAttribute(RoleSummoning roleSummoning, int leixing) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.name = roleSummoning.getSummoningname();
            this.names = AccessTeamInfoUntil.getss(this.name);
            this.leixing = leixing;
            this.roleSummoning = roleSummoning;
            this.part = roleSummoning.getPart();
            LoginResult loginResult = new LoginResult();
            loginResult.setX(Long.valueOf(0L));
            loginResult.setY(Long.valueOf(0L));
            loginResult.setGrade(Integer.valueOf(0));
            loginResult.setTurnAround(roleSummoning.getTurnRount());
            this.roleShow = new RoleShow(loginResult);
            this.getMoves();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ManimgAttribute(NpcInfoBean npcInfoBean) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.changeskin(npcInfoBean.getNpctable().getSkin());
            this.name = npcInfoBean.getNpctable().getNpcname();
            this.names = AccessTeamInfoUntil.getss(this.name);
            this.leixing = 2;
            this.npc = npcInfoBean;
            this.x = Integer.parseInt(this.npc.getNpctable().getTx());
            this.y = Integer.parseInt(this.npc.getNpctable().getTy());
            this.appellation = AccessTeamInfoUntil.getss(this.npc.getNpctable().getTitle());
            String dir = this.npc.getNpctable().getDir();
            if (dir == null || dir.trim().length() <= 0) {
                this.dir = this.dirtiao(1);
            }else{
                this.dir = this.dirtiao(Integer.parseInt(dir));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int npctou=0; // npc头顶
    public ManimgAttribute(NpcInfoBean npcInfoBean,String name) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.changeskin("1000");
            this.name = name;
            this.names = AccessTeamInfoUntil.getss(this.name);
            this.leixing = 2;
            this.npc = npcInfoBean;
            this.npctou=1;
            if (npc != null && npc.getNpctable() != null && StringUtils.isNotBlank(npc.getNpctable().getTx())) {
                x = Integer.parseInt(npc.getNpctable().getTx())-33;
            }
            if (npc != null && npc.getNpctable() != null && StringUtils.isNotBlank(npc.getNpctable().getTy())) {
                y = Integer.parseInt(npc.getNpctable().getTy())-157;
            }
            this.appellation = null;
            this.dir = 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void npcmsg() {
        String text = this.npc.getNpctable().getTicktxt();
        if (text == null || text.equals("")) {
            return;
        }
        String jg = this.npc.getNpctable().getTick();
        if (jg == null || jg.equals("") || jg.equals("0")) {
            return;
        }
        int jgtime = Integer.parseInt(jg) * 1000;
        if (this.time > (long)jgtime) {
            this.time = 0L;
            if (this.chatPanels.size() == 0) {
                this.chatPanels.add(new FloatPanel(text));
            }
            FrameMessageChangeJpanel.addtext(0, text, null, this.name);
        }
    }
    
    public int dirtiao(int dir) {
        if (dir == 1) {
            return 0;
        }
        if (dir == 3) {
            return 5;
        }
        if (dir == 5) {
            return 1;
        }
        if (dir == 7) {
            return 7;
        }
        return 1;
    }
    
    public ManimgAttribute(MapMonsterBean mapMonsterBean, String[] names, String[] appellation, String taskIds) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.name = mapMonsterBean.getRobotname();
            this.names = names;
            this.appellation = appellation;
            this.changeskin(mapMonsterBean.getRobotskin());
            this.leixing = 5;
            if (mapMonsterBean.getRobottitle() != null && !mapMonsterBean.getRobottitle().equals("")) {
                Random random = new Random();
                Color[] cl = { UIUtils.COLOR_NAME, UIUtils.COLOR_NAME1, UIUtils.COLOR_NAME2, UIUtils.COLOR_NAME3, UIUtils.COLOR_NAME4 };
                this.setNameColor(cl[random.nextInt(cl.length - 1)]);
                this.setNameColor(UIUtils.COLOR_NAME);
            }
            this.mapMonsterBean = mapMonsterBean;
            if (mapMonsterBean.getRobottitle() != null && mapMonsterBean.getRobottitle() != "") {
                this.appellation = AccessTeamInfoUntil.getss(mapMonsterBean.getRobottitle());
            }
            this.x = (int)mapMonsterBean.getX();
            this.y = (int)mapMonsterBean.getY();
        }
        catch (Exception ex) {}
    }
    
    public ManimgAttribute(TaskProgress progress) {
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.flytcp = new flytcp("cloud1/stand");
        this.flyruntcp = new Flyruntcp("cloud2/stand");
        this.flywalktcp = new flywalktcp("cloud3/stand");
        this.flyX = 0;
        this.flyY = 0;
        this.isHigh = false;
        this.exit = true;
        this.m = true;
        this.divineWalkCount = 1;
        this.movejiange = 0L;
        this.list = new ArrayList<>();
        try {
            this.name = progress.getDName();
            if (StringUtils.isNotEmpty(progress.getTitle())) {
                this.appellation = new String[] { progress.getTitle() };
            }
            this.names = AccessTeamInfoUntil.getss(this.name);
            Robots robots = UserMessUntil.getRobot(progress.getDId() + "");
            this.changeskin((robots != null) ? robots.getRobotskin() : "100");
            this.leixing = 6;
            this.taskdata = progress;
            this.x = progress.getX();
            this.y = progress.getY();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PathPoint isdraw() {
        PathPoint pathPoint = null;
        if (this.roleShow != null) {
            if (this.roleShow.getFly_id() > 0 && this.leixing == 0) {
                ManimgAttribute.lx = this.leixing;
                pathPoint = Util.mapmodel.path(this.roleShow.getX(), this.roleShow.getY() - ManimgAttribute.yys);
            }
            else if (this.roleShow.getFly_id() > 0 && this.leixing == 1) {
                ManimgAttribute.lx = this.leixing;
                pathPoint = Util.mapmodel.path(this.roleShow.getX(), this.roleShow.getY() - 140);
            }
            else if (this.roleShow.getFly_id() == 0 && this.leixing == 1) {
                ManimgAttribute.lx = this.leixing;
                pathPoint = Util.mapmodel.path(this.roleShow.getX(), this.roleShow.getY());
            }
            else if (this.roleShow.getFly_id() == 0 && this.leixing == 0) {
                ManimgAttribute.lx = this.leixing;
                pathPoint = Util.mapmodel.path(this.roleShow.getX(), this.roleShow.getY() - ManimgAttribute.yys);
            }
        }
        else {
            pathPoint = Util.mapmodel.path(this.x, this.y);
        }
        return pathPoint;
    }
    
    public boolean Drawing(Graphics g2, long DieTime) {
        ImageMixDeal.ismove = false;
        PathPoint pathPoint = this.isdraw();
        //if (skin == null && this.roleShow!=null&&this.roleShow.getCar_id() != 0 ) {
        if (this.roleShow != null && this.roleShow.getFlyskin() != null &&this.roleShow.getCar_id() == 0 ) {
            this.part.setFly(this.roleShow.getFlyskin(), 2, Boolean.valueOf(true));
            this.part.setFlyShadow(this.roleShow.getFlyskin() + "1", 2);
        }
        if (pathPoint == null) {
            this.part.recycle();
            ImageMixDeal.ismove = true;
            return false;
        }
        this.time += DieTime;
        if (this.part != null && this.part.getTCP() != null) {
            if (this.isHigh) {
                this.part.getTCP().setisHigh(true);
            }
            else {
                this.part.getTCP().setisHigh(false);
            }
        }
        if (this.m && ManimgAttribute.part3 != null && ManimgAttribute.part5 == null) {
            for (int i = 0; i < 1; ++i) {
                Mythreadrun1 mythreadrun1 = new Mythreadrun1(g2, 450);
                mythreadrun1.start();
            }
        }
        if (ManimgAttribute.part5 != null && ManimgAttribute.part4 == null) {
            for (int i = 0; i < 1; ++i) {
                Mythreadrun2 mythreadrun2 = new Mythreadrun2(g2, 450);
                mythreadrun2.start();
            }
            Dingshi dingshi = new Dingshi(Integer.parseInt(ManimgAttribute.dianshu[0]));
            dingshi.start();
        }
        if (ManimgAttribute.part4 != null) {
            Dingshi2 dingshi2 = new Dingshi2(false);
            dingshi2.start();
            for (int s = 0; s <= ManimgAttribute.partList.size() - 1; ++s) {
                ((NewPart)ManimgAttribute.partList.get(s)).draw(g2, s * 200 + 350, 300, this.dir, this.time);
            }
        }
        if (!ManimgAttribute.ISTCP && this.leixing == 1) {
            this.part.recycle();
            if (ManimgAttribute.ISNAME) {
                this.drawfonts(g2, pathPoint.getX(), pathPoint.getY());
            }
            if (this.roleShow != null) {
                this.drawTou(g2, pathPoint);
            }
            return ImageMixDeal.ismove = true;
        }
        else {
            if (ManimgAttribute.ISNAME || this.leixing != 1) {
                this.drawfonts(g2, pathPoint.getX(), pathPoint.getY());
            }
            this.draw(g2, pathPoint.getX(), pathPoint.getY());
            if (this.part != null) {
                if (Util.mapmodel.zhezhao(pathPoint.getX(), pathPoint.getY())) {
                    if (ManimgAttribute.ISFLY) {
                        this.part.draw(g2, pathPoint.getX(), pathPoint.getY(), this.dir, this.time, 1.0f);
                    }
                    else {
                        this.part.draw(g2, pathPoint.getX(), pathPoint.getY(), this.dir, this.time, 0.6f);
                    }
                    if ((this.leixing == 0 && (boolean)this.roleShow.getDivineRune()) || (this.leixing == 1 && (boolean)this.roleShow.getDivineRune())) {
                        this.drawDivineWalks(this.part, g2, pathPoint);
                    }
                }
                else {
                    this.part.draw(g2, pathPoint.getX(), pathPoint.getY(), this.dir, this.time);
                    if ((this.leixing == 0 && (boolean)this.roleShow.getDivineRune()) || (this.leixing == 1 && (boolean)this.roleShow.getDivineRune())) {
                        this.drawDivineWalks(this.part, g2, pathPoint);
                    }
                }
                if (this.roleShow != null) {
                    this.drawTou(g2, pathPoint);
                }
                else if (this.mapMonsterBean != null && this.mapMonsterBean.getHP() != null && this.mapMonsterBean.getHP().getX() > 0) {
                    int hp_bai = (int)(75.0 * (double)this.mapMonsterBean.getHP().getX() / (double)this.mapMonsterBean.getHP().getY());
                    if (hp_bai > 75) {
                        hp_bai = 75;
                    }
                    else if (hp_bai < 0) {
                        hp_bai = 0;
                    }
                    g2.drawImage(Fightingimage.xuelans[0].getImage(), pathPoint.getX() - 35, pathPoint.getY() - 125, 75, 5, null);
                    g2.drawImage(Fightingimage.xuelans[1].getImage(), pathPoint.getX() - 35, pathPoint.getY() - 125, hp_bai, 5, null);
                }
                if (npctou == 1) {
                    drawfonts(g2, pathPoint.getX()-4, pathPoint.getY()-1);
                }
                if (this.txZJ != null) {
                    this.txZJ.draw(g2, DieTime);
                }
                if (ManimgAttribute.ISFLY) {
                    this.flytcp.draw(g2, DieTime / 2L);
                    this.flywalktcp.draw(g2, DieTime / 2L);
                    this.flyruntcp.draw(g2, DieTime / 2L);
                }
            }
            return ImageMixDeal.ismove = true;
        }
    }
    
    public void addmove() {
        Mythreadrun mythreadrun = new Mythreadrun();
        if (this.getMoves().size() >= 13) {
            if (this.part.getAct() == 1) {
                sidian sidian = (sidian)this.moves.remove(0);
                sidian.setsidian(this.roleShow.getX(), this.roleShow.getY(), this.dir, this.part.getAct());
                if ((this.leixing == 0 && this.divineWalkCount % 10 == 0 && (boolean)this.roleShow.getDivineRune()) || (this.leixing == 1 && this.divineWalkCount % 10 == 0 && (boolean)this.roleShow.getDivineRune())) {
                    DivineWalk divineWalk = new DivineWalk(null, this.roleShow.getX(), this.roleShow.getY(), this.dir);
                    if (this.list.size() >= 6) {
                        this.list.add(divineWalk);
                        this.list.remove(1);
                    }
                    else {
                        this.list.add(divineWalk);
                    }
                    this.divineWalkCount = 0;
                }
                if (this.txZJ != null) {
                    this.txZJ.addZJ(this.roleShow.getX(), this.roleShow.getY(), this.dir);
                }
                if (ManimgAttribute.ISFLY) {
                    Random m = new Random();
                    int k = m.nextInt(30);
                    int p = m.nextInt(8);
                    int c = m.nextInt(50);
                    if (c < 20) {
                        Math.negateExact(p);
                    }
                }
                this.moves.add(sidian);
                ++this.divineWalkCount;
            }
            else {
                if (ManimgAttribute.ISFLY) {
                    mythreadrun.start();
                }
                this.getmove().setW(2);
                this.list.clear();
            }
        }
        else {
            if (this.part.getAct() == 1 && this.txZJ != null) {
                this.txZJ.addZJ(this.roleShow.getX(), this.roleShow.getY() + 10, this.dir);
            }
            if (ManimgAttribute.ISFLY) {
                mythreadrun.start();
            }
            this.moves.add(new sidian(this.roleShow.getX(), this.roleShow.getY(), this.dir, this.part.getAct()));
        }
    }
    
    private void drawTou(Graphics g2, PathPoint pathPoint) {
        if (this.roleShow.getFighting() != 0) {
            Sprite mouse = SpriteFactory.Prepare(GetTcpPath.PK);
            if (mouse != null) {
                mouse.updateToTime(this.time, 0);
                mouse.draw(g2, pathPoint.getX() - 9, pathPoint.getY() - 110);
            }
        }
        else if (this.roleShow.getCaptian() == 1) {
            Sprite mouse = SpriteFactory.Prepare(GetTcpPath.LIN);
            if (mouse != null) {
                mouse.updateToTime(this.time, 0);
                if (this.roleShow.getMount_id() > 7) {
                    mouse.draw(g2, pathPoint.getX(), pathPoint.getY() - 165);
                }
                else {
                    mouse.draw(g2, pathPoint.getX(), pathPoint.getY() - 125);
                }
            }
        }
    }
    
    public List<sidian> getMoves() {
        if (this.moves == null) {
            this.moves = new ArrayList<>();
        }
        return this.moves;
    }
    
    public void setMoves(List<sidian> moves) {
        this.moves = moves;
    }
    
    public sidian getmove() {
        if (this.getMoves().size() == 0) {
            this.moves.add(new sidian(this.roleShow.getX(), this.roleShow.getY(), this.dir, this.part.getAct()));
        }
        return (sidian)this.moves.get(0);
    }
    
    public void CZmove() {
        this.getMoves();
        for (int i = 0; i < this.moves.size(); ++i) {
            ((sidian)this.moves.get(i)).setsidian(this.roleShow.getX(), this.roleShow.getY(), this.dir, this.part.getAct());
        }
    }
    
    public void drawDivineWalks(NewPart part, Graphics g2, PathPoint pathPoint) {
        for (int i = 0; i < this.list.size(); ++i) {
            DivineWalk divineWalk = (DivineWalk)this.list.get(i);
            PathPoint pathPoint2 = Util.mapmodel.path(divineWalk.getX(), divineWalk.getY());
            if (pathPoint2 != null) {
                part.draw(g2, pathPoint2.getX(), pathPoint2.getY() - ManimgAttribute.yys, divineWalk.getDir(), this.time, (float)i * 0.15f);
            }
        }
    }
    
    public void initTeam() {
        this.teams = null;
        if (this.roleShow.getTroop_id() == null || this.roleShow.getTeamInfo() == null || this.roleShow.getTeamInfo().equals("")) {
            this.teams = new String[] { this.roleShow.getRolename() };
            this.roleShow.setCaptian(0);
        }
        else {
            this.teams = this.roleShow.getTeamInfo().split("\\|");
            if (!this.teams[0].equals(this.roleShow.getRolename())) {
                this.teams = null;
            }
            this.roleShow.setCaptian((this.teams != null) ? 1 : 0);
        }
        if (this.leixing == 0 && this.teams == null) {
            TimeControlRunnable.removeScript(true);
        }
    }
    
    public void move(long time) {
        if (this.teams != null) {
            this.addmove();
            if (this.roleShow.getPlayer_Paths().size() <= 1) {
                this.setType(2);
            }
            else {
                this.setType(1);
                double m = (this.roleShow.getMount_id() == 7) ? 0.27 : 0.21;//坐骑+走路速度
                if (this.roleShow.getFly_id() != 0 && ZhuJpanel.getListFly() != null && ZhuJpanel.getListFly().size() > 0) {
                    for (Fly fly : ZhuJpanel.getListFly()) {
                        if ((int)fly.getFlytid() == this.roleShow.getFly_id() && (int)fly.getFlystate() > 0) {
                            m = 0.26 + 0.026 * (double)(int)fly.getFlystate();//飞行器速度
                        }
                    }
                }
                double l = 0.0;
                if (m >= 1.09) {
                    l = 0.31;
                }
                else {
                    l = m;
                }
                if ((boolean)this.roleShow.getDivineRune()) {
                    l *= 1.8;
                }
                this.movejiange = FightingMove2.getmovetime(this, this.roleShow, l, this.getMovejiange(time), this.dir);
                if (this.leixing == 0) {
                    this.DoorRect();
                }
            }
            ManimgAttribute my = this;
            for (int i = 1; i < this.teams.length; ++i) {
                ManimgAttribute manimgAttribute = ImageMixDeal.huoquLogin(this.teams[i]);
                if (manimgAttribute != null) {
                    manimgAttribute.bh(my.getmove());
                    my = manimgAttribute;
                }
            }
        }
    }
    
    public void movezou(long time) {
        if (this.teams != null) {
            this.addmove();
            if (this.roleShow.getPlayer_Paths().size() <= 1) {
                this.setType(2);
                Util.ZOU = false;
            }
            else {
                if (this.leixing == 0) {
                    if (this.roleShow.getMount_id() > 0) {
                        this.setType(1);
                    }
                    else {
                        this.setType(0);
                    }
                    this.movejiange = FightingMove2.getmovetime(this, this.roleShow, (this.roleShow.getMount_id() > 7) ? 0.2 : 0.14, this.getMovejiange(time), this.dir);
                    if (this.leixing == 0) {
                        this.DoorRect();
                    }
                }
                else {
                    this.setType(1);
                    this.movejiange = FightingMove2.getmovetime(this, this.roleShow, (this.roleShow.getMount_id() > 7) ? 0.24 : 0.24, this.getMovejiange(time), this.dir);
                    if (this.leixing == 0) {
                        this.DoorRect();
                    }
                }
                if (this.leixing == 0) {
                    this.DoorRect();
                }
            }
            ManimgAttribute my = this;
            for (int i = 1; i < this.teams.length; ++i) {
                ManimgAttribute manimgAttribute = ImageMixDeal.huoquLogin(this.teams[i]);
                if (manimgAttribute != null) {
                    manimgAttribute.bh(my.getmove());
                    my = manimgAttribute;
                }
            }
        }
    }
    
    public void move2(long time) {
        if (this.mapMonsterBean.getFollow() != null) {
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(this.mapMonsterBean.getFollow());
            if (attribute != null) {
                this.GSM(attribute.getmove());
                return;
            }
        }
        if (this.mapMonsterBean.getMovePath() != null && this.mapMonsterBean.getType() == 0 && this.mapMonsterBean.getMovePath().isMove(this, time, this.dir)) {
            this.setType(2);
            this.mapMonsterBean.setMovePath(null);
        }
    }
    
    public void bh(sidian sidian) {
        this.addmove();
        this.roleShow.setX(sidian.getX());
        this.roleShow.setY(sidian.getY());
        this.setDir(sidian.getZ());
        this.setType(sidian.getW());
    }
    
    public void GS(sidian sidian) {
        this.x = sidian.getX();
        this.y = sidian.getY();
        this.setDir(sidian.getZ());
        this.setType(sidian.getW());
    }
    
    public void GSM(sidian sidian) {
        this.x = sidian.getX() + 5;
        this.y = sidian.getY() + 5;
        this.setDir(sidian.getZ());
        this.setType(sidian.getW());
    }
    
    public void DoorRect() {
        int size = this.roleShow.getPlayer_Paths().size();
        if (size > 1) {
            PathPoint point = (PathPoint)this.roleShow.getPlayer_Paths().get(size - 1);
            if (Math.abs(point.getX() - this.roleShow.getX()) > 81 || Math.abs(point.getY() - this.roleShow.getY()) > 81) {
                return;
            }
        }
        Door door = Util.mapmodel.tp(this.roleShow.getX(), this.roleShow.getY());
        if (door == null) {
            return;
        }
        this.roleShow.getPlayer_Paths().clear();
        TP.tp(door, 0);
    }
    
    public void draw(Graphics g, int x, int y) {
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF));
        if (this.chatPanels.size() != 0) {
            int py = this.part.getPy();
            if (py != -1) {
                int chatY = y - py;
                for (int i = this.chatPanels.size() - 1; i >= 0; --i) {
                    FloatPanel chatPanel = (FloatPanel)this.chatPanels.get(i);
                    if (shouldDisplay(chatPanel)) {
                        int chatX = x - chatPanel.getWidth() / 2;
                        chatY -= chatPanel.getHeight() + 2;
                        g.translate(chatX, chatY);
                        chatPanel.paint(g);
                        g.translate(-chatX, -chatY);
                    }
                    else {
                        chatPanel.remove();
                        this.chatPanels.remove(i);
                    }
                }
            }
        }
    }
    
    public void draweffects(Graphics g) {
        for (int i = ManimgAttribute.Effectslist.size() - 1; i >= 0; --i) {
            Effects effects = (Effects)ManimgAttribute.Effectslist.get(i);
            effects.addTime(15L);
            if (EffectsDisplay(effects)) {
                PathPoint pathPoint = Util.mapmodel.path(((Effects)ManimgAttribute.Effectslist.get(i)).getX(), ((Effects)ManimgAttribute.Effectslist.get(i)).getY());
                if (pathPoint != null) {
                    Sprite sprite1 = SpriteFactory.Prepare(GetTcpPath.getMouseTcp(effects.getEffectsName()));
                    if (sprite1 != null) {
                        sprite1.updateToTime(effects.getTime(), 0);
                        sprite1.draw(g, pathPoint.getX(), pathPoint.getY());
                    }
                }
            }
            else {
                ManimgAttribute.Effectslist.remove(i);
            }
        }
    }
    
    public static boolean shouldDisplay(FloatPanel chatPanel) {
        return Util.getTime() - chatPanel.getCreateTime() < Util.TIME_CHAT;
    }
    
    public static boolean EffectsDisplay(Effects effects) {
        if (effects.getEffectsName().equals("升级")) {
            return effects.getTime() < Util.TIME_CHAT / 2L;
        }
        return effects.getTime() < Util.TIME_CHAT / 10L;
    }
    
    public static Image getHzImg(int i) {
        if (i >= ManimgAttribute.hzImgs.length) {
            i = ManimgAttribute.hzImgs.length - 1;
        }
        return ManimgAttribute.hzImgs[i];
    }
    
    public static void main(String[] args) {
    }
    
    public void drawfonts(Graphics g, int x, int y) {
        if (this.names == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(UIUtils.nameFont);
        int textY = y + 50;
        if (this.size1 == 0) {
            this.size1 = g2d.getFontMetrics().stringWidth(this.name) / 2;
        }
        int textX = x - this.size1;
        if (this.leixing == 0 || this.leixing == 1) {
            if ((this.leixing == 0 && this.roleShow.getFlyskin() != null) || (this.leixing == 1 && this.roleShow.getFlyskin() != null)) {//修改上飞行器徽章位置
                if (this.roleShow.getSkill_id() != null) {
                    Image hz = getHzImg((int)this.roleShow.getSkill_id());
                    g2d.drawImage(hz, textX - 28, textY + 43, null);
                }
            }
            else if (this.roleShow.getSkill_id() != null) {
                Image hz = getHzImg((int)this.roleShow.getSkill_id());
                g2d.drawImage(hz, textX - 28, textY - 15, null);
            }
            if ((this.leixing == 0 && this.roleShow.getFlyskin() != null&&this.roleShow.getCar_id() == 0 ) || (this.leixing == 1 && this.roleShow.getFlyskin() != null&&this.roleShow.getCar_id() == 0 )) {//修改上飞行器角色名字位置
                textY += 60;
            }
            else {
                textY += 3;
            }
            FontMetrics fm = g2d.getFontMetrics();
            for (int i = 0; i < this.names.length; ++i) {
                g2d.setColor(UIUtils.COLOR_NAME8);
                g2d.drawString(this.names[i], textX + 1, textY + 1);//画角色名字阴影部分
                g2d.setColor(UIUtils.getcolor(this.roleShow.getTurnAround()));//根据转生获取角色名字颜色
                g2d.drawString(this.names[i], textX, textY);
                g2d.drawString(this.names[i], textX, textY);
                textX += fm.stringWidth(this.names[i]) - 1;
            }
            if (Util.hideTitle && this.appellation != null && this.roleShow.getTitle() != null) {
                textY -= 19;
                textX = x - g2d.getFontMetrics().stringWidth(this.roleShow.getTitle()) / 2;
                for (int i = 0; i < this.appellation.length; ++i) {
                    g2d.setColor(UIUtils.COLOR_NAME8);
                    g2d.drawString(this.appellation[i], textX + 1, textY + 1);
                    g2d.setColor((UserMessUntil.getTitleColorsByName(this.roleShow.getTitle()) != null) ? UserMessUntil.getTitleColorsByName(this.roleShow.getTitle()) : UIUtils.COLOR_title2);
                    g2d.drawString(this.appellation[i], textX, textY);
                    textX += fm.stringWidth(this.appellation[i]) - 1;
                }
            }
        }
        else {
            if (this.npc != null && this.npc.getNpctable() != null && !StringUtil.isNullOrEmpty(this.npc.getNpctable().getSkin())) {
                if ("300057".equals(this.npc.getNpctable().getSkin())) {
                    textX -= 15;
                }
                if ("300056".equals(this.npc.getNpctable().getSkin())) {
                    textX += 10;
                }
                if ("400062".equals(this.npc.getNpctable().getSkin())) {
                    textX += 17;
                }
            }
            if (this.appellation != null) {
                textY -= 5;
            }
            else {
                textY -= 20;
            }
            FontMetrics fm = g2d.getFontMetrics();
            if (npctou == 0) {
                for (int i = 0; i < this.names.length; ++i) {
                    g2d.setColor(UIUtils.COLOR_NAME8);
                    g2d.drawString(this.names[i], textX + 1, textY + 1);
                    g2d.setColor((this.nameColor == null) ? UIUtils.COLOR_NPC1 : this.nameColor);
                    g2d.drawString(this.names[i], textX, textY);
                    g2d.drawString(this.names[i], textX, textY);
                    textX += fm.stringWidth(this.names[i]) - 1;
                }
            }else{
                for (int i = 0; i < names.length; i++) {//NPC名字间距
                    // 黑
                    g2d.setColor(UIUtils.COLOR_NPCTOU);
                    g2d.setFont(UIUtils.TEXT_FONT811);
                    g2d.drawString(names[i], textX + 37, textY -3);
                    textX += fm.stringWidth(names[i]) - 1;

                }
                g2d.setFont(UIUtils.nameFont);
            }
            if (this.appellation != null) {
                if (this.npc != null) {
                    textX = x - g2d.getFontMetrics().stringWidth(this.npc.getNpctable().getTitle()) / 2;
                }
                else if (this.mapMonsterBean != null) {
                    textX = x - g2d.getFontMetrics().stringWidth(this.mapMonsterBean.getRobottitle()) / 2;
                }
                for (int i = 0; i < this.appellation.length; ++i) {
                    g2d.setColor(UIUtils.COLOR_NAME8);
                    g2d.drawString(this.appellation[i], textX + 1, textY - 19);
                    g2d.setColor(UIUtils.COLOR_title2);
                    g2d.drawString(this.appellation[i], textX, textY - 20);
                    textX += fm.stringWidth(this.appellation[i]) - 1;
                }
            }
        }
        g2d.dispose();
    }
    
    public void Dialogue(String text) {
        if (FightingMixDeal.State == 0) {
            this.chatPanels.add(new FloatPanel(text));
        }
        else {
            FightingMixDeal.Dialogue(this.roleShow.getRolename(), text);
        }
    }
    
    public static void addEffects(String text, int x, int y) {
        int i = 0;
        while (i < ManimgAttribute.Effectslist.size()) {
            if (!((Effects)ManimgAttribute.Effectslist.get(i)).getEffectsName().equals(text)) {
                ++i;
            }
            else {
                ((Effects)ManimgAttribute.Effectslist.get(i)).setX(x);
                ((Effects)ManimgAttribute.Effectslist.get(i)).setY(y);
                ((Effects)ManimgAttribute.Effectslist.get(i)).setTime(0L);
                return;
            }
        }
        ManimgAttribute.Effectslist.add(new Effects(text, x, y));
    }
    
    public RoleShow getRoleShow() {
        return this.roleShow;
    }
    
    public void setRoleShow(RoleShow roleShow) {
        this.roleShow = roleShow;
        this.initTeam();
    }
    
    public MapMonsterBean getMapMonsterBean() {
        return this.mapMonsterBean;
    }
    
    public void setMapMonsterBean(MapMonsterBean mapMonsterBean) {
        this.mapMonsterBean = mapMonsterBean;
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
    
    public int getType() {
        return this.part.getAct();
    }
    
    public void setType(int type) {
        if (this.part.getAct() == type) {
            return;
        }
        this.part.setAct(type);
    }
    
    public long getMovejiange(long movejiange) {
        return this.movejiange += movejiange;
    }
    
    public void setMovejiange(long movejiange) {
        this.movejiange = movejiange;
    }
    
    public NpcInfoBean getNpc() {
        return this.npc;
    }
    
    public void setNpc(NpcInfoBean npc) {
        this.npc = npc;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time += time;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.size1 = 0;
        this.name = name;
        this.names = AccessTeamInfoUntil.getss(this.name);
    }
    
    public TaskProgress getTaskdata() {
        return this.taskdata;
    }
    
    public void setTaskdata(TaskProgress taskdata) {
        this.taskdata = taskdata;
    }
    
    public String[] getNames() {
        return this.names;
    }
    
    public void setNames(String[] names) {
        this.names = names;
    }
    
    public String[] getAppellation() {
        return this.appellation;
    }
    
    public void setAppellation(String chang) {
        this.appellation = AccessTeamInfoUntil.getss(chang);
    }
    
    public String[] getTeams() {
        return this.teams;
    }
    
    public void setTeams(String[] teams) {
        this.teams = teams;
    }
    
    public int getLeixing() {
        return this.leixing;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public String[] getTaskIds() {
        return this.taskIds;
    }
    
    public void setTaskIds(String[] taskIds) {
        this.taskIds = taskIds;
    }
    
    public boolean isContains(int x, int y) {
        if ((this.leixing == 0 && this.roleShow.getFlyskin() != null) || (this.leixing == 1 && this.roleShow.getFlyskin() != null)) {
            if (this.roleShow != null) {
                return this.part.contains(x - this.roleShow.getX(), y - this.roleShow.getY() + 120);
            }
            return this.part.contains(x - this.x, y - this.y);
        }
        else {
            if (this.roleShow != null) {
                return this.part.contains(x - this.roleShow.getX(), y - this.roleShow.getY());
            }
            return this.part.contains(x - this.x, y - this.y);
        }
    }
    
    public boolean get() {
        return ManimgAttribute.ISFLY;
    }
    
    public void setISFLY(boolean ISFLY) {
        ManimgAttribute.ISFLY = ISFLY;
    }
    
    public boolean setisHigh(boolean t) {
        return this.isHigh = t;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    static {
        ManimgAttribute.ISTCP = true;
        ManimgAttribute.ISNAME = true;
        ManimgAttribute.Effectslist = new ArrayList<>();
        ManimgAttribute.isdaiji = false;
        ManimgAttribute.ISFLY = false;
        ManimgAttribute.IS = false;
        ManimgAttribute.yys = 0;
        ManimgAttribute.lx = 99;
        ManimgAttribute.XNMSXZSXF = "1";
        ManimgAttribute.part3 = null;
        ManimgAttribute.part4 = null;
        ManimgAttribute.part5 = null;
        ManimgAttribute.partList = new ArrayList<>();
        ManimgAttribute.start = false;
        ManimgAttribute.zhongjiang = null;
        ManimgAttribute.dianshu = new String[] { "1", "2", "3" };
        (ManimgAttribute.hzImgs = new Image[6])[0] = new ImageIcon("img/head/hz_0.png").getImage();
        ManimgAttribute.hzImgs[1] = new ImageIcon("img/head/hz_1.png").getImage();
        ManimgAttribute.hzImgs[2] = new ImageIcon("img/head/hz_2.png").getImage();
        ManimgAttribute.hzImgs[3] = new ImageIcon("img/head/hz_3.png").getImage();
        ManimgAttribute.hzImgs[4] = new ImageIcon("img/head/hz_4.png").getImage();
        ManimgAttribute.hzImgs[5] = new ImageIcon("img/head/hz_5.png").getImage();
    }
    
    public class Mythread extends Thread
    {
        @Override
        public void run() {
            for (int i = 0; i <= 140; ++i) {
                if (ManimgAttribute.IS) {
                    if (ManimgAttribute.yys < 140) {
                        try {
                            Thread.sleep(40L);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ManimgAttribute.yys += i;
                    }
                    if (ManimgAttribute.yys > 140) {
                        ManimgAttribute.yys = 140;
                    }
                    if (ManimgAttribute.yys == 140) {
                        break;
                    }
                }
                else {
                    if (ManimgAttribute.this.roleShow.getFly_id() == 0 && ManimgAttribute.yys > 0) {
                        try {
                            Thread.sleep(45L);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ManimgAttribute.yys -= i;
                    }
                    if (ManimgAttribute.yys <= 0) {
                        ManimgAttribute.yys = 0;
                        break;
                    }
                }
            }
            if (ManimgAttribute.yys == 0 && AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().getText().equals("待机")) {
                String mes = Agreement.getAgreement().rolechangeAgreement("F");
                ManimgAttribute.this.roleShow.setFly_id(0);
                SendMessageUntil.toServer(mes);
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("飞行");
                ImageMixDeal.userimg.changeskin(null);
                ManimgAttribute.ISFLY = false;
                ManimgAttribute.isdaiji = false;
            }
            ManimgAttribute.IS = false;
            ManimgAttribute.isdaiji = false;
        }
    }
    
    public class Mythreadrun extends Thread
    {
        @Override
        public void run() {
            ManimgAttribute.this.exit = false;
            Random m = new Random();
            int k = m.nextInt(30);
            int p = m.nextInt(8);
            int c = m.nextInt(50);
            if (c < 20) {
                Math.negateExact(p);
            }
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            k = m.nextInt(9);
            p = m.nextInt(15);
            c = m.nextInt(50);
            if (c < 20) {
                Math.negateExact(p);
            }
            k = m.nextInt(10);
            p = m.nextInt(20);
            c = m.nextInt(50);
            if (c < 20) {
                Math.negateExact(p);
            }
            ManimgAttribute.this.exit = true;
        }
    }
    
    public class Mythreadrun1 extends Thread
    {
        private Graphics g2;
        private int x;
        
        public Mythreadrun1(Graphics g, int x) {
            this.g2 = g;
            this.x = x;
        }
        
        @Override
        public void run() {
            ManimgAttribute.this.m = false;
            if (ManimgAttribute.part3 != null) {
                if (ScrenceUntil.Screen_x >= 1366) {
                    ManimgAttribute.part3.draw(this.g2, this.x + 250, 300, ManimgAttribute.this.dir, ManimgAttribute.this.time);
                }
                else if (ScrenceUntil.Screen_x >= 1024) {
                    ManimgAttribute.part3.draw(this.g2, this.x + 100, 300, ManimgAttribute.this.dir, ManimgAttribute.this.time);
                }
                else {
                    ManimgAttribute.part3.draw(this.g2, this.x, 300, ManimgAttribute.this.dir, ManimgAttribute.this.time);
                }
            }
            ManimgAttribute.part5 = SpriteFactory.createPart("骰子全/骰子转动特效1", -2, 5, null);
            ManimgAttribute.this.m = true;
        }
    }
    
    public class Mythreadrun2 extends Thread
    {
        private Graphics g2;
        private int x;
        
        public Mythreadrun2(Graphics g, int x) {
            this.g2 = g;
            this.x = x;
        }
        
        @Override
        public void run() {
            long k = (long)((double)ManimgAttribute.this.time / 1.5);
            ManimgAttribute.this.m = false;
            if (ManimgAttribute.part5 != null) {
                if (ScrenceUntil.Screen_x >= 1366) {
                    ManimgAttribute.part5.draw(this.g2, this.x + 250, 370, ManimgAttribute.this.dir, k);
                }
                else if (ScrenceUntil.Screen_x >= 1024) {
                    ManimgAttribute.part5.draw(this.g2, this.x + 100, 370, ManimgAttribute.this.dir, k);
                }
                else {
                    ManimgAttribute.part5.draw(this.g2, this.x, 370, ManimgAttribute.this.dir, k);
                }
            }
            ManimgAttribute.this.m = true;
        }
    }
    
    public class Dingshi extends Thread
    {
        private Graphics g2;
        private int x;
        private int p;
        
        public Dingshi(int p) {
            this.p = p;
        }
        
        @Override
        public void run() {
            ManimgAttribute.start = true;
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            String b = "骰子全/" + this.p + "点";
            ManimgAttribute.part4 = SpriteFactory.createPart(b, -2, 5, null);
        }
    }
    
    public class Dingshi2 extends Thread
    {
        private Graphics g2;
        private int x;
        private boolean p;
        
        public Dingshi2(boolean p) {
            this.p = ManimgAttribute.this.m;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            ManimgAttribute.part4 = null;
            ManimgAttribute.part5 = null;
            ManimgAttribute.part3 = null;
            ManimgAttribute.partList.clear();
            ManimgAttribute.start = false;
        }
    }
}
