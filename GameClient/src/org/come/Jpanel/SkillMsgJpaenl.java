package org.come.Jpanel;

import java.awt.Point;
import come.tool.Fighting.Fightingimage;
import java.math.BigDecimal;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import org.come.Frame.ZhuFrame;
import com.tool.imagemonitor.FightingMonitor;
import java.awt.event.MouseAdapter;

import org.come.bean.FightOperation;
import org.come.entity.RoleSummoning;
import org.come.bean.LoginResult;
import com.gl.util.Xy2Util;
import org.come.Frame.MsgJframe1;
import org.come.until.MessagrFlagUntil;
import org.come.Frame.SkillMsgJframe1;
import com.tool.image.ImageMixDeal;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Image;
import com.tool.tcpimg.UIUtils;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Set;
import org.come.until.FormsManagement;
import org.come.until.ScrenceUntil;
import org.come.Frame.SkillMsgJframe;
import java.util.HashSet;
import java.util.Arrays;
import com.tool.role.SkillUtil;
import java.util.Objects;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import java.util.ArrayList;
import come.tool.Fighting.TypeState;
import java.util.List;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import java.awt.Color;
import org.come.bean.ImgZoom;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SkillMsgJpaenl extends JPanel
{
    private SkillMsg[] skillMsgs;
    private JLabel mp;
    private JLabel[] gans;
    int type;
    private ImgZoom imgZoom;
    private ImgZoom imgZoomS;
    Color color;
    static String[] vs;
    String[] vr;
    int size;
    int bh;
    int bw;
    int bh1;
    int bw1;
    
    public SkillMsgJpaenl() {
        this.skillMsgs = new SkillMsg[10];
        this.gans = new JLabel[15];
        this.type = 0;
        this.imgZoomS = CutButtonImage.cuts("resource/jiuUI/ss853.png", 14, 7, true);
        this.color = Color.decode("0xc0ac56");
        this.vr = new String[] { "冥", "蛊", "忘" };
        this.size = 0;
        this.setPreferredSize(new Dimension(420, 350));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBackground(new Color(0, 0, 0, 0));
        (this.mp = new JLabel()).setVisible(false);
        this.add(this.mp);
        for (int i = 0; i < this.gans.length; ++i) {
            this.gans[i] = new JLabel();
            int w = i % 3;
            int h = i / 3;
            this.gans[i].setBounds(40 + 130 * w, 10 + 155 * h, 13, 13);
            this.add(this.gans[i]);
        }
        for (int i = 0; i < this.skillMsgs.length; ++i) {
            this.skillMsgs[i] = new SkillMsg();
            int w = i % 3;
            int h = i / 3;
            this.skillMsgs[i].setBounds(28 + 130 * w, 10 + 155 * h, 110, 140);
            this.add(this.skillMsgs[i]);
        }
    }
    
    public void showSkill(List<TypeState> list, int type) {
        this.type = type;
        for (int i = 0; i < this.skillMsgs.length; ++i) {
            SkillMsg skillMsg = this.skillMsgs[i];
            skillMsg.skillModel.clear();
            skillMsg.imgs.clear();
            skillMsg.typeStates.clear();
        }
        if (type == 1) {
            for (int i = 0; i < this.skillMsgs.length; ++i) {
                int w = i % 3;
                int h = i / 3;
                this.skillMsgs[i].setBounds(28 + 130 * w, 10 + 155 * h, 110, 140);
            }
            List<String> list2 = new ArrayList<>();
            for (int j = 0; j < list.size(); ++j) {
                Skill skill = UserMessUntil.getskill1(list.get(j).getType());
                int id = Integer.parseInt(Objects.requireNonNull(skill).getSkillid());
                double mv = Double.parseDouble(skill.getDielectric());
                String skilltype = SkillUtil.getSkillDoor(list.get(j).getType());
                int p = 5;
                if (skilltype != null) {
                    boolean l = true;
                    int k = list2.size() - 1;
                    while (k >= 0) {
                        if (((String)list2.get(k)).equals(skilltype)) {
                            p = k;
                            l = false;
                            break;
                        }
                        else {
                            --k;
                        }
                    }
                    if (l && list2.size() <= 3) {
                        list2.add(skilltype);
                        p = list2.size() - 1;
                    }
                }
                if (list.get(j).getType().equals("福报因果") || list.get(j).getType().equals("黯然销魂") || list.get(j).getType().equals("强化曼陀罗") || list.get(j).getType().equals("强化反身香") || list.get(j).getType().equals("藏怒") || list.get(j).getType().equals("曼陀罗") || list.get(j).getType().equals("天牢地网") || list.get(j).getType().equals("宿怨") || list.get(j).getType().equals("金钟罩") || list.get(j).getType().equals("七上八下") || list.get(j).getType().equals("落井下石") || list.get(j).getType().equals("一锤定音") || ((TypeState)list.get(j)).getType().equals("神农") || list.get(j).getType().equals("祥瑞御兔") || list.get(j).getType().equals("复活符文") || list.get(j).getType().equals("怨而不宣") || list.get(j).getType().equals("怒而不发") || list.get(j).getType().equals("软猬甲") || ((TypeState)list.get(j)).getType().equals("清心静气") || ((TypeState)list.get(j)).getType().equals("气吞山河") || ((TypeState)list.get(j)).getType().equals("神龙摆尾") || ((TypeState)list.get(j)).getType().equals("行气如虹") || ((TypeState)list.get(j)).getType().equals("气聚神凝") || list.get(j).getType().equals("天罡八卦") || list.get(j).getType().equals("我自岿然") || list.get(j).getType().equals("流风回雪")) {
                    p = 9;
                }
                Set<String> types = new HashSet(Arrays.asList(new String[] { "拔山", "七星贯日", "惊才艳艳", "蔷薇吐刺", "拨云见日", "鸿雁长飞", "覆雨", "一苇渡江", "苦海慈航", "万里云天", "意乱神迷", "失神狂乱", "知盈处虚", "黯然销魂", "十面埋伏", "行面立盹", "酣然入梦", "铺天盖地", "哀鸿遍野", "烽烟四起", "长风万里", "风吹云散", "疾风迅雷", "好事成双", "龙破云惊", "灼体噬骨", "流风回血", "潇潇雨歇", "汤煮松风", "佳期如梦", "阑风长雨", "气贯碧霄", "怒雷齐名", "雷车动地", "沐雨经霜", "震风陵雨", "趁波逐浪", "惊涛断浪", "平波息浪" }));
                if (types.contains(list.get(j).getType())) {
                    p = 4;
                }
                this.skillMsgs[p].addskill(list.get(j));
            }
            for (int j = 0; j < 3; ++j) {
                if (j < list2.size()) {
                    this.skillMsgs[j].vs = new String[] {list2.get(j)};
                }
                else {
                    this.skillMsgs[j].vs = new String[] { "" };
                }
            }
            this.size = 6;
            this.bh = 350;
            this.bw = 420;
            SkillMsgJframe.getSkillMsgJframe().setBounds(ScrenceUntil.Screen_x / 2 - 200, 208, this.bw, this.bh);
            this.imgZoomS.setMiddlew(this.bw - 2 * this.imgZoomS.getEdgew());
            this.imgZoomS.setMiddleh(this.bh - 2 * this.imgZoomS.getEdgeh());
            this.skillMsgs[3].vs = new String[] { "法", "门" };
            this.skillMsgs[4].vs = new String[] { "天", "赋" };
            this.skillMsgs[5].vs = new String[] { "其", "他" };
        }
        else if (type == 2) {
            for (int i = 0; i < this.skillMsgs.length; ++i) {
                int w = i % 3;
                int h = i / 3;
                this.skillMsgs[i].setBounds(28 + 130 * w, 10 + 155 * h, 110, 300);
            }
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).getState() == 0) {
                    if (list.get(i).getType().equals("遮天蔽日") || list.get(i).getType().equals("妙手空空") || list.get(i).getType().equals("万剑归宗") || list.get(i).getType().equals("偷梁换柱")) {
                        int pp = 1;
                        this.skillMsgs[1].addskill(list.get(i));
                    }
                    if (list.get(i).getType().equals("度厄") ||list.get(i).getType().equals("青鳞") || list.get(i).getType().equals("素手") || list.get(i).getType().equals("雷霆霹雳") || list.get(i).getType().equals("日照光华") || list.get(i).getType().equals("雷神怒击") || list.get(i).getType().equals("电闪雷鸣") || list.get(i).getType().equals("飞砂走石") || list.get(i).getType().equals("乘风破浪") || list.get(i).getType().equals("太乙生风") || list.get(i).getType().equals("风雷涌动") || list.get(i).getType().equals("龙卷雨击") || ((TypeState)list.get(i)).getType().equals("龙腾水溅") || ((TypeState)list.get(i)).getType().equals("龙啸九天") || ((TypeState)list.get(i)).getType().equals("蛟龙出海") || ((TypeState)list.get(i)).getType().equals("地狱烈火") || ((TypeState)list.get(i)).getType().equals("天雷怒火") || ((TypeState)list.get(i)).getType().equals("三味真火") || ((TypeState)list.get(i)).getType().equals("烈火骄阳") || ((TypeState)list.get(i)).getType().equals("反间之计") || ((TypeState)list.get(i)).getType().equals("情真意切") || ((TypeState)list.get(i)).getType().equals("谗言相加") || ((TypeState)list.get(i)).getType().equals("借刀杀人") || ((TypeState)list.get(i)).getType().equals("作茧自缚") || ((TypeState)list.get(i)).getType().equals("金蛇缠丝") || ((TypeState)list.get(i)).getType().equals("天罗地网") || list.get(i).getType().equals("作壁上观") || list.get(i).getType().equals("催眠咒") || list.get(i).getType().equals("瞌睡咒") || list.get(i).getType().equals("离魂咒") || list.get(i).getType().equals("迷魂醉") || ((TypeState)list.get(i)).getType().equals("蛇蝎美人") || ((TypeState)list.get(i)).getType().equals("追魂迷香") || ((TypeState)list.get(i)).getType().equals("断肠烈散") || ((TypeState)list.get(i)).getType().equals("鹤顶红粉") || ((TypeState)list.get(i)).getType().equals("夺命勾魂") || ((TypeState)list.get(i)).getType().equals("追神摄魄") || ((TypeState)list.get(i)).getType().equals("魔音摄心") || ((TypeState)list.get(i)).getType().equals("销魂蚀骨") || ((TypeState)list.get(i)).getType().equals("妖之魔力") || list.get(i).getType().equals("力神复苏") || list.get(i).getType().equals("狮王之怒") || list.get(i).getType().equals("兽王神力") || list.get(i).getType().equals("魔之飞步") || ((TypeState)list.get(i)).getType().equals("急速之魔") || ((TypeState)list.get(i)).getType().equals("魔神飞舞") || ((TypeState)list.get(i)).getType().equals("天外飞魔") || ((TypeState)list.get(i)).getType().equals("红袖添香") || list.get(i).getType().equals("莲步轻舞") || list.get(i).getType().equals("楚楚可怜") || list.get(i).getType().equals("魔神护体") || ((TypeState)list.get(i)).getType().equals("幽冥鬼火") || ((TypeState)list.get(i)).getType().equals("火影迷踪") || ((TypeState)list.get(i)).getType().equals("冥烟销骨") || ((TypeState)list.get(i)).getType().equals("落日熔金") || list.get(i).getType().equals("吸血水蛭") || list.get(i).getType().equals("六翅毒蝉") || list.get(i).getType().equals("啮骨抽髓") || ((TypeState)list.get(i)).getType().equals("血煞之蛊") || ((TypeState)list.get(i)).getType().equals("麻沸散") || ((TypeState)list.get(i)).getType().equals("鬼失惊") || list.get(i).getType().equals("乱魂钉") || list.get(i).getType().equals("失心疯") || list.get(i).getType().equals("幽怜魅影") || list.get(i).getType().equals("醉生梦死") || ((TypeState)list.get(i)).getType().equals("一曲销魂") || ((TypeState)list.get(i)).getType().equals("秦丝冰雾") || ((TypeState)list.get(i)).getType().equals("分光化影") || list.get(i).getType().equals("天魔解体") || list.get(i).getType().equals("小楼夜哭") || list.get(i).getType().equals("青面獠牙") || list.get(i).getType().equals("炊金馔玉") || ((TypeState)list.get(i)).getType().equals("枯木逢春") || ((TypeState)list.get(i)).getType().equals("西天净土") || ((TypeState)list.get(i)).getType().equals("如人饮水") || list.get(i).getType().equals("风火燎原") || list.get(i).getType().equals("万籁俱寂") || list.get(i).getType().equals("兵临城下") || list.get(i).getType().equals("妙手回春") || ((TypeState)list.get(i)).getType().equals("春意盎然") || list.get(i).getType().equals("舍身取义") || list.get(i).getType().equals("绝境逢生") || list.get(i).getType().equals("子虚乌有") || list.get(i).getType().equals("春回大地") || list.get(i).getType().equals("春暖花开") || list.get(i).getType().equals("夺魂索命") || list.get(i).getType().equals("五蕴皆空") || list.get(i).getType().equals("瞒天过海") || ((TypeState)list.get(i)).getType().equals("泽披天下") || list.get(i).getType().equals("慧眼菩提") || list.get(i).getType().equals("醍醐灌顶") || list.get(i).getType().equals("红衰翠减") || list.get(i).getType().equals("此恨绵绵") || ((TypeState)list.get(i)).getType().equals("移花接木") || list.get(i).getType().equals("神迟魂钝") || list.get(i).getType().equals("梵音初晓") || list.get(i).getType().equals("防不慎防") || list.get(i).getType().equals("万剑穿心") || ((TypeState)list.get(i)).getType().equals("困世法笼") || ((TypeState)list.get(i)).getType().equals("直捣黄龙") || list.get(i).getType().equals("倒转乾坤") || list.get(i).getType().equals("开山裂石") || list.get(i).getType().equals("戟指怒目") || list.get(i).getType().equals("气贯长虹") || list.get(i).getType().equals("恭行天罚") || list.get(i).getType().equals("雷罚") || list.get(i).getType().equals("神罚") || list.get(i).getType().equals("引火烧身") || ((TypeState)list.get(i)).getType().equals("藏锋蓄势") || list.get(i).getType().equals("魔尽佛生") || list.get(i).getType().equals("炎阳神罚") || list.get(i).getType().equals("气化三清") || list.get(i).getType().equals("天籁梵音") || ((TypeState)list.get(i)).getType().equals("流连忘返") || list.get(i).getType().equals("吞天之口")) {
                        int pp = 1;
                        this.skillMsgs[0].addskill(list.get(i));
                    }
                }
            }
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).getState() != 0) {
                    this.skillMsgs[1].skillModel.addElement(list.get(i).getType());
                    this.skillMsgs[1].typeStates.add(list.get(i));
                }
            }
            this.size = 10;
            this.bh = 290;
            this.bw = 290;
            this.imgZoomS.setMiddlew(this.bw - 2 * this.imgZoomS.getEdgew());
            this.imgZoomS.setMiddleh(this.bh - 2 * this.imgZoomS.getEdgeh());
            SkillMsgJframe.getSkillMsgJframe().setBounds(ScrenceUntil.Screen_x / 2 - 100, 213, this.bw, this.bh);
            this.skillMsgs[0].vs = new String[] { "召", "唤", "兽" };
            this.skillMsgs[1].vs = new String[] { "其", "他" };
            this.skillMsgs[2].vs = new String[] { "", "" };
            this.skillMsgs[3].vs = new String[] { "", "" };
            this.skillMsgs[4].vs = new String[] { "", "" };
            this.skillMsgs[5].vs = new String[] { "", "" };
        }
        FormsManagement.showForm(34);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(3, 0.999f));
        g2d.setFont(UIUtils.TEXT_FONT22);
        g2d.setColor(new Color(203, 181, 91));
        this.imgZoomS.draw(g2d);
        for (int i = 0; i < this.size; ++i) {
            SkillMsg skillMsg = this.skillMsgs[i];
            int w = i % 3;
            int h = i / 3;
            for (int j = 0; j < skillMsg.vs.length; ++j) {
                g2d.drawString(skillMsg.vs[j], 5 + w * 130, 26 + h * 160 + j * 20);
                g2d.drawString(skillMsg.vs[j], 6 + w * 130, 26 + h * 160 + j * 20);
            }
            for (int j = skillMsg.imgs.size() - 1; j >= 0; --j) {
                g2d.drawImage((Image)skillMsg.imgs.get(j), 30 + 140 * w, 10 + 170 * h + j * 20, 20, 20, this);
            }
        }
    }
    
    public JLabel getMp() {
        return this.mp;
    }
    
    public void setMp(JLabel mp) {
        this.mp = mp;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    static {
        SkillMsgJpaenl.vs = new String[] { "法", "门", "天", "赋", "其", "他", "召", "唤", "兽" };
    }
    
    public class SkillMsg extends JList<String>
    {
        public String[] vs;
        public String[] name;
        public List<Image> imgs;
        public List<TypeState> typeStates;
        public DefaultListModel<String> skillModel;
        public SkillMsg SkillMsg;
        public ImageIcon icons;
        public ImageIcon iconh;
        
        public SkillMsg() {
            this.vs = new String[] { "测", "试" };
            this.imgs = new ArrayList<>();
            this.typeStates = new ArrayList<>();
            this.SkillMsg = this;
            this.skillModel = new DefaultListModel<>();
            this.setSelectionForeground(Color.WHITE);
            this.setOpaque(false);
            this.setForeground(Color.white);
            this.setBackground(new Color(0, 0, 0, 0));
            this.setModel(this.skillModel);
            this.setFont(UIUtils.TEXT_FONT8);
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (e.getY() / 28 < SkillMsgJpaenl.SkillMsg.this.SkillMsg.getModel().getSize()) {
                        SkillMsgJpaenl.SkillMsg.this.SkillMsg.setSelectedIndex((e.getY() - 1) / 28);
                        Skill skill = UserMessUntil.getskill1((String)SkillMsgJpaenl.SkillMsg.this.skillModel.get((e.getY() - 1) / 28));
                        if (skill == null) {
                            return;
                        }
                        int id = Integer.parseInt(skill.getSkillid());
                        double mv = Double.parseDouble(skill.getDielectric());
                        int lvl = (int)RoleData.getRoleData().getLoginResult().getGrade();
                        int lvltrue = AnalysisString.lvltrue(lvl);
                        int grade = AnalysisString.lvlint(lvl);
                        double sld = (double)AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
                        if (SkillMsgJpaenl.this.type == 1 && id >= 1001 && id <= 1100) {
                            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                            SkillMsgJpaenl.this.mp.setForeground(UIUtils.COLOR_HURTB3);
                            SkillMsgJpaenl.this.mp.setBackground(new Color(0, 0, 0, 0));
                            SkillMsgJpaenl.this.mp.setBounds(8, 6, 300, 30);
                            SkillMsgJpaenl.this.mp.setFont(UIUtils.TEXT_FONT14);
                            SkillMsgJpaenl.this.mp.setText("消耗MP:" + (int)(mv * (sld / 25000.0 + 1.0)) + ",当前MP:" + loginResult.getMp());
                            FormsManagement.showForm(631);
                            SkillMsgJframe1.getSkillMsgJframe1().setBounds(ScrenceUntil.Screen_x / 2 - 200, 160, SkillMsgJpaenl.this.bw, SkillMsgJpaenl.this.bh);
                            if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                            }
                            MsgJframe1.getJframe1().getJapnel1().SM(skill, (double)Xy2Util.getProficiency(lvltrue), grade);
                        }
                        if (SkillMsgJpaenl.this.type == 2) {
                            RoleSummoning pet = UserMessUntil.getChosePetMes();
                            SkillMsgJpaenl.this.mp.setForeground(UIUtils.COLOR_HURTB3);
                            SkillMsgJpaenl.this.mp.setBackground(new Color(0, 0, 0, 0));
                            SkillMsgJpaenl.this.mp.setBounds(8, 5, 300, 30);
                            SkillMsgJpaenl.this.mp.setFont(UIUtils.TEXT_FONT14);
                            if (pet != null) {
                                SkillMsgJpaenl.this.mp.setText("消耗MP:" + (int)mv + ",当前MP:" + pet.getBasismp());
                            }
                            FormsManagement.showForm(631);
                            SkillMsgJframe1.getSkillMsgJframe1().setBounds(ScrenceUntil.Screen_x / 2 - 100, 165, SkillMsgJpaenl.this.bw, SkillMsgJpaenl.this.bh);
                            if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                            }
                            MsgJframe1.getJframe1().getJapnel1().smx(skill, 1.0, 200);
                        }
                    }
                    else {
                        SkillMsgJpaenl.SkillMsg.this.SkillMsg.clearSelection();
                        FormsManagement.HideForm(46);
                        FormsManagement.HideForm(603);
                    }
                }
                
                @Override
                public void mouseDragged(MouseEvent e) {
                }
            });
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    SkillMsgJpaenl.SkillMsg.this.setSelectionBackground(new Color(122, 117, 112));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                    SkillMsgJpaenl.SkillMsg.this.setSelectionBackground(new Color(0, 0, 0, 0));
                    FormsManagement.HideForm(46);
                    FormsManagement.HideForm(603);
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    JList list = (JList)e.getSource();
                    if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown() && !this.isMenuShortcutKeyDown(e)) {
                        list.clearSelection();
                    }
                    FormsManagement.HideForm(46);
                    FormsManagement.HideForm(603);
                    FormsManagement.HideForm(631);
                    FormsManagement.HideForm(34);
                    if (list.getSelectedIndex() == -1) {
                        return;
                    }
                    if (e.getButton() == 1) {
                        try {
                            int tmp = 0;
                            int[] index = SkillMsgJpaenl.SkillMsg.this.SkillMsg.getSelectedIndices();
                            for (int i = 0; i < index.length; ++i) {
                                tmp = index[i];
                            }
                            if (!((String)SkillMsgJpaenl.SkillMsg.this.skillModel.get(tmp)).equals("")) {
                                if (SkillMsgJpaenl.SkillMsg.this.skillModel.get(tmp) != null&&SkillMsgJpaenl.SkillMsg.this.skillModel.get(tmp).equals("度厄")) {
                                    FightOperation operation1=FightingMonitor.getOperation();
                                    operation1.Record(operation1.getCamp(),operation1.getMan(),1, "度厄");
                                    FightingMonitor.execution(operation1);
                                }else {
                                    FightingMonitor.mousesname = (String) SkillMsgJpaenl.SkillMsg.this.skillModel.get(tmp);
                                    FightingMonitor.mousestate = 1;
                                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE8);
                                    ZhuFrame.getZhuJpanel().HideBeastBtn();
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                
                private boolean isMenuShortcutKeyDown(InputEvent event) {
                    return (event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0x0;
                }
            });
            this.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (SkillMsgJpaenl.SkillMsg.this.typeStates != null) {
                        for (int i = 0, len = SkillMsgJpaenl.SkillMsg.this.typeStates.size(); i < len; ++i) {
                            if (((TypeState)SkillMsgJpaenl.SkillMsg.this.typeStates.get(i)).getState() == 2 && i == index) {
                                this.setForeground(Color.red);
                            }
                        }
                    }
                    if (SkillMsgJpaenl.this.type == 2) {
                        Skill skill = UserMessUntil.getskill1((String)SkillMsgJpaenl.SkillMsg.this.skillModel.get(index));
                        if (skill != null) {
                            double mv = Double.parseDouble(skill.getDielectric());
                            RoleSummoning pet = UserMessUntil.getChosePetMes();
                            if (pet != null) {
                                if (pet.getBasismp() < (long)(int)mv) {
                                    ImageIcon icon = new ImageIcon("img/skill/wxs_" + skill.getSkillid() + ".png");
                                    icon.setImage(icon.getImage().getScaledInstance(24, 24, 1));
                                    this.setIcon(icon);
                                    this.setForeground(Color.red);
                                }
                                if (pet.getBasismp() > (long)(int)mv) {
                                    ImageIcon icon = new ImageIcon("img/skill/wxs_" + skill.getSkillid() + ".png");
                                    icon.setImage(icon.getImage().getScaledInstance(24, 24, 1));
                                    this.setIcon(icon);
                                }
                            }
                        }
                    }
                    if (SkillMsgJpaenl.this.type == 1) {
                        Skill skill = UserMessUntil.getskill1((String)SkillMsgJpaenl.SkillMsg.this.skillModel.get(index));
                        if (skill != null) {
                            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                            double mv2 = Double.parseDouble(skill.getDielectric());
                            double sld = (double)AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
                            if (loginResult.getMp().intValue() < (int)(mv2 * (sld / 25000.0 + 1.0))) {
                                ImageIcon icon2 = new ImageIcon("img/fighting-skill/" + skill.getSkillid() + ".png");
                                icon2.setImage(icon2.getImage().getScaledInstance(24, 24, 1));
                                this.setIcon(icon2);
                                this.setForeground(Color.red);
                            }
                            Fightingimage fightingimage = FightingMixDeal.CurrentDataImage(FightingMixDeal.camp, FightingMixDeal.man);
                            if (fightingimage.getFightingManData().getNqz() < new BigDecimal(skill.getDielectric()).intValue() && Integer.parseInt(skill.getSkillid()) >= 9001 && Integer.parseInt(skill.getSkillid()) <= 9812) {
                                this.setForeground(Color.red);
                            }
                            if (loginResult.getMp().intValue() < (int)(mv2 * (sld / 25000.0 + 1.0))) {
                                ImageIcon icon3 = new ImageIcon("img/fighting-skill/" + skill.getSkillid() + ".png");
                                icon3.setImage(icon3.getImage().getScaledInstance(24, 24, 1));
                                this.setIcon(icon3);
                                this.setForeground(Color.red);
                            }
                            if (loginResult.getMp().intValue() > (int)(mv2 * (sld / 25000.0 + 1.0))) {
                                ImageIcon icon3 = new ImageIcon("img/fighting-skill/" + skill.getSkillid() + ".png");
                                icon3.setImage(icon3.getImage().getScaledInstance(24, 24, 1));
                                this.setIcon(icon3);
                            }
                        }
                    }
                    return this;
                }
            });
        }
        
        @Override
        public int locationToIndex(Point location) {
            int index = super.locationToIndex(location);
            if (index != -1 && !this.getCellBounds(index, index).contains(location)) {
                return -1;
            }
            return index;
        }
        
        public void addskill(TypeState typeState) {
            this.typeStates.add(typeState);
            this.skillModel.addElement(typeState.getType());
        }
        
        public String[] getVs() {
            return this.vs;
        }
        
        public void setVs(String[] vs) {
            this.vs = vs;
        }
        
        public List<Image> getImgs() {
            return this.imgs;
        }
        
        public void setImgs(List<Image> imgs) {
            this.imgs = imgs;
        }
        
        public List<TypeState> getTypeStates() {
            return this.typeStates;
        }
        
        public void setTypeStates(List<TypeState> typeStates) {
            this.typeStates = typeStates;
        }
        
        public DefaultListModel<String> getSkillModel() {
            return this.skillModel;
        }
        
        public void setSkillModel(DefaultListModel<String> skillModel) {
            this.skillModel = skillModel;
        }
        
        public SkillMsg getSkillMsg() {
            return this.SkillMsg;
        }
        
        public void setSkillMsg(SkillMsg skillMsg) {
            this.SkillMsg = skillMsg;
        }
        
        public ImageIcon getIcons() {
            return this.icons;
        }
        
        public void setIcons(ImageIcon icons) {
            this.icons = icons;
        }
        
        public ImageIcon getIconh() {
            return this.iconh;
        }
        
        public void setIconh(ImageIcon iconh) {
            this.iconh = iconh;
        }
    }
}
