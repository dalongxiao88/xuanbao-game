package org.come.MountShouHu;

import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Iterator;
import org.come.bean.QualityClBean;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.until.GoodsListFromServerUntil;
import java.util.Objects;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.btn.MountShouhuBtn;
import org.come.entity.Goodstable;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShouhuPackJpanel extends JPanel
{
    private ImageIcon ico;
    private JLabel[] GoodsListLabel;
    private ShouhuPackGoodsMouslisten[] goodsMouslistens;
    private static JLabel effect;
    private static ImageIcon iconx;
    private static int xz;
    public int page;
    private static ImageIcon[] goodimg;
    private int idx;
    private static List<Goodstable> goodstableList;
    private int xuanzhong1;
    private int xuanzhong2;
    public static List<Goodstable> Eqment;
    public MountShouhuBtn up;
    public MountShouhuBtn down;
    
    public ShouhuPackJpanel() {
        this.ico = new ImageIcon("inkImg/Client/守护石物品栏.png");
        this.GoodsListLabel = new JLabel[12];
        this.goodsMouslistens = new ShouhuPackGoodsMouslisten[12];
        this.page = 0;
        this.idx = -1;
        this.xuanzhong1 = -1;
        this.xuanzhong2 = -1;
        this.setPreferredSize(new Dimension(339, 150));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        for (int i = 0; i < 12; ++i) {
            int row = i % 6 * 51;
            int col = i / 6 * 51;
            int[] arr = { 10 + row + 2 + 6, 10 + col + 1 + 6 };
            this.GoodsListLabel[i] = new JLabel();
            this.goodsMouslistens[i] = new ShouhuPackGoodsMouslisten(i, this, arr);
            this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
            this.GoodsListLabel[i].setBounds(10 + row + 2 + 6, 10 + col + 1 + 6, 50, 50);
            this.add(this.GoodsListLabel[i]);
        }
        this.add(ShouhuPackJpanel.effect);
        (this.up = new MountShouhuBtn("inkImg/Client/向右箭头11X15.png", 1, 70, this)).setBounds(35, 120, 17, 15);
        this.add(this.up);
        (this.down = new MountShouhuBtn("inkImg/Client/向左箭头11X15.png", 1, 71, this)).setBounds(20, 120, 17, 15);
        this.add(this.down);
    }
    
    public void init(int idx) {
        for (int k = 0; k <= ShouhuPackJpanel.goodimg.length - 1; ++k) {
            ShouhuPackJpanel.goodimg[k] = null;
        }
        this.<Objects>getGoodstableList().removeIf(Objects::isNull);
        this.getGoodstableList().removeIf(e/* org.come.entity.Goodstable, */ -> (int)e.getUsetime() == 0);
        for (int i = this.page * 12; i <= this.getGoodstableList().size() - 1; ++i) {
            if (this.getGoodstableList().get(i) != null && i - 12 * this.page < 12) {
                ShouhuPackJpanel.goodimg[i - 12 * this.page] = GoodsListFromServerUntil.imgpath(((Goodstable)this.getGoodstableList().get(i)).getSkin());
            }
        }
        if (idx != -1) {
            this.idx = idx;
        }
    }
    
    public void updata() {
        for (int k = 0; k <= ShouhuPackJpanel.goodimg.length - 1; ++k) {
            ShouhuPackJpanel.goodimg[k] = null;
        }
        this.<Objects>getGoodstableList().removeIf(Objects::isNull);
        this.getGoodstableList().removeIf(e/* org.come.entity.Goodstable, */ -> (int)e.getUsetime() == 0);
        for (int i = this.page * 12; i <= this.getGoodstableList().size() - 1; ++i) {
            if (this.getGoodstableList().get(i) != null && i - 12 * this.page < 12) {
                ShouhuPackJpanel.goodimg[i - 12 * this.page] = GoodsListFromServerUntil.imgpath(((Goodstable)this.getGoodstableList().get(i)).getSkin());
            }
        }
    }
    
    public void xuanzhong(int shopPlace) {
        Goodstable goodstable = null;
        if (this.getGoodstableList().size() != 0 && shopPlace + this.page * 12 < this.getGoodstableList().size()) {
            goodstable = (Goodstable)ShouhuPackJpanel.goodstableList.get(shopPlace + this.page * 12);
        }
        if (goodstable == null) {
            if (ShouhuPackJpanel.xz != -1) {
                this.GoodsListLabel[ShouhuPackJpanel.xz].setBorder(null);
            }
            ShouhuPackJpanel.xz = -1;
        }
        else {
            if (ShouhuPackJpanel.xz != -1) {
                this.GoodsListLabel[ShouhuPackJpanel.xz].setBorder(null);
            }
            ShouhuPackJpanel.xz = shopPlace;
            this.GoodsListLabel[ShouhuPackJpanel.xz].setBorder(BorderFactory.createLineBorder(Color.red));
        }
        this.init(this.idx);
    }
    
    public void ew(QualityClBean clBean) {
        for (Goodstable goodstable : this.getGoodstableList()) {
            if (goodstable != null && goodstable.getRgid().longValue() == clBean.getRgid().longValue()) {
                goodstable.setValue(clBean.getNewAttr());
                RandFJframe.getRandFJframe().getRandFJpanel().RL1 = null;
                RandFJframe.getRandFJframe().getRandFJpanel().RL2 = null;
                RandFJframe.getRandFJframe().getRandFJpanel().FL = null;
                break;
            }
        }
        this.updata();
        int i = 0;
        while (i <= this.getGoodstableList().size() - 1) {
            if (((Goodstable)this.getGoodstableList().get(i)).getRgid().longValue() == clBean.getRgid().longValue()) {
                this.xuanzhong1 = i;
                break;
            }
            else {
                ++i;
            }
        }
        this.init(1);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.ico != null) {
            g.drawImage(this.ico.getImage(), 0, 0, 339, 150, null);
        }
        for (int i = 0; i < 12; ++i) {
            int row = i % 6 * 51;
            int col = i / 6 * 51;
            if (ShouhuPackJpanel.goodimg[i] != null) {
                g.drawImage(ShouhuPackJpanel.goodimg[i].getImage(), 10 + row + 2 + 6, 10 + col + 1 + 6, 45, 45, null);
                if (((Goodstable)this.getGoodstableList().get(i)).getGoodlock() == 1) {
                    g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 10 + row + 2 + 6, 10 + col + 1 + 6, 10, 12, null);
                }
            }
        }
        g.setColor(Color.black);
        g.setFont(UIUtils.TEXT_NAME_FONT);
        g.drawString(this.page + "/4", 60, 135);
    }
    
    public List<Goodstable> getGoodstableList() {
        return ShouhuPackJpanel.goodstableList;
    }
    
    public void setGoodstableList(List<Goodstable> goodstableList) {
        ShouhuPackJpanel.goodstableList = goodstableList;
    }
    
    public static JLabel getEffect() {
        return ShouhuPackJpanel.effect;
    }
    
    public static void setEffect(JLabel effect) {
        ShouhuPackJpanel.effect = effect;
    }
    
    public int getIdx() {
        return this.idx;
    }
    
    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    public void effect(int place) {
        if (place != -1) {
            ShouhuPackJpanel.effect.setBounds(this.GoodsListLabel[place].getX(), this.GoodsListLabel[place].getY(), 50, 50);
        }
        else {
            ShouhuPackJpanel.effect.setBounds(1000, 1000, 52, 52);
        }
    }
    
    public int getXuanzhong1() {
        return this.xuanzhong1;
    }
    
    public void setXuanzhong1(int xuanzhong1) {
        if (xuanzhong1 != -1 && RandFJframe.getRandFJframe().getRandFJpanel().type == 2) {
            RandFJframe.getRandFJframe().getRandFJpanel().RL1 = (Goodstable)ShouhuPackJpanel.goodstableList.get(xuanzhong1);
        }
        this.xuanzhong1 = xuanzhong1;
    }
    
    public int getXuanzhong2() {
        return this.xuanzhong2;
    }
    
    public void setXuanzhong2(int xuanzhong2) {
        if (xuanzhong2 != -1) {
            RandFJframe.getRandFJframe().getRandFJpanel().RL2 = (Goodstable)ShouhuPackJpanel.goodstableList.get(xuanzhong2);
        }
        this.xuanzhong2 = xuanzhong2;
    }
    
    static {
        ShouhuPackJpanel.goodimg = new ImageIcon[12];
        ShouhuPackJpanel.goodstableList = new ArrayList<>();
        ShouhuPackJpanel.Eqment = new ArrayList<>();
        ShouhuPackJpanel.effect = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ShouhuPackJpanel.iconx.getImage(), 0, 0, 50, 50, this);
            }
        };
        ShouhuPackJpanel.iconx = new ImageIcon("inkImg/Client/border_quack.png");
        ShouhuPackJpanel.xz = -1;
    }
}
