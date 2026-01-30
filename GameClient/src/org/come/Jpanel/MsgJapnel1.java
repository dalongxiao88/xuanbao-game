package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe1;
import org.come.model.Lingbao;
import org.come.until.UserData;
import com.tool.role.RoleLingFa;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.tool.role.RoleData;
import java.awt.Point;
import java.awt.Dimension;
import org.come.Frame.ZhuFrame;
import org.come.until.ScrenceUntil;
import com.tool.tcpimg.UIUtils;
import java.math.BigDecimal;
import come.tool.FightingData.FBUtil;
import javax.swing.ImageIcon;
import org.come.until.UserMessUntil;
import org.come.until.Arith;
import org.come.bean.Skill;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import org.come.until.CutButtonImage;
import org.come.bean.ImgZoom;
import com.tool.tcpimg.PetSkillBox;
import com.tool.tcpimg.WlinBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MsgJapnel1 extends JPanel
{
    private JLabel goodsImg;
    private WlinBox box;
    private PetSkillBox petbox;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private long time;
    private String vs;
    private ImgZoom imgZoom;
    
    public MsgJapnel1() {
        this.time = 0L;
        this.imgZoom = CutButtonImage.cuts("resource/jiuUI/ss853.png", 14, 7, true);
        this.box = new WlinBox();
        this.petbox = new PetSkillBox();
        this.box.setAlpha(0.6f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        (this.goodsImg = new JLabel()).setBounds(6, 10, 60, 60);
        this.add(this.goodsImg);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create(0, 0, this.boxw, this.boxh);
        this.box.paint(g2);
        this.petbox.paint(g2);
        g2.dispose();
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = new Font("宋体", 0, 17);
        g2d.setFont(font);
        g2d.setColor(Color.yellow);
        g2d.drawString(this.vs, 68, 22);
        g2d.drawString(this.vs, 68, 22);
        g2d.drawString(this.vs, 68, 22);
    }
    
    public static String skillMsgchange(String remark, Skill skillAll) {
        int id = Integer.parseInt(skillAll.getSkillid());
        if (id == 9412 || id == 9811) {
            remark = remark.replace("{公式三十二}", String.valueOf(Arith.add(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), (double)Double.valueOf(skillAll.getValue())), 20.0)));
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9189) {
            remark = remark.replace("{公式三十一}", String.valueOf(Arith.sub(Arith.add((double)Double.valueOf(skillAll.getValue()), 50.0), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9382) {
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9372) {
            remark = remark.replace("{公式二十九}", String.valueOf(Arith.sub(100.0, (Integer.parseInt(skillAll.getSkilllevel()) <= 2) ? Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), 10.0)) : Arith.add(10.0, Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 5.0)))));
        }
        else if (id == 9370) {
            remark = remark.replace("{公式二十八}", String.valueOf(Arith.add(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), 3000.0), (double)Double.valueOf(skillAll.getSkilllevel())), Arith.mul((double)Double.valueOf(skillAll.getValue()), 1500.0))));
            remark = remark.replace("{公式二十七}", String.valueOf(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 3000.0))));
        }
        else if (id == 9365) {
            remark = remark.replace("{公式二十六}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel()))))));
        }
        else if (id == 9352) {
            remark = remark.replace("{公式二十五}", String.valueOf(Arith.add(25.0, Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 2.0), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel()))))));
            remark = remark.replace("{公式八}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9328) {
            remark = remark.replace("{公式二十四}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), Arith.mul(3.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
            remark = remark.replace("{公式二十三}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9326) {
            remark = remark.replace("{公式二十二}", String.valueOf(Arith.add(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getValue()), 2.0), 5.0), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9307) {
            remark = remark.replace("{公式二十一}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 5.0), Arith.mul(2.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9369) {
            remark = remark.replace("{公式三十三}", String.valueOf(Arith.mul(2.0, (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9271) {
            remark = remark.replace("{公式十九}", String.valueOf(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), 1000.0))));
        }
        else if (id == 9269) {
            remark = remark.replace("{公式十八}", String.valueOf(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((Integer.parseInt(skillAll.getSkilllevel()) <= 4) ? ((double)Double.valueOf(skillAll.getSkilllevel())) : Arith.add((double)Double.valueOf(skillAll.getSkilllevel()), 1.0), 500.0)), 13000.0)));
            remark = remark.replace("{公式十七}", String.valueOf(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 250.0)), 10000.0)));
        }
        else if (id == 9251) {
            remark = remark.replace("{公式十六}", String.valueOf(Arith.sub(Arith.add((double)Double.valueOf(skillAll.getValue()), (Integer.parseInt(skillAll.getSkilllevel()) > 3) ? Arith.sub((double)Double.valueOf(skillAll.getSkilllevel()), 3.0) : 0.0), 2.0)));
            remark = remark.replace("{公式十五}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), (Integer.parseInt(skillAll.getSkilllevel()) <= 3) ? ((double)Double.valueOf(skillAll.getSkilllevel())) : 3.0)));
        }
        else if (id == 9250) {
            remark = remark.replace("{公式十四}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 6.0)));
        }
        else if (id == 9244) {
            remark = remark.replace("{公式十三}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 800.0)));
        }
        else if (id == 9241) {
            remark = remark.replace("{公式十二}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.div((double)Double.valueOf(skillAll.getSkilllevel()), 4000.0)), 6.0)));
        }
        else if (id == 9231) {
            remark = remark.replace("{公式十一}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 10.0)));
        }
        else if (id == 9227 || id == 9287 || id == 9711) {
            remark = remark.replace("{公式九}", String.valueOf(Arith.sub((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9182) {
            remark = remark.replace("{公式八}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9162 || id == 9265 || id == 9266) {
            remark = remark.replace("{公式七}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9152 || id == 9188) {
            remark = remark.replace("{公式六}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 500.0)));
            remark = remark.replace("{公式五}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 3.0)));
        }
        else if (id == 9171) {
            remark = remark.replace("{公式四}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow()))));
        }
        else if (id == 9125) {
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9508) {
            remark = remark.replace("{公式三十四}", String.valueOf(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(600.0, Arith.mul(Double.parseDouble(skillAll.getSkilllevel()), Double.parseDouble(skillAll.getValue()))))));
        }
        else if (id == 9510) {
            remark = remark.replace("{公式三十五}", String.valueOf(Arith.add(60.0, Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(skillAll.getSkilllevel()), 5.0)))));
        }
        else if (id == 9511) {
            remark = remark.replace("{公式三十六}", String.valueOf(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.add(Double.parseDouble(skillAll.getSkilllevel()), 1.0))));
            remark = remark.replace("{公式三十七}", String.valueOf(Arith.mul(10000.0, Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.add(Double.parseDouble(skillAll.getSkilllevel()), 1.0)))));
        }
        else if (id == 9612) {
            remark = remark.replace("{公式三十八}", String.valueOf((Double.parseDouble(skillAll.getSkilllevel()) <= 2.0) ? 1 : ((Double.parseDouble(skillAll.getSkilllevel()) <= 4.0) ? 2 : 3)));
        }
        else if (id == 9191) {
            remark = remark.replace("{公式十}", String.valueOf(Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Double.parseDouble(skillAll.getSkilllevel())), Arith.add(50.0, Double.parseDouble(skillAll.getValue())))));
        }
        remark = remark.replace("{公式一}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        remark = remark.replace("{公式二}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 10.0), (double)Double.valueOf(skillAll.getSkilllevel()))));
        remark = remark.replace("{公式二十}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 2.0), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
        return remark;
    }
    
    public void smx(Skill skill, double sld, int lvl) {
        this.petbox.removemsg();
        this.box.removemsg();
        String[] iconx = skill.getSkillname().split("_");
        Skill skillx = UserMessUntil.getskill1(iconx[0]);
        ImageIcon icon = new ImageIcon("img/skill/wxs_" + skillx.getSkillid() + ".png");
        icon.setImage(icon.getImage().getScaledInstance(55, 55, 1));
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        double mv = Double.parseDouble(skill.getDielectric());
        if (id >= 22000 && id <= 22035) {
            this.goodsImg.setIcon(icon);
        }
        if (id >= 1001 && id <= 1100) {
            this.goodsImg.setIcon(icon);
            int level = Integer.parseInt(skill.getSkilllevel());
            double sv = Double.parseDouble(skill.getGrow());
            double value = Double.parseDouble(skill.getValue());
            String type = (id <= 1005) ? "混乱" : ((id <= 1010) ? "封印" : ((id <= 1015) ? "昏睡" : ((id <= 1020) ? "中毒" : ((id <= 1025) ? "震慑" : ((id <= 1030) ? "力量" : ((id <= 1035) ? "抗性" : ((id <= 1040) ? "加速" : ((id <= 1045) ? "风" : ((id <= 1050) ? "雷" : ((id <= 1055) ? "水" : ((id <= 1060) ? "火" : ((id <= 1065) ? "鬼火" : ((id <= 1070) ? "三尸虫" : ((id <= 1075) ? "遗忘" : ((id <= 1080) ? "smmh" : ((id <= 1085) ? "霹雳" : ((id <= 1090) ? "沧波" : ((id <= 1095) ? "甘霖" : "扶摇"))))))))))))))))));
            msg = msg.replace("|个数|", FBUtil.geshu(level, (int)sld, type) + "");
            if (id <= 1015 || (id >= 1071 && id <= 1075)) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue());
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "7");
            }
            else if (id <= 1020) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0);
                msg = msg.replace("|伤害|", (level > 3) ? "15" : ((level > 1) ? "12.5" : "10"));
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "3");
            }
            else if (id <= 1040 || (id >= 1076 && id <= 1080)) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0);
                msg = msg.replace("|回合|", "7");
                msg = msg.replace("|伤害|", skillhitrate.setScale(2, 4).toString());
            }
            else if (id <= 1065 || id >= 1081) {
                BigDecimal skillhitrate = new BigDecimal((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)lvl);
                msg = msg.replace("|伤害|", skillhitrate.intValue() + "");
            }
            else if (id <= 1070) {
                msg = msg.replace("|伤害|", (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) + "");
                msg = msg.replace("|几率|", (int)(value * 100.0 + (double)(int)(sld / 250.0)) + "");
            }
            msg = msg.replace("|蓝|", (int)(mv * (sld / 25000.0 + 1.0)) + "");
            msg = msg.replace("|熟练|", "1");
        }
        if (id >= 1500 && id <= 1889) {
            this.goodsImg.setIcon(icon);
            if (id == 1600) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 提高敌方全体金五行50";
            }
            if (id == 1601) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 提高敌方全体木五行50";
            }
            if (id == 1602) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 提高敌方全体土五行50";
            }
            if (id == 1603) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 提高敌方全体水五行50";
            }
            if (id == 1604) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 提高敌方全体火五行50";
            }
            if (id == 1605) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 消除敌方全部单位的五行";
            }
            if (id == 1606) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 给己方所有单位回复#r#W 60%的气血与60%的法力#r#R 10回合以后才可使用";
            }
            if (id == 1607) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 自己与己方任意单位同时隐身";
            }
            if (id == 1608) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 群体解除本方全体被控制状态#r#R 整场战斗只可使用1次。";
            }
            if (id == 1609) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 消耗自身50%气血和20%法力上限#r#W 使伤害提至2.5倍。";
            }
            if (id == 1611) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 给己方三个单位回复50%的气血及法力#r#W 整场战斗只可使用1次。";
            }
            if (id == 1612) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 解除本方3个单位的被控制状态#r#W 整场战斗只可使用1次。";
            }
            if (id == 1500) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 通过牺牲自己的HP给对手造成伤害";
            }
            if (id == 1501) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 通过牺牲自己的HP给对手造成伤害";
            }
            if (id == 1502) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 通过牺牲自己的MP给对手造成伤害";
            }
            if (id == 1503) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 通过牺牲自己的MP给对手造成伤害";
            }
            if (id == 1839) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 牺牲自己，有几率使一个目标只能进行物理攻击，持续2回合(主动技能，仅限玩家之间PK时使用)。";
            }
            if (id == 1868) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 指定本方任意一个召唤兽消耗50%的法量上线使处于冷却中的技能本回合就能立即使用，普通高级技能冷却10回合，终极技能冷却20回合";
            }
            if (id == 1869) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 有几率成功锁定单体目标魂魄，使其无法被任何法术，药品等复活，持续2回合，冷却10回合";
            }
            if (id == 1876) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 全方位克技能：享受无属性和克属性的效果{主动技能，消耗MP12600}有持续状态{冷却5回合}";
            }
            if (id == 1877) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 主动释放技能，指定释放己方一个单位处于隐身状态，跟   隐身药一个效果{主动技能，消耗MP6800,冷却10回合}";
            }
            if (id == 1889) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 全方位克技能：提高敌方全体五行50{主动技能，消耗MP12600}有持续状态{冷却5回合}";
            }
        }
        if ((id >= 1200 && id <= 1283) || (id==1279||id==1287||id==1288||id==1289)||id == 450050) {
            long qm = (long)UserMessUntil.getChosePetMes().getFriendliness();
            double value2 = Double.parseDouble(skill.getValue());
            double sv2 = Double.parseDouble(skill.getGrow());
            this.goodsImg.setIcon(icon);
            if (id == 1200) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 给自方所有在场单位施加庇护状态#r#W（死亡和封印状态除外） #r#W 白泽可为此状态成员承担部分伤害 #r#W 且自身所受伤害弱化";
            }
            if (id == 1204) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】3#r#Y【持续回合】2#r#Y【消耗MP】" + (int)mv + "#r#W 降低敌方仙法抗性减20% #r#W 人法抗性减8%。";
            }
            if (id == 1202) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】3#r#Y【持续回合】2#r#Y 【消耗MP】" + (int)mv + "#r#W  减少目标抗性#r#W 【抗震慑】-10%#r#W 【抗遗忘】-8%#r#W 【抗鬼火】-20%#r#W 【抗三尸虫】-1000";
            }
            if (id == 1203) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【持续回合】3#r#Y 【消耗MP】" + (int)mv + "#r#W  减少目标抗性#r#W 【抗震慑】-15%#r#W 【抗遗忘】-12%#r#W 【抗鬼火】-25%#r#W 【抗三尸虫】-2000";
            }
            if (id == 1205) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【持续回合】3#r#Y【消耗MP】" + (int)mv + "#r#W 降低敌方仙法抗性减25%#r#W 人法抗性减12%。";
            }
            if (id == 1215) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【冷却回合】5#r#Y【消耗MP】" + (int)mv + "#r#W 该召唤兽与战斗中任意目标交换状态。#r#W (盘，牛，速，冰，混)#r#W (睡，忘，毒，魅, 惑)";
            }
            if (id == 1234) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】3#r#Y【只针对PVp有用】#r#Y【消耗MP】" + (int)mv + "#r#W 敌方释放师门技能时#r#W 有几率将师门法术返还给敌方#r#W 亲密越高返还的几率越大#r#W 该技能在场只能生效一次";
            }
            if (id == 1246) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 增加附法内丹伤害, #r#W有一定几率出现法术狂暴效果!";
            }
            if (id == 1247) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【学习要求】根骨>=500#r#Y【消耗MP】" + (int)mv + "#r#Y【针对目标数】1#r#W 增加内丹反震效果#r#W 免疫仙法鬼火伤害!#r#W 血量低于30%有一定几率护盾加身!";
            }
            if (id == 1248) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【学习要求】力量>=500#r#Y【消耗MP】" + (int)mv + "#r#W 增加30%的攻击力#r#W 被攻击方血量低于30%有几率一击毙命!";
            }
            if (id == 1249) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【持续回合】2#r#Y【学习要求】敏捷>=500#r#Y【消耗MP】" + (int)mv + "#r#W 回合开始时有几率偷取对方#r#W 第一个出手的技能为己方所用!#r#W 且自己进入隐身状态!";
            }
            if (id == 1250) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 牺牲自己的行动3个回合#r#W (不能使用药品,不能攻击,不能使用法术)#r#W 把己方随机3个单位所受的伤害转嫁到对方身上 #r#R (前十回合不能使用,每场战斗只能生效一次)";
            }
            if (id == 1251) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 释放一个强力的隐身技能!#r#W 清除所有的负面状态#r#W 己方所有成员进入不能被选定状态! #r#R (前十回合不能使用,每场战斗只能生效一次)";
            }
            if (id == 1252) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 物理攻击敌方5个目标";
            }
            if (id == 1253) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 物理攻击一个目标#r#W 有几率消耗对手的MP值";
            }
            if (id == 1254) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【消耗MP】" + (int)mv + "#r#W 法术攻击敌方5个目标";
            }
            if (id == 1255) {
                msg = "#r#W【消耗MP】#W30%#r#G增加连击几率#r#G增加反震几率#r#G增加内丹附法几率";
            }
            if (id == 1258) {
                msg = "#r#W【消耗MP】" + (int)mv + "#r#W当兰亭处于隐身状态时#r#W可将自己的隐身状态转移给主人。";
            }
            if (id == 1264) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【冷却回合】2#r#Y【消耗MP】" + (int)mv + "#r#W一声：【剑来】召唤无数利剑攻击敌人，有20%几率随机目标产生双倍伤害，施法者法力值越高伤害越高。由于太过残暴，施法者遭天地约束，攻击目标会在1-10之间波动，冷却时间2回合。施法结束后有百分之30的几率进入隐身状态。#R（只能在PVE使用）";
            }
            if (id == 1280) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "#r#W 增加己方2个目标狂暴、命中、致命率20%，持续3回合 。(主动技能)";
            }
            if (id == 1279) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "给己方全部单位增加15%伤害减免，且伤害抗性低于自身的单位，将获得相差数值20%的伤害抗性，倒地、隐身、冰封单位亦生效，冷却时间5回合。(此效果不会叠加，与泽披天下伤害减免互斥)。";
            }
            if (id == 1287) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "#r#W 青白娘子天生内蕴双灵，进入战场时以白娘子形态示人。使用此技能后可回复自身25%的气血，并从当前白娘子/小青形态切换至另一形态。";
            }
            if (id == 1288) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "#r#W 白娘子牺牲自身当前气血的20%，对己方一个单位进行气血回复，回复量为所牺牲气血的一定比例。此技能需处于白娘子形态下方可使用。";
            }
            if (id == 1289) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "#r#W 小青牺牲自身当前气血的20%，对敌方一个单位施放特殊的鹤顶红粉，造成的毒法伤害为基础伤害并额外附加一定比例的所牺牲气血值，毒法持续两回合。此技能需处于小青形态下方可使用。";
            }
            if (id == 1281) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】2#r#Y【消耗MP】" + (int)mv + "#r#W 增加己方2个目标狂暴、命中、致命率20%，持续3回合 。(主动技能)";
            }
            if (id == 1282) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】2#r#Y【消耗MP】" + (int)mv + "#r#W 增加己方2个目标狂暴、命中、致命率20%，持续3回合 。(主动技能)";
            }
            if (id == 1214) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】1#r#Y【消耗MP】" + (int)mv + "#r#W 增加己方1个目标狂暴、命中、致命率20%，持续3回合 。(主动技能)";
            }
            if (id == 450050) {
                msg = "#cFF8C00 " + skill.getSkillname() + "#r#Y【目标数】5#r#Y【消耗MP】" + (int)mv + "#r#W 饕餮随机吞噬地方5个单位，直接杀死！ 。(主动技能)";
            }
        }
        msg = skillFMMsgchange(msg, getFmsld(skill));
        this.vs = skill.getSkillname();
        this.box.addText(" ", 320, new Font("宋体", 1, 20));
        this.box.addText(msg, 320, UIUtils.TEXT_FONT2);
        this.boxw = 320;
        this.boxh = ScrenceUntil.Screen_y;
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 25;
        this.boxy = (int)goodx.getY() + 400;
        this.displaymsg();
    }
    
    private static int getFmsld(Skill skill) {
        int skillId = Integer.parseInt(skill.getSkillid());
        if (skillId % 2 == 0) {
            return RoleData.getRoleData().getLoginResult().getScoretype("法门1").intValue();
        }
        return RoleData.getRoleData().getLoginResult().getScoretype("法门2").intValue();
    }
    
    public static String skillFMMsgchange(String remark, int fmsld) {
        Matcher mat = Pattern.compile("<([^>]*)>").matcher(remark);
        while (mat.find()) {
            String str = mat.group();
            String replaceStr = str.replaceAll("<", "").replaceAll(">", "");
            if (str.indexOf("+") > -1) {
                String[] num = replaceStr.split("\\+");
                if (num.length == 2) {
                    String[] vals = num[1].split(",");
                    if (vals.length == 1) {
                        double s = Double.parseDouble(num[0]);
                        double e = Double.parseDouble(num[1]);
                        String suffix = remark.substring(remark.indexOf(str) + str.length(), remark.indexOf(str) + str.length() + 1);
                        int n = -1;
                        switch (suffix.hashCode()) {
                            case 37: {
                                if (suffix.equals("%")) {
                                    n = 0;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        switch (n) {
                            case 0: {
                                replaceStr = Arith.round(Arith.add(s, Arith.mul(e, (double)fmsld)), 2, 1) + "";
                                break;
                            }
                            default: {
                                replaceStr = (int)Arith.round(Arith.add(s, Arith.mul(e, (double)fmsld)), 0, 1) + "";
                                break;
                            }
                        }
                    }
                    else {
                        int sum = Integer.parseInt(num[0]);
                        for (int j = 0; j < vals.length && Integer.parseInt(vals[j]) <= fmsld; ++j) {
                            ++sum;
                        }
                        replaceStr = sum + "";
                    }
                }
            }
            else if (str.indexOf("-") > -1) {
                String[] num = str.split("-");
                if (num.length == 2) {
                    double s2 = Double.parseDouble(num[0]);
                    double e2 = Double.parseDouble(num[1]);
                    replaceStr = Arith.round(Arith.sub(s2, Arith.mul(e2, (double)fmsld)), 2, 1) + "";
                }
            }
            remark = remark.replace(str, replaceStr);
        }
        return remark;
    }
    
    public void SM(Skill skill, double sld, int lvl) {
        this.petbox.removemsg();
        this.box.removemsg();
        String[] iconx = skill.getSkillname().split("_");
        Skill skillx = UserMessUntil.getskill1(iconx[0]);
        ImageIcon icon = new ImageIcon("img/fighting-skill/" + skillx.getSkillid() + ".png");
        icon.setImage(icon.getImage().getScaledInstance(60, 60, 1));
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id >= 1001 && id <= 1100) {
            int level = Integer.parseInt(skill.getSkilllevel());
            double sv = Double.parseDouble(skill.getGrow());
            double mv = Double.parseDouble(skill.getDielectric());
            double value = Double.parseDouble(skill.getValue());
            this.goodsImg.setIcon(icon);
            String type = (id <= 1005) ? "混乱" : ((id <= 1010) ? "封印" : ((id <= 1015) ? "昏睡" : ((id <= 1020) ? "中毒" : ((id <= 1025) ? "震慑" : ((id <= 1030) ? "力量" : ((id <= 1035) ? "抗性" : ((id <= 1040) ? "加速" : ((id <= 1045) ? "风" : ((id <= 1050) ? "雷" : ((id <= 1055) ? "水" : ((id <= 1060) ? "火" : ((id <= 1065) ? "鬼火" : ((id <= 1070) ? "三尸虫" : ((id <= 1075) ? "遗忘" : ((id <= 1080) ? "smmh" : ((id <= 1085) ? "霹雳" : ((id <= 1090) ? "沧波" : ((id <= 1095) ? "甘霖" : "扶摇"))))))))))))))))));
            msg = msg.replace("|个数|", FBUtil.geshu(level, (int)sld, type) + "");
            if (id <= 1015 || (id >= 1071 && id <= 1075)) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue());
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "7");
            }
            else if (id <= 1020) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0);
                msg = msg.replace("|伤害|", (level > 3) ? "15" : ((level > 1) ? "12.5" : "10"));
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "3");
            }
            else if (id <= 1040 || (id >= 1076 && id <= 1080)) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0);
                msg = msg.replace("|回合|", "7");
                msg = msg.replace("|伤害|", skillhitrate.setScale(2, 4).toString());
            }
            else if (id <= 1065 || id >= 1081) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * Math.pow(sld, 0.4) * 1.75 * (double)lvl);
                msg = msg.replace("|伤害|", skillhitrate.intValue() + "");
            }
            else if (id <= 1070) {
                msg = msg.replace("|伤害|", (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) + "");
                msg = msg.replace("|几率|", (int)(value * 100.0 + (double)(int)(sld / 250.0)) + "");
            }
            msg = msg.replace("|蓝|", (int)(mv * (sld / 25000.0 + 1.0)) + "");
            msg = msg.replace("|熟练|", (int)sld + "/" + (int)sld);
        }
        else if (id >= 5001 && id <= 5015) {
            msg = "#c8A2BE2" + skill.getSkillname() + "#r#Y【消耗怨气值】" + skill.getDielectric() + "#r#W" + msg;
            int born = ImageMixDeal.userimg.getRoleShow().getTurnAround();
            int lvl2 = AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            int bzlvl = RoleLingFa.getRoleLingFa().getFaPJ();
            Lingbao fabao = RoleLingFa.getRoleLingFa().getFabaoByName(skill.getSkillname());
            if (fabao == null) {
                return;
            }
            int qv = RoleLingFa.getQv(fabao.getBaoquality());
            int blvl = fabao.getLingbaolvl().intValue();
            int pz = FBUtil.getFBlvl(born, lvl2, bzlvl, qv, blvl);
            double grow = 0.0;
            double value2 = 0.0;
            if (skill.getGrow() != null && !skill.getGrow().equals("")) {
                grow = Double.parseDouble(skill.getGrow());
            }
            if (skill.getValue() != null && !skill.getGrow().equals("")) {
                value2 = Double.parseDouble(skill.getValue());
            }
            String v1 = UserData.xiaoshu(value2 + (double)pz * grow);
            String v2 = FBUtil.getFBcx(id, blvl) + "";
            String v3 = null;
            String v4 = FBUtil.getFBsum(id, blvl) + "";
            if (id == 4014) {
                v3 = (int)((value2 + (double)pz * grow) * 12500.0) + "";
            }
            else if (id == 4015) {
                v3 = UserData.xiaoshu((value2 + (double)pz * grow) * 2.0 / 3.0);
            }
            msg = msg.replace("{概率}", "#R" + v1 + "#W");
            msg = msg.replace("{回合}", "#G" + v2 + "#W");
            if (v3 != null) {
                msg = msg.replace("{数值}", "#Y" + v3 + "#W");
            }
            msg = msg.replace("{目标}", "#Y" + v4 + "#W");
        }
        else if (id >= 9101 && id <= 9812) {
            msg = skillMsgchange(msg, skill);
            msg = "#cFF8C00" + skill.getSkillname() + "#r#Y【消耗怒气值】" + skill.getDielectric() + "#r#W" + msg;
        }
        else {
            return;
        }
        this.vs = skill.getSkillname();
        this.box.addText(" ", 320, new Font("宋体", 0, 17));
        this.box.addText(msg, 320, new Font("宋体", 0, 14));
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 10;
        this.boxy = (int)goodx.getY() + 80;
        this.displaymsg();
    }
    
    public static String StringReplace(String vs) {
        Pattern p = Pattern.compile(">(.*?)<");
        Matcher m = p.matcher(vs);
        Pattern pend = Pattern.compile("'#(.*?)'");
        Matcher mend = pend.matcher(vs);
        StringBuffer sb1 = new StringBuffer();
        while (mend.find()) {
            String s = mend.group();
            if (!sb1.toString().equals("")) {
                sb1.append("|" + s.split("'#")[1].split("'")[0]);
            }
            else {
                sb1.append(s.split("'#")[1].split("'")[0]);
            }
        }
        StringBuffer sb2 = new StringBuffer();
        while (m.find()) {
            String s2 = m.group();
            if (!s2.equals("><")) {
                if (!sb2.toString().equals("")) {
                    sb2.append("|" + s2.split(">")[1].split("<")[0]);
                }
                else {
                    sb2.append(s2.split(">")[1].split("<")[0]);
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        String[] v = sb1.toString().split("\\|");
        String[] v2 = sb2.toString().split("\\|");
        for (int i = 0; i < v.length && i < v2.length; ++i) {
            if (!buffer.toString().equals("")) {
                buffer.append("|" + v[i] + "=" + v2[i]);
            }
            else {
                buffer.append(v[i] + "=" + v2[i]);
            }
        }
        return buffer.toString();
    }
    
    public void displaymsg() {
        MsgJframe1.getJframe1().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(603);
    }
    
    public WlinBox getBox() {
        return this.box;
    }
    
    public void setBox(WlinBox box) {
        this.box = box;
    }
    
    public JLabel getGoodsImg() {
        return this.goodsImg;
    }
    
    public void setGoodsImg(JLabel goodsImg) {
        this.goodsImg = goodsImg;
    }
}
