package org.come.Jpanel;

import com.updateNew.MyIsif;
import java.awt.Graphics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import come.tool.FightingData.FBUtil;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.entity.Pal;
import org.come.model.PalData;
import java.util.ArrayList;
import org.come.Frame.PartnerJframe;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import com.tool.btn.PartnerBtn;
import javax.swing.JPanel;

public class PartnerSkillJpanel extends JPanel
{
    private PartnerBtn btnDelete;
    private PartnerBtn btnUseGoods;
    public static PartnerBtn[] btnGetSkill;
    public static JLabel[] labSkill;
    public static JLabel[] labSkillName;
    private RichLabel richMessage;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList<String> listNaturalskill;
    private DefaultListModel<String> modelNaturalskill;
    private ImageIcon icon;
    public static int x;
    public static List<String> AIdataList;
    
    public PartnerSkillJpanel() {
        this.setPreferredSize(new Dimension(335, 288));
        this.setOpaque(false);
        this.setLayout(null);
        this.getBtnDelete();
        this.getLabSkill();
        this.getBtnUseGoods();
        this.getLabSkill();
        this.getRichMessage();
        this.modelNaturalskill = new DefaultListModel<>();
        (this.listNaturalskill = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listNaturalskill.setSelectionForeground(Color.YELLOW);
        this.listNaturalskill.setForeground(Color.YELLOW);
        this.listNaturalskill.setFont(new Font("微软雅黑", 1, 14));
        this.listNaturalskill.setBackground(UIUtils.Color_BACK);
        this.listNaturalskill.setModel(this.modelNaturalskill);
        this.listNaturalskill.setBounds(210, 20, 113, 78);
        this.add(this.listNaturalskill);
        this.listNaturalskill.addMouseListener(new MouseListener() {
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
                if (PartnerSkillJpanel.this.listNaturalskill.getSelectedValue() != null) {
                    String skillname = (String)PartnerSkillJpanel.this.listNaturalskill.getSelectedValue();
                    Skill skill = UserMessUntil.getskill1(skillname);
                    if (skill != null) {
                        PartnerSkillJpanel.this.skillmsg(skill);
                    }
                    else {
                        PartnerSkillJpanel.this.richMessage.setText(null);
                    }
                }
            }
        });
        (this.jScrollPane2 = new JScrollPane(this.richMessage)).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        this.jScrollPane2.setBounds(211, 123, 124, 165);
        this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
        for (int i = 0; i < 5; ++i) {
            (PartnerSkillJpanel.labSkillName[i] = new JLabel()).setForeground(UIUtils.COLOR_WHITE[0]);
            PartnerSkillJpanel.labSkillName[i].setFont(UIUtils.TEXT_BOLD_FONT);
            PartnerSkillJpanel.labSkillName[i].setOpaque(true);
            PartnerSkillJpanel.labSkillName[i].setBackground(Color.GRAY);
            PartnerSkillJpanel.labSkillName[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel add = (JLabel)e.getSource();
                    String[] txt = PartnerSkillJpanel.labSkill[PartnerSkillJpanel.x].getText().split("：");
                    PartnerSkillJpanel.labSkill[PartnerSkillJpanel.x].setText(txt[0] + "：" + add.getText());
                    PartnerSkillJpanel.AIdataList.set(PartnerSkillJpanel.x, add.getText());
                    for (int i = 0; i < 5; ++i) {
                        PartnerSkillJpanel.labSkillName[i].setVisible(false);
                    }
                }
            });
            this.add(PartnerSkillJpanel.labSkillName[i]);
        }
        this.getLabSkill();
    }
    
    public void showBornSkill(int chooseId) {
        for (int s = 0; s < 5; ++s) {
            PartnerSkillJpanel.labSkillName[s].setText("");
        }
        this.modelNaturalskill.removeAllElements();
        PalData palData = UserMessUntil.getPalData(chooseId);
        PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
        int chooseId2 = mainJpanel.getPalDataChooseId();
        if (chooseId > 0) {
            Pal pidGetPal = mainJpanel.pidGetPal(chooseId2);
            if (pidGetPal != null) {
                PartnerSkillJpanel.AIdataList = new ArrayList<>();
                for (int i = 0; i < 7; ++i) {
                    PartnerSkillJpanel.AIdataList.add("");
                    PartnerSkillJpanel.labSkill[i].setText("第" + (i + 1) + "回合：");
                }
                if (pidGetPal.getPalSkillAI() != null) {
                    for (int i = 0; i < pidGetPal.getPalSkillAI().size(); ++i) {
                        PartnerSkillJpanel.AIdataList.set(i, pidGetPal.getPalSkillAI().get(i));
                        String[] txt = PartnerSkillJpanel.labSkill[i].getText().split("：");
                        PartnerSkillJpanel.labSkill[i].setText(txt[0] + "：" + (String)PartnerSkillJpanel.AIdataList.get(i));
                    }
                }
            }
        }
        if (palData.getSkill() != null && !palData.getSkill().equals("")) {
            String[] petnaturalskill = palData.getSkill().split("\\|");
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                this.modelNaturalskill.add(i, skill.getSkillname());
                PartnerSkillJpanel.labSkillName[i].setText(skill.getSkillname());
            }
        }
    }
    
    public void skillmsg(Skill skill) {
        this.richMessage.setText(null);
        if (skill != null) {
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            PalData palData = UserMessUntil.getPalData(mainJpanel.getPalDataChooseId());
            Pal pal = RoleData.getRoleData().getPal(palData.getPalId());
            String msg = skill.getRemark();
            int id = Integer.parseInt(skill.getSkillid());
            if (id < 1001 || id > 1100) {
                return;
            }
            int level = Integer.parseInt(skill.getSkilllevel());
            double sv = Double.parseDouble(skill.getGrow());
            double mv = Double.parseDouble(skill.getDielectric());
            double value = Double.parseDouble(skill.getValue());
            Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
            int lvl = AnalysisString.lvlint((int)grade);
            double sld = (double)(lvl * 150);
            if (sld < 10000.0) {
                sld = 10000.0;
            }
            else if (sld >= 25000.0) {
                sld = 25000.0;
            }
            String type = (id <= 1005) ? "混乱" : ((id <= 1010) ? "封印" : ((id <= 1015) ? "昏睡" : ((id <= 1020) ? "中毒" : ((id <= 1025) ? "震慑" : ((id <= 1030) ? "力量" : ((id <= 1035) ? "抗性" : ((id <= 1040) ? "加速" : ((id <= 1045) ? "风" : ((id <= 1050) ? "雷" : ((id <= 1055) ? "水" : ((id <= 1060) ? "火" : ((id <= 1065) ? "鬼火" : ((id <= 1070) ? "三尸虫" : ((id <= 1075) ? "遗忘" : ((id <= 1080) ? "smmh" : ((id <= 1085) ? "霹雳" : ((id <= 1090) ? "沧波" : ((id <= 1095) ? "甘霖" : "扶摇"))))))))))))))))));
            msg = msg.replace("|个数|", FBUtil.geshu(level, (int)sld, type) + "");
            if (id <= 1015 || (id >= 1071 && id <= 1075)) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue());
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "7");
            }
            else if (id <= 1020) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0);
                msg = msg.replace("|伤害|", (level > 3) ? "15" : ((level > 1) ? "12.5" : "10"));
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "3");
            }
            else if (id <= 1040 || (id >= 1076 && id <= 1080)) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0);
                msg = msg.replace("|回合|", "7");
                msg = msg.replace("|伤害|", skillhitrate.setScale(2, 4).toString());
            }
            else if (id <= 1065 || id >= 1081) {
                BigDecimal skillhitrate = new BigDecimal((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)lvl);
                msg = msg.replace("|伤害|", skillhitrate.intValue() + "");
            }
            else if (id <= 1070) {
                msg = msg.replace("|伤害|", (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) + "");
                msg = msg.replace("|几率|", (int)(value * 100.0 + (double)(int)(sld / 250.0)) + "");
            }
            msg = msg.replace("|蓝|", (int)(mv * (sld / 25000.0 + 1.0)) + "");
            this.richMessage.setText(msg);
            Dimension d = this.richMessage.computeSize(118);
            this.richMessage.setSize(d);
            this.richMessage.setPreferredSize(d);
        }
    }
    
    public String gongshi(String[] v, int i) {
        if (v.length > i + 1) {
            String[] vs = v[i + 1].split("=");
            if (vs.length > 1 && (vs[1].equals("{公式一}") || vs[1].equals("{公式二}") || vs[1].equals("{公式三}") || vs[1].equals("{公式四}") || vs[1].equals("{公式五}"))) {
                return "1";
            }
        }
        return null;
    }
    
    public static String StringReplace(String vs) {
        Pattern p = Pattern.compile(">(.*?)<");
        Matcher m = p.matcher(vs);
        Pattern pend = Pattern.compile("'#(.*?)'");
        Matcher mend = pend.matcher(vs);
        StringBuffer sb1 = new StringBuffer();
        while (mend.find()) {
            String s = mend.group();
            if (!sb1.toString().equals("")) {
                sb1.append("|" + s.split("'#")[1].split("'")[0]);
            }
            else {
                sb1.append(s.split("'#")[1].split("'")[0]);
            }
        }
        StringBuffer sb2 = new StringBuffer();
        while (m.find()) {
            String s2 = m.group();
            if (!s2.equals("><")) {
                if (!sb2.toString().equals("")) {
                    sb2.append("|" + s2.split(">")[1].split("<")[0]);
                }
                else {
                    sb2.append(s2.split(">")[1].split("<")[0]);
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        String[] v = sb1.toString().split("\\|");
        String[] v2 = sb2.toString().split("\\|");
        for (int i = 0; i < v.length && i < v2.length; ++i) {
            if (!buffer.toString().equals("")) {
                buffer.append("|" + v[i] + "=" + v2[i]);
            }
            else {
                buffer.append(v[i] + "=" + v2[i]);
            }
        }
        return buffer.toString();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B305.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 335, 288, this);
            g.setColor(UIUtils.COLOR_WHITE[0]);
            g.setFont(UIUtils.TEXT_HY17);
            g.drawString("自定义施法方案", 40, 16);
            g.setColor(Color.RED);
            g.setFont(UIUtils.TEXT_FONT2);
            g.drawString("注意：技能方案会循环生效哦！", 5, 253);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S151.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 335, 288, this);
            g.setColor(UIUtils.COLOR_BTNPUTONG[0]);
            g.setFont(UIUtils.TEXT_HY17);
            g.drawString("自定义施法方案", 40, 16);
            g.setColor(Color.RED);
            g.setFont(UIUtils.TEXT_FONT2);
            g.drawString("注意：技能方案会循环生效哦！", 5, 253);
        }
    }
    
    public PartnerBtn getBtnDelete() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnDelete == null) {
                (this.btnDelete = new PartnerBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "清空方案", 7)).setBounds(0, 256, 99, 24);
                this.add(this.btnDelete);
            }
        }
        else if (this.btnDelete == null) {
            (this.btnDelete = new PartnerBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "清空方案", 7)).setBounds(0, 256, 80, 26);
            this.add(this.btnDelete);
        }
        return this.btnDelete;
    }
    
    public void setBtnDelete(PartnerBtn btnDelete) {
        this.btnDelete = btnDelete;
    }
    
    public PartnerBtn getBtnUseGoods() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnUseGoods == null) {
                (this.btnUseGoods = new PartnerBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "保存方案", 6)).setBounds(105, 256, 99, 24);
                this.add(this.btnUseGoods);
            }
        }
        else if (this.btnUseGoods == null) {
            (this.btnUseGoods = new PartnerBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "保存方案", 6)).setBounds(120, 256, 80, 26);
            this.add(this.btnUseGoods);
        }
        return this.btnUseGoods;
    }
    
    public void setBtnUseGoods(PartnerBtn btnUseGoods) {
        this.btnUseGoods = btnUseGoods;
    }
    
    public void getLabSkill() {
        Font font = UIUtils.TEXT_FONT_17;
        for (int i = 0; i < 7; ++i) {
            (PartnerSkillJpanel.labSkill[i] = new JLabel()).setForeground(UIUtils.COLOR_WHITE[0]);
            PartnerSkillJpanel.labSkill[i].setBounds(8, 25 + i * 30, 156, 25);
            PartnerSkillJpanel.labSkill[i].setFont(font);
            PartnerSkillJpanel.labSkill[i].setText("第" + (i + 1) + "回合：");
            this.add(PartnerSkillJpanel.labSkill[i]);
            (PartnerSkillJpanel.btnGetSkill[i] = new PartnerBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "设", 2024 + i)).setBounds(170, 25 + i * 30, 19, 19);
            this.add(PartnerSkillJpanel.btnGetSkill[i]);
        }
    }
    
    public void setLabSkill(JLabel[] labSkill) {
        PartnerSkillJpanel.labSkill = labSkill;
    }
    
    public RichLabel getRichMessage() {
        if (this.richMessage == null) {
            this.add(this.richMessage = new RichLabel());
        }
        return this.richMessage;
    }
    
    public void setRichMessage(RichLabel richMessage) {
        this.richMessage = richMessage;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    static {
        PartnerSkillJpanel.btnGetSkill = new PartnerBtn[7];
        PartnerSkillJpanel.labSkill = new JLabel[7];
        PartnerSkillJpanel.labSkillName = new JLabel[5];
        PartnerSkillJpanel.x = 0;
        PartnerSkillJpanel.AIdataList = new ArrayList<>();
    }
}
