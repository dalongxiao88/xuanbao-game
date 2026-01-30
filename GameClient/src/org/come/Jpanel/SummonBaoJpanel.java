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
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.math.BigDecimal;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.PetJlistChoseMouslisten;
import java.util.List;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.TypeState;
import com.tool.role.RoleLingFa;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.tool.tcp.NewPart;
import com.tool.btn.PetPanelBtn;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.come.model.Lingbao;
import javax.swing.JPanel;

public class SummonBaoJpanel extends JPanel
{
    private Lingbao choseLingBao;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private JScrollPane jScrollPane;
    private Color fontcolor;
    private JLabel[] skillLabs;
    private JLabel[] skillIcons;
    private static JLabel labName;
    private static JLabel labName1;
    private static JLabel labLvl;
    private static JLabel labQuality;
    private static JLabel labQihe;
    private static JLabel labactive;
    private static JLabel labSpeed;
    private PetPanelBtn btnRolequality;
    private PetPanelBtn btnSummon;
    private int warNum;
    public static NewPart part;
    public static ImageIcon icon;
    
    public SummonBaoJpanel() {
        this.warNum = -1;
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
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (SummonBaoJpanel.this.warNum == index) {
                    this.setForeground(Color.orange);
                }
                else if (FightingMixDeal.State != 0) {
                    Lingbao[] lingBaos = RoleLingFa.getRoleLingFa().getLingBaos();
                    Fightingimage fightingimage = FightingMixDeal.getdata(0);
                    if (fightingimage != null && fightingimage.getFightingManData() != null) {
                        List<TypeState> data = fightingimage.getFightingManData().cxxx("灵宝");
                        if (data != null && data.size() > 0) {
                            Lingbao lingBao = lingBaos[index];
                            for (int i = 0; i < data.size(); ++i) {
                                if (((TypeState)data.get(i)).getState() != 0 && lingBao.getBaoid().intValue() == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                                    this.setForeground(Color.red);
                                }
                            }
                        }
                    }
                }
                return this;
            }
        };
        this.listpet.setCellRenderer(new SummonRenderer(1));
        this.listpet.addMouseListener(new PetJlistChoseMouslisten(this.listpet, this));
        this.listpet.addMouseMotionListener(new PetJlistChoseMouslisten(this.listpet, this));
        (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        SummonBaoJpanel.labName1 = new JLabel("", 0);
        SummonBaoJpanel.labName = new JLabel();
        SummonBaoJpanel.labLvl = new JLabel();
        SummonBaoJpanel.labQuality = new JLabel();
        SummonBaoJpanel.labQihe = new JLabel();
        SummonBaoJpanel.labactive = new JLabel();
        SummonBaoJpanel.labSpeed = new JLabel();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(469, 480));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 710);
            offBtn.setBounds(432, 10, 25, 25);
            this.add(offBtn);
            this.jScrollPane.setBounds(50, 81, 189, 218);
            SummonBaoJpanel.labName1.setBounds(260, 80, 160, 15);
            SummonBaoJpanel.labName1.setForeground(UIUtils.COLOR_NAME);
            SummonBaoJpanel.labName1.setFont(UIUtils.TEXT_FONT64);
            Font font = new Font("微软雅黑", 1, 14);
            SummonBaoJpanel.labName.setBounds(94, 335, 99, 15);
            SummonBaoJpanel.labName.setForeground(Color.WHITE);
            SummonBaoJpanel.labName.setFont(font);
            SummonBaoJpanel.labLvl.setBounds(94, 364, 99, 15);
            SummonBaoJpanel.labLvl.setForeground(Color.WHITE);
            SummonBaoJpanel.labLvl.setFont(font);
            SummonBaoJpanel.labQuality.setBounds(94, 394, 99, 15);
            SummonBaoJpanel.labQuality.setForeground(Color.WHITE);
            SummonBaoJpanel.labQuality.setFont(font);
            SummonBaoJpanel.labQihe.setBounds(296, 335, 135, 15);
            SummonBaoJpanel.labQihe.setForeground(Color.WHITE);
            SummonBaoJpanel.labQihe.setFont(font);
            SummonBaoJpanel.labactive.setBounds(296, 365, 99, 15);
            SummonBaoJpanel.labactive.setForeground(Color.WHITE);
            SummonBaoJpanel.labactive.setFont(font);
            SummonBaoJpanel.labSpeed.setBounds(296, 394, 135, 15);
            SummonBaoJpanel.labSpeed.setForeground(Color.WHITE);
            SummonBaoJpanel.labSpeed.setFont(font);
            (this.btnRolequality = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "属性", Integer.valueOf(10), this)).setBounds(110, 304, 59, 24);
            (this.btnSummon = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "召唤", Integer.valueOf(11), this)).setBounds(214, 436, 68, 26);
        }
        else {
            this.setPreferredSize(new Dimension(440, 507));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 710);
            offBtn.setBounds(415, 0, 25, 25);
            this.add(offBtn);
            this.jScrollPane.setBounds(23, 94, 185, 216);
            SummonBaoJpanel.labName1.setBounds(212, 94, 160, 15);
            SummonBaoJpanel.labName1.setForeground(UIUtils.COLOR_NAME);
            SummonBaoJpanel.labName1.setFont(UIUtils.TEXT_FONT64);
            Font font = new Font("微软雅黑", 1, 14);
            SummonBaoJpanel.labName.setBounds(67, 349, 99, 15);
            SummonBaoJpanel.labName.setForeground(Color.WHITE);
            SummonBaoJpanel.labName.setFont(font);
            SummonBaoJpanel.labLvl.setBounds(67, 378, 99, 15);
            SummonBaoJpanel.labLvl.setForeground(Color.WHITE);
            SummonBaoJpanel.labLvl.setFont(font);
            SummonBaoJpanel.labQuality.setBounds(67, 408, 99, 15);
            SummonBaoJpanel.labQuality.setForeground(Color.WHITE);
            SummonBaoJpanel.labQuality.setFont(font);
            SummonBaoJpanel.labQihe.setBounds(269, 349, 135, 15);
            SummonBaoJpanel.labQihe.setForeground(Color.WHITE);
            SummonBaoJpanel.labQihe.setFont(font);
            SummonBaoJpanel.labactive.setBounds(269, 379, 99, 15);
            SummonBaoJpanel.labactive.setForeground(Color.WHITE);
            SummonBaoJpanel.labactive.setFont(font);
            SummonBaoJpanel.labSpeed.setBounds(269, 408, 135, 15);
            SummonBaoJpanel.labSpeed.setForeground(Color.WHITE);
            SummonBaoJpanel.labSpeed.setFont(font);
            (this.btnRolequality = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "属性", Integer.valueOf(10), this)).setBounds(83, 317, 59, 25);
            (this.btnSummon = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "召唤", Integer.valueOf(11), this)).setBounds(187, 449, 59, 25);
        }
        this.add(this.jScrollPane);
        this.add(SummonBaoJpanel.labName1);
        this.add(SummonBaoJpanel.labName);
        this.add(SummonBaoJpanel.labLvl);
        this.add(SummonBaoJpanel.labQuality);
        this.add(SummonBaoJpanel.labQihe);
        this.add(SummonBaoJpanel.labactive);
        this.add(SummonBaoJpanel.labSpeed);
        this.add(this.btnRolequality);
        this.add(this.btnSummon);
    }
    
    public void showListModel(Lingbao[] lingBaos, BigDecimal equipBaoId) {
        this.listModel.removeAllElements();
        for (int i = 0; i < lingBaos.length; ++i) {
            Lingbao lingBao = lingBaos[i];
            if (lingBao != null) {
                this.listModel.addElement(lingBao.getBaoname());
                if (equipBaoId != null && equipBaoId.compareTo(lingBao.getBaoid()) == 0) {
                    this.listModel.set(i, lingBao.getBaoname());
                    this.showBaoValue(lingBao);
                }
            }
        }
    }
    
    public void showBaoValue(Lingbao lingbao) {
        this.choseLingBao = lingbao;
        List<String> skills = new ArrayList<>();
        if (lingbao != null) {
            SummonBaoJpanel.labName.setText(lingbao.getBaoname());
            SummonBaoJpanel.labName1.setText(lingbao.getBaoname());
            SummonBaoJpanel.labLvl.setText(lingbao.getLingbaolvl() + "级");
            SummonBaoJpanel.labQuality.setText(lingbao.getBaoquality());
            SummonBaoJpanel.labQihe.setText(lingbao.getLingbaoqihe() + "");
            if (lingbao.getBaoactive() != null) {
                SummonBaoJpanel.labactive.setText(lingbao.getBaoactive() + "");
            }
            else {
                SummonBaoJpanel.labactive.setText("0");
            }
            SummonBaoJpanel.labSpeed.setText(lingbao.getBaospeed() + "");
            if (StringUtils.isNotBlank(lingbao.getSkills())) {
                skills.addAll(Arrays.asList(lingbao.getSkills().split("\\|")));
            }
            SummonBaoJpanel.part = lingbao.getPart();
        }
        this.showSkill(skills);
    }
    
    public void showSkill(List<String> skills) {
        if (this.skillIcons != null) {
            for (int i = 0; i < this.skillIcons.length; ++i) {
                if (this.skillIcons != null) {
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
                String val = (String)skills.get(i);
                this.skillIcons[i] = new JLabel();
                this.skillLabs[i] = new JLabel();
                if (MyIsif.getStyle().equals("水墨")) {
                    this.skillIcons[i].setBounds(407, 66 + i * 26, 20, 20);
                    this.skillIcons[i].setIcon(CutButtonImage.getImage("img/lingbao/skill/" + val.split("=")[0] + ".png", 20, 20));
                    this.skillLabs[i].setBounds(405, 64 + i * 26, 24, 24);
                    this.skillLabs[i].setIcon(CutButtonImage.getImage("inkImg/button1/s602.png", 24, 24));
                }
                else {
                    this.skillIcons[i].setBounds(380, 79 + i * 26, 20, 20);
                    this.skillIcons[i].setIcon(CutButtonImage.getImage("img/lingbao/skill/" + val.split("=")[0] + ".png", 20, 20));
                    this.skillLabs[i].setBounds(377, 76 + i * 26, 26, 26);
                    this.skillLabs[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/88_png.xy2uiimg.skill_tip.png", 26, 26));
                }
                this.skillIcons[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        if (val != null) {
                            PathPoint point = GameJpanel.getGameJpanel().mousepath();
                            MsgJframe.getJframe().getJapnel().zsskill(val, point.getX(), point.getY());
                        }
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
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            SummonBaoJpanel.icon = new ImageIcon("inkImg/background1/B601.png");
            g.drawImage(SummonBaoJpanel.icon.getImage(), 0, 0, this);
            if (SummonBaoJpanel.part != null) {
                Graphics2D graphics2d = (Graphics2D)g;
                SummonBaoJpanel.part.draw(graphics2d, 312, 285, 0, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            SummonBaoJpanel.icon = new ImageIcon("inkImg/hongmu1/B601.png");
            g.drawImage(SummonBaoJpanel.icon.getImage(), 0, 0, this);
            if (SummonBaoJpanel.part != null) {
                Graphics2D graphics2d = (Graphics2D)g;
                SummonBaoJpanel.part.draw(graphics2d, 290, 300, 0, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public Lingbao getChoseLingBao() {
        return this.choseLingBao;
    }
    
    public void setChoseLingBao(Lingbao choseLingBao) {
        this.choseLingBao = choseLingBao;
    }
}
