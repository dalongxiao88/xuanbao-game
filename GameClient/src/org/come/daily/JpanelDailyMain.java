package org.come.daily;

import org.come.mouslisten.DailyMainMouseListener;
import com.tool.tcpimg.UIUtils;
import org.come.until.SrcollPanelUI;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.Graphics;
import com.tool.ModerateTask.TaskData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import org.come.until.UserMessUntil;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.List;
import org.come.model.EventModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JPanel;

public class JpanelDailyMain extends JPanel
{
    private BtnDailyMain menuBtnEverDay;
    private BtnDailyMain menuBtnTime;
    private BtnDailyMain menuBtnChallenge;
    private BtnDailyMain menuBtnWeekend;
    private BtnDailyMain menuBtnSurprise;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    private JScrollPane activityScp;
    private JScrollPane introductionScp;
    private JLabel[] firstTeams;
    private JLabel[] firstAwards;
    private BtnDailyMain receiveBtn;
    private BtnDailyMain shoutBtn;
    private BtnDailyMain transBtn;
    private int nowEventType;
    private EventModel eventModel;
    private JpanelDailytask dailytask;
    private List<JpanelDailytask> dailytasksList;
    private ImageIcon iconBack;
    
    public JpanelDailyMain() {
        this.nowEventType = -1;
        this.dailytasksList = new ArrayList<>();
        this.setPreferredSize(new Dimension(683, 475));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 90);
        offBtn.setBounds(646, 10, 25, 25);
        this.add(offBtn);
        this.getActivityScp();
        this.getFirstTeams();
        this.getFirstAwards();
        this.getTransBtn();
        this.getShoutBtn();
        this.getReceiveBtn();
        this.getIntroductionScp();
        this.getMenuBtnEverDay();
        this.getMenuBtnTime();
        this.getMenuBtnChallenge();
        this.getMenuBtnWeekend();
        this.getMenuBtnSurprise();
    }
    
    public void changeDailyMain(JpanelDailytask dailytask) {
        if (this.dailytask != null) {
            this.dailytask.setBorder(BorderFactory.createEmptyBorder());
        }
        (this.dailytask = dailytask).setBorder(BorderFactory.createLineBorder(Color.yellow));
    }
    
    public void getEventMap(int type) {
        if (type == -1) {
            if (this.nowEventType == -1) {
                type = 1;
            }
            else {
                return;
            }
        }
        this.nowEventType = type;
        List<List<EventModel>> allEventMap = this.getAllEventMap(type);
        this.top.removeAllChildren();
        int listSize = 0;
        int size = 0;
        for (int j = 0; j < allEventMap.size(); ++j) {
            List<EventModel> list = (List<EventModel>)allEventMap.get(j);
            DefaultMutableTreeNode taskName = new DefaultMutableTreeNode(((EventModel)list.get(0)).getTaskName());
            this.top.add(taskName);
            listSize += list.size();
            for (int i = 0; i < list.size(); ++i) {
                if (this.dailytasksList.size() < listSize) {
                    JpanelDailytask jpanelDailytask = new JpanelDailytask((EventModel)list.get(i));
                    taskName.add(new DefaultMutableTreeNode(jpanelDailytask));
                    this.dailytasksList.add(jpanelDailytask);
                }
                else if (this.dailytasksList.size() >= listSize) {
                    ((JpanelDailytask)this.dailytasksList.get(size)).changeView((EventModel)list.get(i));
                    taskName.add(new DefaultMutableTreeNode(this.dailytasksList.get(size)));
                }
                ++size;
            }
        }
        DefaultTreeModel treeModel = (DefaultTreeModel)this.jTree.getModel();
        treeModel.reload();
    }
    
    public List<List<EventModel>> getAllEventMap(int type) {
        Map<Integer, EventModel> allEventModelMap = UserMessUntil.getAllEventMap().getAllEventModelMap();
        Map<String, List<EventModel>> eventModelMap = new HashMap<>();
        for (Integer num : allEventModelMap.keySet()) {
            EventModel eventModel = (EventModel)allEventModelMap.get(num);
            if (type != eventModel.getTaskBigType()) {
                continue;
            }
            else if (eventModelMap.get(eventModel.getTaskName()) == null) {
                ArrayList<EventModel> arrayList = new ArrayList<>();
                arrayList.add(eventModel);
                eventModelMap.put(eventModel.getTaskName(), arrayList);
            }
            else {
                ((List<EventModel>)eventModelMap.get(eventModel.getTaskName())).add(eventModel);
            }
        }
        for (String name : eventModelMap.keySet()) {
            List<EventModel> list = (List<EventModel>)eventModelMap.get(name);
            if (list.size() <= 1) {
                continue;
            }
            else {
                for (int i = 0; i < list.size() - 1; ++i) {
                    for (int j = i + 1; j < list.size(); ++j) {
                        if (((EventModel)list.get(i)).getgId() > ((EventModel)list.get(j)).getgId()) {
                            EventModel model = (EventModel)list.get(i);
                            list.set(i, list.get(j));
                            list.set(j, model);
                        }
                    }
                }
            }
        }
        List<List<EventModel>> lists = new ArrayList<>();
        for (String name2 : eventModelMap.keySet()) {
            List<EventModel> list2 = (List<EventModel>)eventModelMap.get(name2);
            lists.add(list2);
        }
        for (int k = 0; k < lists.size() - 1; ++k) {
            for (int l = k + 1; l < lists.size(); ++l) {
                if (((EventModel)((List<EventModel>)lists.get(k)).get(0)).getgId() > ((EventModel)((List<EventModel>)lists.get(l)).get(0)).getgId()) {
                    List<EventModel> model2 = (List<EventModel>)lists.get(k);
                    lists.set(k, lists.get(l));
                    lists.set(l, model2);
                }
            }
        }
        return lists;
    }
    
    public void changeMenuIcon(int type) {
        try {
            this.menuBtnEverDay.setIcons(CutButtonImage.cuts("inkImg/button/B" + ((type == 0) ? 220 : 219) + ".png"));
            this.menuBtnTime.setIcons(CutButtonImage.cuts("inkImg/button/B" + ((type == 1) ? 224 : 223) + ".png"));
            this.menuBtnChallenge.setIcons(CutButtonImage.cuts("inkImg/button/B" + ((type == 2) ? 222 : 221) + ".png"));
            this.menuBtnWeekend.setIcons(CutButtonImage.cuts("inkImg/button/B" + ((type == 3) ? 228 : 227) + ".png"));
            this.menuBtnSurprise.setIcons(CutButtonImage.cuts("inkImg/button/B" + ((type == 4) ? 226 : 225) + ".png"));
            this.getEventMap(type + 1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearView() {
    }
    
    public void sendRanking() {
        if (this.eventModel == null) {
            return;
        }
        for (int i = 0; i < this.firstTeams.length; ++i) {
            this.firstTeams[i].setText("");
        }
        String sendmes = Agreement.getFiveMsgAgreement("E" + this.eventModel.getgId());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void getIntroduction() {
        if (this.eventModel == null) {
            return;
        }
        TaskData taskData = UserMessUntil.getTaskData(this.eventModel.getTaskId());
        if (taskData == null) {
            return;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/S141.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 683, 475, this);
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public int getNowEventType() {
        return this.nowEventType;
    }
    
    public void setNowEventType(int nowEventType) {
        this.nowEventType = nowEventType;
    }
    
    public EventModel getEventModel() {
        return this.eventModel;
    }
    
    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    
    public BtnDailyMain getMenuBtnEverDay() {
        if (this.menuBtnEverDay == null) {
            (this.menuBtnEverDay = new BtnDailyMain("inkImg/button/B220.png", 1, 0, this)).setBounds(57, 23, 100, 35);
            this.add(this.menuBtnEverDay);
        }
        return this.menuBtnEverDay;
    }
    
    public void setMenuBtnEverDay(BtnDailyMain menuBtnEverDay) {
        this.menuBtnEverDay = menuBtnEverDay;
    }
    
    public BtnDailyMain getMenuBtnTime() {
        if (this.menuBtnTime == null) {
            (this.menuBtnTime = new BtnDailyMain("inkImg/button/B223.png", 1, 1, this)).setBounds(159, 23, 100, 35);
            this.add(this.menuBtnTime);
        }
        return this.menuBtnTime;
    }
    
    public void setMenuBtnTime(BtnDailyMain menuBtnTime) {
        this.menuBtnTime = menuBtnTime;
    }
    
    public BtnDailyMain getMenuBtnChallenge() {
        if (this.menuBtnChallenge == null) {
            (this.menuBtnChallenge = new BtnDailyMain("inkImg/button/B221.png", 1, 2, this)).setBounds(261, 23, 100, 35);
            this.add(this.menuBtnChallenge);
        }
        return this.menuBtnChallenge;
    }
    
    public void setMenuBtnChallenge(BtnDailyMain menuBtnChallenge) {
        this.menuBtnChallenge = menuBtnChallenge;
    }
    
    public BtnDailyMain getMenuBtnWeekend() {
        if (this.menuBtnWeekend == null) {
            (this.menuBtnWeekend = new BtnDailyMain("inkImg/button/B227.png", 1, 3, this)).setBounds(363, 23, 100, 35);
            this.add(this.menuBtnWeekend);
        }
        return this.menuBtnWeekend;
    }
    
    public void setMenuBtnWeekend(BtnDailyMain menuBtnWeekend) {
        this.menuBtnWeekend = menuBtnWeekend;
    }
    
    public BtnDailyMain getMenuBtnSurprise() {
        if (this.menuBtnSurprise == null) {
            (this.menuBtnSurprise = new BtnDailyMain("inkImg/button/B225.png", 1, 4, this)).setBounds(465, 23, 100, 35);
            this.add(this.menuBtnSurprise);
        }
        return this.menuBtnSurprise;
    }
    
    public void setMenuBtnSurprise(BtnDailyMain menuBtnSurprise) {
        this.menuBtnSurprise = menuBtnSurprise;
    }
    
    public List<JpanelDailytask> getDailytasksList() {
        return this.dailytasksList;
    }
    
    public void setDailytasksList(List<JpanelDailytask> dailytasksList) {
        this.dailytasksList = dailytasksList;
    }
    
    public JpanelDailytask getDailytask() {
        return this.dailytask;
    }
    
    public void setDailytask(JpanelDailytask dailytask) {
        this.dailytask = dailytask;
    }
    
    public JTree getjTree() {
        if (this.jTree == null) {
            (this.jTree = new JTree(this.getTop())).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getUserObject() instanceof JpanelDailytask) {
                        return (JpanelDailytask)node.getUserObject();
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            try {
                cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]);
                cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            cellRenderer.setFont(new Font("宋体", 0, 14));
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = JpanelDailyMain.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (JpanelDailyMain.this.jTree.isExpanded(path)) {
                                JpanelDailyMain.this.jTree.collapsePath(path);
                            }
                            else {
                                JpanelDailyMain.this.jTree.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (selectionNode == null) {
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf() && selectionNode.getUserObject() instanceof JpanelDailytask) {
                        JpanelDailytask dailytask = (JpanelDailytask)selectionNode.getUserObject();
                        JpanelDailyMain.this.changeDailyMain(dailytask);
                        JpanelDailyMain.this.eventModel = dailytask.getEventModel();
                        JpanelDailyMain.this.getIntroduction();
                        JpanelDailyMain.this.sendRanking();
                    }
                }
            });
        }
        return this.jTree;
    }
    
    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
    
    public DefaultMutableTreeNode getTop() {
        if (this.top == null) {
            this.top = new DefaultMutableTreeNode("");
        }
        return this.top;
    }
    
    public void setTop(DefaultMutableTreeNode top) {
        this.top = top;
    }
    
    public JScrollPane getActivityScp() {
        if (this.activityScp == null) {
            (this.activityScp = new JScrollPane(this.getjTree())).setVerticalScrollBarPolicy(22);
            this.activityScp.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.activityScp.getVerticalScrollBar().setUnitIncrement(50);
            this.activityScp.getViewport().setOpaque(false);
            this.activityScp.setOpaque(false);
            this.activityScp.setBounds(49, 96, 391, 323);
            this.activityScp.setBorder(BorderFactory.createEmptyBorder());
            this.activityScp.setHorizontalScrollBarPolicy(31);
            this.add(this.activityScp);
        }
        return this.activityScp;
    }
    
    public void setActivityScp(JScrollPane activityScp) {
        this.activityScp = activityScp;
    }
    
    public JLabel[] getFirstTeams() {
        if (this.firstTeams == null) {
            this.firstTeams = new JLabel[5];
            for (int i = 0; i < this.firstTeams.length; ++i) {
                (this.firstTeams[i] = new JLabel()).setBounds(452 + (i + 1) % 2 * 101, 282 + (i + 1) / 2 * 24, 84, 16);
                this.firstTeams[i].setForeground(UIUtils.COLOR_FIRSTTEAM);
                this.firstTeams[i].setFont(UIUtils.TEXT_HY16);
                this.firstTeams[i].setHorizontalAlignment(0);
                this.add(this.firstTeams[i]);
            }
        }
        return this.firstTeams;
    }
    
    public void setFirstTeams(JLabel[] firstTeams) {
        this.firstTeams = firstTeams;
    }
    
    public JLabel[] getFirstAwards() {
        if (this.firstAwards == null) {
            this.firstAwards = new JLabel[5];
            for (int i = 0; i < this.firstAwards.length; ++i) {
                (this.firstAwards[i] = new JLabel()).setBounds(495 + i * 30, 359, 26, 26);
                this.firstAwards[i].addMouseListener(new DailyMainMouseListener(null));
                this.add(this.firstAwards[i]);
            }
        }
        return this.firstAwards;
    }
    
    public void setFirstAwards(JLabel[] firstAwards) {
        this.firstAwards = firstAwards;
    }
    
    public BtnDailyMain getReceiveBtn() {
        if (this.receiveBtn == null) {
            (this.receiveBtn = new BtnDailyMain("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "领取", 11, this)).setBounds(579, 390, 60, 26);
            this.add(this.receiveBtn);
        }
        return this.receiveBtn;
    }
    
    public void setReceiveBtn(BtnDailyMain receiveBtn) {
        this.receiveBtn = receiveBtn;
    }
    
    public BtnDailyMain getShoutBtn() {
        if (this.shoutBtn == null) {
            (this.shoutBtn = new BtnDailyMain("inkImg/button/18.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "一键喊话", 12, this)).setBounds(49, 426, 100, 26);
            this.add(this.shoutBtn);
        }
        return this.shoutBtn;
    }
    
    public void setShoutBtn(BtnDailyMain shoutBtn) {
        this.shoutBtn = shoutBtn;
    }
    
    public BtnDailyMain getTransBtn() {
        if (this.transBtn == null) {
            (this.transBtn = new BtnDailyMain("inkImg/button/18.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "传送地点", 13, this)).setBounds(179, 426, 100, 26);
            this.add(this.transBtn);
        }
        return this.transBtn;
    }
    
    public void setTransBtn(BtnDailyMain transBtn) {
        this.transBtn = transBtn;
    }
    
    public JScrollPane getIntroductionScp() {
        if (this.introductionScp == null) {
            (this.introductionScp = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.introductionScp.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.introductionScp.getVerticalScrollBar().setUnitIncrement(20);
            this.introductionScp.getViewport().setOpaque(false);
            this.introductionScp.setOpaque(false);
            this.introductionScp.setBounds(445, 96, 215, 147);
            this.introductionScp.setBorder(BorderFactory.createEmptyBorder());
            this.introductionScp.setHorizontalScrollBarPolicy(31);
            this.add(this.introductionScp);
        }
        return this.introductionScp;
    }
    
    public void setIntroductionScp(JScrollPane introductionScp) {
        this.introductionScp = introductionScp;
    }
}
