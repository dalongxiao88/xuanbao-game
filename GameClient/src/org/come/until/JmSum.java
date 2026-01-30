package org.come.until;

import org.come.Frame.ZhuFrame;

public class JmSum
{
    public static long ZM(long V) {
        V += 1341L;
        boolean is = V < 0L;
        if (is) {
            V = -V;
        }
        int s = Util.random.nextInt(9) + 1;
        int w = 0;
        long zhi = (long)s;
        if (V != 0L) {
            w = (int)Math.log10((double)V);
        }
        if (s >> 2 == 0) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            int yzs = (int)((V + 4L) % 10L);
            for (int i = w; i >= 0; --i) {
                zhi *= 10L;
                zhi += V % 10L;
                if (s == i) {
                    zhi *= 10L;
                    zhi += (long)yzs;
                }
                V /= 10L;
            }
        }
        else if (s >> 2 == 1) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            int yzs = 0;
            if (V >= 10L) {
                yzs = (int)((V / 10L + 2L) % 10L);
            }
            else {
                yzs = (int)((V + 2L) % 10L);
            }
            for (int i = w; i >= 0; --i) {
                zhi *= 10L;
                zhi += V % 10L;
                if (s == i) {
                    zhi *= 10L;
                    zhi += (long)yzs;
                }
                V /= 10L;
            }
        }
        else if (s >> 2 == 2) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            int yzs = (int)((V + 1L) % 10L);
            for (int i = w; i >= 0; --i) {
                zhi *= 10L;
                zhi += V % 10L;
                if (s == i) {
                    zhi *= 10L;
                    zhi += (long)yzs;
                }
                V /= 10L;
            }
        }
        if (is) {
            zhi = -zhi;
        }
        return zhi;
    }
    
    public static long MZ(long V) {
        if (V == 0L) {
            return 0L;
        }
        boolean is = V < 0L;
        if (is) {
            V = -V;
        }
        long zhi = 0L;
        int w = (int)Math.log10((double)V);
        int s = (int)(V / (long)Math.pow(10.0, (double)w));
        w -= 2;
        if (s >> 2 == 0) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            ++w;
            int yzs = 0;
            int yzv = 0;
            for (int i = 0; i <= w; ++i) {
                if (s == i) {
                    yzs = (int)(V % 10L);
                    yzs -= 4;
                    if (yzs < 0) {
                        yzs += 10;
                    }
                }
                else {
                    if (i == w) {
                        yzv = (int)(V % 10L);
                    }
                    zhi *= 10L;
                    zhi += V % 10L;
                }
                V /= 10L;
            }
            if (yzs != yzv) {
                xiugaiqi();
                return 0L;
            }
        }
        else if (s >> 2 == 1) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            ++w;
            int yzs = 0;
            int yzv = 0;
            for (int i = 0; i <= w; ++i) {
                if (s == i) {
                    yzs = (int)(V % 10L);
                    yzs -= 2;
                    if (yzs < 0) {
                        yzs += 10;
                    }
                }
                else {
                    if (w >= 2) {
                        if (i == w - 1) {
                            yzv = (int)(V % 10L);
                        }
                    }
                    else if (i == w) {
                        yzv = (int)(V % 10L);
                    }
                    zhi *= 10L;
                    zhi += V % 10L;
                }
                V /= 10L;
            }
            if (yzs != yzv) {
                xiugaiqi();
                return 0L;
            }
        }
        else if (s >> 2 == 2) {
            s += 3;
            if (s == 3) {
                s = 1;
            }
            else if (s == 10) {
                s = 5;
            }
            if (w != 0) {
                s %= w;
            }
            else {
                s = 0;
            }
            ++w;
            int yzs = 0;
            int yzv = 0;
            for (int i = 0; i <= w; ++i) {
                if (s == i) {
                    yzs = (int)(V % 10L);
                    if (--yzs < 0) {
                        yzs += 10;
                    }
                }
                else {
                    if (i == w) {
                        yzv = (int)(V % 10L);
                    }
                    zhi *= 10L;
                    zhi += V % 10L;
                }
                V /= 10L;
            }
            if (yzs != yzv) {
                xiugaiqi();
                return 0L;
            }
        }
        if (is) {
            zhi = -zhi;
        }
        zhi -= 1341L;
        return zhi;
    }
    
    public static void xiugaiqi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ZhuFrame.getZhuJpanel().addPrompt2("系统检测到有非法进程,20秒后退出游戏!");
                }
                catch (Exception ex) {}
                try {
                    Thread.sleep(20000L);
                    System.exit(0);
                }
                catch (Exception ex2) {}
            }
        }).start();
    }
}
