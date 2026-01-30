package org.come.Jpanel;

import org.come.until.UserData;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.GrayFilter;
import java.awt.Image;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Color;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.bean.Skill;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import org.come.Frame.RoleSkillJframe;
import javax.swing.JPanel;

public class RoleSkillJpanel extends JPanel
{
    private RoleSkillJframe roleSkillJframe;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList<String> listspell;
    private JList<String> listspellctro;
    private DefaultListModel<String> modelspell;
    private DefaultListModel<String> modelspellctro;
    public List<Skill> skills;
    private StringBuilder builder;
    private JLabel skilljLabel;
    final ImageIcon imageIcon;
    public static JLabel labgundong;
    private ImageIcon icon;
    
    public RoleSkillJpanel(RoleSkillJframe roleSkillJframe) throws Exception {
        this.skills = new ArrayList<>();
        this.imageIcon = new ImageIcon("img/123_副本.png");
        if (MyIsif.getStyle().equals("水墨")) {
            RoleSkillJpanel.labgundong = new JLabel(new ImageIcon("inkImg/button/23.png"));
        }
        else {
            RoleSkillJpanel.labgundong = new JLabel(CutButtonImage.getImage("img/xy2uiimg/gundongtiao_副本.png", 17, 336));
        }
        this.builder = new StringBuilder();
        this.roleSkillJframe = roleSkillJframe;
        this.setPreferredSize(new Dimension(441, 395));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 9);
        offBtn.setBounds(421, 0, 23, 23);
        this.add(offBtn);
        this.modelspell = new DefaultListModel<>();
        RoleSkillJpanel.labgundong.setBounds(185, 81, 17, 271);
        this.add(RoleSkillJpanel.labgundong);
        (this.listspell = new JList<String>() {
            Image image = RoleSkillJpanel.this.imageIcon.getImage();
            Image grayImage = GrayFilter.createDisabledImage(this.image);
            
            {
                this.setOpaque(false);
            }
            
            @Override
            public void paint(Graphics g) {
                g.drawImage(this.grayImage, 0, 0, this);
                super.paint(g);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listspell.setSelectionForeground(Color.WHITE);
        this.listspell.setForeground(Color.WHITE);
        this.listspell.setFont(new Font("宋体", 1, 16));
        this.listspell.setOpaque(false);
        this.listspell.setBackground(new Color(0, 0, 0, 0));
        this.listspell.setModel(this.modelspell);
        this.listspell.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                int tmp = 0;
                String stmp = "";
                int[] index = RoleSkillJpanel.this.listspell.getSelectedIndices();
                if (index.length > 0) {
                    for (int i = 0; i < index.length; ++i) {
                        tmp = index[i];
                    }
                    RoleSkillJpanel.this.Skilltest(tmp);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(9);
                }
            }
        });
        (this.jScrollPane1 = new JScrollPane(this.listspell)).setVerticalScrollBarPolicy(22);
        this.jScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane1.getViewport().setOpaque(false);
        this.jScrollPane1.setOpaque(false);
        this.jScrollPane1.setBounds(25, 62, 177, 308);
        this.jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane1);
        String gdt = "";
        if (MyIsif.getStyle().equals("水墨")) {
            gdt = "inkImg/button/23.png";
        }
        else {
            gdt = "img/xy2uiimg/gundongtiao_副本_副本.png";
        }
        this.modelspellctro = new DefaultListModel<>();
        (this.skilljLabel = new JLabel()).setBounds(240, 10, 150, 271);
        this.add(this.skilljLabel);
        if (this.modelspellctro.size() <= 14) {
            JLabel labgundong = new JLabel(new ImageIcon(gdt));
            labgundong.setBounds(393, 80, 17, 271);
            this.add(labgundong);
        }
        (this.listspellctro = new JList<String>() {
            Image image = RoleSkillJpanel.this.imageIcon.getImage();
            Image grayImage = GrayFilter.createDisabledImage(this.image);
            
            {
                this.setOpaque(false);
            }
            
            @Override
            public void paint(Graphics g) {
                g.drawImage(this.grayImage, 0, 0, this);
                super.paint(g);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listspellctro.setSelectionForeground(Color.YELLOW);
        this.listspellctro.setForeground(Color.YELLOW);
        this.listspellctro.setFont(new Font("宋体", 0, 14));
        this.listspellctro.setOpaque(false);
        this.listspellctro.setBackground(new Color(0, 0, 0, 0));
        this.listspellctro.setModel(this.modelspellctro);
        this.listspellctro.addMouseListener(new MouseListener() {
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
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(9);
                }
            }
        });
        (this.jScrollPane2 = new JScrollPane(this.listspellctro)).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        this.jScrollPane2.setBounds(238, 61, 172, 308);
        this.jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/91_png.xy2uiimg.skill.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 441, 395, this);
        if (this.modelspell.size() >= 15) {
            this.remove(RoleSkillJpanel.labgundong);
        }
    }
    
    public void addskill(String v, int i) {
    }
    
    public void Skilltest(int i) {
        this.builder.delete(0, this.builder.length());
        this.builder.append("<html>");
        this.builder.append(StringReplace((Skill)this.skills.get(i), AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade())));
        this.builder.append("</html>");
        this.skilljLabel.setText(this.builder.toString());
    }
    
    public static String StringReplace(Skill RoleSkill, int renlvl) {
        Pattern p = Pattern.compile("】(.*?)<");
        Matcher m = p.matcher(RoleSkill.getRemark());
        Pattern pend = Pattern.compile("</font></p><p><font color='#FFFFFF'>(.*?)</font></p>");
        Matcher mend = pend.matcher(RoleSkill.getRemark());
        String end = null;
        String[] CharReplace = new String[4];
        while (mend.find()) {
            end = "</font></p><p><font color='#FFFFFF'>" + mend.group(1) + "</font></p>";
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int j = 0;
        while (m.find()) {
            if (i == 6) {
                m.appendReplacement(sb, "】" + CharReplace[j] + end);
            }
            else if (i > 2) {
                m.appendReplacement(sb, "】" + CharReplace[j] + "<");
                ++j;
            }
            else if (i == 2) {
                CharReplace = Analysis(m.group(1), RoleSkill, renlvl);
            }
            ++i;
        }
        return sb.toString();
    }
    
    public static String[] Analysis(String type, Skill skill, int renlvl) {
        String[] CharReplace = new String[4];
        CharReplace[0] = geshu(skill, type);
        double lvl = Double.parseDouble(skill.getSkilllevel());
        double sld = (double)(int)skill.getSkilled();
        double sv = Double.parseDouble(skill.getGrow());
        double mv = Double.parseDouble(skill.getDielectric());
        double value = Double.parseDouble(skill.getValue());
        if (type.equals("混乱") || type.equals("封印") || type.equals("昏睡") || type.equals("遗忘系魔法")) {
            CharReplace[1] = (int)(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue()) + "%";
            CharReplace[2] = "7";
            CharReplace[3] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("中毒")) {
            CharReplace[1] = "5";
            CharReplace[2] = UserData.xiaoshu3((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0) + "%";
            CharReplace[3] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("震慑")) {
            CharReplace[1] = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0 + "";
            CharReplace[2] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("力量增益") || type.equals("抗性增益") || type.equals("加速增益")) {
            CharReplace[1] = (double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0 + "";
            CharReplace[2] = "7";
            CharReplace[3] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("风系魔法") || type.equals("雷系魔法") || type.equals("水系魔法") || type.equals("火系魔法")) {
            CharReplace[1] = (int)((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)renlvl) + "";
            CharReplace[2] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("鬼火系魔法")) {
            CharReplace[1] = (int)((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)renlvl) + "";
            CharReplace[2] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("三尸虫")) {
            CharReplace[1] = (double)(int)((value * (double)renlvl + sld * sv / 1000.0) * 1000.0) / 10.0 + "回血程度" + (value * 100.0 + (double)(int)(sld / 250.0)) + "%";
            CharReplace[2] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        else if (type.equals("遗忘系魔法")) {
            CharReplace[1] = (int)(value * (double)renlvl + sld * sv / 1000.0) + "";
            CharReplace[2] = (int)(mv * (sld / 25000.0 + 1.0)) + "";
        }
        return CharReplace;
    }
    
    public static String geshu(Skill skill, String type) {
        int lvl = Integer.parseInt(skill.getSkilllevel());
        if (type.equals("鬼火系魔法") || type.equals("火系魔法") || type.equals("水系魔法") || type.equals("雷系魔法") || type.equals("风系魔法")) {
            return xian((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("震慑")) {
            return moz((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("力量增益") || type.equals("抗性增益") || type.equals("加速增益")) {
            return moq((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("中毒") || type.equals("封印") || type.equals("昏睡") || type.equals("遗忘系魔法")) {
            return renq((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("混乱")) {
            return renh((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("混乱")) {
            return renh((int)skill.getSkilled(), lvl) + "";
        }
        if (type.equals("三尸虫")) {
            return guis((int)skill.getSkilled(), lvl) + "";
        }
        return xian((int)skill.getSkilled(), lvl) + "";
    }
    
    public static int xian(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 720) {
                return 2;
            }
            if (ed < 5215) {
                return 3;
            }
            if (ed < 16610) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 558) {
                return 3;
            }
            if (ed < 5621) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public static int moz(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 426) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11868) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int moq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 214) {
                return 2;
            }
            if (ed < 2155) {
                return 3;
            }
            if (ed < 8324) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 117) {
                return 3;
            }
            if (ed < 1174) {
                return 4;
            }
            if (ed < 4533) {
                return 5;
            }
            if (ed < 11826) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int renq(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 428) {
                return 2;
            }
            if (ed < 3098) {
                return 3;
            }
            if (ed < 9866) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 226) {
                return 3;
            }
            if (ed < 1638) {
                return 4;
            }
            if (ed < 5215) {
                return 5;
            }
            if (ed < 11864) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int renh(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 1362) {
                return 2;
            }
            if (ed < 9866) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 973) {
                return 3;
            }
            if (ed < 7051) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public static int guiv(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 2200) {
                return 2;
            }
            if (ed < 4600) {
                return 3;
            }
            if (ed < 9600) {
                return 4;
            }
            return 5;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 4600) {
                return 4;
            }
            if (ed < 9600) {
                return 5;
            }
            if (ed < 12000) {
                return 6;
            }
            return 7;
        }
        else {
            return 1;
        }
    }
    
    public static int guis(int ed, int lvl) {
        if (lvl == 3) {
            if (ed < 5200) {
                return 2;
            }
            if (ed < 6800) {
                return 3;
            }
            return 4;
        }
        else if (lvl == 5) {
            if (ed < 2200) {
                return 3;
            }
            if (ed < 6800) {
                return 4;
            }
            return 5;
        }
        else {
            return 1;
        }
    }
    
    public RoleSkillJframe getRoleSkillJframe() {
        return this.roleSkillJframe;
    }
    
    public void setRoleSkillJframe(RoleSkillJframe roleSkillJframe) {
        this.roleSkillJframe = roleSkillJframe;
    }
    
    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }
    
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public JScrollPane getjScrollPane2() {
        return this.jScrollPane2;
    }
    
    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }
    
    public JList<String> getListspell() {
        return this.listspell;
    }
    
    public void setListspell(JList<String> listspell) {
        this.listspell = listspell;
    }
    
    public JList<String> getListspellctro() {
        return this.listspellctro;
    }
    
    public void setListspellctro(JList<String> listspellctro) {
        this.listspellctro = listspellctro;
    }
    
    public DefaultListModel<String> getModelspell() {
        return this.modelspell;
    }
    
    public void setModelspell(DefaultListModel<String> modelspell) {
        this.modelspell = modelspell;
    }
    
    public DefaultListModel<String> getModelspellctro() {
        return this.modelspellctro;
    }
    
    public void setModelspellctro(DefaultListModel<String> modelspellctro) {
        this.modelspellctro = modelspellctro;
    }
    
    public StringBuilder getBuilder() {
        return this.builder;
    }
    
    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }
    
    public JLabel getSkilljLabel() {
        return this.skilljLabel;
    }
    
    public void setSkilljLabel(JLabel skilljLabel) {
        this.skilljLabel = skilljLabel;
    }
    
    public List<Skill> getSkills() {
        return this.skills;
    }
    
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
