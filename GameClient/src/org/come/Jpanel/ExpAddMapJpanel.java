package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.Util;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class ExpAddMapJpanel extends JPanel
{
    private static JTextField findName1;
    private static JLabel time;
    private static JLabel exp;
    private static RoleCaoZuoBtn sureGive;
    private Goodstable good;
    private static String[] Fvalue;
    public String npczb;
    private ImageIcon icon;
    
    public ExpAddMapJpanel() {
        this.icon = CutButtonImage.getImage("inkImg/background/paodian.png", 684, 455);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String[] expAdd = configure.getExpAdd().split("\\|");
        this.setPreferredSize(new Dimension(684, 455));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/background/gift-close-btn.png", 1, 3003);
        offBtn.setBounds(629, 25, 18, 18);
        this.add(offBtn);
        (ExpAddMapJpanel.sureGive = new RoleCaoZuoBtn("inkImg/background/paodian-btn.png", 1, "", 30031, UIUtils.COLOR_BTNPUTONG)).setBounds(90, 395, 100, 19);
        this.add(ExpAddMapJpanel.sureGive);
        (ExpAddMapJpanel.sureGive = new RoleCaoZuoBtn("inkImg/background/paodian-btn.png", 1, "", 30032, UIUtils.COLOR_BTNPUTONG)).setBounds(295, 395, 100, 19);
        this.add(ExpAddMapJpanel.sureGive);
        (ExpAddMapJpanel.sureGive = new RoleCaoZuoBtn("inkImg/background/paodian-btn.png", 1, "", 30033, UIUtils.COLOR_BTNPUTONG)).setBounds(500, 395, 100, 19);
        this.add(ExpAddMapJpanel.sureGive);
        (ExpAddMapJpanel.time = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.time.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.time.setBounds(77, 159, 20, 20);
        ExpAddMapJpanel.time.setText(expAdd[0]);
        this.add(ExpAddMapJpanel.time);
        (ExpAddMapJpanel.time = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.time.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.time.setBounds(284, 159, 20, 20);
        ExpAddMapJpanel.time.setText(expAdd[0]);
        this.add(ExpAddMapJpanel.time);
        (ExpAddMapJpanel.time = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.time.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.time.setBounds(546, 159, 20, 20);
        ExpAddMapJpanel.time.setText(expAdd[0]);
        this.add(ExpAddMapJpanel.time);
        (ExpAddMapJpanel.exp = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.exp.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.exp.setBounds(110, 207, 120, 20);
        ExpAddMapJpanel.exp.setText(Long.parseLong(expAdd[1]) * 2L + "");
        this.add(ExpAddMapJpanel.exp);
        (ExpAddMapJpanel.exp = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.exp.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.exp.setBounds(320, 207, 120, 20);
        ExpAddMapJpanel.exp.setText(expAdd[1]);
        this.add(ExpAddMapJpanel.exp);
        (ExpAddMapJpanel.exp = new JLabel()).setForeground(Color.red);
        ExpAddMapJpanel.exp.setFont(UIUtils.TEXT_FONT22);
        ExpAddMapJpanel.exp.setBounds(580, 207, 120, 20);
        ExpAddMapJpanel.exp.setText(Long.parseLong(expAdd[1]) / 10L + "");
        this.add(ExpAddMapJpanel.exp);
    }
    
    public static void iWantToFlyGo(int index) {
        if (index == 30031) {
            Util.pdtype = "3";
            Util.iWantToFly("3344,仙玉泡点,898,698");
        }
        else if (index == 30032) {
            Util.pdtype = "2";
            Util.iWantToFly("3342,金币泡点,978,697");
        }
        else if (index == 30033) {
            Util.pdtype = "1";
            Util.iWantToFly("1207,长安城,4960,3820");
        }
        else if (index == 30034) {
            Util.pdtype = "0";
            Util.hdzjy = Long.valueOf(0L);
            Util.xfzje = Long.valueOf(0L);
            Util.ifpf = false;
            Util.iWantToFly("1207,长安城,4960,3820");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, 684, 455, this);
    }
    
    public static JTextField getFindName1() {
        return ExpAddMapJpanel.findName1;
    }
    
    public static void setFindName1(JTextField findName1) {
        ExpAddMapJpanel.findName1 = findName1;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return ExpAddMapJpanel.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        ExpAddMapJpanel.Fvalue = fvalue;
    }
}
