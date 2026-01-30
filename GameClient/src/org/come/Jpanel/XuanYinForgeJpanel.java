package org.come.Jpanel;

import com.tool.btn.DazaoBtn;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.goodbtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

import java.awt.*;
import java.math.BigDecimal;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import org.come.mouslisten.TransmuteMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;

public class XuanYinForgeJpanel extends ForgeJpanel {
    private BigDecimal money1 = new BigDecimal(100000);
    private DazaoBtn btndazao;
    private JList<String> listEquipment;
    private DefaultListModel<String> modelTrade;
    private DefaultListModel<String> modelname;
    private Color fontcolor;
    private JLabel[] topTitle;
    private ImageIcon jiemianimg;
    private JLabel[] GoodsListLabel = new JLabel[26];
    private String type = "我要合成玄印";
    private boolean shoptext = false;
    private int goodPosition;
    private TransmuteMouslisten[] transmuteMouslistens = new TransmuteMouslisten[26];
    private goodbtn[] btnrights;
    private goodbtn[] btn_linked;
    private int Flag = 0;
    private int count = 1;

    public XuanYinForgeJpanel() throws Exception {
        // 只保留水墨UI样式，移除其他皮肤样式的代码
        this.setPreferredSize(new Dimension(640, 445));
        this.setLayout(null);
        this.setOpaque(false);

        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/old/2/1.png", 1, 26);
        offBtn.setBounds(640 - 37, 10, 25, 25);
        this.add(offBtn);

        btn_linked = new goodbtn[6];
        for (int i = 0; i < btn_linked.length; i++) {
            btn_linked[i] = new goodbtn("skin/8100226/newbutton/C0"+(i+1)+".png", 0, this, i);
            btn_linked[i].setBounds(353, 174 + i * 35, 35, 31);
            this.add(btn_linked[i]);
        }

        // 操作栏上的字
        topTitle = new JLabel[2];
        for (int i = 0; i < topTitle.length; i++) {
            topTitle[i] = new JLabel();
            topTitle[i].setBounds(93 + i * 134, 15, 56 + i * 34, 20);
            topTitle[i].setHorizontalAlignment(SwingConstants.CENTER);
            topTitle[i].setFont(UIUtils.TEXT_FONT1);
            topTitle[i].setForeground(Color.white);
            topTitle[i].setText("玄印");
            this.add(topTitle[i]);
        }

        // 存放物品的表格1~26
        for (int i = 0; i < 26; i++) {
            GoodsListLabel[i] = new JLabel();
            transmuteMouslistens[i] = new TransmuteMouslisten(i, this);
            GoodsListLabel[i].addMouseListener(transmuteMouslistens[i]);
            if (i < 24) {
                GoodsListLabel[i].setBounds(16 + 28 + i % 6 * 51, 175 + i / 6 * 51, 48, 48);
                this.add(GoodsListLabel[i]);
            } else {
                GoodsListLabel[i].setBounds(96 + i % 6 * 150, 40, 55, 51);
                this.add(GoodsListLabel[i]);
            }
        }

        btndazao = new DazaoBtn("skin/8100226/newbutton/B21.png", 1, UIUtils.COLOR_WHITE, "合成", UIUtils.TEXT_HY16, this);
        btndazao.setBounds(266, 123, 79, 24);
        this.add(btndazao);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 只保留水墨UI样式
        if (jiemianimg == null) {
            jiemianimg = new ImageIcon("inkImg/background/shengjixuanyin.png"); // 使用你提供的新背景图
        }
        g.drawImage(jiemianimg.getImage(), 0, 0, 640, 445, this); // 设置正确的大小

        // 左侧升级区域 - 3个物品槽位
        // 上方两个槽位
        int slotWidth = 70;
        int slotHeight = 70;
        int slotGap = 10;

        // 上左槽位
        g.setColor(new Color(255, 255, 255));
        g.drawRect(100, 80, slotWidth, slotHeight);

        // 上右槽位
        g.drawRect(190, 80, slotWidth, slotHeight);

        // 下中槽位
        g.drawRect(145, 160, slotWidth, slotHeight);

        // 显示消耗和拥有信息
        Font font = new Font("宋体", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.WHITE);

        // 消耗体力
        g.drawString("消耗体力", 100, 240);
        g.drawString("拥有体力", 100, 270);

        // 消耗金钱
        g.drawString("消耗金钱", 100, 300);
        g.drawString("拥有金钱", 100, 330);

        // 右侧背包区域 - 10x6网格
        int backpackSlotWidth = 50;
        int backpackSlotHeight = 50;
        int backpackStartX = 350;
        int backpackStartY = 50;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 10; col++) {
                int x = backpackStartX + col * (backpackSlotWidth + 5);
                int y = backpackStartY + row * (backpackSlotHeight + 5);

                g.setColor(new Color(255, 255, 255));
                g.drawRect(x, y, backpackSlotWidth, backpackSlotHeight);

                // 显示物品图标
                int index = row * 10 + col;
                if (index < GoodsListLabel.length && GoodsListLabel[index] != null && GoodsListLabel[index].getIcon() != null) {
                    // 使用 Icon 的 paintIcon 方法来绘制图标
                    GoodsListLabel[index].getIcon().paintIcon(this, g, x, y);
                }
            }
        }
    }

    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }

    public void ClearText() {
        this.shoptext = false;
    }

    public void remove() {
        TransmuteMouslisten.goods2[0] = null;
        TransmuteMouslisten.goods2[1] = null;
        this.GoodsListLabel[24].setIcon(null);
        this.GoodsListLabel[25].setIcon(null);
    }

    public JList<String> getListEquipment() {
        return this.listEquipment;
    }

    public void setListEquipment(JList<String> listEquipment) {
        this.listEquipment = listEquipment;
    }

    public DefaultListModel<String> getModelTrade() {
        return this.modelTrade;
    }

    public void setModelTrade(DefaultListModel<String> modelTrade) {
        this.modelTrade = modelTrade;
    }

    public DefaultListModel<String> getModelname() {
        return this.modelname;
    }

    public void setModelname(DefaultListModel<String> modelname) {
        this.modelname = modelname;
    }

    public Color getFontcolor() {
        return this.fontcolor;
    }

    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }

    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }

    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }

    public int getFlag() {
        return this.Flag;
    }

    public void setFlag(int flag) {
        this.Flag = flag;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isShoptext() {
        return this.shoptext;
    }

    public void setShoptext(boolean shoptext) {
        this.shoptext = shoptext;
    }

    public int getGoodPosition() {
        return this.goodPosition;
    }

    public void setGoodPosition(int goodPosition) {
        this.goodPosition = goodPosition;
    }

    public TransmuteMouslisten[] getTransmuteMouslistens() {
        return this.transmuteMouslistens;
    }

    public void setTransmuteMouslistens(TransmuteMouslisten[] transmuteMouslistens) {
        this.transmuteMouslistens = transmuteMouslistens;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DazaoBtn getBtndazao() {
        return this.btndazao;
    }

    public void setBtndazao(DazaoBtn btndazao) {
        this.btndazao = btndazao;
    }

    public ImageIcon getJiemianimg() {
        return this.jiemianimg;
    }

    public void setJiemianimg(ImageIcon jiemianimg) {
        this.jiemianimg = jiemianimg;
    }

    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }

    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }

    public BigDecimal getMoney1() {
        return this.money1;
    }

    public void setMoney1(BigDecimal money1) {
        this.money1 = money1;
    }

    public JLabel[] getTopTitle() {
        return this.topTitle;
    }

    public void setTopTitle(JLabel[] topTitle) {
        this.topTitle = topTitle;
    }
}
