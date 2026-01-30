package org.cbg.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cbg.bean.SearchGoodsBean;
import org.cbg.btn.TrslationMainBtn;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CutButtonImage;
import org.come.until.GsonUtil;

import com.updateNew.MyIsif;

public class TraslationWantBuyShouyeJpanel extends JPanel
{
    public static String sousuoneirong;
    public static Integer sousuoType;
    public static SearchGoodsBean searchGoodsBean;
    public static Integer showOrder;
    private JLabel chooseLeft;
    private JLabel chooseRight;
    private TrslationMainBtn chooseLeftArrows;
    private TrslationMainBtn chooseRightArrows;
    private TraslationSelectOptionJpanel chooseLeftModel;
    private TraslationSelectOptionJpanel chooseRightModel;
    private int leftFlag;
    private int rightFlag;
    private JTextField jTextField;
    private TrslationMainBtn button;
    private JLabel allMenu;
    private JLabel dahuabi;
    private JLabel daoju;
    private JLabel zhaohuanshou;
    private JLabel zhaungbei;
    private JLabel lingbao;
    private int openType;
    private TraslationWantBuyShouyeModelJpanel traslationWantBuyShouyeModelJpanel;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private ImageIcon icon5;
    private ImageIcon icon6;
    private ImageIcon icon7;
    private ImageIcon icon8;
    private ImageIcon icon9;
    
    public TraslationWantBuyShouyeJpanel() {
        this.leftFlag = 1;
        this.rightFlag = 1;
        this.openType = 1;
        String gdt = "";
        if (MyIsif.getStyle().equals("水墨")) {
            gdt = "inkImg/button/23.png";
        }
        else {
            gdt = "img/xy2uiimg/gundongtiao_副本_副本.png";
        }
        class JTextFieldHintListener
        implements FocusListener {
            private String mHintText;
            private JTextField mTextField;

            public JTextFieldHintListener(String hintText, JTextField textField) {
                this.mHintText = hintText;
                this.mTextField = textField;
            }

            @Override
            public void focusGained(FocusEvent e) {
                String temp = this.mTextField.getText();
                if (temp.equals(this.mHintText)) {
                    this.mTextField.setText("");
                    this.mTextField.setForeground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = this.mTextField.getText();
                if (temp.equals("")) {
                    this.mTextField.setForeground(Color.GRAY);
                    this.mTextField.setText(this.mHintText);
                }
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantBuyShouyeModelJpanel = new TraslationWantBuyShouyeModelJpanel(7));
            this.add(this.getChooseRightArrows(new TraslationWantBuyShouyeJpanel[] { this }));
            this.add(this.getChooseLeftArrows(new TraslationWantBuyShouyeJpanel[] { this }));
            (this.chooseLeft = new JLabel("一亿")).setBounds(232, 98, 108, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.chooseLeft.setVisible(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(108, 120, "inkImg/background/18.png", rowDataLeft)).setBounds(229, 117, 108, 120);
            this.add(this.chooseLeftModel);
            this.chooseLeftModel.setVisible(false);
            this.chooseLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.leftFlag == 1) {
                        TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyShouyeJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyShouyeJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyShouyeJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyShouyeJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyShouyeJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyShouyeJpanel.this.leftFlag = 1;
                }
            });
            (this.chooseRight = new JLabel("一百亿")).setBounds(380, 98, 108, 20);
            this.chooseRight.setForeground(Color.white);
            this.chooseRight.setOpaque(false);
            this.chooseRight.setVisible(false);
            this.add(this.chooseRight);
            String[] rowDataRight = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseRightModel = new TraslationSelectOptionJpanel(98, 120, "inkImg/background/15.png", rowDataRight)).setBounds(376, 117, 98, 120);
            this.add(this.chooseRightModel);
            this.chooseRightModel.setVisible(false);
            this.chooseRightModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyShouyeJpanel.this.chooseRightModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyShouyeJpanel.this.chooseRightModel.getJlist().getSelectedValue();
                    TraslationWantBuyShouyeJpanel.this.chooseRight.setText(getname);
                    TraslationWantBuyShouyeJpanel.this.chooseRightModel.setVisible(false);
                    TraslationWantBuyShouyeJpanel.this.rightFlag = 1;
                }
            });
            (this.jTextField = new JTextField()).setBorder(null);
            this.jTextField.setOpaque(false);
            this.jTextField.setBounds(239, 98, 250, 21);
            this.jTextField.setForeground(Color.white);
            this.jTextField.setCaretColor(Color.white);
            this.jTextField.setVisible(true);
            this.jTextField.setFont(new Font("宋体", 0, 12));
            this.add(this.jTextField);
            (this.button = new TrslationMainBtn("inkImg/button/32.png", 1, 15, "搜索", this)).setForeground(Color.black);
            this.button.setBounds(300, 200, 60, 26);
            this.button.setVisible(true);
            this.button.setOpaque(false);
            this.button.setBorder(null);
            this.add(this.button);
            this.jTextField.addFocusListener(new JTextFieldHintListener("关键字或者ID", this.jTextField));
            (this.allMenu = new JLabel("全部")).setBounds(16, 36, 104, 20);
            this.allMenu.setBackground(Color.gray);
            this.allMenu.setOpaque(true);
            this.allMenu.setForeground(Color.white);
            this.allMenu.setFont(new Font("宋体", 0, 17));
            this.add(this.allMenu);
            (this.dahuabi = new JLabel("大话币")).setBounds(16, 56, 104, 20);
            this.dahuabi.setForeground(Color.white);
            this.dahuabi.setFont(new Font("宋体", 0, 17));
            this.add(this.dahuabi);
            (this.daoju = new JLabel("道具")).setBounds(16, 76, 104, 20);
            this.daoju.setForeground(Color.white);
            this.daoju.setFont(new Font("宋体", 0, 17));
            this.add(this.daoju);
            (this.zhaohuanshou = new JLabel("召唤兽")).setBounds(16, 96, 104, 20);
            this.zhaohuanshou.setForeground(Color.white);
            this.zhaohuanshou.setFont(new Font("宋体", 0, 17));
            this.add(this.zhaohuanshou);
            (this.zhaungbei = new JLabel("装备")).setBounds(16, 116, 104, 20);
            this.zhaungbei.setForeground(Color.white);
            this.zhaungbei.setFont(new Font("宋体", 0, 17));
            this.add(this.zhaungbei);
            (this.lingbao = new JLabel("灵宝")).setBounds(16, 136, 104, 20);
            this.lingbao.setForeground(Color.white);
            this.lingbao.setFont(new Font("宋体", 0, 17));
            this.add(this.lingbao);
            this.allMenu.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 1) {
                        TraslationWantBuyShouyeJpanel.this.allMenu.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.allMenu.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.allMenu.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.allMenu.setOpaque(true);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 1;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
            this.dahuabi.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 2) {
                        TraslationWantBuyShouyeJpanel.this.dahuabi.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.dahuabi.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.dahuabi.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.dahuabi.setOpaque(true);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 2;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
            this.daoju.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.daoju.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.daoju.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 3) {
                        TraslationWantBuyShouyeJpanel.this.daoju.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.daoju.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 3;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
            this.zhaohuanshou.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 4) {
                        TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 4;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
            this.zhaungbei.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.zhaungbei.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.zhaungbei.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 5) {
                        TraslationWantBuyShouyeJpanel.this.zhaungbei.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.zhaungbei.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 5;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
            this.lingbao.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.lingbao.setBackground(Color.gray);
                    TraslationWantBuyShouyeJpanel.this.lingbao.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.openType != 6) {
                        TraslationWantBuyShouyeJpanel.this.lingbao.setBackground(null);
                        TraslationWantBuyShouyeJpanel.this.lingbao.setOpaque(false);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.openType = 6;
                    TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                    TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantBuyShouyeModelJpanel = new TraslationWantBuyShouyeModelJpanel(7));
            this.add(this.getChooseRightArrows(new TraslationWantBuyShouyeJpanel[] { this }));
            this.add(this.getChooseLeftArrows(new TraslationWantBuyShouyeJpanel[] { this }));
            (this.chooseLeft = new JLabel("一亿")).setBounds(232, 98, 108, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.chooseLeft.setVisible(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(108, 120, "inkImg/background/18.png", rowDataLeft)).setBounds(229, 117, 108, 120);
            this.add(this.chooseLeftModel);
            this.chooseLeftModel.setVisible(false);
            this.chooseLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyShouyeJpanel.this.leftFlag == 1) {
                        TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyShouyeJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyShouyeJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyShouyeJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyShouyeJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyShouyeJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyShouyeJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyShouyeJpanel.this.leftFlag = 1;
                }
            });
            (this.chooseRight = new JLabel("一百亿")).setBounds(380, 98, 108, 20);
            this.chooseRight.setForeground(Color.white);
            this.chooseRight.setOpaque(false);
            this.chooseRight.setVisible(false);
            this.add(this.chooseRight);
            String[] rowDataRight = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseRightModel = new TraslationSelectOptionJpanel(98, 120, gdt, rowDataRight)).setBounds(376, 117, 98, 120);
            this.add(this.chooseRightModel);
            this.chooseRightModel.setVisible(false);
            this.chooseRightModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyShouyeJpanel.this.chooseRightModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyShouyeJpanel.this.chooseRightModel.getJlist().getSelectedValue();
                    TraslationWantBuyShouyeJpanel.this.chooseRight.setText(getname);
                    TraslationWantBuyShouyeJpanel.this.chooseRightModel.setVisible(false);
                    TraslationWantBuyShouyeJpanel.this.rightFlag = 1;
                }
            });
            (this.jTextField = new JTextField()).setBorder(null);
            this.jTextField.setOpaque(false);
            this.jTextField.setBounds(239, 98, 250, 21);
            this.jTextField.setForeground(Color.white);
            this.jTextField.setCaretColor(Color.white);
            this.jTextField.setVisible(true);
            this.jTextField.setFont(new Font("宋体", 0, 12));
            this.add(this.jTextField);
            (this.button = new TrslationMainBtn("inkImg/hongmu/6026.png", 1, 15, "搜索", this)).setBounds(300, 200, 60, 26);
            this.button.setVisible(true);
            this.button.setOpaque(false);
            this.button.setBorder(null);
            this.add(this.button);
        }
        this.jTextField.addFocusListener(new JTextFieldHintListener("关键字或者ID", this.jTextField));
        (this.allMenu = new JLabel("全部")).setBounds(16, 36, 104, 20);
        this.allMenu.setBackground(Color.gray);
        this.allMenu.setOpaque(true);
        this.allMenu.setForeground(Color.white);
        this.allMenu.setFont(new Font("宋体", 0, 17));
        this.add(this.allMenu);
        (this.dahuabi = new JLabel("大话币")).setBounds(16, 56, 104, 20);
        this.dahuabi.setForeground(Color.white);
        this.dahuabi.setFont(new Font("宋体", 0, 17));
        this.add(this.dahuabi);
        (this.daoju = new JLabel("道具")).setBounds(16, 76, 104, 20);
        this.daoju.setForeground(Color.white);
        this.daoju.setFont(new Font("宋体", 0, 17));
        this.add(this.daoju);
        (this.zhaohuanshou = new JLabel("召唤兽")).setBounds(16, 96, 104, 20);
        this.zhaohuanshou.setForeground(Color.white);
        this.zhaohuanshou.setFont(new Font("宋体", 0, 17));
        this.add(this.zhaohuanshou);
        (this.zhaungbei = new JLabel("装备")).setBounds(16, 116, 104, 20);
        this.zhaungbei.setForeground(Color.white);
        this.zhaungbei.setFont(new Font("宋体", 0, 17));
        this.add(this.zhaungbei);
        (this.lingbao = new JLabel("灵宝")).setBounds(16, 136, 104, 20);
        this.lingbao.setForeground(Color.white);
        this.lingbao.setFont(new Font("宋体", 0, 17));
        this.add(this.lingbao);
        this.allMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 1) {
                    TraslationWantBuyShouyeJpanel.this.allMenu.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.allMenu.setOpaque(false);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.allMenu.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.allMenu.setOpaque(true);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 1;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
        this.dahuabi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 2) {
                    TraslationWantBuyShouyeJpanel.this.dahuabi.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.dahuabi.setOpaque(false);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.dahuabi.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.dahuabi.setOpaque(true);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 2;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
        this.daoju.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.daoju.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.daoju.setOpaque(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 3) {
                    TraslationWantBuyShouyeJpanel.this.daoju.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.daoju.setOpaque(false);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 3;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
        this.zhaohuanshou.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setOpaque(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 4) {
                    TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.zhaohuanshou.setOpaque(false);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 4;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
        this.zhaungbei.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.zhaungbei.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.zhaungbei.setOpaque(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 5) {
                    TraslationWantBuyShouyeJpanel.this.zhaungbei.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.zhaungbei.setOpaque(false);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 5;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
        this.lingbao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.lingbao.setBackground(Color.gray);
                TraslationWantBuyShouyeJpanel.this.lingbao.setOpaque(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TraslationWantBuyShouyeJpanel.this.openType != 6) {
                    TraslationWantBuyShouyeJpanel.this.lingbao.setBackground(null);
                    TraslationWantBuyShouyeJpanel.this.lingbao.setOpaque(false);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationWantBuyShouyeJpanel.this.setJLabelNull(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.openType = 6;
                TraslationWantBuyShouyeJpanel.this.setOpenPanelGray(TraslationWantBuyShouyeJpanel.this.openType);
                TraslationWantBuyShouyeJpanel.this.jTextField.setText("");
            }
        });
    }
    
    public static BigDecimal changeMath(String mathChina) {
        BigDecimal math = null;
        int n = -1;
        switch (mathChina.hashCode()) {
            case 639167: {
                if (mathChina.equals("一亿")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 680862: {
                if (mathChina.equals("十亿")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 20004650: {
                if (mathChina.equals("二十亿")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 19878759: {
                if (mathChina.equals("三十亿")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 22048697: {
                if (mathChina.equals("四十亿")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 20012338: {
                if (mathChina.equals("五十亿")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 20712907: {
                if (mathChina.equals("六十亿")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 19872993: {
                if (mathChina.equals("七十亿")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 20710985: {
                if (mathChina.equals("八十亿")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 19959483: {
                if (mathChina.equals("九十亿")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 20149761: {
                if (mathChina.equals("一百亿")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                math = new BigDecimal("100000000");
                break;
            }
            case 1: {
                math = new BigDecimal("1000000000");
                break;
            }
            case 2: {
                math = new BigDecimal("2000000000");
                break;
            }
            case 3: {
                math = new BigDecimal("3000000000");
                break;
            }
            case 4: {
                math = new BigDecimal("4000000000");
                break;
            }
            case 5: {
                math = new BigDecimal("5000000000");
                break;
            }
            case 6: {
                math = new BigDecimal("6000000000");
                break;
            }
            case 7: {
                math = new BigDecimal("7000000000");
                break;
            }
            case 8: {
                math = new BigDecimal("8000000000");
                break;
            }
            case 9: {
                math = new BigDecimal("9000000000");
                break;
            }
            case 10: {
                math = new BigDecimal("10000000000");
                break;
            }
        }
        return math;
    }
    
    public static void souyeSousuo(Integer dangqianyeshu, Integer showGongshiqi, Integer showOrder, SearchGoodsBean searchGoodsBean) {
        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(1);
        searchGoodsBean.setPageNum(dangqianyeshu);
        searchGoodsBean.setShow((int)showGongshiqi);
        searchGoodsBean.setOrder((int)showOrder);
        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
        SendMessageUntil.toServer(sendmes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/30.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
            if (this.icon2 == null) {
                this.icon2 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon2.getImage(), 27, 277, 59, 57, this);
            if (this.icon3 == null) {
                this.icon3 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon3.getImage(), 102, 277, 59, 57, this);
            if (this.icon4 == null) {
                this.icon4 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon4.getImage(), 177, 277, 59, 57, this);
            if (this.icon5 == null) {
                this.icon5 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon5.getImage(), 252, 277, 59, 57, this);
            if (this.icon6 == null) {
                this.icon6 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon6.getImage(), 327, 277, 59, 57, this);
            if (this.icon7 == null) {
                this.icon7 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon7.getImage(), 402, 277, 59, 57, this);
            if (this.icon8 == null) {
                this.icon8 = CutButtonImage.getImage("inkImg/background/9.png", 59, 57);
            }
            g.drawImage(this.icon8.getImage(), 477, 277, 59, 57, this);
            if (this.openType != 2) {
                if (this.icon9 == null || this.icon9.toString().equals("inkImg/background/13.png")) {
                    this.icon9 = CutButtonImage.getImage("inkImg/background/11.png", 254, 19);
                }
                g.drawImage(this.icon9.getImage(), 235, 100, 254, 20, this);
            }
            else {
                if (this.icon9 == null || this.icon9.toString().equals("inkImg/background/11.png")) {
                    this.icon9 = CutButtonImage.getImage("inkImg/background/13.png", 289, 20);
                }
                g.drawImage(this.icon9.getImage(), 185, 100, 289, 20, this);
            }
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/我要买-首页w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png");
            }
            g.drawImage(this.icon2.getImage(), 27, 277, 59, 57, this);
            if (this.icon3 == null) {
                this.icon3 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon3.getImage(), 102, 277, 59, 57, this);
            if (this.icon4 == null) {
                this.icon4 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon4.getImage(), 177, 277, 59, 57, this);
            if (this.icon5 == null) {
                this.icon5 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon5.getImage(), 252, 277, 59, 57, this);
            if (this.icon6 == null) {
                this.icon6 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon6.getImage(), 327, 277, 59, 57, this);
            if (this.icon7 == null) {
                this.icon7 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon7.getImage(), 402, 277, 59, 57, this);
            if (this.icon8 == null) {
                this.icon8 = CutButtonImage.getImage("img/xy2uiimg/最新上架物品框&商品详情W59,h57px.png", 59, 57);
            }
            g.drawImage(this.icon8.getImage(), 477, 277, 59, 57, this);
            if (this.openType != 2) {
                if (this.icon9 == null || this.icon9.toString().equals("inkImg/background/13.png")) {
                    this.icon9 = CutButtonImage.getImage("inkImg/background/11.png", 254, 19);
                }
                g.drawImage(this.icon9.getImage(), 235, 100, 254, 20, this);
            }
            else {
                if (this.icon9 == null || this.icon9.toString().equals("inkImg/background/11.png")) {
                    this.icon9 = CutButtonImage.getImage("inkImg/background/13.png", 289, 20);
                }
                g.drawImage(this.icon9.getImage(), 185, 100, 289, 20, this);
            }
        }
    }
    
    public void setOpenPanelGray(int openType) {
        this.changeViewZhuJian(openType);
        switch (openType) {
            case 1: {
                this.allMenu.setBackground(Color.gray);
                this.allMenu.setOpaque(true);
                break;
            }
            case 2: {
                this.dahuabi.setBackground(Color.gray);
                this.dahuabi.setOpaque(true);
                break;
            }
            case 3: {
                this.daoju.setBackground(Color.gray);
                this.daoju.setOpaque(true);
                break;
            }
            case 4: {
                this.zhaohuanshou.setBackground(Color.gray);
                this.zhaohuanshou.setOpaque(true);
                break;
            }
            case 5: {
                this.zhaungbei.setBackground(Color.gray);
                this.zhaungbei.setOpaque(true);
                break;
            }
            case 6: {
                this.lingbao.setBackground(Color.gray);
                this.lingbao.setOpaque(true);
                break;
            }
        }
    }
    
    public void changeViewZhuJian(int type) {
        if (type != 2) {
            this.getChooseLeft().setVisible(false);
            this.getChooseLeftModel().setVisible(false);
            this.getChooseLeftArrows().setVisible(false);
            this.getChooseRight().setVisible(false);
            this.getChooseRightModel().setVisible(false);
            this.getChooseRightArrows().setVisible(false);
            this.getjTextField().setVisible(true);
        }
        else {
            this.getChooseLeft().setVisible(true);
            this.getChooseLeftArrows().setVisible(true);
            this.getChooseRight().setVisible(true);
            this.getChooseRightArrows().setVisible(true);
            this.getjTextField().setVisible(false);
        }
    }
    
    public void setJLabelNull(int openType) {
        switch (openType) {
            case 1: {
                this.allMenu.setBackground(null);
                this.allMenu.setOpaque(false);
                break;
            }
            case 2: {
                this.dahuabi.setBackground(null);
                this.dahuabi.setOpaque(false);
                break;
            }
            case 3: {
                this.daoju.setBackground(null);
                this.daoju.setOpaque(false);
                break;
            }
            case 4: {
                this.zhaohuanshou.setBackground(null);
                this.zhaohuanshou.setOpaque(false);
                break;
            }
            case 5: {
                this.zhaungbei.setBackground(null);
                this.zhaungbei.setOpaque(false);
                break;
            }
            case 6: {
                this.lingbao.setBackground(null);
                this.lingbao.setOpaque(false);
                break;
            }
        }
    }
    
    public JLabel getAllMenu() {
        return this.allMenu;
    }
    
    public void setAllMenu(JLabel allMenu) {
        this.allMenu = allMenu;
    }
    
    public JLabel getDahuabi() {
        return this.dahuabi;
    }
    
    public void setDahuabi(JLabel dahuabi) {
        this.dahuabi = dahuabi;
    }
    
    public JLabel getDaoju() {
        return this.daoju;
    }
    
    public void setDaoju(JLabel daoju) {
        this.daoju = daoju;
    }
    
    public JLabel getZhaohuanshou() {
        return this.zhaohuanshou;
    }
    
    public void setZhaohuanshou(JLabel zhaohuanshou) {
        this.zhaohuanshou = zhaohuanshou;
    }
    
    public JLabel getZhaungbei() {
        return this.zhaungbei;
    }
    
    public void setZhaungbei(JLabel zhaungbei) {
        this.zhaungbei = zhaungbei;
    }
    
    public JLabel getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(JLabel lingbao) {
        this.lingbao = lingbao;
    }
    
    public int getOpenType() {
        return this.openType;
    }
    
    public void setOpenType(int openType) {
        this.openType = openType;
    }
    
    public TraslationWantBuyShouyeModelJpanel getTraslationWantBuyShouyeModelJpanel() {
        return this.traslationWantBuyShouyeModelJpanel;
    }
    
    public void setTraslationWantBuyShouyeModelJpanel(TraslationWantBuyShouyeModelJpanel traslationWantBuyShouyeModelJpanel) {
        this.traslationWantBuyShouyeModelJpanel = traslationWantBuyShouyeModelJpanel;
    }
    
    public TrslationMainBtn getChooseLeftArrows(TraslationWantBuyShouyeJpanel... traslationWantBuyShouyeFenyeisousuoQuanbuJpanel) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.chooseLeftArrows == null) {
                (this.chooseLeftArrows = new TrslationMainBtn("inkImg/button/8.png", 1, 13, "", traslationWantBuyShouyeFenyeisousuoQuanbuJpanel[0])).setBounds(319, 101, 18, 18);
                this.chooseLeftArrows.setVisible(false);
            }
            return this.chooseLeftArrows;
        }
        else {
            if (this.chooseLeftArrows == null) {
                (this.chooseLeftArrows = new TrslationMainBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1, 13, "", traslationWantBuyShouyeFenyeisousuoQuanbuJpanel[0])).setBounds(319, 101, 18, 18);
                this.chooseLeftArrows.setVisible(false);
            }
            return this.chooseLeftArrows;
        }
    }
    
    public void setChooseLeftArrows(TrslationMainBtn chooseLeftArrows) {
        this.chooseLeftArrows = chooseLeftArrows;
    }
    
    public TrslationMainBtn getChooseRightArrows(TraslationWantBuyShouyeJpanel... traslationWantBuyShouyeFenyeisousuoQuanbuJpanel) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.chooseRightArrows == null) {
                (this.chooseRightArrows = new TrslationMainBtn("inkImg/button/8.png", 1, 14, "", traslationWantBuyShouyeFenyeisousuoQuanbuJpanel[0])).setBounds(456, 101, 18, 18);
                this.chooseRightArrows.setVisible(false);
            }
            return this.chooseRightArrows;
        }
        else {
            if (this.chooseRightArrows == null) {
                (this.chooseRightArrows = new TrslationMainBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1, 14, "", traslationWantBuyShouyeFenyeisousuoQuanbuJpanel[0])).setBounds(456, 101, 18, 18);
                this.chooseRightArrows.setVisible(false);
            }
            return this.chooseRightArrows;
        }
    }
    
    public void setChooseRightArrows(TrslationMainBtn chooseRightArrows) {
        this.chooseRightArrows = chooseRightArrows;
    }
    
    public static String getSousuoneirong() {
        return TraslationWantBuyShouyeJpanel.sousuoneirong;
    }
    
    public static void setSousuoneirong(String sousuoneirong) {
        TraslationWantBuyShouyeJpanel.sousuoneirong = sousuoneirong;
    }
    
    public static Integer getSousuoType() {
        return TraslationWantBuyShouyeJpanel.sousuoType;
    }
    
    public static void setSousuoType(Integer sousuoType) {
        TraslationWantBuyShouyeJpanel.sousuoType = sousuoType;
    }
    
    public static SearchGoodsBean getSearchGoodsBean() {
        return TraslationWantBuyShouyeJpanel.searchGoodsBean;
    }
    
    public static void setSearchGoodsBean(SearchGoodsBean searchGoodsBean) {
        TraslationWantBuyShouyeJpanel.searchGoodsBean = searchGoodsBean;
    }
    
    public static Integer getShowOrder() {
        return TraslationWantBuyShouyeJpanel.showOrder;
    }
    
    public static void setShowOrder(Integer showOrder) {
        TraslationWantBuyShouyeJpanel.showOrder = showOrder;
    }
    
    public JLabel getChooseLeft() {
        return this.chooseLeft;
    }
    
    public void setChooseLeft(JLabel chooseLeft) {
        this.chooseLeft = chooseLeft;
    }
    
    public JLabel getChooseRight() {
        return this.chooseRight;
    }
    
    public void setChooseRight(JLabel chooseRight) {
        this.chooseRight = chooseRight;
    }
    
    public TraslationSelectOptionJpanel getChooseLeftModel() {
        return this.chooseLeftModel;
    }
    
    public void setChooseLeftModel(TraslationSelectOptionJpanel chooseLeftModel) {
        this.chooseLeftModel = chooseLeftModel;
    }
    
    public TraslationSelectOptionJpanel getChooseRightModel() {
        return this.chooseRightModel;
    }
    
    public void setChooseRightModel(TraslationSelectOptionJpanel chooseRightModel) {
        this.chooseRightModel = chooseRightModel;
    }
    
    public int getLeftFlag() {
        return this.leftFlag;
    }
    
    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
    }
    
    public int getRightFlag() {
        return this.rightFlag;
    }
    
    public void setRightFlag(int rightFlag) {
        this.rightFlag = rightFlag;
    }
    
    public JTextField getjTextField() {
        return this.jTextField;
    }
    
    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public ImageIcon getIcon2() {
        return this.icon2;
    }
    
    public void setIcon2(ImageIcon icon2) {
        this.icon2 = icon2;
    }
    
    public ImageIcon getIcon3() {
        return this.icon3;
    }
    
    public void setIcon3(ImageIcon icon3) {
        this.icon3 = icon3;
    }
    
    public ImageIcon getIcon4() {
        return this.icon4;
    }
    
    public void setIcon4(ImageIcon icon4) {
        this.icon4 = icon4;
    }
    
    public ImageIcon getIcon5() {
        return this.icon5;
    }
    
    public void setIcon5(ImageIcon icon5) {
        this.icon5 = icon5;
    }
    
    public ImageIcon getIcon6() {
        return this.icon6;
    }
    
    public void setIcon6(ImageIcon icon6) {
        this.icon6 = icon6;
    }
    
    public ImageIcon getIcon7() {
        return this.icon7;
    }
    
    public void setIcon7(ImageIcon icon7) {
        this.icon7 = icon7;
    }
    
    public ImageIcon getIcon8() {
        return this.icon8;
    }
    
    public void setIcon8(ImageIcon icon8) {
        this.icon8 = icon8;
    }
    
    public TrslationMainBtn getChooseLeftArrows() {
        return this.chooseLeftArrows;
    }
    
    public TrslationMainBtn getChooseRightArrows() {
        return this.chooseRightArrows;
    }
}
