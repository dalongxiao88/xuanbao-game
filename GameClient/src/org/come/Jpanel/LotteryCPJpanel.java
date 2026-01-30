package org.come.Jpanel;

import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.UserData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.socket.DownLoadTxt;
import org.come.Frame.ZhuFrame;
import java.awt.Graphics;
import java.util.Calendar;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.NumberValidator;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Map;
import java.util.Set;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JPanel;

public class LotteryCPJpanel extends JPanel
{
    private static RoleCaoZuoBtn numbtn;
    private static RoleCaoZuoBtn emptynum;
    private static RoleCaoZuoBtn refreshbtn;
    private static RoleCaoZuoBtn purchasebtn;
    private static JLabel labtext5;
    private JLabel labtext1;
    private JLabel labtext2;
    private JLabel labtext3;
    private JLabel labtext4;
    private JLabel labtext6;
    private JLabel labtext7;
    private JLabel labtext8;
    private JLabel labtext9;
    private JLabel labtext10;
    private static JTextField findName;
    private static JTextField findName1;
    private Set<Integer> days;
    private Set<Integer> lqs;
    private static String numbers;
    public static String previousmsg;
    public static String previousmsg1;
    public static String previousmsg2;
    public static String[] previousmsg3;
    private Map<String, String> map;
    private JScrollPane jScrollPane;
    private static JList<String> listPank;
    private static DefaultListModel<String> pankModel;
    public static String[] zodiac;
    private static long time;
    private ImageIcon icon;
    
    public LotteryCPJpanel() {
        this.days = new HashSet<>();
        this.lqs = new HashSet<>();
        this.map = new HashMap<>();
        this.icon = CutButtonImage.getImage("inkImg/background/jcdt.png", 605, 400);
        this.setPreferredSize(new Dimension(605, 400));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/background/gift-close-btn.png", 1, 1105);
        offBtn.setBounds(570, 20, 18, 18);
        this.add(offBtn);
        (this.labtext1 = new JLabel()).setForeground(Color.BLACK);
        this.labtext1.setFont(UIUtils.TEXT_FONTZSRL);
        this.labtext1.setBounds(257, 5, 150, 50);
        this.labtext1.setText("全民竞猜");
        this.add(this.labtext1);
        (this.labtext2 = new JLabel()).setForeground(Color.BLACK);
        this.labtext2.setFont(UIUtils.TEXT_FONTZS);
        this.labtext2.setText("往期开奖");
        this.labtext2.setBounds(187, 50, 150, 50);
        this.add(this.labtext2);
        Font font = new Font("宋体", 0, 14);
        (this.labtext3 = new JLabel()).setForeground(Color.BLACK);
        this.labtext3.setFont(font);
        this.labtext3.setText("已选生肖：");
        this.labtext3.setBounds(35, 308, 150, 50);
        this.add(this.labtext3);
        (this.labtext4 = new JLabel()).setForeground(Color.BLACK);
        this.labtext4.setFont(font);
        this.labtext4.setText("购买金额：");
        this.labtext4.setBounds(216, 308, 150, 50);
        this.add(this.labtext4);
        font = new Font("小篆", 1, 18);
        (LotteryCPJpanel.labtext5 = new JLabel()).setForeground(Color.BLACK);
        LotteryCPJpanel.labtext5.setFont(font);
        LotteryCPJpanel.labtext5.setText("");
        LotteryCPJpanel.labtext5.setBounds(106, 308, 150, 50);
        this.add(LotteryCPJpanel.labtext5);
        (LotteryCPJpanel.findName = new JTextField()).setDocument(new NumberValidator(10));
        LotteryCPJpanel.findName.setBounds(285, 325, 128, 18);
        LotteryCPJpanel.findName.setOpaque(false);
        LotteryCPJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
        LotteryCPJpanel.findName.setForeground(Color.black);
        LotteryCPJpanel.findName.setCaretColor(Color.black);
        LotteryCPJpanel.findName.setFont(new Font("宋体", 0, 15));
        this.add(LotteryCPJpanel.findName);
        (this.jScrollPane = new JScrollPane(LotteryCPJpanel.listPank)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(430, 57, 153, 287);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        String str = "<html>玩家每次可选择3个生肖，可重复选择同一个生肖，如果猜中生肖，将获得奖励，系统每10分钟开一奖，详细中奖规则请咨询【大话精灵】！</html>";
        font = new Font("楷体", 0, 16);
        (this.labtext7 = new JLabel()).setForeground(Color.BLACK);
        this.labtext7.setFont(font);
        this.labtext7.setText(str);
        this.labtext7.setBounds(40, 128, 370, 150);
        this.add(this.labtext7);
        font = new Font("楷体", 0, 14);
        (this.labtext8 = new JLabel()).setForeground(Color.red);
        this.labtext8.setFont(font);
        this.labtext8.setText(LotteryCPJpanel.previousmsg);
        this.labtext8.setBounds(40, 25, 380, 150);
        this.add(this.labtext8);
        (this.labtext9 = new JLabel()).setForeground(Color.red);
        this.labtext9.setFont(font);
        this.labtext9.setText(LotteryCPJpanel.previousmsg1);
        this.labtext9.setBounds(40, 45, 380, 150);
        this.add(this.labtext9);
        (this.labtext10 = new JLabel()).setForeground(Color.red);
        this.labtext10.setFont(font);
        this.labtext10.setText(LotteryCPJpanel.previousmsg2);
        this.labtext10.setBounds(40, 65, 380, 150);
        this.add(this.labtext10);
        font = new Font("小篆", 1, 20);
        for (int i = 0; i < LotteryCPJpanel.zodiac.length; ++i) {
            (LotteryCPJpanel.numbtn = new RoleCaoZuoBtn("inkImg/background/numbtn.png", 1, UIUtils.COLOR_BTNPUTONG, font, LotteryCPJpanel.zodiac[i] + "", 1105, "")).setFont(font);
            if (i > 5) {
                LotteryCPJpanel.numbtn.setBounds(70 + (i - 6) * 50, 280, 35, 35);
            }
            else {
                LotteryCPJpanel.numbtn.setBounds(70 + i * 50, 240, 35, 35);
            }
            this.add(LotteryCPJpanel.numbtn);
        }
        font = new Font("楷体", 1, 16);
        (LotteryCPJpanel.emptynum = new RoleCaoZuoBtn("inkImg/background/cxbtn.png", 1, UIUtils.COLOR_BTNPUTONG, font, "重选", 1105)).setBounds(130, 350, 70, 35);
        this.add(LotteryCPJpanel.emptynum);
        font = new Font("楷体", 1, 16);
        (LotteryCPJpanel.refreshbtn = new RoleCaoZuoBtn("inkImg/background/cxbtn.png", 1, UIUtils.COLOR_BTNPUTONG, font, "刷新", 1105)).setBounds(430, 350, 70, 35);
        this.add(LotteryCPJpanel.refreshbtn);
        (LotteryCPJpanel.purchasebtn = new RoleCaoZuoBtn("inkImg/background/cxbtn.png", 1, UIUtils.COLOR_BTNPUTONG, font, "购买", 1105)).setBounds(250, 350, 70, 35);
        this.add(LotteryCPJpanel.purchasebtn);
        Calendar now = Calendar.getInstance();
        int year = now.get(1);
        int month = now.get(2) + 1;
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
        g.drawImage(this.icon.getImage(), 0, 0, 605, 400, this);
        this.labtext8.setText(LotteryCPJpanel.previousmsg);
        this.labtext9.setText(LotteryCPJpanel.previousmsg1);
        this.labtext10.setText(LotteryCPJpanel.previousmsg2);
    }
    
    public static void ChoiceNumber(String num) {
        if (LotteryCPJpanel.numbers.length() > 2) {
            ZhuFrame.getZhuJpanel().addPrompt2("最多可以选择3个号！");
            return;
        }
        LotteryCPJpanel.numbers += num;
        LotteryCPJpanel.labtext5.setText(LotteryCPJpanel.numbers);
    }
    
    public static void emptyNumber() {
        LotteryCPJpanel.numbers = "";
        LotteryCPJpanel.labtext5.setText(LotteryCPJpanel.numbers);
    }
    
    public static void purchaseNumber() {
        DownLoadTxt.getDownLoadTxt().initMes("configure.txt");
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getQmjckg().equals("关")) {
            ZhuFrame.getZhuJpanel().addPrompt2("购买通道暂时关闭，请等待通知！");
            return;
        }
        if (LotteryCPJpanel.numbers.length() != 3) {
            ZhuFrame.getZhuJpanel().addPrompt2("必须选择3个号码！");
            return;
        }
        if (LotteryCPJpanel.numbers.length() > 0 && LotteryCPJpanel.findName.getText().length() > 0) {
            if (Integer.parseInt(LotteryCPJpanel.findName.getText()) < 200000 || Integer.parseInt(LotteryCPJpanel.findName.getText()) % 100 != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("最低购买20W两！并且是100的倍数！");
                return;
            }
            if (UserData.uptael(Long.parseLong(LotteryCPJpanel.findName.getText()))) {
                String msg = "QMJC=" + LotteryCPJpanel.numbers + "|" + LotteryCPJpanel.findName.getText();
                SendMessageUntil.toServer(Agreement.getAgreement().LotteryCPAgreement(msg));
            }
        }
        else {
            if (LotteryCPJpanel.numbers.equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择号码！");
                return;
            }
            if (LotteryCPJpanel.findName.getText().equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("请输入金额！");
                return;
            }
        }
    }
    
    public Set<Integer> getDays() {
        return this.days;
    }
    
    public void setDays(Set<Integer> days) {
        this.days = days;
    }
    
    public Set<Integer> getLqs() {
        return this.lqs;
    }
    
    public void setLqs(Set<Integer> lqs) {
        this.lqs = lqs;
    }
    
    public Map<String, String> getMap() {
        return this.map;
    }
    
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
    public static void getlisysta() {
        LotteryCPJpanel.pankModel = new DefaultListModel<>();
        for (int i = LotteryCPJpanel.previousmsg3.length - 1; i >= 0; --i) {
            (LotteryCPJpanel.listPank = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            LotteryCPJpanel.listPank.setSelectionForeground(Color.yellow);
            LotteryCPJpanel.listPank.setForeground(Color.black);
            LotteryCPJpanel.listPank.setFont(new Font("宋体", 0, 12));
            LotteryCPJpanel.listPank.setBackground(new Color(0, 0, 0, 0));
            LotteryCPJpanel.listPank.setFixedCellHeight(25);
            LotteryCPJpanel.pankModel.addElement(LotteryCPJpanel.previousmsg3[i]);
            LotteryCPJpanel.listPank.setModel(LotteryCPJpanel.pankModel);
        }
    }
    
    public void getjScrollPane() {
        (this.jScrollPane = new JScrollPane(LotteryCPJpanel.listPank)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(435, 57, 148, 287);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
    }
    
    public void deleteScrollPane() {
        this.remove(this.jScrollPane);
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public static void refresh() {
        long time2 = System.currentTimeMillis();
        if (time2 - LotteryCPJpanel.time < 300000L) {
            ZhuFrame.getZhuJpanel().addPrompt("5分钟内之能刷新一次！");
            return;
        }
        LotteryCPJpanel.time = time2;
        SendMessageUntil.toServer(Agreement.getAgreement().LotteryCPAgreement("QPEN"));
    }
    
    static {
        LotteryCPJpanel.numbers = "";
        LotteryCPJpanel.previousmsg = "";
        LotteryCPJpanel.previousmsg1 = "";
        LotteryCPJpanel.previousmsg2 = "";
        LotteryCPJpanel.previousmsg3 = new String[0];
        LotteryCPJpanel.zodiac = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
    }
}
