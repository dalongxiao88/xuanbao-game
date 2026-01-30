package org.come.Jpanel;

import org.come.bean.Skill;
import come.tool.FightingData.FBUtil;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import com.tool.role.ExpUtil;
import org.come.until.UserData;
import com.tool.role.RoleLingFa;
import org.come.model.Lingbao;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.ImageIcon;
import com.tool.tcpimg.ChatBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LingMsgJpanel extends JPanel
{
    private JLabel quality;
    private JLabel baoname;
    private JLabel baolvl;
    private JLabel baotext;
    private ChatBox bao;
    private JLabel fushi;
    private JLabel[] fushiimg;
    private ChatBox shi;
    private JLabel tianfu;
    private JLabel shanchang1;
    private JLabel shanchang2;
    private JLabel skill;
    private JLabel[] skillimg;
    private JLabel[] skillname;
    private ImageIcon kaiqi;
    private ImageIcon guanbi;
    private ImageIcon fushikaiqi;
    private ImageIcon fushiguanbi;
    private ImageIcon icon;
    private ImageIcon lingimg;
    private int lock;
    private ImageIcon lockIcon;
    
    public LingMsgJpanel() {
        this.quality = new JLabel();
        this.bao = new ChatBox();
        this.fushiimg = new JLabel[5];
        this.shi = new ChatBox();
        this.skillimg = new JLabel[5];
        this.skillname = new JLabel[5];
        this.kaiqi = new ImageIcon("img/lingbao/msg/小开.png");
        this.guanbi = new ImageIcon("img/lingbao/msg/小封.png");
        this.fushikaiqi = new ImageIcon("img/lingbao/msg/小圆开.png");
        this.fushiguanbi = new ImageIcon("img/lingbao/msg/小圆封.png");
        this.lock = 0;
        this.lockIcon = new ImageIcon("img/xy2uiimg/goodorpet_lock.png");
        this.quality.setBounds(15, 5, 90, 25);
        this.quality.setForeground(new Color(205, 79, 57));
        this.quality.setFont(UIUtils.TEXT_MSG);
        this.quality.setHorizontalAlignment(0);
        this.quality.setVerticalAlignment(0);
        this.add(this.quality);
        (this.baoname = new JLabel()).setBounds(110, 5, 55, 25);
        this.baoname.setFont(UIUtils.TEXT_MSG);
        this.baoname.setForeground(Color.WHITE);
        this.add(this.baoname);
        (this.baolvl = new JLabel()).setBounds(165, 5, 80, 25);
        this.baolvl.setFont(UIUtils.TEXT_MSG);
        this.baolvl.setForeground(new Color(205, 79, 57));
        this.add(this.baolvl);
        (this.baotext = new JLabel()).setBounds(110, 30, 300, 25);
        this.baotext.setFont(UIUtils.TEXT_MSG);
        this.baotext.setForeground(Color.WHITE);
        this.add(this.baotext);
        (this.fushi = new JLabel()).setBounds(110, 140, 30, 25);
        this.fushi.setFont(UIUtils.TEXT_MSG);
        this.fushi.setForeground(new Color(210, 180, 140));
        this.fushi.setText("符石");
        this.add(this.fushi);
        for (int i = 0; i < 5; ++i) {
            (this.fushiimg[i] = new JLabel()).setBounds(145, 145 + i * 18, 18, 18);
            this.add(this.fushiimg[i]);
        }
        (this.tianfu = new JLabel()).setBounds(110, 240, 300, 25);
        this.tianfu.setFont(UIUtils.TEXT_MSG);
        this.tianfu.setForeground(new Color(164, 211, 238));
        this.tianfu.setText("天赋技能: 低级根骨灰色  低级根骨灰色");
        this.add(this.tianfu);
        (this.shanchang1 = new JLabel()).setBounds(110, 260, 300, 25);
        this.shanchang1.setFont(UIUtils.TEXT_MSG);
        this.shanchang1.setForeground(new Color(162, 181, 205));
        this.shanchang1.setText("擅长技能: 低级根骨  低级根骨  低级根骨");
        this.add(this.shanchang1);
        (this.shanchang2 = new JLabel()).setBounds(175, 280, 300, 25);
        this.shanchang2.setFont(UIUtils.TEXT_MSG);
        this.shanchang2.setForeground(new Color(162, 181, 205));
        this.shanchang2.setText("低级根骨  低级根骨  低级根骨");
        this.add(this.shanchang2);
        (this.skill = new JLabel()).setBounds(15, 160, 90, 25);
        this.skill.setForeground(new Color(238, 232, 205));
        this.skill.setFont(UIUtils.TEXT_MSG);
        this.skill.setHorizontalAlignment(0);
        this.skill.setVerticalAlignment(0);
        this.skill.setText("技能");
        this.add(this.skill);
        for (int i = 0; i < 5; ++i) {
            (this.skillimg[i] = new JLabel()).setBounds(15, 185 + i * 20, 20, 20);
            ImageIcon img = new ImageIcon("img/灵宝/skill/疾风骤雨.png");
            img.setImage(img.getImage().getScaledInstance(20, 20, 1));
            this.skillimg[i].setIcon(img);
            this.add(this.skillimg[i]);
            (this.skillname[i] = new JLabel()).setBounds(40, 176 + i * 21, 60, 30);
            this.skillname[i].setFont(UIUtils.TEXT_MSG);
            this.skillname[i].setForeground(new Color(162, 181, 205));
            this.skillname[i].setText("疾风骤雨");
            this.add(this.skillname[i]);
        }
        this.setPreferredSize(new Dimension(470, 310));
        this.setLayout((LayoutManager)null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/37682229670000.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 470, 310, this);
        if (this.lingimg != null) {
            g.drawImage(this.lingimg.getImage(), 10, 30, 96, 96, this);
        }
        if (this.lock == 1) {
            g.drawImage(this.lockIcon.getImage(), 10, 10, 10, 12, this);
        }
        Graphics g2 = g.create(110, 30, 360, 125);
        this.bao.paint(g2);
        g2.dispose();
        Graphics g3 = g.create(180, 140, 285, 105);
        this.shi.paint(g3);
        g3.dispose();
    }
    
    public void lingbaoshow(Lingbao lingbao) {
        this.quality.setText("品质  " + lingbao.getBaoquality());
        this.baoname.setText(lingbao.getBaoname());
        this.baolvl.setText(lingbao.getLingbaolvl() + " 级");
        this.baotext.setText(" ");
        this.lock = lingbao.getGoodlock();
        this.lingimg = RoleLingFa.lingbaoimg(lingbao.getSkin(), -1, -1);
        if (lingbao.getBaotype().equals("法宝")) {
            this.showfb(lingbao);
        }
        else {
            if (lingbao.getTianfuskill() == null) {
                lingbao.setTianfuskill("");
            }
            String[] tianv = lingbao.getTianfuskill().split("\\|");
            StringBuffer tianb = new StringBuffer();
            tianb.append("天赋技能:");
            for (int i = 0; i < tianv.length; ++i) {
                tianb.append(" " + tianv[i]);
            }
            this.tianfu.setText(tianb.toString());
            String[] shanb = null;
            if (lingbao.getGoodskill() != null && !lingbao.getGoodskill().equals("")) {
                shanb = lingbao.getGoodskill().split("\\|");
            }
            StringBuffer tianb2 = new StringBuffer();
            tianb2.append("擅长技能:");
            if (shanb != null) {
                for (int j = 0; j < shanb.length && j < 3; ++j) {
                    tianb2.append(" " + shanb[j]);
                }
            }
            this.shanchang1.setText(tianb2.toString());
            StringBuffer tianb3 = new StringBuffer();
            if (shanb != null && shanb.length >= 3) {
                for (int k = 3; k < shanb.length; ++k) {
                    tianb3.append(" " + shanb[k]);
                }
            }
            this.shanchang2.setText(tianb3.toString());
            this.bao.removemsg();
            this.bao.addText("#G契合度" + lingbao.getLingbaoqihe() + " 活跃度" + lingbao.hyz() + "#W(" + lingbao.getBaoactive() + ")", 350);
            this.bao.addText("#G速度" + lingbao.spz() + "#W(" + lingbao.getBaospeed() + ") #G援助" + UserData.xiaoshu(lingbao.zyz() * 1000.0 / 10.0) + "#W(" + UserData.xiaoshu(lingbao.getAssistance() * 1000.0 / 10.0) + ")", 350);
            this.bao.addText("#G回复" + UserData.xiaoshu(lingbao.replyz() * 1000.0 / 10.0) + "#W(" + UserData.xiaoshu(Double.parseDouble(lingbao.getBaoreply()) * 1000.0 / 10.0) + ") #G落宝" + UserData.xiaoshu(lingbao.shotz() * 1000.0 / 10.0) + "#W(" + UserData.xiaoshu(lingbao.getBaoshot() * 1000.0 / 10.0) + ")", 350);
            this.bao.addText("#G伤害" + UserData.xiaoshu(lingbao.apz() * 1000.0 / 10.0) + "#W(" + UserData.xiaoshu(Double.parseDouble(lingbao.getBaoap()) * 1000.0 / 10.0) + ") #G抗落宝" + UserData.xiaoshu(lingbao.resistshopz() * 1000.0 / 10.0) + "#W(" + UserData.xiaoshu(lingbao.getResistshop() * 1000.0 / 10.0) + ")", 350);
            this.bao.addText("#Y" + lingbao.getKangxing().split("=")[0] + " " + lingbao.getKangxing().split("=")[1], 350);
            this.bao.addText("#Y道行:#R" + ExpUtil.LFExptoString(lingbao.getLingbaoexe().longValue() + ExpUtil.LFExp2(lingbao.getLingbaolvl().intValue() - 1)) + "/" + ExpUtil.LFExptoString(ExpUtil.LFExp2(lingbao.getLingbaolvl().intValue())), 350, UIUtils.TEXT_FONT1);
            shanb = null;
            if (lingbao.getSkills() != null && !lingbao.getSkills().equals("")) {
                shanb = lingbao.getSkills().split("\\|");
            }
            for (int k = 0; k < 5; ++k) {
                if (k < lingbao.getSkillsum()) {
                    if (shanb != null && k < shanb.length) {
                        this.skillxiugai(k, shanb[k].split("=")[0]);
                    }
                    else {
                        this.skillxiugai(k, "已开启");
                    }
                }
                else {
                    this.skillxiugai(k, "未开启");
                }
            }
            shanb = null;
            if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                shanb = lingbao.getFushis().split("\\|");
            }
            this.shi.removemsg();
            for (int k = 0; k < 5; ++k) {
                if (k >= lingbao.getFusum()) {
                    this.fushixiugai(k, "#cD2B48C未开启", this.fushiguanbi);
                }
                else if (shanb != null && k < shanb.length) {
                    Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(shanb[k]));
                    if (goodstable != null) {
                        String[] vfu = goodstable.getValue().split("\\|");
                        StringBuffer fua = new StringBuffer();
                        fua.append("#cD2B48C");
                        for (int l = 0; l < vfu.length; ++l) {
                            if (l == 0) {
                                fua.append("[" + vfu[l].split("=")[1] + "]");
                            }
                            else {
                                fua.append(vfu[l].split("=")[0] + vfu[l].split("=")[1]);
                            }
                        }
                        this.fushixiugai(k, fua.toString(), new ImageIcon("img/lingbao/msg/" + goodstable.getSkin() + ".png"));
                    }
                }
                else {
                    this.fushixiugai(k, "#cD2B48C已开启", this.fushikaiqi);
                }
            }
        }
    }
    
    public void showfb(Lingbao lingbao) {
        StringBuffer tianb = new StringBuffer();
        tianb.append("天赋技能:");
        tianb.append(lingbao.getBaoname());
        this.tianfu.setText(tianb.toString());
        this.shanchang1.setText("擅长技能:无");
        this.shanchang2.setText("");
        this.shi.removemsg();
        this.bao.removemsg();
        for (int i = 0; i < 5; ++i) {
            this.skillxiugai(i, "无法开启");
            this.fushixiugai(i, "#cD2B48C无法开启", this.fushiguanbi);
        }
        this.bao.addText("#Y" + lingbao.getKangxing().split("=")[0] + " " + lingbao.getKangxing().split("=")[1], 350);
        this.bao.addText("#Y道行:#R" + ExpUtil.LFExptoString(lingbao.getLingbaoexe().longValue() + ExpUtil.LFExp2(lingbao.getLingbaolvl().intValue() - 1)) + "/" + ExpUtil.LFExptoString(ExpUtil.LFExp2(lingbao.getLingbaolvl().intValue())), 350, UIUtils.TEXT_FONT1);
        Skill skill = UserMessUntil.getskill1(lingbao.getBaoname());
        if (skill != null) {
            int id = Integer.parseInt(skill.getSkillid());
            int born = ImageMixDeal.userimg.getRoleShow().getTurnAround();
            int lvl = AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            int bzlvl = RoleLingFa.getRoleLingFa().getFaPJ();
            int qv = RoleLingFa.getQv(lingbao.getBaoquality());
            int blvl = lingbao.getLingbaolvl().intValue();
            int pz = FBUtil.getFBlvl(born, lvl, bzlvl, qv, blvl);
            double grow = 0.0;
            double value = 0.0;
            if (skill.getGrow() != null && !skill.getGrow().equals("")) {
                grow = Double.parseDouble(skill.getGrow());
            }
            if (skill.getValue() != null && !skill.getGrow().equals("")) {
                value = Double.parseDouble(skill.getValue());
            }
            String v1 = UserData.xiaoshu(value + (double)pz * grow);
            String v2 = String.valueOf(FBUtil.getFBcx(id, blvl));
            String v3 = null;
            String v4 = String.valueOf(FBUtil.getFBsum(id, blvl));
            if (id == 4014) {
                v3 = String.valueOf((int)((value + (double)pz * grow) * 12500.0));
            }
            else if (id == 4015) {
                v3 = UserData.xiaoshu((value + (double)pz * grow) * 2.0 / 3.0);
            }
            String v5 = skill.getRemark();
            v5 = v5.replace("{概率}", v1);
            v5 = v5.replace("{回合}", v2);
            if (v3 != null) {
                v5 = v5.replace("{数值}", v3);
            }
            v5 = v5.replace("{目标}", v4);
            this.bao.addText(v5, 350);
        }
    }
    
    public void fushixiugai(int i, String text, ImageIcon imageIcon) {
        this.shi.addText(text, 285);
        this.fushiimg[i].setIcon(imageIcon);
    }
    
    public void skillxiugai(int i, String skill) {
        ImageIcon imageIcon = null;
        if (skill.equals("已开启")) {
            imageIcon = this.kaiqi;
        }
        else if (!skill.equals("未开启") && !skill.equals("无法开启")) {
            imageIcon = new ImageIcon("img/lingbao/skill/" + skill + ".png");
        }
        else {
            imageIcon = this.guanbi;
        }
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20, 1));
        this.skillimg[i].setIcon(imageIcon);
        this.skillname[i].setText(skill);
    }
    
    public JLabel getQuality() {
        return this.quality;
    }
    
    public void setQuality(JLabel quality) {
        this.quality = quality;
    }
    
    public JLabel getBaoname() {
        return this.baoname;
    }
    
    public void setBaoname(JLabel baoname) {
        this.baoname = baoname;
    }
    
    public JLabel getBaolvl() {
        return this.baolvl;
    }
    
    public void setBaolvl(JLabel baolvl) {
        this.baolvl = baolvl;
    }
    
    public JLabel getBaotext() {
        return this.baotext;
    }
    
    public void setBaotext(JLabel baotext) {
        this.baotext = baotext;
    }
    
    public ChatBox getBao() {
        return this.bao;
    }
    
    public void setBao(ChatBox bao) {
        this.bao = bao;
    }
    
    public JLabel getFushi() {
        return this.fushi;
    }
    
    public void setFushi(JLabel fushi) {
        this.fushi = fushi;
    }
    
    public JLabel[] getFushiimg() {
        return this.fushiimg;
    }
    
    public void setFushiimg(JLabel[] fushiimg) {
        this.fushiimg = fushiimg;
    }
    
    public ChatBox getShi() {
        return this.shi;
    }
    
    public void setShi(ChatBox shi) {
        this.shi = shi;
    }
    
    public JLabel getTianfu() {
        return this.tianfu;
    }
    
    public void setTianfu(JLabel tianfu) {
        this.tianfu = tianfu;
    }
    
    public JLabel getShanchang1() {
        return this.shanchang1;
    }
    
    public void setShanchang1(JLabel shanchang1) {
        this.shanchang1 = shanchang1;
    }
    
    public JLabel getShanchang2() {
        return this.shanchang2;
    }
    
    public void setShanchang2(JLabel shanchang2) {
        this.shanchang2 = shanchang2;
    }
    
    public JLabel[] getSkillimg() {
        return this.skillimg;
    }
    
    public void setSkillimg(JLabel[] skillimg) {
        this.skillimg = skillimg;
    }
    
    public JLabel[] getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(JLabel[] skillname) {
        this.skillname = skillname;
    }
}
