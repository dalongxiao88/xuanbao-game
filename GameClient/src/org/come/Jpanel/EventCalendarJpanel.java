package org.come.Jpanel;

import java.util.Iterator;
import java.awt.Graphics;
import org.come.bean.ConfigureBean;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.CutButtonImage;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventCalendarJpanel extends JPanel
{
    private JLabel labtext1;
    private JLabel labtext2;
    private JLabel labtext4;
    private JLabel labtext5;
    private JLabel labtext6;
    public JLabel labtext7;
    public JLabel labtext8;
    public JLabel labtext9;
    private Integer maxDay;
    private int day;
    private Set<Integer> days;
    private Set<Integer> lqs;
    private int[] s;
    private int[] sy;
    private String[] str1;
    private Map<String, String> map;
    private String[] task1;
    private String[] task2;
    private Map<String, Object> taskMap;
    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon icon3;
    private ImageIcon today;
    private ImageIcon week;
    private ImageIcon icon2;
    private ImageIcon icon4;
    private ImageIcon icon5;
    private ImageIcon icon6;
    
    public EventCalendarJpanel() {
        this.maxDay = Integer.valueOf(getCurrentMonthDay());
        this.day = 0;
        this.days = new HashSet<>();
        this.lqs = new HashSet<>();
        this.s = new int[] { 70, 169, 265, 361, 457 };
        this.sy = new int[] { 3, 7, 15, 20, 28 };
        this.str1 = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
        this.map = new HashMap<>();
        this.task1 = new String[0];
        this.task2 = new String[0];
        this.taskMap = new HashMap<>();
        this.icon = CutButtonImage.getImage("img/xy2uiimg/活动日历.png", 522, 520);
        this.icon1 = CutButtonImage.getImage("inkImg/background/日历底图1.png", 474, 319);
        this.icon3 = CutButtonImage.getImage("inkImg/background/格子.png", 67, 52);
        this.today = new ImageIcon("inkImg/background/今日框.png");
        this.week = CutButtonImage.getImage("inkImg/background/week.png", 67, 26);
        this.icon2 = CutButtonImage.getImage("inkImg/background/边框底图.png", 467, 337);
        this.icon4 = CutButtonImage.getImage("inkImg/background/年月.png", 188, 35);
        this.icon5 = CutButtonImage.getImage("inkImg/background/左.png", 33, 35);
        this.icon6 = CutButtonImage.getImage("inkImg/background/右.png", 33, 35);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure1 = (Configure)item.get(new BigDecimal(2));
        Configure configure2 = (Configure)item.get(new BigDecimal(3));
        Configure configure3 = (Configure)item.get(new BigDecimal(4));
        if (configure1.getZqsld() != null) {
            this.task1 = configure1.getZqsld().split(",");
        }
        if (configure2.getZqsld() != null) {
            this.task2 = configure2.getZqsld().split(",");
        }
        if (configure3.getZqsld() != null) {
            String[] task3;
            for (String s : task3 = configure3.getZqsld().split(",")) {
                String[] stak = s.split("\\|");
                this.taskMap.put(stak[0], stak[1]);
            }
        }
        this.setPreferredSize(new Dimension(522, 520));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1104);
            offBtn.setBounds(497, 0, 25, 25);
            this.add(offBtn);
            Font font = new Font("宋体", 0, 14);
            (this.labtext2 = new JLabel()).setForeground(Color.BLACK);
            this.labtext2.setFont(font);
            this.add(this.labtext2);
            (this.labtext1 = new JLabel()).setForeground(Color.BLACK);
            this.labtext1.setFont(font);
            this.add(this.labtext1);
            (this.labtext6 = new JLabel()).setForeground(Color.green);
            this.labtext6.setFont(font);
            this.add(this.labtext6);
            font = new Font("宋体", 0, 14);
            (this.labtext7 = new JLabel()).setBounds(58, 405, 500, 51);
            this.labtext7.setForeground(Color.green);
            this.labtext7.setFont(font);
            this.add(this.labtext7);
            (this.labtext8 = new JLabel()).setBounds(58, 425, 500, 51);
            this.labtext8.setForeground(Color.green);
            this.labtext8.setFont(font);
            this.add(this.labtext8);
            (this.labtext9 = new JLabel()).setBounds(58, 445, 500, 51);
            this.labtext9.setForeground(Color.green);
            this.labtext9.setFont(font);
            this.add(this.labtext9);
            for (int x = 0; x < 7; ++x) {
                (this.labtext4 = new JLabel()).setBounds(75 + x * 60, 83, 60, 51);
                this.labtext4.setText(this.str1[x] + "");
                this.add(this.labtext4);
            }
            Calendar now = Calendar.getInstance();
            int year = now.get(1);
            int month = now.get(2) + 1;
            font = new Font("小篆", 1, 24);
            (this.labtext5 = new JLabel()).setFont(UIUtils.TEXT_FONTZSRL);
            this.labtext5.setBounds(207, 48, 350, 50);
            this.labtext5.setText(year + "年" + month + "月");
            this.add(this.labtext5);
            font = new Font("楷体", 1, 20);
            this.day = now.get(5);
            now.set(year, month - 1, 0);
            int x2 = now.get(7) - 2;
            int y = 0;
            int dd = now.get(7) - 1;
            int j = 0;
            int ls = 0;
            for (int i = 1; i <= 7 - dd; ++i) {
                ls = i;
                JLabel temp = new JLabel();
                if (++x2 < 7) {
                    temp.setForeground(UIUtils.COLOR_YMRCode);
                    temp.setFont(font);
                    temp.setBounds(60 + x2 * 60, 41 + y * 52, 380, 180);
                    temp.setText(i + "");
                    this.getstak(i, x2, y);
                }
                else {
                    temp.setForeground(Color.black);
                    temp.setFont(font);
                    temp.setBounds(60 + x2 * 60, 41 + y * 52, 380, 180);
                    temp.setText(i + "");
                    this.getstak(i, x2, y);
                }
                ls = i;
                this.add(temp);
            }
            x2 = 0;
            y = 0;
            int sq = 0;
            for (int k = ls + 1; k <= (int)this.maxDay; ++k) {
                JLabel temp2 = new JLabel();
                if (sq % 7 == 0) {
                    x2 = 0;
                    ++y;
                }
                if (y != 0) {
                    if (x2 > 1) {
                        temp2.setForeground(UIUtils.COLOR_YMRCode);
                        temp2.setFont(font);
                        temp2.setBounds(60 + x2 * 60, 41 + y * 52, 380, 180);
                        temp2.setText(k + "");
                        this.getstak(k, x2, y);
                    }
                    else {
                        temp2.setForeground(Color.BLACK);
                        temp2.setFont(font);
                        temp2.setBounds(60 + x2 * 60, 41 + y * 52, 380, 180);
                        temp2.setText(k + "");
                        this.getstak(k, x2, y);
                    }
                }
                this.add(temp2);
                ++sq;
                ++x2;
            }
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 1104);
            offBtn.setBounds(497, 0, 25, 25);
            this.add(offBtn);
            Font font = new Font("宋体", 0, 14);
            (this.labtext2 = new JLabel()).setForeground(Color.BLACK);
            this.labtext2.setFont(font);
            this.add(this.labtext2);
            (this.labtext1 = new JLabel()).setForeground(Color.BLACK);
            this.labtext1.setFont(font);
            this.add(this.labtext1);
            (this.labtext6 = new JLabel()).setForeground(Color.green);
            this.labtext6.setFont(font);
            this.add(this.labtext6);
            font = new Font("宋体", 0, 14);
            (this.labtext7 = new JLabel()).setBounds(45, 410, 500, 51);
            this.labtext7.setForeground(Color.green);
            this.labtext7.setFont(font);
            this.add(this.labtext7);
            (this.labtext8 = new JLabel()).setBounds(45, 430, 500, 51);
            this.labtext8.setForeground(Color.green);
            this.labtext8.setFont(font);
            this.add(this.labtext8);
            (this.labtext9 = new JLabel()).setBounds(45, 450, 500, 51);
            this.labtext9.setForeground(Color.green);
            this.labtext9.setFont(font);
            this.add(this.labtext9);
            for (int x = 0; x < 7; ++x) {
                (this.labtext4 = new JLabel()).setBounds(65 + x * 60, 83, 60, 51);
                this.labtext4.setText(this.str1[x] + "");
                this.add(this.labtext4);
            }
            Calendar now = Calendar.getInstance();
            int year = now.get(1);
            int month = now.get(2) + 1;
            font = new Font("小篆", 1, 24);
            (this.labtext5 = new JLabel()).setFont(UIUtils.TEXT_FONTZSRL);
            this.labtext5.setBounds(207, 48, 350, 50);
            this.labtext5.setText(year + "年" + month + "月");
            this.add(this.labtext5);
            font = new Font("楷体", 1, 20);
            this.day = now.get(5);
            now.set(year, month - 1, 0);
            int x2 = now.get(7) - 2;
            int y = 0;
            int dd = now.get(7) - 1;
            int j = 0;
            int ls = 0;
            for (int i = 1; i <= 7 - dd; ++i) {
                ls = i;
                JLabel temp = new JLabel();
                if (++x2 > 4 && x2 < 7) {
                    temp.setForeground(UIUtils.COLOR_YMRCode);
                    temp.setFont(font);
                    temp.setBounds(50 + x2 * 60, 41 + y * 52, 380, 180);
                    temp.setText(i + "");
                    this.getstak(i, x2, y);
                }
                else {
                    temp.setForeground(Color.black);
                    temp.setFont(font);
                    temp.setBounds(50 + x2 * 60, 41 + y * 52, 380, 180);
                    temp.setText(i + "");
                    this.getstak(i, x2, y);
                }
                ls = i;
                this.add(temp);
            }
            x2 = 0;
            y = 0;
            int sq = 0;
            for (int k = ls + 1; k <= (int)this.maxDay; ++k) {
                JLabel temp2 = new JLabel();
                if (sq % 7 == 0) {
                    x2 = 0;
                    ++y;
                }
                if (y != 0) {
                    if (x2 > 4) {
                        temp2.setForeground(UIUtils.COLOR_YMRCode);
                        temp2.setFont(font);
                        temp2.setBounds(50 + x2 * 60, 41 + y * 52, 380, 180);
                        temp2.setText(k + "");
                        this.getstak(k, x2, y);
                    }
                    else {
                        temp2.setForeground(Color.BLACK);
                        temp2.setFont(font);
                        temp2.setBounds(50 + x2 * 60, 41 + y * 52, 380, 180);
                        temp2.setText(k + "");
                        this.getstak(k, x2, y);
                    }
                }
                this.add(temp2);
                ++sq;
                ++x2;
            }
        }
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
            this.icon = CutButtonImage.getImage("inkImg/background/活动日历.png", 522, 520);
            Calendar now = Calendar.getInstance();
            int year = now.get(1);
            int month = now.get(2) + 1;
            this.day = now.get(5);
            now.set(year, month - 1, 0);
            int dd = now.get(7) - 1;
            g.drawImage(this.icon.getImage(), 0, 0, 522, 520, this);
            g.drawImage(this.icon1.getImage(), 40, 50, 454, 349, this);
            g.drawImage(this.icon2.getImage(), 55, 95, 425, 290, this);
            g.drawImage(this.icon4.getImage(), 175, 55, 188, 35, this);
            g.drawImage(this.icon5.getImage(), 140, 55, 33, 35, this);
            g.drawImage(this.icon6.getImage(), 365, 55, 33, 35, this);
            for (int x = 0; x < 7; ++x) {
                g.drawImage(this.week.getImage(), 57 + x * 60, 95, 60, 26, this);
            }
            int x = dd;
            int y = 0;
            g.drawImage(this.icon3.getImage(), 57, 120, 60, 52, this);
            for (int i = 1; i <= (int)this.maxDay + 1; ++i) {
                ++x;
                if (i % 7 == 0) {
                    x = 0;
                    ++y;
                }
                if (y == 0) {
                    if (x < 7) {
                        g.drawImage(this.icon3.getImage(), 57 + x * 60, 120 + y * 52, 60, 51, this);
                    }
                }
                else {
                    g.drawImage(this.icon3.getImage(), 57 + x * 60, 120 + y * 52, 60, 51, this);
                }
                if (this.day == i - 1) {
                    g.drawImage(this.today.getImage(), 57 + x * 60, 120 + y * 52, 60, 51, this);
                }
            }
        }
        else {
            this.icon = CutButtonImage.getImage("img/xy2uiimg/活动日历.png", 522, 520);
            Calendar now = Calendar.getInstance();
            int year = now.get(1);
            int month = now.get(2) + 1;
            this.day = now.get(5);
            now.set(year, month - 1, 0);
            int dd = now.get(7) - 1;
            g.drawImage(this.icon.getImage(), 0, 0, 522, 520, this);
            g.drawImage(this.icon1.getImage(), 30, 50, 454, 349, this);
            g.drawImage(this.icon2.getImage(), 45, 95, 425, 290, this);
            g.drawImage(this.icon4.getImage(), 165, 55, 188, 35, this);
            g.drawImage(this.icon5.getImage(), 130, 55, 33, 35, this);
            g.drawImage(this.icon6.getImage(), 355, 55, 33, 35, this);
            for (int x = 0; x < 7; ++x) {
                g.drawImage(this.week.getImage(), 47 + x * 60, 95, 60, 26, this);
            }
            int x = dd - 2;
            int y = 0;
            g.drawImage(this.icon3.getImage(), 47, 120, 60, 52, this);
            for (int i = 1; i <= (int)this.maxDay + 1; ++i) {
                ++x;
                if (i % 7 == 0) {
                    x = 0;
                    ++y;
                }
                if (y == 0) {
                    if (x < 7) {
                        g.drawImage(this.icon3.getImage(), 47 + x * 60, 120 + y * 52, 60, 52, this);
                    }
                }
                else {
                    g.drawImage(this.icon3.getImage(), 47 + x * 60, 120 + y * 52, 60, 52, this);
                }
                if (this.day == i - 1) {
                    g.drawImage(this.today.getImage(), 47 + x * 60, 120 + y * 52, 60, 53, this);
                }
            }
        }
    }
    
    public void getstak(int i, int x, int y) {
        if (MyIsif.getStyle().equals("水墨")) {
            Calendar now1 = Calendar.getInstance();
            int year1 = now1.get(1);
            int month1 = now1.get(2) + 1;
            now1.set(year1, month1 - 1, i - 1);
            int tayweek = now1.get(7);
            Font font = new Font("宋体", 0, 11);
            this.labtext1.setFont(font);
            this.labtext2.setFont(font);
            this.labtext6.setForeground(UIUtils.COLOR_YMRCode);
            this.labtext6.setFont(font);
            this.map.put(i + "", 57 + x * 60 + "," + (120 + y * 52) + "," + 60 + "," + 52);
            if (this.taskMap != null) {
                for (String key : this.taskMap.keySet()) {
                    if (key.equals(i + "") && this.taskMap.get(i + "") != null) {
                        (this.labtext6 = new JLabel()).setBounds(58 + x * 60, 118 + y * 52, 60, 51);
                        this.labtext6.setText(this.taskMap.get(i + "").toString());
                        this.map.put(i + "三", this.taskMap.get(i + "").toString());
                        this.add(this.labtext6);
                    }
                }
            }
            this.map.put(i + "一", this.task1[tayweek - 1].toString());
            this.map.put(i + "二", this.task2[tayweek - 1].toString());
            if (tayweek == 1) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 2) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 3) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 4) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                this.labtext2 = new JLabel();
                font = new Font("宋体", 0, 11);
                this.labtext2.setFont(font);
                this.labtext2.setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 5) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 6) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 7) {
                (this.labtext1 = new JLabel()).setBounds(58 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(58 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
        }
        else {
            Calendar now1 = Calendar.getInstance();
            int year1 = now1.get(1);
            int month1 = now1.get(2) + 1;
            now1.set(year1, month1 - 1, i - 1);
            int tayweek = now1.get(7);
            Font font = new Font("宋体", 0, 11);
            this.labtext1.setFont(font);
            this.labtext2.setFont(font);
            this.labtext6.setForeground(UIUtils.COLOR_YMRCode);
            this.labtext6.setFont(font);
            this.map.put(i + "", 47 + x * 60 + "," + (120 + y * 52) + "," + 60 + "," + 52);
            if (this.taskMap != null) {
                for (String key : this.taskMap.keySet()) {
                    if (key.equals(i + "") && this.taskMap.get(i + "") != null) {
                        (this.labtext6 = new JLabel()).setBounds(48 + x * 60, 118 + y * 52, 60, 51);
                        this.labtext6.setText(this.taskMap.get(i + "").toString());
                        this.map.put(i + "三", this.taskMap.get(i + "").toString());
                        this.add(this.labtext6);
                    }
                }
            }
            this.map.put(i + "一", this.task1[tayweek - 1].toString());
            this.map.put(i + "二", this.task2[tayweek - 1].toString());
            if (tayweek == 1) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 2) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 3) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 4) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                this.labtext2 = new JLabel();
                font = new Font("宋体", 0, 11);
                this.labtext2.setFont(font);
                this.labtext2.setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 5) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 6) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
            if (tayweek == 7) {
                (this.labtext1 = new JLabel()).setBounds(48 + x * 60, 130 + y * 52, 60, 51);
                if (this.task1[tayweek - 1].length() > 0) {
                    this.labtext1.setText(this.task1[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext1);
                (this.labtext2 = new JLabel()).setBounds(48 + x * 60, 141 + y * 52, 60, 51);
                if (this.task2[tayweek - 1].length() > 0) {
                    this.labtext2.setText(this.task2[tayweek - 1].split("\\|")[0]);
                }
                this.add(this.labtext2);
            }
        }
    }
    
    public Set<Integer> getDays() {
        return this.days;
    }
    
    public void setDays(Set<Integer> days) {
        this.days = days;
    }
    
    public Set<Integer> getLqs() {
        return this.lqs;
    }
    
    public void setLqs(Set<Integer> lqs) {
        this.lqs = lqs;
    }
    
    public Map<String, String> getMap() {
        return this.map;
    }
    
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
