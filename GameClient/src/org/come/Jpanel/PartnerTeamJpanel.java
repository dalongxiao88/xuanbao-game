package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import com.tool.image.ImageMixDeal;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.entity.Baby;
import com.tool.tcp.SpriteFactory;
import com.tool.pet.PetProperty;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.model.PalData;
import org.come.entity.Pal;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import com.tool.btn.PartnerBtn;
import javax.swing.JPanel;

public class PartnerTeamJpanel extends JPanel
{
    private PartnerBtn btnWar;
    private PartnerBtn btnResistance;
    private PartnerBtn[] btnArrAck;
    private String[] btnArrStr;
    private JLabel[] labMessage;
    private Color[] colors;
    private ImageIcon icon;
    public static NewPart part;
    public static NewPart petpart;
    public static NewPart lingBaopart;
    public static NewPart babypart;
    
    public PartnerTeamJpanel() {
        this.setOpaque(false);
        this.setLayout(null);
        this.getBtnWar();
        this.getBtnResistance();
        this.getBtnArrAck();
        this.getLabMessage();
    }
    
    public void showBtnArr(boolean type) {
        for (int i = 0; i < this.btnArrAck.length; ++i) {
            this.btnArrAck[i].setVisible(type);
        }
    }
    
    public boolean hideBtnArr() {
        if (this.btnArrAck[0].isVisible()) {
            this.showBtnArr(false);
            return true;
        }
        return false;
    }
    
    public void showPalMessage(Pal pal, PalData palData) {
        String zz = null;
        if (pal != null) {
            zz = this.growLvl(pal.getGrow());
        }
        this.labMessage[0].setText(palData.getName());
        this.labMessage[1].setText(palData.getTrait());
        Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
        String lvl = AnalysisString.lvl((int)grade);
        this.labMessage[2].setText(lvl + "级");
        this.labMessage[3].setForeground(this.getColors()[this.growColor((pal == null) ? 0.0 : pal.getGrow())]);
        this.labMessage[3].setText((pal == null) ? this.growLvl(0.0) : zz);
        int[] palHMASp = PetProperty.getPalHMASp(pal, palData);
        this.labMessage[4].setText(palHMASp[0] + "");
        this.labMessage[5].setText(palHMASp[1] + "");
        this.labMessage[6].setText(palHMASp[2] + "");
        this.labMessage[7].setText(palHMASp[3] + "");
        if (PartnerTeamJpanel.part == null) {
            PartnerTeamJpanel.part = SpriteFactory.createPart(palData.getSkin(), 7, 1, null);
        }
        else {
            PartnerTeamJpanel.part = SpriteFactory.setPart(PartnerTeamJpanel.part, 1, palData.getSkin());
        }
        if (pal != null) {
            if (pal.getSummoning() != null) {
                if (PartnerTeamJpanel.petpart == null) {
                    PartnerTeamJpanel.petpart = SpriteFactory.createPart(pal.getSummoning().getSummoningskin(), 7, 1, null);
                }
                else {
                    PartnerTeamJpanel.petpart = SpriteFactory.setPart(PartnerTeamJpanel.petpart, 1, pal.getSummoning().getSummoningskin());
                }
            }
            else {
                PartnerTeamJpanel.petpart = null;
            }
            if (pal.getLingbao() != null) {
                if (PartnerTeamJpanel.lingBaopart == null) {
                    PartnerTeamJpanel.lingBaopart = SpriteFactory.createPart(pal.getLingbao().getSkin(), 7, 1, null);
                }
                else {
                    PartnerTeamJpanel.lingBaopart = SpriteFactory.setPart(PartnerTeamJpanel.lingBaopart, 1, pal.getLingbao().getSkin());
                }
            }
            else {
                PartnerTeamJpanel.lingBaopart = null;
            }
            if (pal.getBaby() != null) {
                Baby baby = pal.getBaby();
                String nan = "100001";
                String nv = "100002";
                if ((int)baby.getXiaoxin() > 1000) {
                    nan = "100007";
                    nv = "100008";
                    if ((int)baby.getXiaoxin() > 2000) {
                        nan = "100009";
                        nv = "100006";
                    }
                }
                String model = ((int)baby.getChildSex() == 0) ? nan : nv;
                if (PartnerTeamJpanel.babypart == null) {
                    PartnerTeamJpanel.babypart = SpriteFactory.createPart(model, 7, 1, null);
                }
                else {
                    PartnerTeamJpanel.babypart = SpriteFactory.setPart(PartnerTeamJpanel.babypart, 1, model);
                }
            }
            else {
                PartnerTeamJpanel.babypart = null;
            }
        }
    }
    
    public int growColor(double grow) {
        if (grow == 1.0) {
            return 0;
        }
        if (grow == 1.5) {
            return 1;
        }
        if (grow == 2.0) {
            return 2;
        }
        if (grow == 2.5) {
            return 3;
        }
        return 0;
    }
    
    public String growLvl(double grow) {
        if (grow == 1.0) {
            return "资质平平";
        }
        if (grow == 1.5) {
            return "出类拔萃";
        }
        if (grow == 2.0) {
            return "神通广大";
        }
        if (grow == 2.5) {
            return "万中无一";
        }
        return "资质平平";
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B304.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 334, 290, this);
            if (PartnerTeamJpanel.part != null) {
                PartnerTeamJpanel.part.draw(g, 80, 200, 0, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S150.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 334, 290, this);
            if (PartnerTeamJpanel.part != null) {
                PartnerTeamJpanel.part.draw(g, 80, 200, 0, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public PartnerBtn getBtnWar() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnWar == null) {
                (this.btnWar = new PartnerBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "参战", 4)).setBounds(10, 254, 59, 24);
                this.add(this.btnWar);
            }
        }
        else if (this.btnWar == null) {
            (this.btnWar = new PartnerBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "参战", 4)).setBounds(10, 254, 60, 26);
            this.add(this.btnWar);
        }
        return this.btnWar;
    }
    
    public void setBtnWar(PartnerBtn btnWar) {
        this.btnWar = btnWar;
    }
    
    public PartnerBtn getBtnResistance() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnResistance == null) {
                (this.btnResistance = new PartnerBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "抗性", 5)).setBounds(90, 254, 59, 24);
                this.add(this.btnResistance);
            }
        }
        else if (this.btnResistance == null) {
            (this.btnResistance = new PartnerBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "抗性", 5)).setBounds(90, 254, 60, 26);
            this.add(this.btnResistance);
        }
        return this.btnResistance;
    }
    
    public void setBtnResistance(PartnerBtn btnResistance) {
        this.btnResistance = btnResistance;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public PartnerBtn[] getBtnArrAck() {
        if (this.btnArrAck == null) {
            this.btnArrAck = new PartnerBtn[4];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.btnArrAck.length; ++i) {
                    (this.btnArrAck[i] = new PartnerBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "", 10)).setBounds(10, 254 - (i + 1) * 26, 59, 24);
                    this.btnArrAck[i].setVisible(false);
                    this.add(this.btnArrAck[i]);
                }
            }
            else {
                for (int i = 0; i < this.btnArrAck.length; ++i) {
                    (this.btnArrAck[i] = new PartnerBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "", 10)).setBounds(10, 254 - (i + 1) * 26, 60, 26);
                    this.btnArrAck[i].setVisible(false);
                    this.add(this.btnArrAck[i]);
                }
            }
        }
        return this.btnArrAck;
    }
    
    public void setBtnArrAck(PartnerBtn[] btnArrAck) {
        this.btnArrAck = btnArrAck;
    }
    
    public String[] getBtnArrStr() {
        if (this.btnArrStr == null) {
            (this.btnArrStr = new String[5])[0] = "1号";
            this.btnArrStr[1] = "2号";
            this.btnArrStr[2] = "3号";
            this.btnArrStr[3] = "4号";
            this.btnArrStr[4] = "休息";
        }
        return this.btnArrStr;
    }
    
    public void setBtnArrStr(String[] btnArrStr) {
        this.btnArrStr = btnArrStr;
    }
    
    public NewPart getPart() {
        return PartnerTeamJpanel.part;
    }
    
    public void setPart(NewPart part) {
        PartnerTeamJpanel.part = part;
    }
    
    public JLabel[] getLabMessage() {
        if (this.labMessage == null) {
            this.labMessage = new JLabel[8];
            for (int i = 0; i < this.labMessage.length; ++i) {
                (this.labMessage[i] = new JLabel()).setForeground(Color.white);
                this.labMessage[i].setBounds(221, 13 + i * 28, 111, 16);
                this.labMessage[i].setFont(UIUtils.TEXT_FONT1);
                this.add(this.labMessage[i]);
            }
        }
        return this.labMessage;
    }
    
    public void setLabMessage(JLabel[] labMessage) {
        this.labMessage = labMessage;
    }
    
    public Color[] getColors() {
        if (this.colors == null) {
            (this.colors = new Color[4])[0] = Color.green;
            this.colors[1] = Color.cyan;
            this.colors[2] = Color.ORANGE;
            this.colors[3] = Color.red;
        }
        return this.colors;
    }
    
    public void setColors(Color[] colors) {
        this.colors = colors;
    }
}
