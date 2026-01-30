package org.come.Jpanel;

import java.awt.Graphics;
import org.come.bean.LoginResult;
import java.util.Iterator;
import javax.swing.JList;
import org.come.entity.Baby;
import org.come.Frame.TestChildJframe;
import org.come.control.BabyControl;
import org.come.until.UserMessUntil;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.until.Util;
import java.util.stream.Collectors;
import org.come.model.InternalForm;
import java.util.List;
import org.come.until.FormsManagement;
import com.updateNew.MyQdModeJFrame;
import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.until.MySliderUI;
import org.come.mouslisten.WLLMouslisten;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.mouslisten.SystemMouslisten;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.awt.Font;
import com.tool.btn.RefineOperBtn;
import org.skill.panel.SetupJpanel;
import com.tool.btn.WorkshopBtn;
import javax.swing.JSlider;
import org.cbg.btn.TrslationBtn;
import org.skill.panel.SetupJpanel3;
import org.skill.panel.SetupJpanel2;
import org.skill.panel.SetupJpanel1;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TestSetupJpanel extends JPanel implements MouseListener
{
    private JLabel labFullscreen;
    private JLabel labFullscreen1;
    private JLabel labWindow;
    private JLabel labWindow1;
    private JLabel labMusic;
    private JLabel labSound;
    private JLabel labMusicNew;
    private JLabel labPlayswitch;
    private JLabel labRefusemsg;
    private JLabel labLetter;
    private JLabel labJoinfriends;
    private JLabel labReceiveitems;
    private JLabel labSkillFull;
    private JLabel jpstyle;
    private JLabel labSkillFullXJ;
    private JLabel xNms;
    private JLabel xNmsZ;
    private JLabel xNmsG;
    private JLabel labAcceptteam;
    public SetupJpanel1 optionJpanel1;
    public SetupJpanel2 optionJpanel2;
    public SetupJpanel3 optionJpanel3;
    private int leftFlag1;
    private int leftFlag2;
    private int leftFlag3;
    public JLabel resolutiontext;
    public JLabel resolution1366;
    public TrslationBtn chooseDownArrows1;
    public TrslationBtn chooseDownArrows2;
    public TrslationBtn chooseDownArrows3;
    private JSlider jSlider;
    private WorkshopBtn palBtn;
    private SetupJpanel optionJpanel;
    private final JLabel labName;
    private RefineOperBtn btnDown;
    public JLabel interfacialtext;
    public JLabel fontsizetext;
    public static Font nameFont;
    private WorkshopBtn qlyxhcBtn;
    private ImageIcon icon;
    
    public TestSetupJpanel() throws Exception {
        this.leftFlag1 = 1;
        this.leftFlag2 = 1;
        this.leftFlag3 = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(438, 350));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 50);
            offBtn.setBounds(401, 10, 25, 25);
            this.add(offBtn);
            String[] resolutionData = { "800x600", "1024x768", "1366x768" };
            (this.optionJpanel1 = new SetupJpanel1(115, 120, resolutionData)).setBounds(116, 87, 115, 120);
            this.optionJpanel1.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel1.getJlist().getSelectedValue();
                    TestSetupJpanel.this.resolutiontext.setText(getname);
                    TestSetupJpanel.this.optionJpanel1.setVisible(false);
                    TestSetupJpanel.this.leftFlag1 = 1;
                    if (TestSetupJpanel.this.resolutiontext.getText().equals("1024x768")) {
                        SystemMouslisten.type1();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.resolutiontext.getText().equals("1366x768")) {
                        SystemMouslisten.type101();
                        SystemMouslisten.writeTxt();
                    }
                    else {
                        SystemMouslisten.type0();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel1.setVisible(false);
            this.add(this.optionJpanel1);
            (this.resolutiontext = new JLabel("1024x768")).setForeground(Color.white);
            this.resolutiontext.setFont(UIUtils.TXT_lianss);
            this.resolutiontext.setBounds(132, 67, 96, 20);
            this.add(this.resolutiontext);
            (this.resolution1366 = new JLabel("1366x768")).setForeground(Color.white);
            this.resolution1366.setFont(UIUtils.TXT_lianss);
            this.resolutiontext.setBounds(132, 67, 96, 20);
            this.add(this.resolution1366);
            (this.chooseDownArrows1 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag1 == 1) {
                        TestSetupJpanel.this.optionJpanel1.setVisible(true);
                        TestSetupJpanel.this.leftFlag1 = 0;
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                    }
                }
            });
            this.chooseDownArrows1.setBounds(116, 67, 115, 20);
            this.chooseDownArrows1.setIcons(CutButtonImage.cuts("inkImg/background1/xl100.png"));
            this.add(this.chooseDownArrows1);
            String[] interfacialData = { "低画质", "标准画质", "超高画质" };
            (this.optionJpanel2 = new SetupJpanel2(115, 120, interfacialData)).setBounds(116, 117, 115, 120);
            this.optionJpanel2.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel2.getJlist().getSelectedValue();
                    TestSetupJpanel.this.interfacialtext.setText(getname);
                    TestSetupJpanel.this.optionJpanel2.setVisible(false);
                    TestSetupJpanel.this.leftFlag2 = 1;
                    if (TestSetupJpanel.this.interfacialtext.getText().equals("低画质")) {
                        SystemMouslisten.type15();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.interfacialtext.getText().equals("标准画质")) {
                        SystemMouslisten.type16();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.interfacialtext.getText().equals("超高画质")) {
                        SystemMouslisten.type17();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel2.setVisible(false);
            this.add(this.optionJpanel2);
            (this.interfacialtext = new JLabel("低画质")).setForeground(Color.white);
            this.interfacialtext.setBounds(132, 97, 96, 20);
            this.interfacialtext.setFont(UIUtils.TEXT_FONT1);
            this.add(this.interfacialtext);
            (this.chooseDownArrows2 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag2 == 1) {
                        TestSetupJpanel.this.optionJpanel2.setVisible(true);
                        TestSetupJpanel.this.leftFlag2 = 0;
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                    }
                }
            });
            this.chooseDownArrows2.setBounds(116, 97, 115, 20);
            this.chooseDownArrows2.setIcons(CutButtonImage.cuts("inkImg/background1/xl100.png"));
            this.add(this.chooseDownArrows2);
            String[] rowData = { "经典红木", "水墨江山" };
            (this.labName = new JLabel()).setBounds(132, 127, 96, 20);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labName.setText(rowData[1]);
            }
            else {
                this.labName.setText(rowData[0]);
            }
            this.add(this.labName);
            (this.btnDown = new RefineOperBtn("inkImg/background1/xl100.png", 1, this, 3)).setBounds(116, 127, 115, 20);
            this.add(this.btnDown);
            (this.optionJpanel = new SetupJpanel(115, 120, rowData)).setBounds(116, 147, 115, 120);
            this.optionJpanel.setVisible(false);
            this.add(this.optionJpanel);
            this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String value = TestSetupJpanel.this.getValue(true);
                    if (value != null) {
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                }
            });
            String[] fontsizeData = { "默认", "楷体", "隶书" };
            (this.optionJpanel3 = new SetupJpanel3(115, 90, fontsizeData)).setBounds(116, 177, 115, 90);
            this.optionJpanel3.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel3.getJlist().getSelectedValue();
                    TestSetupJpanel.this.fontsizetext.setText(getname);
                    TestSetupJpanel.this.optionJpanel3.setVisible(false);
                    TestSetupJpanel.this.leftFlag3 = 1;
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("默认")) {
                        SystemMouslisten.typemoren();
                        SystemMouslisten.writeTxt();
                    }
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("楷体")) {
                        SystemMouslisten.type100();
                        SystemMouslisten.writeTxt();
                    }
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("隶书")) {
                        SystemMouslisten.typelushu();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel3.setVisible(false);
            this.add(this.optionJpanel3);
            (this.fontsizetext = new JLabel("默认")).setForeground(Color.white);
            this.fontsizetext.setBounds(132, 157, 96, 20);
            this.fontsizetext.setFont(UIUtils.TEXT_FONT1);
            this.add(this.fontsizetext);
            (this.chooseDownArrows3 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag3 == 1) {
                        TestSetupJpanel.this.optionJpanel3.setVisible(true);
                        TestSetupJpanel.this.leftFlag3 = 0;
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                    }
                }
            });
            this.chooseDownArrows3.setBounds(116, 157, 115, 20);
            this.chooseDownArrows3.setIcons(CutButtonImage.cuts("inkImg/background1/xl100.png"));
            this.add(this.chooseDownArrows3);
            this.add(this.labFullscreen = new JLabel());
            this.add(this.labWindow = new JLabel());
            this.add(this.labFullscreen1 = new JLabel());
            this.add(this.xNms = new JLabel());
            this.add(this.xNmsZ = new JLabel());
            this.add(this.xNmsG = new JLabel());
            (this.labMusic = new JLabel()).setBounds(59, 250, 16, 14);
            this.labMusic.addMouseListener(new SystemMouslisten(2));
            this.add(this.labMusic);
            (this.labSound = new JLabel()).setBounds(139, 250, 16, 14);
            this.labSound.addMouseListener(new SystemMouslisten(3));
            this.add(this.labSound);
            (this.labMusicNew = new JLabel()).setBounds(59, 220, 16, 14);
            this.labMusicNew.addMouseListener(new SystemMouslisten(11));
            this.add(this.labMusicNew);
            (this.labSkillFullXJ = new JLabel()).setBounds(264, 70, 16, 14);
            this.labSkillFullXJ.addMouseListener(new SystemMouslisten(18));
            this.add(this.labSkillFullXJ);
            (this.labSkillFull = new JLabel()).setBounds(264, 99, 16, 14);
            this.labSkillFull.addMouseListener(new SystemMouslisten(10));
            this.add(this.labSkillFull);
            (this.labPlayswitch = new JLabel()).setBounds(264, 128, 16, 14);
            this.labPlayswitch.addMouseListener(new SystemMouslisten(4));
            this.add(this.labPlayswitch);
            (this.labAcceptteam = new JLabel()).setBounds(264, 157, 16, 14);
            this.labAcceptteam.addMouseListener(new SystemMouslisten(9));
            this.add(this.labAcceptteam);
            (this.labJoinfriends = new JLabel()).setBounds(264, 186, 16, 14);
            this.labJoinfriends.addMouseListener(new SystemMouslisten(7));
            this.add(this.labJoinfriends);
            (this.labReceiveitems = new JLabel()).setBounds(264, 215, 16, 14);
            this.labReceiveitems.addMouseListener(new SystemMouslisten(8));
            this.add(this.labReceiveitems);
            (this.labLetter = new JLabel()).setBounds(264, 244, 16, 14);
            this.labLetter.addMouseListener(new SystemMouslisten(6));
            this.add(this.labLetter);
            (this.labRefusemsg = new JLabel()).setBounds(264, 273, 16, 14);
            this.labRefusemsg.addMouseListener(new SystemMouslisten(5));
            this.add(this.labRefusemsg);
            (this.qlyxhcBtn = new WorkshopBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT11, "清理缓存", 30)).setBounds(116, 188, 68, 17);
            this.qlyxhcBtn.addMouseListener(new WLLMouslisten(500));
            this.add(this.qlyxhcBtn);
            (this.jSlider = new JSlider(0, 0, 100, 100)).setBounds(50, 278, 185, 10);
            this.jSlider.setBackground(UIUtils.Color_BACK);
            this.jSlider.setUI(new MySliderUI());
            this.jSlider.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    float sec = (float)TestSetupJpanel.this.jSlider.getValue();
                }
            });
            this.add(this.jSlider);
        }
        else {
            this.setPreferredSize(new Dimension(395, 369));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 50);
            offBtn.setBounds(370, 0, 25, 25);
            this.add(offBtn);
            String[] resolutionData = { "800x600", "1024x768", "1366x768" };
            (this.optionJpanel1 = new SetupJpanel1(115, 120, resolutionData)).setBounds(88, 103, 115, 120);
            this.optionJpanel1.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel1.getJlist().getSelectedValue();
                    TestSetupJpanel.this.resolutiontext.setText(getname);
                    TestSetupJpanel.this.optionJpanel1.setVisible(false);
                    TestSetupJpanel.this.leftFlag1 = 1;
                    if (TestSetupJpanel.this.resolutiontext.getText().equals("1024x768")) {
                        SystemMouslisten.type1();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.resolutiontext.getText().equals("1366x768")) {
                        SystemMouslisten.type101();
                        SystemMouslisten.writeTxt();
                    }
                    else {
                        SystemMouslisten.type0();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel1.setVisible(false);
            this.add(this.optionJpanel1);
            (this.resolutiontext = new JLabel("1024x768")).setForeground(Color.white);
            this.resolutiontext.setFont(UIUtils.TXT_lianss);
            this.resolutiontext.setBounds(104, 83, 96, 20);
            this.add(this.resolutiontext);
            (this.resolution1366 = new JLabel("1366x768")).setForeground(Color.white);
            this.resolution1366.setFont(UIUtils.TXT_lianss);
            this.resolutiontext.setBounds(104, 83, 96, 20);
            this.add(this.resolution1366);
            (this.chooseDownArrows1 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag1 == 1) {
                        TestSetupJpanel.this.optionJpanel1.setVisible(true);
                        TestSetupJpanel.this.leftFlag1 = 0;
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                    }
                }
            });
            this.chooseDownArrows1.setBounds(88, 83, 115, 20);
            this.chooseDownArrows1.setIcons(CutButtonImage.cuts("img/xy2uiimg/47_png.button.combobox.png"));
            this.add(this.chooseDownArrows1);
            String[] interfacialData = { "低画质", "标准画质", "超高画质" };
            (this.optionJpanel2 = new SetupJpanel2(115, 120, interfacialData)).setBounds(88, 133, 115, 120);
            this.optionJpanel2.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel2.getJlist().getSelectedValue();
                    TestSetupJpanel.this.interfacialtext.setText(getname);
                    TestSetupJpanel.this.optionJpanel2.setVisible(false);
                    TestSetupJpanel.this.leftFlag2 = 1;
                    if (TestSetupJpanel.this.interfacialtext.getText().equals("低画质")) {
                        SystemMouslisten.type15();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.interfacialtext.getText().equals("标准画质")) {
                        SystemMouslisten.type16();
                        SystemMouslisten.writeTxt();
                    }
                    else if (TestSetupJpanel.this.interfacialtext.getText().equals("超高画质")) {
                        SystemMouslisten.type17();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel2.setVisible(false);
            this.add(this.optionJpanel2);
            (this.interfacialtext = new JLabel("低画质")).setForeground(Color.white);
            this.interfacialtext.setBounds(104, 113, 96, 20);
            this.interfacialtext.setFont(UIUtils.TEXT_FONT1);
            this.add(this.interfacialtext);
            (this.chooseDownArrows2 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag2 == 1) {
                        TestSetupJpanel.this.optionJpanel2.setVisible(true);
                        TestSetupJpanel.this.leftFlag2 = 0;
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                    }
                }
            });
            this.chooseDownArrows2.setBounds(88, 113, 115, 20);
            this.chooseDownArrows2.setIcons(CutButtonImage.cuts("img/xy2uiimg/47_png.button.combobox.png"));
            this.add(this.chooseDownArrows2);
            String[] rowData = { "经典红木", "水墨江山" };
            (this.labName = new JLabel()).setBounds(104, 143, 96, 20);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            if (MyIsif.getStyle().equals("水墨UI")) {
                this.labName.setText(rowData[1]);
            }
            else {
                this.labName.setText(rowData[0]);
            }
            this.add(this.labName);
            (this.btnDown = new RefineOperBtn("img/xy2uiimg/47_png.button.combobox.png", 1, this, 3)).setBounds(88, 143, 115, 20);
            this.add(this.btnDown);
            (this.optionJpanel = new SetupJpanel(115, 120, rowData)).setBounds(88, 163, 115, 120);
            this.optionJpanel.setVisible(false);
            this.add(this.optionJpanel);
            this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String value = TestSetupJpanel.this.getValue(true);
                    if (value != null) {
                        TestSetupJpanel.this.optionJpanel.setVisible(false);
                    }
                }
            });
            String[] fontsizeData = { "默认", "楷体", "隶书" };
            (this.optionJpanel3 = new SetupJpanel3(115, 90, fontsizeData)).setBounds(88, 193, 115, 90);
            this.optionJpanel3.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String getname = (String)TestSetupJpanel.this.optionJpanel3.getJlist().getSelectedValue();
                    TestSetupJpanel.this.fontsizetext.setText(getname);
                    TestSetupJpanel.this.optionJpanel3.setVisible(false);
                    TestSetupJpanel.this.leftFlag3 = 1;
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("默认")) {
                        SystemMouslisten.typemoren();
                        SystemMouslisten.writeTxt();
                    }
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("楷体")) {
                        SystemMouslisten.type100();
                        SystemMouslisten.writeTxt();
                    }
                    if (TestSetupJpanel.this.fontsizetext.getText().equals("隶书")) {
                        SystemMouslisten.typelushu();
                        SystemMouslisten.writeTxt();
                    }
                }
            });
            this.optionJpanel3.setVisible(false);
            this.add(this.optionJpanel3);
            (this.fontsizetext = new JLabel("默认")).setForeground(Color.white);
            this.fontsizetext.setBounds(104, 173, 96, 20);
            this.fontsizetext.setFont(UIUtils.TEXT_FONT1);
            this.add(this.fontsizetext);
            (this.chooseDownArrows3 = new TrslationBtn("", 1)).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (TestSetupJpanel.this.leftFlag3 == 1) {
                        TestSetupJpanel.this.optionJpanel3.setVisible(true);
                        TestSetupJpanel.this.leftFlag3 = 0;
                        TestSetupJpanel.this.optionJpanel1.setVisible(false);
                        TestSetupJpanel.this.leftFlag1 = 1;
                        TestSetupJpanel.this.optionJpanel2.setVisible(false);
                        TestSetupJpanel.this.leftFlag2 = 1;
                    }
                    else {
                        TestSetupJpanel.this.optionJpanel3.setVisible(false);
                        TestSetupJpanel.this.leftFlag3 = 1;
                    }
                }
            });
            this.chooseDownArrows3.setBounds(88, 173, 115, 20);
            this.chooseDownArrows3.setIcons(CutButtonImage.cuts("img/xy2uiimg/47_png.button.combobox.png"));
            this.add(this.chooseDownArrows3);
            this.add(this.labFullscreen = new JLabel());
            this.add(this.labWindow = new JLabel());
            this.add(this.labFullscreen1 = new JLabel());
            this.add(this.xNms = new JLabel());
            this.add(this.xNmsZ = new JLabel());
            this.add(this.xNmsG = new JLabel());
            (this.labMusic = new JLabel()).setBounds(31, 266, 16, 14);
            this.labMusic.addMouseListener(new SystemMouslisten(2));
            this.add(this.labMusic);
            (this.labSound = new JLabel()).setBounds(111, 266, 16, 14);
            this.labSound.addMouseListener(new SystemMouslisten(3));
            this.add(this.labSound);
            (this.labMusicNew = new JLabel()).setBounds(31, 236, 16, 14);
            this.labMusicNew.addMouseListener(new SystemMouslisten(11));
            this.add(this.labMusicNew);
            (this.labSkillFullXJ = new JLabel()).setBounds(237, 86, 16, 14);
            this.labSkillFullXJ.addMouseListener(new SystemMouslisten(18));
            this.add(this.labSkillFullXJ);
            (this.labSkillFull = new JLabel()).setBounds(237, 115, 16, 14);
            this.labSkillFull.addMouseListener(new SystemMouslisten(10));
            this.add(this.labSkillFull);
            (this.labPlayswitch = new JLabel()).setBounds(237, 144, 16, 14);
            this.labPlayswitch.addMouseListener(new SystemMouslisten(4));
            this.add(this.labPlayswitch);
            (this.labAcceptteam = new JLabel()).setBounds(237, 173, 16, 14);
            this.labAcceptteam.addMouseListener(new SystemMouslisten(9));
            this.add(this.labAcceptteam);
            (this.labJoinfriends = new JLabel()).setBounds(237, 202, 16, 14);
            this.labJoinfriends.addMouseListener(new SystemMouslisten(7));
            this.add(this.labJoinfriends);
            (this.labReceiveitems = new JLabel()).setBounds(237, 231, 16, 14);
            this.labReceiveitems.addMouseListener(new SystemMouslisten(8));
            this.add(this.labReceiveitems);
            (this.labLetter = new JLabel()).setBounds(237, 260, 16, 14);
            this.labLetter.addMouseListener(new SystemMouslisten(6));
            this.add(this.labLetter);
            (this.labRefusemsg = new JLabel()).setBounds(237, 289, 16, 14);
            this.labRefusemsg.addMouseListener(new SystemMouslisten(5));
            this.add(this.labRefusemsg);
            (this.qlyxhcBtn = new WorkshopBtn("inkImg/hongmu/B34h.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT11, "清理缓存", 30)).setBounds(88, 204, 68, 17);
            this.qlyxhcBtn.addMouseListener(new WLLMouslisten(500));
            this.add(this.qlyxhcBtn);
            (this.jSlider = new JSlider(0, 0, 100, 100)).setBounds(22, 294, 185, 10);
            this.jSlider.setBackground(UIUtils.Color_BACK);
            this.jSlider.setUI(new MySliderUI());
            this.jSlider.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    float sec = (float)TestSetupJpanel.this.jSlider.getValue();
                }
            });
            this.add(this.jSlider);
        }
    }
    
    public void showArenaDownLab() {
        this.optionJpanel.setVisible(!this.optionJpanel.isVisible());
    }
    
    public String getValue(boolean type) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以切换！");
            return null;
        }
        if (FightingMixDeal.State != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("战斗中,不能切换");
            return null;
        }
        JList<String> jlist = this.optionJpanel.getJlist();
        String selectedValue = (String)jlist.getSelectedValue();
        this.labName.setText(selectedValue);
        if (selectedValue.equals("经典红木")) {
            MyQdModeJFrame.updateStyle("红木");
        }
        else if (selectedValue.equals("水墨江山")) {
            MyQdModeJFrame.updateStyle("水墨");
        }
        List<InternalForm> forms = FormsManagement.getForms();
        List<Integer> fromIds = (List)forms.<InternalForm>stream().map(InternalForm::getFormid).collect(Collectors.toList());
        try {
            for (Integer fromId : fromIds) {
                FormsManagement.disposeForm((int)fromId);
            }
        }
        catch (Exception a) {
            a.printStackTrace();
        }
        if (MyIsif.getStyle().equals("水墨")) {
            Util.mapmodel.setZoom(CutButtonImage.cuts("inkImg/background/49.png", 1, 1, false));
            Util.mapmodel.MiniMap(new ImageIcon("resource/smap/s" + Util.ditubianma + ".png").getImage());
        }
        else {
            Util.mapmodel.setZoom(CutButtonImage.cuts("img/xy2uiimg/icon_smallmap_border.png", 1, 1, false));
            Util.mapmodel.MiniMap(new ImageIcon("resource/smap/h" + Util.ditubianma + ".png").getImage());
        }
        ZhuJpanel.styles = true;
        FrameMessageChangeJpanel.styles = true;
        SystemMouslisten.readSysteminit();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        GoodsListFromServerUntil.GoodExpansion(loginResult.getTurnAround(), loginResult.getAttachPack());
        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().updata();
        List<Baby> a2 = UserMessUntil.getMyListBaby();
        BabyControl.babyinit(a2);
        for (int i = 0; i < a2.size(); ++i) {
            TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby((Baby)a2.get(i));
        }
        Baby baby = UserMessUntil.getbaby(RoleData.getRoleData().getLoginResult().getBabyId());
        TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
        return selectedValue;
    }
    
    public String concealArena(boolean type) {
        this.optionJpanel.setVisible(false);
        this.btnDown.setVisible(type);
        this.labName.setVisible(type);
        if (type) {
            return this.getValue(false);
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B211a.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 438, 350, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu1/B211ah.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 395, 369, this);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
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
    
    public JLabel getLabFullscreen() {
        return this.labFullscreen;
    }
    
    public void setLabFullscreen(JLabel labFullscreen) {
        this.labFullscreen = labFullscreen;
    }
    
    public JLabel getLabFullscreen1() {
        return this.labFullscreen1;
    }
    
    public void setLabFullscreen1(JLabel labFullscreen1) {
        this.labFullscreen1 = labFullscreen1;
    }
    
    public JLabel getLabWindow() {
        return this.labWindow;
    }
    
    public void setLabWindow(JLabel labWindow) {
        this.labWindow = labWindow;
    }
    
    public JLabel getLabWindow1() {
        return this.labWindow1;
    }
    
    public void setLabWindow1(JLabel labWindow1) {
        this.labWindow1 = labWindow1;
    }
    
    public JLabel getLabMusic() {
        return this.labMusic;
    }
    
    public void setLabMusic(JLabel labMusic) {
        this.labMusic = labMusic;
    }
    
    public JLabel getLabSound() {
        return this.labSound;
    }
    
    public void setLabSound(JLabel labSound) {
        this.labSound = labSound;
    }
    
    public JLabel getLabPlayswitch() {
        return this.labPlayswitch;
    }
    
    public void setLabPlayswitch(JLabel labPlayswitch) {
        this.labPlayswitch = labPlayswitch;
    }
    
    public JLabel getLabRefusemsg() {
        return this.labRefusemsg;
    }
    
    public void setLabRefusemsg(JLabel labRefusemsg) {
        this.labRefusemsg = labRefusemsg;
    }
    
    public JLabel getLabLetter() {
        return this.labLetter;
    }
    
    public void setLabLetter(JLabel labLetter) {
        this.labLetter = labLetter;
    }
    
    public JLabel getLabJoinfriends() {
        return this.labJoinfriends;
    }
    
    public void setLabJoinfriends(JLabel labJoinfriends) {
        this.labJoinfriends = labJoinfriends;
    }
    
    public JLabel getLabReceiveitems() {
        return this.labReceiveitems;
    }
    
    public void setLabReceiveitems(JLabel labReceiveitems) {
        this.labReceiveitems = labReceiveitems;
    }
    
    public JLabel getLabAcceptteam() {
        return this.labAcceptteam;
    }
    
    public void setLabAcceptteam(JLabel labAcceptteam) {
        this.labAcceptteam = labAcceptteam;
    }
    
    public JLabel getLabSkillFull() {
        return this.labSkillFull;
    }
    
    public void setLabSkillFull(JLabel labSkillFull) {
        this.labSkillFull = labSkillFull;
    }
    
    public JLabel getJpstyle() {
        return this.jpstyle;
    }
    
    public void setJpstyle(JLabel jpstyle) {
        this.jpstyle = jpstyle;
    }
    
    public SetupJpanel getOptionJpanel() {
        return this.optionJpanel;
    }
    
    public void setOptionJpanel(SetupJpanel optionJpanel) {
        this.optionJpanel = optionJpanel;
    }
    
    public void sfindu() {
        ImageIcon tips = new ImageIcon("inkImg/background/S33.png");
    }
    
    public JLabel getResolutiontext() {
        return this.resolutiontext;
    }
    
    public void setResolutiontext(JLabel resolutiontext) {
        this.resolutiontext = resolutiontext;
    }
    
    public JLabel getResolution1366() {
        return this.resolution1366;
    }
    
    public void setResolution1366(JLabel resolution1366) {
        this.resolution1366 = resolution1366;
    }
    
    public JLabel getXNms() {
        return this.xNms;
    }
    
    public void setXNms(JLabel xNms) {
        this.xNms = xNms;
    }
    
    public JLabel getXNmsZ() {
        return this.xNmsZ;
    }
    
    public void setXNmsZ(JLabel xNmsZ) {
        this.xNmsZ = xNmsZ;
    }
    
    public JLabel getXNmsG() {
        return this.xNmsG;
    }
    
    public void setXNmsG(JLabel xNmsG) {
        this.xNmsG = xNmsG;
    }
    
    public JLabel getInterfacialtext() {
        return this.interfacialtext;
    }
    
    public void setInterfacialtext(JLabel interfacialtext) {
        this.interfacialtext = interfacialtext;
    }
    
    public JLabel getFontsizetext() {
        return this.fontsizetext;
    }
    
    public void setFontsizetext(JLabel fontsizetext) {
        this.fontsizetext = fontsizetext;
    }
    
    public JLabel getLabMusicNew() {
        return this.labMusicNew;
    }
    
    public void setLabMusicNew(JLabel labMusicNew) {
        this.labMusicNew = labMusicNew;
    }
    
    public JLabel getLabSkillFullXJ() {
        return this.labSkillFullXJ;
    }
    
    public void setLabSkillFullXJ(JLabel labSkillFullXJ) {
        this.labSkillFullXJ = labSkillFullXJ;
    }
}
