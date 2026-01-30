package org.cbg.panel;

import java.util.ArrayList;
import org.cbg.entity.Salegoods;
import org.cbg.bean.SearchGoodsResultBean;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.bean.SearchMyGoodsBean;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.cbg.until.TraslationTableYijishangpinUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.util.List;
import org.cbg.btn.CBGMySaleBtn;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationWantSentYijishangpinJpanel extends JLayeredPane
{
    private JLabel chooseLeft;
    private JLabel yema;
    private JLabel upSort;
    private JLabel downSort;
    private TraslationSelectOptionJpanel chooseLeftModel;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private CBGMySaleBtn downBtn;
    private CBGMySaleBtn shouBtn;
    private CBGMySaleBtn moBtn;
    private CBGMySaleBtn leftBtn;
    private CBGMySaleBtn rightBtn;
    private List<TraslationWantSentYijishangpinModelJpanel> lists;
    private ImageIcon icon;
    
    public TraslationWantSentYijishangpinJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(8, 46, 573, 300);
            this.jScrollPane.setBorder(null);
            this.jPanel = TraslationTableYijishangpinUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            (this.chooseLeft = new JLabel("全部")).setBounds(54, 4, 78, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "全部", "未上架", "已上架", "被下单", "已卖出" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(98, 120, "inkImg/background/15.png", rowDataLeft)).setBounds(50, 20, 98, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantSentYijishangpinJpanel.this.chooseLeft.setText(getname);
                    TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantSentYijishangpinJpanel.this.yema.setText("1/1");
                    TraslationWantSentYijishangpinJpanel.this.search();
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            (this.yema = new JLabel("1/1")).setBounds(258, 354, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.downBtn = new CBGMySaleBtn("inkImg/button/8.png", 1, 0, this)).setBounds(130, 3, 18, 18);
            this.add(this.downBtn);
            (this.shouBtn = new CBGMySaleBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 1, "首页", this)).setBounds(209, 352, 34, 17);
            this.add(this.shouBtn);
            (this.moBtn = new CBGMySaleBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 2, "末页", this)).setBounds(342, 352, 34, 17);
            this.add(this.moBtn);
            (this.leftBtn = new CBGMySaleBtn("inkImg/button/10.png", 1, 3, this)).setBounds(248, 352, 18, 18);
            this.add(this.leftBtn);
            (this.rightBtn = new CBGMySaleBtn("inkImg/button/9.png", 1, 4, this)).setBounds(320, 352, 18, 18);
            this.add(this.rightBtn);
            (this.upSort = new JLabel()).setBounds(333, 31, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", -1, -1));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSentYijishangpinJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantSentYijishangpinJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 31, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSentYijishangpinJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantSentYijishangpinJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(8, 46, 573, 300);
            this.jScrollPane.setBorder(null);
            this.jPanel = TraslationTableYijishangpinUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            (this.chooseLeft = new JLabel("全部")).setBounds(54, 4, 78, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "全部", "未上架", "已上架", "被下单", "已卖出" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(98, 120, "img/xy2uiimg/下拉框(2)w98,h120px.png", rowDataLeft)).setBounds(52, 24, 98, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantSentYijishangpinJpanel.this.chooseLeft.setText(getname);
                    TraslationWantSentYijishangpinJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantSentYijishangpinJpanel.this.yema.setText("1/1");
                    TraslationWantSentYijishangpinJpanel.this.search();
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            (this.yema = new JLabel("1/1")).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.downBtn = new CBGMySaleBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1, 0, this)).setBounds(132, 5, 18, 18);
            this.add(this.downBtn);
            (this.shouBtn = new CBGMySaleBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 1, "首页", this)).setBounds(200, 354, 34, 17);
            this.add(this.shouBtn);
            (this.moBtn = new CBGMySaleBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 2, "末页", this)).setBounds(342, 354, 34, 17);
            this.add(this.moBtn);
            (this.leftBtn = new CBGMySaleBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 3, this)).setBounds(235, 354, 18, 18);
            this.add(this.leftBtn);
            (this.rightBtn = new CBGMySaleBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 4, this)).setBounds(320, 354, 18, 18);
            this.add(this.rightBtn);
            (this.upSort = new JLabel()).setBounds(333, 31, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", -1, -1));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSentYijishangpinJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantSentYijishangpinJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 31, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSentYijishangpinJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantSentYijishangpinJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/37.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/我要卖-已寄售品w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void xl() {
        this.chooseLeftModel.setVisible(!this.chooseLeftModel.isVisible());
    }
    
    public String YSqh(int type) {
        String[] vs = this.yema.getText().split("/");
        int dq = Integer.parseInt(vs[0]);
        int max = Integer.parseInt(vs[1]);
        if (type == 1) {
            type = 1;
        }
        else if (type == 2) {
            type = max;
        }
        else if (type == 3) {
            type = dq - 1;
        }
        else if (type == 4) {
            type = dq + 1;
        }
        if (type < 1) {
            return "已在首页";
        }
        if (type > max) {
            return "已在末页";
        }
        if (dq == type) {
            return "已在该页";
        }
        this.yema.setText(type + "/" + max);
        this.search();
        return null;
    }
    
    public void search() {
        String[] vs = this.yema.getText().split("/");
        int dq = Integer.parseInt(vs[0]);
        String typename = this.chooseLeft.getText();
        SearchMyGoodsBean searchMyGoodsBean = new SearchMyGoodsBean();
        searchMyGoodsBean.setPageNum(Integer.valueOf(dq));
        if (typename.equals("未上架")) {
            searchMyGoodsBean.setFlag(Integer.valueOf(1));
        }
        else if (typename.equals("已上架")) {
            searchMyGoodsBean.setFlag(Integer.valueOf(2));
        }
        else if (typename.equals("被下单")) {
            searchMyGoodsBean.setFlag(Integer.valueOf(3));
        }
        else if (typename.equals("已卖出")) {
            searchMyGoodsBean.setFlag(Integer.valueOf(4));
        }
        String sendmes = Agreement.getAgreement().searchMyWaresAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchMyGoodsBean));
        SendMessageUntil.toServer(sendmes);
    }
    
    public void MyShow() {
        this.yema.setText("1/1");
        this.search();
    }
    
    public void MyShow(SearchGoodsResultBean bean) {
        String[] vs = this.yema.getText().split("/");
        this.yema.setText(vs[0] + "/" + bean.getTotal());
        int size = this.getLists().size();
        List<Salegoods> salegoods = bean.getSalegoods();
        int p = 0;
        if (salegoods != null) {
            p = salegoods.size();
            for (int i = 0; i < p; ++i) {
                this.xiugai((Salegoods)salegoods.get(i), i);
            }
        }
        for (int i = size - 1; i >= p; --i) {
            TraslationWantSentYijishangpinModelJpanel modelJpanel = (TraslationWantSentYijishangpinModelJpanel)this.getLists().remove(i);
            this.jPanel.remove(modelJpanel);
        }
        this.tz();
    }
    
    public void xiugai(Salegoods salegoods, int p) {
        TraslationWantSentYijishangpinModelJpanel modelJpanel = null;
        if (p < this.getLists().size()) {
            modelJpanel = (TraslationWantSentYijishangpinModelJpanel)this.getLists().get(p);
        }
        if (modelJpanel != null) {
            modelJpanel.SX(salegoods);
        }
        else {
            modelJpanel = new TraslationWantSentYijishangpinModelJpanel(salegoods);
            this.getLists().add(modelJpanel);
            this.jPanel.add(modelJpanel);
        }
    }
    
    public void removeJpanel(TraslationWantSentYijishangpinModelJpanel modelJpanel) {
        this.getLists().remove(modelJpanel);
        this.jPanel.remove(modelJpanel);
        this.tz();
    }
    
    public void tz() {
        for (int i = 0; i < this.lists.size(); ++i) {
            TraslationWantSentYijishangpinModelJpanel modelJpanel = (TraslationWantSentYijishangpinModelJpanel)this.lists.get(i);
            modelJpanel.setBounds(0, i * 50, 550, 50);
        }
        this.jPanel.setPreferredSize(new Dimension(565, 50 * this.lists.size()));
        this.jScrollPane.getViewport().setViewSize(this.jPanel.getPreferredSize());
    }
    
    public JLabel getChooseLeft() {
        return this.chooseLeft;
    }
    
    public void setChooseLeft(JLabel chooseLeft) {
        this.chooseLeft = chooseLeft;
    }
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
    }
    
    public JLabel getUpSort() {
        return this.upSort;
    }
    
    public void setUpSort(JLabel upSort) {
        this.upSort = upSort;
    }
    
    public JLabel getDownSort() {
        return this.downSort;
    }
    
    public void setDownSort(JLabel downSort) {
        this.downSort = downSort;
    }
    
    public TraslationSelectOptionJpanel getChooseLeftModel() {
        return this.chooseLeftModel;
    }
    
    public void setChooseLeftModel(TraslationSelectOptionJpanel chooseLeftModel) {
        this.chooseLeftModel = chooseLeftModel;
    }
    
    public JTable getjTable() {
        return this.jTable;
    }
    
    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public List<TraslationWantSentYijishangpinModelJpanel> getLists() {
        if (this.lists == null) {
            this.lists = new ArrayList<>();
        }
        return this.lists;
    }
}
