package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.entity.Baby;
import java.awt.Graphics;
import org.come.mouslisten.ChoseChildTypeMouslisten;
import java.awt.Font;
import org.come.mouslisten.BabyPartsMouslisten;
import java.awt.Color;
import org.come.mouslisten.ChangeBabyMouslisten;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import javax.swing.JLabel;
import com.tool.btn.BabyBtn;
import javax.swing.JPanel;

public class TestChildJpanel extends JPanel
{
    private TestChildCardJpanel testChildCardJpanel;
    private BabyBtn labAttr;
    private BabyBtn labTalent;
    private JLabel labchildhat;
    private JLabel labchildclothes;
    private JLabel labchildarms;
    private JLabel labchildshoes;
    private JLabel labchildage;
    private JLabel labchildImg;
    private JLabel[] labListChild;
    private BigDecimal babyid;
    ImageIcon icon;
    ImageIcon icon2;
    int p;
    
    public TestChildJpanel() throws Exception {
        this.labListChild = new JLabel[6];
        this.p = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(578, 369));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1);
            offBtn.setBounds(541, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < 6; ++i) {
                this.labListChild[i] = new JLabel();
                ChangeBabyMouslisten mouslisten = new ChangeBabyMouslisten(i);
                this.labListChild[i].setBounds(57, 37 + i * 51, 39, 39);
                this.labListChild[i].setForeground(Color.WHITE);
                this.labListChild[i].setFont(UIUtils.TEXT_FONT);
                this.labListChild[i].addMouseListener(mouslisten);
                this.add(this.labListChild[i]);
            }
            (this.labchildclothes = new JLabel("衣 服", 0)).setBounds(384, 277, 52, 50);
            this.labchildclothes.setForeground(new Color(177, 162, 107));
            this.labchildclothes.addMouseListener(new BabyPartsMouslisten(0));
            this.labchildclothes.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildclothes);
            (this.labchildhat = new JLabel("帽 子", 0)).setBounds(324, 277, 52, 50);
            this.labchildhat.setForeground(new Color(177, 162, 107));
            this.labchildhat.addMouseListener(new BabyPartsMouslisten(1));
            this.labchildhat.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildhat);
            (this.labchildshoes = new JLabel("鞋 子", 0)).setBounds(504, 277, 52, 50);
            this.labchildshoes.setForeground(new Color(177, 162, 107));
            this.labchildshoes.addMouseListener(new BabyPartsMouslisten(2));
            this.labchildshoes.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildshoes);
            (this.labchildarms = new JLabel("武 器", 0)).setBounds(444, 277, 52, 50);
            this.labchildarms.setForeground(new Color(177, 162, 107));
            this.labchildarms.addMouseListener(new BabyPartsMouslisten(3));
            this.labchildarms.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildarms);
            (this.labchildage = new JLabel("", 0)).setForeground(Color.YELLOW);
            this.labchildage.setFont(new Font("宋体", 1, 16));
            this.labchildage.setBounds(162, 54, 100, 20);
            this.add(this.labchildage);
            (this.testChildCardJpanel = new TestChildCardJpanel()).setBounds(319, 57, 240, 215);
            this.add(this.testChildCardJpanel);
            (this.labAttr = new BabyBtn("inkImg/button/B180.png", 1)).setBounds(333, 31, 63, 26);
            this.labAttr.addMouseListener(new ChoseChildTypeMouslisten(1, this.testChildCardJpanel));
            this.add(this.labAttr);
            (this.labTalent = new BabyBtn("inkImg/button/B181.png", 1)).setBounds(398, 31, 63, 26);
            this.labTalent.addMouseListener(new ChoseChildTypeMouslisten(2, this.testChildCardJpanel));
            this.add(this.labTalent);
        }
        else {
            this.setPreferredSize(new Dimension(552, 378));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 1);
            offBtn.setBounds(532, 0, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < 6; ++i) {
                (this.labListChild[i] = new JLabel()).setText(" 暂无");
                ChangeBabyMouslisten mouslisten = new ChangeBabyMouslisten(i);
                this.labListChild[i].setBounds(31, 49 + i * 51, 39, 39);
                this.labListChild[i].setForeground(Color.WHITE);
                this.labListChild[i].setFont(UIUtils.TEXT_FONT);
                this.labListChild[i].addMouseListener(mouslisten);
                this.add(this.labListChild[i]);
            }
            (this.labchildclothes = new JLabel("衣 服", 0)).setBounds(357, 295, 52, 50);
            this.labchildclothes.setForeground(new Color(177, 162, 107));
            this.labchildclothes.addMouseListener(new BabyPartsMouslisten(0));
            this.labchildclothes.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildclothes);
            (this.labchildhat = new JLabel("帽 子", 0)).setBounds(297, 295, 52, 50);
            this.labchildhat.setForeground(new Color(177, 162, 107));
            this.labchildhat.addMouseListener(new BabyPartsMouslisten(1));
            this.labchildhat.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildhat);
            (this.labchildshoes = new JLabel("鞋 子", 0)).setBounds(477, 295, 52, 50);
            this.labchildshoes.setForeground(new Color(177, 162, 107));
            this.labchildshoes.addMouseListener(new BabyPartsMouslisten(2));
            this.labchildshoes.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildshoes);
            (this.labchildarms = new JLabel("武 器", 0)).setBounds(417, 295, 52, 50);
            this.labchildarms.setForeground(new Color(177, 162, 107));
            this.labchildarms.addMouseListener(new BabyPartsMouslisten(3));
            this.labchildarms.setFont(UIUtils.TEXT_FONT);
            this.add(this.labchildarms);
            (this.labchildage = new JLabel("", 0)).setForeground(Color.YELLOW);
            this.labchildage.setFont(new Font("宋体", 1, 16));
            this.labchildage.setBounds(142, 54, 100, 20);
            this.add(this.labchildage);
            (this.testChildCardJpanel = new TestChildCardJpanel()).setBounds(293, 69, 240, 214);
            this.add(this.testChildCardJpanel);
            (this.labAttr = new BabyBtn("img/xy2uiimg/childattr_checked.png", 1)).setBounds(303, 48, 75, 26);
            this.labAttr.addMouseListener(new ChoseChildTypeMouslisten(1, this.testChildCardJpanel));
            this.add(this.labAttr);
            (this.labTalent = new BabyBtn("img/xy2uiimg/childtalent_unchecked.png", 1)).setBounds(367, 48, 75, 26);
            this.labTalent.addMouseListener(new ChoseChildTypeMouslisten(2, this.testChildCardJpanel));
            this.add(this.labTalent);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B216.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 578, 369, this);
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), 112, 34, 199, 299, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/71_png.xy2uiimg.child_bg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 378, this);
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), 86, 47, 199, 299, this);
            }
        }
    }
    
    public boolean ChangeParts(Baby baby, Goodstable good1, Goodstable good2, int type) {
        if (!GoodsListFromServerUntil.ChangeParts(good1, good2)) {
            return false;
        }
        if (good1 != null) {
            GoodsListFromServerUntil.fushis.remove(good1.getRgid());
        }
        if (good2 != null) {
            if (type == 1) {
                this.labchildhat.setIcon(GoodsListFromServerUntil.imgpathAdaptive(good2.getSkin(), 49, 49));
                this.labchildhat.setText(null);
            }
            else if (type == 0) {
                this.labchildclothes.setIcon(GoodsListFromServerUntil.imgpathAdaptive(good2.getSkin(), 49, 49));
                this.labchildclothes.setText(null);
            }
            else if (type == 3) {
                this.labchildarms.setIcon(GoodsListFromServerUntil.imgpathAdaptive(good2.getSkin(), 49, 49));
                this.labchildarms.setText(null);
            }
            else if (type == 2) {
                this.labchildshoes.setIcon(GoodsListFromServerUntil.imgpathAdaptive(good2.getSkin(), 49, 49));
                this.labchildshoes.setText(null);
            }
            GoodsListFromServerUntil.fushis.put(good2.getRgid(), good2);
        }
        else if (type == 1) {
            this.labchildhat.setIcon(null);
            this.labchildhat.setText("帽 子");
        }
        else if (type == 0) {
            this.labchildclothes.setIcon(null);
            this.labchildclothes.setText("衣 服");
        }
        else if (type == 3) {
            this.labchildarms.setIcon(null);
            this.labchildarms.setText("武 器");
        }
        else if (type == 2) {
            this.labchildshoes.setIcon(null);
            this.labchildshoes.setText("鞋 子");
        }
        this.testChildCardJpanel.getChildAttributeJpanel().showBaby(baby, baby.getpartAll());
        return true;
    }
    
    public void ShowAge(int age, int sex, String outcome) {
        int mouth = age / 30 % 12;
        int year = age / 360;
        if (outcome == null || outcome.equals("")) {
            StringBuffer buffer = new StringBuffer();
            if (year > 0) {
                buffer.append(year);
                buffer.append("岁");
            }
            buffer.append(mouth);
            buffer.append("月");
            this.labchildage.setText(buffer.toString());
        }
        else {
            this.labchildage.setText(outcome);
        }
        year /= 3;
        if (year > 5) {
            year = 5;
        }
        if (sex == 0) {
            if (this.p != year) {
                this.p = year;
                this.icon2 = new ImageIcon("img/baby/c110" + year + ".png");
            }
        }
        else if (this.p != year + 10) {
            this.p = year + 10;
            this.icon2 = new ImageIcon("img/baby/c100" + year + ".png");
        }
    }
    
    public void ShowBaby(Baby baby) {
        this.babyid = ((baby != null) ? baby.getBabyid() : null);
        BigDecimal[] bigs = (BigDecimal[])((baby != null) ? baby.getpartAll() : null);
        this.testChildCardJpanel.getChildAttributeJpanel().showBaby(baby, bigs);
        this.testChildCardJpanel.getChildTalentJpanel().showBaby(baby);
        if (baby == null) {
            this.icon2 = null;
            this.labchildage.setText("");
            this.labchildhat.setIcon(null);
            this.labchildhat.setText("帽 子");
            this.labchildclothes.setIcon(null);
            this.labchildclothes.setText("衣 服");
            this.labchildarms.setIcon(null);
            this.labchildarms.setText("武 器");
            this.labchildshoes.setIcon(null);
            this.labchildshoes.setText("鞋 子");
        }
        else {
            this.ShowAge((int)baby.getBabyage(), (int)baby.getChildSex(), baby.getOutcome());
            Goodstable naozi = (Goodstable)GoodsListFromServerUntil.fushis.get(bigs[1]);
            if (naozi == null) {
                this.labchildhat.setIcon(null);
                this.labchildhat.setText("帽 子");
            }
            else {
                this.labchildhat.setIcon(GoodsListFromServerUntil.imgpathAdaptive(naozi.getSkin(), 49, 49));
                this.labchildhat.setText(null);
            }
            Goodstable yifu = (Goodstable)GoodsListFromServerUntil.fushis.get(bigs[0]);
            if (yifu == null) {
                this.labchildclothes.setIcon(null);
                this.labchildclothes.setText("衣 服");
            }
            else {
                this.labchildclothes.setIcon(GoodsListFromServerUntil.imgpathAdaptive(yifu.getSkin(), 49, 49));
                this.labchildclothes.setText(null);
            }
            Goodstable wuqi = (Goodstable)GoodsListFromServerUntil.fushis.get(bigs[3]);
            if (wuqi == null) {
                this.labchildarms.setIcon(null);
                this.labchildarms.setText("武 器");
            }
            else {
                this.labchildarms.setIcon(GoodsListFromServerUntil.imgpathAdaptive(wuqi.getSkin(), 49, 49));
                this.labchildarms.setText(null);
            }
            Goodstable xuezi = (Goodstable)GoodsListFromServerUntil.fushis.get(bigs[2]);
            if (xuezi == null) {
                this.labchildshoes.setIcon(null);
                this.labchildshoes.setText("鞋 子");
            }
            else {
                this.labchildshoes.setIcon(GoodsListFromServerUntil.imgpathAdaptive(xuezi.getSkin(), 49, 49));
                this.labchildshoes.setText(null);
            }
        }
    }
    
    public TestChildCardJpanel getTestChildCardJpanel() {
        return this.testChildCardJpanel;
    }
    
    public void setTestChildCardJpanel(TestChildCardJpanel testChildCardJpanel) {
        this.testChildCardJpanel = testChildCardJpanel;
    }
    
    public JLabel getLabchildhat() {
        return this.labchildhat;
    }
    
    public void setLabchildhat(JLabel labchildhat) {
        this.labchildhat = labchildhat;
    }
    
    public JLabel getLabchildclothes() {
        return this.labchildclothes;
    }
    
    public void setLabchildclothes(JLabel labchildclothes) {
        this.labchildclothes = labchildclothes;
    }
    
    public JLabel getLabchildarms() {
        return this.labchildarms;
    }
    
    public void setLabchildarms(JLabel labchildarms) {
        this.labchildarms = labchildarms;
    }
    
    public JLabel getLabchildshoes() {
        return this.labchildshoes;
    }
    
    public void setLabchildshoes(JLabel labchildshoes) {
        this.labchildshoes = labchildshoes;
    }
    
    public JLabel[] getLabListChild() {
        return this.labListChild;
    }
    
    public void setLabListChild(JLabel[] labListChild) {
        this.labListChild = labListChild;
    }
    
    public JLabel getLabchildage() {
        return this.labchildage;
    }
    
    public void setLabchildage(JLabel labchildage) {
        this.labchildage = labchildage;
    }
    
    public JLabel getLabchildImg() {
        return this.labchildImg;
    }
    
    public void setLabchildImg(JLabel labchildImg) {
        this.labchildImg = labchildImg;
    }
    
    public BigDecimal getBabyid() {
        return this.babyid;
    }
    
    public void setBabyid(BigDecimal babyid) {
        this.babyid = babyid;
    }
    
    public BabyBtn getLabAttr() {
        return this.labAttr;
    }
    
    public void setLabAttr(BabyBtn labAttr) {
        this.labAttr = labAttr;
    }
    
    public BabyBtn getLabTalent() {
        return this.labTalent;
    }
    
    public void setLabTalent(BabyBtn labTalent) {
        this.labTalent = labTalent;
    }
}
