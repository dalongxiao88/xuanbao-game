package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import org.come.XuanBao.XuanBaoJframe;
import org.come.bean.*;
import org.come.until.Arith;
import org.come.summonequip.JframeSummonEquipMain;
import org.come.model.Eshop;
import org.come.model.Shop;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.until.AnalysisString;
import org.come.until.AccessSuitMsgUntil;
import org.wing.panel.WingMainFrame;
import org.wing.panel.WingMainPanel;
import com.tool.btn.BaptizeBtn;
import org.come.starcard.JpanelStarCardMain;
import org.come.summonequip.JpanelSummonEquipMain;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import java.util.List;

import com.tool.tcpimg.RichLabel;
import java.util.ArrayList;
import com.tool.role.RoleProperty;
import org.come.until.UserMessUntil;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import java.util.LinkedHashMap;
import java.awt.Font;
import org.come.until.Goodtype;
import com.tool.image.ImageMixDeal;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.come.until.CutButtonImage;
import com.tool.tcp.Sprite;

import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import com.tool.tcpimg.ChatBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoodsMsgJpanel extends JPanel
{
    private int gwidth;
    private int gheight;
    private JLabel labgoodsimg;
    public static String[] NUMS;
    private ChatBox box;
    private boolean jy;
    private int lock;
    private long type;
    private long code;
    private String zhizuo;
    private Goodstable goodsss;
    private ImageIcon daoju;
    private ImageIcon yaopin;
    private ImageIcon putongzhuangbei;
    private ImageIcon cailiao;
    private ImageIcon kuangshi;
    private ImageIcon CWzhuangbei;
    private ImageIcon hushenfu;
    private ImageIcon huoban;
    private ImageIcon peishi;
    private ImageIcon xianqi;
    private ImageIcon shenbing;
    private ImageIcon Ding;
    private ImageIcon Dingzhi;
    private ImageIcon shenqi;
    private ImageIcon jyimg;
    private ImageIcon icon;
    private ImageIcon shanggu;
    private ImgZoom imgZoom;
    static Sprite tcp14;
    static Sprite tcp15;
    static Sprite tcp16;
    static Sprite tcp17;
    static Sprite tcp18;
    
    public GoodsMsgJpanel(int gwidth, int gheight) {
        this.jy = false;
        this.lock = 0;
        this.type = 0L;
        this.code = 0L;
        this.daoju = new ImageIcon("img/background/137.png");
        this.yaopin = new ImageIcon("img/background/138.png");
        this.putongzhuangbei = new ImageIcon("img/background/139.png");
        this.cailiao = new ImageIcon("img/background/141.png");
        this.kuangshi = new ImageIcon("img/background/142.png");
        this.CWzhuangbei = new ImageIcon("img/background/146.png");
        this.hushenfu = new ImageIcon("img/background/143.png");
        this.huoban = new ImageIcon("img/background/147.png");
        this.peishi = new ImageIcon("img/background/140.png");
        this.xianqi = new ImageIcon("inkImg/background/7.png");
        this.shenbing = new ImageIcon("inkImg/background/5.png");
        this.Ding = new ImageIcon("inkImg/hongmu/bukejiaoyi.png");
        this.Dingzhi = new ImageIcon("inkImg/hongmu/bukexilian.png");
        this.shenqi = new ImageIcon("inkimg/hongmu/dingzhishenqi.png");
        this.jyimg = new ImageIcon("resource/mouse/禁交易.png");
        this.icon = new ImageIcon("img/xy2uiimg/goodorpet_lock.png");
        this.shanggu = new ImageIcon("inkImg/hongmu/shenqi.png");
        this.imgZoom = CutButtonImage.cuts("inkImg/background/S145.png", 14, 7, true);
        this.gwidth = gwidth;
        this.gheight = gheight;
        this.setPreferredSize(new Dimension(gwidth, gheight));
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        (this.labgoodsimg = new JLabel()).setBounds(15, 15, 120, 120);
        this.add(this.labgoodsimg);
        this.box = new ChatBox();
    }
    
    @Override
    public void paint(Graphics g) {
        this.imgZoom.draw(g);
        Graphics g2 = g.create(135, 5, 310, this.gheight - 10);
        this.box.paint(g2);
        g2.dispose();
        if (this.jy) {
            g.drawImage(this.jyimg.getImage(), this.gwidth - 49, 0, this.jyimg.getIconWidth(), this.jyimg.getIconHeight(), this);
        }
        if ((this.type == 6500L || this.type == 6900L || this.type == 6601L || this.type == 6600L || this.type == 6701L || this.type == 6700L || this.type == 6800L) && this.code != 0L) {
            g.setColor(UIUtils.COLOR_SBCode1);
            g.drawString("编号  8888@" + this.code / 1000L, 140, this.gheight - 20);
            g.setColor(Color.red);
            g.drawString("拥有人：" + ImageMixDeal.userimg.getRoleShow().getRolename(), 140, this.gheight - 5);
            g.drawImage(this.shenbing.getImage(), 5, 5, this.shenbing.getIconWidth(), this.shenbing.getIconHeight(), this);
        }
        if (this.type >= 7000L && this.type <= 7004L && this.code != 0L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_SBCode1);
            g.drawString("编号  8888@" + this.code / 1000L, 140, this.gheight - 20);
            g.setColor(Color.red);
            g.drawString("拥有人：" + ImageMixDeal.userimg.getRoleShow().getRolename(), 140, this.gheight - 5);
            g.drawImage(this.xianqi.getImage(), 5, 5, this.xianqi.getIconWidth(), this.xianqi.getIconHeight(), this);
        }
        if (this.type >= 8868L && this.type <= 8872L) {
            g.drawImage(this.Ding.getImage(), 15, 130, this.Ding.getIconWidth(), this.Ding.getIconHeight(), this);
            g.drawImage(this.shenqi.getImage(), 5, 5, this.shenqi.getIconWidth(), this.shenqi.getIconHeight(), this);
            g.drawImage(this.Dingzhi.getImage(), 15, 160, this.Dingzhi.getIconWidth(), this.Dingzhi.getIconHeight(), this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode1);
                g.drawString("编号  8888@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if ((this.type == 927L || this.type == 928L || this.type == 929L || this.type == 930L || this.type == 931L || this.type == 606L || this.type == 607L || this.type == 608L || this.type == 609L || this.type == 610L) && this.code != 0L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_SBCode1);
            g.setColor(Color.red);
            g.drawString("拥有人：" + ImageMixDeal.userimg.getRoleShow().getRolename(), 140, this.gheight - 5);
            g.drawImage(this.peishi.getImage(), 5, 5, this.peishi.getIconWidth(), this.peishi.getIconHeight(), this);
        }
        if (this.type >= 7500L && this.type <= 7511L && this.code != 0L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_SBCode1);
            g.drawImage(this.huoban.getImage(), 5, 5, this.huoban.getIconWidth(), this.huoban.getIconHeight(), this);
        }
        if ((this.type == 611L || this.type == 612L) && this.code != 0L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_SBCode1);
            g.setColor(Color.red);
            g.drawString("拥有人：" + ImageMixDeal.userimg.getRoleShow().getRolename(), 140, this.gheight - 5);
            g.drawImage(this.hushenfu.getImage(), 5, 5, this.hushenfu.getIconWidth(), this.hushenfu.getIconHeight(), this);
        }
        if ((this.type > 1L && this.type < 119L) || (this.type > 119L && this.type < 301L) || (this.type >= 302L && this.type < 497L) || (this.type >= 501L && this.type <= 504L) || this.type == 506L || (this.type > 507L && this.type <= 509L) || (this.type > 515L && this.type <= 520L) || (this.type > 525L && this.type <= 599L) || (this.type >= 712L && this.type <= 743L) || (this.type >= 745L && this.type <= 799L) || (this.type >= 801L && this.type <= 926L) || (this.type >= 932L && this.type <= 6499L) || (this.type >= 7005L && this.type <= 7511L) || this.type == 60001L || this.type == 60002L) {
            g.drawImage(this.daoju.getImage(), 5, 5, this.daoju.getIconWidth(), this.daoju.getIconHeight(), this);
        }
        if (this.type == 500L) {
            g.drawImage(this.kuangshi.getImage(), 5, 5, this.kuangshi.getIconWidth(), this.kuangshi.getIconHeight(), this);
        }
        if (this.type == 0L) {
            g.drawImage(this.yaopin.getImage(), 5, 5, this.yaopin.getIconWidth(), this.yaopin.getIconHeight(), this);
        }
        if ((this.type >= 600L && this.type <= 605L) || this.type == 800L) {
            g.setColor(UIUtils.COLOR_NAME6);
            g.setFont(UIUtils.TEXT_FONTZSF20);
            g.drawImage(this.putongzhuangbei.getImage(), 5, 5, this.putongzhuangbei.getIconWidth(), this.putongzhuangbei.getIconHeight(), this);
        }
        boolean tyepe2 = Goodtype.OrdinaryEquipment(this.type);
        if (tyepe2) {
            Goodstable good = this.goodsss;
            if (good.getValue() != null) {
                String[] vcs = good.getValue().split("\\|");
                for (int i = 0; i < vcs.length; ++i) {
                    String[] zhi = vcs[i].split("=");
                    if (zhi[0].equals("制作人")) {
                        g.setFont(new Font("宋体", 0, 14));
                        g.setColor(Color.red);
                        g.drawString("制作人 " + this.zhizuo, 10, 150);
                    }
                }
            }
            g.setFont(new Font("宋体", 0, 13));
            g.setColor(Color.red);
            g.drawString("拥有人：" + ImageMixDeal.userimg.getRoleShow().getRolename(), 139, this.gheight - 10);
        }
        if (this.type >= 510L && this.type <= 512L) {
            g.drawImage(this.CWzhuangbei.getImage(), 5, 5, this.CWzhuangbei.getIconWidth(), this.CWzhuangbei.getIconHeight(), this);
        }
        if ((this.type >= 702L && this.type <= 711L) || (this.type >= 497L && this.type < 500L) || this.type == 505L || this.type == 507L || this.type == 744L || (this.type >= 8889L && this.type <= 8893L) || (this.type >= 513L && this.type <= 515L) || (this.type >= 521L && this.type <= 524L) || this.type == 119L) {
            g.drawImage(this.cailiao.getImage(), 5, 5, this.cailiao.getIconWidth(), this.cailiao.getIconHeight(), this);
        }
        if (this.type == 7000L) {
            GoodsMsgJpanel.tcp15.draw(g, 70, 75);
        }
        if (this.type == 7001L) {
            GoodsMsgJpanel.tcp15.draw(g, 70, 75);
        }
        if (this.type == 7003L) {
            GoodsMsgJpanel.tcp15.draw(g, 70, 75);
        }
        if (this.type == 7002L) {
            GoodsMsgJpanel.tcp15.draw(g, 70, 75);
        }
        if (this.type == 7004L) {
            GoodsMsgJpanel.tcp14.draw(g, 70, 75);
        }
        if (this.type == 6500L) {
            GoodsMsgJpanel.tcp14.draw(g, 70, 75);
        }
        if (this.type == 6601L) {}
        if (this.type == 6600L) {}
        if (this.type == 6800L) {}
        if (this.type == 6900L) {}
        if (this.type == 606L) {
            GoodsMsgJpanel.tcp17.draw(g, 70, 75);
        }
        if (this.type == 607L) {
            GoodsMsgJpanel.tcp17.draw(g, 70, 75);
        }
        if (this.type == 608L) {
            GoodsMsgJpanel.tcp17.draw(g, 70, 75);
        }
        if (this.type == 609L) {
            GoodsMsgJpanel.tcp17.draw(g, 70, 75);
        }
        if (this.type == 610L) {
            GoodsMsgJpanel.tcp17.draw(g, 70, 75);
        }
        if (this.type == 611L) {
            GoodsMsgJpanel.tcp16.draw(g, 70, 75);
        }
        if (this.type == 612L) {
            GoodsMsgJpanel.tcp16.draw(g, 70, 75);
        }
        if (this.lock == 1) {
            g.drawImage(this.icon.getImage(), 5, 5, 10, 12, this);
        }
        if (this.type == 929L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_Wing1);
            g.drawString("神饰类:琉璃", 10, this.gheight - 7);
            g.drawImage(this.shanggu.getImage(), 315, this.gheight - 45, 86, 42, this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode);
                g.drawString("编号  2895@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if (this.type == 928L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_Wing1);
            g.drawString("神饰类:琉璃", 35, this.gheight - 7);
            g.drawImage(this.shanggu.getImage(), 315, this.gheight - 45, 86, 42, this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode);
                g.drawString("编号  2895@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if (this.type == 931L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_Wing1);
            g.drawString("神饰类:琉璃", 10, this.gheight - 7);
            g.drawImage(this.shanggu.getImage(), 315, this.gheight - 45, 86, 42, this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode);
                g.drawString("编号  2895@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if (this.type == 927L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_Wing1);
            g.drawString("神饰类:琉璃", 35, this.gheight - 7);
            g.drawImage(this.shanggu.getImage(), 315, this.gheight - 45, 86, 42, this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode);
                g.drawString("编号  2895@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if (this.type == 930L) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(UIUtils.COLOR_Wing1);
            g.drawString("神饰类:琉璃", 35, this.gheight - 7);
            g.drawImage(this.shanggu.getImage(), 315, this.gheight - 45, 86, 42, this);
            if (this.code != 0L) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(UIUtils.COLOR_SBCode);
                g.drawString("编号  2895@" + this.code / 1000L, 140, this.gheight - 7);
            }
        }
        if (this.type == 923L) {
            GoodsMsgJpanel.tcp14.draw(g, 60, 140);
        }
        super.paint(g);
    }
    
    public String upColor(StringBuffer buffer, String color1, String color2) {
        if (color1 == null || !color2.equals(color1)) {
            buffer.append(color2);
            return color2;
        }
        return color1;
    }
    
    private static String[] coalesce(String[] values) {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < values.length; ++i) {
            String[] vs = values[i].split("=");
            if (!vs[0].equals("特技") && !vs[0].equals("星阵属性") && !vs[0].equals("五行属性") && vs.length == 2) {
                String v = (String)map.get(vs[0]);
                if (v == null) {
                    map.put(vs[0], vs[1]);
                }
                else {
                    map.put(vs[0], Double.parseDouble(v) + Double.parseDouble(vs[1]) + "");
                }
            }
            else {
                map.put(values[i], "");
            }
        }
        String[] strings = new String[map.keySet().size()];
        int index = 0;
        for (String key : map.keySet()) {
            if (StringUtils.isNotBlank((String)map.get(key))) {
                strings[index] = key + "=" + (String)map.get(key);
            }
            else {
                strings[index] = key;
            }
            ++index;
        }
        return strings;
    }
    
    public String showEw0(StringBuffer buffer, String value, long type) {
        return this.showEw0(buffer, value, type, null);
    }
    
    public String showEw0(StringBuffer buffer, String value, long type, StringBuffer tj) {
        String xz = null;
        String color = null;
        boolean is = Goodtype.GodEquipment_God(type) || Goodtype.GodEquipment_xian(type) || Goodtype.GodEquipment_Ding(type);
        String[] v = value.split("&");
        if (Goodtype.EquipmentType(type) != -1) {
            v = coalesce(v);
        }
        for (int i = 1; i < v.length; ++i) {
            if (v[i].startsWith("星阵属性")) {
                xz = v[i];
            }
            else {
                String[] vs = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                if (vs[0].equals("特技")) {
                    if (tj != null) {
                        StringBuffer sb = tj;
                        color = this.upColor(sb, color, "#ca098c8");
                        sb.append("特技");
                        for (int j = 1; j < vs.length; ++j) {
                            Skill skill = UserMessUntil.getSkillId(vs[j]);
                            if (skill != null) {
                                if (sb.length() != 0) {
                                    sb.append("#r");
                                }
                                sb.append(skill.getSkillname());
                                sb.append(":");
                                sb.append(skill.getRemark());
                                if ("8055".equals(skill.getSkillid())) {
                                    int length = v.length;
                                    int l = 0;
                                    while (l < length) {
                                        String s = v[l];
                                        if (s.startsWith("冰刃术伤害")) {
                                            sb.append("(冰刃术伤害" + s.split("=")[1] + ")");
                                            break;
                                        }
                                        else {
                                            ++l;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        color = this.upColor(buffer, color, "#ca098c8");
                        buffer.append("特技");
                        for (int k = 1; k < vs.length; ++k) {
                            Skill skill2 = UserMessUntil.getSkillId(vs[k]);
                            if (skill2 != null) {
                                if (buffer.length() != 0) {
                                    buffer.append("#r");
                                }
                                buffer.append(skill2.getSkillname());
                                buffer.append(":");
                                buffer.append(skill2.getRemark());
                                if ("8055".equals(skill2.getSkillid())) {
                                    int length2 = v.length;
                                    int n = 0;
                                    while (n < length2) {
                                        String s2 = v[n];
                                        if (s2.startsWith("冰刃术伤害")) {
                                            buffer.append("(冰刃术伤害" + s2.split("=")[1] + ")");
                                            break;
                                        }
                                        else {
                                            ++n;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (!vs[0].endsWith("冰刃术伤害")) {
                    if (vs[0].endsWith("等级")) {
                        color = this.upColor(buffer, color, "#c00ff00");
                        buffer.append(vs[0]);
                        buffer.append(" ");
                        buffer.append(vs[1]);
                        buffer.append("级");
                    }
                    else if (vs[0].equals("资质")) {
                        color = this.upColor(buffer, color, "#cFFFFFF");
                        buffer.append("【神通】");
                        buffer.append(vs[0]);
                        buffer.append(" ");
                        buffer.append(vs[1]);
                        buffer.append("/100");
                    }
                    else {
                        color = this.upColor(buffer, color, is ? "#c00EAFF" : "#c00ff00");
                        buffer.append(vs[0]);
                        buffer.append(" ");
                        buffer.append(zffh(vs[1]));
                        buffer.append(tianjia(vs[0]));
                    }
                }
            }
        }
        return xz;
    }
    
    public void showEw1(StringBuffer buffer, String value) {
        String[] v = value.split("&");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        String color = this.upColor(buffer, null, "#W");
        buffer.append("【炼器】");
        color = this.upColor(buffer, color, "#c00EAFF");
        buffer.append("开光次数 ");
        buffer.append(v[1]);
        for (int i = 2; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#c00ff00");
            buffer.append(vs[0]);
            buffer.append(" ");
            buffer.append(zffh(vs[1]));
            buffer.append(tianjia(vs[0]));
        }
    }
    
    public void showEw2(StringBuffer buffer, String value) {
        String color = null;
        String[] v = value.split("&");
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#cdb8738");
            buffer.append(vs[0]);
            buffer.append(" ");
            buffer.append(zffh(vs[1]));
            buffer.append(tianjia(vs[0]));
        }
    }
    
    public void showEw3(StringBuffer buffer, String value, String goodName) {
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        String color = this.upColor(buffer, null, "#c00ffff");
        buffer.append("【套装属性】");
        String[] v = value.split("&");
        for (int i = 4; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#c00ff00");
            buffer.append(vs[0]);
            buffer.append(" ");
            buffer.append(zffh(vs[1]));
            buffer.append(tianjia(vs[0]));
        }
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, null, "#c00ffff");
        buffer.append("【套装名称:");
        buffer.append(goodName.split("·")[0]);
        buffer.append("】品质:【");
        buffer.append(v[3]);
        buffer.append("】");
        int suitId = Integer.parseInt(v[1]);
        RoleSuitBean suit = UserMessUntil.getSuit(suitId);
        if (suit != null) {
            int sum = RoleProperty.getRoleProperty().getSuitSum(v[1]);
            String[] vs2 = suit.getHaveSkill().split("\\|");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            List<Goodstable> qbtz = accessIdlEqu(2);
            RichLabel.num = new ArrayList<>();
            for (Goodstable goodstable : qbtz) {
                if (goodName.split("·")[0].equals(goodstable.getGoodsname().split("·")[0])) {
                    RichLabel.num.add(goodstable.getValue().split("\\|")[1].split("=")[1]);
                }
            }
            int tzid = suit.getSuitID();
            String[] tzids = suit.getHaveParts().split("\\|");
            if (tzids != null && tzids.length > 0) {
                for (String s : tzids) {
                    buffer.append("#9" + tzid + s);
                }
            }
            for (int j = 0; j < vs2.length; ++j) {
                String[] vss = vs2[j].split("-");
                int maxsum = Integer.parseInt(vss[0]);
                Skill skill = UserMessUntil.getSkillId(vss[1]);
                if (skill != null) {
                    if (buffer.length() != 0) {
                        buffer.append("#r");
                    }
                    if (sum >= maxsum) {
                        color = this.upColor(buffer, null, "#c00ff00");
                        buffer.append("[");
                        buffer.append(maxsum);
                    }
                    else {
                        color = this.upColor(buffer, null, "#c807876");
                        buffer.append("[");
                        buffer.append(sum);
                    }
                    buffer.append("/");
                    buffer.append(maxsum);
                    buffer.append("]");
                    buffer.append(skill.getSkillname());
                }
            }
        }
    }
    
    public void showEw4(StringBuffer buffer, String value, Goodstable goodstable) {
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        String color = this.upColor(buffer, null, "#c00ffff");
        buffer.append("【宝石镶嵌】");
        String[] v = value.split("&");
        for (int i = 1; i < v.length; ++i) {
            Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
            if (good == null) {
                List<String> goodinfos = goodstable.getOtherInfo();
                if (goodinfos != null && goodinfos.size() > 0) {
                    for (String goodsinfo : goodinfos) {
                        if (StringUtils.isNotEmpty(goodsinfo)) {
                            String key = goodsinfo.substring(0, goodsinfo.indexOf("&"));
                            String val = goodsinfo.substring(goodsinfo.indexOf("&") + 1);
                            if (StringUtils.isNotEmpty(v[i]) && key.equals(v[i])) {
                                good = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(val, Goodstable.class);
                            }
                            else {
                                continue;
                            }
                        }
                    }
                }
            }
            if (good != null) {
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                buffer.append("#" + good.getSkin());
                color = this.upColor(buffer, null, "#cEA5700");
                buffer.append(good.getGoodsname());
                String[] bs = good.getValue().split("\\|");
                buffer.append(" ");
                buffer.append(bs[0].split("=")[1]);
                buffer.append("级 ");
                bs = bs[1].split("=");
                buffer.append(bs[0]);
                buffer.append(" ");
                buffer.append(zffh(bs[1]) + tianjia(bs[1]));
            }
        }
    }
    
    public void showEw5(StringBuffer buffer, String value) {
        String[] split = value.split("&");
        Skill skill = UserMessUntil.getSkillId(split[1]);
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        long lvl = JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[3]));
        String color = this.upColor(buffer, null, "#cFFFF00");
        buffer.append("【觉醒技】 ");
        buffer.append(skill.getSkillname());
        buffer.append("(");
        buffer.append(split[2]);
        buffer.append(")");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【觉醒技等级】 ");
        buffer.append(lvl);
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【类型】 通用");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#c00FF00");
        buffer.append(SummonSkillRemark(skill.getRemark(), skill, split[2], lvl + ""));
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cC5C583");
        buffer.append("铃、环、甲觉醒三合一，觉醒技方可生效");
    }
    
    public void showEw6(StringBuffer buffer, String value, String xz) {
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        String color = this.upColor(buffer, null, "#cFFFFFF");
        buffer.append("【五行】");
        String[] v = value.split("&");
        String[] starArray = (String[])((xz != null) ? xz.split("=") : null);
        double num = 0.0;
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#cFFFF00");
            buffer.append(vs[0]);
            buffer.append(" ");
            buffer.append(vs[1]);
            buffer.append("/100");
            if (starArray != null) {
                num += JpanelStarCardMain.fiveElementRestrainCreate(starArray[2], vs[0], vs[1]);
            }
        }
        if (starArray != null) {
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#c4ADEDD");
            buffer.append("五行加成星阵之力 ");
            buffer.append(String.format("%.1f", new Object[] { Double.valueOf(num) }));
            buffer.append("%");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            buffer.append("【星阵】");
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            buffer.append(starArray[1]);
            buffer.append("(");
            buffer.append(starArray[2]);
            buffer.append(")");
            if (this.isStarArrayName(starArray[1])) {
                buffer.append("#r#cFFFFFF赤帝宫 #c00FF00" + starArray[3]);
                buffer.append("#r#cFFFFFF青帝宫 #c00FF00" + starArray[4]);
                buffer.append("#r#cFFFFFF黄帝宫 #c00FF00" + starArray[5]);
                buffer.append("#r#cFFFFFF白帝宫 #c00FF00" + starArray[6]);
                buffer.append("#r#cFFFFFF黑帝宫 #c00FF00" + starArray[7]);
                color = null;
            }
            buffer.append("#r#c00FF00" + this.getStarArrayAttribute(starArray[1]));
        }
        else {
            buffer.append("#r#c4ADEDD无星阵，五行暂不生效");
        }
    }
    
    public String showEw7(StringBuffer buffer, String value, long type) {
        String xz = null;
        String color = null;
        String[] v = value.split("&");
        for (int i = 1; i < v.length; ++i) {
            if (v[i].startsWith("星阵属性")) {
                xz = v[i];
            }
            else {
                String[] vs = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                color = this.upColor(buffer, color, "#c00EAFF");
                buffer.append("附加属性：");
                buffer.append(vs[0]);
                buffer.append(" ");
                buffer.append(zffh(vs[1]));
                buffer.append(tianjia(vs[0]));
            }
        }
        return xz;
    }
    
    public void showEw8(StringBuffer buffer, String value, long type) {
        String xz = null;
        String color = null;
        boolean is = Goodtype.GodEquipment_God(type) || Goodtype.GodEquipment_xian(type);
        String[] v = value.split("&");
        for (int i = 1; i < v.length; ++i) {
            if (v[i].startsWith("星阵属性")) {
                xz = v[i];
            }
            else {
                String[] vs = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                if (vs[0].equals("特技")) {
                    color = this.upColor(buffer, color, "#ca098c8");
                    buffer.append("特技");
                    for (int j = 1; j < vs.length; ++j) {
                        Skill skill = UserMessUntil.getSkillId(vs[j]);
                        if (skill != null) {
                            if (buffer.length() != 0) {
                                buffer.append("#r");
                            }
                            buffer.append(skill.getSkillname());
                            buffer.append(":");
                            buffer.append(skill.getRemark());
                        }
                    }
                }
                else if (vs[0].endsWith("等级")) {
                    color = this.upColor(buffer, color, "#c00ff00");
                    buffer.append(vs[0]);
                    buffer.append(" ");
                    buffer.append(vs[1]);
                    buffer.append("级");
                }
                else if (vs[0].equals("资质")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【神通】");
                    buffer.append(vs[0]);
                    buffer.append(" ");
                    buffer.append(vs[1]);
                    buffer.append("/100");
                }
                else {
                    color = this.upColor(buffer, color, is ? "#c00EAFF" : "#cdb8738");
                    buffer.append(vs[0]);
                    buffer.append(" ");
                    if (vs[0].equals("总点粹值")) {
                        buffer.append(zffh1(vs[1]));
                    }
                    else {
                        buffer.append(zffh(vs[1]));
                        buffer.append(tianjia(vs[0]));
                    }
                }
            }
        }
    }
    
    public static String zffh1(String v) {
        StringBuffer buffer = new StringBuffer();
        try {
            double z = Double.parseDouble(v);
            if (z > 0.0) {
                buffer.append(":");
            }
            String[] vs = v.split("\\.");
            buffer.append(vs[0]);
            if (vs.length >= 2) {
                buffer.append(".");
                buffer.append(vs[1].charAt(0));
            }
            return buffer.toString();
        }
        catch (Exception ex) {
            return v;
        }
    }
    
    public void showStarCard(String value) {
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级】 ");
        buffer.append(v[0].substring(3));
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【神力】 ");
        color = this.upColor(buffer, color, "#cFFFF00");
        buffer.append(v[1].substring(3));
        buffer.append("/");
        buffer.append(Integer.parseInt(v[0].split("=")[1].split("/")[0]) * 200);
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【战力】 ");
        color = this.upColor(buffer, color, "#cFFFF00");
        buffer.append(v[2].substring(3));
        String xz = null;
        for (int i = 3; i < v.length; ++i) {
            if (v[i].startsWith(BaptizeBtn.Extras[0])) {
                xz = this.showEw0(buffer, v[i], 520L);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[6])) {
                this.showEw6(buffer, v[i], xz);
            }
        }
        this.box.addText(buffer.toString(), 235, UIUtils.TEXT_FONT);
    }
    
    public void showWing(String value) {
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【品质】");
        String quality = v[0].substring(3);
        color = this.upColor(buffer, color, WingMainPanel.getQualityColorOx(quality));
        buffer.append(quality);
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【星级】");
        color = this.upColor(buffer, color, "#cFFFF00");
        String starLvl = v[1].substring(3);
        buffer.append(starLvl);
        buffer.append("/15");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级】");
        String exp = v[2].substring(3);
        buffer.append(WingMainPanel.getWingLevel(exp));
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【经验】");
        buffer.append(exp);
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【颜色】");
        buffer.append(v[3].substring(3));
        int parseIntStar = Integer.parseInt(starLvl);
        for (int i = 4; i < v.length; ++i) {
            if (v[i].startsWith("力量") || v[i].startsWith("灵性") || v[i].startsWith("根骨") || v[i].startsWith("敏捷")) {
                String[] basics = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                color = this.upColor(buffer, color, "#cCDCA6D");
                buffer.append(basics[0]);
                buffer.append(" ");
                StringBuffer sb = buffer;
                WingMainFrame.getWingMainFrame().getWingMainPanel();
                sb.append(WingMainPanel.getUpStarData(basics[1], parseIntStar, quality));
                color = this.upColor(buffer, color, "#cFFFFFF");
                buffer.append("(");
                buffer.append(basics[1]);
                buffer.append(")");
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[0])) {
                this.showEw0(buffer, v[i], 8888L);
            }
        }
        this.box.addText(buffer.toString(), 235, UIUtils.TEXT_FONT);
    }
    
    public void showSummonEquip(String value, long type) {
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级】 ");
        buffer.append(v[0].substring(3));
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【装备部位】 ");
        buffer.append(v[1].substring(5));
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级需求】 ");
        buffer.append(v[2].substring(5));
        int i;
        for (i = 3; i < v.length && !v[i].startsWith("品质"); ++i) {
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#cFFFFFF");
            String[] basics = v[i].split("=");
            buffer.append("【");
            buffer.append(basics[0]);
            buffer.append("】 ");
            buffer.append(basics[1]);
        }
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【品质】 ");
        buffer.append(v[i].substring(3));
        buffer.append("/100");
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【通灵】 ");
        int level = Integer.parseInt(v[0].substring(3));
        buffer.append(v[i + 1].substring(3));
        buffer.append("/");
        buffer.append(level * 1000);
        boolean is = true;
        for (int j = i + 2; j < v.length; ++j) {
            if (v[j].startsWith(BaptizeBtn.Extras[0])) {
                this.showEw0(buffer, v[j], type);
            }
            else if (v[j].startsWith(BaptizeBtn.Extras[5])) {
                is = false;
                this.showEw5(buffer, v[j]);
            }
        }
        if (is) {
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#c00FDF1");
            buffer.append("未开启觉醒(开启铃、环、甲之觉醒，可领悟觉醒技)");
        }
        this.box.addText(buffer.toString(), 235, UIUtils.TEXT_FONT);
    }
    
    public void showPalEquip(String value, long type) {
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        int i = 0;
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级】 ");
        buffer.append(v[i].substring(3));
        ++i;
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cFFFFFF");
        buffer.append("【等级要求】 ");
        buffer.append(v[i].substring(5));
        ++i;
        if (v[i].startsWith("性别")) {
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            color = this.upColor(buffer, color, "#c1E90FF");
            buffer.append("【性别要求】 ");
            String sex = v[i].substring(5);
            buffer.append(sex.equals("1") ? "男" : (sex.equals("0") ? "女" : "全部性别"));
            ++i;
        }
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#c1E90FF");
        buffer.append("【品质】 ");
        buffer.append(v[i].substring(3));
        ++i;
        while (i < v.length && !v[i].startsWith("契合度")) {
            if (buffer.length() != 0) {
                buffer.append("#r");
            }
            String[] split = v[i].split("=");
            color = this.upColor(buffer, color, "#cDEDE9E");
            buffer.append(split[0]);
            buffer.append(zffh(split[1]));
            buffer.append(tianjia(split[0]));
            ++i;
        }
        if (buffer.length() != 0) {
            buffer.append("#r");
        }
        color = this.upColor(buffer, color, "#cDEDE9E");
        buffer.append("契合度 ");
        buffer.append(v[i].substring(4));
        for (int j = i + 1; j < v.length; ++j) {
            this.showEw0(buffer, v[j], type);
        }
        this.box.addText(buffer.toString(), 235, UIUtils.TEXT_FONT);
    }
    
    public void showEquipment(Goodstable goodstable, long type, Integer qhv, String goodname) {
        String value = goodstable.getValue();
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        String xz = null;
        StringBuffer tj = new StringBuffer();
        StringBuffer lq = new StringBuffer();
        Boolean b = Boolean.valueOf(false);
        Boolean b2 = Boolean.valueOf(false);
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(BaptizeBtn.Extras[0])) {
                b2 = Boolean.valueOf(true);
                xz = this.showEw0(buffer, v[i], type, tj);
                if ((boolean)b) {
                    buffer.append("#r").append(lq.toString());
                    lq = new StringBuffer();
                }
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[1])) {
                b = Boolean.valueOf(true);
                int i2 = Goodtype.EquipmentType(type);
                if (i2 == 0) {
                    if (!(boolean)b2) {
                        this.showEw1(lq, v[i]);
                    }
                    else {
                        this.showEw1(buffer, v[i]);
                    }
                }
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[2])) {
                this.showEw2(buffer, v[i]);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[3])) {
                this.showEw3(buffer, v[i], goodname);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[4])) {
                this.showEw4(buffer, v[i], goodstable);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[5])) {
                this.showEw5(buffer, v[i]);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[6])) {
                this.showEw6(buffer, v[i], xz);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[8])) {
                this.showEw7(buffer, v[i], type);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[9])) {
                this.showEw8(buffer, v[i], type);
            }
            else {
                String[] zhi = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                if (zhi[0].equals("装备角色") || zhi[0].equals("等级") || zhi[0].equals("最高携带等级") || zhi[0].equals("装备等级") || zhi[0].equals("阶数") || zhi[0].equals("套装品质")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【");
                    buffer.append(zhi[0]);
                    buffer.append("】");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("等级要求") || zhi[0].equals("力量要求") || zhi[0].equals("灵性要求") || zhi[0].equals("根骨要求") || zhi[0].equals("敏捷要求")) {
                    color = this.upColor(buffer, color, "#ce1df59");
                    buffer.append("");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("装备需求")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【装备需求】");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("装备部位")) {
                    String part = AccessSuitMsgUntil.returnPartsName(zhi[1]);
                    if (part != null) {
                        zhi[1] = part;
                    }
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【装备部位】");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("性别要求") || zhi[0].equals("性别")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【性别要求】");
                    buffer.append(zhi[1].equals("1") ? "男" : (zhi[1].equals("0") ? "女" : "全部性别"));
                }
                else if (zhi[0].equals("制作人")) {
                    color = this.upColor(buffer, color, "#ce43d32");
                    buffer.append("");
                }
                else if (zhi[0].equals("耐久")) {
                    color = this.upColor(buffer, color, "#ce1df59");
                    buffer.append("耐久 #ce1df59");
                    buffer.append(zhi[1].split(",")[0]);
                }
                else if (zhi[0].equals("培养")) {
                    color = this.upColor(buffer, color, "#c00ffff");
                    buffer.append("培养: #c00ffff");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("神类属性")) {
                    color = this.upColor(buffer, color, "#cFFA500");
                    buffer.append("【神类属性】 #cFFA500");
                    buffer.append(zhi[1]);
                }
                else if (zhi.length > 1 && !zhi[0].equals("标签") && !zhi[0].equals("值")) {
                    color = this.upColor(buffer, color, "#ce1df59");
                    buffer.append(zhi[0]);
                    buffer.append("");
                    buffer.append(zffh(zhi[1]));
                    buffer.append(tianjia(zhi[0]));
                    if (qhv != null && (int)qhv > 0) {
                        color = this.upColor(buffer, color, "#G");
                        buffer.append(" ");
                        buffer.append("(");
                        buffer.append("+ ");
                        buffer.append(String.format("%.1f", new Object[] { Double.valueOf(RoleProperty.getQHGemXS((int)qhv) / 100.0 * Double.parseDouble(zhi[1])) }));
                        buffer.append(")");
                    }
                }
            }
        }
        if (tj.toString().length() > 0) {
            buffer.append("#r").append(tj.toString());
        }
        if (lq.toString().length() > 0) {
            buffer.append("#r").append(lq.toString());
        }
        this.box.addText(buffer.toString(), 265, UIUtils.TEXT_FONT1);
    }
    
    public void showOther(String value, long type, Goodstable goodstable) {
        if (value == null || value.equals("")) {
            return;
        }
        String color = null;
        StringBuffer buffer = new StringBuffer();
        String[] v = value.split("\\|");
        String xz = null;
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(BaptizeBtn.Extras[0])) {
                xz = this.showEw0(buffer, v[i], type);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[1])) {
                this.showEw1(buffer, v[i]);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[2])) {
                this.showEw2(buffer, v[i]);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[3])) {
                this.showEw3(buffer, v[i], null);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[4])) {
                this.showEw4(buffer, v[i], goodstable);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[5])) {
                this.showEw5(buffer, v[i]);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[6])) {
                this.showEw6(buffer, v[i], xz);
            }
            else if (v[i].startsWith(BaptizeBtn.Extras[8])) {
                this.showEw7(buffer, v[i], type);
            }
            else {
                String[] zhi = v[i].split("=");
                if (buffer.length() != 0) {
                    buffer.append("#r");
                }
                if (zhi[0].equals("装备角色") || zhi[0].equals("等级") || zhi[0].equals("装备等级") || zhi[0].equals("阶数") || zhi[0].equals("等级要求") || zhi[0].equals("力量要求") || zhi[0].equals("灵性要求") || zhi[0].equals("根骨要求") || zhi[0].equals("敏捷要求") || zhi[0].equals("套装品质")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【");
                    buffer.append(zhi[0]);
                    buffer.append("】");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("装备部位")) {
                    String part = AccessSuitMsgUntil.returnPartsName(zhi[1]);
                    if (part != null) {
                        zhi[1] = part;
                    }
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【装备部位】");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("性别要求") || zhi[0].equals("性别")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append("【性别要求】");
                    buffer.append(zhi[1].equals("1") ? "男" : (zhi[1].equals("0") ? "女" : "全部性别"));
                }
                else if (zhi[0].equals("HP%") || zhi[0].equals("MP%")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    String label = zhi[0].replaceAll("%", "");
                    buffer.append(label);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                    buffer.append("%");
                }
                else if (zhi[0].equals("HP") || zhi[0].equals("MP") || zhi[0].equals("经验") || zhi[0].equals("亲密") || zhi[0].equals("钱") || zhi[0].equals("点")) {
                    color = this.upColor(buffer, color, "#cFFFFFF");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                }
                else if (zhi[0].equals("力量") || zhi[0].equals("灵性") || zhi[0].equals("根骨") || zhi[0].equals("敏捷")) {
                    color = this.upColor(buffer, color, "#cCCCC99");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                }
                else if (zhi[0].equals("培养") || zhi[0].equals("品质")) {
                    color = this.upColor(buffer, color, "#c1E90FF");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                }
                else if (zhi[0].equals("坐标") || zhi[0].equals("内丹等级")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append(zhi[0]);
                    buffer.append(":");
                    buffer.append(zffh(zhi[1]));
                }
                else if (zhi[0].equals("次数")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("炼妖次数")) {
                    color = this.upColor(buffer, color, "#c00BFFF");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                }
                else if (zhi[0].equals("描述") || zhi[0].equals("名称")) {
                    color = this.upColor(buffer, color, "#c00BFFF");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("传送") || zhi[0].equals("宝图")) {
                    String[] vs = zhi[1].split(",");
                    int x = Integer.parseInt(vs[2]) / 20;
                    int y = Integer.parseInt(vs[3]) / 20;
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append("坐标: ");
                    buffer.append(vs[1]);
                    buffer.append("(");
                    buffer.append(x);
                    buffer.append(",");
                    buffer.append(y);
                    buffer.append(")");
                }
                else if (zhi[0].equals("可用次数")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append("还可以使用");
                    buffer.append(zhi[1]);
                    buffer.append("次");
                }
                else if (zhi[0].equals("小树苗")) {
                    String[] vs = zhi[1].split(",");
                    int x = Integer.parseInt(vs[1]) / 20;
                    int y = Integer.parseInt(vs[2]) / 20;
                    color = upColor(buffer, color, "#c00FFFF");
                    buffer.append("#Y小树苗在#c00FFFF长安东#R");
                    buffer.append("(");
                    buffer.append(x);
                    buffer.append(",");
                    buffer.append(y);
                    buffer.append(")#Y等着你去照看#24");
                }
                else if (zhi[0].equals("技能")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append("技能:#G");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("技能等级")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append("技能等级:#G");
                    buffer.append(zhi[1]);
                }
                else if (zhi[0].equals("耐久")) {
                    color = this.upColor(buffer, color, "#cFFFF33");
                    buffer.append("耐久:#G");
                    buffer.append(zhi[1].split(",")[0]);
                }
                else if (zhi[0].matches("\\d+") && UserMessUntil.getSkillId(zhi[0]) != null) {
                    color = this.upColor(buffer, color, "#ca098c8");
                    buffer.append("【特技】#r");
                    buffer.append(UserMessUntil.getSkillId(zhi[0]).getSkillname() + "(" + zhi[1] + "级):");
                    String msg = UserMessUntil.getSkillId(zhi[0]).getRemark();
                    int skillId = Integer.parseInt(UserMessUntil.getSkillId(zhi[0]).getSkillid());
                    String[] v2 = PetSkillsJpanel.StringReplace(msg).split("\\|");
                    String[] v3 = v2[3].split("=");
                    if (skillId >= 4000 && skillId <= 4022) {
                        Skill skill = UserMessUntil.getSkillById(String.valueOf(skillId) + "");
                        if (skillId >= 4023) {
                            if (v3[1].contains("{守护石1}")) {
                                v3[1] = v3[1].replace("{守护石1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(skill.getValue1())) }) + "%#ca098c8");
                            }
                            if (v3[1].contains("{守护石2}")) {
                                v3[1] = v3[1].replace("{守护石2}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(skill.getValue2())) }) + "%#ca098c8");
                            }
                        }
                        else {
                            if (v3[1].contains("{守护石1}")) {
                                v3[1] = v3[1].replace("{守护石1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(skill.getValue1())) }) + "%#ca098c8");
                            }
                            if (v3[1].contains("{守护石2}")) {
                                v3[1] = v3[1].replace("{守护石2}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(skill.getValue2())) }) + "%#ca098c8");
                            }
                        }
                    }
                    buffer.append(v3[1]);
                }
                else if (zhi[0].equals("场数")) {
                    buffer.append("");
                }
                else if (zhi.length > 1 && !zhi[0].equals("召唤兽") && !zhi[0].equals("物品") && !zhi[0].equals("标签") && !zhi[0].equals("值") && !zhi[0].equals("皮肤") && !zhi[0].equals("方向") && !zhi[0].equals("变身卡类") && !zhi[0].equals("lvl") && !zhi[0].equals("刮奖") && !zhi[0].equals("卡类")) {
                    color = this.upColor(buffer, color, "#cDEDE9E");
                    buffer.append(zhi[0]);
                    buffer.append(" ");
                    buffer.append(zffh(zhi[1]));
                    buffer.append(tianjia(zhi[0]));
                }
            }
        }
        this.box.addText(buffer.toString(), 265, UIUtils.TEXT_FONT1);
    }
    /**
     * 展示物品
     */
    public int showGood(Goodstable good) {
        this.jy = AnalysisString.jiaoyi((long)good.getQuality());
        this.type = good.getType();
        this.goodsss = good;
        this.lock = good.getGoodlock();
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpath(good.getSkin()));
        if (good.getValue() != null) {
            String[] vcs = good.getValue().split("\\|");
            for (int i = 0; i < vcs.length; ++i) {
                String[] zhi = vcs[i].split("=");
                if (zhi[0].equals("制作人")) {
                    this.zhizuo = zhi[1];
                }
            }
        }
        if (good.getQht() != null && good.getQht() > 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#Y");
            buffer.append(good.getGoodsname());
            buffer.append("#r");
            for (int i = 0; i < (int)good.getQht(); ++i) {
                if (good.getQhv() == null || i > (int)good.getQhv()) {
                    buffer.append("#880");
                }
                else if (i < 3) {
                    buffer.append("#882");
                }
                else if (3 <= i && i < 6) {
                    buffer.append("#883");
                }
                else if (6 <= i && i < 9) {
                    buffer.append("#881");
                }
                else if (9 <= i && i < 12) {
                    buffer.append("#884");
                }
                else {
                    buffer.append("#885");
                }
            }
            this.box.removeAddText(buffer.toString(), 235, UIUtils.TEXT_COM_FONT);
        }
        else {
            this.box.removeAddText("#Y" + good.getGoodsname(), 235, UIUtils.TEXT_COM_FONT);
        }
        if (good.getInstruction() != null && !good.getInstruction().equals("")) {
            this.box.addText(good.getInstruction(), 255, UIUtils.TEXT_FONT1);
        }
        if (good.getMinute() != null && good.getCreateTime() != null) {
            int j = good.getMinute() * 60000;
            long timestamp = good.getCreateTime() + (long)j;
            Date date = new Date(timestamp);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            this.box.addText("过期时间:#R" + formattedDate, 255, UIUtils.TEXT_FONT1);//仙器装备物品说明描述
        }
        if (!Goodtype.CancelMsg(good.getType())) {//展示属性
            if (good.getType() == 520L) {//星卡类型
                this.showStarCard(good.getValue());
            }
            else if (good.getType() == 8888L) {//翅膀类型
                this.showWing(good.getValue());
            }
            else if (Goodtype.isSummonEquip(good.getType())) {//召唤兽装备
                this.showSummonEquip(good.getValue(), good.getType());
            }
            else if (Goodtype.isPalEquip(good.getType())) {//伙伴装备类
                this.showPalEquip(good.getValue(), good.getType());
            }
            else if (Goodtype.EquipmentType(good.getType()) != -1) {//人物装备类
                this.showEquipment(good, good.getType(), good.getQhv(), good.getGoodsname());
                this.code = good.getRgid().longValue();//只有装备才显示编号
            }
            else {//其他类
                this.showOther(good.getValue(), good.getType(), good);
            }
        }
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 30;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    /**
     * 展示Shop属性
     */
    public int showShop(Shop shop) {
        this.jy = false;
        this.lock = 0;
        this.type = 0L;
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpath(shop.getShopskin()));
        this.box.removeAddText("#Y" + shop.getShopname(), 255, UIUtils.TEXT_COM_FONT);
        if (shop.getShoptext() != null && !shop.getShoptext().equals("")) {
            this.box.addText(shop.getShoptext(), 235, UIUtils.TEXT_FONT);
        }
        int shoptype = shop.getShoptype();
        if (!Goodtype.CancelMsg((long)shoptype)) {
            this.showOther(shop.getValue(), (long)shoptype, null);
        }
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int showEshop(Eshop eshop) {
        this.jy = false;
        this.lock = 0;
        this.type = 0L;
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpath(eshop.getEshopskin()));
        this.box.removeAddText("#Y" + eshop.getEshopname(), 235, UIUtils.TEXT_COM_FONT);
        if (eshop.getEshoptext() != null && !eshop.getEshoptext().equals("")) {
            this.box.addText(eshop.getEshoptext(), 235, UIUtils.TEXT_FONT);
        }
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int showPayvipBean(PayvipBean payvipBean) {
        this.jy = false;
        this.lock = 0;
        this.type = 0L;
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("S" + payvipBean.getGrade(), 120, 120));
        this.box.removeAddText("#Y" + payvipBean.getPaynum() + "成长礼包", 235, UIUtils.TEXT_COM_FONT);
        if (payvipBean.getInstructiontext() != null && !payvipBean.getInstructiontext().equals("")) {
            this.box.addText(payvipBean.getInstructiontext(), 235, UIUtils.TEXT_FONT);
        }
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int showRoleTxBean(RoleTxBean roleTxBean) {
        this.jy = true;
        this.lock = 0;
        this.type = 0L;
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpath("tx" + roleTxBean.getRdId()));
        this.box.removeAddText("#Y" + roleTxBean.getRdName(), 235, UIUtils.TEXT_COM_FONT);
        if (roleTxBean.getRdAsk() != null && !roleTxBean.getRdAsk().equals("")) {
            this.box.addText(roleTxBean.getRdAsk(), 235, UIUtils.TEXT_FONT);
        }
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int goodmsg(ImageIcon icon, String goodname, String goodtext, String goodvalue, boolean jiaoyi, int goodlock, long type, String gemValue) {
        this.jy = jiaoyi;
        this.lock = goodlock;
        this.labgoodsimg.setIcon(icon);
        this.box.removemsg();
        this.box.addText("#Y" + goodname, 235, UIUtils.TEXT_COM_FONT);
        if (gemValue != null) {
            this.box.addText(gemValue, 235, UIUtils.TEXT_FONT);
        }
        this.box.addText((goodtext != null) ? goodtext : "", 235, UIUtils.TEXT_FONT);
        this.goodmsg(goodvalue, type);
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int goodsWingmsg(ImageIcon icon, String goodname, String goodtext, String goodvalue, boolean jiaoyi, int goodlock) {
        this.jy = jiaoyi;
        this.lock = goodlock;
        this.labgoodsimg.setIcon(icon);
        this.box.removemsg();
        this.box.addText("#Y" + goodname, 235, UIUtils.TEXT_FONT2);
        if (goodtext != null) {
            this.box.addText(goodtext, 235, UIUtils.TEXT_FONT);
        }
        else {
            this.box.addText("", 235, UIUtils.TEXT_FONT);
        }
        this.goodsWingmsg(goodvalue);
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int goodsStarCard(ImageIcon icon, String goodname, String goodtext, String goodvalue, boolean jiaoyi, int goodlock) {
        this.jy = jiaoyi;
        this.lock = goodlock;
        this.labgoodsimg.setIcon(icon);
        this.box.removemsg();
        this.box.addText("#Y" + goodname, 235, UIUtils.TEXT_FONT2);
        if (goodtext != null) {
            this.box.addText(goodtext, 235, UIUtils.TEXT_FONT);
        }
        else {
            this.box.addText("", 235, UIUtils.TEXT_FONT);
        }
        this.goodsStarCard(goodvalue);
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public int goodsSummonEquipmsg(ImageIcon icon, String goodname, String goodtext, String goodvalue, boolean jiaoyi, int goodlock) {
        this.jy = jiaoyi;
        this.lock = goodlock;
        this.labgoodsimg.setIcon(icon);
        this.box.removemsg();
        this.box.addText("#Y" + goodname, 235, UIUtils.TEXT_FONT2);
        if (goodtext != null) {
            this.box.addText(goodtext, 235, UIUtils.TEXT_FONT);
        }
        else {
            this.box.addText("", 235, UIUtils.TEXT_FONT);
        }
        this.goodsSummonEquipmsg(goodvalue);
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        }
        else {
            this.gheight = this.box.getHeight() + 20;
        }
        this.setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;
    }
    
    public void goodsSummonEquipmsg(String value) {
        if (value != null && !value.equals("")) {
            String[] split = value.split("\\|");
            boolean is = true;
            for (int i = 0; i < split.length; ++i) {
                this.goodsSummonEquipValueMessage(split[i], split);
                if (split[i].startsWith("觉醒技")) {
                    is = false;
                }
            }
            if (is) {
                this.box.addText("#c00FDF1未开启觉醒（开启铃、环、甲之觉醒，可领悟觉醒技）", 235, UIUtils.TEXT_FONT);
            }
        }
    }
    
    public void goodsSummonEquipValueMessage(String message, String[] value) {
        if (message.startsWith("炼化属性")) {
            String[] split = message.split("&");
            for (int i = 1; i < split.length; ++i) {
                String[] split2 = split[i].split("=");
                if (split2[0].endsWith("等级")) {
                    this.box.addText("#c00ff00" + split2[0] + " " + split2[1] + "级", 235, UIUtils.TEXT_FONT);
                }
                else {
                    this.box.addText("#c00ff00" + split2[0] + " " + zffh(split2[1]) + tianjia(split2[0]), 235, UIUtils.TEXT_FONT);
                }
            }
        }
        else if (message.startsWith("觉醒技")) {
            String[] split = message.split("&");
            Skill skill = UserMessUntil.getSkillId(split[1]);
            if (skill != null) {
                this.box.addText(" #r", 235, UIUtils.TEXT_FONT);
                long lvl = JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[3]));
                this.box.addText("#cFFFF00【觉醒技】 " + skill.getSkillname() + "(" + split[2] + ")", 235, UIUtils.TEXT_FONT);
                this.box.addText("#cFFFFFF【觉醒技等级】" + lvl, 235, UIUtils.TEXT_FONT);
                this.box.addText("#cFFFFFF【类型】 通用", 235, UIUtils.TEXT_FONT);
                this.box.addText("#c00FF00" + SummonSkillRemark(skill.getRemark(), skill, split[2], lvl + ""), 235, UIUtils.TEXT_FONT);
                this.box.addText("#cC5C583 铃、环、甲觉醒三合一，觉醒技方可生效", 235, UIUtils.TEXT_FONT);
            }
        }
        else {
            String[] split = message.split("=");
            if (split[0].equals("等级")) {
                this.box.addText("#cFFFFFF【" + split[0] + "】 " + split[1], 235, UIUtils.TEXT_FONT);
            }
            else if (split[0].equals("装备部位")) {
                this.box.addText("#cFFFFFF【" + split[0] + "】 " + split[1], 235, UIUtils.TEXT_FONT);
            }
            else if (split[0].equals("品质")) {
                this.box.addText("#cFFFF00【" + split[0] + "】 " + split[1] + "/100", 235, UIUtils.TEXT_FONT);
            }
            else if (split[0].equals("通灵")) {
                int level = Integer.parseInt(JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(value, "等级"));
                this.box.addText("#cFFFF00【" + split[0] + "】 " + split[1] + "/" + level * 1000, 235, UIUtils.TEXT_FONT);
            }
            else {
                this.box.addText("#cFFFF00【" + split[0] + "】 " + split[1], 235, UIUtils.TEXT_FONT);
            }
        }
    }
    
    public static String SummonSkillRemark(String remark, Skill skill, String sld, String lvl) {
        remark = remark.replace("{公式一}", "#R" + (int)Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))) + "#G");
        remark = remark.replace("{公式二}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.8")) + "#G");
        remark = remark.replace("{公式三}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.7")) + "#G");
        remark = remark.replace("{公式四}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.3")) + "#G");
        remark = remark.replace("{公式五}", "#R" + (int)Arith.sub(Double.parseDouble("100"), Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl)))) + "#G");
        remark = remark.replace("{公式六}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.25")) + "#G");
        remark = remark.replace("{公式七}", "#R" + (int)Arith.add(Double.parseDouble("100"), Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl)))) + "#G");
        remark = remark.replace("{公式八}", "#R" + (int)Arith.add(Double.parseDouble("1"), Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.07"))) + "#G");
        remark = remark.replace("{公式九}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.5")) + "#G");
        remark = remark.replace("{公式十}", "#R" + (int)Arith.mul(Arith.mul(Arith.div(Arith.mul(Double.parseDouble(skill.getValue()), Double.parseDouble(sld)), 5.0, 0), Math.sqrt(Double.parseDouble(lvl))), Double.parseDouble("0.05")) + "#G");
        //灯火阑珊技能描述
        remark = remark.replace("{公式十一}", "#R" + (int) (Double.parseDouble(skill.getValue()) * Double.parseDouble(sld) * Double.parseDouble(lvl)) + "#G");
        remark = remark.replace("{公式十二}", "#R" + (int) (Double.parseDouble(skill.getGrow()) * Double.parseDouble(sld) * Double.parseDouble(lvl)) + "#G");
        return remark;
    }
    
    public void goodmsg(String value, long type) {
        if (value != null && !value.equals("")) {
            String[] v = value.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                this.goodmsg1(v[i], type);
            }
        }
    }
    
    public void goodsWingmsg(String value) {
        if (value != null && !value.equals("")) {
            String[] v = value.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                this.goodsWingmsg1(v[i], v);
            }
        }
    }
    
    public void goodsStarCard(String value) {
        if (value != null && !value.equals("")) {
            String[] v = value.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                this.goodsStarCard(v[i], v);
            }
        }
    }
    
    public void goodsWingmsg1(String field, String[] valueSplit) {
        if (field.length() >= 4) {
            String vj = field.substring(0, 4);
            if (vj.equals("炼化属性")) {
                this.box.addText("#c978FC3【炼化属性】", 235, UIUtils.TEXT_FONT);
                String[] v = field.split("&");
                for (int i = 1; i < v.length; ++i) {
                    String[] vs = v[i].split("=");
                    if (!vs[0].equals("特技")) {
                        this.box.addText("#c00ff00" + vs[0] + " " + zffh(vs[1]) + tianjia(vs[0]), 235, UIUtils.TEXT_FONT);
                    }
                    else {
                        this.box.addText("#c868090特技", 235, UIUtils.TEXT_FONT);
                        for (int j = 1; j < vs.length; ++j) {
                            Skill skill = UserMessUntil.getSkillId(vs[j]);
                            if (skill != null) {
                                this.box.addText("#c868090" + skill.getSkillname() + ":" + skill.getRemark(), 235, UIUtils.TEXT_FONT);
                            }
                        }
                    }
                }
                return;
            }
        }
        String[] zhi = field.split("=");
        if (zhi[0].equals("品质")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + WingMainPanel.getQualityColorOx(zhi[1]) + zhi[1], 235, UIUtils.TEXT_FONT);
        }
        else if (zhi[0].equals("星级")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】#cFFFF00 " + zhi[1] + "/15", 235, UIUtils.TEXT_FONT);
        }
        else if (zhi[0].equals("经验")) {
            this.box.addText("#cFFFFFF【等级】 " + WingMainPanel.getWingLevel(zhi[1]), 235, UIUtils.TEXT_FONT);
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + zhi[1], 235, UIUtils.TEXT_FONT);
        }
        else if (zhi[0].equals("颜色")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + zhi[1], 235, UIUtils.TEXT_FONT);
        }
        else if (zhi[0].equals("力量") || zhi[0].equals("灵性") || zhi[0].equals("根骨") || zhi[0].equals("敏捷")) {
            int parseIntStar = Integer.parseInt(WingMainFrame.getWingMainFrame().getWingMainPanel().getGoodsValue(valueSplit, "星级"));
            String qualityAttr = WingMainFrame.getWingMainFrame().getWingMainPanel().getGoodsValue(valueSplit, "品质");
            ChatBox box = this.box;
            StringBuilder append = new StringBuilder().append("#cCDCA6D").append(zhi[0]).append(" ");
            WingMainFrame.getWingMainFrame().getWingMainPanel();
            box.addText(append.append(WingMainPanel.getUpStarData(zhi[1], parseIntStar, qualityAttr)).append("#cFFFFFF(").append(zhi[1]).append(")").toString(), 235, UIUtils.TEXT_FONT);
        }
        else if (zhi.length > 1 && !zhi[0].equals("召唤兽") && !zhi[0].equals("物品") && !zhi[0].equals("标签") && !zhi[0].equals("值") && !zhi[0].equals("皮肤") && !zhi[0].equals("方向") && !zhi[0].equals("变身卡类") && !zhi[0].equals("lvl") && !zhi[0].equals("刮奖") && !zhi[0].equals("卡类")) {
            this.box.addText("#cDEDE9E" + zhi[0] + " " + zffh(zhi[1]) + tianjia(zhi[0]), 235, UIUtils.TEXT_FONT);
        }
    }
    
    public void goodsStarCard(String field, String[] valueSplit) {
        if (field.length() >= 4) {
            String vj = field.substring(0, 4);
            if (vj.equals("炼化属性")) {
                String[] v = field.split("&");
                String[] split = v[1].split("=");
                if (split[0].equals("资质")) {
                    this.box.addText("#cFFFFFF【神通】" + split[0] + " " + split[1] + "/100", 235, UIUtils.TEXT_FONT78);
                }
                for (int i = 2; i < v.length; ++i) {
                    String[] vs = v[i].split("=");
                    if (!vs[0].equals("星阵属性")) {
                        this.box.addText("#c00ff00" + vs[0] + " " + zffh(vs[1]) + tianjia(vs[1]), 235, UIUtils.TEXT_FONT78);
                    }
                }
                return;
            }
            else if (vj.equals("五行属性")) {
                this.box.addText("#cFFFFFF【五行】", 235, UIUtils.TEXT_FONT78);
                String[] v = field.split("&");
                String[] split = valueSplit[3].split("&");
                boolean is = true;
                if (split.length > 4) {
                    String[] starArray = split[4].split("=");
                    double num = 0.0;
                    for (int j = 1; j < v.length; ++j) {
                        String[] vs2 = v[j].split("=");
                        this.box.addText("#cFFFF00" + vs2[0] + " " + vs2[1] + "/100", 235, UIUtils.TEXT_FONT78);
                        num += JpanelStarCardMain.fiveElementRestrainCreate(starArray[2], vs2[0], vs2[1]);
                    }
                    this.box.addText("#c4ADEDD五行加成星阵之力 " + String.format("%.1f", new Object[] { Double.valueOf(num) }) + "%", 235, UIUtils.TEXT_FONT78);
                    is = false;
                    this.box.addText("#c4ADEDD【星阵】", 235, UIUtils.TEXT_FONT);
                    this.box.addText("#c4ADEDD" + starArray[1] + "(" + starArray[2] + ")", 235, UIUtils.TEXT_FONT78);
                    if (this.isStarArrayName(starArray[1])) {
                        this.box.addText("#cFFFFFF赤帝宫 #c00FF00" + starArray[3], 235, UIUtils.TEXT_FONT78);
                        this.box.addText("#cFFFFFF青帝宫 #c00FF00" + starArray[4], 235, UIUtils.TEXT_FONT78);
                        this.box.addText("#cFFFFFF黄帝宫 #c00FF00" + starArray[5], 235, UIUtils.TEXT_FONT78);
                        this.box.addText("#cFFFFFF白帝宫 #c00FF00" + starArray[6], 235, UIUtils.TEXT_FONT78);
                        this.box.addText("#cFFFFFF黑帝宫 #c00FF00" + starArray[7], 235, UIUtils.TEXT_FONT78);
                    }
                    this.box.addText("#c00FF00" + this.getStarArrayAttribute(starArray[1]), 235, UIUtils.TEXT_FONT78);
                }
                else {
                    for (int k = 1; k < v.length; ++k) {
                        String[] vs3 = v[k].split("=");
                        this.box.addText("#cFFFF00" + vs3[0] + " " + vs3[1] + "/100", 235, UIUtils.TEXT_FONT78);
                    }
                }
                if (is) {
                    this.box.addText("#c4ADEDD无星阵，五行暂不生效", 235, UIUtils.TEXT_FONT78);
                }
                return;
            }
        }
        String[] zhi = field.split("=");
        if (zhi[0].equals("品质")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + WingMainPanel.getQualityColorOx(zhi[1]) + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("神力")) {
            int lvlNow = Integer.parseInt(valueSplit[0].split("=")[1].split("/")[0]);
            this.box.addText("#cFFFFFF【" + zhi[0] + "】#cFFFF00 " + zhi[1] + "/" + lvlNow * 200, 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("战力")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】#cFFFF00 " + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("等级")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("颜色")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】 " + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("力量") || zhi[0].equals("灵性") || zhi[0].equals("根骨") || zhi[0].equals("敏捷")) {
            int parseIntStar = Integer.parseInt(WingMainFrame.getWingMainFrame().getWingMainPanel().getGoodsValue(valueSplit, "星级"));
            String qualityAttr = WingMainFrame.getWingMainFrame().getWingMainPanel().getGoodsValue(valueSplit, "品质");
            ChatBox box = this.box;
            StringBuilder append = new StringBuilder().append("#cCDCA6D").append(zhi[0]).append(" ");
            WingMainFrame.getWingMainFrame().getWingMainPanel();
            box.addText(append.append(WingMainPanel.getUpStarData(zhi[1], parseIntStar, qualityAttr)).append("#cFFFFFF(").append(zhi[1]).append(")").toString(), 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi.length > 1 && !zhi[0].equals("召唤兽") && !zhi[0].equals("物品") && !zhi[0].equals("标签") && !zhi[0].equals("值") && !zhi[0].equals("皮肤") && !zhi[0].equals("方向") && !zhi[0].equals("变身卡类") && !zhi[0].equals("lvl") && !zhi[0].equals("刮奖") && !zhi[0].equals("卡类")) {
            this.box.addText("#cDEDE9E" + zhi[0] + " " + zffh(zhi[1]) + tianjia(zhi[0]), 235, UIUtils.TEXT_FONT78);
        }
    }
    
    public boolean isStarArrayName(String name) {
        return name.equals("朱雀") || name.equals("青龙") || name.equals("白虎") || name.equals("玄武");
    }
    
    public String getStarArrayAttribute(String name) {
        if (name.equals("朱雀")) {
            return "瑕疵:略微减少全队冰混睡忘抗性";
        }
        if (name.equals("青龙")) {
            return "瑕疵:略微减少全队仙法抗性";
        }
        if (name.equals("白虎")) {
            return "瑕疵:略微减少全队鬼火、三尸虫抗性";
        }
        if (name.equals("玄武")) {
            return "瑕疵:略微减少全队震慑抗性";
        }
        if (name.equals("金牛")) {
            return "本方所有人物、召唤兽对敌方造成的物理伤害有一定的加成";
        }
        if (name.equals("火猿")) {
            return "若对方灵宝对本方任意单位造成伤害，抵抗一定程度伤害；每2回合可生效一次";
        }
        if (name.equals("赤马")) {
            return "本方所有人物、召唤兽获得一定经验加成";
        }
        if (name.equals("黄鹤")) {
            return "本方所有人物增加冰混睡忘抗性";
        }
        return "本方所有人物、召唤兽的仙法、鬼火、震慑有一定加成";
    }
    
    public static String tianjia(String v) {
        if (v.indexOf("抗") != -1) {
            if (v.equals("抗三尸虫") || v.equals("抗三尸") || v.equals("抗浩然正气") || v.equals("抗青面獠牙") || v.equals("抗天魔解体") || v.equals("抗小楼夜哭") || v.equals("抗分光化影") || v.equals("抗毒伤") || v.equals("抗反震") || v.equals("抗毒伤害") || v.equals("加强三尸虫") || v.equals("加强三尸") || v.equals("强三尸虫")) {
                return "";
            }
            return "%";
        }
        else {
            if ((v.indexOf("强") != -1 || v.indexOf("程度") != -1 || v.indexOf("率") != -1 || v.indexOf("附加混") != -1 || v.indexOf("附加封") != -1 || v.indexOf("狂暴") != -1 || v.indexOf("命中") != -1 || v.indexOf("致命") != -1 || v.indexOf("忽视") != -1 || v.indexOf("属性需求") != -1 || v.indexOf("附加中毒攻击") != -1 || v.indexOf("附加风法攻击") != -1 || v.indexOf("附加火法攻击") != -1 || v.indexOf("附加水法攻击") != -1 || v.indexOf("附加雷法攻击") != -1 || v.indexOf("附加震慑攻击") != -1 || v.indexOf("附加三尸攻击") != -1) && !v.equals("加强三尸虫") && v.indexOf("次数") == -1 && !v.endsWith("值")) {
                return "%";
            }
            return "";
        }
    }
    
    public static String zffh(String v) {
        StringBuffer buffer = new StringBuffer();
        try {
            double z = Double.parseDouble(v);
            if (z > 0.0) {
                buffer.append("+");
            }
            String[] vs = v.split("\\.");
            buffer.append(vs[0]);
            if (vs.length >= 2) {
                buffer.append(".");
                buffer.append(vs[1].charAt(0));
            }
            return buffer.toString();
        }
        catch (Exception ex) {
            return v;
        }
    }
    
    public void goodmsg1(String field, long type) {
        if (field.length() >= 4) {
            if (field.startsWith("炼化属性")) {
                boolean is = Goodtype.GodEquipment_God(type) || Goodtype.GodEquipment_xian(type) || Goodtype.GodEquipment_Ding(type);
                String[] v = field.split("&");
                for (int i = 1; i < v.length; ++i) {
                    String[] vs = v[i].split("=");
                    if (!vs[0].equals("特技")) {
                        this.box.addText((is ? "#c00f8f8" : "#c00ff00") + vs[0] + " " + zffh(vs[1]) + tianjia(vs[0]), 235, UIUtils.TEXT_FONT78);
                    }
                    else {
                        this.box.addText("#c868090特技", 235, UIUtils.TEXT_FONT78);
                        for (int j = 1; j < vs.length; ++j) {
                            Skill skill = UserMessUntil.getSkillId(vs[j]);
                            if (skill != null) {
                                this.box.addText("#c868090" + skill.getSkillname() + ":" + skill.getRemark(), 235, UIUtils.TEXT_FONT78);
                            }
                        }
                    }
                }
                return;
            }
            else if (field.startsWith("炼器属性")) {
                String[] v2 = field.split("&");
                this.box.addText("#W【炼器】#c00EAFF开光次数 " + v2[1], 235, UIUtils.TEXT_FONT78);
                for (int k = 2; k < v2.length; ++k) {
                    String[] vs2 = v2[k].split("=");
                    this.box.addText("#c00ff00" + vs2[0] + " " + zffh(vs2[1]) + tianjia(vs2[0]), 235, UIUtils.TEXT_FONT78);
                }
                return;
            }
            else if (field.startsWith("神兵属性")) {
                String[] v2 = field.split("&");
                for (int k = 1; k < v2.length; ++k) {
                    String[] vs2 = v2[k].split("=");
                    this.box.addText("#cBE9786" + vs2[0] + " " + zffh(vs2[1]) + tianjia(vs2[0]), 235, UIUtils.TEXT_FONT78);
                }
                return;
            }
            else if (field.startsWith("套装属性")) {
                this.box.addText("#c00ffff【套装属性】", 235, UIUtils.TEXT_FONT78);
                String[] v2 = field.split("&");
                for (int k = 4; k < v2.length; ++k) {
                    String[] vs2 = v2[k].split("=");
                    this.box.addText("#c00ff00" + vs2[0] + " " + zffh(vs2[1]) + tianjia(vs2[0]), 235, UIUtils.TEXT_FONT78);
                }
                this.box.addText("#c00ffff【套装品质:" + v2[3] + "】", 235, UIUtils.TEXT_FONT78);
                int suitId = Integer.parseInt(v2[1]);
                RoleSuitBean suit = UserMessUntil.getSuit(suitId);
                if (suit != null) {
                    int sum = RoleProperty.getRoleProperty().getSuitSum(v2[1]);
                    String[] vs3 = suit.getHaveSkill().split("\\|");
                    for (int l = 0; l < vs3.length; ++l) {
                        String[] vss = vs3[l].split("-");
                        int maxsum = Integer.parseInt(vss[0]);
                        Skill skill2 = UserMessUntil.getSkillId(vss[1]);
                        if (skill2 != null) {
                            if (sum >= maxsum) {
                                this.box.addText("#c00ff00[" + maxsum + "/" + maxsum + "]" + skill2.getSkillname(), 235, UIUtils.TEXT_FONT78);
                            }
                            else {
                                this.box.addText("#c807876[" + sum + "/" + maxsum + "]" + skill2.getSkillname(), 235, UIUtils.TEXT_FONT78);
                            }
                        }
                    }
                }
                return;
            }
            else if (field.startsWith("宝石属性")) {
                this.box.addText("#c00ffff【宝石镶嵌】", 235, UIUtils.TEXT_FONT78);
                String[] v2 = field.split("&");
                for (int k = 1; k < v2.length; ++k) {
                    Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v2[k]));
                    if (good != null) {
                        this.box.imageUpdate(this.daoju.getImage(), 1, 100, 100, 20, 20);
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("#cEA5700");
                        buffer.append(good.getGoodsname());
                        String[] bs = good.getValue().split("\\|");
                        buffer.append(" ");
                        buffer.append(bs[0].split("=")[1]);
                        buffer.append("级 ");
                        bs = bs[1].split("=");
                        buffer.append(bs[0]);
                        buffer.append(" ");
                        buffer.append(zffh(bs[1]) + tianjia(bs[1]));
                        this.box.addText(buffer.toString(), 235, UIUtils.TEXT_FONT78);
                    }
                }
                return;
            }
        }
        String[] zhi = field.split("=");
        if (zhi[0].equals("装备角色") || zhi[0].equals("等级") || zhi[0].equals("装备等级") || zhi[0].equals("阶数") || zhi[0].equals("等级要求") || zhi[0].equals("力量要求") || zhi[0].equals("灵性要求") || zhi[0].equals("根骨要求") || zhi[0].equals("敏捷要求") || zhi[0].equals("套装品质")) {
            this.box.addText("#cFFFFFF【" + zhi[0] + "】" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("装备部位")) {
            String value = AccessSuitMsgUntil.returnPartsName(zhi[1]);
            if (value != null) {
                zhi[1] = value;
            }
            this.box.addText("#cFFFFFF【装备部位】" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("性别要求") || zhi[0].equals("性别")) {
            if (zhi[1].equals("1")) {
                this.box.addText("#cFFFFFF【性别要求】男", 235, UIUtils.TEXT_FONT78);
            }
            else if (zhi[1].equals("0")) {
                this.box.addText("#cFFFFFF【性别要求】女", 235, UIUtils.TEXT_FONT78);
            }
            else {
                this.box.addText("#cFFFFFF【性别要求】全部性别", 235, UIUtils.TEXT_FONT78);
            }
        }
        else if (zhi[0].equals("HP") || zhi[0].equals("MP") || zhi[0].equals("HP%") || zhi[0].equals("MP%") || zhi[0].equals("经验") || zhi[0].equals("亲密") || zhi[0].equals("钱") || zhi[0].equals("点")) {
            this.box.addText("#cFFFFFF" + zhi[0] + " " + zffh(zhi[1]), 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("力量") || zhi[0].equals("灵性") || zhi[0].equals("根骨") || zhi[0].equals("敏捷")) {
            this.box.addText("#cCCCC99" + zhi[0] + " " + zffh(zhi[1]), 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("培养") || zhi[0].equals("品质")) {
            this.box.addText("#c1E90FF" + zhi[0] + " " + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("坐标") || zhi[0].equals("内丹等级")) {
            this.box.addText("#cFFFF33" + zhi[0] + ":" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("次数")) {
            this.box.addText("#cFFFF33" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("炼妖次数")) {
            this.box.addText("#c00BFFF" + zhi[0] + " " + zffh(zhi[1]), 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("描述") || zhi[0].equals("名称")) {
            this.box.addText("#c00BFFF" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("传送") || zhi[0].equals("宝图")) {
            String[] v = zhi[1].split(",");
            int x = Integer.parseInt(v[2]) / 20;
            int y = Integer.parseInt(v[3]) / 20;
            this.box.addText("#cFFFF33坐标: " + v[1] + " (" + x + "," + y + ")", 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("可用次数")) {
            this.box.addText("#cFFFF33还可以使用" + zhi[1] + "次", 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("技能")) {
            this.box.addText("#cFFFF33技能:#G" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("技能等级")) {
            this.box.addText("#cFFFF33技能等级:#G" + zhi[1], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("耐久")) {
            this.box.addText("#cFFFF33耐久:#G" + zhi[1].split(",")[0], 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi[0].equals("场数")) {
            this.box.addText("", 235, UIUtils.TEXT_FONT78);
        }
        else if (zhi.length > 1 && !zhi[0].equals("召唤兽") && !zhi[0].equals("物品") && !zhi[0].equals("标签") && !zhi[0].equals("值") && !zhi[0].equals("皮肤") && !zhi[0].equals("方向") && !zhi[0].equals("变身卡类") && !zhi[0].equals("lvl") && !zhi[0].equals("刮奖") && !zhi[0].equals("卡类")) {
            this.box.addText("#cDEDE9E" + zhi[0] + " " + zffh(zhi[1]) + tianjia(zhi[0]), 235, UIUtils.TEXT_FONT78);
        }
    }
    
    public int getGwidth() {
        return this.gwidth;
    }
    
    public void setGwidth(int gwidth) {
        this.gwidth = gwidth;
    }
    
    public int getGheight() {
        return this.gheight;
    }
    
    public void setGheight(int gheight) {
        this.gheight = gheight;
    }
    
    public JLabel getLabgoodsimg() {
        return this.labgoodsimg;
    }
    
    public void setLabgoodsimg(JLabel labgoodsimg) {
        this.labgoodsimg = labgoodsimg;
    }
    
    public static List<Goodstable> accessIdlEqu(int type) {
        List<Goodstable> listEqui = new ArrayList<>();
        for (int i = 0; i < GoodsListFromServerUntil.choseGoodsList.length; ++i) {
            if (GoodsListFromServerUntil.choseGoodsList[i] != null) {
                long goodid = GoodsListFromServerUntil.choseGoodsList[i].getGoodsid().longValue();
                long goodtype = (long)GoodsListFromServerUntil.choseGoodsList[i].getType();
                if (Goodtype.EquipmentType(goodtype) != -1 && Goodtype.EquipmentType(goodtype) != 0 && Goodtype.EquipmentType(goodtype) != 4 && Goodtype.EquipmentType(goodtype) != 5 && goodid != 6029L && goodid != 6030L && goodid != 6031L && goodid != 6032L && goodid != 6033L && goodid != 6034L) {
                    if (type == 2) {
                        if (GoodsListFromServerUntil.choseGoodsList[i].getValue().indexOf("套装属性") != -1) {
                            listEqui.add(GoodsListFromServerUntil.choseGoodsList[i]);
                        }
                    }
                    else if (type == 1 && GoodsListFromServerUntil.choseGoodsList[i].getValue().indexOf("套装属性") == -1) {
                        listEqui.add(GoodsListFromServerUntil.choseGoodsList[i]);
                    }
                }
            }
        }
        return listEqui;
    }
    public int showXuanbao(XuanBao xuanBao) {
        if (xuanBao.getSkill_1() == null) {
            (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(xuanBao);

        }
        this.labgoodsimg.setIcon(GoodsListFromServerUntil.imgpath(xuanBao.getSkin()));
        this.box.removeAddText("#Y" + xuanBao.getName(), 235, UIUtils.TEXT_FONT22);
//        this.box.removeAddText(" ", 255, UIUtils.TEXT_FONT19);
//        this.goodName = xuanBao.getName();
        this.box.addText(xuanBao.getRemark(), 255, UIUtils.TEXT_FONT1);
        String color = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append("【").append("玄宝等级").append("】").append(xuanBao.lvl);
        buffer.append("#r");
        buffer.append("【").append("玄宝品质").append("】").append(xuanBao.pinzhi);
        buffer.append("#r");
        buffer.append("【").append("玄宝类型").append("】").append(xuanBao.type);
        buffer.append("#r");
        buffer.append("【").append("适用角色").append("】").append(xuanBao.rolelvl);
        buffer.append("#r");
        int xiaohao = 0;
        Skill skill = UserMessUntil.getskillformname(xuanBao.name);
        if (skill != null) {
            xiaohao = (int) Double.parseDouble(skill.getDielectric());

        }
        buffer.append("【").append("技能消耗").append("】").append(xiaohao);
        buffer.append("玄元");
        buffer.append("#r");
        int lengque = 0;
        if (xuanBao.getId() == 1) {
            lengque = 10 * xuanBao.getLvl();
        }
        buffer.append("【").append("冷却回合").append("】").append(lengque);
        buffer.append("#r");
        color = upColor(buffer, color, "#c00FF00");
        buffer.append(xuanBao.getSkill_1()).append("#r");
        color = upColor(buffer, color, "#cCCCC99");
        buffer.append("【").append("玄印").append("】#r");
        this.box.addText(buffer.toString(), 255, UIUtils.TEXT_FONT1);
        StringBuffer buffer1 = new StringBuffer();
        String[] rgb = xuanBao.getRgb().split("\\|");
        int num = xuanBao.getNum();
        int i = 0;

        if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
            String[] fushi = xuanBao.getFushi().split("\\|");
            for (; i < fushi.length; i++) {
                Goodstable goodstable = GoodsListFromServerUntil.fushis.get(new BigDecimal(fushi[i]));
                if (goodstable != null) {
                    if (goodstable.getType() == 10018L) {
                        if (goodstable.getGoodsname().equals("红峰印")) {
                            buffer1.append("#x70" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("红磐印"))  {
                            buffer1.append("#x71" + goodstable.getValue()).append("#r");
                        }
                    } else if (goodstable.getType() == 10012L) {
                        if (goodstable.getGoodsname().equals("蓝煞印"))  {
                            buffer1.append("#x72" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("蓝罡印")) {
                            buffer1.append("#x73" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("蓝破印")) {
                            buffer1.append("#x74" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("蓝影印")) {
                            buffer1.append("#x75" + goodstable.getValue()).append("#r");
                        }
                    } else if (goodstable.getType() == 10013L) {
                        if (goodstable.getGoodsname().equals("绿生印")) {
                            buffer1.append("#x76" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("绿息印")) {
                            buffer1.append("#x77" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("绿灵印")) {
                            buffer1.append("#x78" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("绿源印")) {
                            buffer1.append("#x79" + goodstable.getValue()).append("#r");
                        }
                    } else if (goodstable.getType() == 10014L) {
                        if (goodstable.getGoodsname().equals("黄定印")) {
                            buffer1.append("#x80" + goodstable.getValue()).append("#r");
                        } else if (goodstable.getGoodsname().equals("黄韧印")) {
                            buffer1.append("#x81" + goodstable.getValue()).append("#r");
                        }
                    }
                }
            }
        }

        for (; i < num; i++) {
            buffer1.append("#x69").append("#r");
        }
        for (; i < rgb.length; i++) {
            buffer1.append("#x68 未开启").append("#r");
        }

        this.box.addText(buffer1.toString(), 255, UIUtils.TEXT_FONT1);
        StringBuffer buffer3 = new StringBuffer();
        buffer3.append("【").append("技能效果").append("】#r");
        upColor(buffer3, color, "#cCCCC99");
        this.box.addText(buffer3.toString(), 255, UIUtils.TEXT_FONT1);
        String color2 = null;
        StringBuffer buffer2 = new StringBuffer();
        color2 = upColor(buffer2, color2, "#c00FF00");


        List<String> contents = new ArrayList<>();
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xuanBao.skill2);
        while (matcher.find()) {
            contents.add(matcher.group(1));

        }
        String[] mes = contents.get(0).split(",");
        StringBuffer me = new StringBuffer();
        for (int k = 0; k <= mes.length - 1; k++) {
            if (mes[k].equals("红")) {
                me.append("#x60");
            } else if (mes[k].equals("蓝")) {
                me.append("#x61");
            } else if (mes[k].equals("黄")) {
                me.append("#x62");
            } else if (mes[k].equals("绿")) {
                me.append("#x63");

            }

        }
        buffer2.append(me.toString() + "  " + xuanBao.getSkill_2().replace("黄", "#Y黄#G").replace("蓝", "#B蓝#G").replace("红", "#R红#G")).append("#r").append("#r");
        contents.clear();
        matcher = pattern.matcher(xuanBao.skill3);
        while (matcher.find()) {
            contents.add(matcher.group(1));

        }
        mes = contents.get(0).split(",");
        StringBuffer me1 = new StringBuffer();
        for (int j = 0; j <= mes.length - 1; j++) {
            if (mes[j].equals("红")) {
                me1.append("#x64");
            } else if (mes[j].equals("蓝")) {
                me1.append("#x65");
            } else if (mes[j].equals("黄")) {
                me1.append("#x66");
            } else if (mes[j].equals("绿")) {
                me1.append("#x67");
            }

        }
        buffer2.append(me1.toString() + "  " + xuanBao.getSkill_3().replace("黄", "#Y黄#G").replace("蓝", "#B蓝#G").replace("红", "#R红#G")).append("#r").append("#r");
        contents.clear();
        matcher = pattern.matcher(xuanBao.skill2);
        while (matcher.find()) {
            contents.add(matcher.group(1));

        }
        mes = ((String) contents.get(0)).split(",");
        StringBuffer me2 = new StringBuffer();
        for (int m = 0; m <= mes.length - 1; m++) {
            if (mes[m].equals("红")) {
                me2.append("#x64");
            } else if (mes[m].equals("蓝")) {
                me2.append("#x65");
            } else if (mes[m].equals("黄")) {
                me2.append("#x66");
            } else if (mes[m].equals("绿")) {
                me2.append("#x67");

            }

        }
        if (xuanBao.getSkill_4() != null && !xuanBao.getSkill_4().equals(""))
            buffer2.append(me2.toString() + "  " + xuanBao.getSkill_4().replace("黄", "#Y黄#G").replace("蓝", "#B蓝#G").replace("红", "#R红#G")).append("#r").append("#r");
        this.box.addText(buffer2.toString(), 255, UIUtils.TEXT_FONT);
        if (this.box.getHeight() < 140) {
            this.gheight = 160;
        } else {
            this.gheight = this.box.getHeight() + 20;
        }
        setPreferredSize(new Dimension(this.gwidth, this.gheight));
        this.imgZoom.setMiddlew(this.gwidth - 2 * this.imgZoom.getEdgew());
        this.imgZoom.setMiddleh(this.gheight - 2 * this.imgZoom.getEdgeh());
        return this.gheight;

    }
    static {
        GoodsMsgJpanel.NUMS = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        GoodsMsgJpanel.tcp14 = SpriteFactory.VloadSprite("resource/NewRoleUi/36角色/最新特效/14.tcp", null);
        GoodsMsgJpanel.tcp15 = SpriteFactory.VloadSprite("resource/NewRoleUi/36角色/最新特效/15.tcp", null);
        GoodsMsgJpanel.tcp16 = SpriteFactory.VloadSprite("resource/NewRoleUi/36角色/最新特效/19.tcp", null);
        GoodsMsgJpanel.tcp17 = SpriteFactory.VloadSprite("resource/NewRoleUi/36角色/最新特效/7.tcp", null);
        GoodsMsgJpanel.tcp18 = SpriteFactory.VloadSprite("resource/NewRoleUi/36角色/最新特效/17.tcp", null);
    }
}
