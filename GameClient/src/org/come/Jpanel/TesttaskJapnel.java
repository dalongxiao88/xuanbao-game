package org.come.Jpanel;

import org.come.until.GsonUtil;
import org.come.model.Gamemap;
import java.math.BigDecimal;
import org.come.bean.Coordinates;
import com.tool.ModerateTask.TaskProgress;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.until.Util;
import org.come.Frame.ZhuFrame;
import com.tool.ModerateTask.Task;
import com.tool.ModerateTask.TaskSet;
import com.tool.ModerateTask.AllTask;
import javax.swing.tree.DefaultTreeModel;
import com.tool.ModerateTask.Hero;
import org.come.until.UserMessUntil;
import java.awt.Graphics;
import org.come.until.ScrollUI;
import org.come.mouslisten.Mouselistener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import com.tool.ModerateTask.TaskData;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import com.tool.tcpimg.InputBean;
import com.tool.btn.JpanelOnJalbelBtn;
import com.tool.tcpimg.RichLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TesttaskJapnel extends JPanel
{
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    private int taskId;
    private RichLabel label;
    private JpanelOnJalbelBtn btnQX;
    private JpanelOnJalbelBtn btnZZ;
    private InputBean inputBean;
    private int caozuo;
    private ImageIcon icon;
    private List<Integer> setList;
    
    public TesttaskJapnel() {
        this.setList = new ArrayList<>();
        this.caozuo = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(579, 409));
            this.setBackground(Color.BLACK);
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 3);
            offBtn.setBounds(542, 10, 25, 25);
            this.add(offBtn);
            this.top = new DefaultMutableTreeNode();
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    if (node.getUserObject() instanceof TaskData) {
                        TaskData taskData = (TaskData)node.getUserObject();
                        this.setText(taskData.getTaskName());
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            cellRenderer.setOpenIcon(new ImageIcon("img/xy2uiimg/expand.png"));
            cellRenderer.setClosedIcon(new ImageIcon("img/xy2uiimg/not_expanded.png"));
            cellRenderer.setFont(new Font("宋体", 0, 14));
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)TesttaskJapnel.this.jTree.getLastSelectedPathComponent();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = TesttaskJapnel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (TesttaskJapnel.this.jTree.isExpanded(path)) {
                                TesttaskJapnel.this.jTree.collapsePath(path);
                            }
                            else {
                                TesttaskJapnel.this.jTree.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (node != null && node.isLeaf()) {
                        TesttaskJapnel.this.taskMsg((TaskData)node.getUserObject());
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(190);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(50, 78, 215, 290);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.label = new RichLabel("", UIUtils.TEXT_FONT1, 287)).addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (TesttaskJapnel.this.inputBean != null) {
                        Mouselistener.DJInputBean(TesttaskJapnel.this.inputBean);
                        TesttaskJapnel.this.inputBean = null;
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TesttaskJapnel.this.inputBean = TesttaskJapnel.this.label.isMonitor(e.getX(), e.getY());
                    if (TesttaskJapnel.this.inputBean != null) {
                        TesttaskJapnel.this.inputBean.setM(true);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            (this.jScrollPane2 = new JScrollPane(this.label)).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(190);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(275, 78, 285, 290);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            (this.btnQX = new JpanelOnJalbelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, 10, "取消任务", this)).setBounds(440, 30, 99, 24);
            this.add(this.btnQX);
            (this.btnZZ = new JpanelOnJalbelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, 112, "任务追踪", this)).setBounds(320, 30, 99, 24);
            this.add(this.btnZZ);
        }
        else {
            this.setPreferredSize(new Dimension(556, 415));
            this.setBackground(Color.BLACK);
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/s74.png", 1, 3);
            offBtn.setBounds(531, 0, 25, 25);
            this.add(offBtn);
            this.top = new DefaultMutableTreeNode();
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    if (node.getUserObject() instanceof TaskData) {
                        TaskData taskData = (TaskData)node.getUserObject();
                        this.setText(taskData.getTaskName());
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            cellRenderer.setOpenIcon(new ImageIcon("img/xy2uiimg/expand.png"));
            cellRenderer.setClosedIcon(new ImageIcon("img/xy2uiimg/not_expanded.png"));
            cellRenderer.setFont(new Font("宋体", 0, 14));
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)TesttaskJapnel.this.jTree.getLastSelectedPathComponent();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = TesttaskJapnel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (TesttaskJapnel.this.jTree.isExpanded(path)) {
                                TesttaskJapnel.this.jTree.collapsePath(path);
                            }
                            else {
                                TesttaskJapnel.this.jTree.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (node != null && node.isLeaf()) {
                        TesttaskJapnel.this.taskMsg((TaskData)node.getUserObject());
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(190);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(20, 59, 212, 290);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.label = new RichLabel("", UIUtils.TEXT_FONT1, 287)).addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (TesttaskJapnel.this.inputBean != null) {
                        Mouselistener.DJInputBean(TesttaskJapnel.this.inputBean);
                        TesttaskJapnel.this.inputBean = null;
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TesttaskJapnel.this.inputBean = TesttaskJapnel.this.label.isMonitor(e.getX(), e.getY());
                    if (TesttaskJapnel.this.inputBean != null) {
                        TesttaskJapnel.this.inputBean.setM(true);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            (this.jScrollPane2 = new JScrollPane(this.label)).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(190);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(247, 59, 280, 290);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            (this.btnQX = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 10, "取消任务", this)).setBounds(440, 360, 80, 30);
            this.add(this.btnQX);
            (this.btnZZ = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG2, 112, "任务追踪", this)).setBounds(340, 360, 80, 30);
            this.add(this.btnZZ);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B224.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 579, 409, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/rw1.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 556, 415, this);
        }
    }
    
    public void showTaskMethod() {
        this.top.removeAllChildren();
        this.setList.clear();
        AllTask allTask = UserMessUntil.getAllTask();
        Hero.getHero().getSetId(this.setList);
        allTask.getSetId(this.setList);
        for (int i = 0; i < allTask.getTypeList().size(); ++i) {
            String taskType = (String)allTask.getTypeList().get(i);
            DefaultMutableTreeNode node = null;
            for (int j = this.setList.size() - 1; j >= 0; --j) {
                TaskSet taskSet = UserMessUntil.getTaskSet((int)this.setList.get(j));
                if (taskSet.getTaskType().equals(taskType)) {
                    this.setList.remove(j);
                    Task task = Hero.getHero().getTaskSet(taskSet.getTaskSetID());
                    if (task != null) {
                        if (node == null) {
                            node = new DefaultMutableTreeNode(taskType);
                        }
                        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(task.getTaskData());
                        node.add(treeNode);
                    }
                }
            }
            if (node != null) {
                this.top.add(node);
            }
        }
        DefaultTreeModel treeModel = (DefaultTreeModel)this.jTree.getModel();
        treeModel.reload();
    }
    
    public void taskMsg(TaskData taskData) {
        if (this.taskId == taskData.getTaskID()) {
            return;
        }
        this.taskShow(Hero.getHero().getTaskId(taskData.getTaskID()), taskData);
    }
    
    public void removeTask() {
        Task task = Hero.getHero().getTaskId(this.taskId);
        if (task == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("选择要取消的任务");
            return;
        }
//        if (Util.getTime() < TaskMixDeal.time + 1000L) {
//            ZhuFrame.getZhuJpanel().addPrompt2("你需要等待1分钟才能再一次取消任务");
//            return;
//        }
        TaskMixDeal.time = Util.getTime();
        String mes = Agreement.getAgreement().TaskNAgreement("E" + task.getTaskId());
        SendMessageUntil.toServer(mes);
    }
    
    public void taskShow(Task task, TaskData taskData) {
        if (taskData == null) {
            this.taskId = 0;
            this.label.setTextSize("", 287);
            return;
        }
        this.taskId = taskData.getTaskID();
        if (task != null) {
            StringBuffer buffer = new StringBuffer();
            if (taskData.getTaskText() != null) {
                buffer.append("#Y任务目的:#r#W");
                String text = taskData.getTaskText();
                int value1 = -1;
                int value2 = -1;
                int size = 0;
                do {
                    value1 = text.indexOf("{");
                    if (value1 != -1) {
                        value2 = text.indexOf("}");
                    }
                    if (value1 == -1 || value2 == -1) {
                        buffer.append(text);
                    }
                    else {
                        buffer.append(text.substring(0, value1));
                        String type = text.substring(value1 + 1, value2);
                        if (type.startsWith("目标")) {
                            if (size < task.getProgress().size()) {
                                TaskProgress progress = (TaskProgress)task.getProgress().get(size);
                                if (progress.getType() == 0 || progress.getType() == 1 || progress.getType() == 2 || progress.getType() == 3) {
                                    if (progress.getMap() == 0) {
                                        buffer.append(progress.getDName());
                                    }
                                    else {
                                        Coordinates coordinates = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getDId()), progress.getDName(), "G", coordinates);
                                        if (progress.getType() == 1) {
                                            buffer.append("#G");
                                            buffer.append(((Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(progress.getMap() + "")).getMapname());
                                            buffer.append("#W击杀");
                                        }
                                        buffer.append("#V");
                                        buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                                        buffer.append("#L");
                                        buffer.append("(");
                                        buffer.append(progress.getX() / 20);
                                        buffer.append(",");
                                        buffer.append(progress.getY() / 20);
                                        buffer.append(")");
                                    }
                                }
                                else if (progress.getType() == 4) {
                                    buffer.append("带着");
                                    buffer.append(progress.getMax());
                                    buffer.append("个");
                                    buffer.append(progress.getDName());
                                    buffer.append("探望");
                                    if (progress.getMap() == 0) {
                                        buffer.append(progress.getGName());
                                    }
                                    else {
                                        Coordinates coordinates = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getGId()), progress.getGName(), "G", coordinates);
                                        buffer.append("#V");
                                        buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                                        buffer.append("#L");
                                        buffer.append("(");
                                        buffer.append(progress.getX() / 20);
                                        buffer.append(",");
                                        buffer.append(progress.getY() / 20);
                                        buffer.append(")");
                                    }
                                }
                                else if (progress.getType() == 5) {
                                    buffer.append("护送");
                                    buffer.append(progress.getDName());
                                    buffer.append("到");
                                    if (progress.getMap() == 0) {
                                        buffer.append(progress.getGName());
                                    }
                                    else {
                                        Coordinates coordinates = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getGId()), progress.getGName(), "G", coordinates);
                                        buffer.append("#V");
                                        buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                                        buffer.append("#L");
                                        buffer.append("(");
                                        buffer.append(progress.getX() / 20);
                                        buffer.append(",");
                                        buffer.append(progress.getY() / 20);
                                        buffer.append(")");
                                    }
                                }
                            }
                            ++size;
                        }
                        else if (type.startsWith("位置")) {
                            String[] vs = type.split("-");
                            Coordinates coordinates = new Coordinates(Integer.parseInt(vs[1]), Integer.parseInt(vs[2]), Integer.parseInt(vs[3]));
                            InputBean inputBean = new InputBean(null, 10, null, vs[1].substring(2), "G", coordinates);
                            buffer.append("#V");
                            buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                            buffer.append("#L");
                            buffer.append("(");
                            buffer.append(coordinates.getX() / 20);
                            buffer.append(",");
                            buffer.append(coordinates.getY() / 20);
                            buffer.append(")");
                        }
                        text = text.substring(value2 + 1);
                    }
                } while (value1 != -1 && value2 != -1);
            }
            if (task.getProgress().size() != 0) {
                if (buffer.length() != 0) {
                    buffer.append("#r #r");
                }
                buffer.append("#Y任务状态:");
                for (int i = 0; i < task.getProgress().size(); ++i) {
                    TaskProgress progress2 = (TaskProgress)task.getProgress().get(i);
                    buffer.append("#r#W");
                    buffer.append(progress2.getDName());
                    buffer.append(":#G");
                    buffer.append(progress2.getSum());
                    buffer.append("/");
                    buffer.append(progress2.getMax());
                }
            }
            TaskSet taskSet = UserMessUntil.getTaskSet(taskData.getTaskSetID());
            if (taskSet.getTaskMsg() != null) {
                if (buffer.length() != 0) {
                    buffer.append("#r #r");
                }
                buffer.append("#Y任务提示:#r#W");
                buffer.append(taskSet.getTaskMsg());
            }
            this.label.setTextSize(buffer.toString(), 287);
        }
    }
    
    public int getTaskId() {
        return this.taskId;
    }
    
    public JpanelOnJalbelBtn getBtnZZ() {
        return this.btnZZ;
    }
    
    public void setBtnZZ(JpanelOnJalbelBtn btnZZ) {
        this.btnZZ = btnZZ;
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
}
