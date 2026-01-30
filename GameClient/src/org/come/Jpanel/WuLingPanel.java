package org.come.Jpanel;

import com.tool.btn.DeleteSkillBtn;
import com.tool.btn.GoodPanelBtn;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.apache.commons.lang.StringUtils;
import org.come.bean.ImgZoom;
import org.come.bean.Skill;
import org.come.mouslisten.WLLMouslisten;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class WuLingPanel extends JPanel {
    public static int y = 150;
    private static int w = 190;
    private static String[] skillId = {"1600", "1601", "1602", "1603", "1604", "1611", "1612", "1820", "1822", "1831", "1833", "1834", "1835", "1836", "1838", "1839", "1850", "1852", "1854", "1858", "1871", "1872", "1873", "1874", "1878", "1880", "1887", "1888", "1889",};

    private RichLabel box;
    private JLabel tiltLab, lvlLab;
    private JLabel jkLab, jLab, sLab, jLab1;
    private DeleteSkillBtn openBtn;

    public static Skill skill;

    public WuLingPanel() {
        this.setPreferredSize(new Dimension(w - 10, 40));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        tiltLab = new JLabel();
        this.add(tiltLab);
        lvlLab = new JLabel(" 等级");
        lvlLab.setFont(UIUtils.TEXT_FONT);
        lvlLab.setForeground(Color.WHITE);
        lvlLab.setBounds(5, 25, 35, 20);
        this.add(lvlLab);


        //悟灵等级颜色
        if (MyIsif.getStyle().equals("水墨")) {
            openBtn = new DeleteSkillBtn("inkImg/button1/B30.png", 1, "开启");//inkImg/button1/B30.png
            openBtn.setBounds(241, 204, 34, 17);//星录
//            openBtn.setVisible(false);
            this.add(openBtn);
            sLab = new JLabel("1/10");
            sLab.setFont(UIUtils.TEXT_FONT);
            sLab.setForeground(Color.WHITE);
            sLab.setBounds(40, 25, 140, 20);
            sLab.setHorizontalAlignment(SwingConstants.CENTER);
            sLab.setVerticalAlignment(SwingConstants.CENTER);
            this.add(sLab);
            jLab = new JLabel();
            jLab.setIcon(CutButtonImage.getImage("inkImg/button/26.png", 140, 12));
            jLab.setBounds(40, 27, 140, 20);
            this.add(jLab);
            jkLab = new JLabel();
            jkLab.setBorder(BorderFactory.createEtchedBorder(0));
            jkLab.setBounds(50, 10, 140, 20);
            this.add(jkLab);

        } else {
            openBtn = new DeleteSkillBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "开启", UIUtils.COLOR_RED_BTNTEXT, UIUtils.TEXT_FONT);//星卡
            openBtn.setBounds(50, 10, 34, 17);//星录
//            openBtn.setVisible(false);
            this.add(openBtn);
            sLab = new JLabel("1/10");
            sLab.setFont(UIUtils.TEXT_FONT);
            sLab.setForeground(Color.WHITE);
            sLab.setBounds(40, 25, 140, 20);
            sLab.setHorizontalAlignment(SwingConstants.CENTER);
            sLab.setVerticalAlignment(SwingConstants.CENTER);
            this.add(sLab);
            jLab = new JLabel();
            jLab.setIcon(CutButtonImage.getImage("inkImg/hongmu/s144.png", 140 - 40, 12));
            jLab.setBounds(43, 27, 140 - 40, 20);
            this.add(jLab);

            jkLab = new JLabel();
            jkLab.setBorder(BorderFactory.createEtchedBorder(0));
            jkLab.setBounds(40, 25, 140 - 45, 20);
            this.add(jkLab);

        }
        //悟灵状态
        jLab1 = new JLabel();
        jLab1.setIcon(new ImageIcon("inkImg/hongmu/s144.png"));
        jLab1.setBounds(10, 15, 100, 10);
        //this.add(jLab1);
        //me
        box = new RichLabel("", UIUtils.TEXT_FONT);
        box.setLocation(5, 50);
        this.add(box);
    }

    public void update(Skill skill, int skillwl) {
        openBtn.setVisible(false);
        this.skill = skill;
        if (MyIsif.getStyle().equals("水墨")) {
            this.box.setText(null);
            if (StringUtils.isNotBlank(skill.getValue3())) {
                String[] v = skill.getValue3().split("\\|");
                String remark2 = skill.getRemark2();
                String remark3 = skill.getRemark2();
                for (int i = 0; i < v.length; i++) {
                    if (i == 0) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式一}", v1 + "");
                        remark3 = remark3.replace("{公式一}", v2 + "");
                    } else if (i == 1) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式二}", v1 + "");
                        remark3 = remark3.replace("{公式二}", v2 + "");
                    } else if (i == 2) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式三}", v1 + "");
                        remark3 = remark3.replace("{公式三}", v2 + "");
                    }
                }
                box.addText("#G当前灵阶效果：" + remark2 + "#r");
                if (skillwl < 10) {
                    box.addText("#R下一灵阶效果：" + remark3 + "#r");
                }
            }
            Dimension d = this.box.computeSize(180);
            this.box.setSize(d);
            this.box.setPreferredSize(d);

            if (!Arrays.asList(skillId).contains(skill.getSkillid())) {
                imgZoom = CutButtonImage.cuts("img/background/技能框2.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GRAY);
                tiltLab.setText("此技能无法悟灵");
                tiltLab.setBounds(60, 8, 180, 22);

                box.remove();
                box.addText("#W此技能无法悟灵");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 10);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);
                tiltLab.setVisible(false);
                return;
            }

            String petQlSkills = UserMessUntil.getChosePetMes().getPetSkillswl();

            if (StringUtils.isBlank(petQlSkills))
                petQlSkills = "";

            String[] petQlSkillsNum = new String[0];
            if (StringUtils.isNotBlank(petQlSkills))
                petQlSkillsNum = petQlSkills.split("\\|");
            Integer openql = UserMessUntil.getChosePetMes().getOpenql();
            if (openql <= 0 || (openql <= petQlSkillsNum.length && !petQlSkills.contains(skill.getSkillid()))) {
                imgZoom = CutButtonImage.cuts("img/background/技能框2.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                box.remove();
                box.addText("#W技能悟灵个数#R已达上限");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 10);
                tiltLab.setVisible(false);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);

                return;
            }
            if (skillwl > 0 || petQlSkills.contains(skill.getSkillid())) {

                imgZoom = CutButtonImage.cuts("img/background/技能框2.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(box.getHeight() + 50);
                this.setSize(new Dimension(w, box.getHeight() + 60));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GREEN);
                tiltLab.setText("该技能处于悟灵状态");
                tiltLab.setBounds(5, 0, 180 - 60, 20);
//                tiltLab.setBorder(BorderFactory.createLineBorder(Color.red));
                sLab.setText(skillwl + "/" + 10);
                box.setLocation(5, 50);
                jLab.setBounds(42, 27, 14 * skillwl, 20);
                jkLab.setBounds(39, 27, 147, 20);
                jkLab.setVisible(true);
                lvlLab.setVisible(true);
                jLab.setVisible(true);
                sLab.setVisible(true);
                box.setVisible(true);
                openBtn.setText("关闭");
                openBtn.setBounds(150, 5, 34, 17);//星录
                openBtn.setVisible(true);
                tiltLab.setVisible(true);
            } else if (Arrays.asList(skillId).contains(skill.getSkillid()) && openql > petQlSkillsNum.length) {
                imgZoom = CutButtonImage.cuts("img/background/技能框2.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GREEN);
                tiltLab.setText("此技能可以悟灵");
                tiltLab.setBounds(60, 8, 180, 22);

                box.remove();
                box.addText("#W技能可以开启#G悟灵状态");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 10);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);
                tiltLab.setVisible(false);
                openBtn.setBounds(150, 10, 34, 17);//星录
                openBtn.setVisible(true);
                openBtn.setText("开启");
                this.setComponentZOrder(openBtn, 0);

            }
        } else {
            this.box.setText(null);
            if (StringUtils.isNotBlank(skill.getValue3())) {
                String[] v = skill.getValue3().split("\\|");
                String remark2 = skill.getRemark2();
                String remark3 = skill.getRemark2();
                for (int i = 0; i < v.length; i++) {
                    if (i == 0) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式一}", v1 + "");
                        remark3 = remark3.replace("{公式一}", v2 + "");
                    } else if (i == 1) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式二}", v1 + "");
                        remark3 = remark3.replace("{公式二}", v2 + "");
                    } else if (i == 2) {
                        double v1 = Double.parseDouble(v[i]) * skillwl;
                        double v2 = Double.parseDouble(v[i]) * (skillwl + 1);
                        remark2 = remark2.replace("{公式三}", v1 + "");
                        remark3 = remark3.replace("{公式三}", v2 + "");
                    }
                }
                box.addText("#G当前灵阶效果：" + remark2 + "#r");
                if (skillwl < 10) {
                    box.addText("#R下一灵阶效果：" + remark3 + "#r");
                }
            }
            Dimension d = this.box.computeSize(180);
            this.box.setSize(d);
            this.box.setPreferredSize(d);

            if (!Arrays.asList(skillId).contains(skill.getSkillid())) {
                imgZoom = CutButtonImage.cuts("img/xy2uiimg/kuang.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GRAY);
                tiltLab.setText("此技能无法悟灵");
                tiltLab.setBounds(60, 8, 180, 22);

                box.remove();
                box.addText("#W此技能无法悟灵");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 10);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);
                tiltLab.setVisible(false);
                return;
            }

            String petQlSkills = UserMessUntil.getChosePetMes().getPetSkillswl();

            if (StringUtils.isBlank(petQlSkills))
                petQlSkills = "";

            String[] petQlSkillsNum = new String[0];
            if (StringUtils.isNotBlank(petQlSkills))
                petQlSkillsNum = petQlSkills.split("\\|");
            Integer openql = UserMessUntil.getChosePetMes().getOpenql();
            if (openql <= 0 || (openql <= petQlSkillsNum.length && !petQlSkills.contains(skill.getSkillid()))) {
                imgZoom = CutButtonImage.cuts("img/xy2uiimg/kuang.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                box.remove();
                box.addText("#W技能悟灵个数#R已达上限");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 10);
                tiltLab.setVisible(false);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);

                return;
            }
            if (skillwl > 0 || petQlSkills.contains(skill.getSkillid())) {

                imgZoom = CutButtonImage.cuts("img/xy2uiimg/kuang.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(box.getHeight() + 70);
                this.setSize(new Dimension(w, box.getHeight() + 85));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GREEN);
                tiltLab.setText("该技能处于悟灵状态");
                tiltLab.setBounds(5, 0, 180 - 60, 20);
                jkLab.setBounds(38, 27, 147, 20);
                jLab.setBounds(42, 27, 14 * skillwl, 20);
                sLab.setText(skillwl + "/" + 10);
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(7, 50);
                jkLab.setVisible(true);
                sLab.setBounds(40, 26, 140, 20);
                lvlLab.setVisible(true);
                jLab.setVisible(true);
                sLab.setVisible(true);
                box.setVisible(true);
                openBtn.setText("关闭");
                openBtn.setBounds(150, 2, 34, 17);//星录
                openBtn.setVisible(true);
                tiltLab.setVisible(true);
            } else if (Arrays.asList(skillId).contains(skill.getSkillid()) && openql > petQlSkillsNum.length) {
                imgZoom = CutButtonImage.cuts("img/xy2uiimg/kuang.png", 5, 5, true);
                imgZoom.setMiddlew(w - 10);
                imgZoom.setMiddleh(30);
                this.setSize(new Dimension(w, 40));
                tiltLab.setFont(UIUtils.TEXT_FONT);
                tiltLab.setForeground(Color.GREEN);
                tiltLab.setText("此技能可以悟灵");
                tiltLab.setBounds(60, 8, 180, 22);

                box.remove();
                box.addText("#W可开启#G悟灵状态");
                d = this.box.computeSize(180);
                this.box.setSize(d);
                this.box.setPreferredSize(d);
                box.setLocation(5, 8);
                jkLab.setVisible(false);
                lvlLab.setVisible(false);
                jLab.setVisible(false);
                sLab.setVisible(false);
                box.setVisible(true);
                tiltLab.setVisible(false);
                openBtn.setBounds(150, 10, 34, 17);//星录
                openBtn.setVisible(true);
                openBtn.setText("开启");
                this.setComponentZOrder(openBtn, 0);
            }
        }
    }

    private ImgZoom imgZoom;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgZoom != null) {
            imgZoom.draw(g);
        }
    }
}
