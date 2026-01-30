package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.bean.PathPoint;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import org.come.bean.Skill;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;
import com.tool.pet.PetProperty;
import java.util.ArrayList;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.PetJlistChoseMouslisten;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import com.tool.tcp.NewPart;
import com.tool.btn.PetPanelBtn;
import com.tool.btn.NeidanBtn;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.come.entity.RoleSummoning;
import javax.swing.JPanel;

public class SummonPetJpanel extends JPanel
{
    private RoleSummoning chosePetMes;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private JScrollPane jScrollPane;
    private Color fontcolor;
    private JLabel[] skillLabs;
    private JLabel[] skillIcons;
    private static JLabel labName;
    private static JLabel labName1;
    private static JLabel labLvl;
    private static JLabel labLoyalty;
    private static JLabel labclose;
    private static JLabel labHP;
    private static JLabel labMP;
    private static JLabel labAP;
    private static JLabel labSP;
    private static JLabel labCP;
    private NeidanBtn[] labNedan;
    private PetPanelBtn btnRolequality;
    private PetPanelBtn btnSummon;
    private int ndsl;
    public static NewPart part;
    public static ImageIcon icon;
    
    public SummonPetJpanel() {
        this.ndsl = 4;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getNdslsx() != null) {
            this.ndsl = Integer.parseInt(configure.getNdslsx());
        }
        this.listModel = new DefaultListModel<>();
        (this.listpet = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(20, 110, 210));
        this.fontcolor = Color.white;
        this.listpet.setSelectionForeground(this.fontcolor);
        this.listpet.setForeground(this.fontcolor);
        this.listpet.setFont(new Font("微软雅黑", 1, 14));
        this.listpet.setBackground(UIUtils.Color_BACK);
        this.listpet.setModel(this.listModel);
        this.listpet.setSelectionMode(0);
        this.listpet.setCellRenderer(new SummonRenderer(0));
        this.listpet.addMouseListener(new PetJlistChoseMouslisten(this.listpet, this));
        this.listpet.addMouseMotionListener(new PetJlistChoseMouslisten(this.listpet, this));
        (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.labNedan = new NeidanBtn[this.ndsl];
        SummonPetJpanel.labName1 = new JLabel("", 0);
        SummonPetJpanel.labName = new JLabel();
        SummonPetJpanel.labLvl = new JLabel();
        SummonPetJpanel.labLoyalty = new JLabel();
        SummonPetJpanel.labclose = new JLabel();
        SummonPetJpanel.labHP = new JLabel();
        SummonPetJpanel.labMP = new JLabel();
        SummonPetJpanel.labAP = new JLabel();
        SummonPetJpanel.labSP = new JLabel();
        SummonPetJpanel.labCP = new JLabel();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(468, 480));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 710);
            offBtn.setBounds(431, 10, 25, 25);
            this.add(offBtn);
            this.jScrollPane.setBounds(50, 81, 189, 218);
            for (int i = 0; i < this.labNedan.length; ++i) {
                (this.labNedan[i] = new NeidanBtn("inkImg/button/B161.png", 1, i)).setBounds(238, 100 + i * 20, 18, 18);
            }
            SummonPetJpanel.labName1.setBounds(260, 80, 160, 15);
            SummonPetJpanel.labName1.setForeground(UIUtils.COLOR_NAME);
            SummonPetJpanel.labName1.setFont(UIUtils.TEXT_FONT64);
            Font font = new Font("微软雅黑", 1, 14);
            SummonPetJpanel.labName.setBounds(94, 335, 99, 15);
            SummonPetJpanel.labName.setForeground(Color.WHITE);
            SummonPetJpanel.labName.setFont(font);
            SummonPetJpanel.labLvl.setBounds(94, 358, 99, 15);
            SummonPetJpanel.labLvl.setForeground(Color.WHITE);
            SummonPetJpanel.labLvl.setFont(font);
            SummonPetJpanel.labLoyalty.setBounds(94, 384, 99, 15);
            SummonPetJpanel.labLoyalty.setForeground(Color.WHITE);
            SummonPetJpanel.labLoyalty.setFont(font);
            SummonPetJpanel.labclose.setBounds(94, 407, 99, 15);
            SummonPetJpanel.labclose.setForeground(Color.WHITE);
            SummonPetJpanel.labclose.setFont(font);
            SummonPetJpanel.labHP.setBounds(308, 310, 135, 15);
            SummonPetJpanel.labHP.setForeground(Color.WHITE);
            SummonPetJpanel.labHP.setFont(font);
            SummonPetJpanel.labMP.setBounds(308, 334, 135, 15);
            SummonPetJpanel.labMP.setForeground(Color.WHITE);
            SummonPetJpanel.labMP.setFont(font);
            SummonPetJpanel.labAP.setBounds(308, 359, 135, 15);
            SummonPetJpanel.labAP.setForeground(Color.WHITE);
            SummonPetJpanel.labAP.setFont(font);
            SummonPetJpanel.labSP.setBounds(308, 383, 135, 15);
            SummonPetJpanel.labSP.setForeground(Color.WHITE);
            SummonPetJpanel.labSP.setFont(font);
            SummonPetJpanel.labCP.setBounds(308, 406, 135, 15);
            SummonPetJpanel.labCP.setForeground(Color.WHITE);
            SummonPetJpanel.labCP.setFont(font);
            (this.btnRolequality = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "抗性", Integer.valueOf(10), this)).setBounds(110, 304, 59, 24);
            (this.btnSummon = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "召唤", Integer.valueOf(11), this)).setBounds(214, 436, 68, 26);
            this.add(this.btnSummon);
        }
        else {
            this.setPreferredSize(new Dimension(440, 506));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 710);
            offBtn.setBounds(415, 0, 25, 25);
            this.add(offBtn);
            this.jScrollPane.setBounds(23, 93, 186, 216);
            for (int i = 0; i < this.labNedan.length; ++i) {
                (this.labNedan[i] = new NeidanBtn("img/xy2uiimg/4_png.button.btn_pet_bar.png", 1, i)).setBounds(212, 113 + i * 18, 15, 15);
            }
            SummonPetJpanel.labName1.setBounds(234, 93, 160, 15);
            SummonPetJpanel.labName1.setForeground(UIUtils.COLOR_NAME);
            SummonPetJpanel.labName1.setFont(UIUtils.TEXT_FONT64);
            Font font = new Font("微软雅黑", 1, 14);
            SummonPetJpanel.labName.setBounds(68, 348, 99, 15);
            SummonPetJpanel.labName.setForeground(Color.WHITE);
            SummonPetJpanel.labName.setFont(font);
            SummonPetJpanel.labLvl.setBounds(68, 371, 99, 15);
            SummonPetJpanel.labLvl.setForeground(Color.WHITE);
            SummonPetJpanel.labLvl.setFont(font);
            SummonPetJpanel.labLoyalty.setBounds(68, 397, 99, 15);
            SummonPetJpanel.labLoyalty.setForeground(Color.WHITE);
            SummonPetJpanel.labLoyalty.setFont(font);
            SummonPetJpanel.labclose.setBounds(68, 420, 99, 15);
            SummonPetJpanel.labclose.setForeground(Color.WHITE);
            SummonPetJpanel.labclose.setFont(font);
            SummonPetJpanel.labHP.setBounds(282, 323, 135, 15);
            SummonPetJpanel.labHP.setForeground(Color.WHITE);
            SummonPetJpanel.labHP.setFont(font);
            SummonPetJpanel.labMP.setBounds(282, 347, 135, 15);
            SummonPetJpanel.labMP.setForeground(Color.WHITE);
            SummonPetJpanel.labMP.setFont(font);
            SummonPetJpanel.labAP.setBounds(282, 372, 135, 15);
            SummonPetJpanel.labAP.setForeground(Color.WHITE);
            SummonPetJpanel.labAP.setFont(font);
            SummonPetJpanel.labSP.setBounds(282, 396, 135, 15);
            SummonPetJpanel.labSP.setForeground(Color.WHITE);
            SummonPetJpanel.labSP.setFont(font);
            SummonPetJpanel.labCP.setBounds(282, 419, 135, 15);
            SummonPetJpanel.labCP.setForeground(Color.WHITE);
            SummonPetJpanel.labCP.setFont(font);
            (this.btnRolequality = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "抗性", Integer.valueOf(10), this)).setBounds(84, 317, 60, 25);
            (this.btnSummon = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "召唤", Integer.valueOf(11), this)).setBounds(188, 449, 60, 25);
            this.add(this.btnSummon);
        }
        this.add(this.jScrollPane);
        for (int j = 0; j < this.labNedan.length; ++j) {
            this.add(this.labNedan[j]);
        }
        this.add(SummonPetJpanel.labMP);
        this.add(SummonPetJpanel.labAP);
        this.add(SummonPetJpanel.labSP);
        this.add(SummonPetJpanel.labCP);
        this.add(SummonPetJpanel.labName1);
        this.add(SummonPetJpanel.labName);
        this.add(SummonPetJpanel.labLvl);
        this.add(SummonPetJpanel.labLoyalty);
        this.add(SummonPetJpanel.labclose);
        this.add(SummonPetJpanel.labHP);
        this.add(this.btnRolequality);
        this.add(this.btnSummon);
    }
    
    public void showPetValue(RoleSummoning chosePetMes) {
        this.chosePetMes = chosePetMes;
        List<String> skills = new ArrayList<>();
        String innerGoods = null;
        if (chosePetMes != null) {
            innerGoods = chosePetMes.getInnerGoods();
            SummonPetJpanel.labName.setText(chosePetMes.getSummoningname());
            SummonPetJpanel.labName1.setText(chosePetMes.getSummoningname());
            this.changeGrade((int)chosePetMes.getGrade());
            SummonPetJpanel.labLoyalty.setText(chosePetMes.getFaithful().toString());
            SummonPetJpanel.labclose.setText(chosePetMes.getFriendliness() + "");
            int[] pets = PetProperty.getPetHMASp(chosePetMes);
            SummonPetJpanel.labHP.setText(chosePetMes.getBasishp() + "/" + pets[0]);
            SummonPetJpanel.labMP.setText(chosePetMes.getBasismp() + "/" + pets[1]);
            SummonPetJpanel.labAP.setText(pets[2] + "");
            SummonPetJpanel.labSP.setText(pets[3] + "");
            SummonPetJpanel.labCP.setText(pets[4] + "");
            SummonPetJpanel.part = chosePetMes.getPart();
            if (chosePetMes.getPetSkills() != null) {
                String[] skill = chosePetMes.getPetSkills().split("\\|");
                skills.addAll(Arrays.asList(skill));
                if (StringUtils.isNotBlank(chosePetMes.getBeastSkills())) {
                    skills.add(chosePetMes.getBeastSkills());
                }
            }
        }
        this.initNeiDan(innerGoods);
        this.showSkill(skills);
    }
    
    public void initNeiDan(String innerGoods) {
        if (StringUtils.isNotBlank(innerGoods)) {
            String[] strings = innerGoods.split("\\|");
            for (int i = 0; i < this.ndsl; ++i) {
                this.labNedan[i].setGoodstable(null);
                this.remove(this.labNedan[i]);
            }
            if (strings.length > 0) {
                for (int i = 0; i < strings.length; ++i) {
                    this.add(this.labNedan[i]);
                    this.labNedan[i].setGoodstable((Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(strings[i])));
                }
            }
        }
        else {
            for (int j = 0; j < this.ndsl; ++j) {
                this.labNedan[j].setGoodstable(null);
                this.remove(this.labNedan[j]);
            }
        }
    }
    
    public void showSkill(List<String> skills) {
        if (this.skillIcons != null) {
            for (int i = 0; i < this.skillIcons.length; ++i) {
                if (this.skillIcons[i] != null) {
                    this.remove(this.skillIcons[i]);
                    this.remove(this.skillLabs[i]);
                }
            }
            this.skillIcons = null;
            this.skillLabs = null;
        }
        if (skills != null && skills.size() > 0) {
            this.skillIcons = new JLabel[skills.size()];
            this.skillLabs = new JLabel[skills.size()];
            for (int i = 0; i < this.skillIcons.length; ++i) {
                String skillId = skills.get(i);
                Skill skill = UserMessUntil.getSkillBean().getSkillMap().get(skillId);
                if (skill == null) {
                    System.out.println("没有技能："+skillId);
                    continue;
                }
                String name = skill.getSkillname();
                if ((Integer.parseInt(skillId) <= 1605 && Integer.parseInt(skillId) >= 1600) || (Integer.parseInt(skillId) <= 1612 && Integer.parseInt(skillId) >= 1611) || (Integer.parseInt(skillId) <= 1827 && Integer.parseInt(skillId) >= 1815) || Integer.parseInt(skillId) == 1831 || (Integer.parseInt(skillId) <= 1839 && Integer.parseInt(skillId) >= 1833) || Integer.parseInt(skillId) == 1811 || Integer.parseInt(skillId) == 1850 || Integer.parseInt(skillId) == 1852 || Integer.parseInt(skillId) == 1854 || Integer.parseInt(skillId) == 1858 || Integer.parseInt(skillId) == 1860 || Integer.parseInt(skillId) == 1862 || Integer.parseInt(skillId) == 1864 || (Integer.parseInt(skillId) <= 1878 && Integer.parseInt(skillId) >= 1871) || Integer.parseInt(skillId) == 1880 || (Integer.parseInt(skillId) <= 1887 && Integer.parseInt(skillId) >= 1882)) {
                    name += "（高级技能）";
                }
                else if ((Integer.parseInt(skillId) <= 1608 && Integer.parseInt(skillId) >= 1606) || Integer.parseInt(skillId) == 1814 || (Integer.parseInt(skillId) <= 1830 && Integer.parseInt(skillId) >= 1828) || (Integer.parseInt(skillId) <= 1842 && Integer.parseInt(skillId) >= 1840) || Integer.parseInt(skillId) == 1881 || (Integer.parseInt(skillId) <= 1869 && Integer.parseInt(skillId) >= 1865)) {
                    name += "（终极技能）";
                }
                else if (Integer.parseInt(skillId) == 1814 || Integer.parseInt(skillId) == 1610 || Integer.parseInt(skillId) == 1609) {
                    name += "（神兽技能）";
                }
                this.skillIcons[i] = new JLabel();
                this.skillLabs[i] = new JLabel();
                if (MyIsif.getStyle().equals("水墨")) {
                    this.skillIcons[i].setBounds(427, 66 + i * 26, 20, 20);
                    this.skillIcons[i].setIcon(CutButtonImage.getImage("img/skill/wxs_" + (String)skills.get(i) + ".png", 20, 20));
                    this.skillLabs[i].setBounds(425, 64 + i * 26, 24, 24);
                    this.skillLabs[i].setIcon(CutButtonImage.getImage("inkImg/button1/s602.png", 24, 24));
                }
                else {
                    this.skillIcons[i].setBounds(399, 79 + i * 26, 20, 20);
                    this.skillIcons[i].setIcon(CutButtonImage.getImage("img/skill/wxs_" + (String)skills.get(i) + ".png", 20, 20));
                    this.skillLabs[i].setBounds(396, 76 + i * 26, 26, 26);
                    this.skillLabs[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/88_png.xy2uiimg.skill_tip.png", 26, 26));
                }
                String finalName = name;
                this.skillIcons[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        PathPoint point = GameJpanel.getGameJpanel().mousepath();
                        MsgJframe.getJframe().getJapnel().showSkillName(finalName, point.getX(), point.getY());
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        FormsManagement.HideForm(46);
                    }
                });
                this.add(this.skillIcons[i]);
                this.add(this.skillLabs[i]);
            }
        }
    }
    
    public void showListModel(List<RoleSummoning> pets, BigDecimal petid) {
        this.listModel.removeAllElements();
        this.initNeiDan(null);
        for (int i = 0, length = pets.size(); i < length; ++i) {
            RoleSummoning pet = (RoleSummoning)pets.get(i);
            this.listModel.addElement(pet.getSummoningname());
            if (pet.getSid() != null && petid != null && petid.compareTo(pet.getSid()) != 0) {
                this.listModel.set(i, pet.getSummoningname());
                this.showPetValue(pet);
            }
        }
    }
    
    private void changeGrade(int grade) {
        if (grade <= 100) {
            SummonPetJpanel.labLvl.setForeground(new Color(255, 255, 255));
            SummonPetJpanel.labLvl.setText("0转" + grade + "级");
        }
        else if (grade > 100 && grade <= 221) {
            SummonPetJpanel.labLvl.setForeground(new Color(255, 255, 255));
            SummonPetJpanel.labLvl.setText("1转" + (grade - 101) + "级");
        }
        else if (grade > 221 && grade <= 362) {
            SummonPetJpanel.labLvl.setForeground(new Color(255, 255, 255));
            SummonPetJpanel.labLvl.setText("2转" + (grade - 222) + "级");
        }
        else if (grade > 362 && grade <= 543) {
            SummonPetJpanel.labLvl.setForeground(new Color(255, 255, 255));
            SummonPetJpanel.labLvl.setText("3转" + (grade - 363) + "级");
        }
        else {
            SummonPetJpanel.labLvl.setForeground(new Color(255, 255, 255));
            SummonPetJpanel.labLvl.setText("点化" + (grade - 544) + "级");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            SummonPetJpanel.icon = new ImageIcon("inkImg/background1/B600.png");
            g.drawImage(SummonPetJpanel.icon.getImage(), 0, 0, this);
            if (SummonPetJpanel.part != null) {
                Graphics2D graphics2d = (Graphics2D)g;
                SummonPetJpanel.part.draw(graphics2d, 350, 245, 0, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            SummonPetJpanel.icon = new ImageIcon("inkImg/hongmu1/B600.png");
            g.drawImage(SummonPetJpanel.icon.getImage(), 0, 0, this);
            if (SummonPetJpanel.part != null) {
                Graphics2D graphics2d = (Graphics2D)g;
                SummonPetJpanel.part.draw(graphics2d, 330, 265, 0, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public RoleSummoning getChosePetMes() {
        return this.chosePetMes;
    }
    
    public void setChosePetMes(RoleSummoning chosePetMes) {
        this.chosePetMes = chosePetMes;
    }
    
    public NeidanBtn[] getLabNedan() {
        return this.labNedan;
    }
    
    public void setLabNedan(NeidanBtn[] labNedan) {
        this.labNedan = labNedan;
    }
}
