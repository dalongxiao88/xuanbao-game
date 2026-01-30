package org.come.Jpanel;

import java.util.Comparator;
import java.awt.Image;
import org.come.until.ScrenceUntil;
import org.come.Frame.WorldTestsmallmapJframe;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.List;
import org.come.bean.RoleShow;
import org.come.thread.TimeControlRunnable;
import come.tool.map.XLPath;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.FormsManagement;
import org.come.npc.TP;
import org.come.model.Door;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.until.Util;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.bean.Mapmodelworld;
import javax.swing.ImageIcon;
import com.tool.btn.MouseStyleBtn;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import org.come.entity.Goodstable;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class WorldMapJpanel extends JPanel
{
    private static String mapZb;
    private static JTextField findName1;
    private static RoleCaoZuoBtn sureGive;
    private static FormsOnOffBtn ssnpc;
    private static FormsOnOffBtn czdl;
    private Goodstable good;
    private static String[] Fvalue;
    private static JTextArea testMes;
    public static String npcfeiji;
    public static String npcfeiji1;
    private static DefaultTableModel tableModel;
    private static RoleCaoZuoBtn maze1;
    private static RoleCaoZuoBtn maze2;
    private static RoleCaoZuoBtn maze3;
    private static RoleCaoZuoBtn maze4;
    private static RoleCaoZuoBtn diyumog1;
    private static RoleCaoZuoBtn diyumog2;
    private static RoleCaoZuoBtn diyumog3;
    private static RoleCaoZuoBtn diyumog4;
    private static RoleCaoZuoBtn dayanta1;
    private static RoleCaoZuoBtn dayanta2;
    private static RoleCaoZuoBtn dayanta3;
    private static RoleCaoZuoBtn dayanta4;
    private static RoleCaoZuoBtn dayanta5;
    private static RoleCaoZuoBtn dayanta6;
    private static RoleCaoZuoBtn dayanta7;
    private static RoleCaoZuoBtn fengcao1;
    private static RoleCaoZuoBtn fengcao2;
    private static RoleCaoZuoBtn fengcao3;
    private static RoleCaoZuoBtn fengcao4;
    private static RoleCaoZuoBtn fengcao5;
    private static RoleCaoZuoBtn fengcao6;
    private static RoleCaoZuoBtn fengcao7;
    private static RoleCaoZuoBtn longku1;
    private static RoleCaoZuoBtn longku2;
    private static RoleCaoZuoBtn longku3;
    private static RoleCaoZuoBtn longku4;
    private static RoleCaoZuoBtn longku5;
    private static RoleCaoZuoBtn longku6;
    private static RoleCaoZuoBtn longku7;
    private static MouseStyleBtn mazekz;
    private static MouseStyleBtn diyumogkz;
    private static MouseStyleBtn dayantakz;
    private static MouseStyleBtn fengcaokz;
    private static MouseStyleBtn longkukz;
    private ImageIcon icon;
    public static Mapmodelworld mapmodel;
    public static int ditubianma;
    
    public static void showIsTeamBtnSx(boolean type, int num) {
        if (num == 0) {
            WorldMapJpanel.maze1.setVisible(type);
            WorldMapJpanel.maze2.setVisible(type);
            WorldMapJpanel.maze3.setVisible(type);
            WorldMapJpanel.maze4.setVisible(type);
        }
        else if (WorldMapJpanel.maze1.isVisible()) {
            WorldMapJpanel.maze1.setVisible(false);
            WorldMapJpanel.maze2.setVisible(false);
            WorldMapJpanel.maze3.setVisible(false);
            WorldMapJpanel.maze4.setVisible(false);
        }
        else {
            WorldMapJpanel.maze1.setVisible(true);
            WorldMapJpanel.maze2.setVisible(true);
            WorldMapJpanel.maze3.setVisible(true);
            WorldMapJpanel.maze4.setVisible(true);
        }
    }
    
    public static void showduyu(boolean type, int num) {
        if (num == 0) {
            WorldMapJpanel.diyumog1.setVisible(type);
            WorldMapJpanel.diyumog2.setVisible(type);
            WorldMapJpanel.diyumog3.setVisible(type);
            WorldMapJpanel.diyumog4.setVisible(type);
        }
        else if (WorldMapJpanel.diyumog1.isVisible()) {
            WorldMapJpanel.diyumog1.setVisible(false);
            WorldMapJpanel.diyumog2.setVisible(false);
            WorldMapJpanel.diyumog3.setVisible(false);
            WorldMapJpanel.diyumog4.setVisible(false);
        }
        else {
            WorldMapJpanel.diyumog1.setVisible(true);
            WorldMapJpanel.diyumog2.setVisible(true);
            WorldMapJpanel.diyumog3.setVisible(true);
            WorldMapJpanel.diyumog4.setVisible(true);
        }
    }
    
    public static void showdayanta(boolean type, int num) {
        if (num == 0) {
            WorldMapJpanel.dayanta1.setVisible(type);
            WorldMapJpanel.dayanta2.setVisible(type);
            WorldMapJpanel.dayanta3.setVisible(type);
            WorldMapJpanel.dayanta4.setVisible(type);
            WorldMapJpanel.dayanta5.setVisible(type);
            WorldMapJpanel.dayanta6.setVisible(type);
            WorldMapJpanel.dayanta7.setVisible(type);
        }
        else if (WorldMapJpanel.dayanta1.isVisible()) {
            WorldMapJpanel.dayanta1.setVisible(false);
            WorldMapJpanel.dayanta2.setVisible(false);
            WorldMapJpanel.dayanta3.setVisible(false);
            WorldMapJpanel.dayanta4.setVisible(false);
            WorldMapJpanel.dayanta5.setVisible(false);
            WorldMapJpanel.dayanta6.setVisible(false);
            WorldMapJpanel.dayanta7.setVisible(false);
        }
        else {
            WorldMapJpanel.dayanta1.setVisible(true);
            WorldMapJpanel.dayanta2.setVisible(true);
            WorldMapJpanel.dayanta3.setVisible(true);
            WorldMapJpanel.dayanta4.setVisible(true);
            WorldMapJpanel.dayanta5.setVisible(true);
            WorldMapJpanel.dayanta6.setVisible(true);
            WorldMapJpanel.dayanta7.setVisible(true);
        }
    }
    
    public static void showfengcao(boolean type, int num) {
        if (num == 0) {
            WorldMapJpanel.fengcao1.setVisible(type);
            WorldMapJpanel.fengcao2.setVisible(type);
            WorldMapJpanel.fengcao3.setVisible(type);
            WorldMapJpanel.fengcao4.setVisible(type);
            WorldMapJpanel.fengcao5.setVisible(type);
            WorldMapJpanel.fengcao6.setVisible(type);
            WorldMapJpanel.fengcao7.setVisible(type);
        }
        else if (WorldMapJpanel.fengcao1.isVisible()) {
            WorldMapJpanel.fengcao1.setVisible(false);
            WorldMapJpanel.fengcao2.setVisible(false);
            WorldMapJpanel.fengcao3.setVisible(false);
            WorldMapJpanel.fengcao4.setVisible(false);
            WorldMapJpanel.fengcao5.setVisible(false);
            WorldMapJpanel.fengcao6.setVisible(false);
            WorldMapJpanel.fengcao7.setVisible(false);
        }
        else {
            WorldMapJpanel.fengcao1.setVisible(true);
            WorldMapJpanel.fengcao2.setVisible(true);
            WorldMapJpanel.fengcao3.setVisible(true);
            WorldMapJpanel.fengcao4.setVisible(true);
            WorldMapJpanel.fengcao5.setVisible(true);
            WorldMapJpanel.fengcao6.setVisible(true);
            WorldMapJpanel.fengcao7.setVisible(true);
        }
    }
    
    public static void showlongku(boolean type, int num) {
        if (num == 0) {
            WorldMapJpanel.longku1.setVisible(type);
            WorldMapJpanel.longku2.setVisible(type);
            WorldMapJpanel.longku3.setVisible(type);
            WorldMapJpanel.longku4.setVisible(type);
            WorldMapJpanel.longku5.setVisible(type);
            WorldMapJpanel.longku6.setVisible(type);
            WorldMapJpanel.longku7.setVisible(type);
        }
        else if (WorldMapJpanel.longku1.isVisible()) {
            WorldMapJpanel.longku1.setVisible(false);
            WorldMapJpanel.longku2.setVisible(false);
            WorldMapJpanel.longku3.setVisible(false);
            WorldMapJpanel.longku4.setVisible(false);
            WorldMapJpanel.longku5.setVisible(false);
            WorldMapJpanel.longku6.setVisible(false);
            WorldMapJpanel.longku7.setVisible(false);
        }
        else {
            WorldMapJpanel.longku1.setVisible(true);
            WorldMapJpanel.longku2.setVisible(true);
            WorldMapJpanel.longku3.setVisible(true);
            WorldMapJpanel.longku4.setVisible(true);
            WorldMapJpanel.longku5.setVisible(true);
            WorldMapJpanel.longku6.setVisible(true);
            WorldMapJpanel.longku7.setVisible(true);
        }
    }
    
    public WorldMapJpanel() {
        this.setPreferredSize(new Dimension(953, 501));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button1/worldtb.png", 1, 1102);
            offBtn.setBounds(845, 38, 20, 20);
            this.add(offBtn);
            (WorldMapJpanel.mazekz = new MouseStyleBtn("img/world-map/1268.png", 1, "海底迷宫", "")).setBounds(605, 245, 73, 24);
            this.add(WorldMapJpanel.mazekz);
            (WorldMapJpanel.maze1 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "海底迷宫1", 1268, "", "", "")).setBounds(602, 273, 68, 17);
            WorldMapJpanel.maze1.setVisible(false);
            this.add(WorldMapJpanel.maze1);
            (WorldMapJpanel.maze2 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "海底迷宫2", 1269, "", "", "")).setBounds(602, 290, 68, 17);
            WorldMapJpanel.maze2.setVisible(false);
            this.add(WorldMapJpanel.maze2);
            (WorldMapJpanel.maze3 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "海底迷宫3", 1270, "", "", "")).setBounds(602, 307, 68, 17);
            WorldMapJpanel.maze3.setVisible(false);
            this.add(WorldMapJpanel.maze3);
            (WorldMapJpanel.maze4 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "海底迷宫4", 1271, "", "", "")).setBounds(602, 324, 68, 17);
            WorldMapJpanel.maze4.setVisible(false);
            this.add(WorldMapJpanel.maze4);
            (WorldMapJpanel.diyumogkz = new MouseStyleBtn("img/world-map/1275.png", 1, "地狱迷宫", "")).setBounds(620, 405, 73, 24);
            this.add(WorldMapJpanel.diyumogkz);
            (WorldMapJpanel.diyumog1 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "地狱迷宫1", 1275, "", "", "")).setBounds(610, 383, 68, 17);
            WorldMapJpanel.diyumog1.setVisible(false);
            this.add(WorldMapJpanel.diyumog1);
            (WorldMapJpanel.diyumog2 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "地狱迷宫2", 1273, "", "", "")).setBounds(610, 366, 68, 17);
            WorldMapJpanel.diyumog2.setVisible(false);
            this.add(WorldMapJpanel.diyumog2);
            (WorldMapJpanel.diyumog3 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "地狱迷宫3", 1276, "", "", "")).setBounds(610, 349, 68, 17);
            WorldMapJpanel.diyumog3.setVisible(false);
            this.add(WorldMapJpanel.diyumog3);
            (WorldMapJpanel.diyumog4 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "地狱迷宫4", 1274, "", "", "")).setBounds(610, 332, 68, 17);
            WorldMapJpanel.diyumog4.setVisible(false);
            this.add(WorldMapJpanel.diyumog4);
            (WorldMapJpanel.dayantakz = new MouseStyleBtn("img/world-map/1221.png", 1, "大雁塔", "")).setBounds(455, 270, 60, 24);
            this.add(WorldMapJpanel.dayantakz);
            (WorldMapJpanel.dayanta1 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔一层", 1221, "", "", "")).setBounds(445, 243, 68, 24);
            WorldMapJpanel.dayanta1.setVisible(false);
            this.add(WorldMapJpanel.dayanta1);
            (WorldMapJpanel.dayanta2 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔二层", 1222, "", "", "")).setBounds(445, 226, 68, 24);
            WorldMapJpanel.dayanta2.setVisible(false);
            this.add(WorldMapJpanel.dayanta2);
            (WorldMapJpanel.dayanta3 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔三层", 1223, "", "", "")).setBounds(445, 209, 68, 24);
            WorldMapJpanel.dayanta3.setVisible(false);
            this.add(WorldMapJpanel.dayanta3);
            (WorldMapJpanel.dayanta4 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔四层", 1224, "", "", "")).setBounds(445, 192, 68, 24);
            WorldMapJpanel.dayanta4.setVisible(false);
            this.add(WorldMapJpanel.dayanta4);
            (WorldMapJpanel.dayanta5 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔五层", 1225, "", "", "")).setBounds(445, 175, 68, 24);
            WorldMapJpanel.dayanta5.setVisible(false);
            this.add(WorldMapJpanel.dayanta5);
            (WorldMapJpanel.dayanta6 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔六层", 1226, "", "", "")).setBounds(445, 158, 68, 24);
            WorldMapJpanel.dayanta6.setVisible(false);
            this.add(WorldMapJpanel.dayanta6);
            (WorldMapJpanel.dayanta7 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "大雁塔七层", 3311, "", "", "")).setBounds(445, 141, 68, 24);
            WorldMapJpanel.dayanta7.setVisible(false);
            this.add(WorldMapJpanel.dayanta7);
            (WorldMapJpanel.fengcaokz = new MouseStyleBtn("img/world-map/1289.png", 1, "凤巢", "")).setBounds(372, 70, 40, 24);
            this.add(WorldMapJpanel.fengcaokz);
            (WorldMapJpanel.fengcao1 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢一层", 1289, "", "", "")).setBounds(352, 87, 68, 24);
            WorldMapJpanel.fengcao1.setVisible(false);
            this.add(WorldMapJpanel.fengcao1);
            (WorldMapJpanel.fengcao2 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢二层", 1290, "", "", "")).setBounds(352, 104, 68, 24);
            WorldMapJpanel.fengcao2.setVisible(false);
            this.add(WorldMapJpanel.fengcao2);
            (WorldMapJpanel.fengcao3 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢三层", 1291, "", "", "")).setBounds(352, 121, 68, 24);
            WorldMapJpanel.fengcao3.setVisible(false);
            this.add(WorldMapJpanel.fengcao3);
            (WorldMapJpanel.fengcao4 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢四层", 1292, "", "", "")).setBounds(352, 138, 68, 24);
            WorldMapJpanel.fengcao4.setVisible(false);
            this.add(WorldMapJpanel.fengcao4);
            (WorldMapJpanel.fengcao5 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢五层", 1293, "", "", "")).setBounds(352, 155, 68, 24);
            WorldMapJpanel.fengcao5.setVisible(false);
            this.add(WorldMapJpanel.fengcao5);
            (WorldMapJpanel.fengcao6 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢六层", 1294, "", "", "")).setBounds(352, 172, 68, 24);
            WorldMapJpanel.fengcao6.setVisible(false);
            this.add(WorldMapJpanel.fengcao6);
            (WorldMapJpanel.fengcao7 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "凤巢七层", 1295, "", "", "")).setBounds(352, 189, 68, 24);
            WorldMapJpanel.fengcao7.setVisible(false);
            this.add(WorldMapJpanel.fengcao7);
            (WorldMapJpanel.longkukz = new MouseStyleBtn("img/world-map/1282.png", 1, "龙窟", "")).setBounds(472, 65, 40, 24);
            this.add(WorldMapJpanel.longkukz);
            (WorldMapJpanel.longku1 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟一层", 1282, "", "", "")).setBounds(452, 87, 68, 24);
            WorldMapJpanel.longku1.setVisible(false);
            this.add(WorldMapJpanel.longku1);
            (WorldMapJpanel.longku2 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟二层", 1283, "", "", "")).setBounds(452, 104, 68, 24);
            WorldMapJpanel.longku2.setVisible(false);
            this.add(WorldMapJpanel.longku2);
            (WorldMapJpanel.longku3 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟三层", 1284, "", "", "")).setBounds(452, 121, 68, 24);
            WorldMapJpanel.longku3.setVisible(false);
            this.add(WorldMapJpanel.longku3);
            (WorldMapJpanel.longku4 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟四层", 1285, "", "", "")).setBounds(452, 138, 68, 24);
            WorldMapJpanel.longku4.setVisible(false);
            this.add(WorldMapJpanel.longku4);
            (WorldMapJpanel.longku5 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟五层", 1286, "", "", "")).setBounds(452, 155, 68, 24);
            WorldMapJpanel.longku5.setVisible(false);
            this.add(WorldMapJpanel.longku5);
            (WorldMapJpanel.longku6 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟六层", 1287, "", "", "")).setBounds(452, 172, 68, 24);
            WorldMapJpanel.longku6.setVisible(false);
            this.add(WorldMapJpanel.longku6);
            (WorldMapJpanel.longku7 = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT, "龙窟七层", 1288, "", "", "")).setBounds(452, 189, 68, 24);
            WorldMapJpanel.longku7.setVisible(false);
            this.add(WorldMapJpanel.longku7);
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button1/worldtb.png", 1, 1102);
            offBtn.setBounds(845, 38, 20, 20);
            this.add(offBtn);
            (WorldMapJpanel.mazekz = new MouseStyleBtn("img/world-map/1268.png", 1, "海底迷宫", "")).setBounds(605, 245, 73, 24);
            this.add(WorldMapJpanel.mazekz);
            (WorldMapJpanel.maze1 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "海底迷宫1", 1268, "", "")).setBounds(602, 273, 68, 17);
            WorldMapJpanel.maze1.setVisible(false);
            this.add(WorldMapJpanel.maze1);
            (WorldMapJpanel.maze2 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "海底迷宫2", 1269, "", "")).setBounds(602, 290, 68, 17);
            WorldMapJpanel.maze2.setVisible(false);
            this.add(WorldMapJpanel.maze2);
            (WorldMapJpanel.maze3 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "海底迷宫3", 1270, "", "")).setBounds(602, 307, 68, 17);
            WorldMapJpanel.maze3.setVisible(false);
            this.add(WorldMapJpanel.maze3);
            (WorldMapJpanel.maze4 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "海底迷宫4", 1271, "", "")).setBounds(602, 324, 68, 17);
            WorldMapJpanel.maze4.setVisible(false);
            this.add(WorldMapJpanel.maze4);
            (WorldMapJpanel.diyumogkz = new MouseStyleBtn("img/world-map/1275.png", 1, "地狱迷宫", "")).setBounds(620, 405, 73, 24);
            this.add(WorldMapJpanel.diyumogkz);
            (WorldMapJpanel.diyumog1 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "地狱迷宫1", 1275, "", "")).setBounds(610, 383, 68, 17);
            WorldMapJpanel.diyumog1.setVisible(false);
            this.add(WorldMapJpanel.diyumog1);
            (WorldMapJpanel.diyumog2 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "地狱迷宫2", 1273, "", "")).setBounds(610, 366, 68, 17);
            WorldMapJpanel.diyumog2.setVisible(false);
            this.add(WorldMapJpanel.diyumog2);
            (WorldMapJpanel.diyumog3 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "地狱迷宫3", 1276, "", "")).setBounds(610, 349, 68, 17);
            WorldMapJpanel.diyumog3.setVisible(false);
            this.add(WorldMapJpanel.diyumog3);
            (WorldMapJpanel.diyumog4 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "地狱迷宫4", 1274, "", "")).setBounds(610, 332, 68, 17);
            WorldMapJpanel.diyumog4.setVisible(false);
            this.add(WorldMapJpanel.diyumog4);
            (WorldMapJpanel.dayantakz = new MouseStyleBtn("img/world-map/1221.png", 1, "大雁塔", "")).setBounds(455, 270, 60, 24);
            this.add(WorldMapJpanel.dayantakz);
            (WorldMapJpanel.dayanta1 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔一层", 1221, "", "")).setBounds(445, 243, 68, 24);
            WorldMapJpanel.dayanta1.setVisible(false);
            this.add(WorldMapJpanel.dayanta1);
            (WorldMapJpanel.dayanta2 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔二层", 1222, "", "")).setBounds(445, 226, 68, 24);
            WorldMapJpanel.dayanta2.setVisible(false);
            this.add(WorldMapJpanel.dayanta2);
            (WorldMapJpanel.dayanta3 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔三层", 1223, "", "")).setBounds(445, 209, 68, 24);
            WorldMapJpanel.dayanta3.setVisible(false);
            this.add(WorldMapJpanel.dayanta3);
            (WorldMapJpanel.dayanta4 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔四层", 1224, "", "")).setBounds(445, 192, 68, 24);
            WorldMapJpanel.dayanta4.setVisible(false);
            this.add(WorldMapJpanel.dayanta4);
            (WorldMapJpanel.dayanta5 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔五层", 1225, "", "")).setBounds(445, 175, 68, 24);
            WorldMapJpanel.dayanta5.setVisible(false);
            this.add(WorldMapJpanel.dayanta5);
            (WorldMapJpanel.dayanta6 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔六层", 1226, "", "")).setBounds(445, 158, 68, 24);
            WorldMapJpanel.dayanta6.setVisible(false);
            this.add(WorldMapJpanel.dayanta6);
            (WorldMapJpanel.dayanta7 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "大雁塔七层", 3311, "", "")).setBounds(445, 141, 68, 24);
            WorldMapJpanel.dayanta7.setVisible(false);
            this.add(WorldMapJpanel.dayanta7);
            (WorldMapJpanel.fengcaokz = new MouseStyleBtn("img/world-map/1289.png", 1, "凤巢", "")).setBounds(372, 70, 40, 24);
            this.add(WorldMapJpanel.fengcaokz);
            (WorldMapJpanel.fengcao1 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢一层", 1289, "", "")).setBounds(352, 87, 68, 24);
            WorldMapJpanel.fengcao1.setVisible(false);
            this.add(WorldMapJpanel.fengcao1);
            (WorldMapJpanel.fengcao2 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢二层", 1290, "", "")).setBounds(352, 104, 68, 24);
            WorldMapJpanel.fengcao2.setVisible(false);
            this.add(WorldMapJpanel.fengcao2);
            (WorldMapJpanel.fengcao3 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢三层", 1291, "", "")).setBounds(352, 121, 68, 24);
            WorldMapJpanel.fengcao3.setVisible(false);
            this.add(WorldMapJpanel.fengcao3);
            (WorldMapJpanel.fengcao4 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢四层", 1292, "", "")).setBounds(352, 138, 68, 24);
            WorldMapJpanel.fengcao4.setVisible(false);
            this.add(WorldMapJpanel.fengcao4);
            (WorldMapJpanel.fengcao5 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢五层", 1293, "", "")).setBounds(352, 155, 68, 24);
            WorldMapJpanel.fengcao5.setVisible(false);
            this.add(WorldMapJpanel.fengcao5);
            (WorldMapJpanel.fengcao6 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢六层", 1294, "", "")).setBounds(352, 172, 68, 24);
            WorldMapJpanel.fengcao6.setVisible(false);
            this.add(WorldMapJpanel.fengcao6);
            (WorldMapJpanel.fengcao7 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "凤巢七层", 1295, "", "")).setBounds(352, 189, 68, 24);
            WorldMapJpanel.fengcao7.setVisible(false);
            this.add(WorldMapJpanel.fengcao7);
            (WorldMapJpanel.longkukz = new MouseStyleBtn("img/world-map/1282.png", 1, "龙窟", "")).setBounds(472, 65, 40, 24);
            this.add(WorldMapJpanel.longkukz);
            (WorldMapJpanel.longku1 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟一层", 1282, "", "")).setBounds(452, 87, 68, 24);
            WorldMapJpanel.longku1.setVisible(false);
            this.add(WorldMapJpanel.longku1);
            (WorldMapJpanel.longku2 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟二层", 1283, "", "")).setBounds(452, 104, 68, 24);
            WorldMapJpanel.longku2.setVisible(false);
            this.add(WorldMapJpanel.longku2);
            (WorldMapJpanel.longku3 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟三层", 1284, "", "")).setBounds(452, 121, 68, 24);
            WorldMapJpanel.longku3.setVisible(false);
            this.add(WorldMapJpanel.longku3);
            (WorldMapJpanel.longku4 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟四层", 1285, "", "")).setBounds(452, 138, 68, 24);
            WorldMapJpanel.longku4.setVisible(false);
            this.add(WorldMapJpanel.longku4);
            (WorldMapJpanel.longku5 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟五层", 1286, "", "")).setBounds(452, 155, 68, 24);
            WorldMapJpanel.longku5.setVisible(false);
            this.add(WorldMapJpanel.longku5);
            (WorldMapJpanel.longku6 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟六层", 1287, "", "")).setBounds(452, 172, 68, 24);
            WorldMapJpanel.longku6.setVisible(false);
            this.add(WorldMapJpanel.longku6);
            (WorldMapJpanel.longku7 = new RoleCaoZuoBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT, "龙窟七层", 1288, "", "")).setBounds(452, 189, 68, 24);
            WorldMapJpanel.longku7.setVisible(false);
            this.add(WorldMapJpanel.longku7);
        }
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1207.png", 1, "    ", 1207, UIUtils.COLOR_BTNPUTONG)).setBounds(477, 300, 95, 35);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1193.png", 1, "    ", 1193, UIUtils.COLOR_BTNPUTONG)).setBounds(577, 340, 60, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1208.png", 1, "    ", 1208, UIUtils.COLOR_BTNPUTONG)).setBounds(657, 290, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1210.png", 1, "    ", 1210, UIUtils.COLOR_BTNPUTONG)).setBounds(432, 375, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1211.png", 1, "    ", 1211, UIUtils.COLOR_BTNPUTONG)).setBounds(582, 450, 60, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1213.png", 1, "    ", 1213, UIUtils.COLOR_BTNPUTONG)).setBounds(657, 322, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1227.png", 1, "    ", 1227, UIUtils.COLOR_BTNPUTONG)).setBounds(547, 420, 60, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1228.png", 1, "    ", 1228, UIUtils.COLOR_BTNPUTONG)).setBounds(152, 229, 80, 26);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1229.png", 1, "    ", 1229, UIUtils.COLOR_BTNPUTONG)).setBounds(132, 275, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1230.png", 1, "    ", 1230, UIUtils.COLOR_BTNPUTONG)).setBounds(182, 110, 72, 26);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1231.png", 1, "    ", 1231, UIUtils.COLOR_BTNPUTONG)).setBounds(227, 89, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1232.png", 1, "    ", 1232, UIUtils.COLOR_BTNPUTONG)).setBounds(147, 80, 50, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1236.png", 1, "    ", 1236, UIUtils.COLOR_BTNPUTONG)).setBounds(434, 423, 95, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1240.png", 1, "    ", 1240, UIUtils.COLOR_BTNPUTONG)).setBounds(147, 45, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1241.png", 1, "    ", 1241, UIUtils.COLOR_BTNPUTONG)).setBounds(217, 35, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1242.png", 1, "    ", 1242, UIUtils.COLOR_BTNPUTONG)).setBounds(362, 360, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1244.png", 1, "    ", 1244, UIUtils.COLOR_BTNPUTONG)).setBounds(557, 220, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1246.png", 1, "    ", 1246, UIUtils.COLOR_BTNPUTONG)).setBounds(122, 172, 78, 19);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1248.png", 1, "    ", 1248, UIUtils.COLOR_BTNPUTONG)).setBounds(623, 100, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1250.png", 1, "    ", 1250, UIUtils.COLOR_BTNPUTONG)).setBounds(579, 128, 95, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3369.png", 1, "    ", 3369, UIUtils.COLOR_BTNPUTONG)).setBounds(624, 160, 59, 20);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3155.png", 1, "    ", 3155, UIUtils.COLOR_BTNPUTONG)).setBounds(599, 183, 59, 20);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1251.png", 1, "    ", 1251, UIUtils.COLOR_BTNPUTONG)).setBounds(277, 357, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1252.png", 1, "    ", 1252, UIUtils.COLOR_BTNPUTONG)).setBounds(112, 368, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1254.png", 1, "    ", 1254, UIUtils.COLOR_BTNPUTONG)).setBounds(500, 245, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1259.png", 1, "    ", 1259, UIUtils.COLOR_BTNPUTONG)).setBounds(267, 388, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1263.png", 1, "    ", 1263, UIUtils.COLOR_BTNPUTONG)).setBounds(392, 302, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1267.png", 1, "    ", 1267, UIUtils.COLOR_BTNPUTONG)).setBounds(397, 100, 107, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1272.png", 1, "    ", 1272, UIUtils.COLOR_BTNPUTONG)).setBounds(367, 333, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1278.png", 1, "    ", 1278, UIUtils.COLOR_BTNPUTONG)).setBounds(311, 298, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1279.png", 1, "    ", 1279, UIUtils.COLOR_BTNPUTONG)).setBounds(300, 325, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1280.png", 1, "    ", 1280, UIUtils.COLOR_BTNPUTONG)).setBounds(335, 267, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1296.png", 1, "    ", 1296, UIUtils.COLOR_BTNPUTONG)).setBounds(397, 50, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1298.png", 1, "    ", 1298, UIUtils.COLOR_BTNPUTONG)).setBounds(537, 275, 40, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3012.png", 1, "    ", 3012, UIUtils.COLOR_BTNPUTONG)).setBounds(652, 453, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3180.png", 1, "    ", 3180, UIUtils.COLOR_BTNPUTONG)).setBounds(262, 252, 95, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3205.png", 1, "    ", 3205, UIUtils.COLOR_BTNPUTONG)).setBounds(427, 33, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3210.png", 1, "    ", 3210, UIUtils.COLOR_BTNPUTONG)).setBounds(217, 327, 73, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3307.png", 1, "    ", 3307, UIUtils.COLOR_BTNPUTONG)).setBounds(232, 282, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/3308.png", 1, "    ", 3308, UIUtils.COLOR_BTNPUTONG)).setBounds(212, 352, 62, 24);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1802.png", 1, "    ", 1802, UIUtils.COLOR_BTNPUTONG)).setBounds(772, 220, 57, 31);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1803.png", 1, "    ", 1803, UIUtils.COLOR_BTNPUTONG)).setBounds(790, 305, 42, 19);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/4006.png", 1, "    ", 4006, UIUtils.COLOR_BTNPUTONG)).setBounds(752, 335, 84, 27);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1804.png", 1, "    ", 1804, UIUtils.COLOR_BTNPUTONG)).setBounds(822, 180, 41, 19);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1805.png", 1, "    ", 1806, UIUtils.COLOR_BTNPUTONG)).setBounds(822, 260, 42, 20);
        this.add(WorldMapJpanel.sureGive);
        (WorldMapJpanel.ssnpc = new FormsOnOffBtn("inkImg/background1/worldXZNPC.png", 1, UIUtils.COLOR_BTNTEXT, "", 1101)).setBounds(255, 425, 94, 40);
        this.add(WorldMapJpanel.ssnpc);
        (WorldMapJpanel.czdl = new FormsOnOffBtn("inkImg/background1/worldCDL.png", 1, UIUtils.COLOR_BTNTEXT, "", 600)).setBounds(132, 425, 94, 40);
        this.add(WorldMapJpanel.czdl);
    }
    
    public static void iWantToFlyGo(int index) {
        String flag = Util.mapmodel.getGamemap().getMapflag();
        if (flag == null || !flag.equals("1") || ImageMixDeal.userimg.getTeams() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你不是队长不可飞行！");
            return;
        }
        boolean b = false;
        String[] mpList;
        for (String str : mpList = WorldMapJpanel.mapZb.split("\\|")) {
            String[] st = str.split(",");
            if (st[0].equals(index + "")) {
                WorldMapJpanel.npcfeiji = str;
                b = true;
            }
        }
        if (b) {
            String[] dt = WorldMapJpanel.npcfeiji.split(",");
            loadMap(Integer.parseInt(dt[0]), Integer.parseInt(dt[2]), Integer.parseInt(dt[3]));
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("暂未开放此地图！");
        }
    }
    
    public static void iWantToFly() {
        if (WorldMapJpanel.npcfeiji == null || WorldMapJpanel.npcfeiji.length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先搜索NPC!!!");
        }
        else {
            String kkf = "传送=" + WorldMapJpanel.npcfeiji + ", 可用次数=10";
            String kkf2 = "传送=" + WorldMapJpanel.npcfeiji;
            String kkf3 = WorldMapJpanel.npcfeiji;
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
        WorldMapJpanel.npcfeiji = "";
        FormsManagement.HideForm(1102);
    }
    
    public static void iWantToFly(String feizb) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以移动！");
            return;
        }
        boolean falg = GoodsListFromServerUntil.istype(2125, 1);
        if (falg) {
            WorldMapJpanel.npcfeiji1 = feizb;
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.SFCS, null, "#R注意：#Y是否消耗一次【任我行】次数进行传送？#G点击确定传送，点击取消自动寻路！");
            return;
        }
        ZhuFrame.getZhuJpanel().addPrompt2("#W道具#R任我行#W不足无法传送！开始进行自动寻路");
        String[] zd = feizb.split(",");
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), Integer.parseInt(zd[2]), Integer.parseInt(zd[3]), Integer.parseInt(zd[0]));
        if (list == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
            return;
        }
        TimeControlRunnable.addScript(list);
    }
    
    public static void feizb(List<String> selectNpc) {
        String feiji = (String)selectNpc.get(0);
        String str11 = feiji.substring(0, feiji.indexOf(","));
        String str12 = feiji.substring(str11.length() + 1, feiji.length());
        String str13 = str12.substring(0, str12.indexOf(","));
        String str14 = str12.substring(str13.length() + 1, str12.length());
        feiji = feiji.replace("(", ",");
        feiji = feiji.replace(")", "");
        String str15 = feiji.substring(0, feiji.indexOf(","));
        String str16 = feiji.substring(str15.length() + 1, feiji.length());
        String[] str17 = str16.split(",");
        str17[2] = Long.parseLong(str17[2]) * 20L + "";
        str17[3] = Long.parseLong(str17[3]) * 20L + "";
        WorldMapJpanel.npcfeiji = str17[0] + "," + str17[1] + "," + str17[2] + "," + str17[3];
    }
    
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/worldmap.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 953, 501, this);
            (WorldMapJpanel.sureGive = new RoleCaoZuoBtn("img/world-map/1207.png", 1, "    ", 1207, UIUtils.COLOR_BTNPUTONG)).setBounds(477, 300, 95, 35);
            Long mapid = ImageMixDeal.userimg.getRoleShow().getMapid();
            Sprite mark = SpriteFactory.Prepare("resource/mouse/word.tcp");
            if (mark != null) {
                mark.updateToTime(ImageMixDeal.userimg.getTime(), 8);
            }
            if (mapid.equals(Long.valueOf(1207L))) {
                if (mark != null) {
                    mark.draw(g, 517, 310);
                }
            }
            else if ((long)mapid >= 1268L && (long)mapid <= 1271L) {
                if (mark != null) {
                    mark.draw(g, 645, 255);
                }
            }
            else if ((long)mapid >= 1273L && (long)mapid <= 1276L) {
                if (mark != null) {
                    mark.draw(g, 660, 415);
                }
            }
            else if (((long)mapid >= 1221L && (long)mapid <= 1226L) || mapid.equals(Integer.valueOf(3311))) {
                if (mark != null) {
                    mark.draw(g, 495, 320);
                }
            }
            else if ((long)mapid >= 1289L && (long)mapid <= 1295L) {
                if (mark != null) {
                    mark.draw(g, 412, 80);
                }
            }
            else if ((long)mapid >= 1282L && (long)mapid <= 1288L) {
                if (mark != null) {
                    mark.draw(g, 512, 75);
                }
            }
            else if (mapid.equals(Long.valueOf(1207L))) {
                if (mark != null) {
                    mark.draw(g, 517, 310);
                }
            }
            else if (mapid.equals(Long.valueOf(1193L))) {
                if (mark != null) {
                    mark.draw(g, 617, 350);
                }
            }
            else if (mapid.equals(Long.valueOf(1208L))) {
                if (mark != null) {
                    mark.draw(g, 697, 300);
                }
            }
            else if (mapid.equals(Long.valueOf(1210L))) {
                if (mark != null) {
                    mark.draw(g, 472, 385);
                }
            }
            else if (mapid.equals(Long.valueOf(1211L))) {
                if (mark != null) {
                    mark.draw(g, 622, 460);
                }
            }
            else if (mapid.equals(Long.valueOf(1213L))) {
                if (mark != null) {
                    mark.draw(g, 697, 332);
                }
            }
            else if (mapid.equals(Long.valueOf(1227L))) {
                if (mark != null) {
                    mark.draw(g, 587, 430);
                }
            }
            else if (mapid.equals(Long.valueOf(1228L))) {
                if (mark != null) {
                    mark.draw(g, 192, 239);
                }
            }
            else if (mapid.equals(Long.valueOf(1229L))) {
                if (mark != null) {
                    mark.draw(g, 172, 285);
                }
            }
            else if (mapid.equals(Long.valueOf(1230L))) {
                if (mark != null) {
                    mark.draw(g, 222, 120);
                }
            }
            else if (mapid.equals(Long.valueOf(1231L))) {
                if (mark != null) {
                    mark.draw(g, 267, 99);
                }
            }
            else if (mapid.equals(Long.valueOf(1232L)) && mark != null) {
                mark.draw(g, 187, 90);
            }
            if (mapid.equals(Long.valueOf(1236L))) {
                if (mark != null) {
                    mark.updateToTime(ImageMixDeal.userimg.getTime(), 8);
                    mark.draw(g, 474, 433);
                }
            }
            else if (mapid.equals(Long.valueOf(1240L))) {
                if (mark != null) {
                    mark.draw(g, 187, 55);
                }
            }
            else if (mapid.equals(Long.valueOf(1241L))) {
                if (mark != null) {
                    mark.draw(g, 257, 45);
                }
            }
            else if (mapid.equals(Long.valueOf(1242L))) {
                if (mark != null) {
                    mark.draw(g, 402, 370);
                }
            }
            else if (mapid.equals(Long.valueOf(1244L))) {
                if (mark != null) {
                    mark.draw(g, 597, 230);
                }
            }
            else if (mapid.equals(Long.valueOf(1246L))) {
                if (mark != null) {
                    mark.draw(g, 162, 182);
                }
            }
            else if (mapid.equals(Long.valueOf(1248L))) {
                if (mark != null) {
                    mark.draw(g, 663, 110);
                }
            }
            else if (mapid.equals(Long.valueOf(1248L))) {
                if (mark != null) {
                    mark.draw(g, 663, 110);
                }
            }
            else if (mapid.equals(Long.valueOf(1250L))) {
                if (mark != null) {
                    mark.draw(g, 619, 138);
                }
            }
            else if (mapid.equals(Long.valueOf(3369L))) {
                if (mark != null) {
                    mark.draw(g, 664, 170);
                }
            }
            else if (mapid.equals(Long.valueOf(3155L))) {
                if (mark != null) {
                    mark.draw(g, 639, 193);
                }
            }
            else if (mapid.equals(Long.valueOf(1251L))) {
                if (mark != null) {
                    mark.draw(g, 317, 367);
                }
            }
            else if (mapid.equals(Long.valueOf(1252L))) {
                if (mark != null) {
                    mark.draw(g, 152, 378);
                }
            }
            else if (mapid.equals(Long.valueOf(1254L))) {
                if (mark != null) {
                    mark.draw(g, 540, 255);
                }
            }
            else if (mapid.equals(Long.valueOf(1259L))) {
                if (mark != null) {
                    mark.draw(g, 307, 398);
                }
            }
            else if (mapid.equals(Long.valueOf(1263L))) {
                if (mark != null) {
                    mark.draw(g, 432, 312);
                }
            }
            else if (mapid.equals(Long.valueOf(1267L))) {
                if (mark != null) {
                    mark.draw(g, 437, 110);
                }
            }
            else if (mapid.equals(Long.valueOf(1272L))) {
                if (mark != null) {
                    mark.draw(g, 407, 343);
                }
            }
            else if (mapid.equals(Long.valueOf(1278L))) {
                if (mark != null) {
                    mark.draw(g, 351, 308);
                }
            }
            else if (mapid.equals(Long.valueOf(1279L))) {
                if (mark != null) {
                    mark.draw(g, 340, 335);
                }
            }
            else if (mapid.equals(Long.valueOf(1280L))) {
                if (mark != null) {
                    mark.draw(g, 375, 277);
                }
            }
            else if (mapid.equals(Long.valueOf(1296L))) {
                if (mark != null) {
                    mark.draw(g, 437, 60);
                }
            }
            else if (mapid.equals(Long.valueOf(1298L))) {
                if (mark != null) {
                    mark.draw(g, 577, 285);
                }
            }
            else if (mapid.equals(Long.valueOf(3012L))) {
                if (mark != null) {
                    mark.draw(g, 692, 463);
                }
            }
            else if (mapid.equals(Long.valueOf(3180L))) {
                if (mark != null) {
                    mark.draw(g, 302, 262);
                }
            }
            else if (mapid.equals(Long.valueOf(3205L))) {
                if (mark != null) {
                    mark.draw(g, 467, 43);
                }
            }
            else if (mapid.equals(Long.valueOf(3210L))) {
                if (mark != null) {
                    mark.draw(g, 257, 337);
                }
            }
            else if (mapid.equals(Long.valueOf(3307L))) {
                if (mark != null) {
                    mark.draw(g, 272, 292);
                }
            }
            else if (mapid.equals(Long.valueOf(3308L))) {
                if (mark != null) {
                    mark.draw(g, 252, 362);
                }
            }
            else if (mapid.equals(Long.valueOf(1802L))) {
                if (mark != null) {
                    mark.draw(g, 812, 230);
                }
            }
            else if (mapid.equals(Long.valueOf(1803L))) {
                if (mark != null) {
                    mark.draw(g, 830, 315);
                }
            }
            else if (mapid.equals(Long.valueOf(4006L))) {
                if (mark != null) {
                    mark.draw(g, 792, 345);
                }
            }
            else if (mapid.equals(Long.valueOf(1804L))) {
                if (mark != null) {
                    mark.draw(g, 862, 190);
                }
            }
            else if (mapid.equals(1806L)) {
                if (mark != null) {
                    mark.draw(g, 862, 270);
                }
            }
            else if (mapid.equals(Long.valueOf(1101L))) {
                if (mark != null) {
                    mark.draw(g, 295, 435);
                }
            }
            else if (mapid.equals(Long.valueOf(600L)) && mark != null) {
                mark.draw(g, 172, 435);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu1/worldmaph.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 953, 501, this);
        }
    }
    
    public DefaultTableModel getTableModel() {
        return WorldMapJpanel.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        WorldMapJpanel.tableModel = tableModel;
    }
    
    public static JTextField getFindName1() {
        return WorldMapJpanel.findName1;
    }
    
    public static void setFindName1(JTextField findName1) {
        WorldMapJpanel.findName1 = findName1;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return WorldMapJpanel.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        WorldMapJpanel.Fvalue = fvalue;
    }
    
    public JTextArea getTestMes() {
        return WorldMapJpanel.testMes;
    }
    
    public void setTestMes(JTextArea testMes) {
        WorldMapJpanel.testMes = testMes;
    }
    
    public static synchronized void loadMap(int mapid, int x, int y) {
        WorldMapJpanel.ditubianma = mapid;
        List<Door> doors = new ArrayList<>();
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(11);
        if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + WorldMapJpanel.ditubianma + "1.map"), doors, false);
        }else if (WorldMapJpanel.ditubianma >= 100000) {
            String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + zhMapID+ ".map"), doors, false);
        }
        else {
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + WorldMapJpanel.ditubianma + ".map"), doors, false);
        }
        WorldMapJpanel.mapmodel.Mapsize();
        WorldMapJpanel.mapmodel.force(x, y);
        try {
            Image image;//新修地图多次使用
            if (MyIsif.getStyle().equals("水墨")) {
                if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                    image = new ImageIcon("resource/smap/s" + WorldMapJpanel.ditubianma + "1.png").getImage();
                }else if (WorldMapJpanel.ditubianma >= 100000) {
                    String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
                    image = new ImageIcon("resource/smap/s" + zhMapID + ".png").getImage();
                }
                else {
                    image = new ImageIcon("resource/smap/s" + WorldMapJpanel.ditubianma + ".png").getImage();
                }
            }
            else if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                image = new ImageIcon("resource/smap/h" + WorldMapJpanel.ditubianma + "1.png").getImage();
            }else if (WorldMapJpanel.ditubianma >= 100000) {
                String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
                image = new ImageIcon("resource/smap/h" + zhMapID + ".png").getImage();
            }
            else {
                image = new ImageIcon("resource/smap/h" + WorldMapJpanel.ditubianma + ".png").getImage();
            }
            if (image.getWidth(null) <= 0) {
                WorldTestsmallmapJframe.getWorldTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, 5, 1);
            }
            else {
                WorldTestsmallmapJframe.getWorldTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, image.getWidth(null) + 5, image.getHeight(null) + 1);
            }
            WorldMapJpanel.mapmodel.MiniMap(image);
            FormsManagement.showForm(1103);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized void loadMapTP(int mapid, int x, int y) {
        WorldMapJpanel.ditubianma = mapid;
        List<Door> doors = new ArrayList<>();
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(11);
        if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + WorldMapJpanel.ditubianma + "1.map"), doors, false);
        }else if (WorldMapJpanel.ditubianma >= 100000) {
            String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + zhMapID + ".map"), doors, false);

        }
        else {
            WorldMapJpanel.mapmodel.getjMap().loadMap(new File("resource/map/" + WorldMapJpanel.ditubianma + ".map"), doors, false);
        }
        WorldMapJpanel.mapmodel.Mapsize();
        WorldMapJpanel.mapmodel.force(x, y);
        try {
            Image image;
            if (MyIsif.getStyle().equals("水墨")) {
                if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                    image = new ImageIcon("resource/smap/s" + WorldMapJpanel.ditubianma + "1.png").getImage();
                }else if (WorldMapJpanel.ditubianma >= 100000) {
                    String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
                    image = new ImageIcon("resource/smap/s" + zhMapID + ".png").getImage();

                }
                else {
                    image = new ImageIcon("resource/smap/s" + WorldMapJpanel.ditubianma + ".png").getImage();
                }
            }
            else if ((WorldMapJpanel.ditubianma == 1193 || WorldMapJpanel.ditubianma == 1207) && (hour >= 19 || hour <= 7)) {
                image = new ImageIcon("resource/smap/h" + WorldMapJpanel.ditubianma + "1.png").getImage();
            }else if (WorldMapJpanel.ditubianma >= 100000) {
                String zhMapID=(WorldMapJpanel.ditubianma+"").substring(0,(WorldMapJpanel.ditubianma+"").length()-2);
                image = new ImageIcon("resource/smap/h" + zhMapID + ".png").getImage();
            }
            else {
                image = new ImageIcon("resource/smap/h" + WorldMapJpanel.ditubianma + ".png").getImage();
            }
            if (image.getWidth(null) <= 0) {
                WorldTestsmallmapJframe.getWorldTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, 5, 1);
            }
            else {
                WorldTestsmallmapJframe.getWorldTestsmallmapJframe().setBounds(ScrenceUntil.Screen_x / 2 - 400, ScrenceUntil.Screen_y / 2 - 250, image.getWidth(null) + 5, image.getHeight(null) + 1);
            }
            WorldMapJpanel.mapmodel.MiniMap(image);
            WorldTestsmallmapJframe.getWorldTestsmallmapJpanel();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setNumber() {
        List<Goodstable> goodstables = GoodsListFromServerUntil.chaxunss(2125L);
        if (goodstables != null && goodstables.size() > 0 && goodstables.size() > 1) {
            goodstables.sort(Comparator.comparing(Goodstable::getValue));
        }
        String[] vs = ((Goodstable)goodstables.get(0)).getValue().split("\\|");
        String[] cishu = vs[vs.length - 1].split("=");
        int sum = Integer.parseInt(cishu[1]);
        --sum;
        StringBuffer buffer = new StringBuffer();
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(cishu[0]);
        buffer.append("=");
        buffer.append(sum);
        ((Goodstable)goodstables.get(0)).setValue(buffer.toString());
        if (sum <= 0) {
            ((Goodstable)goodstables.get(0)).setUsetime(Integer.valueOf(0));
        }
        GoodsMouslisten.gooduse((Goodstable)goodstables.get(0), 2);
        if (sum <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(((Goodstable)goodstables.get(0)).getRgid());
        }
    }
    
    static {
        WorldMapJpanel.mapZb = "1207,长安城,4070,2710|1193,长安东,2850,2330|1208,东海渔村,3270,2790|1210,大唐境内,3730,376|1211,地府,770,1030|1213,珊瑚海岛,119,466|1227,轮回司,1030,770|1228,长寿村,830,4130|1229,长寿村外,2350,530|1230,天宫,2590,1910|1231,御马监,450,1810|1232,瑶池,380,1620|1236,洛阳城,2293,2966|1240,广寒宫,2540,1400|1241,蟠桃园,300,3920|1242,五指山,5299,1588|1244,龙宫,1580,1360|1246,方寸山,97,1142|1248,女儿村,250,1010|1250,傲来国,4429,2129|1251,大唐边境,1560,2400|1252,普陀山,504,1741|1254,化生寺,3586,2754|1259,狮驼岭,1102,1863|1263,五庄观,180,1800|1267,北俱芦洲,1119,2687|1272,斧头帮,3541,2789|1278,万寿山,398,1520|1279,四圣庄,4305,3664|1280,白骨山,4840,3240|1296,灵兽村,364,2707|1298,皇宫,588,2448|3012,兰若寺,1890,1830|3180,宝象国,694,1320|3205,修罗古城,2362,1183|3210,火云戈壁,1157,1343|3307,平顶山,6020,3280|3308,女儿国,1977,2952|1275,地狱迷宫1,1280,1024|1273,地狱迷宫2,1280,1024|1276,地狱迷宫3,1280,1024|1274,地狱迷宫4,1280,1024|1268,海底迷宫1,1792,1280|1269,海底迷宫2,1792,1280|1270,海底迷宫3,1792,1280|1271,海底迷宫4,1792,1280|1221,大雁塔一层,3072,2304|1222,大雁塔二层,3072,2304|1223,大雁塔三层,3072,2304|1224,大雁塔四层,3072,2304|1225,大雁塔五层,3072,2304|1226,大雁塔六层,1024,768|3311,大雁塔七层,960,720|1289,凤巢一层,2048,1536|1290,凤巢二层,2048,1536|1291,凤巢三层,2560,2048|1292,凤巢四层,2560,2048|1293,凤巢五层,2560,2048|1294,凤巢六层,2560,2048|1295,凤巢七层,2560,2048|1282,龙窟,2048,1536|1283,龙窟二层,2048,1536|1284,龙窟三层,2560,2048|1285,龙窟四层,2560,2048|1286,龙窟五层,2560,2048|1287,龙窟六层,2560,2048|1288,龙窟七层,2560,2048|3155,水帘洞,2500,1300|3369,花果山,2100,1980|1803,方壶,860,1300|1804,流洲,1220,860|1806,瀛洲,1530,990";
        WorldMapJpanel.mapmodel = new Mapmodelworld();
        WorldMapJpanel.ditubianma = 0;
    }
}
