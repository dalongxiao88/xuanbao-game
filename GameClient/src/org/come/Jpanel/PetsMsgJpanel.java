package org.come.Jpanel;

import org.come.until.CutButtonImage;
import org.come.bean.Skill;

import java.util.*;

import org.come.mouslisten.PetEquipMouslisten;
import org.come.until.JmSum;
import com.gl.util.LingXiUtil;
import io.netty.util.internal.StringUtil;
import com.tool.pet.PetProperty;
import org.come.until.AnalysisString;
import org.come.until.GsonUtil;
import org.apache.commons.lang.StringUtils;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.RoleSummoning;
import com.tool.image.ImageMixDeal;
import com.updateNew.MyIsif;
import org.come.bean.ConfigureBean;
import java.awt.Graphics;
import com.gl.util.Xy2Util;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.entity.Goodstable;
import com.tool.tcp.NewPart;
import com.tool.tcpimg.RichLabel;

import javax.swing.*;

public class PetsMsgJpanel extends JPanel
{
    private int lock;
    private String type;
    private int gheight;
    private ImageIcon kaiqi;
    private ImageIcon kaiqi1;
    private ImageIcon qiling;
    private ImageIcon tianfu;
    private ImageIcon jzjy;
    private JLabel qilingcount;
    private JLabel itemText;
    private JLabel[] labFive;
    private JLabel[] labPart;
    private JLabel[] labSkillImg;
    private JLabel[] labSkillName;
    private JLabel[] qlSkillImg;
    private JLabel[] qlSkillName;
    private RichLabel richLabel;
    private JLabel qlLab;
    private JLabel tfLab;
    private JLabel tianfucount;
    private NewPart part;
    public static ImageIcon icon;
    private ImageIcon shenshou1;
    private ImageIcon shenshou;
    private ImageIcon icon1;
    private List<Goodstable> nds;
    
    public PetsMsgJpanel(int gheight) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int ndsl = 4;
        if (configure.getNdslsx() != null) {
            ndsl = Integer.parseInt(configure.getNdslsx());
        }
        int numskll = 9;
        if (configure.getZhsjngs() != null) {
            numskll = Integer.parseInt(configure.getZhsjngs());
        }
        this.lock = 0;
        this.type = "";
        this.shenshou1 = new ImageIcon("inkImg/hongmu/dingzhishenshou.png");
        this.icon1 = new ImageIcon("img/xy2uiimg/goodorpet_lock.png");
        this.kaiqi = new ImageIcon("inkImg/background/S156.png");
        this.shenshou = new ImageIcon("inkImg/background/6.png");
        this.kaiqi1 = new ImageIcon("img/lingbao/msg/开.png");
        this.qiling = new ImageIcon("inkImg/hongmu/qiling.png");
        this.tianfu = new ImageIcon("inkImg/hongmu/S7071.png");
        this.jzjy = new ImageIcon("img/background/156.png");
        this.gheight = gheight;
        this.setPreferredSize(new Dimension(434, gheight));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.richLabel = new RichLabel("", UIUtils.TEXT_FONT1, 255)).setBounds(155, 10, this.richLabel.getWidth(), this.richLabel.getHeight());
        this.add(this.richLabel);
        (this.qilingcount = new JLabel()).setForeground(Color.yellow);
        this.qilingcount.setBackground(UIUtils.Color_BACK);
        this.qilingcount.setFont(UIUtils.TEXT_FONT2);
        this.qilingcount.setBounds(68, 180, 40, 20);
        if (numskll == 9) {
            this.add(this.qilingcount);
        }
        (this.qlLab = new JLabel()).setOpaque(true);
        this.qlLab.setBounds(10, 180, 50, 20);
        this.qlLab.setIcon(this.qiling);
        if (numskll == 9) {
            this.add(this.qlLab);
        }
        (this.tianfucount = new JLabel()).setForeground(Color.WHITE);
        this.tianfucount.setBackground(UIUtils.Color_BACK);
        this.tianfucount.setFont(UIUtils.TEXT_FONT2);
        this.tianfucount.setBounds(54, 200, 40, 20);
        this.add(this.tianfucount);
        this.tianfucount.setText("0阶");
        (this.tfLab = new JLabel()).setOpaque(true);
        this.tfLab.setBackground(UIUtils.Color_BACK);
        this.tfLab.setBounds(10, 200, 71, 20);
        this.tfLab.setIcon(this.tianfu);
        this.add(this.tfLab);
        Color color = Color.decode("0xA49BC8");
        this.labFive = new JLabel[5];
        for (int i = 0; i < this.labFive.length; ++i) {
            (this.labFive[i] = new JLabel()).setForeground(color);
            this.labFive[i].setBackground(UIUtils.Color_BACK);
            this.labFive[i].setFont(UIUtils.TEXT_FONT1);
            this.add(this.labFive[i]);
        }
        this.labPart = new JLabel[4];
        if (!Xy2Util.zzs.equals("3")) {
            for (int i = 0; i < this.labPart.length; ++i) {
                this.add(this.labPart[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.drawImage(PetsMsgJpanel.this.kaiqi1.getImage(), 0, 0, 26, 26, null);
                        g.translate(1, 1);
                        super.paintComponent(g);
                        g.translate(-1, -1);
                    }
                });
            }
        }
        this.labSkillImg = new JLabel[numskll];
        this.labSkillName = new JLabel[numskll];
        color = Color.decode("0xA5B4F3");
        for (int i = 0; i < this.labSkillImg.length; ++i) {
            if (numskll == 5) {
                (this.labSkillImg[i] = new JLabel()).setOpaque(true);
                this.labSkillImg[i].setBounds(10, 200 + i * 20, 20, 20);
                this.add(this.labSkillImg[i]);
                (this.labSkillName[i] = new JLabel()).setForeground(color);
                this.labSkillName[i].setBackground(UIUtils.Color_BACK);
                this.labSkillName[i].setFont(UIUtils.TEXT_FONT1);
                this.labSkillName[i].setBounds(35, 200 + i * 20, 105, 20);
                this.add(this.labSkillName[i]);
            }
            else if (numskll == 7) {
                (this.labSkillImg[i] = new JLabel()).setOpaque(true);
                this.labSkillImg[i].setBounds(10, 200 + i * 20, 20, 20);
                this.add(this.labSkillImg[i]);
                (this.labSkillName[i] = new JLabel()).setForeground(color);
                this.labSkillName[i].setBackground(UIUtils.Color_BACK);
                this.labSkillName[i].setFont(UIUtils.TEXT_FONT1);
                this.labSkillName[i].setBounds(35, 200 + i * 20, 105, 20);
                this.add(this.labSkillName[i]);
            }
            else {
                (this.labSkillImg[i] = new JLabel()).setOpaque(true);
                this.labSkillImg[i].setBounds(10, 230 + i * 20, 20, 20);
                this.add(this.labSkillImg[i]);
                (this.labSkillName[i] = new JLabel()).setForeground(color);
                this.labSkillName[i].setBackground(UIUtils.Color_BACK);
                this.labSkillName[i].setFont(UIUtils.TEXT_FONT1);
                this.labSkillName[i].setBounds(35, 230 + i * 20, 105, 20);
                this.add(this.labSkillName[i]);
            }
        }
        this.qlSkillImg = new JLabel[6];
        this.qlSkillName = new JLabel[6];
        for (int i = 0; i < this.qlSkillImg.length; ++i) {
            (this.qlSkillImg[i] = new JLabel()).setOpaque(true);
            this.qlSkillImg[i].setBounds(10, 230 + i * 20, 20, 20);
            this.add(this.qlSkillImg[i]);
            (this.qlSkillName[i] = new JLabel()).setForeground(Color.green);
            this.qlSkillName[i].setBackground(UIUtils.Color_BACK);
            this.qlSkillName[i].setFont(UIUtils.TEXT_FONT1);
            this.qlSkillName[i].setBounds(35, 230 + i * 20, 105, 20);
            this.add(this.qlSkillName[i]);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            PetsMsgJpanel.icon = new ImageIcon("inkImg/background/S144.png");
        }
        else {
            PetsMsgJpanel.icon = new ImageIcon("inkImg/hongmu/S145.png");
        }
        g.drawImage(PetsMsgJpanel.icon.getImage(), 0, 0, 434, this.gheight, this);
        super.paint(g);
        if (this.lock == 1) {
            g.drawImage(this.icon1.getImage(), 140, 15, 10, 12, this);
        }
        if (this.part != null) {
            this.part.draw(g, 85, 135, 0, ImageMixDeal.userimg.getTime());
        }
    }
    
    public int petMsg(RoleSummoning pet, List<Goodstable> goods) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int numskll = 9;
        if (configure.getZhsjngs() != null) {
            numskll = Integer.parseInt(configure.getZhsjngs());
        }
        int max = 75;
        if (configure.getZhskxsx() != null && configure.getZhskxsx() != "") {
            max = 75 + Integer.parseInt(configure.getZhskxsx());
        }
        this.lock = pet.getPetlock();
        this.part = pet.getPart();
        this.type = pet.getSsn();
        if (this.nds == null) {
            this.nds = new ArrayList<>();
        }
        else {
            this.nds.clear();
        }
        Goodstable cjs = null;
        Goodstable part1 = null;
        Goodstable part2 = null;
        Goodstable part3 = null;
        if (goods != null && goods.size() > 0) {
            for (int i = 0; i < goods.size(); ++i) {
                Goodstable good = (Goodstable)goods.get(i);
                if ((long)good.getType() == 750L) {
                    this.nds.add(good);
                }
                else if ((long)good.getType() == 729L) {
                    cjs = good;
                }
                else if ((long)good.getType() == 510L) {
                    part1 = good;
                }
                else if ((long)good.getType() == 511L) {
                    part2 = good;
                }
                else if ((long)good.getType() == 512L) {
                    part3 = good;
                }
            }
        }
        else {
            if (pet.getStye() != null && pet.getStye().length() > 1) {
                String[] v = pet.getStye().split("\\|");
                for (int j = 1; j < v.length; ++j) {
                    String[] vs = v[j].split("-");
                    if (vs.length >= 2) {
                        Goodstable good2 = GoodsListFromServerUntil.getRgid(new BigDecimal(vs[1]));
                        if (good2 == null) {
                            List<String> goodinfos = pet.getOtherInfo();
                            if (goodinfos != null && goodinfos.size() > 0) {
                                for (String goodsinfo : goodinfos) {
                                    if (StringUtils.isNotEmpty(goodsinfo)) {
                                        String key = goodsinfo.substring(0, goodsinfo.indexOf("&"));
                                        String val = goodsinfo.substring(goodsinfo.indexOf("&") + 1);
                                        if (StringUtils.isNotEmpty(vs[1]) && key.equals(vs[1])) {
                                            good2 = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(val, Goodstable.class);
                                        }
                                        else {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                        if (good2 != null) {
                            if ((long)good2.getType() == 729L) {
                                cjs = good2;
                            }
                            else if ((long)good2.getType() == 510L) {
                                part1 = good2;
                            }
                            else if ((long)good2.getType() == 511L) {
                                part2 = good2;
                            }
                            else if ((long)good2.getType() == 512L) {
                                part3 = good2;
                            }
                        }
                    }
                }
            }
            if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                String[] v = pet.getInnerGoods().split("\\|");
                for (int j = 0; j < v.length; ++j) {
                    Goodstable good3 = GoodsListFromServerUntil.getRgid(new BigDecimal(v[j]));
                    if (good3 == null) {
                        List<String> goodinfos2 = pet.getOtherInfo();
                        if (goodinfos2 != null && goodinfos2.size() > 0) {
                            for (String goodsinfo2 : goodinfos2) {
                                if (StringUtils.isNotEmpty(goodsinfo2)) {
                                    String key2 = goodsinfo2.substring(0, goodsinfo2.indexOf("&"));
                                    String val2 = goodsinfo2.substring(goodsinfo2.indexOf("&") + 1);
                                    if (StringUtils.isNotEmpty(v[j]) && key2.equals(v[j])) {
                                        good3 = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(val2, Goodstable.class);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    if (good3 != null) {
                        this.nds.add(good3);
                    }
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#W");
        buffer.append(pet.getSummoningname());
        buffer.append("   ");
        if ((int)pet.getGrade() <= 100) {
            buffer.append("#cd42020");
            buffer.append(AnalysisString.petLvl((int)pet.getGrade()));
        }
        else if ((int)pet.getGrade() > 100 && (int)pet.getGrade() <= 221) {
            buffer.append("#cd42020");
            buffer.append(AnalysisString.petLvl((int)pet.getGrade()));
        }
        else if ((int)pet.getGrade() > 221 && (int)pet.getGrade() <= 362) {
            buffer.append("#cd42020");
            buffer.append(AnalysisString.petLvl((int)pet.getGrade()));
        }
        else if ((int)pet.getGrade() > 362 && (int)pet.getGrade() <= 543) {
            buffer.append("#cd42020");
            buffer.append(AnalysisString.petLvl((int)pet.getGrade()));
        }
        else {
            buffer.append("#cff0000");
            buffer.append(AnalysisString.petLvl((int)pet.getGrade()));
        }
        if (pet.getQuality() != null && !pet.getQuality().equals("2")) {
            buffer.append("#cEE2C2C");
            buffer.append("\t  禁止交易");
        }
        if (pet.getQuality() != null && pet.getQuality().equals("1") && pet.getSurplusTimes() != null) {
            buffer.append("#r#cEE2C2C过期时间：" + pet.getSurplusTimes());
            buffer.append("#r ");
        }
        buffer.append("#r#cFBFFC1成长率 ");
        buffer.append(pet.getGrowlevel());
        buffer.append("\t #cFBFFC1龙之骨x");
        buffer.append(pet.getDragon() + pet.getSpdragon());
        if (pet.getSsn().equals("5") || pet.getSummoningid().equals("200125")) {
            buffer.append("  龙涎丸×");
            buffer.append(pet.getDraC());
        }
        int[] pets = PetProperty.getPetHMASp(pet);
        int sizeW = (pets[0] + "").length() + (pet.getHp() + "").length();
        buffer.append("#r#c7DDE8C气血 ");
        buffer.append(pets[0]);
        buffer.append("#W(" + pet.getHp() + ") ");
        for (int k = sizeW; k < 10; ++k) {
            buffer.append(" ");
        }
        buffer.append("#c7DDE8C攻击 ");
        buffer.append(pets[2]);
        buffer.append("#W(" + pet.getAp() + ") ");
        buffer.append("#r#c7DDE8C法力 ");
        buffer.append(pets[1]);
        buffer.append("#W(" + pet.getMp() + ") ");
        for (int k = sizeW = (pets[1] + "").length() + (pet.getMp() + "").length(); k < 10; ++k) {
            buffer.append(" ");
        }
        buffer.append("#c7DDE8C速度 ");
        buffer.append(pets[3]);
        buffer.append("#W(" + pet.getSp() + ") ");
        if (pet.getTurnRount() == 4) {
            buffer.append("#r#c7DDE8C禅定 ");
            buffer.append(pets[4]);
            buffer.append("#W(0)");
        }
        buffer.append("#r#cD8AB6C内   丹 ");
        for (int k = 0; k < this.nds.size(); ++k) {
            if (k != 0) {
                buffer.append("#r        ");
            }
            Goodstable good4 = (Goodstable)this.nds.get(k);
            String[] strings = good4.getValue().split("\\|");
            String[] stringLevel = strings[2].split("\\=");
            String[] stringLevel2 = stringLevel[1].split("\\转");
            buffer.append(good4.getGoodsname());
            buffer.append("  ");
            buffer.append((stringLevel2[0].equals("4") ? "点化" : (stringLevel2[0] + "转")) + stringLevel2[1]);
            buffer.append("级");
        }
        buffer.append("#r ");
        if (!StringUtil.isNullOrEmpty(pet.getLingxi())) {
            buffer.append("#r#Y灵犀等级：" + LingXiUtil.getPointCount(pet.getLingxi()) + "  开启技能格：" + LingXiUtil.getLingXiGe(pet.getLingxi()) + "/16");
        }
        buffer.append("#r#c8EDEFF炼妖次数    ");
        buffer.append(pet.getAlchemynum());
        if (pet.getResistance() != null && !pet.getResistance().equals("")) {
            String[] v2 = pet.getResistance().split("\\|");
            if (v2.length >= 3) {
                JmSum.xiugaiqi();
            }
            for (int l = 0; l < v2.length; ++l) {
                String[] v3 = v2[l].split("=");
                double value = Double.parseDouble(v3[1]);
                if (value != 30.0) {
                    JmSum.xiugaiqi();
                }
            }
        }
        String[] tss = null;
        String[] lys = null;
        if (pet.getResistance() != null && !pet.getResistance().equals("")) {
            tss = pet.getResistance().split("\\|");
        }
        if (pet.getLyk() != null && !pet.getLyk().equals("")) {
            lys = pet.getLyk().split("\\|");
        }
        if (tss != null) {
            double t = 0.0;
            double m = 0.0;
            for (int m2 = 0; m2 < tss.length; ++m2) {
                String[] v4 = tss[m2].split("=");
                t = Double.parseDouble(v4[1]);
                String qz = v4[0] + "=";
                m = 0.0;
                if (lys != null) {
                    int j2 = 0;
                    while (j2 < lys.length) {
                        if (lys[j2] != null && lys[j2].startsWith(qz)) {
                            m = Double.parseDouble(lys[j2].split("=")[1]);
                            m = ((m < (double)(max - 30)) ? m : ((double)(max - 30)));
                            lys[j2] = null;
                            break;
                        }
                        else if (lys[j2] != null && qz.startsWith("抗物理") && lys[j2].startsWith("物理吸收率")) {
                            m = Double.parseDouble(lys[j2].split("=")[1]);
                            m = ((m < (double)(max - 30)) ? m : ((double)(max - 30)));
                            lys[j2] = null;
                            break;
                        }
                        else {
                            ++j2;
                        }
                    }
                }
                buffer.append("#r");
                buffer.append(v4[0]);
                buffer.append(": ");
                buffer.append(t + m);
                buffer.append("(#W");
                buffer.append(t);
                buffer.append("#c8EDEFF+");
                buffer.append(m);
                buffer.append(")");
            }
        }
        if (lys != null) {
            int size = 0;
            for (int i2 = 0; i2 < lys.length; ++i2) {
                if (lys[i2] != null) {
                    buffer.append((size % 2 == 0) ? "#r" : " ");
                    String[] kx = lys[i2].split("=");
                    buffer.append(kx[0]);
                    buffer.append(": ");
                    buffer.append(((double)Double.valueOf(kx[1]) > (double)max) ? ((double)max) : ((double)Double.valueOf(kx[1])));
                    ++size;
                }
            }
        }
        buffer.append("#r ");
        if (!Xy2Util.zzs.equals("3")) {
            buffer.append("#r#Y装备:#r #r#Y觉醒技:");
            String jx = JX((part1 != null) ? part1.getValue() : null, (part2 != null) ? part2.getValue() : null, (part3 != null) ? part3.getValue() : null);
            if (jx != null) {
                buffer.append(jx);
            }
            else {
                buffer.append("未觉醒");
            }
        }
        Long qm = pet.getFriendliness();
        buffer.append("#r#c8EDEFF亲密度:" + qm);
        this.richLabel.setTextSize(buffer.toString(), 255);
        this.richLabel.setBounds(155, 10, this.richLabel.getWidth(), this.richLabel.getHeight());
        if (!Xy2Util.zzs.equals("3")) {
            for (int i3 = 0; i3 < this.labPart.length; ++i3) {
                this.labPart[i3].setBounds(195 + 30 * i3, this.richLabel.getHeight() - 60, 26, 26);
                if (i3 == 3) {
                    if (cjs != null) {
                        this.labPart[i3].setIcon(GoodsListFromServerUntil.imgpathAdaptive(cjs.getSkin(), 24, 24));
                        PetEquipMouslisten equipMouslisten = new PetEquipMouslisten(this, i3);
                        this.labPart[i3].addMouseListener(equipMouslisten);
                    }
                    else {
                        this.labPart[i3].setIcon(null);
                    }
                }
                else {
                    Goodstable good5 = (i3 == 0) ? part1 : ((i3 == 1) ? part2 : part3);
                    if (good5 != null) {
                        this.labPart[i3].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good5.getSkin(), 24, 24));
                        PetEquipMouslisten equipMouslisten2 = new PetEquipMouslisten(this, i3);
                        this.labPart[i3].addMouseListener(equipMouslisten2);
                    }
                    else {
                        this.labPart[i3].setIcon(null);
                    }
                }
            }
        }
        for (int i3 = 0; i3 < pets.length; ++i3) {
            pets[i3] = 0;
        }
        int openSeal = (int)pet.getOpenSeal();
        if (openSeal > numskll) {
            openSeal = numskll;
        }
        for (int i4 = openSeal; i4 < numskll; ++i4) {
            this.labSkillImg[i4].setVisible(false);
            this.labSkillName[i4].setVisible(false);
        }
        String[] skills = null;
        if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
            skills = pet.getPetSkills().split("\\|");
        }
        Map<String, String> qlMap = new HashMap<>();
        if (StringUtils.isNotBlank(pet.getPetSkillswl())) {
            String[] v = pet.getPetSkillswl().split("\\|");
            for (String s : v) {
                String[] n = s.split("=");
                qlMap.put(n[0], n[1]);
            }
        }
        for (int m = 0; m < openSeal; ++m) {
            if ((skills != null && m < skills.length) || (m == numskll - 1 && pet.getBeastSkills() != null && !pet.getBeastSkills().equals(""))) {
                final Skill skill = UserMessUntil.getSkillId((m == numskll - 1) ? pet.getBeastSkills() : skills[m]);
                if (skill != null) {
                    this.labSkillImg[m].setIcon((Icon) this.petskillIcon(skill.getSkillid()));
                    String s = qlMap.get(skill.getSkillid() + "");
                    if (StringUtils.isNotBlank(s)) {
                        this.labSkillName[m].setText(skill.getSkillname() + "(" + s + "阶)");//启灵阶数
                    } else
                        this.labSkillName[m].setText(skill.getSkillname());
                    final int skillID = Integer.parseInt(skill.getSkillid());
                    if (skillID == 1815) {
                        pets[0] = 50;
                    } else if (skillID == 1816) {
                        pets[1] = 50;
                    } else if (skillID == 1817) {
                        pets[4] = 50;
                    } else if (skillID == 1818) {
                        pets[3] = 50;
                    } else if (skillID == 1819) {
                        pets[2] = 50;
                    }
                }
            } else {
                this.labSkillImg[m].setIcon((Icon) this.kaiqi);
                this.labSkillName[m].setText("已开启");
            }
            this.labSkillImg[m].setVisible(true);
            this.labSkillName[m].setVisible(true);
        }
        if (pet.getBeastSkills() != null && !pet.getBeastSkills().equals("")) {
            try {
                Skill skill2 = UserMessUntil.getSkillId(pet.getBeastSkills());
                this.labSkillImg[openSeal].setIcon(this.petskillIcon(skill2.getSkillid()));
                this.labSkillName[openSeal].setText(skill2.getSkillname());
                this.labSkillImg[openSeal].setVisible(true);
                this.labSkillName[openSeal].setVisible(true);
                this.gheight += 50;
            }
            catch (Exception ex) {}
        }
        int openQlSeal = (int)pet.getOpenql();
        if (openQlSeal > 6) {
            openQlSeal = 6;
        }
        for (int i5 = openQlSeal; i5 < 6; ++i5) {
            this.qlSkillImg[i5].setVisible(false);
            this.qlSkillName[i5].setVisible(false);
        }
        String[] skillsx = null;
        if (pet.getPetQlSkills() != null && !pet.getPetQlSkills().equals("")) {
            skillsx = pet.getPetQlSkills().split("\\|");
        }
        for (int i6 = 0; i6 < openQlSeal; ++i6) {
            if (skillsx != null && i6 < skillsx.length) {
                Skill skillx = UserMessUntil.getSkillId(skillsx[i6]);
                if (skillx != null) {
                    this.qlSkillImg[i6].setIcon(this.petskillIcon(skillx.getSkillid()));
                    this.qlSkillName[i6].setText(skillx.getSkillname());
                    this.qlSkillImg[i6].setBounds(10, 230 + i6 * 20 + openSeal * 20, 20, 20);
                    this.qlSkillName[i6].setBounds(35, 230 + i6 * 20 + openSeal * 20, 105, 20);
                    int skillID2 = Integer.parseInt(skillx.getSkillid());
                    if (skillID2 == 1815) {
                        pets[0] = 50;
                    }
                    else if (skillID2 == 1816) {
                        pets[1] = 50;
                    }
                    else if (skillID2 == 1817) {
                        pets[4] = 50;
                    }
                    else if (skillID2 == 1818) {
                        pets[3] = 50;
                    }
                    else if (skillID2 == 1819) {
                        pets[2] = 50;
                    }
                }
            }
            else {
                this.qlSkillImg[i6].setIcon(this.kaiqi);
                this.qlSkillName[i6].setText("");
                this.qlSkillImg[i6].setBounds(10, 230 + i6 * 20 + openSeal * 20, 20, 20);
                this.qlSkillName[i6].setBounds(35, 230 + i6 * 20 + openSeal * 20, 105, 20);
            }
//            this.qlSkillImg[i6].setVisible(true);
//            this.qlSkillName[i6].setVisible(true);
        }
        if (numskll == 9) {
            this.qilingcount.setText(pet.getOpenql() + "次");
            this.qilingcount.setVisible(false);
            this.qlLab.setVisible(false);
        }
        if (pet.getTalentLvl() > 0) {
            this.tfLab.setVisible(true);
            this.tianfucount.setVisible(true);
            this.tianfucount.setText(pet.getTalentLvl() + "阶");
        }
        else {
            this.tfLab.setVisible(false);
            this.tianfucount.setVisible(false);
        }
        this.gheight = 10 + this.richLabel.getHeight();
        if (180 + openSeal * 20 + openQlSeal * 20 > this.gheight) {
            this.gheight = 180 + openSeal * 20 + openQlSeal * 20;
        }
        this.gheight += 30;
        sizeW = ((pets[0] != 0 || pets[1] != 0 || pets[2] != 0 || pets[3] != 0 || pets[4] != 0) ? 2 : 1);
        int[] array = pets;
        int n = 0;
        int n6 = 0;
        array[n6] += Integer.parseInt(pet.getGold()) / sizeW;
        int[] array2 = pets;
        int n2 = 1;
        int n7 = 1;
        array2[n7] += Integer.parseInt(pet.getWood()) / sizeW;
        int[] array3 = pets;
        int n3 = 2;
        int n8 = 2;
        array3[n8] += Integer.parseInt(pet.getSoil()) / sizeW;
        int[] array4 = pets;
        int n4 = 3;
        int n9 = 3;
        array4[n9] += Integer.parseInt(pet.getWater()) / sizeW;
        int[] array5 = pets;
        int n5 = 4;
        int n10 = 4;
        array5[n10] += Integer.parseInt(pet.getFire()) / sizeW;
        this.labFive[0].setText("金:" + pets[0]);
        this.labFive[1].setText("木:" + pets[1]);
        this.labFive[2].setText("土:" + pets[2]);
        this.labFive[3].setText("水:" + pets[3]);
        this.labFive[4].setText("火:" + pets[4]);
        for (int i7 = 0; i7 < 5; ++i7) {
            this.labFive[i7].setBounds(130 + i7 * 52, this.gheight - 20, 52, 30);
        }
        this.gheight += 15;
        if (pet.getQuality() != null && pet.getQuality().equals("1")) {
            this.gheight += 15;
        }
        this.gheight += 40;
        this.setPreferredSize(new Dimension(434, this.gheight));
        return this.gheight;
    }
    
    private ImageIcon petskillIcon(String skillid) {
        return CutButtonImage.getImage("img/skill/wxs_" + skillid + ".png", 20, 20);
    }
    
    public int getGheight() {
        return this.gheight;
    }
    
    public static String JX(String jx1, String jx2, String jx3) {
        if (jx1 == null || jx2 == null || jx3 == null) {
            return null;
        }
        for (int i = 0; i < 3; ++i) {
            String x = (i == 0) ? jx1 : ((i == 1) ? jx2 : jx3);
            int s1 = x.indexOf("觉醒技");
            if (s1 == -1) {
                return null;
            }
            x = x.substring(s1);
            int s2 = x.indexOf("|");
            if (s2 != -1) {
                x = x.substring(0, s2);
            }
            if (i == 0) {
                jx1 = x;
            }
            else if (i == 1) {
                jx2 = x;
            }
            else {
                jx3 = x;
            }
        }
        String[] jxs1 = jx1.split("\\&");
        String[] jxs2 = jx2.split("\\&");
        String[] jxs3 = jx3.split("\\&");
        if (!jxs1[1].equals(jxs2[1]) || !jxs1[1].equals(jxs3[1])) {
            return null;
        }
        Skill skill = UserMessUntil.getSkillId(jxs1[1]);
        double sld = PetEquipmentJpanel.averageMath(jxs1[2], jxs2[2], jxs3[2]).doubleValue();
        return skill.getSkillname() + "(" + sld + ")";
    }
}
