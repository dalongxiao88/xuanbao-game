package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import org.come.mouslisten.TeamPostMessageListener;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.TeamPanelBtn;
import org.come.until.LimitNumTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamPostMessageJpanel extends JPanel
{
    private JLabel[] labArrRestrains;
    private JLabel[] labArrTask;
    private JLabel[] labArrTeam;
    private LimitNumTextArea sendBelTextArea;
    private TeamPanelBtn btnIssue;
    private String[] strRestrains;
    private String[] strTask;
    private ImageIcon icon;
    
    public TeamPostMessageJpanel() {
        this.setPreferredSize(new Dimension(402, 316));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 19);
        offBtn.setBounds(362, 10, 25, 25);
        this.add(offBtn);
        this.getLabArrRestrains();
        this.getLabArrTask();
        this.getLabArrTeam();
        this.getSendBelTextArea();
        this.getBtnIssue();
    }
    
    public String getChooseRestrainStr(int type) {
        if (type == 1) {
            for (int i = 0; i < this.labArrRestrains.length; ++i) {
                if (this.labArrRestrains[i].getIcon() != null) {
                    return this.getStrRestrains()[i];
                }
            }
        }
        else {
            for (int i = 0; i < this.labArrTask.length; ++i) {
                if (this.labArrTask[i].getIcon() != null) {
                    return this.getStrTask()[i];
                }
            }
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/B210.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 399, 311, null);
    }
    
    public JLabel[] getLabArrRestrains() {
        if (this.labArrRestrains == null) {
            this.labArrRestrains = new JLabel[7];
            for (int i = 0; i < this.labArrRestrains.length; ++i) {
                (this.labArrRestrains[i] = new JLabel()).addMouseListener(new TeamPostMessageListener(1, this, i));
                this.labArrRestrains[i].setBounds(124 + i % 4 * 64, 31 + i / 4 * 24, 18, 18);
                this.add(this.labArrRestrains[i]);
            }
            this.labArrRestrains[0].setIcon(CutButtonImage.getImage("inkImg/button/B214.png", -1, -1));
        }
        return this.labArrRestrains;
    }
    
    public void setLabArrRestrains(JLabel[] labArrRestrains) {
        this.labArrRestrains = labArrRestrains;
    }
    
    public JLabel[] getLabArrTask() {
        if (this.labArrTask == null) {
            this.labArrTask = new JLabel[3];
            for (int i = 0; i < this.labArrTask.length; ++i) {
                (this.labArrTask[i] = new JLabel()).setBounds(127 + i * 64, 83, 13, 13);
                this.labArrTask[i].addMouseListener(new TeamPostMessageListener(2, this, i));
                this.add(this.labArrTask[i]);
            }
            this.labArrTask[0].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
        }
        return this.labArrTask;
    }
    
    public void setLabArrTask(JLabel[] labArrTask) {
        this.labArrTask = labArrTask;
    }
    
    public JLabel[] getLabArrTeam() {
        if (this.labArrTeam == null) {
            this.labArrTeam = new JLabel[5];
            for (int i = 0; i < this.labArrTeam.length; ++i) {
                (this.labArrTeam[i] = new JLabel("不限", 0) {
                    private ImageIcon icon;
                    
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (this.getIcon() == null) {
                            if (this.icon == null) {
                                this.icon = CutButtonImage.getImage("inkImg/background/S158.png", -1, -1);
                            }
                            g.drawImage(this.icon.getImage(), 1, 1, 39, 39, null);
                        }
                        super.paintComponent(g);
                    }
                }).setFont(UIUtils.TEXT_FONT1);
                this.labArrTeam[i].setForeground(Color.white);
                this.labArrTeam[i].addMouseListener(new TeamPostMessageListener(3, this, i));
                this.labArrTeam[i].setBounds(129 + i * 50, 111, 41, 41);
                this.add(this.labArrTeam[i]);
            }
        }
        return this.labArrTeam;
    }
    
    public void setLabArrTeam(JLabel[] labArrTeam) {
        this.labArrTeam = labArrTeam;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public LimitNumTextArea getSendBelTextArea() {
        if (this.sendBelTextArea == null) {
            (this.sendBelTextArea = new LimitNumTextArea(50)).setLineWrap(true);
            this.sendBelTextArea.setOpaque(false);
            this.sendBelTextArea.setForeground(Color.white);
            this.sendBelTextArea.setBounds(128, 165, 241, 91);
            this.sendBelTextArea.setCaretColor(Color.white);
            this.add(this.sendBelTextArea);
        }
        return this.sendBelTextArea;
    }
    
    public void setSendBelTextArea(LimitNumTextArea sendBelTextArea) {
        this.sendBelTextArea = sendBelTextArea;
    }
    
    public TeamPanelBtn getBtnIssue() {
        if (this.btnIssue == null) {
            (this.btnIssue = new TeamPanelBtn("inkImg/button1/B22.png", 1, "立即发布", UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, this)).setBounds(140, 268, 99, 24);
            this.add(this.btnIssue);
        }
        return this.btnIssue;
    }
    
    public void setBtnIssue(TeamPanelBtn btnIssue) {
        this.btnIssue = btnIssue;
    }
    
    public String[] getStrRestrains() {
        if (this.strRestrains == null) {
            this.strRestrains = new String[] { "不限", "克金", "克木", "克水", "克火", "克土", "无属性" };
        }
        return this.strRestrains;
    }
    
    public void setStrRestrains(String[] strRestrains) {
        this.strRestrains = strRestrains;
    }
    
    public String[] getStrTask() {
        if (this.strTask == null) {
            this.strTask = new String[] { "日常", "PK", "其他" };
        }
        return this.strTask;
    }
    
    public void setStrTask(String[] strTask) {
        this.strTask = strTask;
    }
}
