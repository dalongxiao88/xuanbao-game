package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.AchievemJframe;
import org.come.Frame.ZhuFrame;
import org.come.model.Achievement;
import org.come.until.*;

import javax.swing.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 功绩千秋
 * 
 * @author admin
 *
 */
public class AchievemJpanel extends JPanel {

	private JTree jTreeAchievemList;
	
	private JScrollPane scpAchievemList, scpAchievemInitialValue;
	
	private DefaultMutableTreeNode nodeAchievemList; // 总节点
	
	private Achievement achievementga;
	
	private SpotAchievemShowJpanel[] achievementSt = new SpotAchievemShowJpanel[99];
	
	private JList<SpotAchievemShowJpanel> showfils = new JList<>();
	
	private JPanel achievementContainer; // 用于放置 achievementSt
	
	private JScrollPane achievementScrollPane; // 用于弹出滚动条

	private JLabel zgjbel,gzTextbel,gzbel,gzimgbel,gzimgbel1;
    public static List<Achievement> allAchievement = new ArrayList<>();
    public static List<Achievement> allCompleteAchievement = new ArrayList<>();
    public static Map<String, List<Achievement>> achievementMap = new HashMap<>();
    public static Map<String, List<Achievement>> AllachievementMap = new HashMap<>();

    public String SummaryText,SummaryCZText,SummarySJText,SummarySJrwText,SummaryRCrwText,SummaryJQrwText,SummaryGXText;

    public int SummaryTextWidth,SummaryCZTextWidth,SummarySJTextWidth,SummarySJrwTextWidth,SummaryRCrwTextWidth,SummaryJQrwTextWidth,SummaryGXTextWidth;
    public JLabel SummaryTextbel,SummaryCZTextbel,SummarySJTextbel,SummarySJrwTextbel,SummaryRCrwTextbel,SummaryJQrwTextbel,SummaryGXTextbel;
    private  FormsOnOffBtn gzBtn;
	//gzBtn = new FormsOnOffBtn("inkImg/hongmu/gj.png", 1, 60888);//官职
    //            gzBtn.setBounds(215, 97, 67, 42);
    //            gzBtn.setVisible(true);
    //            this.add(gzBtn);//大开关
	public AchievemJpanel() {

		this.setPreferredSize(new Dimension(659, 471));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);

		Font font = new Font("楷体", 1, 16);
		zgjbel = new JLabel();
		zgjbel.setFont(UIUtils.TEXT_FONT33);
        zgjbel.setBounds(480, 39, 300, 70);
		add(zgjbel);
        gzbel = new JLabel();
        gzbel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
        gzbel.setFont(UIUtils.TEXT_HY88);
//        gzbel.setText("当前官职：");
        gzbel.setBounds(220, 5, 300, 70);
        if(MyIsif.getStyle().equals("水墨")){
            zgjbel.setForeground(Color.black);
            gzbel.setForeground(Color.black);
            zgjbel.setBounds(549, 35, 300, 70);
            zgjbel.setForeground(Color.white);
            add(gzbel);
            gzTextbel = new JLabel();
            gzTextbel.setBounds(300, 5, 300, 70);
            gzTextbel.setForeground(Color.WHITE);
            gzTextbel.setFont(UIUtils.TEXT_FONT33);
            gzTextbel.setText("");
            add(gzTextbel);

            gzBtn = new FormsOnOffBtn("img/Client/gzl.png", 1, 60888);//水墨界面官职
            gzBtn.setBounds(128, 19, 75, 33);
            this.add(gzBtn);//大开关
        }else{
            zgjbel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
            gzbel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
            zgjbel.setBounds(550, 26, 300, 70);
            zgjbel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
            zgjbel.setForeground(Color.white);
            add(gzbel);
            gzTextbel = new JLabel();
            gzTextbel.setBounds(300, 5, 300, 70);
            gzTextbel.setForeground(Color.WHITE);
            gzTextbel.setFont(UIUtils.TEXT_FONT33);
            gzTextbel.setText("");
            add(gzTextbel);
            gzBtn = new FormsOnOffBtn("img/gemstone/gzl.png", 1, 60888);//红木界面官职
            gzBtn.setBounds(135, 10, 80, 33);
            this.add(gzBtn);//大开关
        }

        gzimgbel = new JLabel();
        gzimgbel.setIcon(new ImageIcon("img/xy2uiimg/S17.png"));
        gzimgbel.setBounds(300, 20, 300, 70);
		add(gzimgbel);
        gzimgbel1 = new JLabel();
        gzimgbel1.setIcon(new ImageIcon("inkImg/hongmu/S17.png"));
        gzimgbel1.setBounds(505, 20, 75, 70);//官职位置
		add(gzimgbel1);



        if(MyIsif.getStyle().equals("水墨")){
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 3072);
            offBtn.setBounds(659 - 25, 0, 25, 25);
            this.add(offBtn);
            gzimgbel.setVisible(false);
            gzimgbel1.setVisible(false);
            gzTextbel.setBounds(294, 35, 300, 70);
            gzbel.setBounds(220, 5, 300, 70);
        }else{
            // 关闭按钮事件
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 3072);
            offBtn.setBounds(659 - 25, 0, 25, 25);
            this.add(offBtn);
            gzimgbel.setVisible(true);
            gzimgbel1.setVisible(true);
            gzTextbel.setBounds(294, 25, 300, 70);
            gzbel.setBounds(220, 20, 300, 70);
        }
		getscpAchievemList();


		// 初始化用于显示的面板和滚动条
	    achievementContainer = new JPanel();
	    achievementContainer.setBackground(UIUtils.Color_BACK);
	    achievementContainer.setLayout(new BoxLayout(achievementContainer, BoxLayout.Y_AXIS));
	    achievementScrollPane = new JScrollPane(achievementContainer);
	    achievementScrollPane.getVerticalScrollBar().setUI((ScrollBarUI)new ScrollUI());
	    achievementScrollPane.getVerticalScrollBar().setUnitIncrement(20);
	    achievementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    achievementScrollPane.getViewport().setOpaque(false);
	    achievementScrollPane.setOpaque(false);
        if(MyIsif.getStyle().equals("水墨")){
            achievementScrollPane.setBounds(191, 84, 385, 190);
        }else{
            achievementScrollPane.setBounds(221, 110, 371, 190); // 设置滚动条的位置和大小
        }
	    achievementScrollPane.setBorder(BorderFactory.createEmptyBorder());
	    achievementScrollPane.setHorizontalScrollBarPolicy(31);
	    this.add(this.achievementScrollPane);


        if (MyIsif.getStyle().equals("水墨")) {
            int x=7,y=12;
            SummaryTextbel = new JLabel();
            SummaryTextbel.setForeground(Color.WHITE);
            SummaryTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummaryTextbel.setFont(UIUtils.TEXT_FONT33);
            SummaryTextbel.setBounds(293+x, 313+y, 289, 22);//总功绩进度
            add(SummaryTextbel);

            SummaryCZTextbel = new JLabel();
            SummaryCZTextbel.setForeground(Color.WHITE);
            SummaryCZTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummaryCZTextbel.setFont(UIUtils.TEXT_FONT33);
            SummaryCZTextbel.setBounds(293+x, 339+y, 109, 22);//人物成长
            add(SummaryCZTextbel);

            SummarySJTextbel = new JLabel();
            SummarySJTextbel.setForeground(Color.WHITE);
            SummarySJTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummarySJTextbel.setFont(UIUtils.TEXT_FONT33);
            SummarySJTextbel.setBounds(293+x, 365+y-6, 109, 22);//人物数据
            add(SummarySJTextbel);

            SummarySJrwTextbel = new JLabel();
            SummarySJrwTextbel.setForeground(Color.WHITE);
            SummarySJrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummarySJrwTextbel.setFont(UIUtils.TEXT_FONT33);
            SummarySJrwTextbel.setBounds(293+x, 391+y-7, 109, 20);//升级任务
            add(SummarySJrwTextbel);

            SummaryRCrwTextbel = new JLabel();
            SummaryRCrwTextbel.setForeground(Color.WHITE);
            SummaryRCrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummaryRCrwTextbel.setFont(UIUtils.TEXT_FONT33);
            SummaryRCrwTextbel.setBounds(473+x, 338+y, 109, 20);//日常任务
            add(SummaryRCrwTextbel);

            SummaryJQrwTextbel = new JLabel();
            SummaryJQrwTextbel.setForeground(Color.WHITE);
            SummaryJQrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummaryJQrwTextbel.setFont(UIUtils.TEXT_FONT33);
            SummaryJQrwTextbel.setBounds(473+x, 366+y-7, 109, 22);//剧情任务
            add(SummaryJQrwTextbel);

            SummaryGXTextbel = new JLabel();
            SummaryGXTextbel.setForeground(Color.WHITE);
            SummaryGXTextbel.setHorizontalAlignment(SwingConstants.CENTER);
            SummaryGXTextbel.setFont(UIUtils.TEXT_FONT33);
            SummaryGXTextbel.setBounds(473+x, 391+y-8, 109, 22);//人物关系
            add(SummaryGXTextbel);

        }else {//红木完成进度
            {int x=0,y=-10;
                SummaryTextbel = new JLabel();
                SummaryTextbel.setForeground(Color.WHITE);
                SummaryTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummaryTextbel.setFont(UIUtils.TEXT_FONT33);
                SummaryTextbel.setBounds(293+x, 313+y, 289, 20);
                add(SummaryTextbel);

                SummaryCZTextbel = new JLabel();
                SummaryCZTextbel.setForeground(Color.WHITE);
                SummaryCZTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummaryCZTextbel.setFont(UIUtils.TEXT_FONT33);
                SummaryCZTextbel.setBounds(293+x, 339+y-3, 109, 20);
                add(SummaryCZTextbel);

                SummarySJTextbel = new JLabel();
                SummarySJTextbel.setForeground(Color.WHITE);
                SummarySJTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummarySJTextbel.setFont(UIUtils.TEXT_FONT33);
                SummarySJTextbel.setBounds(293+x, 365+y-8, 109, 20);
                add(SummarySJTextbel);

                SummarySJrwTextbel = new JLabel();
                SummarySJrwTextbel.setForeground(Color.WHITE);
                SummarySJrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummarySJrwTextbel.setFont(UIUtils.TEXT_FONT33);
                SummarySJrwTextbel.setBounds(293+x, 391+y-15, 109, 20);
                add(SummarySJrwTextbel);

                SummaryRCrwTextbel = new JLabel();
                SummaryRCrwTextbel.setForeground(Color.WHITE);
                SummaryRCrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummaryRCrwTextbel.setFont(UIUtils.TEXT_FONT33);
                SummaryRCrwTextbel.setBounds(473+x, 338+y-3, 109, 20);
                add(SummaryRCrwTextbel);

                SummaryJQrwTextbel = new JLabel();
                SummaryJQrwTextbel.setForeground(Color.WHITE);
                SummaryJQrwTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummaryJQrwTextbel.setFont(UIUtils.TEXT_FONT33);
                SummaryJQrwTextbel.setBounds(473+x, 366+y-9, 109, 20);
                add(SummaryJQrwTextbel);

                SummaryGXTextbel = new JLabel();
                SummaryGXTextbel.setForeground(Color.WHITE);
                SummaryGXTextbel.setHorizontalAlignment(SwingConstants.CENTER);
                SummaryGXTextbel.setFont(UIUtils.TEXT_FONT33);
                SummaryGXTextbel.setBounds(473+x, 391+y-15, 109, 20);
                add(SummaryGXTextbel);

            }
        }
        SummaryTextWidth = 0;
        SummaryCZTextWidth = 0;
        SummarySJTextWidth = 0;
        SummarySJrwTextWidth = 0;
        SummaryRCrwTextWidth = 0;
        SummaryJQrwTextWidth = 0;
        SummaryGXTextWidth = 0;



        ShowInitialValueAllComplete();
        setSelectCompleteAchievement();
	}

    public void setSelectCompleteAchievement() {
        AllachievementMap.clear();
        achievementMap.clear();
        Set<Entry<Integer, Achievement>> entrySet = UserMessUntil.getAllAchievement().getAllAchievement().entrySet();
        List<Achievement> mapList = entrySet.stream()
                .map(Entry::getValue)
                .collect(Collectors.toList());
        List<Achievement> list = new ArrayList<>();
        for (Achievement entry : mapList) {
            String cjjf = RoleData.getRoleData().getLoginResult().getAchieveRecordtype(entry.getConditions());
            if((StringUtils.isNotEmpty(cjjf) && Integer.parseInt(cjjf)>=Integer.parseInt(entry.getNum())) || cjjf.equals("-1")) {
                Achievement achievement1 = new Achievement();
                achievement1.setTrunk(entry.getTrunk());
                achievement1.setTaskName(entry.getTaskName());
                achievement1.setGoodsId(entry.getGoodsId());
                achievement1.setConditions(entry.getConditions());
                achievement1.setPrice(entry.getPrice());
                achievement1.setDescribeText(entry.getDescribeText());
                achievement1.setNum(entry.getNum());
                list.add(achievement1);
            }
        }
        if(!list.isEmpty()){
            for (Achievement achievement : list) {
                String trunk = achievement.getTrunk(); // 获取 Trunk 属性
                if (!achievementMap.containsKey(trunk)) {
                    achievementMap.put(trunk, new ArrayList<>()); // 如果 map 中没有这个 Trunk，创建一个新列表
                }
                achievementMap.get(trunk).add(achievement); // 将 Achievement 对象添加到对应的列表中
            }
        }
        if(!mapList.isEmpty()){
            for (Achievement achievement : mapList) {
                String trunk = achievement.getTrunk(); // 获取 Trunk 属性
                if (!AllachievementMap.containsKey(trunk)) {
                    AllachievementMap.put(trunk, new ArrayList<>()); // 如果 map 中没有这个 Trunk，创建一个新列表
                }
                AllachievementMap.get(trunk).add(achievement); // 将 Achievement 对象添加到对应的列表中
            }
        }
        SummaryTextWidth = 296/mapList.size()*list.size();//284*100;
        SummaryCZTextWidth = 110/AllachievementMap.get("人物成长").size()*(achievementMap.get("人物成长") == null ? 0 : achievementMap.get("人物成长").size());
        SummarySJTextWidth = 110/AllachievementMap.get("人物数据").size()*(achievementMap.get("人物数据") == null ? 0 : achievementMap.get("人物数据").size());
        SummarySJrwTextWidth = 110/AllachievementMap.get("升级任务").size()*(achievementMap.get("升级任务") == null ? 0 : achievementMap.get("升级任务").size());
        SummaryRCrwTextWidth = 110/AllachievementMap.get("日常任务").size()*(achievementMap.get("日常任务") == null ? 0 : achievementMap.get("日常任务").size());
        SummaryJQrwTextWidth = 110/AllachievementMap.get("剧情任务").size()*(achievementMap.get("剧情任务") == null ? 0 : achievementMap.get("剧情任务").size());
        SummaryGXTextWidth = 110/AllachievementMap.get("人物关系").size()*(achievementMap.get("人物关系") == null ? 0 : achievementMap.get("人物关系").size());
        //人物成长、人物数据、升级任务、日常任务、剧情任务、人物关系
        SummaryCZText = (achievementMap.get("人物成长") == null ? "0" : String.valueOf(achievementMap.get("人物成长").size()))+"/"+AllachievementMap.get("人物成长").size();
        SummarySJText = (achievementMap.get("人物数据") == null ? "0" : String.valueOf(achievementMap.get("人物数据").size()))+"/"+AllachievementMap.get("人物数据").size();
        SummaryRCrwText = (achievementMap.get("日常任务") == null ? "0" : String.valueOf(achievementMap.get("日常任务").size()))+"/"+AllachievementMap.get("日常任务").size();
        SummaryGXText = (achievementMap.get("人物关系") == null ? "0" : String.valueOf(achievementMap.get("人物关系").size()))+"/"+AllachievementMap.get("人物关系").size();
        SummaryJQrwText = (achievementMap.get("剧情任务") == null ? "0" : String.valueOf(achievementMap.get("剧情任务").size()))+"/"+AllachievementMap.get("剧情任务").size();
        SummarySJrwText = (achievementMap.get("升级任务") == null ? "0" : String.valueOf(achievementMap.get("升级任务").size()))+"/"+AllachievementMap.get("升级任务").size();
        //汇总统计
        SummaryText = list.size()+"/"+mapList.size();
    }
	public JScrollPane getscpAchievemList() {
		jTreeAchievemList = null;
		scpAchievemList = null;
		nodeAchievemList = null;
		scpAchievemInitialValue = null;
		if (this.scpAchievemList == null) {
			(this.scpAchievemList = new JScrollPane((Component)this.getjTreeAchievemList())).setVerticalScrollBarPolicy(22);
			this.scpAchievemList.getVerticalScrollBar().setUI((ScrollBarUI)new ScrollUI());
			this.scpAchievemList.getVerticalScrollBar().setUnitIncrement(20);
			this.scpAchievemList.getViewport().setOpaque(false);
			this.scpAchievemList.setOpaque(false);
            if(MyIsif.getStyle().equals("水墨")){
                this.scpAchievemList.setBounds(56, 85, 150, 369);
            }else{
                this.scpAchievemList.setBounds(33, 75, 159, 348);
            }
			this.scpAchievemList.setBorder(BorderFactory.createEmptyBorder());
			this.scpAchievemList.setHorizontalScrollBarPolicy(31);
			this.add(this.scpAchievemList);
		}
        return scpAchievemList;
    }
    public JTree getjTreeAchievemList() {
        if (jTreeAchievemList == null) {
            // 创建一个新的根节点
            DefaultMutableTreeNode root = new DefaultMutableTreeNode();
            // 创建单独的叶子节点
            DefaultMutableTreeNode singleLeafNode = new DefaultMutableTreeNode(new JTreeData("当前已完成", "0"));
            // 创建原有的树结构节点
            DefaultMutableTreeNode existingTreeRoot = getNodeAchievemList();
            root.add(singleLeafNode);
            Enumeration<DefaultMutableTreeNode> childrenEnum = existingTreeRoot.children();
            List<DefaultMutableTreeNode> childrenList = new ArrayList<>();
            while (childrenEnum.hasMoreElements()) {
                childrenList.add(childrenEnum.nextElement());
            }
            childrenList.forEach(root::add);
            jTreeAchievemList = new JTree(root);
            jTreeAchievemList.setOpaque(false);
            jTreeAchievemList.putClientProperty("JTree.lineStyle", "None"); // 去除jtree的线条
            ((BasicTreeUI) jTreeAchievemList.getUI()).setLeftChildIndent(0); // 设置父节点与子节点左对齐，并去除缩进
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                              boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                    // 判断是几级的叶子
                    if (node.getLevel() == 2 && node.getParent() != root) {
                        if(node.getUserObject() instanceof JTreeData){
                            JTreeData jtd = (JTreeData) node.getUserObject();
                            setText(jtd.getString());
                        }
                    } else if (node.getLevel() == 1 && node.getParent() == root) {
                        if(node.getUserObject() instanceof JTreeData){
                            JTreeData jtd = (JTreeData) node.getUserObject();
                            setText(jtd.getString());
                        }
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null); // 设置叶子图标
            try {
                cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]); // 设置打开子节点图标
                cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]); // 设置关闭子节点图标
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            cellRenderer.setFont(UIUtils.TEXT_FONT1);// 设置字体.
            cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);// 设置非选定节点的背景色
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));// 设置节点在选中状态下的背景色
            cellRenderer.setBorderSelectionColor(UIUtils.Color_BACK);// 设置选中状态下节点边框的颜色
            // 设置选时或不选时，文字的变化颜色
            cellRenderer.setTextNonSelectionColor(Color.white);// 设置绘制未选中状态下节点文本的颜色
            cellRenderer.setTextSelectionColor(Color.white);// 设置绘制选中状态下节点文本的颜色
            jTreeAchievemList.setCellRenderer(cellRenderer);
            jTreeAchievemList.setRootVisible(false); // 隐藏根节点
            jTreeAchievemList.setRowHeight(20); // 设置节点行高
            // 单击展开子节点
            jTreeAchievemList.addMouseListener(new MouseAdapter() { // 添加鼠标事件处理
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) { // 点击了鼠标左键
                        TreePath path = jTreeAchievemList.getSelectionPath();
                        if (path != null) {
                            if (jTreeAchievemList.isExpanded(path)) {
                                jTreeAchievemList.collapsePath(path);// 关闭节点
                            } else {
                                jTreeAchievemList.expandPath(path);// 展开节点
                            }
                        }
                    }
                }
            });
            jTreeAchievemList.addTreeSelectionListener(e -> {

                JTree tree = (JTree) e.getSource();
                // 利用JTree的getLastSelectedPathComponent()方法取得目前选取的节点.
                DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectionNode == null) {
                    scpAchievemList.setViewportView(null);
                    return;
                }
                if (selectionNode.isLeaf()) {// 判断是否是叶子节点
                    JTreeData treeData = (JTreeData) selectionNode.getUserObject();
                    int parseInt = Integer.parseInt(treeData.getPath());
                    if(parseInt!=0){
                        Achievement achievement = UserMessUntil.getAchievement(parseInt);
                        achievementga = achievement;
                        ShowInitialValue(achievement);
                    }else{
                        ShowInitialValueAllComplete();
                    }
                }
            });
        }
        return jTreeAchievemList;
    }

	public void ShowInitialValue(Achievement achievement) {
		String branch = achievement.getBranch();
		List<Achievement> mapList = new ArrayList<>();
        Set<Entry<Integer, Achievement>> entrySet = UserMessUntil.getAllAchievement().getAllAchievement().entrySet();
        S: for (Entry<Integer, Achievement> entry : entrySet) {
        	Achievement ach = entry.getValue();
            for (int i = 0; i < mapList.size(); i++) {
                if (mapList.get(i).getId() > ach.getId()) {
                    mapList.add(i, ach);
                    continue S;
                }
            }
            mapList.add(achievement);
        }
        achievementContainer.removeAll();
        
        List<Achievement> lsts = new ArrayList<>();
        for (Entry<Integer, Achievement> entry : entrySet) {
        	Achievement ach = entry.getValue();
        	if(ach.getBranch().equals(branch)) {
        		Achievement achievement1 = new Achievement();
				achievement1.setTaskName(ach.getTaskName());
				achievement1.setGoodsId(ach.getGoodsId());
				achievement1.setConditions(ach.getConditions());
				achievement1.setPrice(ach.getPrice());
				achievement1.setDescribeText(ach.getDescribeText());
				achievement1.setNum(ach.getNum());
                String cjjf = RoleData.getRoleData().getLoginResult().getAchieveRecordtype(ach.getConditions());
                if((StringUtils.isNotEmpty(cjjf) && Integer.parseInt(cjjf)>=Integer.parseInt(ach.getNum())) || cjjf.equals("-1")) {
                    achievement1.setRemark("1");
                }
				lsts.add(achievement1);
        	}
        }
        Map<Boolean, List<Achievement>> partitioned = lsts.stream()
                .collect(Collectors.partitioningBy(achievement1 -> "1".equals(achievement1.getRemark())));
        List<Achievement> sortedLsts = new ArrayList<>();
        sortedLsts.addAll(partitioned.get(true));
        sortedLsts.addAll(partitioned.get(false));
        // 更新 lsts 列表
        lsts.clear();
        lsts.addAll(sortedLsts);
		for(int i=0;i<achievementSt.length;i++) {
			if(achievementSt[i]!=null) {
				this.remove(achievementSt[i]);
				achievementSt[i] = null;
			}
		}
        IntStream.range(0, lsts.size()).forEach(index -> {
            Achievement achievementBtn = lsts.get(index);
            achievementSt[index] = new SpotAchievemShowJpanel(achievementBtn, index, 0);
            achievementSt[index].addMouseListener(new CommodityListener(index));
            achievementContainer.add(achievementSt[index]);
        });
		achievementContainer.revalidate(); // 重新验证容器
        achievementContainer.repaint(); // 重绘容器
    }


    public void ShowInitialValueAllComplete() {
        Set<Entry<Integer, Achievement>> entrySet = UserMessUntil.getAllAchievement().getAllAchievement().entrySet();
        List<Achievement> mapList = entrySet.stream()
                .map(Entry::getValue)
                .collect(Collectors.toList());
        achievementContainer.removeAll();
        List<Achievement> lsts = new ArrayList<>();
        for (Achievement entry : mapList) {
            String cjjf = RoleData.getRoleData().getLoginResult().getAchieveRecordtype(entry.getConditions());
            if((StringUtils.isNotEmpty(cjjf) && Integer.parseInt(cjjf)>=Integer.parseInt(entry.getNum())) || cjjf.equals("-1")) {
                Achievement achievement1 = new Achievement();
                achievement1.setTaskName(entry.getTaskName());
                achievement1.setGoodsId(entry.getGoodsId());
                achievement1.setConditions(entry.getConditions());
                achievement1.setPrice(entry.getPrice());
                achievement1.setDescribeText(entry.getDescribeText());
                achievement1.setNum(entry.getNum());
                lsts.add(achievement1);
            }
        }
        IntStream.range(0, achievementSt.length)
                .filter(i -> achievementSt[i] != null)
                .forEach(i -> {
                    this.remove(achievementSt[i]);
                    achievementSt[i] = null;
                });
        IntStream.range(0, lsts.size()).forEach(i -> {
            Achievement achievementBtn = lsts.get(i);
            if (i < achievementSt.length) {
                achievementSt[i] = new SpotAchievemShowJpanel(achievementBtn, i, 0);
                achievementSt[i].addMouseListener(new CommodityListener(i));
                achievementContainer.add(achievementSt[i]);
            }

        });
        achievementContainer.revalidate(); // 重新验证容器
        achievementContainer.repaint(); // 重绘容器
    }




	public DefaultMutableTreeNode getNodeAchievemList() {
        if (nodeAchievemList == null) {
        	nodeAchievemList = new DefaultMutableTreeNode("");
            reAchievemList(UserMessUntil.getAllAchievement().getAllAchievement());
        }
        return nodeAchievemList;
    }
	
	public void reAchievemList(Map<Integer, Achievement> map) {
        nodeAchievemList.removeAllChildren();
        List<Achievement> mapList = new ArrayList<>();
        Set<Entry<Integer, Achievement>> entrySet = map.entrySet();
        S: for (Entry<Integer, Achievement> entry : entrySet) {
        	Achievement achievement = entry.getValue();
            for (int i = 0; i < mapList.size(); i++) {
                if (mapList.get(i).getId() > achievement.getId()) {
                    mapList.add(i, achievement);
                    continue S;
                }
            }
            mapList.add(achievement);
        }

        Map<String, String> maps = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            Achievement achievement = mapList.get(i);
            int childCount = nodeAchievemList.getChildCount();
            boolean trunkExists = false; // 用于检查主节点是否存在
            // 检查当前主节点是否已经存在
            for (int j = 0; j < childCount; j++) {
                DefaultMutableTreeNode childAt = (DefaultMutableTreeNode) nodeAchievemList.getChildAt(j);
                if (achievement.getTrunk().equals(childAt.toString())) {
                    trunkExists = true; // 找到主节点
                    // 检查该主节点下是否已存在当前分支
                    if (!maps.containsKey(achievement.getBranch())) {
                        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(new JTreeData(
                                achievement.getBranch(), String.valueOf(achievement.getId())));
                        childAt.add(defaultMutableTreeNode);
                        maps.put(achievement.getBranch(), achievement.getBranch()); // 更新 maps
                    }
                    break;
                }
            }
            // 如果主节点不存在，则添加主节点
            if (!trunkExists) {
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(achievement.getTrunk());
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new JTreeData(achievement.getBranch(), String.valueOf(achievement.getId())));
                defaultMutableTreeNode.add(childNode);
                nodeAchievemList.add(defaultMutableTreeNode);
                maps.put(achievement.getBranch(), achievement.getBranch()); // 更新 maps
            }
        }


    }

	private class CommodityListener extends MouseAdapter {
        protected int index;
        public CommodityListener(int index) {
            this.index = index;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // 设置选中
            for (int i = 0; i < achievementSt.length; i++) {
                if (achievementSt[i] != null) {
                	achievementSt[i].setSelect(i == index);
                }
            }
        }
    }

    private class HeadListener extends CommodityListener {

        public HeadListener(int index) {
           super(index);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Achievement commodityBean = achievementSt[index].getAchievement();
            System.err.println(234);
//            ZhuFrame.getZhuJpanel().creatgoodtext(commodityBean.getGoodsId(),null);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
            ZhuFrame.getZhuJpanel().pettext();
            ZhuFrame.getZhuJpanel().clearlingtext();
        }
    }

	public ImageIcon icon2 = CutButtonImage.getImage("resource/emoticons/功绩千秋.png", 659, 471);
	public ImageIcon icon3 = CutButtonImage.getImage("resource/emoticons/千秋册.png", 659, 471);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        if(!MyIsif.getStyle().equals("水墨")){
            g.drawImage(icon2.getImage(), 9, -19, 659, 471, this);//内容图大小
            g.setColor(new Color(227, 166, 13));//红木进度条颜色
            int x = -10;
            int w = 23;
            int y = 11;
            g.fillRect(295 +x, 316 -y, SummaryTextWidth, 15);
            g.fillRect(295 + x, 340-y, SummaryCZTextWidth, 15);
            g.fillRect(295 + x, 361-y, SummarySJTextWidth, 15);
            g.fillRect(295 + x, 381-y, SummarySJrwTextWidth, 15);
            g.fillRect(469+w, 340-y, SummaryRCrwTextWidth, 15);
            g.fillRect(469+w, 361-y, SummaryJQrwTextWidth, 15);
            g.fillRect(469+w, 381-y, SummaryGXTextWidth, 15);
            achievementScrollPane.setBounds(202, 100, 432, 167);//功绩内容展示//2
//            achievementScrollPane.setBounds(215, 102, 481, 219);//功绩内容展示
        }else{
            achievementScrollPane.setBounds(211, 109, 420, 182);
            g.drawImage(icon3.getImage(), 0, 0, 659, 471, this);
            g.setColor(new Color(13, 209, 227, 255));//水墨进度条颜色
            int y = -12;
            int w = 18;
            int x = -4;
            g.fillRect(295 +x, 316 -y, SummaryTextWidth, 15);//总功绩
            g.fillRect(295 + x, 340-y, SummaryCZTextWidth, 15);//任务成长
            g.fillRect(295 + x, 362-y, SummarySJTextWidth, 15);//人物数据
            g.fillRect(295 + x, 385-y, SummarySJrwTextWidth, 15);//升级任务
            g.fillRect(470+w, 340-y, SummaryRCrwTextWidth, 15);//日常任务
            g.fillRect(470+w, 361-y, SummaryJQrwTextWidth, 15);//剧情任务
            g.fillRect(470+w, 384-y, SummaryGXTextWidth, 15);//人物关系
        }
		BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩");
        zgjbel.setText(""+zgj);
        gzTextbel.setText(Util.setZhiWeiRank(zgj.intValue()));
        if(!MyIsif.getStyle().equals("水墨")) {
            if (achievementSt != null) {
                int cy = 12000;
                for (int i = 0; i < achievementSt.length; i++) {
                    if (achievementSt[i] != null) {
                        if (achievementSt[i].isSelect()) {
                            cy = i;
                            achievementSt[i].setBounds(9, 1 + 64 * i, 398, 100);//1
                        }
                        if (i > cy) {
                            achievementSt[i].setBounds(9, 1 + 28 + 64 * i, 398, 64);
                        } else {
                            if (i != cy) {
                                achievementSt[i].setBounds(9, 1 + 64 * i, 398, 64);
                            }
                        }
                    }
                }
            }
        }else{
            if (achievementSt != null) {
                int cy = 12000;
                for (int i = 0; i < achievementSt.length; i++) {
                    if (achievementSt[i] != null) {
                        if (achievementSt[i].isSelect()) {
                            cy = i;
                            achievementSt[i].setBounds(6, 1 + 64 * i, 398, 100);
                        }
                        if (i > cy) {
                            achievementSt[i].setBounds(6, 1 + 33 + 64 * i, 398, 64);
                        } else {
                            if (i != cy) {
                                achievementSt[i].setBounds(6, 1 + 64 * i, 398, 64);
                            }
                        }
                    }
                }
            }
        }
        SummaryTextbel.setForeground(Color.WHITE);
        SummaryTextbel.setText(SummaryText);
        SummaryCZTextbel.setText(SummaryCZText);
        SummarySJTextbel.setText(SummarySJText);
        SummarySJrwTextbel.setText(SummarySJrwText);
        SummaryRCrwTextbel.setText(SummaryRCrwText);
        SummaryJQrwTextbel.setText(SummaryJQrwText);
        SummaryGXTextbel.setText(SummaryGXText);

	}

    public JLabel getGzTextbel() {
        return gzTextbel;
    }

    public void setGzTextbel(JLabel gzTextbel) {
        this.gzTextbel = gzTextbel;
    }
}