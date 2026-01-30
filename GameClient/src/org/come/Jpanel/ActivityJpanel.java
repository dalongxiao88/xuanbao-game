package org.come.Jpanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.tool.btn.VipShopBtn;
import com.tool.role.RoleData;
import org.come.Frame.ActivityJframe;
import org.come.Frame.ImpactGradeJframe;
import org.come.bean.AllActive;
import org.come.entity.Goodstable;
import org.come.model.ActiveAward;
import org.come.model.ActiveBase;
import org.come.mouslisten.WLLMouslisten;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.*;

import com.tool.ModerateTask.TaskRoleData;
import com.tool.btn.ActivityBtn;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

/**
 * 活动主面板 编号:40
 *
 * @author HGC<br>
 * @time 2020年3月2日 上午10:05:41<br>
 * @class 类名:ActivityJpanel<br>
 */
public class ActivityJpanel extends JPanel {
    private ActivityBtn cjBtn;
    /**
     * 活动列表，活动详情
     */
    private JScrollPane paneList, paneDetails;
    /**
     * 活跃度
     */
    private JLabel[] labVitality;

    private ActivityBtn[] btnVitality;

    private RichLabel richDetails;

    private JList<ActivityModelJpanel> JlistActivityModelPanel;
    private JLabel labNowVitality;
    private Map<Integer, ActivityModelJpanel> mapActivityModelPanel;

    /**
     * 总的活跃度
     */
    private int nowVitality = 0;
    public static String WBXS = "";
    public static int[] CJTYPE = new int[6];
    public static String CJTYPENUM = "";
    public static String[] RCCJTYPE = {"小鬼", "天庭", "鬼王", "修罗", "域外", "宝象"};

    public String[] iscj = new String[6];
    public List<String> goodsIds = new ArrayList<>();
    public static Boolean start = false;
    public  static  JLabel goodImg;
    public  static  JLabel ts;

    public ActivityJpanel() {
//    	if(MyIsif.getStyle().equals("水墨")) {
        setPreferredSize(new Dimension(711, 444));
        setOpaque(false);
        setLayout(null);

        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/acClose.png", 1, 40);
        offBtn.setBounds(711 - 35, 15, 16, 16);
        this.add(offBtn);

        getPaneList();
        getPaneDetails();
        getLabNowVitality();

        showView();
        cjBtn = new ActivityBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_HUI1, "抽奖", UIUtils.TEXT_FONT61, 2,
                null);
        cjBtn.setBounds(490, 385, 73, 36);
        cjBtn.setVisible(false);
        this.add(cjBtn);
        goodImg =new JLabel();
        goodImg.setBounds(566, 100, 52, 52);
        ImageIcon image = CutButtonImage.getImage("img/item/b115.png", 52, 52);
        goodImg.setIcon(image);
        this.add(goodImg);

        ts =new JLabel();
        ts.setBounds(637, 304, 18, 18);
        ts.addMouseListener(new WLLMouslisten(9999));
        this.add(ts);
//    	}else {
//
//    		setPreferredSize(new Dimension(653, 479));
//    		setOpaque(false);
//    		setLayout(null);
//
//    		// 关闭按钮事件
//    		FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 40);
//    		offBtn.setBounds(628, 0, 25, 25);
//    		this.add(offBtn);
//
//    		getPaneList();
//    		getPaneDetails();
//    		getLabNowVitality();
//
//    		showView();
//    	}
    }


    /**
     * 列表鼠标监听
     */
    class PaneListMouse extends MouseAdapter {
        private ActivityModelJpanel activityModelJpanel;

        public PaneListMouse(ActivityModelJpanel activityModelJpanel) {
            this.activityModelJpanel = activityModelJpanel;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            ActiveBase activeBase = activityModelJpanel.getActiveBase();
            if (activeBase == null)
                return;
            richDetails.setText(activeBase.getText());
            Dimension d = richDetails.computeSize(166);
            richDetails.setSize(d);
            richDetails.setPreferredSize(d);
        }

    }

    /**
     * 展示面板
     */
    public void showView() {
//        if (MyIsif.getStyle().equals("水墨")) {
        AllActive allActive = UserMessUntil.getAllActive();
        if (allActive == null) {
            return;
        }
        nowVitality = 0;
        mapActivityModelPanel = new HashMap<Integer, ActivityModelJpanel>();
        ActiveBase[] activeBases = allActive.getBases();
        for (int i = 0; i < activeBases.length; i++) {
            ActiveBase activeBase = activeBases[i];
            int sumReceive = TaskRoleData.SumReceive(activeBase.getSid(), 2);
            if (activeBase.getSid() == 101) {
                CJTYPE[0] = sumReceive;
            }
            if (activeBase.getSid() == 102) {
                CJTYPE[1] = sumReceive;
            }
            if (activeBase.getSid() == 103) {
                CJTYPE[2] = sumReceive;
            }
            if (activeBase.getSid() == 104) {
                CJTYPE[3] = sumReceive;
            }
            if (activeBase.getSid() == 105) {
                CJTYPE[4] = sumReceive;
            }
            if (activeBase.getSid() == 106) {
                CJTYPE[5] = sumReceive;
            }
            ActivityModelJpanel activityModelJpanel = new ActivityModelJpanel();
            activityModelJpanel.setBounds(0, 0 + i * 76, 360, 76);
            activityModelJpanel.showActiveBase(activeBase, sumReceive);
            if (activeBase.getNum() < sumReceive) {
                sumReceive = activeBase.getNum();
            }
            nowVitality += (sumReceive * activeBase.getValue());
            activityModelJpanel.addMouseListener(new PaneListMouse(activityModelJpanel));
            JlistActivityModelPanel.add(activityModelJpanel);
            mapActivityModelPanel.put(activeBase.getSid(), activityModelJpanel);
        }
        JlistActivityModelPanel.setPreferredSize(new Dimension(350, 76 * activeBases.length));
        labNowVitality.setText(String.valueOf(nowVitality));

        ActiveAward[] awardsz = allActive.getAwards();
        ActiveAward[] awards = new ActiveAward[4];
        if (awardsz != null && awardsz.length > 0) {
            for (int i = 0; i < 4; i++) {
                if (awardsz[i].getActive() > 0) {//新月卡
                    awards[i] = awardsz[i];
                }
            }
        }

        labVitality = new JLabel[awards.length];
        btnVitality = new ActivityBtn[awards.length];
        int num = 340 / 4;
        int sumReceive = TaskRoleData.SumReceive(2, 3);
        for (int i = 0; i < awards.length; i++) {
            labVitality[i] = new JLabel(String.valueOf(awards[i].getActive()));
            labVitality[i].setBounds(55 + num * (i + 1), 400, 45, 20);
            labVitality[i].setForeground(Color.black);
            labVitality[i].setHorizontalAlignment(SwingConstants.CENTER);
            labVitality[i].setFont(UIUtils.TEXT_MSG);
            labVitality[i].setOpaque(false);
            this.add(labVitality[i]);
            String s = awards[i].getJvValue().split("&")[1].split("\\$")[0];
            btnVitality[i] = new ActivityBtn("inkImg/button/B308.png", 1, 1, i, this, s);
            btnVitality[i].setName(awards[i].getValue());
            if (nowVitality >= awards[i].getActive()) {
                int c = (sumReceive >> i) & 0x01;
                if (c == 1) {
                    btnVitality[i].setBtn(-1);
                    btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B310.png", -1, -1));
                }
            } else {
                btnVitality[i].setBtn(-1);
                btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B309.png", -1, -1));
            }
            btnVitality[i].setBounds(55 + num * (i + 1), 350, 50, 50);
            this.add(btnVitality[i]);
        }
//        } else {
//            AllActive allActive = UserMessUntil.getAllActive();
//            if (allActive == null) {
//                return;
//            }
//            nowVitality = 0;
//            mapActivityModelPanel = new HashMap<Integer, ActivityModelJpanel>();
//            ActiveBase[] activeBases = allActive.getBases();
//            for (int i = 0; i < activeBases.length; i++) {
//                ActiveBase activeBase = activeBases[i];
//                int sumReceive = TaskRoleData.SumReceive(activeBase.getSid(), 2);
//                ActivityModelJpanel activityModelJpanel = new ActivityModelJpanel();
//                activityModelJpanel.setBounds(0, 0 + i * 61, 371, 61);
//                activityModelJpanel.showActiveBase(activeBase, sumReceive);
//                if (activeBase.getNum() < sumReceive) {
//                    sumReceive = activeBase.getNum();
//                }
//                nowVitality += (sumReceive * activeBase.getValue());
//                activityModelJpanel.addMouseListener(new PaneListMouse(activityModelJpanel));
//                JlistActivityModelPanel.add(activityModelJpanel);
//                mapActivityModelPanel.put(activeBase.getSid(), activityModelJpanel);
//            }
//            JlistActivityModelPanel.setPreferredSize(new Dimension(371, 61 * activeBases.length));
//            labNowVitality.setText(String.valueOf(nowVitality));
//
//            ActiveAward[] awardsz = allActive.getAwards();
//            ActiveAward[] awards = new ActiveAward[6];
//            if (awardsz != null && awardsz.length > 0) {
//                for (int i = 0; i < awardsz.length; i++) {
//                    if (awardsz[i].getActive() > 0) {
//                        awards[i] = awardsz[i];
//                    }
//                }
//            }
//            labVitality = new JLabel[awards.length];
//            btnVitality = new ActivityBtn[awards.length];
//            int num = 340 / awards.length;
//            int sumReceive = TaskRoleData.SumReceive(2, 3);
//            for (int i = 0; i < awards.length; i++) {
//                labVitality[i] = new JLabel(String.valueOf(awards[i].getActive()));
//                labVitality[i].setBounds(90 + num * (i + 1), 443, 45, 20);
//                labVitality[i].setForeground(Color.white);
//                labVitality[i].setHorizontalAlignment(SwingConstants.CENTER);
//                labVitality[i].setFont(UIUtils.TEXT_MSG);
//                labVitality[i].setOpaque(false);
//                this.add(labVitality[i]);
//                btnVitality[i] = new ActivityBtn("inkImg/button/B308.png", 1, 1, i, this);
//                btnVitality[i].setName(awards[i].getValue());
//                if (nowVitality >= awards[i].getActive()) {
//                    int c = (sumReceive >> i) & 0x01;
//                    if (c == 1) {
//                        btnVitality[i].setBtn(-1);
//                        btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B310.png", -1, -1));
//                    }
//                } else {
//                    btnVitality[i].setBtn(-1);
//                    btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B309.png", -1, -1));
//                }
//                btnVitality[i].setBounds(90 + num * (i + 1), 412, 45, 36);
//                this.add(btnVitality[i]);
//            }
//        }

    }

    /**
     * 全体刷新面板
     */
    public void refreshView() {
        AllActive allActive = UserMessUntil.getAllActive();
        ActiveBase[] activeBases = allActive.getBases();
        nowVitality = 0;
        for (int i = 0; i < activeBases.length; i++) {
            ActiveBase activeBase = activeBases[i];
            int sumReceive = TaskRoleData.SumReceive(activeBase.getSid(), 2);
            if (activeBase.getSid() == 101) {
                CJTYPE[0] = sumReceive;
            }
            if (activeBase.getSid() == 102) {
                CJTYPE[1] = sumReceive;
            }
            if (activeBase.getSid() == 103) {
                CJTYPE[2] = sumReceive;
            }
            if (activeBase.getSid() == 104) {
                CJTYPE[3] = sumReceive;
            }
            if (activeBase.getSid() == 105) {
                CJTYPE[4] = sumReceive;
            }
            if (activeBase.getSid() == 106) {
                CJTYPE[5] = sumReceive;
            }
            ActivityModelJpanel activityModelJpanel = mapActivityModelPanel.get(activeBase.getSid());
            activityModelJpanel.showActiveBase(activeBase, sumReceive);
            if (activeBase.getNum() < sumReceive) {
                sumReceive = activeBase.getNum();
            }
            nowVitality += (sumReceive * activeBase.getValue());
        }
        labNowVitality.setText(String.valueOf(nowVitality));
        ActiveAward[] awardsz = allActive.getAwards();
        ActiveAward[] awards = new ActiveAward[4];//修改活跃礼包数量
        if (awardsz != null && awardsz.length > 0) {
            for (int i = 0; i < awardsz.length; i++) {
                if (awardsz[i].getActive() > 0) {
                    awards[i] = awardsz[i];
                }
            }
        }
        int sumReceive = TaskRoleData.SumReceive(2, 3);
        for (int i = 0; i < awards.length; i++) {
            ActiveAward activeAward = awards[i];
            if (activeAward.getActive() > nowVitality) {
                btnVitality[i].setBtn(-1);
                btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B309.png", -1, -1));
            } else {
                int c = (sumReceive >> i) & 0x01;
                if (c == 1) {
                    btnVitality[i].setBtn(-1);
                    btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B310.png", -1, -1));
                } else {
                    btnVitality[i].setBtn(1);
                    try {
                        btnVitality[i].setIcons(CutButtonImage.cuts("inkImg/button/B308.png"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public final int yi = 100;//第一次抽奖次数
    public final int ei = 200;//第二次抽奖次数

    public void updateButtonState(int cjType, String rccjType, int type) {
        if ((RoleData.getRoleData().getLoginResult().getDayDrawtype(rccjType) < 2) &&
                (cjType >= yi && RoleData.getRoleData().getLoginResult().getDayDrawtype(rccjType) == 0) ||
                (cjType >= ei && RoleData.getRoleData().getLoginResult().getDayDrawtype(rccjType) == 1)) {
            String sendmes = Agreement.getAgreement().roledaydrawAgreement("获取奖池=" + CJTYPENUM);
            SendMessageUntil.toServer(sendmes);
            iscj[type - 101] = rccjType + "=1";
            cjBtn.setVisible(true);
        } else {
            cjBtn.setVisible(false);
        }
    }

    /**
     * 局部活动任务刷新
     */
    public void partRefreshView(int sid) {
        if (sid == 2) {
            int sum = TaskRoleData.SumReceive(2, 3);
            for (int i = 0; i < labVitality.length; i++) {
                int parseInt = Integer.parseInt(labVitality[i].getText());
                if (parseInt > nowVitality) {
                    btnVitality[i].setBtn(-1);
                    btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B309.png", -1, -1));
                    break;
                } else {
                    int c = (sum >> i) & 0x01;
                    if (c == 1) {
                        btnVitality[i].setBtn(-1);
                        btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B310.png", -1, -1));
                    } else {
                        btnVitality[i].setBtn(1);
                        try {
                            btnVitality[i].setIcons(CutButtonImage.cuts("inkImg/button/B308.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            ActivityModelJpanel activityModelJpanel = mapActivityModelPanel.get(sid);
            if (activityModelJpanel == null) {
                return;
            }
            ActiveBase activeBase = activityModelJpanel.getActiveBase();
            int sumReceive = TaskRoleData.SumReceive(sid, 2);
            activityModelJpanel.showActiveBase(activeBase, sumReceive);
            if (activeBase.getNum() < sumReceive) {
                sumReceive = activeBase.getNum();
            } else {
                nowVitality += activeBase.getValue();
            }
            int sum = TaskRoleData.SumReceive(2, 3);
            for (int i = 0; i < labVitality.length; i++) {
                int parseInt = Integer.parseInt(labVitality[i].getText());
                if (parseInt > nowVitality) {
                    btnVitality[i].setBtn(-1);
                    btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B309.png", -1, -1));
                    break;
                } else {
                    int c = (sum >> i) & 0x01;
                    if (c == 1) {
                        btnVitality[i].setBtn(-1);
                        btnVitality[i].setIcon(CutButtonImage.getImage("inkImg/button/B310.png", -1, -1));
                    } else {
                        btnVitality[i].setBtn(1);
                        try {
                            btnVitality[i].setIcons(CutButtonImage.cuts("inkImg/button/B308.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            labNowVitality.setText(String.valueOf(nowVitality));
        }

    }

    private ImageIcon icon;
    private int i = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if(MyIsif.getStyle().equals("水墨")) {
        if (icon == null) {
            icon = new ImageIcon("inkImg/background/activeBack.png");
        }
        g.drawImage(icon.getImage(), 0, 0, 711, 444, this);
        g.setColor(Color.white);
        paneList.setBounds(73, 81, 390, 220);
        paneDetails.setVisible(false);
        g.setColor(UIUtils.COLOR_CBG2);
        g.setFont(UIUtils.TEXT_FONT6);
        int length = (nowVitality + "").length();
        if (length == 1)
            g.drawString(nowVitality + "", 102, 384);
        else if (length == 2)
            g.drawString(nowVitality + "", 97, 384);
        else if (length == 3)
            g.drawString(nowVitality + "", 91, 384);
        else if (length == 4)
            g.drawString(nowVitality + "", 91, 384);
        g.setFont(UIUtils.TEXT_FONT);
        if (WBXS.equals("小鬼")) {
            g.drawString(CJTYPE[0] + "", 553, 347);
            g.drawString(RoleData.getRoleData().getLoginResult().getDayDrawtype(WBXS) + "/2次", 547, 373);
        } else if (WBXS.equals("天庭")) {
            g.drawString(CJTYPE[1] + "", 553, 347);
            g.drawString(RoleData.getRoleData().getLoginResult().getDayDrawtype(WBXS) + "/2次", 547, 373);
        } else if (WBXS.equals("鬼王")) {
            g.drawString(CJTYPE[2] + "", 553, 347);
            g.drawString(RoleData.getRoleData().getLoginResult().getDayDrawtype(WBXS) + "/2次", 547, 373);
        } else if (WBXS.equals("修罗")) {
            g.drawString(CJTYPE[3] + "", 553, 347);
            g.drawString(RoleData.getRoleData().getLoginResult().getDayDrawtype(WBXS) + "/2次", 547, 373);
        } else if (WBXS.equals("域外")) {
            g.drawString(CJTYPE[4] + "", 553, 347);
            g.drawString(RoleData.getRoleData().getLoginResult().getDayDrawtype(WBXS) + "/2次", 547, 373);
        }
//        if (goodsIds != null && goodsIds.size() > 0) {
////            if (!start) {
////                String s = goodsIds.get(0);
////                Goodstable goodstable = UserMessUntil.getGoodsBean().getAllGoodsMap().get(new BigDecimal(s));
////                ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 50, 50);
////                g.drawImage(image.getImage(), 630, 350, null);
////            } else
//            if (System.currentTimeMillis() % 20 == 0 && start) {
//
//                for (; i < goodsIds.size(); i++) {
//                    String s = goodsIds.get(i);
//                    Goodstable goodstable = UserMessUntil.getGoodsBean().getAllGoodsMap().get(new BigDecimal(s));
//                    goodImg = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 50, 50);
//                    if (goodsIds.size() - 1 == i) {
//                        start = false;
//                        goodImg = null;
//                        i = 0;
//                        cj();
//                        break;
//                    }
//                    i += 1;
//                    break;
//                }
//            }
//
//            if (start && goodImg != null) {
//                g.drawImage(goodImg.getImage(), 635, 350, null);
//            }
//        }
//        }else {
//        	if (icon == null) {
//        		icon = new ImageIcon("img/xy2uiimg/S176.png");
//        	}
//        	g.drawImage(icon.getImage(), 0, 0, 653, 479, this);
//        	g.setColor(Color.white);
//        }
    }

    public void cj() {
        List<String> ids = goodsIds;
        String goodis = "";
        for (String id : ids) {
            goodis += id + "|";
        }
        String sendmes = Agreement.getAgreement().roledaydrawAgreement("日常抽奖=" + ActivityJpanel.CJTYPENUM + "=" + goodis);
        SendMessageUntil.toServer(sendmes);
        cjBtn.setVisible(false);
    }

    public JScrollPane getPaneList() {
//        if (MyIsif.getStyle().equals("水墨")) {
        if (paneList == null) {
            paneList = new JScrollPane(getJlistActivityModelPanel());
            paneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            paneList.getVerticalScrollBar().setUI(new ActiveSrcollPanelUI());
            paneList.getVerticalScrollBar().setUnitIncrement(20);
            paneList.getViewport().setOpaque(false);
            paneList.setOpaque(false);
            paneList.setBounds(53, 81, 390, 310);
            paneList.setBorder(BorderFactory.createEmptyBorder());
            paneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(paneList);
        }
//        } else {
//            if (paneList == null) {
//                paneList = new JScrollPane(getJlistActivityModelPanel());
//                paneList.setVerticalScrollBarPolicy(22);
//                paneList.getVerticalScrollBar().setUI(new ScrollUI());
//                paneList.getVerticalScrollBar().setUnitIncrement(20);
//                paneList.getViewport().setOpaque(false);
//                paneList.setOpaque(false);
//                paneList.setBounds(49, 81, 386, 310);
//                paneList.setBorder(BorderFactory.createEmptyBorder());
//                paneList.setHorizontalScrollBarPolicy(31);
//                this.add(paneList);
//            }
//        }
        return paneList;
    }

    public void setPaneList(JScrollPane paneList) {
        this.paneList = paneList;
    }

    public JScrollPane getPaneDetails() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (paneDetails == null) {
                paneDetails = new JScrollPane(getRichDetails());
                paneDetails.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                paneDetails.getVerticalScrollBar().setUI(new SrcollPanelUI());
                paneDetails.getVerticalScrollBar().setUnitIncrement(20);
                paneDetails.getViewport().setOpaque(false);
                paneDetails.setOpaque(false);
                paneDetails.setBounds(442, 81, 181, 310);
                paneDetails.setBorder(BorderFactory.createEmptyBorder());
                paneDetails.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                this.add(paneDetails);
            }
        } else {
            if (paneDetails == null) {
                paneDetails = new JScrollPane(getRichDetails());
                paneDetails.setVerticalScrollBarPolicy(22);
                paneDetails.getVerticalScrollBar().setUI(new ScrollUI());
                paneDetails.getVerticalScrollBar().setUnitIncrement(20);
                paneDetails.getViewport().setOpaque(false);
                paneDetails.setOpaque(false);
                paneDetails.setBounds(442, 81, 181, 310);
                paneDetails.setBorder(BorderFactory.createEmptyBorder());
                paneDetails.setHorizontalScrollBarPolicy(31);
                this.add(paneDetails);
            }
        }
        return paneDetails;
    }

    public void setPaneDetails(JScrollPane paneDetails) {
        this.paneDetails = paneDetails;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public JLabel[] getLabVitality() {
        return labVitality;
    }

    public void setLabVitality(JLabel[] labVitality) {
        this.labVitality = labVitality;
    }

    public ActivityBtn[] getBtnVitality() {
        return btnVitality;
    }

    public void setBtnVitality(ActivityBtn[] btnVitality) {
        this.btnVitality = btnVitality;
    }

    public RichLabel getRichDetails() {
        if (richDetails == null) {
            richDetails = new RichLabel("", UIUtils.TEXT_FONT1);
        }
        return richDetails;
    }

    public void setRichDetails(RichLabel richDetails) {
        this.richDetails = richDetails;
    }

    public JList<ActivityModelJpanel> getJlistActivityModelPanel() {
        if (JlistActivityModelPanel == null) {
            JlistActivityModelPanel = new JList<ActivityModelJpanel>();
            JlistActivityModelPanel.setSelectionBackground(new Color(122, 117, 112));
            JlistActivityModelPanel.setSelectionForeground(Color.white);
            JlistActivityModelPanel.setForeground(Color.white);
            JlistActivityModelPanel.setFont(UIUtils.TEXT_HY16);
            JlistActivityModelPanel.removeAll();
            JlistActivityModelPanel.setBackground(UIUtils.Color_BACK); // 设置列表框为透明背景
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    return this;
                }
            };
            JlistActivityModelPanel.setCellRenderer(cellRenderer);
            JlistActivityModelPanel.setOpaque(false);
        }
        return JlistActivityModelPanel;

    }

    public void setJlistActivityModelPanel(JList<ActivityModelJpanel> jlistActivityModelPanel) {
        JlistActivityModelPanel = jlistActivityModelPanel;
    }

    public JLabel getLabNowVitality() {
        if (labNowVitality == null) {
            labNowVitality = new JLabel();
            labNowVitality.setHorizontalAlignment(SwingConstants.CENTER);
            labNowVitality.setForeground(Color.white);
            labNowVitality.setFont(UIUtils.TEXT_FONT1);
            if (MyIsif.getStyle().equals("水墨"))
                labNowVitality.setBounds(524, 438, 68, 16);
            else labNowVitality.setBounds(524, 438, 68, 16);
            this.add(labNowVitality);
        }
        return labNowVitality;
    }

    public void setLabNowVitality(JLabel labNowVitality) {
        this.labNowVitality = labNowVitality;
    }

    public Map<Integer, ActivityModelJpanel> getMapActivityModelPanel() {
        return mapActivityModelPanel;
    }

    public void setMapActivityModelPanel(Map<Integer, ActivityModelJpanel> mapActivityModelPanel) {
        this.mapActivityModelPanel = mapActivityModelPanel;
    }

    public String[] getIscj() {
        return iscj;
    }

    public void setIscj(String[] iscj) {
        this.iscj = iscj;
    }

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }
}
