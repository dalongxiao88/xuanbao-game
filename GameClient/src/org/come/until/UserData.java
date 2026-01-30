package org.come.until;

import java.text.DecimalFormat;

import org.come.bean.XuanBao;
import org.come.model.Lingbao;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.util.List;
import java.util.ArrayList;

public class UserData
{
    public static final String[] LBKX;
    
    public static String xiaoshu(double xs) {
        String is = xs + "";
        String[] v = is.split("\\.");
        if (v.length == 1 || v[1].length() == 1) {
            return is;
        }
        v[1] = v[1].substring(0, 1);
        is = v[0] + "." + v[1];
        return is;
    }
    
    public static String xiaoshu3(double xs) {
        String is = xs + "";
        String[] v = is.split("\\.");
        if (v.length == 1 || v[1].length() <= 3) {
            return is;
        }
        v[1] = v[1].substring(0, 3);
        is = v[0] + "." + v[1];
        return is;
    }
    
    public static String vfh(String v, String b) {
        try {
            return v.split(b + "=")[1].split("\\|")[0];
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public static String Splice(String v, String b, int type) {
        boolean s = true;
        boolean s2 = false;
        if (type == 2 || type == 3 || type == 5) {
            s2 = true;
        }
        List<String> jihe = new ArrayList<>();
        if (v == null) {
            v = "";
        }
        String[] vs = v.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (type == 0) {
                if (!vs[i].equals(b)) {
                    jihe.add(vs[i]);
                }
                else {
                    s = false;
                }
            }
            else {
                String[] vs2 = vs[i].split("=");
                String[] vs3 = b.split("=");
                if (vs2[0].equals(vs3[0])) {
                    if (type == 1) {
                        jihe.add(b);
                    }
                    else if (type == 2) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 += x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 3) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 -= x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 5) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        if (x2 > x1) {
                            x1 = x2;
                        }
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                }
                else {
                    jihe.add(vs[i]);
                }
            }
        }
        if (s && type == 0) {
            jihe.add(b);
        }
        if (s2) {
            jihe.add(b);
        }
        StringBuffer genggai = new StringBuffer();
        for (int j = 0; j < jihe.size(); ++j) {
            if (!genggai.toString().equals("")) {
                genggai.append("|" + (String)jihe.get(j));
            }
            else {
                genggai.append((String)jihe.get(j));
            }
        }
        return genggai.toString();
    }
    
    public static boolean uptael(long tael) {
        if (tael <= 0L) {
            return false;
        }
        long shengyu = RoleData.getRoleData().getLoginResult().getGold().longValue() - tael;
        if (tael > 0L && shengyu >= 0L) {
            RoleData.getRoleData().getLoginResult().setGold(new BigDecimal(shengyu));
            Senduptael(tael + "");
            ZhuFrame.getZhuJpanel().addPrompt2("#W您扣除了#R" + tael + "#W两!");
            return true;
        }
        ZhuFrame.getZhuJpanel().addPrompt("#R注意：#Y您的银两不足！");
        return false;
    }
    
    public static void Senduptael(String tael) {
        String sendMes = Agreement.Deductiontael(tael);
        SendMessageUntil.toServer(sendMes);
    }
    
    public static void upling(Lingbao lingbao) {
        String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
        SendMessageUntil.toServer(sendMes);
    }
    public static void upling(XuanBao lingbao) {
        String sendMes = Agreement.Updatexuan(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
        SendMessageUntil.toServer(sendMes);
    }
    public static double getDouble(double min, double max, int type) {
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < type; ++i) {
            a.append("0");
        }
        max -= min;
        max = max / 4.0 * (double)Util.random.nextInt(5);
        DecimalFormat df = new DecimalFormat("#." + a);
        double b = (double)Double.valueOf(df.format(Util.random.nextDouble() * max + min));
        return b;
    }
    
    public static String kangxing(int lvl) {
        return kangxing(lvl, suiji(UserData.LBKX));
    }
    
    public static String kangxing(int lvl, String kx) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(kx + "=");
        int fudu = (lvl - 1) / 40 + 1;
        double a = getDouble((double)(fudu * 2) + 0.1, (double)(fudu * 2) + 0.1, 1);
        if (a > 10.0) {
            a = 10.0;
        }
        buffer.append(a);
        return buffer.toString();
    }
    
    public static String suiji(String[] v) {
        return v[Util.random.nextInt(v.length)];
    }
    
    public static String getMS(BigDecimal money) {
        StringBuffer buffer = new StringBuffer();
        long jg = money.longValue();
        jg /= 1000000L;
        if (jg <= 0L) {
            return "";
        }
        int x1 = (int)(jg / 1000L % 10L);
        int x2 = (int)(jg / 100L % 10L);
        int x3 = (int)(jg / 10L % 10L);
        int x4 = (int)(jg % 10L);
        String value = getS(x1);
        if (value != null) {
            if (x1 != 1) {
                buffer.append(value);
            }
            buffer.append("十");
        }
        value = getS(x2);
        if (value != null) {
            buffer.append(value);
        }
        if (buffer.length() != 0) {
            buffer.append("亿");
        }
        value = getS(x3);
        if (value != null) {
            buffer.append(value);
            buffer.append("千");
        }
        value = getS(x4);
        if (value != null) {
            buffer.append(value);
            buffer.append("百");
        }
        if (!buffer.substring(buffer.length() - 1, buffer.length()).equals("亿")) {
            buffer.append("万");
        }
        return buffer.toString();
    }
    
    public static String getS(int x) {
        switch (x) {
            case 1: {
                return "一";
            }
            case 2: {
                return "二";
            }
            case 3: {
                return "三";
            }
            case 4: {
                return "四";
            }
            case 5: {
                return "五";
            }
            case 6: {
                return "六";
            }
            case 7: {
                return "七";
            }
            case 8: {
                return "八";
            }
            case 9: {
                return "九";
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        LBKX = new String[] { "抗混乱", "抗封印", "抗物理", "抗遗忘", "抗鬼火", "抗风", "抗雷", "抗水", "抗火", "抗昏睡", "抗震慑", "抗中毒" };
    }
}
