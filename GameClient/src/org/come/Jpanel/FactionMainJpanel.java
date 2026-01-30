package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import com.tool.btn.FactionBtn;
import javax.swing.JPanel;

public class FactionMainJpanel extends JPanel
{
    private FactionBtn btnMenuPandect;
    private FactionBtn btnMenuMember;
    private FactionBtn btnMenuWar;
    private FactionCardJpanel factionCardJpanel;
    
    public FactionMainJpanel() {
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 48);
            offBtn.setBounds(625, 10, 25, 25);
            this.add(offBtn);
            this.getBtnMenuMember();
            this.getBtnMenuPandect();
            this.getBtnMenuWar();
            (this.factionCardJpanel = new FactionCardJpanel()).setBounds(0, 0, 662, 475);
            this.add(this.factionCardJpanel);
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 48);
            offBtn.setBounds(637, 1, 25, 25);
            this.add(offBtn);
            this.getBtnMenuMember();
            this.getBtnMenuPandect();
            this.getBtnMenuWar();
            (this.factionCardJpanel = new FactionCardJpanel()).setBounds(0, 0, 662, 475);
            this.add(this.factionCardJpanel);
        }
    }
    
    public FactionBtn getBtnMenuPandect() {
        if (this.btnMenuPandect == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuPandect = new FactionBtn("inkImg/button1/K16.png", 1, 1, this)).setBounds(56, 23, 95, 33);
            }
            else {
                (this.btnMenuPandect = new FactionBtn("img/xy2uiimg/B270.png", 1, 1, this)).setBounds(56, 28, 80, 30);
            }
            this.add(this.btnMenuPandect);
        }
        return this.btnMenuPandect;
    }
    
    public void setBtnMenuPandect(FactionBtn btnMenuPandect) {
        this.btnMenuPandect = btnMenuPandect;
    }
    
    public FactionBtn getBtnMenuMember() {
        if (this.btnMenuMember == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuMember = new FactionBtn("inkImg/button1/K17.png", 1, 2, this)).setBounds(158, 23, 95, 33);
            }
            else {
                (this.btnMenuMember = new FactionBtn("img/xy2uiimg/B267.png", 1, 2, this)).setBounds(136, 28, 80, 30);
            }
            this.add(this.btnMenuMember);
        }
        return this.btnMenuMember;
    }
    
    public void setBtnMenuMember(FactionBtn btnMenuMember) {
        this.btnMenuMember = btnMenuMember;
    }
    
    public FactionBtn getBtnMenuWar() {
        if (this.btnMenuWar == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuWar = new FactionBtn("inkImg/button1/K19.png", 1, 3, this)).setBounds(260, 23, 95, 33);
            }
            else {
                (this.btnMenuWar = new FactionBtn("img/xy2uiimg/B269.png", 1, 3, this)).setBounds(216, 28, 80, 30);
            }
            this.add(this.btnMenuWar);
        }
        return this.btnMenuWar;
    }
    
    public void setBtnMenuWar(FactionBtn btnMenuWar) {
        this.btnMenuWar = btnMenuWar;
    }
    
    public FactionCardJpanel getFactionCardJpanel() {
        return this.factionCardJpanel;
    }
    
    public void setFactionCardJpanel(FactionCardJpanel factionCardJpanel) {
        this.factionCardJpanel = factionCardJpanel;
    }
}
