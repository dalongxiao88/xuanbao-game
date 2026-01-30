package org.come.Jpanel;

import java.util.List;
import java.awt.Component;
import com.tool.tcpimg.RichLabel;
import org.apache.commons.lang.StringUtils;
import come.tool.Fighting.FightingSkill;
import come.tool.Fighting.AddState;
import java.awt.Font;
import javax.swing.JLabel;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.MousePosUtil;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.Frame.Buffstatejframe;
import javax.swing.ImageIcon;
import org.come.bean.ImgZoom;
import javax.swing.JPanel;

public class BuffstateJpanel extends JPanel
{
    private final long serialVersionUID = 1L;
    private ImgZoom imgZoom;
    private ImageIcon skillBack;
    
    public BuffstateJpanel(Buffstatejframe buffstatejframe) throws Exception {
        this.skillBack = new ImageIcon("img/fighting-skill/S2711.png");
        this.setBorder(null);
        this.setLayout(null);
        this.setOpaque(false);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        (this.imgZoom = CutButtonImage.cuts("inkImg/tupiankuang/Z1007.png", 6, 6, true)).setMiddlew(5);
        this.imgZoom.setMiddleh(5);
        this.setSize(200, 200);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Component[] comps = this.getComponents();
        if (comps != null && comps.length > 0) {
            for (Component comp : comps) {
                this.remove(comp);
            }
        }
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(3, 0.5f));
        g2d.setColor(Color.BLACK);
        g2d.dispose();
        int index = FightingMixDeal.CurrentData(MousePosUtil.ccamp, MousePosUtil.cman);
        if (index < 0) {
            return;
        }
        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(index);
        Buffstatejframe buffstatejframe = Buffstatejframe.getBuffstatejframe();
        List<AddState> buffs = FightingMixDeal.getManBuffs(MousePosUtil.ccamp, MousePosUtil.cman);
        buffstatejframe.setBounds(fightingimage.getX(), fightingimage.getY(), 150, buffs.size() * 42 + 10);
        this.setSize(150, buffs.size() * 42 + 4);
        (this.imgZoom = CutButtonImage.cuts("inkImg/tupiankuang/Z1007.png", 6, 6, true)).setMiddlew(120);
        this.imgZoom.setMiddleh(buffs.size() * 42 + 16);
        if (this.imgZoom != null) {
            this.imgZoom.draw(g);
        }
        if (buffs != null && buffs.size() > 0) {
            JLabel buflabel1 = new JLabel("<html><u style=\"text-decoration: underline; margin-bottom: 5px;\">" + fightingimage.getFightingManData().getManname() + "</u></html>");
            buflabel1.setFocusable(true);
            buflabel1.setFont(new Font("", 1, 15));
            buflabel1.setForeground(Color.GREEN);
            buflabel1.setBounds(20, 4, 100, 22);
            if (this.getComponents().length < buffs.size() * 3 + 1) {
                this.add(buflabel1);
            }
            for (int i = 0; i < buffs.size(); ++i) {
                AddState buf = (AddState)buffs.get(i);
                JLabel label = new JLabel();
                label.setFocusable(true);
                label.setBounds(24, i * 48 + 26, 40, 40);
                g.drawImage(this.skillBack.getImage(), 21, i * 47 + 26, 40, 40, null);
                if (buf.getSkills() != null && buf.getSkills().size() >= 1 && buf.getSkills().get(0) != null && ((FightingSkill)buf.getSkills().get(0)).getSkillid() > 0) {
                    int killid = ((FightingSkill)buf.getSkills().get(0)).getSkillid();
                    if ((killid >= 1500 && killid <= 1900) || (killid >= 1200 && killid <= 1300)) {
                        label.setIcon(CutButtonImage.getImage("img/fighting-skill/wxs_" + ((FightingSkill)buf.getSkills().get(0)).getSkillid() + ".png", 33, 33));
                    }
                    else {
                        label.setIcon(CutButtonImage.getImage("img/fighting-skill/" + ((FightingSkill)buf.getSkills().get(0)).getSkillid() + ".png", 33, 33));
                    }
                }
                if (label.getIcon() == null) {
                    label.setIcon(CutButtonImage.getImage(this.getBufIcon(buf), 33, 33));
                }
                if (this.getComponents().length < buffs.size() * 3 + 1) {
                    this.add(label);
                }
                JLabel buflabel2 = new JLabel();
                if (buf.getSkills() != null && buf.getSkills().size() >= 1 && buf.getSkills().get(0) != null && ((FightingSkill)buf.getSkills().get(0)).getSkillid() > 0 && StringUtils.isNotBlank(((FightingSkill)buf.getSkills().get(0)).getSkillname())) {
                    buflabel2.setText(this.getRealName(((FightingSkill)buf.getSkills().get(0)).getSkillname()));
                }
                else {
                    buflabel2.setText(this.getRealName(buf.getStatename()));
                }
                buflabel2.setFocusable(true);
                buflabel2.setFont(new Font("", 0, 12));
                if (buf.getType() == 1) {
                    buflabel2.setForeground(Color.yellow);
                }
                else {
                    buflabel2.setForeground(Color.yellow);
                }
                buflabel2.setBounds(69, i * 48 + 30, 100, 20);
                if (this.getComponents().length < buffs.size() * 3 + 1) {
                    this.add(buflabel2);
                }
                RichLabel buflabel3 = new RichLabel("还剩余#Y" + buf.getSurplus() + "#W回合");
                buflabel3.setFocusable(true);
                buflabel3.setFont(new Font("", 1, 12));
                buflabel3.setForeground(Color.white);
                buflabel3.setBounds(69, i * 48 + 48, 100, 20);
                if (this.getComponents().length < buffs.size() * 3 + 1) {
                    this.add(buflabel3);
                }
            }
        }
        buffstatejframe.setBounds(fightingimage.getX(), fightingimage.getY(), 150, buffs.size() * 42 + 30);
    }
    //战斗内快捷查看状态
    public String getRealName(String oldName) {
        if (oldName != null) {
            if (oldName.equals("bbss")) {
                return "舍身取义";
            }
            if (oldName.equals("rj3")) {
                return "舍身取义";
            }
            if (oldName.equals("9371")) {
                return "小鬼难缠";
            }
            if (oldName.equals("9367")) {
                return "轻云蔽月";
            }
            if (oldName.equals("9111")) {
                return "黯然销魂";
            }
            if (oldName.equals("1315")) {
                return "患难与共抗";
            }
            if (oldName.equals("1606")) {
                return "绝境逢生";
            }
            if (oldName.equals("1608")) {
                return "春回大地";
            }
            if (oldName.equals("1611")) {
                return "妙手回春";
            }
            if (oldName.equals("1612")) {
                return "春意盎然";
            }
            if (oldName.equals("9228")) {
                return "御宇乘风";
            }
            if (oldName.equals("1308")) {
                return "心有灵犀加抗";
            }
            if (oldName.equals("9164")) {
                return "如履薄冰";
            }
            if (oldName.equals("6022")) {
                return "万古同悲";
            }
            if (oldName.equals("1231")) {
                return "脱兔-迟钝";
            }
            if (oldName.equals("11027")) {
                return "八面玲珑";
            }
            if (oldName.equals("11029")) {
                return "风荷送香";
            }
            if (oldName.equals("11032")) {
                return "月共潮生";
            }
            if (oldName.equals("11034")) {
                return "步步为营";
            }
            if (oldName.equals("11010")) {
                return "化险为夷";
            }
            if (oldName.equals("11012")) {
                return "冲冠一怒";
            }
            if (oldName.equals("11014")) {
                return "怒不可遏";
            }
            if (oldName.equals("11006")) {
                return "羊入虎口";
            }
            if (oldName.equals("11007")) {
                return "势不可挡";
            }
            if (oldName.equals("11018")) {
                return "火冒三丈";
            }
            if(oldName.equals("785666")) {//标记1
                return "没有的改好";
            }
            if (oldName.equals("fbJge")) {
                return "金箍";
            }
            if (oldName.equals("fbJjl")) {
                return "将军令";
            }
            if (oldName.equals("fbYsjl")) {
                return "银锁金铃";
            }
            if (oldName.equals("fbHlz")) {
                return "黑龙珠";
            }
            if (oldName.equals("fbFty")) {
                return "翻天印";
            }
            if (oldName.equals("fbQw")) {
                return "情网";
            }
            if (oldName.equals("fbDsc")) {
                return "大势锤";
            }
            if (oldName.equals("fbYmgs")) {
                return "幽冥鬼手";
            }
            if (oldName.equals("fbQbllt")) {
                return "七宝玲珑塔";
            }
            if (oldName.equals("fbDsy")) {
                return "大手印";
            }
            if (oldName.equals("fbBld")) {
                return "宝莲灯";
            }
            if (oldName.equals("fbBgz")) {
                return "白骨爪";
            }
            if (oldName.equals("fbJljs")) {
                return "锦镧袈裟";
            }
            if (oldName.equals("fbHd")) {
                return "化蝶";
            }

        } else if (oldName == null){//标记2
            return "未知技能";
        }
        return oldName;
    }

    /**
     * 找图
     * @param buf
     * @return
     */
    public String getBufIcon(AddState buf) {
        if (buf.getStatename() != null) {
            if (buf.getStatename().equals("bbss")) {//舍身
                return "img/fighting-skill/wxs_1839.png";
            }
            if (buf.getStatename().equals("rj3")) {//舍身
                return "img/fighting-skill/wxs_1839.png";
            }
            if (buf.getStatename().equals("1315")) {
                return "img/item/sh_06.png";
            }
            if (buf.getStatename().equals("9164")) {
                return "img/fighting-skill/9164.png";
            }
            if (buf.getStatename().equals("9371")) {
                return "img/fighting-skill/9371.png";
            }
            if (buf.getStatename().equals("6022")) {
                return "img/fighting-skill/9325.png";
            }
            if (buf.getStatename().equals("1231")) {
                return "img/fighting-skill/wxs_1231.png";
            }
            if (buf.getStatename().equals("11027")) {
                return "img/fighting-skill/wxs_11027.png";
            }
            if (buf.getStatename().equals("11029")) {
                return "img/fighting-skill/wxs_11029.png";
            }
            if (buf.getStatename().equals("11032")) {
                return "img/fighting-skill/wxs_11032.png";
            }
            if (buf.getStatename().equals("11034")) {
                return "img/fighting-skill/wxs_11034.png";
            }
            if (buf.getStatename().equals("11018")) {
                return "img/fighting-skill/wxs_11018.png";
            }
            if (buf.getStatename().equals("11014")) {
                return "img/fighting-skill/wxs_11014.png";
            }
            if (buf.getStatename().equals("11012")) {
                return "img/fighting-skill/wxs_11012.png";
            }
            if (buf.getStatename().equals("11010")) {
                return "img/fighting-skill/wxs_11010.png";
            }
            if (buf.getStatename().equals("22001")) {
                return "img/skill/wxs_22001.png";
            }
            if (buf.getStatename().equals("22000")) {
                return "img/skill/wxs_22000.png";
            }
            if (buf.getStatename().equals("fbJge")) {
                return "img/lingbao/111.png";
            }
            if (buf.getStatename().equals("fbJjl")) {
                return "img/lingbao/102.png";
            }
            if (buf.getStatename().equals("fbYsjl")) {
                return "img/lingbao/101.png";
            }
            if (buf.getStatename().equals("fbHlz")) {
                return "img/lingbao/105.png";
            }
            if (buf.getStatename().equals("fbFty")) {
                return "img/lingbao/112.png";
            }
            if (buf.getStatename().equals("fbQw")) {
                return "img/lingbao/109.png";
            }
            if (buf.getStatename().equals("fbDsc")) {
                return "img/lingbao/103.png";
            }
            if (buf.getStatename().equals("fbYmgs")) {
                return "img/lingbao/106.png";
            }
            if (buf.getStatename().equals("fbBgz")) {
                return "img/lingbao/106.png";
            }
            if (buf.getStatename().equals("fbQbllt")) {
                return "img/lingbao/104.png";
            }
            if (buf.getStatename().equals("fbDsy")) {
                return "img/lingbao/107.png";
            }
            if (buf.getStatename().equals("fbBld")) {
                return "img/lingbao/110.png";
            }
            if (buf.getStatename().equals("fbJljs")) {
                return "img/lingbao/113.png";
            }
            if (buf.getStatename().equals("fbHd")) {
                return "img/lingbao/115.png";
            }
            if (buf.getStatename().equals("11006")) {
                return "img/skill/wxs_11006.png";
            }
            if (buf.getStatename().equals("11007")) {
                return "img/skill/wxs_11007.png";
            }
        }
        return "img/fighting-skill/wxs_1839.png";
    }
}
