package come.tool.FightingData;
/**
        * 五行系统
        * @author Administrator
        *
        */
public class FiveLineSystem
{
    public static void main(String[] args) {
        double[] myline = { 0.0, 0.0, 0.0, 0.0, 0.0 };
        double[] myforce = { 0.0, 0.0, 0.0, 0.0, 200.0 };
        double[] nomyline = { 0.0, 0.0, 0.0, 0.0, 100.0 };
        System.out.println(fiveline(myline, nomyline, myforce));
        double xs = fivenexus(myline, nomyline);
        System.out.println(xs);
        System.out.println(xs * forcenexus(myforce, nomyline));
    }
    //  金克木，木克土，土克水，水克火，火克金      金木土水火
    public static double getSwing(ManData mydata, ManData nomydata) {
        double[] myline = shuxing(mydata, null, 0);
        double[] nomyline = shuxing(nomydata, null, 0);
        double[] myforce = shuxing(mydata, nomydata, 1);
        return fiveline(myline, nomyline, myforce);
    }
    //获取指定属性集合 0获取金木土水火 1获取强力克金木土水火
    public static double[] shuxing(ManData data, ManData nomydata, int type) {
        double[] ds = new double[5];
        Ql roleQuality = data.getQuality();
        if (roleQuality != null) {
            if (type == 0) {
                int n = 0;
                ds[n] += roleQuality.getRolewxj();
                int n2 = 1;
                ds[n2] += roleQuality.getRolewxm();
                int n3 = 2;
                ds[n3] += roleQuality.getRolewxt();
                int n4 = 3;
                ds[n4] += roleQuality.getRolewxs();
                int n5 = 4;
                ds[n5] += roleQuality.getRolewxh();
                if (data.xzstate("水") != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        int n6 = i;
                        ds[n6] /= 2.0;
                    }
                    int n7 = 3;
                    ds[n7] += 50.0;
                }
                else if (data.xzstate("金") != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        int n8 = i;
                        ds[n8] /= 2.0;
                    }
                    int n9 = 0;
                    ds[n9] += 50.0;
                }
                else if (data.xzstate("木") != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        int n10 = i;
                        ds[n10] /= 2.0;
                    }
                    int n11 = 1;
                    ds[n11] += 50.0;
                }
                else if (data.xzstate("土") != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        int n12 = i;
                        ds[n12] /= 2.0;
                    }
                    int n13 = 2;
                    ds[n13] += 50.0;
                }
                else if (data.xzstate("火") != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        int n14 = i;
                        ds[n14] /= 2.0;
                    }
                    int n15 = 4;
                    ds[n15] += 50.0;
                }
                else if (data.xzstate(TypeUtil.BB_WYJK) != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        ds[i] = 20.0;
                    }
                }
                else if (data.xzstate(TypeUtil.BB_TNFY) != null) {
                    for (int i = 0; i < ds.length; ++i) {
                        ds[i] = 45.0;
                    }
                }
            }
            else {
                ds[0] = roleQuality.getRolewxqkj();
                ds[1] = roleQuality.getRolewxqkm();
                ds[2] = roleQuality.getRolewxqkt();
                ds[3] = roleQuality.getRolewxqks();
                ds[4] = roleQuality.getRolewxqkh();
                double xs = roleQuality.getQ_qk();
                if (nomydata != null && nomydata.getQuality() != null) {
                    xs -= nomydata.getQuality().getK_qk();
                    FightingSkill skill = nomydata.getAppendSkill(9405);
                    if (skill != null) {
                        xs -= skill.getSkillhurt();
                    }
                }
                int n16 = 0;
                ds[n16] += xs;
                int n17 = 1;
                ds[n17] += xs;
                int n18 = 2;
                ds[n18] += xs;
                int n19 = 3;
                ds[n19] += xs;
                int n20 = 4;
                ds[n20] += xs;
            }
        }
        else {
            ds[0] = 0.0;
            ds[2] = (ds[1] = 0.0);
            ds[4] = (ds[3] = 0.0);
        }
        return ds;
    }
    //五行伤害和强力克加成
    public static double fiveline(double[] myline, double[] nomyline, double[] myforce) {
        return forcenexus(myforce, nomyline) * fivenexus(myline, nomyline);
    }
    //  获取强力克的差值
    public static double forcenexus(double[] myforce, double[] nomyline) {
        double cha = 1.0;
        for (int i = 0; i < myforce.length; ++i) {
            if (myforce[i] != 0.0 && nomyline[i] != 0.0) {
                cha += myforce[i] * nomyline[i] * 8.0E-5;
            }
        }
        return cha;
    }
    //	获取五行相克的差值
    public static double fivenexus(double[] myline, double[] nomyline) {
        double cha = 0.0;
        for (int i = 0; i < myline.length; ++i) {
            if (myline[i] != 0.0 && nomyline[(i < 4) ? (i + 1) : 0] != 0.0) {
                cha += myline[i] * nomyline[(i < 4) ? (i + 1) : 0] / 100.0;
            }
        }
        for (int i = 0; i < nomyline.length; ++i) {
            if (nomyline[i] != 0.0 && myline[(i < 4) ? (i + 1) : 0] != 0.0) {
                cha -= nomyline[i] * myline[(i < 4) ? (i + 1) : 0] / 100.0;
            }
        }
        return cha / 100.0 * 0.4 + 1.0;
    }
}
