package com.tool.time;

import java.util.HashMap;
import org.come.bean.ColorScheme;
import org.apache.commons.lang.StringUtils;
import java.awt.Graphics;
import come.tool.Fighting.SkillTx;
import org.come.model.Title;
import org.come.bean.RoleTxBean;
import org.come.bean.ConfigureBean;
import com.tool.image.Creepsskin;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.tool.btn.VipShopBtn;
import org.come.until.ScrenceUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Util;
import org.come.bean.RoleShow;
import org.come.Frame.TestpackJframe;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleProperty;
import org.come.Frame.YuekaJframe;
import org.come.Frame.TaobaoCourtMainJframe;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.bean.PrivateData;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Image;
import java.util.List;

public class TimeLimit
{
    public static TimeLimit timeLimit;
    public List<Limit> limits;
    private static Image image;
    public static final Map<Long, Long> valueMap;
    
    public TimeLimit() {
        this.limits = new ArrayList<>();
    }
    
    public static TimeLimit getLimits() {
        if (TimeLimit.timeLimit == null) {
            initial();
        }
        return TimeLimit.timeLimit;
    }
    
    public static void initial() {
        TimeLimit.timeLimit = new TimeLimit();
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String v = data.getTimingGood();
        if (v == null || v.equals("")) {
            return;
        }
        String[] vs = v.split("\\^");
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("#");
            Limit limit = new Limit();
            for (int j = 0; j < vss.length; ++j) {
                if (j == 0) {
                    limit.setName(vss[j]);
                }
                else if (j == 1) {
                    limit.setType(vss[j]);
                }
                else if (j == 2) {
                    limit.setSkin(vss[j]);
                }
                else if (j == 3) {
                    long time = Long.parseLong(vss[j]);
                    if (time < 100000000L) {
                        time *= 60000L;
                    }
                    else if (time < 2000000000L) {
                        time *= 1000L;
                    }
                    limit.setTime(time);
                }
                else if (j == 4) {
                    limit.setValue(vss[j]);
                }
            }
            if (!limit.getType().equals("SVIP")) {
                JLabel jLabel = getTimeJlabel();
                ImageIcon icon = GoodsListFromServerUntil.imgpathAdaptive(limit.getSkin(), 20, 20);
                jLabel.addMouseListener(new TimeMouslisten(limit));
                jLabel.setIcon(icon);
                limit.setjLabel(jLabel);
                ZhuFrame.getZhuJpanel().add(jLabel);
                TimeLimit.timeLimit.limits.add(limit);
            }
        }
        TimeLimit.timeLimit.Sorting();
    }
    
    public void addLimit(String name, String type, String skin, String value, long time) {
        Limit limit = this.getLimit(type);
        if (time == -1L) {
            this.removeLimit(limit);
            return;
        }
        if (limit != null) {
            limit.setName(name);
            limit.setTime(time);
            limit.setValue(value);
            if (!limit.getSkin().equals(skin)) {
                limit.setSkin(skin);
                ImageIcon icon = GoodsListFromServerUntil.imgpathAdaptive(limit.getSkin(), 20, 20);
                limit.getjLabel().setIcon(icon);
            }
        }
        else {
            limit = new Limit(name, type, skin, time, value);
            JLabel jLabel = getTimeJlabel();
            ImageIcon icon2 = GoodsListFromServerUntil.imgpathAdaptive(limit.getSkin(), 20, 20);
            jLabel.setIcon(icon2);
            jLabel.addMouseListener(new TimeMouslisten(limit));
            limit.setjLabel(jLabel);
            ZhuFrame.getZhuJpanel().add(jLabel);
            TimeLimit.timeLimit.limits.add(limit);
        }
        if (type.equals("VIP")) {
            TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getMonthlyCardJpanel().changeTime();
            TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getVipShopJpanel().changeTimeS();
            YuekaJframe.getYuekaJframe().getYuekaJpanel().changeTime();
        }
        if (type.equals("JVIP")) {
//            TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getMonthlyCardJpanel().changeTime();
//            TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getVipShopJpanel().changeTimeS();
            YuekaJframe.getYuekaJframe().getYuekaJpanel().changeTime1();
        }
        TimeLimit.timeLimit.Sorting();
        if (limit.getType().equals("变身卡") || limit.getType().equals("强法型") || limit.getType().equals("加抗型") || limit.getType().equals("增益型") || limit.getType().equals("VIP") || limit.getType().equals("JVIP") || limit.getType().equals("帮派") || limit.getType().equals("单人竞技场")) {
            RoleProperty.ResetEw();
        }
        if (limit.getType().equals("变身卡") || limit.getType().equals("童卡")) {
            this.changeSkin();
        }
    }
    
    public void removeLimit(Limit limit) {
        if (limit == null) {
            return;
        }
        ZhuFrame.getZhuJpanel().addPrompt2(limit.getName() + "已经失去效果");
        ZhuFrame.getZhuJpanel().remove(limit.getjLabel());
        this.limits.remove(limit);
        if (limit.getType().equals("变身卡") || limit.getType().equals("强法型") || limit.getType().equals("加抗型") || limit.getType().equals("增益型") || limit.getType().equals("VIP")|| limit.getType().equals("JVIP") ) {
            RoleProperty.ResetEw();
        }
        TimeLimit.timeLimit.Sorting();
        if (limit.getType().equals("变身卡") || limit.getType().equals("童卡")) {
            this.changeSkin();
        }
    }
    
    public void changeSkin() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        String skin = roleShow.getSkin();
        String skin2 = getskin(this.getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), roleShow);
        if (skin == null) {
            skin = "";
        }
        if (skin2 == null) {
            skin2 = "";
        }
        if (!skin.equals(skin2)) {
            roleShow.setSkin(skin2.equals("") ? null : skin2);
            RoleData.getRoleData().getLoginResult().setSkin(roleShow.getSkin());
            TestpackJframe.getTestpackJframe().getJpac().setPart(null);
            ImageMixDeal.userimg.changeskin(null);
            GoodsListFromServerUntil.sendPackRecord(5, skin2);
        }
    }
    
    public void gwChangeSkin() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        String skin2 = getskin(this.getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), roleShow);
        roleShow.setSkin(skin2.equals("") ? null : skin2);
        RoleData.getRoleData().getLoginResult().setSkin(roleShow.getSkin());
        TestpackJframe.getTestpackJframe().getJpac().setPart(null);
        ImageMixDeal.userimg.changeskin(null);
        GoodsListFromServerUntil.sendPackRecord(5, skin2);
    }
    
    public static void Timeout() {
        if (TimeLimit.timeLimit == null) {
            return;
        }
        StringBuffer buffer = null;
        for (int i = TimeLimit.timeLimit.limits.size() - 1; i >= 0; --i) {
            Limit limit = (Limit)TimeLimit.timeLimit.limits.get(i);
            if (limit.getTime() != 0L && Util.getTime() > limit.getTime()) {
                if (buffer == null) {
                    buffer = new StringBuffer("T");
                }
                else {
                    buffer.append("|");
                }
                buffer.append(limit.getType());
                TimeLimit.timeLimit.removeLimit(limit);
            }
        }
        if (buffer != null) {
            String senmes = Agreement.getAgreement().usercardAgreement(buffer.toString());
            SendMessageUntil.toServer(senmes);
        }
    }
    
    public Limit getLimit(String type) {
        for (int i = 0; i < this.limits.size(); ++i) {
            if (((Limit)this.limits.get(i)).getType().equals(type)) {
                return (Limit)this.limits.get(i);
            }
        }
        return null;
    }
    
    public String getSkin() {
        Limit limit = this.getLimit("童卡");
        if (limit == null) {
            limit = this.getLimit("变身卡");
        }
        if (limit != null && limit.getValue() != null && !limit.getValue().equals("")) {
            String[] vs = limit.getValue().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss[0].equals("皮肤")) {
                    return vss[1];
                }
            }
        }
        return null;
    }
    
    public void Sorting() {
        for (int i = 0; i < this.limits.size(); ++i) {
            JLabel jLabel = ((Limit)this.limits.get(i)).getjLabel();
            if (jLabel != null) {
                jLabel.setBounds(ScrenceUntil.Screen_x - 22 - i * 25, 75, 22, 22);
            }
        }
        VipShopBtn.changeIconVie();
    }
    
    public static String getskin(String skin, List<String> txs, RoleShow roleShow) {
        StringBuffer buffer = new StringBuffer();
        if (skin != null && !skin.equals("")) {
            buffer.append("S");
            buffer.append(skin);
        }
        else if (GoodsListFromServerUntil.getChoseGoodsList()[0] != null) {
            long se = roleShow.getSpecies_id().longValue();
            long w = (long)good(Integer.parseInt(GoodsListFromServerUntil.getChoseGoodsList()[0].getSkin()));
            int s = 5;
            if (w != 0L) {
                Limit limit = getLimits().getLimit("童卡");
                if (limit == null) {
                    limit = getLimits().getLimit("变身卡");
                }
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                String nao = "新";
                if (configure.getNeworold() != null) {
                    nao = configure.getNeworold();
                }
                Boolean b = Boolean.valueOf(false);
                if (w != 0L && nao.equals("旧")) {
                    buffer.append("S");
                    buffer.append(w << 32 | se);
                }
                else if (w != 0L && s >= 4 && nao.equals("新")) {
                    if (nao.equals("新") && s >= 4) {
                        if (getRoleId(Creepsskin.getLocalName(RoleData.getRoleData().getLoginResult().getSpecies_id().intValue())) != null && s >= 0 && nao.equals("新") && w != 0L && (se != 21005L || w != 1L) && (se != 20010L || w != 1L) && (se != 21008L || w != 10L) && (se != 24005L || w != 12L) && (se != 20009L || w != 2L) && (se != 20001L || w != 2L) && (se != 21007L || w != 12L) && (se != 21009L || w != 10L) && (se != 23001L || w != 1L) && (se != 23005L || w != 17L) && (se != 24001L || w != 1L) && (se != 24004L || w != 3L) && (se != 22009L || w != 1L) && (se != 22004L || w != 10L) && (se != 23002L || w != 5L)) {
                            b = Boolean.valueOf(true);
                        }
                        else if ((w == 1L && se == 20001L) || (w == 2L && se == 20001L) || (w == 1L && se == 20002L) || (w == 3L && se == 20002L) || (w == 4L && se == 20003L) || (w == 5L && se == 20003L) || (w == 9L && se == 20004L) || (w == 8L && se == 20004L) || (w == 10L && se == 20005L) || (w == 7L && se == 20005L) || (w == 10L && se == 20006L) || (w == 12L && se == 20006L) || (w == 1L && se == 20007L) || (w == 5L && se == 20007L) || (w == 1L && se == 20008L) || (w == 10L && se == 20008L) || (w == 2L && se == 20009L) || (w == 6L && se == 20009L) || (w == 8L && se == 20010L) || (w == 1L && se == 20010L) || (w == 12L && se == 21001L) || (w == 7L && se == 21001L) || (w == 10L && se == 21002L) || (w == 13L && se == 21002L) || (w == 10L && se == 21003L) || (w == 12L && se == 21003L) || (w == 9L && se == 21004L) || (w == 10L && se == 21004L) || (w == 7L && se == 21005L) || (w == 1L && se == 21005L) || (w == 14L && se == 21006L) || (w == 8L && se == 21006L) || (w == 12L && se == 21007L) || (w == 4L && se == 21007L) || (w == 10L && se == 21008L) || (w == 11L && se == 21008L) || (w == 10L && se == 21009L) || (w == 4L && se == 21009L) || (w == 14L && se == 21010L) || (w == 9L && se == 21010L) || (w == 12L && se == 22001L) || (w == 3L && se == 22001L) || (w == 14L && se == 22002L) || (w == 1L && se == 22002L) || (w == 7L && se == 22003L) || (w == 14L && se == 22003L) || (w == 10L && se == 22004L) || (w == 5L && se == 22004L) || (w == 7L && se == 22005L) || (w == 16L && se == 22005L) || (w == 1L && se == 22006L) || (w == 12L && se == 22006L) || (w == 12L && se == 22007L) || (w == 14L && se == 22007L) || (w == 11L && se == 22008L) || (w == 16L && se == 22008L) || (w == 1L && se == 22009L) || (w == 13L && se == 22009L) || (w == 16L && se == 22010L) || (w == 17L && se == 22010L) || (w == 1L && se == 23001L) || (w == 10L && se == 23001L) || (w == 12L && se == 23002L) || (w == 5L && se == 23002L) || (w == 13L && se == 23003L) || (w == 6L && se == 23003L) || (w == 9L && se == 23004L) || (w == 8L && se == 23004L) || (w == 17L && se == 23005L) || (w == 11L && se == 23005L) || (w == 11L && se == 23006L) || (w == 16L && se == 23006L) || (w == 1L && se == 24001L) || (w == 6L && se == 24001L) || (w == 12L && se == 24002L) || (w == 10L && se == 24002L) || (w == 18L && se == 24003L) || (w == 11L && se == 24003L) || (w == 9L && se == 24004L) || (w == 3L && se == 24004L) || (w == 18L && se == 24005L) || (w == 12L && se == 24005L) || (w == 1L && se == 24006L) || (w == 17L && se == 24006L)) {
                            w += 18L;
                        }
                    }
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    if ((RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 20002 && w == 3L) || (RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 22005 && w == 7L) || (RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 21007 && w == 30L) || (RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 22001 && w == 30L) || (RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 23002 && w == 12L) || (RoleData.getRoleData().getLoginResult().getSpecies_id().intValue() == 24004 && w == 21L)) {
                        buffer.append("S");
                        buffer.append(w << 32 | se);
                    }
                    else {
                        buffer.append("S");
                        long key = makeKey(w, se);
                        if (TimeLimit.valueMap.containsKey(Long.valueOf(key))) {
                            buffer.append(TimeLimit.valueMap.get(Long.valueOf(key)));
                        }
                        else {
                            buffer.append(w << 32 | se);
                        }
                    }
                }
                else if (nao.equals("新")) {
                    buffer.append("S");
                    long key = makeKey(w, se);
                    if (TimeLimit.valueMap.containsKey(Long.valueOf(key))) {
                        buffer.append(TimeLimit.valueMap.get(Long.valueOf(key)));
                    }
                    else {
                        buffer.append(w << 32 | se);
                    }
                }
                if ((boolean)b && getRoleId(Creepsskin.getLocalName(RoleData.getRoleData().getLoginResult().getSpecies_id().intValue())) != null && s >= 0 && nao.equals("新") && w != 0L) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("GW_" + getRoleId(Creepsskin.getLocalName(RoleData.getRoleData().getLoginResult().getSpecies_id().intValue())));
                }
            }
        }
        if (txs != null) {
            for (int i = 0; i < txs.size(); ++i) {
                int id = Integer.parseInt((String)txs.get(i));
                RoleTxBean bean = UserMessUntil.getTxBean(id);
                if (bean != null) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    if (bean.getRdType() == 1) {
                        buffer.append("X");
                    }
                    else if (bean.getRdType() == 2) {
                        buffer.append("P");
                    }
                    else {
                        buffer.append("J");
                    }
                    buffer.append(bean.getRdId());
                    if (bean.getRdType() == 1 || bean.getRdType() == 2) {
                        buffer.append("_");
                        buffer.append(bean.getRdStatues() - bean.getRdType());
                    }
                }
            }
        }
        if (roleShow.getTitle() != null) {
            Title te = UserMessUntil.getTitle(roleShow.getTitle());
            if (te != null && te.getSkin() != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("C");
                buffer.append(te.getSkin());
            }
        }
        if (GoodsListFromServerUntil.getChoseGoodsList()[12] != null) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("B");
            buffer.append(GoodsListFromServerUntil.getChoseGoodsList()[12].getSkin());
        }
        return buffer.toString();
    }
    
    public static int good(int id) {
        if ((id >= 1600 && id <= 1616) || id == 6100 || id == 7006) {
            return 1;
        }
        if ((id >= 1400 && id <= 1416) || id == 6106 || id == 7012) {
            return 2;
        }
        if ((id >= 1100 && id <= 1116) || id == 6124 || id == 7021) {
            return 3;
        }
        if ((id >= 1200 && id <= 1216) || id == 6122 || id == 7022) {
            return 4;
        }
        if ((id >= 2201 && id <= 2216) || id == 6109 || id == 7016) {
            return 5;
        }
        if ((id >= 2400 && id <= 2416) || id == 6119 || id == 7020) {
            return 6;
        }
        if ((id >= 1300 && id <= 1316) || id == 6103 || id == 7009) {
            return 7;
        }
        if ((id >= 1700 && id <= 1716) || id == 6102 || id == 7008) {
            return 8;
        }
        if ((id >= 2101 && id <= 2116) || id == 7013 || id == 6105) {
            return 9;
        }
        if ((id >= 1000 && id <= 1016) || id == 6118 || id == 7007) {
            return 10;
        }
        if ((id >= 2501 && id <= 2516) || id == 7019 || id == 6120) {
            return 11;
        }
        if ((id >= 1800 && id <= 1816) || id == 6104 || id == 7011) {
            return 12;
        }
        if ((id >= 1900 && id <= 1916) || id == 6108 || id == 7017) {
            return 13;
        }
        if ((id >= 2301 && id <= 2316) || id == 6109 || id == 7010) {
            return 14;
        }
        if ((id >= 1500 && id <= 1516) || id == 7014 || id == 6117) {
            return 15;
        }
        if ((id >= 2001 && id <= 2016) || id == 6107 || id == 7015) {
            return 16;
        }
        if ((id >= 2601 && id <= 2616) || id == 7018 || id == 6121) {
            return 17;
        }
        if ((id >= 2617 && id <= 2632) || id == 6125 || id == 7023) {
            return 18;
        }
        return 0;
    }
    
    public int getlimit(Limit limit) {
        return this.limits.indexOf(limit);
    }
    
    public static JLabel getTimeJlabel() {
        if (TimeLimit.image == null) {
            TimeLimit.image = SkillTx.getImage();
        }
        JLabel jLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(TimeLimit.image, 0, 0, 22, 22, null);
                g.translate(1, 1);
                super.paintComponent(g);
                g.translate(-1, -1);
            }
        };
        return jLabel;
    }
    //光武变色
    public static String getRoleId(String roleId) {
        BigDecimal colorId = RoleData.getRoleData().getLoginResult().getScoretype("光武颜色");
        ColorScheme color = UserMessUntil.getColor(colorId.intValue());
        String skin = "";
        int n = -1;
        switch (roleId.hashCode()) {
            case 29561761: {
                if (roleId.equals("猎魂引")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 38281421: {
                if (roleId.equals("飞剑侠")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 27183226: {
                if (roleId.equals("武尊神")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 31961340: {
                if (roleId.equals("红挑友")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 39066655: {
                if (roleId.equals("骨精灵")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 40065607: {
                if (roleId.equals("龙战将")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 33773636: {
                if (roleId.equals("虎头怪")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 30586698: {
                if (roleId.equals("神天兵")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 20906515: {
                if (roleId.equals("剑侠客")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                skin = "500800";
                break;
            }
            case 1: {
                skin = "500799";
                break;
            }
            case 2: {
                skin = "500801";
                break;
            }
            case 3: {
                skin = "500220";
                break;
            }
            case 4: {
                skin = "500802";
                break;
            }
            case 5: {
                skin = "500803";
                break;
            }
            case 6: {
                skin = "500804";
                break;
            }
            case 7: {
                skin = "500805";
                break;
            }
            case 8: {
                skin = "600034";
                break;
            }
        }
        if (StringUtils.isBlank(skin)) {
            return "";
        }
        if (color != null) {
            String replace = color.getValue().replace("|", "^");
            skin = skin + "#" + replace;
        }
        return skin;
    }
    
    public static long makeKey(long w, long se) {
        return w << 32 | (se & 0xFFFFFFFFL);
    }
    
    static {
        (valueMap = new HashMap<>()).put(Long.valueOf(makeKey(1L, 20011L)), Long.valueOf(4294987307L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(7L, 20011L)), Long.valueOf(30064791083L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(10L, 20012L)), Long.valueOf(42949692972L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(8L, 20012L)), Long.valueOf(34359758380L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(12L, 21011L)), Long.valueOf(51539628563L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(10L, 21011L)), Long.valueOf(42949693971L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(9L, 21012L)), Long.valueOf(38654726676L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(10L, 21012L)), Long.valueOf(42949693972L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(7L, 22011L)), Long.valueOf(30064793083L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(12L, 22011L)), Long.valueOf(51539629563L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(11L, 22012L)), Long.valueOf(47244662268L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(16L, 22012L)), Long.valueOf(68719498748L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(1L, 23007L)), Long.valueOf(4294990303L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(10L, 23007L)), Long.valueOf(42949695967L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(1L, 23008L)), Long.valueOf(4294990304L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(16L, 23008L)), Long.valueOf(68719499744L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(1L, 24007L)), Long.valueOf(4294991303L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(10L, 24007L)), Long.valueOf(42949696967L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(16L, 24008L)), Long.valueOf(68719500744L));
        TimeLimit.valueMap.put(Long.valueOf(makeKey(17L, 24008L)), Long.valueOf(73014468040L));
    }
}
