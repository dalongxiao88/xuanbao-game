package org.come.Jpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import com.tool.ModerateTask.TaskRoleData;
import org.come.until.GoodsListFromServerUntil;
import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.tcpimg.RichLabel;
import javax.swing.JScrollPane;
import com.tool.btn.DreamlandTrialBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DreamlandTrialMainJpanel extends JPanel
{
    private int pageNow;
    private int pageMax;
    private int nowLvl;
    private int chooseNum;
    private int moveNum;
    private JLabel labPage;
    private JLabel labLvl;
    private JLabel labNum;
    private DreamlandTrialModelJpanel[] modelJpanelList;
    private DreamlandTrialBtn btnHomePage;
    private DreamlandTrialBtn btnUpPage;
    private DreamlandTrialBtn btnLastPage;
    private DreamlandTrialBtn btnDownPage;
    private DreamlandTrialBtn btnChallenge;
    private DreamlandTrialBtn btnRecruit;
    private DreamlandTrialBtn btnAdd;
    private JScrollPane jscPanel;
    private RichLabel richText;
    private ImageIcon icon;
    private ImageIcon iconChoose;
    private ImageIcon iconAward;
    
    public DreamlandTrialMainJpanel() {
        this.pageNow = -1;
        this.chooseNum = -1;
        this.moveNum = -1;
        this.setPreferredSize(new Dimension(472, 403));
        this.setOpaque(false);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 111);
            offBtn.setBounds(435, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 111);
            offBtn.setBounds(447, 0, 23, 23);
            this.add(offBtn);
        }
        this.getBtnAdd();
        this.getBtnChallenge();
        this.getBtnDownPage();
        this.getBtnHomePage();
        this.getBtnLastPage();
        this.getBtnUpPage();
        this.getLabPage();
        this.getLabNum();
        this.getLabLvl();
        this.getModelJpanelList();
        this.getJscPanel();
        this.showLvlTier(Integer.valueOf(1));
    }
    
    public void showView(String path) {
        this.iconAward = GoodsListFromServerUntil.imgpathAdaptive(path, -1, -1);
        if (this.pageNow == -1) {
            this.showLvlTier(null);
        }
        else {
            this.showLvlTier(Integer.valueOf(this.pageNow));
        }
    }
    
    public void showLvlTier(Integer num) {
        int sumReceive = TaskRoleData.SumReceive(6, 3);
        if (sumReceive == 0) {
            int n = 1;
            this.pageMax = n;
            this.pageNow = n;
        }
        else {
            this.pageMax = sumReceive / 6 + 1;
            this.nowLvl = sumReceive % 6;
            if (num == null) {
                this.pageNow = this.pageMax;
                this.chooseNum = -1;
            }
            else {
                this.pageNow = (int)num;
            }
        }
        this.labNum.setText(String.valueOf(TaskRoleData.SumReceive(6, 4)));
        this.labPage.setText(this.pageNow + "/" + this.pageMax);
        this.labLvl.setText(String.valueOf(this.pageNow));
        for (int i = 0, len = this.modelJpanelList.length; i < len; ++i) {
            if (this.pageNow == this.pageMax) {
                if (i < this.nowLvl) {
                    this.modelJpanelList[i].changeShow(0);
                }
                else if (i == this.nowLvl) {
                    this.modelJpanelList[i].changeShow(1);
                }
                else {
                    this.modelJpanelList[i].changeShow(2);
                }
            }
            else {
                this.modelJpanelList[i].changeShow(0);
            }
        }
        if (this.pageNow == this.pageMax) {
            this.chooseNum = this.nowLvl;
        }
        else {
            this.chooseNum = 5;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S201.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconAward != null) {
                g.drawImage(this.iconAward.getImage(), 47, 44, null);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S201.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconAward != null) {
                g.drawImage(this.iconAward.getImage(), 47, 44, null);
            }
        }
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconChoose == null) {
                this.iconChoose = CutButtonImage.getImage("inkImg/background/S202.png", -1, -1);
            }
            for (int i = 0; i < 6; ++i) {
                if (this.chooseNum == i || this.moveNum == i) {
                    g.drawImage(this.iconChoose.getImage(), 218, 43 + i * 50, null);
                }
            }
        }
        else {
            if (this.iconChoose == null) {
                this.iconChoose = CutButtonImage.getImage("inkImg/background/S202.png", -1, -1);
            }
            for (int i = 0; i < 6; ++i) {
                if (this.chooseNum == i || this.moveNum == i) {
                    g.drawImage(this.iconChoose.getImage(), 230, 43 + i * 56, null);
                }
            }
        }
    }
    
    public int getPageNow() {
        return this.pageNow;
    }
    
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
    
    public int getPageMax() {
        return this.pageMax;
    }
    
    public void setPageMax(int pageMax) {
        this.pageMax = pageMax;
    }
    
    public JLabel getLabPage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labPage == null) {
                (this.labPage = new JLabel("1/20", 0)).setForeground(Color.white);
                this.labPage.setFont(UIUtils.TEXT_FONT1);
                this.labPage.setBounds(317, 342, 42, 16);
                this.add(this.labPage);
            }
        }
        else if (this.labPage == null) {
            (this.labPage = new JLabel("1/1", 0)).setForeground(Color.white);
            this.labPage.setFont(UIUtils.TEXT_FONT1);
            this.labPage.setBounds(101, 342, 42, 16);
            this.add(this.labPage);
        }
        return this.labPage;
    }
    
    public void setLabPage(JLabel labPage) {
        this.labPage = labPage;
    }
    
    public JLabel getLabLvl() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labLvl == null) {
                (this.labLvl = new JLabel("100", 0)).setForeground(Color.yellow);
                this.labLvl.setFont(UIUtils.TEXT_FONT1);
                this.labLvl.setBounds(355, 26, 36, 14);
                this.add(this.labLvl);
            }
        }
        else if (this.labLvl == null) {
            (this.labLvl = new JLabel("100", 0)).setForeground(Color.yellow);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.labLvl.setBounds(355, 24, 36, 14);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public DreamlandTrialBtn getBtnLastPage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnLastPage == null) {
                (this.btnLastPage = new DreamlandTrialBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页", 3, this)).setBounds(382, 342, 34, 17);
                this.add(this.btnLastPage);
            }
        }
        else if (this.btnLastPage == null) {
            (this.btnLastPage = new DreamlandTrialBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页", 3, this)).setBounds(166, 342, 34, 17);
            this.add(this.btnLastPage);
        }
        return this.btnLastPage;
    }
    
    public void setBtnLastPage(DreamlandTrialBtn btnLastPage) {
        this.btnLastPage = btnLastPage;
    }
    
    public DreamlandTrialBtn getBtnChallenge() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnChallenge == null) {
                (this.btnChallenge = new DreamlandTrialBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "开始挑战", 4, this)).setBounds(220, 364, 68, 17);
                this.add(this.btnChallenge);
            }
        }
        else if (this.btnChallenge == null) {
            (this.btnChallenge = new DreamlandTrialBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "开始挑战", 4, this)).setBounds(44, 363, 68, 17);
            this.add(this.btnChallenge);
        }
        return this.btnChallenge;
    }
    
    public void setBtnChallenge(DreamlandTrialBtn btnChallenge) {
        this.btnChallenge = btnChallenge;
    }
    
    public DreamlandTrialBtn getBtnAdd() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnAdd == null) {
                (this.btnAdd = new DreamlandTrialBtn("inkImg/button/16.png", 1, 6, this)).setBounds(359, 365, 18, 18);
                this.add(this.btnAdd);
            }
        }
        else if (this.btnAdd == null) {
            (this.btnAdd = new DreamlandTrialBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 6, this)).setBounds(164, 363, 18, 18);
            this.add(this.btnAdd);
        }
        return this.btnAdd;
    }
    
    public void setBtnAdd(DreamlandTrialBtn btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public DreamlandTrialBtn getBtnHomePage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnHomePage == null) {
                (this.btnHomePage = new DreamlandTrialBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页", 0, this)).setBounds(260, 342, 34, 17);
                this.add(this.btnHomePage);
            }
        }
        else if (this.btnHomePage == null) {
            (this.btnHomePage = new DreamlandTrialBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页", 0, this)).setBounds(44, 342, 34, 17);
            this.add(this.btnHomePage);
        }
        return this.btnHomePage;
    }
    
    public void setBtnHomePage(DreamlandTrialBtn btnHomePage) {
        this.btnHomePage = btnHomePage;
    }
    
    public DreamlandTrialBtn getBtnUpPage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnUpPage == null) {
                (this.btnUpPage = new DreamlandTrialBtn("inkImg/button/10.png", 1, 1, this)).setBounds(296, 342, 18, 18);
                this.add(this.btnUpPage);
            }
        }
        else if (this.btnUpPage == null) {
            (this.btnUpPage = new DreamlandTrialBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 1, this)).setBounds(80, 342, 19, 20);
            this.add(this.btnUpPage);
        }
        return this.btnUpPage;
    }
    
    public void setBtnUpPage(DreamlandTrialBtn btnUpPage) {
        this.btnUpPage = btnUpPage;
    }
    
    public DreamlandTrialBtn getBtnDownPage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnDownPage == null) {
                (this.btnDownPage = new DreamlandTrialBtn("inkImg/button/9.png", 1, 2, this)).setBounds(362, 342, 18, 18);
                this.add(this.btnDownPage);
            }
        }
        else if (this.btnDownPage == null) {
            (this.btnDownPage = new DreamlandTrialBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 2, this)).setBounds(146, 342, 19, 20);
            this.add(this.btnDownPage);
        }
        return this.btnDownPage;
    }
    
    public void setBtnDownPage(DreamlandTrialBtn btnDownPage) {
        this.btnDownPage = btnDownPage;
    }
    
    public DreamlandTrialModelJpanel[] getModelJpanelList() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.modelJpanelList == null) {
                this.modelJpanelList = new DreamlandTrialModelJpanel[6];
                for (int i = 0; i < this.modelJpanelList.length; ++i) {
                    (this.modelJpanelList[i] = new DreamlandTrialModelJpanel()).setBounds(218, 43 + i * 50, 227, 47);
                    this.modelJpanelList[i].addMouseListener(new MListener(i));
                    this.add(this.modelJpanelList[i]);
                }
            }
        }
        else if (this.modelJpanelList == null) {
            this.modelJpanelList = new DreamlandTrialModelJpanel[6];
            for (int i = 0; i < this.modelJpanelList.length; ++i) {
                (this.modelJpanelList[i] = new DreamlandTrialModelJpanel()).setBounds(231, 43 + i * 55, 227, 47);
                this.modelJpanelList[i].addMouseListener(new MListener(i));
                this.add(this.modelJpanelList[i]);
            }
        }
        return this.modelJpanelList;
    }
    
    public void setModelJpanelList(DreamlandTrialModelJpanel[] modelJpanelList) {
        this.modelJpanelList = modelJpanelList;
    }
    
    public JLabel getLabNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labNum == null) {
                (this.labNum = new JLabel("", 0)).setBounds(341, 366, 17, 14);
                this.labNum.setForeground(Color.yellow);
                this.labNum.setFont(UIUtils.TEXT_FONT);
                this.add(this.labNum);
            }
        }
        else if (this.labNum == null) {
            (this.labNum = new JLabel("", 0)).setBounds(135, 364, 17, 14);
            this.labNum.setForeground(Color.yellow);
            this.labNum.setFont(UIUtils.TEXT_FONT);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }
    
    public JScrollPane getJscPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jscPanel == null) {
                (this.jscPanel = new JScrollPane(this.getRichText())).setVerticalScrollBarPolicy(22);
                this.jscPanel.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jscPanel.getVerticalScrollBar().setUnitIncrement(20);
                this.jscPanel.getViewport().setOpaque(false);
                this.jscPanel.setOpaque(false);
                this.jscPanel.setBounds(46, 222, 167, 159);
                this.jscPanel.setBorder(BorderFactory.createEmptyBorder());
                this.jscPanel.setHorizontalScrollBarPolicy(31);
                this.add(this.jscPanel);
            }
        }
        else if (this.jscPanel == null) {
            (this.jscPanel = new JScrollPane(this.getRichText())).setVerticalScrollBarPolicy(22);
            this.jscPanel.getVerticalScrollBar().setUI(new ScrollUI());
            this.jscPanel.getVerticalScrollBar().setUnitIncrement(20);
            this.jscPanel.getViewport().setOpaque(false);
            this.jscPanel.setOpaque(false);
            this.jscPanel.setBounds(46, 35, 167, 259);
            this.jscPanel.setBorder(BorderFactory.createEmptyBorder());
            this.jscPanel.setHorizontalScrollBarPolicy(31);
            this.add(this.jscPanel);
        }
        return this.jscPanel;
    }
    
    public void setJscPanel(JScrollPane jscPanel) {
        this.jscPanel = jscPanel;
    }
    
    public RichLabel getRichText() {
        if (this.richText == null) {
            (this.richText = new RichLabel()).setText("试炼规则：#r1,每天重置奖励次数#r2,每天可重复挑战5次#r3,成功挑战后解锁新关卡#r4,挑战新关卡不消耗挑战次数#r5,每层的奖励唯一，可以选择性击杀");
            Dimension d = this.richText.computeSize(151);
            this.richText.setSize(d);
            this.richText.setPreferredSize(d);
        }
        return this.richText;
    }
    
    public void setRichText(RichLabel richText) {
        this.richText = richText;
    }
    
    public int getChooseNum() {
        return this.chooseNum;
    }
    
    public void setChooseNum(int chooseNum) {
        this.chooseNum = chooseNum;
    }
    
    public int getNowLvl() {
        return this.nowLvl;
    }
    
    public void setNowLvl(int nowLvl) {
        this.nowLvl = nowLvl;
    }
    
    public int getMoveNum() {
        return this.moveNum;
    }
    
    public void setMoveNum(int moveNum) {
        this.moveNum = moveNum;
    }
    
    public ImageIcon getIconChoose() {
        return this.iconChoose;
    }
    
    public void setIconChoose(ImageIcon iconChoose) {
        this.iconChoose = iconChoose;
    }
    
    class MListener implements MouseListener
    {
        private int num;
        
        public MListener(int num) {
            this.num = num;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (DreamlandTrialMainJpanel.this.modelJpanelList[this.num].getType() != 2) {
                DreamlandTrialMainJpanel.this.chooseNum = this.num;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (DreamlandTrialMainJpanel.this.modelJpanelList[this.num].getType() != 2) {
                DreamlandTrialMainJpanel.this.moveNum = this.num;
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (DreamlandTrialMainJpanel.this.modelJpanelList[this.num].getType() != 2) {
                DreamlandTrialMainJpanel.this.moveNum = -1;
            }
        }
    }
}
