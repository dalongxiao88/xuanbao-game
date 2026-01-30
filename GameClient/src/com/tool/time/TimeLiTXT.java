package com.tool.time;

import java.awt.RenderingHints;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.come.model.Title;
import org.come.bean.RoleTxBean;
import org.come.until.UserMessUntil;
import com.tool.role.RoleProperty;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Util;
import org.come.bean.RoleShow;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.TestSetupJpanel;
import org.come.Frame.TestSetupJframe;
import java.util.Iterator;
import com.tool.btn.VipShopBtn;
import org.come.until.ScrenceUntil;
import javax.swing.JLabel;
import org.come.bean.PrivateData;
import org.come.Frame.ZhuFrame;
import java.awt.Color;
import java.awt.Font;
import com.tool.role.RoleData;
import java.util.ArrayList;
import java.util.List;

public class TimeLiTXT
{
    public static TimeLiTXT timeLiTXT;
    public List<Limit> limits;
    
    public TimeLiTXT() {
        this.limits = new ArrayList<>();
    }
    
    public static TimeLiTXT getTimeLiTXT() {
        if (TimeLiTXT.timeLiTXT == null) {
            initial();
        }
        return TimeLiTXT.timeLiTXT;
    }
    
    public static void initial() {
        TimeLiTXT.timeLiTXT = new TimeLiTXT();
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
                JLabel Timetxt = getTimeJlabelTXT();
                limit.setTXT(Timetxt);
                Timetxt.setFont(new Font("楷体", 0, 14));
                int timei = TimerUtil.fenzhong(limit.getTime());
                int xiaos = TimerUtil.xiaoshi(limit.getTime());
                int tian = TimerUtil.residueDay(limit.getTime());
                if (timei > 60 && timei < 600) {
                    Timetxt.setText("0" + xiaos);
                    Timetxt.updateUI();
                    Timetxt.setForeground(Color.cyan);
                }
                else if (timei >= 600) {
                    Timetxt.setText(xiaos + "");
                    Timetxt.setForeground(Color.yellow);
                }
                else if (timei < 10) {
                    Timetxt.setText("0" + timei);
                    Timetxt.setForeground(Color.red);
                }
                else {
                    Timetxt.setText(timei + "");
                    Timetxt.updateUI();
                    Timetxt.setForeground(Color.green);
                }
                if (limit.getType().equals("超级六脉化神丸_月") || limit.getType().equals("超级玉枢返虚丸_月") || limit.getType().equals("VIP")  || limit.getType().equals("JVIP") || limit.getType().equals("BUFF") || limit.getType().equals("月饼") || limit.getType().equals("珍珠水泽丸药盒")) {
                    Timetxt.setText(tian + "");
                    Timetxt.updateUI();
                    Timetxt.setForeground(Color.YELLOW);
                }
                Timetxt.updateUI();
                limit.setTXT(Timetxt);
                Timetxt.setHorizontalAlignment(0);
                ZhuFrame.getZhuJpanel().add(Timetxt);
                TimeLiTXT.timeLiTXT.limits.add(limit);
            }
        }
        TimeLiTXT.timeLiTXT.Sortingtxt();
    }
    
    public void clearLimit() {
        for (int i = 0; i < this.limits.size(); ++i) {
            Limit limit = (Limit)TimeLiTXT.timeLiTXT.limits.get(i);
            ZhuFrame.getZhuJpanel().remove(limit.getTXT());
        }
        this.limits.clear();
    }
    
    public void addLimit(String name, String type, String skin, String value, long time) {
        Limit limit = this.getLimit(type);
        JLabel Timetxt = getTimeJlabelTXT();
        Timetxt.setFont(new Font("楷体", 0, 14));
        if (time == -1L) {
            this.removeLimit(limit);
            return;
        }
        if (limit != null) {
            limit.setName(name);
            limit.setTime(time);
            limit.setValue(value);
            if (!limit.getTXT().equals(Timetxt)) {
                ZhuFrame.getZhuJpanel().remove(limit.getTXT());
                limit.setTXT(Timetxt);
                limit = new Limit(name, type, skin, time, value);
                int timei = TimerUtil.fenzhong(limit.getTime());
                int xiaos = TimerUtil.xiaoshi(limit.getTime());
                int tian = TimerUtil.residueDay(limit.getTime());
                if (timei > 60 && timei <= 600) {
                    Timetxt.setText("0" + xiaos);
                    Timetxt.updateUI();
                    Timetxt.setForeground(Color.cyan);
                }
                else if (timei >= 600) {
                    Timetxt.setText(xiaos + "");
                    Timetxt.setForeground(Color.yellow);
                }
                else if (timei < 10) {
                    Timetxt.setText("0" + timei);
                    Timetxt.setForeground(Color.red);
                }
                else {
                    Timetxt.setText(timei + "");
                    Timetxt.setForeground(Color.green);
                }
                if (limit.getType().equals("超级六脉化神丸_月") || limit.getType().equals("超级玉枢返虚丸_月") || limit.getType().equals("VIP") || limit.getType().equals("JVIP") || limit.getType().equals("BUFF") || limit.getType().equals("月饼") || limit.getType().equals("珍珠水泽丸药盒")) {
                    Timetxt.setText(tian + "");
                    Timetxt.setForeground(Color.YELLOW);
                }
                if (limit.getType().equals("SVIP")) {
                    Timetxt.setText("");
                }
                limit.setTXT(Timetxt);
                Timetxt.updateUI();
                Timetxt.setHorizontalAlignment(0);
                ZhuFrame.getZhuJpanel().add(Timetxt);
            }
        }
        else {
            limit = new Limit(name, type, skin, time, value);
            int timei = TimerUtil.fenzhong(limit.getTime());
            int xiaos = TimerUtil.xiaoshi(limit.getTime());
            int tian = TimerUtil.residueDay(limit.getTime());
            if (timei > 60 && timei <= 600) {
                Timetxt.setText("0" + xiaos);
                Timetxt.updateUI();
                Timetxt.setForeground(Color.cyan);
            }
            else if (timei >= 600) {
                Timetxt.setText(xiaos + "");
                Timetxt.setForeground(Color.yellow);
            }
            else if (timei < 10) {
                Timetxt.setText("0" + timei);
                Timetxt.setForeground(Color.red);
            }
            else {
                Timetxt.setText(timei + "");
                Timetxt.setForeground(Color.green);
            }
            if (limit.getType().equals("超级六脉化神丸_月") || limit.getType().equals("超级玉枢返虚丸_月") || limit.getType().equals("VIP") || limit.getType().equals("JVIP") || limit.getType().equals("BUFF") || limit.getType().equals("月饼") || limit.getType().equals("珍珠水泽丸药盒")) {
                Timetxt.setText(tian + "");
                Timetxt.setForeground(Color.YELLOW);
            }
            if (limit.getType().equals("SVIP")) {
                Timetxt.setText("");
            }
            limit.setTXT(Timetxt);
            ZhuFrame.getZhuJpanel().add(Timetxt);
            Timetxt.setHorizontalAlignment(0);
            TimeLiTXT.timeLiTXT.limits.add(limit);
        }
        TimeLiTXT.timeLiTXT.Sortingtxt();
    }
    
    public void Sortingtxt() {
        for (int i = 0; i < this.limits.size(); ++i) {
            JLabel jLabel = ((Limit)this.limits.get(i)).getTXT();
            if (jLabel != null) {
                jLabel.setBounds(ScrenceUntil.Screen_x - 22 - i * 25, 95, 22, 22);
            }
        }
        VipShopBtn.changeIconVie();
    }
    
    public static void update() {
        List<Limit> limits = new ArrayList(getTimeLiTXT().limits);
        for (Limit limit : limits) {
            getTimeLiTXT().removeLimit(limit);
            getTimeLiTXT().addLimit(limit.getName(), limit.getType(), limit.getSkin(), limit.getValue(), limit.getTime());
        }
    }
    
    public void removeLimit(Limit limit) {
        if (limit == null) {
            return;
        }
        TestSetupJpanel ttt = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        ZhuFrame.getZhuJpanel().remove(limit.getTXT());
        this.limits.remove(limit);
        if (limit.getType().equals("变身卡") || limit.getType().equals("强法型") || limit.getType().equals("加抗型") || limit.getType().equals("增益型") || limit.getType().equals("VIP")|| limit.getType().equals("JVIP") ) {}
        TimeLiTXT.timeLiTXT.Sortingtxt();
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
            ImageMixDeal.userimg.changeskin(null);
            GoodsListFromServerUntil.sendPackRecord(5, skin2);
        }
    }
    
    public static void Timeout() {
        if (TimeLiTXT.timeLiTXT == null) {
            return;
        }
        StringBuffer buffer = null;
        for (int i = TimeLiTXT.timeLiTXT.limits.size() - 1; i >= 0; --i) {
            Limit limit = (Limit)TimeLiTXT.timeLiTXT.limits.get(i);
            if (limit.getTime() != 0L && Util.getTime() > limit.getTime()) {
                if (buffer == null) {
                    buffer = new StringBuffer("T");
                }
                else {
                    buffer.append("|");
                }
                buffer.append(limit.getType());
                TimeLiTXT.timeLiTXT.removeLimit(limit);
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
    
    public static String getskin(String skin, List<String> txs, RoleShow roleShow) {
        StringBuffer buffer = new StringBuffer();
        if (skin != null && !skin.equals("")) {
            buffer.append("S");
            buffer.append(skin);
        }
        else if (GoodsListFromServerUntil.getChoseGoodsList()[0] != null) {
            long se = roleShow.getSpecies_id().longValue();
            long w = (long)good(Integer.parseInt(GoodsListFromServerUntil.getChoseGoodsList()[0].getSkin()));
            if (w != 0L) {
                if (RoleProperty.getRoleProperty().getQhv() >= 12 && ((w == 1L && se == 20001L) || (w == 2L && se == 20001L) || (w == 1L && se == 20002L) || (w == 3L && se == 20002L) || (w == 4L && se == 20003L) || (w == 5L && se == 20003L) || (w == 9L && se == 20004L) || (w == 8L && se == 20004L) || (w == 10L && se == 20005L) || (w == 7L && se == 20005L) || (w == 10L && se == 20006L) || (w == 12L && se == 20006L) || (w == 1L && se == 20007L) || (w == 5L && se == 20007L) || (w == 1L && se == 20008L) || (w == 10L && se == 20008L) || (w == 2L && se == 20009L) || (w == 6L && se == 20009L) || (w == 8L && se == 20010L) || (w == 1L && se == 20010L) || (w == 12L && se == 21001L) || (w == 7L && se == 21001L) || (w == 10L && se == 21002L) || (w == 13L && se == 21002L) || (w == 10L && se == 21003L) || (w == 12L && se == 21003L) || (w == 9L && se == 21004L) || (w == 10L && se == 21004L) || (w == 7L && se == 21005L) || (w == 1L && se == 21005L) || (w == 14L && se == 21006L) || (w == 8L && se == 21006L) || (w == 12L && se == 21007L) || (w == 4L && se == 21007L) || (w == 10L && se == 21008L) || (w == 11L && se == 21008L) || (w == 10L && se == 21009L) || (w == 4L && se == 21009L) || (w == 14L && se == 21010L) || (w == 9L && se == 21010L) || (w == 12L && se == 22001L) || (w == 3L && se == 22001L) || (w == 14L && se == 22002L) || (w == 1L && se == 22002L) || (w == 7L && se == 22003L) || (w == 14L && se == 22003L) || (w == 10L && se == 22004L) || (w == 5L && se == 22004L) || (w == 7L && se == 22005L) || (w == 16L && se == 22005L) || (w == 1L && se == 22006L) || (w == 12L && se == 22006L) || (w == 12L && se == 22007L) || (w == 14L && se == 22007L) || (w == 11L && se == 22008L) || (w == 16L && se == 22008L) || (w == 1L && se == 22009L) || (w == 13L && se == 22009L) || (w == 16L && se == 22010L) || (w == 17L && se == 22010L) || (w == 1L && se == 23001L) || (w == 10L && se == 23001L) || (w == 12L && se == 23002L) || (w == 5L && se == 23002L) || (w == 13L && se == 23003L) || (w == 6L && se == 23003L) || (w == 9L && se == 23004L) || (w == 8L && se == 23004L) || (w == 17L && se == 23005L) || (w == 11L && se == 23005L) || (w == 11L && se == 23006L) || (w == 16L && se == 23006L) || (w == 1L && se == 24001L) || (w == 6L && se == 24001L) || (w == 12L && se == 24002L) || (w == 10L && se == 24002L) || (w == 18L && se == 24003L) || (w == 11L && se == 24003L) || (w == 9L && se == 24004L) || (w == 3L && se == 24004L) || (w == 18L && se == 24005L) || (w == 12L && se == 24005L) || (w == 1L && se == 24006L) || (w == 17L && se == 24006L))) {
                    w += 18L;
                }
                buffer.append("S");
                buffer.append(w << 32 | se);
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
        if ((id >= 1600 && id <= 1615) || id == 6100 || id == 7006) {
            return 1;
        }
        if ((id >= 1400 && id <= 1415) || id == 6106 || id == 7012) {
            return 2;
        }
        if ((id >= 1100 && id <= 1115) || id == 6124 || id == 7021) {
            return 3;
        }
        if ((id >= 1200 && id <= 1215) || id == 6122 || id == 7022) {
            return 4;
        }
        if ((id >= 2200 && id <= 2215) || id == 6109 || id == 7016) {
            return 5;
        }
        if ((id >= 2400 && id <= 2415) || id == 6119 || id == 7020) {
            return 6;
        }
        if ((id >= 1300 && id <= 1315) || id == 6103 || id == 7009) {
            return 7;
        }
        if ((id >= 1700 && id <= 1715) || id == 6102 || id == 7008) {
            return 8;
        }
        if ((id >= 2100 && id <= 2115) || id == 7013 || id == 6105) {
            return 9;
        }
        if ((id >= 1000 && id <= 1015) || id == 6118 || id == 7007) {
            return 10;
        }
        if (id == 7019 || id == 6120) {
            return 11;
        }
        if ((id >= 1800 && id <= 1815) || id == 6104 || id == 7011) {
            return 12;
        }
        if ((id >= 1900 && id <= 1915) || id == 6108 || id == 7017) {
            return 13;
        }
        if ((id >= 2200 && id <= 2215) || id == 6109 || id == 7010) {
            return 14;
        }
        if ((id >= 1500 && id <= 1515) || id == 7014 || id == 6117) {
            return 15;
        }
        if ((id >= 2000 && id <= 2015) || id == 6107 || id == 7015) {
            return 16;
        }
        if (id == 7018 || id == 6121) {
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
    
    public static JLabel getTimeJlabelTXT() {
        JLabel jLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setComposite(AlphaComposite.getInstance(5));
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setColor(Color.BLACK);
                Font fon1t = new Font("楷体", 0, 15);
                g2d.setFont(fon1t);
                g2d.setPaint(new Color(0, 0, 0, 0));
                this.setBackground(Color.red);
                this.setVerticalAlignment(0);
                g.translate(1, -4);
                super.paintComponent(g);
                g.translate(-1, -1);
            }
        };
        return jLabel;
    }
}
