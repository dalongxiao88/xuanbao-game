package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Calendar;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.EquipTool;
import java.util.Iterator;
import java.math.BigDecimal;
import org.come.bean.GMshopItemBean;
import java.util.List;
import org.come.model.GMshopItem;
import java.util.Map;
import org.come.until.UserMessUntil;
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import org.come.until.NumberValidator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import org.come.entity.Goodstable;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GMshopJpanel extends JPanel
{
    private JLabel labtext2;
    private JLabel labtext3;
    private JLabel labtext4;
    private static JLabel labtext5;
    private static JLabel labtext6;
    private static JLabel labtext1;
    private int day;
    private static NPCJpanel npcJpanel;
    private static JScrollPane jScrollPane1;
    private static JScrollPane jScrollPane2;
    private static DefaultListModel<String> pankModel;
    private static DefaultListModel<String> pankModel1;
    private static JList<String> listPank;
    private static JList<String> listPank1;
    private static JTextField findName;
    private static JTextField findName1;
    private static RoleCaoZuoBtn sureGive;
    private static RoleCaoZuoBtn deleteGive;
    private static String itemId;
    private static int number;
    private static String itemName;
    private static boolean is;
    private Goodstable good;
    private static String[] Fvalue;
    private static JTextArea testMes;
    public String npczb;
    public static String npcfeiji;
    private static DefaultTableModel tableModel;
    private ImageIcon icon;
    
    public GMshopJpanel() {
        this.day = 0;
        this.icon = CutButtonImage.getImage("img/xy2uiimg/GMWP.png", 362, 308);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(362, 308));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (GMshopJpanel.findName = new JTextField()).setBounds(138, 95, 128, 18);
            GMshopJpanel.findName.setOpaque(false);
            GMshopJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            GMshopJpanel.findName.setForeground(Color.white);
            GMshopJpanel.findName.setCaretColor(Color.white);
            GMshopJpanel.findName.setFont(new Font("宋体", 0, 15));
            this.add(GMshopJpanel.findName);
            (GMshopJpanel.findName1 = new JTextField()).setDocument(new NumberValidator(2));
            GMshopJpanel.findName1.setBounds(138, 164, 128, 18);
            GMshopJpanel.findName1.setOpaque(false);
            GMshopJpanel.findName1.setBorder(BorderFactory.createEmptyBorder());
            GMshopJpanel.findName1.setForeground(Color.white);
            GMshopJpanel.findName1.setCaretColor(Color.white);
            GMshopJpanel.findName1.setFont(new Font("宋体", 0, 15));
            this.add(GMshopJpanel.findName1);
            Font font = new Font("楷体", 1, 16);
            (GMshopJpanel.labtext5 = new JLabel()).setForeground(Color.BLACK);
            GMshopJpanel.labtext5.setFont(font);
            GMshopJpanel.labtext5.setBounds(70, 78, 250, 50);
            GMshopJpanel.labtext5.setText("物品ID");
            this.add(GMshopJpanel.labtext5);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 2000);
            offBtn.setBounds(337, 0, 25, 25);
            this.add(offBtn);
            font = new Font("楷体", 1, 18);
            (this.labtext3 = new JLabel()).setForeground(Color.BLACK);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(145, -10, 250, 50);
            this.labtext3.setText("物资发送");
            font = new Font("楷体", 1, 16);
            (this.labtext4 = new JLabel()).setForeground(Color.BLACK);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(55, 115, 250, 50);
            this.labtext4.setText("物品名称");
            this.add(this.labtext4);
            (GMshopJpanel.labtext1 = new JLabel()).setForeground(Color.white);
            GMshopJpanel.labtext1.setFont(font);
            GMshopJpanel.labtext1.setBounds(138, 130, 128, 18);
            GMshopJpanel.labtext1.setText("物品名字");
            this.add(GMshopJpanel.labtext1);
            (GMshopJpanel.labtext5 = new JLabel()).setForeground(Color.BLACK);
            GMshopJpanel.labtext5.setFont(font);
            GMshopJpanel.labtext5.setBounds(55, 148, 250, 50);
            GMshopJpanel.labtext5.setText("发送数量");
            this.add(GMshopJpanel.labtext5);
            font = new Font("楷体", 0, 16);
            (GMshopJpanel.labtext6 = new JLabel()).setForeground(Color.red);
            GMshopJpanel.labtext6.setFont(font);
            GMshopJpanel.labtext6.setBounds(60, 25, 250, 50);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            String type = "";
            if (loginResult.getGmshoptype() != null) {
                if (loginResult.getGmshoptype().equals("1")) {
                    type = "体验版";
                }
                else if (loginResult.getGmshoptype().equals("2")) {
                    type = "豪华版";
                }
                else if (loginResult.getGmshoptype().equals("3")) {
                    type = "尊享版";
                }
                else {
                    type = "未开通";
                }
            }
            else {
                type = "未开通";
            }
            GMshopJpanel.labtext6.setText("开通级别：" + type);
            this.add(GMshopJpanel.labtext6);
            font = new Font("楷体", 0, 14);
            (this.labtext2 = new JLabel()).setForeground(Color.BLACK);
            this.labtext2.setFont(font);
            this.labtext2.setBounds(40, 230, 350, 50);
            this.labtext2.setText("提示：物品ID请详查群文件或者咨询群主！");
            this.add(this.labtext2);
            (GMshopJpanel.sureGive = new RoleCaoZuoBtn("inkImg/button/B59.png", 1, "搜索", 2101, UIUtils.COLOR_BLACK)).setBounds(100, 200, 68, 26);
            this.add(GMshopJpanel.sureGive);
            (GMshopJpanel.sureGive = new RoleCaoZuoBtn("inkImg/button/B59.png", 1, "发送", 2102, UIUtils.COLOR_BLACK)).setBounds(220, 200, 68, 26);
            this.add(GMshopJpanel.sureGive);
        }
        else {
            this.setPreferredSize(new Dimension(362, 308));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (GMshopJpanel.findName = new JTextField()).setBounds(140, 97, 128, 18);
            GMshopJpanel.findName.setOpaque(false);
            GMshopJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            GMshopJpanel.findName.setForeground(Color.white);
            GMshopJpanel.findName.setCaretColor(Color.white);
            GMshopJpanel.findName.setFont(new Font("宋体", 0, 15));
            this.add(GMshopJpanel.findName);
            (GMshopJpanel.findName1 = new JTextField()).setDocument(new NumberValidator(2));
            GMshopJpanel.findName1.setBounds(140, 164, 128, 18);
            GMshopJpanel.findName1.setOpaque(false);
            GMshopJpanel.findName1.setBorder(BorderFactory.createEmptyBorder());
            GMshopJpanel.findName1.setForeground(Color.white);
            GMshopJpanel.findName1.setCaretColor(Color.white);
            GMshopJpanel.findName1.setFont(new Font("宋体", 0, 15));
            this.add(GMshopJpanel.findName1);
            Font font = new Font("楷体", 0, 16);
            (GMshopJpanel.labtext5 = new JLabel()).setForeground(Color.YELLOW);
            GMshopJpanel.labtext5.setFont(font);
            GMshopJpanel.labtext5.setBounds(70, 80, 250, 50);
            GMshopJpanel.labtext5.setText("物品ID");
            this.add(GMshopJpanel.labtext5);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 2000);
            offBtn.setBounds(342, 0, 25, 25);
            this.add(offBtn);
            font = new Font("楷体", 1, 18);
            (this.labtext3 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(145, -10, 250, 50);
            this.labtext3.setText("物资发送");
            this.add(this.labtext3);
            font = new Font("楷体", 0, 16);
            (this.labtext4 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(55, 115, 250, 50);
            this.labtext4.setText("物品名称");
            this.add(this.labtext4);
            (GMshopJpanel.labtext1 = new JLabel()).setForeground(Color.white);
            GMshopJpanel.labtext1.setFont(font);
            GMshopJpanel.labtext1.setBounds(140, 130, 128, 18);
            GMshopJpanel.labtext1.setText("物品名字");
            this.add(GMshopJpanel.labtext1);
            (GMshopJpanel.labtext5 = new JLabel()).setForeground(Color.YELLOW);
            GMshopJpanel.labtext5.setFont(font);
            GMshopJpanel.labtext5.setBounds(55, 148, 250, 50);
            GMshopJpanel.labtext5.setText("发送数量");
            this.add(GMshopJpanel.labtext5);
            font = new Font("楷体", 0, 16);
            (GMshopJpanel.labtext6 = new JLabel()).setForeground(Color.green);
            GMshopJpanel.labtext6.setFont(font);
            GMshopJpanel.labtext6.setBounds(60, 39, 250, 50);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            String type = "";
            if (loginResult.getGmshoptype() != null) {
                if (loginResult.getGmshoptype().equals("1")) {
                    type = "体验版";
                }
                else if (loginResult.getGmshoptype().equals("2")) {
                    type = "豪华版";
                }
                else if (loginResult.getGmshoptype().equals("3")) {
                    type = "尊享版";
                }
                else {
                    type = "未开通";
                }
            }
            else {
                type = "未开通";
            }
            GMshopJpanel.labtext6.setText("开通级别：" + type);
            this.add(GMshopJpanel.labtext6);
            font = new Font("楷体", 0, 14);
            (this.labtext2 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext2.setFont(font);
            this.labtext2.setBounds(40, 230, 350, 50);
            this.labtext2.setText("提示：物品ID请详查群文件或者咨询群主！");
            this.add(this.labtext2);
            (GMshopJpanel.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "搜索", 2101, UIUtils.COLOR_BTNPUTONG)).setBounds(100, 200, 68, 26);
            this.add(GMshopJpanel.sureGive);
            (GMshopJpanel.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "发送", 2102, UIUtils.COLOR_BTNPUTONG)).setBounds(220, 200, 68, 26);
            this.add(GMshopJpanel.sureGive);
        }
    }
    
    private static void GMshopJpanel() {
    }
    
    public static String findEnsure() {
        if (GMshopJpanel.findName.getText() == null || GMshopJpanel.findName.getText().length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入物品ID！");
            return null;
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGmshoptype() == null || loginResult.getGmshoptype().equals("")) {
            ZhuFrame.getZhuJpanel().addPrompt2("您还未开通特权请联系GM开通！");
            return null;
        }
        List<Map<String, Object>> dataNpcMapList = new ArrayList<>();
        List<GMshopItem> itemList = new ArrayList<>();
        GMshopItemBean allGMshopItem = UserMessUntil.getAllGMshopItem();
        if (allGMshopItem != null) {
            Map<BigDecimal, GMshopItem> item = allGMshopItem.getAllGMshopItem();
            for (Map.Entry<BigDecimal, GMshopItem> entry : item.entrySet()) {
                System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
                itemList.add(entry.getValue());
            }
        }
        List<GMshopItem> itemTypeList = new ArrayList<>();
        if (loginResult.getGmshoptype() != null && itemList != null && itemList.size() > 0) {
            for (GMshopItem item2 : itemList) {
                if (Integer.parseInt(item2.getShoptype()) <= Integer.parseInt(loginResult.getGmshoptype())) {
                    itemTypeList.add(item2);
                }
            }
        }
        if (itemTypeList != null && itemTypeList.size() > 0) {
            for (GMshopItem item2 : itemTypeList) {
                if (item2.getShopId().equals(GMshopJpanel.findName.getText())) {
                    GMshopJpanel.labtext1.setText(item2.getShopName());
                    GMshopJpanel.itemId = item2.getItemId();
                    GMshopJpanel.itemName = item2.getShopName();
                    GMshopJpanel.is = true;
                }
            }
        }
        if (!GMshopJpanel.is) {
            ZhuFrame.getZhuJpanel().addPrompt2("未查到该物品 或者 您的特权等级不够 请升级！");
            GMshopJpanel.labtext1.setText("");
            GMshopJpanel.findName.setText("");
            GMshopJpanel.findName1.setText("");
            GMshopJpanel.itemName = "";
            return GMshopJpanel.itemId = "";
        }
        GMshopJpanel.is = false;
        return GMshopJpanel.itemId;
    }
    
    public static List<Map<String, Object>> iWantToFly() {
        if (GMshopJpanel.findName1.getText() == null || GMshopJpanel.findName1.getText().length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入数量！");
            return null;
        }
        if (Integer.parseInt(GMshopJpanel.findName1.getText()) > 999) {
            ZhuFrame.getZhuJpanel().addPrompt2("最大不能超过999");
            return null;
        }
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(GMshopJpanel.itemId));
        if (goodstable != null) {
            boolean dj = EquipTool.isEquip(goodstable.getType());
            if (dj && Integer.parseInt(GMshopJpanel.findName1.getText()) > 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该道具不可叠加，每次只能发送数量不能超过1个！");
                return null;
            }
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGmshoptype() == null || loginResult.getGmshoptype().equals("")) {
            ZhuFrame.getZhuJpanel().addPrompt2("您还未开通特权请联系GM开通！");
            return null;
        }
        GMshopJpanel.number = Integer.parseInt(GMshopJpanel.findName1.getText());
        if (GMshopJpanel.itemId != null && GMshopJpanel.number > 0) {
            String msg = GMshopJpanel.itemId + "," + GMshopJpanel.number + "," + GMshopJpanel.itemName;
            SendMessageUntil.toServer(Agreement.getAgreement().GMshopItemAgreement(msg));
            GMshopJpanel.labtext1.setText("");
            GMshopJpanel.findName.setText("");
            GMshopJpanel.findName1.setText("");
            GMshopJpanel.itemName = "";
            GMshopJpanel.itemId = "";
        }
        return null;
    }
    
    private static void GMshopJpanel(List<Map<String, Object>> dataNpcMapList) {
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
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = CutButtonImage.getImage("inkImg/background/GMWP.png", 362, 308);
            g.drawImage(this.icon.getImage(), 0, 0, 362, 308, this);
        }
        else {
            this.icon = CutButtonImage.getImage("img/xy2uiimg/GMWP.png", 362, 308);
            g.drawImage(this.icon.getImage(), 0, 0, 362, 308, this);
        }
    }
    
    public DefaultTableModel getTableModel() {
        return GMshopJpanel.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        GMshopJpanel.tableModel = tableModel;
    }
    
    public static JTextField getFindName() {
        return GMshopJpanel.findName;
    }
    
    public static void setFindName(JTextField findName) {
        GMshopJpanel.findName = findName;
    }
    
    public static JTextField getFindName1() {
        return GMshopJpanel.findName1;
    }
    
    public static void setFindName1(JTextField findName1) {
        GMshopJpanel.findName1 = findName1;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return GMshopJpanel.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        GMshopJpanel.Fvalue = fvalue;
    }
    
    public JTextArea getTestMes() {
        return GMshopJpanel.testMes;
    }
    
    public void setTestMes(JTextArea testMes) {
        GMshopJpanel.testMes = testMes;
    }
    
    static {
        GMshopJpanel.itemId = "";
        GMshopJpanel.number = 0;
        GMshopJpanel.itemName = "";
        GMshopJpanel.is = false;
    }
}
