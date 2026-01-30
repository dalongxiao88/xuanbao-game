package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.UserMessUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.util.Vector;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import com.tool.role.RoleProperty;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.Meridians;
import com.tool.role.BaseMeridians;
import org.soaring.btn.CharacterMeridiansBtn;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterMeridiansMainJpanel extends JPanel
{
    private JLabel labName;
    private JLabel labQuality;
    private JLabel labLvl;
    private JLabel labExp;
    private JLabel labAttribute;
    private JLabel labIntegral;
    private JLabel labxianyu;
    private JLabel[] labMeridians;
    private JLabel[] labValue;
    private BigDecimal money;
    private String gold;
    private CharacterMeridiansBtn btnRedo;
    private CharacterMeridiansBtn btnPractice;
    private CharacterMeridiansBtn btnSeepUp;
    private int ChooseNum;
    private int moneyType;
    private JLabel lockAttr;
    private JLabel lockQuality;
    private String lockAttrMsg;
    private String lockQualityMsg;
    BaseMeridians baseMeridians;
    Meridians meridians;
    private ImageIcon icon;
    private ImageIcon iconExp;
    private ImageIcon iconMoney;
    private int numExp;
    
    public CharacterMeridiansMainJpanel() {
        this.money = null;
        this.gold = null;
        this.ChooseNum = -1;
        this.moneyType = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(40, 60, 544, 347);
        }
        else {
            this.setBounds(16, 72, 544, 347);
        }
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabValue();
        this.getLabLvl();
        this.getLabName();
        this.getLabQuality();
        this.getLabExp();
        this.getLabAttribute();
        this.getBtnPractice();
        this.getBtnRedo();
        this.getBtnSeepUp();
        this.getLabIntegral();
        this.getLabMeridians();
        this.getLabxianyu();
        this.setLock();
    }
    
    public JLabel getLockAttr() {
        return this.lockAttr;
    }
    
    public JLabel getLockQuality() {
        return this.lockQuality;
    }
    
    void setLock() {
        if (MyIsif.getStyle().equals("水墨")) {
            (this.lockAttr = new JLabel()).setBounds(290, 20, 13, 13);
            this.lockAttr.addMouseListener(new LockListen(this.lockAttr));
            this.add(this.lockAttr);
            (this.lockQuality = new JLabel()).setBounds(290, 50, 13, 13);
            this.lockQuality.addMouseListener(new LockListen(this.lockQuality));
            this.add(this.lockQuality);
        }
        else {
            (this.lockAttr = new JLabel()).setBounds(285, 20, 13, 13);
            this.lockAttr.addMouseListener(new LockListen(this.lockAttr));
            this.add(this.lockAttr);
            (this.lockQuality = new JLabel()).setBounds(285, 50, 13, 13);
            this.lockQuality.addMouseListener(new LockListen(this.lockQuality));
            this.add(this.lockQuality);
        }
    }
    
    public void refreshAllAttribute() {
        this.ChooseNum = -1;
        Vector<BaseMeridians> meridiansVector = RoleProperty.getRoleProperty().meridiansVector;
        int size = meridiansVector.size();
        if (this.moneyType == 1) {
            this.labxianyu.setText(String.valueOf(RoleData.getRoleData().getLoginResult().getCodecard()));
        }
        for (int i = 0, len = this.labMeridians.length; i < len; ++i) {
            if (i < size) {
                BaseMeridians baseMeridians = (BaseMeridians)meridiansVector.get(i);
                this.labMeridians[i].setIcon(CutButtonImage.getImage("img/soaringSkill/dot.png", -1, -1));
                this.labValue[i].setText(baseMeridians.getKey() + ":" + String.format("%.2f", new Object[] { Double.valueOf(baseMeridians.getKeyValue()) }));
                this.labMeridians[i].setText(this.getMeridians(i));
            }
            else {
                this.labMeridians[i].setIcon(null);
                this.labValue[i].setText(null);
            }
        }
        this.refreshMeridians(null);
    }
    
    public String getMeridians(int value) {
        switch (value) {
            case 0: {
                return "属兑";
            }
            case 1: {
                return "冲阳";
            }
            case 2: {
                return "解溪";
            }
            case 3: {
                return "神阙";
            }
            case 4: {
                return "梁门";
            }
            case 5: {
                return "盆缺";
            }
            case 6: {
                return "晴明";
            }
            case 7: {
                return "乳根";
            }
            case 8: {
                return "横谷";
            }
            case 9: {
                return "然谷";
            }
            case 10: {
                return "交信";
            }
            case 11: {
                return "天门";
            }
            default: {
                return "不明";
            }
        }
    }
    
    public Color getQualityColor(int quality) {
        if (quality > 7) {
            return Color.orange;
        }
        if (quality > 5) {
            return UIUtils.getColor("#CEE82EE");
        }
        if (quality > 2) {
            return UIUtils.getColor("#C00FFFF");
        }
        return Color.green;
    }
    
    public void serverRefreshView(BaseMeridians baseMeridians) {
        int bh = baseMeridians.getId() - 1;
        if (this.labMeridians[bh].getIcon() == null) {
            this.labMeridians[bh].setIcon(CutButtonImage.getImage("img/soaringSkill/dot.png", -1, -1));
        }
        if (this.ChooseNum != bh) {
            if (this.ChooseNum != -1) {
                this.labMeridians[this.ChooseNum].setIcon(CutButtonImage.getImage("img/soaringSkill/dot.png", -1, -1));
            }
            this.ChooseNum = bh;
            this.labMeridians[this.ChooseNum].setIcon(CutButtonImage.getImage("img/soaringSkill/dotChoose.png", -1, -1));
        }
        this.labValue[bh].setText(baseMeridians.getKey() + ":" + String.format("%.2f", new Object[] { Double.valueOf(baseMeridians.getKeyValue()) }));
        if (this.moneyType == 1) {
            this.labxianyu.setText(String.valueOf(RoleData.getRoleData().getLoginResult().getCodecard()));
        }
        this.refreshMeridians(baseMeridians);
    }
    
    public Meridians getMeridians() {
        return this.meridians;
    }
    
    public void refreshMeridians(BaseMeridians baseMeridians) {
        this.baseMeridians = baseMeridians;
        if (baseMeridians == null) {
            this.labName.setText(null);
            this.labQuality.setText(null);
            this.labLvl.setText(null);
            this.labExp.setText(null);
            this.numExp = 0;
            this.labAttribute.setText(null);
            this.meridians = null;
            this.lockAttr.setIcon(null);
            this.lockQuality.setIcon(null);
            this.lockAttrMsg = null;
            this.lockQualityMsg = null;
            this.labIntegral.setText(null);
            this.gold = null;
            this.money = null;
            return;
        }
        this.meridians = UserMessUntil.getAllMeridians().getByQuality(baseMeridians.getId(), baseMeridians.getQuality());
        this.labName.setText(this.getMeridians(this.ChooseNum));
        this.labMeridians[this.ChooseNum].setIcon(CutButtonImage.getImage("img/soaringSkill/dotChoose.png", -1, -1));
        this.labQuality.setText(this.meridians.getQualityName());
        this.labQuality.setForeground(this.getQualityColor(this.meridians.getQuality()));
        String stagename = this.meridians.getStagesName().split("_")[baseMeridians.getStage()];
        this.labLvl.setText(stagename);
        this.labExp.setText(baseMeridians.getExp() + "/" + this.meridians.getAllExp());
        this.labAttribute.setText(baseMeridians.getKey() + ":" + String.format("%.2f", new Object[] { Double.valueOf(baseMeridians.getKeyValue()) }));
        this.numExp = (int)((double)baseMeridians.getExp() / (double)this.meridians.getAllExp() * 185.0);
        this.numExp = ((this.numExp == 0) ? 1 : this.numExp);
        if (this.moneyType == 0) {
            this.money = new BigDecimal(this.meridians.getGold());
        }
        else {
            this.money = new BigDecimal(this.meridians.getMoney());
        }
        this.gold = this.meridians.getResetGold() + "";
        this.labIntegral.setText(this.meridians.getExp() + "");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S203.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconExp == null) {
                this.iconExp = new ImageIcon("img/soaringSkill/exp.png");
            }
            g.drawImage(this.iconExp.getImage(), 347, 220, this.numExp, 14, null);
            if (this.gold != null) {
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.white);
                g.drawString(this.gold, 399, 253);
            }
            if (this.money != null) {
                Util.drawPrice(g, this.money, 399, 294);
            }
            if (this.moneyType == 1) {
                if (this.iconMoney == null) {
                    this.iconMoney = new ImageIcon("inkImg/background/S204.png");
                }
                g.drawImage(this.iconMoney.getImage(), 353, 284, null);
                g.drawImage(this.iconMoney.getImage(), 373, 305, null);
            }
            else if (this.moneyType == 0) {
                Util.drawMoney(g, 420, 316);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/jingamai.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconExp == null) {
                this.iconExp = new ImageIcon("img/soaringSkill/exp.png");
            }
            g.drawImage(this.iconExp.getImage(), 347, 220, this.numExp, 14, null);
            if (this.gold != null) {
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.white);
                g.drawString(this.gold, 399, 253);
            }
            if (this.money != null) {
                Util.drawPrice(g, this.money, 399, 294);
            }
            if (this.moneyType == 1) {
                if (this.iconMoney == null) {
                    this.iconMoney = new ImageIcon("inkImg/background/S204.png");
                }
                g.drawImage(this.iconMoney.getImage(), 353, 284, null);
                g.drawImage(this.iconMoney.getImage(), 373, 305, null);
            }
            else if (this.moneyType == 0) {
                Util.drawMoney(g, 420, 316);
            }
        }
        g.setColor(Color.white);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.baseMeridians != null) {
                g.drawString("锁定属性", this.lockAttr.getX() - 58, this.lockAttr.getY() + 13);
                g.drawImage(new ImageIcon("inkImg/button/14.png").getImage(), this.lockAttr.getX(), this.lockAttr.getY(), null);
                g.drawString("锁定品质", this.lockQuality.getX() - 58, this.lockQuality.getY() + 13);
                g.drawImage(new ImageIcon("inkImg/button/14.png").getImage(), this.lockQuality.getX(), this.lockQuality.getY(), null);
                if (this.lockAttrMsg != null) {
                    g.drawString(this.lockAttrMsg, this.lockAttr.getX() - 258, this.lockAttr.getY() + 13);
                }
                if (this.lockQualityMsg != null) {
                    g.drawString(this.lockQualityMsg, this.lockQuality.getX() - 258, this.lockQuality.getY() + 13);
                }
            }
            else if (this.baseMeridians != null) {
                g.drawString("锁定属性", this.lockAttr.getX() - 62, this.lockAttr.getY() + 13);
                g.drawImage(new ImageIcon("inkImg/button/14.png").getImage(), this.lockAttr.getX(), this.lockAttr.getY(), null);
                g.drawString("锁定品质", this.lockQuality.getX() - 62, this.lockQuality.getY() + 13);
                g.drawImage(new ImageIcon("inkImg/button/14.png").getImage(), this.lockQuality.getX(), this.lockQuality.getY(), null);
                if (this.lockAttrMsg != null) {
                    g.drawString(this.lockAttrMsg, this.lockAttr.getX() - 230, this.lockAttr.getY() + 13);
                }
                if (this.lockQualityMsg != null) {
                    g.drawString(this.lockQualityMsg, this.lockQuality.getX() - 230, this.lockQuality.getY() + 13);
                }
            }
        }
    }
    
    public JLabel[] getLabMeridians() {
        if (this.labMeridians == null) {
            this.labMeridians = new JLabel[12];
            for (int i = 0; i < this.labMeridians.length; ++i) {
                (this.labMeridians[i] = new JLabel()).addMouseListener(new MListener(i));
                this.labMeridians[i].setForeground(Color.YELLOW);
                this.add(this.labMeridians[i]);
            }
            if (MyIsif.getStyle().equals("水墨")) {
                this.labMeridians[0].setBounds(85, 276, 50, 11);
                this.labMeridians[1].setBounds(93, 245, 50, 11);
                this.labMeridians[2].setBounds(138, 267, 50, 11);
                this.labMeridians[3].setBounds(179, 251, 50, 11);
                this.labMeridians[4].setBounds(155, 231, 50, 11);
                this.labMeridians[5].setBounds(120, 219, 50, 11);
                this.labMeridians[6].setBounds(132, 183, 50, 11);
                this.labMeridians[7].setBounds(169, 190, 50, 11);
                this.labMeridians[8].setBounds(187, 161, 50, 11);
                this.labMeridians[9].setBounds(152, 146, 50, 11);
                this.labMeridians[10].setBounds(125, 124, 50, 11);
                this.labMeridians[11].setBounds(93, 154, 50, 11);
            }
            else {
                this.labMeridians[0].setBounds(90, 274, 50, 11);
                this.labMeridians[1].setBounds(97, 245, 50, 11);
                this.labMeridians[2].setBounds(139, 265, 50, 11);
                this.labMeridians[3].setBounds(177, 250, 50, 11);
                this.labMeridians[4].setBounds(155, 231, 50, 11);
                this.labMeridians[5].setBounds(122, 220, 50, 11);
                this.labMeridians[6].setBounds(133, 186, 50, 11);
                this.labMeridians[7].setBounds(169, 194, 50, 11);
                this.labMeridians[8].setBounds(184, 166, 50, 11);
                this.labMeridians[9].setBounds(153, 152, 50, 11);
                this.labMeridians[10].setBounds(128, 131, 50, 11);
                this.labMeridians[11].setBounds(97, 160, 50, 11);
            }
        }
        return this.labMeridians;
    }
    
    public void setLabMeridians(JLabel[] labMeridians) {
        this.labMeridians = labMeridians;
    }
    
    public JLabel[] getLabValue() {
        if (this.labValue == null) {
            this.labValue = new JLabel[12];
            for (int i = 0; i < this.labValue.length; ++i) {
                (this.labValue[i] = new JLabel()).setForeground(Color.white);
                this.labValue[i].setFont(UIUtils.TEXT_FONT);
                if (MyIsif.getStyle().equals("水墨")) {
                    this.labValue[i].setBounds(318 + i % 2 * 111, 19 + i / 2 * 25, 111, 24);
                }
                else {
                    this.labValue[i].setBounds(314 + i % 2 * 111, 19 + i / 2 * 25, 111, 24);
                }
                this.add(this.labValue[i]);
            }
        }
        return this.labValue;
    }
    
    public void setLabValue(JLabel[] labValue) {
        this.labValue = labValue;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            this.labName = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labName.setBounds(331, 176, 63, 15);
            }
            else {
                this.labName.setBounds(331, 176, 63, 15);
            }
            this.labName.setForeground(Color.orange);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabQuality() {
        if (this.labQuality == null) {
            this.labQuality = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labQuality.setBounds(420, 176, 36, 13);
            }
            else {
                this.labQuality.setBounds(420, 176, 36, 13);
            }
            this.labQuality.setForeground(Color.white);
            this.labQuality.setFont(UIUtils.TEXT_FONT);
            this.add(this.labQuality);
        }
        return this.labQuality;
    }
    
    public void setLabQuality(JLabel labQuality) {
        this.labQuality = labQuality;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            this.labLvl = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labLvl.setBounds(488, 176, 40, 13);
            }
            else {
                this.labLvl.setBounds(468, 176, 60, 13);
            }
            this.labLvl.setForeground(Color.white);
            this.labLvl.setFont(UIUtils.TEXT_FONT);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public JLabel getLabExp() {
        if (this.labExp == null) {
            this.labExp = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labExp.setBounds(333, 218, 186, 16);
            }
            else {
                this.labExp.setBounds(333, 218, 186, 16);
            }
            this.labExp.setForeground(Color.white);
            this.labExp.setFont(UIUtils.TEXT_FONT);
            this.add(this.labExp);
        }
        return this.labExp;
    }
    
    public void setLabExp(JLabel labExp) {
        this.labExp = labExp;
    }
    
    public JLabel getLabAttribute() {
        if (this.labAttribute == null) {
            this.labAttribute = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labAttribute.setBounds(361, 199, 111, 13);
            }
            else {
                this.labAttribute.setBounds(361, 199, 111, 13);
            }
            this.labAttribute.setForeground(Color.white);
            this.labAttribute.setFont(UIUtils.TEXT_FONT);
            this.add(this.labAttribute);
        }
        return this.labAttribute;
    }
    
    public void setLabAttribute(JLabel labAttribute) {
        this.labAttribute = labAttribute;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public CharacterMeridiansBtn getBtnRedo() {
        if (this.btnRedo == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnRedo = new CharacterMeridiansBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "重洗", 0, this)).setBounds(488, 198, 34, 17);
            }
            else {
                (this.btnRedo = new CharacterMeridiansBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "重洗", 0, this)).setBounds(498, 198, 34, 17);
            }
            this.add(this.btnRedo);
        }
        return this.btnRedo;
    }
    
    public void setBtnRedo(CharacterMeridiansBtn btnRedo) {
        this.btnRedo = btnRedo;
    }
    
    public CharacterMeridiansBtn getBtnPractice() {
        if (this.btnPractice == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnPractice = new CharacterMeridiansBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "转换", 1, this)).setBounds(328, 324, 34, 17);
            }
            else {
                (this.btnPractice = new CharacterMeridiansBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "转换", 1, this)).setBounds(328, 324, 34, 17);
            }
            this.add(this.btnPractice);
        }
        return this.btnPractice;
    }
    
    public void setBtnPractice(CharacterMeridiansBtn btnPractice) {
        this.btnPractice = btnPractice;
    }
    
    public CharacterMeridiansBtn getBtnSeepUp() {
        if (this.btnSeepUp == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnSeepUp = new CharacterMeridiansBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "金币修炼", 2, this)).setBounds(455, 324, 68, 17);
            }
            else {
                (this.btnSeepUp = new CharacterMeridiansBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "金币修炼", 2, this)).setBounds(455, 324, 68, 17);
            }
            this.add(this.btnSeepUp);
        }
        return this.btnSeepUp;
    }
    
    public void setBtnSeepUp(CharacterMeridiansBtn btnSeepUp) {
        this.btnSeepUp = btnSeepUp;
    }
    
    public JLabel getLabIntegral() {
        if (this.labIntegral == null) {
            this.labIntegral = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labIntegral.setBounds(399, 262, 132, 14);
            }
            else {
                this.labIntegral.setBounds(399, 262, 132, 14);
            }
            this.labIntegral.setForeground(Color.white);
            this.labIntegral.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labIntegral);
        }
        return this.labIntegral;
    }
    
    public void setLabIntegral(JLabel labIntegral) {
        this.labIntegral = labIntegral;
    }
    
    public int getChooseNum() {
        return this.ChooseNum;
    }
    
    public void setChooseNum(int chooseNum) {
        this.ChooseNum = chooseNum;
    }
    
    public ImageIcon getIconExp() {
        return this.iconExp;
    }
    
    public void setIconExp(ImageIcon iconExp) {
        this.iconExp = iconExp;
    }
    
    public int getNumExp() {
        return this.numExp;
    }
    
    public void setNumExp(int numExp) {
        this.numExp = numExp;
    }
    
    public JLabel getLabxianyu() {
        if (this.labxianyu == null) {
            this.labxianyu = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labxianyu.setBounds(418, 303, 109, 14);
            }
            else {
                this.labxianyu.setBounds(418, 303, 109, 14);
            }
            this.labxianyu.setForeground(Color.white);
            this.labxianyu.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labxianyu);
        }
        return this.labxianyu;
    }
    
    public void setLabxianyu(JLabel labxianyu) {
        this.labxianyu = labxianyu;
    }
    
    public int getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(int moneyType) {
        this.moneyType = moneyType;
    }
    
    public ImageIcon getIconMoney() {
        return this.iconMoney;
    }
    
    public void setIconMoney(ImageIcon iconMoney) {
        this.iconMoney = iconMoney;
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
            if (this.num >= RoleProperty.getRoleProperty().meridiansVector.size()) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("X1" + (this.num + 1)));
                return;
            }
            if (CharacterMeridiansMainJpanel.this.ChooseNum != -1) {
                CharacterMeridiansMainJpanel.this.labMeridians[CharacterMeridiansMainJpanel.this.ChooseNum].setIcon(CutButtonImage.getImage("img/soaringSkill/dot.png", -1, -1));
            }
            CharacterMeridiansMainJpanel.this.ChooseNum = this.num;
            BaseMeridians baseMeridians = (BaseMeridians)RoleProperty.getRoleProperty().meridiansVector.get(CharacterMeridiansMainJpanel.this.ChooseNum);
            if (baseMeridians != null) {
                CharacterMeridiansMainJpanel.this.refreshMeridians(baseMeridians);
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            MsgJframe.getJframe().getJapnel().JM(CharacterMeridiansMainJpanel.this.getMeridians(this.num), CharacterMeridiansMainJpanel.this.labValue[this.num].getText());
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            FormsManagement.HideForm(46);
        }
    }
    
    class LockListen implements MouseListener
    {
        JLabel label;
        
        public LockListen(JLabel label) {
            this.label = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (CharacterMeridiansMainJpanel.this.meridians == null) {
                return;
            }
            if (this.label.getIcon() == null) {
                this.label.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                if (CharacterMeridiansMainJpanel.this.lockAttr.getIcon() != null) {
                    CharacterMeridiansMainJpanel.this.lockAttrMsg = "锁定属性花费" + CharacterMeridiansMainJpanel.this.meridians.getLockAttr() + "仙玉";
                }
                if (CharacterMeridiansMainJpanel.this.lockQuality.getIcon() != null) {
                    CharacterMeridiansMainJpanel.this.lockQualityMsg = "锁定品质花费" + CharacterMeridiansMainJpanel.this.meridians.getLockQuality() + "仙玉";
                }
            }
            else {
                this.label.setIcon(null);
                if (CharacterMeridiansMainJpanel.this.lockAttr.getIcon() == null) {
                    CharacterMeridiansMainJpanel.this.lockAttrMsg = null;
                }
                if (CharacterMeridiansMainJpanel.this.lockQuality.getIcon() == null) {
                    CharacterMeridiansMainJpanel.this.lockQualityMsg = null;
                }
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
