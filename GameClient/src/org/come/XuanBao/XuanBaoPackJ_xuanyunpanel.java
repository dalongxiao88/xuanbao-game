
package org.come.XuanBao;


import com.tool.btn.MountShouhuBtn;
import com.tool.tcpimg.UIUtils;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

import javax.swing.border.Border;

import org.come.XuanBao.XuanBaoPackGoodsMouslisten;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;


public class XuanBaoPackJ_xuanyunpanel extends JPanel {
    private ImageIcon ico = new ImageIcon("img/xuan/玄蕴使用面板.png");
    private JLabel[] GoodsListLabel = new JLabel[12];
    private XuanBaoPackGoodsMouslisten[] goodsMouslistens = new XuanBaoPackGoodsMouslisten[12];
    private static JLabel effect= new JLabel();
    public MountShouhuBtn up;
    public MountShouhuBtn down;
    private XuanBaoPagingBtn down1;
    public int page = 0;
    private static ImageIcon[] goodimg = new ImageIcon[12];
    private List<Goodstable> goodstableList = new ArrayList<>();
    private static ImageIcon iconx = new ImageIcon("inkImg/background/border_quack.png");
    public static int     xz = -1;
    private int idx = -1;
    public String text;


    public XuanBaoPackJ_xuanyunpanel() {
        setPreferredSize(new Dimension(390, 238));
        setLayout(null);
        setBackground(UIUtils.Color_BACK);
        int i = 0;
        while (i < 12) {
            int row = i % 6 * 51;
            int col = i / 6 * 51;
            int[] arr = {22 + row + 2 + 6, 10 + col + 1 + 6};
            this.GoodsListLabel[i] = new JLabel();
            this.goodsMouslistens[i] = new XuanBaoPackGoodsMouslisten(i, this);
            this.GoodsListLabel[i].addMouseListener((MouseListener) this.goodsMouslistens[i]);
            this.GoodsListLabel[i].setBounds(38 + row + 35 + 16, 28 + col + 23 + 6, 50, 50);
            add(this.GoodsListLabel[i]);
            i++;
        }
        add(effect);
            this.up = new MountShouhuBtn("img/xuan/43_.png", 1, 70, this);
        this.up.setBounds(350, 315, 18, 20);
        add(this.up);
        this.down = new MountShouhuBtn("img/xuan/42_.png", 1, 71, this);
        this.down.setBounds(320, 315, 18, 20);
        add(this.down);

    }


    public void init(int idx) {
        for (int k = 0; k <= goodimg.length - 1; k++) {
            goodimg[k] = null;
        }
        getGoodstableList().removeIf(Objects::isNull);
        getGoodstableList().removeIf(e -> (e.getUsetime() == 0));
        for (int i = this.page * 12; i <= getGoodstableList().size() - 1; i++) {
            if (getGoodstableList().get(i) != null && i - 12 * this.page < 12) {
                goodimg[i - 12 * this.page] = GoodsListFromServerUntil.imgpath(getGoodstableList().get(i).getSkin());
            }
        }
        if (idx != -1) {
            this.idx = idx;
        }
    }


    public void xuanzhong(int shopPlace) {
        Goodstable goodstable = null;
        if (getGoodstableList().size() != 0 && shopPlace + this.page * 12 < getGoodstableList().size()) {
            goodstable = this.goodstableList.get(shopPlace + this.page * 12);
        }
        if (goodstable == null) {
            if (xz != -1) {
                this.GoodsListLabel[xz].setBorder(null);
            }
            xz = -1;
        } else {
            if (xz != -1) {
                this.GoodsListLabel[xz].setBorder(null);
            }
            xz = shopPlace;
            this.GoodsListLabel[xz].setBorder(BorderFactory.createLineBorder(Color.red, 2));
        }
        init(this.idx);
    }

    public void update(String text) {
        if (text != null) {
            String[] split = text.split("=");
            StringBuffer sb = new StringBuffer();
            sb.append("此槽可镶嵌");
            for (int j = 0; j <= split.length - 1; j++) {
                sb.append(split[j]);
            }
            sb.append("玄印");
            this.text = sb.toString();

        }
        for (int k = 0; k <= goodimg.length - 1; k++) {
            goodimg[k] = null;
        }
        getGoodstableList().removeIf(Objects::isNull);
        getGoodstableList().removeIf(e -> (e.getUsetime() == 0));
        for (int i = this.page * 12; i <= getGoodstableList().size() - 1; i++) {
            if (getGoodstableList().get(i) != null && i - 12 * this.page < 12) {
                goodimg[i - 12 * this.page] = GoodsListFromServerUntil.imgpath(((Goodstable) getGoodstableList().get(i)).getSkin());
            }
        }
    }


    public void update() {
        for (int k = 0; k <= goodimg.length - 1; k++) {
            goodimg[k] = null;
        }
        getGoodstableList().removeIf(Objects::isNull);
        getGoodstableList().removeIf(e -> (e.getUsetime() == 0));
        for (int i = this.page * 12; i <= getGoodstableList().size() - 1; i++) {
            if (getGoodstableList().get(i) != null && i - 12 * this.page < 12) {
                goodimg[i - 12 * this.page] = GoodsListFromServerUntil.imgpath(getGoodstableList().get(i).getSkin());
            }
        }
    }


    public void effect(int place) {
        if (place != -1) {
            effect.setBounds(this.GoodsListLabel[place].getX(), this.GoodsListLabel[place].getY(), 50, 50);
        } else {
            effect.setBounds(1000, 1000, 52, 52);
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.ico != null){
            g.drawImage(this.ico.getImage(), 0, 0, 390, 238, null);
        }
        for (int i = 0; i < 12; i++) {
            int row = i % 6 * 51;
            int col = i / 6 * 51;
            if (goodimg[i] != null) {
                g.drawImage(goodimg[i].getImage(), 38 + row + 35 + 16, 28 + col + 24 + 6, 45, 45, null);
                g.setFont(new Font("宋体", 0, 16));
                g.setColor(new Color(188, 188, 188));
                g.drawString("" + getGoodstableList().get(i).getUsetime(), 38 + row + 35 + 16, 28 + col + 24 + 6 + 12);
                if (getGoodstableList().get(i).getGoodlock() == 1) {
                    g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 10 + row + 2 + 6, 10 + col + 1 + 6, 10, 12, null);
                }
            }
        }
        g.setColor(Color.black);
        g.setFont(UIUtils.TEXT_NAME_FONT);
        g.drawString(this.page + "/4", 375, 330);
        g.setColor(Color.black);
        g.setFont(UIUtils.TEXT_HY193);
        if (this.text != null){
            g.drawString(this.text, 140, 40);
        }
    }


    public List<Goodstable> getGoodstableList() {
        return this.goodstableList;
    }

}


