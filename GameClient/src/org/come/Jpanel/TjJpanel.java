package org.come.Jpanel;

import java.util.List;
import org.come.until.GsonUtil;
import org.apache.commons.lang.StringUtils;
import come.tool.FightingData.FightingStatistics;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.PK_MixDeal;
import java.awt.Color;
import org.come.Frame.TestSetupJframe;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import come.tool.FightingData.FightingForesee;
import javax.swing.ImageIcon;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JLabel;
import com.tool.btn.SupportBtn;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TjJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private SupportBtn btntop;
    private SupportBtn btnbottom;
    private SupportBtn btntopset;
    private SupportBtn btnbottomset;
    private JLabel labOpen;
    private JLabel labLocking;
    public static boolean closeCk;
    public static int idx;
    private static JLabel labNoC;
    private FormsOnOffBtn offBtn;
    private ImageIcon iconS;
    private ImageIcon iconH;
    private ImageIcon iconD;
    private ImageIcon icon1;
    public static FightingForesee fightingForesee;
    
    public TjJpanel() {
        this.setPreferredSize(new Dimension(296, 198));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        if (this.iconS == null) {
            this.iconS = new ImageIcon("inkImg/background/zdtj.png");
        }
        g.drawImage(this.iconS.getImage(), 0, 0, 296, 198, this);
        try {
            if (TjJpanel.fightingForesee != null) {
                g.setFont(UIUtils.TEXT_FONT78);
                g.setColor(Color.yellow);
                g.drawString(PK_MixDeal.isPK(TjJpanel.fightingForesee.getType()) ? "40回合上限" : "120回合上限", 115, 60);
                g.setFont(UIUtils.TEXT_FONT78);
                g.setColor(Color.orange);
                g.drawString("消耗召唤兽数量", 100, 90);
                g.drawString("消耗灵宝数量", 105, 155);
                String n = "0";
                if (FightingMixDeal.camp == 1) {
                    n = "0";
                }
                else {
                    n = "1";
                }
                if (TjJpanel.fightingForesee.getFightingStatisticsMap() != null) {
                    if (FightingMixDeal.camp == -1) {
                        String yidui = TjJpanel.fightingForesee.getYidui();
                        FightingStatistics fightingStatistics = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get("1");
                        if (!yidui.equals(fightingStatistics.gettName())) {
                            fightingStatistics = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get("2");
                        }
                        g.setColor(Color.WHITE);
                        g.drawString(fightingStatistics.gettName(), 200, 25);
                        g.drawString(fightingStatistics.getPetNum() + "   (" + fightingStatistics.gettName() + ")", this.getWidth() / 2 + 20, 120);
                        g.drawString(fightingStatistics.getLingNum() + "   (" + fightingStatistics.gettName() + ")", this.getWidth() / 2 + 20, 180);
                        g.drawString(":", this.getWidth() / 2, 120);
                        g.drawString(":", this.getWidth() / 2, 180);
                        if (StringUtils.isNotBlank(TjJpanel.fightingForesee.getAlias())) {
                            n = "0";
                        }
                        else if (FightingMixDeal.camp == 1) {
                            n = "2";
                        }
                        else {
                            n = "1";
                        }
                        fightingStatistics = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get("2");
                        if (fightingStatistics != null && yidui.equals(fightingStatistics.gettName())) {
                            fightingStatistics = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get("1");
                        }
                        if (TjJpanel.fightingForesee.getAlias() != null) {
                            fightingStatistics = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get("0");
                        }
                        g.drawString(fightingStatistics.gettName(), 20, 25);
                        g.drawString(fightingStatistics.getPetNum() + "", this.getWidth() / 2 - 20, 120);
                        g.drawString(fightingStatistics.getLingNum() + "", this.getWidth() / 2 - 20, 180);
                        g.drawString("   (" + fightingStatistics.gettName() + ")", 10, 120);
                        g.drawString("   (" + fightingStatistics.gettName() + ")", 10, 180);
                    }
                    else {
                        FightingStatistics fightingStatistics2 = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get((FightingMixDeal.camp == -1) ? "1" : (FightingMixDeal.camp + ""));
                        g.setColor(Color.WHITE);
                        g.drawString(fightingStatistics2.gettName(), 200, 25);
                        g.drawString(fightingStatistics2.getPetNum() + "   (" + fightingStatistics2.gettName() + ")", this.getWidth() / 2 + 20, 120);
                        g.drawString(fightingStatistics2.getLingNum() + "   (" + fightingStatistics2.gettName() + ")", this.getWidth() / 2 + 20, 180);
                        g.drawString(":", this.getWidth() / 2, 120);
                        g.drawString(":", this.getWidth() / 2, 180);
                        if (StringUtils.isNotBlank(TjJpanel.fightingForesee.getAlias())) {
                            n = "0";
                        }
                        else if (FightingMixDeal.camp == 1) {
                            n = "2";
                        }
                        else {
                            n = "1";
                        }
                        fightingStatistics2 = (FightingStatistics)TjJpanel.fightingForesee.getFightingStatisticsMap().get(n);
                        g.drawString(fightingStatistics2.gettName(), 20, 25);
                        g.drawString(fightingStatistics2.getPetNum() + "", this.getWidth() / 2 - 20, 120);
                        g.drawString(fightingStatistics2.getLingNum() + "", this.getWidth() / 2 - 20, 180);
                    }
                }
                else {
                    g.setColor(Color.WHITE);
                    g.drawString(":", this.getWidth() / 2, 120);
                    g.drawString(":", this.getWidth() / 2, 180);
                    g.drawString("0   (我方)", this.getWidth() / 2 + 20, 120);
                    g.drawString("0   (我方)", this.getWidth() / 2 + 20, 180);
                    g.drawString("0", this.getWidth() / 2 - 20, 120);
                    g.drawString("0", this.getWidth() / 2 - 20, 180);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FightingForesee getFightingForesee() {
        return TjJpanel.fightingForesee;
    }
    
    public void setFightingForesee(FightingForesee fightingForesee) {
        TjJpanel.fightingForesee = fightingForesee;
    }
    
    public static void setFightingForesee(String s) {
        FightingForesee fightingForesee = TjJpanel.fightingForesee = (FightingForesee)GsonUtil.getGsonUtil().getgson().fromJson(s, FightingForesee.class);
    }
    
    public void init(List<String> list) {
        this.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.listModel.addElement(list.get(i));
        }
    }
    
    public JLabel getLabNoC() {
        return TjJpanel.labNoC;
    }
    
    public void setLabNoC(JLabel labNoC) {
        TjJpanel.labNoC = labNoC;
    }
    
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    
    public JList<String> getListpet() {
        return this.listpet;
    }
    
    public void setListpet(JList<String> listpet) {
        this.listpet = listpet;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public SupportBtn getBtntop() {
        return this.btntop;
    }
    
    public void setBtntop(SupportBtn btntop) {
        this.btntop = btntop;
    }
    
    public SupportBtn getBtnbottom() {
        return this.btnbottom;
    }
    
    public void setBtnbottom(SupportBtn btnbottom) {
        this.btnbottom = btnbottom;
    }
    
    public SupportBtn getBtntopset() {
        return this.btntopset;
    }
    
    public void setBtntopset(SupportBtn btntopset) {
        this.btntopset = btntopset;
    }
    
    public SupportBtn getBtnbottomset() {
        return this.btnbottomset;
    }
    
    public void setBtnbottomset(SupportBtn btnbottomset) {
        this.btnbottomset = btnbottomset;
    }
    
    public JLabel getLabOpen() {
        return this.labOpen;
    }
    
    public void setLabOpen(JLabel labOpen) {
        this.labOpen = labOpen;
    }
    
    public JLabel getLabLocking() {
        return this.labLocking;
    }
    
    public void setLabLocking(JLabel labLocking) {
        this.labLocking = labLocking;
    }
    
    public static boolean isCloseCk() {
        return TjJpanel.closeCk;
    }
    
    public static void setCloseCk(boolean closeCk) {
        TjJpanel.closeCk = closeCk;
    }
    
    public static int getIdx() {
        return TjJpanel.idx;
    }
    
    public static void setIdx(int idx) {
        TjJpanel.idx = idx;
    }
    
    public FormsOnOffBtn getOffBtn() {
        return this.offBtn;
    }
    
    public void setOffBtn(FormsOnOffBtn offBtn) {
        this.offBtn = offBtn;
    }
    
    public ImageIcon getIconS() {
        return this.iconS;
    }
    
    public void setIconS(ImageIcon iconS) {
        this.iconS = iconS;
    }
    
    public ImageIcon getIconH() {
        return this.iconH;
    }
    
    public void setIconH(ImageIcon iconH) {
        this.iconH = iconH;
    }
    
    public ImageIcon getIconD() {
        return this.iconD;
    }
    
    public void setIconD(ImageIcon iconD) {
        this.iconD = iconD;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    static {
        TjJpanel.closeCk = false;
        TjJpanel.idx = -1;
    }
}
