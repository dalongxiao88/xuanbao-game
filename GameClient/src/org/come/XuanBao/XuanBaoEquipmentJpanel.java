package org.come.XuanBao;

import com.tool.tcpimg.UIUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.cbg.btn.TrslationBtn;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoFuShi;
import org.come.XuanBao.XuanBaoMouseListener;
import org.come.XuanBao.XuanBaoPagingBtn;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.skill.panel.SetupJpanel1;
import org.skill.panel.SetupJpanel2;
import org.skill.panel.SetupJpanel3;


public class XuanBaoEquipmentJpanel
        extends JPanel {
    ImageIcon icon = new ImageIcon("img/xuan/1.png");
    public SetupJpanel1 optionJpanel1;
    public SetupJpanel2 optionJpanel2;
    public SetupJpanel3 optionJpanel3;
    private int leftFlag1;
    private int leftFlag2;
    private int leftFlag3;
    public TrslationBtn chooseDownArrows1;
    public TrslationBtn chooseDownArrows2;
    public TrslationBtn chooseDownArrows3;
    public JLabel resolutiontext;
    public JLabel resolutiontext2;
    public JLabel resolutiontext3;
    private XuanBaoPagingBtn btnLast1;
    private XuanBaoPagingBtn btnNext1;
    private XuanBaoPagingBtn btnNext2;
    public JPanel[] jPanel = new JPanel[40];
    public JLabel[] jPanelxuanbao = new JLabel[3];
    private JLabel[] RuneListLabel = new JLabel[5];
    private JLabel[] back = new JLabel[5];


    public JLabel jPanelxuanbaozhu = new JLabel();
    public JTextArea jPanelxuanbaoText = new JTextArea();
    public static ImageIcon r;
    public static ImageIcon r1;
    public static ImageIcon r2;
    public static ImageIcon r3;
    public static ImageIcon b;
    public static ImageIcon b1;
    public static ImageIcon b2;
    public static ImageIcon b3;
    public static ImageIcon b4;
    public static ImageIcon b5;
    public static ImageIcon b6;
    public static ImageIcon b7;
    public static ImageIcon y;
    public static ImageIcon y1;
    public static ImageIcon y2;
    public static ImageIcon y3;
    public static ImageIcon g;
    public static ImageIcon g1;
    public static ImageIcon g2;
    public static ImageIcon g3;
    public static ImageIcon g4;
    public static ImageIcon g5;
    public static ImageIcon g6;
    public static ImageIcon g7;
    public static ImageIcon weikaiqi;
    public static ImageIcon fenyin;
    public static ImageIcon kaikong;
    public static ImageIcon hong;
    public static ImageIcon lan;
    public static ImageIcon honglan;


    public XuanBaoEquipmentJpanel() {
        setPreferredSize(new Dimension(619, 582));
        setLayout((LayoutManager) null);
        setBackground(UIUtils.Color_BACK);
        String[][] resolutionData = {{"通用", "承脉"}, {"类型", "攻击", "防御", "辅助", "通用"}, {"品质", "初品", "中品", "珍品"}};
        createOptionPanel(73, 460, 115, 120, resolutionData[0], 1);
        createOptionPanel(193, 460, 115, 120, resolutionData[1], 2);
        createOptionPanel(313, 460, 115, 120, resolutionData[2], 3);
        this.btnLast1 = new XuanBaoPagingBtn("img/xuan/42_.png", 1, 0, null);
        this.btnLast1.setBounds(530, 420, 18, 54);
        add((Component) this.btnLast1);
        this.btnNext1 = new XuanBaoPagingBtn("img/xuan/43_.png", 1, 1, null);
        this.btnNext1.setBounds(560, 420, 18, 54);
        add((Component) this.btnNext1);
        this.btnNext2 = new XuanBaoPagingBtn("img/xuan/B411_副本.png", 1, 6, null);
        this.btnNext2.setBounds(83, 33, 18, 54);
        add(this.btnNext2);
        for (int i = 0; i < 20; i++) {
            int row = i % 10 * 51;
            int col = i / 10 * 51;
            this.jPanel[i] = new JPanel();
            this.jPanel[i].setOpaque(false);
            this.jPanel[i].setBounds(75 + row, 332 + col, 51, 51);
            this.jPanel[i].addMouseListener(new XuanBaoMouseListener(i));
            add(this.jPanel[i]);

        }
        for (int i = 0; i < 3; i++) {
            this.jPanelxuanbao[i] = new JLabel();
            this.jPanelxuanbao[i].setOpaque(false);
            this.jPanelxuanbao[i].setBounds(87, 82 + i * 56 + i * 3, 51, 51);
            final int finalI = i;
            this.jPanelxuanbao[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 3 && RoleXuanBao.getRoleXuanBao().equipBao[finalI] != null) {
                        RoleXuanBao.getRoleXuanBao().choseuse(RoleXuanBao.getRoleXuanBao().equipBao[finalI], false);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            add(this.jPanelxuanbao[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.back[i] = new JLabel();
            this.back[i].setOpaque(false);
            this.back[i].setBounds(320 + i * 40, 250, 29, 29);
            this.back[i].setVisible(false);
            add(this.back[i]);
        }
        for (int i = 0; i < 5; i++) {
            this.RuneListLabel[i] = new JLabel();
            this.RuneListLabel[i].setOpaque(false);
            this.RuneListLabel[i].setBounds(321 + i * 40, 250, 55, 55);
            this.RuneListLabel[i].addMouseListener(new XuanBaoFuShi(i));
            this.RuneListLabel[i].setVisible(false);
            add(this.RuneListLabel[i]);
        }
        this.jPanelxuanbaozhu.setOpaque(false);
        this.jPanelxuanbaozhu.setBounds(270, 125, 120, 120);
        this.jPanelxuanbaozhu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) { // 右键点击
                    RoleXuanBao.getRoleXuanBao().zhuangbei = null;
                    jPanelxuanbaozhu.setIcon(null);
                    for (int i = 0; i < 5; ++i) {
                        RuneListLabel[i].setVisible(false);
                        back[i].setVisible(false);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(this.jPanelxuanbaozhu);
        add(this.jPanelxuanbaoText);
    }

    public static ImageIcon huang;
    public static ImageIcon huanghong;
    public static ImageIcon huanglan;
    public static ImageIcon honglanhuang;
    public static ImageIcon lv;
    public static ImageIcon lvhong;
    public static ImageIcon lvlan;
    public static ImageIcon honglanlv;
    public static ImageIcon huanglv;
    public static ImageIcon huanghonglv;
    public static ImageIcon huanglanlv;
    public static ImageIcon huanghonglanlv;
    public static ImageIcon weikaiqi1;
    public static ImageIcon fenyin1;
    public static ImageIcon kaikong1;
    public static ImageIcon hong1;
    public static ImageIcon lan1;
    public static ImageIcon honglan1;
    public static ImageIcon huang1;
    public static ImageIcon huanghong1;
    public static ImageIcon huanglan1;
    public static ImageIcon honglanhuang1;
    public static ImageIcon lv1;
    public static ImageIcon lvhong1;
    public static ImageIcon lvlan1;
    public static ImageIcon honglanlv1;
    public static ImageIcon huanglv1;
    public static ImageIcon huanghonglv1;
    public static ImageIcon huanglanlv1;
    public static ImageIcon huanghonglanlv1;
    public XuanBao xuanBao;


    private void createOptionPanel(int x, int y, int width, int height, String[] data, int index) {
        int[] leftFlag = new int[1];
        JLabel resolutionText;
        TrslationBtn chooseDownArrows;
        switch (index) {
            case 1:
                this.optionJpanel1 = new SetupJpanel1(width, height, data);
                resolutionText = this.resolutiontext = new JLabel("通用");
                chooseDownArrows = this.chooseDownArrows1 = new TrslationBtn("", 1);
                leftFlag[0] = this.leftFlag1;
                this.optionJpanel1.setBounds(x, y, width, height);
                this.optionJpanel1.getJlist().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        String getname = (String) optionJpanel1.getJlist().getSelectedValue();
                        resolutionText.setText(getname);
                        optionJpanel1.setVisible(false);
                        leftFlag[0] = 1;
                    }
                });
                this.optionJpanel1.setVisible(false);
                this.add(this.optionJpanel1);
                chooseDownArrows.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (leftFlag[0] == 1) {
                            optionJpanel1.setVisible(true);
                            leftFlag[0] = 0;
                        } else {
                            optionJpanel1.setVisible(false);
                            leftFlag[0] = 1;
                        }
                    }
                });
                break;
            case 2:
                this.optionJpanel2 = new SetupJpanel2(width, height, data);
                resolutionText = this.resolutiontext2 = new JLabel("类型");
                chooseDownArrows = this.chooseDownArrows2 = new TrslationBtn("", 1);
                leftFlag[0] = this.leftFlag2;
                this.optionJpanel2.setBounds(x, y, width, height);
                this.optionJpanel2.getJlist().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        String getname = optionJpanel2.getJlist().getSelectedValue();
                        resolutionText.setText(getname);
                        optionJpanel2.setVisible(false);
                        leftFlag[0] = 1;
                    }
                });
                this.optionJpanel2.setVisible(false);
                this.add(this.optionJpanel2);
                chooseDownArrows.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (leftFlag[0] == 1) {
                            optionJpanel2.setVisible(true);
                            leftFlag[0] = 0;
                        } else {
                            optionJpanel2.setVisible(false);
                            leftFlag[0] = 1;
                        }
                    }
                });
                break;
            case 3:
                this.optionJpanel3 = new SetupJpanel3(width, height, data);
                resolutionText = this.resolutiontext3 = new JLabel("品质");
                chooseDownArrows = this.chooseDownArrows3 = new TrslationBtn("", 1);
                leftFlag[0] = this.leftFlag3;
                this.optionJpanel3.setBounds(x, y, width, height);
                this.optionJpanel3.getJlist().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        String getname = optionJpanel3.getJlist().getSelectedValue();
                        resolutionText.setText(getname);
                        optionJpanel3.setVisible(false);
                        leftFlag[0] = 1;
                    }
                });
                this.optionJpanel3.setVisible(false);
                this.add(this.optionJpanel3);
                chooseDownArrows.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (leftFlag[0] == 1) {
                            optionJpanel3.setVisible(true);
                            leftFlag[0] = 0;
                        } else {
                            optionJpanel3.setVisible(false);
                            leftFlag[0] = 1;
                        }
                    }
                });
                break;
            default:
                throw new IllegalArgumentException("Invalid index: " + index);
        }

        resolutionText.setForeground(Color.white);
        resolutionText.setFont(UIUtils.TEXT_FONTS15);
        resolutionText.setBounds(x + 2, y - 20, 96, 20);
        this.add(resolutionText);
        chooseDownArrows.setBounds(x, y - 20, width, 20);

        try {
            chooseDownArrows.setIcons(CutButtonImage.cuts("img/xuan/xl100.png"));
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }

        this.add(chooseDownArrows);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xuan/1.png");
        }

        g.drawImage(this.icon.getImage(), 0, 0, 619, 482, this);
        RoleXuanBao.getRoleXuanBao().drawL(g, 73, 330);
        if (this.xuanBao != null) {
            g.setFont(UIUtils.TEXT_FONT62);
            g.setColor(new Color(224, 238, 237, 255));
            g.drawString(this.xuanBao.getName(), 508, 66);
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(new Color(70, 113, 139, 255));
            if (this.xuanBao.getRemark() != null && !this.xuanBao.getRemark().isEmpty()) {
                String[] data = this.xuanBao.getRemark().split("，");
                g.drawString(data[0], 500, 90);
                g.drawString(data[1], 500, 105);
            }
            this.jPanelxuanbaoText.setBounds(463, 115, 130, 100);
            this.jPanelxuanbaoText.setOpaque(false);
            this.jPanelxuanbaoText.setForeground(new Color(70, 113, 139, 255));
            this.jPanelxuanbaoText.setLineWrap(true);
        }
    }


    static {

        try {
            r = CutButtonImage.getImage("img/xuan/图标1/hongfengyin.png", 64, 64);
            r1 = CutButtonImage.getImage("img/xuan/图标1/hongpanyin.png", 64, 64);
            b = CutButtonImage.getImage("img/xuan/图标1/lanshayin.png", 64, 64);
            b1 = CutButtonImage.getImage("img/xuan/图标1/langangyin.png", 64, 64);
            b2 = CutButtonImage.getImage("img/xuan/图标1/lanpoyin.png", 64, 64);
            b3 = CutButtonImage.getImage("img/xuan/图标1/lanyingyin.png", 64, 64);
            y = CutButtonImage.getImage("img/xuan/图标1/huangdingyin.png", 64, 64);
            y1 = CutButtonImage.getImage("img/xuan/图标1/huangrenyin.png", 64, 64);
            g = CutButtonImage.getImage("img/xuan/图标1/lushengyin.png", 64, 64);
            g1 = CutButtonImage.getImage("img/xuan/图标1/luxiyin.png", 64, 64);
            g2 = CutButtonImage.getImage("img/xuan/图标1/lulingyin.png", 64, 64);
            g3 = CutButtonImage.getImage("img/xuan/图标1/luyuanyin.png", 64, 64);
            r2 = CutButtonImage.getImage("img/xuan/图标1/hongfengyin1.png", 36, 36);
            r3 = CutButtonImage.getImage("img/xuan/图标1/hongpanyin1.png", 36, 36);
            b4 = CutButtonImage.getImage("img/xuan/图标1/lanshayin1.png", 36, 36);
            b5 = CutButtonImage.getImage("img/xuan/图标1/langangyin1.png", 36, 36);
            b6 = CutButtonImage.getImage("img/xuan/图标1/lanpoyin1.png", 36, 36);
            b7 = CutButtonImage.getImage("img/xuan/图标1/lanyingyin1.png", 36, 36);
            y2 = CutButtonImage.getImage("img/xuan/图标1/huangdingyin1.png", 36, 36);
            y3 = CutButtonImage.getImage("img/xuan/图标1/huangrenyin1.png", 36, 36);
            g4 = CutButtonImage.getImage("img/xuan/图标1/lushengyin1.png", 36, 36);
            g5 = CutButtonImage.getImage("img/xuan/图标1/luxiyin1.png", 36, 36);
            g6 = CutButtonImage.getImage("img/xuan/图标1/lulingyin1.png", 36, 36);
            g7 = CutButtonImage.getImage("img/xuan/图标1/luyuanyin1.png", 36, 36);
            kaikong = CutButtonImage.getImage("img/xuan/xya4_副本2.png", 64, 64);
            weikaiqi = CutButtonImage.getImage("img/xuan/xya4_副本1.png", 64, 64);
            fenyin = CutButtonImage.getImage("img/xuan/xya4_副本.png", 64, 64);
            hong = CutButtonImage.getImage("img/xuan/xya2.png", 64, 64);
            lan = CutButtonImage.getImage("img/xuan/xya4.png", 64, 64);
            honglan = CutButtonImage.getImage("img/xuan/xya6.png", 64, 64);
            huang = CutButtonImage.getImage("img/xuan/xya8.png", 64, 64);
            huanghong = CutButtonImage.getImage("img/xuan/xya10.png", 64, 64);
            huanglan = CutButtonImage.getImage("img/xuan/xya12.png", 64, 64);
            honglanhuang = CutButtonImage.getImage("img/xuan/xya14.png", 64, 64);
            lv = CutButtonImage.getImage("img/xuan/xya16.png", 64, 64);
            lvhong = CutButtonImage.getImage("img/xuan/xya18.png", 64, 64);
            lvlan = CutButtonImage.getImage("img/xuan/xya20.png", 64, 64);
            honglanlv = CutButtonImage.getImage("img/xuan/xya22.png", 64, 64);
            huanglv = CutButtonImage.getImage("img/xuan/xya24.png", 64, 64);
            huanghonglv = CutButtonImage.getImage("img/xuan/xya26.png", 64, 64);
            huanglanlv = CutButtonImage.getImage("img/xuan/xya28.png", 64, 64);
            huanghonglanlv = CutButtonImage.getImage("img/xuan/xya30.png", 64, 64);
            weikaiqi1 = CutButtonImage.getImage("img/xuan/xyb2_副本1.png", 36, 36);
            fenyin1 = CutButtonImage.getImage("img/xuan/xyb2_副本.png", 36, 36);
            kaikong1 = CutButtonImage.getImage("img/xuan/xyb2_副本2.png", 36, 36);
            hong1 = CutButtonImage.getImage("img/xuan/xyb2.png", 36, 36);
            lan1 = CutButtonImage.getImage("img/xuan/xyb4.png", 36, 36);
            honglan1 = CutButtonImage.getImage("img/xuan/xyb6.png", 36, 36);
            huang1 = CutButtonImage.getImage("img/xuan/xyb8.png", 36, 36);
            huanghong1 = CutButtonImage.getImage("img/xuan/xyb10.png", 36, 36);
            huanglan1 = CutButtonImage.getImage("img/xuan/xyb12.png", 36, 36);
            honglanhuang1 = CutButtonImage.getImage("img/xuan/xyb14.png", 36, 36);
            lv1 = CutButtonImage.getImage("img/xuan/xyb16.png", 36, 36);
            lvhong1 = CutButtonImage.getImage("img/xuan/xyb18.png", 36, 36);
            lvlan1 = CutButtonImage.getImage("img/xuan/xyb20.png", 36, 36);
            honglanlv1 = CutButtonImage.getImage("img/xuan/xyb22.png", 36, 36);
            huanglv1 = CutButtonImage.getImage("img/xuan/xyb24.png", 36, 36);
            huanghonglv1 = CutButtonImage.getImage("img/xuan/xyb26.png", 36, 36);
            huanglanlv1 = CutButtonImage.getImage("img/xuan/xyb28.png", 36, 36);
            huanghonglanlv1 = CutButtonImage.getImage("img/xuan/xyb30.png", 36, 36);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(XuanBao xuanBao) {
        this.xuanBao = xuanBao;
        for (int i = 0; i < 5; i++) {
            this.RuneListLabel[i].setVisible(false);
            this.back[i].setVisible(false);
        }
        if (xuanBao != null) {
            int m;
            this.jPanelxuanbaozhu.setIcon(RoleXuanBao.lingbaoimg(xuanBao.getSkin(), 120, 120));
            this.jPanelxuanbaoText.setText(xuanBao.getMark3());
            (RoleXuanBao.getRoleXuanBao()).zhuangbei = xuanBao;
            int lvl = xuanBao.getLvl();
            int k = (xuanBao.getRgb().split("\\|")).length;
            String[] rgb = xuanBao.getRgb().split("\\|");
            int num = 5;
            if (lvl < 40) {
                num = 0;
            } else if (lvl < 60) {
                num = 1;
            } else if (lvl < 80) {
                num = 2;
            } else if (lvl < 100) {
                num = 3;
            } else if (lvl < 120) {
                num = 4;
            }
            num = Math.min(num, k);
            int c = 0;
            for (; c < num; c++) {
                this.RuneListLabel[c].setIcon(fenyin);
                switch (rgb[c]) {
                    case "红":
                        this.back[c].setIcon(hong);
                        break;
                    case "蓝":
                        this.back[c].setIcon(lan);
                        break;
                    case "黄":
                        this.back[c].setIcon(huang);
                        break;
                    case "绿":
                        this.back[c].setIcon(lv);
                        break;
                    case "蓝=绿":
                        this.back[c].setIcon(lvlan);
                        break;
                    case "红=蓝":
                        this.back[c].setIcon(honglan);
                        break;

                    case "红=黄":
                        this.back[c].setIcon(huanghong);
                        break;
                    case "黄=蓝":
                        this.back[c].setIcon(huanglan);
                        break;
                    case "红=蓝=黄":
                        this.back[c].setIcon(honglanhuang);
                        break;
                    case "红=蓝=绿":
                        this.back[c].setIcon(honglanlv);
                        break;
                    case "红=绿":
                        this.back[c].setIcon(lvhong);
                        break;
                    case "黄=绿":
                        this.back[c].setIcon(huanglv);
                        break;
                    case "黄=红=绿":
                        this.back[c].setIcon(huanghonglv);
                        break;
                    case "黄=蓝=绿":
                        this.back[c].setIcon(huanglanlv);
                        break;
                    case "黄=红=蓝=绿":
                        this.back[c].setIcon(huanghonglanlv);
                        break;
                }
            }
            for (; c < k; c++) {
                this.RuneListLabel[c].setIcon(weikaiqi);
                switch (rgb[c]) {
                    case "红":
                        this.back[c].setIcon(hong);
                        break;
                    case "蓝":
                        this.back[c].setIcon(lan);
                        break;
                    case "黄":
                        this.back[c].setIcon(huang);
                        break;
                    case "绿":
                        this.back[c].setIcon(lv);
                        break;
                    case "蓝=绿":
                        this.back[c].setIcon(lvlan);
                        break;
                    case "红=蓝":
                        this.back[c].setIcon(honglan);
                        break;
                    case "红=黄":
                        this.back[c].setIcon(huanghong);
                        break;
                    case "黄=蓝":
                        this.back[c].setIcon(huanglan);
                        break;
                    case "红=蓝=黄":
                        this.back[c].setIcon(honglanhuang);
                        break;
                    case "红=蓝=绿":
                        this.back[c].setIcon(honglanlv);
                        break;
                    case "红=绿":
                        this.back[c].setIcon(lvhong);
                        break;

                    case "黄=绿":
                        this.back[c].setIcon(huanglv);
                        break;

                    case "黄=红=绿":
                        this.back[c].setIcon(huanghonglv);

                        break;

                    case "黄=蓝=绿":
                        this.back[c].setIcon(huanglanlv);

                        break;

                    case "黄=红=蓝=绿":
                        this.back[c].setIcon(huanghonglanlv);

                        break;

                }

            }
            for (int j = 0; j <= xuanBao.getNum() - 1; j++) {
                this.RuneListLabel[j].setIcon(kaikong);
                switch (rgb[j]) {
                    case "红":
                        this.back[j].setIcon(hong);
                        break;
                    case "蓝":
                        this.back[j].setIcon(lan);
                        break;
                    case "黄":
                        this.back[j].setIcon(huang);

                        break;

                    case "绿":
                        this.back[j].setIcon(lv);

                        break;

                    case "蓝=绿":
                        this.back[j].setIcon(lvlan);

                        break;

                    case "红=蓝":
                        this.back[j].setIcon(honglan);

                        break;

                    case "红=黄":
                        this.back[j].setIcon(huanghong);

                        break;

                    case "黄=蓝":
                        this.back[j].setIcon(huanglan);

                        break;

                    case "红=蓝=黄":
                        this.back[j].setIcon(honglanhuang);

                        break;

                    case "红=蓝=绿":
                        this.back[j].setIcon(honglanlv);

                        break;

                    case "红=绿":
                        this.back[j].setIcon(lvhong);

                        break;

                    case "黄=绿":
                        this.back[j].setIcon(huanglv);

                        break;

                    case "黄=红=绿":
                        this.back[j].setIcon(huanghonglv);

                        break;

                    case "黄=蓝=绿":
                        this.back[j].setIcon(huanglanlv);

                        break;

                    case "黄=红=蓝=绿":
                        /* 589 */
                        this.back[j].setIcon(huanghonglanlv);

                        break;

                }

            }
            if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
                String[] fushi = xuanBao.getFushi().split("\\|");
                for (int n = 0; n < fushi.length; n++) {
                    Goodstable goodstable = GoodsListFromServerUntil.fushis.get(new BigDecimal(fushi[n]));
                    if (goodstable != null) {
                        if (goodstable.getType().longValue() == 10018L) {
                            if (goodstable.getGoodsname().equals("红峰印")) {
                                this.RuneListLabel[n].setIcon(r);
                            } else if (goodstable.getGoodsname().equals("红磐印")) {
                                this.RuneListLabel[n].setIcon(r1);
                            }
                        } else if (goodstable.getType() == 10012L) {
                            if (goodstable.getGoodsname().equals("蓝煞印")) {
                                this.RuneListLabel[n].setIcon(b);
                            } else if (goodstable.getGoodsname().equals("蓝罡印")) {
                                this.RuneListLabel[n].setIcon(b1);
                            } else if (goodstable.getGoodsname().equals("蓝破印")) {
                                this.RuneListLabel[n].setIcon(b2);
                            } else if (goodstable.getGoodsname().equals("蓝影印")) {
                                this.RuneListLabel[n].setIcon(b3);
                            }
                        } else if (goodstable.getType() == 10013L) {
                            if (goodstable.getGoodsname().equals("绿生印")) {
                                this.RuneListLabel[n].setIcon(g);
                            } else if (goodstable.getGoodsname().equals("绿息印")) {
                                this.RuneListLabel[n].setIcon(g1);
                            }  else if (goodstable.getGoodsname().equals("绿灵印")) {
                                this.RuneListLabel[n].setIcon(g2);
                            }  else if (goodstable.getGoodsname().equals("绿源印")) {
                                this.RuneListLabel[n].setIcon(g3);

                            }
                        } else if (goodstable.getType() == 10014L) {
                            if (goodstable.getGoodsname().equals("黄定印")) {
                                this.RuneListLabel[n].setIcon(y);
                            } else if (goodstable.getGoodsname().equals("黄韧印")) {
                                this.RuneListLabel[n].setIcon(y1);
                            }

                        }

                    }

                }

            }
            switch (xuanBao.getPinzhi()) {
                case "初品":
                    for (m = 0; m < 3; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);
                    }
                    this.RuneListLabel[0].setBounds(298, 48, 64, 64);
                    this.back[0].setBounds(297, 47, 64, 64);
                    this.RuneListLabel[1].setBounds(208, 233, 64, 64);
                    this.back[1].setBounds(207, 232, 64, 64);
                    this.RuneListLabel[2].setBounds(390, 233, 64, 64);
                    this.back[2].setBounds(389, 232, 64, 64);
                    break;
                case "中品":
                    for (m = 0; m < 4; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);
                    }
                    this.RuneListLabel[0].setBounds(298, 48, 64, 64);
                    this.back[0].setBounds(297, 47, 64, 64);
                    this.RuneListLabel[1].setBounds(195, 150, 64, 64);
                    this.back[1].setBounds(194, 149, 64, 64);
                    this.RuneListLabel[2].setBounds(298, 262, 64, 64);
                    this.back[2].setBounds(297, 261, 64, 64);
                    this.RuneListLabel[3].setBounds(407, 150, 64, 64);
                    this.back[3].setBounds(406, 149, 64, 64);
                    break;
                case "珍品":
                    for (m = 0; m < 5; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);

                    }
                    this.RuneListLabel[0].setBounds(298, 48, 64, 64);
                    this.back[0].setBounds(297, 47, 64, 64);
                    this.RuneListLabel[1].setBounds(195, 150, 64, 64);
                    this.back[1].setBounds(194, 149, 64, 64);
                    this.RuneListLabel[2].setBounds(239, 257, 64, 64);
                    this.back[2].setBounds(238, 256, 64, 64);
                    this.RuneListLabel[3].setBounds(364, 256, 64, 64);
                    this.back[3].setBounds(363, 255, 64, 64);
                    this.RuneListLabel[4].setBounds(404, 149, 64, 64);
                    this.back[4].setBounds(403, 148, 64, 64);

                    break;

            }

        }

    }

}


