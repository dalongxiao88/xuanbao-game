package org.come.Jpanel;

import java.util.Iterator;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import java.awt.Color;
import java.awt.Graphics;
import com.tool.btn.RefineOperBtn;
import org.come.Frame.NewRefiningJframe;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import org.come.bean.QualityClBean;
import java.util.List;
import javax.swing.JPanel;

public class DianQJpanel extends JPanel
{
    public double bili;
    private boolean p;
    private boolean zuo;
    private boolean you;
    public List<String> shuxing;
    public List<Double> shuzhi;
    public QualityClBean clBean;
    String[] mes1;
    int dianzui1;
    int diancui2;
    ImageIcon icon;
    ImageIcon icon1;
    ImageIcon icon2;
    int zong;
    int xin;
    static Random rand;
    
    public DianQJpanel() {
        this.bili = 1.0;
        this.p = true;
        this.zuo = false;
        this.you = false;
        this.shuxing = new ArrayList<>();
        this.shuzhi = new ArrayList<>();
        this.mes1 = new String[] { "加强混乱", "加强封印", "加强昏睡", "加强雷", "加强火", "加强鬼火", "强力克金", "加强克土", "加强克火", "加强克水", "加强克木", "加强风" };
        this.dianzui1 = 0;
        this.diancui2 = 0;
        this.icon1 = new ImageIcon("inkImg/background/S67.png");
        this.icon2 = new ImageIcon("inkImg/background/S154.png");
        this.zong = 0;
        this.xin = 0;
        this.setPreferredSize(new Dimension(470, 315));
        this.setLayout(null);
        this.setOpaque(false);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 128);
        offBtn.setBounds(450, 10, 25, 25);
        this.add(offBtn);
        RefineOperBtn operBtn3 = new RefineOperBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 2, "再次炼化");
        operBtn3.setBounds(365, 29, 68, 17);
        this.add(operBtn3);
        RefineOperBtn operBtn4 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 0, "保 留");
        operBtn4.setBounds(80, 275, 99, 24);
        this.add(operBtn4);
        RefineOperBtn operBtn5 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel(), 1, "替 换");
        operBtn5.setBounds(300, 275, 99, 24);
        this.add(operBtn5);
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
                g.drawString((this.zong == 0) ? "2" : String.valueOf(this.zong), 170, 135);
                g.drawImage(this.icon1.getImage(), 35, 140, 191, 28, this);
                g.drawImage(this.icon1.getImage(), 35, 170, 191, 28, this);
                g.setFont(UIUtils.TEXT_FONT2);
                g.setColor(Color.white);
                g.drawString((String)this.getShuxing().get(0), 45, 160);
                g.drawString(String.valueOf(this.getShuzhi().get(0)), 175, 158);
                g.drawString((String)this.getShuxing().get(1), 45, 190);
                g.drawString(String.valueOf(this.getShuzhi().get(1)), 175, 193);
            }
            if (this.isYou()) {
                g.setFont(UIUtils.nameFont);
                g.setColor(Color.black);
                g.drawString("总点粹值", 250, 135);
                g.drawString((this.xin == 0) ? "2" : String.valueOf(this.xin), 400, 135);
                g.drawImage(this.icon1.getImage(), 245, 140, 191, 28, this);
                g.drawImage(this.icon1.getImage(), 245, 170, 191, 28, this);
                g.setFont(UIUtils.TEXT_FONT2);
                g.setColor(Color.white);
                g.drawString((String)this.getShuxing().get(2), 255, 160);
                g.drawString(String.valueOf(this.getShuzhi().get(2)), 385, 158);
                g.drawString((String)this.getShuxing().get(3), 255, 190);
                g.drawString(String.valueOf(this.getShuzhi().get(3)), 385, 190);
            }
        }
    }
    
    public static double dianchizhi() {
        return 1.0;
    }
    
    public double getBili() {
        return dianchizhi();
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
            this.zong = Integer.parseInt(mes1[3].split("\\|")[0].split("=")[1]);
            this.setZuo(true);
        }
        NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().leixing = 0;
        FormsManagement.showForm(128);
    }
    
    public void ew(QualityClBean clBean) {
        this.setP(true);
        String[] mes1 = clBean.getNewAttr().split("&");
        this.shuxing.set(2, mes1[1].split("=")[0]);
        this.shuzhi.set(2, Double.valueOf(mes1[1].split("=")[1]));
        this.shuxing.set(3, mes1[2].split("=")[0]);
        this.shuzhi.set(3, Double.valueOf(mes1[2].split("=")[1]));
        this.xin = Integer.parseInt(mes1[3].split("\\|")[0].split("=")[1]);
        this.setYou(true);
        this.clBean = clBean;
    }
    
    public void TH(QualityClBean clBean) {
        Goodstable good = GoodsListFromServerUntil.getRgid(clBean.getRgid());
        assert good != null;
        String[] value = good.getValue().split("\\|");
        List<String> value2 = new ArrayList<>();
        for (String s : value) {
            if (!s.startsWith("点粹属性") && !s.startsWith("炼化属性&特技")) {
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
        this.zong = this.xin;
        this.clBean = null;
        this.setYou(false);
        this.setZuo(true);
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
    
    public static Random getRand() {
        return DianQJpanel.rand;
    }
    
    public static void setRand(Random rand) {
        DianQJpanel.rand = rand;
    }
    
    static {
        DianQJpanel.rand = new Random();
    }
}
