package org.come.Jpanel;

import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.util.Iterator;
import java.util.Set;
import org.come.until.JTreeData;
import java.util.ArrayList;
import java.util.Map;
import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.experimentCompoundBtn;
import org.come.model.ItemExchange;
import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import com.tool.tcpimg.RichLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import org.come.until.Goodtype;
import javax.swing.JPanel;

public class experimentCompoundJpanel extends JPanel
{
    private Goodtype goodtype;
    private JLabel labOne;
    private JLabel labTwo;
    private JLabel labThree;
    private JLabel laba;
    private JLabel labb;
    private JLabel labc;
    private JLabel labd;
    private JScrollPane scpSummoningList;
    private JScrollPane scpSummoningInitialValue;
    private JScrollPane scpSummoningEndValue;
    private JTree jTreeSummoningList;
    private DefaultMutableTreeNode nodeSummoningList;
    private RichLabel richInitialValue;
    private RichLabel richEndValue;
    private RichLabel richOneNum;
    private RichLabel richTwoNum;
    private RichLabel richa;
    private RichLabel richb;
    private RichLabel richc;
    private RichLabel richd;
    private BigDecimal money;
    private List<Goodstable> goodsList;
    private String valuest;
    private ItemExchange ItemExchange;
    private experimentCompoundBtn btnCompound;
    private experimentCompoundBtn btnCompoundYI;
    private MLintener mlrOne;
    private MLintener mlrTwo;
    private MLintener mlrThree;
    private MLintener mlra;
    private MLintener mlrb;
    private MLintener mlrc;
    private MLintener mlrd;
    private ImageIcon icon;
    private int oneNum;
    private int twoNum;
    private int num;
    private int a;
    private int b;
    private int c;
    private int d;
    
    public experimentCompoundJpanel() {
        this.setPreferredSize(new Dimension(525, 408));
        this.setOpaque(false);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 700);
            offBtn.setBounds(488, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 700);
            offBtn.setBounds(500, 0, 25, 25);
            this.add(offBtn);
        }
        this.getRichTwoNum();
        this.getRichOneNum();
        this.getRicha();
        this.getRichb();
        this.getRichc();
        this.getRichd();
        this.getLabOne();
        this.getLabTwo();
        this.getLaba();
        this.getLabb();
        this.getLabc();
        this.getLabd();
        this.getLabThree();
        this.getScpSummoningList();
        this.getScpSummoningEndValue();
        this.getScpSummoningInitialValue();
        this.getBtnCompound();
        this.getBtnCompoundYI();
    }
    
    public void reSummoningList(Map<Integer, ItemExchange> map) {
        this.nodeSummoningList.removeAllChildren();
        List<ItemExchange> mapList = new ArrayList<>();
        Set<Map.Entry<Integer, ItemExchange>> entrySet = map.entrySet();
    LOOP:
        for (Map.Entry<Integer, ItemExchange> entry : entrySet) {
            ItemExchange ItemExchange = (ItemExchange)entry.getValue();
            int i = 0;
            while (i < mapList.size()) {
                if (((ItemExchange)mapList.get(i)).geteId() > ItemExchange.geteId()) {
                    mapList.add(i, ItemExchange);
                    continue LOOP;
                }
                else {
                    ++i;
                }
            }
            mapList.add(ItemExchange);
        }
	    int i;
	    LOOP: for (i = 0; i < mapList.size(); i++) {
	      ItemExchange itemExchange = mapList.get(i);
	      int childCount = this.nodeSummoningList.getChildCount();
	      for (int j = 0; j < childCount; j++) {
	        DefaultMutableTreeNode childAt = (DefaultMutableTreeNode)this.nodeSummoningList.getChildAt(j);
	        if (itemExchange.getType().equals(childAt.toString())) {
	          DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode(new JTreeData(itemExchange.getGoodsname(), String.valueOf(itemExchange.geteId())));
	          childAt.add(defaultMutableTreeNode1);
	          continue LOOP;
	        } 
	      } 
	      DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(itemExchange.getType());
	      defaultMutableTreeNode.add(new DefaultMutableTreeNode(new JTreeData(itemExchange.getGoodsname(), 
	              String.valueOf(itemExchange.geteId()))));
	      this.nodeSummoningList.add(defaultMutableTreeNode);
	    } 
    }
    
    public void ShowInitialValue(ItemExchange exchange) {
        StringBuffer buffer = new StringBuffer();
        if (exchange != null) {
            buffer.append(exchange.getInstruction());
            buffer.append("#r");
        }
        this.richInitialValue.setText(buffer.toString());
        Dimension d = this.richInitialValue.computeSize(105);
        this.richInitialValue.setSize(d);
        this.richInitialValue.setPreferredSize(d);
    }
    
    public void ShowConsume(ItemExchange exchange) {
        this.clearConsume();
        if (exchange != null) {
            String consume = exchange.getConsume();
            if (consume != null && !"".equals(consume)) {
                String[] split = consume.split("\\|");
                int num = 0;
                for (int i = 0; i < split.length; ++i) {
                    if (split[i].startsWith("D")) {
                        String[] arrMoney = split[i].split("=");
                        this.money = new BigDecimal(arrMoney[1]);
                    }
                    else if (split[i].startsWith("G")) {
                        String[] arrMoney = split[i].split("=");
                        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(arrMoney[1]));
                        int needMum = Integer.parseInt(arrMoney[2]);
                        if (num == 0) {
                            this.labOne.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            this.mlrOne.setGrid(goodstable.getGoodsid());
                            this.mlrOne.setNeedNum(needMum);
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            if (needMum > goodNum) {
                                this.richOneNum.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richOneNum.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        else if (num == 1) {
                            this.labTwo.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            this.mlrTwo.setGrid(goodstable.getGoodsid());
                            this.mlrTwo.setNeedNum(needMum);
                            if (needMum > goodNum) {
                                this.richTwoNum.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richTwoNum.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        else if (num == 2) {
                            this.laba.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            this.mlra.setGrid(goodstable.getGoodsid());
                            this.mlra.setNeedNum(needMum);
                            if (needMum > goodNum) {
                                this.richa.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richa.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        else if (num == 3) {
                            this.labb.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            this.mlrb.setGrid(goodstable.getGoodsid());
                            this.mlrb.setNeedNum(needMum);
                            if (needMum > goodNum) {
                                this.richb.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richb.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        else if (num == 4) {
                            this.labc.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            this.mlrc.setGrid(goodstable.getGoodsid());
                            this.mlrc.setNeedNum(needMum);
                            if (needMum > goodNum) {
                                this.richc.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richc.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        else if (num == 5) {
                            this.labd.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50));
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            this.mlrd.setGrid(goodstable.getGoodsid());
                            this.mlrd.setNeedNum(needMum);
                            if (needMum > goodNum) {
                                this.richd.setText("#R" + goodNum + "#c228B22/" + needMum);
                            }
                            else {
                                this.richd.setText("#c228B22" + goodNum + "/" + needMum);
                            }
                        }
                        ++num;
                    }
                }
            }
            this.labThree.setIcon(GoodsListFromServerUntil.imgpathAdaptive(exchange.getSkin(), 100, 100));
            int goodNum2 = GoodsListFromServerUntil.getGoodNum(exchange.getGoods());
            this.mlrThree.setGrid(exchange.getGoods());
            this.mlrThree.setNeedNum(exchange.geteId());
        }
    }
    
    public void clearConsume() {
        this.labOne.setIcon(null);
        this.labTwo.setIcon(null);
        this.labThree.setIcon(null);
        this.laba.setIcon(null);
        this.labb.setIcon(null);
        this.labc.setIcon(null);
        this.labd.setIcon(null);
        this.richOneNum.setText("");
        this.richa.setText("");
        this.richb.setText("");
        this.richc.setText("");
        this.richd.setText("");
        this.richTwoNum.setText("");
        this.mlrTwo.setGrid(null);
        this.mlrTwo.setNeedNum(0);
        this.mlrThree.setGrid(null);
        this.mlrThree.setNeedNum(0);
        this.mlrOne.setGrid(null);
        this.mlrOne.setNeedNum(0);
        this.mlra.setGrid(null);
        this.mlra.setNeedNum(0);
        this.mlrb.setGrid(null);
        this.mlrb.setNeedNum(0);
        this.mlrc.setGrid(null);
        this.mlrc.setNeedNum(0);
        this.mlrd.setGrid(null);
        this.mlrd.setNeedNum(0);
        this.money = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.icon = new ImageIcon("inkImg/background1/B296.png");
            }
            else {
                this.icon = new ImageIcon("img/xy2uiimg/S16511.png");
            }
        }
        g.drawImage(this.icon.getImage(), 0, 0, 525, 408, null);
        if (MyIsif.getStyle().equals("水墨")) {
            Util.drawMoney(g, 396, 200);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 396, 174);
            }
        }
        else {
            Util.drawMoney(g, 410, 218);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 410, 189);
            }
        }
        if (this.num > 100) {
            this.num = 0;
            if (this.mlrOne.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlrOne.getGrid());
                if (goodNum != this.oneNum) {
                    this.oneNum = goodNum;
                    int needNum = this.mlrOne.getNeedNum();
                    if (needNum > goodNum) {
                        this.richOneNum.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richOneNum.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
            if (this.mlrTwo.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlrTwo.getGrid());
                if (goodNum != this.twoNum) {
                    this.twoNum = goodNum;
                    int needNum = this.mlrTwo.getNeedNum();
                    if (needNum > goodNum) {
                        this.richTwoNum.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richTwoNum.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
            if (this.mlra.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlra.getGrid());
                if (goodNum != this.a) {
                    this.a = goodNum;
                    int needNum = this.mlra.getNeedNum();
                    if (needNum > goodNum) {
                        this.richa.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richa.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
            if (this.mlrb.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlrTwo.getGrid());
                if (goodNum != this.b) {
                    this.b = goodNum;
                    int needNum = this.mlrb.getNeedNum();
                    if (needNum > goodNum) {
                        this.richb.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richb.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
            if (this.mlrc.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlrTwo.getGrid());
                if (goodNum != this.c) {
                    this.c = goodNum;
                    int needNum = this.mlrc.getNeedNum();
                    if (needNum > goodNum) {
                        this.richc.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richc.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
            if (this.mlrd.getGrid() != null) {
                int goodNum = GoodsListFromServerUntil.getGoodNum(this.mlrd.getGrid());
                if (goodNum != this.d) {
                    this.d = goodNum;
                    int needNum = this.mlrd.getNeedNum();
                    if (needNum > goodNum) {
                        this.richd.setText("#R" + goodNum + "#c228B22/" + needNum);
                    }
                    else {
                        this.richd.setText("#c228B22" + goodNum + "/" + needNum);
                    }
                }
            }
        }
        ++this.num;
    }
    
    public JLabel getLabOne() {
        if (this.labOne == null) {
            this.labOne = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labOne.setBounds(230, 45, 50, 50);
            }
            else {
                this.labOne.setBounds(230, 55, 50, 50);
            }
            this.mlrOne = new MLintener(null);
            this.labOne.addMouseListener(this.mlrOne);
            this.add(this.labOne);
        }
        return this.labOne;
    }
    
    public void setLabOne(JLabel labOne) {
        this.labOne = labOne;
    }
    
    public JLabel getLabTwo() {
        if (this.labTwo == null) {
            this.labTwo = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labTwo.setBounds(295, 45, 50, 50);
            }
            else {
                this.labTwo.setBounds(295, 55, 50, 50);
            }
            this.mlrTwo = new MLintener(null);
            this.labTwo.addMouseListener(this.mlrTwo);
            this.add(this.labTwo);
        }
        return this.labTwo;
    }
    
    public void setLabThree(JLabel labThree) {
        this.labThree = labThree;
    }
    
    public JLabel getLabThree() {
        if (this.labThree == null) {
            this.labThree = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labThree.setBounds(382, 48, 100, 100);
            }
            else {
                this.labThree.setBounds(382, 58, 100, 100);
            }
            this.mlrThree = new MLintener(null);
            this.labThree.addMouseListener(this.mlrThree);
            this.add(this.labThree);
        }
        return this.labThree;
    }
    
    public JLabel getLaba() {
        if (this.laba == null) {
            this.laba = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.laba.setBounds(230, 105, 50, 50);
            }
            else {
                this.laba.setBounds(230, 120, 50, 50);
            }
            this.mlra = new MLintener(null);
            this.laba.addMouseListener(this.mlra);
            this.add(this.laba);
        }
        return this.laba;
    }
    
    public void setLaba(JLabel laba) {
        this.laba = laba;
    }
    
    public JLabel getLabb() {
        if (this.labb == null) {
            this.labb = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labb.setBounds(295, 105, 50, 50);
            }
            else {
                this.labb.setBounds(295, 120, 50, 50);
            }
            this.mlrb = new MLintener(null);
            this.labb.addMouseListener(this.mlrb);
            this.add(this.labb);
        }
        return this.labb;
    }
    
    public void setLabb(JLabel labb) {
        this.labb = labb;
    }
    
    public JLabel getLabc() {
        if (this.labc == null) {
            this.labc = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labc.setBounds(230, 163, 50, 50);
            }
            else {
                this.labc.setBounds(230, 185, 50, 50);
            }
            this.mlrc = new MLintener(null);
            this.labc.addMouseListener(this.mlrc);
            this.add(this.labc);
        }
        return this.labc;
    }
    
    public void setLabc(JLabel labc) {
        this.labc = labc;
    }
    
    public JLabel getLabd() {
        if (this.labd == null) {
            this.labd = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labd.setBounds(295, 163, 50, 50);
            }
            else {
                this.labd.setBounds(295, 185, 50, 50);
            }
            this.mlrd = new MLintener(null);
            this.labd.addMouseListener(this.mlrd);
            this.add(this.labd);
        }
        return this.labd;
    }
    
    public void setLabd(JLabel labd) {
        this.labd = labd;
    }
    
    public void setLabTwo(JLabel labTwo) {
        this.labTwo = labTwo;
    }
    
    public JScrollPane getScpSummoningList() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scpSummoningList == null) {
                (this.scpSummoningList = new JScrollPane(this.getjTreeSummoningList())).setVerticalScrollBarPolicy(22);
                this.scpSummoningList.getVerticalScrollBar().setUI(new ScrollUI());
                this.scpSummoningList.getVerticalScrollBar().setUnitIncrement(20);
                this.scpSummoningList.getViewport().setOpaque(false);
                this.scpSummoningList.setOpaque(false);
                this.scpSummoningList.setBounds(51, 52, 160, 333);
                this.scpSummoningList.setBorder(BorderFactory.createEmptyBorder());
                this.scpSummoningList.setHorizontalScrollBarPolicy(31);
                this.add(this.scpSummoningList);
            }
        }
        else if (this.scpSummoningList == null) {
            (this.scpSummoningList = new JScrollPane(this.getjTreeSummoningList())).setVerticalScrollBarPolicy(22);
            this.scpSummoningList.getVerticalScrollBar().setUI(new ScrollUI());
            this.scpSummoningList.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningList.getViewport().setOpaque(false);
            this.scpSummoningList.setOpaque(false);
            this.scpSummoningList.setBounds(25, 66, 178, 320);
            this.scpSummoningList.setBorder(BorderFactory.createEmptyBorder());
            this.scpSummoningList.setHorizontalScrollBarPolicy(31);
            this.add(this.scpSummoningList);
        }
        return this.scpSummoningList;
    }
    
    public void setScpSummoningList(JScrollPane scpSummoningList) {
        this.scpSummoningList = scpSummoningList;
    }
    
    public JScrollPane getScpSummoningInitialValue() {
        if (this.scpSummoningInitialValue == null) {
            (this.scpSummoningInitialValue = new JScrollPane(this.getRichInitialValue())).setVerticalScrollBarPolicy(22);
            this.scpSummoningInitialValue.getVerticalScrollBar().setUI(new ScrollUI());
            this.scpSummoningInitialValue.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningInitialValue.getViewport().setOpaque(false);
            this.scpSummoningInitialValue.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scpSummoningInitialValue.setBounds(223, 266, 279, 118);
            }
            else {
                this.scpSummoningInitialValue.setBounds(221, 276, 276, 115);
            }
            this.scpSummoningInitialValue.setBorder(BorderFactory.createEmptyBorder());
            this.scpSummoningInitialValue.setHorizontalScrollBarPolicy(31);
            this.add(this.scpSummoningInitialValue);
        }
        return this.scpSummoningInitialValue;
    }
    
    public void setScpSummoningInitialValue(JScrollPane scpSummoningInitialValue) {
        this.scpSummoningInitialValue = scpSummoningInitialValue;
    }
    
    public JScrollPane getScpSummoningEndValue() {
        if (this.scpSummoningEndValue == null) {
            (this.scpSummoningEndValue = new JScrollPane(this.getRichEndValue())).setVerticalScrollBarPolicy(22);
            this.scpSummoningEndValue.getVerticalScrollBar().setUI(new ScrollUI());
            this.scpSummoningEndValue.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningEndValue.getViewport().setOpaque(false);
            this.scpSummoningEndValue.setOpaque(false);
            this.scpSummoningEndValue.setBounds(376, 277, 121, 115);
            this.scpSummoningEndValue.setBorder(BorderFactory.createEmptyBorder());
            this.scpSummoningEndValue.setHorizontalScrollBarPolicy(31);
        }
        return this.scpSummoningEndValue;
    }
    
    public void setScpSummoningEndValue(JScrollPane scpSummoningEndValue) {
        this.scpSummoningEndValue = scpSummoningEndValue;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JTree getjTreeSummoningList() {
        if (this.jTreeSummoningList == null) {
            (this.jTreeSummoningList = new JTree(this.getNodeSummoningList())).setOpaque(false);
            this.jTreeSummoningList.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTreeSummoningList.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getLevel() == 2) {
                        JTreeData jtd = (JTreeData)node.getUserObject();
                        this.setText(jtd.getString());
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            try {
                cellRenderer.setOpenIcon(null);
                cellRenderer.setClosedIcon(null);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            cellRenderer.setFont(UIUtils.TEXT_FONT1);
            cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTreeSummoningList.setCellRenderer(cellRenderer);
            this.jTreeSummoningList.setRootVisible(false);
            this.jTreeSummoningList.setRowHeight(20);
            this.jTreeSummoningList.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = experimentCompoundJpanel.this.jTreeSummoningList.getSelectionPath();
                        if (path != null) {
                            if (experimentCompoundJpanel.this.jTreeSummoningList.isExpanded(path)) {
                                experimentCompoundJpanel.this.jTreeSummoningList.collapsePath(path);
                            }
                            else {
                                experimentCompoundJpanel.this.jTreeSummoningList.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTreeSummoningList.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (selectionNode == null) {
                        experimentCompoundJpanel.this.scpSummoningList.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        JTreeData treeData = (JTreeData)selectionNode.getUserObject();
                        int parseInt = Integer.parseInt(treeData.getPath());
                        ItemExchange exchange = UserMessUntil.getItemExchange(Integer.valueOf(parseInt));
                        experimentCompoundJpanel.this.ItemExchange = exchange;
                        experimentCompoundJpanel.this.valuest = exchange.getValue();
                        experimentCompoundJpanel.this.ShowInitialValue(exchange);
                        experimentCompoundJpanel.this.ShowConsume(exchange);
                    }
                }
            });
        }
        return this.jTreeSummoningList;
    }
    
    public void setjTreeSummoningList(JTree jTreeSummoningList) {
        this.jTreeSummoningList = jTreeSummoningList;
    }
    
    public DefaultMutableTreeNode getNodeSummoningList() {
        if (this.nodeSummoningList == null) {
            this.nodeSummoningList = new DefaultMutableTreeNode("");
            this.reSummoningList(UserMessUntil.getAllItemExchange().getAllItemExchange());
        }
        return this.nodeSummoningList;
    }
    
    public void setNodeSummoningList(DefaultMutableTreeNode nodeSummoningList) {
        this.nodeSummoningList = nodeSummoningList;
    }
    
    public RichLabel getRichInitialValue() {
        if (this.richInitialValue == null) {
            this.richInitialValue = new RichLabel();
        }
        return this.richInitialValue;
    }
    
    public void setRichInitialValue(RichLabel richInitialValue) {
        this.richInitialValue = richInitialValue;
    }
    
    public RichLabel getRichEndValue() {
        if (this.richEndValue == null) {
            this.richEndValue = new RichLabel();
        }
        return this.richEndValue;
    }
    
    public void setRichEndValue(RichLabel richEndValue) {
        this.richEndValue = richEndValue;
    }
    
    public RichLabel getRichOneNum() {
        if (this.richOneNum == null) {
            this.richOneNum = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richOneNum.setBounds(235, 82, 50, 16);
            }
            else {
                this.richOneNum.setBounds(235, 92, 50, 16);
            }
            this.add(this.richOneNum);
        }
        return this.richOneNum;
    }
    
    public void setRichOneNum(RichLabel richOneNum) {
        this.richOneNum = richOneNum;
    }
    
    public RichLabel getRichTwoNum() {
        if (this.richTwoNum == null) {
            this.richTwoNum = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richTwoNum.setBounds(300, 82, 50, 16);
            }
            else {
                this.richTwoNum.setBounds(300, 92, 50, 16);
            }
            this.add(this.richTwoNum);
        }
        return this.richTwoNum;
    }
    
    public RichLabel getRicha() {
        if (this.richa == null) {
            this.richa = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richa.setBounds(235, 145, 50, 16);
            }
            else {
                this.richa.setBounds(235, 157, 50, 16);
            }
            this.add(this.richa);
        }
        return this.richa;
    }
    
    public void setRicha(RichLabel richa) {
        this.richa = richa;
    }
    
    public RichLabel getRichb() {
        if (this.richb == null) {
            this.richb = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richb.setBounds(300, 145, 50, 16);
            }
            else {
                this.richb.setBounds(300, 157, 50, 16);
            }
            this.add(this.richb);
        }
        return this.richb;
    }
    
    public void setRichb(RichLabel richb) {
        this.richb = richb;
    }
    
    public RichLabel getRichc() {
        if (this.richc == null) {
            this.richc = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richc.setBounds(235, 202, 50, 16);
            }
            else {
                this.richc.setBounds(235, 222, 50, 16);
            }
            this.add(this.richc);
        }
        return this.richc;
    }
    
    public void setRichc(RichLabel richc) {
        this.richc = richc;
    }
    
    public RichLabel getRichd() {
        if (this.richd == null) {
            this.richd = new RichLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.richd.setBounds(300, 202, 50, 16);
            }
            else {
                this.richd.setBounds(300, 222, 50, 16);
            }
            this.add(this.richd);
        }
        return this.richd;
    }
    
    public void setRichd(RichLabel richd) {
        this.richd = richd;
    }
    
    public void setRichTwoNum(RichLabel richTwoNum) {
        this.richTwoNum = richTwoNum;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public List<Goodstable> getGoodsList() {
        return this.goodsList;
    }
    
    public void setGoodsList(List<Goodstable> goodsList) {
        this.goodsList = goodsList;
    }
    
    public ItemExchange getItemExchange() {
        return this.ItemExchange;
    }
    
    public void setItemExchange(ItemExchange ItemExchange) {
        this.ItemExchange = ItemExchange;
    }
    
    public experimentCompoundBtn getBtnCompound() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnCompound == null) {
                (this.btnCompound = new experimentCompoundBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_WHITE, "兑换", UIUtils.TEXT_HYZK, 1, this)).setBounds(362, 205, 51, 17);
                this.add(this.btnCompound);
            }
        }
        else if (this.btnCompound == null) {
            (this.btnCompound = new experimentCompoundBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "兑换", UIUtils.TEXT_HY16, 1, this)).setBounds(362, 230, 60, 26);
            this.add(this.btnCompound);
        }
        return this.btnCompound;
    }
    
    public experimentCompoundBtn getBtnCompoundYI() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnCompoundYI == null) {
                (this.btnCompoundYI = new experimentCompoundBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, "一键兑换", UIUtils.TEXT_HYZK, 2, this)).setBounds(436, 205, 68, 17);
                this.add(this.btnCompoundYI);
            }
        }
        else if (this.btnCompoundYI == null) {
            (this.btnCompoundYI = new experimentCompoundBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "一键", UIUtils.TEXT_HY16, 2, this)).setBounds(436, 230, 60, 26);
            this.add(this.btnCompoundYI);
        }
        return this.btnCompoundYI;
    }
    
    public void setBtnCompound(experimentCompoundBtn btnCompound) {
        this.btnCompound = btnCompound;
    }
    
    class MLintener implements MouseListener
    {
        private BigDecimal grid;
        private int needNum;
        
        public MLintener(BigDecimal grid) {
            this.grid = grid;
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
            Goodstable goodstable = UserMessUntil.getgoodstable(this.grid);
            if (goodstable != null) {
                Goodtype f61 = experimentCompoundJpanel.this.goodtype;
                boolean b = Goodtype.ExerciseMonsterOre((long)goodstable.getType());
                Goodtype f62 = experimentCompoundJpanel.this.goodtype;
                int xs = Goodtype.EquipmentType((long)goodstable.getType());
                if (b || xs != -1 || (long)goodstable.getType() == 520L || (long)goodstable.getType() == 190L) {
                    goodstable.setValue(experimentCompoundJpanel.this.valuest);
                }
                else {
                    goodstable.setValue(null);
                }
                if (goodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            TestpackJframe.getTestpackJframe().getJpac().ClearText();
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        
        public BigDecimal getGrid() {
            return this.grid;
        }
        
        public void setGrid(BigDecimal grid) {
            this.grid = grid;
        }
        
        public int getNeedNum() {
            return this.needNum;
        }
        
        public void setNeedNum(int needNum) {
            this.needNum = needNum;
        }
    }
}
