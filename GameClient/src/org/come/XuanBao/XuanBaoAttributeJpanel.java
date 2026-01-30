
package org.come.XuanBao;


import com.tool.btn.FormsOnOffBtn;
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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.plaf.ScrollBarUI;

import org.cbg.btn.TrslationBtn;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoEquipmentJpanel;
import org.come.XuanBao.XuanBaoMouseListener;
import org.come.XuanBao.XuanBaoPagingBtn;
import org.come.XuanBao.XuanBaoXiuLianJpanel;
import org.come.bean.Skill;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.entity.XuanBaoSkill;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.SrcollPanelUI;
import org.come.until.UserMessUntil;
import org.skill.panel.SetupJpanel1;
import org.skill.panel.SetupJpanel2;
import org.skill.panel.SetupJpanel3;
import org.skill.panel.WrapTextPanel;


public class XuanBaoAttributeJpanel extends JPanel {
    ImageIcon icon = new ImageIcon("img/xuan/2.png");
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
    public JPanel[] jPanel = new JPanel[8];
    public JLabel jPanelxuanbao = new JLabel();
    private JLabel[] RuneListLabel = new JLabel[5];
    private JLabel[] back = new JLabel[5];

    private WrapTextPanel skilltext;
    private XuanBaoPagingBtn shanchu1; // 添加删除按钮字段
    private WrapTextPanel skilltext1;
    private WrapTextPanel skilltext2;
    private JScrollPane jScrollPane;
    public JLabel xiulianjpanel;
    private XuanBaoPagingBtn xiulian;
    private XuanBaoPagingBtn xiulian1;
    private WrapTextPanel skillzhu;
    public JTextPane jTextPaneremark = new JTextPane();


    public ImageIcon jdt;


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
                        String getname = optionJpanel1.getJlist().getSelectedValue();
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
                        String getname = (String) optionJpanel2.getJlist().getSelectedValue();
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
                        String getname = (String) optionJpanel3.getJlist().getSelectedValue();
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
        resolutionText.setBounds(x + 2, y - 20, 60, 20);
        this.add(resolutionText);
        chooseDownArrows.setBounds(x, y - 20, width, 20);

        try {
            chooseDownArrows.setIcons(CutButtonImage.cuts("img/xuan/xl100_副本.png"));
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }

        this.add(chooseDownArrows);
    }

    public XuanBaoAttributeJpanel() {
        this.jdt = new ImageIcon("inkImg/button/66.png");
        setPreferredSize(new Dimension(619, 482));
        setLayout((LayoutManager) null);
        setBackground(UIUtils.Color_BACK);
        String[][] resolutionData = {{"通用", "承脉"}, {"类型", "攻击", "防御", "辅助", "通用"}, {"品质", "初品", "中品", "珍品"}};
        createOptionPanel(75, 455, 60, 60, resolutionData[0], 1);
        createOptionPanel(145, 455, 60, 60, resolutionData[1], 2);
        createOptionPanel(215, 455, 60, 60, resolutionData[2], 3);
        this.btnLast1 = new XuanBaoPagingBtn("img/xuan/47_.png", 1, 2, (String) null);
        this.btnLast1.setBounds(50, 375, 19, 20);
        add(this.btnLast1);
        this.btnNext1 = new XuanBaoPagingBtn("img/xuan/48_.png", 1, 3, (String) null);
        this.btnNext1.setBounds(50, 400, 19, 20);
        add(this.btnNext1);
        this.shanchu1 = new XuanBaoPagingBtn("inkImg/Client/nex.png", 1, 8039,null);
        this.shanchu1.setBounds(245, 233, 30, 17);
        add((Component) this.shanchu1);
        int i;
        for (i = 0; i < 8; i++) {
            int row = i % 4 * 51;
            int col = i / 4 * 51;
            this.jPanel[i] = new JPanel();
            this.jPanel[i].setOpaque(false);
            this.jPanel[i].setBounds(85 + row, 345 + col, 48, 48);
            this.jPanel[i].addMouseListener(new XuanBaoMouseListener(i));
            add(this.jPanel[i]);
        }
        this.jPanelxuanbao.setOpaque(false);
        this.jPanelxuanbao.setBounds(115, 90, 180, 180);
        this.jPanelxuanbao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) { // 右键点击
                    RoleXuanBao.getRoleXuanBao().choseBao = null;
                    jPanelxuanbao.setIcon(null);
                    skilltext.setText("", 0.1f, null);
                    skilltext.setText1("", 0.1f);
                    skilltext.setText2("", 0.1f);
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
        add(this.jPanelxuanbao);
        for (i = 0; i < 5; i++) {
            this.back[i] = new JLabel();
            this.back[i].setOpaque(false);
            this.back[i].setBounds(300 + i * 38, 230, 36, 36);
            add(this.back[i]);
        }

        for (i = 0; i < 5; i++) {
            this.RuneListLabel[i] = new JLabel();
            this.RuneListLabel[i].setOpaque(false);
            this.RuneListLabel[i].setBounds(300 + i * 38, 230, 36, 36);
            add(this.RuneListLabel[i]);
        }
        this.skilltext = new WrapTextPanel("", UIUtils.TEXT_NAME_FONT, 999);
        this.skilltext.setOpaque(false);
        this.skilltext.setFont(UIUtils.TEXT_FONT);
        this.skilltext.setBounds(0, 0, 999, 300);
        this.jScrollPane = new JScrollPane(this.skilltext);
        this.jScrollPane.setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.jScrollPane.setBounds(300, 310, 280, 136);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        add(this.jScrollPane);
        this.xiulian = new XuanBaoPagingBtn("img/xuan/2_.png", 1, 4, "", UIUtils.COLOR_BTNTEXT);
        this.xiulian.setBounds(245, 298, 34, 17);
        add((Component) this.xiulian);
        this.xiulian1 = new XuanBaoPagingBtn("img/xuan/B411_副本.png", 1, 7, "", UIUtils.COLOR_BTNTEXT);
        this.xiulian1.setBounds(255, 268, 20, 20);
        add((Component) this.xiulian1);
        FormsOnOffBtn deleteBtn = new FormsOnOffBtn("inkImg/Client/nex.png", 1, UIUtils.COLOR_BTNTEXT,UIUtils.TEXT_FONT2, "", 8039);
        deleteBtn.setBounds(245, 233, 30, 17);
        add(deleteBtn);
//        this.skillzhu = new WrapTextPanel("cas as das as 阿松大阿松大", UIUtils.TEXT_NAME_FONT, 280);
//        this.skillzhu.setOpaque(false);
//        this.skillzhu.setFont(UIUtils.TEXT_FONT1);
//        this.skillzhu.setBounds(270, 80, 310, 400);
        this.jTextPaneremark.setOpaque(false);
        this.jTextPaneremark.setContentType("text/html");
        this.jTextPaneremark.setEditable(false);
        this.jTextPaneremark.setFocusable(false);
        this.jTextPaneremark.setBounds(295, 78, 290, 400);
        add(this.jTextPaneremark);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.icon == null) {
            this.icon = new ImageIcon("img/xuan/2.png");

        }
        g.drawImage(this.icon.getImage(), 0, 0, 619, 482, this);
        RoleXuanBao.getRoleXuanBao().drawF(g, 75, 330);
        if ((RoleXuanBao.getRoleXuanBao()).choseBao != null) {
            int lvl = (RoleXuanBao.getRoleXuanBao()).choseBao.getLvl();
            int xun = (RoleXuanBao.getRoleXuanBao()).choseBao.getXuanyun();
            g.setColor(Color.WHITE);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawImage(this.jdt.getImage(), 122, 298, Math.min(106, (int) ((106 * xun) / XuanBaoXiuLianJpanel.LFExp(lvl))), 15, null);
            g.drawString(lvl + "级", 130, 283);
            g.drawString(xun + "/" + XuanBaoXiuLianJpanel.LFExp(lvl), 140, 310);
            g.setColor(new Color(243, 218, 147, 255));
            g.setFont(UIUtils.TEXT_FONT62);
            for (int i = 0; i < (RoleXuanBao.getRoleXuanBao()).choseBao.getName().length(); i++) {
                g.drawString((RoleXuanBao.getRoleXuanBao()).choseBao.getName().charAt(i) + "", 92, 78 + i * 20);

            }


            g.setColor(new Color(253, 230, 184, 255));
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString("品质", 140, 245);
            g.setColor(new Color(253, 219, 63, 255));
            g.drawString((RoleXuanBao.getRoleXuanBao()).choseBao.pinzhi, 175, 245);

        }

        this.jPanelxuanbao.setBounds(100, 65, 180, 180);
    }


    public List<String> extractBracketContents(String input) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(input);


        List<String> results = new ArrayList<>();


        while (matcher.find()) {
            String content = matcher.group(1);
            if (containsChinese(content)) {
                results.add(content);

            }

        }

        return results;

    }


    public static boolean containsChinese(String str) {
        return (str != null && str.matches("[\\u4e00-\\u9fa5,]+"));

    }


    public float setaplpha(String text, int r, int g, int y, int b) {
        String[] mes = ((String) extractBracketContents(text).get(0)).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return 1.0F;

        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && r >= 1) {
            return 1.0F;

        }

        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return 1.0F;

        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return 1.0F;

        }


        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return 1.0F;

        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return 1.0F;

        }


        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return 1.0F;

        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return 1.0F;

        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return 1.0F;

        }
        return 0.4F;

    }


    public void update(XuanBao xuanBao) {
        for (int i = 0; i < 5; i++) {
            this.RuneListLabel[i].setVisible(false);
            this.back[i].setVisible(false);
        }

        if (xuanBao != null) {
            int m;
            addXuanbao(xuanBao);
            int r_ = 0;
            int g_ = 0;
            int y_ = 0;
            int b_ = 0;
            String css = "<style>p { margin: 0; padding: 0; }</style>";
            if (xuanBao.getSkill_1() != null) {
                String htmlContent = convertToHtml(xuanBao.getSkill_1());
                this.jTextPaneremark.setText(css + htmlContent);
            }
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
                this.RuneListLabel[c].setIcon(XuanBaoEquipmentJpanel.fenyin1);
                switch (rgb[c]) {
                    case "红":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.hong1);
                        break;
                    case "蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lan1);
                        break;
                    case "黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huang1);
                        break;

                    case "绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lv1);

                        break;

                    case "蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lvlan1);

                        break;

                    case "红=蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglan1);

                        break;

                    case "红=黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghong1);

                        break;

                    case "黄=蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglan1);

                        break;

                    case "红=蓝=黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglanhuang1);

                        break;

                    case "红=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglanlv1);

                        break;

                    case "红=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lvhong1);

                        break;

                    case "黄=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglv1);

                        break;

                    case "黄=红=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghonglv1);

                        break;

                    case "黄=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglanlv1);

                        break;

                    case "黄=红=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghonglanlv1);

                        break;

                }


            }
            for (; c < k; c++) {
                this.RuneListLabel[c].setIcon(XuanBaoEquipmentJpanel.weikaiqi1);
                switch (rgb[c]) {

                    case "红":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.hong1);

                        break;

                    case "蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lan1);

                        break;

                    case "黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huang1);

                        break;

                    case "绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lv1);

                        break;

                    case "蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lvlan1);

                        break;

                    case "红=蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglan1);

                        break;

                    case "红=黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghong1);

                        break;

                    case "黄=蓝":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglan1);

                        break;

                    case "红=蓝=黄":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglanhuang1);

                        break;

                    case "红=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.honglanlv1);

                        break;

                    case "红=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.lvhong1);

                        break;

                    case "黄=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglv1);

                        break;

                    case "黄=红=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghonglv1);

                        break;

                    case "黄=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanglanlv1);

                        break;

                    case "黄=红=蓝=绿":
                        this.back[c].setIcon(XuanBaoEquipmentJpanel.huanghonglanlv1);

                        break;

                }

            }
            for (int j = 0; j <= xuanBao.getNum() - 1; j++) {
                this.RuneListLabel[j].setIcon(XuanBaoEquipmentJpanel.kaikong1);
                switch (rgb[j]) {

                    case "红":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.hong1);

                        break;

                    case "蓝":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.lan1);

                        break;

                    case "黄":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huang1);

                        break;

                    case "绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.lv1);

                        break;

                    case "蓝=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.lvlan1);

                        break;

                    case "红=蓝":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.honglan1);

                        break;

                    case "红=黄":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanghong1);

                        break;

                    case "黄=蓝":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanglan1);

                        break;

                    case "红=蓝=黄":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.honglanhuang1);

                        break;

                    case "红=蓝=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.honglanlv1);

                        break;

                    case "红=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.lvhong1);

                        break;

                    case "黄=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanglv1);

                        break;

                    case "黄=红=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanghonglv1);

                        break;

                    case "黄=蓝=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanglanlv1);

                        break;

                    case "黄=红=蓝=绿":
                        this.back[j].setIcon(XuanBaoEquipmentJpanel.huanghonglanlv1);

                        break;

                }

            }
            if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
                String[] fushi = xuanBao.getFushi().split("\\|");
                for (int n = 0; n < fushi.length; n++) {
                    Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(new BigDecimal(fushi[n]));
                    if (goodstable != null) {
                        if (goodstable.getType().longValue() == 10018L) {
                            if (goodstable.getGoodsname().equals("红峰印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.r2);
                                r_++;
                            } else if (goodstable.getGoodsname().equals("红磐印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.r3);
                                r_++;

                            }

                        } else if (goodstable.getType().longValue() == 10012L) {
                            if (goodstable.getGoodsname().equals("蓝煞印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.b4);
                                b_++;
                            } else if (goodstable.getGoodsname().equals("蓝罡印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.b5);
                                b_++;
                            } else if (goodstable.getGoodsname().equals("蓝破印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.b6);
                                b_++;
                            } else if (goodstable.getGoodsname().equals("蓝影印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.b7);
                                b_++;

                            }
                        } else if (goodstable.getType().longValue() == 10013L) {
                            if (goodstable.getGoodsname().equals("绿生印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.g4);
                                g_++;
                            } else if (goodstable.getGoodsname().equals("绿息印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.g5);
                                g_++;
                            } else if (goodstable.getGoodsname().equals("绿灵印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.g6);
                                g_++;
                            } else if (goodstable.getGoodsname().equals("绿源印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.g7);
                                g_++;

                            }
                        } else if (goodstable.getType().longValue() == 10014L) {
                            if (goodstable.getGoodsname().equals("黄定印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.y2);
                                y_++;
                            } else if (goodstable.getGoodsname().equals("黄韧印")) {
                                this.RuneListLabel[n].setIcon(XuanBaoEquipmentJpanel.y3);
                                y_++;

                            }

                        }

                    }

                }

            }


            switch (xuanBao.pinzhi) {

                case "初品":
                    for (m = 0; m < 3; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);

                    }

                    break;


                case "中品":
                    for (m = 0; m < 4; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);
                    }
                    break;
                case "珍品":
                    for (m = 0; m < 5; m++) {
                        this.RuneListLabel[m].setVisible(true);
                        this.back[m].setVisible(true);
                    }
                    break;

            }

            if (xuanBao.skill2 != null && !xuanBao.skill2.isEmpty()) {
                (new String[1])[0] = "";
                String[] mes = extractBracketContents(xuanBao.skill_2).isEmpty() ? new String[1] : ((String) extractBracketContents(xuanBao.skill_2).get(0)).split(",");
                if (setaplpha(xuanBao.skill_2, r_, g_, y_, b_) == 0.4F) {

                    this.skilltext.setText(xuanBao.getMenpai() + "[" + mes[0] + "," + mes[1] + "]", 0.4F, null);

                } else {
                    this.skilltext.setText(xuanBao.skill_2, 1.0F, null);

                }

            }
            if (xuanBao.skill3 != null && !xuanBao.skill3.isEmpty()) {
                (new String[1])[0] = "";
                String[] mes = extractBracketContents(xuanBao.skill_3).isEmpty() ? new String[1] : ((String) extractBracketContents(xuanBao.skill_3).get(0)).split(",");
                if (setaplpha(xuanBao.skill_3, r_, g_, y_, b_) == 0.4F) {
                    this.skilltext.setText1(xuanBao.getMark1() + "[" + mes[0] + "," + mes[1] + "]", 0.4F);
                } else {
                    this.skilltext.setText1(xuanBao.skill_3, 1.0F);
                }
            }
            if (xuanBao.skill4 != null && !xuanBao.skill4.isEmpty()) {
                (new String[1])[0] = "";
                String[] mes = extractBracketContents(xuanBao.skill_4).isEmpty() ? new String[1] : ((String) extractBracketContents(xuanBao.skill_4).get(0)).split(",");
                if (setaplpha(xuanBao.skill_4, r_, g_, y_, b_) == 0.4F) {

                    this.skilltext.setText2(xuanBao.getMark2() + "[" + mes[0] + "," + mes[1] + "]", 0.4F);

                } else {
                    this.skilltext.setText2(xuanBao.skill_4, 1.0F);

                }

            }

        }

    }


    public void addXuanbao(XuanBao xuanBao) {
        int xblvl = 0;


        xblvl = xuanBao.getLvl();
        int r_ = 0;
        int g_ = 0;
        int y_ = 0;
        int b_ = 0;
        int lvl = 0;
        int lvl_r_ = 0;
        int lvl_g_ = 0;
        int lvl_y_ = 0;
        int lvl_b_ = 0;
        if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
            String[] fushi = xuanBao.getFushi().split("\\|");
            for (int i = 0; i < fushi.length; i++) {
                Goodstable goodstable = GoodsListFromServerUntil.fushis.get(new BigDecimal(fushi[i]));
                if (goodstable != null) {
                    if (goodstable.getType() == 10018L) {
                        r_++;
                        lvl_r_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                    } else if (goodstable.getType() == 10012L) {
                        b_++;
                        lvl_b_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                    } else if (goodstable.getType() == 10013L) {
                        g_++;
                        lvl_g_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                    } else if (goodstable.getType() == 10014L) {
                        y_++;
                        lvl_y_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);

                    }

                }

            }

        }
        Skill skill = Objects.requireNonNull(UserMessUntil.getskillformname(xuanBao.getName())).clone();
//        Skill skill = new Skill(UserMessUntil.getskillformname(xuanBao.getName()));
        Map<Integer, String> xuanBaoSkillMap_zhuskill = new HashMap<>();
        Map<Integer, String> xuanBaoSkillMap_xuanyin1 = new HashMap<>();
        Map<Integer, String> xuanBaoSkillMap_xuanyin2 = new HashMap<>();
        Map<Integer, String> xuanBaoSkillMap_xuanyin3 = new HashMap<>();
        if (skill != null) {
            XuanBaoSkill xuanBaoSkill = xuanBaoSkillMap.get(Integer.parseInt(skill.getSkillid()));
            xuanBao.setSkill_1(xuanBao.getSkill1());
            String[] sk1 = xuanBaoSkill.getSkill1().split("&");
            for (String s : sk1) {
                xuanBaoSkillMap_zhuskill.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);

            }
            xuanBaoSkillMap_zhuskill = new TreeMap<>(xuanBaoSkillMap_zhuskill);
            for (Integer i : xuanBaoSkillMap_zhuskill.keySet()) {
                if (xblvl <= i) {
                    String[] p = xuanBaoSkillMap_zhuskill.get(i).split("\\|");
                    for (int c = 0; c < p.length; c++) {
                        if (c == 0) {
                            skill.setValue(String.valueOf(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * xblvl)));

                        }
                        if (c == 1) {
                            skill.setValue1(String.valueOf(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * xblvl)));

                        }
                        if (c == 2) {
                            skill.setValue2(String.valueOf(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * xblvl)));

                        }
                        if (c == 3) {
                            skill.setValue3(String.valueOf(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * xblvl)));

                        }

                    }
                    if (Objects.equals(skill.getSkillid(), "30000")) {
                        skill.setValue3(String.valueOf(3));

                    }
                    if (Objects.equals(skill.getSkillid(), "30003")) {
                        if (i == 49) {
                            skill.setValue(String.valueOf(1));
                        } else if (i == 50) {
                            skill.setValue(String.valueOf(2));
                        } else if (i == 100) {
                            skill.setValue(String.valueOf(2));
                        } else if (i == 150) {
                            skill.setValue(String.valueOf(3));
                        } else if (i == 200) {
                            skill.setValue(String.valueOf(3));

                        }

                    }
                    if (Objects.equals(skill.getSkillid(), "30004")) {
                        if (i == 49) {
                            skill.setValue(String.valueOf(1));
                        } else if (i == 50) {
                            skill.setValue(String.valueOf(2));
                        } else if (i == 100) {
                            skill.setValue(String.valueOf(3));
                        } else if (i == 150) {
                            skill.setValue(String.valueOf(4));
                        } else if (i == 200) {
                            skill.setValue(String.valueOf(4));

                        }

                    }
                    if (Objects.equals(skill.getSkillid(), "30005")) {
                        if (i == 49) {
                            skill.setValue(String.valueOf(3));
                            break;
                        }
                        if (i == 50) {
                            skill.setValue(String.valueOf(4));
                            break;
                        }
                        if (i == 100) {
                            skill.setValue(String.valueOf(5));
                            break;
                        }
                        if (i == 150) {
                            skill.setValue(String.valueOf(5));
                            break;
                        }
                        if (i == 200) {
                            skill.setValue(String.valueOf(5));

                        }

                    }


                    break;

                }

            }
            if (xuanBao.skill2 != null && !xuanBao.skill2.isEmpty() &&
                    setaplpha_(xuanBao.skill2, r_, g_, y_, b_)) {
                lvl = setaplpha(xuanBao.skill2, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                lvl = Math.min(lvl, 30);
                xuanBao.setSkill_2("[" + lvl + "级]" + xuanBao.skill2);
                String[] sk2 = xuanBaoSkill.getSkill2().split("&");
                for (String s : sk2) {
                    xuanBaoSkillMap_xuanyin1.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);

                }
                xuanBaoSkillMap_xuanyin1 = new TreeMap<>(xuanBaoSkillMap_xuanyin1);
                for (Integer i : xuanBaoSkillMap_xuanyin1.keySet()) {
                    if (lvl <= i) {
                        String[] p = ((String) xuanBaoSkillMap_xuanyin1.get(i)).split("\\|");
                        for (int c = 0; c < p.length; c++) {
                            if (c == 0) {
                                skill.setS1(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 1) {
                                skill.setS2(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 2) {
                                skill.setS3(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 3) {
                                skill.setS4(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 4) {
                                skill.setS5(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 5) {
                                skill.setS6(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 6) {
                                skill.setS7(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }

                        }


                        break;

                    }

                }

            }
            if (setaplpha_(xuanBao.skill3, r_, g_, y_, b_)) {
                lvl = setaplpha(xuanBao.skill3, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                lvl = Math.min(lvl, 30);
                xuanBao.setSkill_3("[" + lvl + "级]" + xuanBao.skill3);
                String[] sk2 = xuanBaoSkill.getSkill3().split("&");
                for (String s : sk2) {
                    xuanBaoSkillMap_xuanyin2.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);

                }
                xuanBaoSkillMap_xuanyin2 = new TreeMap<>(xuanBaoSkillMap_xuanyin2);
                for (Integer i : xuanBaoSkillMap_xuanyin2.keySet()) {
                    if (lvl <= i) {
                        String[] p = xuanBaoSkillMap_xuanyin2.get(i).split("\\|");
                        for (int c = 0; c < p.length; c++) {
                            if (c == 0) {
                                skill.setP1(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 1) {
                                skill.setP2(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 2) {
                                skill.setP3(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 3) {
                                skill.setP4(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 4) {
                                skill.setP5(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 5) {
                                skill.setP6(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 6) {
                                skill.setP7(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }

                        }
                        break;
                    }
                }
            }
            if (xuanBao.skill4 != null && !xuanBao.skill4.isEmpty() && setaplpha_(xuanBao.skill4, r_, g_, y_, b_)) {
                lvl = setaplpha(xuanBao.skill4, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                lvl = Math.min(lvl, 30);
                xuanBao.setSkill_4("[" + lvl + "级]" + xuanBao.skill4);
                String[] sk2 = xuanBaoSkill.getSkill4().split("&");
                for (String s : sk2) {
                    xuanBaoSkillMap_xuanyin3.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);
                }
                xuanBaoSkillMap_xuanyin3 = new TreeMap<>(xuanBaoSkillMap_xuanyin3);
                for (Integer i : xuanBaoSkillMap_xuanyin3.keySet()) {
                    if (lvl <= i) {
                        String[] p = xuanBaoSkillMap_xuanyin3.get(i).split("\\|");
                        for (int c = 0; c < p.length; c++) {
                            if (c == 0) {
                                skill.setE1(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 1) {
                                skill.setE2(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 2) {
                                skill.setE3(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 3) {
                                skill.setE4(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 4) {
                                skill.setE5(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 5) {
                                skill.setE6(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }
                            if (c == 6) {
                                skill.setE7(roundToOneDecimalPlace(Double.parseDouble(p[c]) / i * lvl));

                            }

                        }


                        break;

                    }

                }

            }

        }
        test(xuanBao, skill);
        if (xuanBao.getEquipment() == 1) ;

    }


    public static double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10.0D) / 10.0D;

    }


    public void test(XuanBao xuanBao, Skill skill) {
        if (xuanBao.getSkill_1() == null) {
            xuanBao.setSkill_1(xuanBao.skill1);

        }
        if (xuanBao.getSkill_2() == null) {
            xuanBao.setSkill_2(xuanBao.skill2);

        }
        if (xuanBao.getSkill_3() == null) {
            xuanBao.setSkill_3(xuanBao.skill3);

        }
        if (xuanBao.getSkill_4() == null) {
            xuanBao.setSkill_4(xuanBao.skill4);

        }
        if (xuanBao.getSkill1() != null) {
            if (xuanBao.skill1.contains("{技能1}")) {
                xuanBao.setSkill_1(xuanBao.getSkill_1().replace("{技能1}", "#R" + skill.getValue() + "#G"));

            }
            if (xuanBao.skill1.contains("{技能2}")) {
                xuanBao.setSkill_1(xuanBao.getSkill_1().replace("{技能2}", "#R" + skill.getValue1() + "#G"));

            }
            if (xuanBao.skill1.contains("{技能3}")) {
                xuanBao.setSkill_1(xuanBao.getSkill_1().replace("{技能3}", "#R" + skill.getValue2() + "#G"));

            }
            if (xuanBao.skill1.contains("{技能4}")) {
                xuanBao.setSkill_1(xuanBao.getSkill_1().replace("{技能4}", "#R" + skill.getValue3() + "#G"));

            }

        }
        if (xuanBao.skill2 != null) {
            if (xuanBao.skill2.contains("{技能1}")) {
                xuanBao.setSkill_2(xuanBao.getSkill_2().replace("{技能1}", skill.getS1() + ""));

            }
            if (xuanBao.skill2.contains("{技能2}")) {
                xuanBao.setSkill_2(xuanBao.getSkill_2().replace("{技能2}", skill.getS2() + ""));

            }
            if (xuanBao.skill2.contains("{技能3}")) {
                xuanBao.setSkill_2(xuanBao.getSkill_2().replace("{技能3}", skill.getS3() + ""));

            }
            if (xuanBao.skill2.contains("{技能4}")) {
                xuanBao.setSkill_2(xuanBao.getSkill_2().replace("{技能4}", skill.getS4() + ""));

            }

        }
        if (xuanBao.skill3 != null) {
            if (xuanBao.skill3.contains("{技能1}")) {
                xuanBao.setSkill_3(xuanBao.getSkill_3().replace("{技能1}", skill.getP1() + ""));

            }
            if (xuanBao.skill3.contains("{技能2}")) {
                xuanBao.setSkill_3(xuanBao.getSkill_3().replace("{技能2}", skill.getP2() + ""));

            }
            if (xuanBao.skill3.contains("{技能3}")) {
                xuanBao.setSkill_3(xuanBao.getSkill_3().replace("{技能3}", skill.getP3() + ""));

            }
            if (xuanBao.skill3.contains("{技能4}")) {
                xuanBao.setSkill_3(xuanBao.getSkill_3().replace("{技能4}", skill.getP4() + ""));

            }

        }
        if (xuanBao.skill4 != null) {
            if (xuanBao.skill4.contains("{技能1}")) {
                xuanBao.setSkill_4(xuanBao.getSkill_4().replace("{技能1}", skill.getE1() + ""));

            }
            if (xuanBao.skill4.contains("{技能2}")) {
                xuanBao.setSkill_4(xuanBao.getSkill_4().replace("{技能2}", skill.getE2() + ""));

            }
            if (xuanBao.skill4.contains("{技能3}")) {
                xuanBao.setSkill_4(xuanBao.getSkill_4().replace("{技能3}", skill.getE3() + ""));

            }
            if (xuanBao.skill4.contains("{技能4}"))
                xuanBao.setSkill_4(xuanBao.getSkill_4().replace("{技能4}", skill.getE4() + ""));

        }

    }


    public boolean setaplpha_(String text, int r, int g, int y, int b) {
        String[] mes = ((String) extractBracketContents(text).get(0)).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return true;

        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return true;

        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return true;

        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && y >= 1) {
            return true;

        }
        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return true;

        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return true;

        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return true;

        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return true;

        }
        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return true;

        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return true;

        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return true;

        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return true;

        }
        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return true;

        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return true;

        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return true;

        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return true;

        }
        return false;

    }


    public int setaplpha(String text, int r, int g, int y, int b, int lvl_r, int lvl_g, int lvl_y, int lvl_b) {
        String[] mes = ((String) extractBracketContents(text).get(0)).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return lvl_r + lvl_b;

        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return lvl_b;

        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return lvl_b + lvl_g;

        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && y >= 1) {
            return lvl_b + lvl_y;

        }
        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return lvl_r + lvl_b;

        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return lvl_r;

        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return lvl_r + lvl_g;

        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return lvl_r + lvl_y;

        }
        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return lvl_g + lvl_b;

        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return lvl_g;

        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return lvl_g + lvl_r;

        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return lvl_g + lvl_y;

        }
        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return lvl_y + lvl_b;

        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return lvl_y;

        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return lvl_y + lvl_r;

        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return lvl_y + lvl_g;

        }
        return 0;

    }


    public static String convertToHtml(String input) {
        String result = input.replaceAll("#R(.*?)(#G|$)", "<font size='4' face='SimSun' color='#FF0000'>$1</font>$2");
        result = result.replaceAll("#G(.*?)(#R|$)", "<font size='4' face='SimSun' color='#00FF00'>$1</font>$2");
        return ("<p><font size='4' face='SimSun' color='#00FF00'>" + result + "</font></p>").replace("#G", "");

    }

    public static Map<Integer, XuanBaoSkill> xuanBaoSkillMap = new ConcurrentHashMap<>();


    static {
        xuanBaoSkillMap.put(30000, new XuanBaoSkill(30000, "50=3086&100=5374&150=7433&200=9357", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3", "10=6.6&15=9.1&20=11.5&25=13.7&30=15.9", "10=31.5&15=43.6&20=54.9&25=65.6&30=75.9", 0, 1, 2));
        xuanBaoSkillMap.put(30001, new XuanBaoSkill(30001, "49=15|5&50=13|4&100=11|3&150=9|2&200=9|2", "10=6.3|6.3&15=8.7|8.7&20=10.9|10.9&25=13.1|13.1&30=15.1|15.1", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3", "10=6.3&15=8.7&20=10.9&25=13.1&30=15.1", 1, 2, 3));
        xuanBaoSkillMap.put(30002, new XuanBaoSkill(30002, "50=20.7&100=36.1&150=49.9&200=62.9", "10=2.6&15=3.6&20=4.6&25=5.5&30=100", "10=6.6&15=9.1&20=11.5&25=13.7&30=100", "10=31.5&15=43.6&20=54.9&25=65.6&30=75.9", 0, 1, 3));
        xuanBaoSkillMap.put(30003, new XuanBaoSkill(30003, "49=1&50=2&100=2&150=3&200=3", "10=11.3&15=15.7&20=19.7&25=23.6&30=27.3", "10=2&15=2.7&20=3.5&25=4.2&30=4.8", "10=3785&15=5236&20=6591&25=7879&30=9116", 0, 3, 2));
        xuanBaoSkillMap.put(30004, new XuanBaoSkill(30004, "49=1&50=2&100=3&150=4&200=4", "10=15.1&15=20.9&20=26.3&25=31.5&30=36.4", "10=1&15=2&20=2&25=3&30=3", "10=1&15=2&20=2&25=3&30=3", 0, 2, 1));
        xuanBaoSkillMap.put(30005, new XuanBaoSkill(30005, "49=3|12582|15348&50=4|20059|24487&100=5|25855|31570&150=5|31072|37947&200=5|35945|43903", "10=20.5&15=28.3&20=35.7&25=42.6&30=49.3", "10=18.9&15=26.1&20=32.9&25=39.3&30=45.5", "10=9.4&15=13&20=16.4&25=19.6&30=22.7", 0, 2, 1));
        xuanBaoSkillMap.put(30006, new XuanBaoSkill(30006, "50=21.9|9.8&100=27|12.1&150=30.5|13.7&200=33.3|14.9", "10=20.8&15=24.8&20=28.1&25=30.9&30=33.4", "10=27.4&15=32.6&20=36.9&25=40.7&30=44", "10=36.6&15=43.5&20=49.3&25=54.2&30=58.7", 0, 2, 1));
        xuanBaoSkillMap.put(30007, new XuanBaoSkill(30007, "50=21.9|9.8&100=27|12.1&150=30.5|13.7&200=33.3|14.9", "10=20.8&15=24.8&20=28.1&25=30.9&30=33.4", "10=27.4&15=32.6&20=36.9&25=40.7&30=44", "10=36.6&15=43.5&20=49.3&25=54.2&30=58.7", 0, 2, 1));
        xuanBaoSkillMap.put(30008, new XuanBaoSkill(30008, "50=1.6|1.6&100=3|3&150=4.1|4.1&200=4.8|4.8", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=32.8&15=45.3&20=57.1&25=68.2&30=79", 0, 2, 1));
        xuanBaoSkillMap.put(30009, new XuanBaoSkill(30009, "50=1.6|1.6&100=3|3&150=4.1|4.1&200=4.8|4.8", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=32.8&15=45.3&20=57.1&25=68.2&30=79", 0, 2, 1));
        xuanBaoSkillMap.put(30010, new XuanBaoSkill(30010, "50=0.7|13&100=1.0|19.8&150=1.3|25.2&200=1.5|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", 0, 2, 1));
        xuanBaoSkillMap.put(30011, new XuanBaoSkill(30011, "50=1.3|13&100=2|19.8&150=2.5|25.2&200=2.9|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "", 0, 2, 1));
        xuanBaoSkillMap.put(30012, new XuanBaoSkill(30012, "50=0.7|13&100=1.0|19.8&150=1.3|25.2&200=1.5|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "", 0, 2, 1));
        xuanBaoSkillMap.put(30013, new XuanBaoSkill(30013, "50=19339|28.5|28.9&100=24779|45.5|47.5&150=29675|60.7|64.3&200=34249|75|80", "10=7.0&15=9.2&20=11.2&25=13.2&30=15", "10=21.5&15=29.3&20=36.5&25=43.4&30=50.0", "10=27.7&15=38.3&20=48.3&25=57.5&30=66.8", 0, 2, 1));
        xuanBaoSkillMap.put(30014, new XuanBaoSkill(30014, "50=2|463&100=2|881&150=3|1213&200=3|1426", "10=1388&15=1919&20=2416&25=2889&30=3342", "10=2776&15=3839&20=4833&25=5778&30=6685", "10=2&15=2&20=3&25=3&30=4", 0, 2, 1));
        xuanBaoSkillMap.put(30015, new XuanBaoSkill(30015, "50=3.3|7.4&100=6.2|14.1&150=8.5|19.4&200=10|22.8", "10=2.0&15=2.8&20=3.6&25=4.3&30=5.0", "10=2.2&15=3.1&20=3.9&25=4.7&30=5.4", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3", 0, 2, 1));
        xuanBaoSkillMap.put(30016, new XuanBaoSkill(30016, "50=22638&100=29300&150=35297&200=40898", "10=14.5|1&15=20|2&20=25.2|2&25=30.2|3&30=34.9|3", "10=2&15=3&20=4&25=5&30=6", "10=6.6&15=9.1&20=11.5&25=13.7&30=15.9", 0, 2, 1));
        xuanBaoSkillMap.put(30017, new XuanBaoSkill(30017, "50=24203|6.6|11.1|77.4&100=32507|9.9|16.2|97.7&150=39981|12.5|20.8|116&200=46964|14.8|25|133.1", "10=5.9&15=7.8&20=9.6&25=11.4&30=13", "10=2.4|4.9&15=2.9|5.8&20=3.3|6.7&25=3.8|7.6&30=4.2|8.4", "10=6.6&15=8.5&20=10.3&25=12.1&30=13.7", 0, 2, 1));
        xuanBaoSkillMap.put(30018, new XuanBaoSkill(30018, "50=5.7|11.5|11.8&100=9.1|18.3|20.3&150=12.2|24.4|27.9&200=15|30.1|35", "10=9.6|7.2&15=13.0|9.6&20=16.1|11.8&25=19.1|14.0&30=22.0|70.0", "10=10.4&15=13.5&20=16.4&25=19.2&30=21.9", "10=2.3&15=3.1&20=3.7&25=4.4&30=5.0", 0, 2, 1));
        xuanBaoSkillMap.put(30019, new XuanBaoSkill(30019, "50=6.8|3&100=11.9|3&150=16.5|4&200=20.7|4", "10=3.1&15=4.0&20=4.8&25=5.5&30=6.1", "10=1&15=1&20=2&25=2&30=2", "10=20.3&15=27.8&20=34.8&25=41.5&30=47.9"));
        xuanBaoSkillMap.put(30020, new XuanBaoSkill(30020, "50=2&100=2&150=3&200=3", "10=3.7&15=5.2&20=6.5&25=7.8&30=9.1", "10=1&15=1&20=2&25=2&30=2", "10=5047&15=6981&20=8788&25=10560&30=12155"));
        xuanBaoSkillMap.put(30021, new XuanBaoSkill(30021, "50=9829|9829|44.2&100=12371|12371|69.7&150=14659|14659|92.5&200=16797|16797|113.9", "10=41.0&15=56.7&20=71.4&25=85.3&30=98.7", "10=9.4&15=13.0&20=16.4&25=19.6&30=227", "10=11.3&15=15.7&20=19.7&25=23.6&30=273", 0, 2, 1));
        xuanBaoSkillMap.put(30022, new XuanBaoSkill(30022, "50=7.5|5.6|3.7&100=12.6|9.4|6.3&150=17.2|12.9|8.6&200=21.4|16.1|10.7", "10=10.4&15=14.0&20=17.4&25=20.6&30=23.7", "10=13.2&15=18.3&20=23.0&25=27.5&30=31.9", "10=25.2&15=30.9&20=35.7&25=40.0&30=43.8", 0, 2, 1));
        xuanBaoSkillMap.put(30023, new XuanBaoSkill(30023, "50=5.7|5.7|17.5&100=9.1|9.1|29.3&150=12.2|12.2|40&200=15|15|10", "10=9.8&15=13.4&20=16.8&25=20.0&30=23.1", "10=2.4&15=2.9&20=3.3&25=3.6&30=4.2", "10=17.9&15=24.0&20=29.6&25=35.0&30=40.1", 0, 2, 1));
        xuanBaoSkillMap.put(30024, new XuanBaoSkill(30024, "50=3|26.2&100=4|33.9&150=5|40.7&200=5|47.1", "10=4.2&15=5.7&20=7.0&25=8.3&30=9.6", "10=12.7&15=17.5&20=22.0&25=26.3&30=30.4", "10=28.9&15=36.7&20=43.9&25=50.8&30=57.4", 0, 2, 1));
        xuanBaoSkillMap.put(30025, new XuanBaoSkill(30025, "50=65.4|21.8&100=76.1|30.3&150=83.9|37.9&200=90.2|45", "10=3.7&15=5.2&20=6.5&25=7.8&30=9.1", "10=2.9&15=3.8&20=4.7&25=5.6&30=6.4", "10=56|2&15=68|3&20=79|3&25=90|4&30=100|4", 0, 2, 1));
        xuanBaoSkillMap.put(30026, new XuanBaoSkill(30026, "50=3.2&100=4&150=4.6&200=5.2", "10=2&15=3&20=3&25=3&30=4", "10=21.6&15=28.8&20=35.6&25=42.0&30=48.2", "10=64.1&15=68.3&20=110.9&25=132.4&30=153.0", 0, 2, 1));
        xuanBaoSkillMap.put(30027, new XuanBaoSkill(30027, "50=19&100=31.7&150=43.1&200=53.8", "10=7.7&15=10.1&20=12.3&25=14.4&30=16.3", "10=3807&15=5258&20=6613&25=7901&30=9138", "10=8.4|66.0&15=11.5|74.5&20=14.4|81.2&25=17.2|86.8&30=19.9|91.7", 0, 2, 1));
        xuanBaoSkillMap.put(30028, new XuanBaoSkill(30028, "50=14.8|3&100=28.2|3&150=38.8|4&200=45.6|4", "10=25.8&15=35.7&20=45.0&25=53.8&30=62.2", "10=23.0&15=31.8&20=40.0&25=47.8&30=55.4", "10=2&15=2&20=2&25=3&30=3", 0, 2, 1));
        xuanBaoSkillMap.put(30029, new XuanBaoSkill(30029, "50=11.8|11.8|23&100=20.3|20.3|30.3&150=27.9|27.9|35.7&200=35|35|40", "10=2.5&15=3.4&20=4.3&25=5.2&30=6.0", "10=2.8&15=3.9&20=4.9&25=5.9&30=6.8", "10=3.1&15=4.3&20=5.4&25=6.5&30=7.5", 0, 2, 1));
        xuanBaoSkillMap.put(30030, new XuanBaoSkill(30030, "50=7.4&100=14.1&150=19.4&200=22.8", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", 0, 2, 1));
        xuanBaoSkillMap.put(30031, new XuanBaoSkill(30031, "50=448|8961|540|10815&100=852|17045|1028|20572&150=1173|23461|1415|28315&200=1379|27580|1664|22186", "10=11.3&15=15.7&20=19.7&25=23.6&30=27.3", "10=11.3|3.1|5804&15=15.7|4.3|8028&20=19.7|5.4|10106&25=23.6|6.5|12082&30=27.3|7.5|13979", "10=11.3|3.1|6940&15=15.7|4.3|9599&20=19.7|5.4|12084&25=23.6|6.5|14445&30=27.3|7.5|16714", 0, 2, 1));
        xuanBaoSkillMap.put(30032, new XuanBaoSkill(30032, "50=105.4|105.4&100=125.7|125.7&150=144|144&200=161.1|161.1", "10=9.4&15=13.0&20=16.4&25=19.6&30=22.7", "10=23.0&15=31.8&20=40.0&25=47.9&30=55.4", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", 0, 2, 1));
        xuanBaoSkillMap.put(30033, new XuanBaoSkill(30033, "50=3|13.5&100=4|20.8&150=5|26.8&200=5|32", "10=10.1&15=12.3&20=14.3&25=16.0&30=17.5", "10=4.3&15=5.5&20=6.6&25=7.5&30=8.4", "10=1&15=2&20=2&25=2&30=3", 0, 2, 1));
        xuanBaoSkillMap.put(30034, new XuanBaoSkill(30034, "50=1|3.3&100=2|5.2&150=2|6.7&200=2|8", "10=25.2&15=30.9&20=35.7&25=40&30=43.8", "10=0.7&15=1&20=1.2&25=1.3&30=1.5", "10=1&15=2&20=2&25=2&30=3", 0, 2, 1));
        xuanBaoSkillMap.put(30035, new XuanBaoSkill(30035, "50=3.3|6.6|6.4&100=6.2|12.5|12.3&150=8.5|17.1|16.9&200=10|20.1|19.9", "10=1.1&15=1.5&20=1.9&25=2.2&30=2.6", "10=2.3&15=3.3&20=4.1&25=4.9&30=5.7", "10=3.2&15=4.5&20=5.7&25=6.8&30=7.9", 0, 2, 1));
        xuanBaoSkillMap.put(30036, new XuanBaoSkill(30036, "50=20.5|3&100=35.8|4&150=49.5|5&200=62.3|5", "10=10.3&15=13.2&20=15.6&25=17.9&30=20.0", "10=12.3&15=15.7&20=18.7&25=21.3&30=23.8", "10=1&15=1&20=2&25=2&30=2", 0, 2, 1));
        xuanBaoSkillMap.put(30037, new XuanBaoSkill(30037, "50=5|8467&100=6|16105&150=7|22167&200=7|26058", "10=11.1&15=14.2&20=16.8&25=19.3&30=21.5", "10=1&15=1&20=2&25=2&30=2", "10=11.1&15=14.2&20=16.8&25=19.3&30=21.5", 0, 2, 1));

    }

}


