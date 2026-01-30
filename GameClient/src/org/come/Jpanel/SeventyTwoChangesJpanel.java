package org.come.Jpanel;

import org.come.until.Util;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import org.cbg.until.TraslationDemoScrollBarUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Graphics;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import com.tool.tcpimg.RichLabel;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.come.model.aCard;
import java.util.List;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import com.tool.btn.SevenTwoChangesBtn;
import javax.swing.JPanel;

public class SeventyTwoChangesJpanel extends JPanel
{
    private String[] transformationName;
    private SevenTwoChangesBtn[] menuBtn;
    private SevenTwoChangesBtn[] transformationBtn;
    private JScrollPane scrollPaneAttribute;
    private JScrollPane scrollPaneTrans;
    private JLabel changeImg;
    private JLabel changImgBack;
    private JTable tablePankList;
    private DefaultTableModel tableModel;
    private JLabel[] commonLab;
    private JTextField selectNameText;
    private SevenTwoChangesBtn selectBtn;
    private Vector<Vector<String>> verVectors;
    private Vector<String> verStrings;
    private List<aCard> aCardList;
    private aCard chooseCard;
    private int chooseMoneyType;
    private ImageIcon iconBack;
    
    public SeventyTwoChangesJpanel() {
        this.transformationName = new String[] { "变身造型", "不变身造型" };
        this.menuBtn = new SevenTwoChangesBtn[2];
        this.transformationBtn = new SevenTwoChangesBtn[2];
        this.aCardList = new ArrayList<>();
        this.chooseMoneyType = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(750, 441));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 89);
            offBtn.setBounds(713, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < this.menuBtn.length; ++i) {
                (this.menuBtn[i] = new SevenTwoChangesBtn((i == 0) ? "inkImg/button/B85.png" : "inkImg/button/B86.png", 1, "", i, this)).setBounds(380 + i * 98, 18, 96, 26);
                this.menuBtn[i].setOpaque(false);
                this.add(this.menuBtn[i]);
                (this.transformationBtn[i] = new SevenTwoChangesBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this.transformationName[i], 10 + i, this)).setBounds(235, 370 + i * 30, 99, 24);
                this.transformationBtn[i].setOpaque(false);
                this.add(this.transformationBtn[i]);
            }
            this.add(this.getScrollPaneAttribute());
            this.add(this.getScrollPaneTrans());
            this.add(this.getChangImgBack());
            this.add(this.getChangeImg());
            this.getCommonLab();
            this.getSelectNameText();
            this.getSelectBtn();
        }
        else {
            this.setPreferredSize(new Dimension(800, 467));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 89);
            offBtn.setBounds(777, 0, 23, 23);
            this.add(offBtn);
            for (int i = 0; i < this.menuBtn.length; ++i) {
                (this.menuBtn[i] = new SevenTwoChangesBtn((i == 0) ? "img/xy2uiimg/小选项卡_金钱变身_已选择_w100,h78.png" : "img/xy2uiimg/小选项卡_仙玉变身_未选择_w100,h78.png", 1, "", i, this)).setBounds(376 + i * 102, 32, 100, 26);
                this.menuBtn[i].setOpaque(false);
                this.add(this.menuBtn[i]);
                (this.transformationBtn[i] = new SevenTwoChangesBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, this.transformationName[i], 10 + i, this)).setBounds(220, 362 + i * 45, 80, 26);
                this.transformationBtn[i].setOpaque(false);
                this.add(this.transformationBtn[i]);
            }
            this.add(this.getScrollPaneAttribute());
            this.add(this.getScrollPaneTrans());
            this.add(this.getChangImgBack());
            this.add(this.getChangeImg());
            this.getCommonLab();
            this.getSelectNameText();
            this.getSelectBtn();
        }
    }
    
    public void getACardDatas(Map<Integer, aCard> aCardMap, int moneyType, String selectName) {
        this.tableModel.getDataVector().clear();
        this.aCardList.clear();
        for (Integer cardId : aCardMap.keySet()) {
            aCard aCard = (aCard)aCardMap.get(cardId);
            if (moneyType == aCard.getType()) {
                if (selectName != null && !"".equals(selectName)) {
                    if (aCard.getName().indexOf(selectName) != -1) {
                        Vector<Object> vector = new Vector<>();
                        vector.add(aCard.getGn());
                        vector.add(aCard.getName());
                        vector.add(aCard.getTime() + "分");
                        vector.add(aCard.getMoney() + ((aCard.getType() == 0) ? "大话币" : "仙玉"));
                        this.aCardList.add(aCard);
                        this.tableModel.addRow(vector);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    Vector<Object> vector = new Vector<>();
                    vector.add(aCard.getGn());
                    vector.add(aCard.getName());
                    vector.add(aCard.getTime() + "分");
                    vector.add(aCard.getMoney() + ((aCard.getType() == 0) ? "大话币" : "仙玉"));
                    this.aCardList.add(aCard);
                    this.tableModel.addRow(vector);
                }
            }
        }
    }
    
    public Color getColorGn(String cardGn) {
        if (cardGn.equals("强法")) {
            return Color.red;
        }
        return cardGn.equals("抗性") ? UIUtils.COLOR_TBule : Color.green;
    }
    
    public void showCard(aCard card) {
        this.chooseCard = card;
        if (this.chooseCard != null) {
            this.changeImg.setIcon(CutButtonImage.getImage("img/head/" + this.chooseCard.getSkin() + ".png", 158, 218));
            RichLabel view = (RichLabel)this.scrollPaneAttribute.getViewport().getView();
            StringBuffer buffer = new StringBuffer();
            buffer.append("#cffffff名称:" + this.chooseCard.getName() + "#r");
            buffer.append("#cff0000消耗:" + this.chooseCard.getMoney() + ((this.chooseCard.getType() == 0) ? "大话币" : "仙玉") + "#r");
            buffer.append("#cffffff【亲和力】" + this.chooseCard.getQhl() + "#r");
            buffer.append("#cffffff【种族】" + this.chooseCard.getZz() + "#r");
            String valueAll = this.chooseCard.getValue();
            List<String> otherAttributes = new ArrayList<>();
            if (valueAll != null && !"".equals(valueAll)) {
                String[] values = this.chooseCard.getValue().split("\\|");
                for (int i = 0; i < values.length; ++i) {
                    String[] valueOne = values[i].split("=");
                    if (valueOne[0].equals("加强气血") || valueOne[0].equals("加强魔法") || valueOne[0].equals("加强攻击") || valueOne[0].equals("加强速度")) {
                        buffer.append("#cffff00" + valueOne[0] + ": " + valueOne[1] + "%#r");
                    }
                    else if (valueOne[0].equals("金") || valueOne[0].equals("木") || valueOne[0].equals("水") || valueOne[0].equals("火") || valueOne[0].equals("土")) {
                        otherAttributes.add(valueOne[0] + ": " + valueOne[1]);
                    }
                    else {
                        buffer.append("#c00FF00" + valueOne[0] + ": " + valueOne[1] + "%#r");
                    }
                }
            }
            buffer.append("#c57FAFF持续时间:" + this.chooseCard.getTime() + "分#r");
            buffer.append(" #r");
            if (!otherAttributes.isEmpty()) {
                buffer.append("#cffff00");
                int columns = 2;
                for (int i = 0; i < otherAttributes.size(); ++i) {
                    buffer.append((String)otherAttributes.get(i));
                    if (i < otherAttributes.size() - 1) {
                        buffer.append(" #cffff00");
                        if ((i + 1) % columns == 0) {
                            buffer.append("#cffff00");
                            buffer.append("#r");
                        }
                    }
                }
                buffer.append("#r");
            }
            view = new RichLabel(buffer.toString(), UIUtils.TEXT_FONT2);
            Dimension d = view.computeSize(130);
            view.setSize(d);
            view.setPreferredSize(d);
            this.scrollPaneAttribute.setViewportView(view);
        }
    }
    
    public void changeMenuBtnSeventyTwoChanges(int caozuo) {
        this.chooseMoneyType = caozuo;
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (0 == caozuo) {
                    this.menuBtn[0].setIcons(CutButtonImage.cuts("inkImg/button/B85.png"));
                    this.menuBtn[1].setIcons(CutButtonImage.cuts("inkImg/button/B86.png"));
                }
                else {
                    this.menuBtn[0].setIcons(CutButtonImage.cuts("inkImg/button/B84.png"));
                    this.menuBtn[1].setIcons(CutButtonImage.cuts("inkImg/button/B87.png"));
                }
                String getlCard = RoleData.getRoleData().getPackRecord().getlCard();
                if (getlCard != null) {
                    String[] split = getlCard.split("\\|");
                    for (int i = 0; i < this.commonLab.length; ++i) {
                        if (split.length >= i + 1) {
                            aCard card = (aCard)UserMessUntil.getaCardMap().get(Integer.decode(split[i]));
                            this.commonLab[i].setIcon(CutButtonImage.getImage("img/head/" + card.getSkin() + ".png", 40, 40));
                            this.commonLab[i].setName(split[i]);
                        }
                        else {
                            this.commonLab[i].setIcon(null);
                            this.commonLab[i].setName(null);
                        }
                    }
                }
                this.getACardDatas(UserMessUntil.getaCardMap(), caozuo, "");
            }
            else {
                if (caozuo == 0) {
                    this.menuBtn[0].setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_金钱变身_已选择_w100,h78.png"));
                    this.menuBtn[1].setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_仙玉变身_未选择_w100,h78.png"));
                }
                else {
                    this.menuBtn[0].setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_金钱变身_未选择_w100,h78.png"));
                    this.menuBtn[1].setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_仙玉变身_已选择_w100,h78.png"));
                }
                String getlCard = RoleData.getRoleData().getPackRecord().getlCard();
                if (getlCard != null) {
                    String[] split = getlCard.split("\\|");
                    for (int i = 0; i < this.commonLab.length; ++i) {
                        if (split.length >= i + 1) {
                            aCard card = (aCard)UserMessUntil.getaCardMap().get(Integer.decode(split[i]));
                            this.commonLab[i].setIcon(CutButtonImage.getImage("img/head/" + card.getSkin() + ".png", 40, 40));
                            this.commonLab[i].setIcon(CutButtonImage.getImage("img/item/" + card.getSkin() + ".png", 40, 40));
                            this.commonLab[i].setName(split[i]);
                        }
                        else {
                            this.commonLab[i].setIcon(null);
                            this.commonLab[i].setName(null);
                        }
                    }
                }
                this.getACardDatas(UserMessUntil.getaCardMap(), caozuo, "");
            }
        }
        catch (Exception var6) {
            var6.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B219.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 750, 441, this);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/七十二变_w800,h467.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 800, 467, this);
        }
    }
    
    public String[] getTransformationName() {
        return this.transformationName;
    }
    
    public void setTransformationName(String[] transformationName) {
        this.transformationName = transformationName;
    }
    
    public SevenTwoChangesBtn[] getMenuBtn() {
        return this.menuBtn;
    }
    
    public void setMenuBtn(SevenTwoChangesBtn[] menuBtn) {
        this.menuBtn = menuBtn;
    }
    
    public SevenTwoChangesBtn[] getTransformationBtn() {
        return this.transformationBtn;
    }
    
    public void setTransformationBtn(SevenTwoChangesBtn[] transformationBtn) {
        this.transformationBtn = transformationBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JScrollPane getScrollPaneAttribute() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPaneAttribute == null) {
                (this.scrollPaneAttribute = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.scrollPaneAttribute.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPaneAttribute.getVerticalScrollBar().setUnitIncrement(20);
                this.scrollPaneAttribute.getViewport().setOpaque(false);
                this.scrollPaneAttribute.setOpaque(false);
                this.scrollPaneAttribute.setBounds(48, 64, 147, 328);
                this.scrollPaneAttribute.setBorder(BorderFactory.createEmptyBorder());
                this.scrollPaneAttribute.setHorizontalScrollBarPolicy(31);
            }
        }
        else if (this.scrollPaneAttribute == null) {
            (this.scrollPaneAttribute = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPaneAttribute.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPaneAttribute));
            this.scrollPaneAttribute.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPaneAttribute.getViewport().setOpaque(false);
            this.scrollPaneAttribute.setOpaque(false);
            this.scrollPaneAttribute.setBounds(23, 77, 144, 327);
            this.scrollPaneAttribute.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPaneAttribute.setHorizontalScrollBarPolicy(31);
        }
        return this.scrollPaneAttribute;
    }
    
    public void setScrollPaneAttribute(JScrollPane scrollPaneAttribute) {
        this.scrollPaneAttribute = scrollPaneAttribute;
    }
    
    public JScrollPane getScrollPaneTrans() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPaneTrans == null) {
                (this.scrollPaneTrans = new JScrollPane()).setViewportView(this.getTablePankList());
                this.scrollPaneTrans.setVerticalScrollBarPolicy(22);
                this.scrollPaneTrans.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPaneTrans.getVerticalScrollBar().setUnitIncrement(30);
                this.scrollPaneTrans.getViewport().setOpaque(false);
                this.scrollPaneTrans.setOpaque(false);
                this.scrollPaneTrans.setBounds(382, 64, 349, 328);
                this.scrollPaneTrans.setBorder(BorderFactory.createEmptyBorder());
                this.scrollPaneTrans.setHorizontalScrollBarPolicy(31);
            }
        }
        else if (this.scrollPaneTrans == null) {
            (this.scrollPaneTrans = new JScrollPane()).setViewportView(this.getTablePankList());
            this.scrollPaneTrans.setVerticalScrollBarPolicy(22);
            this.scrollPaneTrans.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPaneTrans));
            this.scrollPaneTrans.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPaneTrans.getViewport().setOpaque(false);
            this.scrollPaneTrans.setOpaque(false);
            this.scrollPaneTrans.setBounds(378, 77, 392, 327);
            this.scrollPaneTrans.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPaneTrans.setHorizontalScrollBarPolicy(31);
        }
        return this.scrollPaneTrans;
    }
    
    public void setScrollPaneTrans(JScrollPane scrollPaneTrans) {
        this.scrollPaneTrans = scrollPaneTrans;
    }
    
    public JTable getTablePankList() {
        if (this.tablePankList == null) {
            (this.tablePankList = new JTable() {
                {
                    this.setOpaque(false);
                }
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column == 0) {
                        if (value instanceof String) {
                            String gn = (String)value;
                            this.setForeground(SeventyTwoChangesJpanel.this.getColorGn(gn));
                        }
                    }
                    else if (column == 1) {
                        if (SeventyTwoChangesJpanel.this.aCardList != null && SeventyTwoChangesJpanel.this.aCardList.size() != 0) {
                            aCard aCard = (aCard)SeventyTwoChangesJpanel.this.aCardList.get(row);
                            if (aCard.getCardType() == 2) {
                                this.setForeground(Color.yellow);
                            }
                            else if (aCard.getCardType() == 1) {
                                this.setForeground(UIUtils.COLOR_TBule);
                            }
                            else {
                                this.setForeground(Color.white);
                            }
                        }
                        else {
                            this.setForeground(Color.white);
                        }
                    }
                    else {
                        this.setForeground(Color.white);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            renderer.setHorizontalAlignment(0);
            this.tablePankList.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tablePankList.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tablePankList.getTableHeader().setBackground(new Color(0, 0, 0, 0));
            this.tablePankList.getTableHeader().setVisible(true);
            this.tablePankList.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tablePankList.setSelectionBackground(new Color(0, 0, 0, 0));
            this.tablePankList.setSelectionForeground(Color.white);
            this.tablePankList.setForeground(Color.white);
            this.tablePankList.setFont(UIUtils.TEXT_FONT1);
            this.tablePankList.setBackground(UIUtils.Color_BACK);
            this.tablePankList.setModel(this.getTableModel());
            this.tablePankList.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int selectedRow = SeventyTwoChangesJpanel.this.tablePankList.getSelectedRow();
                    if (selectedRow != -1 && SeventyTwoChangesJpanel.this.aCardList != null && SeventyTwoChangesJpanel.this.aCardList.size() != 0) {
                        SeventyTwoChangesJpanel.this.showCard((aCard)SeventyTwoChangesJpanel.this.aCardList.get(selectedRow));
                    }
                }
            });
            this.tablePankList.getColumnModel().getColumn(0).setPreferredWidth(50);
            this.tablePankList.getColumnModel().getColumn(1).setPreferredWidth(95);
            this.tablePankList.getColumnModel().getColumn(2).setPreferredWidth(60);
            this.tablePankList.getColumnModel().getColumn(3).setPreferredWidth(120);
            this.tablePankList.setRowHeight(20);
            this.tablePankList.setAutoResizeMode(0);
            this.tablePankList.isCellEditable(1, 1);
            this.tablePankList.setEnabled(true);
        }
        return this.tablePankList;
    }
    
    public void setTablePankList(JTable tablePankList) {
        this.tablePankList = tablePankList;
    }
    
    public DefaultTableModel getTableModel() {
        if (this.tableModel == null) {
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.verStrings = new Vector<>();
            for (int i = 0; i < 20; ++i) {
                this.verStrings.add("第一" + i + 1);
                this.verStrings.add("第一" + i + 2);
                this.verStrings.add("第一" + i + 3);
                this.verStrings.add("第一" + i + 4);
                this.verVectors.add(this.verStrings);
            }
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
        }
        return this.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public Vector<Vector<String>> getVerVectors() {
        return this.verVectors;
    }
    
    public void setVerVectors(Vector<Vector<String>> verVectors) {
        this.verVectors = verVectors;
    }
    
    public Vector<String> getVerStrings() {
        return this.verStrings;
    }
    
    public void setVerStrings(Vector<String> verStrings) {
        this.verStrings = verStrings;
    }
    
    public JLabel getChangeImg() {
        if (this.changeImg == null) {
            (this.changeImg = new JLabel()).setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.changeImg.setBounds(209, 54, 158, 218);
            }
            else {
                this.changeImg.setBounds(194, 66, 158, 218);
            }
        }
        return this.changeImg;
    }
    
    public void setChangeImg(JLabel changeImg) {
        this.changeImg = changeImg;
    }
    
    public JLabel getChangImgBack() {
        if (this.changImgBack == null) {
            (this.changImgBack = new JLabel()).setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.changImgBack.setBounds(209, 54, 158, 218);
                this.changImgBack.setIcon(CutButtonImage.getImage("inkImg/background1/B230.png", 158, 218));
            }
            else {
                this.changImgBack.setBounds(191, 57, 165, 240);
                this.changImgBack.setIcon(CutButtonImage.getImage("img/xy2uiimg/七十二变_框_w165,h240.png", 165, 240));
            }
        }
        return this.changImgBack;
    }
    
    public void setChangImgBack(JLabel changImgBack) {
        this.changImgBack = changImgBack;
    }
    
    public List<aCard> getaCardList() {
        return this.aCardList;
    }
    
    public void setaCardList(List<aCard> aCardList) {
        this.aCardList = aCardList;
    }
    
    public aCard getChooseCard() {
        return this.chooseCard;
    }
    
    public void setChooseCard(aCard chooseCard) {
        this.chooseCard = chooseCard;
    }
    
    public JLabel[] getCommonLab() {
        class MListener extends MouseAdapter
        {
            private final int i;
            private final SeventyTwoChangesJpanel seventyTwoChangesJpanel;
            
            public MListener(SeventyTwoChangesJpanel seventyTwoChangesJpanel,int i) {
            	this.i = i;
            	this.seventyTwoChangesJpanel = seventyTwoChangesJpanel;
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (SeventyTwoChangesJpanel.this.commonLab[this.i].getName() != null) {
                    aCard card = UserMessUntil.getACard((int)Integer.decode(SeventyTwoChangesJpanel.this.commonLab[this.i].getName()));
                    if (card != null) {
                        SeventyTwoChangesJpanel.this.showCard(card);
                    }
                }
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.commonLab == null) {
                this.commonLab = new JLabel[3];
                for (int i = 0; i < this.commonLab.length; ++i) {
                    (this.commonLab[i] = new JLabel()).setBounds(209 + i * 58, 308, 40, 40);
                    this.commonLab[i].setOpaque(false);
                    this.commonLab[i].addMouseListener(new MListener(this,i));
                    this.add(this.commonLab[i]);
                }
            }
        }
        else if (this.commonLab == null) {
            this.commonLab = new JLabel[3];
            for (int i = 0; i < this.commonLab.length; ++i) {
                (this.commonLab[i] = new JLabel()).setBounds(190 + i * 60, 300, 50, 50);
                this.commonLab[i].setOpaque(false);
                this.commonLab[i].addMouseListener(new MListener(this,i));
                this.add(this.commonLab[i]);
            }
        }
        return this.commonLab;
    }
    
    public void setCommonLab(JLabel[] commonLab) {
        this.commonLab = commonLab;
    }
    
    public JTextField getSelectNameText() {
        if (this.selectNameText == null) {
            (this.selectNameText = new JTextField()).setBorder(null);
            this.selectNameText.setOpaque(false);
            this.selectNameText.setCaretColor(Color.white);
            this.selectNameText.setForeground(Color.white);
            if (MyIsif.getStyle().equals("水墨")) {
                this.selectNameText.setBounds(626, 26, 80, 16);
            }
            else {
                this.selectNameText.setBounds(655, 37, 80, 17);
            }
            this.add(this.selectNameText);
        }
        return this.selectNameText;
    }
    
    public void setSelectNameText(JTextField selectNameText) {
        this.selectNameText = selectNameText;
    }
    
    public SevenTwoChangesBtn getSelectBtn() {
        if (this.selectBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.selectBtn = new SevenTwoChangesBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "搜索", 20, this)).setBounds(589, 25, 34, 17);
            }
            else {
                (this.selectBtn = new SevenTwoChangesBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "搜索", 20, this)).setBounds(740, 34, 34, 17);
            }
            this.selectBtn.setOpaque(false);
            this.add(this.selectBtn);
        }
        return this.selectBtn;
    }
    
    public void setSelectBtn(SevenTwoChangesBtn selectBtn) {
        this.selectBtn = selectBtn;
    }
    
    public int getChooseMoneyType() {
        return this.chooseMoneyType;
    }
    
    public void setChooseMoneyType(int chooseMoneyType) {
        this.chooseMoneyType = chooseMoneyType;
    }
}
