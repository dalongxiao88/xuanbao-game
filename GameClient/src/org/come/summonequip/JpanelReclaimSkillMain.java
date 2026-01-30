package org.come.summonequip;

import javax.swing.BorderFactory;
import org.cbg.until.TraslationDemoScrollBarUI;
import java.awt.Color;
import java.awt.Graphics;
import org.come.bean.Skill;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.GoodsMsgJpanel;
import com.tool.tcpimg.RichLabel;
import org.come.until.UserMessUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.QualityClBean;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelReclaimSkillMain extends JPanel
{
    private JLabel leftName;
    private JLabel rightName;
    private JLabel[] leftDegree;
    private JLabel[] rightDegree;
    private JScrollPane leftPane;
    private JScrollPane rightPane;
    private BtnSummonEquipMain retainBtn;
    private BtnSummonEquipMain replaceBtn;
    private QualityClBean qualityClBean;
    private ImageIcon iconBack;
    
    public JpanelReclaimSkillMain() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(478, 316));
            this.setOpaque(false);
            this.setLayout(null);
            this.getLeftName();
            this.getRightName();
            this.getLeftDegree();
            this.getRightDegree();
            this.getLeftPane();
            this.getRightPane();
            this.getRetainBtn();
            this.getReplaceBtn();
        }
        else {
            this.setPreferredSize(new Dimension(450, 300));
            this.setOpaque(false);
            this.setLayout(null);
            this.getLeftName();
            this.getRightName();
            this.getLeftDegree();
            this.getRightDegree();
            this.getLeftPane();
            this.getRightPane();
            this.getRetainBtn();
            this.getReplaceBtn();
        }
    }
    
    public void washSkillViewgetMessage(String valueMessage, int type) {
        String[] split = valueMessage.split("&");
        double parseDouble = Double.parseDouble(split[2]);
        int max = (int)parseDouble;
        double num = parseDouble - (double)max;
        Skill skill = UserMessUntil.getSkillId(split[1]);
        if (type == 0) {
            this.leftName.setText(skill.getSkillname() + "(" + parseDouble + ")");
            RichLabel view = (RichLabel)this.leftPane.getViewport().getView();
            long lvl = JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[3]));
            view = new RichLabel(GoodsMsgJpanel.SummonSkillRemark("#G" + skill.getRemark(), skill, split[2], lvl + ""), UIUtils.TEXT_FONT2);
            Dimension d = view.computeSize(145);
            view.setSize(d);
            view.setPreferredSize(d);
            this.leftPane.setViewportView(view);
            for (int i = 0; i < this.leftDegree.length; ++i) {
                if (i + 1 <= max) {
                    this.leftDegree[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                }
                else {
                    this.leftDegree[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_无_w15,h16.png", 15, 16));
                }
            }
            if (max == 5) {
                return;
            }
            if (num < 0.2) {
                this.leftDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_无_w15,h16.png", 15, 16));
            }
            else if (num > 0.8) {
                this.leftDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
            }
            else {
                this.leftDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_半_w15,h16.png", 15, 16));
            }
        }
        else {
            this.rightName.setText(skill.getSkillname() + "(" + parseDouble + ")");
            RichLabel view = (RichLabel)this.rightPane.getViewport().getView();
            long lvl = JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[3]));
            view = new RichLabel(GoodsMsgJpanel.SummonSkillRemark("#G" + skill.getRemark(), skill, split[2], lvl + ""), UIUtils.TEXT_FONT2);
            Dimension d = view.computeSize(145);
            view.setSize(d);
            view.setPreferredSize(d);
            this.rightPane.setViewportView(view);
            for (int i = 0; i < this.rightDegree.length; ++i) {
                if (i + 1 <= max) {
                    this.rightDegree[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                }
                else {
                    this.rightDegree[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_无_w15,h16.png", 15, 16));
                }
            }
            if (max == 5) {
                return;
            }
            if (num < 0.2) {
                this.rightDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_无_w15,h16.png", 15, 16));
            }
            else if (num > 0.8) {
                this.rightDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
            }
            else {
                this.rightDegree[max].setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_半_w15,h16.png", 15, 16));
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background/S135.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 478, 316, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString("觉醒度", 60, 108);
            g.drawString("觉醒度", 262, 108);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/觉醒_觉醒技能_底板_w450,h300.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 450, 300, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString("觉醒度", 40, 118);
            g.drawString("觉醒度", 236, 118);
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel getLeftName() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.leftName == null) {
                (this.leftName = new JLabel()).setBounds(60, 65, 164, 22);
                this.leftName.setForeground(Color.white);
                this.leftName.setFont(UIUtils.TEXT_FONT1);
                this.leftName.setOpaque(false);
                this.add(this.leftName);
            }
            return this.leftName;
        }
        else {
            if (this.leftName == null) {
                (this.leftName = new JLabel()).setBounds(39, 75, 164, 22);
                this.leftName.setForeground(Color.white);
                this.leftName.setFont(UIUtils.TEXT_FONT1);
                this.leftName.setOpaque(false);
                this.add(this.leftName);
            }
            return this.leftName;
        }
    }
    
    public void setLeftName(JLabel leftName) {
        this.leftName = leftName;
    }
    
    public JLabel getRightName() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.rightName == null) {
                (this.rightName = new JLabel()).setBounds(262, 65, 164, 22);
                this.rightName.setForeground(Color.white);
                this.rightName.setFont(UIUtils.TEXT_FONT1);
                this.rightName.setOpaque(false);
                this.add(this.rightName);
            }
            return this.rightName;
        }
        else {
            if (this.rightName == null) {
                (this.rightName = new JLabel()).setBounds(236, 75, 164, 22);
                this.rightName.setForeground(Color.white);
                this.rightName.setFont(UIUtils.TEXT_FONT1);
                this.rightName.setOpaque(false);
                this.add(this.rightName);
            }
            return this.rightName;
        }
    }
    
    public void setRightName(JLabel rightName) {
        this.rightName = rightName;
    }
    
    public JLabel[] getLeftDegree() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.leftDegree == null) {
                this.leftDegree = new JLabel[5];
                for (int i = 0; i < this.leftDegree.length; ++i) {
                    (this.leftDegree[i] = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                    this.leftDegree[i].setBounds(105 + i * 22, 95, 15, 16);
                    this.add(this.leftDegree[i]);
                }
            }
            return this.leftDegree;
        }
        else {
            if (this.leftDegree == null) {
                this.leftDegree = new JLabel[5];
                for (int i = 0; i < this.leftDegree.length; ++i) {
                    (this.leftDegree[i] = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                    this.leftDegree[i].setBounds(87 + i * 22, 106, 15, 16);
                    this.add(this.leftDegree[i]);
                }
            }
            return this.leftDegree;
        }
    }
    
    public void setLeftDegree(JLabel[] leftDegree) {
        this.leftDegree = leftDegree;
    }
    
    public JLabel[] getRightDegree() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.rightDegree == null) {
                this.rightDegree = new JLabel[5];
                for (int i = 0; i < this.rightDegree.length; ++i) {
                    (this.rightDegree[i] = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                    this.rightDegree[i].setBounds(308 + i * 22, 95, 15, 16);
                    this.add(this.rightDegree[i]);
                }
            }
            return this.rightDegree;
        }
        else {
            if (this.rightDegree == null) {
                this.rightDegree = new JLabel[5];
                for (int i = 0; i < this.rightDegree.length; ++i) {
                    (this.rightDegree[i] = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_星_满_w15,h16.png", 15, 16));
                    this.rightDegree[i].setBounds(284 + i * 22, 106, 15, 16);
                    this.add(this.rightDegree[i]);
                }
            }
            return this.rightDegree;
        }
    }
    
    public void setRightDegree(JLabel[] rightDegree) {
        this.rightDegree = rightDegree;
    }
    
    public JScrollPane getLeftPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.leftPane == null) {
                (this.leftPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.leftPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.leftPane));
                this.leftPane.getVerticalScrollBar().setUnitIncrement(30);
                this.leftPane.getViewport().setOpaque(false);
                this.leftPane.setOpaque(false);
                this.leftPane.setBounds(60, 116, 178, 104);
                this.leftPane.setBorder(BorderFactory.createEmptyBorder());
                this.leftPane.setHorizontalScrollBarPolicy(31);
                this.add(this.leftPane);
            }
            return this.leftPane;
        }
        else {
            if (this.leftPane == null) {
                (this.leftPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.leftPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.leftPane));
                this.leftPane.getVerticalScrollBar().setUnitIncrement(30);
                this.leftPane.getViewport().setOpaque(false);
                this.leftPane.setOpaque(false);
                this.leftPane.setBounds(39, 129, 166, 94);
                this.leftPane.setBorder(BorderFactory.createEmptyBorder());
                this.leftPane.setHorizontalScrollBarPolicy(31);
                this.add(this.leftPane);
            }
            return this.leftPane;
        }
    }
    
    public void setLeftPane(JScrollPane leftPane) {
        this.leftPane = leftPane;
    }
    
    public JScrollPane getRightPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.rightPane == null) {
                (this.rightPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.rightPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.rightPane));
                this.rightPane.getVerticalScrollBar().setUnitIncrement(30);
                this.rightPane.getViewport().setOpaque(false);
                this.rightPane.setOpaque(false);
                this.rightPane.setBounds(261, 116, 178, 104);
                this.rightPane.setBorder(BorderFactory.createEmptyBorder());
                this.rightPane.setHorizontalScrollBarPolicy(31);
                this.add(this.rightPane);
            }
            return this.rightPane;
        }
        else {
            if (this.rightPane == null) {
                (this.rightPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.rightPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.rightPane));
                this.rightPane.getVerticalScrollBar().setUnitIncrement(30);
                this.rightPane.getViewport().setOpaque(false);
                this.rightPane.setOpaque(false);
                this.rightPane.setBounds(236, 129, 166, 94);
                this.rightPane.setBorder(BorderFactory.createEmptyBorder());
                this.rightPane.setHorizontalScrollBarPolicy(31);
                this.add(this.rightPane);
            }
            return this.rightPane;
        }
    }
    
    public void setRightPane(JScrollPane rightPane) {
        this.rightPane = rightPane;
    }
    
    public BtnSummonEquipMain getRetainBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.retainBtn == null) {
                (this.retainBtn = new BtnSummonEquipMain("inkImg/button/B59.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "保 留", 40, this)).setBounds(104, 238, 68, 26);
                this.add(this.retainBtn);
            }
            return this.retainBtn;
        }
        else {
            if (this.retainBtn == null) {
                (this.retainBtn = new BtnSummonEquipMain("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "保 留", 40, this)).setBounds(79, 251, 80, 26);
                this.add(this.retainBtn);
            }
            return this.retainBtn;
        }
    }
    
    public void setRetainBtn(BtnSummonEquipMain retainBtn) {
        this.retainBtn = retainBtn;
    }
    
    public BtnSummonEquipMain getReplaceBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.replaceBtn == null) {
                (this.replaceBtn = new BtnSummonEquipMain("inkImg/button/B59.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "替 换", 41, this)).setBounds(316, 238, 68, 26);
                this.add(this.replaceBtn);
            }
            return this.replaceBtn;
        }
        else {
            if (this.replaceBtn == null) {
                (this.replaceBtn = new BtnSummonEquipMain("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "替 换", 41, this)).setBounds(276, 251, 80, 26);
                this.add(this.replaceBtn);
            }
            return this.replaceBtn;
        }
    }
    
    public void setReplaceBtn(BtnSummonEquipMain replaceBtn) {
        this.replaceBtn = replaceBtn;
    }
    
    public QualityClBean getQualityClBean() {
        return this.qualityClBean;
    }
    
    public void setQualityClBean(QualityClBean qualityClBean) {
        this.qualityClBean = qualityClBean;
    }
}
