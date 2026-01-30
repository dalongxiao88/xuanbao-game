package org.come.Jpanel;

import org.come.until.Util;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import org.come.until.AnalysisString;
import java.util.Vector;
import org.come.bean.LaborRole;
import java.util.List;
import java.awt.Dimension;
import org.come.bean.LaborRank;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import com.tool.btn.TrueFeedbackBtn;
import javax.swing.JPanel;

public class TrueFeedbackScPanel extends JPanel
{
    private TrueFeedbackBtn btnHomePage;
    private TrueFeedbackBtn btnUpPage;
    private TrueFeedbackBtn btnNextPage;
    private TrueFeedbackBtn btnLastPage;
    private TrueFeedbackBtn btnAward;
    private TrueFeedbackBtn BtnGet;
    private JTable tablePankList;
    private DefaultTableModel tableModel;
    private JLabel labPage;
    private ImageIcon topImg;
    private ImageIcon downImg;
    private ImageIcon downBackImg;
    private LaborRank laborRank;
    private int page;
    private int maxPage;
    
    public TrueFeedbackScPanel() {
        this.setPreferredSize(new Dimension(430, 450));
        this.setOpaque(false);
        this.setLayout(null);
        this.getBtnHomePage();
        this.getBtnLastPage();
        this.getBtnNextPage();
        this.getBtnUpPage();
        this.getLabPage();
        this.getBtnAward();
        this.getBtnGet();
        this.getTablePankList();
    }
    
    public void showScpane(LaborRank laborRank) {
        List<LaborRole> laborRoles = laborRank.getRoles();
        this.maxPage = ((laborRoles.size() % 10 == 0) ? (laborRoles.size() / 10) : (laborRoles.size() / 10 + 1));
        this.page = 1;
        this.laborRank = laborRank;
        this.addData(laborRank.getType());
        this.changeViewImg(laborRank.getType());
    }
    
    public void addData(int type) {
        List<LaborRole> laborRoles = this.laborRank.getRoles();
        int len = laborRoles.size();
        Vector<Vector<String>> dataVector = (Vector<Vector<String>>)this.tableModel.getDataVector();
        int size = dataVector.size();
        int num = (this.page - 1) * 10;
        for (int i = 0; i < size; ++i) {
            Vector<String> vector = (Vector<String>)dataVector.get(i);
            if (num < len) {
                LaborRole laborRole = (LaborRole)laborRoles.get(num);
                vector.set(0, String.valueOf(num + 1));
                vector.set(1, laborRole.getName());
                vector.set(2, AnalysisString.lvl(laborRole.getLvl()) + "级");
                if (type == 0) {
                    vector.set(3, String.valueOf(laborRole.getCz()));
                }
                else if (type == 1) {
                    vector.set(3, String.valueOf(laborRole.getCj()));
                }
                ++num;
                if (i >= this.page * 9) {
                    break;
                }
            }
            else {
                vector.set(0, "");
                vector.set(1, "");
                vector.set(2, "");
                vector.set(3, "");
            }
        }
        Vector<String> vector2 = (Vector<String>)dataVector.get(10);
        LaborRole role = this.laborRank.getRole();
        vector2.set(0, (this.laborRank.getRank() == 0) ? "未上榜" : String.valueOf(this.laborRank.getRank()));
        vector2.set(1, role.getName());
        vector2.set(2, AnalysisString.lvl(role.getLvl()) + "级");
        if (type == 0) {
            vector2.set(3, String.valueOf(role.getCz()));
        }
        else if (type == 1) {
            vector2.set(3, String.valueOf(role.getCj()));
        }
        this.labPage.setText(this.page + "/" + this.maxPage);
    }
    
    public void changeViewImg(int typeMenu) {
        if (typeMenu == 0) {
            this.topImg = CutButtonImage.getImage("inkImg/background/S196.png", -1, -1);
            this.downBackImg = null;
            this.btnAward.setVisible(true);
            try {
                this.BtnGet.setBounds(325, 93, 80, 26);
                this.BtnGet.setColors(UIUtils.COLOR_BTNPUTONG);
                this.BtnGet.setFont(UIUtils.TEXT_HY16);
                this.BtnGet.setIcons(CutButtonImage.cuts("inkImg/button/50.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (typeMenu == 1) {
            this.topImg = CutButtonImage.getImage("inkImg/background/S197.png", -1, -1);
            this.downBackImg = CutButtonImage.getImage("inkImg/background/S200.png", -1, -1);
            this.btnAward.setVisible(false);
            try {
                this.BtnGet.setBounds(359, 12, 34, 17);
                this.BtnGet.setColors(UIUtils.COLOR_BTNTEXT);
                this.BtnGet.setFont(UIUtils.TEXT_FONT);
                this.BtnGet.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.topImg != null) {
            g.drawImage(this.topImg.getImage(), 0, 0, null);
        }
        if (this.downImg == null) {
            this.downImg = CutButtonImage.getImage("inkImg/background/S194.png", -1, -1);
        }
        g.drawImage(this.downImg.getImage(), 0, 131, null);
        if (this.downBackImg != null) {
            g.drawImage(this.downBackImg.getImage(), 0, 131, null);
        }
    }
    
    public TrueFeedbackBtn getBtnHomePage() {
        if (this.btnHomePage == null) {
            (this.btnHomePage = new TrueFeedbackBtn("inkImg/button/2.png", 1, 3, "首页", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, this)).setBounds(124, 416, 34, 17);
            this.add(this.btnHomePage);
        }
        return this.btnHomePage;
    }
    
    public void setBtnHomePage(TrueFeedbackBtn btnHomePage) {
        this.btnHomePage = btnHomePage;
    }
    
    public TrueFeedbackBtn getBtnUpPage() {
        if (this.btnUpPage == null) {
            (this.btnUpPage = new TrueFeedbackBtn("inkImg/button/10.png", 1, 4, this)).setBounds(159, 416, 18, 18);
            this.add(this.btnUpPage);
        }
        return this.btnUpPage;
    }
    
    public void setBtnUpPage(TrueFeedbackBtn btnUpPage) {
        this.btnUpPage = btnUpPage;
    }
    
    public TrueFeedbackBtn getBtnNextPage() {
        if (this.btnNextPage == null) {
            (this.btnNextPage = new TrueFeedbackBtn("inkImg/button/9.png", 1, 5, this)).setBounds(241, 416, 18, 18);
            this.add(this.btnNextPage);
        }
        return this.btnNextPage;
    }
    
    public void setBtnNextPage(TrueFeedbackBtn btnNextPage) {
        this.btnNextPage = btnNextPage;
    }
    
    public TrueFeedbackBtn getBtnLastPage() {
        if (this.btnLastPage == null) {
            (this.btnLastPage = new TrueFeedbackBtn("inkImg/button/2.png", 1, 6, "末页", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, this)).setBounds(261, 416, 34, 17);
            this.add(this.btnLastPage);
        }
        return this.btnLastPage;
    }
    
    public void setBtnLastPage(TrueFeedbackBtn btnLastPage) {
        this.btnLastPage = btnLastPage;
    }
    
    public ImageIcon getTopImg() {
        return this.topImg;
    }
    
    public void setTopImg(ImageIcon topImg) {
        this.topImg = topImg;
    }
    
    public ImageIcon getDownImg() {
        return this.downImg;
    }
    
    public void setDownImg(ImageIcon downImg) {
        this.downImg = downImg;
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
            this.tablePankList.setBounds(1, 152, 426, 288);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column >= 2) {
                        this.setForeground(Color.red);
                    }
                    else {
                        this.setForeground(Color.white);
                    }
                    if (row == 10) {
                        this.setForeground(Color.yellow);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            renderer.setHorizontalAlignment(0);
            this.tablePankList.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tablePankList.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tablePankList.getTableHeader().setBackground(UIUtils.Color_BACK);
            this.tablePankList.getTableHeader().setVisible(false);
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
                    int selectedRow = TrueFeedbackScPanel.this.tablePankList.getSelectedRow();
                    if (selectedRow == -1) {
                        return;
                    }
                }
            });
            this.tablePankList.getColumnModel().getColumn(0).setPreferredWidth(60);
            this.tablePankList.getColumnModel().getColumn(1).setPreferredWidth(135);
            this.tablePankList.getColumnModel().getColumn(2).setPreferredWidth(80);
            this.tablePankList.getColumnModel().getColumn(3).setPreferredWidth(150);
            this.tablePankList.setRowHeight(23);
            this.tablePankList.setAutoResizeMode(0);
            this.tablePankList.isCellEditable(1, 1);
            this.tablePankList.setEnabled(true);
            this.add(this.tablePankList);
        }
        return this.tablePankList;
    }
    
    public void setTablePankList(JTable tablePankList) {
        this.tablePankList = tablePankList;
    }
    
    public DefaultTableModel getTableModel() {
        if (this.tableModel == null) {
            this.tableModel = new DefaultTableModel();
            Vector<Vector<String>> verVectors = new Vector<>();
            for (int i = 0; i < 11; ++i) {
                Vector<String> strings = new Vector<>();
                strings.add("");
                strings.add("");
                strings.add("");
                strings.add("");
                verVectors.add(strings);
            }
            this.tableModel.setDataVector(verVectors, Util.vector3);
        }
        return this.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public JLabel getLabPage() {
        if (this.labPage == null) {
            (this.labPage = new JLabel("1/1", 0)).setForeground(Color.white);
            this.labPage.setFont(UIUtils.TEXT_FONT1);
            this.labPage.setBounds(178, 416, 61, 16);
            this.add(this.labPage);
        }
        return this.labPage;
    }
    
    public void setLabPage(JLabel labPage) {
        this.labPage = labPage;
    }
    
    public TrueFeedbackBtn getBtnAward() {
        if (this.btnAward == null) {
            (this.btnAward = new TrueFeedbackBtn("inkImg/button/49.png", 1, 7, "奖励一览", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, this)).setBounds(35, 102, 68, 17);
            this.add(this.btnAward);
        }
        return this.btnAward;
    }
    
    public void setBtnAward(TrueFeedbackBtn btnAward) {
        this.btnAward = btnAward;
    }
    
    public TrueFeedbackBtn getBtnGet() {
        if (this.BtnGet == null) {
            (this.BtnGet = new TrueFeedbackBtn("inkImg/button/50.png", 1, 8, "领取", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this)).setBounds(325, 93, 80, 26);
            this.add(this.BtnGet);
        }
        return this.BtnGet;
    }
    
    public void setBtnGet(TrueFeedbackBtn btnGet) {
        this.BtnGet = btnGet;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getMaxPage() {
        return this.maxPage;
    }
    
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    public LaborRank getLaborRank() {
        return this.laborRank;
    }
    
    public void setLaborRank(LaborRank laborRank) {
        this.laborRank = laborRank;
    }
}
