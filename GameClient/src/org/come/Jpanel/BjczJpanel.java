package org.come.Jpanel;

import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import org.come.entity.Goodstable;
import javax.swing.JTextField;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BjczJpanel extends JPanel
{
    private JLabel labtext;
    private JLabel labtext1;
    private JLabel labtext2;
    private static FormsOnOffBtn palBtn;
    private static FormsOnOffBtn arenaBtn;
    private static FormsOnOffBtn TJBtn1;
    private static FormsOnOffBtn zhuanshen;
    private static FormsOnOffBtn zaBtn;
    private static FormsOnOffBtn byBtn;
    private static FormsOnOffBtn ypBtn;
    private static FormsOnOffBtn lsBtn;
    private static FormsOnOffBtn xqbtn;
    private static FormsOnOffBtn sbbtn;
    private static FormsOnOffBtn bsbtn;
    private static FormsOnOffBtn rwzsbtn;
    private static FormsOnOffBtn spbtn;
    private static FormsOnOffBtn tzbtn;
    private static FormsOnOffBtn shenshoubtn;
    private static FormsOnOffBtn wupinbtn;
    private static FormsOnOffBtn richang;
    private static FormsOnOffBtn huangdx;
    private static FormsOnOffBtn neidanbtn;
    private static FormsOnOffBtn jinengbtn;
    private static FormsOnOffBtn ksbtn;
    private static FormsOnOffBtn zbbtn;
    private static FormsOnOffBtn wybtn;
    private static FormsOnOffBtn sgbtn;
    private static FormsOnOffBtn ssdj;
    private static FormsOnOffBtn dzbs;
    private static FormsOnOffBtn hcbs;
    private static FormsOnOffBtn gzcdh;
    private static FormsOnOffBtn lscunz;
    private static FormsOnOffBtn hhsr1;
    private static FormsOnOffBtn hzsd;
    private static FormsOnOffBtn slsd;
    private static FormsOnOffBtn xfsd;
    private static FormsOnOffBtn dnsd;
    private static FormsOnOffBtn bssd;
    private static FormsOnOffBtn dgsd;
    private static FormsOnOffBtn ytsd;
    private static FormsOnOffBtn fssd;
    private static FormsOnOffBtn jdlb;
    private static FormsOnOffBtn guajizs;
    private static FormsOnOffBtn guajizs1;
    private static JTextField findName;
    private static JTextField findName1;
    private Goodstable good;
    private static String[] Fvalue;
    private static JTextArea testMes;
    public String npczb;
    public static String npcfeiji;
    private ImageIcon icon;
    private ImageIcon icon2;
    
    public BjczJpanel() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        this.setPreferredSize(new Dimension(764, 435));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 3000);//关闭按钮
        offBtn.setBounds(692, 35, 22, 21);
        this.add(offBtn);
        (BjczJpanel.palBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "伙伴助战", 105)).setBounds(135, 75, 100, 30);
        this.add(BjczJpanel.palBtn);
        (BjczJpanel.xqbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "仙器打造", 4000)).setBounds(254, 109, 100, 30);
        this.add(BjczJpanel.xqbtn);
        (BjczJpanel.sbbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "神兵打造", 4001)).setBounds(254, 177, 100, 30);
        this.add(BjczJpanel.sbbtn);
        (BjczJpanel.ssdj = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "神兽大将", 9999)).setBounds(254, 211, 100, 30);
        this.add(BjczJpanel.ssdj);
        (BjczJpanel.rwzsbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "人物转生", 4003)).setBounds(373, 143, 100, 30);
        this.add(BjczJpanel.rwzsbtn);
        (BjczJpanel.richang = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_HONG, UIUtils.TEXT_MSG1, "活跃领取", 40031)).setBounds(373, 177, 100, 30);
        this.add(BjczJpanel.richang);
        (BjczJpanel.zhuanshen = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "宝宝转生", 999)).setBounds(373, 109, 100, 30);
        this.add(BjczJpanel.zhuanshen);
        (BjczJpanel.spbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "饰品打造", 4004)).setBounds(254, 75, 100, 30);
        this.add(BjczJpanel.spbtn);
        (BjczJpanel.tzbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "套装打造", 4005)).setBounds(254, 143, 100, 30);
        this.add(BjczJpanel.tzbtn);
        (BjczJpanel.shenshoubtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "神兽合成", 4006)).setBounds(373, 75, 100, 30);
        this.add(BjczJpanel.shenshoubtn);
        (BjczJpanel.wupinbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "物品兑换", 4007)).setBounds(135, 143, 100, 30);
        this.add(BjczJpanel.wupinbtn);
        (BjczJpanel.TJBtn1 = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "物品典当", 29)).setBounds(135, 177, 100, 30);
        this.add(BjczJpanel.TJBtn1);
        (BjczJpanel.arenaBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "物品赎回", 1024)).setBounds(135, 211, 100, 30);
        this.add(BjczJpanel.arenaBtn);
        (BjczJpanel.lsBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "双倍领取", 81)).setBounds(135, 109, 100, 30);
        this.add(BjczJpanel.lsBtn);
        (BjczJpanel.wybtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "超级巫医", 80)).setBounds(373, 245, 100, 30);
        this.add(BjczJpanel.wybtn);
        (BjczJpanel.dzbs = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "宝石打造", 84)).setBounds(135, 245, 100, 30);
        this.add(BjczJpanel.dzbs);
        (BjczJpanel.hcbs = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "宝石合成", 85)).setBounds(254, 245, 100, 30);
        this.add(BjczJpanel.hcbs);
        (BjczJpanel.jdlb = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "酒店老板", 83)).setBounds(373, 211, 100, 30);
        this.add(BjczJpanel.jdlb);
        (BjczJpanel.guajizs = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "挂机助手", 637)).setBounds(135, 279, 100, 30);
        this.add(BjczJpanel.guajizs);
        (BjczJpanel.guajizs1 = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "新增副本", 3111)).setBounds(135, 279+34, 100, 30);
        this.add(BjczJpanel.guajizs1);


        (BjczJpanel.lscunz = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "灵兽村长", 400302)).setBounds(254, 279, 100, 30);
        this.add(BjczJpanel.lscunz);
        (BjczJpanel.huangdx = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_HONG, UIUtils.TEXT_MSG1, "种族转换", 400301)).setBounds(373, 279, 100, 30);
        this.add(BjczJpanel.huangdx);
        (BjczJpanel.zaBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "杂货商店", 79)).setBounds(505, 75, 100, 30);
        this.add(BjczJpanel.zaBtn);
        (BjczJpanel.ypBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "药品商店", 131)).setBounds(624, 75, 100, 30);
        this.add(BjczJpanel.ypBtn);
        (BjczJpanel.neidanbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "内丹商店", 4008)).setBounds(505, 109, 100, 30);
        this.add(BjczJpanel.neidanbtn);
        (BjczJpanel.jinengbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "技能商店", 4009)).setBounds(624, 109, 100, 30);
        this.add(BjczJpanel.jinengbtn);
        (BjczJpanel.ksbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "矿石商店", 4010)).setBounds(505, 143, 100, 30);
        this.add(BjczJpanel.ksbtn);
        (BjczJpanel.zbbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "装备商店", 4011)).setBounds(624, 143, 100, 30);
        this.add(BjczJpanel.zbbtn);
        (BjczJpanel.byBtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "符文商店", 77)).setBounds(505, 177, 100, 30);
        this.add(BjczJpanel.byBtn);
        (BjczJpanel.wybtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "超级巫医", 80)).setBounds(624, 177, 100, 30);
        this.add(BjczJpanel.wybtn);
        (BjczJpanel.sgbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "师贡商店", 129)).setBounds(505, 211, 100, 30);
        this.add(BjczJpanel.sgbtn);
        (BjczJpanel.slsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "水陆商店", 126)).setBounds(624, 177, 100, 30);
        this.add(BjczJpanel.slsd);
        (BjczJpanel.xfsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "寻访商店", 123)).setBounds(624, 245, 100, 30);
        this.add(BjczJpanel.xfsd);
        (BjczJpanel.dnsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "大闹商店", 128)).setBounds(624, 211, 100, 30);
        this.add(BjczJpanel.dnsd);
        (BjczJpanel.bssd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "宝石商店", 851)).setBounds(624, 313, 100, 30);
        (BjczJpanel.dgsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "地宫商店", 121)).setBounds(505, 245, 100, 30);
        this.add(BjczJpanel.dgsd);
        (BjczJpanel.ytsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "雁塔商店", 120)).setBounds(505, 279, 100, 30);
        this.add(BjczJpanel.ytsd);
        (BjczJpanel.fssd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "符石商店", 180)).setBounds(624, 279, 100, 30);
        this.add(BjczJpanel.fssd);
        (BjczJpanel.gzcdh = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "孤竹商店", 887)).setBounds(505, 313, 100, 30);
        this.add(BjczJpanel.gzcdh);
        (BjczJpanel.hzsd = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "孩子物品", 184)).setBounds(505, 347, 100, 30);
        this.add(BjczJpanel.hzsd);
        (BjczJpanel.hhsr1 = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "飞行器", 185)).setBounds(624, 313, 100, 30);
        this.add(BjczJpanel.hhsr1);
        (BjczJpanel.zbbtn = new FormsOnOffBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_Pack1, UIUtils.TEXT_MSG1, "副本积分", 8900)).setBounds(624, 347, 100, 30);
        this.add(BjczJpanel.zbbtn);
    }
    
    private static void BjczJpanel() {
    }
    
    private static void BjczJpanel(List<Map<String, Object>> dataNpcMapList) {
    }
    
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.icon = CutButtonImage.getImage("inkImg/background1/bjcz1.png", 795, 449);
        g.drawImage(this.icon.getImage(), 0, 0, 795, 449, this);
    }
    
    public static JTextField getFindName() {
        return BjczJpanel.findName;
    }
    
    public static void setFindName(JTextField findName) {
        BjczJpanel.findName = findName;
    }
    
    public static JTextField getFindName1() {
        return BjczJpanel.findName1;
    }
    
    public static void setFindName1(JTextField findName1) {
        BjczJpanel.findName1 = findName1;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return BjczJpanel.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        BjczJpanel.Fvalue = fvalue;
    }
    
    public JTextArea getTestMes() {
        return BjczJpanel.testMes;
    }
    
    public void setTestMes(JTextArea testMes) {
        BjczJpanel.testMes = testMes;
    }
}
