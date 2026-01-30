package org.come.Jpanel;

import org.come.mouslisten.WLLMouslisten;
import com.tool.role.RoleData;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.awt.Graphics;
import org.come.until.DeviceEshopUntil;
import java.util.ArrayList;
import org.come.model.Eshop;
import java.util.List;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.SevenTwoChangesBtn;
import com.tool.btn.TextBtn;
import javax.swing.JLabel;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JPanel;

public class TaobaoCourtMainJpanel extends JPanel
{
    private TaoBaoBtn shopMenuBtn;
    private TaoBaoBtn taobaoMenuBtn;
    private TaoBaoBtn restrictionMenuBtn;
    private TaoBaoBtn monthCardBtn;
    private TaoBaoBtn vipMenuBtn;
    private JLabel jadeLab;
    private JLabel jadeNum;
    private JLabel jfNum;
    private JLabel jflab;
    private JLabel zqblab;
    private JLabel zqbNum;
    private JLabel smlab;
    private TextBtn changeXY;
    private SevenTwoChangesBtn selectBtn;
    private TaobaoCourtCardJpanel taobaoCourtCardJpanel;
    private ImageIcon icon;
    
    public TaobaoCourtMainJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(682, 475));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 39);
            offBtn.setBounds(645, 10, 25, 25);
            this.add(offBtn);
            this.taobaoCourtCardJpanel = new TaobaoCourtCardJpanel();
            this.getShopMenuBtn();
            this.getTaobaoMenuBtn();
            this.add(this.getJadeNum());
            this.add(this.getJadeLab());
            this.add(this.getjfNum());
            this.add(this.getjflab());
            this.add(this.getzqbNum());
            this.add(this.getzqblab());
            this.getChangeXY();
            this.add(this.taobaoCourtCardJpanel);
        }
        else {
            this.setPreferredSize(new Dimension(732, 480));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 39);
            offBtn.setBounds(707, 0, 25, 25);
            this.add(offBtn);
            this.taobaoCourtCardJpanel = new TaobaoCourtCardJpanel();
            this.getShopMenuBtn();
            this.getTaobaoMenuBtn();
            this.add(this.getJadeNum());
            this.add(this.getJadeLab());
            this.add(this.getjfNum());
            this.add(this.getjflab());
            this.add(this.getzqbNum());
            this.add(this.getzqblab());
            this.getChangeXY();
            this.add(this.taobaoCourtCardJpanel);
        }
    }
    
    public static List<Eshop> returnlEshops(int type) {
        List<Eshop> lEshops = new ArrayList<>();
        if (type == 11) {
            lEshops = DeviceEshopUntil.getShopingTX();
        }
        else if (type == 12) {
            lEshops = DeviceEshopUntil.getShopingZS();
        }
        else if (type == 13) {
            lEshops = DeviceEshopUntil.getShopingZJ();
        }
        return lEshops;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/1.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 682, 475, this);
            g.setFont(UIUtils.TEXT_HURT2);
            g.setColor(new Color(187, 165, 75));
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu1/shangchengshouw.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 732, 480, this);
            g.setFont(UIUtils.TEXT_HURT2);
            g.setColor(new Color(187, 165, 75));
        }
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public TaobaoCourtCardJpanel getTaobaoCourtCardJpanel() {
        return this.taobaoCourtCardJpanel;
    }
    
    public void setTaobaoCourtCardJpanel(TaobaoCourtCardJpanel taobaoCourtCardJpanel) {
        this.taobaoCourtCardJpanel = taobaoCourtCardJpanel;
    }
    
    public TaoBaoBtn getShopMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.shopMenuBtn == null) {
                (this.shopMenuBtn = new TaoBaoBtn("inkImg/button/B263.png", 1, "", 2, this)).setBounds(60, 19, 75, 33);
                this.add(this.shopMenuBtn);
            }
        }
        else if (this.shopMenuBtn == null) {
            (this.shopMenuBtn = new TaoBaoBtn("img/xy2uiimg/一级选项卡_商城_选中_w80,h120.png", 1, "", 2, this)).setBounds(60, 27, 80, 40);
            this.add(this.shopMenuBtn);
        }
        return this.shopMenuBtn;
    }
    
    public void setShopMenuBtn(TaoBaoBtn shopMenuBtn) {
        this.shopMenuBtn = shopMenuBtn;
    }
    
    public TaoBaoBtn getTaobaoMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.taobaoMenuBtn == null) {
                (this.taobaoMenuBtn = new TaoBaoBtn("inkImg/button/B260.png", 1, "", 3, this)).setBounds(135, 19, 75, 33);
                this.add(this.taobaoMenuBtn);//锦绣
            }
        }
        else if (this.taobaoMenuBtn == null) {
            (this.taobaoMenuBtn = new TaoBaoBtn("img/xy2uiimg/一级选项卡_锦绣_未选中_w80,h120.png", 1, "", 3, this)).setBounds(132, 27, 80, 40);
            this.add(this.taobaoMenuBtn);
        }
        return this.taobaoMenuBtn;
    }
    
    public void setTaobaoMenuBtn(TaoBaoBtn taobaoMenuBtn) {
        this.taobaoMenuBtn = taobaoMenuBtn;
    }
    
    public TaoBaoBtn getRestrictionMenuBtn() {
        if (this.restrictionMenuBtn == null) {
            (this.restrictionMenuBtn = new TaoBaoBtn("img/xy2uiimg/一级选项卡_限购_未选中_w80,h120.png", 1, "", 4, this)).setBounds(224, 19, 80, 40);
            this.add(this.restrictionMenuBtn);
            this.restrictionMenuBtn.setVisible(false);
        }
        return this.restrictionMenuBtn;
    }
    
    public void setRestrictionMenuBtn(TaoBaoBtn restrictionMenuBtn) {
        this.restrictionMenuBtn = restrictionMenuBtn;
    }
    
    public JLabel getJadeLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jadeLab == null) {
                (this.jadeLab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/Xy1.png", -1, -1));//仙玉显示
                this.jadeLab.setOpaque(false);
                this.jadeLab.setBounds(60, 435, 124, 19);
            }
        }
        else if (this.jadeLab == null) {
            (this.jadeLab = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/border_quac1k.png", -1, -1));
            this.jadeLab.setOpaque(false);
            this.jadeLab.setBounds(45, 435, 124, 19);
        }
        return this.jadeLab;
    }
    
    public void setJadeLab(JLabel jadeLab) {
        this.jadeLab = jadeLab;
    }
    
    public JLabel getJadeNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jadeNum == null) {
                (this.jadeNum = new JLabel(RoleData.getRoleData().getLoginResult().getCodecard() + "")).setBounds(103, 437, 80, 15);
                this.jadeNum.setOpaque(false);
                this.jadeNum.setForeground(Color.white);
                this.jadeNum.setFont(UIUtils.TEXT_COM_FONT);
            }
        }
        else if (this.jadeNum == null) {
            (this.jadeNum = new JLabel(RoleData.getRoleData().getLoginResult().getCodecard() + "")).setBounds(83, 435, 80, 15);
            this.jadeNum.setOpaque(false);
            this.jadeNum.setForeground(Color.white);
            this.jadeNum.setFont(UIUtils.TEXT_COM_FONT);
        }
        return this.jadeNum;
    }
    
    public void setJadeNum(JLabel jadeNum) {
        this.jadeNum = jadeNum;
    }
    
    public JLabel getjfNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jfNum == null) {
                (this.jfNum = new JLabel(RoleData.getRoleData().getLoginResult().getMoney().toString())).setBounds(538, 16, 120, 15);
                this.jfNum.setOpaque(false);
                this.jfNum.setForeground(Color.white);
                this.jfNum.setFont(UIUtils.TEXT_COM_FONT);
            }
        }
        else if (this.jfNum == null) {
            (this.jfNum = new JLabel(RoleData.getRoleData().getLoginResult().getMoney().toString())).setBounds(270, 435, 120, 15);
            this.jfNum.setOpaque(false);
            this.jfNum.setVisible(true);
            this.jfNum.setForeground(Color.white);
            this.jfNum.setFont(UIUtils.TEXT_COM_FONT);
        }
        return this.jfNum;
    }
    
    public JLabel getjflab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jflab == null) {
                (this.jflab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/Jf1.png", -1, -1));//积分显示
                this.jflab.setOpaque(false);
                this.jflab.setBounds(480, 13, 145, 19);
            }
        }
        else if (this.jflab == null) {
            (this.jflab = new JLabel("")).setBounds(83, 435, 80, 15);
            this.jflab.setOpaque(false);
            this.jflab.setForeground(Color.white);
            this.jflab.setFont(UIUtils.TEXT_FONT14);
        }
        return this.jflab;
    }
    
    public void setjflab(JLabel jflab) {
        this.jflab = jflab;
    }
    
    public JLabel getzqbNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.zqbNum == null) {
                (this.zqbNum = new JLabel(RoleData.getRoleData().getLoginResult().getTransfergold() + "")).setBounds(538, 35, 120, 20);
                this.zqbNum.setOpaque(false);
                this.zqbNum.setForeground(Color.white);
                this.zqbNum.setFont(UIUtils.TEXT_COM_FONT);
            }
        }
        else if (this.zqbNum == null) {
            (this.zqbNum = new JLabel(RoleData.getRoleData().getLoginResult().getTransfergold() + "")).setBounds(270+172, 433, 120, 20);
            this.zqbNum.setOpaque(false);
            this.zqbNum.setForeground(Color.white);
            this.zqbNum.setFont(UIUtils.TEXT_COM_FONT);
        }
        return this.zqbNum;
    }
    
    public void setzqbNum(JLabel zqbNum) {
        this.zqbNum = zqbNum;
    }
    
    public JLabel getzqblab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.zqblab == null) {
                (this.zqblab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/Szqb.png", -1, -1));//转区币显示
                this.zqblab.setOpaque(false);
                this.zqblab.setBounds(480, 35, 145, 19);
            }
        }
        else if (this.zqblab == null) {
            (this.zqblab = new JLabel("")).setBounds(380, 435, 110, 15);
            this.zqblab.setIcon(CutButtonImage.getImage("inkImg/button/Szqb.png", -1, -1));
            this.zqblab.setOpaque(false);
            this.zqblab.setForeground(Color.white);
            this.zqblab.setFont(UIUtils.TEXT_FONT14);
        }
        return this.zqblab;
    }
    
    public void setzqblab(JLabel zqblab) {
        this.zqblab = zqblab;
    }
    
    public JLabel getSmlab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.smlab == null) {
                (this.smlab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/sm1.png", -1, -1));//说明显示
                this.smlab.setOpaque(false);
                this.smlab.setBounds(625, 36, 34, 17);
                this.smlab.addMouseListener(new WLLMouslisten(501));
            }
        }
        else if (this.smlab == null) {
            (this.smlab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/button/sm2.png", -1, -1));
            this.smlab.setBounds(555, 41, 34, 17);
            this.smlab.setOpaque(false);
            this.smlab.addMouseListener(new WLLMouslisten(501));
        }
        return this.smlab;
    }
    
    public void setSmlab(JLabel smlab) {
        this.smlab = smlab;
    }
    
    public TextBtn getChangeXY() {
        if (this.changeXY == null) {
            (this.changeXY = new TextBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "充值", 888)).setBounds(188, 433, 34, 17);
        }
        return this.changeXY;
    }
    
    public void setChangeXY(TextBtn changeXY) {
        this.changeXY = changeXY;
    }
    
    public TaoBaoBtn getMonthCardBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.monthCardBtn == null) {
                (this.monthCardBtn = new TaoBaoBtn("inkImg/button/B264.png", 1, "", 6, this)).setBounds(210, 19, 75, 33);
                this.add(this.monthCardBtn);
            }
        }
        else if (this.monthCardBtn == null) {
            (this.monthCardBtn = new TaoBaoBtn("img/xy2uiimg/yuekao.png", 1, "", 6, this)).setBounds(204, 27, 80, 40);
            this.add(this.monthCardBtn);
        }
        return this.monthCardBtn;
    }
    
    public void setMonthCardBtn(TaoBaoBtn monthCardBtn) {
        this.monthCardBtn = monthCardBtn;
    }
    
    public TaoBaoBtn getVipMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.vipMenuBtn == null) {
                (this.vipMenuBtn = new TaoBaoBtn("inkImg/button/B258.png", 1, "", 7, this)).setBounds(204, 19, 70, 35);
                this.add(this.vipMenuBtn);
            }
        }
        else if (this.vipMenuBtn == null) {
            (this.vipMenuBtn = new TaoBaoBtn("img/xy2uiimg/vip.png", 1, "", 7, this)).setBounds(204, 27, 80, 40);
            this.add(this.vipMenuBtn);
        }
        return this.vipMenuBtn;
    }
    
    public void setVipMenuBtn(TaoBaoBtn vipMenuBtn) {
        this.vipMenuBtn = vipMenuBtn;
    }
}
