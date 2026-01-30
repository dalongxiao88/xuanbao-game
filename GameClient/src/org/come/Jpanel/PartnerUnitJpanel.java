package org.come.Jpanel;

import java.util.List;
import org.come.bean.LoginResult;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.entity.Pal;
import org.come.model.PalData;
import org.come.Frame.PartnerJframe;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.PartnerBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartnerUnitJpanel extends JPanel
{
    private JLabel labUnitImg;
    private JLabel labName;
    private JLabel labLvl;
    private JLabel labActivate;
    private PartnerBtn labAck;
    private int pid;
    private int state;
    private ImageIcon icon;
    
    public PartnerUnitJpanel() {
        this.state = -1;
        this.setPreferredSize(new Dimension(147, 49));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabActivate();
        this.getLabUnitImg();
        this.getLabName();
        this.getLabLvl();
        this.getLabAck();
        MListener listener = new MListener(this);
        this.addMouseListener(listener);
    }
    
    public void changeWarBtn() {
        String text = "激活";
        if (this.state != -1) {
            if (this.state == 0) {
                text = "参战";
            }
            else if (this.state > 0) {
                text = "调整";
            }
        }
        PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel().getBtnWar().setText(text);
    }
    
    public void showPal(PalData palData, Pal pal, String[] palsArr, String lvl) {
        this.pid = palData.getPalId();
        if (pal == null) {
            this.labUnitImg.setIcon(CutButtonImage.getImage("img/head/p" + palData.getSkin() + ".png", 37, 37));
            this.labName.setText(palData.getName());
            if (lvl != null) {
                this.labLvl.setText(lvl + "级");
            }
            PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel().getBtnWar().setText("激活");
            this.state = -1;
        }
        else {
            this.labUnitImg.setIcon(CutButtonImage.getImage("img/head/p" + palData.getSkin() + ".png", 37, 37));
            this.labName.setText(palData.getName());
            if (lvl != null) {
                this.labLvl.setText(lvl + "级");
            }
            this.state = this.changePalState(pal, palsArr, lvl);
        }
    }
    
    public int changePalState(Pal pal, String[] palsArr, String lvl) {
        if (pal != null) {
            this.labActivate.setVisible(false);
            if (lvl != null) {
                this.labLvl.setText(lvl + "级");
            }
            if (palsArr != null) {
                for (int i = 0; i < palsArr.length; ++i) {
                    if (palsArr[i].equals(pal.getId() + "")) {
                        this.labAck.setBtn(1);
                        this.labAck.setIcon(CutButtonImage.getImage("inkImg/button/2.png", -1, -1));
                        this.labAck.setText(PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel().getBtnArrStr()[i]);
                        return i + 1;
                    }
                }
            }
            this.labAck.setBtn(0);
            this.labAck.setIcon(null);
            this.labAck.setText(null);
            return 0;
        }
        else {
            this.labAck.setBtn(0);
            this.labAck.setText(null);
            this.labActivate.setVisible(true);
            return -1;
        }
    }
    
    public int changePalState(String[] palsArr) {
        if (palsArr != null) {
            for (int i = 0; i < palsArr.length; ++i) {
                if (palsArr[i].equals(this.pid + "")) {
                    this.labAck.setBtn(1);
                    this.labAck.setIcon(CutButtonImage.getImage("inkImg/button/2.png", -1, -1));
                    this.labAck.setText(PartnerJframe.getPartnerJframe().getPartnerMainJpanel().getPartnerCardJpanel().getPartnerTeamJpanel().getBtnArrStr()[i]);
                    return i + i;
                }
            }
        }
        else {
            this.labAck.setBtn(0);
            this.labAck.setIcon(null);
            this.labAck.setText(null);
        }
        return 0;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S153.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 147, 49, this);
    }
    
    public JLabel getLabUnitImg() {
        if (this.labUnitImg == null) {
            (this.labUnitImg = new JLabel()).setBounds(5, 4, 37, 37);
            this.add(this.labUnitImg);
        }
        return this.labUnitImg;
    }
    
    public void setLabUnitImg(JLabel labUnitImg) {
        this.labUnitImg = labUnitImg;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel("绿光鹿")).setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT2);
            this.labName.setBounds(45, 2, 70, 16);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel("一转126级")).setForeground(Color.white);
            this.labLvl.setFont(UIUtils.TEXT_FONT2);
            this.labLvl.setBounds(45, 22, 70, 16);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JLabel getLabAck() {
        if (this.labAck == null) {
            (this.labAck = new PartnerBtn("inkImg/button/2.png", 0, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", 11)).setBounds(100, 10, 34, 17);
            this.labAck.setForeground(Color.white);
            this.add(this.labAck);
        }
        return this.labAck;
    }
    
    public void setLabAck(PartnerBtn labAck) {
        this.labAck = labAck;
    }
    
    public int getPid() {
        return this.pid;
    }
    
    public void setPid(int pid) {
        this.pid = pid;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public JLabel getLabActivate() {
        if (this.labActivate == null) {
            (this.labActivate = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/B235.png", -1, -1));
            this.labActivate.setBounds(0, 0, 147, 49);
            this.add(this.labActivate);
        }
        return this.labActivate;
    }
    
    public void setLabActivate(JLabel labActivate) {
        this.labActivate = labActivate;
    }
    
    class MListener extends MouseAdapter
    {
        private PartnerUnitJpanel partnerUnitJpanel;
        
        public MListener(PartnerUnitJpanel partnerUnitJpanel) {
            this.partnerUnitJpanel = partnerUnitJpanel;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            PartnerJframe.getPartnerJframe().getPartnerMainJpanel().setPalDataChooseId(this.partnerUnitJpanel.getPid());
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            String pals = loginResult.getPals();
            String lvl = AnalysisString.lvl((int)loginResult.getGrade());
            List<Pal> palsList = RoleData.getRoleData().getPals();
            if (pals != null) {
                String[] palsArr = pals.split("\\|");
                int i = 0;
                while (i < palsList.size()) {
                    Pal pal = (Pal)palsList.get(i);
                    if (pal.getpId() == this.partnerUnitJpanel.getPid()) {
                        PartnerUnitJpanel.this.state = PartnerUnitJpanel.this.changePalState(pal, palsArr, lvl);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
            PartnerUnitJpanel.this.changeWarBtn();
        }
    }
}
