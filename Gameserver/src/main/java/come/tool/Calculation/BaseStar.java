package come.tool.Calculation;

public class BaseStar
{
    private String[] vs;//星阵属性 星阵属性=星阵名称=星阵的五行属性=宫位属性星卡
    private double xs;//五行加成之力
    private int man;
    
    public BaseStar(String xz, String wx) {
        // TODO Auto-generated constructor stub
        this.vs = xz.split("=");
        String[] wxs = wx.split("&");
        this.xs = 0.0;
        for (int j = 1; j < wxs.length; ++j) {
            String[] split = wxs[j].split("=");
            this.xs += fiveElementRestrainCreate(this.vs[2], split[0], split[1]);
        }
        this.xs /= 100.0;
    }
    /** 判断相克五行属性 */
    public static Integer fiveElementRestrainNum(String attr) {
        if (attr.equals("金")) {
            return 1;
        }
        if (attr.equals("木")) {
            return 2;
        }
        if (attr.equals("土")) {
            return 3;
        }
        if (attr.equals("水")) {
            return 4;
        }
        if (attr.equals("火")) {
            return 5;
        }
        return null;
    }
    /** 判断相生五行属性 */
    public static Integer fiveElementCreateNum(String attr) {
        if (attr.equals("金")) {
            return 1;
        }
        if (attr.equals("水")) {
            return 2;
        }
        if (attr.equals("木")) {
            return 3;
        }
        if (attr.equals("火")) {
            return 4;
        }
        if (attr.equals("土")) {
            return 5;
        }
        return null;
    }
    /** 判断相生相克 */
    public static double fiveElementRestrainCreate(String attr, String value, String num) {
        Integer num2 = fiveElementRestrainNum(attr);
        Integer num3 = fiveElementRestrainNum(value);
        int abs = Math.abs((int)num2 - (int)num3);//相克
        if ((abs == 1 || abs == 4) && (((int)num2 == 1 && (int)num3 == 5) || (int)num2 > (int)num3)) {
            return (double)Integer.parseInt(num) * 0.1;
        }
        num2 = fiveElementCreateNum(attr);
        num3 = fiveElementCreateNum(value);
        abs = Math.abs((int)num2 - (int)num3);//相生
        if ((abs == 1 || abs == 4) && (((int)num2 == 1 && (int)num3 == 5) || (int)num2 > (int)num3)) {
            return (double)Integer.parseInt(num) * 0.3;
        }
        return (double)Integer.parseInt(num) * 0.2;
    }
    
    public String[] getVs() {
        return this.vs;
    }
    
    public void setVs(String[] vs) {
        this.vs = vs;
    }
    
    public double getXs() {
        return this.xs;
    }
    
    public void setXs(double xs) {
        this.xs = xs;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
}
