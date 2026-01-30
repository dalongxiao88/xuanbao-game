package org.come.Jpanel;

import java.util.Set;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.util.Calendar;
import org.come.npc.TP;
import org.come.model.Door;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.until.Util;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.bean.AllMapBean;
import java.util.Iterator;
import org.come.bean.AllNpcBean;
import org.come.model.Npctable;
import java.util.HashMap;
import org.come.model.Gamemap;
import org.come.bean.NpcInfoBean;
import org.come.until.UserMessUntil;
import java.util.ArrayList;
import org.come.until.ScrollUI;
import org.come.until.SrcollPanelUI;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import org.come.entity.Goodstable;
import java.util.Map;
import java.util.List;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChaojifeiListJpanel extends JPanel
{
    private JLabel labtext1;
    private JLabel labtext2;
    private JLabel labtext3;
    private JLabel labtext4;
    private static JLabel labtext5;
    private static JLabel labtext6;
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
    private static List<Map<String, Object>> dataNpcMapListSX;
    private static List<Map<String, Object>> dataNpcMapList;
    private static List<Map<String, Object>> dataNpcMapListgds;
    private static List<String> selectNpc;
    private Goodstable good;
    private static String[] Fvalue;
    private static JTextArea testMes;
    private static List<String> npcfunction;
    private static int ys;
    private static String[] npcsStrings;
    private static JList<String> list1;
    private static ImageIcon headImg;
    private static int headHeight;
    public String npczb;
    public static String npcfeiji;
    private static DefaultTableModel tableModel;
    private ImageIcon icon;
    
    public ChaojifeiListJpanel() {
        this.day = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(540, 480));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (ChaojifeiListJpanel.findName = new JTextField()).setBounds(115, 112, 210, 20);
            ChaojifeiListJpanel.findName.setOpaque(false);
            ChaojifeiListJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.findName.setForeground(Color.white);
            ChaojifeiListJpanel.findName.setCaretColor(Color.white);
            ChaojifeiListJpanel.findName.setFont(new Font("宋体", 0, 15));
            this.add(ChaojifeiListJpanel.findName);
            Font font = new Font("楷体", 0, 16);
            (ChaojifeiListJpanel.labtext5 = new JLabel()).setForeground(Color.white);
            ChaojifeiListJpanel.labtext5.setFont(font);
            ChaojifeiListJpanel.labtext5.setBounds(115, 69, 320, 20);
            ChaojifeiListJpanel.labtext5.setText("请输入并搜索NPC");
            this.add(ChaojifeiListJpanel.labtext5);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1101);
            offBtn.setBounds(503, 10, 25, 25);
            this.add(offBtn);
            font = new Font("楷体", 1, 20);
            (this.labtext3 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(288, -10, 250, 50);
            this.labtext3.setText("NPC查询");
            font = new Font("楷体", 0, 16);
            (this.labtext4 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(70, 45, 250, 50);
            this.labtext4.setText("NPC名称");
            if (ChaojifeiListJpanel.dataNpcMapList != null && ChaojifeiListJpanel.dataNpcMapList.size() > 0) {
                getlisysta(ChaojifeiListJpanel.dataNpcMapList);
            }
            else {
                getlisysta(null);
            }
            (ChaojifeiListJpanel.jScrollPane1 = new JScrollPane(ChaojifeiListJpanel.listPank)).setVerticalScrollBarPolicy(22);
            ChaojifeiListJpanel.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            ChaojifeiListJpanel.jScrollPane1.getViewport().setOpaque(false);
            ChaojifeiListJpanel.jScrollPane1.setOpaque(false);
            ChaojifeiListJpanel.jScrollPane1.setBounds(23, 176, 131, 276);
            ChaojifeiListJpanel.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(ChaojifeiListJpanel.jScrollPane1);
            (ChaojifeiListJpanel.jScrollPane2 = new JScrollPane(ChaojifeiListJpanel.listPank1)).setVerticalScrollBarPolicy(22);
            ChaojifeiListJpanel.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            ChaojifeiListJpanel.jScrollPane2.getViewport().setOpaque(false);
            ChaojifeiListJpanel.jScrollPane2.setOpaque(false);
            ChaojifeiListJpanel.jScrollPane2.setBounds(163, 176, 352, 276);
            ChaojifeiListJpanel.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(ChaojifeiListJpanel.jScrollPane2);
            (ChaojifeiListJpanel.sureGive = new RoleCaoZuoBtn("inkImg/button1/B21.png", 1, "搜 索", 1101, UIUtils.COLOR_BLACK)).setBounds(350, 109, 79, 24);
            this.add(ChaojifeiListJpanel.sureGive);
            (ChaojifeiListJpanel.sureGive = new RoleCaoZuoBtn("inkImg/button1/B21.png", 1, "传 送", 1102, UIUtils.COLOR_BLACK)).setBounds(430, 109, 79, 24);
        }
        else {
            this.setPreferredSize(new Dimension(800, 600));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (ChaojifeiListJpanel.findName = new JTextField()).setBounds(135, 60, 128, 20);
            ChaojifeiListJpanel.findName.setOpaque(false);
            ChaojifeiListJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.findName.setForeground(Color.white);
            ChaojifeiListJpanel.findName.setCaretColor(Color.white);
            ChaojifeiListJpanel.findName.setFont(new Font("宋体", 0, 15));
            Font font = new Font("楷体", 0, 16);
            (ChaojifeiListJpanel.labtext5 = new JLabel()).setForeground(Color.white);
            ChaojifeiListJpanel.labtext5.setFont(font);
            ChaojifeiListJpanel.labtext5.setBounds(390, 60, 128, 20);
            ChaojifeiListJpanel.labtext5.setText("请输入并搜索NPC");
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 1101);
            offBtn.setBounds(635, 0, 25, 25);
            this.add(offBtn);
            font = new Font("楷体", 1, 20);
            (this.labtext3 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext3.setFont(font);
            this.labtext3.setBounds(288, -10, 250, 50);
            this.labtext3.setText("NPC查询");
            font = new Font("楷体", 0, 16);
            (this.labtext4 = new JLabel()).setForeground(Color.YELLOW);
            this.labtext4.setFont(font);
            this.labtext4.setBounds(70, 45, 250, 50);
            this.labtext4.setText("NPC名称");
            this.add(this.labtext4);
            if (ChaojifeiListJpanel.dataNpcMapList != null && ChaojifeiListJpanel.dataNpcMapList.size() > 0) {
                getlisysta(ChaojifeiListJpanel.dataNpcMapList);
            }
            else {
                getlisysta(null);
            }
            (ChaojifeiListJpanel.jScrollPane1 = new JScrollPane(ChaojifeiListJpanel.listPank)).setVerticalScrollBarPolicy(22);
            ChaojifeiListJpanel.jScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
            ChaojifeiListJpanel.jScrollPane1.getViewport().setOpaque(false);
            ChaojifeiListJpanel.jScrollPane1.setOpaque(false);
            ChaojifeiListJpanel.jScrollPane1.setBounds(28, 117, 193, 317);
            ChaojifeiListJpanel.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(ChaojifeiListJpanel.jScrollPane1);
            (ChaojifeiListJpanel.jScrollPane2 = new JScrollPane(ChaojifeiListJpanel.listPank1)).setVerticalScrollBarPolicy(22);
            ChaojifeiListJpanel.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            ChaojifeiListJpanel.jScrollPane2.getViewport().setOpaque(false);
            ChaojifeiListJpanel.jScrollPane2.setOpaque(false);
            ChaojifeiListJpanel.jScrollPane2.setBounds(235, 117, 396, 317);
            ChaojifeiListJpanel.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            ChaojifeiListJpanel.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(ChaojifeiListJpanel.jScrollPane2);
            (ChaojifeiListJpanel.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "搜索", 1101, UIUtils.COLOR_BTNPUTONG)).setBounds(260, 55, 68, 26);
            this.add(ChaojifeiListJpanel.sureGive);
            (ChaojifeiListJpanel.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "传送", 1102, UIUtils.COLOR_BTNPUTONG)).setBounds(555, 55, 68, 26);
        }
    }
    
    public void getNpcName(int indexs) {
        if (ChaojifeiListJpanel.dataNpcMapListSX != null && ChaojifeiListJpanel.dataNpcMapListSX.size() > 0) {
            Map<String, Object> maps = (Map<String, Object>)ChaojifeiListJpanel.dataNpcMapListSX.get(indexs);
            List<Map<String, Object>> list = (List)maps.get("npcList");
            for (int i = 0; i < list.size(); ++i) {
                (ChaojifeiListJpanel.listPank1 = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
                ChaojifeiListJpanel.listPank1.setSelectionForeground(Color.yellow);
                ChaojifeiListJpanel.listPank1.setForeground(Color.white);
                ChaojifeiListJpanel.listPank1.setFont(new Font("楷体", 1, 16));
                ChaojifeiListJpanel.listPank1.setBackground(new Color(0, 0, 0, 0));
                ChaojifeiListJpanel.listPank1.setFixedCellHeight(25);
                ChaojifeiListJpanel.pankModel1.addElement(((Map<String, Object>)list.get(i)).get("npcName").toString());
                ChaojifeiListJpanel.listPank1.setModel(ChaojifeiListJpanel.pankModel1);
            }
        }
    }
    
    public static List<Map<String, Object>> getlist(String name) {
        String text = ChaojifeiListJpanel.findName.getText();
        List<Map<String, Object>> dataNpcMapList = new ArrayList<>();
        List<Npctable> npcList = new ArrayList<>();
        AllNpcBean allNpcBean = UserMessUntil.getAllNpcBean();
        if (allNpcBean != null) {
            Map<String, NpcInfoBean> npc = allNpcBean.getAllNpcInfo();
            for (Map.Entry<String, NpcInfoBean> entry : npc.entrySet()) {
                if (((NpcInfoBean)entry.getValue()).getNpctable().getNpcname() != null && !((NpcInfoBean)entry.getValue()).getNpctable().getNpcname().equals("")) {
                    npcList.add(((NpcInfoBean)entry.getValue()).getNpctable());
                }
            }
        }
        AllMapBean allMapBean = UserMessUntil.getAllmapbean();
        Map<String, Gamemap> mapa = allMapBean.getAllMap();
        List<Gamemap> mapListALL = new ArrayList<>();
        List<Gamemap> mapList = new ArrayList<>();
        for (Map.Entry<String, Gamemap> entry2 : mapa.entrySet()) {
            if (((Gamemap)entry2.getValue()).getMapnpc() != null && !((Gamemap)entry2.getValue()).getMapnpc().equals("")) {
                mapListALL.add(entry2.getValue());
            }
        }
        if (mapListALL != null && mapListALL.size() > 0) {
            for (Gamemap gamemap : mapListALL) {
                if (gamemap.getMapflag().equals("1")) {
                    mapList.add(gamemap);
                }
            }
        }
        if (mapList != null && mapList.size() > 0) {
            for (Gamemap gamemap : mapList) {
                List<Map<String, Object>> mapNpcList = new ArrayList<>();
                Map<String, Object> maps = new HashMap<>();
                int x = Integer.parseInt(gamemap.getWidth()) / 2;
                int y = Integer.parseInt(gamemap.getHeight()) / 2;
                String mapName = gamemap.getMapname();
                String mapxy = x + "," + y;
                String mapid = gamemap.getMapid();
                String[] mapnpc = gamemap.getMapnpc().split("\\|");
                List<String> mapnpcList = new ArrayList<>();
                if (mapnpc != null && mapnpc.length > 0) {
                    for (String npcs : mapnpc) {
                        int result1 = npcs.indexOf("-");
                        if (result1 != -1) {
                            String[] mapnpcs = npcs.split("\\-");
                            if (mapnpcs.length > 1) {
                                for (int i = Integer.parseInt(mapnpcs[1]) - Integer.parseInt(mapnpcs[0]) + 1, j = 0; j < i; ++j) {
                                    int s = Integer.parseInt(mapnpcs[0]) + j;
                                    mapnpcList.add(s + "");
                                }
                            }
                        }
                        else {
                            mapnpcList.add(npcs);
                        }
                    }
                    if (mapnpcList != null && mapnpcList.size() > 0) {
                        for (String npc2 : mapnpcList) {
                            for (Npctable npctable : npcList) {
                                if (npc2.equals(npctable.getNpcid())) {
                                    Map<String, Object> mapNpc = new HashMap<>();
                                    mapNpc.put("npcName", npctable.getNpcname());
                                    mapNpc.put("npcx", npctable.getTx());
                                    mapNpc.put("npcy", npctable.getTy());
                                    mapNpcList.add(mapNpc);
                                }
                            }
                        }
                    }
                }
                maps.put("mapName", mapName);
                maps.put("mapxy", mapxy);
                maps.put("mapid", mapid);
                if (mapNpcList != null && mapNpcList.size() > 0) {
                    maps.put("npcList", mapNpcList);
                    dataNpcMapList.add(maps);
                }
            }
        }
        List<Map<String, Object>> dataNpcMapListJS = new ArrayList<>();
        if (dataNpcMapList != null && dataNpcMapList.size() > 0) {
            for (Map<String, Object> map : dataNpcMapList) {
                Map<String, Object> maps = new HashMap<>();
                List<Map<String, Object>> mapNpcList2 = new ArrayList<>();
                List<Map<String, Object>> mapNpcList3 = (List)map.get("npcList");
                if (mapNpcList3 != null && mapNpcList3.size() > 0) {
                    for (Map<String, Object> map2 : mapNpcList3) {
                        int result2 = map2.get("npcName").toString().indexOf(text);
                        if (result2 != -1) {
                            mapNpcList2.add(map2);
                            maps = map;
                            maps.put("npcList", mapNpcList2);
                            dataNpcMapListJS.add(maps);
                        }
                    }
                }
            }
        }
        if (text != null && text.length() > 0) {
            dataNpcMapList = (ChaojifeiListJpanel.dataNpcMapListgds = removeRepeatMapByKey(dataNpcMapListJS, "mapName"));
        }
        ChaojifeiListJpanel.dataNpcMapListSX = dataNpcMapList;
        ChaojifeiListJpanel();
        return dataNpcMapList;
    }
    
    public static void getlisysta(List<Map<String, Object>> dataNpcMapList) {
        if (dataNpcMapList == null || dataNpcMapList.size() <= 0) {
            dataNpcMapList = getlist(ChaojifeiListJpanel.findName.getText());
        }
        ChaojifeiListJpanel.pankModel = new DefaultListModel<>();
        for (int i = 0; i < dataNpcMapList.size(); ++i) {
            Font font = new Font("楷体", 0, 18);
            (ChaojifeiListJpanel.listPank = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            ChaojifeiListJpanel.listPank.setSelectionForeground(Color.yellow);
            ChaojifeiListJpanel.listPank.setForeground(Color.white);
            ChaojifeiListJpanel.listPank.setFont(new Font("楷体", 1, 16));
            ChaojifeiListJpanel.listPank.setBackground(new Color(0, 0, 0, 0));
            ChaojifeiListJpanel.listPank.setFixedCellHeight(25);
            ChaojifeiListJpanel.pankModel.addElement(((Map<String, Object>)dataNpcMapList.get(i)).get("mapName").toString() + "," + ((Map<String, Object>)dataNpcMapList.get(i)).get("mapid").toString());
            ChaojifeiListJpanel.listPank.setModel(ChaojifeiListJpanel.pankModel);
            ChaojifeiListJpanel.pankModel1 = new DefaultListModel<>();
            (ChaojifeiListJpanel.listPank1 = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            ChaojifeiListJpanel.listPank1.setSelectionForeground(Color.white);
            ChaojifeiListJpanel.listPank1.setForeground(Color.yellow);
            ChaojifeiListJpanel.listPank1.setFont(new Font("楷体", 1, 16));
            ChaojifeiListJpanel.listPank1.setBackground(new Color(0, 0, 0, 0));
            ChaojifeiListJpanel.listPank1.setFixedCellHeight(25);
            ChaojifeiListJpanel.pankModel1.addElement("点击要查看的地图名称");
            ChaojifeiListJpanel.listPank1.setModel(ChaojifeiListJpanel.pankModel1);
            ChaojifeiListJpanel.listPank.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChaojifeiListJpanel.pankModel1 = new DefaultListModel<>();
                    if (ChaojifeiListJpanel.listPank.getSelectedIndex() != -1) {
                        int indexs = ChaojifeiListJpanel.listPank.getSelectedIndex();
                        Map<String, Object> maps = (Map)ChaojifeiListJpanel.dataNpcMapListSX.get(indexs);
                        List<Map<String, Object>> list = (List)maps.get("npcList");
                        for (int i = 0; i < list.size(); ++i) {
                            ChaojifeiListJpanel.pankModel1.addElement(((Map<String, Object>)list.get(i)).get("npcName").toString() + "," + maps.get("mapid").toString() + "," + maps.get("mapName").toString() + "(" + Integer.parseInt(((Map<String, Object>)list.get(i)).get("npcx").toString()) / 20 + "," + Integer.parseInt(((Map<String, Object>)list.get(i)).get("npcy").toString()) / 20 + ")");
                            ChaojifeiListJpanel.listPank1.setModel(ChaojifeiListJpanel.pankModel1);
                            ChaojifeiListJpanel.listPank1.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                }
                                
                                @Override
                                public void mousePressed(MouseEvent e) {
                                }
                                
                                @Override
                                public void mouseExited(MouseEvent e) {
                                }
                                
                                @Override
                                public void mouseEntered(MouseEvent e) {
                                }
                                
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    ChaojifeiListJpanel.pankModel1 = new DefaultListModel<>();
                                    if (ChaojifeiListJpanel.listPank1.getSelectedIndex() != -1) {
                                        List<String> kka = ChaojifeiListJpanel.listPank1.getSelectedValuesList();
                                        ChaojifeiListJpanel.feizb(kka);
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }
    
    public static void iWantToFly() {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以移动！");
            return;
        }
        String flag = Util.mapmodel.getGamemap().getMapflag();
        if (flag == null || !flag.equals("1") || ImageMixDeal.userimg.getTeams() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前地图不可以飞行！");
            return;
        }
        if (ChaojifeiListJpanel.npcfeiji == null || ChaojifeiListJpanel.npcfeiji.length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先搜索NPC!!!");
        }
        else {
            String kkf = "传送=" + ChaojifeiListJpanel.npcfeiji + ", 可用次数=10";
            String kkf2 = "传送=" + ChaojifeiListJpanel.npcfeiji;
            String kkf3 = ChaojifeiListJpanel.npcfeiji;
            String[] vs = kkf.split(",");
            String[] cishu = vs[vs.length - 1].split("=");
            int sum = Integer.parseInt(cishu[1]);
            --sum;
            String[] path = null;
            StringBuffer buffer = new StringBuffer();
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(kkf2);
            if (path == null) {
                String[] pathv = kkf3.split(",");
                String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                path = pathv;
            }
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(cishu[0]);
            buffer.append("=");
            buffer.append(sum);
            if (path != null) {
                if (sum <= 0) {
                    NPCJfram.getNpcJfram().getNpcjpanel().getGood().setUsetime(Integer.valueOf(0));
                }
                GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
                if (sum <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(NPCJfram.getNpcJfram().getNpcjpanel().getGood().getRgid());
                }
                Door door = new Door();
                door.setDoormap(path[0]);
                door.setDoorpoint(path[2] + "|" + path[3]);
                try {
                    TP.tp(door, 1);
                }
                catch (Exception var9) {
                    var9.printStackTrace();
                }
            }
        }
    }
    
    private static void ChaojifeiListJpanel() {
    }
    
    public static List<Map<String, Object>> findEnsure() {
        if (ChaojifeiListJpanel.findName.getText() == null || ChaojifeiListJpanel.findName.getText().length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入要查找的NPC名称！");
            return null;
        }
        List<Map<String, Object>> dataNpcMapList = new ArrayList<>();
        List<Npctable> npcList = new ArrayList<>();
        AllNpcBean allNpcBean = UserMessUntil.getAllNpcBean();
        if (allNpcBean != null) {
            Map<String, NpcInfoBean> npc = allNpcBean.getAllNpcInfo();
            for (Map.Entry<String, NpcInfoBean> entry : npc.entrySet()) {
                if (((NpcInfoBean)entry.getValue()).getNpctable().getNpcname() != null && !((NpcInfoBean)entry.getValue()).getNpctable().getNpcname().equals("")) {
                    npcList.add(((NpcInfoBean)entry.getValue()).getNpctable());
                }
            }
        }
        AllMapBean allMapBean = UserMessUntil.getAllmapbean();
        Map<String, Gamemap> mapa = allMapBean.getAllMap();
        List<Gamemap> mapListALL = new ArrayList<>();
        List<Gamemap> mapList = new ArrayList<>();
        for (Map.Entry<String, Gamemap> entry2 : mapa.entrySet()) {
            if (((Gamemap)entry2.getValue()).getMapnpc() != null && !((Gamemap)entry2.getValue()).getMapnpc().equals("")) {
                mapListALL.add(entry2.getValue());
            }
        }
        if (mapListALL != null && mapListALL.size() > 0) {
            for (Gamemap gamemap : mapListALL) {
                if (gamemap.getMapflag().equals("1")) {
                    mapList.add(gamemap);
                }
            }
        }
        if (mapList != null && mapList.size() > 0) {
            for (Gamemap gamemap : mapList) {
                List<Map<String, Object>> mapNpcList = new ArrayList<>();
                Map<String, Object> maps = new HashMap<>();
                int x = Integer.parseInt(gamemap.getWidth()) / 2;
                int y = Integer.parseInt(gamemap.getHeight()) / 2;
                String mapid = gamemap.getMapid();
                String mapName = gamemap.getMapname();
                String mapxy = x + "," + y;
                String[] mapnpc = gamemap.getMapnpc().split("\\|");
                List<String> mapnpcList = new ArrayList<>();
                if (mapnpc != null && mapnpc.length > 0) {
                    for (String npcs : mapnpc) {
                        int result1 = npcs.indexOf("-");
                        if (result1 != -1) {
                            String[] mapnpcs = npcs.split("\\-");
                            if (mapnpcs.length > 1) {
                                for (int i = Integer.parseInt(mapnpcs[1]) - Integer.parseInt(mapnpcs[0]) + 1, j = 0; j < i; ++j) {
                                    int s = Integer.parseInt(mapnpcs[0]) + j;
                                    mapnpcList.add(s + "");
                                }
                            }
                        }
                        else {
                            mapnpcList.add(npcs);
                        }
                    }
                    if (mapnpcList != null && mapnpcList.size() > 0) {
                        for (String npc2 : mapnpcList) {
                            for (Npctable npctable : npcList) {
                                if (npc2.equals(npctable.getNpcid())) {
                                    Map<String, Object> mapNpc = new HashMap<>();
                                    mapNpc.put("npcName", npctable.getNpcname());
                                    mapNpc.put("npcx", npctable.getTx());
                                    mapNpc.put("npcy", npctable.getTy());
                                    mapNpcList.add(mapNpc);
                                }
                            }
                        }
                    }
                }
                maps.put("mapid", mapid);
                maps.put("mapName", mapName);
                maps.put("mapxy", mapxy);
                if (mapNpcList != null && mapNpcList.size() > 0) {
                    maps.put("npcList", mapNpcList);
                    dataNpcMapList.add(maps);
                }
            }
        }
        List<Map<String, Object>> dataNpcMapListC = new ArrayList<>();
        if (dataNpcMapList != null && dataNpcMapList.size() > 0) {
            for (Map<String, Object> map : dataNpcMapList) {
                for (Gamemap gamemap2 : mapList) {
                    if (map.get("mapid").toString().equals(gamemap2.getMapid())) {
                        dataNpcMapListC.add(map);
                    }
                }
            }
        }
        dataNpcMapList = dataNpcMapListC;
        String text = ChaojifeiListJpanel.findName.getText();
        List<Map<String, Object>> dataNpcMapListJS = new ArrayList<>();
        if (dataNpcMapList != null && dataNpcMapList.size() > 0) {
            for (Map<String, Object> map2 : dataNpcMapList) {
                Map<String, Object> maps2 = new HashMap<>();
                List<Map<String, Object>> mapNpcList2 = new ArrayList<>();
                List<Map<String, Object>> mapNpcList3 = (List)map2.get("npcList");
                if (mapNpcList3 != null && mapNpcList3.size() > 0) {
                    for (Map<String, Object> map3 : mapNpcList3) {
                        int result2 = map3.get("npcName").toString().indexOf(text);
                        if (result2 != -1) {
                            mapNpcList2.add(map3);
                            maps2 = map2;
                            maps2.put("npcList", mapNpcList2);
                            dataNpcMapListJS.add(maps2);
                        }
                    }
                }
            }
        }
        if (text != null && text.length() > 0) {
            dataNpcMapList = removeRepeatMapByKey(dataNpcMapListJS, "mapName");
        }
        if (dataNpcMapList != null && dataNpcMapList.size() > 0) {
            List<Map<String, Object>> npc3 = (List)((Map<String, Object>)dataNpcMapList.get(0)).get("npcList");
            if (npc3 != null && npc3.size() > 0) {
                ChaojifeiListJpanel.labtext5.setText(((Map<String, Object>)dataNpcMapList.get(0)).get("mapName").toString() + "(" + Long.parseLong(((Map<String, Object>)npc3.get(0)).get("npcx").toString()) / 20L + "," + Long.parseLong(((Map<String, Object>)npc3.get(0)).get("npcy").toString()) / 20L + ")");
                ChaojifeiListJpanel.npcfeiji = ((Map<String, Object>)dataNpcMapList.get(0)).get("mapid").toString() + "," + ((Map<String, Object>)dataNpcMapList.get(0)).get("mapName").toString() + "," + ((Map<String, Object>)npc3.get(0)).get("npcx").toString() + "," + ((Map<String, Object>)npc3.get(0)).get("npcy").toString();
            }
        }
        else {
            ChaojifeiListJpanel.labtext5.setText("未查到NPC！");
            ChaojifeiListJpanel.npcfeiji = null;
        }
        return dataNpcMapList;
    }
    
    private static void ChaojifeiListJpanel(List<Map<String, Object>> dataNpcMapList) {
    }
    
    public static void feizb(List<String> selectNpc) {
        String feiji = (String)selectNpc.get(0);
        String str11 = feiji.substring(0, feiji.indexOf(","));
        String str12 = feiji.substring(str11.length() + 1, feiji.length());
        String str13 = str12.substring(0, str12.indexOf(","));
        String str14 = str12.substring(str13.length() + 1, str12.length());
        ChaojifeiListJpanel.labtext5.setText(str14);
        feiji = feiji.replace("(", ",");
        feiji = feiji.replace(")", "");
        String str15 = feiji.substring(0, feiji.indexOf(","));
        String str16 = feiji.substring(str15.length() + 1, feiji.length());
        String[] str17 = str16.split(",");
        str17[2] = Long.parseLong(str17[2]) * 20L + "";
        str17[3] = Long.parseLong(str17[3]) * 20L + "";
        ChaojifeiListJpanel.npcfeiji = str17[0] + "," + str17[1] + "," + str17[2] + "," + str17[3];
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
            this.icon = CutButtonImage.getImage("inkImg/background1/B331.png", 540, 480);
            g.drawImage(this.icon.getImage(), 0, 0, 540, 480, this);
        }
        else {
            this.icon = CutButtonImage.getImage("img/xy2uiimg/chaojifei.png", 659, 472);
            g.drawImage(this.icon.getImage(), 0, 0, 659, 472, this);
        }
    }
    
    public DefaultTableModel getTableModel() {
        return ChaojifeiListJpanel.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        ChaojifeiListJpanel.tableModel = tableModel;
    }
    
    public static JTextField getFindName() {
        return ChaojifeiListJpanel.findName;
    }
    
    public static void setFindName(JTextField findName) {
        ChaojifeiListJpanel.findName = findName;
    }
    
    public static JTextField getFindName1() {
        return ChaojifeiListJpanel.findName1;
    }
    
    public static void setFindName1(JTextField findName1) {
        ChaojifeiListJpanel.findName1 = findName1;
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    public void setGood(Goodstable good) {
        this.good = good;
    }
    
    public String[] getFvalue() {
        return ChaojifeiListJpanel.Fvalue;
    }
    
    public void setFvalue(String[] fvalue) {
        ChaojifeiListJpanel.Fvalue = fvalue;
    }
    
    public JTextArea getTestMes() {
        return ChaojifeiListJpanel.testMes;
    }
    
    public void setTestMes(JTextArea testMes) {
        ChaojifeiListJpanel.testMes = testMes;
    }
    
    public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String mapKey) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for (int i = list.size() - 1; i >= 0; --i) {
            Map map = (Map<String, Object>)list.get(i);
            String id = (String)map.get(mapKey);
            map.remove(mapKey);
            msp.put(id, map);
        }
        Set<String> mspKey = msp.keySet();
        for (String key : mspKey) {
            Map<String, Object> newMap = (Map)msp.get(key);
            newMap.put(mapKey, key);
            listMap.add(newMap);
        }
        return listMap;
    }
    
    static {
        ChaojifeiListJpanel.npcfunction = new ArrayList<>();
        ChaojifeiListJpanel.ys = 0;
        ChaojifeiListJpanel.headHeight = 0;
    }
}
