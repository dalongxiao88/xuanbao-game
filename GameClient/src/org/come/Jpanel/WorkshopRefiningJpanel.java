package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import com.tool.btn.WorkshopRefiningBtn;
import javax.swing.JPanel;

public class WorkshopRefiningJpanel extends JPanel
{
    private WorkshopRefiningCardJpanel cardJpanel;
    private WorkshopRefiningBtn btnEqui;
    private WorkshopRefiningBtn btnAcc;
    private WorkshopRefiningBtn btnRefiners;
    private WorkshopRefiningBtn btnSetsynthesis;
    private WorkshopRefiningBtn btnMenuGem;
    private WorkshopRefiningBtn btnGemRefinery;
    private ImageIcon icon;
    
    public WorkshopRefiningJpanel() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(558, 516));
            this.cardJpanel = new WorkshopRefiningCardJpanel();
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 61);
            offBtn.setBounds(525, 10, 25, 25);
            this.add(offBtn);
            (this.btnEqui = new WorkshopRefiningBtn("inkImg/button1/K30.png", 1, 1, this.cardJpanel, this)).setBounds(55, 14, 95, 33);
            this.add(this.btnEqui);
            if (!configure.getLzjskg().equals("3")) {
                (this.btnAcc = new WorkshopRefiningBtn("inkImg/button1/K31.png", 1, 2, this.cardJpanel, this)).setBounds(152, 14, 95, 33);
                this.add(this.btnAcc);
                (this.btnRefiners = new WorkshopRefiningBtn("inkImg/button1/K33.png", 1, 3, this.cardJpanel, this)).setBounds(249, 14, 95, 33);
                this.add(this.btnRefiners);
                (this.btnSetsynthesis = new WorkshopRefiningBtn("inkImg/button1/K35.png", 1, 4, this.cardJpanel, this)).setBounds(346, 14, 95, 33);
                this.add(this.btnSetsynthesis);
                if (configure.getBsqhkg().equals("开")) {
                    (this.btnGemRefinery = new WorkshopRefiningBtn("inkImg/button1/K47.png", 1, 5, this.cardJpanel, this)).setBounds(443, 14, 95, 33);
                    this.add(this.btnGemRefinery);
                }
            }
            this.cardJpanel.setBounds(0, 0, 558, 516);
            this.add(this.cardJpanel);
        }
        else {
            this.setPreferredSize(new Dimension(536, 542));
            this.cardJpanel = new WorkshopRefiningCardJpanel();
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 61);
            offBtn.setBounds(510, 0, 23, 23);
            this.add(offBtn);
            (this.btnEqui = new WorkshopRefiningBtn("img/xy2uiimg/refinEquipMenu.png", 1, 1, this)).setBounds(30, 21, 93, 38);
            this.add(this.btnEqui);
            if (!configure.getLzjskg().equals("3")) {
                (this.btnAcc = new WorkshopRefiningBtn("img/xy2uiimg/unRefinAccMenu.png", 1, 2, this)).setBounds(125, 21, 93, 38);
                this.add(this.btnAcc);
                (this.btnRefiners = new WorkshopRefiningBtn("img/xy2uiimg/unRefinWareMenu.png", 1, 3, this)).setBounds(220, 21, 93, 38);
                this.add(this.btnRefiners);
                (this.btnSetsynthesis = new WorkshopRefiningBtn("img/xy2uiimg/unSuitComMenu.png", 1, 4, this)).setBounds(315, 21, 93, 38);
                this.add(this.btnSetsynthesis);
                if (configure.getBsqhkg().equals("开")) {
                    (this.btnMenuGem = new WorkshopRefiningBtn("img/xy2uiimg/unGemMenu.png", 1, 5, this)).setBounds(410, 21, 93, 38);
                    this.add(this.btnMenuGem);
                }
            }
            this.cardJpanel.setBounds(0, 0, 536, 542);
            this.add(this.cardJpanel);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B241.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 558, 516, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/workshop.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 536, 542, null);
        }
    }
    
    public WorkshopRefiningCardJpanel getCardJpanel() {
        return this.cardJpanel;
    }
    
    public void setCardJpanel(WorkshopRefiningCardJpanel cardJpanel) {
        this.cardJpanel = cardJpanel;
    }
    
    public WorkshopRefiningBtn getBtnEqui() {
        return this.btnEqui;
    }
    
    public void setBtnEqui(WorkshopRefiningBtn btnEqui) {
        this.btnEqui = btnEqui;
    }
    
    public WorkshopRefiningBtn getBtnAcc() {
        return this.btnAcc;
    }
    
    public void setBtnAcc(WorkshopRefiningBtn btnAcc) {
        this.btnAcc = btnAcc;
    }
    
    public WorkshopRefiningBtn getBtnRefiners() {
        return this.btnRefiners;
    }
    
    public void setBtnRefiners(WorkshopRefiningBtn btnRefiners) {
        this.btnRefiners = btnRefiners;
    }
    
    public WorkshopRefiningBtn getBtnSetsynthesis() {
        return this.btnSetsynthesis;
    }
    
    public void setBtnSetsynthesis(WorkshopRefiningBtn btnSetsynthesis) {
        this.btnSetsynthesis = btnSetsynthesis;
    }
    
    public WorkshopRefiningBtn getBtnMenuGem() {
        return this.btnMenuGem;
    }
    
    public void setBtnMenuGem(WorkshopRefiningBtn btnMenuGem) {
        this.btnMenuGem = btnMenuGem;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public WorkshopRefiningBtn getBtnGemRefinery() {
        return this.btnGemRefinery;
    }
    
    public void setBtnGemRefinery(WorkshopRefiningBtn btnGemRefinery) {
        this.btnGemRefinery = btnGemRefinery;
    }
}
