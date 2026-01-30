package com.tool.tcpimg;

import java.util.HashMap;

import org.come.until.FontUntil;
import org.come.until.AnalysisString;

import java.util.Map;
import java.awt.Color;
import java.awt.Font;

//颜色
public class UIUtils {
    public static final int NAMESiZE = 18;
    public static final Font TEXT_FONT10 = new Font("宋体", Font.PLAIN, 10);
    public static final Font TEXT_FONT11 = new Font("宋体", Font.PLAIN, 12);
    public static final Font TEXT_FONT6 = new Font("微软雅体", Font.BOLD, 20);
    public static final Font TEXT_FONT8 = new Font("微软雅体", 0, 17);
    public static final Font TEXT_FONT = new Font("宋体", Font.PLAIN, 12);
    public static final Font TEXT_FONT221 = new Font("正楷", Font.BOLD, 14);
    public static final Font TEXT_FONT2 = new Font("宋体", Font.PLAIN, 13);
    public static final Font TEXT_FONT1 = new Font("宋体", Font.PLAIN, 14);
    public static final Font TEXT_HYZK = new Font("汉仪中楷简", Font.BOLD, 12);
    public static final Font TEXT_FZKT = new Font("FZ26", Font.BOLD, 16);
    public static final Font TEXT_FONT15 = new Font("宋体", 0, 15);
    public static final Font TEXT_FONT14 = new Font("宋体", 0, 14);
    public static final Font TEXT_FONT78 = new Font("宋体", Font.PLAIN, 14);
    public static final Font TEXT_FONT1B = new Font("宋体", Font.BOLD, 14);
    public static final Font TEXT_NAME_FONT = new Font("宋体", Font.PLAIN, 16);
    public static final Font TEXT_BOLD_FONT = new Font("宋体", Font.BOLD, 16);
    public static final Font TEXT_FONT22 = new Font("宋体", Font.PLAIN, 18);
    public static final Font TEXT_FONT_17 = new Font("宋楷", Font.BOLD, 17);
    public static final Font TEXT_FONT3 = new Font("宋体", Font.PLAIN, 20);
    public static final Font TEXT_FONT60 = new Font("汉仪小隶书", Font.PLAIN, 14);
    public static final Font TEXT_FONT61 = new Font("楷体", Font.BOLD, 17);
    public static final Font TEXT_FONT62 = new Font("LiSu", Font.BOLD, 18);
    public static final Font TEXT_FONT64 = new Font("楷体", Font.BOLD, 19);
    public static final Font TEXT_FONT19 = new Font("宋体", Font.PLAIN, 19);
    //给按钮使用的
    public static final Font TEXT_FONT79 = new Font("仿宋", Font.BOLD, 17);
    //人物包裹按钮
    public static final Font TEXT_FONT82 = new Font("宋体", Font.PLAIN, 12);
    public static final Font TEXT_FONT991 = new Font("宋体", Font.PLAIN, 0);
    public static final Font TEXT_FONTS15 = new Font("宋体", Font.PLAIN, 15);
    public static final Font TEXT_FONT80 = new Font("宋楷", Font.PLAIN, 15);
    public static final Font TEXT_FONT81 = new Font("正楷", Font.BOLD, 16);
    public static final Font TEXT_FONT811 = new Font("楷体", 1, 18);
    // 天演策技能名称
    public static final Font TEXT_FONT5 = new Font("宋体", 1, 19);
    // 汉仪篆书简
    public static final Font TEXT_FONTZS = FontUntil.getHanYiFont(16);
    public static final Font TEXT_FONTZSRL = FontUntil.getHanYiFont(24);
    // 汉仪篆书繁
    public static final Font TEXT_FONTZSF18 = FontUntil.getHYLSFFont(18);
    public static final Font TEXT_FONTZSF20 = FontUntil.getHYLSFFont(20);
    public static final Font TEXT_FONTZSF24 = FontUntil.getHYLSFFont(24);
    public static final Font TEXT_FONT7 = new Font("微软雅体", Font.PLAIN, 8);
    public static final Font TEXT_FONT33 = new Font("宋体", Font.PLAIN, 14);
    public static final Font TEXT_FONT86 = new Font("正楷", Font.PLAIN, 16);
    public static final Font TEXT_FONT_BOLD = new Font("宋体", 1, 12);
    public static final Font TEXT_HYK16a = FontUntil.getHYKTJFont1(16);
    public static final Font TEXT_FONTZS1 = FontUntil.getHanYiFont1(16);
    public static final Font TEXT_FONTZS2 = FontUntil.getHanYiFont1(18);
    public static final Font TEXT_HYK18a = FontUntil.getHYKTJFont1(18);
    public static Color[] COLOR_BTNPUTONG3 = new Color[3];
    public static Font HYI_WBJ19S;
    public static Font TEXT_BT40s;
    public static Font TEXT_BT16;
    public static Font TEXT_HY16P;
    public static Font nameFont;
    public static Font TEXT_FONT87;
    public static Font TEXT_FONT89;
    public static Font TEXT_FONT90;
    public static Font TXT_lianss;
    public static Font TEXT_shuimo2;
    public static Font TXT_lishud;
    public static Font TXT_hyzjt;
    public static Font TXT_hyzjt16;
    public static Font TXT_hyzjt18;
    public static Font TEXT_HYK16;
    public static Font TEXT_HYK20;
    public static Font TEXT_BT65;
    public static Font TEXT_HY12;
    public static Font TEXT_HY14;
    public static Font TEXT_HY15;
    public static Font TEXT_HY16;
    public static Font TEXT_HY17;
    public static Font TEXT_HY19;
    public static Font TEXT_HY24;
    public static Font TEXT_HY34;
    public static Font TEXT_HY99;
    public static Font TEXT_HY88;
    public static Font TEXT_HY888;
    public static Font TEXT_HY192;
    public static Font TEXT_HY193;
    public static Font HYXLSJ15;
    public static final Color[] CangBaoGe;
    public static Font TEXT_HY16s;
    public static Font TEXT_HY16sb;
    public static Color[] Black;
    public static final Font TEXT_MSGX;
    public static final Font TEXT_NAME_FONT15;
    public static Color[] COLOR_NAVIGATIONS = new Color[3];
    public static Color[] COLOR_NAVIGATIONSW = new Color[3];
    public static Color[] COLOR_NAVIGATIONAJ = new Color[3];
    public static Color[] COLOR_WHITE4 = new Color[3];
    /**
     * 字体:宋体-加粗:0-字体:15号
     */
    public static final Font TEXT_COM_FONT = new Font("宋体", 0, 15);

    // 翅膀品质对应颜色
    /**
     * 把玩品质颜色
     */
    public static final Color COLOR_PLAY = new Color(41, 191, 107);
    /**
     * 贴身品质颜色
     */
    public static final Color COLOR_PERSONAL = new Color(1, 251, 249);
    /**
     * 珍藏品质颜色
     */
    public static final Color COLOR_TREASURE = new Color(239, 238, 12);
    /**
     * 无价品质颜色
     */
    public static final Color COLOR_NONVALENT = new Color(219, 10, 205);
    /**
     * 传世品质颜色
     */
    public static final Color COLOR_CHENS = new Color(243, 94, 1);
    /**
     * 神迹品质颜色
     */
    public static final Color COLOR_SIGN = new Color(251, 0, 1);

    /**
     * 按钮字体颜色1
     */
    public static final Color COLOR_TEXT1 = new Color(187, 165, 75);
    public static Color[] White = {new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)};
    // 黑
    public static final Color COLOR_NAME16 = new Color(0, 0, 0);// 黑
    /**
     * 字段名:COLOR_BTNPUTONG1 <br>
     * 普通按钮
     */
    public static Color[] COLOR_BTNPUTONG;
    public static Color[] COLOR_RED_BTNTEXT;
    public static Color[] COLOR_BTNPUTONGS;
    public static Color[] COLOR_YELLOW;
    /**
     * 灰土色
     */
    public static Color[] COLOR_HUI;//灰土色
    /**
     * 灰土色
     */
    public static Color[] COLOR_HUI1;
    /**
     * 黑
     */
    public static Color[] COLOR_BLACK;
    /**
     * 白
     */
    public static Color[] COLOR_WHITE;
    /**
     * 灰白
     */
    public static Color[] COLOR_WHITE1;
    /**
     * 红
     */
    public static Color[] COLOR_HONG;
    /**
     * 黄白
     */
    public static Color[] COLOR_WHITE3;
    /**
     * 字段名:COLOR_BTNTEXT <br>
     * 文字按钮/选项卡按钮
     */
    public static Color[] COLOR_BTNTEXT;
    /**
     * 字段名:COLOR_BTNXUANXIANGKA <br>
     * 选项卡按钮
     */
    public static Color[] COLOR_BTNXUANXIANGKA;
    public static Color[] COLOR_BTNPUTONG2;
    /**
     * 黑绿按键
     */
    public static Color[] COLOR_WHITE2;
    public static Color[] COLOR_WHITE5;
    public static Color[] COLOR_Pack1;
    /**
     * 提示文字
     */
    public static final Color COLOR_SIGN1 = new Color(255, 255, 0);
    public static Color[] COLOR_BTNPUTONG4 = new Color[3];
    public static Color[] COLOR_BTNPUTONG5 = new Color[3];
    public static final Color COLOR_FIRSTTEAM;
    public static final Font TEXT_HURT2;
    public static final Font TEXT_FONT42;
    public static final Font TEXT_FONTSC;
    public static final Font TEXT_FONT21;
    public static final Font TEXT_FONT41;
    public static final Font TEXT_MSG;
    public static final Font TEXT_MSG1;
    public static final Font TEXT_FONT4;
    public static final Color COLOR_HIHT;
    public static final Color COLOR_NAME_BACKGROUND;
    public static final Color Color_BACK;
    public static final Font TEXT_TIP;
    public static final Font TEXT_RESULT;
    public static final Color COLOR_HURTR1;
    public static final Color COLOR_HURTB1;
    public static final Color COLOR_HURTB2;
    public static final Color COLOR_HURTY1;
    // NPC暗
    public static final Color COLOR_NPC0;
    // NPC亮
    public static final Color COLOR_NPC1;
    public static final Color COLOR_Y2 = Color.GREEN;
    public static final Color COLOR_NPC_SELECT;
    public static final Color COLOR_NAME;
    public static final Color COLOR_NAME1;
    public static final Color COLOR_NAME2;
    public static final Color COLOR_NAME3;
    public static final Color COLOR_NAME4;
    public static final Color COLOR_NAME5;
    public static final Color COLOR_NAME6;
    public static final Color COLOR_NAME7;
    public static final Color COLOR_NAME8;
    public static final Color COLOR_NAME9;
    public static final Color COLOR_NAME10;
    public static final Color COLOR_NAME_PET;
    public static final Color COLOR_NAME1_PET;
    public static final Color COLOR_NAME2_PET;
    public static final Color COLOR_NAME3_PET;
    public static final Color COLOR_NAME4_PET;
    public static final Color COLOR_title;
    public static final Color COLOR_title2;
    public static final Color COLOR_HURTB3;
    public static final Color COLOR_FightingRound;
    public static final Color COLOR_FightingRound_Black;
    public static final Color COLOR_cbg9;
    public static final Color COLOR_SBCode1;
    public static final Color COLOR_SBCode;
    public static final Color COLOR_YCode;
    public static final Color COLOR_RCode;
    public static final Color COLOR_BCode;
    public static final Color COLOR_YMRCode;
    public static final Color COLOR_Wing1;
    public static final Color COLOR_Draw1;
    public static final Color COLOR_TBule;
    public static final Color COLOR_Pack;
    public static final Color COLOR_CBG1;
    public static final Color COLOR_CBG2;
    public static final Color COLOR_CBG3;
    public static final Color COLOR_CBG4;
    public static final Color COLOR_CBG5;
    public static final Color TEXT_NAME_NPC_COLOR;
    public static final Color COLOR_NAME_HIGHLIGHT;
    public static Color[] xbTotBtnColor;
    private static Map<String, Color> colors;
    public static Color[] danxin;

    public static Color getcolor(int zs) {
        switch (zs) {
            case -1: {
                return Color.gray;
            }
            case 0: {
                return UIUtils.COLOR_NAME;
            }
            case 1: {
                return UIUtils.COLOR_NAME1;
            }
            case 2: {
                return UIUtils.COLOR_NAME2;
            }
            case 3: {
                return UIUtils.COLOR_NAME3;
            }
            case 4: {
                return UIUtils.COLOR_NAME4;
            }
            default: {
                return UIUtils.COLOR_NAME;
            }
        }
    }
    public static final Color COLOR_cbg3 = new Color(83, 47, 7);
    public static final Color COLOR_NPCTOU = new Color(160, 86, 9);
    public static Font hywbt;
    public static Color getDarkColor(int zs) {
        switch (zs) {
            case 0: {
                return UIUtils.COLOR_NAME5;
            }
            case 1: {
                return UIUtils.COLOR_NAME6;
            }
            case 2: {
                return UIUtils.COLOR_NAME7;
            }
            case 3: {
                return UIUtils.COLOR_NAME8;
            }
            case 4: {
                return UIUtils.COLOR_NAME9;
            }
            default: {
                return UIUtils.COLOR_NAME;
            }
        }
    }

    public static Color getPetcolor(int zs) {
        switch (zs) {
            case -1: {
                return Color.gray;
            }
            case 0: {
                return UIUtils.COLOR_NAME_PET;
            }
            case 1: {
                return UIUtils.COLOR_NAME1_PET;
            }
            case 2: {
                return UIUtils.COLOR_NAME2_PET;
            }
            case 3: {
                return UIUtils.COLOR_NAME3_PET;
            }
            case 4: {
                return UIUtils.COLOR_NAME4_PET;
            }
            default: {
                return UIUtils.COLOR_NAME;
            }
        }
    }

    public static Color getColor(String color) {
        Color c = (Color) UIUtils.colors.get(color);
        if (c == null) {
            c = Color.decode("0x" + color.substring(2));
            UIUtils.colors.put(color, c);
        }
        return c;
    }

    public static Color gradeForColor(int grade) {
        String gradeMes = AnalysisString.lvl(grade);
        String[] intMes = gradeMes.split("转");
        Color returnColor;
        if (intMes.length > 1) {
            returnColor = getcolor((int) Integer.valueOf(intMes[0]));
        } else {
            returnColor = getcolor(4);
        }
        return returnColor;
    }

    static {
        Font font = FontUntil.loadFont("font/方正仿宋_GBK.TTF");
        (xbTotBtnColor = new Color[3])[0] = new Color(245, 230, 202);
        xbTotBtnColor[2] = (xbTotBtnColor[1] = new Color(87, 70, 44));
        COLOR_BTNPUTONG4[0] = new Color(254, 251, 233, 255);
        COLOR_BTNPUTONG4[1] = new Color(255, 255, 255, 255);
        COLOR_BTNPUTONG4[2] = new Color(246, 241, 221, 255);
//        TEXT_FONT10 = new Font("宋体", 0, 10);
//        TEXT_FONT11 = new Font("宋体", 0, 12);
//        TEXT_FONT6 = new Font("微软雅体", 1, 20);
//        TEXT_FONT8 = new Font("微软雅体", 0, 17);
//        TEXT_FONT = new Font("宋体", 0, 12);
//        TEXT_FONT221 = new Font("正楷", 1, 14);
//        TEXT_FONT2 = new Font("宋体", 0, 13);
//        TEXT_FONT1 = new Font("宋体", 0, 14);
//        TEXT_HYZK = new Font("汉仪中楷简", 1, 12);
//        TEXT_FZKT = new Font("FZ26", 1, 16);
//        TEXT_FONT15 = new Font("宋体", 0, 15);
//        TEXT_FONT14 = new Font("宋体", 0, 14);
//        TEXT_FONT78 = new Font("宋体", 0, 14);
//        TEXT_FONT1B = new Font("宋体", 1, 14);
//        TEXT_NAME_FONT = new Font("宋体", 0, 16);
//        TEXT_BOLD_FONT = new Font("宋体", 1, 16);
//        TEXT_FONT22 = new Font("宋体", 0, 18);
//        TEXT_FONT_17 = new Font("宋楷", 1, 17);
//        TEXT_FONT3 = new Font("宋体", 0, 20);
//        TEXT_FONT60 = new Font("汉仪小隶书", 0, 14);
//        TEXT_FONT61 = new Font("楷体", 1, 17);
//        TEXT_FONT62 = new Font("LiSu", 1, 18);
//        TEXT_FONT64 = new Font("楷体", 1, 19);
//        TEXT_FONT19 = new Font("宋体", 0, 19);
//        TEXT_FONT79 = new Font("仿宋", 1, 17);
//        TEXT_FONT82 = new Font("宋体", 0, 12);
//        TEXT_FONT991 = new Font("宋体", 0, 0);
//        TEXT_FONTS15 = new Font("宋体", 0, 15);
//        TEXT_FONT80 = new Font("宋楷", 0, 15);
//        TEXT_FONT81 = new Font("正楷", 1, 16);
//        TEXT_FONT5 = new Font("宋体", 1, 19);
//        TEXT_FONTZS = FontUntil.getHanYiFont(16);
//        TEXT_FONTZSRL = FontUntil.getHanYiFont(24);
//        TEXT_FONTZSF18 = FontUntil.getHYLSFFont(18);
//        TEXT_FONTZSF20 = FontUntil.getHYLSFFont(20);
//        TEXT_FONTZSF24 = FontUntil.getHYLSFFont(24);
//        TEXT_FONT7 = new Font("微软雅体", 0, 8);
//        TEXT_FONT33 = new Font("宋体", 0, 14);
//        TEXT_FONT86 = new Font("正楷", 0, 16);
        TEXT_BT40s = font.deriveFont(0, 40.0F);
        TEXT_BT65 = font.deriveFont(0, 65.0F);
        TEXT_BT16 = font.deriveFont(0, 16.0F);
        UIUtils.nameFont = new Font("方正规范书宋", 1, 16);
        UIUtils.TEXT_FONT87 = new Font("方正规范书宋", 0, 14);
        UIUtils.TEXT_FONT89 = new Font("汉仪中楷简", 1, 16);
        UIUtils.TEXT_FONT90 = new Font("汉仪中楷简", 1, 14);
        CangBaoGe = new Color[3];

        UIUtils.TEXT_HY12 = font.deriveFont(1, 12.0f);
        UIUtils.TEXT_HY14 = font.deriveFont(1, 14.0f);
        UIUtils.TEXT_HY16s = font.deriveFont(0, 16.0f);
        UIUtils.TEXT_HY16sb = font.deriveFont(1, 16.0f);
        UIUtils.TEXT_HY15 = font.deriveFont(1, 15.0f);
        UIUtils.TEXT_HY16 = font.deriveFont(1, 18.0f);
        UIUtils.TEXT_HY12 = font.deriveFont(0, 12.0f);
        UIUtils.TEXT_HY17 = font.deriveFont(1, 17.0f);
        UIUtils.TEXT_HY19 = font.deriveFont(1, 19.0f);
        UIUtils.TEXT_HY24 = font.deriveFont(1, 24.0f);
        UIUtils.TEXT_HY34 = font.deriveFont(1, 34.0f);
        UIUtils.TEXT_HY99 = font.deriveFont(1, 15.0f);
        UIUtils.TEXT_HY192 = font.deriveFont(1, 18.0f);
        UIUtils.TEXT_HY193 = font.deriveFont(1, 20.0f);
        UIUtils.HYXLSJ15 = font.deriveFont(1, 16.0f);
        UIUtils.TEXT_HY88 = font.deriveFont(1, 16.0f);
        UIUtils.TEXT_HY888 = font.deriveFont(1, 13.0f);
        font = FontUntil.loadFont("font/HYC1GJM.TTF");
        UIUtils.TEXT_HYK16 = font.deriveFont(1, 16.0f);
        UIUtils.TEXT_HYK20 = font.deriveFont(1, 20.0f);
        (UIUtils.Black = new Color[3])[0] = new Color(0, 0, 0);
        UIUtils.Black[2] = (UIUtils.Black[1] = new Color(0, 0, 0));
        TEXT_MSGX = new Font("宋体", 0, 14);
        TEXT_NAME_FONT15 = new Font("宋体", 0, 15);
//        font = FontUntil.loadFont("font/HYC.ttf");
//        hywbt = font.deriveFont(0, 20.0F);
//        hywbt24 = font.deriveFont(Font.LAYOUT_LEFT_TO_RIGHT + Font.BOLD, 22);
//        TEXT_COM_FONT = new Font("宋体", 0, 15);
//        COLOR_PLAY = new Color(41, 191, 107);
//        COLOR_PERSONAL = new Color(1, 251, 249);
//        COLOR_TREASURE = new Color(239, 238, 12);
//        COLOR_NONVALENT = new Color(219, 10, 205);
//        COLOR_CHENS = new Color(243, 94, 1);
//        COLOR_SIGN = new Color(251, 0, 1);
//        COLOR_TEXT1 = new Color(187, 165, 75);
//        UIUtils.White = new Color[] { new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255) };
//        COLOR_NAME16 = new Color(0, 0, 0);
//        COLOR_SIGN1 = new Color(255, 255, 0);
        COLOR_FIRSTTEAM = new Color(176, 180, 48);
        TEXT_HURT2 = new Font("宋体", 1, 14);
        TEXT_FONT42 = new Font("楷体", 0, 18);
        TEXT_FONTSC = new Font("楷体", 1, 18);
        TEXT_FONT21 = new Font("楷体", 3, 17);
        TEXT_FONT41 = new Font("微软雅体", 0, 20);
        TEXT_MSG = new Font("微软雅体", 0, 14);
        TEXT_MSG1 = new Font("微软雅体", 1, 14);
        TEXT_FONT4 = new Font("微软雅体", 1, 50);
        COLOR_HIHT = new Color(8, 4, 8);
        COLOR_NAME_BACKGROUND = new Color(27, 26, 18);
        Color_BACK = new Color(0, 0, 0, 0);
        TEXT_TIP = new Font("微软雅黑", 1, 15);
        TEXT_RESULT = new Font("微软雅黑", 1, 20);
        COLOR_HURTR1 = new Color(255, 0, 0);
        COLOR_HURTB1 = new Color(13, 116, 186);
        COLOR_HURTB2 = new Color(0, 18, 31);
        COLOR_HURTY1 = new Color(208, 208, 14);
        COLOR_NPC0 = new Color(218, 220, 116);
        COLOR_NPC1 = new Color(231, 231, 17);
//        COLOR_Y2 = Color.GREEN;
        COLOR_NPC_SELECT = Color.RED;
        COLOR_NAME = new Color(0, 205, 0);
        COLOR_NAME1 = new Color(255, 110, 0);
        COLOR_NAME2 = new Color(39, 203, 218);
        COLOR_NAME3 = new Color(180, 16, 16);
        COLOR_NAME4 = new Color(99, 8, 183);
        COLOR_NAME5 = new Color(0, 205, 0);
        COLOR_NAME6 = new Color(45, 15, 15);
        COLOR_NAME7 = new Color(4, 81, 91);
        COLOR_NAME8 = new Color(45, 15, 15);
        COLOR_NAME9 = new Color(88, 13, 157);
        COLOR_NAME10 = new Color(0, 205, 0);
        COLOR_NAME_PET = new Color(238, 99, 99);
        COLOR_NAME1_PET = new Color(255, 105, 180);
        COLOR_NAME2_PET = new Color(153, 250, 204);
        COLOR_NAME3_PET = Color.BLUE;
        COLOR_NAME4_PET = new Color(99, 8, 183);
        COLOR_title = new Color(58, 126, 194);
        COLOR_title2 = new Color(13, 111, 229);
        COLOR_HURTB3 = new Color(255, 255, 255);
        COLOR_FightingRound = new Color(199, 252, 0);
        COLOR_FightingRound_Black = new Color(0, 0, 0, 179);
        COLOR_cbg9 = new Color(182, 150, 80);
        COLOR_SBCode1 = new Color(225, 223, 89);
        COLOR_SBCode = new Color(250, 250, 198);
        COLOR_YCode = new Color(255, 241, 17);
        COLOR_RCode = new Color(255, 31, 31);
        COLOR_BCode = new Color(99, 31, 255);
        COLOR_YMRCode = new Color(170, 2, 2);
        COLOR_Wing1 = new Color(187, 165, 75);
        COLOR_Draw1 = new Color(210, 222, 122);
        COLOR_TBule = new Color(87, 250, 255);
        COLOR_Pack = new Color(36, 31, 0);
        COLOR_CBG1 = new Color(120, 82, 37);
        COLOR_CBG2 = new Color(83, 47, 7);
        COLOR_CBG3 = new Color(123, 105, 84);
        COLOR_CBG4 = new Color(255, 246, 219);
        COLOR_CBG5 = new Color(249, 240, 215);
        (UIUtils.COLOR_RED_BTNTEXT = new Color[3])[0] = new Color(16, 24, 2);
        UIUtils.COLOR_RED_BTNTEXT[2] = (UIUtils.COLOR_RED_BTNTEXT[1] = new Color(48, 88, 56));
        UIUtils.COLOR_BTNTEXT = new Color[3];
        (UIUtils.COLOR_BTNPUTONGS = new Color[3])[0] = new Color(16, 24, 2);
        UIUtils.COLOR_BTNPUTONGS[2] = (UIUtils.COLOR_BTNPUTONGS[1] = new Color(48, 88, 56));
        Color[] color_BTNTEXT = UIUtils.COLOR_BTNTEXT;
        int n = 0;
        Color[] color_BTNTEXT2 = UIUtils.COLOR_BTNTEXT;
        int n2 = 2;
        Color[] color_BTNTEXT3 = UIUtils.COLOR_BTNTEXT;
        int n3 = 1;
        Color color = new Color(255, 255, 255);
        color_BTNTEXT3[n3] = color;
        color_BTNTEXT[n] = (color_BTNTEXT2[n2] = color);
        (UIUtils.COLOR_RED_BTNTEXT = new Color[3])[0] = new Color(187, 165, 75);
        UIUtils.COLOR_RED_BTNTEXT[1] = (UIUtils.COLOR_RED_BTNTEXT[2] = new Color(219, 229, 107));
        (UIUtils.COLOR_BTNPUTONG = new Color[3])[0] = new Color(198, 174, 79);
        UIUtils.COLOR_BTNPUTONG[2] = (UIUtils.COLOR_BTNPUTONG[1] = new Color(219, 229, 107));
        UIUtils.COLOR_BTNTEXT = new Color[3];
        Color[] color_BTNTEXT4 = UIUtils.COLOR_BTNTEXT;
        int n4 = 0;
        Color[] color_BTNTEXT5 = UIUtils.COLOR_BTNTEXT;
        int n5 = 2;
        Color[] color_BTNTEXT6 = UIUtils.COLOR_BTNTEXT;
        int n6 = 1;
        Color color2 = new Color(255, 255, 255);
        color_BTNTEXT6[n6] = color2;
        color_BTNTEXT4[n4] = (color_BTNTEXT5[n5] = color2);
        (UIUtils.COLOR_BTNXUANXIANGKA = new Color[3])[0] = new Color(23, 18, 22);
        UIUtils.COLOR_BTNXUANXIANGKA[2] = (UIUtils.COLOR_BTNXUANXIANGKA[1] = new Color(40, 68, 40));
        UIUtils.COLOR_YELLOW = new Color[3];
        Color[] color_YELLOW = UIUtils.COLOR_YELLOW;
        int n7 = 0;
        Color[] color_YELLOW2 = UIUtils.COLOR_YELLOW;
        int n8 = 1;
        Color[] color_YELLOW3 = UIUtils.COLOR_YELLOW;
        int n9 = 2;
        Color yellow = Color.YELLOW;
        color_YELLOW3[n9] = yellow;
        color_YELLOW[n7] = (color_YELLOW2[n8] = yellow);
        UIUtils.COLOR_HUI = new Color[3];
        Color[] color_HUI = UIUtils.COLOR_HUI;
        int n10 = 0;
        Color[] color_HUI2 = UIUtils.COLOR_HUI;
        int n11 = 1;
        Color[] color_HUI3 = UIUtils.COLOR_HUI;
        int n12 = 2;
        Color color3 = new Color(120, 82, 37);
        color_HUI3[n12] = color3;
        color_HUI[n10] = (color_HUI2[n11] = color3);
        UIUtils.COLOR_BLACK = new Color[3];
        Color[] color_BLACK = UIUtils.COLOR_BLACK;
        int n13 = 0;
        Color[] color_BLACK2 = UIUtils.COLOR_BLACK;
        int n14 = 1;
        Color[] color_BLACK3 = UIUtils.COLOR_BLACK;
        int n15 = 2;
        Color black = Color.BLACK;
        color_BLACK3[n15] = black;
        color_BLACK[n13] = (color_BLACK2[n14] = black);
        UIUtils.COLOR_WHITE = new Color[3];
        Color[] color_WHITE = UIUtils.COLOR_WHITE;
        int n16 = 0;
        Color[] color_WHITE2 = UIUtils.COLOR_WHITE;
        int n17 = 1;
        Color[] color_WHITE3 = UIUtils.COLOR_WHITE;
        int n18 = 2;
        Color white = Color.white;
        color_WHITE3[n18] = white;
        color_WHITE[n16] = (color_WHITE2[n17] = white);
        UIUtils.COLOR_WHITE1 = new Color[3];
        Color[] color_WHITE4 = UIUtils.COLOR_WHITE1;
        int n19 = 0;
        Color[] color_WHITE5 = UIUtils.COLOR_WHITE1;
        int n20 = 1;
        Color[] color_WHITE6 = UIUtils.COLOR_WHITE1;
        int n21 = 2;
        Color color4 = new Color(236, 236, 236);
        color_WHITE6[n21] = color4;
        color_WHITE4[n19] = (color_WHITE5[n20] = color4);
        UIUtils.COLOR_HONG = new Color[3];
        Color[] color_HONG = UIUtils.COLOR_HONG;
        int n22 = 0;
        Color[] color_HONG2 = UIUtils.COLOR_HONG;
        int n23 = 1;
        Color[] color_HONG3 = UIUtils.COLOR_HONG;
        int n24 = 2;
        Color color5 = new Color(255, 0, 0);
        color_HONG3[n24] = color5;
        color_HONG[n22] = (color_HONG2[n23] = color5);
        (UIUtils.COLOR_BTNPUTONG2 = new Color[3])[0] = new Color(187, 165, 75);
        UIUtils.COLOR_BTNPUTONG2[2] = (UIUtils.COLOR_BTNPUTONG2[1] = new Color(219, 229, 107));
        UIUtils.COLOR_WHITE3 = new Color[3];
        Color[] color_WHITE7 = UIUtils.COLOR_WHITE3;
        int n25 = 0;
        Color[] color_WHITE8 = UIUtils.COLOR_WHITE3;
        int n26 = 1;
        Color[] color_WHITE9 = UIUtils.COLOR_WHITE3;
        int n27 = 2;
        Color color6 = new Color(150, 129, 59);
        color_WHITE9[n27] = color6;
        color_WHITE7[n25] = (color_WHITE8[n26] = color6);
        UIUtils.COLOR_Pack1 = new Color[3];
        Color[] color_Pack1 = UIUtils.COLOR_Pack1;
        int n28 = 0;
        Color[] color_Pack2 = UIUtils.COLOR_Pack1;
        int n29 = 1;
        Color[] color_Pack3 = UIUtils.COLOR_Pack1;
        int n30 = 2;
        Color color7 = new Color(79, 79, 79);
        color_Pack3[n30] = color7;
        color_Pack1[n28] = (color_Pack2[n29] = color7);
        (UIUtils.COLOR_WHITE2 = new Color[3])[0] = new Color(16, 24, 24);
        UIUtils.COLOR_WHITE2[1] = new Color(48, 88, 56);
        UIUtils.COLOR_WHITE2[2] = new Color(16, 24, 24);
        (UIUtils.COLOR_WHITE5 = new Color[3])[0] = Color.red;
        UIUtils.COLOR_WHITE5[1] = new Color(48, 88, 56);
        UIUtils.COLOR_WHITE5[2] = Color.red;
        font = FontUntil.loadFont("font/FZ26.TTF");
        UIUtils.TEXT_shuimo2 = font.deriveFont(1, 24.0f);
        font = FontUntil.loadFont("font/HYC1GJM.ttf");
        Font font2 = FontUntil.loadFont("font/simli.ttf");
        UIUtils.TXT_lishud = font.deriveFont(0, 16.0f);
        UIUtils.TXT_lianss = font2.deriveFont(0, 13.0f);
        Font font3 = FontUntil.loadFont("font/汉仪中楷简.ttf");
        UIUtils.TXT_hyzjt = font3.deriveFont(0, 24.0f);
        UIUtils.TXT_hyzjt18 = font3.deriveFont(0, 18.0f);
        UIUtils.TXT_hyzjt16 = font3.deriveFont(0, 16.0f);
        TEXT_NAME_NPC_COLOR = new Color(255, 255, 0);
        COLOR_NAME_HIGHLIGHT = Color.RED;
        UIUtils.colors = new HashMap<>();
        (danxin = new Color[3])[0] = new Color(222, 181, 165);
        danxin[2] = (danxin[1] = new Color(245, 230, 184));
    }

    public static final Font TEXT_KT_13 = new Font("楷体", Font.PLAIN, 13);
}
