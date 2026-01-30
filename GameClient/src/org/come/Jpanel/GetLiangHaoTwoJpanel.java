package org.come.Jpanel;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.apache.commons.lang.StringUtils;
import javax.swing.table.JTableHeader;
import javax.swing.SwingUtilities;
import java.awt.AlphaComposite;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.Util;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.util.ArrayList;
import org.come.entity.SellLianghaoAuc;
import org.come.bean.MyLiangHaoAucBean;
import org.come.entity.SellLiangHaoBase;
import java.util.List;
import java.math.BigDecimal;
import java.awt.Color;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import org.come.bean.SearchSellLiangHaoResultBean;
import com.tool.btn.LiangHaoItemBtn;
import javax.swing.JLabel;
import com.tool.btn.LiangHaoPaiBtn;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GetLiangHaoTwoJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    ImageIcon bg1;
    ImageIcon zttiao;
    ImageIcon fengexian;
    private LiangHaoPaiBtn liangHaoPaiBtn;
    private JLabel name;
    private JLabel labName;
    private String preViewLiangHao;
    private LiangHaoItemBtn[] liangHaoListBtn;
    private LiangHaoItemBtn[] liangHaoViewListBtn;
    private LiangHaoItemBtn[] aucProcessListBtn;
    ImageIcon itemBg;
    private SearchSellLiangHaoResultBean alb;
    private JTable tableLiangHaoList;
    private DefaultTableModel tableModel;
    private Vector<Vector<String>> verVectors;
    private Color foreground;
    private Color background;
    private BigDecimal selectSellId;
    private int tab_row;
    private LiangHaoItemBtn btnsyy;
    private LiangHaoItemBtn btnxyy;
    private int nowpage;
    private JLabel labpage;
    private List<SellLiangHaoBase> nLhList;
    private boolean isMylist;
    MyLiangHaoAucBean myLiangHaoAucBean;
    private int my_tab_row;
    private int my_nowpage;
    private List<SellLianghaoAuc> my_AucList;
    private Color color;
    
    public GetLiangHaoTwoJpanel() {
        this.preViewLiangHao = "888888";
        this.tab_row = -1;
        this.nowpage = 1;
        this.nLhList = new ArrayList<>();
        this.isMylist = false;
        this.my_tab_row = -1;
        this.my_nowpage = 1;
        this.my_AucList = new ArrayList<>();
        this.color = new Color(56, 53, 46, 238);
        this.setPreferredSize(new Dimension(697, 538));
        this.setLayout(null);
        this.setOpaque(false);
        this.bg1 = new ImageIcon("inkImg/background1/LiangHaoOneBg2.png");
        this.itemBg = new ImageIcon("inkImg/button1/199.png");
        this.zttiao = new ImageIcon("inkImg/background1/jingpaizhuangtai.png");
        this.fengexian = new ImageIcon("inkImg/button1/fengexian.png");
        (this.liangHaoPaiBtn = new LiangHaoPaiBtn("inkImg/button1/LiangHaoPaiBtn.png", 1, UIUtils.COLOR_WHITE, UIUtils.TXT_hyzjt18, "我的竞拍", Integer.valueOf(2), this)).setBounds(540, 80, 112, 43);
        this.add(this.liangHaoPaiBtn);
        (this.name = new JLabel()).setBounds(80, 93, 120, 15);
        this.name.setForeground(Color.BLACK);
        this.name.setFont(new Font("宋体", 0, 16));
        this.name.setText("竞拍时间为");
        this.add(this.name);
        (this.labName = new JLabel()).setBounds(180, 93, 400, 15);
        this.labName.setForeground(Color.RED);
        this.labName.setFont(new Font("宋体", 0, 16));
        this.add(this.labName);
        this.liangHaoListBtn = new LiangHaoItemBtn[6];
        for (int i = 1; i <= 6; ++i) {
            this.liangHaoListBtn[i - 1] = new LiangHaoItemBtn("inkImg/button1/187.png", 1, 2, "竟拍", UIUtils.COLOR_WHITE);
            int line = (i - 1) / 2;
            int index = (i - 1) % 2;
            this.liangHaoListBtn[i - 1].setBounds(272 + 318 * index, 187 + 115 * line, 67, 35);
            this.add(this.liangHaoListBtn[i - 1]);
        }
        this.liangHaoViewListBtn = new LiangHaoItemBtn[6];
        for (int i = 1; i <= 6; ++i) {
            this.liangHaoViewListBtn[i - 1] = new LiangHaoItemBtn("inkImg/button1/190.png", 1, 7, "预览", UIUtils.COLOR_HUI);
            int line = (i - 1) / 2;
            int index = (i - 1) % 2;
            this.liangHaoViewListBtn[i - 1].setBounds(212 + 318 * index, 192 + 115 * line, 49, 24);
            this.add(this.liangHaoViewListBtn[i - 1]);
        }
        this.aucProcessListBtn = new LiangHaoItemBtn[8];
        for (int i = 1; i <= 8; ++i) {
            (this.aucProcessListBtn[i - 1] = new LiangHaoItemBtn("inkImg/button1/190.png", 1, 8, "处理", UIUtils.COLOR_HUI)).setBounds(565, 143 + 40 * i, 49, 24);
            this.add(this.aucProcessListBtn[i - 1]);
        }
        (this.btnsyy = new LiangHaoItemBtn("inkImg/button1/156.png", 1, "", 4, this)).setBounds(310, 493, 18, 25);
        this.add(this.btnsyy);
        (this.btnxyy = new LiangHaoItemBtn("inkImg/button1/155.png", 1, "", 5, this)).setBounds(392, 493, 18, 25);
        this.add(this.btnxyy);
        this.tableModel = new DefaultTableModel();
        this.verVectors = new Vector<>();
        this.tableModel.setDataVector(this.verVectors, Util.vector4);
        (this.tableLiangHaoList = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
            }
        });
        this.tableLiangHaoList.setOpaque(false);
        this.tableLiangHaoList.setShowGrid(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 0 || column == 1) {
                    GetLiangHaoTwoJpanel.this.foreground = Color.BLACK;
                }
                else if (column == 2 || column == 3) {
                    if (value.toString().length() < 5) {
                        GetLiangHaoTwoJpanel.this.foreground = Color.BLACK;
                    }
                    else if (value.toString().length() < 6) {
                        GetLiangHaoTwoJpanel.this.foreground = new Color(36, 219, 118);
                    }
                    else if (value.toString().length() < 7) {
                        GetLiangHaoTwoJpanel.this.foreground = new Color(253, 68, 221);
                    }
                    else if (value.toString().length() < 8) {
                        GetLiangHaoTwoJpanel.this.foreground = new Color(251, 217, 50);
                    }
                    else if (value.toString().length() < 9) {
                        GetLiangHaoTwoJpanel.this.foreground = new Color(0, 239, 239);
                    }
                    else if (value.toString().length() < 10) {
                        GetLiangHaoTwoJpanel.this.foreground = Color.GREEN;
                    }
                    else {
                        GetLiangHaoTwoJpanel.this.foreground = Color.RED;
                    }
                }
                GetLiangHaoTwoJpanel.this.background = UIUtils.Color_BACK;
                if (isSelected) {
                    GetLiangHaoTwoJpanel.this.foreground = Color.GREEN;
                }
                this.setBackground(GetLiangHaoTwoJpanel.this.background);
                this.setForeground(GetLiangHaoTwoJpanel.this.foreground);
                if (column == 2) {
                    StringBuffer gold = new StringBuffer(value.toString());
                    for (int index = gold.length() - 3; index > 0; index -= 3) {
                        gold.insert(index, ',');
                    }
                    value = gold.toString();
                }
                else if (column == 1) {
                    if (value.toString().length() < 2) {
                        value = value.toString() + "     ";
                    }
                    else if (value.toString().length() < 3) {
                        value = value.toString() + "    ";
                    }
                    else if (value.toString().length() < 4) {
                        value = value.toString() + "   ";
                    }
                    else if (value.toString().length() < 5) {
                        value = value.toString() + "  ";
                    }
                    else if (value.toString().length() < 6) {
                        value = value.toString() + " ";
                    }
                }
                if (column == 3) {
                    value = value.toString() + "   ";
                }
                return super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
            }
        };
        renderer.setHorizontalAlignment(4);
        this.tableLiangHaoList.setDefaultRenderer(Object.class, renderer);
        JTableHeader header = this.tableLiangHaoList.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 0));
        this.tableLiangHaoList.getTableHeader().setBackground(new Color(0, 0, 0, 0));
        this.tableLiangHaoList.getTableHeader().setVisible(true);
        this.tableLiangHaoList.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        this.tableLiangHaoList.setSelectionBackground(new Color(0, 0, 0, 0));
        this.tableLiangHaoList.setSelectionForeground(Color.white);
        this.tableLiangHaoList.setForeground(Color.white);
        this.tableLiangHaoList.setFont(new Font("楷体", 1, 18));
        this.tableLiangHaoList.setBackground(UIUtils.Color_BACK);
        this.tableLiangHaoList.setModel(this.tableModel);
        this.tableLiangHaoList.setAutoResizeMode(0);
        this.tableLiangHaoList.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.tableLiangHaoList.getColumnModel().getColumn(1).setPreferredWidth(50);
        this.tableLiangHaoList.getColumnModel().getColumn(2).setPreferredWidth(140);
        this.tableLiangHaoList.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.tableLiangHaoList.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.tableLiangHaoList.setRowHeight(40);
        this.tableLiangHaoList.setAutoResizeMode(0);
        this.tableLiangHaoList.isCellEditable(1, 1);
        this.tableLiangHaoList.setEnabled(true);
        this.tableLiangHaoList.removeColumn(this.tableLiangHaoList.getColumnModel().getColumn(0));
        this.tableLiangHaoList.setBounds(66, 175, 600, 360);
        this.add(this.tableLiangHaoList);
        this.tableLiangHaoList.setVisible(false);
        Font font = new Font("微软雅黑", 0, 12);
        (this.labpage = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 5.0, 5.0);
                g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                g2d.setColor(GetLiangHaoTwoJpanel.this.color);
                g2d.fill(roundedRectangle);
                g2d.setColor(Color.white);
                super.paintComponent(g);
            }
        }).setVerticalAlignment(0);
        this.labpage.setHorizontalAlignment(0);
        SwingUtilities.invokeLater(()/*  */ -> this.labpage.repaint());
        this.labpage.setForeground(Color.white);
        this.labpage.setBackground(UIUtils.Color_BACK);
        this.labpage.setBorder(BorderFactory.createEmptyBorder());
        this.labpage.setFont(font);
        this.labpage.setBounds(330, 496, 58, 17);
        this.add(this.labpage);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bg1.getImage(), 45, 65, 630, 70, null);
        if (this.isMylist) {
            g.drawImage(this.zttiao.getImage(), 45, 148, 630, 25, null);
            g.drawImage(this.fengexian.getImage(), 45, 140, 630, 2, null);
        }
        else {
            g.drawImage(this.fengexian.getImage(), 45, 140, 630, 2, null);
        }
        if (this.alb != null) {
            this.refreshSellList(this.alb.getSellLiangHaos(), this.nowpage);
        }
        if (this.isMylist && this.myLiangHaoAucBean != null) {
            this.refreshMyAucList(this.myLiangHaoAucBean.getSellLianghaoAucs(), this.my_nowpage);
        }
        if (!this.isMylist && this.aucProcessListBtn.length > 0) {
            for (LiangHaoItemBtn btn : this.aucProcessListBtn) {
                btn.setVisible(false);
            }
        }
        if (!this.isMylist && this.nLhList != null && this.nLhList != null && this.nLhList.size() > 0) {
            for (int i = 1; i <= this.nLhList.size(); ++i) {
                int line = (i - 1) / 2;
                int index = (i - 1) % 2;
                g.drawImage(this.itemBg.getImage(), 45 + 318 * index, 148 + 115 * line, 313, 110, null);
                g.setColor(new Color(79, 79, 79));
                g.setFont(new Font("汉仪中楷简", 1, 22));
                g.drawString(((SellLiangHaoBase)this.nLhList.get(i - 1)).getLianghao(), 70 + 318 * index, 188 + 115 * line);
                g.setFont(new Font("汉仪中楷简", 0, 14));
                g.setColor(new Color(0, 0, 0));
                g.drawString("预购数：", 70 + 318 * index, 213 + 115 * line);
                g.setColor(new Color(255, 0, 0));
                g.drawString("0", 130 + 318 * index, 214 + 115 * line);
                g.setColor(new Color(0, 0, 0));
                g.drawString("竞拍价：", 70 + 318 * index, 235 + 115 * line);
                g.setColor(new Color(255, 0, 0));
                if (((SellLiangHaoBase)this.nLhList.get(i - 1)).getAucPrice() > ((SellLiangHaoBase)this.nLhList.get(i - 1)).getPrice()) {
                    g.drawString(String.valueOf(((SellLiangHaoBase)this.nLhList.get(i - 1)).getAucPrice()), 130 + 318 * index, 236 + 115 * line);
                }
                else {
                    g.drawString(String.valueOf(((SellLiangHaoBase)this.nLhList.get(i - 1)).getPrice()), 130 + 318 * index, 236 + 115 * line);
                }
                if (StringUtils.isBlank(this.labName.getText())) {
                    this.labName.setText(((SellLiangHaoBase)this.nLhList.get(i - 1)).getAucStartTime() + " 00:00:00 ~ " + ((SellLiangHaoBase)this.nLhList.get(i - 1)).getAucEndTime() + " 00:00:00");
                }
                this.liangHaoListBtn[i - 1].setLianghaoItem((SellLiangHaoBase)this.nLhList.get(i - 1));
                this.liangHaoViewListBtn[i - 1].setLianghaoItem((SellLiangHaoBase)this.nLhList.get(i - 1));
            }
        }
        for (int j = 0; j < this.liangHaoListBtn.length; ++j) {
            if (this.liangHaoListBtn[j].getLianghaoItem() == null || StringUtils.isEmpty(this.liangHaoListBtn[j].getLianghaoItem().getLianghao()) || j >= this.nLhList.size() || this.isMylist) {
                this.liangHaoListBtn[j].setVisible(false);
            }
            else {
                this.liangHaoListBtn[j].setVisible(true);
            }
        }
        for (int j = 0; j < this.liangHaoViewListBtn.length; ++j) {
            if (this.liangHaoViewListBtn[j].getLianghaoItem() == null || StringUtils.isEmpty(this.liangHaoViewListBtn[j].getLianghaoItem().getLianghao()) || j >= this.nLhList.size() || this.isMylist) {
                this.liangHaoViewListBtn[j].setVisible(false);
            }
            else {
                this.liangHaoViewListBtn[j].setVisible(true);
            }
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    public SearchSellLiangHaoResultBean getAlb() {
        return this.alb;
    }
    
    public void setAlb(SearchSellLiangHaoResultBean alb) {
        for (int j = 0; j < this.liangHaoListBtn.length; ++j) {
            this.liangHaoListBtn[j].setLianghaoItem(null);
        }
        this.alb = alb;
    }
    
    public void changeContent() {
        if (!this.isMylist) {
            this.isMylist = true;
            this.tableLiangHaoList.setVisible(true);
            this.liangHaoPaiBtn.setText("返回竞拍");
            for (LiangHaoItemBtn btn : this.liangHaoListBtn) {
                btn.setVisible(false);
            }
            String mes = Agreement.getAgreement().selllianghaoAgreement("MYAUCLIST");
            SendMessageUntil.toServer(mes);
        }
        else {
            this.isMylist = false;
            this.tableLiangHaoList.setVisible(false);
            this.liangHaoPaiBtn.setText("我的竞拍");
            for (LiangHaoItemBtn btn : this.liangHaoListBtn) {
                btn.setVisible(true);
            }
        }
    }
    
    public LiangHaoPaiBtn getLiangHaoPaiBtn() {
        return this.liangHaoPaiBtn;
    }
    
    public void setLiangHaoPaiBtn(LiangHaoPaiBtn liangHaoPaiBtn) {
        this.liangHaoPaiBtn = liangHaoPaiBtn;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
    
    public void refreshSellList(List<SellLiangHaoBase> lhlist, int nowpage) {
        int totalpage = lhlist.size() / 6 + ((lhlist.size() % 6 == 0) ? 0 : 1);
        this.labpage.setText(nowpage + "/" + totalpage);
        this.nLhList.clear();
        for (int i = 0; i < ((lhlist.size() - (nowpage - 1) * 6 >= 6) ? 6 : (lhlist.size() - (nowpage - 1) * 6)); ++i) {
            this.nLhList.add(lhlist.get(i + (nowpage - 1) * 6));
        }
    }
    
    public void refreshMyAucList(List<SellLianghaoAuc> auclist, int nowpage) {
        int totalpage = auclist.size() / 8 + ((auclist.size() % 8 == 0) ? 0 : 1);
        this.labpage.setText(nowpage + "/" + totalpage);
        this.verVectors.clear();
        int i;
        SellLianghaoAuc cslc;
        Vector<String> verStrings;
        int status;
        String statusStr;
        for (i = 0, i = 0; i < ((auclist.size() - (nowpage - 1) * 8 >= 8) ? 8 : (auclist.size() - (nowpage - 1) * 8)); ++i) {
            this.aucProcessListBtn[i].setVisible(true);
            cslc = (SellLianghaoAuc)this.myLiangHaoAucBean.getSellLianghaoAucs().get(i + (nowpage - 1) * 8);
            this.aucProcessListBtn[i].setSellLianghaoAuc(cslc);
            verStrings = new Vector<>();
            verStrings.add(String.valueOf(cslc.getId()));
            verStrings.add(cslc.getLianghao());
            verStrings.add(String.valueOf(cslc.getAucPoint()));
            status = (short)cslc.getStatus();
            statusStr = "";
            if (status == 1) {
                this.aucProcessListBtn[i].setText("放弃");
                statusStr = "进行中";
            }
            else if (status == 2) {
                this.aucProcessListBtn[i].setVisible(false);
                statusStr = "竞拍成功";
            }
            else if (status == 3) {
                this.aucProcessListBtn[i].setText("重拍");
                statusStr = "已失败";
            }
            else if (status == 4) {
                this.aucProcessListBtn[i].setText("重拍");
                statusStr = "已取消";
            }
            else if (status == 5) {
                this.aucProcessListBtn[i].setText("重拍");
                statusStr = "已失败";
            }
            verStrings.add(statusStr);
            verStrings.add("");
            this.verVectors.add(verStrings);
        }
        while (i < 8) {
            this.aucProcessListBtn[i].setVisible(false);
            ++i;
        }
        this.tableModel.fireTableDataChanged();
    }
    
    public boolean isMylist() {
        return this.isMylist;
    }
    
    public void setMylist(boolean mylist) {
        this.isMylist = mylist;
    }
    
    public int getMy_tab_row() {
        return this.my_tab_row;
    }
    
    public void setMy_tab_row(int my_tab_row) {
        this.my_tab_row = my_tab_row;
    }
    
    public int getMy_nowpage() {
        return this.my_nowpage;
    }
    
    public void setMy_nowpage(int my_nowpage) {
        this.my_nowpage = my_nowpage;
    }
    
    public MyLiangHaoAucBean getMyLiangHaoAucBean() {
        return this.myLiangHaoAucBean;
    }
    
    public void setMyLiangHaoAucBean(MyLiangHaoAucBean myLiangHaoAucBean) {
        this.myLiangHaoAucBean = myLiangHaoAucBean;
    }
    
    public String getPreViewLiangHao() {
        return this.preViewLiangHao;
    }
    
    public void setPreViewLiangHao(String preViewLiangHao) {
        this.preViewLiangHao = preViewLiangHao;
    }
    
    public List<SellLiangHaoBase> getnLhList() {
        return this.nLhList;
    }
    
    public void setnLhList(List<SellLiangHaoBase> nLhList) {
        this.nLhList = nLhList;
    }
}
