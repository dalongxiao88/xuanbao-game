package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.table.JTableHeader;
import org.come.until.ScrollUI;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import org.come.until.Util;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.ModerateTask.TaskRoleData;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class RankingListJpanel extends JPanel
{
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList<String> listPank;
    private DefaultListModel<String> pankModel;
    private JTable tablePankList;
    private DefaultTableModel tableModel;
    public static int idx2;
    private Vector<Vector<String>> verVectors;
    private Vector<String> verStrings;
    private JLabel labtext1;
    private JLabel labtext2;
    private JLabel labtext3;
    private JLabel labtext4;
    public static JLabel labgundong;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public RankingListJpanel() {
        this.icon = new ImageIcon("img/xy2uiimg/pankinglist.png");
        this.icon1 = new ImageIcon("img/xy2uiimg/SuperRichList.png");
        if (MyIsif.getStyle().equals("水墨")) {
            RankingListJpanel.labgundong = new JLabel(new ImageIcon("inkImg/button/23.png"));
        }
        else {
            RankingListJpanel.labgundong = new JLabel(CutButtonImage.getImage("inkImg/hongmu/15.png", 17, 336));
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(698, 464));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 60);
            offBtn.setBounds(661, 10, 25, 25);
            this.add(offBtn);
            Font font = new Font("宋体", 0, 14);
            (this.labtext1 = new JLabel()).setForeground(Color.yellow);
            this.labtext1.setFont(font);
            this.labtext1.setBounds(255, 31, 85, 20);
            this.labtext1.setText("你的金钱：");
            this.add(this.labtext1);
            (this.labtext2 = new JLabel()).setForeground(Color.white);
            this.labtext2.setFont(font);
            this.labtext2.setBounds(340, 31, 170, 20);
            this.add(this.labtext2);
            (this.labtext3 = new JLabel()).setForeground(Color.yellow);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(255, 51, 100, 20);
            this.labtext3.setText("你目前的排行：");
            this.add(this.labtext3);
            (this.labtext4 = new JLabel()).setForeground(Color.white);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(360, 51, 200, 20);
            this.labtext4.setText("暂时未能上榜，请继续加油");
            this.add(this.labtext4);
            RankingListJpanel.labgundong.setBounds(220, 45, 17, 336);
            this.add(RankingListJpanel.labgundong);
            (this.pankModel = new DefaultListModel<>()).addElement("  超级富豪榜");
            this.pankModel.addElement("  练功狂人榜");
            this.pankModel.addElement("  帮派战功榜");
            this.pankModel.addElement("  水路积分榜");
            this.pankModel.addElement("  擂台积分榜");
            this.pankModel.addElement("  天梯竞技榜");
            this.pankModel.addElement("  试炼幻境榜");
            (this.listPank = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            this.listPank.setSelectionForeground(Color.white);
            this.listPank.setForeground(Color.white);
            this.listPank.setFont(new Font("楷体", 1, 16));
            this.listPank.setBackground(new Color(0, 0, 0, 0));
            this.listPank.setFixedCellHeight(25);
            this.listPank.setModel(this.pankModel);
            this.listPank.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (RankingListJpanel.this.listPank.getSelectedIndex() != -1) {
                        int index = RankingListJpanel.this.listPank.getSelectedIndex();
                        if (index == 0) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/69.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的金钱:");
                            RankingListJpanel.this.labtext2.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                            String mes = Agreement.getAgreement().pankinglistAgreement("1");
                            SendMessageUntil.toServer(mes);
                        }
                        else if (index == 1) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/70.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("");
                            RankingListJpanel.this.labtext2.setText("");
                            String mes = Agreement.getAgreement().pankinglistAgreement("2");
                            SendMessageUntil.toServer(mes);
                        }
                        else if (index == 2) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/71.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的功绩值:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("帮派积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("帮派积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("3");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 3) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/68.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("4");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 4) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/68.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("5");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 5) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/68.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getPkrecord() != null) ? RoleData.getRoleData().getLoginResult().getPkrecord() : new BigDecimal(0);
                            String pk = RankingListJpanel.this.getPk(big);
                            RankingListJpanel.this.labtext2.setText(pk + big + "点");
                            String mes3 = Agreement.getAgreement().pankinglistAgreement("6");
                            SendMessageUntil.toServer(mes3);
                        }
                        else if (index == 6) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("inkImg/background/68.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的层数:");
                            int sumReceive = TaskRoleData.SumReceive(6, 3);
                            int num = 0;
                            int lvl = 0;
                            if (sumReceive != 0) {
                                num = ((sumReceive % 6 != 0) ? (sumReceive / 6 + 1) : (sumReceive / 6));
                                lvl = ((sumReceive % 6 == 0) ? 6 : (sumReceive % 6));
                            }
                            RankingListJpanel.this.labtext2.setText(num + "层" + lvl + "关");
                            String mes4 = Agreement.getAgreement().pankinglistAgreement("7");
                            SendMessageUntil.toServer(mes4);
                        }
                    }
                }
            });
            (this.jScrollPane1 = new JScrollPane(this.listPank)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(48, 28, 190, 420);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.verStrings = new Vector<>();
            this.verVectors.add(this.verStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
            (this.tablePankList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setOpaque(false);
            this.tablePankList.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (row < 3) {
                        this.setForeground(Color.yellow);
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
            this.tablePankList.setFont(new Font("宋体", 0, 14));
            this.tablePankList.setBackground(UIUtils.Color_BACK);
            this.tablePankList.setModel(this.tableModel);
            this.tablePankList.getColumnModel().getColumn(0).setPreferredWidth(60);
            this.tablePankList.getColumnModel().getColumn(1).setPreferredWidth(140);
            this.tablePankList.getColumnModel().getColumn(2).setPreferredWidth(115);
            this.tablePankList.getColumnModel().getColumn(3).setPreferredWidth(100);
            this.tablePankList.setRowHeight(20);
            this.tablePankList.setAutoResizeMode(0);
            this.tablePankList.isCellEditable(1, 1);
            this.tablePankList.setEnabled(true);
            (this.jScrollPane2 = new JScrollPane()).setViewportView(this.tablePankList);
            this.jScrollPane2.setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(239, 98, 441, 349);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
        }
        else {
            this.setPreferredSize(new Dimension(616, 442));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 60);
            offBtn.setBounds(590, 0, 23, 23);
            this.add(offBtn);
            Font font = new Font("宋体", 0, 14);
            (this.labtext1 = new JLabel()).setForeground(Color.yellow);
            this.labtext1.setFont(font);
            this.labtext1.setBounds(230, 45, 200, 20);
            this.labtext1.setText("你的金钱：");
            this.add(this.labtext1);
            (this.labtext2 = new JLabel()).setForeground(Color.white);
            this.labtext2.setFont(font);
            this.labtext2.setBounds(315, 45, 200, 20);
            this.add(this.labtext2);
            (this.labtext3 = new JLabel()).setForeground(Color.yellow);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(230, 65, 100, 20);
            this.labtext3.setText("你目前的排行：");
            this.add(this.labtext3);
            (this.labtext4 = new JLabel()).setForeground(Color.white);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(335, 65, 200, 20);
            this.labtext4.setText("暂时未能上榜，请继续加油");
            this.add(this.labtext4);
            RankingListJpanel.labgundong.setBounds(191, 58, 17, 336);
            this.add(RankingListJpanel.labgundong);
            (this.pankModel = new DefaultListModel<>()).addElement("  超级富豪榜");
            this.pankModel.addElement("  练功狂人榜");
            this.pankModel.addElement("  帮派战功榜");
            this.pankModel.addElement("  水路积分榜");
            this.pankModel.addElement("  擂台积分榜");
            this.pankModel.addElement("  天梯竞技榜");
            this.pankModel.addElement("  试炼幻境榜");
            (this.listPank = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            this.listPank.setSelectionForeground(Color.white);
            this.listPank.setForeground(Color.white);
            this.listPank.setFont(new Font("楷体", 1, 16));
            this.listPank.setBackground(new Color(0, 0, 0, 0));
            this.listPank.setFixedCellHeight(25);
            this.listPank.setModel(this.pankModel);
            this.listPank.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (RankingListJpanel.this.listPank.getSelectedIndex() != -1) {
                        int index = RankingListJpanel.this.listPank.getSelectedIndex();
                        if (index == 0) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/SuperRichList.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的金钱:");
                            RankingListJpanel.this.labtext2.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                            String mes = Agreement.getAgreement().pankinglistAgreement("1");
                            SendMessageUntil.toServer(mes);
                        }
                        else if (index == 1) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Trainerlist.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("");
                            RankingListJpanel.this.labtext2.setText("");
                            String mes = Agreement.getAgreement().pankinglistAgreement("2");
                            SendMessageUntil.toServer(mes);
                        }
                        else if (index == 2) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Gangbattlelist.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的功绩值:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("帮派积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("帮派积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("3");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 3) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Waterwaystandings.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("4");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 4) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Waterwaystandings.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") != null) ? RoleData.getRoleData().getLoginResult().getScoretype("水陆积分") : new BigDecimal(0);
                            RankingListJpanel.this.labtext2.setText(big.toString());
                            String mes2 = Agreement.getAgreement().pankinglistAgreement("5");
                            SendMessageUntil.toServer(mes2);
                        }
                        else if (index == 5) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Waterwaystandings.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的积分:");
                            BigDecimal big = (RoleData.getRoleData().getLoginResult().getPkrecord() != null) ? RoleData.getRoleData().getLoginResult().getPkrecord() : new BigDecimal(0);
                            String pk = RankingListJpanel.this.getPk(big);
                            RankingListJpanel.this.labtext2.setText(pk + big + "点");
                            String mes3 = Agreement.getAgreement().pankinglistAgreement("6");
                            SendMessageUntil.toServer(mes3);
                        }
                        else if (index == 6) {
                            RankingListJpanel.this.icon1 = CutButtonImage.getImage("img/xy2uiimg/Waterwaystandings.png", -1, -1);
                            RankingListJpanel.this.labtext1.setText("你的层数:");
                            int sumReceive = TaskRoleData.SumReceive(6, 3);
                            int num = 0;
                            int lvl = 0;
                            if (sumReceive != 0) {
                                num = ((sumReceive % 6 != 0) ? (sumReceive / 6 + 1) : (sumReceive / 6));
                                lvl = ((sumReceive % 6 == 0) ? 6 : (sumReceive % 6));
                            }
                            RankingListJpanel.this.labtext2.setText(num + "层" + lvl + "关");
                            String mes4 = Agreement.getAgreement().pankinglistAgreement("7");
                            SendMessageUntil.toServer(mes4);
                        }
                    }
                }
            });
            (this.jScrollPane1 = new JScrollPane(this.listPank)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(24, 41, 183, 369);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.verStrings = new Vector<>();
            this.verVectors.add(this.verStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
            (this.tablePankList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setOpaque(false);
            this.tablePankList.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (row < 3) {
                        this.setForeground(Color.yellow);
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
            this.tablePankList.setFont(new Font("宋体", 0, 14));
            this.tablePankList.setBackground(UIUtils.Color_BACK);
            this.tablePankList.setModel(this.tableModel);
            this.tablePankList.getColumnModel().getColumn(0).setPreferredWidth(60);
            this.tablePankList.getColumnModel().getColumn(1).setPreferredWidth(100);
            this.tablePankList.getColumnModel().getColumn(2).setPreferredWidth(105);
            this.tablePankList.getColumnModel().getColumn(3).setPreferredWidth(90);
            this.tablePankList.setRowHeight(20);
            this.tablePankList.setAutoResizeMode(0);
            this.tablePankList.isCellEditable(1, 1);
            this.tablePankList.setEnabled(true);
            (this.jScrollPane2 = new JScrollPane()).setViewportView(this.tablePankList);
            this.jScrollPane2.setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(218, 110, 374, 299);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
        }
    }
    
    public String getPk(BigDecimal pkrecord) {
        int intValue = pkrecord.divide(new BigDecimal(100)).intValue();
        if (intValue <= 0) {
            return "初窥门径";
        }
        if (intValue == 1) {
            return "略有小成";
        }
        if (intValue == 2) {
            return "心领神会";
        }
        if (intValue == 3) {
            return "炉火纯青";
        }
        if (intValue == 4) {
            return "登峰造极";
        }
        return "登峰造极";
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = CutButtonImage.getImage("inkImg/background1/B206.png", 642, 420);
            if (this.listPank.getSelectedIndex() == 5) {
                this.icon1 = new ImageIcon("inkImg/background1/B207a.png");
            }
            else {
                this.icon1 = new ImageIcon("inkImg/background1/B207.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 698, 464, this);
            g.drawImage(this.icon1.getImage(), 239, 75, 440, 21, this);
            if (this.pankModel.size() > 14) {
                this.remove(RankingListJpanel.labgundong);
            }
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/pankinglist.png");
            if (this.listPank.getSelectedIndex() == 5) {
                this.icon1 = new ImageIcon("img/xy2uiimg/SuperRichList1.png");
            }
            else {
                this.icon1 = new ImageIcon("img/xy2uiimg/SuperRichList.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 616, 442, this);
            g.drawImage(this.icon1.getImage(), 214, 89, 381, 21, this);
            if (this.pankModel.size() > 14) {
                this.remove(RankingListJpanel.labgundong);
            }
        }
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public JList<String> getListPank() {
        return this.listPank;
    }
    
    public void setListPank(JList<String> listPank) {
        this.listPank = listPank;
    }
    
    public DefaultListModel<String> getPankModel() {
        return this.pankModel;
    }
    
    public void setPankModel(DefaultListModel<String> pankModel) {
        this.pankModel = pankModel;
    }
    
    public JTable getTablePankList() {
        return this.tablePankList;
    }
    
    public void setTablePankList(JTable tablePankList) {
        this.tablePankList = tablePankList;
    }
    
    public DefaultTableModel getTableModel() {
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
    
    public JLabel getLabtext1() {
        return this.labtext1;
    }
    
    public void setLabtext1(JLabel labtext1) {
        this.labtext1 = labtext1;
    }
    
    public JLabel getLabtext2() {
        return this.labtext2;
    }
    
    public void setLabtext2(JLabel labtext2) {
        this.labtext2 = labtext2;
    }
    
    public JLabel getLabtext3() {
        return this.labtext3;
    }
    
    public void setLabtext3(JLabel labtext3) {
        this.labtext3 = labtext3;
    }
    
    public JLabel getLabtext4() {
        return this.labtext4;
    }
    
    public void setLabtext4(JLabel labtext4) {
        this.labtext4 = labtext4;
    }
}
