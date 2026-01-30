package org.come.Jpanel;

import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.tcp.SpriteFactory;
import org.come.until.AnalysisString;
import com.tool.tcpimg.UIUtils;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import org.come.bean.OneArenaNotes;
import org.come.Frame.PartnerArenaWarJframe;
import org.come.until.FormsManagement;
import org.come.bean.OneArenaBean;
import com.tool.ModerateTask.TaskRoleData;
import org.come.bean.OneArenaRole;
import java.util.List;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.awt.Color;
import com.tool.tcp.NewPart;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import com.tool.btn.PartnerArenaBtn;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class PartnerArenaMainPanel extends JPanel
{
    private JScrollPane scrollPane;
    private JList<PartnerArenaModelPanel> partnerUnitJpanels;
    private JLabel labRank;
    private JLabel labNum;
    private PartnerArenaBtn btnAdd;
    private PartnerArenaBtn btnWar;
    private PartnerArenaBtn btnExchange;
    private PartnerArenaBtn btnRule;
    private PartnerArenaBtn btnGet;
    private PartnerArenaBtn btnrefinery;
    private JLabel[] luckyDrawImg;
    private ImageIcon icon;
    static Sprite tcp;
    private NewPart part;
    private int size1;
    private int size2;
    private int lvlNum;
    private Color turnColor;
    private String name;
    private String lvl;
    
    public PartnerArenaMainPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(531, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 5);
            offBtn.setBounds(494, 10, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.getLabNum();
            this.getBtnAdd();
            this.getBtnWar();
            this.getLabRank();
            this.getBtnGet();
            this.getBtnrefinery();
            this.getBtnExchange();
        }
        else {
            this.setPreferredSize(new Dimension(531, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 5);
            offBtn.setBounds(500, 5, 25, 25);
            this.add(offBtn);
            this.getScrollPane();
            this.getLabNum();
            this.getBtnAdd();
            this.getBtnWar();
            this.getLabRank();
            this.getBtnGet();
            this.getBtnrefinery();
            this.getBtnExchange();
        }
    }
    
    public void showRankView(List<OneArenaRole> list, int place) {
        if (list != null) {
            int size = list.size();
            int componentCount = this.partnerUnitJpanels.getComponentCount();
            int type = (place == 0) ? 0 : ((place <= 10) ? 1 : -1);
            for (int i = 0; i < size; ++i) {
                if (componentCount < size) {
                    PartnerArenaModelPanel panel = new PartnerArenaModelPanel();
                    panel.setBounds(0, 50 * componentCount, 220, 49);
                    this.partnerUnitJpanels.add(panel);
                    ++componentCount;
                }
                else if (componentCount > size) {
                    this.partnerUnitJpanels.remove(componentCount);
                    --componentCount;
                }
                PartnerArenaModelPanel component2 = (PartnerArenaModelPanel)this.partnerUnitJpanels.getComponent(i);
                component2.showView((OneArenaRole)list.get(i), type);
            }
            this.partnerUnitJpanels.setPreferredSize(new Dimension(220, 50 * size));
            this.refreshWarNum();
            if (place != 0) {
                this.labRank.setText(String.valueOf(place));
            }
        }
    }
    
    public void refreshWarNum() {
        int sumReceive = TaskRoleData.SumReceive(3, 4);
        this.labNum.setText(String.valueOf(sumReceive));
    }
    
    public void showView(OneArenaBean arenaBean) {
        if (arenaBean == null) {
            return;
        }
        List<OneArenaRole> arenaList = arenaBean.getArenaList();
        if (arenaList != null) {
            this.showRankView(arenaBean.getArenaList(), arenaBean.getPlace());
            if (!FormsManagement.getframe(5).isVisible()) {
                FormsManagement.showForm(5);
            }
        }
        List<OneArenaNotes> notesList = arenaBean.getNotesList();
        if (notesList != null) {
            PartnerArenaWarJframe.getPartnerArenaWarJframe().getPartnerArenaWarPanel().showView(notesList);
            if (!FormsManagement.getframe(80).isVisible()) {
                FormsManagement.showForm(80);
            }
        }
        OneArenaNotes notes = arenaBean.getNotes();
        if (notes != null && FormsManagement.getInternalForm2(80) != null && FormsManagement.getframe(80).isVisible()) {
            PartnerArenaWarJframe.getPartnerArenaWarJframe().getPartnerArenaWarPanel().refreshOneNoets(notes);
        }
    }
    
    public void drawNameAndLvl(Graphics g, int x, int y) {
        if (!ImageMixDeal.userimg.getName().equals(this.name)) {
            this.name = ImageMixDeal.userimg.getName();
            this.size1 = g.getFontMetrics().stringWidth(this.name) / 2;
        }
        if (this.lvlNum != (int)ImageMixDeal.userimg.getRoleShow().getGrade()) {
            this.lvlNum = (int)ImageMixDeal.userimg.getRoleShow().getGrade();
            this.turnColor = UIUtils.getcolor(ImageMixDeal.userimg.getRoleShow().getTurnAround());
            this.lvl = AnalysisString.lvl(this.lvlNum) + "级";
            this.size2 = g.getFontMetrics().stringWidth(this.lvl) / 2;
        }
        g.setFont(UIUtils.TEXT_FONT22);
        g.setColor(this.turnColor);
        g.drawString(this.name, x - 10 - this.size1, y);
        g.drawString(this.lvl, x - this.size2, y + 18);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S179.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 531, 375, null);
            if (ImageMixDeal.userimg != null) {
                if (this.part == null) {
                    this.part = SpriteFactory.createPart(ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString(), 2, 1, null);
                }
                else {
                    this.part = SpriteFactory.setPart(this.part, 1, ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString());
                }
                this.part.draw(g, 155, 265, 4, ImageMixDeal.userimg.getTime());
                this.drawNameAndLvl(g, 138, 100);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S179.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 531, 375, null);
            if (ImageMixDeal.userimg != null) {
                if (this.part == null) {
                    this.part = SpriteFactory.createPart(ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString(), 2, 1, null);
                }
                else {
                    this.part = SpriteFactory.setPart(this.part, 1, ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString());
                }
                this.part.draw(g, 135, 300, 4, ImageMixDeal.userimg.getTime());
                PartnerArenaMainPanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                PartnerArenaMainPanel.tcp.draw(g, 135, 300);
                this.drawNameAndLvl(g, 105, 90);
            }
        }
    }
    
    public JScrollPane getScrollPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPane == null) {
                (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.scrollPane.getViewport().setView(this.getPartnerUnitJpanels());
                this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
                this.scrollPane.getViewport().setOpaque(false);
                this.scrollPane.setOpaque(false);
                this.scrollPane.setBounds(274, 44, 236, 306);
                this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
                this.scrollPane.setHorizontalScrollBarPolicy(31);
                this.add(this.scrollPane);
            }
        }
        else if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getViewport().setView(this.getPartnerUnitJpanels());
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(264, 60, 236, 280);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public JList<PartnerArenaModelPanel> getPartnerUnitJpanels() {
        if (this.partnerUnitJpanels == null) {
            (this.partnerUnitJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.partnerUnitJpanels.setSelectionForeground(Color.white);
            this.partnerUnitJpanels.setForeground(Color.white);
            this.partnerUnitJpanels.setFont(UIUtils.TEXT_HY16);
            this.partnerUnitJpanels.removeAll();
            this.partnerUnitJpanels.setBackground(UIUtils.Color_BACK);
            this.partnerUnitJpanels.setOpaque(false);
        }
        return this.partnerUnitJpanels;
    }
    
    public void setPartnerUnitJpanels(JList<PartnerArenaModelPanel> partnerUnitJpanels) {
        this.partnerUnitJpanels = partnerUnitJpanels;
    }
    
    public JLabel getLabRank() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labRank == null) {
                (this.labRank = new JLabel()).setBounds(170, 46, 80, 15);
                this.labRank.setForeground(Color.white);
                this.labRank.setFont(UIUtils.TEXT_FONT3);
                this.add(this.labRank);
            }
        }
        else if (this.labRank == null) {
            (this.labRank = new JLabel()).setBounds(110, 59, 80, 15);
            this.labRank.setForeground(Color.white);
            this.labRank.setFont(UIUtils.TEXT_FONT22);
            this.add(this.labRank);
        }
        return this.labRank;
    }
    
    public void setLabRank(JLabel labRank) {
        this.labRank = labRank;
    }
    
    public JLabel getLabNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labNum == null) {
                (this.labNum = new JLabel()).setHorizontalAlignment(0);
                this.labNum.setBounds(195, 289, 33, 15);
                this.labNum.setForeground(Color.red);
                this.labNum.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labNum);
            }
        }
        else if (this.labNum == null) {
            (this.labNum = new JLabel()).setHorizontalAlignment(0);
            this.labNum.setBounds(122, 113, 33, 15);
            this.labNum.setForeground(Color.red);
            this.labNum.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }
    
    public PartnerArenaBtn getBtnAdd() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnAdd == null) {
                (this.btnAdd = new PartnerArenaBtn("inkImg/button/B35.png", 1, 0, this)).setBounds(231, 289, 17, 17);
                this.add(this.btnAdd);
            }
        }
        else if (this.btnAdd == null) {
            (this.btnAdd = new PartnerArenaBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 0, this)).setBounds(148, 112, 17, 17);
            this.add(this.btnAdd);
        }
        return this.btnAdd;
    }
    
    public void setBtnAdd(PartnerArenaBtn btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    public PartnerArenaBtn getBtnWar() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnWar == null) {
                (this.btnWar = new PartnerArenaBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "战报", UIUtils.TEXT_FONT, 1, this)).setBounds(84, 312, 34, 17);
                this.add(this.btnWar);
            }
        }
        else if (this.btnWar == null) {
            (this.btnWar = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "战报", UIUtils.TEXT_FONT, 1, this)).setBounds(59, 322, 34, 17);
            this.add(this.btnWar);
        }
        return this.btnWar;
    }
    
    public void setBtnWar(PartnerArenaBtn btnWar) {
        this.btnWar = btnWar;
    }
    
    public PartnerArenaBtn getBtnExchange() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnExchange == null) {
                (this.btnExchange = new PartnerArenaBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "兑换", UIUtils.TEXT_FONT, 2, this)).setBounds(124, 312, 34, 17);
                this.add(this.btnExchange);
            }
        }
        else if (this.btnExchange == null) {
            (this.btnExchange = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "兑换", UIUtils.TEXT_FONT, 2, this)).setBounds(99, 322, 34, 17);
            this.add(this.btnExchange);
        }
        return this.btnExchange;
    }
    
    public void setBtnExchange(PartnerArenaBtn btnExchange) {
        this.btnExchange = btnExchange;
    }
    
    public PartnerArenaBtn getBtnRule() {
        return this.btnRule;
    }
    
    public void setBtnRule(PartnerArenaBtn btnRule) {
        this.btnRule = btnRule;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public PartnerArenaBtn getBtnGet() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnGet == null) {
                (this.btnGet = new PartnerArenaBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "领取", UIUtils.TEXT_FONT, 5, this)).setBounds(164, 312, 34, 17);
                this.add(this.btnGet);
            }
        }
        else if (this.btnGet == null) {
            (this.btnGet = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "领取", UIUtils.TEXT_FONT, 5, this)).setBounds(139, 322, 34, 17);
            this.add(this.btnGet);
        }
        return this.btnGet;
    }
    
    public void setBtnGet(PartnerArenaBtn btnGet) {
        this.btnGet = btnGet;
    }
    
    public PartnerArenaBtn getBtnrefinery() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnrefinery == null) {
                (this.btnrefinery = new PartnerArenaBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "炼化", UIUtils.TEXT_FONT, 6, this)).setBounds(204, 312, 34, 17);
                this.add(this.btnrefinery);
            }
        }
        else if (this.btnrefinery == null) {
            (this.btnrefinery = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "炼化", UIUtils.TEXT_FONT, 6, this)).setBounds(179, 322, 34, 17);
            this.add(this.btnrefinery);
        }
        return this.btnrefinery;
    }
    
    public void setBtnrefinery(PartnerArenaBtn btnrefinery) {
        this.btnrefinery = btnrefinery;
    }
    
    static {
        PartnerArenaMainPanel.tcp = SpriteFactory.VloadSprite("resource/mouse/tx/tx14.tcp", null);
    }
}
