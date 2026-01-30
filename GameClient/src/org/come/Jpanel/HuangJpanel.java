package org.come.Jpanel;

import java.awt.event.MouseEvent;
import org.come.Frame.ZhuFrame;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.JTextField;
import com.tool.btn.HuangBtn;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class HuangJpanel extends JPanel implements MouseListener
{
    static ImageIcon icon;
    static ImageIcon icon1;
    private static HuangBtn xuanyu;
    private static HuangBtn jinbi;
    private static HuangBtn dashuang;
    private static HuangBtn da;
    private static HuangBtn xiao;
    private static HuangBtn baozi;
    private static HuangBtn mai;
    private static HuangBtn quxiao;
    private static JTextField gold;
    private int type;
    private int tpye1;
    public long gold0;
    public static String tou;
    public static String tou1;
    
    public HuangJpanel() {
        this.type = 0;
        this.tpye1 = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(350, 320));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (HuangJpanel.xuanyu = new HuangBtn("inkImg/button/B59.png", 1, 1, "头彩", this)).setBounds(40, 80, 68, 40);
            HuangJpanel.xuanyu.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.xuanyu);
            (HuangJpanel.dashuang = new HuangBtn("inkImg/button/B59.png", 1, 3, "七星", this)).setBounds(HuangJpanel.xuanyu.getX() + 75, 80, 68, 40);
            HuangJpanel.dashuang.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.dashuang);
            (HuangJpanel.jinbi = new HuangBtn("inkImg/button/B59.png", 1, 2, "双对", this)).setBounds(HuangJpanel.dashuang.getX() + 75, 80, 68, 40);
            HuangJpanel.jinbi.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.jinbi);
            (HuangJpanel.da = new HuangBtn("inkImg/button/B59.png", 1, 4, "散星", this)).setBounds(HuangJpanel.jinbi.getX() + 75, 80, 68, 40);
            HuangJpanel.da.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.da);
            (HuangJpanel.xiao = new HuangBtn("inkImg/button/B59.png", 1, 5, "金币", this)).setBounds(HuangJpanel.dashuang.getX() + 75, HuangJpanel.dashuang.getY() + 40, 68, 40);
            HuangJpanel.xiao.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.xiao);
            (HuangJpanel.baozi = new HuangBtn("inkImg/button/B59.png", 1, 6, "仙玉", this)).setBounds(HuangJpanel.xiao.getX() + 75, HuangJpanel.dashuang.getY() + 40, 68, 40);
            HuangJpanel.baozi.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.baozi);
            (HuangJpanel.mai = new HuangBtn("inkImg/button/18.png", 1, 7, "买定离手", this)).setBounds(50, 240, 100, 40);
            HuangJpanel.mai.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.mai);
            (HuangJpanel.quxiao = new HuangBtn("inkImg/button/18.png", 1, 8, "取消选择", this)).setBounds(190, 240, 100, 40);
            HuangJpanel.quxiao.setColors(UIUtils.COLOR_BLACK);
            this.add(HuangJpanel.quxiao);
            (HuangJpanel.gold = new JTextField()).setBounds(165, 204, 160, 15);
            HuangJpanel.gold.setForeground(Color.white);
            HuangJpanel.gold.setOpaque(false);
            HuangJpanel.gold.setFont(new Font("宋体", 1, 13));
            HuangJpanel.gold.setBorder(BorderFactory.createEmptyBorder());
            HuangJpanel.gold.setCaretColor(Color.white);
            this.add(HuangJpanel.gold);
        }
        else {
            this.setPreferredSize(new Dimension(350, 320));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (HuangJpanel.xuanyu = new HuangBtn("inkImg/hongmu/6026.png", 1, 1, "头彩", this)).setBounds(30, 85, 60, 30);
            HuangJpanel.xuanyu.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.xuanyu);
            (HuangJpanel.dashuang = new HuangBtn("inkImg/hongmu/6026.png", 1, 3, "七星", this)).setBounds(HuangJpanel.xuanyu.getX() + 75, 85, 60, 30);
            HuangJpanel.dashuang.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.dashuang);
            (HuangJpanel.jinbi = new HuangBtn("inkImg/hongmu/6026.png", 1, 2, "双对", this)).setBounds(HuangJpanel.dashuang.getX() + 75, 85, 60, 30);
            HuangJpanel.jinbi.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.jinbi);
            (HuangJpanel.da = new HuangBtn("inkImg/hongmu/6026.png", 1, 4, "散星", this)).setBounds(HuangJpanel.jinbi.getX() + 75, 85, 60, 30);
            HuangJpanel.da.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.da);
            (HuangJpanel.xiao = new HuangBtn("inkImg/hongmu/6026.png", 1, 5, "金币", this)).setBounds(HuangJpanel.dashuang.getX() + 75, HuangJpanel.dashuang.getY() + 35, 60, 30);
            HuangJpanel.xiao.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.xiao);
            (HuangJpanel.baozi = new HuangBtn("inkImg/hongmu/6026.png", 1, 6, "仙玉", this)).setBounds(HuangJpanel.xiao.getX() + 75, HuangJpanel.dashuang.getY() + 35, 60, 30);
            HuangJpanel.baozi.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.baozi);
            (HuangJpanel.mai = new HuangBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, 7, "买定离手", this)).setBounds(60, 200, 80, 30);
            HuangJpanel.mai.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.mai);
            (HuangJpanel.quxiao = new HuangBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, 8, "取消选择", this)).setBounds(210, 200, 80, 30);
            HuangJpanel.quxiao.setColors(UIUtils.COLOR_BTNPUTONG);
            this.add(HuangJpanel.quxiao);
            (HuangJpanel.gold = new JTextField()).setBounds(138, 165, 160, 15);
            HuangJpanel.gold.setForeground(Color.white);
            HuangJpanel.gold.setOpaque(false);
            HuangJpanel.gold.setFont(new Font("宋体", 1, 13));
            HuangJpanel.gold.setBorder(BorderFactory.createEmptyBorder());
            HuangJpanel.gold.setCaretColor(Color.white);
            this.add(HuangJpanel.gold);
        }
    }
    
    public static String getTou() {
        return HuangJpanel.tou;
    }
    
    public static void setTou(String tou) {
        HuangJpanel.tou = tou;
    }
    
    public static String getTou1() {
        return HuangJpanel.tou1;
    }
    
    public static void setTou1(String tou1) {
        HuangJpanel.tou1 = tou1;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (HuangJpanel.icon != null) {
                g.drawImage(HuangJpanel.icon.getImage(), 0, 0, 350, 320, this);
            }
            if (HuangJpanel.gold.getText().length() != 0 && HuangJpanel.gold.getText().matches("\\d+")) {
                if (this.getTpye1() == 6) {
                    this.gold0 = Long.parseLong(HuangJpanel.gold.getText());
                    if (Long.parseLong(HuangJpanel.gold.getText()) > 100000L) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R最大投注10万仙玉！");
                        HuangJpanel.gold.setText("100000");
                    }
                }
                else {
                    this.gold0 = Long.parseLong(HuangJpanel.gold.getText());
                    if (Long.parseLong(HuangJpanel.gold.getText()) > 10000000000L) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R最大投注100亿！");
                        HuangJpanel.gold.setText("10000000000");
                    }
                }
            }
            else {
                this.gold0 = 0L;
            }
            Font font = new Font("宋体", 1, 20);
            g.setFont(font);
            g.setColor(Color.blue);
            if (!HuangJpanel.tou.equals("null") && !HuangJpanel.tou1.equals("null")) {
                g.setColor(Color.red);
                g.drawString(HuangJpanel.tou, 140, 70);
                g.drawString("，", 160, 70);
                g.drawString(HuangJpanel.tou1, 180, 70);
            }
            else {
                g.setColor(Color.red);
                g.drawString("请等待头彩号码开启", 150, 69);
            }
        }
        else {
            if (HuangJpanel.icon1 != null) {
                g.drawImage(HuangJpanel.icon1.getImage(), 0, 0, 350, 320, this);
            }
            if (HuangJpanel.gold.getText().length() != 0 && HuangJpanel.gold.getText().matches("\\d+")) {
                if (this.getTpye1() == 6) {
                    this.gold0 = Long.parseLong(HuangJpanel.gold.getText());
                    if (Long.parseLong(HuangJpanel.gold.getText()) > 100000L) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R最大投注10万仙玉！");
                        HuangJpanel.gold.setText("100000");
                    }
                }
                else {
                    this.gold0 = Long.parseLong(HuangJpanel.gold.getText());
                    if (Long.parseLong(HuangJpanel.gold.getText()) > 10000000000L) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R最大投注100亿！");
                        HuangJpanel.gold.setText("10000000000");
                    }
                }
            }
            else {
                this.gold0 = 0L;
            }
            Font font = new Font("宋体", 1, 20);
            g.setFont(font);
            g.setColor(Color.orange);
            if (!HuangJpanel.tou.equals("null") && !HuangJpanel.tou1.equals("null")) {
                g.setColor(Color.red);
                g.drawString(HuangJpanel.tou, 140, 75);
                g.drawString("，", 160, 75);
                g.drawString(HuangJpanel.tou1, 180, 75);
            }
            else {
                g.setColor(Color.red);
                g.drawString("请等待头彩号码开启", 120, 72);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
    
    public static ImageIcon getIcon() {
        return HuangJpanel.icon;
    }
    
    public static void setIcon(ImageIcon icon) {
        HuangJpanel.icon = icon;
    }
    
    public static HuangBtn getDashuang() {
        return HuangJpanel.dashuang;
    }
    
    public void setDashuang(HuangBtn dashuang) {
        HuangJpanel.dashuang = dashuang;
    }
    
    public static HuangBtn getDa() {
        return HuangJpanel.da;
    }
    
    public void setDa(HuangBtn da) {
        HuangJpanel.da = da;
    }
    
    public static HuangBtn getBaozi() {
        return HuangJpanel.baozi;
    }
    
    public void setBaozi(HuangBtn baozi) {
        HuangJpanel.baozi = baozi;
    }
    
    public static JTextField getGold() {
        return HuangJpanel.gold;
    }
    
    public void setGold(JTextField gold) {
        HuangJpanel.gold = gold;
    }
    
    public long getGold0() {
        return this.gold0;
    }
    
    public void setGold0(long gold0) {
        this.gold0 = gold0;
    }
    
    public static HuangBtn getXuanyu() {
        return HuangJpanel.xuanyu;
    }
    
    public void setXuanyu(HuangBtn xuanyu) {
        HuangJpanel.xuanyu = xuanyu;
    }
    
    public static HuangBtn getJinbi() {
        return HuangJpanel.jinbi;
    }
    
    public void setJinbi(HuangBtn jinbi) {
        HuangJpanel.jinbi = jinbi;
    }
    
    public static HuangBtn getXiao() {
        return HuangJpanel.xiao;
    }
    
    public void setXiao(HuangBtn xiao) {
        HuangJpanel.xiao = xiao;
    }
    
    public static HuangBtn getMai() {
        return HuangJpanel.mai;
    }
    
    public void setMai(HuangBtn mai) {
        HuangJpanel.mai = mai;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getTpye1() {
        return this.tpye1;
    }
    
    public void setTpye1(int tpye1) {
        this.tpye1 = tpye1;
    }
    
    static {
        HuangJpanel.icon = new ImageIcon("inkImg/background/黄大小水墨.png");
        HuangJpanel.icon1 = new ImageIcon("inkImg/hongmu/黄大小红木.png");
    }
}
