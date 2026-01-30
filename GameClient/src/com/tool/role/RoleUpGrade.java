package com.tool.role;

import java.math.BigDecimal;

public class RoleUpGrade
{
    public String[] kx;
    public String[] ren;
    public String[] mo;
    public String[] xian;
    public String[] gui;
    public String[] LONG;
    private static RoleUpGrade gradekx;
    
    public RoleUpGrade() {
        this.kx = new String[] { "抗混乱", "抗封印", "抗昏睡", "抗中毒", "抗风", "抗火", "抗雷", "抗水", "命中率", "狂暴率", "致命率", "物理吸收率", "抗遗忘", "抗三尸", "抗鬼火", "抗浩然正气", "躲闪率" };
        this.ren = new String[] { "0=4=1", "1=4=1", "2=4=1", "3=4=1" };
        this.mo = new String[] { "0=8=1", "1=8=1", "2=8=1", "3=8=1", "4=12=1", "5=12=1", "6=12=1", "7=12=1", "8=20=1", "11=8=1" };
        this.xian = new String[] { "4=4=1", "5=4=1", "6=4=1", "7=4=1" };
        this.gui = new String[] { "15=1=-100", "16=4=1", "0=6=1", "1=6=1", "2=6=1", "3=6=1", "14=6=1", "12=6=1", "13=6=120", "4=8=-1", "5=8=-1", "6=8=-1", "7=8=-1", "8=12=1" };
        this.LONG = new String[] { "11=6=1", "0=6=1", "1=6=1", "2=6=1", "3=6=1", "12=6=1", "8=20=1" };
    }
    
    public static RoleUpGrade getGradeKX() {
        if (RoleUpGrade.gradekx == null) {
            RoleUpGrade.gradekx = new RoleUpGrade();
        }
        return RoleUpGrade.gradekx;
    }
    
    public String upGrade(int lvl, BigDecimal raceid) {
        if (lvl > 162) {
            lvl = 162;
        }
        if (raceid.intValue() == 10001) {
            return this.getren((double)lvl);
        }
        if (raceid.intValue() == 10002) {
            return this.getmo((double)lvl);
        }
        if (raceid.intValue() == 10003) {
            return this.getxian((double)lvl);
        }
        if (raceid.intValue() == 10004) {
            return this.getgui((double)lvl);
        }
        if (raceid.intValue() == 10005) {
            return this.getlong((double)lvl);
        }
        return this.getren((double)lvl);
    }
    
    public String getren(double lvl) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.ren.length; ++i) {
            if (i != 0) {
                builder.append("|");
            }
            String[] v = this.ren[i].split("=");
            builder.append(this.kx[Integer.parseInt(v[0])]);
            builder.append("=");
            builder.append(lvl / (double)Integer.parseInt(v[1]) * (double)Integer.parseInt(v[2]));
        }
        return builder.toString();
    }
    
    public String getmo(double lvl) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.mo.length; ++i) {
            if (i != 0) {
                builder.append("|");
            }
            String[] v = this.mo[i].split("=");
            builder.append(this.kx[Integer.parseInt(v[0])]);
            builder.append("=");
            builder.append(lvl / (double)Integer.parseInt(v[1]) * (double)Integer.parseInt(v[2]));
        }
        builder.append("|命中率=5|狂暴率=5|致命率=5");
        return builder.toString();
    }
    
    public String getxian(double lvl) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.xian.length; ++i) {
            if (i != 0) {
                builder.append("|");
            }
            String[] v = this.xian[i].split("=");
            builder.append(this.kx[Integer.parseInt(v[0])]);
            builder.append("=");
            builder.append(lvl / (double)Integer.parseInt(v[1]) * (double)Integer.parseInt(v[2]));
        }
        return builder.toString();
    }
    
    public String getgui(double lvl) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.gui.length; ++i) {
            if (i != 0) {
                builder.append("|");
            }
            String[] v = this.gui[i].split("=");
            builder.append(this.kx[Integer.parseInt(v[0])]);
            builder.append("=");
            builder.append(lvl / (double)Integer.parseInt(v[1]) * (double)Integer.parseInt(v[2]));
        }
        return builder.toString();
    }
    
    public String getlong(double lvl) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.LONG.length; ++i) {
            if (i != 0) {
                builder.append("|");
            }
            String[] v = this.LONG[i].split("=");
            builder.append(this.kx[Integer.parseInt(v[0])]);
            builder.append("=");
            builder.append(lvl / (double)Integer.parseInt(v[1]) * (double)Integer.parseInt(v[2]));
        }
        builder.append("|命中率=5|狂暴率=5|致命率=5");
        return builder.toString();
    }
}
