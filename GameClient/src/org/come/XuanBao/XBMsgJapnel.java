package org.come.XuanBao;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.role.RoleLingFa;
import com.tool.tcpimg.PetSkillBox;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.tool.tcpimg.WlinBox;
import come.tool.FightingData.FBUtil;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.MsgJframe1;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.GameJpanel;
import org.come.bean.ImgZoom;
import org.come.bean.Skill;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.entity.XuanBaoSkill;
import org.come.model.Lingbao;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.come.XuanBao.XuanBaoAttributeJpanel.xuanBaoSkillMap;

public class XBMsgJapnel extends JPanel {
    private RichLabel xbInfobox;
    private RichLabel xbInfobox1;
    private RichLabel xbInfobox2;
    private RichLabel xbInfobox3;
    private int boxw;
    private int boxh;
    private XuanBao xuanBao;
    private XuanBao extXuanBao;

    public XBMsgJapnel() {
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 0, 0));
        xbInfobox = new RichLabel();
        xbInfobox1 = new RichLabel();
        xbInfobox2 = new RichLabel();
        xbInfobox3 = new RichLabel();
        this.setBounds(0, 0, 320, 250);
    }

    private String skill2Colors;
    private String skill3Colors;
    private String skill4Colors;
    private String skill2;
    private String skill3;
    private String skill4;
    private String[] skill2ColorsArr;
    private String[] skill3ColorsArr;
    private String[] skill4ColorsArr;
    private ImageIcon rB = CutButtonImage.getImage("img/xuan/红.png", -1, -1);
    private ImageIcon gB = CutButtonImage.getImage("img/xuan/绿.png", -1, -1);
    private ImageIcon bB = CutButtonImage.getImage("img/xuan/蓝.png", -1, -1);
    private ImageIcon yB = CutButtonImage.getImage("img/xuan/黄.png", -1, -1);
    private ImageIcon cB = CutButtonImage.getImage("img/xuan/dc.png", -1, -1);

    private Boolean flag = true;
    int y3 = 28;
    int y4 = 0;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);


        final Font font = new Font("宋体", 0, 15);
        g.setFont(font);
        g.setColor(new Color(92, 70, 44));
//        g.drawString(xuanBao.getName(), 68, 22);
        g.drawString("【玄宝类型】" + xuanBao.getLeixing(), 2, 19);
        g.drawString("【所属门派】" + xuanBao.getRolelvl(), 2, 38);
        g.drawString("【玄宝技能】", 2, 70);
        final Graphics2D g2d = (Graphics2D) g.create();
        // 线条颜色
        int y = 50;
        int w = getWidth();
        GradientPaint gp = new GradientPaint(
                0, y, new Color(150, 154, 128, 170),
                w, y, new Color(150, 154, 130, 200)
        );
        g2d.setPaint(gp);
        g2d.fillRect(10, y - 1, w - 29, 1);

        Graphics g2 = g.create(5, 80, xbInfobox.getWidth(), xbInfobox.getHeight());
        this.xbInfobox.paint(g2);
        g2.dispose();
        y = 80 + xbInfobox.getHeight() + 7;
        g2d.setPaint(gp);
        g2d.fillRect(10, y - 1, w - 29, 1);

        g.drawString("【玄印配置】", 2, 115 + xbInfobox.getHeight());
        g2 = g.create(5, 125 + xbInfobox.getHeight(), xbInfobox1.getWidth(), xbInfobox1.getHeight());
        this.xbInfobox1.paint(g2);
        g2.dispose();
        y = 130 + xbInfobox1.getHeight() + xbInfobox.getHeight() + 7;
        g2d.setPaint(gp);
        g2d.fillRect(10, y - 1, w - 29, 1);

        g.drawString("【玄印技能】", 2, 160 + xbInfobox.getHeight() + xbInfobox1.getHeight());
        g2 = g.create(5 + 60, 170 + xbInfobox.getHeight() + xbInfobox1.getHeight(), xbInfobox2.getWidth(), xbInfobox2.getHeight());
        this.xbInfobox2.paint(g2);
        g2.dispose();
        y = 185 + xbInfobox1.getHeight() + xbInfobox.getHeight() + xbInfobox2.getHeight() + 7;
        g2d.setPaint(gp);
        g2d.fillRect(10, y - 1, w - 29, 1);
        int height = g.getFontMetrics().getHeight();


        if (StringUtils.isNotBlank(skill2Colors)) {
            g.drawImage(cB.getImage(), 5, 170 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            for (int i = 0; i < skill2ColorsArr.length; i++) {
                ImageIcon colorY = CutButtonImage.getImage("img/xuan/" + skill2ColorsArr[i] + ".png", -1, -1);
                g.drawImage(colorY.getImage(), 18 + i * 15, 172 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            }
            if (flag) {
                BigDecimal count = new BigDecimal(skill2.length()).divide(new BigDecimal(20)).setScale(0, RoundingMode.CEILING);
                y3 = count.intValue() * height + 13;
            }
        }

        if (StringUtils.isNotBlank(skill3Colors)) {
//            BigDecimal count = new BigDecimal(skill3.length()).divide(new BigDecimal(20)).setScale(0, RoundingMode.CEILING);
//            y3 = count.intValue() * height;
            g.drawImage(cB.getImage(), 5, y3 + 170 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            for (int i = 0; i < skill2ColorsArr.length; i++) {
                ImageIcon colorY = CutButtonImage.getImage("img/xuan/" + skill2ColorsArr[i] + ".png", -1, -1);
                g.drawImage(colorY.getImage(), 18 + i * 15, y3 + 172 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            }
            if (flag) {
                BigDecimal count = new BigDecimal(skill3.length()).divide(new BigDecimal(20)).setScale(0, RoundingMode.CEILING);
                y4 = count.intValue() * height + 13;
            }
        }

        if (StringUtils.isNotBlank(skill4Colors)) {
//            BigDecimal count = new BigDecimal(skill4.length()).divide(new BigDecimal(20)).setScale(0, RoundingMode.CEILING);
//            y4 = count.intValue() *height+ count.intValue()*2;
            g.drawImage(cB.getImage(), 5, y4 + y3 + 170 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            for (int i = 0; i < skill4ColorsArr.length; i++) {
                ImageIcon colorY = CutButtonImage.getImage("img/xuan/" + skill4ColorsArr[i] + ".png", -1, -1);
                g.drawImage(colorY.getImage(), 18 + i * 15, y4 + y3 + 172 + xbInfobox.getHeight() + xbInfobox1.getHeight() + 3, null);
            }
        }

        g.drawString("【玄印历史】", 2, 215 + xbInfobox.getHeight() + xbInfobox1.getHeight() + xbInfobox2.getHeight());
        g2 = g.create(5, 225 + xbInfobox.getHeight() + xbInfobox1.getHeight() + xbInfobox2.getHeight(), xbInfobox3.getWidth(), xbInfobox3.getHeight());
        this.xbInfobox3.paint(g2);
        g2.dispose();
        flag = false;
    }


    public void XB(final XuanBao xuanBao, XuanBao xuanBao1) {
        flag = true;
        y3 = 28;
        int y4 = 0;
        this.xuanBao = xuanBao;
        this.extXuanBao = xuanBao1;
//        this.petbox.removemsg();
//        this.box.removemsg();
        final Skill skill = UserMessUntil.getskill1(xuanBao.getName());
        XuanBaoSkill xuanBaoSkill = xuanBaoSkillMap.get(Integer.parseInt(skill.getSkillid()));
        xuanBao.setSkill_1(xuanBao.getSkill1());

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#c5C462C【消耗】" + new BigDecimal(skill.getDielectric()).intValue() + "玄元");
        stringBuffer.append("#r【冷却回合】" + new BigDecimal(skill.getValue()).intValue() + "#r");
        if (xuanBao.getSkill1() != null) {
            String skill_1 = xuanBao.getSkill_1();
            skill_1 = initSkillInfo(skill_1, skill);
            stringBuffer.append(skill_1);
        }

        this.xbInfobox = new RichLabel(stringBuffer.toString(), new Font("宋体", 0, 13), 310);
        //玄印
        stringBuffer = new StringBuffer();
        String rgb = xuanBao.getRgb();
        rgb = rgb.replace("G", "#G绿");
        rgb = rgb.replace("B", "#B蓝");
        rgb = rgb.replace("R", "#R红");
        rgb = rgb.replace("Y", "#Y黄");
        String[] v = rgb.split("\\|");
        for (int i = 0; i < v.length; i++) {
            String s = v[i];
            String[] v1 = s.split("=");
            stringBuffer.append("#c5C462C#x82#x82玄印槽" + i + "  可镶嵌");
            for (int i1 = 0; i1 < v1.length; i1++) {
                if (i1 < v1.length - 1) {
                    if ("黄".equals(v1[i1])) {
                        stringBuffer.append("#Y");
                    } else if ("绿".equals(v1[i1])) {
                        stringBuffer.append("#G");
                    } else if ("蓝".equals(v1[i1])) {
                        stringBuffer.append("#B");
                    } else if ("红".equals(v1[i1])) {
                        stringBuffer.append("#R");
                    }
                    stringBuffer.append(v1[i1] + "#c5C462C、");
                } else {
                    if ("黄".equals(v1[i1])) {
                        stringBuffer.append("#Y");
                    } else if ("绿".equals(v1[i1])) {
                        stringBuffer.append("#G");
                    } else if ("蓝".equals(v1[i1])) {
                        stringBuffer.append("#B");
                    } else if ("红".equals(v1[i1])) {
                        stringBuffer.append("#R");
                    }
                    stringBuffer.append(v1[i1]);
                }
            }
            stringBuffer.append("#c5C462C玄印#r");

        }
        this.xbInfobox1 = new RichLabel(stringBuffer.toString(), new Font("宋体", 0, 13), 310);
        skill2 = xuanBao.getSkill2();
        skill2Colors = getColorString(skill2);
        skill2 = initSkillInfo(skill2, skill);
        skill3 = xuanBao.getSkill3();
        skill3Colors = getColorString(skill3);
        skill3 = initSkillInfo(skill3, skill);
        skill4 = xuanBao.getSkill4();
        skill4Colors = getColorString(skill4);
        skill4 = initSkillInfo(skill4, skill);
        //玄印技能
        stringBuffer = new StringBuffer("#c5C462C");
        if (StringUtils.isNotBlank(skill2)) {
            stringBuffer.append(skill2 + "#r  #r");
            skill2ColorsArr = skill2Colors.split(",");
        }
        if (StringUtils.isNotBlank(skill3)) {
            stringBuffer.append(skill3 + "#r");
            skill3ColorsArr = skill3Colors.split(",");
            if (StringUtils.isNotBlank(skill4)) {
                stringBuffer.append("  #r");
            }
        }
        if (StringUtils.isNotBlank(skill4)) {
            stringBuffer.append(skill4);
            skill4ColorsArr = skill4Colors.split(",");
        }

        this.xbInfobox2 = new RichLabel(stringBuffer.toString(), new Font("宋体", 0, 13), 240);
        stringBuffer = new StringBuffer("#c5C462C");
        stringBuffer.append(xuanBao.getLs());
        this.xbInfobox3 = new RichLabel(stringBuffer.toString(), new Font("宋体", 0, 13), 310);
        this.boxw = 320;// 宽
        this.boxh = 250;
        this.setBounds(0, 0, this.boxw, this.boxh + xbInfobox.getHeight() + xbInfobox1.getHeight() + xbInfobox2.getHeight() + xbInfobox3.getHeight());
//        final Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));

//        this.displaymsg();

    }

    private String getColorString(String skillMsg) {
        if (StringUtils.isBlank(skillMsg)) return "";
//        int start = skillMsg.indexOf("[");

        String[] split = skillMsg.split("\\[");
        return split[1].split("]")[0];

//        int end = skillMsg.indexOf("]");
//        return skillMsg.substring(start + 1, end);
    }

    private String initSkillInfo(String skill_1, Skill skill) {
        if (skill_1.contains("{技能1}"))
            skill_1 = skill_1.replace("{技能1}", skill.getValue());
        if (skill_1.contains("{技能2}"))
            skill_1 = skill_1.replace("{技能2}", skill.getValue1());
        if (skill_1.contains("{技能3}"))
            skill_1 = skill_1.replace("{技能3}", skill.getValue2());
        if (skill_1.contains("{技能4}") && skill.getValue3() != null)
            skill_1 = skill_1.replace("{技能4}", skill.getValue3());
        return skill_1;
    }


}
