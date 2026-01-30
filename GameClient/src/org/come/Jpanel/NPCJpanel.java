package org.come.Jpanel;

import org.apache.commons.lang.StringUtils;
import org.come.Frame.AnswerMinJframe;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import com.tool.PlayerKill.PKSys;
import org.come.until.UserData;
import org.come.npc.PetConversion;
import org.come.control.DelSkillMenuControl;
import java.util.Arrays;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import io.netty.util.internal.StringUtil;
import com.tool.image.ManimgAttribute;
import org.come.bean.NPCDialog;
import com.tool.ModerateTask.TaskData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.UserMessUntil;
import com.tool.ModerateTask.TaskRoleData;
import org.come.until.Util;
import org.come.model.Talk;
import org.come.until.SplitStringTool;
import com.tool.ModerateTask.TaskMixDeal;
import java.util.Iterator;
import com.tool.image.ImageMixDeal;
import org.come.bean.NpcMenuBean;
import org.come.bean.HeadImgBean;
import org.come.until.ScrenceUntil;
import org.come.Frame.NPCJfram;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import org.come.action.MapAction;
import org.come.action.NpcMenuAction;
import org.come.until.FormsManagement;
import org.come.gltools.RendererTools;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Point;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

import com.tool.tcpimg.RichLabel;
import org.come.bean.NpcInfoBean;
import org.come.entity.Goodstable;
import org.come.model.Npctable;
import com.tool.ModerateTask.TaskProgress;
import org.come.bean.MapMonsterBean;
import org.come.model.Door;
import java.util.List;

public class NPCJpanel extends JPanel
{
    private JTextArea testMes;
    private List<Door> doors;
    private String[] npcsStrings;
    private List<String> npcfunction;
    private JList<String> list1;
    private int ys;
    private String npctype;
    private String pettype;
    private MapMonsterBean mapMonsterBean;
    private TaskProgress taskMonster;
    private Npctable npctable;
    private Goodstable good;
    private String[] Fvalue;
    private NpcInfoBean npcInfoBean;
    private RichLabel richLabel;
    public static ImageIcon icon;
    private ImageIcon headImg;
    private int headHeight;
    private static final int PAGE_SUM = 7;

    private static JLabel newTitlelab = new JLabel();
    public NPCJpanel() {
        this.npcfunction = new ArrayList<>();
        this.ys = 0;
        this.headHeight = 0;
        this.setPreferredSize(new Dimension(534, 208));
        this.setOpaque(false);
        (this.testMes = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                if (NPCJpanel.this.richLabel != null) {
                    try {
                        NPCJpanel.this.richLabel.paint(g, 10, NPCJpanel.this.headHeight + 13);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).setMargin(new Insets(13, 10, 18, 10));
        this.testMes.setLineWrap(true);
        this.testMes.setOpaque(false);
        this.testMes.setFont(UIUtils.TEXT_FONT1);
        this.testMes.setForeground(Color.white);
        this.testMes.setText("");
        (this.list1 = new JList() {
            @Override
            public int locationToIndex(Point location) {
                int index = super.locationToIndex(location);
                if (index != -1 && !this.getCellBounds(index, index).contains(location)) {
                    return -1;
                }
                return index;
            }
        }).setOpaque(false);
        this.list1.setBackground(UIUtils.Color_BACK);
        this.list1.setForeground(Color.green);
        this.list1.setFont(UIUtils.TEXT_FONT1);
        this.list1.setSelectionForeground(Color.green);
        this.list1.setSelectionBackground(UIUtils.Color_BACK);
        this.list1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                NPCJpanel.this.list1.setCellRenderer(new RendererTools(NPCJpanel.this.list1.locationToIndex(e.getPoint()), new Color(135, 135, 135, 100)));
            }
        });
        (this.richLabel = new RichLabel(530)).addText("", UIUtils.TEXT_FONT1);
        this.list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                FormsManagement.showForm(289);
                JList list = (JList)e.getSource();
                if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown() && !this.isMenuShortcutKeyDown(e)) {
                    list.clearSelection();
                }
                FormsManagement.HideForm(27);
                if (list.getSelectedIndex() == -1) {
                    return;
                }
                for (int i = 0; i <= NPCJpanel.this.list1.getMaxSelectionIndex(); ++i) {
                    NPCJpanel.this.list1.setCellRenderer(new RendererTools(i, new Color(0, 0, 0, 0)));
                }
                if (!NPCJpanel.this.npcsStrings[list.getSelectedIndex()].equals("我什么都不做")) {
                    MapAction.npcmenuAction.get("菜单").menuControl(NPCJpanel.this.npcsStrings[list.getSelectedIndex()]);
                }
            }
            
            private boolean isMenuShortcutKeyDown(InputEvent event) {
                return (event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0x0;
            }
        });
        this.setLayout(new BorderLayout());
        this.add(this.testMes, "North");
        JTextArea testMes1 = new JTextArea();
        testMes1.setBounds(10, 10, 20, 100);
        testMes1.setOpaque(false);
        testMes1.setMargin(new Insets(0, 10, 0, 0));
        this.add(testMes1, "West");
        JTextArea testMes2 = new JTextArea();
        testMes2.setBounds(10, 10, 20, 100);
        testMes2.setOpaque(false);
        testMes2.setMargin(new Insets(0, 10, 0, 0));
        this.add(testMes2, "East");
        this.add(this.list1, "Center");
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            NPCJpanel.icon = new ImageIcon("inkImg/background1/B233.png");
        }
        else {
            NPCJpanel.icon = new ImageIcon("img/xy2uiimg/95_png.xy2uiimg.npctalk.png");
        }
        if (this.headImg != null) {
            g.drawImage(this.headImg.getImage(), 2, 1, this.headImg.getIconWidth(), this.headImg.getIconHeight(), this);
        }
        g.drawImage(NPCJpanel.icon.getImage(), 0, this.headHeight, 534, 208, this);
    }


    public void FlightMsg(Goodstable good) {
        String[] vs = good.getValue().split("\\|");
        String[] path = vs[0].split("=")[1].split(",");
        String wz = path[1] + "(" + path[2] + "," + path[3] + ")";
        this.testMes.setText("你真的要去" + wz + "吗？");
        this.good = good;
        this.Fvalue = new String[] { wz };
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("快送我去");
        this.npcfunction.add("重新做路标");
        this.npcfunction.add("我什么都不想做");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.changeImage(null);
    }
    
    public void FlightMsgS(Goodstable good) {
        FormsManagement.HideForm(27);
        this.good = good;
        this.testMes.setText("记录多个坐标的飞行棋");
        this.npcfunction.clear();
        String[] vs = good.getValue().split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith("传送")) {
                vs[i] = vs[i].split("=")[1];
                String[] path = vs[i].split(",");
                this.npcfunction.add(path[1] + "(" + path[2] + "," + path[3] + ")");
            }
            else {
                vs[i] = null;
            }
        }
        this.Fvalue = vs;
        if (vs.length < 6) {
            this.npcfunction.add("新增坐标");
        }
        this.showList(null);
    }
    
    public void showMap() {
        this.removedizi();
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("长安城(4070,2710)");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.FlightMsgSD("长安城(4070,2710)");
        FormsManagement.showForm(27);
    }
    
    public boolean FlightMsgSD(String value) {
        if (this.good == null || this.Fvalue == null) {
            return false;
        }
        for (int i = 0; i < this.Fvalue.length - 1; ++i) {
            if (this.Fvalue[i] != null) {
                String[] path = this.Fvalue[i].split(",");
                String wz = path[1] + "(" + path[2] + "," + path[3] + ")";
                if (value.equals(wz)) {
                    this.Fvalue = new String[] { wz };
                    FormsManagement.HideForm(27);
                    this.testMes.setText("你真的要去" + value + "吗？");
                    this.npcfunction.clear();
                    this.npcfunction.add("快送我去");
                    this.npcfunction.add("删除路标");
                    this.npcfunction.add("重新做路标");
                    this.showList(null);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void showskill(int npctype) {
        this.npcfunction.clear();
        switch (npctype) {
            case 19: {
                this.npcfunction.add("雷霆霹雳");
                this.npcfunction.add("日照光华");
                this.npcfunction.add("雷神怒击");
                this.npcfunction.add("电闪雷鸣");
                this.npcfunction.add("天诛地灭");
                break;
            }
            case 20: {
                this.npcfunction.add("地狱烈火");
                this.npcfunction.add("天雷怒火");
                this.npcfunction.add("三味真火");
                this.npcfunction.add("烈火骄阳");
                this.npcfunction.add("九阴纯火");
                break;
            }
            case 21: {
                this.npcfunction.add("飞砂走石");
                this.npcfunction.add("乘风破浪");
                this.npcfunction.add("太乙生风");
                this.npcfunction.add("风雷涌动");
                this.npcfunction.add("袖里乾坤");
                break;
            }
            case 22: {
                this.npcfunction.add("龙卷雨击");
                this.npcfunction.add("龙腾水溅");
                this.npcfunction.add("龙啸九天");
                this.npcfunction.add("蛟龙出海");
                this.npcfunction.add("九龙冰封");
                break;
            }
            case 23: {
                this.npcfunction.add("作茧自缚");
                this.npcfunction.add("金蛇缠丝");
                this.npcfunction.add("天罗地网");
                this.npcfunction.add("作壁上观");
                this.npcfunction.add("四面楚歌");
                break;
            }
            case 24: {
                this.npcfunction.add("催眠咒");
                this.npcfunction.add("瞌睡咒");
                this.npcfunction.add("离魂咒");
                this.npcfunction.add("迷魂醉");
                this.npcfunction.add("百日眠");
                break;
            }
            case 25: {
                this.npcfunction.add("蛇蝎美人");
                this.npcfunction.add("追魂迷香");
                this.npcfunction.add("断肠烈散");
                this.npcfunction.add("鹤顶红粉");
                this.npcfunction.add("万毒攻心");
                break;
            }
            case 26: {
                this.npcfunction.add("反间之计");
                this.npcfunction.add("情真意切");
                this.npcfunction.add("谗言相加");
                this.npcfunction.add("借刀杀人");
                this.npcfunction.add("失心狂乱");
                break;
            }
            case 27: {
                this.npcfunction.add("夺命勾魂");
                this.npcfunction.add("追神摄魄");
                this.npcfunction.add("魔音摄心");
                this.npcfunction.add("销魂蚀骨");
                this.npcfunction.add("阎罗追命");
                break;
            }
            case 28: {
                this.npcfunction.add("妖之魔力");
                this.npcfunction.add("力神复苏");
                this.npcfunction.add("狮王之怒");
                this.npcfunction.add("兽王神力");
                this.npcfunction.add("魔神附身");
                break;
            }
            case 29: {
                this.npcfunction.add("红袖添香");
                this.npcfunction.add("莲步轻舞");
                this.npcfunction.add("楚楚可怜");
                this.npcfunction.add("魔神护体");
                this.npcfunction.add("含情脉脉");
                break;
            }
            case 30: {
                this.npcfunction.add("魔之飞步");
                this.npcfunction.add("急速之魔");
                this.npcfunction.add("魔神飞舞");
                this.npcfunction.add("天外飞魔");
                this.npcfunction.add("乾坤借速");
                break;
            }
            case 72: {
                this.npcfunction.add("幽冥鬼火");
                this.npcfunction.add("火影迷踪");
                this.npcfunction.add("冥烟销骨");
                this.npcfunction.add("落日熔金");
                this.npcfunction.add("血海深仇");
                break;
            }
            case 73: {
                this.npcfunction.add("吸血水蛭");
                this.npcfunction.add("六翅毒蝉");
                this.npcfunction.add("啮骨抽髓");
                this.npcfunction.add("血煞之蛊");
                this.npcfunction.add("吸星大法");
                break;
            }
            case 74: {
                this.npcfunction.add("麻沸散");
                this.npcfunction.add("鬼失惊");
                this.npcfunction.add("乱魂钉");
                this.npcfunction.add("失心疯");
                this.npcfunction.add("孟婆汤");
                break;
            }
            case 75: {
                this.npcfunction.add("幽怜魅影");
                this.npcfunction.add("醉生梦死");
                this.npcfunction.add("一曲销魂");
                this.npcfunction.add("秦丝冰雾");
                this.npcfunction.add("倩女幽魂");
                break;
            }
        }
        this.npcfunction.add("我什么都不想做");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        FormsManagement.showForm(27);
        FormsManagement.HideForm(642);
    }
    
    public JTextArea getTestMes() {
        return this.testMes;
    }
    
    public void setTestMes(JTextArea testMes) {
        this.testMes = testMes;
    }
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        doors = null;
        this.doors = doors;
    }
    
    public void changeImage(String skin) {
        this.headImg = null;
        this.headHeight = 0;
        if (skin != null) {
            HeadImgBean npcHeadImg = CutButtonImage.getNPCHeadImg(skin);
            if (npcHeadImg != null) {
                this.headImg = npcHeadImg.getHeadImg();
                this.headHeight = this.headImg.getIconHeight();
            }
        }
        this.testMes.setMargin(new Insets(13 + this.headHeight, 10, 18, 10));
        this.richLabel.setText(this.testMes.getText());
        NPCJfram.getNpcJfram().setBounds(ScrenceUntil.Screen_x / 2 - 267, (ScrenceUntil.Screen_y - (this.headHeight + 200)) / 3, 534, this.headHeight + 208);
        FormsManagement.showForm(27);
        FormsManagement.HideForm(642);
    }
    
    public void npc(NpcMenuBean npcMenu, int type) {
        this.removedizi();
        this.npctype = type + "";
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        if (npcMenu != null) {
            if (npcMenu.getSureBean() != null && npcMenu.getSureBean().getConditions() != null) {
                String[] vs = npcMenu.getSureBean().getConditions().split("-");
                for (int i = 0; i < vs.length; ++i) {
                    if (vs[i].equals(ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString())) {
                        for (String key : npcMenu.getSureBean().getMenuAndMethod().keySet()) {
                            this.npcfunction.add(key);
                        }
                    }
                }
            }
            for (String key2 : npcMenu.getInittitle().keySet()) {
                this.npcfunction.add(key2);
            }
        }
        this.testMes.setText("");
        this.showList(null);
    }
    /**
     * 弹出npc对话
     */
    public void npc(NpcMenuBean npcMenu, NpcInfoBean npcInfoBean) {
        this.removedizi();
        this.npctype = npcInfoBean.getNpctable().getNpctype();
        this.npcInfoBean = npcInfoBean;
        FormsManagement.HideForm(27);
        if (this.npctype.equals("101")) {
            this.GetOut();
            return;
        }
        if (this.npctype.equals("102")) {
            this.ZiShou();
            return;
        }
        if (npctype.equals("99129")) {
            if(!npcInfoBean.getNpctable().getRoleId().equals(RoleData.getRoleData().getLoginResult().getRole_id().toString())) {
                if(npcInfoBean.getNpctable().getSkin().equals("700011") || npcInfoBean.getNpctable().getSkin().equals("700012")) {
                    if(StringUtils.isEmpty(npcInfoBean.getNpctable().getRoleIdName()) || !npcInfoBean.getNpctable().getRoleIdName().contains(RoleData.getRoleData().getLoginResult().getRole_id().toString()+RoleData.getRoleData().getLoginResult().getRolename())) {
                        FormsManagement.showForm(3075);
                        AnswerMinJframe.getAnswerMinJpanel().getWt();
                        AnswerMinJframe.getAnswerMinJpanel().NPCID = npcInfoBean.getNpctable().getNpcid();
                        AnswerMinJframe.getAnswerMinJpanel().initTimer();
                    }else {
                        newTitlelab.setText("");
                        npcfunction.clear();
                        testMes.setText("小树苗已经长成了参天大树，此树苗用观音姐姐的水滋养，镇元大仙亲自施肥，用太上老君的仙丹做养料，吸收天地精华，日月灵气，历经千载，方才长成！可带来无尽财富和福运！");
                        npcfunction.add("我什么也不做");
                        showList(null);
                    }
                    return;
                }else {
                    newTitlelab.setText("");
                    npcfunction.clear();
                    testMes.setText("这不是您钟的树哦，您也可以种招财进宝树，长安桥每日必做领取小树苗！");
                    showList(null);
                    return;
                }
            }else {
                if(Integer.parseInt(npcInfoBean.getNpctable().getNum())<3) {
                    newTitlelab.setText("");
                    npcfunction.clear();
                    testMes.setText("您已照顾您的小树苗"+npcInfoBean.getNpctable().getNum()+"次了");
                    npcfunction.add("我要浇水");
                    npcfunction.add("我要除虫");
                    npcfunction.add("我要施肥");
                    npcfunction.add("我什么也不做");
                    showList(null);
                    return;
                }else {
                    if(npcInfoBean.getNpctable().getSkin().equals("700011") || npcInfoBean.getNpctable().getSkin().equals("700012")) {
                        if(StringUtils.isEmpty(npcInfoBean.getNpctable().getRoleIdName()) || !npcInfoBean.getNpctable().getRoleIdName().contains(RoleData.getRoleData().getLoginResult().getRole_id().toString()+RoleData.getRoleData().getLoginResult().getRolename())) {
                            FormsManagement.showForm(3075);
                            AnswerMinJframe.getAnswerMinJpanel().getWt();
                            AnswerMinJframe.getAnswerMinJpanel().NPCID = npcInfoBean.getNpctable().getNpcid();
                            AnswerMinJframe.getAnswerMinJpanel().initTimer();
                        }else {
                            newTitlelab.setText("");
                            npcfunction.clear();
                            testMes.setText("您的小树苗已经长成了参天大树，此树苗用观音姐姐的水滋养，镇元大仙亲自施肥，用太上老君的仙丹做养料，吸收天地精华，日月灵气，历经千载，方才长成！可带来无尽财富和福运！");
                            npcfunction.add("我什么也不做");
                            showList(null);
                        }
                        return;
                    }else {
                        newTitlelab.setText("");
                        npcfunction.clear();
                        testMes.setText("您的小树苗已经长成了参天大树！");
                        npcfunction.add("我什么也不做");
                        showList(null);
                        return;
                    }
                }
            }
        }
        this.npcfunction.clear();
        TaskProgress taskdata = TaskMixDeal.KillNpc(Integer.parseInt(npcInfoBean.getNpctable().getNpcid()));
        TaskData data = (taskdata != null) ? taskdata.getTask().getTaskData() : null;
        if (data != null) {
            this.npcfunction.add("我是来击杀你的");
            for (int i = 1; i <= data.getNd(); ++i) {
                if (i == 1) {
                    this.npcfunction.add("我是来击杀你的(困难难度)");
                }
                else if (i == 2) {
                    this.npcfunction.add("我是来击杀你的(卓越难度)");
                }
                else if (i == 3) {
                    this.npcfunction.add("我是来击杀你的(地狱难度)");
                }
                else if (i == 4) {
                    this.npcfunction.add("我是来击杀你的(天堂难度)");
                }
            }
        }
        String npcway = npcInfoBean.getNpctable().getNpcway();
        if (!this.npctype.equals("2") && !this.npctype.equals("222") && !this.npctype.equals("2222") && npcway != null && !npcway.equals("")) {
            String[] v = npcway.split(" ");
            for (int j = 0; j < v.length; ++j) {
                List<String> tasks = SplitStringTool.splitString(v[j]);
                String task = TaskMixDeal.taskoption((String)tasks.get(0));
                if (task != null) {
                    this.npcfunction.add(task);
                }
            }
        }
        if (npcInfoBean.getDoors() != null) {
            for (int k = 0; k < npcInfoBean.getDoors().size(); ++k) {
                if (npcInfoBean.getDoors().get(k) != null && ((Door)npcInfoBean.getDoors().get(k)).getDoorkey() != null) {
                    this.npcfunction.add(((Door)npcInfoBean.getDoors().get(k)).getDoorkey());
                }
            }
        }
        if (npcMenu != null) {
            if (npcMenu.getSureBean() != null && npcMenu.getSureBean().getConditions() != null) {
                String[] vs = npcMenu.getSureBean().getConditions().split("-");
                for (int j = 0; j < vs.length; ++j) {
                    if (vs[j].equals(ImageMixDeal.userimg.getRoleShow().getSpecies_id().toString())) {
                        for (String key : npcMenu.getSureBean().getMenuAndMethod().keySet()) {
                            this.npcfunction.add(key);
                        }
                    }
                }
            }
            for (String key2 : npcMenu.getInittitle().keySet()) {
                this.npcfunction.add(key2);
            }
        }
        if (npcInfoBean.getTalks() != null && npcInfoBean.getTalks().size() != 0) {
            Talk talk = (Talk)npcInfoBean.getTalks().get(Util.random.nextInt(npcInfoBean.getTalks().size()));
            if (talk != null) {
                this.testMes.setText(talk.getTalktext() + (this.npctype.equals("801") ? ("   （本日已完成 " + TaskRoleData.SumReceive(801, 2) + "/" + UserMessUntil.getTaskSet(Integer.parseInt(this.npctype)).getSumlimit() + "）") : ""));
                if (npcInfoBean.getNpctable().getNpcid().equals("400102")) {
                    SendMessageUntil.toServer(Agreement.getAgreement().HatchvalueAgreement(""));
                }
            }
            else {
                this.testMes.setText("");
            }
        }
        else {
            this.testMes.setText("");
        }
        this.showList(npcInfoBean.getNpctable().getSkin());
    }
    
    public void npc(NPCDialog npcDialog) {
        this.removedizi();
        this.testMes.setLineWrap(true);
        this.testMes.setText(npcDialog.getMsg());
        if (npcDialog.getType() != null) {
            if (npcDialog.getType().startsWith("N")) {
                this.npctype = npcDialog.getType().substring(1);
            }
            else if (npcDialog.getType().startsWith("M")) {
                int I = Integer.parseInt(npcDialog.getType().substring(1));
                List<ManimgAttribute> mapMonsterlist = ImageMixDeal.mapMonsterlist;
                if (mapMonsterlist == null) {
                    return;
                }
                int i = mapMonsterlist.size() - 1;
                while (i >= 0) {
                    MapMonsterBean bean = ((ManimgAttribute)mapMonsterlist.get(i)).getMapMonsterBean();
                    if ((int)bean.getI() == I) {
                        this.mapMonsterBean = bean;
                        break;
                    }
                    else {
                        --i;
                    }
                }
                if (this.mapMonsterBean == null) {
                    return;
                }
            }
        }
        if (npcDialog.getFunctions() != null) {
            this.npcsStrings = new String[npcDialog.getFunctions().size()];
            for (int j = 0; j < this.npcsStrings.length; ++j) {
                this.npcsStrings[j] = (String)npcDialog.getFunctions().get(j);
            }
            this.list1.setListData(this.npcsStrings);
        }
        else {
            (this.npcsStrings = new String[1])[0] = "我什么都不做";
            this.list1.setListData(this.npcsStrings);
        }
        if (this.npctype != null && StringUtil.isNullOrEmpty(npcDialog.getMsg())) {
            for (ManimgAttribute attr : ImageMixDeal.npcimglist) {
                if (attr.getNpc().getNpctable().getNpctype().equals(this.npctype)) {
                    if (attr.getNpc().getTalks() != null && attr.getNpc().getTalks().size() != 0) {
                        Talk talk = (Talk)attr.getNpc().getTalks().get(Util.random.nextInt(attr.getNpc().getTalks().size()));
                        if (talk != null) {
                            this.testMes.setText(talk.getTalktext());
                        }
                    }
                    this.changeImage(attr.getNpc().getNpctable().getSkin());
                    return;
                }
            }
        }
        this.changeImage(null);
    }
    
    public void removedizi() {
        this.mapMonsterBean = null;
        this.taskMonster = null;
        this.npcInfoBean = null;
        this.good = null;
        this.Fvalue = null;
        this.pettype = null;
        this.npctype = null;
    }
    
    public void taskend(String text) {
        this.removedizi();
        FormsManagement.HideForm(27);
        if (text == null || text.equals("")) {
            return;
        }
        this.npcsStrings = new String[0];
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText(text);
        this.changeImage(null);
    }
    
    public void yeguai2(MapMonsterBean monsterBean) {
        this.removedizi();
        this.mapMonsterBean = monsterBean;
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcsStrings = new String[] { "你等着，我这就去领" };
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("任务都没领也想来挑战我？");
        this.changeImage(monsterBean.getRobotskin());
    }
    
    public void yeguai(MapMonsterBean monsterBean) {
        this.removedizi();
        this.mapMonsterBean = monsterBean;
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        int robotType = monsterBean.getRobottype();
        if (robotType == 2) {
            this.npcfunction.add("我想买点东西");
        }
        else if ((robotType == 4 || robotType == 132) && monsterBean.getFollow() != null) {
            this.npcfunction.add("我是来劫道的");
        }
        else {
            this.npcfunction.add("满地宝物先抢一个在说");
        }
        this.npcfunction.add("我什么都不想做");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("想要我身上的宝物吗? 想要就来抢啊!");
        this.changeImage(monsterBean.getRobotskin());
    }
    
    public void task(TaskProgress taskdata) {
        FormsManagement.HideForm(27);
        this.removedizi();
        this.taskMonster = taskdata;
        this.npcfunction.clear();
        this.npcfunction.add("我是来击杀你的");
        TaskData data = taskdata.getTask().getTaskData();
        if (data != null) {
            for (int i = 1; i <= data.getNd(); ++i) {
                if (i == 1) {
                    this.npcfunction.add("我是来击杀你的(困难难度)");
                }
                else if (i == 2) {
                    this.npcfunction.add("我是来击杀你的(卓越难度)");
                }
                else if (i == 3) {
                    this.npcfunction.add("我是来击杀你的(地狱难度)");
                }
                else if (i == 4) {
                    this.npcfunction.add("我是来击杀你的(天堂难度)");
                }
            }
        }
        this.npcfunction.add("我什么都不想做");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("这里风景还不差");
        this.changeImage(null);
    }
    
    public void whetherChange(BigDecimal petId) {
        this.removedizi();
        this.pettype = petId.toString();
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("是");
        this.npcfunction.add("否");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("是否变化该召唤兽的造型");
        this.changeImage(null);
    }
    
    public void GetDelpetSkill(List<RoleSummoning> pet) {
        this.npcfunction.clear();
        this.testMes.setText("少侠想重新修炼哪个召唤兽呢？");
        this.npcsStrings = new String[pet.size()];
        for (int i = 0; i < pet.size(); ++i) {
            this.npcsStrings[i] = ((RoleSummoning)pet.get(i)).getSummoningname() + "  (编号：" + ((RoleSummoning)pet.get(i)).getSid() + ")";
        }
        this.npcfunction.addAll(Arrays.asList(this.npcsStrings));
        this.showList(null);
    }
    
    public void GetDelpetSkill1(List<RoleSummoning> pet, BigDecimal id) {
        this.npcfunction.clear();
        DelSkillMenuControl.skills.clear();
        this.testMes.setText("你要重新修炼该召唤兽的哪个终极技能？重修的费用为#G5E、20E、50E、100E#W后面的均为#G200E。");
        for (int i = 0; i < pet.size(); ++i) {
            if (((RoleSummoning)pet.get(i)).getSid().equals(id)) {
                String[] skill = ((RoleSummoning)pet.get(i)).getPetSkills().split("\\|");
                this.npcsStrings = new String[skill.length];
                for (int j = 0; j < skill.length; ++j) {
                    if (PetConversion.getskill(skill[j])) {
                        this.npcsStrings[j] = "终极技能：" + this.getSkillName(skill[j]);
                        DelSkillMenuControl.skills.add(skill[j]);
                    }
                }
            }
        }
        this.npcfunction.addAll(Arrays.asList(this.npcsStrings));
        this.showList(null);
    }
    
    public void getLingBaoKX() {
        this.removedizi();
        this.testMes.setText("请选择要替换的抗性");
        this.npcfunction.clear();
        this.npcsStrings = new String[UserData.LBKX.length];
        for (int i = 0; i < UserData.LBKX.length; ++i) {
            this.npcfunction.add(UserData.LBKX[i]);
        }
        this.npcfunction.add("我什么都不想做");
        this.showList(null);
    }
    
    public String getSkillName(String id) {
        int n = -1;
        switch (id.hashCode()) {
            case 1513195: {
                if (id.equals("1606")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1513196: {
                if (id.equals("1607")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 1513197: {
                if (id.equals("1608")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515181: {
                if (id.equals("1828")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515182: {
                if (id.equals("1829")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515204: {
                if (id.equals("1830")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515235: {
                if (id.equals("1840")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515236: {
                if (id.equals("1841")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515237: {
                if (id.equals("1842")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515304: {
                if (id.equals("1867")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515305: {
                if (id.equals("1868")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515306: {
                if (id.equals("1869")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "绝境逢生";
            }
            case 1: {
                return "子虚乌有";
            }
            case 2: {
                return "春回大地";
            }
            case 3: {
                return "化无";
            }
            case 4: {
                return "作鸟兽散";
            }
            case 5: {
                return "将死";
            }
            case 6: {
                return "明察秋毫";
            }
            case 7: {
                return "双管齐下";
            }
            case 8: {
                return "当头棒喝";
            }
            case 9: {
                return "以牙还牙";
            }
            case 10: {
                return "春暖花开";
            }
            case 11: {
                return "夺魂索命";
            }
            default: {
                return "";
            }
        }
    }
    
    public String getSkillid(String id) {
        int n = -1;
        switch (id.hashCode()) {
            case 1513195: {
                if (id.equals("1606")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1513196: {
                if (id.equals("1607")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 1513197: {
                if (id.equals("1608")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515181: {
                if (id.equals("1828")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515182: {
                if (id.equals("1829")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515204: {
                if (id.equals("1830")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515235: {
                if (id.equals("1840")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515236: {
                if (id.equals("1841")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515237: {
                if (id.equals("1842")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515304: {
                if (id.equals("1867")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515305: {
                if (id.equals("1868")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 1515306: {
                if (id.equals("1869")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "绝境逢生";
            }
            case 1: {
                return "子虚乌有";
            }
            case 2: {
                return "春回大地";
            }
            case 3: {
                return "化无";
            }
            case 4: {
                return "作鸟兽散";
            }
            case 5: {
                return "将死";
            }
            case 6: {
                return "明察秋毫";
            }
            case 7: {
                return "双管齐下";
            }
            case 8: {
                return "当头棒喝";
            }
            case 9: {
                return "以牙还牙";
            }
            case 10: {
                return "春暖花开";
            }
            case 11: {
                return "夺魂索命";
            }
            default: {
                return "";
            }
        }
    }
    
    public void moneyCQ() {
        this.npcfunction.clear();
        this.testMes.setText("小心哦，带着很多银钱在身上可是会有强盗打劫，所以还是存在钱庄比较保险");
        this.npcfunction.add("查看我的保险箱");
        this.npcfunction.add("我的钱太多了，想存起来");
        this.npcfunction.add("我没有钱花了，想把存款拿出来");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void selectValue(BigDecimal petId, int value) {
        this.removedizi();
        this.pettype = petId + "|" + value;
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("HP");
        this.npcfunction.add("MP");
        this.npcfunction.add("AP");
        this.npcfunction.add("SP");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("请选择下面其中一项增加" + value + "点的基础值");
        this.changeImage(null);
    }
    
    public void compulsoryDivorce() {
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("确定");
        this.npcfunction.add("我再考虑考虑");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("您的对象没有一起过来，您确定要单方面强制离婚（离婚费用：5000W两）。 ");
        this.changeImage(null);
    }
    
    public void openJxryd() {
        FormsManagement.HideForm(27);
        this.npcfunction.clear();
        this.npcfunction.add("直接打开");
        this.npcfunction.add("祈福（500W金币）");
        this.npcsStrings = new String[this.npcfunction.size()];
        for (int i = 0; i < this.npcsStrings.length; ++i) {
            this.npcsStrings[i] = (String)this.npcfunction.get(i);
        }
        this.list1.setListData(this.npcsStrings);
        this.testMes.setText("选择打开吉祥如意蛋的方式（祈福奖励更丰富）");
        this.changeImage(null);
    }
    
    public void GetOut() {
        PKSys pkSys = PKSys.getPkSys();
        if (pkSys.getPk2() == 0) {
            this.testMes.setText("一个良民瞎点啥,在闹请你进去");
            this.npcsStrings = new String[] { "我要回长安" };
        }
        else if (pkSys.getPk1() != 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(ImageMixDeal.userimg.getRoleShow().getRolename());
            buffer.append(",你还有");
            buffer.append(pkSys.getPk1());
            buffer.append("点PK标志未清除");
            this.testMes.setText(buffer.toString());
            this.npcsStrings = new String[] { "我什么都不做" };
        }
        else if (pkSys.getPk2() == 1) {
            this.testMes.setText("今天又收到红色炸弹了。哎日子难过啊");
            this.npcsStrings = new String[] { "这是8888W,小小意识", "别愁,我去帮你筹点钱", "哦?大爷是来办理出狱手续" };
        }
        else {
            this.testMes.setText("今天天气真好,是出狱的好日子");
            this.npcsStrings = new String[] { "我是来办理出狱手续" };
        }
        this.list1.setListData(this.npcsStrings);
        this.changeImage(null);
    }
    
    public void ZiShou() {
        PKSys pkSys = PKSys.getPkSys();
        if (pkSys.getPk1() == 0) {
            this.testMes.setText("一个良民瞎点啥,在闹请你进去");
            this.npcsStrings = new String[] { "我想去死牢探监", "我想去地牢探监", "我想去天牢探监" };
        }
        else {
            StringBuffer buffer = new StringBuffer();
            buffer.append(ImageMixDeal.userimg.getRoleShow().getRolename());
            buffer.append(",你还有");
            buffer.append(pkSys.getPk1());
            buffer.append("点PK标志自首可以减轻罪行");
            this.testMes.setText(buffer.toString());
            this.npcsStrings = new String[] { "我是来自首的", "我什么都不做" };
        }
        this.list1.setListData(this.npcsStrings);
        this.changeImage(null);
    }
    /** 玄印合成弹窗 */
    public void xuanyinhetanchuang() {
        npcfunction.clear();
        testMes.setText("玄印合成");
        npcfunction.add("我要合成玄印");
        npcfunction.add("我什么都不做");
        showList(null);
    }
    public void showCJS(Goodstable goodstable) {
        this.good = goodstable;
        this.testMes.setText("将彩晶石转换为对应的召唤兽饰品");
        this.npcfunction.clear();
        this.npcfunction.add("龙马饰品");
        this.npcfunction.add("白龙帝饰品");
        this.npcfunction.add("瀚威猫将的大刀");
        this.npcfunction.add("棕小仙饰品");
        this.npcfunction.add("罗刹鬼姬饰品");
        this.npcfunction.add("冥雷饰品");
        this.npcfunction.add("西域响马饰品");
        this.npcfunction.add("金不换饰品");
        this.npcfunction.add("松鼠饰品");
        this.npcfunction.add("拨浪鼓饰品");
        this.npcfunction.add("哥俩好饰品");
        this.npcfunction.add("孔雀明王饰品");
        this.npcfunction.add("赭炎饰品");
        this.npcfunction.add("画皮娘子饰品");
        this.npcfunction.add("冥灵妃子饰品");
        this.npcfunction.add("黄金兽饰品");
        this.npcfunction.add("剑精灵饰品");
        this.npcfunction.add("泥石怪饰品");
        this.npcfunction.add("冰雪魔饰品");
        this.npcfunction.add("蝴蝶仙子饰品");
        this.npcfunction.add("凤凰饰品");
        this.npcfunction.add("冲冲虫饰品");
        this.npcfunction.add("猪怪饰品");
        this.npcfunction.add("符咒女娲饰品");
        this.npcfunction.add("精卫饰品");
        this.npcfunction.add("猴精饰品");
        this.npcfunction.add("天龙女饰品");
        this.npcfunction.add("吉祥果饰品");
        this.npcfunction.add("狮虎兽饰品");
        this.npcfunction.add("妙音鸾女饰品");
        this.npcfunction.add("碧水精魄饰品");
        this.npcfunction.add("俏娘子饰品");
        this.npcfunction.add("木甲人饰品");
        this.npcfunction.add("孟极饰品");
        this.npcfunction.add("海螺姑娘饰品");
        this.showList(null);
    }
    
    public void showStarCardSupplement(Goodstable goodstable) {
        String warPower = goodstable.getValue().split("\\|")[2].split("=")[1];
        this.npcfunction.clear();
        this.testMes.setText("该星卡当前战力为:" + warPower + ",可以选择以下两种补充战力");
        this.npcfunction.add("星芒恢复(100点=1000点战力)");
        this.npcfunction.add("金币恢复(5000W=1000点战力)");
        this.showList(null);
    }
    
    public void anniutanchuang() {
        if (UserMessUntil.getChosePetMes() == null) {
            ZhuFrame.getZhuJpanel().addPrompt("请先参战召唤兽");
            return;
        }
        String pet = UserMessUntil.getChosePetMes().getSummoningname();
        this.npcfunction.clear();
        this.testMes.setText("你当前的召唤: 【" + pet + "】 选择下面的操作方式");
        this.npcfunction.add("转生当前召唤兽");
        this.npcfunction.add("点化当前召唤兽");
        npcfunction.add("我要启灵当前参战召唤兽");

        this.npcfunction.add("点化当前坐骑");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void shenshoudajiang() {
        if (UserMessUntil.getChosePetMes() == null) {
            ZhuFrame.getZhuJpanel().addPrompt("请先参战召唤兽");
            return;
        }
        String pet = UserMessUntil.getChosePetMes().getSummoningname();
        this.npcfunction.clear();
        this.testMes.setText("你当前的召唤: 【" + pet + "】 选择下面的操作方式");
        this.npcfunction.add("神兽开启技能格子(消耗游戏币2000万)");
        this.npcfunction.add("学习神兽技能(扣除50万亲密)");
        this.npcfunction.add("飞升当前参战神兽");
        this.npcfunction.add("我要学习天赋(扣除一定金钱和亲密,有概率失败!)");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void shuangbeijingyan() {
        this.npcfunction.clear();
        String name = RoleData.getRoleData().getLoginResult().getRolename();
        this.testMes.setText("亲爱的玩家: 【" + name + "】 选择下面的操作方式");
        this.npcfunction.add("我要领取一小时双倍时间");
        this.npcfunction.add("我要领取二小时双倍时间");
        this.npcfunction.add("我要冻结双倍时间");
        this.npcfunction.add("我要查询剩余双倍时间");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void dangpu() {
        this.npcfunction.clear();
        this.testMes.setText("你有多少东西，我这里都可以放的下。");
        this.npcfunction.add("一号当铺");
        this.npcfunction.add("二号当铺");
        this.npcfunction.add("三号当铺");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void wupinduihuan() {
        String name = RoleData.getRoleData().getLoginResult().getRolename();
        this.testMes.setText("亲爱的玩家: 【" + name + "】 选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("兑换超级龙之骨");
        this.npcfunction.add("兑换高级聚魄丹");
        this.npcfunction.add("尽请期待");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void yiyuan() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.testMes.setText("亲爱的玩家你当前的血量上限:【" + loginResult.getHp() + "】 选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要住店(扣除2000银两)");
        this.npcfunction.add("全部医治和修复");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void xianqihecheng() {
        this.testMes.setText("亲爱的玩家打造仙器请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要合成仙器");
        this.npcfunction.add("我要升级仙器");
        this.npcfunction.add("重铸仙器-悔梦石");
        this.npcfunction.add("洗炼仙器-超级悔梦");
        this.npcfunction.add("改变仙器模型");
        this.npcfunction.add("我要分解仙器");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void shenbingdazao() {
        this.testMes.setText("亲爱的玩家打造神兵请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要升级神兵");
        this.npcfunction.add("精炼神兵");
        this.npcfunction.add("我要上神兵石");
        this.npcfunction.add("我要合成炼妖石");
        this.npcfunction.add("符石合成重铸");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void baoshidazao() {
        this.testMes.setText("亲爱的玩家打造宝石请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要打造,摘抄宝石");
        this.npcfunction.add("我要合成宝石等");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void renwuzhuansheng() {
        this.testMes.setText("亲爱的玩家转生请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我已做好了转生准备");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void huangdaxian() {
        this.testMes.setText("亲爱的玩家请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我想转换种族");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void lingshoucun() {
        this.testMes.setText("我这里可以为召唤兽终极重新修炼!");
        this.npcfunction.clear();
        this.npcfunction.add("我想重新修炼终极技能");
        this.npcfunction.add("我什么也不做");
        this.showList(null);
    }
    
    public void shipinpeiyang() {
        this.testMes.setText("亲爱的玩家饰品打造请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要培养护身符");
        this.npcfunction.add("我要重铸护身符");
        this.npcfunction.add("我什么都不做");
        this.showList(null);
    }
    
    public void taozhuangdazao() {
        this.testMes.setText("亲爱的玩家套装打造请选择下面的操作方式");
        this.npcfunction.clear();
        this.npcfunction.add("我要兑换灵修值");
        this.npcfunction.add("我要收录玉符（玉符转符录）");
        this.npcfunction.add("我要查看已有符录（符录转玉符）");
        this.npcfunction.add("我想买点东西");
        this.npcfunction.add("我什么都不做");
        this.showList(null);
    }
    
    public void showList(String skin) {
        this.ys = 0;
        if (this.npcfunction.size() <= 7) {
            this.npcsStrings = new String[this.npcfunction.size()];
            for (int i = 0; i < this.npcfunction.size(); ++i) {
                this.npcsStrings[i] = (String)this.npcfunction.get(i);
            }
            this.list1.setListData(this.npcsStrings);
            this.changeImage(skin);
        }
        else {
            this.changeImage(skin);
            this.FY(0);
        }
    }
    
    public void FY(int value) {
        this.ys += value;
        int max = (this.npcfunction.size() - 1) / 7;
        if (this.ys > max) {
            this.ys = max;
        }
        else if (this.ys < 0) {
            this.ys = 0;
        }
        int Smin = this.ys * 7;
        int Smax = (this.ys + 1) * 7;
        if (Smax > this.npcfunction.size()) {
            Smax = this.npcfunction.size();
        }
        int size = Smax - Smin;
        ++size;
        if (this.ys != 0 && this.ys != max) {
            ++size;
        }
        this.npcsStrings = new String[size];
        if (this.ys != 0) {
            this.npcsStrings[0] = "上一页";
        }
        for (int i = Smin; i < Smax; ++i) {
            int p = i % 7;
            if (this.ys != 0) {
                ++p;
            }
            this.npcsStrings[p] = (String)this.npcfunction.get(i);
        }
        if (this.ys != max) {
            this.npcsStrings[this.npcsStrings.length - 1] = "下一页";
        }
        this.list1.setListData(this.npcsStrings);
        this.changeImage(null);
    }
    
    public String[] getNpcsStrings() {
        return this.npcsStrings;
    }
    
    public void setNpcsStrings(String[] npcsStrings) {
        this.npcsStrings = npcsStrings;
    }
    
    public List<String> getNpcfunction() {
        return this.npcfunction;
    }
    
    public void setNpcfunction(List<String> npcfunction) {
        this.npcfunction = npcfunction;
    }
    
    public JList<String> getList1() {
        return this.list1;
    }
    
    public void setList1(JList<String> list1) {
        this.list1 = list1;
    }
    
    public int getYs() {
        return this.ys;
    }
    
    public void setYs(int ys) {
        this.ys = ys;
    }
    
    public String getNpctype() {
        return this.npctype;
    }
    
    public void setNpctype(String npctype) {
        this.npctype = npctype;
    }
    
    public String getPettype() {
        return this.pettype;
    }
    
    public void setPettype(String pettype) {
        this.pettype = pettype;
    }
    
    public MapMonsterBean getMapMonsterBean() {
        return this.mapMonsterBean;
    }
    
    public void setMapMonsterBean(MapMonsterBean mapMonsterBean) {
        this.mapMonsterBean = mapMonsterBean;
    }
    
    public TaskProgress getTaskMonster() {
        return this.taskMonster;
    }
    
    public void setTaskMonster(TaskProgress taskMonster) {
        this.taskMonster = taskMonster;
    }
    
    public Npctable getNpctable() {
        return this.npctable;
    }
    
    public void setNpctable(Npctable npctable) {
        this.npctable = npctable;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return this.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        this.Fvalue = fvalue;
    }
    
    public NpcInfoBean getNpcInfoBean() {
        return this.npcInfoBean;
    }
    
    public void setNpcInfoBean(NpcInfoBean npcInfoBean) {
        this.npcInfoBean = npcInfoBean;
    }
    
    public ImageIcon getIcon() {
        return NPCJpanel.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        NPCJpanel.icon = icon;
    }


    public void xuanbaoxiulian() {
        this.npcfunction.clear();
        this.testMes.setText("请选择提升玄蕴的方式");
        this.npcfunction.add("使用玄蕴禄/极蕴禄");
        this.npcfunction.add("人物经验转换");
        this.npcfunction.add("取消");
        showList(null);
    }
}
