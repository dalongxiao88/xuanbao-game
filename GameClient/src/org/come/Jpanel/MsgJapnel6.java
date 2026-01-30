package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcpimg.ChatBox;
import com.tool.tcpimg.UIUtils;
import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.MsgJframe4;
import org.come.Frame.MsgJframe6;
import org.come.Frame.ZhuFrame;
import org.come.bean.LoginResult;
import org.come.entity.UserInfo;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tool.role.RoleProperty.xianMap;
import static org.come.Jpanel.MyRenderera.df;

public class MsgJapnel6 extends JPanel {
    private ChatBox box;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private long time;

    public MsgJapnel6() {
        this.time = 0L;
        (this.box = new ChatBox()).setAlpha(0.6f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        ChatBox.imgZoomSx = CutButtonImage.cuts("inkImg/background/kxmsg.png", 6, 6, true);
    }

    @Override
    protected void paintComponent(final Graphics g) {//抗性展示
        super.paintComponent(g);
        final Graphics g2 = g.create(0, 0, this.boxw, this.boxh);
        this.box.paintSSS11(g2);
        g2.dispose();
    }

    public static double raceMax(){
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int grade =(int) ImageMixDeal.userimg.getRoleShow().getGrade();
        int nowlvl = 0;
        if (grade <= 102) {
            nowlvl =grade;
        }
        else if (grade > 102 && grade <= 210) {
            nowlvl =(grade - 102 + 14);
        }
        else if (grade > 210 && grade <= 338) {
            nowlvl =(grade - 210 + 14);
        }
        else if (grade > 338 && grade <= 459) {
            nowlvl = (grade - 338 + 59);
        }
        else if (grade > 459) {
            nowlvl =(grade - 459 + 139);
        }
        int raceid=loginResult.getSpecies_id().intValue();
            if (raceid > 24000) {
                return ((double)nowlvl)/20+5;
            } else if (raceid > 23000) {
                return 0;
            } else if (raceid > 22000) {
                return 0;
            } else if (raceid > 21000) {
                return ((double)nowlvl)/20+5;
            } else if (raceid > 20000) {
                return 0;
            } else {
                return 0;
            }
    }
    public void xy(String str, Integer type) {
        this.box.removemsg();
        this.box.addText(" #r", 10, new Font("宋体", Font.PLAIN, 3));
        String raceSting = Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id());

//        AllAlchemyMaxBean allAlchemyMaxBean = UserMessUntil.getAllAlchemyMaxBean();
//        ConcurrentHashMap<String, Map<String, Double>> alchemyMax = allAlchemyMaxBean.getAllAlchemyMax();
        Double max = -1d;
        String[] v = str.split(":");
        if (xianMap != null) {

            max = xianMap.get(v[0]);
            if (max != null)
                if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                    max = Double.valueOf(df.format(max));
                } else {
                    try{
                        max = Double.valueOf(df.format(Double.valueOf(v[1].split("%")[0])));
                    } catch (NumberFormatException e) {
                        max = Double.valueOf(df.format(max));
                    }
                }

            if (max == null) {
                max = -1d;
            }
        }
        if (type == 1) {

            StringBuffer sb = new StringBuffer();
            if (v[0].equals("抗雷") || v[0].equals("抗风") || v[0].equals("抗水") || v[0].equals("抗火")||
                    v[0].equals("抗雷法") || v[0].equals("抗风法") || v[0].equals("抗水法") || v[0].equals("抗火法")) {
                sb.append("能抵" + v[0] + "系法术伤害的一定百分比");
                this.box.addText(sb.toString(), 220, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 220, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("抗鬼火")) {
                sb.append("能抵" + v[0] + "系法术伤害的一定百分比");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("混") || v[0].contains("睡") || v[0].contains("封印") || v[0].contains("中毒") || v[0].contains("遗忘")) {
                sb.append("降低中" + v[0].substring(1) + "法术的几率");
                this.box.addText(sb.toString(), 157, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 157, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("三尸")) {
                sb.append("能抵抗" + v[0].substring(1) + "伤害的一定值");
                this.box.addText(sb.toString(), 170, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 170, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("抗灵宝伤害")) {
                sb.append("抵" + v[0].substring(1) + "的百分比");
                this.box.addText(sb.toString(), 250, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 250, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("抗震慑")) {
                sb.append("能抵" + v[0].substring(1) + "法术伤害的一定百分比");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].equals("抗震慑魔法")) {
                sb.append("能抵" + v[0].substring(1) + "法术伤害的一定百分比");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            }


        } else if (type == 3) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("物理吸收率")) {
                sb.append("能抵" + v[0] + "伤害的一定百分比");
                this.box.addText(sb.toString(), 193, UIUtils.TEXT_FONT2);
                extracted(max, v, "物理吸收率");
//                this.box.addText("距离物理吸收率上限：" + v[1], 193, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 193, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("躲闪率")) {
                sb.append("躲避物理攻击的几率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                extracted(max, v, "躲闪率");
//                this.box.addText("距离躲闪率上限：" + v[1], 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("命中率")) {
                sb.append("增加物理攻击命中的几率");
                this.box.addText(sb.toString(), 170, UIUtils.TEXT_FONT2);
                this.box.addText("基础命中率："+raceMax(), 170, UIUtils.TEXT_FONT2);
                extracted(max, v, "命中率");
//                this.box.addText("距离命中率上限：" + v[1], 170, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 170, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("狂暴几率")) {
                sb.append("增加物理攻击狂暴的几率");
                this.box.addText(sb.toString(), 170, UIUtils.TEXT_FONT2);
                this.box.addText("基础狂暴率：80.0", 170, UIUtils.TEXT_FONT2);
                extracted(max, v, "狂暴率");
//                this.box.addText("距离命中率上限：" + v[1], 170, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 170, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("连击率")) {
                sb.append("物理攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 208, UIUtils.TEXT_FONT2);
                extracted(max, v, "连击率");
//                this.box.addText("距离连击率上限：" + v[1], 208, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 208, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反击率")) {
                sb.append("被物理攻击时出现反击行为的几率");
                this.box.addText(sb.toString(), 219, UIUtils.TEXT_FONT2);
                extracted(max, v, "反击率");
//                this.box.addText("距离反击率上限：" + v[1], 219, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 219, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("致命几率")) {
                sb.append("物理攻击时造成致命伤害的几率");
                this.box.addText(sb.toString(), 208, UIUtils.TEXT_FONT2);
                extracted(max, v, "致命几率");
//                this.box.addText("距离致命几率上限：" + v[1], 208, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 208, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("连击次数")) {
                sb.append("出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 181, UIUtils.TEXT_FONT2);
                extracted(max, v, "连击次数");
                this.box.addText(" #r", 181, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反击次数")) {
                sb.append("每回合最多进行反击的次数");
                this.box.addText(sb.toString(), 180, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 180, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("忽视防御几率")) {
                sb.append("物理攻击时忽视对手物理抗性以及防御力的几率");
                this.box.addText(sb.toString(), 295, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 295, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("忽视防御程度")) {
                sb.append("忽视对方的物理抗性以及防御力的程度");
                this.box.addText(sb.toString(), 247, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 247, new Font("宋体", Font.PLAIN, 1));}
//            } else if (v[0].equals("附火攻击")) {
//                sb.append("攻击时附带火法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附风攻击")) {
//                sb.append("攻击时附带风法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附雷攻击")) {
//                sb.append("攻击时附带雷法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附水攻击")) {
//                sb.append("攻击时附带水法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附封攻击")) {
//                sb.append("攻击时附带封印法术的几率");
//                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附混攻击")) {
//                sb.append("攻击时附带混乱法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附毒攻击")) {
//                sb.append("攻击时附带中毒法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附震慑攻击")) {
//                sb.append("攻击时附带震慑法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].equals("附三尸攻击")) {
//                sb.append("攻击时附带三尸法术的几率");
//                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
//            } else if (v[0].contains("无属性伤害")) {
//                sb.append("无属性对目标造成法术和物理伤害时，受到百分比加成");
//                this.box.addText(sb.toString(), 343, UIUtils.TEXT_FONT2);
//                this.box.addText(" #r", 343, new Font("宋体", Font.PLAIN, 1));
//            }

        } else if (type == 4) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("强力克水")) {
                sb.append("对五行属水的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克火")) {
                sb.append("对五行属火的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克土")) {
                sb.append("对五行属土的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克金")) {
                sb.append("对五行属金的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克木")) {
                sb.append("对五行属木的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("土")) {
                sb.append("五行之一，土克水");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("木")) {
                sb.append("五行之一，木克土");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("金")) {
                sb.append("五行之一，金克木");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水")) {
                sb.append("五行之一，水克火");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火")) {
                sb.append("五行之一，火克金");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            }
            else if (v[0].contains("增加强克效果")) {
                sb.append("对五行（金、木、水、火、土）的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 478, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 478, new Font("宋体", Font.PLAIN, 1));
            }

        } else if (type == 2) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("加强水") || v[0].equals("加强风") || v[0].equals("加强雷") || v[0].equals("加强火") || v[0].equals("加强鬼火")) {
                sb.append(v[0] + "法术的强度");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视水法") || v[0].contains("忽视风法") || v[0].contains("忽视雷法") || v[0].contains("忽视火法") || v[0].contains("忽视鬼火")) {
                sb.append("忽" + v[0].substring(1) + "法术的抗性");
                this.box.addText(sb.toString(), 140, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 140, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法伤害") || v[0].contains("风法伤害") || v[0].contains("雷法伤害") || v[0].contains("火法伤害") || v[0].contains("鬼火伤害") || v[0].contains("毒法伤害")) {
                sb.append("增加基础法术伤害的一定值（同样加强或忽视基础值越高伤害越高）");
                this.box.addText(sb.toString(), 410, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 410, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("仙法连击率")) {
                sb.append("仙族法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("仙法连击次数")) {
                sb.append("仙法出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("毒法连击率")) {
                sb.append("毒法法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("毒法连击次数")) {
                sb.append("毒法出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火连击率")) {
                sb.append("鬼火法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火连击次数")) {
                sb.append("鬼火出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强混乱") || v[0].contains("强封印") || v[0].contains("强昏睡") || v[0].contains("强中毒") || v[0].contains("强遗忘")) {
                sb.append("加" + v[0].substring(1) + "法术的命中率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视混乱") || v[0].contains("忽视封印") || v[0].contains("忽视昏睡") || v[0].contains("忽视中毒") || v[0].contains("忽视遗忘")) {
                sb.append("忽" + v[0].substring(1) + "敌方抗性的抵抗");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强沧波效果")) {
                sb.append("加强沧波法术附加的命中率、攻击加成、目标物理/法术命中率的降低程度");
                this.box.addText(sb.toString(), 450, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 450, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强扶摇效果")) {
                sb.append("加强扶摇法术附加的命中率、攻击加成、气血上限的降低程度");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强霹雳效果")) {
                sb.append("加强霹雳法术附加的攻击加成、目标物理的降低程度");
                this.box.addText(sb.toString(), 325, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 325, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强甘霖回血值")) {
                sb.append("加强甘霖法术回复的基础数值");
                this.box.addText(sb.toString(), 196, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 196, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("甘霖回血程度")) {
                sb.append("加强甘霖法术回复的程度数值");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强震慑")) {
                sb.append("加强对敌方震慑的气血、魔法百分比");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视抗震慑")) {
                sb.append("忽视对敌方抵抗震慑的抗性百分比");
                this.box.addText(sb.toString(), 222, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 222, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强加攻法术效果")) {
                sb.append("加强对人物施放的攻击加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强加防法术效果")) {
                sb.append("加强对人物施放的防御加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强加速法术效果")) {
                sb.append("加强对人物施放的速度加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强三尸虫")) {
                sb.append("加强三尸虫效果数值");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强三尸回血")) {
                sb.append("加强三尸虫效果的程度百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            }// else if (v[0].contains("增加强克效果")) {
              //  sb.append("对五行（金、木、水、火、土）的目标造成法术和物理伤害时，受到百分比加成");
              //  this.box.addText(sb.toString(), 478, UIUtils.TEXT_FONT2);
              //  this.box.addText(" #r", 478, new Font("宋体", Font.PLAIN, 1));}
             else if (v[0].contains("雷系狂暴几率")) {
                sb.append("使用仙法雷系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风系狂暴几率")) {
                sb.append("使用仙法风系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水系狂暴几率")) {
                sb.append("使用仙法水系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火系狂暴几率")) {
                sb.append("使用仙法火系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火狂暴几率")) {
                sb.append("使用鬼火攻击时提升鬼火狂暴率");
                this.box.addText(sb.toString(), 212, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 212, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("雷法狂暴程度")) {
                sb.append("使用仙法雷系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风法狂暴程度")) {
                sb.append("使用仙法风系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法狂暴程度")) {
                sb.append("使用仙法水系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火法狂暴程度")) {
                sb.append("使用仙法火系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火法暴击率")) {
                sb.append("使用仙法火系攻击时增加火法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("雷法暴击率")) {
                sb.append("使用仙法雷系攻击时增加雷法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风法暴击率")) {
                sb.append("使用仙法风系攻击时增加风法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法暴击率")) {
                sb.append("使用仙法水系攻击时增加水法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火狂暴程度")) {
                sb.append("使用鬼火攻击时忽视对方的鬼火抗性以及防御力的程度");
                this.box.addText(sb.toString(), 340, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 340, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("三尸虫狂暴几率")) {
                sb.append("使用三尸虫攻击时提升三尸虫狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].contains("三尸虫狂暴程度")) {
                sb.append("使用三尸虫攻击时忽视对方的三尸虫抗性以及防御力的程度");
                this.box.addText(sb.toString(), 360, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 360, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].contains("法术暴击率")) {
                sb.append("法术出现（灵爆）暴击的几率");
                this.box.addText(sb.toString(), 236, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 236, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].contains("法术暴击程度")) {
                sb.append("（灵爆）伤害造成暴击的加成值");
                this.box.addText(sb.toString(), 233, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 233, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].contains("法术命中率")) {
                sb.append("使用技能命中的几率值");
                this.box.addText(sb.toString(), 153, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 153, new Font("宋体", Font.PLAIN, 1));
            }

        } else if (type == 5) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("反震率")) {
                sb.append("受到物理，仙法，鬼火或三尸攻击时将伤害反馈给对手的几率");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反震程度")) {
                sb.append("受到物理，仙法，鬼火或三尸攻击时将伤害反馈给对手的程度");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("上善若水")) {
                sb.append("能抵抗浩然正气所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("美人迟暮")) {
                sb.append("能抵抗青面獠牙所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("化血成碧")) {
                sb.append("能抵抗天魔解体所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("灵犀一点")) {
                sb.append("能抵抗分光化影所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("明珠有泪")) {
                sb.append("能抵抗小楼夜哭所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动魔神附身")) {
                sb.append("被攻击时释放魔神附身的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动含情脉脉")) {
                sb.append("被攻击时释放含情脉脉的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动乾坤借速")) {
                sb.append("被攻击时释放乾坤借速的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("伤害加深")) {
                sb.append("伤害攻击时按一定百分比加深伤害数值");
                this.box.addText(sb.toString(), 246, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 246, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("伤害减免")) {
                sb.append("被攻击时受到的所有伤害按一定百分比减除");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("法术躲闪")) {
                sb.append("躲避法术攻击的几率");
                this.box.addText(sb.toString(), 146, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 146, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("对召唤兽伤害")) {
                sb.append("伤害攻击召唤兽时按一定百分比加深伤害数值");
                this.box.addText(sb.toString(), 286, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 286, new Font("宋体", Font.PLAIN, 1));
            }else if (v[0].equals("附火攻击")) {
                sb.append("攻击时附带火法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附风攻击")) {
                sb.append("攻击时附带风法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附雷攻击")) {
                sb.append("攻击时附带雷法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附水攻击")) {
                sb.append("攻击时附带水法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附封攻击")) {
                sb.append("攻击时附带封印法术的几率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附混攻击")) {
                sb.append("攻击时附带混乱法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附毒攻击")) {
                sb.append("攻击时附带中毒法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附震慑攻击")) {
                sb.append("攻击时附带震慑法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附三尸攻击")) {
                sb.append("攻击时附带三尸法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("无属性伤害")) {
                sb.append("无属性对目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 343, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 343, new Font("宋体", Font.PLAIN, 1));
            }


            }

        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        final Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int) goodx.getX() - 40;
        this.boxy = (int) goodx.getY() - this.boxh - 10;
        this.displaymsg();

    }

    private void extracted(Double max, String[] v) {
        if (max != -1d) {
            double v1 = Double.parseDouble(v[1].replace("%", ""));
            double v2 = max - v1;
//            v2 = Double.valueOf(df.format(max));
            if (v2 < 0d)
                this.box.addText("超出抗性上限：#R" + df.format(v2 * -1), 200, UIUtils.TEXT_FONT2);
            else
                this.box.addText("距离抗性上限：" + df.format(v2), 200, UIUtils.TEXT_FONT2);
        }
    }


    private void extracted(Double max, String[] v, String str) {
        if (max != -1d) {
            double v1 = Double.parseDouble(v[1].replace("%", ""));
            double v2 = max - v1;
            if (v2 < 0d)
                this.box.addText("超出" + str + "上限：#R" + df.format(v2 * -1), 200, UIUtils.TEXT_FONT2);
            else
                this.box.addText("距离" + str + "上限：" + df.format(v2), 200, UIUtils.TEXT_FONT2);
        }
    }


    public void pet(String str, Integer type) {
        this.box.removemsg();
        this.box.addText(" #r", 10, new Font("宋体", Font.PLAIN, 3));
        String raceSting = "召唤兽";

//        AllAlchemyMaxBean allAlchemyMaxBean = UserMessUntil.getAllAlchemyMaxBean();
//        ConcurrentHashMap<String, Map<String, Double>> alchemyMax = allAlchemyMaxBean.getAllAlchemyMax();
        Double max = -1d;
        String[] v = str.split(":");
//        if (alchemyMax != null) {
//            Map<String, Double> stringDoubleMap = alchemyMax.get(raceSting);
//            if (stringDoubleMap != null) {
//                max = stringDoubleMap.get(v[0]);
//            }
//        }
        if (type == 10) {

            StringBuffer sb = new StringBuffer();
            if (v[0].equals("抗雷") || v[0].equals("抗风") || v[0].equals("抗水") || v[0].equals("抗火")) {
                sb.append("能抵" + v[0] + "系法术伤害的一定百分比");
                this.box.addText(sb.toString(), 220, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 220, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("抗鬼火")) {
                sb.append("能抵" + v[0] + "系法术伤害的一定百分比");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("混") || v[0].contains("睡") || v[0].contains("封印") || v[0].contains("中毒") || v[0].contains("遗忘")) {
                sb.append("降低中" + v[0].substring(1) + "法术的几率");
                this.box.addText(sb.toString(), 157, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 157, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("三尸")) {
                sb.append("能抵抗" + v[0].substring(1) + "伤害的一定值");
                this.box.addText(sb.toString(), 170, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 170, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("抗灵宝伤害")) {
                sb.append("抵" + v[0].substring(1) + "的百分比");
                this.box.addText(sb.toString(), 250, UIUtils.TEXT_FONT2);
                extracted(max, v);
                this.box.addText(" #r", 250, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("抗震慑")) {
                sb.append("能抵" + v[0].substring(1) + "法术伤害的一定百分比");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            }


        } else if (type == 12) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("物理吸收率")) {
                sb.append("能抵" + v[0] + "伤害的一定百分比");
                this.box.addText(sb.toString(), 193, UIUtils.TEXT_FONT2);
                extracted(max, v, "物理吸收率");
//                this.box.addText("距离物理吸收率上限：" + v[1], 193, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 193, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("躲闪率")) {
                sb.append("躲避物理攻击的几率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                extracted(max, v, "躲闪率");
//                this.box.addText("距离躲闪率上限：" + v[1], 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("命中率")) {
                sb.append("增加物理攻击命中的几率");
                this.box.addText(sb.toString(), 170, UIUtils.TEXT_FONT2);
                this.box.addText("基础命中率：85.0", 170, UIUtils.TEXT_FONT2);
                extracted(max, v, "命中率");
//                this.box.addText("距离命中率上限：" + v[1], 170, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 170, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("连击率")) {
                sb.append("物理攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 208, UIUtils.TEXT_FONT2);
                extracted(max, v, "连击率");
//                this.box.addText("距离连击率上限：" + v[1], 208, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 208, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反击率")) {
                sb.append("被物理攻击时出现反击行为的几率");
                this.box.addText(sb.toString(), 219, UIUtils.TEXT_FONT2);
                extracted(max, v, "反击率");
//                this.box.addText("距离反击率上限：" + v[1], 219, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 219, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("致命率")) {
                sb.append("物理攻击时造成致命伤害的几率");
                this.box.addText(sb.toString(), 208, UIUtils.TEXT_FONT2);
                extracted(max, v, "致命几率");
//                this.box.addText("距离致命几率上限：" + v[1], 208, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 208, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("连击次数")) {
                sb.append("出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 181, UIUtils.TEXT_FONT2);
                extracted(max, v, "连击次数");
//                this.box.addText("距离连击次数上限：" + v[1], 181, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 181, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反击次数")) {
                sb.append("每回合最多进行反击的次数");
                this.box.addText(sb.toString(), 180, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 180, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("忽视防御几率")) {
                sb.append("物理攻击时忽视对手物理抗性以及防御力的几率");
                this.box.addText(sb.toString(), 295, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 295, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("忽视防御程度")) {
                sb.append("忽视对方的物理抗性以及防御力的程度");
                this.box.addText(sb.toString(), 247, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 247, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附火攻击")) {
                sb.append("攻击时附带火法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附风攻击")) {
                sb.append("攻击时附带风法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附雷攻击")) {
                sb.append("攻击时附带雷法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附水攻击")) {
                sb.append("攻击时附带水法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附封攻击")) {
                sb.append("攻击时附带封印法术的几率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附混攻击")) {
                sb.append("攻击时附带混乱法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附毒攻击")) {
                sb.append("攻击时附带中毒法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附震慑攻击")) {
                sb.append("攻击时附带震慑法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("附三尸攻击")) {
                sb.append("攻击时附带三尸法术的几率");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("无属性伤害")) {
                sb.append("无属性对目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 343, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 343, new Font("宋体", Font.PLAIN, 1));
            }

        } else if (type == 13) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("强力克水")) {
                sb.append("对五行属水的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克火")) {
                sb.append("对五行属火的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克土")) {
                sb.append("对五行属土的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克金")) {
                sb.append("对五行属金的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强力克木")) {
                sb.append("对五行属木的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 365, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 365, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("土")) {
                sb.append("五行之一，土克水");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("木")) {
                sb.append("五行之一，木克土");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("金")) {
                sb.append("五行之一，金克木");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水")) {
                sb.append("五行之一，水克火");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火")) {
                sb.append("五行之一，火克金");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            }

        } else if (type == 11) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("加强水") || v[0].equals("加强风法") || v[0].equals("强雷法") || v[0].equals("强火") || v[0].equals("强鬼火")) {
                sb.append(v[0] + "法术的强度");
                this.box.addText(sb.toString(), 130, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 130, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视抗水") || v[0].contains("忽视抗风") || v[0].contains("忽视抗雷") || v[0].contains("忽视抗火") || v[0].contains("忽视鬼火")) {
                sb.append("忽" + v[0].substring(1) + "法术的抗性");
                this.box.addText(sb.toString(), 140, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 140, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法伤害") || v[0].contains("风法伤害") || v[0].contains("雷法伤害") || v[0].contains("火法伤害") || v[0].contains("鬼火伤害") || v[0].contains("毒法伤害")) {
                sb.append("增加基础法术伤害的一定值（同样加强或忽视基础值越高伤害越高）");
                this.box.addText(sb.toString(), 410, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 410, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("仙法连击率")) {
                sb.append("仙族法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("仙法连击次数")) {
                sb.append("仙法出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("毒法连击率")) {
                sb.append("毒法法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("毒法连击次数")) {
                sb.append("毒法出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火连击率")) {
                sb.append("鬼火法术攻击时出现连续攻击的几率");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火连击次数")) {
                sb.append("鬼火出现连击时所能连击的次数");
                this.box.addText(sb.toString(), 210, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 210, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强混乱") || v[0].contains("加强封印") || v[0].contains("加强昏睡") || v[0].contains("加强中毒") || v[0].contains("加强遗忘")) {
                sb.append("加" + v[0].substring(1) + "法术的命中率");
                this.box.addText(sb.toString(), 160, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 160, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视抗混") || v[0].contains("忽视抗封") || v[0].contains("忽视抗睡") || v[0].contains("忽视抗毒") || v[0].contains("忽视抗遗忘")) {
                sb.append("忽" + v[0].substring(1) + "敌方抗性的抵抗");
                this.box.addText(sb.toString(), 168, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 168, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强沧波效果")) {
                sb.append("加强沧波法术附加的命中率、攻击加成、目标物理/法术命中率的降低程度");
                this.box.addText(sb.toString(), 450, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 450, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强扶摇效果")) {
                sb.append("加强扶摇法术附加的命中率、攻击加成、气血上限的降低程度");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强霹雳效果")) {
                sb.append("加强霹雳法术附加的攻击加成、目标物理的降低程度");
                this.box.addText(sb.toString(), 325, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 325, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强甘霖回血值")) {
                sb.append("加强甘霖法术回复的基础数值");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("甘霖回血程度")) {
                sb.append("加强甘霖法术回复的程度数值");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强震慑")) {
                sb.append("加强对敌方震慑的气血、魔法百分比");
                this.box.addText(sb.toString(), 232, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 232, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视抗震慑")) {
                sb.append("忽视对敌方抵抗震慑的抗性百分比");
                this.box.addText(sb.toString(), 222, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 222, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强攻击法术")) {
                sb.append("加强对人物施放的攻击加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强防御法术")) {
                sb.append("加强对人物施放的防御加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("加强速度法术")) {
                sb.append("加强对人物施放的速度加强法术效果百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强三尸血")) {
                sb.append("加强三尸虫效果数值");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("强三尸血回血程度")) {
                sb.append("加强三尸虫效果的程度百分比");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("增加强克效果")) {
                sb.append("对五行（金、木、水、火、土）的目标造成法术和物理伤害时，受到百分比加成");
                this.box.addText(sb.toString(), 478, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 478, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("雷法狂暴几率")) {
                sb.append("使用仙法雷系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风法狂暴几率")) {
                sb.append("使用仙法风系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法狂暴几率")) {
                sb.append("使用仙法水系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火法狂暴几率")) {
                sb.append("使用仙法火系攻击时提升仙法狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火狂暴几率")) {
                sb.append("使用鬼火攻击时提升鬼火狂暴率");
                this.box.addText(sb.toString(), 212, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 212, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("雷法狂暴程度")) {
                sb.append("使用仙法雷系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火法暴击率")) {
                sb.append("使用仙法火系攻击时增加火法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("雷法暴击率")) {
                sb.append("使用仙法雷系攻击时增加雷法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风法暴击率")) {
                sb.append("使用仙法风系攻击时增加风法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法暴击率")) {
                sb.append("使用仙法水系攻击时增加水法的暴击几率");
                this.box.addText(sb.toString(), 263, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 263, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("风法狂暴程度")) {
                sb.append("使用仙法风系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("水法狂暴程度")) {
                sb.append("使用仙法水系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 363, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 363, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("火法狂暴程度")) {
                sb.append("使用仙法火系攻击时忽视对方的雷系抗性以及防御力的程度");
                this.box.addText(sb.toString(), 333, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 333, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("鬼火狂暴程度")) {
                sb.append("使用鬼火攻击时忽视对方的鬼火抗性以及防御力的程度");
                this.box.addText(sb.toString(), 340, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 340, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("三尸虫狂暴几率")) {
                sb.append("使用三尸虫攻击时提升三尸虫狂暴率");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视仙法抗性率")) {
                sb.append("使用仙法攻击时忽视对方的法术抗性");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].contains("忽视仙法抗性程度")) {
                sb.append("使用仙法攻击时忽视对方的仙法抗性以及防御力的程度");
                this.box.addText(sb.toString(), 339, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 339, new Font("宋体", Font.PLAIN, 1));
            }

        } else if (type == 14) {
            StringBuffer sb = new StringBuffer();
            if (v[0].equals("反震率")) {
                sb.append("受到物理，仙法，鬼火或三尸攻击时将伤害反馈给对手的几率");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("反震程度")) {
                sb.append("受到物理，仙法，鬼火或三尸攻击时将伤害反馈给对手的程度");
                this.box.addText(sb.toString(), 375, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 375, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("上善若水")) {
                sb.append("能抵抗浩然正气所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("美人迟暮")) {
                sb.append("能抵抗青面獠牙所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("化血成碧")) {
                sb.append("能抵抗天魔解体所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("灵犀一点")) {
                sb.append("能抵抗分光化影所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("明珠有泪")) {
                sb.append("能抵抗小楼夜哭所造成伤害的一定值");
                this.box.addText(sb.toString(), 235, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 235, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动牛几率")) {
                sb.append("被攻击时释放魔神附身的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动盘几率")) {
                sb.append("被攻击时释放含情脉脉的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("被动速几率")) {
                sb.append("被攻击时释放乾坤借速的几率");
                this.box.addText(sb.toString(), 198, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 198, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("伤害加深")) {
                sb.append("伤害攻击时按一定百分比加深伤害数值");
                this.box.addText(sb.toString(), 246, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 246, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("伤害减免")) {
                sb.append("被攻击时受到的所有伤害按一定百分比减除");
                this.box.addText(sb.toString(), 272, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 272, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("法术躲闪")) {
                sb.append("躲避法术攻击的几率");
                this.box.addText(sb.toString(), 146, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 146, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("对召唤兽伤害")) {
                sb.append("伤害攻击召唤兽时按一定百分比加深伤害数值");
                this.box.addText(sb.toString(), 286, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 286, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("成长率")) {
                sb.append("召唤兽成长率");
                this.box.addText(sb.toString(), 103, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 103, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("亲密度")) {
                sb.append("召唤兽亲密度");
                this.box.addText(sb.toString(), 103, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 103, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("气血初值")) {
                sb.append("召唤兽气血初值");
                this.box.addText(sb.toString(), 116, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 116, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("攻击初值")) {
                sb.append("召唤兽攻击初值");
                this.box.addText(sb.toString(), 116, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 116, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("法力初值")) {
                sb.append("召唤兽法力初值");
                this.box.addText(sb.toString(), 116, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 116, new Font("宋体", Font.PLAIN, 1));
            } else if (v[0].equals("速度初值")) {
                sb.append("召唤兽速度初值");
                this.box.addText(sb.toString(), 116, UIUtils.TEXT_FONT2);
                this.box.addText(" #r", 116, new Font("宋体", Font.PLAIN, 1));
            }

        }

        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        final Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int) goodx.getX() - 40;
        this.boxy = (int) goodx.getY() - this.boxh - 10;
        this.displaymsg();

    }

    public void displaymsg() {
        MsgJframe6.getJframe6().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(6333);
//        Music.addyinxiao("关闭窗口.mp3");
    }

    public ChatBox getBox() {
        return this.box;
    }

    public void setBox(final ChatBox box) {
        this.box = box;
    }
}
