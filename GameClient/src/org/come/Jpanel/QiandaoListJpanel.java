package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Calendar;
import org.come.entity.Goodstable;
import org.apache.commons.collections.CollectionUtils;
import org.come.mouslisten.QiandaoGoodsMouselistener;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.come.bean.QianDao;
import java.util.Map;
import org.come.until.UserMessUntil;
import org.come.until.MyDayNLJQ;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.tcp.SpriteFactory;
import org.come.until.CutButtonImage;
import java.util.HashSet;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import com.tool.tcpimg.RichLabel;
import java.util.Set;
import com.tool.btn.QianDaoBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QiandaoListJpanel extends JPanel
{
    private JLabel labtext1;
    private JLabel labtext2;
    private JLabel labtext3;
    private JLabel labtext4;
    private JLabel labtext5;
    private JLabel[] labtext6;
    private JLabel[] fullDayGoods;
    private JLabel[] labelList;
    private QianDaoBtn qianDaobtn;
    private Integer maxDay;
    private int day;
    private Set<Integer> days;
    private Set<Integer> lqs;
    private int[] s;
    private int[] sy;
    private RichLabel richLabel;
    private ImageIcon icon;
    private ImageIcon qiandaoed2;
    private ImageIcon qiandaoed;
    NewPart part;
    
    public QiandaoListJpanel() {
        this.fullDayGoods = new JLabel[3];
        this.labelList = new JLabel[25];
        this.maxDay = Integer.valueOf(getCurrentMonthDay());
        this.day = 0;
        this.days = new HashSet<>();
        this.lqs = new HashSet<>();
        this.s = new int[] { 70, 169, 265, 361, 457 };
        this.sy = new int[] { 3, 7, 15, 20, 28 };
        this.icon = CutButtonImage.getImage("inkImg/background/qds.png", 640, 400);
        this.qiandaoed2 = CutButtonImage.getImage("img/xy2uiimg/21_png.xy2uiimg.atips.png", 20, 20);
        this.qiandaoed = new ImageIcon("img/xy2uiimg/打勾.png");
        this.part = SpriteFactory.createPart("400523", 2, 1, null);
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button1/cjlbgb.png", 1, 1100);
        offBtn.setBounds(635, 0, 25, 25);
        this.add(offBtn);
        Font font = new Font("宋体", 0, 18);
        (this.labtext3 = new JLabel()).setForeground(Color.YELLOW);
        this.labtext3.setFont(font);
        this.labtext3.setBounds(383, 25, 250, 50);
        this.labtext3.setText("累计签到:");
        this.add(this.labtext3);
        (this.labtext4 = new JLabel()).setForeground(Color.YELLOW);
        this.labtext4.setFont(font);
        this.labtext4.setBounds(487, 25, 250, 50);
        this.labtext4.setText("天");
        this.add(this.labtext4);
        (this.richLabel = new RichLabel()).setBounds(170, 100, 160, 500);
        this.richLabel.addText("   签到赢取大奖#r每日日常活跃度达到     200即可签到!#r 轻松领取海量物资#r累计签到更有机会赢   取神兽、稀有物资！", UIUtils.TEXT_NAME_FONT);
        this.add(this.richLabel);
        String[] nljq = MyDayNLJQ.getMyDaynljq();
        String pu = "";
        if (nljq != null && nljq.length > 0) {
            for (String s : nljq) {
                pu = pu + s + " ";
            }
        }
        font = new Font("宋体", 0, 14);
        (this.labtext5 = new JLabel()).setForeground(Color.green);
        this.labtext5.setFont(font);
        if (nljq.length == 3) {
            this.labtext5.setBounds(387, 73, 250, 50);
        }
        else if (nljq.length == 4) {
            this.labtext5.setBounds(373, 73, 250, 50);
        }
        else if (nljq.length == 5) {
            font = new Font("宋体", 0, 12);
            this.labtext5.setFont(font);
            this.labtext5.setBounds(355, 73, 250, 50);
        }
        this.labtext5.setText(pu);
        this.add(this.labtext5);
        font = new Font("宋体", 0, 20);
        (this.labtext1 = new JLabel()).setForeground(Color.RED);
        this.labtext1.setFont(font);
        this.labtext1.setBounds(471, 50, 20, 20);
        this.labtext1.setText(this.days.size() + "");
        this.add(this.labtext1);
        (this.qianDaobtn = new QianDaoBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "签到", this)).setBounds(530, 35, 72, 32);
        this.add(this.qianDaobtn);
        int x = 0;
        int y = 0;
        for (int i = 1; i <= (int)this.maxDay; ++i) {
            JLabel temp = new JLabel();
            ++x;
            if (i % 8 == 0) {
                x = 0;
                ++y;
            }
            temp.setForeground(Color.YELLOW);
            temp.setFont(font);
            temp.setBounds(365 + x * 30, 120 + y * 30, 20, 20);
            temp.setText(i + "");
            this.add(temp);
        }
        Font gf = new Font("宋体", 10, 20);
        int left = 70;
        List<QianDao> qiandaoBean = UserMessUntil.getQiandaoBean();
        Map<Integer, List<QianDao>> collect = (Map)qiandaoBean.stream().collect(Collectors.groupingBy(QianDao::getDay));
        if (collect == null) {
            return;
        }
        this.s = new int[] { 70, 178, 280, 383, 492 };
        for (int j = 0; j < 5; ++j) {
            y = -1;
            left = this.s[j];
            List<QianDao> qianDaos = (List<QianDao>)collect.get(Integer.valueOf(this.sy[j]));
            if (qianDaos != null) {
                QianDao qianDao = (QianDao)qianDaos.get(0);
                String goods = qianDao.getGoods();
                String[] split = goods.split("=")[1].split("&");
                for (int k = 0; k < 5; ++k) {
                    if (k % 2 == 0) {
                        x = 0;
                        ++y;
                    }
                    if (k < split.length) {
                        String[] s2 = split[k].split("\\$");
                        Goodstable getgoodstable = UserMessUntil.getgoodstable(BigDecimal.valueOf(Long.parseLong(s2[0])));
                        if (getgoodstable != null) {
                            JLabel temp2 = new JLabel();
                            temp2.setForeground(Color.white);
                            temp2.setBackground(Color.BLACK);
                            temp2.setFont(gf);
                            temp2.setBounds(left - 20 + x * 42, 275 + y * 42, 40, 40);
                            temp2.setIcon(GoodsListFromServerUntil.imgpathAdaptive(getgoodstable.getSkin(), 31, 31));
                            temp2.addMouseListener(new QiandaoGoodsMouselistener(getgoodstable));
                            this.add(temp2);
                        }
                    }
                    ++x;
                }
            }
        }
        Map<Integer, List<QianDao>> collect2 = (Map)qiandaoBean.stream().collect(Collectors.groupingBy(QianDao::getDay));
        if (collect2 == null) {
            return;
        }
        font = new Font("宋体", 0, 12);
        this.labtext6 = new JLabel[5];
        for (int l = 0; l < 5; ++l) {
            y = -1;
            left = this.s[l];
            List<QianDao> qianDaos = (List<QianDao>)collect2.get(Integer.valueOf(this.sy[l]));
            if (qianDaos != null) {
                QianDao qianDao = (QianDao)qianDaos.get(0);
                String goods = qianDao.getDay().toString();
                (this.labtext6[l] = new JLabel()).setForeground(Color.GREEN);
                this.labtext6[l].setFont(font);
                this.labtext6[l].setBounds(45 + l * 105 + 10, 250, 85, 32);
                this.labtext6[l].setText("满签" + goods + "天领取");
                this.add(this.labtext6[l]);
            }
        }
        for (int m = 0; m < 5; ++m) {
            QianDaoBtn temp3 = new QianDaoBtn("inkImg/button1/aj1.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "领取", this.sy[m], this);
            temp3.setBounds(45 + m * 108 + 10, 405, 72, 32);
            this.add(temp3);
        }
        List<QianDao> qianDaos = (List<QianDao>)collect.get(Integer.valueOf(32));
        if (CollectionUtils.isNotEmpty(qianDaos)) {
            QianDao qianDao = (QianDao)qianDaos.get(0);
            String[] split2 = qianDao.getGoods().split("=")[1].split("&");
            for (int i2 = 0; i2 < split2.length; ++i2) {
                String s3 = split2[i2].split("\\$")[0];
                Goodstable getgoodstable2 = UserMessUntil.getgoodstable(BigDecimal.valueOf(Long.parseLong(s3)));
                if (getgoodstable2 != null) {
                    JLabel jl = new JLabel();
                    jl.setForeground(Color.BLACK);
                    jl.setBackground(Color.BLACK);
                    jl.setFont(gf);
                    jl.setBounds(100 + i2 * 110, 180, 60, 60);
                    jl.setIcon(GoodsListFromServerUntil.imgpathAdaptive(getgoodstable2.getSkin(), 50, 50));
                    jl.addMouseListener(new QiandaoGoodsMouselistener(getgoodstable2));
                    this.add(jl);
                }
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
        g.drawImage(this.icon.getImage(), 0, 0, 659, 472, this);
        this.labtext1.setBounds(471, 40, 20, 20);
        this.labtext1.setText(this.days.size() + "");
        this.part.draw(g, 100, 200, 8, System.currentTimeMillis());
    }
    
    public int getDay() {
        return this.day;
    }
    
    public void setDay(int day) {
        this.day = day;
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
}
