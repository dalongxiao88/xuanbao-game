package org.come.lianhua;

import org.come.until.CutButtonImage;
import org.come.until.AccessSuitMsgUntil;
import org.come.Jpanel.RuneOperateJpanel;
import org.come.Frame.RuneOperateJframe;
import org.come.summonequip.JframeSummonEquipMain;
import org.come.Jpanel.RefinersJpanel;
import java.util.regex.Matcher;
import org.come.entity.Goodstable;
import org.come.bean.Skill;
import org.come.Jpanel.RefiningEquiJpanel;
import org.come.Frame.DdianJframe;
import org.come.Frame.SuitBaptizeJframe;
import org.come.starcard.JframeStarCardMain;
import org.come.MountShouHu.RandFJframe;
import org.come.Frame.NewRefiningJframe;
import org.come.until.RefiningUtil;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import org.come.starcard.JpanelStarCardMain;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;
import org.wing.panel.LHMainFrame;
import org.come.Frame.WorkshopRefiningJframe;
import java.util.stream.Collectors;
import org.come.bean.QualityClBean;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.tool.tcp.NewPart;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import com.tool.tcpimg.RichLabel;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AutoMaticRefiningJpanel extends JPanel
{
    private ImageIcon iconBack;
    private JScrollPane paneList;
    private AotoMaticRefiningBtn assistantBtn;
    private AotoMaticRefiningBtn start;
    private AutoMaticRefiningView optionJpanel;
    public JLabel displaymodetext;
    private int leftFlag;
    private List<AttributeVo> attributeVoList;
    private String[] lianHua;
    private RichLabel richLabel;
    private JLabel msgBox;
    private Color color;
    private RoundRectangle2D roundedRectangle;
    private int count;
    private NewPart part;

    public AutoMaticRefiningJpanel() {
        this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四", "属性五", "特技一", "特技二" };
        this.color = new Color(56, 53, 46, 238);
        this.roundedRectangle = new RoundRectangle2D.Double(60.0, 30.0, 100.0, 20.0, 7.0, 7.0);
        this.count = 0;
        this.setPreferredSize(new Dimension(423, 475));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/danxin/ss83.png", 1, 603);
        offBtn.setBounds(297, 3, 17, 17);
        (this.msgBox = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (AutoMaticRefiningJpanel.this.richLabel != null) {
                    AutoMaticRefiningJpanel.this.richLabel.paint(g);
                }
            }
        }).setBounds(40, 280, 350, 100);
        this.add(this.msgBox);
        (this.displaymodetext = new JLabel("炼化")).setForeground(Color.white);
        this.displaymodetext.setFont(UIUtils.TEXT_FONT1);
        this.displaymodetext.setHorizontalAlignment(0);
        this.displaymodetext.setVerticalTextPosition(0);
        this.displaymodetext.setBounds(60, 30, 100, 20);
        this.add(this.displaymodetext);
        String[] displaymodeData = { "炼化", "炼器", "配饰重铸", "星卡洗炼", "星卡五行", "兽装重悟技能", "兽装重洗属性", "套装洗炼", "靓号炼化", "符石重铸", "点粹洗炼", "坐骑守护" };
        this.optionJpanel = new AutoMaticRefiningView(105, 120, displaymodeData);
        this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String getname = (String)AutoMaticRefiningJpanel.this.optionJpanel.getJlist().getSelectedValue();
                AutoMaticRefiningJpanel.this.optionJpanel.setVisible(false);
                AutoMaticRefiningJpanel.this.leftFlag = 1;
                if (!getname.equals(AutoMaticRefiningJpanel.this.displaymodetext.getText())) {
                    AutoMaticRefiningJpanel assistantJpanel = AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel();
                    for (AttributeVo attributeVo : assistantJpanel.getAttributeVoList()) {
                        assistantJpanel.remove(attributeVo.getJ1());
                        assistantJpanel.remove(attributeVo.getJ2());
                        assistantJpanel.remove(attributeVo.getAttributeName());
                    }
                    AutoMaticRefiningJpanel.this.attributeVoList.clear();
                    AutoMaticRefiningJpanel.this.richLabel = null;
                    if (getname.equals("炼化")) {
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四", "属性五", "特技一", "特技二" };
                        String msg = "#K调出炼化面板在点击运行即可开始自动炼化#r如果需要锁定属性请在炼化面板进行#R锁定#K特技属性只需填写#R特技名称#r#K可通过#R|#K符号同时匹配多个属性。例:忽视抗混|加强风#r#R(注意别写错别字)";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("炼器")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四", "属性五" };
                        String msg = "#K打开炼器面板在点击运行即可开始自动炼化#r如果需要锁定属性请在面板进行锁定#r可通过#R|#K符号同时匹配多个属性。例:忽视抗混|加强风#r#R(注意别写错别字)";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("星卡洗炼")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "资质", "神通一", "神通二", "星阵" };
                        String msg = "#K打开星录->重铸->重洗面板在点击运行即可开始自动洗星卡#r星阵属性只需填写星阵名称即可如#R(赤马|青龙)#K等等#r第一排资质要求#K后面填入#R资质#K二字，后面填写数字";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("星卡五行")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "五行加成" };
                        String msg = "#K打开星录->重铸->重洗面板在点击运行即可开始自动洗星卡五行#r五行属性只需填写五行加成星阵之力值";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("配饰重铸")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性要求", "基础属性", "黄字抗性", "绿字属性一", "绿字属性二" };
                        String msg = "#K请将材料放置#R作坊#K面板上在点击运行即可开始自动炼化属性要求填写(根骨要求/灵性要求/力量要求/敏捷要求)不填写则无要求#r属性只需填写字段名(洗戒指#R黄字抗性不需要填写)#K可通过#R|#K符号同时匹配多个属性#r例:属性字段1属性字段2";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("兽装重悟技能")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "觉醒技", "星级" };
                        String msg = "#K请将材料放置#R兽装洗练#K面板上在点击运行即可开始自动洗炼#r觉醒技填写名称即可#K#r多个需求则用#R|#K分隔#r例:一击毙命|法力灵动";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                        ((AttributeVo)AutoMaticRefiningJpanel.this.attributeVoList.get(1)).getJ1().setText("星级");
                    }
                    else if (getname.equals("兽装重洗属性")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "基础属性", "属性一", "属性二", "属性三" };
                        String msg = "#K规则同炼化，填写所需要的属性文字即可";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("套装洗炼")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二" };
                        String msg = "#K规则同炼化，填写所需要的属性文字即可";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("靓号炼化")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四" };
                        String msg = "#K规则同炼化，填写所需要的属性文字即可";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("符石重铸")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "伤害", "活跃", "落宝", "抗落宝", "速度", "负敏", "回复" };
                        String msg = "#K规则同炼化，填写所需要的属性对应值即可#r#r例如：你在#R伤害、活跃、速度#W后面填上青龙符石的值,但其他符石没用青龙的活跃高,只能硬洗青龙,消耗较大!#R活跃、速度#W后填上1，只要出了双属性就停，看一眼不要的属性点继续运行最好";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("点粹洗炼")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二", "特技一" };
                        String msg = "#K调出炼化面板在点击运行即可开始自动炼化#r如果需要锁定属性请在炼化面板进行#R锁定#K特技属性只需填写#R特技名称#r#K可通过#R|#K符号同时匹配多个属性。#r#R(注意别写错别字)";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    else if (getname.equals("坐骑守护")) {
                        AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                        AutoMaticRefiningJpanel.this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四", "属性五", "特技一", "特技二" };
                        String msg = "#K调出炼化面板在点击运行即可开始自动炼化#r如果需要锁定属性请在炼化面板进行#R锁定#K特技属性只需填写#R特技名称#r#K可通过#R|#K符号同时匹配多个属性。#r#R(注意别写错别字)";
                        AutoMaticRefiningJpanel.this.initLianHua(AutoMaticRefiningJpanel.this.lianHua, msg);
                    }
                    AutoMaticRefiningJpanel.this.displaymodetext.setText(getname);
                }
            }
        });
        this.optionJpanel.setVisible(false);
        this.optionJpanel.setBounds(60, 50, 120, 120);
        this.add(this.optionJpanel);
        (this.assistantBtn = new AotoMaticRefiningBtn("inkimg/button/8.png", 1, UIUtils.COLOR_BTNXUANXIANGKA, UIUtils.TEXT_FONT81, "", this, 0)).setBounds(160, 32, 18, 18);
        this.add(this.assistantBtn);
        (this.start = new AotoMaticRefiningBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNXUANXIANGKA, UIUtils.TEXT_FONT81, "运行", this, 1)).setBounds(200, 350, 60, 26);
        this.add(this.start);
        this.lianHua = new String[] { "属性一", "属性二", "属性三", "属性四", "属性五", "特技一", "特技二" };
        String msg = "#K调出炼化面板在点击运行即可开始自动炼化#r如果需要锁定属性请在炼化面板进行#R锁定#K特技属性只需填写#R特技名称#r#K可通过#R|#K符号同时匹配多个属性。例:忽视抗混|加强风#r#R(注意别写错别字)";
        this.initLianHua(this.lianHua, msg);
    }

    private void initLianHua(String[] lianHua, String msg) {
        if (this.attributeVoList == null) {
            this.attributeVoList = new ArrayList<>();
        }
        int x = 130;
        int y = 60;
        Font font = new Font("微软雅黑", 0, 16);
        for (int i = 0; i < lianHua.length; ++i) {
            AttributeVo attributeVo = new AttributeVo();
            JLabel attrName = new JLabel(this.lianHua[i]);
            attrName.setBounds(x - 70, y + i * 30, 100, 20);
            attrName.setFont(UIUtils.TEXT_FONT1);
            attributeVo.setAttributeName(attrName);
            this.add(attrName);
            JTextField j1 = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 5.0, 5.0);
                    g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                    g2d.setColor(AutoMaticRefiningJpanel.this.color);
                    g2d.fill(roundedRectangle);
                    g2d.setColor(Color.white);
                    super.paintComponent(g);
                }
            };
            if (this.displaymodetext.getText().equals("星卡五行")) {
                if (i == 0) {
                    j1.setText("星阵之力加成");
                    j1.setEnabled(false);
                }
            }
            else if (this.displaymodetext.getText().equals("符石重铸")) {
                switch (i) {
                    case 0: {
                        j1.setText("伤害");
                        break;
                    }
                    case 1: {
                        j1.setText("活跃");
                        break;
                    }
                    case 2: {
                        j1.setText("落宝");
                        break;
                    }
                    case 3: {
                        j1.setText("抗落宝");
                        break;
                    }
                    case 4: {
                        j1.setText("速度");
                        break;
                    }
                    case 5: {
                        j1.setText("负敏");
                        break;
                    }
                    case 6: {
                        j1.setText("回复");
                        break;
                    }
                }
                j1.setEnabled(false);
            }
            else if (i == 0 && this.displaymodetext.getText().equals("星卡洗炼") && i == 0) {
                j1.setText("资质");
                j1.setEnabled(false);
            }
            j1.setForeground(Color.white);
            j1.setBackground(UIUtils.Color_BACK);
            j1.setBorder(BorderFactory.createEmptyBorder());
            j1.setCaretColor(Color.WHITE);
            j1.setFont(font);
            j1.setBounds(x, y + i * 30, 100, 20);
            attributeVo.setJ1(j1);
            this.add(j1);
            JTextField j2 = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 7.0, 7.0);
                    g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                    g2d.setColor(AutoMaticRefiningJpanel.this.color);
                    g2d.fill(roundedRectangle);
                    g2d.setColor(Color.white);
                    super.paintComponent(g);
                }
            };
            j2.setForeground(Color.white);
            j2.setBackground(UIUtils.Color_BACK);
            j2.setBorder(BorderFactory.createEmptyBorder());
            j2.setCaretColor(Color.WHITE);
            j2.setFont(font);
            j2.setBounds(x + 120, y + i * 30, 100, 20);
            attributeVo.setJ2(j2);
            this.add(j2);
            this.attributeVoList.add(attributeVo);
        }
        AttributeVo attributeVo2 = (AttributeVo)this.attributeVoList.get(this.attributeVoList.size() - 1);
        Rectangle bounds = attributeVo2.getJ1().getBounds();
        if (this.displaymodetext.getText().equals("星卡洗炼")) {
            this.msgBox.setBounds(40, (int)bounds.getY() + 80, 350, 475 - (int)bounds.getY());
        }
        else if (this.displaymodetext.getText().equals("配饰重铸")) {
            this.msgBox.setBounds(40, (int)bounds.getY() + 50, 350, 475 - (int)bounds.getY());
        }
        else if (this.displaymodetext.getText().equals("兽装重悟技能")) {
            this.msgBox.setBounds(40, (int)bounds.getY() + 50, 350, 475 - (int)bounds.getY());
        }
        else if (this.displaymodetext.getText().equals("兽装重洗属性")) {
            this.msgBox.setBounds(40, (int)bounds.getY() + 50, 350, 475 - (int)bounds.getY());
        }
        else if (this.displaymodetext.getText().equals("套装洗炼")) {
            this.msgBox.setBounds(40, (int)bounds.getY() + 50, 350, 475 - (int)bounds.getY());
        }
        else if (this.displaymodetext.getText().equals("点粹洗炼")) {
            this.msgBox.setBounds(60, (int)bounds.getY() + 50, 310, 475 - (int)bounds.getY());
        }
        else {
            this.msgBox.setBounds(40, (int)bounds.getY() + 20, 350, 475 - (int)bounds.getY());
        }
        (this.richLabel = new RichLabel()).addText(msg);
        this.richLabel.setSize(this.msgBox.getWidth(), this.msgBox.getHeight());
    }

    public void detectionProperties(QualityClBean clBean) {
        ++this.count;
        List<AttributeVo> collect = (List)this.attributeVoList.stream().filter(item/* org.come.lianhua.AttributeVo, */ -> item.getJ1().getText() != null && item.getJ1().getText().length() >= 1).collect(Collectors.toList());
        RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
        Integer[] res = new Integer[this.attributeVoList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Integer.valueOf(0);
        }
        String newAttr = clBean.getNewAttr();
        System.out.println(newAttr);
        int rightType = LHMainFrame.LHMainFrame().getLhMainPanel().getRightType();
        if (rightType == 6 || rightType == 7) {
            String[] split = newAttr.split("@");
            int index = 0;
            for (String value : split) {
                if (value.startsWith("炼化属性")) {
                    if (index == 7 - rightType) {
                        newAttr = value;
                    }
                    ++index;
                }
            }
        }
        if (newAttr.contains("特技")) {
            String[] split = newAttr.split("特技");
            String[] tjs;
            for (String s : tjs = split[1].split("=")) {
                if (!StringUtils.isBlank(s)) {
                    Skill skill = UserMessUntil.getSkillId(s);
                    if (skill != null) {
                        StringBuilder sb = new StringBuilder();
                        int n3 = 0;
                        split[n3] = sb.append(split[n3]).append("&").append(skill.getSkillname()).append("=1").toString();
                    }
                }
            }
            newAttr = split[0];
        }
        if (newAttr.contains("星阵属性")) {
            String[] split = newAttr.split("星阵属性");
            String[] tjs = split[1].split("=");
            if (tjs.length >= 2) {
                StringBuilder sb2 = new StringBuilder();
                int n4 = 0;
                split[n4] = sb2.append(split[n4]).append(tjs[1]).append("=1").toString();
            }
            newAttr = split[0];
        }
        if (newAttr.contains("五行属性")) {
            String[] vs = newAttr.split("&");
            Goodstable chooseStarCard = GoodsListFromServerUntil.getRgid(clBean.getRgid());
            String[] split2 = chooseStarCard.getValue().split("星阵属性=");
            Double num = Double.valueOf(0.0);
            if (split2.length == 2) {
                String[] split3 = split2[1].split("=");
                for (int j = 1; j < vs.length; ++j) {
                    String[] split4 = vs[j].split("=");
                    num = Double.valueOf((double)num + JpanelStarCardMain.fiveElementRestrainCreate(split3[1], split4[0], split4[1]));
                }
            }
            BigDecimal bigDecimal = new BigDecimal(num.toString()).setScale(2, 4);
            bigDecimal.stripTrailingZeros();
            newAttr = newAttr + "&星阵之力加成=" + bigDecimal;
        }
        String[] split = newAttr.split("&");
        if (clBean.getType() == 47) {
            split = newAttr.split("\\|");
            String regex = "\\d";
            Pattern pattern = Pattern.compile(regex);
            int index2 = 0;
            for (String s2 : split) {
                Matcher matcher = pattern.matcher(s2);
                if (matcher.find() && s2.contains("=")) {
                    String[] v = s2.split("=");
                    Skill skill2 = UserMessUntil.getSkillId(v[0]);
                    if (skill2 != null) {
                        split[index2] = skill2.getSkillname() + "=" + v[1];
                        newAttr = newAttr + "|" + skill2.getSkillname() + "=" + v[1];
                    }
                }
                ++index2;
            }
        }
        int index = -1;
        for (AttributeVo attributeVo : this.attributeVoList) {
            ++index;
            if (StringUtils.isBlank(attributeVo.getJ1().getText())) {
                continue;
            }
            else {
                if (this.start.getText().equals("运行")) {
                    return;
                }
                long count = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
                if (count == (long)collect.size()) {
                    this.start.setText("运行");
                    return;
                }
                String text = attributeVo.getJ1().getText();
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                else {
                    String[] vs2 = text.split("\\|");
                    for (int k = 0; k < vs2.length; ++k) {
                        Double attrNumber = Double.valueOf(0.0);
                        if (StringUtils.isNotBlank(attributeVo.getJ2().getText())) {
                            attrNumber = Double.valueOf(Double.parseDouble(attributeVo.getJ2().getText().replace(" ", "")));
                        }
                        int l = -1;
                        if (newAttr.contains(vs2[k])) {
                            for (int i2 = 1; i2 < split.length; ++i2) {
                                if (split[i2].startsWith(vs2[k]) && !split[i2].split("=")[1].contains("|制作人")) {
                                    double v2 = Double.parseDouble(split[i2].split("=")[1]);
                                    if ((double)attrNumber != 0.0 && v2 >= (double)attrNumber) {
                                        l = i2;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                    else if ((double)attrNumber == 0.0) {
                                        l = k;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                }
                            }
                            if (l != -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        long sum = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
        if (sum == (long)collect.size()) {
            this.start.setText("运行");
            return;
        }
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        if (this.displaymodetext.getText().equals("炼化")) {
            String v3 = RefiningUtil.detection(EJpanel.goods, EJpanel.getType());
            if ((v3.equals("炼化装备") || v3.equals("炼化仙器") || v3.equals("炼化神兵")) && this.start.getText().equals("停止")) {
                NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().getOperBtn3().nochoose(null);
            }
        }
        if (this.displaymodetext.getText().equals("坐骑守护")) {
            if (this.start.getText().equals("停止")) {
                RandFJframe.getRandFJframe().getRandFJpanel().getKaishifuling().nochoose(null);
            }
        }
        else if (this.displaymodetext.getText().equals("靓号炼化")) {
            if (this.start.getText().equals("停止")) {
                LHMainFrame.LHMainFrame().getLhMainPanel().getRefineryBtn().nochoose(null);
            }
        }
        else if (this.displaymodetext.getText().equals("炼器")) {
            RefinersJpanel rJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getRefinersJpanel();
            String v4 = rJpanel.detection();
            if (v4.equals("炼器") && this.start.getText().equals("停止")) {
                NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().getOperBtn3().nochoose(null);
            }
        }
        else if (this.displaymodetext.getText().equals("星卡洗炼") || this.displaymodetext.getText().equals("星卡五行")) {
            int smallType = JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().getSmallType();
            if (smallType == 3 && this.start.getText().equals("停止")) {
                JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().caoZuoStarCard();
            }
        }
        else if (this.displaymodetext.getText().equals("套装洗炼")) {
            if (this.start.getText().equals("停止")) {
                SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getBaptizeBtn1().nochoose(null);
            }
        }
        else if (this.displaymodetext.getText().equals("点粹洗炼") && this.start.getText().equals("停止")) {
            DdianJframe.getDdianJframe().getDianJpanel().getOperBtn3().nochoose(null);
        }
    }

    public void petEquiDetectionProperties(QualityClBean clBean) {
        ++this.count;
        List<AttributeVo> collect = (List)this.attributeVoList.stream().filter(item/* org.come.lianhua.AttributeVo, */ -> item.getJ1().getText() != null && item.getJ1().getText().length() >= 1).collect(Collectors.toList());
        RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
        Integer[] res = new Integer[this.attributeVoList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Integer.valueOf(0);
        }
        String newAttr = clBean.getNewAttr();
        if (newAttr.contains("觉醒技")) {
            String[] split = newAttr.split("觉醒技");
            String[] tjs;
            for (String s : tjs = split[1].split("&")) {
                if (!StringUtils.isBlank(s)) {
                    Skill skill = UserMessUntil.getSkillId(s);
                    if (skill != null) {
                        StringBuilder sb = new StringBuilder();
                        int n = 0;
                        split[n] = sb.append(split[n]).append("&").append(skill.getSkillname()).append("=1").toString();
                    }
                    else {
                        StringBuilder sb2 = new StringBuilder();
                        int n2 = 0;
                        split[n2] = sb2.append(split[n2]).append("&星级=").append(s).toString();
                        break;
                    }
                }
            }
            newAttr = split[0];
        }
        String[] split = newAttr.split("&");
        int index = -1;
        for (AttributeVo attributeVo : this.attributeVoList) {
            ++index;
            if (StringUtils.isBlank(attributeVo.getJ1().getText())) {
                continue;
            }
            else {
                if (this.start.getText().equals("运行")) {
                    return;
                }
                long count = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
                if (count == (long)collect.size()) {
                    this.start.setText("运行");
                    return;
                }
                String text = attributeVo.getJ1().getText();
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                else {
                    String[] vs = text.split("\\|");
                    for (int j = 0; j < vs.length; ++j) {
                        Double attrNumber = Double.valueOf(0.0);
                        if (StringUtils.isNotBlank(attributeVo.getJ2().getText())) {
                            attrNumber = Double.valueOf(Double.parseDouble(attributeVo.getJ2().getText().replace(" ", "")));
                        }
                        int k = -1;
                        if (newAttr.contains(vs[j])) {
                            for (int i2 = 1; i2 < split.length; ++i2) {
                                if (split[i2].startsWith(vs[j])) {
                                    double v = Double.parseDouble(split[i2].split("=")[1]);
                                    if ((double)attrNumber != 0.0 && v >= (double)attrNumber) {
                                        k = i2;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                    else if ((double)attrNumber == 0.0) {
                                        k = j;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                }
                            }
                            if (k != -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        long sum = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
        if (sum == (long)collect.size()) {
            this.start.setText("运行");
            return;
        }
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        if (this.displaymodetext.getText().equals("兽装重悟技能")) {
            JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getAckBtn().nochoose(null);
        }
    }

    public void petShidetectionProperties(Goodstable goodstable) {
        ++this.count;
        if (this.start.getText().equals("运行")) {
            return;
        }
        if (goodstable != null) {
            Goodstable[] goods = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getGoods();
            goods[0] = goodstable;
            JLabel[] labGoods = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getLabGoods();
            labGoods[0].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
        }
        else {
            Goodstable[] goods = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getGoods();
            String detection = RefiningUtil.detection(goods, 2);
            if (detection.equals("佩饰重铸")) {
                WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getWorkshopBtn().nochoose(null);
                return;
            }
        }
        List<AttributeVo> collect = (List)this.attributeVoList.stream().filter(item/* org.come.lianhua.AttributeVo, */ -> item.getJ1().getText() != null && item.getJ1().getText().length() >= 1).collect(Collectors.toList());
        Integer[] res = new Integer[this.attributeVoList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Integer.valueOf(0);
        }
        String newAttr = goodstable.getValue();
        String[] split = newAttr.split("\\|");
        String attr = "";
        String sxyq = "";
        String jcsx = "";
        String kxyq = "";
        for (String s : split) {
            String[] split2 = s.split("=");
            if (split2[0].equals("灵性要求") || split2[0].equals("力量要求") || split2[0].equals("根骨要求") || split2[0].equals("敏捷要求")) {
                sxyq = s;
            }
            else if (split2[0].equals("灵性") || split2[0].equals("力量") || split2[0].equals("根骨") || split2[0].equals("敏捷")) {
                jcsx = s;
            }
            else if (s.startsWith("抗")) {
                kxyq = s;
            }
            else if (s.startsWith("炼化属性")) {
                attr = s;
            }
        }
        attr = attr + "&" + sxyq + "&" + jcsx;
        String[] jcsxs = kxyq.split("=");
        if (StringUtils.isNotBlank(((AttributeVo)this.attributeVoList.get(2)).getJ1().getText())) {
            String[] split3 = ((AttributeVo)this.attributeVoList.get(2)).getJ1().getText().split("\\|");
            int length2 = split3.length;
            int n = 0;
            while (n < length2) {
                String s2 = split3[n];
                if (s2.equals(jcsxs[0])) {
                    if (StringUtils.isBlank(((AttributeVo)this.attributeVoList.get(2)).getJ2().getText())) {
                        res[2] = Integer.valueOf(1);
                        break;
                    }
                    else if (Integer.parseInt(jcsxs[1]) >= Integer.parseInt(((AttributeVo)this.attributeVoList.get(2)).getJ2().getText())) {
                        res[2] = Integer.valueOf(1);
                        break;
                    }
                    else {
                        res[2] = Integer.valueOf(0);
                        break;
                    }
                }
                else {
                    ++n;
                }
            }
        }
        else {
            res[2] = Integer.valueOf(0);
        }
        split = attr.split("&");
        int index = -1;
        for (AttributeVo attributeVo : this.attributeVoList) {
            if (++index == 2) {
                continue;
            }
            else if (StringUtils.isBlank(attributeVo.getJ1().getText())) {
                continue;
            }
            else {
                if (this.start.getText().equals("运行")) {
                    return;
                }
                long count = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
                if (count == (long)collect.size()) {
                    this.start.setText("运行");
                    return;
                }
                String text = attributeVo.getJ1().getText();
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                else {
                    String[] vs = text.split("\\|");
                    for (int j = 0; j < vs.length; ++j) {
                        Double attrNumber = Double.valueOf(0.0);
                        if (StringUtils.isNotBlank(attributeVo.getJ2().getText())) {
                            attrNumber = Double.valueOf(Double.parseDouble(attributeVo.getJ2().getText().replace(" ", "")));
                        }
                        int k = -1;
                        if (newAttr.contains(vs[j])) {
                            for (int i2 = 1; i2 < split.length; ++i2) {
                                if (split[i2].startsWith(vs[j])) {
                                    double v = Double.parseDouble(split[i2].split("=")[1]);
                                    if ((double)attrNumber != 0.0 && v >= (double)attrNumber) {
                                        k = i2;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                    else if ((double)attrNumber == 0.0) {
                                        k = j;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                }
                            }
                            if (k != -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        long sum = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
        if (sum == (long)collect.size()) {
            this.start.setText("运行");
            return;
        }
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        if (this.displaymodetext.getText().equals("配饰重铸")) {
            Goodstable[] goods2 = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getGoods();
            String detection2 = RefiningUtil.detection(goods2, 2);
            if (detection2.equals("佩饰重铸")) {
                WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getWorkshopBtn().nochoose(null);
            }
        }
    }

    public void runeOperateProperties1(Goodstable goodstable) {
        ++this.count;
        if (this.start.getText().equals("运行")) {
            return;
        }
        List<AttributeVo> collect = (List)this.attributeVoList.stream().filter(item/* org.come.lianhua.AttributeVo, */ -> StringUtils.isNotBlank(item.getJ2().getText()) && Integer.parseInt(item.getJ2().getText()) > 0).collect(Collectors.toList());
        Integer[] res = new Integer[this.attributeVoList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Integer.valueOf(0);
        }
        String newAttr = goodstable.getValue();
        String[] split = newAttr.split("\\|");
        String attr = "";
        for (String s : split) {
            if (s.startsWith("伤害") || s.startsWith("活跃") || s.startsWith("落宝") || s.startsWith("抗落宝") || s.startsWith("速度") || s.startsWith("负敏") || s.startsWith("回复")) {
                if (attr.length() > 0) {
                    attr += "&";
                }
                attr += s;
            }
        }
        split = attr.split("&");
        int index = -1;
        for (AttributeVo attributeVo : this.attributeVoList) {
            if (++index == 2) {
                continue;
            }
            else if (StringUtils.isBlank(attributeVo.getJ1().getText())) {
                continue;
            }
            else {
                if (this.start.getText().equals("运行")) {
                    return;
                }
                long count = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
                if (count >= (long)collect.size()) {
                    this.start.setText("运行");
                    return;
                }
                String text = attributeVo.getJ1().getText();
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                else {
                    Double attrNumber = Double.valueOf(0.0);
                    if (StringUtils.isNotBlank(attributeVo.getJ2().getText())) {
                        attrNumber = Double.valueOf(Double.parseDouble(attributeVo.getJ2().getText().replace(" ", "")));
                    }
                    if ((double)attrNumber <= 0.0) {
                        continue;
                    }
                    else {
                        String[] vs = text.split("\\|");
                        for (int j = 0; j < vs.length; ++j) {
                            int k = -1;
                            if (newAttr.contains(vs[j])) {
                                for (int i2 = 0; i2 < split.length; ++i2) {
                                    if (split[i2].startsWith(vs[j])) {
                                        double v = Double.parseDouble(split[i2].split("=")[1]);
                                        if ((double)attrNumber != 0.0 && v >= (double)attrNumber) {
                                            k = i2;
                                            res[index] = Integer.valueOf(1);
                                            split[i2] = "";
                                            break;
                                        }
                                        else if ((double)attrNumber == 0.0) {
                                            k = j;
                                            res[index] = Integer.valueOf(1);
                                            split[i2] = "";
                                            break;
                                        }
                                    }
                                }
                                if (k != -1) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        long sum = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
        if (sum == (long)collect.size()) {
            this.start.setText("运行");
            return;
        }
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        if (this.displaymodetext.getText().equals("配饰重铸")) {
            Goodstable[] goods = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getGoods();
            String detection = RefiningUtil.detection(goods, 2);
            if (detection.equals("佩饰重铸")) {
                WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getWorkshopBtn().nochoose(null);
            }
        }
        else if (this.displaymodetext.getText().equals("符石重铸")) {
            RuneOperateJpanel operateJpanel = RuneOperateJframe.getRuneOperateJframe().getOperateJpanel();
            if (operateJpanel.getRuneType() == 1) {
                RuneOperateJframe.getRuneOperateJframe().getOperateJpanel().getPerBtn1().nochoose(null);
            }
        }
    }

    public void petShidetectionPropertiesAttr(Goodstable goodstable) {
        ++this.count;
        List<AttributeVo> collect = (List)this.attributeVoList.stream().filter(item/* org.come.lianhua.AttributeVo, */ -> item.getJ1().getText() != null && item.getJ1().getText().length() >= 1).collect(Collectors.toList());
        RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
        Integer[] res = new Integer[this.attributeVoList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Integer.valueOf(0);
        }
        String s = goodstable.getValue().split("\\|")[3];
        String newAttr = AccessSuitMsgUntil.getExtra(goodstable.getValue(), "炼化属性");
        newAttr = newAttr + "&" + s;
        String[] split = newAttr.split("&");
        int index = -1;
        for (AttributeVo attributeVo : this.attributeVoList) {
            ++index;
            if (StringUtils.isBlank(attributeVo.getJ1().getText())) {
                continue;
            }
            else {
                if (this.start.getText().equals("运行")) {
                    return;
                }
                long count = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
                if (count == (long)collect.size()) {
                    this.start.setText("运行");
                    return;
                }
                String text = attributeVo.getJ1().getText();
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                else {
                    String[] vs = text.split("\\|");
                    for (int j = 0; j < vs.length; ++j) {
                        Double attrNumber = Double.valueOf(0.0);
                        if (StringUtils.isNotBlank(attributeVo.getJ2().getText())) {
                            attrNumber = Double.valueOf(Double.parseDouble(attributeVo.getJ2().getText().replace(" ", "")));
                        }
                        int k = -1;
                        if (newAttr.contains(vs[j])) {
                            for (int i2 = 1; i2 < split.length; ++i2) {
                                if (split[i2].startsWith(vs[j])) {
                                    double v = Double.parseDouble(split[i2].split("=")[1]);
                                    if ((double)attrNumber != 0.0 && v >= (double)attrNumber) {
                                        k = i2;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                    else if ((double)attrNumber == 0.0) {
                                        k = j;
                                        res[index] = Integer.valueOf(1);
                                        split[i2] = "";
                                        break;
                                    }
                                }
                            }
                            if (k != -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        long sum = Arrays.stream(res).filter(item/* java.lang.Integer, */ -> (int)item == 1).count();
        if (sum == (long)collect.size()) {
            this.start.setText("运行");
            return;
        }
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        if (this.displaymodetext.getText().equals("兽装重洗属性")) {
            JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getAckBtn().nochoose(null);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S285.png", 423, 475);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 423, 411, this);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(60.0, 30.0, 100.0, 20.0, 7.0, 7.0);
        g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
        g2d.setColor(this.color);
        g2d.fill(roundedRectangle);
        g.drawString("您已经炼化了", 200, 50);
        g.setColor(Color.red);
        g.drawString(this.count + "", 295, 50);
        g.setColor(Color.BLACK);
        g.drawString("次", 320, 50);
    }

    public ImageIcon getIconBack() {
        return this.iconBack;
    }

    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }

    public void setPaneList(JScrollPane paneList) {
        this.paneList = paneList;
    }

    public AotoMaticRefiningBtn getAssistantBtn() {
        return this.assistantBtn;
    }

    public void setAssistantBtn(AotoMaticRefiningBtn assistantBtn) {
        this.assistantBtn = assistantBtn;
    }

    public JScrollPane getPaneList() {
        return this.paneList;
    }

    public AutoMaticRefiningView getOptionJpanel() {
        return this.optionJpanel;
    }

    public void setOptionJpanel(AutoMaticRefiningView optionJpanel) {
        this.optionJpanel = optionJpanel;
    }

    public JLabel getDisplaymodetext() {
        return this.displaymodetext;
    }

    public void setDisplaymodetext(JLabel displaymodetext) {
        this.displaymodetext = displaymodetext;
    }

    public int getLeftFlag() {
        return this.leftFlag;
    }

    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
    }

    public AotoMaticRefiningBtn getStart() {
        return this.start;
    }

    public void setStart(AotoMaticRefiningBtn start) {
        this.start = start;
    }

    public List<AttributeVo> getAttributeVoList() {
        return this.attributeVoList;
    }

    public void setAttributeVoList(List<AttributeVo> attributeVoList) {
        this.attributeVoList = attributeVoList;
    }

    public String[] getLianHua() {
        return this.lianHua;
    }

    public void setLianHua(String[] lianHua) {
        this.lianHua = lianHua;
    }

    public RichLabel getRichLabel() {
        return this.richLabel;
    }

    public void setRichLabel(RichLabel richLabel) {
        this.richLabel = richLabel;
    }

    public JLabel getMsgBox() {
        return this.msgBox;
    }

    public void setMsgBox(JLabel msgBox) {
        this.msgBox = msgBox;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public RoundRectangle2D getRoundedRectangle() {
        return this.roundedRectangle;
    }

    public void setRoundedRectangle(RoundRectangle2D roundedRectangle) {
        this.roundedRectangle = roundedRectangle;
    }

    public NewPart getPart() {
        return this.part;
    }

    public void setPart(NewPart part) {
        this.part = part;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
