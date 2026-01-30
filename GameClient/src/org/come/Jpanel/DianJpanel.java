package org.come.Jpanel;

import org.come.until.FormsManagement;
import java.util.Iterator;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.MsgJframe;
import org.come.until.UserMessUntil;
import java.awt.Color;
import java.awt.Graphics;
import org.come.mouslisten.WLLMouslisten;
import org.come.Frame.NewRefiningJframe;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import org.come.mouslisten.RefinersMouslisten;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import com.tool.btn.goodbtn;
import com.tool.btn.RefineOperBtn;
import javax.swing.JLabel;
import java.util.List;
import org.come.bean.QualityClBean;
import javax.swing.JPanel;

public class DianJpanel extends JPanel
{
    public double bili;
    public QualityClBean clBean;
    public List<String> shuxing;
    public List<Double> shuzhi;
    String[] mes1;
    private JLabel[] labGoods;
    private RefineOperBtn workshopBtn;
    public JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    public BigDecimal money;
    public Goodstable[] goods;
    private final RefineOperBtn operBtn3;
    ImageIcon icon;
    ImageIcon icon1;
    ImageIcon icon2;
    private boolean p;
    private boolean zuo;
    private boolean you;
    static Random rand;
    public static int teji1;
    public static int teji2;
    public JLabel[] teji;
    
    public DianJpanel() {
        this.bili = 0.2;
        this.shuxing = new ArrayList<>();
        this.shuzhi = new ArrayList<>();
        this.mes1 = new String[] { "加强混乱", "加强封印", "加强昏睡", "加强雷", "加强火", "加强鬼火", "强力克金", "加强克土", "加强克火", "加强克水", "加强克木", "加强风" };
        this.labGoods = new JLabel[6];
        this.GoodsListLabel = new JLabel[24];
        this.money = new BigDecimal(100000);
        this.goods = new Goodstable[6];
        this.icon1 = new ImageIcon("inkImg/background/S67.png");
        this.icon2 = new ImageIcon("inkImg/background/S154.png");
        this.p = true;
        this.zuo = false;
        this.you = false;
        this.teji = new JLabel[2];
        this.setPreferredSize(new Dimension(470, 315));
        this.setLayout(null);
        this.setOpaque(false);
        for (int i = 0; i < 6; ++i) {
            int row = i % 3;
            int col = i / 3;
            (this.labGoods[i] = new JLabel()).addMouseListener(new RefinersMouslisten(this, 24 + i));
            this.labGoods[i].setBounds(250 + row * 67, 79 + col * 65, 54, 51);
            this.add(this.labGoods[i]);
        }
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 127);
        offBtn.setBounds(440, 10, 25, 25);
        this.add(offBtn);
        (this.operBtn3 = new RefineOperBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 2, "再次炼化")).setBounds(365, 29, 68, 17);
        this.add(this.operBtn3);
        RefineOperBtn operBtn1 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 0, "保 留");
        operBtn1.setBounds(80, 275, 99, 24);
        this.add(operBtn1);
        RefineOperBtn operBtn2 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 1, "替 换");
        operBtn2.setBounds(300, 275, 99, 24);
        this.add(operBtn2);
        for (int j = 0; j < 24; ++j) {
            int row2 = j % 6;
            int col2 = j / 6;
            (this.GoodsListLabel[j] = new JLabel()).addMouseListener(new RefinersMouslisten(this, j));
            this.GoodsListLabel[j].setBounds(218 + row2 * 51, 277 + col2 * 51, 49, 49);
            this.add(this.GoodsListLabel[j]);
        }
        this.btnrights = new goodbtn[6];
        for (int j = 0; j < this.btnrights.length; ++j) {
            (this.btnrights[j] = new goodbtn("inkImg/button1/C0" + (j + 1) + ".png", 0, this, j)).setBounds(524, 284 + j * 35, 39, 31);
            this.add(this.btnrights[j]);
        }
        (this.teji[0] = new JLabel()).setBounds(250, 200, 100, 28);
        this.teji[0].setVisible(false);
        this.teji[0].addMouseListener(new WLLMouslisten(100));
        this.teji[0].setFont(UIUtils.TEXT_FONT);
        this.add(this.teji[0]);
        (this.teji[1] = new JLabel()).setBounds(40, 200, 100, 28);
        this.teji[1].setVisible(false);
        this.teji[1].addMouseListener(new WLLMouslisten(101));
        this.teji[1].setFont(UIUtils.TEXT_FONT);
        this.add(this.teji[1]);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/点翠1.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 470, 315, this);
        g.setFont(UIUtils.nameFont);
        g.setColor(Color.white);
        g.drawString("原点粹属性", 55, 70);
        g.setColor(Color.green);
        g.drawString("新点粹属性", 265, 70);
        if (this.isP()) {
            g.setColor(Color.black);
            if (this.isZuo()) {
                g.drawString("总点粹值", 40, 135);
                g.drawString("2", 170, 135);
                g.drawImage(this.icon1.getImage(), 35, 140, 191, 28, this);
                g.drawImage(this.icon1.getImage(), 35, 170, 191, 28, this);
                g.setFont(UIUtils.TEXT_FONT2);
                g.setColor(Color.white);
                g.drawString((String)this.getShuxing().get(0), 45, 160);
                g.drawString(String.valueOf(this.getShuzhi().get(0)), 175, 158);
                g.drawString((String)this.getShuxing().get(1), 45, 190);
                g.drawString(String.valueOf(this.getShuzhi().get(1)), 175, 193);
                if (DianJpanel.teji2 != 0) {
                    g.setColor(Color.GREEN);
                    g.drawImage(this.icon1.getImage(), 35, 200, 191, 28, this);
                    g.drawString("特技", 175, 217);
                }
            }
            if (this.isYou()) {
                g.setFont(UIUtils.nameFont);
                g.setColor(Color.black);
                g.drawString("总点粹值", 250, 135);
                g.drawString("2", 400, 135);
                g.drawImage(this.icon1.getImage(), 245, 140, 191, 28, this);
                g.drawImage(this.icon1.getImage(), 245, 170, 191, 28, this);
                g.setFont(UIUtils.TEXT_FONT2);
                g.setColor(Color.white);
                g.drawString((String)this.getShuxing().get(2), 255, 160);
                g.drawString(String.valueOf(this.getShuzhi().get(2)), 385, 158);
                g.drawString((String)this.getShuxing().get(3), 255, 190);
                g.drawString(String.valueOf(this.getShuzhi().get(3)), 385, 190);
                if (DianJpanel.teji1 != 0) {
                    g.setColor(Color.GREEN);
                    g.drawImage(this.icon1.getImage(), 245, 200, 191, 28, this);
                    g.drawString("特技", 385, 217);
                }
            }
        }
    }
    
    public void ew(QualityClBean clBean) {
        this.setP(true);
        String[] mes1 = clBean.getNewAttr().split("&");
        this.shuxing.set(2, mes1[1].split("=")[0]);
        this.shuzhi.set(2, Double.valueOf(mes1[1].split("=")[1]));
        this.shuxing.set(3, mes1[2].split("=")[0]);
        this.shuzhi.set(3, Double.valueOf(mes1[2].split("=")[1]));
        this.setYou(true);
        this.clBean = clBean;
        if (mes1.length == 5) {
            DianJpanel.teji1 = Integer.parseInt(mes1[4].split("=")[1]);
            this.teji[0].setVisible(true);
            this.teji[0].setText(UserMessUntil.getSkillId(String.valueOf(DianJpanel.teji1)).getSkillname());
            this.teji[0].setForeground(Color.green);
            MsgJframe.getJframe().getJapnel().texiao("悟技");
        }
        else {
            DianJpanel.teji1 = 0;
            this.teji[0].setVisible(false);
        }
    }
    
    public void TH(QualityClBean clBean) {
        Goodstable good = GoodsListFromServerUntil.getRgid(clBean.getRgid());
        assert good != null;
        String[] value = good.getValue().split("\\|");
        List<String> value2 = new ArrayList<>();
        for (String s : value) {
            if (!s.startsWith("点粹属性") && !s.startsWith("炼化属性&特技") && !s.startsWith("点翠属性")) {
                value2.add(s);
            }
        }
        value2.removeIf(bb/* java.lang.String, */ -> bb.equals(""));
        StringBuilder mes = null;
        for (String vv : value2) {
            if (vv.startsWith("培养")) {
                assert mes != null;
                mes.append("|");
                mes.append(clBean.getNewAttr());
                mes.append("|");
            }
            if (mes == null) {
                mes = new StringBuilder(vv);
            }
            else {
                mes.append("|").append(vv);
            }
        }
        assert mes != null;
        good.setValue(mes.toString());
        this.shuxing.set(0, this.shuxing.get(2));
        this.shuxing.set(1, this.shuxing.get(3));
        this.shuzhi.set(0, this.shuzhi.get(2));
        this.shuzhi.set(1, this.shuzhi.get(3));
        this.clBean = null;
        this.setYou(false);
        this.setZuo(true);
        if (clBean.getNewAttr().split("&").length == 5) {
            DianJpanel.teji2 = Integer.parseInt(clBean.getNewAttr().split("&")[4].split("=")[1]);
            this.teji[1].setVisible(true);
            this.teji[1].setText(UserMessUntil.getSkillId(String.valueOf(DianJpanel.teji2)).getSkillname());
            this.teji[1].setForeground(Color.green);
            this.teji[0].setVisible(false);
        }
        else {
            DianJpanel.teji2 = 0;
            this.teji[1].setVisible(false);
        }
    }
    
    public static double dianchizhi() {
        return 1.0;
    }
    
    public double getBili() {
        return this.bili;
    }
    
    public void setBili(double bili) {
        this.bili = bili;
    }
    
    public List<String> getShuxing() {
        return this.shuxing;
    }
    
    public void setShuxing(List<String> shuxing) {
        this.shuxing = shuxing;
    }
    
    public List<Double> getShuzhi() {
        return this.shuzhi;
    }
    
    public void setShuzhi(List<Double> shuzhi) {
        this.shuzhi = shuzhi;
    }
    
    public void show(String value, int leixing, boolean is) {
        this.p = true;
        this.setZuo(false);
        this.setYou(false);
        this.shuzhi.clear();
        this.shuxing.clear();
        this.shuxing.add("1");
        this.shuzhi.add(Double.valueOf(1.0));
        this.shuxing.add("2");
        this.shuzhi.add(Double.valueOf(3.0));
        this.shuxing.add("1");
        this.shuzhi.add(Double.valueOf(1.0));
        this.shuxing.add("2");
        this.shuzhi.add(Double.valueOf(3.0));
        if (value != null) {
            String[] mes1 = value.split("&");
            this.shuxing.set(0, mes1[1].split("=")[0]);
            this.shuzhi.set(0, Double.valueOf(mes1[1].split("=")[1]));
            this.shuxing.set(1, mes1[2].split("=")[0]);
            this.shuzhi.set(1, Double.valueOf(mes1[2].split("=")[1]));
            this.setZuo(true);
            if (mes1.length == 5) {
                DianJpanel.teji2 = Integer.parseInt(mes1[4].split("=")[1]);
                this.teji[1].setVisible(true);
                this.teji[1].setText(UserMessUntil.getSkillId(String.valueOf(DianJpanel.teji2)).getSkillname());
                this.teji[1].setForeground(Color.green);
                this.teji[0].setVisible(false);
            }
            else {
                DianJpanel.teji2 = 0;
                this.teji[1].setVisible(false);
            }
        }
        NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().leixing = 0;
        FormsManagement.showForm(127);
    }
    
    public String[] getMes1() {
        return this.mes1;
    }
    
    public void setMes1(String[] mes1) {
        this.mes1 = mes1;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public ImageIcon getIcon2() {
        return this.icon2;
    }
    
    public void setIcon2(ImageIcon icon2) {
        this.icon2 = icon2;
    }
    
    public boolean isP() {
        return this.p;
    }
    
    public void setP(boolean p) {
        this.p = p;
    }
    
    public boolean isZuo() {
        return this.zuo;
    }
    
    public void setZuo(boolean zuo) {
        this.zuo = zuo;
    }
    
    public boolean isYou() {
        return this.you;
    }
    
    public void setYou(boolean you) {
        this.you = you;
    }
    
    public static Random getRand() {
        return DianJpanel.rand;
    }
    
    public static void setRand(Random rand) {
        DianJpanel.rand = rand;
    }
    
    public RefineOperBtn getOperBtn3() {
        return this.operBtn3;
    }
    
    static {
        DianJpanel.rand = new Random();
        DianJpanel.teji1 = 0;
        DianJpanel.teji2 = 0;
    }
}
