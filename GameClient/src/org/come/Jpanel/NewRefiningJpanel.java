package org.come.Jpanel;

import org.come.socket.GameClient;
import java.awt.event.MouseListener;
import org.come.model.LianHua;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.bean.Skill;
import org.come.starcard.JpanelStarCardMain;
import org.come.starcard.JframeStarCardMain;
import org.come.until.UserMessUntil;
import com.tool.btn.BaptizeBtn;
import java.util.Iterator;
import org.come.until.RefiningUtil;
import org.come.Frame.WorkshopRefiningJframe;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import com.tool.time.Limit;
import javax.swing.JList;
import org.come.Frame.ZhuFrame;
import com.tool.time.TimeLimit;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.come.entity.RefiningValue;
import org.come.bean.QualityClBean;
import java.util.Map;
import java.util.List;
import org.come.until.AlchemyTXT;
import javax.swing.JTextField;
import org.skill.panel.SkillSMSelectOptionJpanel;
import javax.swing.JLabel;
import com.tool.btn.RefineOperBtn;
import javax.swing.JPanel;

public class NewRefiningJpanel extends JPanel
{
    private final RefineOperBtn operBtn1;
    private final RefineOperBtn operBtn2;
    private final RefineOperBtn operBtn3;
    private RefineOperBtn btnDown;
    private final JLabel[] jLabels;
    private SkillSMSelectOptionJpanel optionJpanel;
    private final JLabel labName;
    private static JTextField findName;
    private static JTextField findName1;
    private static JLabel labtext1;
    private AlchemyTXT alchemyTXT;
    private List<Map<String, String>> alchemyList;
    public static boolean isLH;
    public QualityClBean clBean;
    public int leixing;
    private final List<RefiningValue> values;
    private ImageIcon icon;
    private ImageIcon iconTitle;
    private String msg;
    
    public NewRefiningJpanel() {
        this.values = new ArrayList<>();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(560, 375));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 11);
            offBtn.setBounds(523, 10, 25, 25);
            this.add(offBtn);
            (this.operBtn1 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, 0, "保留")).setBounds(103, 330, 99, 24);
            this.add(this.operBtn1);
            (this.operBtn2 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, 1, "替换")).setBounds(363, 330, 99, 24);
            this.add(this.operBtn2);
            (this.operBtn3 = new RefineOperBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, 2, "炼化")).setBounds(415, 45, 99, 24);
            this.add(this.operBtn3);
            this.jLabels = new JLabel[6];
            for (int i = 0; i < this.jLabels.length; ++i) {
                (this.jLabels[i] = new JLabel()).setBounds(171, 111 + i * 30, 13, 13);
                this.jLabels[i].addMouseListener(new Gmouslisten(i));
                this.jLabels[i].setVisible(false);
                this.add(this.jLabels[i]);
            }
            (this.labName = new JLabel()).setBounds(72, 24, 96, 16);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_HY16);
            this.add(this.labName);
            String[] rowData = { "初学乍练", "初窥门径", "略有小成", "融会贯通", "心领神会", "炉火纯青" };
            (this.optionJpanel = new SkillSMSelectOptionJpanel(98, 120, "inkImg/background/15.png", rowData)).setBounds(72, 40, 98, 120);
            this.add(this.optionJpanel);
            this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String value = NewRefiningJpanel.this.getValue(true);
                    NewRefiningJpanel.this.addValues(value, 0, false);
                    if (value != null) {
                        NewRefiningJpanel.this.optionJpanel.setVisible(false);
                    }
                }
            });
            (this.btnDown = new RefineOperBtn("inkImg/button/8.png", 1, this, 3)).setBounds(151, 23, 18, 18);
            this.add(this.btnDown);
            Font font = new Font("楷体", 0, 16);
        }
        else {
            AlchemyTXT alchemyTXT = this.alchemyTXT;
            this.alchemyList = AlchemyTXT.getAlchemyList();
            this.setPreferredSize(new Dimension(478, 362));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 11);
            offBtn.setBounds(452, 0, 25, 25);
            this.add(offBtn);
            (this.operBtn1 = new RefineOperBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, 0, "保 留")).setBounds(80, 315, 80, 26);
            this.add(this.operBtn1);
            (this.operBtn2 = new RefineOperBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, 1, "替 换")).setBounds(300, 315, 80, 26);
            this.add(this.operBtn2);
            (this.operBtn3 = new RefineOperBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, 2, "炼化")).setBounds(360, 36, 80, 26);
            this.add(this.operBtn3);
            this.jLabels = new JLabel[6];
            for (int i = 0; i < this.jLabels.length; ++i) {
                (this.jLabels[i] = new JLabel()).setBounds(148, 92 + i * 28, 13, 13);
                this.jLabels[i].addMouseListener(new Gmouslisten(i));
                this.jLabels[i].setVisible(false);
                this.add(this.jLabels[i]);
            }
            (this.labName = new JLabel()).setBounds(68, 24, 96, 16);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_HY16);
            this.add(this.labName);
            String[] rowData = { "初学乍练", "初窥门径", "略有小成", "融会贯通", "心领神会", "炉火纯青" };
            (this.optionJpanel = new SkillSMSelectOptionJpanel(98, 120, "img/xy2uiimg/下拉框(2)w98,h120px.png", rowData)).setBounds(68, 25, 98, 120);
            this.add(this.optionJpanel);
            this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String value = NewRefiningJpanel.this.getValue(true);
                    NewRefiningJpanel.this.addValues(value, 0, false);
                    if (value != null) {
                        NewRefiningJpanel.this.optionJpanel.setVisible(false);
                    }
                }
            });
            (this.btnDown = new RefineOperBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1, this, 3)).setBounds(148, 9, 19, 20);
            this.add(this.btnDown);
        }
    }
    
    public String getValue(boolean type) {
        JList<String> jlist = this.optionJpanel.getJlist();
        int selectedIndex = jlist.getSelectedIndex();
        if (selectedIndex == -1) {
            return null;
        }
        Limit limit = TimeLimit.getLimits().getLimit("单人竞技场");
        if (limit == null) {
            if (type) {
                ZhuFrame.getZhuJpanel().addPrompt2("你还未获得称号");
            }
            return null;
        }
        else {
            String[] split = limit.getValue().split("\\|");
            String selectedValue = (String)jlist.getSelectedValue();
            if (split.length < selectedIndex + 1) {
                if (type) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你还未获得" + selectedValue + "称号");
                }
                return null;
            }
            else {
                this.labName.setText(selectedValue);
                return split[selectedIndex];
            }
        }
    }
    
    public void selectArenaIndex() {
        JList<String> jlist = this.optionJpanel.getJlist();
        int selectedIndex = jlist.getSelectedIndex();
        if (selectedIndex == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个称号");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(1000000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("大话币不足100万,无法再次炼化");
            return;
        }
        List<Goodstable> chaxunss = GoodsListFromServerUntil.chaxunss(119L);
        if (chaxunss.size() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("炼化道具不足,无法再次炼化");
            return;
        }
        Goodstable goodstable = (Goodstable)chaxunss.get(0);
        Limit limit = TimeLimit.getLimits().getLimit("单人竞技场");
        if (limit == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你还未获得称号");
            return;
        }
        String[] split = limit.getValue().split("\\|");
        if (split.length < selectedIndex + 1) {
            String selectedValue = (String)jlist.getSelectedValue();
            ZhuFrame.getZhuJpanel().addPrompt2("你还未获得" + selectedValue + "称号");
            return;
        }
        SuitOperBean operBean = new SuitOperBean();
        operBean.setType(70 + selectedIndex + 1);
        List<BigDecimal> list = new ArrayList<>();
        list.add(goodstable.getRgid());
        operBean.setGoods(list);
        String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(sendmes);
    }
    
    public void show(String value, int leixing, boolean is) {
        for (int i = 0; i < this.jLabels.length; ++i) {
            this.jLabels[i].setIcon(null);
            this.jLabels[i].setVisible(false);
        }
        this.msg = null;
        this.leixing = leixing;
        String concealArena = this.concealArena(leixing == 4);
        if (leixing == 4) {
            value = concealArena;
        }
        this.addValues(value, 0, is);
        FormsManagement.showForm(11);
        NewRefiningJpanel.isLH = true;
    }
    
    public String concealArena(boolean type) {
        this.optionJpanel.setVisible(false);
        this.btnDown.setVisible(type);
        this.labName.setVisible(type);
        if (type) {
            return this.getValue(false);
        }
        return null;
    }
    
    public void showArenaDownLab() {
        this.optionJpanel.setVisible(!this.optionJpanel.isVisible());
    }
    
    public void ew(QualityClBean clBean) {
        if (clBean.getType() == 2) {
            this.clBean = clBean;
            this.addValues(clBean.getNewAttr(), 2, false);
            String text = ZhuFrame.getZhuJpanel().getSendMes().getText();
            text = text.replaceAll("\\s*", "");
            if (text.equals("")) {
                return;
            }
            String[] vs = text.split("\\|");
            if (!vs[0].equals("炼器")) {
                return;
            }
            boolean f = false;
            int i = 1;
            while (i < vs.length) {
                String[] v = vs[i].split("=");
                if (this.alchemyList != null && this.alchemyList.size() > 0) {
                    for (Map<String, String> maps : this.alchemyList) {
                        if (maps.keySet().contains("武器开光") && v[0].equals(maps.get("武器开光"))) {
                            f = true;
                        }
                    }
                }
                if (f) {
                    String s = v[1].replace(" ", "");
                    int size = 0;
                    for (int sc = 0; sc < this.jLabels.length; ++sc) {
                        if (this.jLabels[sc].getIcon() != null) {
                            ++size;
                        }
                    }
                    if (Integer.parseInt(s) < 6) {
                        if (v.length == 1) {
                            if (clBean.getNewAttr().indexOf(vs[i]) != -1) {
                                return;
                            }
                        }
                        else if (Integer.parseInt(v[1]) <= getCount(clBean.getNewAttr(), v[0])) {
                            return;
                        }
                        if (size != Integer.parseInt(s) - 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("格式错误,请先锁定" + (Integer.parseInt(s) - 1) + "条数据！");
                            return;
                        }
                        ++i;
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("格式错误,最多只能炼化5条！最后数字不能大于5！");
                        return;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("格式错误，没有【" + v[0] + "】这样的属性！");
                    return;
                }
            }
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
            RefinersJpanel rJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getRefinersJpanel();
            String v2 = rJpanel.detection();
            if (v2.equals("炼器")) {
                this.operBtn1.cao1(rJpanel.goods, rJpanel.money, 1);
            }
        }
        else if (clBean.getType() == 1) {
            this.clBean = clBean;
            this.addValues(clBean.getNewAttr(), 1, false);
            String text = ZhuFrame.getZhuJpanel().getSendMes().getText();
            if (text.equals("")) {
                return;
            }
            String[] vs = text.split("\\|");
            if (!vs[0].equals("炼化")) {
                return;
            }
            RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
            String vstype = RefiningUtil.detection(EJpanel.goods, EJpanel.getType());
            boolean f2 = false;
            int j = 1;
            while (j < vs.length) {
                String[] v3 = vs[j].split("=");
                if (this.alchemyList != null && this.alchemyList.size() > 0) {
                    for (Map<String, String> maps2 : this.alchemyList) {
                        if (vstype.equals("炼化仙器")) {
                            if (EJpanel.goods[0].getType().toString().equals("7004") && maps2.keySet().contains("仙器武器") && v3[0].equals(maps2.get("仙器武器"))) {
                                f2 = true;
                            }
                            if (EJpanel.goods[0].getType().toString().equals("7000") && maps2.keySet().contains("仙器衣服") && v3[0].equals(maps2.get("仙器衣服"))) {
                                f2 = true;
                            }
                            if (EJpanel.goods[0].getType().toString().equals("7003") && maps2.keySet().contains("仙器鞋子") && v3[0].equals(maps2.get("仙器鞋子"))) {
                                f2 = true;
                            }
                            if (EJpanel.goods[0].getType().toString().equals("7001") && maps2.keySet().contains("仙器帽子") && v3[0].equals(maps2.get("仙器帽子"))) {
                                f2 = true;
                            }
                            if (EJpanel.goods[0].getType().toString().equals("7002") && maps2.keySet().contains("仙器项链") && v3[0].equals(maps2.get("仙器项链"))) {
                                f2 = true;
                            }
                            else {
                                continue;
                            }
                        }
                    }
                }
                if (f2) {
                    String s2 = v3[1].replace(" ", "");
                    int size2 = 0;
                    for (int sc2 = 0; sc2 < this.jLabels.length; ++sc2) {
                        if (this.jLabels[sc2].getIcon() != null) {
                            ++size2;
                        }
                    }
                    if (Integer.parseInt(s2) < 6) {
                        if (v3.length == 1) {
                            if (clBean.getNewAttr().indexOf(vs[j]) != -1) {
                                return;
                            }
                        }
                        else if (Integer.parseInt(v3[1]) <= getCount(clBean.getNewAttr(), v3[0])) {
                            return;
                        }
                        if (size2 != Integer.parseInt(s2) - 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("格式错误,请先锁定" + (Integer.parseInt(s2) - 1) + "条数据！");
                            return;
                        }
                        ++j;
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("格式错误,最多只能炼化5条！最后数字不能大于5！");
                        return;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("格式错误，没有【" + v3[0] + "】这样的属性！");
                    return;
                }
            }
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex2) {}
            String v4 = RefiningUtil.detection(EJpanel.goods, EJpanel.getType());
            if (v4.equals("炼化装备") || v4.equals("炼化仙器") || v4.equals("炼化神兵")) {
                this.operBtn1.cao1(EJpanel.goods, EJpanel.money, 4);
            }
        }
        else if (clBean.getType() == 53) {
            this.clBean = clBean;
            this.addValues(clBean.getNewAttr(), 1, false);
        }
        else if (clBean.getType() == 54) {
            this.clBean = clBean;
            this.addValues(clBean.getNewAttr(), 1, false);
        }
        else if (clBean.getType() >= 71 && clBean.getType() <= 76) {
            this.clBean = clBean;
            this.addValues(clBean.getNewAttr(), 1, false);
        }
    }
    
    public static int getCount(String source, String sub) {
        int count = 0;
        for (int length = source.length() - sub.length(), i = 0; i < length; ++i) {
            String sourceBak = source.substring(i, i + sub.length());
            int index = sourceBak.indexOf(sub);
            if (index != -1) {
                ++count;
            }
        }
        return count;
    }
    
    public void TH(QualityClBean clBean) {
        if (clBean.getType() >= 71 && clBean.getType() <= 76) {
            this.leixing = 4;
            this.addValues(clBean.getNewAttr(), 0, false);
        }
        else {
            Goodstable good = GoodsListFromServerUntil.getRgid(clBean.getRgid());
            if (good != null) {
                if (clBean.getType() == 2 || clBean.getType() == -2) {
                    this.leixing = 1;
                    good.setValue(BaptizeBtn.newExtra(good.getValue().split("\\|"), 1, clBean.getNewAttr()));
                    this.addValues(clBean.getNewAttr(), 0, true);
                }
                else if (clBean.getType() == 1) {
                    this.leixing = 0;
                    good.setValue(BaptizeBtn.newExtra(good.getValue().split("\\|"), 0, clBean.getNewAttr()));
                    this.addValues(clBean.getNewAttr(), 0, true);
                }
                else if (clBean.getType() == 53) {
                    this.leixing = 2;
                    good.setValue(BaptizeBtn.newExtra(good.getValue().split("\\|"), 0, clBean.getNewAttr()));
                    this.addValues(clBean.getNewAttr(), 0, false);
                }
                else if (clBean.getType() == 54) {
                    this.leixing = 3;
                    good.setValue(BaptizeBtn.newExtra(good.getValue().split("\\|"), 6, clBean.getNewAttr()));
                    this.addValues(clBean.getNewAttr(), 0, false);
                }
            }
            else {
                this.addValues(null, 0, false);
            }
        }
        this.clBean = null;
    }
    
    public void addValues(String v, int type, boolean is) {
        if (MyIsif.getStyle().equals("水墨")) {
            int x = 25;
            if (type == 0) {
                for (int i = 0; i < this.jLabels.length; ++i) {
                    this.jLabels[i].setVisible(false);
                }
                if (this.leixing == 0) {
                    this.msg = null;
                }
                this.clBean = null;
                this.values.clear();
                x = 25;
            }
            else if (type == 1 || type == 2) {
                for (int i = this.values.size() - 1; i >= 0; --i) {
                    if (((RefiningValue)this.values.get(i)).getX() != 25) {
                        this.values.remove(i);
                    }
                }
                x = 290;
            }
            if (v == null || v.equals("")) {
                return;
            }
            String[] vs = v.split("&");
            int size = 0;
            if (this.leixing == 0 || this.leixing == 1) {
                for (int j = (this.leixing == 0) ? 1 : 2; j < vs.length; ++j) {
                    if (is) {
                        try {
                            this.jLabels[size].setVisible(true);
                        }
                        catch (Exception e) {
                            continue;
                        }
                    }
                    String[] vss = vs[j].split("=");
                    if (!vss[0].equals("特技")) {
                        RefiningValue value = new RefiningValue(x, 105 + size * 30, vss[0], vss[1] + GoodsMsgJpanel.tianjia(vss[0]), is, this);
                        this.values.add(value);
                        ++size;
                    }
                    else {
                        for (int k = 1; k < vss.length; ++k) {
                            Skill skill = UserMessUntil.getSkillId(vss[k]);
                            if (skill != null) {
                                RefiningValue value2 = new RefiningValue(x, 105 + size * 30, vss[0], skill.getSkillname(), is, this);
                                this.values.add(value2);
                                ++size;
                            }
                        }
                    }
                }
            }
            else if (this.leixing == 2 || this.leixing == 3) {
                for (int j = 1; j < vs.length; ++j) {
                    if (is) {
                        this.jLabels[size].setVisible(true);
                    }
                    String[] vss = vs[j].split("=");
                    if (!vss[0].equals("星阵 ")) {
                        RefiningValue value = new RefiningValue(x, 105 + size * 30, vss[0], vss[1] + GoodsMsgJpanel.tianjia(vss[0]), is, this);
                        this.values.add(value);
                        ++size;
                    }
                    else {
                        RefiningValue value = new RefiningValue(x, 105 + size * 30, vss[1], vss[2], is, this);
                        this.values.add(value);
                        ++size;
                    }
                }
                if (this.leixing == 3) {
                    BigDecimal chooseStarCardId = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getChooseStarCardId();
                    Goodstable chooseStarCard = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    String[] v2 = chooseStarCard.getValue().split("&");
                    String[] split = chooseStarCard.getValue().split("星阵属性=");
                    if (split.length == 2) {
                        String[] split2 = split[1].split("=");
                        Double num = Double.valueOf(0.0);
                        for (int l = 1; l < vs.length; ++l) {
                            String[] split3 = vs[l].split("=");
                            num = Double.valueOf((double)num + JpanelStarCardMain.fiveElementRestrainCreate(split2[1], split3[0], split3[1]));
                        }
                        BigDecimal bigDecimal = new BigDecimal(num.toString()).setScale(2, 4);
                        bigDecimal.stripTrailingZeros();
                        RefiningValue value3 = new RefiningValue(x, 105 + size * 30, "五行加成星阵之力", bigDecimal.toString() + GoodsMsgJpanel.tianjia("五行加成星阵之力"), is, this);
                        this.values.add(value3);
                        ++size;
                    }
                }
            }
            else if (this.leixing == 4) {
                for (int j = 0; j < vs.length; ++j) {
                    if (is) {
                        this.jLabels[size].setVisible(true);
                    }
                    String[] vss = vs[j].split("=");
                    RefiningValue value = new RefiningValue(x, 105 + size * 30, vss[0], vss[1], is, this);
                    this.values.add(value);
                    ++size;
                }
            }
            boolean is2 = false;
            if (type == 0) {
                if (this.leixing == 1) {
                    int m = 0;
                    while (m < this.jLabels.length) {
                        if (this.jLabels[m].getIcon() != null && m >= this.values.size()) {
                            is2 = true;
                            ZhuFrame.getZhuJpanel().addPrompt2("请重新锁定");
                            break;
                        }
                        else {
                            ++m;
                        }
                    }
                }
                else {
                    is2 = true;
                }
            }
            if (is2) {
                for (int m = 0; m < this.jLabels.length; ++m) {
                    this.jLabels[m].setIcon(null);
                }
            }
        }
        else {
            int x = 35;
            if (type == 0) {
                for (int i = 0; i < this.jLabels.length; ++i) {
                    this.jLabels[i].setVisible(false);
                }
                if (this.leixing == 0) {
                    this.msg = null;
                }
                this.clBean = null;
                this.values.clear();
                x = 35;
            }
            else if (type == 1 || type == 2) {
                for (int i = this.values.size() - 1; i >= 0; --i) {
                    if (((RefiningValue)this.values.get(i)).getX() != 35) {
                        this.values.remove(i);
                    }
                }
                x = 245;
            }
            if (v == null || v.equals("")) {
                return;
            }
            String[] vs = v.split("&");
            int size = 0;
            if (this.leixing == 0 || this.leixing == 1) {
                for (int j = (this.leixing == 0) ? 1 : 2; j < vs.length; ++j) {
                    if (is) {
                        this.jLabels[size].setVisible(true);
                    }
                    String[] vss = vs[j].split("=");
                    if (!vss[0].equals("特技")) {
                        RefiningValue value = new RefiningValue(x, 87 + size * 28, vss[0], vss[1] + GoodsMsgJpanel.tianjia(vss[0]), is, this);
                        this.values.add(value);
                        ++size;
                    }
                    else {
                        for (int k = 1; k < vss.length; ++k) {
                            Skill skill = UserMessUntil.getSkillId(vss[k]);
                            if (skill != null) {
                                RefiningValue value2 = new RefiningValue(x, 87 + size * 28, vss[0], skill.getSkillname(), is, this);
                                this.values.add(value2);
                                ++size;
                            }
                        }
                    }
                }
            }
            else if (this.leixing == 2 || this.leixing == 3) {
                for (int j = 1; j < vs.length; ++j) {
                    if (is) {
                        this.jLabels[size].setVisible(true);
                    }
                    String[] vss = vs[j].split("=");
                    if (!vss[0].equals("星阵 ")) {
                        RefiningValue value = new RefiningValue(x, 87 + size * 28, vss[0], vss[1] + GoodsMsgJpanel.tianjia(vss[0]), is, this);
                        this.values.add(value);
                        ++size;
                    }
                    else {
                        RefiningValue value = new RefiningValue(x, 87 + size * 28, vss[1], vss[2], is, this);
                        this.values.add(value);
                        ++size;
                    }
                }
                if (this.leixing == 3) {
                    BigDecimal chooseStarCardId = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getChooseStarCardId();
                    Goodstable chooseStarCard = GoodsListFromServerUntil.getRgid(chooseStarCardId);
                    String[] v2 = chooseStarCard.getValue().split("&");
                    String[] split = chooseStarCard.getValue().split("星阵属性=");
                    if (split.length == 2) {
                        String[] split2 = split[1].split("=");
                        Double num = Double.valueOf(0.0);
                        for (int l = 1; l < vs.length; ++l) {
                            String[] split3 = vs[l].split("=");
                            num = Double.valueOf((double)num + JpanelStarCardMain.fiveElementRestrainCreate(split2[1], split3[0], split3[1]));
                        }
                        BigDecimal bigDecimal = new BigDecimal(num.toString()).setScale(2, 4);
                        bigDecimal.stripTrailingZeros();
                        RefiningValue value3 = new RefiningValue(x, 87 + size * 28, "五行加成星阵之力", bigDecimal.toString() + GoodsMsgJpanel.tianjia("五行加成星阵之力"), is, this);
                        this.values.add(value3);
                        ++size;
                    }
                }
            }
            else if (this.leixing == 4) {
                for (int j = 0; j < vs.length; ++j) {
                    if (is) {
                        this.jLabels[size].setVisible(true);
                    }
                    String[] vss = vs[j].split("=");
                    RefiningValue value = new RefiningValue(x, 87 + size * 28, vss[0], vss[1], is, this);
                    this.values.add(value);
                    ++size;
                }
            }
            boolean is2 = false;
            if (type == 0) {
                if (this.leixing == 1) {
                    int m = 0;
                    while (m < this.jLabels.length) {
                        if (this.jLabels[m].getIcon() != null && m >= this.values.size()) {
                            is2 = true;
                            ZhuFrame.getZhuJpanel().addPrompt2("请重新锁定");
                            break;
                        }
                        else {
                            ++m;
                        }
                    }
                }
                else {
                    is2 = true;
                }
            }
            if (is2) {
                for (int m = 0; m < this.jLabels.length; ++m) {
                    this.jLabels[m].setIcon(null);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B281.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 560, 375, this);
            g.setColor(Color.white);
            if (this.leixing == 4) {
                if (this.iconTitle == null) {
                    this.iconTitle = CutButtonImage.getImage("inkImg/background1/S300.png", 42, 28);
                }
                g.drawImage(this.iconTitle.getImage(), 28, 18, 42, 28, null);
            }
            g.setColor(UIUtils.COLOR_HIHT);
            if (this.msg != null) {
                g.drawString(this.msg, 170, 36);
            }
            g.setFont(UIUtils.TEXT_FONT);
            for (int i = 0; i < this.values.size(); ++i) {
                ((RefiningValue)this.values.get(i)).draw(g);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/newrefining.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 478, 362, this);
            g.setFont(UIUtils.TEXT_FONT2);
            if (this.leixing == 4) {
                g.setColor(Color.green);
                g.drawString("称号:", 32, 22);
            }
            if (this.msg != null) {
                g.drawString(this.msg, 135, 54);
            }
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT);
            for (RefiningValue value : this.values) {
                value.draw(g);
            }
        }
    }
    
    public void click(int p, int type) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (!configure.getLxsdkg().equals("开")) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前版本不可锁定！");
            return;
        }
        int snum = 4;
        if (configure.getLhsdts() != null && !configure.getLhsdts().equals("")) {
            snum = Integer.parseInt(configure.getLhsdts());
        }
        int size = 0;
        for (int i = 0; i < this.jLabels.length; ++i) {
            if (this.jLabels[i].getIcon() != null) {
                ++size;
            }
        }
        if (!configure.getLxsdkg().equals("开")) {
            if (type == 0 || this.leixing == 0 || this.leixing == 1) {
                if (this.jLabels[p].getIcon() != null) {
                    this.jLabels[p].setIcon(null);
                    --size;
                }
            }
            else {
                if (size >= snum) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前版本只可以锁定" + snum + "条属性！");
                    return;
                }
                if (this.jLabels[p].getIcon() == null) {
                    this.jLabels[p].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                    ++size;
                }
            }
        }
        else if (type == 0) {
            if (this.jLabels[p].getIcon() != null) {
                this.jLabels[p].setIcon(null);
                --size;
            }
        }
        else {
            if (size >= snum) {
                ZhuFrame.getZhuJpanel().addPrompt2("当前版本只可以锁定" + snum + "条属性！");
                return;
            }
            if (this.jLabels[p].getIcon() == null) {
                this.jLabels[p].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                ++size;
            }
        }
        if (size == 0) {
            this.msg = null;
        }
        else if (size == 1) {
            if (this.leixing == 0) {
                this.msg = "消耗500仙玉锁定一条炼化属性";
            }
            else if (this.leixing == 1) {
                this.msg = "消耗1000仙玉锁定一条炼化属性";
            }
        }
        else if (size == 2) {
            if (this.leixing == 0) {
                this.msg = "消耗1000仙玉锁定二条炼化属性";
            }
            else if (this.leixing == 1) {
                this.msg = "消耗3000仙玉锁定一条炼化属性";
            }
        }
        else if (size == 3) {
            if (this.leixing == 0) {
                this.msg = "消耗2500仙玉锁定三条炼化属性";
            }
            else if (this.leixing == 1) {
                this.msg = "消耗6000仙玉锁定一条炼化属性";
            }
        }
        else if (size == 4) {
            if (this.leixing == 0) {
                this.msg = "消耗5000仙玉锁定四条炼化属性";
            }
            else if (this.leixing == 1) {
                this.msg = "消耗1000仙玉锁定一条炼化属性";
            }
        }
        if (size > 0 && this.leixing == 0) {
            LianHua lianHua = UserMessUntil.getAllLianHua().get1000(size);
            this.msg = String.format(lianHua.getDes(), new Object[] { Integer.valueOf(lianHua.getMoney()) });
        }
        if (size > 0 && this.leixing == 1) {
            LianHua lianHua = UserMessUntil.getAllLianHua().get2000(size);
            this.msg = String.format(lianHua.getDes(), new Object[] { Integer.valueOf(lianHua.getMoney()) });
        }
    }
    
    public int getlock() {
        int lock = 0;
        int size = 0;
        for (int i = 0; i < this.jLabels.length; ++i) {
            if (this.jLabels[i].getIcon() != null) {
                lock = (int)((double)lock + Math.pow(10.0, (double)i));
                ++size;
            }
        }
        return lock * 10 + size;
    }
    
    public RefineOperBtn getBtnDown() {
        return this.btnDown;
    }
    
    public void setBtnDown(RefineOperBtn btnDown) {
        this.btnDown = btnDown;
    }
    
    public SkillSMSelectOptionJpanel getOptionJpanel() {
        return this.optionJpanel;
    }
    
    public void setOptionJpanel(SkillSMSelectOptionJpanel optionJpanel) {
        this.optionJpanel = optionJpanel;
    }
    
    public RefineOperBtn getOperBtn3() {
        return this.operBtn3;
    }
    
    static {
        NewRefiningJpanel.isLH = false;
    }
    
    class Gmouslisten implements MouseListener
    {
        private final int p;
        
        public Gmouslisten(int p) {
            this.p = p;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (GameClient.lianhua == 0) {
                NewRefiningJpanel.this.click(this.p, (NewRefiningJpanel.this.jLabels[this.p].getIcon() == null) ? 1 : 0);
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
