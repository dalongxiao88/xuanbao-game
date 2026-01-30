package org.come.Jpanel.spot.stall;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.come.until.UserMessUntil;
import javax.swing.tree.TreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import org.come.Jpanel.spot.Commodity;
import javax.swing.JLabel;
import com.tool.Stall.CommodityBean;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.Frame.spot.InputMoneyJframe;
import org.come.until.UserStallUntil;
import org.come.Frame.ZhuFrame;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import org.come.until.CutButtonImage;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.until.StallPurchaseUntil;
import javax.swing.tree.DefaultMutableTreeNode;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import com.tool.btn.spot.SpotPublishBtn;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.come.bean.StallPurchase;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import org.come.Jpanel.spot.commodity.SpotShowJpanel;

public class SpotStallPurchaseJpanel extends SpotStallBaseJpanel
{
    private final SpotShowJpanel spotShowJpanel;
    private final JScrollPane jScrollPane;
    private final JScrollPane jScrollPane2;
    private final JTree jTree;
    private StallPurchase[] detaileds;
    private final JList<String> detailedList;
    private final DefaultListModel<String> detailedModel;
    private final JTextField textSearch;
    private final SpotPublishBtn searchBtn;
    private final SpotPublishBtn btnPurchase;
    
    public SpotStallPurchaseJpanel(SpotBoxJpanel spotBoxJpanel) {
        super(spotBoxJpanel, "inkImg/background/S336.png", "purchase");
        (this.spotShowJpanel = new SpotShowJpanel(this, 4)).setBounds(43, 76, 278, 306);
        this.add(this.spotShowJpanel);
        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("");
        StallPurchaseUntil.showStallPurchaseTree(defaultMutableTreeNode);
        this.jTree = new JTree(defaultMutableTreeNode);
        this.initListTree();
        (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(334, 74, 126, 206);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        this.detailedModel = new DefaultListModel<>();
        this.detailedList = new JList<>();
        this.initListModel();
        (this.jScrollPane2 = new JScrollPane(this.detailedList)).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        this.jScrollPane2.setBounds(464, 74, 166, 176);
        this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
        (this.textSearch = new JTextField()).setBounds(464, 256, 100, 18);
        this.textSearch.setFont(UIUtils.TEXT_FONT1);
        this.textSearch.setOpaque(false);
        this.textSearch.setCaretColor(Color.WHITE);
        this.textSearch.setForeground(Color.WHITE);
        this.textSearch.setBorder(BorderFactory.createEmptyBorder());
        this.textSearch.getDocument().addDocumentListener(new SearchDocumentListener());
        this.add(this.textSearch);
        (this.searchBtn = new SpotPublishBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "搜索", 110, this)).setBounds(570, 252, 59, 25);
        this.add(this.searchBtn);
        (this.btnPurchase = new SpotPublishBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "收购", 111, this)).setBounds(546, 318, 59, 25);
        this.add(this.btnPurchase);
    }
    
    private void initListTree() {
        this.jTree.setOpaque(false);
        this.jTree.putClientProperty("JTree.lineStyle", "None");
        ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
        DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
        cellRenderer.setLeafIcon(null);
        try {
            cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]);
            cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        cellRenderer.setFont(UIUtils.TEXT_FONT);
        cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);
        cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
        cellRenderer.setTextNonSelectionColor(Color.white);
        cellRenderer.setTextSelectionColor(Color.white);
        cellRenderer.setBorderSelectionColor(new Color(99, 93, 90));
        this.jTree.setCellRenderer(cellRenderer);
        this.jTree.setRootVisible(false);
        this.jTree.setRowHeight(18);
        this.jTree.addMouseListener(new ListMouseAdapter());
        this.jTree.addTreeSelectionListener(new ListTreeSelectionListener());
    }
    
    private void initListModel() {
        this.detailedList.setBackground(UIUtils.Color_BACK);
        this.detailedList.setOpaque(false);
        this.detailedList.setFixedCellHeight(18);
        this.detailedList.setFont(UIUtils.TEXT_FONT);
        this.detailedList.setForeground(Color.WHITE);
        this.detailedList.setBackground(UIUtils.Color_BACK);
        this.detailedList.setModel(this.detailedModel);
        this.detailedList.setSelectionMode(0);
        this.detailedList.setSelectionBackground(new Color(99, 93, 90));
        this.detailedList.setSelectionForeground(Color.WHITE);
        this.detailedList.addMouseListener(new DetailedMouslisten());
        this.detailedList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ((JComponent)component).setBorder(BorderFactory.createEmptyBorder());
                return component;
            }
        });
    }
    
    public void showStallDetailedList(StallPurchase[] detaileds) {
        this.detailedModel.removeAllElements();
        this.detaileds = detaileds;
        if (detaileds != null) {
            for (int i = 0; i < detaileds.length; ++i) {
                this.detailedModel.addElement(detaileds[i].getGoodsName());
            }
        }
    }
    
    public void search() {
        this.showStallDetailedList(StallPurchaseUntil.getStallPurchases(this.textSearch.getText()));
    }
    
    @Override
    public void listing() {
        if (this.commodity == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的商品");
            return;
        }
        int sum = this.getNumber();
        if (sum <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少也的卖一个吧");
            return;
        }
        List<CommodityBean> collectGoodsList = UserStallUntil.getStall().getCollectGoodsList();
        if (collectGoodsList != null && collectGoodsList.size() >= 12 && UserStallUntil.getStall().getCommodityByCommodityId(this.commodity.getCommodityId()) == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("最多可收购12个商品");
            return;
        }
        long money = this.getUnitprice();
        if (money <= 0L) {
            InputMoneyJframe.open(this);
            return;
        }
        BigDecimal totalPrice = BigDecimal.valueOf(this.getTotalPrice());
        if (totalPrice.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("余额不足");
            return;
        }
        this.commodity.setState(1);
        this.commodity.setSum(sum);
        this.commodity.setMoney(BigDecimal.valueOf(money));
        SendMessageUntil.toServer(Agreement.getAgreement().listingAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.commodity)));
    }
    
    @Override
    public void withdraw() {
        if (this.commodity.getState() == 1) {
            this.commodity.setState(0);
            UserStallUntil.getStall().removeCommodity(this.commodity);
            SendMessageUntil.toServer(Agreement.getAgreement().listingAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.commodity)));
        }
    }
    
    @Override
    public void updateCommoditys() {
        super.updateCommoditys();
        this.spotShowJpanel.updateCommoditys(UserStallUntil.getStall().getCollectGoodsList(), 1);
    }
    
    @Override
    public void showSelelctBorder(int type, int index) {
    }
    
    @Override
    public void showEnteredBorder(int type, int index) {
    }
    
    @Override
    void setCommodityBounds(JTextField number, JTextField unitprice, JLabel price, JLabel commodity) {
        commodity.setBounds(338, 290, 54, 54);
        number.setBounds(340, 350, 60, 18);
        unitprice.setBounds(438, 294, 100, 18);
        price.setBounds(438, 324, 100, 18);
    }
    
    @Override
    public CommodityBean getCommodity(Commodity commodity) {
        if (commodity != null) {
            BigDecimal commodityId = super.getCommodityId(commodity, 1);
            CommodityBean commodityBean = UserStallUntil.getStall().getCommodityById(commodity.getCommodityId());
            if (commodityBean == null) {
                commodityBean = UserStallUntil.getStall().getCommodityByCommodityId(commodityId);
            }
            if (commodityBean != null) {
                return commodityBean;
            }
            commodityBean = super.getCommodity();
            if (commodityBean != null && commodityId.compareTo(commodityBean.getCommodityId()) == 0) {
                return commodityBean;
            }
            Goodstable goods = (Goodstable)commodity;
            commodityBean = new CommodityBean(goods, 3);
            commodityBean.setCommodityId(goods.getGoodsid());
            commodityBean.setCommodityName(goods.getGoodsname());
            commodityBean.setCommoditySkin(goods.getSkin());
            commodityBean.setCurrency("金钱");
            commodityBean.setSum(1);
            return commodityBean;
        }
        else {
            return null;
        }
    }
    
    public void changePublishBtn(boolean is) {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Util.drawMoney(g, 438, 364);
    }
    
    private class ListMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                TreePath path = SpotStallPurchaseJpanel.this.jTree.getSelectionPath();
                if (path != null) {
                    if (SpotStallPurchaseJpanel.this.jTree.isExpanded(path)) {
                        SpotStallPurchaseJpanel.this.jTree.collapsePath(path);
                    }
                    else {
                        SpotStallPurchaseJpanel.this.jTree.expandPath(path);
                    }
                }
            }
        }
    }
    
    private class ListTreeSelectionListener implements TreeSelectionListener
    {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            JTree tree = (JTree)e.getSource();
            DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if (selectionNode == null) {
                SpotStallPurchaseJpanel.this.jScrollPane2.setViewportView(null);
                return;
            }
            if (selectionNode.isLeaf()) {
                TreeNode parent = selectionNode.getParent();
                String rootKey = parent.toString();
                String key = selectionNode.toString();
                SpotStallPurchaseJpanel.this.showStallDetailedList(StallPurchaseUntil.getStallPurchases(rootKey, key));
            }
        }
    }
    
    private class DetailedMouslisten extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            int index = SpotStallPurchaseJpanel.this.detailedList.getSelectedIndex();
            Goodstable goods = UserMessUntil.getgoodstable(SpotStallPurchaseJpanel.this.detaileds[index].getGoodsId());
            SpotStallPurchaseJpanel.this.setCurrentCommodity(SpotStallPurchaseJpanel.this.getCommodity(goods));
        }
    }
    
    private class SearchDocumentListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SpotStallPurchaseJpanel.this.search();
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            SpotStallPurchaseJpanel.this.search();
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
            SpotStallPurchaseJpanel.this.search();
        }
    }
}
