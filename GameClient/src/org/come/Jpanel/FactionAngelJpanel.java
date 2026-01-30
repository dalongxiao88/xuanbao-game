package org.come.Jpanel;

import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import org.come.bean.LoginResult;
import org.come.Frame.FactionAngelJframe;
import com.tool.role.RoleData;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.tool.btn.FactionBtn;
import javax.swing.JPanel;

public class FactionAngelJpanel extends JPanel
{
    private FactionBtn btnMenuSmall;
    private FactionBtn btnMenuBig;
    private FactionBtn btnPractice;
    private FactionBtn btnWash;
    private FactionBtn btnExchange;
    private FactionBtn btnYes;
    private JLabel labLvl;
    private JScrollPane scrollPane;
    private JList<FactionAngelModelJpanel> listFactionJpanel;
    private int typeLvl;
    private int menuType;
    private int typeLvlNow;
    private int typeLvlResidue;
    private ImageIcon icon;
    
    public FactionAngelJpanel() {
        this.typeLvl = 30;
        this.menuType = 0;
        this.typeLvlNow = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(382, 397));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 54);
            offBtn.setBounds(345, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(382, 397));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 54);
            offBtn.setBounds(357, 0, 25, 25);
            this.add(offBtn);
        }
        this.getBtnMenuBig();
        this.getBtnMenuSmall();
        this.getScrollPane();
        this.getBtnPractice();
        this.getLabLvl();
        this.getBtnWash();
        this.getBtnExchange();
        this.getBtnYes();
    }
    
    public void changeMenuShow(int type) {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.menuType != type) {
                    this.menuType = type;
                    if (this.menuType == 1) {
                        this.btnMenuSmall.setIcons(CutButtonImage.cuts("inkImg/button/B287.png"));
                        this.btnMenuBig.setIcons(CutButtonImage.cuts("inkImg/button/B284.png"));
                        this.typeLvl = 30;
                    }
                    else if (this.menuType == 2) {
                        this.btnMenuSmall.setIcons(CutButtonImage.cuts("inkImg/button/B286.png"));
                        this.btnMenuBig.setIcons(CutButtonImage.cuts("inkImg/button/B285.png"));
                        this.typeLvl = 60;
                    }
                }
            }
            else if (this.menuType != type) {
                this.menuType = type;
                if (this.menuType == 1) {
                    this.btnMenuSmall.setIcons(CutButtonImage.cuts("img/xy2uiimg/C287.png"));
                    this.btnMenuBig.setIcons(CutButtonImage.cuts("img/xy2uiimg/B286.png"));
                    this.typeLvl = 30;
                }
                else if (this.menuType == 2) {
                    this.btnMenuSmall.setIcons(CutButtonImage.cuts("img/xy2uiimg/C288.png"));
                    this.btnMenuBig.setIcons(CutButtonImage.cuts("img/xy2uiimg/B285.png"));
                    this.typeLvl = 60;
                }
            }
            this.showMenuMessage();
        }
        catch (Exception ex) {}
    }
    
    public void showMenuMessage() {
        this.listFactionJpanel.removeAll();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.typeLvlResidue = 0;
        if (this.menuType == 1) {
            String[] resistance = loginResult.getResistance("X");
            int extraPointInt = loginResult.getExtraPointInt("X");
            FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().setTypeLvlNow(extraPointInt);
            for (int i = 0; i < 13; ++i) {
                int componentCount = this.listFactionJpanel.getComponentCount();
                FactionAngelModelJpanel factionAngelModelJpanel = new FactionAngelModelJpanel();
                factionAngelModelJpanel.showModel(i, resistance, this);
                factionAngelModelJpanel.setBounds(0, i * 38, 324, 34);
                this.listFactionJpanel.add(factionAngelModelJpanel);
            }
            this.listFactionJpanel.setPreferredSize(new Dimension(324, 38 * this.listFactionJpanel.getComponentCount()));
            this.typeLvlResidue = extraPointInt - this.typeLvlResidue;
            this.labLvl.setText(extraPointInt + "/30");
        }
        else if (this.menuType == 2) {
            String[] resistance = loginResult.getResistance("D");
            int extraPointInt = loginResult.getExtraPointInt("D");
            FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().setTypeLvlNow(extraPointInt);
            for (int i = 0; i <= 17; ++i) {
                int componentCount = this.listFactionJpanel.getComponentCount();
                FactionAngelModelJpanel factionAngelModelJpanel = new FactionAngelModelJpanel();
                factionAngelModelJpanel.showModel(i + 13, resistance, this);
                factionAngelModelJpanel.setBounds(0, i * 38, 324, 34);
                this.listFactionJpanel.add(factionAngelModelJpanel);
            }
            this.typeLvlResidue = extraPointInt - this.typeLvlResidue;
            this.labLvl.setText(extraPointInt + "/60");
            this.listFactionJpanel.setPreferredSize(new Dimension(324, 38 * this.listFactionJpanel.getComponentCount()));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S172.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 382, 397, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S172.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 382, 397, null);
        }
    }
    
    public FactionBtn getBtnMenuSmall() {
        if (this.btnMenuSmall == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuSmall = new FactionBtn("inkImg/button/B287.png", 1, 16, this)).setBounds(51, 30, 96, 26);
            }
            else {
                (this.btnMenuSmall = new FactionBtn("img/xy2uiimg/C287.png", 1, 16, this)).setBounds(51, 30, 75, 26);
            }
            this.add(this.btnMenuSmall);
        }
        return this.btnMenuSmall;
    }
    
    public void setBtnMenuSmall(FactionBtn btnMenuSmall) {
        this.btnMenuSmall = btnMenuSmall;
    }
    
    public FactionBtn getBtnMenuBig() {
        if (this.btnMenuBig == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuBig = new FactionBtn("inkImg/button/B284.png", 1, 17, this)).setBounds(149, 30, 96, 26);
            }
            else {
                (this.btnMenuBig = new FactionBtn("img/xy2uiimg/B286.png", 1, 17, this)).setBounds(129, 30, 75, 26);
            }
            this.add(this.btnMenuBig);
        }
        return this.btnMenuBig;
    }
    
    public void setBtnMenuBig(FactionBtn btnMenuBig) {
        this.btnMenuBig = btnMenuBig;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public FactionBtn getBtnPractice() {
        if (this.btnPractice == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnPractice = new FactionBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "修炼", UIUtils.TEXT_FONT, 20, this)).setBounds(176, 352, 34, 17);
            }
            else {
                (this.btnPractice = new FactionBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "修炼", UIUtils.TEXT_FONT, 20, this)).setBounds(176, 352, 34, 17);
            }
            this.add(this.btnPractice);
        }
        return this.btnPractice;
    }
    
    public void setBtnPractice(FactionBtn btnPractice) {
        this.btnPractice = btnPractice;
    }
    
    public FactionBtn getBtnWash() {
        if (this.btnWash == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnWash = new FactionBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "洗点", UIUtils.TEXT_FONT, 21, this)).setBounds(216, 352, 34, 17);
            }
            else {
                (this.btnWash = new FactionBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "洗点", UIUtils.TEXT_FONT, 21, this)).setBounds(216, 352, 34, 17);
            }
            this.add(this.btnWash);
        }
        return this.btnWash;
    }
    
    public void setBtnWash(FactionBtn btnWash) {
        this.btnWash = btnWash;
    }
    
    public FactionBtn getBtnExchange() {
        if (this.btnExchange == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnExchange = new FactionBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, "兑换", UIUtils.TEXT_FONT, 22, this)).setBounds(252, 352, 34, 17);
            }
            else {
                (this.btnExchange = new FactionBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "兑换", UIUtils.TEXT_FONT, 22, this)).setBounds(252, 352, 34, 17);
            }
            this.add(this.btnExchange);
        }
        return this.btnExchange;
    }
    
    public void setBtnExchange(FactionBtn btnExchange) {
        this.btnExchange = btnExchange;
    }
    
    public FactionBtn getBtnYes() {
        if (this.btnYes == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnYes = new FactionBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BLACK, "确定", UIUtils.TEXT_HY16, 23, this)).setBounds(300, 348, 60, 26);
            }
            else {
                (this.btnYes = new FactionBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "确定", UIUtils.TEXT_HY88, 23, this)).setBounds(300, 348, 60, 26);
            }
            this.add(this.btnYes);
        }
        return this.btnYes;
    }
    
    public void setBtnYes(FactionBtn btnYes) {
        this.btnYes = btnYes;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel("0/" + this.typeLvl)).setOpaque(false);
            this.labLvl.setBounds(131, 353, 81, 16);
            this.labLvl.setForeground(Color.white);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane(this.getListFactionJpanel())).setBounds(53, 58, 307, 276);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setVerticalScrollBarPolicy(22);
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.scrollPane.setOpaque(false);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public JList<FactionAngelModelJpanel> getListFactionJpanel() {
        if (this.listFactionJpanel == null) {
            (this.listFactionJpanel = new JList<>()).setOpaque(false);
            this.listFactionJpanel.removeAll();
            for (int i = 0; i < 18; ++i) {
                FactionAngelModelJpanel factionAngelModelJpanel = new FactionAngelModelJpanel();
                factionAngelModelJpanel.setBounds(0, i * 44, 324, 34);
                this.listFactionJpanel.add(factionAngelModelJpanel);
            }
            this.listFactionJpanel.setPreferredSize(new Dimension(324, 680));
        }
        return this.listFactionJpanel;
    }
    
    public void setListFactionJpanel(JList<FactionAngelModelJpanel> listFactionJpanel) {
        this.listFactionJpanel = listFactionJpanel;
    }
    
    public int getTypeLvl() {
        return this.typeLvl;
    }
    
    public void setTypeLvl(int typeLvl) {
        this.typeLvl = typeLvl;
    }
    
    public int getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
    
    public int getTypeLvlNow() {
        return this.typeLvlNow;
    }
    
    public void setTypeLvlNow(int typeLvlNow) {
        this.typeLvlNow = typeLvlNow;
    }
    
    public int getTypeLvlResidue() {
        return this.typeLvlResidue;
    }
    
    public void setTypeLvlResidue(int typeLvlResidue) {
        this.typeLvlResidue = typeLvlResidue;
    }
}
