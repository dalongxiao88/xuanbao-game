package org.come.until;

import com.tool.tcp.GetTcpPath;
import com.tool.tcp.SpriteFactory;
import java.util.HashMap;
import java.io.FileOutputStream;
import org.come.npc.TP;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.entity.Mount;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;
import java.util.HashSet;
import com.tool.time.TimeLimit;
import com.tool.role.RoleData;
import java.util.Objects;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.come.socket.GameClient;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;
import org.come.npc.Creeps;
import com.tool.PlayerKill.PKSys;
import org.come.bean.LoginResult;
import org.come.bean.RoleShow;
import java.awt.Image;
import java.text.DateFormat;
import org.come.bean.ConfigureBean;
import org.come.model.Door;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.image.ManimgAttribute;
import org.come.Jpanel.ZhuJpanel;
import org.come.entity.Fly;
import org.come.Frame.AircraftJframe;
import org.come.Frame.TestsmallmapJframe;
import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import org.come.Frame.ZhuFrame;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.model.Configure;
import java.io.File;
import java.util.GregorianCalendar;
import com.tool.image.ImageMixDeal;
import java.util.ArrayList;
import org.come.model.Gamemap;
import org.come.Jpanel.GameJpanel;
import com.tool.tcp.Sprite;
import java.util.Map;
import java.util.Vector;
import java.math.BigDecimal;
import org.come.entity.RegionResult;
import java.util.List;
import javax.swing.JList;
import org.come.bean.Mapmodel;
import java.util.Random;

public class Util
{
    private static final String[] NUMS;
    private static final String[] UNITS;
    public static long nowtime;
    public static int TIME_CHAT2;
    public static long TIME_CHAT;
    public static long TIME_TCP;
    public static final int CELL_WIDTH = 20;
    public static final int CREEPS = 320;
    public static boolean hideTitle;
    public static boolean CREEPSMAP;
    public static Random random;
    public static int ditubianma;
    public static long maptime;
    public static Mapmodel mapmodel;
    private static long BJTime;
    public static boolean canBuyOrno;
    private static long dateTime;
    public static int nums;
    public static String mapName;
    public static String xy;
    public static Long startTime;
    public static String startTimeName;
    public static boolean ifpf;
    public static boolean dhjl;
    public static boolean ZOU;
    public static boolean dj;
    public static int oldFly_id;
    public static int oldFlyIndex;
    public static JList<String> oldFly;
    public static int moveGrade;
    public static int zzs;
    public static List<RegionResult> regionResultList;
    public static double[] xishu;
    public static double[][] zuoqi;
    public static BigDecimal GoldUP;
    public static Vector<String> vector;
    private static Object Ex4_1;
    private static Map<String, String> mountSkillMsgMap;
    public static Vector<String> vector2;
    public static Vector<String> vector3;
    public static Vector<String> vector4;
    public static Vector<String> vector_3;
    private static Long tm;
    private static Long hf;
    private static Long jy;
    private static Long jz;
    public static Long hdzjy;
    public static Long xfzje;
    public static Long xfzxy;
    public static String pdtype;
    public static Long victory;
    public static boolean qbg;
    public static boolean ifjr;
    public static final BigDecimal b1;
    public static final BigDecimal b2;
    public static final BigDecimal b3;
    public static final BigDecimal b4;
    public static final BigDecimal b5;
    public static final BigDecimal b6;
    public static final BigDecimal b7;
    static Sprite MallX;
    private static Object object;
    static int s;
    static long time;
    
    public static boolean isIllegal(String name) {
        boolean bool = true;
        String ss = "管理|GM|gm|Gm|gM|卖|物集|菜|新开|群|号|币|艹|贱";
        String[] v = ss.split("\\|");
        int i = 0;
        while (i < v.length) {
            if (name.contains(v[i])) {
                bool = false;
                break;
            }
            else {
                ++i;
            }
        }
        return bool;
    }
    
    public static void uptime(String uptime) {
        try {
            Util.BJTime = Long.parseLong(uptime) + 3000L;
        }
        catch (Exception e) {
            Util.BJTime = WebTimeUntil.getNetworkTime();
        }
    }
    
    public static synchronized void loadMap(int mapid, int x, int y) {
        if (mapid != 1207 && Util.ditubianma == mapid) {
            if (GameJpanel.isAlpha) {
                GameJpanel.alpha = 0.35f;
            }
            else {
                GameJpanel.isAlpha = true;
            }
            Util.mapmodel.force(x, y);
            return;
        }
        else {
            if (mapid == 3333) {
                FormsManagement.showForm(9);
            }
            else if (Util.ditubianma == 3333) {
                FormsManagement.HideForm(9);
            }
            Util.ditubianma = mapid;
            Util.maptime = 0L;
            Gamemap gamemap = (Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(Util.ditubianma + "");
            Util.mapmodel.setGamemap(gamemap);
            List<Door> doors = new ArrayList<>();
            if (gamemap.getMapway() != null) {
                List<String> strings = SplitStringTool.splitString(gamemap.getMapway());
                for (int i = 0; i < strings.size(); ++i) {
                    doors.add(UserMessUntil.getDoor((String)strings.get(i)));
                }
            }
            Util.mapmodel.setDoors(doors);//新修地图多次使用
            ImageMixDeal.NpcLoad(gamemap.getMapnpc());
            DarkCreeps(gamemap.getMonster());
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(11);
            if ((Util.ditubianma == 1193 || Util.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                Util.mapmodel.getjMap().loadMap(new File("resource/map/" + Util.ditubianma + "1.map"), doors, true);
            }else if (Util.ditubianma >= 100000) {
                String zhMapID=(Util.ditubianma+"").substring(0,(Util.ditubianma+"").length()-2);
                Util.mapmodel.getjMap().loadMap(new File("resource/map/"+zhMapID+".map"), doors, true);
            } else {
                Util.mapmodel.getjMap().loadMap(new File("resource/map/" + Util.ditubianma + ".map"), doors, true);
            }
            Util.mapmodel.Mapsize();
            Util.mapmodel.force(x, y);
            if (Util.mapmodel.getGamemap().getMapid().equals("3344") || Util.mapmodel.getGamemap().getMapid().equals("3342") || (Util.mapmodel.getGamemap().getMapid().equals("1207") && Util.pdtype.equals("1"))) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                String[] expAdd = configure.getExpAdd().split("\\|");
                Util.tm = Long.valueOf(Long.parseLong(expAdd[0]));
                Util.hf = Long.valueOf(Long.parseLong(expAdd[1]));
                Util.jy = Long.valueOf(Long.parseLong(expAdd[2]));
                if (isjb(Util.hf) || Util.pdtype.equals("1")) {
                    FormsManagement.HideForm(3003);
                    String text = "";
                    if (!Util.pdtype.equals("1")) {
                        text = "#R注意：#Y您已进入泡点地图，请注意消费金额！！！";
                    }
                    else {
                        text = "#R注意：#Y您已进入泡点地图！！！";
                    }
                    Util.qbg = true;
                    GameJpanel.getGameJpanel().addPrompt("#Y" + text);
                    FrameMessageChangeJpanel.addtext(6, text, null, null);
                    Util.hdzjy = Long.valueOf(0L);
                    Util.xfzje = Long.valueOf(0L);
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    Util.startTimeName = df.format(new Date());
                    Util.startTime = Long.valueOf(new Date().getTime());
                    Util.ifpf = true;
                }
                else {
                    Util.pdtype = "";
                    ZhuFrame.getZhuJpanel().addPrompt2("#R注意：#Y金钱不足，无法进入泡点地图！努力赚钱，下次再来！");
                    iWantToFly("1207,长安城,4960,3820");
                }
            }
            else {
                Util.hdzjy = Long.valueOf(0L);
                Util.xfzje = Long.valueOf(0L);
                Util.xfzxy = Long.valueOf(0L);
                Util.ifpf = false;
                Util.pdtype = "";
            }
            try {
                Image image;
                if (MyIsif.getStyle().equals("水墨")) {
                    if ((Util.ditubianma == 1193 || Util.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                        image = new ImageIcon("resource/smap/s" + Util.ditubianma + "1.png").getImage();
                    }else if (Util.ditubianma >= 100000) {
                        String zhMapID=(Util.ditubianma+"").substring(0,(Util.ditubianma+"").length()-2);
                        image = new ImageIcon("resource/smap/s" + zhMapID + ".png").getImage();
                    }
                    else {
                        image = new ImageIcon("resource/smap/s" + Util.ditubianma + ".png").getImage();
                    }
                }
                else if ((Util.ditubianma == 1193 || Util.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                    image = new ImageIcon("resource/smap/h" + Util.ditubianma + "1.png").getImage();
                }else if (Util.ditubianma >= 100000) {
                    String zhMapID=(Util.ditubianma+"").substring(0,(Util.ditubianma+"").length()-2);
                    image = new ImageIcon("resource/smap/h" + zhMapID + ".png").getImage();
                }
                else {
                    image = new ImageIcon("resource/smap/h" + Util.ditubianma + ".png").getImage();
                }
                if (image.getWidth(null) <= 0) {
                    TestsmallmapJframe.getTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, 5, 1);
                }
                else {
                    TestsmallmapJframe.getTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, image.getWidth(null) + 5, image.getHeight(null) + 1);
                }
                Util.mapmodel.MiniMap(image);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (Music.kz1 && Music.MusicNew != null) {
                if (Music.MusicNew.equals("1")) {
                    try {
                        Music.addbeijing(gamemap.getMusic() + "o.mp3");
                    }
                    catch (Exception ex) {}
                }
                else if (Music.MusicNew.equals("2")) {
                    try {
                        Music.addbeijing(gamemap.getMusic() + ".mp3");
                    }
                    catch (Exception ex2) {}
                }
            }
            if (Util.mapmodel.getGamemap().getFlyFlag().equals("0") && AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().getText().equals("待机")) {
                int index = AircraftJframe.getAircraftJframe().getaircraftJPanel().getlistfly().getSelectedIndex();
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getmodelfly().set(index, ((Fly)ZhuJpanel.getListFly().get(index)).getFlyname());
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                roleShow.setFly_id(0);
                ManimgAttribute.isdaiji = true;
                ImageMixDeal.userimg.changeskin(null);
                String mes = Agreement.getAgreement().rolechangeAgreement("F");
                SendMessageUntil.toServer(mes);
                AircraftJframe.getAircraftJframe().getaircraftJPanel().getBtnFight().setText("飞行");
                ManimgAttribute.ISFLY = false;
            }
            return;
        }
    }
    
    public static void dogame(LoginResult loginResult) {
        int x = loginResult.getX().intValue();
        int y = loginResult.getY().intValue();
        int map_id = loginResult.getMapid().intValue();
        boolean isCM = Util.ditubianma != map_id;
        if (isCM) {
            Util.CREEPSMAP = false;
        }
        loadMap(map_id, x, y);
        Util.mapName = Util.mapmodel.getGamemap().getMapname();
        ZhuJpanel.getxAndY().setText(Util.mapName + "   [ " + (long)loginResult.getX() / 20L + "," + (long)loginResult.getY() / 20L + " ]");
        ZhuJpanel.getMapName().setText(Util.mapmodel.getGamemap().getMapname());
        if (isCM && PKSys.getPkSys().getPk1() > 4 && PKSys.getPkSys().getPk2() == 0) {
            String[] v = ImageMixDeal.userimg.getTeams();
            if (v != null && !Util.CREEPSMAP && Util.random.nextInt(100) < 30) {
                Creeps.getfight(15);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("你当前有" + PKSys.getPkSys().getPk1() + "点PK标志,进城有惊喜");
            }
        }
        Timer timer = new Timer();
        long date = 0L;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                String format = Util.getHour(date.getHours());
                format = format.replace("", " ").trim();
                ZhuJpanel.getTimemiao().setText(format);
            }
        }, date, 2000L);
    }
    
    public static void writeHotkey(String roleId, String text) {
        try {
            System.out.println(GameClient.Mac);
            String filein = GameClient.Mac + text;
            OutputStreamWriter write = new OutputStreamWriter(Files.newOutputStream(new File("resource/other/hotKey_" + roleId + ".txt").toPath(), new OpenOption[0]), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write((String)Objects.requireNonNull(AC999.AESJDKEncode(filein)));
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String readHotkey(String roleId) {
        String value = readTxt("resource/other/hotKey_" + roleId + ".txt");
        if (value == null || "".equals(value)) {
            return null;
        }
        String value2 = AC999.AESJDKDncode(value);
        if (value2 == null) {
            if (value.contains("=")) {
                writeUserPwd(value);
                return value;
            }
            return "";
        }
        else {
            value2 = value2.substring(13);
            if (GameClient.Mac == null) {
                try {
                    GameClient.getServerIpAndPort();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (value2.startsWith(GameClient.Mac)) {
                return value2.substring(17);
            }
            return "";
        }
    }
    
    public void dtime() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        loginResult.setGameTimeRemaining(Integer.valueOf((int)loginResult.getGameTimeRemaining() - 1));
    }
    
    public static void GotoCreeps() {
        if (Util.random.nextInt(320) == 0 && good()) {
            Creeps.yeguai();
        }
    }
    
    public static boolean good() {
        if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
            return false;
        }
        boolean k = true;
        if (ImageMixDeal.userimg.getRoleShow().getFly_id() != 0) {
            k = false;
        }
        return k && (TimeLimit.getLimits().getLimit("摄妖香") == null || AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade()) - 10 <= Integer.parseInt(Util.mapmodel.getGamemap().getMaplvl()));
    }
    
    public static void DarkCreeps(String ids) {
        if (ids == null || ids.equals("")) {
            Util.CREEPSMAP = false;
        }
        else {
            Util.CREEPSMAP = true;
        }
    }
    
    public static int getSex(BigDecimal se) {
        Set<BigDecimal> sepcisesone = new HashSet<>();
        sepcisesone.add(new BigDecimal(22009));
        sepcisesone.add(new BigDecimal(22011));
        sepcisesone.add(new BigDecimal(21009));
        sepcisesone.add(new BigDecimal(21011));
        sepcisesone.add(new BigDecimal(20009));
        sepcisesone.add(new BigDecimal(20011));
        Set<BigDecimal> sepcisestwo = new HashSet<>();
        sepcisestwo.add(new BigDecimal(22010));
        sepcisestwo.add(new BigDecimal(22012));
        sepcisestwo.add(new BigDecimal(21010));
        sepcisestwo.add(new BigDecimal(21012));
        sepcisestwo.add(new BigDecimal(20010));
        sepcisestwo.add(new BigDecimal(20012));
        if (sepcisesone.contains(se)) {
            return 1;
        }
        if (sepcisestwo.contains(se)) {
            return 0;
        }
        int id = se.intValue() % 10;
        return (id == 1 || id == 2 || id == 3 || id == 7 || id == 9) ? 1 : 0;
    }
    
    public static String getSexSting(BigDecimal se) {
        Set<BigDecimal> sepcisesone = new HashSet<>();
        sepcisesone.add(new BigDecimal(22009));
        sepcisesone.add(new BigDecimal(22011));
        sepcisesone.add(new BigDecimal(21009));
        sepcisesone.add(new BigDecimal(21011));
        sepcisesone.add(new BigDecimal(20009));
        sepcisesone.add(new BigDecimal(20011));
        Set<BigDecimal> sepcisestwo = new HashSet<>();
        sepcisestwo.add(new BigDecimal(22010));
        sepcisestwo.add(new BigDecimal(22012));
        sepcisestwo.add(new BigDecimal(21010));
        sepcisestwo.add(new BigDecimal(21012));
        sepcisestwo.add(new BigDecimal(20010));
        sepcisestwo.add(new BigDecimal(20012));
        if (sepcisesone.contains(se)) {
            return "男";
        }
        if (sepcisestwo.contains(se)) {
            return "女";
        }
        int id = se.intValue() % 10;
        return (id == 1 || id == 2 || id == 3 || id == 7 || id == 9) ? "男" : "女";
    }
    
    public static int getRace(BigDecimal se) {
        if (se == null) {
            return 0;
        }
        int id = se.intValue();
        if (id >= 20001 && id <= 20012) {
            return 10001;
        }
        if (id >= 21001 && id <= 21012) {
            return 10002;
        }
        if (id >= 22001 && id <= 22012) {
            return 10003;
        }
        if (id >= 23001 && id <= 23012) {
            return 10004;
        }
        return 10005;
    }
    
    public static String getRaceSting(BigDecimal se) {
        if (se == null) {
            return "";
        }
        int id = se.intValue();
        if (id >= 20001 && id <= 20012) {
            return "人";
        }
        if (id >= 21001 && id <= 21012) {
            return "魔";
        }
        if (id >= 22001 && id <= 22012) {
            return "仙";
        }
        if (id >= 23001 && id <= 23012) {
            return "鬼";
        }
        return "龙";
    }
    
    public static boolean special(String ChangeName) {
        String[] a = { "!", "|", "*", "&", "@", "#", "$", "%", "^", "/", " " };
        for (int i = 0; i < a.length; ++i) {
            if (ChangeName.indexOf(a[i]) != -1) {
                return false;
            }
        }
        return true;
    }
    
    public static void drawMoney(Graphics g, int x, int y) {
        BigDecimal money = RoleData.getRoleData().getLoginResult().getGold();
        drawPrice(g, money, x, y);
    }
    
    public static void drawMoneyJF(Graphics g, int x, int y) {
        BigDecimal money = new BigDecimal((int)RoleData.getRoleData().getLoginResult().getMoney());
        drawPrice(g, money, x, y);
    }
    
    public static void drawTotalXianYu(Graphics g, int x, int y) {
        BigDecimal money = new BigDecimal(RoleData.getRoleData().getLoginResult().getCodecard().longValue());
        drawPrice(g, money, x, y);
    }
    
    public static void drawMoneyS(Graphics g, int x, int y) {
        g.drawString(RoleData.getRoleData().getLoginResult().getCodecard().toString(), x, y);
    }
    
    public static void drawPrice(Graphics g, BigDecimal money, int x, int y) {
        if (money.compareTo(Util.b1) < 0) {
            g.setColor(Color.white);
        }
        else if (money.compareTo(Util.b2) < 0) {
            g.setColor(new Color(36, 219, 118));
        }
        else if (money.compareTo(Util.b3) < 0) {
            g.setColor(new Color(253, 68, 221));
        }
        else if (money.compareTo(Util.b4) < 0) {
            g.setColor(new Color(251, 217, 50));
        }
        else if (money.compareTo(Util.b5) < 0) {
            g.setColor(new Color(0, 239, 239));
        }
        else if (money.compareTo(Util.b6) < 0) {
            g.setColor(Color.GREEN);
        }
        else if (money.compareTo(Util.b7) < 0) {
            g.setColor(Color.RED);
        }
        else {
            g.setColor(new Color(216, 23, 217));
        }
        g.setFont(UIUtils.TEXT_FONT1);
        if (money.compareTo(new BigDecimal(1000)) >= 0) {
            String str = money.toString();
            StringBuffer gold = new StringBuffer(str);
            for (int index = gold.length() - 3; index > 0; index -= 3) {
                gold.insert(index, ',');
            }
            g.drawString(gold.toString(), x, y);
            g.drawString(gold.toString(), x, y);
        }
        else {
            g.drawString(money.toString(), x, y);
            g.drawString(money.toString(), x, y);
        }
    }
    
    public static void changeTextColor(JComponent component, BigDecimal money) {
        if (money.compareTo(Util.b1) < 0) {
            component.setForeground(Color.white);
        }
        else if (money.compareTo(Util.b2) < 0) {
            component.setForeground(new Color(36, 219, 118));
        }
        else if (money.compareTo(Util.b3) < 0) {
            component.setForeground(new Color(253, 68, 221));
        }
        else if (money.compareTo(Util.b4) < 0) {
            component.setForeground(new Color(251, 217, 50));
        }
        else if (money.compareTo(Util.b5) < 0) {
            component.setForeground(new Color(0, 239, 239));
        }
        else if (money.compareTo(Util.b6) < 0) {
            component.setForeground(Color.GREEN);
        }
        else if (money.compareTo(Util.b7) < 0) {
            component.setForeground(Color.RED);
        }
        else {
            component.setForeground(new Color(216, 23, 217));
        }
    }
    
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    
    public static List<String> readGG(String path) {
        List<String> gg = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            try {
                String read;
                while ((read = br.readLine()) != null) {
                    gg.add(read);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            br.close();
            isr.close();
            fis.close();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return gg;
    }
    
    public static String readUserPwd() {
        String value = readTxt("resource/other/RecordUser.txt");
        if (value == null || "".equals(value)) {
            return "";
        }
        String value2 = AC999.AESJDKDncode(value);
        if (value2 == null) {
            if (value.contains("=")) {
                writeUserPwd(value);
                return value;
            }
            return "";
        }
        else {
            value2 = value2.substring(13);
            if (GameClient.Mac == null) {
                try {
                    GameClient.getServerIpAndPort();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (value2.startsWith(GameClient.Mac)) {
                return value2.substring(17);
            }
            return "";
        }
    }
    
    public static void writeUserPwd(String text) {
        try {
            System.out.println(GameClient.Mac);
            String filein = GameClient.Mac + text;
            OutputStreamWriter write = new OutputStreamWriter(Files.newOutputStream(new File("resource/other/RecordUser.txt").toPath(), new OpenOption[0]), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write((String)Objects.requireNonNull(AC999.AESJDKEncode(filein)));
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String readTxt(String path) {
        String readStr = "";
        try {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                try {
                    String read;
                    while ((read = br.readLine()) != null) {
                        readStr += read;
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                br.close();
                isr.close();
                fis.close();
            }
            else {
                return null;
            }
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return readStr;
    }
    
    public static String getSkillMsg(String mountSkillName) {
        return (String)Util.mountSkillMsgMap.get(mountSkillName);
    }
    
    public static String changeToPercentage(String mountskillmsg) {
        String newmsg = "";
        String[] strings = mountskillmsg.split("\\=");
        String s = strings[0];
        int n = -1;
        switch (s.hashCode()) {
            case 771859205: {
                if (s.equals("抗三尸虫")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case -1580214290: {
                if (s.equals("抗灵宝伤害")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0:
            case 1: {
                newmsg = "增加" + strings[0] + "#R" + Integer.parseInt(strings[1]);
                break;
            }
            default: {
                newmsg = "增加" + strings[0] + "#R" + keepTwoDecimals(Double.valueOf(Double.parseDouble(strings[1]) * 100.0), 1) + "%";
                break;
            }
        }
        return newmsg;
    }
    
    public static String calculateAdditionBase(Mount mount, String skillname) {
        if (skillname.equals("夺命追魂")) {
            return returnsCalculation("SP", mount, "夺命追魂");
        }
        if (skillname.equals("天雷怒火")) {
            return returnsCalculation("MP", mount, "天雷怒火");
        }
        if (skillname.equals("金身不坏")) {
            return returnsCalculation("HP", mount, "金身不坏");
        }
        if (skillname.equals("破釜沉舟")) {
            return returnsCalculation("AP", mount, "破釜沉舟");
        }
        if (skillname.equals("兴风作浪")) {
            return returnsCalculation("MP", mount, "兴风作浪");
        }
        if (skillname.equals("天神护体")) {
            return returnsCalculation("SP", mount, "天神护体");
        }
        if (skillname.equals("后发制人")) {
            return returnsCalculation("HP", mount, "后发制人");
        }
        if (skillname.equals("万劫不复")) {
            return returnsCalculation("MP", mount, "万劫不复");
        }
        if (skillname.equals("心如止水")) {
            return returnsCalculation("HP", mount, "心如止水");
        }
        if (skillname.equals("视险如夷")) {
            return returnsCalculation("HP", mount, "视险如夷");
        }
        if (skillname.equals("游刃有余")) {
            return returnsCalculation("SP", mount, "游刃有余");
        }
        if (skillname.equals("反客为主")) {
            return returnsCalculation("AP", mount, "反客为主");
        }
        if (skillname.equals("反治其身")) {
            return returnsCalculation("HP", mount, "反治其身");
        }
        if (skillname.equals("得心应手")) {
            return returnsCalculation("MP", mount, "得心应手");
        }
        if (skillname.equals("山外青山")) {
            return returnsCalculation("SP", mount, "山外青山");
        }
        return null;
    }
    
    public static String calculateAddition(Mount mount, String skillname) {
        String value = "";
        if (skillname.equals("夺命追魂")) {
            String s1 = returnsCalculation("SP", mount, "夺命追魂");
            String s2 = returnsCalculation("连击率", mount, "夺命追魂");
            String s3 = returnsCalculation("致命", mount, "夺命追魂");
            String s4 = returnsCalculation("命中", mount, "夺命追魂");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4;
        }
        else if (skillname.equals("天雷怒火")) {
            String s1 = returnsCalculation("MP", mount, "天雷怒火");
            String s2 = returnsCalculation("火法伤害", mount, "天雷怒火");
            String s3 = returnsCalculation("雷法伤害", mount, "天雷怒火");
            String s4 = returnsCalculation("鬼火伤害", mount, "天雷怒火");
            String s5 = returnsCalculation("抗火法", mount, "天雷怒火");
            String s6 = returnsCalculation("抗雷法", mount, "天雷怒火");
            String s7 = returnsCalculation("抗鬼火", mount, "天雷怒火");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6 + "|" + s7;
        }
        else if (skillname.equals("金身不坏")) {
            String s1 = returnsCalculation("HP", mount, "金身不坏");
            String s2 = returnsCalculation("抗物理", mount, "金身不坏");
            String s3 = returnsCalculation("抗震慑", mount, "金身不坏");
            String s4 = returnsCalculation("抗中毒", mount, "金身不坏");
            String s5 = returnsCalculation("抗三尸虫", mount, "金身不坏");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5;
        }
        else if (skillname.equals("破釜沉舟")) {
            String s1 = returnsCalculation("AP", mount, "破釜沉舟");
            String s2 = returnsCalculation("狂暴", mount, "破釜沉舟");
            String s3 = returnsCalculation("忽视防御几率", mount, "破釜沉舟");
            String s4 = returnsCalculation("忽视防御程度", mount, "破釜沉舟");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4;
        }
        else if (skillname.equals("兴风作浪")) {
            String s1 = returnsCalculation("MP", mount, "兴风作浪");
            String s2 = returnsCalculation("风法伤害", mount, "兴风作浪");
            String s3 = returnsCalculation("水法伤害", mount, "兴风作浪");
            String s4 = returnsCalculation("抗风法", mount, "兴风作浪");
            String s5 = returnsCalculation("抗水法", mount, "兴风作浪");
            String s6 = returnsCalculation("抗鬼火", mount, "兴风作浪");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6;
        }
        else if (skillname.equals("天神护体")) {
            String s1 = returnsCalculation("SP", mount, "天神护体");
            String s2 = returnsCalculation("抗风法", mount, "天神护体");
            String s3 = returnsCalculation("抗火法", mount, "天神护体");
            String s4 = returnsCalculation("抗水法", mount, "天神护体");
            String s5 = returnsCalculation("抗雷法", mount, "天神护体");
            String s6 = returnsCalculation("抗鬼火", mount, "天神护体");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6;
        }
        else if (skillname.equals("后发制人")) {
            String s1 = returnsCalculation("HP", mount, "后发制人");
            String s2 = returnsCalculation("狂暴", mount, "后发制人");
            value = s1 + "|" + s2;
        }
        else if (skillname.equals("万劫不复")) {
            String s1 = returnsCalculation("MP", mount, "万劫不复");
            String s2 = returnsCalculation("加强风", mount, "万劫不复");
            String s3 = returnsCalculation("加强火", mount, "万劫不复");
            String s4 = returnsCalculation("加强水", mount, "万劫不复");
            String s5 = returnsCalculation("加强雷", mount, "万劫不复");
            String s6 = returnsCalculation("加强鬼火", mount, "万劫不复");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6;
        }
        else if (skillname.equals("心如止水")) {
            String s1 = returnsCalculation("HP", mount, "心如止水");
            String s2 = returnsCalculation("抗昏睡", mount, "心如止水");
            String s3 = returnsCalculation("抗封印", mount, "心如止水");
            String s4 = returnsCalculation("抗中毒", mount, "心如止水");
            String s5 = returnsCalculation("抗混乱", mount, "心如止水");
            String s6 = returnsCalculation("抗遗忘", mount, "心如止水");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5 + "|" + s6;
        }
        else if (skillname.equals("视险如夷")) {
            String s1 = returnsCalculation("HP", mount, "视险如夷");
            String s2 = returnsCalculation("抵御强克效果", mount, "视险如夷");
            value = s1 + "|" + s2;
        }
        else if (skillname.equals("游刃有余")) {
            String s1 = returnsCalculation("SP", mount, "游刃有余");
            String s2 = returnsCalculation("法术躲闪率", mount, "游刃有余");
            String s3 = returnsCalculation("抗灵宝伤害", mount, "游刃有余");
            value = s1 + "|" + s2 + "|" + s3;
        }
        else if (skillname.equals("反客为主")) {
            String s1 = returnsCalculation("AP", mount, "反客为主");
            String s2 = returnsCalculation("反击率", mount, "反客为主");
            String s3 = returnsCalculation("反击忽视防御几率", mount, "反客为主");
            String s4 = returnsCalculation("反击忽视防御程度", mount, "反客为主");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4;
        }
        else if (skillname.equals("反治其身")) {
            String s1 = returnsCalculation("HP", mount, "反治其身");
            String s2 = returnsCalculation("躲闪率", mount, "反治其身");
            String s3 = returnsCalculation("反震率", mount, "反治其身");
            String s4 = returnsCalculation("反震程度", mount, "反治其身");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4;
        }
        else if (skillname.equals("得心应手")) {
            String s1 = returnsCalculation("MP", mount, "得心应手");
            String s2 = returnsCalculation("忽视仙法", mount, "得心应手");
            String s3 = returnsCalculation("忽视鬼火", mount, "得心应手");
            String s4 = returnsCalculation("仙法狂暴", mount, "得心应手");
            String s5 = returnsCalculation("鬼火狂暴几率", mount, "得心应手");
            value = s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5;
        }
        else if (skillname.equals("山外青山")) {
            String s1 = returnsCalculation("SP", mount, "山外青山");
            String s2 = returnsCalculation("增加强克效果", mount, "山外青山");
            value = s1 + "|" + s2;
        }
        return value;
    }
    
    public static String returnsCalculation(String mes, Mount mount, String skillname) {
        double zjxz = 1.0;
        if ((int)mount.getMountid() == 2 || (int)mount.getMountid() == 4) {
            zjxz = 1.2;
        }
        int grade = (int)mount.getMountlvl();
        if (grade > 100) {
            grade -= 100;
        }
        int lingxing = (int)Math.floor((double)((float)(int)mount.getSpri() + (float)grade / 10.0f * (float)(int)mount.getSpri() / 2.0f));
        int liliang = (int)Math.floor((double)((float)(int)mount.getPower() + (float)grade / 10.0f * (float)(int)mount.getPower() / 2.0f));
        int genggu = (int)Math.floor((double)((float)(int)mount.getBone() + (float)grade / 10.0f * (float)(int)mount.getBone() / 2.0f));
        int shulian = (int)mount.getProficiency();
        double jnxgz = 0.0;
        if (mes.equals("HP")) {
            if (skillname.equals("金身不坏")) {
                jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[1]) * zjxz / Util.zuoqi[4][0] + (double)(shulian / 10000) / Util.zuoqi[4][1];
            }
            else if (skillname.equals("后发制人")) {
                jnxgz = ((double)genggu * Util.xishu[0] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[3]) * zjxz / Util.zuoqi[16][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[16][1];
            }
            else if (skillname.equals("心如止水")) {
                jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[1][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[1][1];
            }
            else if (skillname.equals("视险如夷")) {
                jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[1][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[22][0];
            }
            else if (skillname.equals("反治其身")) {
                jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[1][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[1][1];
            }
        }
        else if (mes.equals("MP")) {
            if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[3] + (double)liliang * Util.xishu[0]) * zjxz / Util.zuoqi[13][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[13][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * Util.xishu[1] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[10][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[10][1];
            }
            else if (skillname.equals("万劫不复")) {
                jnxgz = ((double)genggu * Util.xishu[5] + (double)(lingxing * 1) + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
            }
            else if (skillname.equals("得心应手")) {
                jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
            }
        }
        else if (mes.equals("AP")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[4] + (double)(liliang * 1)) * zjxz / Util.zuoqi[18][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[18][1];
            if (skillname.equals("反客为主")) {
                jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[3]) * zjxz / Util.zuoqi[22][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[22][1];
            }
        }
        else if (mes.equals("SP")) {
            if (skillname.equals("夺命追魂")) {
                jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[0] + (double)liliang * Util.xishu[3]) * zjxz / Util.zuoqi[21][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[21][1];
            }
            else if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[3][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[3][1];
            }
            else if (skillname.equals("山外青山")) {
                jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[21][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[22][0];
            }
            else if (skillname.equals("游刃有余")) {
                jnxgz = ((double)genggu * Util.xishu[1] + (double)liliang * Util.xishu[1] + (double)lingxing * Util.xishu[4]) * zjxz / Util.zuoqi[21][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[20][0];
            }
        }
        else if (mes.equals("连击率") || mes.equals("致命") || mes.equals("命中")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[22][0] + (double)(shulian / 10000) / Util.zuoqi[22][1];
        }
        else if (mes.equals("反击率")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[2][0] + (double)(shulian / 10000) / Util.zuoqi[20][0];
        }
        else if (mes.equals("躲闪率")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[3] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[22][0] + (double)(shulian / 10000) / Util.zuoqi[21][0];
        }
        else if (mes.equals("反震率") || mes.equals("反震程度")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[3] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[22][0] + (double)(shulian / 10000) / Util.zuoqi[21][1];
        }
        else if (mes.equals("法术躲闪率")) {
            jnxgz = ((double)genggu * Util.xishu[1] + (double)liliang * Util.xishu[1] + (double)lingxing * Util.xishu[4]) * zjxz / Util.zuoqi[22][0] + (double)(shulian / 10000) / Util.zuoqi[21][0];
        }
        else if (mes.equals("抗灵宝伤害")) {
            jnxgz = ((double)genggu * Util.xishu[1] + (double)liliang * Util.xishu[1] + (double)lingxing * Util.xishu[4]) * zjxz / Util.zuoqi[21][0] + (double)shulian / Util.zuoqi[22][1];
        }
        else if (mes.equals("抵御强克效果")) {
            jnxgz = ((double)genggu * Util.xishu[8] + (double)lingxing * Util.xishu[1] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[1][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[0][1];
        }
        else if (mes.equals("增加强克效果")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[22][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[20][1];
        }
        else if (mes.equals("忽视仙法")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
        }
        else if (mes.equals("忽视鬼火")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
        }
        else if (mes.equals("仙法狂暴")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
        }
        else if (mes.equals("鬼火狂暴几率")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[2]) * zjxz / Util.zuoqi[8][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[8][1];
        }
        else if (mes.equals("抗鬼火")) {
            if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[2][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[2][1];
            }
            else if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[1]) * zjxz / Util.zuoqi[15][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[15][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * Util.xishu[1] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[12][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[12][1];
            }
        }
        else if (mes.equals("抗风法") || mes.equals("抗火法") || mes.equals("抗水法") || mes.equals("抗雷法")) {
            if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[2][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[2][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * Util.xishu[1] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[12][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[12][1];
            }
            else if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[1]) * zjxz / Util.zuoqi[15][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[15][1];
            }
        }
        else if (mes.equals("火法伤害") || mes.equals("雷法伤害") || mes.equals("鬼火伤害") || mes.equals("火雷伤害")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[3] + (double)liliang * Util.xishu[0]) * zjxz / Util.zuoqi[14][0] + (double)(shulian / 10000) / Util.zuoqi[14][1];
        }
        else if (mes.equals("风系狂暴几率") || mes.equals("火系狂暴几率") || mes.equals("水系狂暴几率") || mes.equals("雷系狂暴几率") || mes.equals("鬼火狂暴几率")) {
            jnxgz = ((double)genggu * Util.xishu[1] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[19][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[19][1];
        }
        else if (mes.equals("抗火雷")) {
            jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[2] + (double)liliang * Util.xishu[1]) * zjxz / Util.zuoqi[15][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[15][1];
        }
        else if (mes.equals("抗物理")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[5] + (double)liliang * Util.xishu[0]) * zjxz / Util.zuoqi[5][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[5][1];
        }
        else if (mes.equals("抗震慑")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[1]) * zjxz / Util.zuoqi[6][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[6][1];
        }
        else if (mes.equals("抗中毒")) {
            if (skillname.equals("金身不坏")) {
                jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[0]) * zjxz / Util.zuoqi[7][0] + (double)(shulian / 10000) / Util.zuoqi[7][1];
            }
            else if (skillname.equals("心如止水")) {
                jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[0] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[0][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[0][1];
            }
        }
        else if (mes.equals("抗三尸虫")) {
            jnxgz = ((double)genggu * Util.xishu[2] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[0]) * 125.0 / 3.0 + (double)(shulian * 1500 / 100000);
        }
        else if (mes.equals("狂暴")) {
            if (skillname.equals("破釜沉舟")) {
                jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[4] + (double)(liliang * 1)) * zjxz / Util.zuoqi[19][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[19][1];
            }
            else if (skillname.equals("后发制人")) {
                jnxgz = ((double)genggu * Util.xishu[1] + (double)lingxing * Util.xishu[5] + (double)liliang * Util.xishu[3]) * zjxz / Util.zuoqi[17][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[17][1];
            }
        }
        else if (mes.equals("忽视防御几率") || mes.equals("忽视防御程度")) {
            jnxgz = ((double)genggu * Util.xishu[4] + (double)lingxing * Util.xishu[5] + (double)(liliang * 1)) * zjxz / Util.zuoqi[20][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[20][1];
        }
        else if (mes.equals("反击忽视防御几率") || mes.equals("反击忽视防御程度")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)lingxing * Util.xishu[4] + (double)liliang * Util.xishu[3]) * zjxz / Util.zuoqi[22][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[22][1];
        }
        else if (mes.equals("风法伤害") || mes.equals("水法伤害")) {
            jnxgz = ((double)genggu * Util.xishu[0] + (double)lingxing * Util.xishu[3] + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[11][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[11][1];
        }
        else if (mes.equals("加强风") || mes.equals("加强火") || mes.equals("加强水") || mes.equals("加强雷") || mes.equals("加强鬼火")) {
            jnxgz = ((double)genggu * Util.xishu[5] + (double)(lingxing * 1) + (double)liliang * Util.xishu[5]) * zjxz / Util.zuoqi[9][0] + (double)shulian / Util.xishu[6] / Util.zuoqi[9][1];
        }
        else if (mes.equals("抗昏睡") || mes.equals("抗封印") || mes.equals("抗混乱")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[0] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[0][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[0][1];
        }
        else if (mes.equals("抗遗忘")) {
            jnxgz = ((double)genggu * Util.xishu[3] + (double)lingxing * Util.xishu[0] + (double)liliang * Util.xishu[4]) * zjxz / Util.zuoqi[0][0] + (double)shulian / Util.xishu[7] / Util.zuoqi[0][1];
        }
        String ss = mes + "=" + keepTwoDecimals(Double.valueOf(jnxgz / 100.0), 3);
        int n = -1;
        switch (mes.hashCode()) {
            case 771859205: {
                if (mes.equals("抗三尸虫")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case -1580214290: {
                if (mes.equals("抗灵宝伤害")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0:
            case 1: {
                ss = mes + "=" + (int)keepTwoDecimals(Double.valueOf(jnxgz), 0);
                break;
            }
        }
        return ss;
    }
    
    public static double keepTwoDecimals(Double value, int newScale) {
        BigDecimal b = new BigDecimal((double)value);
        double d = b.setScale(newScale, 4).doubleValue();
        return d;
    }
    
    public static long getTime() {
        return Util.BJTime;
    }
    
    public static long getTime2() {
        synchronized (Util.object) {
            ++Util.s;
            return ++Util.BJTime;
        }
    }
    
    public static void addtime(int sj) {
        synchronized (Util.object) {
            sj -= Util.s;
            Util.s = 0;
            Util.BJTime += (long)sj;
        }
    }
    
    public static boolean isM() {
        if (Util.BJTime - Util.time < 200L) {
            return true;
        }
        Util.time = Util.BJTime;
        return false;
    }
    
    public static boolean isCanBuyOrno() {
        if (!Util.canBuyOrno) {
            if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                FormsManagement.showForm(32);
            }
            else {
                FormsManagement.showForm(33);
            }
            return true;
        }
        else {
            return UserStallUntil.isStall();
        }
    }
    
    public static boolean isCanBuyOrno1() {
        if (!Util.canBuyOrno) {
            if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                FormsManagement.showForm(32);
            }
            else {
                FormsManagement.showForm(33);
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static String getHour(int hour) {
        String SC = "";
        if (hour == 23 || hour == 0) {
            SC = "子时";
        }
        if (hour == 1 || hour == 2) {
            SC = "丑时";
        }
        if (hour == 3 || hour == 4) {
            SC = "寅时";
        }
        if (hour == 5 || hour == 6) {
            SC = "卯辰";
        }
        if (hour == 7 || hour == 8) {
            SC = "辰时";
        }
        if (hour == 9 || hour == 10) {
            SC = "巳时";
        }
        if (hour == 11 || hour == 12) {
            SC = "午时";
        }
        if (hour == 13 || hour == 14) {
            SC = "未时";
        }
        if (hour == 15 || hour == 16) {
            SC = "申时";
        }
        if (hour == 17 || hour == 18) {
            SC = "酉时";
        }
        if (hour == 19 || hour == 20) {
            SC = "戌时";
        }
        if (hour == 21 || hour == 22) {
            SC = "亥时";
        }
        return SC;
    }
    
    public static String formatTime(Long ms) {
        if (!Util.qbg) {
            return "";
        }
        Integer ss = Integer.valueOf(1000);
        Integer mi = Integer.valueOf((int)ss * 60);
        Integer hh = Integer.valueOf((int)mi * 60);
        Integer dd = Integer.valueOf((int)hh * 24);
        Long day = Long.valueOf((long)ms / (long)(int)dd);
        Long hour = Long.valueOf(((long)ms - (long)day * (long)(int)dd) / (long)(int)hh);
        Long minute = Long.valueOf(((long)ms - (long)day * (long)(int)dd - (long)hour * (long)(int)hh) / (long)(int)mi);
        Long second = Long.valueOf(((long)ms - (long)day * (long)(int)dd - (long)hour * (long)(int)hh - (long)minute * (long)(int)mi) / (long)(int)ss);
        if ((long)second != 0L && Util.jz != second && (long)second % (long)Util.tm == 0L) {
            if (Util.pdtype.equals("2")) {
                if (UserData.uptael((long)Util.hf)) {
                    LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                    Long exp = Long.valueOf(0L);
                    if (loginResult.getTurnAround() == 0) {
                        exp = Util.jy;
                    }
                    else if (loginResult.getTurnAround() == 1) {
                        exp = Util.jy;
                    }
                    else if (loginResult.getTurnAround() == 2) {
                        exp = Long.valueOf((long)Util.jy * 2L);
                    }
                    else if (loginResult.getTurnAround() == 3) {
                        exp = Long.valueOf((long)Util.jy * 3L);
                    }
                    else if (loginResult.getTurnAround() == 4) {
                        exp = Long.valueOf((long)Util.jy * 4L);
                    }
                    SendMessageUntil.toServer(Agreement.getAgreement().expaddAgreement("expAddJ=" + exp));
                    Util.hdzjy = Long.valueOf((long)Util.hdzjy + (long)exp);
                    Util.xfzje = Long.valueOf((long)Util.xfzje + (long)Util.hf);
                    Util.jz = second;
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("#R注意：#Y金钱不足，退出泡点地图！努力赚钱，下次再来！");
                    iWantToFly("1207,长安城,4960,3820");
                    Util.qbg = false;
                }
            }
            else if (Util.pdtype.equals("3")) {
                Long xy = Long.valueOf((long)Util.hf / 10000L);
                if (isxy(xy)) {
                    LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
                    Long exp2 = Long.valueOf(0L);
                    if (loginResult2.getTurnAround() == 0) {
                        exp2 = Long.valueOf((long)Util.jy * 2L);
                    }
                    else if (loginResult2.getTurnAround() == 1) {
                        exp2 = Long.valueOf((long)Util.jy * 2L);
                    }
                    else if (loginResult2.getTurnAround() == 2) {
                        exp2 = Long.valueOf((long)Util.jy * 3L);
                    }
                    else if (loginResult2.getTurnAround() == 3) {
                        exp2 = Long.valueOf((long)Util.jy * 4L);
                    }
                    else if (loginResult2.getTurnAround() == 4) {
                        exp2 = Long.valueOf((long)Util.jy * 5L);
                    }
                    SendMessageUntil.toServer(Agreement.getAgreement().expaddAgreement("expAddY=" + exp2 + "=" + xy));
                    Util.hdzjy = Long.valueOf((long)Util.hdzjy + (long)exp2);
                    Util.xfzxy = Long.valueOf((long)Util.xfzxy + (long)xy);
                    Util.jz = second;
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("#R注意：#Y仙玉不足，退出泡点地图！努力赚钱，下次再来！");
                    iWantToFly("1207,长安城,4960,3820");
                    Util.qbg = false;
                }
            }
            else if (Util.pdtype.equals("1")) {
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                Long exp = Long.valueOf(0L);
                if (loginResult.getTurnAround() == 0) {
                    exp = Long.valueOf((long)Util.jy / 10L);
                }
                else if (loginResult.getTurnAround() == 1) {
                    exp = Long.valueOf((long)Util.jy / 10L);
                }
                else if (loginResult.getTurnAround() == 2) {
                    exp = Long.valueOf((long)Util.jy * 2L / 10L);
                }
                else if (loginResult.getTurnAround() == 3) {
                    exp = Long.valueOf((long)Util.jy * 3L / 10L);
                }
                else if (loginResult.getTurnAround() == 4) {
                    exp = Long.valueOf((long)Util.jy * 4L / 10L);
                }
                exp = Long.valueOf((long)exp * (long)Util.victory);
                SendMessageUntil.toServer(Agreement.getAgreement().expaddAgreement("expAddM=" + exp + "=" + Util.victory));
                Util.hdzjy = Long.valueOf((long)Util.hdzjy + (long)exp);
                Util.jz = second;
            }
        }
        StringBuffer sb = new StringBuffer();
        if ((long)day > 0L) {
            sb.append(day + "天");
        }
        if ((long)hour > 0L) {
            sb.append(hour + "小时");
        }
        if ((long)minute > 0L) {
            sb.append(minute + "分");
        }
        if ((long)second > 0L) {
            sb.append(second + "秒");
        }
        return sb.toString();
    }
    
    public static void iWantToFly(String npcfeiji) {
        if (npcfeiji == null || npcfeiji.length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("坐标为空，无法传送");
        }
        else {
            String kkf = "传送=" + npcfeiji + ", 可用次数=10";
            String kkf2 = "传送=" + npcfeiji;
            String kkf3 = npcfeiji;
            String[] vs = kkf.split(",");
            String[] cishu = vs[vs.length - 1].split("=");
            int sum = Integer.parseInt(cishu[1]);
            --sum;
            String[] path = null;
            StringBuffer buffer = new StringBuffer();
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(kkf2);
            if (path == null) {
                String[] pathv = kkf3.split(",");
                String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                path = pathv;
            }
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(cishu[0]);
            buffer.append("=");
            buffer.append(sum);
            if (path != null) {
                if (sum <= 0) {
                    NPCJfram.getNpcJfram().getNpcjpanel().getGood().setUsetime(Integer.valueOf(0));
                }
                GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
                if (sum <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(NPCJfram.getNpcJfram().getNpcjpanel().getGood().getRgid());
                }
                Door door = new Door();
                door.setDoormap(path[0]);
                door.setDoorpoint(path[2] + "|" + path[3]);
                try {
                    TP.tp(door, 1);
                }
                catch (Exception var9) {
                    var9.printStackTrace();
                }
            }
        }
        FormsManagement.HideForm(1102);
    }
    
    public static boolean isjb(Long tael) {
        long shengyu = RoleData.getRoleData().getLoginResult().getGold().longValue() - (long)tael;
        return (long)tael > 0L && shengyu >= 0L;
    }
    
    public static boolean isxy(Long tael) {
        return (long)tael <= RoleData.getRoleData().getLoginResult().getCodecard().longValue();
    }
    
    public static String number2Chinese(int value) {
        String result = "";
        for (int i = String.valueOf(value).length() - 1; i >= 0; --i) {
            int r = (int)((double)value / Math.pow(10.0, (double)i));
            result = result + Util.NUMS[r % 10] + Util.UNITS[i];
        }
        result = result.replaceAll("零[十, 百, 千]", "零");
        result = result.replaceAll("零+", "零");
        result = result.replaceAll("零([万, 亿])", "$1");
        result = result.replaceAll("亿万", "亿");
        if (result.startsWith("一十")) {
            result = result.substring(1);
        }
        if (result.endsWith("零")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
    
    public static void writeAutoNum(String roleId, String text) {
        try {
            System.out.println(GameClient.Mac);
            String filein = GameClient.Mac + text;
            OutputStreamWriter write = new OutputStreamWriter(Files.newOutputStream(new File("resource/other/autoNum_" + roleId + ".txt").toPath(), new OpenOption[0]), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write((String)Objects.requireNonNull(AC999.AESJDKEncode(filein)));
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String readAutoNum(String roleId) {
        String value = readTxt("resource/other/autoNum_" + roleId + ".txt");
        if (value == null || "".equals(value)) {
            return null;
        }
        String value2 = AC999.AESJDKDncode(value);
        value2 = value2.substring(13);
        if (GameClient.Mac == null) {
            try {
                GameClient.getServerIpAndPort();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (value2.startsWith(GameClient.Mac)) {
            return value2.substring(17);
        }
        return "";
    }
    
    public static Color getPriceColor(BigDecimal money) {
        if (money.compareTo(Util.b1) < 0) {
            return Color.white;
        }
        if (money.compareTo(Util.b2) < 0) {
            return new Color(37, 218, 119);
        }
        if (money.compareTo(Util.b3) < 0) {
            return new Color(253, 68, 221);
        }
        if (money.compareTo(Util.b4) < 0) {
            return new Color(251, 217, 50);
        }
        if (money.compareTo(Util.b5) < 0) {
            return new Color(0, 239, 239);
        }
        if (money.compareTo(Util.b6) < 0) {
            return new Color(10, 253, 4);
        }
        return Color.RED;
    }
    
    public static String readUserInfo() {
        try {
            GameClient.Mac = GameClient.getLocalMac();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String value = readTxt("resource/other/RecordUserInfo.txt");
        if (value == null || "".equals(value)) {
            return "";
        }
        String value2 = null;
        try {
            value2 = AC999.AESJDKDncode_utf8(value);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        if (value2 == null) {
            return "";
        }
        value2 = value2.substring(13);
        if (value2.startsWith(GameClient.Mac)) {
            return value2.substring(17);
        }
        return "";
    }
    
    public static void writeUserInfo(String text) {
        try {
            String filein = GameClient.Mac + text;
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(new File("resource/other/RecordUserInfo.txt")), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(AC999.AESJDKEncode(filein));
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static RegionResult getRegionResultByName(String name) {
        for (int i = 0; i < Util.regionResultList.size(); ++i) {
            if (((RegionResult)Util.regionResultList.get(i)).getRA_NAME().equals(name)) {
                return (RegionResult)Util.regionResultList.get(i);
            }
        }
        return null;
    }
    
    static {
        NUMS = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        UNITS = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        Util.nowtime = 0L;
        Util.TIME_CHAT2 = 108;
        Util.TIME_CHAT = 5000L;
        Util.TIME_TCP = 180000L;
        Util.hideTitle = true;
        Util.CREEPSMAP = false;
        Util.random = new Random();
        Util.ditubianma = 0;
        Util.maptime = 0L;
        Util.mapmodel = new Mapmodel();
        Util.canBuyOrno = false;
        Util.nums = 0;
        Util.mapName = "";
        Util.xy = "";
        Util.startTime = Long.valueOf(0L);
        Util.startTimeName = "";
        Util.ifpf = false;
        Util.dhjl = false;
        Util.ZOU = false;
        Util.dj = false;
        Util.oldFly_id = 0;
        Util.oldFlyIndex = 0;
        Util.moveGrade = 0;
        Util.zzs = 5;
        Util.regionResultList = new ArrayList<>();
        Util.xishu = new double[] { 0.3, 0.3, 0.7, 0.7, 0.0, 0.0, 10000.0, 10000.0, 1.2 };
        Util.zuoqi = new double[][] { { 4.115226337, 1.141552511 }, { 14.40329218, 3.99543379 }, { 4.8, 1.333333333 }, { 14.4, 4.0 }, { 14.4, 4.0 }, { 3.6, 1.0 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 14.4, 4.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 3.6, 1.0 }, { 14.4, 4.0 }, { 7.2, 2.0 } };
        Util.GoldUP = new BigDecimal("999999999999");
        Util.vector = new Vector<>();
        Util.BJTime = (long)new Long("1587606505273");
        Util.vector.add("成员名称");
        Util.vector.add("职务");
        Util.vector.add("种族");
        Util.vector.add("等级");
        Util.vector.add("贡献");
        Util.vector.add("成就");
        Util.vector.add("上线时间");
        (Util.mountSkillMsgMap = new HashMap<>()).put("夺命追魂", "增加召唤兽的致命、连击和SP#r【适合】力量、灵性高的坐骑学习");
        Util.mountSkillMsgMap.put("破釜沉舟", "增加召唤兽的狂暴、AP和无视物理程度#r【适合】力量高的坐骑学习");
        Util.mountSkillMsgMap.put("后发制人", "增加召唤兽的狂暴、致命和HP#r【适合】力量、根骨高的坐骑学习");
        Util.mountSkillMsgMap.put("万劫不复", "增加召唤兽的四系法术、鬼火威力。#r【适合】灵性高的坐骑学习");
        Util.mountSkillMsgMap.put("天雷怒火", "增加召唤兽的火、雷、鬼火系法术威力和抗性#r【适合】灵性、力量高的坐骑学习");
        Util.mountSkillMsgMap.put("兴风作浪", "增加召唤兽的风、水系法术威力和抗性#r【适合】灵性、根骨高的坐骑学习");
        Util.mountSkillMsgMap.put("心如止水", "增加召唤兽的混乱、昏睡、封印抗性和HP#r【适合】根骨、灵性高的坐骑学习");
        Util.mountSkillMsgMap.put("天神护体", "增加仙族四系法术、鬼火抗性和SP#r【适合】根骨高的坐骑学习");
        Util.mountSkillMsgMap.put("金身不坏", "增加召唤兽的物理、震慑、毒抗性和HP#r【适合】根骨、力量高的坐骑学习");
        Util.mountSkillMsgMap.put("游刃有余", "增加法术躲闪、随机抗灵宝伤害和SP#r【适合】根骨、力量高的坐骑学习");
        Util.mountSkillMsgMap.put("视险如夷", "增加增加HP、抵御强克效果#r【适合】根骨、灵性高的坐骑学习");
        Util.mountSkillMsgMap.put("反客为主", "增加AP、反击、触发反击时增加忽视物理几率及程度#r【适合】力量、灵性高的坐骑学习");
        Util.mountSkillMsgMap.put("反治其身", "增加HP、躲闪率、反震率、反震程度#r【适合】力量、根骨高的坐骑学习");
        Util.mountSkillMsgMap.put("得心应手", "增加MP、忽视仙法鬼火抗性、仙法鬼火狂暴几率#r【适合】灵性、根骨高的坐骑学习");
        Util.mountSkillMsgMap.put("山外青山", "增加SP、强克效果#r【适合】灵性、力量高的坐骑学习");
        (Util.vector2 = new Vector<>()).add("名字");
        Util.vector2.add("等级");
        Util.vector2.add("种族");
        Util.vector2.add("性别");
        (Util.vector3 = new Vector<>()).add("");
        Util.vector3.add("");
        Util.vector3.add("");
        Util.vector3.add("");
        (Util.vector4 = new Vector<>()).add("");
        Util.vector4.add("");
        Util.vector4.add("");
        Util.vector4.add("");
        Util.vector4.add("");
        (Util.vector_3 = new Vector<>()).add("");
        Util.vector_3.add("");
        Util.vector_3.add("");
        Util.tm = Long.valueOf(0L);
        Util.hf = Long.valueOf(0L);
        Util.jy = Long.valueOf(0L);
        Util.jz = Long.valueOf(0L);
        Util.hdzjy = Long.valueOf(0L);
        Util.xfzje = Long.valueOf(0L);
        Util.xfzxy = Long.valueOf(0L);
        Util.pdtype = "";
        Util.victory = Long.valueOf(1L);
        Util.qbg = true;
        Util.ifjr = false;
        b1 = new BigDecimal(10000);
        b2 = new BigDecimal(100000);
        b3 = new BigDecimal(1000000);
        b4 = new BigDecimal(10000000);
        b5 = new BigDecimal(100000000);
        b6 = new BigDecimal(1000000000);
        b7 = new BigDecimal("10000000000");
        Util.MallX = SpriteFactory.VloadSprite("inkImg/hongmu/4389-D1D89FFE.tcp", null);
        Util.object = new Object();
        Util.s = 0;
    }

    /**
     * NPC头顶
     * @param type
     * @return
     */
    public static String getNPCType(int type) {
        String type111 = null;
        switch (type) {
            case 2:
            case 222:
            case 2222:

                type111 = "传送";
                break;
            case 4:
            case 70:
                type111 = "任务";
                break;
            case 5:
                type111 = "杂货";
                break;
            case 7:
                type111 = "商店";
                break;
            case 8:
            case 9:
                type111 = "药店";
                break;
            case 10://李靖
                type111= "任务";
                break;
            case 12://神兵
                type111= "神兵";
                break;
            case 13://矿石
                type111= "矿石";
                break;
            case 14://商店
            case 15://商店
                type111= "商店";
                break;
            case 16://巫医
                type111= "巫医";
                break;
            case 17://郎中
                type111= "郎中";
                break;
            case 39://双倍
                type111= "双倍";
                break;
            case 40://宝图
                type111= "宝图";
                break;
            case 41://福来酒店
                type111= "酒店";
                break;

            case 42://大闹
            case 43:
                type111= "任务";
                break;
            case 44://灵兽
                type111= "灵兽";
                break;
            case 47://孟婆
                type111= "转生";
                break;
            case 48://黄大仙
                type111= "种族";
                break;
            case 53://灵兽村使者
                type111= "修罗";
                break;
            case 54://铁匠
                type111= "打造";
                break;
            case 61://战斗
                type111= "战斗";
                break;
            case 62://退场
            case 103://退场
            case 104://退场
//            case 62://退场
//            case 62://退场

                type111= "退场";
                break;
            case 63://帮派
            case 64://帮派
                type111= "帮派";
                break;
            case 66://崔无眠
                type111= "仙器";
                break;
            case 69://王麻子
                type111= "配饰";
                break;
            case 71://石破烂
                type111= "回收";
                break;
            case 85://宝石
                type111= "宝石";
                break;
            case 88://符文商店
                type111= "符文";
                break;
            case 120://雁塔积分
                type111= "兑换";
                break;
            case 123://寻芳
                type111= "寻芳";
                break;
            case 126://水陆
                type111= "水陆";
                break;
            case 128://皇宫胡敬德
                type111= "挑战";
                break;
            case 129://魔王窟二弟子
                type111= "灵修";
                break;
            case 130://管家
                type111= "管家";
                break;
            case 131://杜老板
                type111= "孩子";
                break;
            case 133://神兽大将
                type111= "神兽";
                break;
            case 181://测试商店
                type111= "测试";
                break;
            case 182://测试商店
                type111= "商店";
                break;
            case 605://日月灯芯
                type111= "大闹";
                break;
            case 5151://庙祝
                type111= "祈福";
                break;
            case 888://月老
                type111= "结婚";
                break;
            case 1106://校场督军
                type111= "比赛";
                break;
            case 1234://五一活动
                type111= "节日";
                break;
            case 2027://全民
                type111= "竞技";
                break;
            case 8901://全民
                type111= "大乱斗";
                break;

            default:
                type111= null;
        }
        return type111;
    }
    public static Map<String, Integer> gjMap = new HashMap<>();

    static {
        gjMap.put("位列仙班", 6);
        gjMap.put("五方揭谛", 5);
        gjMap.put("六丁六甲", 4);
        gjMap.put("护法珈蓝", 3);
        gjMap.put("三十六天将", 2);
        gjMap.put("日值功曹", 1);
        gjMap.put("城隍土地", 0);
    }

    public static String setZhiWeiRank(int value) {
        String rank = "";
        if (value >= 8000) {
            rank = "位列仙班";
        } else if (value >= 5000) {
            rank = "五方揭谛";
        } else if (value >= 3000) {
            rank = "六丁六甲";
        } else if (value >= 1000) {
            rank = "护法珈蓝";
        } else if (value >= 500) {
            rank = "三十六天将";
        } else if (value >= 100) {
            rank = "日值功曹";
        } else if (value >= 0) {
            rank = "城隍土地";
        }
        return rank;
    }
    public static void yhbztexiao(String type) {
        String url = GetTcpPath.getMouseTcp(type);
        Sprite mouse = SpriteFactory.Prepare(url);
        if(mouse==null) {
            mouse = SpriteFactory.VloadSprite(GetTcpPath.getMouseTcp(type),null);
        }
        if (mouse != null) {
            for(int i=0;i<ZhuFrame.getZhuJpanel().yhbztx.length;i++){
                if(ZhuFrame.getZhuJpanel().yhbztx[i]==null){
                    ZhuFrame.getZhuJpanel().yhx[i] = (random.nextInt(21) - 10) * 30;
                    ZhuFrame.getZhuJpanel().yhtime[i] = 0;
                    ZhuFrame.getZhuJpanel().yhbztx[i]=mouse;
                    break;
                }
            }
        }
    }
}
