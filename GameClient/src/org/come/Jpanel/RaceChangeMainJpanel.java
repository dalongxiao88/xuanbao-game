package org.come.Jpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.mouslisten.ChoseRoleChangeMouslisten;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.RoleCaoZuoBtn;
import com.tool.btn.RoleMetemBtn;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.NewPart;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

public class RaceChangeMainJpanel extends JPanel
{
    private int leixing;
    private BigDecimal species_id;
    private String[] menuName;
    private String[] sexNames;
    private RoleMetemBtn menuBtnHuman;
    private RoleMetemBtn menuBtnInferno;
    private RoleMetemBtn menuBtnImmortal;
    private RoleMetemBtn menuBtnGhost;
    private RoleMetemBtn menuBtnNagas;
    private RoleMetemBtn[] menuBtn;
    private JLabel[] sexBtn;
    private JLabel[] sexName;
    private JLabel[] changeLabel;
    private JLabel[] changeLabelBack;
    private JLabel[] changeLabelKuang;
    private ChoseRoleChangeMouslisten[] ChangeListen;
    public JTextArea textArea;
    public static Map<Integer, String> introMap;
    private RoleCaoZuoBtn btnSure;
    private RoleCaoZuoBtn btnCancel;
    private int roleType;
    private int number;
    private ImageIcon icon;
    private NewPart rolePart1;
    private NewPart rolePart2;
    
    public RaceChangeMainJpanel() {
        this.leixing = 0;
        this.menuName = new String[] { "人", "魔", "仙", "鬼", "龙" };
        this.sexNames = new String[] { "男人", "女人", "男魔", "女魔", "男仙", "女仙", "男鬼", "女鬼", "男龙", "女龙" };
        this.menuBtn = new RoleMetemBtn[5];
        this.sexBtn = new JLabel[2];
        this.sexName = new JLabel[2];
        this.changeLabel = new JLabel[6];
        this.changeLabelBack = new JLabel[6];
        this.changeLabelKuang = new JLabel[6];
        this.ChangeListen = new ChoseRoleChangeMouslisten[6];
        this.roleType = 1;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String lzjs = configure.getLzjskg();
        class UpMouse extends MouseAdapter {
            private int type;

            public UpMouse(int type) {
                this.type = type;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (this.type % 2 != RaceChangeMainJpanel.this.roleType % 2) {
                    return;
                }
                if (this.type % 2 == 0) {
                    RaceChangeMainJpanel.this.roleType--;
                } else {
                    RaceChangeMainJpanel.this.roleType++;
                }
                RaceChangeMainJpanel.this.reloadRace(RaceChangeMainJpanel.this.roleType, RaceChangeMainJpanel.this.number);
                for (int i = 0; i < 2; ++i) {
                    if (this.type == i) {
                        RaceChangeMainJpanel.this.sexBtn[i].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
                        continue;
                    }
                    RaceChangeMainJpanel.this.sexBtn[i].setIcon(null);
                }
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(500, 420));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn onOffBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 41);
            onOffBtn.setBounds(463, 10, 25, 25);
            this.add(onOffBtn);
            if (lzjs.equals("5")) {
                this.menuName = new String[] { "人", "魔", "仙", "鬼", "龙" };
                this.menuBtn = new RoleMetemBtn[5];
            }
            else if (lzjs.equals("4")) {
                this.menuName = new String[] { "人", "魔", "仙", "鬼" };
                this.menuBtn = new RoleMetemBtn[4];
            }
            else {
                this.menuName = new String[] { "人", "魔", "仙" };
                this.menuBtn = new RoleMetemBtn[3];
            }
            for (int i = 0; i < this.menuBtn.length; ++i) {
                (this.menuBtn[i] = new RoleMetemBtn("inkImg/button1/K85" + this.menuName[i] + ".png", 1, "", i + 1, this)).setBounds(41 + i * 80, 23, 75, 33);
                this.add(this.menuBtn[i]);
            }
            try {
                this.menuBtn[0].setIcons(CutButtonImage.cuts("inkImg/button1/K86" + this.menuName[0] + ".png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < this.changeLabel.length; ++i) {
                (this.changeLabelKuang[i] = new JLabel()).setBounds(43 + i % 2 * 74, 78 + i / 2 * 93, 70, 90);
                this.changeLabelKuang[i].setIcon(CutButtonImage.getImage("inkImg/background/S103.png", 70, 90));
                this.ChangeListen[i] = new ChoseRoleChangeMouslisten(i);
                this.changeLabelKuang[i].addMouseListener(this.ChangeListen[i]);
                this.changeLabelKuang[i].setOpaque(false);
                this.add(this.changeLabelKuang[i]);
                (this.changeLabel[i] = new JLabel()).setBounds(44 + i % 2 * 74, 79 + i / 2 * 93, 68, 88);
                this.changeLabel[i].setOpaque(false);
                this.add(this.changeLabel[i]);
                (this.changeLabelBack[i] = new JLabel()).setBounds(43 + i % 2 * 74, 78 + i / 2 * 93, 70, 90);
                this.changeLabelBack[i].setIcon(CutButtonImage.getImage("inkImg/background/S102.png", 70, 90));
                this.changeLabelBack[i].setOpaque(false);
                this.add(this.changeLabelBack[i]);
            }
            (this.textArea = new JTextArea()).setBounds(210, 68, 250, 128);
            this.textArea.setEditable(false);
            this.textArea.setForeground(Color.black);
            this.textArea.setLineWrap(true);
            this.textArea.setOpaque(false);
            this.add(this.textArea);
            for (int i = 0; i < this.sexBtn.length; ++i) {
                (this.sexBtn[i] = new JLabel("", 0)).setBounds(42 + i * 79, 368, 19, 18);
                this.sexBtn[i].setOpaque(false);
                this.sexBtn[i].setIcon((i == 0) ? CutButtonImage.getImage("inkImg/button/B118.png", 7, 7) : null);
                this.sexBtn[i].setVerticalTextPosition(0);
                this.sexBtn[i].setHorizontalTextPosition(0);
                this.sexBtn[i].addMouseListener(new UpMouse(i));
                this.add(this.sexBtn[i]);
                (this.sexName[i] = new JLabel((i == 1) ? this.sexNames[1] : this.sexNames[0])).setBounds(64 + i * 79, 369, 50, 18);
                this.sexName[i].setFont(UIUtils.TEXT_HY16);
                this.sexName[i].setForeground(Color.black);
                this.sexName[i].setOpaque(false);
                this.add(this.sexName[i]);
            }
            (this.btnSure = new RoleCaoZuoBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "转种族", 3)).setBounds(300, 360, 79, 24);
            this.add(this.btnSure);
        }
        else {
            this.setPreferredSize(new Dimension(500, 420));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn onOffBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 41);
            onOffBtn.setBounds(466, 9, 23, 23);
            this.add(onOffBtn);
            if (lzjs.equals("5")) {
                this.menuName = new String[] { "人", "魔", "仙", "鬼", "龙" };
                this.menuBtn = new RoleMetemBtn[5];
            }
            else if (lzjs.equals("4")) {
                this.menuName = new String[] { "人", "魔", "仙", "鬼" };
                this.menuBtn = new RoleMetemBtn[4];
            }
            else {
                this.menuName = new String[] { "人", "魔", "仙" };
                this.menuBtn = new RoleMetemBtn[3];
            }
            for (int i = 0; i < this.menuBtn.length; ++i) {
                (this.menuBtn[i] = new RoleMetemBtn("img/xy2uiimg/一级选项卡_" + this.menuName[i] + "族_未选中_w84,h120.png", 1, "", i + 1, this)).setBounds(41 + i * 86, 17, 84, 40);
                this.add(this.menuBtn[i]);
            }
            try {
                this.menuBtn[0].setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_" + this.menuName[0] + "族_选中_w84,h120.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < this.changeLabel.length; ++i) {
                (this.changeLabelKuang[i] = new JLabel()).setBounds(43 + i % 2 * 74, 78 + i / 2 * 93, 70, 90);
                this.changeLabelKuang[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/头像_框_w70,h90.png", 70, 90));
                this.ChangeListen[i] = new ChoseRoleChangeMouslisten(i);
                this.changeLabelKuang[i].addMouseListener(this.ChangeListen[i]);
                this.changeLabelKuang[i].setOpaque(false);
                this.add(this.changeLabelKuang[i]);
                (this.changeLabel[i] = new JLabel()).setBounds(44 + i % 2 * 74, 79 + i / 2 * 93, 68, 88);
                this.changeLabel[i].setOpaque(false);
                this.add(this.changeLabel[i]);
                (this.changeLabelBack[i] = new JLabel()).setBounds(43 + i % 2 * 74, 78 + i / 2 * 93, 70, 90);
                this.changeLabelBack[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/头像_框（底图）_w70,h90.png", 70, 90));
                this.changeLabelBack[i].setOpaque(false);
                this.add(this.changeLabelBack[i]);
            }
            (this.textArea = new JTextArea()).setBounds(210, 68, 250, 128);
            this.textArea.setEditable(false);
            this.textArea.setForeground(Color.red);
            this.textArea.setLineWrap(true);
            this.textArea.setOpaque(false);
            this.add(this.textArea);
            for (int i = 0; i < this.sexBtn.length; ++i) {
                (this.sexBtn[i] = new JLabel("", 0)).setBounds(42 + i * 79, 368, 19, 18);
                this.sexBtn[i].setOpaque(false);
                this.sexBtn[i].setIcon((i == 0) ? CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7) : null);
                this.sexBtn[i].setVerticalTextPosition(0);
                this.sexBtn[i].setHorizontalTextPosition(0);
                this.sexBtn[i].addMouseListener(new UpMouse(i));
                this.add(this.sexBtn[i]);
                (this.sexName[i] = new JLabel((i == 1) ? this.sexNames[1] : this.sexNames[0])).setBounds(64 + i * 79, 369, 50, 18);
                this.sexName[i].setFont(UIUtils.TEXT_HY16);
                this.sexName[i].setForeground(Color.yellow);
                this.sexName[i].setOpaque(false);
                this.add(this.sexName[i]);
            }
            (this.btnSure = new RoleCaoZuoBtn("img/xy2uiimg/按钮_转种族_w80,h78.png", 1, "", 3)).setBounds(300, 360, 80, 26);
            this.add(this.btnSure);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B312.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 500, 420, this);
            if (this.rolePart1 != null) {
                this.rolePart1.draw(g, 280, 325, 4, ImageMixDeal.userimg.getTime());
            }
            if (this.rolePart2 != null) {
                this.rolePart2.draw(g, 400, 325, 4, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/底板_w500,h420.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 500, 420, this);
            if (this.rolePart1 != null) {
                this.rolePart1.draw(g, 280, 325, 4, ImageMixDeal.userimg.getTime());
            }
            if (this.rolePart2 != null) {
                this.rolePart2.draw(g, 400, 325, 4, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public void cleanView() {
        for (int i = 0; i < 6; ++i) {
            this.changeLabelKuang[i].setBorder(BorderFactory.createEmptyBorder());
            this.changeLabel[i].setVisible(false);
            this.changeLabelBack[i].setVisible(false);
            this.changeLabelKuang[i].setVisible(false);
        }
    }
    
    public void isTrueView(int i) {
        this.changeLabel[i].setVisible(true);
        this.changeLabelBack[i].setVisible(true);
        this.changeLabelKuang[i].setVisible(true);
    }
    
    public void reloadRace(int raceNum, int number) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            this.cleanView();
            int num = 0;
            for (int i = 0; i < this.sexBtn.length; ++i) {
                this.sexBtn[i].setIcon((raceNum % 2 != i % 2) ? CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7) : null);
            }
            switch (raceNum) {
                case 1: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 4: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 5: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 6: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + "-1.png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 7: {
                    for (int i = 0; i < 7; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 6 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 8: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 7 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 9: {
                    for (int i = 0; i < 7; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 6 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 10: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + "-1.png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 7 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
            }
        }
        else {
            this.cleanView();
            int num = 0;
            for (int i = 0; i < this.sexBtn.length; ++i) {
                this.sexBtn[i].setIcon((raceNum % 2 != i % 2) ? CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7) : null);
            }
            switch (raceNum) {
                case 1: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (20000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(20000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(20000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 4: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (21000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(21000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(21000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 5: {
                    for (int i = 0; i < number; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 10) {
                            if ((i + 1) % 2 != 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 10 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 6: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                        else if (i > 5 && i < 11) {
                            if ((i + 1) % 2 == 0) {
                                ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                                this.changeLabel[num].setIcon(img1);
                                this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                                this.isTrueView(num);
                                this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                                ++num;
                            }
                        }
                        else if (i == 11 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (22000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(22000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(22000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 7: {
                    for (int i = 0; i < 7; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 6 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 8: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 7 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (23000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(23000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(23000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 9: {
                    for (int i = 0; i < 7; ++i) {
                        if (i < 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 6 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
                case 10: {
                    for (int i = 0; i < number; ++i) {
                        if (i > 2 && i < 6) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                        else if (i == 7 && ImageMixDeal.userimg.getRoleShow().getTurnAround() + this.leixing >= 3) {
                            ImageIcon img1 = CutButtonImage.getImage("img/head/" + (24000 + (i + 1)) + ".png", 68, 88);
                            this.changeLabel[num].setIcon(img1);
                            this.ChangeListen[num].setSpecies_id(new BigDecimal(24000 + i + 1));
                            this.isTrueView(num);
                            this.ChangeListen[num].setSpecies_intro((String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(24000 + (i + 1))));
                            ++num;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void changSexNames() {
        for (int i = this.sexName.length - 1; i >= 0; --i) {
            this.sexName[this.sexName.length - 1 - i].setText(this.sexNames[this.roleType - i]);
        }
    }
    
    public RoleCaoZuoBtn getBtnSure() {
        return this.btnSure;
    }
    
    public void setBtnSure(RoleCaoZuoBtn btnSure) {
        this.btnSure = btnSure;
    }
    
    public RoleCaoZuoBtn getBtnCancel() {
        return this.btnCancel;
    }
    
    public void setBtnCancel(RoleCaoZuoBtn btnCancel) {
        this.btnCancel = btnCancel;
    }
    
    public JTextArea getTextArea() {
        return this.textArea;
    }
    
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    public String[] getSexNames() {
        return this.sexNames;
    }
    
    public void setSexNames(String[] sexNames) {
        this.sexNames = sexNames;
    }
    
    public JLabel[] getSexBtn() {
        return this.sexBtn;
    }
    
    public void setSexBtn(JLabel[] sexBtn) {
        this.sexBtn = sexBtn;
    }
    
    public JLabel[] getSexName() {
        return this.sexName;
    }
    
    public void setSexName(JLabel[] sexName) {
        this.sexName = sexName;
    }
    
    public JLabel[] getChangeLabel() {
        return this.changeLabel;
    }
    
    public void setChangeLabel(JLabel[] changeLabel) {
        this.changeLabel = changeLabel;
    }
    
    public JLabel[] getChangeLabelBack() {
        return this.changeLabelBack;
    }
    
    public void setChangeLabelBack(JLabel[] changeLabelBack) {
        this.changeLabelBack = changeLabelBack;
    }
    
    public JLabel[] getChangeLabelKuang() {
        return this.changeLabelKuang;
    }
    
    public void setChangeLabelKuang(JLabel[] changeLabelKuang) {
        this.changeLabelKuang = changeLabelKuang;
    }
    
    public ChoseRoleChangeMouslisten[] getChangeListen() {
        return this.ChangeListen;
    }
    
    public void setChangeListen(ChoseRoleChangeMouslisten[] changeListen) {
        this.ChangeListen = changeListen;
    }
    
    public int getRoleType() {
        return this.roleType;
    }
    
    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public NewPart getRolePart1() {
        return this.rolePart1;
    }
    
    public void setRolePart1(NewPart rolePart1) {
        this.rolePart1 = rolePart1;
    }
    
    public NewPart getRolePart2() {
        return this.rolePart2;
    }
    
    public void setRolePart2(NewPart rolePart2) {
        this.rolePart2 = rolePart2;
    }
    
    public int getLeixing() {
        return this.leixing;
    }
    
    public void setLeixing(int leixing) {
        this.leixing = leixing;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
    
    public RoleMetemBtn getMenuBtnHuman() {
        return this.menuBtnHuman;
    }
    
    public void setMenuBtnHuman(RoleMetemBtn menuBtnHuman) {
        this.menuBtnHuman = menuBtnHuman;
    }
    
    public RoleMetemBtn getMenuBtnInferno() {
        return this.menuBtnInferno;
    }
    
    public void setMenuBtnInferno(RoleMetemBtn menuBtnInferno) {
        this.menuBtnInferno = menuBtnInferno;
    }
    
    public RoleMetemBtn getMenuBtnImmortal() {
        return this.menuBtnImmortal;
    }
    
    public void setMenuBtnImmortal(RoleMetemBtn menuBtnImmortal) {
        this.menuBtnImmortal = menuBtnImmortal;
    }
    
    public RoleMetemBtn getMenuBtnGhost() {
        return this.menuBtnGhost;
    }
    
    public void setMenuBtnGhost(RoleMetemBtn menuBtnGhost) {
        this.menuBtnGhost = menuBtnGhost;
    }
    
    public RoleMetemBtn getMenuBtnNagas() {
        return this.menuBtnNagas;
    }
    
    public void setMenuBtnNagas(RoleMetemBtn menuBtnNagas) {
        this.menuBtnNagas = menuBtnNagas;
    }
    
    public RoleMetemBtn[] getMenuBtn() {
        return this.menuBtn;
    }
    
    public void setMenuBtn(RoleMetemBtn[] menuBtn) {
        this.menuBtn = menuBtn;
    }
    
    public String[] getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String[] menuName) {
        this.menuName = menuName;
    }
    
    static {
        (RaceChangeMainJpanel.introMap = new HashMap<>()).put(Integer.valueOf(20001), "逍遥生\r\n【种族】人【性别】男\r\n【武器】扇或剑\r\n一袭白衫，一把铁扇或一柄长剑，凭此三物而走遍江湖。与一般武夫不同的是，他们在快意恩仇之余也喜欢舞文弄墨。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20009), "纯阳子\r\n【种族】人【性别】男\r\n【武器】扇子或书\r\n本风流倜傥一秀才，喜四处行走，行侠仗义。后因奇遇修行得道，跻身八仙，位列全真五祖。得正果后弃剑不用，羽扇轻挥，谈笑间惩恶扬善，令妖魔贼子闻风丧胆。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20002), "剑侠客\r\n【种族】人【性别】男\r\n【武器】剑或锤\r\n潇洒倜傥，风度不凡。或手执长剑，或手握双锤，锄强扶弱，直指世间一切不平事。儿女情，英雄意，都付笑谈中。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20003), "猛壮士\r\n【种族】人【性别】男\r\n【武器】拳套或斧\r\n豪迈而不拘小节，讲义气重情义，一身肝胆，不畏强暴，是响当当的好汉。外表虽然粗犷，但内心也有细腻的一面。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20007), "飞剑侠\r\n【种族】人【性别】男\r\n【武器】剑或拳套\r\n行走江湖多年，现退隐江湖，已经不再过问江湖之事，一身绝世武功，能剑随意走，人剑合一。真是御剑游神州，世外逍遥侠。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20006), "俏千金\r\n【种族】人【性别】女\r\n【武器】刀或枪矛\r\n出身于富贵之家，家门显赫，去不喜欢养尊处优；向往独立，渴望自由，英雌而自尊处优；向往独立，渴望自由，因此而自习得一身好刀法及枪法，令人不敢小窥。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20004), "飞燕女\r\n【种族】人【性别】女\r\n【武器】钩或鞭子\r\n从小被异人带进深山修炼武艺，爱用短剑和鞭子，喜欢大自然，身轻如燕，生性自然跳脱，活泼可爱，是个天生的乐观派。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20005), "英女侠\r\n【种族】人【性别】女\r\n【武器】刀或棍杖\r\n名士之女，贤淑端庄，有着一股与生俱来的浩然正气。擅使短刀或棍杖，有爱心，路见不平必拔刀相助，是个巾帼不让须眉的女侠。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20008), "燕山雪\r\n【种族】人【性别】女\r\n【武器】剑或刀\r\n使得一手绝世剑法，武林中人不知其剑法从何而来，只知她出剑之处剑光如雪花，无处不在。是个十载燕山苦学艺，一朝功成闯江湖的侠义女子。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20010), "红拂女\r\n【种族】人【性别】女\r\n【武器】鞭或剑\r\n此女为风尘三侠之一，以女流之身而修武入道，亦侠亦仙，属传说中的人物。其性情刁钻、武艺高强，见不得一点不平事，听不得一些是非语。手中长鞭有如龙蛇，矫灵异常，实为无知宵小的克星。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20011), "神秀生\r\n【种族】人【性别】男\r\n【武器】棍或剑\r\n长安城中有个奇侠，风神秀彻，落拓不羁，大有魏晋风度，谪仙气韵，人称“神秀生。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(20012), "玲珑女\r\n【种族】人【性别】女\r\n【武器】鞭或刀\r\n人道她是千种风情的酒肆老板，实际却是覆雨翻云于无形的江湖尊主。最善密语传机，冷眼观人，真正是个心有七窍意玲珑的妙人。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21003), "巨魔王\r\n【种族】魔【性别】男\r\n【武器】刀或枪矛\r\n魔族之子，以庞大的身形庞大及天生的神力而著称。钟情的武器是大刀和枪矛，挥舞起来虎虎有声，令对手不战而退。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21002), "夺命妖\r\n【种族】魔【性别】男\r\n【武器】刀或招魂幡\r\n有阴间“夺命杀手”之称。不喜欢硬打硬拼，刀走偏锋，专攻对手弱点；还有独门武器招魂幡，夺命勾魂，实是厉害非常。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21001), "虎头怪\r\n【种族】魔【性别】男\r\n【武器】棍杖或抢矛\r\n足智多谋，力量与智慧的结合体。他们并不一味地依赖于与生俱来的魔族之力，而是尽可能多地拓展自己的潜能。拿手武器是棍杖和枪矛。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21007), "逆天魔\r\n【种族】魔【性别】男\r\n【武器】枪或斧\r\n吸取日月精华，潜心修行千年，终于练就魔功。手中一把夺魂枪犀利非凡。一出手石破天惊，鬼哭神嚎，连神佛都不敢等闲视之。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21009), "混天魔\r\n【种族】魔【性别】男\r\n【武器】刀或斧\r\n天庭诞生之前就存在的混世魔王，因屡屡扰乱天地和谐曾被元始天尊封印了九万九千九百年。形体极为高大修长，身着亮银魔甲，手执六鬼锁元刀，加上冷竣的眼神，给人一种不寒而栗的感觉。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21006), "狐美人\r\n【种族】魔【性别】女\r\n【武器】爪或鞭\r\n天生对动物有着特别的感情，爱与动物为伴；驯有一妖狐，使得一手好鞭法，好胜心强，喜欢追求各种刺激和挑战。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21005), "骨精灵\r\n【种族】魔【性别】女\r\n【武器】棍杖或剑\r\n由不死白骨吸取天地养分多年所化而成的妖精，对世间的许多事物都抱有一颗好奇之心。爱好和平，但受侵犯时也会以棍杖或短剑来出击自卫。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21004), "小蛮妖\r\n【种族】魔【性别】女\r\n【武器】钩或刀\r\n刁蛮任性，性格泼辣，红色头饰和带刺的护腕使之显得与众不同。擅长单刀，刀法干脆利落；还有一对双钩，挥动起来也是得心应手。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21008), "媚灵狐\r\n【种族】魔【性别】女\r\n【武器】刀或环\r\n乃是千年灵狐，吸天地灵气，修得正果，已是妖中之仙。手中一把半月刀更是灵气逼人，能在无形间取人性命。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21010), "九尾狐\r\n【种族】魔【性别】女\r\n【武器】爪或钩\r\n狐修千年得一尾，该妖共有九尾，乃灵狐之祖。其长相极为妖媚，有一笑倾城，二笑倾国之容。攻击时一改柔弱之相，谈笑间挥动利爪将敌人瞬间撕为齑粉。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21011), "绝影魔\r\n【种族】魔【性别】男\r\n【武器】刀或枪\r\n平日是俊朗不凡的大漠王子，会逢日月之蚀，却会化作神骏。其行也，超光越影，仙宫冥府顷刻而返。世人难见其影，只能望尘兴叹。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(21012), "霜月灵\r\n【种族】魔【性别】女\r\n【武器】刀或钩\r\n本是狼魔首领之女，被一个人族女巫抚养长大，起名“霜月”。女巫在她身上种下了蛊，以便操纵她的狼性成为自己的武器。可没有想到霜月体内的蛊虫失控，化为兽形咬死了女巫，她最终还是变成了狼魔。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22001), "神天兵\r\n【种族】仙【性别】男\r\n【武器】枪矛或锤\r\n一身金光黄金甲、一顶凤翅紫金冠，就是他们身为天界神兵的见证。神兵们都习得一手好枪法和锤法，对一切邪恶之徒可是毫不留情。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22003), "龙战将\r\n【种族】仙【性别】男\r\n【武器】棍杖或爪\r\n身经百战的天界战将，不怒自威，一支舞得出神入化得棍杖和一对无坚不摧的利爪均是挡者披靡，让不少敌人闻风丧胆。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22002), "智圣仙\r\n【种族】仙【性别】男\r\n【武器】拂尘或剑\r\n修道多年的神仙，以一把随身携带、伤人于无形的拂尘和利剑为除敌的武器。最可怕还是他们睿智的头脑，不轻易为敌人的挑衅所动。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22007), "武尊神\r\n【种族】仙【性别】男\r\n【武器】枪或爪\r\n天神之子，自幼在天宫勤修仙术，虽未授神职，然仙力不亚大罗金仙，一身神功在天界早已传为嘉话，是未来仙界青睐的栋梁之材。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22009), "紫薇神\r\n【种族】仙【性别】男\r\n【武器】剑或幡\r\n北极中天紫薇大帝，为斗部诸神之首，有着无上法力。身形端庄威武，一脸严肃之相。袖中暗藏一剑，犀利无匹。剑出，天地暗，神鬼泣，风云为之变色。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22005), "舞天姬\r\n【种族】仙【性别】女\r\n【武器】飘带或棍\r\n美貌、优雅而高贵，讲究穿着和打扮。攻击时使用长棍或彩带，看似无力，但经她们的手施出时却让人不可轻视。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22004), "精灵仙\r\n【种族】仙【性别】女\r\n【武器】刀或拳套\r\n精灵调皮，爱恶作剧和开些善意的玩笑。性格好动，因圆刀的外形可爱而选择了它作为防身武器，又因孩子气而喜欢上了拳套。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22006), "玄剑娥\r\n【种族】仙【性别】女\r\n【武器】枪矛或剑\r\n神将之后，自小习武，因此不像普通女仙般喜欢脂粉，而独钟于武学；尤其擅长于枪矛和长剑。平时性格温和，但到了比武场上则是判若两人。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22008), "玄天姬\r\n【种族】仙【性别】女\r\n【武器】飘带或环\r\n拥有超凡脱俗的气质，倾国倾城的容貌。飘逸的身形搭配上彩衣缎带，犹如画中仙。平时温文尔雅，不在万不得已之时不会轻易出手。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22010), "霓裳仙\r\n【种族】仙【性别】女\r\n【武器】飘带或灯笼\r\n玉皇大帝之女，娇柔之状，我见尤怜。因自幼挚爱纺织，遂采白云为布、积月光为丝、以彩虹染色织就霓裳。难以想象一位那么娟秀的女子，一双如此柔弱的纤手竟能释放出那么强大的法术。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22011), "青阳使\r\n【种族】仙【性别】男\r\n【武器】枪或棍\r\n青帝之子，司春仙童。冬去春来时，遣灵鹿踏云下界，施青阳于人间，布春华于万物。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(22012), "云中君\r\n【种族】仙【性别】女\r\n【武器】飘带或双环\r\n她是羲和与帝俊之女，行云之神。云裾风带，秀美不可方物。朝暮随金乌而行，遍施云霞于天际，灿烂明艳与日月齐光。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23002), "猎魂引\r\n【种族】鬼【性别】男\r\n【武器】枪或拳套\r\n一个无忧无虑的草根少年，生时打猎为生，死后依然喜欢和小动物一起生活在丛林里。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23003), "无崖子\r\n【种族】鬼【性别】男\r\n【武器】幡或书卷\r\n生前是容貌俊秀，文采出众的皇子。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23001), "祭剑魂\r\n【种族】鬼【性别】男\r\n【武器】剑或刀\r\n每一把神兵名剑都是有意识的。兵器饮血越多，这种意识就会越强烈，最后脱离剑体，化而为灵。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23005), "夜溪灵\r\n【种族】鬼【性别】女\r\n【武器】灯笼或环\r\n幽冥地府阴阳河中水灯化成的小鬼仙。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23006), "幽梦影\r\n【种族】鬼【性别】女\r\n【武器】环或飘带\r\n大家闺秀，自小知书达理。因不满父母安排的婚姻，与封建礼教之间展开了殊死对抗。最终以死抗婚，用生命换取真爱和自由。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23007), "南冠客\r\n【种族】鬼【性别】男\r\n【武器】剑或刀\r\n年幼时举家流放，长大后被神秘人所点醒，成为令人丧胆的绝命杀手。但他心中从未失却大是大非，最终跳脱出失格的人间，在幽冥中求索清浊黑白。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23008), "镜花影\r\n【种族】鬼【性别】女\r\n【武器】剑或飘带\r\n本是金枝玉叶，却因宗庙崩塌充为教坊伶人。深宫二十年，终得御前剑舞。—曲舞罢，青丝成雪，大仇得报。从此化作太液池边临水照花的孤魂，徘徊不去。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(23004), "墨衣行\r\n【种族】鬼【性别】女\r\n【武器】钩或鞭子\r\n生前是武艺高强、快意恩仇的江湖女子。在江湖恩怨中不行殒命。他们漠视生死，独来独往，荡尽泉下不平事。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24001), "沧浪君\r\n【种族】龙【性别】男\r\n【武器】剑或书\r\n瀛海本无言，神龙闻其音。俯察麟羽迹，仰观日月行。万象走龙蛇，百感付啸吟。浮沉随波去，沧浪歌一声。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24002), "龙渊客\r\n【种族】龙【性别】男\r\n【武器】枪或刀\r\n身如寒铁心似火，璞质浑然待雕琢。霹雳洞照冥蒙破，换骨脱胎成超卓。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24003), "忘忧子\r\n【种族】龙【性别】男\r\n【武器】弓或环\r\n草木尚知愁，金鳞岂无忧?东风解微意，百草知寸心。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24004), "骊珠儿\r\n【种族】龙【性别】女\r\n【武器】锤或钩\r\n明月沉海雾，鱼惊蛟夜哭。老蚌捧泪至，骊珠跳波出。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24005), "木兰行\r\n【种族】龙【性别】女\r\n【武器】弓或枪\r\n红妆灼烁似血色，断角峥嵘作干戈。醉笑沙场何辞死，人间再作木兰歌。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24006), "莫解语\r\n【种族】龙【性别】女\r\n【武器】剑或灯笼\r\n翩跹与鸟旋，婉转从鱼游。不解人间事，笑语问何愁。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24007), "游无极\r\n【种族】龙【性别】男\r\n【武器】剑或刀\r\n本是冷血杀手，以为龙是天地万物主宰，以屠杀弱小生灵为乐，在一次血腥屠杀后，被万千回游的飞鱼组成的巨浪所震慑，领悟到自然之无极，并以此作为力里来源。");
        RaceChangeMainJpanel.introMap.put(Integer.valueOf(24008), "临九渊\r\n【种族】龙【性别】女\r\n【武器】飘带或灯笼\r\n祖龙的阴影所化。祖龙在临死前将自己内心的恐惧逼出体内，封印了在九渊之下的寒冰之中。这阴影最终化作影龙降临蓬莱三岛，她能够操纵人们的恐惧，让四海臣服。");
    }
}
