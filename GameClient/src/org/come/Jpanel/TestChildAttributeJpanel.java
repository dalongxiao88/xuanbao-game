package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Map;
import com.tool.pet.BabyProperty;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.entity.Baby;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.BabyBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class TestChildAttributeJpanel extends JPanel
{
    private static JTextField labPetname;
    private static JLabel labtemperament;
    private static JLabel labintelligence;
    private static JLabel labFame;
    private static JLabel labtreason;
    private static JLabel labfood;
    private static JLabel labclose;
    private static JLabel labforce;
    private static JLabel labendurance;
    private static JLabel labMorality;
    private static JLabel labPlay;
    private static JLabel labLove;
    private static JLabel labfatigue;
    private static JLabel labrisingmoney;
    private BabyBtn btnpetnamech;
    private BabyBtn btnrelease;
    public static BabyBtn btnfollow;
    public static BabyBtn btnStandby;
    ImageIcon icon;
    
    public TestChildAttributeJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(240, 215));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (TestChildAttributeJpanel.labPetname = new JTextField()).setBounds(56, 10, 150, 18);
            TestChildAttributeJpanel.labPetname.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labPetname.setOpaque(false);
            TestChildAttributeJpanel.labPetname.setFont(new Font("微软雅黑", 0, 13));
            TestChildAttributeJpanel.labPetname.setBorder(BorderFactory.createEmptyBorder());
            TestChildAttributeJpanel.labPetname.setCaretColor(Color.darkGray);
            this.add(TestChildAttributeJpanel.labPetname);
            (TestChildAttributeJpanel.labtemperament = new JLabel()).setBounds(56, 31, 58, 18);
            TestChildAttributeJpanel.labtemperament.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labtemperament.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labtemperament);
            (TestChildAttributeJpanel.labintelligence = new JLabel()).setBounds(56, 52, 58, 18);
            TestChildAttributeJpanel.labintelligence.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labintelligence.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labintelligence);
            (TestChildAttributeJpanel.labFame = new JLabel()).setBounds(56, 73, 58, 18);
            TestChildAttributeJpanel.labFame.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labFame.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labFame);
            (TestChildAttributeJpanel.labtreason = new JLabel()).setBounds(56, 94, 58, 18);
            TestChildAttributeJpanel.labtreason.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labtreason.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labtreason);
            (TestChildAttributeJpanel.labfood = new JLabel()).setBounds(56, 115, 58, 18);
            TestChildAttributeJpanel.labfood.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labfood.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labfood);
            (TestChildAttributeJpanel.labclose = new JLabel()).setBounds(56, 136, 58, 18);
            TestChildAttributeJpanel.labclose.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labclose.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labclose);
            (TestChildAttributeJpanel.labforce = new JLabel()).setBounds(164, 31, 58, 18);
            TestChildAttributeJpanel.labforce.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labforce.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labforce);
            (TestChildAttributeJpanel.labendurance = new JLabel()).setBounds(164, 52, 58, 18);
            TestChildAttributeJpanel.labendurance.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labendurance.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labendurance);
            (TestChildAttributeJpanel.labMorality = new JLabel()).setBounds(164, 73, 58, 18);
            TestChildAttributeJpanel.labMorality.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labMorality.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labMorality);
            (TestChildAttributeJpanel.labPlay = new JLabel()).setBounds(164, 94, 58, 18);
            TestChildAttributeJpanel.labPlay.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labPlay.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labPlay);
            (TestChildAttributeJpanel.labLove = new JLabel()).setBounds(164, 115, 58, 18);
            TestChildAttributeJpanel.labLove.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labLove.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labLove);
            (TestChildAttributeJpanel.labfatigue = new JLabel()).setBounds(164, 136, 58, 18);
            TestChildAttributeJpanel.labfatigue.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labfatigue.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labfatigue);
            (TestChildAttributeJpanel.labrisingmoney = new JLabel()).setBounds(56, 156, 167, 18);
            TestChildAttributeJpanel.labrisingmoney.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labrisingmoney.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labrisingmoney);
            (TestChildAttributeJpanel.btnfollow = new BabyBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "寄养")).setBounds(89, 181, 59, 24);
            this.add(TestChildAttributeJpanel.btnfollow);
            (TestChildAttributeJpanel.btnStandby = new BabyBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "参战")).setBounds(165, 181, 59, 24);
            this.add(TestChildAttributeJpanel.btnStandby);
            (this.btnpetnamech = new BabyBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "放生")).setBounds(13, 181, 59, 24);
            this.add(this.btnpetnamech);
            (this.btnrelease = new BabyBtn("inkImg/button/19.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_FONT2, "改")).setBounds(204, 11, 18, 18);
            this.add(this.btnrelease);
        }
        else {
            this.setPreferredSize(new Dimension(240, 215));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (TestChildAttributeJpanel.labPetname = new JTextField()).setBounds(56, 10, 150, 18);
            TestChildAttributeJpanel.labPetname.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labPetname.setOpaque(false);
            TestChildAttributeJpanel.labPetname.setFont(new Font("微软雅黑", 0, 13));
            TestChildAttributeJpanel.labPetname.setBorder(BorderFactory.createEmptyBorder());
            TestChildAttributeJpanel.labPetname.setCaretColor(Color.white);
            this.add(TestChildAttributeJpanel.labPetname);
            (TestChildAttributeJpanel.labtemperament = new JLabel()).setBounds(56, 31, 58, 18);
            TestChildAttributeJpanel.labtemperament.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labtemperament.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labtemperament);
            (TestChildAttributeJpanel.labintelligence = new JLabel()).setBounds(56, 52, 58, 18);
            TestChildAttributeJpanel.labintelligence.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labintelligence.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labintelligence);
            (TestChildAttributeJpanel.labFame = new JLabel()).setBounds(56, 73, 58, 18);
            TestChildAttributeJpanel.labFame.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labFame.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labFame);
            (TestChildAttributeJpanel.labtreason = new JLabel()).setBounds(56, 94, 58, 18);
            TestChildAttributeJpanel.labtreason.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labtreason.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labtreason);
            (TestChildAttributeJpanel.labfood = new JLabel()).setBounds(56, 115, 58, 18);
            TestChildAttributeJpanel.labfood.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labfood.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labfood);
            (TestChildAttributeJpanel.labclose = new JLabel()).setBounds(56, 136, 58, 18);
            TestChildAttributeJpanel.labclose.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labclose.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labclose);
            (TestChildAttributeJpanel.labforce = new JLabel()).setBounds(164, 31, 58, 18);
            TestChildAttributeJpanel.labforce.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labforce.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labforce);
            (TestChildAttributeJpanel.labendurance = new JLabel()).setBounds(164, 52, 58, 18);
            TestChildAttributeJpanel.labendurance.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labendurance.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labendurance);
            (TestChildAttributeJpanel.labMorality = new JLabel()).setBounds(164, 73, 58, 18);
            TestChildAttributeJpanel.labMorality.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labMorality.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labMorality);
            (TestChildAttributeJpanel.labPlay = new JLabel()).setBounds(164, 94, 58, 18);
            TestChildAttributeJpanel.labPlay.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labPlay.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labPlay);
            (TestChildAttributeJpanel.labLove = new JLabel()).setBounds(164, 115, 58, 18);
            TestChildAttributeJpanel.labLove.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labLove.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labLove);
            (TestChildAttributeJpanel.labfatigue = new JLabel()).setBounds(164, 136, 58, 18);
            TestChildAttributeJpanel.labfatigue.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labfatigue.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labfatigue);
            (TestChildAttributeJpanel.labrisingmoney = new JLabel()).setBounds(56, 156, 167, 18);
            TestChildAttributeJpanel.labrisingmoney.setForeground(Color.WHITE);
            TestChildAttributeJpanel.labrisingmoney.setFont(new Font("宋体", 1, 14));
            this.add(TestChildAttributeJpanel.labrisingmoney);
            (TestChildAttributeJpanel.btnfollow = new BabyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "寄养")).setBounds(89, 181, 60, 26);
            this.add(TestChildAttributeJpanel.btnfollow);
            (TestChildAttributeJpanel.btnStandby = new BabyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "参战")).setBounds(165, 181, 60, 26);
            this.add(TestChildAttributeJpanel.btnStandby);
            (this.btnpetnamech = new BabyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "放生")).setBounds(13, 181, 60, 26);
            this.add(this.btnpetnamech);
            (this.btnrelease = new BabyBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "改")).setBounds(204, 11, 17, 17);
            this.add(this.btnrelease);
        }
    }
    
    public void showBaby(Baby baby, BigDecimal[] bigs) {
        if (baby == null) {
            TestChildAttributeJpanel.labPetname.setText("");
            TestChildAttributeJpanel.labtemperament.setText("");
            TestChildAttributeJpanel.labintelligence.setText("");
            TestChildAttributeJpanel.labFame.setText("");
            TestChildAttributeJpanel.labtreason.setText("");
            TestChildAttributeJpanel.labfood.setText("");
            TestChildAttributeJpanel.labclose.setText("");
            TestChildAttributeJpanel.labforce.setText("");
            TestChildAttributeJpanel.labendurance.setText("");
            TestChildAttributeJpanel.labMorality.setText("");
            TestChildAttributeJpanel.labPlay.setText("");
            TestChildAttributeJpanel.labLove.setText("");
            TestChildAttributeJpanel.labfatigue.setText("");
            TestChildAttributeJpanel.labrisingmoney.setText("");
            TestChildAttributeJpanel.btnStandby.setText("寄养");
            TestChildAttributeJpanel.btnStandby.setText("参战");
            this.btnrelease.setText("改");
            this.btnpetnamech.setText("放生");
        }
        else {
            BigDecimal big = RoleData.getRoleData().getLoginResult().getBabyId();
            if (big == null) {
                big = new BigDecimal(-1);
            }
            if (big.compareTo(baby.getBabyid()) == 0) {}
            TestChildAttributeJpanel.btnStandby.setText("待机");
            Map<String, Integer> maps = BabyProperty.getBabyProperty().getProperty(baby, bigs);
            TestChildAttributeJpanel.labPetname.setText(baby.getBabyname());
            TestChildAttributeJpanel.labtemperament.setText(((Integer)maps.get("气质")).toString());
            TestChildAttributeJpanel.labintelligence.setText(((Integer)maps.get("智力")).toString());
            TestChildAttributeJpanel.labFame.setText(((Integer)maps.get("名气")).toString());
            TestChildAttributeJpanel.labtreason.setText(((Integer)maps.get("叛逆")).toString());
            TestChildAttributeJpanel.labfood.setText(baby.getWenbao().toString());
            TestChildAttributeJpanel.labclose.setText(baby.getQingmi().toString());
            TestChildAttributeJpanel.labforce.setText(((Integer)maps.get("内力")).toString());
            TestChildAttributeJpanel.labendurance.setText(((Integer)maps.get("耐力")).toString());
            TestChildAttributeJpanel.labMorality.setText(((Integer)maps.get("道德")).toString());
            TestChildAttributeJpanel.labPlay.setText(((Integer)maps.get("玩性")).toString());
            TestChildAttributeJpanel.labLove.setText(((Integer)maps.get("孝心")).toString());
            TestChildAttributeJpanel.labfatigue.setText(baby.getPilao().toString());
            TestChildAttributeJpanel.labrisingmoney.setText(baby.getYangyujin().toString());
            BigDecimal bid = RoleData.getRoleData().getLoginResult().getBabyId();
            if (bid == null || bid.compareTo(baby.getBabyid()) != 0) {
                TestChildAttributeJpanel.btnStandby.setText("参战");
            }
            else {
                TestChildAttributeJpanel.btnStandby.setText("待机");
            }
            if (baby.getState() != null && baby.getState().equals("1")) {
                TestChildAttributeJpanel.btnfollow.setText("取回");
            }
            else {
                TestChildAttributeJpanel.btnfollow.setText("寄养");
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B217.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 240, 215, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/99_png.xy2uiimg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 240, 214, this);
        }
    }
    
    public static JTextField getLabPetname() {
        return TestChildAttributeJpanel.labPetname;
    }
    
    public void setLabPetname(JTextField labPetname) {
        TestChildAttributeJpanel.labPetname = labPetname;
    }
    
    public static JLabel getLabtemperament() {
        return TestChildAttributeJpanel.labtemperament;
    }
    
    public void setLabtemperament(JLabel labtemperament) {
        TestChildAttributeJpanel.labtemperament = labtemperament;
    }
    
    public static JLabel getLabintelligence() {
        return TestChildAttributeJpanel.labintelligence;
    }
    
    public void setLabintelligence(JLabel labintelligence) {
        TestChildAttributeJpanel.labintelligence = labintelligence;
    }
    
    public static JLabel getLabFame() {
        return TestChildAttributeJpanel.labFame;
    }
    
    public void setLabFame(JLabel labFame) {
        TestChildAttributeJpanel.labFame = labFame;
    }
    
    public static JLabel getLabtreason() {
        return TestChildAttributeJpanel.labtreason;
    }
    
    public void setLabtreason(JLabel labtreason) {
        TestChildAttributeJpanel.labtreason = labtreason;
    }
    
    public static JLabel getLabfood() {
        return TestChildAttributeJpanel.labfood;
    }
    
    public void setLabfood(JLabel labfood) {
        TestChildAttributeJpanel.labfood = labfood;
    }
    
    public static JLabel getLabclose() {
        return TestChildAttributeJpanel.labclose;
    }
    
    public void setLabclose(JLabel labclose) {
        TestChildAttributeJpanel.labclose = labclose;
    }
    
    public static JLabel getLabforce() {
        return TestChildAttributeJpanel.labforce;
    }
    
    public void setLabforce(JLabel labforce) {
        TestChildAttributeJpanel.labforce = labforce;
    }
    
    public static JLabel getLabendurance() {
        return TestChildAttributeJpanel.labendurance;
    }
    
    public void setLabendurance(JLabel labendurance) {
        TestChildAttributeJpanel.labendurance = labendurance;
    }
    
    public static JLabel getLabMorality() {
        return TestChildAttributeJpanel.labMorality;
    }
    
    public void setLabMorality(JLabel labMorality) {
        TestChildAttributeJpanel.labMorality = labMorality;
    }
    
    public static JLabel getLabPlay() {
        return TestChildAttributeJpanel.labPlay;
    }
    
    public void setLabPlay(JLabel labPlay) {
        TestChildAttributeJpanel.labPlay = labPlay;
    }
    
    public static JLabel getLabLove() {
        return TestChildAttributeJpanel.labLove;
    }
    
    public void setLabLove(JLabel labLove) {
        TestChildAttributeJpanel.labLove = labLove;
    }
    
    public static JLabel getLabfatigue() {
        return TestChildAttributeJpanel.labfatigue;
    }
    
    public void setLabfatigue(JLabel labfatigue) {
        TestChildAttributeJpanel.labfatigue = labfatigue;
    }
    
    public static JLabel getLabrisingmoney() {
        return TestChildAttributeJpanel.labrisingmoney;
    }
    
    public void setLabrisingmoney(JLabel labrisingmoney) {
        TestChildAttributeJpanel.labrisingmoney = labrisingmoney;
    }
    
    public BabyBtn getBtnrelease() {
        return this.btnrelease;
    }
    
    public void setBtnrelease(BabyBtn btnrelease) {
        this.btnrelease = btnrelease;
    }
    
    public BabyBtn getBtnpetnamech() {
        return this.btnpetnamech;
    }
    
    public void setBtnpetnamech(BabyBtn btnpetnamech) {
        this.btnpetnamech = btnpetnamech;
    }
}
