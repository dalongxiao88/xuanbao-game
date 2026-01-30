package org.come.Jpanel;

import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseListener;
import com.tool.tcp.SpriteFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.image.ImageMixDeal;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import java.util.Iterator;
import java.util.Set;
import org.come.until.JTreeData;
import java.util.ArrayList;
import java.util.Map;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import com.tool.btn.SummoningCompoundBtn;
import org.come.model.petExchange;
import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import com.tool.tcpimg.RichLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SummoningCompoundJpanel extends JPanel
{
    private JLabel labOne;
    private JLabel labTwo;
    private JScrollPane scpSummoningList;
    private JScrollPane scpSummoningInitialValue;
    private JScrollPane scpSummoningEndValue;
    private JTree jTreeSummoningList;
    private DefaultMutableTreeNode nodeSummoningList;
    private RichLabel richInitialValue;
    private RichLabel richEndValue;
    private RichLabel richOneNum;
    private RichLabel richTwoNum;
    private BigDecimal money;
    private List<Goodstable> goodsList;
    private petExchange petExchange;
    private SummoningCompoundBtn btnCompound;
    private MLintener mlrOne;
    private MLintener mlrTwo;
    private ImageIcon icon;
    private NewPart part;
    private int oneNum;
    private int twoNum;
    private int num;
    
    public SummoningCompoundJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(525, 408));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 36);
            offBtn.setBounds(488, 10, 25, 25);
            this.add(offBtn);
            this.getRichTwoNum();
            this.getRichOneNum();
            this.getLabOne();
            this.getLabTwo();
            this.getScpSummoningList();
            this.getScpSummoningEndValue();
            this.getScpSummoningInitialValue();
            this.getBtnCompound();
        }
        else {
            this.setPreferredSize(new Dimension(525, 408));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 36);
            offBtn.setBounds(500, 0, 25, 25);
            this.add(offBtn);
            this.getRichTwoNum();
            this.getRichOneNum();
            this.getLabOne();
            this.getLabTwo();
            this.getScpSummoningList();
            this.getScpSummoningEndValue();
            this.getScpSummoningInitialValue();
            this.getBtnCompound();
        }
    }
    
    public void reSummoningList(Map<Integer, petExchange> map) {
        this.nodeSummoningList.removeAllChildren();
        List<petExchange> mapList = new ArrayList<>();
        Set<Map.Entry<Integer, petExchange>> entrySet = map.entrySet();
        LOOP: for (Map.Entry<Integer, petExchange> entry : entrySet) {
          petExchange petExchange1 = entry.getValue();
          if (petExchange1.getType().equals("召唤兽分解"))
            continue; 
          for (int j = 0; j < mapList.size(); j++) {
            if (((petExchange)mapList.get(j)).geteId() > petExchange1.geteId()) {
              mapList.add(j, petExchange1);
              continue LOOP;
            } 
          } 
          mapList.add(petExchange1);
        } 
        int i;
        LOOP: for (i = 0; i < mapList.size(); i++) {
          petExchange petExchange1 = mapList.get(i);
          int childCount = this.nodeSummoningList.getChildCount();
          for (int j = 0; j < childCount; j++) {
            DefaultMutableTreeNode childAt = (DefaultMutableTreeNode)this.nodeSummoningList.getChildAt(j);
            if (petExchange1.getType().equals(childAt.toString())) {
              DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode(new JTreeData(petExchange1.getName(), String.valueOf(petExchange1.geteId())));
              childAt.add(defaultMutableTreeNode1);
              continue LOOP;
            } 
          } 
          DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(petExchange1.getType());
          defaultMutableTreeNode.add(new DefaultMutableTreeNode(new JTreeData(petExchange1.getName(), 
                  String.valueOf(petExchange1.geteId()))));
          this.nodeSummoningList.add(defaultMutableTreeNode);
        }
    }
    
    public void ShowInitialValue(petExchange exchange) {
        StringBuffer buffer = new StringBuffer();
        if (exchange != null) {
            buffer.append("成长率:");
            buffer.append(exchange.getGrow());
            buffer.append("#r");
            buffer.append("血初值:");
            buffer.append(exchange.getHp());
            buffer.append("#r");
            buffer.append("法初值:");
            buffer.append(exchange.getMp());
            buffer.append("#r");
            buffer.append("功初值:");
            buffer.append(exchange.getAp());
            buffer.append("#r");
            buffer.append("敏初值:");
            buffer.append(exchange.getSp());
            buffer.append("#r");
            buffer.append("五行:");
            buffer.append(exchange.getFive());
            buffer.append("#r");
        }
        this.richInitialValue.setText(buffer.toString());
        Dimension d = this.richInitialValue.computeSize(123);
        this.richInitialValue.setSize(d);
        this.richInitialValue.setPreferredSize(d);
    }
    
    public void ShowEndValue(petExchange exchange) {
        StringBuffer buffer = new StringBuffer();
        if (exchange != null) {
            String skill = exchange.getSkill();
            if (skill != null && !"".equals(skill)) {
                String[] split = skill.split("\\|");
                for (int i = 0; i < split.length; ++i) {
                    Skill skillOne = UserMessUntil.getSkillId(split[i]);
                    buffer.append(skillOne.getSkillname());
                    buffer.append("#r");
                    this.richEndValue.setText(buffer.toString());
                    Dimension d = this.richEndValue.computeSize(123);
                    this.richEndValue.setSize(d);
                    this.richEndValue.setPreferredSize(d);
                }
                return;
            }
        }
        this.richEndValue.setText(buffer.toString());
        Dimension d2 = this.richEndValue.computeSize(123);
        this.richEndValue.setSize(d2);
        this.richEndValue.setPreferredSize(d2);
    }
    
    public void ShowConsume(petExchange exchange) {
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
                            this.labOne.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
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
                            this.labTwo.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
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
                        ++num;
                    }
                }
            }
        }
    }
    
    public void clearConsume() {
        this.labOne.setIcon(null);
        this.labTwo.setIcon(null);
        this.richOneNum.setText("");
        this.richTwoNum.setText("");
        this.mlrTwo.setGrid(null);
        this.mlrTwo.setNeedNum(0);
        this.mlrOne.setGrid(null);
        this.mlrOne.setNeedNum(0);
        this.money = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B295.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 525, 408, null);
            Util.drawMoney(g, 396, 184);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 396, 150);
            }
            if (this.part != null) {
                this.part.draw(g, 300, 201, 0, ImageMixDeal.userimg.getTime());
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
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S165.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 525, 408, null);
            Util.drawMoney(g, 407, 189);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 407, 159);
            }
            if (this.part != null) {
                this.part.draw(g, 300, 201, 0, ImageMixDeal.userimg.getTime());
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
            }
        }
        ++this.num;
    }
    
    public JLabel getLabOne() {
        if (this.labOne == null) {
            this.labOne = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labOne.setBounds(363, 70, 50, 50);
            }
            else {
                this.labOne.setBounds(375, 70, 50, 50);
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
                this.labTwo.setBounds(436, 70, 50, 50);
            }
            else {
                this.labTwo.setBounds(450, 70, 50, 50);
            }
            this.mlrTwo = new MLintener(null);
            this.labTwo.addMouseListener(this.mlrTwo);
            this.add(this.labTwo);
        }
        return this.labTwo;
    }
    
    public void setLabTwo(JLabel labTwo) {
        this.labTwo = labTwo;
    }
    
    public JScrollPane getScpSummoningList() {
        if (this.scpSummoningList == null) {
            (this.scpSummoningList = new JScrollPane(this.getjTreeSummoningList())).setVerticalScrollBarPolicy(22);
            this.scpSummoningList.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scpSummoningList.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningList.getViewport().setOpaque(false);
            this.scpSummoningList.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scpSummoningList.setBounds(47, 52, 164, 333);
            }
            else {
                this.scpSummoningList.setBounds(38, 75, 161, 310);
            }
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
            this.scpSummoningInitialValue.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scpSummoningInitialValue.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningInitialValue.getViewport().setOpaque(false);
            this.scpSummoningInitialValue.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scpSummoningInitialValue.setBounds(219, 267, 138, 119);
            }
            else {
                this.scpSummoningInitialValue.setBounds(223, 268, 126, 116);
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
            this.scpSummoningEndValue.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scpSummoningEndValue.getVerticalScrollBar().setUnitIncrement(20);
            this.scpSummoningEndValue.getViewport().setOpaque(false);
            this.scpSummoningEndValue.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scpSummoningEndValue.setBounds(364, 267, 138, 119);
            }
            else {
                this.scpSummoningEndValue.setBounds(373, 266, 128, 118);
            }
            this.scpSummoningEndValue.setBorder(BorderFactory.createEmptyBorder());
            this.scpSummoningEndValue.setHorizontalScrollBarPolicy(31);
            this.add(this.scpSummoningEndValue);
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
                cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]);
                cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]);
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
                        TreePath path = SummoningCompoundJpanel.this.jTreeSummoningList.getSelectionPath();
                        if (path != null) {
                            if (SummoningCompoundJpanel.this.jTreeSummoningList.isExpanded(path)) {
                                SummoningCompoundJpanel.this.jTreeSummoningList.collapsePath(path);
                            }
                            else {
                                SummoningCompoundJpanel.this.jTreeSummoningList.expandPath(path);
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
                        SummoningCompoundJpanel.this.scpSummoningList.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        JTreeData treeData = (JTreeData)selectionNode.getUserObject();
                        int parseInt = Integer.parseInt(treeData.getPath());
                        petExchange exchange = UserMessUntil.getPetExchange(Integer.valueOf(parseInt));
                        SummoningCompoundJpanel.this.petExchange = exchange;
                        SummoningCompoundJpanel.this.part = SpriteFactory.createPart(exchange.getSkin(), 5, 1, null);
                        SummoningCompoundJpanel.this.ShowInitialValue(exchange);
                        SummoningCompoundJpanel.this.ShowEndValue(exchange);
                        SummoningCompoundJpanel.this.ShowConsume(exchange);
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
            this.reSummoningList(UserMessUntil.getAllPetExchange().getAllPetExchange());
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
                this.richOneNum.setBounds(368, 118, 50, 16);
            }
            else {
                this.richOneNum.setBounds(370, 120, 50, 16);
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
                this.richTwoNum.setBounds(441, 118, 50, 16);
            }
            else {
                this.richTwoNum.setBounds(443, 120, 50, 16);
            }
            this.add(this.richTwoNum);
        }
        return this.richTwoNum;
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
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    public petExchange getPetExchange() {
        return this.petExchange;
    }
    
    public void setPetExchange(petExchange petExchange) {
        this.petExchange = petExchange;
    }
    
    public SummoningCompoundBtn getBtnCompound() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnCompound == null) {
                (this.btnCompound = new SummoningCompoundBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, "合成", UIUtils.TEXT_HY16, 1, this)).setBounds(392, 198, 59, 24);
                this.add(this.btnCompound);
            }
        }
        else if (this.btnCompound == null) {
            (this.btnCompound = new SummoningCompoundBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "合成", UIUtils.TEXT_HY88, 1, this)).setBounds(420, 210, 60, 26);
            this.add(this.btnCompound);
        }
        return this.btnCompound;
    }
    
    public void setBtnCompound(SummoningCompoundBtn btnCompound) {
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
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
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
