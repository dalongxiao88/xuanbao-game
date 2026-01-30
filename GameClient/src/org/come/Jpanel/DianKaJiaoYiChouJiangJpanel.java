package org.come.Jpanel;

import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.tcpimg.UIUtils;
import org.come.until.Util;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.tcpimg.RichLabel;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import javax.swing.JLabel;
import com.tool.btn.DianKaJiaoYiBtn;
import javax.swing.JPanel;

public class DianKaJiaoYiChouJiangJpanel extends JPanel implements Runnable
{
    private static final long serialVersionUID = 1L;
    public static int jifen;
    private DianKaJiaoYiBtn f1000;
    private DianKaJiaoYiBtn f50;
    private DianKaJiaoYiBtn f150;
    private JLabel jiangliImage;
    private String[] jiangliStr;
    private String[] jiangliStr1;
    public static Boolean isOpen;
    public static Boolean isOpen1;
    public static long time;
    public Goodstable goodstable;
    private ImageIcon bg;
    private RichLabel bgtxt;
    
    public DianKaJiaoYiChouJiangJpanel() {
        this.jiangliStr = new String[] { "8", "203", "204", "228", "229", "230", "231", "232", "233", "308", "309", "310", "731", "732", "2223", "2226", "bsk98", "bsk99", "bsk100", "bsk101", "bsk102", "bsk107", "bsk1078", "6103", "6114", "6115", "6116", "7000", "7004", "7005", "7006", "7011" };
        this.jiangliStr1 = new String[] { "8", "203", "204", "7011" };
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(480, 520));
            this.setLayout(null);
            this.setOpaque(false);
            (this.jiangliImage = new JLabel()).setBounds(190, 120, 120, 120);
            this.jiangliImage.setOpaque(false);
            this.add(this.jiangliImage);
            (this.f1000 = new DianKaJiaoYiBtn("inkImg/button/18-1.png", 1, "1000积分抽大奖", 1000, this)).setBounds(165, 255, 160, 26);
            this.add(this.f1000);
            (this.f50 = new DianKaJiaoYiBtn("inkImg/button/18-2.png", 1, "50积分抽奖", 50, this)).setBounds(118, 300, 120, 26);
            this.add(this.f50);
            (this.f150 = new DianKaJiaoYiBtn("inkImg/button/18-2.png", 1, "150积分抽奖", 150, this)).setBounds(258, 300, 120, 26);
            this.add(this.f150);
        }
        else {
            this.setPreferredSize(new Dimension(480, 520));
            this.setLayout(null);
            this.setOpaque(false);
            (this.jiangliImage = new JLabel()).setBounds(173, 120, 120, 120);
            this.jiangliImage.setOpaque(false);
            this.add(this.jiangliImage);
            (this.f1000 = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/18-1h.png", 1, "1000积分抽大奖", 1000, this, "")).setBounds(152, 255, 160, 26);
            this.add(this.f1000);
            (this.f50 = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/18-2h.png", 1, "50积分抽奖", 50, this, "")).setBounds(105, 300, 120, 26);
            this.add(this.f50);
            (this.f150 = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/18-2h.png", 1, "150积分抽奖", 150, this, "")).setBounds(245, 300, 120, 26);
            this.add(this.f150);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.bg == null) {
                this.bg = CutButtonImage.getImage("Client/神通天演册/40×40/仙/dkjy-cj.png", -1, -1);
            }
            g.drawImage(this.bg.getImage(), 49, 70, this);
            g.setColor(Color.white);
            Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getScoretype("充值积分").toString()), 261, 99);
            (this.bgtxt = new RichLabel()).setBounds(60, 360, 390, 200);
            this.bgtxt.addText("#Y积分规则#r#W1.赞助1积分获得1抽奖积分；#r2.您可以选择消耗50，150，1000积分抽奖一次，抽奖积分越多奖品越贵重；#r3.积分不可转移，账号不通用；#r4.#R此抽奖积分抽奖是唯一途径，#W请勿轻信骗子；", UIUtils.TEXT_NAME_FONT);
            this.add(this.bgtxt);
        }
        else {
            super.paintComponent(g);
            if (this.bg == null) {
                this.bg = CutButtonImage.getImage("Client/神通天演册/40×40/仙/仙玉寄售3.png", -1, -1);
            }
            g.drawImage(this.bg.getImage(), 0, 0, this);
            g.setColor(Color.white);
            Util.drawPrice(g, new BigDecimal(RoleData.getRoleData().getLoginResult().getScoretype("充值积分").toString()), 240, 105);
            (this.bgtxt = new RichLabel()).setBounds(52, 340, 370, 200);
            this.bgtxt.addText("#Y积分规则#r#W1.赞助1积分获得1抽奖积分；#r2.您可以选择消耗50，150，1000积分抽奖一次，抽奖积分越多奖品越贵重；#r3.积分不可转移，账号不通用；#r4.#R此抽奖积分抽奖是唯一途径，#W请勿轻信骗子；", UIUtils.TEXT_NAME_FONT);
            this.add(this.bgtxt);
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    @Override
    public void run() {
        DianKaJiaoYiChouJiangJpanel.isOpen = Boolean.valueOf(true);
        DianKaJiaoYiChouJiangJpanel.isOpen1 = Boolean.valueOf(false);
        DianKaJiaoYiChouJiangJpanel.time = 0L;
        while ((boolean)DianKaJiaoYiChouJiangJpanel.isOpen) {
            try {
                Thread.sleep(130L);
                int j = Util.random.nextInt(this.jiangliStr.length);
                this.jiangliImage.setIcon(new ImageIcon("img/item/" + this.jiangliStr[j] + ".png"));
                ++DianKaJiaoYiChouJiangJpanel.time;
                if (DianKaJiaoYiChouJiangJpanel.time > 5L && !(boolean)DianKaJiaoYiChouJiangJpanel.isOpen1) {
                    String msg = Agreement.getAgreement().rolechangeAgreement("CZCJ|" + DianKaJiaoYiChouJiangJpanel.jifen);
                    SendMessageUntil.toServer(msg);
                    DianKaJiaoYiChouJiangJpanel.isOpen1 = Boolean.valueOf(true);
                }
                else {
                    continue;
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.jiangliStr1[this.jiangliStr1.length - 1] = this.goodstable.getSkin();
        for (int i = 0; i < this.jiangliStr1.length; ++i) {
            try {
                Thread.sleep(130L);
                this.jiangliImage.setIcon(new ImageIcon("img/item/" + this.jiangliStr1[i] + ".png"));
            }
            catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        ZhuFrame.getZhuJpanel().addPrompt("恭喜少侠抽中#R" + this.goodstable.getGoodsname() + "#Y!");
        this.f50.btntypechange(1);
        this.f150.btntypechange(1);
        this.f1000.btntypechange(1);
        this.f50.setEnabled(true);
        this.f150.setEnabled(true);
        this.f1000.setEnabled(true);
    }
    
    public void setJPGoods(Goodstable goods) {
        this.goodstable = goods;
        DianKaJiaoYiChouJiangJpanel.isOpen = Boolean.valueOf(false);
    }
    
    public DianKaJiaoYiBtn getF1000() {
        return this.f1000;
    }
    
    public void setF1000(DianKaJiaoYiBtn f1000) {
        this.f1000 = f1000;
    }
    
    public DianKaJiaoYiBtn getF50() {
        return this.f50;
    }
    
    public void setF50(DianKaJiaoYiBtn f50) {
        this.f50 = f50;
    }
    
    public DianKaJiaoYiBtn getF150() {
        return this.f150;
    }
    
    public void setF150(DianKaJiaoYiBtn f150) {
        this.f150 = f150;
    }
    
    static {
        DianKaJiaoYiChouJiangJpanel.jifen = 0;
        DianKaJiaoYiChouJiangJpanel.isOpen = Boolean.valueOf(false);
        DianKaJiaoYiChouJiangJpanel.isOpen1 = Boolean.valueOf(false);
        DianKaJiaoYiChouJiangJpanel.time = 0L;
    }
}
