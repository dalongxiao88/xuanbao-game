package org.skill.panel;

import com.tool.role.GetExp;
import org.come.until.MessagrFlagUntil;
import org.come.until.FormsManagement;
import org.come.action.MsgJframe2;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.List;
import org.come.bean.TYCTypeBean;
import org.come.bean.TYCPointBean;
import org.come.until.JmSum;
import org.come.until.UserMessUntil;
import org.come.until.AnalysisString;
import org.come.Jpanel.MsgJapnel;
import org.come.bean.Skill;
import org.come.bean.PathPoint;
import java.awt.Graphics;
import java.awt.Font;
import org.skill.frame.SkillMainFrame;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.bean.LoginResult;
import com.tool.role.RoleProperty;
import org.come.Frame.ZhuFrame;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleData;
import org.come.action.CopyOfSkillTYCMuse;
import com.tool.role.SkillUtil;
import com.tool.image.ImageMixDeal;
import org.come.until.CutButtonImage;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.skill.btn.SkillTYCBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CopyOfSkillTYCPanel extends JPanel
{
    public JLabel maxPointCase;
    public JLabel maxPointLab;
    public SkillTYCBtn switchBtn;
    public SkillTYCBtn exchangeBtn;
    public SkillTYCBtn washPointBtn;
    public SkillTYCBtn simulationBtn;
    public SkillTYCBtn confirmBtn;
    public JLabel raceCase;
    public JLabel raceLab;
    public SkillSMSelectOptionJpanel TYCselect;
    public int typeBtn;
    public int sum;
    public int bmw;
    public int issum;
    public int issum1;
    public int issum2;
    public int issum3;
    public int issum4;
    public int issum5;
    public int issum6;
    public int issum7;
    public String names;
    public String string;
    public String seName;
    public String system;
    public String system1;
    public boolean isdonw;
    public JLabel jLabel;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public JLabel type1;
    public JLabel type2;
    public JLabel type3;
    public boolean stop;
    public long time;
    public BigDecimal race;
    public boolean start;
    public boolean fof;
    public InitializeSkills skills;
    public TYCRole tycRole;
    public XSkillTYC xSkillTYC;
    public static int Levatoranus;
    public static String Skillnumber;
    public static String getRacename;
    
    public CopyOfSkillTYCPanel() {
        this.bmw = 1;
        this.isdonw = false;
        this.stop = true;
        this.start = false;
        this.fof = true;
        if (MyIsif.getStyle().equals("水墨")) {
            this.typeBtn = -1;
            this.sum = -1;
            this.setBounds(49, 62, 697, 558);
            this.setOpaque(false);
            this.setLayout(null);
            String[] rowData = { "男人", "女人", "男魔", "女魔", "男仙", "女仙", "男鬼", "女鬼", "男龙", "女龙" };
            (this.TYCselect = new SkillSMSelectOptionJpanel(66, 120, "Client/神通天演册/0426_2.png", rowData)).setBounds(240, 328, 64, 120);
            this.add(this.TYCselect);
            this.TYCselect.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int count = CopyOfSkillTYCPanel.this.TYCselect.getJlist().getSelectedIndex();
                    if (count == -1) {
                        return;
                    }
                    String getname = (String)CopyOfSkillTYCPanel.this.TYCselect.getJlist().getSelectedValue();
                    CopyOfSkillTYCPanel.this.initTYC(getname, null, (int)CopyOfSkillTYCPanel.this.tycRole.getLvl());
                    CopyOfSkillTYCPanel.this.names = getname;
                    CopyOfSkillTYCPanel.this.fof = true;
                    CopyOfSkillTYCPanel.this.sum = -1;
                    CopyOfSkillTYCPanel.this.raceLab.setText(getname);
                    CopyOfSkillTYCPanel.this.TYCselect.setVisible(false);
                }
            });
            this.add(this.switchBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "切换", UIUtils.TEXT_FONT81, 0, this));
            this.add(this.exchangeBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "兑换", UIUtils.TEXT_FONT82, 1, this));
            this.add(this.washPointBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "洗点", UIUtils.TEXT_FONT82, 2, this));
            (this.simulationBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "模拟加点", UIUtils.TEXT_FONT81, 3, this)).setBounds(488, 451, 99, 25);
            this.add(this.simulationBtn);
            (this.confirmBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "确认加点", UIUtils.TEXT_FONT81, 4, this)).setBounds(588, 451, 99, 25);
            this.add(this.confirmBtn);
            (this.maxPointLab = new JLabel()).setForeground(Color.white);
            this.maxPointLab.setBounds(63, 450, 60, 22);
            this.maxPointLab.setFont(UIUtils.TEXT_FONT1);
            this.add(this.maxPointLab);
            (this.maxPointCase = new JLabel()).setBounds(25, 450, 98, 22);
            this.maxPointCase.setIcon(CutButtonImage.getImage("inkImg/background/72.png", 98, 22));
            this.add(this.maxPointCase);
            (this.raceLab = new JLabel()).setForeground(Color.white);
            this.raceLab.setBounds(243, 449, 64, 20);
            this.raceLab.setFont(UIUtils.TEXT_FONT2);
            this.add(this.raceLab);
            (this.raceCase = new JLabel()).setBounds(205, 450, 104, 20);
            this.raceCase.setIcon(CutButtonImage.getImage("inkImg/background/73.png", 104, 20));
            this.add(this.raceCase);
            this.skills = new InitializeSkills();
            this.tycRole = new TYCRole();
            this.xSkillTYC = new XSkillTYC();
            this.jLabel = new JLabel();
            this.jLabel1 = new JLabel();
            this.jLabel2 = new JLabel();
            String seName = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            String[] vs = SkillUtil.getSepcieswas(seName);
            this.jLabel.addMouseListener(new CopyOfSkillTYCMuse(vs[0], 1, this, seName, this.bmw));
            this.jLabel1.addMouseListener(new CopyOfSkillTYCMuse(vs[1], 2, this, seName, this.bmw));
            this.jLabel2.addMouseListener(new CopyOfSkillTYCMuse(vs[2], 3, this, seName, this.bmw));
            this.add(this.jLabel);
            this.add(this.jLabel1);
            this.add(this.jLabel2);
            this.type1 = new JLabel();
            this.type2 = new JLabel();
            this.type3 = new JLabel();
            this.add(this.type1);
            this.add(this.type2);
            this.add(this.type3);
        }
        else {
            this.typeBtn = -1;
            this.sum = -1;
            this.setBounds(37, 58, 697, 558);
            this.setOpaque(false);
            this.setLayout(null);
            String[] rowData = { "男人", "女人", "男魔", "女魔", "男仙", "女仙", "男鬼", "女鬼", "男龙", "女龙" };
            (this.TYCselect = new SkillSMSelectOptionJpanel(64, 120, "img/soaringSkill/下拉框w64,h120px.png", rowData)).setBounds(240, 328, 64, 120);
            this.add(this.TYCselect);
            this.TYCselect.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int count = CopyOfSkillTYCPanel.this.TYCselect.getJlist().getSelectedIndex();
                    if (count == -1) {
                        return;
                    }
                    String getname = (String)CopyOfSkillTYCPanel.this.TYCselect.getJlist().getSelectedValue();
                    CopyOfSkillTYCPanel.this.initTYC(getname, null, (int)CopyOfSkillTYCPanel.this.tycRole.getLvl());
                    CopyOfSkillTYCPanel.this.names = getname;
                    CopyOfSkillTYCPanel.this.fof = true;
                    CopyOfSkillTYCPanel.this.sum = -1;
                    CopyOfSkillTYCPanel.this.raceLab.setText(getname);
                    CopyOfSkillTYCPanel.this.TYCselect.setVisible(false);
                }
            });
            this.add(this.switchBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "切换", UIUtils.TEXT_FONT81, 0, this));
            this.add(this.exchangeBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "兑换", UIUtils.TEXT_FONT82, 1, this));
            this.add(this.washPointBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "洗点", UIUtils.TEXT_FONT82, 2, this));
            (this.simulationBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "模拟加点", UIUtils.TEXT_FONT81, 3, this)).setBounds(488, 449, 99, 25);
            this.add(this.simulationBtn);
            (this.confirmBtn = new SkillTYCBtn(null, 1, UIUtils.COLOR_BTNPUTONGS, "确认加点", UIUtils.TEXT_FONT81, 4, this)).setBounds(588, 449, 99, 25);
            this.add(this.confirmBtn);
            (this.maxPointLab = new JLabel()).setForeground(Color.white);
            this.maxPointLab.setBounds(63, 450, 60, 22);
            this.maxPointLab.setFont(UIUtils.TEXT_FONT1);
            this.add(this.maxPointLab);
            (this.maxPointCase = new JLabel()).setBounds(25, 450, 98, 22);
            this.maxPointCase.setIcon(CutButtonImage.getImage("Client/天枢点w98,h22.png", 98, 22));
            this.add(this.maxPointCase);
            (this.raceLab = new JLabel()).setForeground(Color.white);
            this.raceLab.setBounds(243, 449, 64, 20);
            this.raceLab.setFont(UIUtils.TEXT_FONT2);
            this.add(this.raceLab);
            (this.raceCase = new JLabel()).setBounds(205, 450, 104, 20);
            this.raceCase.setIcon(CutButtonImage.getImage("Client/种族w98,h22.png", 104, 20));
            this.add(this.raceCase);
            this.skills = new InitializeSkills();
            this.tycRole = new TYCRole();
            this.xSkillTYC = new XSkillTYC();
            this.jLabel = new JLabel();
            this.jLabel1 = new JLabel();
            this.jLabel2 = new JLabel();
            String seName = SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            String[] vs = SkillUtil.getSepcieswas(seName);
            this.jLabel.addMouseListener(new CopyOfSkillTYCMuse(vs[0], 1, this, seName, this.bmw));
            this.jLabel1.addMouseListener(new CopyOfSkillTYCMuse(vs[1], 2, this, seName, this.bmw));
            this.jLabel2.addMouseListener(new CopyOfSkillTYCMuse(vs[2], 3, this, seName, this.bmw));
            this.add(this.jLabel);
            this.add(this.jLabel1);
            this.add(this.jLabel2);
            this.type1 = new JLabel();
            this.type2 = new JLabel();
            this.type3 = new JLabel();
            this.add(this.type1);
            this.add(this.type2);
            this.add(this.type3);
        }
    }
    
    public void addPoint() {
        if (this.skills.visible) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(this.skills.value4);
            for (int j = 0; j < this.skills.lists.size(); ++j) {
                XSkillTYC skillTYC = (XSkillTYC)this.skills.lists.get(j);
                if ((int)skillTYC.tycPointBean.getNowPoint() > 0) {
                    buffer.append("#");
                    buffer.append(skillTYC.tycPointBean.getTableId().toString());
                    buffer.append("_");
                    buffer.append(skillTYC.tycPointBean.getNowPoint());
                    buffer.append("_");
                    buffer.append(j);
                }
            }
            String skill = buffer.toString();
            if (skill.equals(this.skills.value4)) {
                return;
            }
            RoleData.getRoleData().getPrivateData().setSkills("T", skill);
            SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            this.initTYC(SkillUtil.getSepciesN(loginResult.getSpecies_id()), skill.split("#"), (int)loginResult.getGrade());
            ZhuFrame.getZhuJpanel().addPrompt2("已保存");
            RoleProperty.ResetEw();
        }
    }
    
    public void washPoint() {
        this.Washing(this.typeBtn);
        if (this.typeBtn == 1) {
            this.ChangeBtnPanel(1, ImageMixDeal.userimg.getRoleShow().getSpecies_id());
        }
        else if (this.skills.visible) {
            int dian = this.skills.getPoint().getX() * 3000000;
            if (dian > 0) {
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.washPoint, Integer.valueOf(dian), "#W你确定要花费#R" + dian + "#W银两,重置天演策加点吗?");
            }
        }
    }
    
    public void changeBtnPanel(int typeBtn) {
        if (this.typeBtn != typeBtn) {
            try {
                this.changeBtn(typeBtn);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.typeBtn = typeBtn;
        String seName = SkillUtil.getSepciesN(RoleData.getRoleData().getLoginResult().getSpecies_id());
        if (this.typeBtn == 0) {
            String[] data = RoleData.getRoleData().getPrivateData().getSkill("T");
            this.initTYC(seName, data, (int)this.tycRole.getLvl());
        }
        else if (this.typeBtn == 1) {
            this.initTYC(seName, null, (int)this.tycRole.getLvl());
        }
    }
    
    public void Washing(int typeBtn) {
        if (MyIsif.getStyle().equals("水墨")) {
            SkillMainPanel skill = SkillMainFrame.getSkillMainFrame().getSkillMainPanel();
            if (typeBtn == 1) {
                for (int i = 0; i < skill.getMainBtns().length; ++i) {
                    if (i != 1) {
                        skill.getMainBtns()[i].setBtn(-1);
                        skill.getMainBtns()[i].setIcon(CutButtonImage.getImage("Client/0419_1.png", 75, 28));
                        skill.getMainBtns()[i].setBounds(56 + 80 * i, 29, 75, 28);
                    }
                }
            }
            else {
                try {
                    for (int i = 0; i < skill.getMainBtns().length; ++i) {
                        String[] name = { "师门", "天演策", "经脉", "法门" };
                        skill.getMainBtns()[i].setIcons(CutButtonImage.cuts(skill.getBtnPanelPath(i, i == skill.getTypeBtn())));
                        skill.getMainBtns()[i].setText(name[i]);
                        skill.getMainBtns()[i].setBtn(1);
                        skill.getMainBtns()[i].setBounds(56 + 80 * i, 24, 75, 33);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        else {
            SkillMainPanel skill = SkillMainFrame.getSkillMainFrame().getSkillMainPanel();
            if (typeBtn == 1) {
                for (int i = 0; i < skill.getMainBtns().length; ++i) {
                    if (i != 1) {
                        skill.getMainBtns()[i].setBtn(-1);
                        skill.getMainBtns()[i].setIcon(CutButtonImage.getImage("Client/选项卡_其他_无法选中_w80,h40.png", 80, 40));
                        skill.getMainBtns()[i].setBounds(56 + 80 * i, 24, 80, 40);
                    }
                }
            }
            else {
                try {
                    for (int i = 0; i < skill.getMainBtns().length; ++i) {
                        String[] name = { "师门", "天演策", "经脉", "法门" };
                        skill.getMainBtns()[i].setIcons(CutButtonImage.cuts(skill.getBtnPanelPath(i, i == skill.getTypeBtn())));
                        skill.getMainBtns()[i].setText(name[i]);
                        skill.getMainBtns()[i].setBtn(1);
                        skill.getMainBtns()[i].setBounds(56 + 80 * i, 24, 80, 40);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    
    public void Roelder(String seName, String[] vs) {
        this.remove(this.jLabel);
        this.remove(this.jLabel1);
        this.remove(this.jLabel2);
        this.jLabel = new JLabel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel.setIcon(CutButtonImage.XZ1(seName, 1));
        this.jLabel1.setIcon(CutButtonImage.XZ1(seName, 2));
        this.jLabel2.setIcon(CutButtonImage.XZ1(seName, 3));
        this.type1.setIcon(CutButtonImage.CJ(vs[0]));
        this.type2.setIcon(CutButtonImage.CJ(vs[1]));
        this.type3.setIcon(CutButtonImage.CJ(vs[2]));
        this.jLabel.addMouseListener(new CopyOfSkillTYCMuse(vs[0], 1, this, seName, this.bmw));
        this.jLabel1.addMouseListener(new CopyOfSkillTYCMuse(vs[1], 2, this, seName, this.bmw));
        this.jLabel2.addMouseListener(new CopyOfSkillTYCMuse(vs[2], 3, this, seName, this.bmw));
        this.add(this.jLabel);
        this.add(this.jLabel1);
        this.add(this.jLabel2);
    }
    
    public void ChangeBtnPanel(int typeBtn, BigDecimal race) {
        if (this.typeBtn != typeBtn) {
            try {
                this.changeBtn(typeBtn);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.typeBtn = typeBtn;
        this.race = race;
        this.sum = -1;
        this.bmw = 1;
        this.fof = true;
        this.issum = 0;
        this.issum1 = 0;
        this.issum2 = 0;
        this.issum3 = 0;
        this.issum4 = 0;
        this.issum5 = 0;
        this.issum6 = 0;
        this.issum7 = 0;
        this.Washing(typeBtn);
        String seName = SkillUtil.getSepciesN(race);
        if (this.typeBtn == 0) {
            String[] data = RoleData.getRoleData().getPrivateData().getSkill("T");
            this.initTYC(seName, data, (int)this.tycRole.getLvl());
        }
        else if (this.typeBtn == 1) {
            this.initTYC(seName, null, (int)this.tycRole.getLvl());
        }
    }
    
    public void initTYC(String seName, String[] data, int lvl) {
        this.tycRole.data = data;
        this.raceLab.setText(seName);
        this.seName = seName;
        this.maxPointLab.setText(this.tycRole.getDian() + "");
        String[] vs = SkillUtil.getSepcieswas(seName);
        this.jLabel.setIcon(CutButtonImage.XZ1(seName, 1));
        this.jLabel1.setIcon(CutButtonImage.XZ1(seName, 2));
        this.jLabel2.setIcon(CutButtonImage.XZ1(seName, 3));
        this.type1.setIcon(CutButtonImage.CJ(vs[0]));
        this.type2.setIcon(CutButtonImage.CJ(vs[1]));
        this.type3.setIcon(CutButtonImage.CJ(vs[2]));
        this.jLabel.setBounds(284, 163, 63, 81);
        this.jLabel1.setBounds(348, 163, 63, 81);
        this.jLabel2.setBounds(304, 227, 89, 62);
        this.type1.setBounds(334, 168, 33, 33);
        this.type2.setBounds(295, 233, 33, 33);
        this.type3.setBounds(370, 233, 33, 33);
        if (data != null) {
            String[] lv = data[0].split("");
            this.skills.reset(seName, data, lvl, lv[0], lv[1], lv[0] + lv[1]);
            this.skills.setShow(true, seName);
            this.jLabel.setVisible(false);
            this.jLabel1.setVisible(false);
            this.jLabel2.setVisible(false);
            this.type1.setVisible(false);
            this.type2.setVisible(false);
            this.type3.setVisible(false);
            this.system = lv[0];
            this.system1 = lv[1];
        }
        else {
            this.skills.reset(seName, null, lvl, vs[0], vs[1], vs[0] + vs[1]);
            this.skills.setShow(false, seName);
            this.jLabel.setVisible(true);
            this.jLabel1.setVisible(true);
            this.jLabel2.setVisible(true);
            this.type1.setVisible(true);
            this.type2.setVisible(true);
            this.type3.setVisible(true);
        }
    }
    
    public void changeBtn(int typeBtn) throws Exception {
        Font font = new Font("隶书", 0, 17);
        if (MyIsif.getStyle().equals("水墨")) {
            if (typeBtn == 0) {
                this.maxPointCase.setBounds(95, 450, 98, 22);
                this.maxPointLab.setBounds(153, 450, 60, 22);
                this.raceCase.setVisible(false);
                this.raceLab.setVisible(false);
                this.TYCselect.setVisible(false);
                this.exchangeBtn.setVisible(true);
                this.switchBtn.setBounds(25, 450, 64, 26);
                this.switchBtn.setText("");
                this.switchBtn.setIcon(CutButtonImage.getImage("Client/神通天演册/0422_2.png", -1, -1));
                this.switchBtn.setBtn(-1);
                this.exchangeBtn.setBounds(195, 451, 34, 18);
                this.exchangeBtn.setText("兑换");
                this.exchangeBtn.setColors(UIUtils.White);
                this.exchangeBtn.setIcons(CutButtonImage.cuts("Client/6.png"));
                this.exchangeBtn.setBtn(1);
                this.washPointBtn.setBounds(235, 451, 34, 18);
                this.washPointBtn.setText("洗点");
                this.washPointBtn.setColors(UIUtils.White);
                this.washPointBtn.setIcons(CutButtonImage.cuts("Client/6.png"));
                this.simulationBtn.setText("模拟加点");
                this.simulationBtn.setFont(UIUtils.TEXT_FONT81);
                this.simulationBtn.setColors(UIUtils.COLOR_BTNPUTONGS);
                this.simulationBtn.setIcons(CutButtonImage.cuts("Client/99_25.png"));
                this.confirmBtn.setText("确认加点");
                this.confirmBtn.setFont(UIUtils.TEXT_FONT81);
                this.confirmBtn.setColors(UIUtils.COLOR_BTNPUTONGS);
                this.confirmBtn.setIcons(CutButtonImage.cuts("Client/99_25.png"));
                this.confirmBtn.setBtn(1);
            }
            else {
                this.maxPointCase.setBounds(25, 450, 98, 22);
                this.maxPointLab.setBounds(83, 450, 60, 22);
                this.raceCase.setVisible(true);
                this.raceLab.setVisible(true);
                this.exchangeBtn.setVisible(true);
                this.switchBtn.setBounds(289, 451, 18, 18);
                this.switchBtn.setText("");
                this.switchBtn.setIcons(CutButtonImage.cuts("Client/神通天演册/0422.png"));
                this.switchBtn.setBtn(1);
                this.exchangeBtn.setBounds(126, 451, 34, 18);
                this.exchangeBtn.setText("兑换");
                this.exchangeBtn.setIcon(CutButtonImage.getImage("Client/神通天演册/0422_1.png", -1, -1));
                this.exchangeBtn.setBtn(-1);
                this.washPointBtn.setBounds(166, 451, 34, 18);
                this.washPointBtn.setText("洗点");
                this.washPointBtn.setIcons(CutButtonImage.cuts("Client/6.png"));
                this.simulationBtn.setText("取消模拟");
                this.simulationBtn.setFont(font);
                this.simulationBtn.setColors(UIUtils.COLOR_BTNPUTONGS);
                this.simulationBtn.setIcons(CutButtonImage.cuts("Client/99_25.png"));
                this.simulationBtn.setBounds(488, 451, 99, 25);
                this.confirmBtn.setText("");
                this.confirmBtn.setIcon(CutButtonImage.getImage("Client/神通天演册/wfqs.png", 99, 25));
                this.confirmBtn.setBounds(588, 451, 99, 25);
                this.confirmBtn.setBtn(-1);
            }
        }
        else if (typeBtn == 0) {
            this.maxPointCase.setBounds(95, 450, 98, 22);
            this.maxPointLab.setBounds(153, 450, 60, 22);
            this.raceCase.setVisible(false);
            this.raceLab.setVisible(false);
            this.TYCselect.setVisible(false);
            this.exchangeBtn.setVisible(true);
            this.switchBtn.setBounds(25, 450, 64, 26);
            this.switchBtn.setText("");
            this.switchBtn.setIcon(CutButtonImage.getImage("img/soaringSkill/切换w60,h26.png", -1, -1));
            this.switchBtn.setBtn(-1);
            this.exchangeBtn.setBounds(195, 451, 34, 18);
            this.exchangeBtn.setText("兑换");
            this.exchangeBtn.setColors(UIUtils.White);
            this.exchangeBtn.setIcons(CutButtonImage.cuts("img/soaringSkill/兑换w34,h17.png"));
            this.exchangeBtn.setBtn(1);
            this.washPointBtn.setBounds(235, 451, 34, 18);
            this.washPointBtn.setText("洗点");
            this.washPointBtn.setColors(UIUtils.White);
            this.washPointBtn.setIcons(CutButtonImage.cuts("img/soaringSkill/洗点w34,h17.png"));
            this.simulationBtn.setText("模拟加点");
            this.simulationBtn.setFont(font);
            this.simulationBtn.setColors(UIUtils.COLOR_BTNPUTONG);
            this.simulationBtn.setIcons(CutButtonImage.cuts("inkImg/hongmu/1_png.button.btn_xy.png"));
            this.confirmBtn.setText("确认加点");
            this.confirmBtn.setFont(font);
            this.confirmBtn.setColors(UIUtils.COLOR_BTNPUTONG);
            this.confirmBtn.setIcons(CutButtonImage.cuts("inkImg/hongmu/1_png.button.btn_xy.png"));
            this.confirmBtn.setBtn(1);
        }
        else {
            this.maxPointCase.setBounds(25, 450, 98, 22);
            this.maxPointLab.setBounds(83, 450, 60, 22);
            this.raceCase.setVisible(true);
            this.raceLab.setVisible(true);
            this.exchangeBtn.setVisible(true);
            this.switchBtn.setBounds(289, 451, 18, 18);
            this.switchBtn.setText("");
            this.switchBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/34_png.button.xy_vscroll$down.png"));
            this.switchBtn.setBtn(1);
            this.exchangeBtn.setBounds(126, 451, 34, 18);
            this.exchangeBtn.setText("兑换");
            this.exchangeBtn.setIcon(CutButtonImage.getImage("img/soaringSkill/兑换（黑）w34,h17.png", -1, -1));
            this.exchangeBtn.setBtn(-1);
            this.washPointBtn.setBounds(166, 451, 34, 18);
            this.washPointBtn.setText("洗点");
            this.washPointBtn.setIcons(CutButtonImage.cuts("img/soaringSkill/洗点w34,h17.png"));
            this.simulationBtn.setText("取消模拟");
            this.simulationBtn.setFont(font);
            this.simulationBtn.setColors(UIUtils.COLOR_BTNPUTONG);
            this.simulationBtn.setIcons(CutButtonImage.cuts("inkImg/hongmu/1_png.button.btn_xy.png"));
            this.simulationBtn.setBounds(488, 451, 99, 25);
            this.confirmBtn.setText("");
            this.confirmBtn.setIcon(CutButtonImage.getImage("img/soaringSkill/确定加点w100,h26.png", 99, 25));
            this.confirmBtn.setBounds(588, 451, 99, 25);
            this.confirmBtn.setBtn(-1);
        }
    }
    
    public void DownName(String value) {
        try {
            String seName = SkillUtil.getSepciesN(this.race);
            if (this.typeBtn == 0) {
                if (seName == null) {
                    return;
                }
                String[] vs = SkillUtil.getSepcieswas(seName);
                if (vs[0].compareTo(value) == 0) {
                    this.string = vs[0] + vs[1];
                    this.skills.reset(seName, null, (int)this.tycRole.getLvl(), value, vs[1], this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[2]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[0]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[1]));
                    if (!this.skills.visible) {
                        ZhuFrame.getZhuJpanel().addPrompt2("#Y你选择了#G" + vs[0] + "系#Y和#G" + vs[1] + "系#Y神通。此后可以学习这两系及其交汇的天演策技能。");
                    }
                    this.system = vs[0];
                    this.system1 = vs[1];
                }
                else if (value.compareTo(vs[1]) == 0) {
                    this.string = vs[2] + vs[0];
                    this.skills.reset(seName, null, (int)this.tycRole.getLvl(), vs[2], vs[0], this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[1]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[2]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[0]));
                    if (!this.skills.visible) {
                        ZhuFrame.getZhuJpanel().addPrompt2("#Y你选择了#G" + vs[2] + "系#Y和#G" + vs[0] + "系#Y神通。此后可以学习这两系及其交汇的天演策技能。");
                    }
                    this.system = vs[2];
                    this.system1 = vs[0];
                }
                else {
                    this.string = vs[1] + vs[2];
                    this.skills.reset(seName, null, (int)this.tycRole.getLvl(), vs[1], value, this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[0]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[1]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[2]));
                    if (!this.skills.visible) {
                        ZhuFrame.getZhuJpanel().addPrompt2("#Y你选择了#G" + vs[1] + "系#Y和#G" + vs[2] + "系#Y神通。此后可以学习这两系及其交汇的天演策技能。");
                    }
                    this.system = vs[1];
                    this.system1 = vs[2];
                }
                this.jLabel2.setIcon(CutButtonImage.ZoomJr(seName, 6));
            }
            else {
                if (this.names == null) {
                    this.names = seName;
                }
                String[] vs = SkillUtil.getSepcieswas(this.names);
                if (vs[0].compareTo(value) == 0) {
                    this.string = vs[0] + vs[1];
                    this.skills.reset(this.names, null, (int)this.tycRole.getLvl(), value, vs[1], this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[2]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[0]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[1]));
                    this.system = vs[0];
                    this.system1 = vs[1];
                }
                else if (value.compareTo(vs[1]) == 0) {
                    this.string = vs[2] + vs[0];
                    this.skills.reset(this.names, null, (int)this.tycRole.getLvl(), vs[2], vs[0], this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[1]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[2]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[0]));
                    this.system = vs[2];
                    this.system1 = vs[0];
                }
                else {
                    this.string = vs[1] + vs[2];
                    this.skills.reset(this.names, null, (int)this.tycRole.getLvl(), vs[1], value, this.string);
                    this.type1.setIcon(CutButtonImage.CJ(vs[0]));
                    this.type2.setIcon(CutButtonImage.Selected(vs[1]));
                    this.type3.setIcon(CutButtonImage.Selected(vs[2]));
                    this.system = vs[1];
                    this.system1 = vs[2];
                }
                this.jLabel2.setIcon(CutButtonImage.ZoomJr(this.names, 6));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.skills.draw(g);
    }
    
    public void GameSum(int row) {
    }
    
    public void GameSumTow(int row) {
    }
    
    public void changePoint(XSkillTYC xSkillTYC, boolean is) {
        if (is) {
            if ((int)xSkillTYC.tycPointBean.getNowPoint() >= (int)xSkillTYC.tycPointBean.getMaxPoint()) {
                if (SkillID((int)xSkillTYC.tycPointBean.getTableId()) && RoleData.getRoleData().getPrivateData().getSkills().contains(xSkillTYC.tycPointBean.getTableId() + "")) {
                    CopyOfSkillTYCPanel.Levatoranus = xSkillTYC.x1;
                    CopyOfSkillTYCPanel.getRacename = SkillUtil.getSepciesN(this.race);
                    CopyOfSkillTYCPanel.Skillnumber = String.valueOf(xSkillTYC.tycPointBean.getTableId());
                    return;
                }
                ZhuFrame.getZhuJpanel().addPrompt2("此技能已达到修炼上限");
                return;
            }
            else {
                if (xSkillTYC.x1 <= 24) {
                    switch ((int)xSkillTYC.tycPointBean.getRow()) {
                        case 2: {
                            if (this.issum >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum;
                            break;
                        }
                        case 3: {
                            if (this.issum1 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum1;
                            break;
                        }
                        case 4: {
                            if (this.issum2 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum2;
                            break;
                        }
                        case 5: {
                            if (this.issum3 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum3;
                            break;
                        }
                    }
                }
                if (xSkillTYC.x1 >= 25) {
                    switch ((int)xSkillTYC.tycPointBean.getRow()) {
                        case 2: {
                            if (this.issum4 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum4;
                            break;
                        }
                        case 3: {
                            if (this.issum5 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum5;
                            break;
                        }
                        case 4: {
                            if (this.issum6 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum6;
                            break;
                        }
                        case 5: {
                            if (this.issum7 >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("对任意系的境界1~4，每境界最多分配10点天枢点");
                                return;
                            }
                            ++this.issum7;
                            break;
                        }
                    }
                }
            }
        }
        else if ((int)xSkillTYC.tycPointBean.getNowPoint() <= 0) {
            return;
        }
        InitializeSkills skillTYC = this.skills;
        if (skillTYC == null) {
            return;
        }
        PathPoint point = skillTYC.getPoint();
        if (is) {
            if ((int)this.tycRole.getDian() <= point.getX()) {
                return;
            }
            if (!skillTYC.isCondition(xSkillTYC, point, (int)this.tycRole.getLvl(), is)) {
                return;
            }
            xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() + 1);
            skillTYC.refreshPanel((int)this.tycRole.getLvl());
            if (this.skills.visible && this.skills != skillTYC) {
                this.skills.setShow(false, null);
            }
            this.maxPointLab.setText((int)this.tycRole.getDian() - point.getX() - 1 + "");
        }
        else {
            if (this.typeBtn == 0 && this.tycRole.isDian((int)xSkillTYC.tycPointBean.getTableId(), (int)xSkillTYC.tycPointBean.getNowPoint())) {
                return;
            }
            if ((int)xSkillTYC.tycPointBean.getNowPoint() <= 0) {
                return;
            }
            if (xSkillTYC.x1 <= 24) {
                switch ((int)xSkillTYC.tycPointBean.getRow()) {
                    case 2: {
                        --this.issum;
                        break;
                    }
                    case 3: {
                        --this.issum1;
                        break;
                    }
                    case 4: {
                        --this.issum2;
                        break;
                    }
                    case 5: {
                        --this.issum3;
                        break;
                    }
                }
            }
            if (xSkillTYC.x1 >= 25) {
                switch ((int)xSkillTYC.tycPointBean.getRow()) {
                    case 2: {
                        --this.issum4;
                        break;
                    }
                    case 3: {
                        --this.issum5;
                        break;
                    }
                    case 4: {
                        --this.issum6;
                        break;
                    }
                    case 5: {
                        --this.issum7;
                        break;
                    }
                }
            }
            xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() - 1);
            PathPoint point2 = skillTYC.getPoint();
            for (int i = skillTYC.lists.size() - 1; i >= 0; --i) {
                XSkillTYC xSkillTYC2 = (XSkillTYC)skillTYC.lists.get(i);
                if (xSkillTYC != xSkillTYC2 && (xSkillTYC2.icon1 == null || (int)xSkillTYC2.tycPointBean.getNowPoint() != 0) && !skillTYC.isCondition(xSkillTYC2, point2, (int)this.tycRole.getLvl(), is)) {
                    xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() + 1);
                    return;
                }
            }
            if (point2.getX() == 0) {
                this.skills.reset(this.skills.value, null, (int)this.tycRole.getLvl(), this.skills.value2, this.skills.value3, this.skills.value4);
            }
            else {
                skillTYC.refreshPanel((int)this.tycRole.getLvl());
            }
            this.maxPointLab.setText((int)this.tycRole.getDian() - point.getX() + 1 + "");
        }
    }
    
    private boolean checkPreArea(int issum, int issumX) {
        return (issum < 5 && issumX != 5) || (issumX < 10 && issum < 5) || (issumX < 5 && issum != 5) || (issum != 10 && issumX < 5);
    }
    
    public String getSkillMsg(Skill skill, XSkillTYC xSkillTYC) {
        TYCPointBean bean = xSkillTYC.tycPointBean;
        TYCTypeBean typeBean = bean.getTycTypeBean();
        int id = (int)bean.getTableId();
        int row = (int)bean.getRow();
        InitializeSkills skillTYC = this.skills;
        if (skillTYC == null || skill == null) {
            return "好像出问题了?联系管理员提交BUG";
        }
        boolean is = true;
        PathPoint point = skillTYC.getPoint();
        StringBuffer buffer = new StringBuffer();
        buffer.append("#cffffff【等级】\t#G");
        buffer.append(bean.getNowPoint());
        buffer.append("/");
        buffer.append(bean.getMaxPoint());
        buffer.append("#W#r【境界】\t");
        buffer.append((row <= 1) ? "0" : ((row == 2) ? "1" : ((row == 3) ? "2" : ((row == 4) ? "3" : ((row == 5) ? "4" : ((row == 6) ? "5" : ((row == 7) ? "5" : ((row == 8) ? "1" : ((row == 9) ? "2" : ((row == 10) ? "3" : "4"))))))))));
        buffer.append("#r【神元类型】");
        buffer.append((row == 1) ? "通用" : ((row >= 8) ? "融合" : "专有"));
        buffer.append("#r【技能类别】\t#c00FF00");
        this.Skillid(buffer, id);
        String skilllevel = skill.getSkilllevel();
        skill.setSkilllevel(bean.getNowPoint() + "");
        buffer.append(MsgJapnel.skillMsgchange(skill.getRemark(), skill));
        buffer.append("#r ");
        String value;
        if (xSkillTYC.value == null) {
            value = "0/5";
        }
        else {
            value = xSkillTYC.value;
        }
        String lv = value.split("/")[0];
        if (Integer.parseInt(lv) >= 1 && Integer.parseInt(lv) != 5) {
            buffer.append("#r ");
            buffer.append("#W下一等级：");
            buffer.append("#r【技能类别】\t#c00FF00");
            this.Skillid(buffer, id);
            buffer.append(MsgJapnel.SkillLowerLv(skill.getRemark(), skill, Integer.parseInt(lv) + 1));
            buffer.append("#r ");
        }
        PathPoint point2 = skillTYC.getTwoPoint();
        PathPoint point3 = skillTYC.getTwoPointY();
        int dias = point2.getY() + point2.getY1() + point2.getY2() + point2.getY3();
        int dias2 = point3.getY() + point3.getY1() + point3.getY2() + point3.getY3();
        if (row == 8 && this.checkPreArea(this.issum, this.issum4)) {
            buffer.append("#r#r#cff0000");
            buffer.append(" 前置区域 <" + this.system + "系境界1、" + this.system1 + "系境界1> 不足");
            buffer.append("5");
            buffer.append("#cff0000点");
        }
        if (row == 9 && this.checkPreArea(this.issum1, this.issum5)) {
            buffer.append("#r#r#cff0000");
            buffer.append(" 前置区域 <" + this.system + "系境界2、" + this.system1 + "系境界2> 不足");
            buffer.append("5");
            buffer.append("#cff0000点");
        }
        if (row == 10 && this.checkPreArea(this.issum2, this.issum5)) {
            buffer.append("#r#r#cff0000");
            buffer.append(" 前置区域 <" + this.system + "系境界3、" + this.system1 + "系境界3> 不足");
            buffer.append("5");
            buffer.append("#cff0000点");
        }
        if (row == 11 && this.checkPreArea(this.issum3, this.issum7)) {
            buffer.append("#r#r#cff0000");
            buffer.append(" 前置区域 <" + this.system + "系境界4、" + this.system1 + "系境界4> 不足");
            buffer.append("5");
            buffer.append("#cff0000点");
        }
        if (xSkillTYC.x1 <= 24) {
            if (point2.getY() < 5 && row == 3) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system + "系境界1> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point2.getY1() < 5 && row == 4) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system + "系境界2> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point2.getY2() < 5 && row == 5) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system + "系境界3> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point2.getY3() < 5 && row == 6) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system + "系境界4> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            if (dias < 20 && row == 6) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 本系境界1~4总点数不足");
                buffer.append("20");
                buffer.append("#cff0000点");
            }
        }
        else {
            if (point3.getY() < 5 && row == 3) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system1 + "系境界1> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point3.getY1() < 5 && row == 4) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system1 + "系境界2> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point3.getY2() < 5 && row == 5) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system1 + "系境界3> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            else if (point3.getY3() < 5 && row == 6) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 前置区域 <" + this.system1 + "系境界4> 不足");
                buffer.append("5");
                buffer.append("#cff0000点");
            }
            if (dias2 < 20 && row == 6) {
                buffer.append("#r#r#cff0000");
                buffer.append(" 本系境界1~4总点数不足");
                buffer.append("20");
                buffer.append("#cff0000点");
            }
        }
        skill.setSkilllevel(skilllevel);
        if (typeBean == null) {
            return buffer.toString();
        }
        Integer dian = typeBean.getLvl();
        if (dian != null && (int)dian > (int)this.tycRole.getLvl()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append(" 需要");
            buffer.append(AnalysisString.lvl((int)dian));
        }
        dian = typeBean.getOneTotalPoint();
        if (dian != null && (int)dian > point.getY()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append(" 前置区域 <境界" + ((row == 2) ? "0" : ((row == 3) ? "1" : ((row == 4) ? "2" : ((row == 5) ? "3" : ((row == 6) ? "4" : "5"))))) + "> 不足");
            buffer.append(dian);
            buffer.append("#cff0000点");
        }
        dian = typeBean.getTotalPoint((int)bean.getNowPoint());
        if (dian != null && (int)dian > point.getX()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append(" 神通天演策已分配点数不足");
            buffer.append(dian);
            buffer.append("#cff0000点");
        }
        dian = typeBean.getQZID();
        if (dian != null) {
            XSkillTYC skillTYC2 = skillTYC.getTyc((int)dian);
            if (skillTYC2 != null && (int)typeBean.getQZPoint() > (int)skillTYC2.tycPointBean.getNowPoint()) {
                Skill skill2 = UserMessUntil.getSkillId(dian.toString());
                if (skill2 != null) {
                    if (is) {
                        buffer.append("#r#r#cff0000");
                        is = false;
                    }
                    else {
                        buffer.append("#r");
                    }
                    buffer.append(" 需要");
                    buffer.append(typeBean.getQZPoint() + "点");
                    buffer.append("#c00ff00");
                    buffer.append(skill2.getSkillname());
                }
            }
        }
        List<Integer> list = typeBean.getExclusiveSkills();
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                dian = Integer.valueOf((int)JmSum.MZ((long)(int)list.get(i)));
                if ((int)dian != (int)bean.getTableId()) {
                    Skill skill3 = UserMessUntil.getSkillId(dian.toString());
                    if (skill3 != null) {
                        if (is) {
                            buffer.append("#r#r#cff0000");
                            is = false;
                        }
                        else {
                            buffer.append("#r");
                        }
                        buffer.append("#cff0000 与#c00ff00");
                        buffer.append(skill3.getSkillname());
                        buffer.append("#cff0000互斥");
                    }
                }
            }
        }
        return buffer.toString();
    }
    
    public void Skillid(StringBuffer buffer, int id) {
        if (id == 9110 || id == 9111 || id == 9126 || id == 9130 || id == 9151 || id == 9169 || id == 9170 || id == 9171 || id == 9189 || id == 9190 || id == 9207 || id == 9208 || id == 9209 || id == 9231 || id == 9232 || id == 9250 || id == 9251 || id == 9252 || id == 9262 || id == 9270 || id == 9286 || id == 9287 || id == 9307 || id == 9350 || id == 9352 || id == 9372 || id == 9389 || id == 9412 || id == 9861 || id == 10046 || id == 10047 || id == 9857 || id == 9870 || id == 9881 || id == 9132 || id == 9957 || id == 10015 || id == 10055 || id == 10080 || id == 9610 || id == 9612 || id == 10121 || id == 9510 || id == 10129 || id == 9711 || id == 10138 || id == 9810 || id == 9812) {
            buffer.append("#W主动");
            buffer.append("#r【冷却回合】\t#c00FF00");
            this.CoolingRound(buffer, id);
            buffer.append("#r ");
        }
        else {
            buffer.append("#W被动#c00FF00");
            buffer.append("#r ");
            buffer.append("#r ");
        }
    }
    
    public void CoolingRound(StringBuffer buffer, int id) {
        switch (id) {
            case 9110:
            case 9190:
            case 9207:
            case 9231:
            case 10046:
            case 10047: {
                buffer.append("#W15#c00FF00");
                buffer.append("#r ");
                break;
            }
            case 9111:
            case 9132:
            case 9170:
            case 9171:
            case 9208:
            case 9209:
            case 9252:
            case 9270:
            case 9510:
            case 9711:
            case 9810:
            case 9812:
            case 9857:
            case 9870:
            case 9881:
            case 9957:
            case 10015:
            case 10055:
            case 10080:
            case 10121:
            case 10129:
            case 10138: {
                buffer.append("#W10#c00FF00");
                buffer.append("#r ");
                break;
            }
            case 9169: {
                buffer.append("#W5#c00FF00");
                buffer.append("#r ");
                break;
            }
            case 9610:
            case 9612: {
                buffer.append("#W15#c00FF00");
                buffer.append("#r ");
                break;
            }
            default: {
                buffer.append("#r ");
                break;
            }
        }
    }
    
    public static int CoolingRound(int id) {
        switch (id) {
            case 9110:
            case 9190:
            case 9207:
            case 9231:
            case 10046:
            case 10047: {
                return 15;
            }
            case 9111:
            case 9208:
            case 9209:
            case 9252: {
                return 10;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static boolean SkillID(int id) {
        return id == 9110 || id == 9111 || id == 9126 || id == 9130 || id == 9151 || id == 9169 || id == 9170 || id == 9171 || id == 9189 || id == 9190 || id == 9207 || id == 9208 || id == 9209 || id == 9231 || id == 9232 || id == 9250 || id == 9251 || id == 9252 || id == 9262 || id == 9270 || id == 9286 || id == 9287 || id == 9307 || id == 9350 || id == 9352 || id == 9372 || id == 9389 || id == 9412 || id == 9861 || id == 10046 || id == 10047 || id == 9857 || id == 9870 || id == 9881 || id == 9132 || id == 9957 || id == 10015 || id == 10055 || id == 10080 || id == 9610 || id == 9612 || id == 10121 || id == 9510 || id == 10129 || id == 9711 || id == 10138 || id == 9810 || id == 9812;
    }
    
    public JLabel getMaxPointCase() {
        return this.maxPointCase;
    }
    
    public void setMaxPointCase(JLabel maxPointCase) {
        this.maxPointCase = maxPointCase;
    }
    
    public JLabel getMaxPointLab() {
        return this.maxPointLab;
    }
    
    public void setMaxPointLab(JLabel maxPointLab) {
        this.maxPointLab = maxPointLab;
    }
    
    public SkillTYCBtn getSwitchBtn() {
        return this.switchBtn;
    }
    
    public void setSwitchBtn(SkillTYCBtn switchBtn) {
        this.switchBtn = switchBtn;
    }
    
    public SkillTYCBtn getExchangeBtn() {
        return this.exchangeBtn;
    }
    
    public void setExchangeBtn(SkillTYCBtn exchangeBtn) {
        this.exchangeBtn = exchangeBtn;
    }
    
    public SkillTYCBtn getWashPointBtn() {
        return this.washPointBtn;
    }
    
    public void setWashPointBtn(SkillTYCBtn washPointBtn) {
        this.washPointBtn = washPointBtn;
    }
    
    public SkillTYCBtn getSimulationBtn() {
        return this.simulationBtn;
    }
    
    public void setSimulationBtn(SkillTYCBtn simulationBtn) {
        this.simulationBtn = simulationBtn;
    }
    
    public SkillTYCBtn getConfirmBtn() {
        return this.confirmBtn;
    }
    
    public void setConfirmBtn(SkillTYCBtn confirmBtn) {
        this.confirmBtn = confirmBtn;
    }
    
    public JLabel getRaceCase() {
        return this.raceCase;
    }
    
    public void setRaceCase(JLabel raceCase) {
        this.raceCase = raceCase;
    }
    
    public JLabel getRaceLab() {
        return this.raceLab;
    }
    
    public void setRaceLab(JLabel raceLab) {
        this.raceLab = raceLab;
    }
    
    public SkillSMSelectOptionJpanel getTYCselect() {
        return this.TYCselect;
    }
    
    public void setTYCselect(SkillSMSelectOptionJpanel TYCselect) {
        this.TYCselect = TYCselect;
    }
    
    public int getTypeBtn() {
        return this.typeBtn;
    }
    
    public void setTypeBtn(int typeBtn) {
        this.typeBtn = typeBtn;
    }
    
    public String getNames() {
        return this.names;
    }
    
    public void setNames(String names) {
        this.names = names;
    }
    
    public boolean isIsdonw() {
        return this.isdonw;
    }
    
    public void setIsdonw(boolean isdonw) {
        this.isdonw = isdonw;
    }
    
    public boolean isStop() {
        return this.stop;
    }
    
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public BigDecimal getRace() {
        return this.race;
    }
    
    public void setRace(BigDecimal race) {
        this.race = race;
    }
    
    public InitializeSkills getSkills() {
        return this.skills;
    }
    
    public void setSkills(InitializeSkills skills) {
        this.skills = skills;
    }
    
    public TYCRole getTycRole() {
        return this.tycRole;
    }
    
    public void setTycRole(TYCRole tycRole) {
        this.tycRole = tycRole;
    }
    
    public XSkillTYC getxSkillTYC() {
        return this.xSkillTYC;
    }
    
    public void setxSkillTYC(XSkillTYC xSkillTYC) {
        this.xSkillTYC = xSkillTYC;
    }
    
    public JLabel getjLabel() {
        return this.jLabel;
    }
    
    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }
    
    public JLabel getjLabel1() {
        return this.jLabel1;
    }
    
    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }
    
    public JLabel getjLabel2() {
        return this.jLabel2;
    }
    
    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }
    
    static {
        CopyOfSkillTYCPanel.Levatoranus = -1;
    }
    
    public class InitializeSkills
    {
        public String value;
        public String value2;
        public String value3;
        public String value4;
        public List<XSkillTYC> lists;
        public int pxone;
        private Integer p;
        private int px;
        public int index;
        public ImageIcon zq;
        public ImageIcon zq1;
        public ImageIcon zq2;
        public ImageIcon toppainting;
        public ImageIcon backimg;
        public ImageIcon[] arrow;
        public boolean visible;
        public int isd;
        public int isd1;
        
        public InitializeSkills() {
            this.index = 0;
            this.arrow = new ImageIcon[3];
            this.isd = 0;
            this.isd1 = 0;
        }
        
        public void reset(String value, String[] data, int lvl, String value2, String value3, String value4) {
            this.value = value;
            this.value2 = value2;
            this.value3 = value3;
            this.value4 = value4;
            this.arrow[0] = CutButtonImage.getImage("Client/0426_1.png", -1, -1);
            this.arrow[1] = CutButtonImage.getImage("Client/0426_1.png", -1, -1);
            this.arrow[2] = CutButtonImage.getImage("Client/0426_1.png", -1, -1);
            this.backimg = CutButtonImage.STTYCWAS(value);
            this.toppainting = CutButtonImage.TYHtoppainting(value);
            this.pxone = CutButtonImage.ZS(value);
            this.zq = CutButtonImage.ZQ(value);
            this.zq1 = CutButtonImage.CJR(value2);
            this.zq2 = CutButtonImage.CJR(value3);
            String[] values = UserMessUntil.getTYC(value).split("\\|");
            if (this.lists == null) {
                this.lists = new ArrayList<>();
            }
            int p = 0;
            int size = this.lists.size();
            for (String s : values) {
                String[] vs = s.split("#");
                TYCPointBean bean = new TYCPointBean();
                int zhi = Integer.parseInt(vs[0]);
                bean.setRow(Integer.valueOf(zhi / 10));
                bean.setRank(Integer.valueOf(zhi % 10));
                bean.setTableId(Integer.valueOf(Integer.parseInt(vs[1])));
                Skill skill = UserMessUntil.getSkillId(vs[1]);
                if (skill != null) {
                    bean.setMaxPoint(Integer.valueOf(Integer.parseInt(skill.getSkilllevel())));
                }
                else {
                    bean.setMaxPoint(Integer.valueOf(5));
                }
                if (p < size) {
                    ((XSkillTYC)this.lists.get(p)).reset(bean, this.pxone, p, value);
                    ++p;
                }
                else {
                    ++p;
                    XSkillTYC skillTYC = new XSkillTYC(bean, this.pxone, p, value);
                    this.lists.add(skillTYC);
                    CopyOfSkillTYCPanel.this.add(skillTYC);
                }
            }
            String[] values2;
            for (String s2 : values2 = UserMessUntil.getTYC(value2).split("\\|")) {
                String[] vs2 = s2.split("#");
                TYCPointBean bean2 = new TYCPointBean();
                int zhi2 = Integer.parseInt(vs2[0]);
                bean2.setRow(Integer.valueOf(zhi2 / 10));
                bean2.setRank(Integer.valueOf(zhi2 % 10));
                bean2.setTableId(Integer.valueOf(Integer.parseInt(vs2[1])));
                Skill skill2 = UserMessUntil.getSkillId(vs2[1]);
                if (skill2 != null) {
                    bean2.setMaxPoint(Integer.valueOf(Integer.parseInt(skill2.getSkilllevel())));
                }
                else {
                    bean2.setMaxPoint(Integer.valueOf(5));
                }
                if (vs2.length > 2) {
                    TYCTypeBean tycTypeBean = new TYCTypeBean();
                    for (int j = 2; j < vs2.length; ++j) {
                        String L = vs2[j].substring(1);
                        if (vs2[j].startsWith("D")) {
                            tycTypeBean.setOneTotalPoint(Integer.valueOf(Integer.parseInt(L)));
                        }
                        else {
                            String[] ls = L.split("-");
                            if (vs2[j].startsWith("Q")) {
                                tycTypeBean.setQZID(Integer.valueOf(Integer.parseInt(ls[0])));
                                tycTypeBean.setQZPoint(Integer.valueOf(Integer.parseInt(ls[1])));
                            }
                            else if (vs2[j].startsWith("Z")) {
                                for (int k = 0; k < ls.length; ++k) {
                                    tycTypeBean.setTotalPoint(Integer.valueOf(Integer.parseInt(ls[k])));
                                }
                            }
                            else if (vs2[j].startsWith("C")) {
                                for (int k = 0; k < ls.length; ++k) {
                                    tycTypeBean.setExclusiveSkill(Integer.valueOf(Integer.parseInt(ls[k])));
                                }
                            }
                            else if (vs2[j].startsWith("L")) {
                                tycTypeBean.setLvl(Integer.valueOf(AnalysisString.lvldirection((ls[0].equals("4") ? "飞升" : (ls[0] + "转")) + ls[1])));
                            }
                        }
                    }
                    bean2.setTycTypeBean(tycTypeBean);
                }
                if (p < size) {
                    ((XSkillTYC)this.lists.get(p)).reset(bean2, this.px, p, value);
                    ++p;
                }
                else {
                    ++p;
                    XSkillTYC skillTYC2 = new XSkillTYC(bean2, this.px, p, value);
                    this.lists.add(skillTYC2);
                    CopyOfSkillTYCPanel.this.add(skillTYC2);
                }
            }
            String[] values3;
            for (String s3 : values3 = UserMessUntil.getTYC(value3).split("\\|")) {
                String[] vs3 = s3.split("#");
                TYCPointBean bean3 = new TYCPointBean();
                int zhi3 = Integer.parseInt(vs3[0]);
                bean3.setRow(Integer.valueOf(zhi3 / 10));
                bean3.setRank(Integer.valueOf(zhi3 % 10));
                bean3.setTableId(Integer.valueOf(Integer.parseInt(vs3[1])));
                Skill skill3 = UserMessUntil.getSkillId(vs3[1]);
                if (skill3 != null) {
                    bean3.setMaxPoint(Integer.valueOf(Integer.parseInt(skill3.getSkilllevel())));
                }
                else {
                    bean3.setMaxPoint(Integer.valueOf(5));
                }
                if (vs3.length > 2) {
                    TYCTypeBean tycTypeBean2 = new TYCTypeBean();
                    for (int i = 2; i < vs3.length; ++i) {
                        String L2 = vs3[i].substring(1);
                        if (vs3[i].startsWith("D")) {
                            tycTypeBean2.setOneTotalPoint(Integer.valueOf(Integer.parseInt(L2)));
                        }
                        else {
                            String[] ls2 = L2.split("-");
                            if (vs3[i].startsWith("Q")) {
                                tycTypeBean2.setQZID(Integer.valueOf(Integer.parseInt(ls2[0])));
                                tycTypeBean2.setQZPoint(Integer.valueOf(Integer.parseInt(ls2[1])));
                            }
                            else if (vs3[i].startsWith("Z")) {
                                for (int l = 0; l < ls2.length; ++l) {
                                    tycTypeBean2.setTotalPoint(Integer.valueOf(Integer.parseInt(ls2[l])));
                                }
                            }
                            else if (vs3[i].startsWith("C")) {
                                for (int l = 0; l < ls2.length; ++l) {
                                    tycTypeBean2.setExclusiveSkill(Integer.valueOf(Integer.parseInt(ls2[l])));
                                }
                            }
                            else if (vs3[i].startsWith("L")) {
                                tycTypeBean2.setLvl(Integer.valueOf(AnalysisString.lvldirection((ls2[0].equals("4") ? "飞升" : (ls2[0] + "转")) + ls2[1])));
                            }
                        }
                    }
                    bean3.setTycTypeBean(tycTypeBean2);
                }
                if (p < size) {
                    ((XSkillTYC)this.lists.get(p)).reset(bean3, this.px, p + 2, value);
                    ++p;
                }
                else {
                    ++p;
                    XSkillTYC skillTYC3 = new XSkillTYC(bean3, this.px, p, value);
                    this.lists.add(skillTYC3);
                    CopyOfSkillTYCPanel.this.add(skillTYC3);
                }
            }
            String[] values4;
            for (String s4 : values4 = UserMessUntil.getTYC(value4).split("\\|")) {
                String[] vs4 = s4.split("#");
                TYCPointBean bean4 = new TYCPointBean();
                int zhi4 = Integer.parseInt(vs4[0]);
                bean4.setRow(Integer.valueOf(zhi4 / 10));
                bean4.setRank(Integer.valueOf(zhi4 % 10));
                bean4.setTableId(Integer.valueOf(Integer.parseInt(vs4[1])));
                Skill skill4 = UserMessUntil.getSkillId(vs4[1]);
                if (skill4 != null) {
                    bean4.setMaxPoint(Integer.valueOf(Integer.parseInt(skill4.getSkilllevel())));
                }
                else {
                    bean4.setMaxPoint(Integer.valueOf(5));
                }
                if (vs4.length > 2) {
                    TYCTypeBean tycTypeBean3 = new TYCTypeBean();
                    for (int m = 2; m < vs4.length; ++m) {
                        String L3 = vs4[m].substring(1);
                        if (vs4[m].startsWith("D")) {
                            tycTypeBean3.setOneTotalPoint(Integer.valueOf(Integer.parseInt(L3)));
                        }
                        else {
                            String[] ls3 = L3.split("-");
                            if (vs4[m].startsWith("Q")) {
                                tycTypeBean3.setQZID(Integer.valueOf(Integer.parseInt(ls3[0])));
                                tycTypeBean3.setQZPoint(Integer.valueOf(Integer.parseInt(ls3[1])));
                            }
                            else if (vs4[m].startsWith("Z")) {
                                for (int k2 = 0; k2 < ls3.length; ++k2) {
                                    tycTypeBean3.setTotalPoint(Integer.valueOf(Integer.parseInt(ls3[k2])));
                                }
                            }
                            else if (vs4[m].startsWith("C")) {
                                for (int k2 = 0; k2 < ls3.length; ++k2) {
                                    tycTypeBean3.setExclusiveSkill(Integer.valueOf(Integer.parseInt(ls3[k2])));
                                }
                            }
                            else if (vs4[m].startsWith("L")) {
                                tycTypeBean3.setLvl(Integer.valueOf(AnalysisString.lvldirection((ls3[0].equals("4") ? "飞升" : (ls3[0] + "转")) + ls3[1])));
                            }
                        }
                    }
                    bean4.setTycTypeBean(tycTypeBean3);
                }
                if (p < size) {
                    ((XSkillTYC)this.lists.get(p)).reset(bean4, this.px, p, value);
                    ++p;
                }
                else {
                    ++p;
                    XSkillTYC skillTYC4 = new XSkillTYC(bean4, this.px, p, value);
                    this.lists.add(skillTYC4);
                    CopyOfSkillTYCPanel.this.add(skillTYC4);
                }
            }
            if (data != null && value4.equals(data[0])) {
                for (int i2 = 1; i2 < data.length; ++i2) {
                    String[] vs = data[i2].split("_");
                    XSkillTYC skillTYC5 = this.getTyclies(Integer.parseInt(vs[0]), Integer.parseInt(vs[2]));
                    if (skillTYC5 != null) {
                        skillTYC5.setPoint(Integer.parseInt(vs[1]));
                        this.Ronhe(skillTYC5.x1, skillTYC5);
                    }
                }
                PathPoint point = this.getPoint();
                CopyOfSkillTYCPanel.this.maxPointLab.setText((int)CopyOfSkillTYCPanel.this.tycRole.getDian() - point.getX() + "");
            }
            this.refreshPanel(lvl);
        }
        
        public void Ronhe(int sum, XSkillTYC skillTYC) {
            if (sum <= 24) {
                switch ((int)skillTYC.tycPointBean.getRow()) {
                    case 2: {
                        CopyOfSkillTYCPanel this$0 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 3: {
                        CopyOfSkillTYCPanel this$2 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum1 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 4: {
                        CopyOfSkillTYCPanel this$3 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum2 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 5: {
                        CopyOfSkillTYCPanel this$4 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum3 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                }
            }
            if (sum >= 25) {
                switch ((int)skillTYC.tycPointBean.getRow()) {
                    case 2: {
                        CopyOfSkillTYCPanel this$5 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum4 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 3: {
                        CopyOfSkillTYCPanel this$6 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum5 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 4: {
                        CopyOfSkillTYCPanel this$7 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum6 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                    case 5: {
                        CopyOfSkillTYCPanel this$8 = CopyOfSkillTYCPanel.this;
                        CopyOfSkillTYCPanel.this.issum7 += (int)skillTYC.tycPointBean.getNowPoint();
                        break;
                    }
                }
            }
        }
        
        public void refreshPanel(int lvl) {
            if (this.lists != null) {
                PathPoint point = this.getPoint();
                for (int i = this.lists.size() - 1; i >= 0; --i) {
                    XSkillTYC skillTYC = (XSkillTYC)this.lists.get(i);
                    boolean is = this.isCondition(skillTYC, point, lvl, true);
                    if ((int)skillTYC.tycPointBean.getRow() == 1) {
                        skillTYC.setIcon1(CutButtonImage.getImage("img/soaringSkill/蒙版w34,h34.png", -1, -1));
                        skillTYC.setIncnd(CutButtonImage.getImage("Client/神通天演册/0413_3.png", -1, -1));
                    }
                    else if (is) {
                        skillTYC.setIcon1(null);
                        skillTYC.setIncnd(CutButtonImage.getImage("Client/神通天演册/0413_1.png", -1, -1));
                        skillTYC.setIcon(CutButtonImage.getImage("Client/神通天演册/0413_2.png", -1, -1));
                        skillTYC.setValue(skillTYC.tycPointBean.setPoint(skillTYC.tycPointBean.getNowPoint()));
                    }
                    else {
                        skillTYC.setIcon1(CutButtonImage.getImage("img/soaringSkill/蒙版w34,h34.png", -1, -1));
                        skillTYC.setIncnd(CutButtonImage.getImage("Client/神通天演册/0413_3.png", -1, -1));
                        skillTYC.setValue(null);
                        skillTYC.setIcon(null);
                    }
                }
            }
        }
        
        public boolean isCondition(XSkillTYC skillTYC, PathPoint point, int lvl, boolean type) {
            TYCPointBean bean = skillTYC.tycPointBean;
            PathPoint point2 = this.getTwoPointY();
            PathPoint point3 = this.getTwoPoint();
            if (skillTYC.x1 >= 25 && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                if (point2.getY() < 5 && (int)bean.getRow() == 3) {
                    return false;
                }
                if (point2.getY1() < 5 && (int)bean.getRow() == 4) {
                    return false;
                }
                if (point2.getY2() < 5 && (int)bean.getRow() == 5) {
                    return false;
                }
                if (point2.getY3() < 5 && (int)bean.getRow() == 6) {
                    return false;
                }
            }
            if (skillTYC.x1 <= 24 && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                if (point3.getY() < 5 && (int)bean.getRow() == 3) {
                    return false;
                }
                if (point3.getY1() < 5 && (int)bean.getRow() == 4) {
                    return false;
                }
                if (point3.getY2() < 5 && (int)bean.getRow() == 5) {
                    return false;
                }
                if (point3.getY3() < 5 && (int)bean.getRow() == 6) {
                    return false;
                }
            }
            if (CopyOfSkillTYCPanel.this.checkPreArea(CopyOfSkillTYCPanel.this.issum, CopyOfSkillTYCPanel.this.issum4) && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0)) && (int)bean.getRow() == 8) {
                return false;
            }
            if (CopyOfSkillTYCPanel.this.checkPreArea(CopyOfSkillTYCPanel.this.issum1, CopyOfSkillTYCPanel.this.issum5) && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0)) && (int)bean.getRow() == 9) {
                return false;
            }
            if (CopyOfSkillTYCPanel.this.checkPreArea(CopyOfSkillTYCPanel.this.issum2, CopyOfSkillTYCPanel.this.issum6) && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0)) && (int)bean.getRow() == 10) {
                return false;
            }
            if (CopyOfSkillTYCPanel.this.checkPreArea(CopyOfSkillTYCPanel.this.issum3, CopyOfSkillTYCPanel.this.issum7) && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0)) && (int)bean.getRow() == 11) {
                return false;
            }
            TYCTypeBean typeBean = bean.getTycTypeBean();
            if (typeBean == null) {
                return true;
            }
            Integer dian = typeBean.getQZID();
            if (dian != null && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                XSkillTYC skillTYC2 = this.getTyc((int)dian);
                if (skillTYC2 == null) {
                    return false;
                }
                if ((int)typeBean.getQZPoint() > (int)skillTYC2.tycPointBean.getNowPoint()) {
                    return false;
                }
            }
            dian = typeBean.getLvl();
            if (dian != null && (int)dian > lvl) {
                return false;
            }
            dian = typeBean.getOneTotalPoint();
            if (dian != null && (int)dian > point.getY() && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                return false;
            }
            dian = typeBean.getTotalPoint((int)bean.getNowPoint());
            if (dian != null && (int)dian > point.getX() && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                return false;
            }
            List<Integer> list = typeBean.getExclusiveSkills();
            if (list != null && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                for (int i = 0; i < list.size(); ++i) {
                    dian = Integer.valueOf((int)JmSum.MZ((long)(int)list.get(i)));
                    if ((int)dian != (int)bean.getTableId()) {
                        XSkillTYC skillTYC3 = this.getTyc((int)dian);
                        if (skillTYC3 != null && (int)skillTYC3.tycPointBean.getNowPoint() > 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        
        public boolean Osries(boolean is, int sum, int sum1, XSkillTYC xSkillTYC) {
            int sbs = sum + sum1;
            return (sbs == 10 || (int)xSkillTYC.tycPointBean.getRow() != 8) && is;
        }
        
        public PathPoint getPoint() {
            PathPoint point = new PathPoint();
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                TYCPointBean bean = ((XSkillTYC)this.lists.get(i)).tycPointBean;
                point.setX(point.getX() + (int)bean.getNowPoint());
                if ((int)bean.getRow() <= 2) {
                    point.setY(point.getY() + (int)bean.getNowPoint());
                }
            }
            return point;
        }
        
        public PathPoint getTwoPoint() {
            PathPoint point = new PathPoint();
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                TYCPointBean bean = ((XSkillTYC)this.lists.get(i)).tycPointBean;
                if (i <= 24) {
                    if ((int)bean.getRow() == 2) {
                        point.setY(point.getY() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 3) {
                        point.setY1(point.getY1() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 4) {
                        point.setY2(point.getY2() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 5) {
                        point.setY3(point.getY3() + (int)bean.getNowPoint());
                    }
                }
            }
            return point;
        }
        
        public PathPoint getTwoPointY() {
            PathPoint point = new PathPoint();
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                TYCPointBean bean = ((XSkillTYC)this.lists.get(i)).tycPointBean;
                if (i >= 24) {
                    if ((int)bean.getRow() == 2) {
                        point.setY(point.getY() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 3) {
                        point.setY1(point.getY1() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 4) {
                        point.setY2(point.getY2() + (int)bean.getNowPoint());
                    }
                    if ((int)bean.getRow() == 5) {
                        point.setY3(point.getY3() + (int)bean.getNowPoint());
                    }
                }
            }
            return point;
        }
        
        public XSkillTYC getTyc(int id) {
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                if ((int)((XSkillTYC)this.lists.get(i)).tycPointBean.getTableId() == id) {
                    return (XSkillTYC)this.lists.get(i);
                }
            }
            return null;
        }
        
        public XSkillTYC getTyclies(int id, int ids) {
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                if (i == ids && (int)((XSkillTYC)this.lists.get(i)).tycPointBean.getTableId() == id) {
                    return (XSkillTYC)this.lists.get(i);
                }
            }
            return null;
        }
        
        public void setShow(boolean flag, String sename) {
            try {
                this.visible = flag;
                if (this.lists != null) {
                    int o = 0;
                    if (sename.equals("男人") || sename.equals("男鬼") || sename.contains("龙")) {
                        o = 8;
                    }
                    else if (sename.equals("女人") || sename.equals("女魔")) {
                        o = 6;
                    }
                    else if (sename.equals("男魔") || sename.contains("仙") || sename.equals("女鬼")) {
                        o = 7;
                    }
                    for (int i = this.lists.size() - 1; i >= 0; --i) {
                        if (i < o) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(true);
                        }
                        else if (i > 49 && sename.equals("男魔")) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(false);
                        }
                        else if (i > 48 && sename.equals("女魔")) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(false);
                        }
                        else if (i > 48 && sename.contains("龙")) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(false);
                        }
                        else if (i > 48 && sename.equals("男鬼")) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(false);
                        }
                        else if (i > 47 && sename.equals("女鬼")) {
                            ((XSkillTYC)this.lists.get(i)).setVisible(false);
                        }
                        else {
                            ((XSkillTYC)this.lists.get(i)).setVisible(this.visible);
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        public void draw(Graphics g) {
            if (this.backimg != null) {
                g.drawImage(this.backimg.getImage(), 0, 0, null);
            }
            if (this.visible) {
                if (this.p == null) {
                    this.p = Integer.valueOf(g.getFontMetrics().stringWidth(this.value) / 2);
                    this.p = Integer.valueOf(this.px + 85 - (int)this.p);
                }
                g.drawImage(this.zq.getImage(), 110, 46, null);
                g.drawImage(this.zq1.getImage(), 149, 68, null);
                g.drawImage(this.zq.getImage(), 500, 46, null);
                g.drawImage(this.zq2.getImage(), 539, 68, null);
                if (this.value4.contains("蛊")) {
                    g.drawImage(this.arrow[0].getImage(), 95, 361, 11, 14, null);
                    g.drawImage(this.arrow[1].getImage(), 137, 361, 11, 14, null);
                    g.drawImage(this.arrow[2].getImage(), 179, 361, 11, 14, null);
                    g.drawImage(this.arrow[2].getImage(), 596, 361, 11, 14, null);
                }
                else {
                    g.drawImage(this.arrow[0].getImage(), 95, 361, 11, 14, null);
                    g.drawImage(this.arrow[1].getImage(), 137, 361, 11, 14, null);
                    g.drawImage(this.arrow[2].getImage(), 179, 361, 11, 14, null);
                    g.drawImage(this.arrow[0].getImage(), 512, 361, 11, 14, null);
                    g.drawImage(this.arrow[1].getImage(), 554, 361, 11, 14, null);
                    g.drawImage(this.arrow[2].getImage(), 596, 361, 11, 14, null);
                }
            }
            if (this.toppainting != null) {
                g.drawImage(this.toppainting.getImage(), 160, 0, null);
            }
        }
        
        public String getValue() {
            return this.value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public List<XSkillTYC> getLists() {
            return this.lists;
        }
        
        public void setLists(List<XSkillTYC> lists) {
            this.lists = lists;
        }
        
        public int getPxone() {
            return this.pxone;
        }
        
        public void setPxone(int pxone) {
            this.pxone = pxone;
        }
        
        public Integer getP() {
            return this.p;
        }
        
        public void setP(Integer p) {
            this.p = p;
        }
        
        public int getPx() {
            return this.px;
        }
        
        public void setPx(int px) {
            this.px = px;
        }
        
        public boolean isVisible() {
            return this.visible;
        }
        
        public void setVisible(boolean visible) {
            this.visible = visible;
        }
    }
    
    class XSkillTYC extends JComponent implements MouseListener
    {
        private TYCPointBean tycPointBean;
        private int px;
        private int py;
        private int y;
        private int s;
        private int x1;
        private boolean stop;
        private String value;
        private ImageIcon icon1;
        private ImageIcon icon2;
        private ImageIcon icon;
        private ImageIcon incnd;
        private ImageIcon arrow;
        
        public XSkillTYC(TYCPointBean tycPointBean, int x, int x1, String value) {
            this.stop = true;
            this.addMouseListener(this);
            this.reset(tycPointBean, x, x1, value);
            this.setOpaque(false);
            this.px = x / 176;
        }
        
        public XSkillTYC() {
            this.stop = true;
        }
        
        public void reset(TYCPointBean tycPointBean, int x, int x1, String value) {
            this.tycPointBean = tycPointBean;
            this.icon1 = null;
            this.incnd = null;
            this.arrow = null;
            this.icon2 = CutButtonImage.STTYCSkill((int)this.tycPointBean.getTableId(), value);
            this.setPoint(0);
            this.py = 0;
            String value2 = CopyOfSkillTYCPanel.this.skills.value4;
            int n = -1;
            switch (value2.hashCode()) {
                case 1094222: {
                    if (value2.equals("蛊忘")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1182408: {
                    if (value2.equals("速震")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 840260: {
                    if (value2.equals("攻速")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 974993: {
                    if (value2.equals("睡毒")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 678833: {
                    if (value2.equals("冰睡")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 876574: {
                    if (value2.equals("毒冰")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 981807: {
                    if (value2.equals("盘震")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1224468: {
                    if (value2.equals("震攻")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 833789: {
                    if (value2.equals("攻盘")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    this.s = 22;
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    this.s = 25;
                    break;
                }
                case 6:
                case 7:
                case 8: {
                    this.s = 24;
                    break;
                }
                default: {
                    this.s = 26;
                    break;
                }
            }
            this.x1 = x1;
            if (x1 <= this.s) {
                if ((int)tycPointBean.getRow() == 2) {
                    this.setBounds(36 + x + (int)tycPointBean.getRank() * 42, 106, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 3) {
                    this.setBounds(x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 4) {
                    this.setBounds(-22 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 5) {
                    this.setBounds(-1 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 6) {
                    this.setBounds(40 + x + (int)tycPointBean.getRank() * 42, 322, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 7) {
                    this.setBounds(40 + x + (int)tycPointBean.getRank() * 42, 376, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 8) {
                    this.setBounds(468 + x + (int)tycPointBean.getRank() * 42, 288, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 9) {
                    this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 244, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 10) {
                    this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 244, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 11) {
                    this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 244, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 8) {
                    this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 106, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 9) {
                    this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 10) {
                    this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                }
                else if ((int)tycPointBean.getRow() == 11) {
                    this.setBounds(285 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                }
                else {
                    this.setBounds(x + (int)tycPointBean.getRank() * 42, 12, 38, 38);
                }
            }
            else {
                switch ((int)tycPointBean.getRow()) {
                    case 2: {
                        this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 106, 38, 38);
                        break;
                    }
                    case 3: {
                        this.setBounds(496 + x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                        break;
                    }
                    case 4: {
                        this.setBounds(516 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                        break;
                    }
                    case 5: {
                        if (value.contains("仙")) {
                            this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(496 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                            break;
                        }
                    }
                    case 6: {
                        if (CopyOfSkillTYCPanel.this.skills.value4.contains("冥蛊")) {
                            this.setBounds(538 + x + (int)tycPointBean.getRank() * 42, 322, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 322, 38, 38);
                            break;
                        }
                    }
                    case 7: {
                        if (CopyOfSkillTYCPanel.this.skills.value4.contains("冥蛊")) {
                            this.setBounds(538 + x + (int)tycPointBean.getRank() * 42, 376, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(456 + x + (int)tycPointBean.getRank() * 42, 376, 38, 38);
                            break;
                        }
                    }
                    case 8: {
                        if (value.contains("鬼") || value.contains("龙")) {
                            this.setBounds(284 + x + (int)tycPointBean.getRank() * 42, 106, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 106, 38, 38);
                            break;
                        }
                    }
                    case 9: {
                        if (value.contains("人") || value.contains("龙")) {
                            this.setBounds(285 + x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                            break;
                        }
                        else if (value.contains("鬼")) {
                            this.setBounds(284 + x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 160, 38, 38);
                            break;
                        }
                    }
                    case 10: {
                        if (value.contains("仙") || value.contains("人")) {
                            this.setBounds(223 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                            break;
                        }
                        else if (value.contains("鬼")) {
                            this.setBounds(284 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 214, 38, 38);
                            break;
                        }
                    }
                    case 11: {
                        if (value.contains("仙") || value.contains("人")) {
                            this.setBounds(265 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                            break;
                        }
                        else if (value.contains("鬼")) {
                            this.setBounds(266 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                            break;
                        }
                        else {
                            this.setBounds(285 + x + (int)tycPointBean.getRank() * 42, 268, 38, 38);
                            break;
                        }
                    }
                    default: {
                        this.setBounds(x + (int)tycPointBean.getRank() * 42, 12, 38, 38);
                        break;
                    }
                }
            }
        }
        
        public void setPoint(int point) {
            this.value = this.tycPointBean.setPoint(Integer.valueOf(point));
            if (this.value.length() == 1) {
                this.icon = CutButtonImage.getImage("inkImg/background/S40.png", -1, -1);
            }
            else {
                this.icon = CutButtonImage.getImage("inkImg/background/S39.png", -1, -1);
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(3, 1.0f));
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), this.y + 2, this.y + 2, 34, 34, this);
            }
            if (CopyOfSkillTYCPanel.this.skills.visible) {
                if ((int)this.tycPointBean.getRow() < 2) {
                    this.icon1 = null;
                    this.incnd = CutButtonImage.getImage("Client/神通天演册/0413_1.png", -1, -1);
                }
                if (this.icon != null) {
                    if (this.value.length() == 1) {
                        this.icon = new ImageIcon("Client/神通天演册/0413_2.png");
                    }
                    else {
                        this.icon = new ImageIcon("Client/神通天演册/0413_2.png");
                    }
                    g.drawImage(this.icon.getImage(), 2, 24, this);
                }
                if (this.value != null) {
                    if ((int)this.tycPointBean.getNowPoint() >= 1 && (int)this.tycPointBean.getNowPoint() < 5) {
                        g.setColor(new Color(124, 220, 105));
                    }
                    else {
                        g.setColor(new Color(220, 198, 105));
                    }
                    g.setFont(UIUtils.TEXT_FONT);
                    g.drawString(this.value, 4, 34);
                }
            }
            if (this.incnd != null) {
                g.drawImage(this.incnd.getImage(), 0, 0, this);
            }
            if (this.icon1 != null) {
                g.drawImage(this.icon1.getImage(), this.y + 2, this.y + 2, this);
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            XSkillTYC source = (XSkillTYC)e.getSource();
            if (e.isControlDown()) {
                for (int i = 0; i < (int)source.getTycPointBean().getMaxPoint(); ++i) {
                    if (e.getButton() == 1) {
                        CopyOfSkillTYCPanel.this.changePoint(this, true);
                    }
                    if (e.getButton() == 3) {
                        CopyOfSkillTYCPanel.this.changePoint(this, false);
                    }
                }
                ++this.y;
                ++this.py;
            }
            else {
                ++this.y;
                if (e.getButton() == 1) {
                    if (this.icon1 != null) {
                        return;
                    }
                    CopyOfSkillTYCPanel.this.changePoint(this, true);
                }
                else if (e.getButton() == 3) {
                    CopyOfSkillTYCPanel.this.changePoint(this, false);
                }
                if (this.icon1 != null) {
                    return;
                }
                ++this.py;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            --this.y;
            if (this.icon1 != null) {
                return;
            }
            --this.py;
            Skill skill = UserMessUntil.getSkillId(this.tycPointBean.getTableId() + "");
            if (skill == null) {
                return;
            }
            CopyOfSkillTYCPanel copy = SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getCopyOfSkillTYCPanel();
            MsgJframe2.getJframe2().getJapnel2().TYC((skill != null) ? skill.getSkillname() : "", copy.getSkillMsg(skill, this));
            FormsManagement.showForm(628);
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
            Skill skill = UserMessUntil.getSkillId(this.tycPointBean.getTableId() + "");
            if (skill == null) {
                return;
            }
            CopyOfSkillTYCPanel copy = SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getCopyOfSkillTYCPanel();
            MsgJframe2.getJframe2().getJapnel2().TYC((skill != null) ? skill.getSkillname() : "", copy.getSkillMsg(skill, this));
            FormsManagement.showForm(628);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
            FormsManagement.HideForm(628);
        }
        
        public TYCPointBean getTycPointBean() {
            return this.tycPointBean;
        }
        
        public void setTycPointBean(TYCPointBean tycPointBean) {
            this.tycPointBean = tycPointBean;
        }
        
        public int getPx() {
            return this.px;
        }
        
        public void setPx(int px) {
            this.px = px;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public int getPy() {
            return this.py;
        }
        
        public void setPy(int py) {
            this.py = py;
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
        
        public ImageIcon getIcon() {
            return this.icon;
        }
        
        public void setIcon(ImageIcon icon) {
            this.icon = icon;
        }
        
        public ImageIcon getIncnd() {
            return this.incnd;
        }
        
        public void setIncnd(ImageIcon incnd) {
            this.incnd = incnd;
        }
    }
    
    class TYCRole
    {
        private int lvl;
        private int dian;
        private String[] data;
        
        public TYCRole() {
            this.lvl = 999;
            this.dian = 120;
            this.setLvl(Integer.valueOf(999));
            this.setDian(Integer.valueOf(120));
        }
        
        public Integer getLvl() {
            if (CopyOfSkillTYCPanel.this.typeBtn == 0) {
                return RoleData.getRoleData().getLoginResult().getGrade();
            }
            return Integer.valueOf((int)JmSum.MZ((long)this.lvl));
        }
        
        public void setLvl(Integer lvl) {
            this.lvl = (int)JmSum.ZM((long)(int)lvl);
        }
        
        public Integer getDian() {
            if (CopyOfSkillTYCPanel.this.typeBtn == 0) {
                return Integer.valueOf(GetExp.getTSP(RoleData.getRoleData().getLoginResult().getExtraPointInt("T")));
            }
            return Integer.valueOf((int)JmSum.MZ((long)this.dian));
        }
        
        public void setDian(Integer dian) {
            this.dian = (int)JmSum.ZM((long)(int)dian);
        }
        
        public boolean isDian(int id, int dian) {
            if (this.data == null) {
                return false;
            }
            String ids = id + "";
            for (int i = 1; i < this.data.length; ++i) {
                if (this.data[i].startsWith(ids)) {
                    return Integer.parseInt(this.data[i].split("_")[1]) <= dian;
                }
            }
            return false;
        }
    }
}
