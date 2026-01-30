package come.tool.Calculation;

import org.come.bean.Meridians;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.come.server.GameServer;

public class BaseMeridians
{
    //	经脉编号_修炼进度_品质_属性_值
    private int id;// 经脉编号
    private int exp;// 进度经验
    private int quality;// 经脉品质
    private String key;
    private double value;
    private int stage;// 经脉阶段，境界
    
    public BaseMeridians() {
    }
    
    public BaseMeridians(int id, int exp, int quality, String key, double value, int stage) {
        this.id = id;
        this.exp = exp;
        this.quality = quality;
        this.key = key;
        this.value = value;
        this.stage = stage;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public void setQuality(int quality) {
        this.quality = quality;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public void setStage(int stage) {
        this.stage = stage;
    }
    
    @Override
    public String toString() {
        return this.id + "_" + this.exp + "_" + this.quality + "_" + this.key + "_" + this.value + "_" + this.stage;
    }
    
    public double getKeyValue() {
        String[] ss = GameServer.getAllMeridians().getByQuality(this.id, this.quality).getStages().split("_");
        String s = ss[this.stage];
        return this.value * (1.0 + Double.parseDouble(s));
    }
    
    static BaseMeridians createBaseMeridians(String meridians) {
        String[] strs = meridians.split("_");
        return new BaseMeridians(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]), strs[3], Double.parseDouble(strs[4]), Integer.parseInt(strs[5]));
    }
    
    public static BaseMeridians getBaseMeridians(List<BaseMeridians> list, int id) {
        for (BaseMeridians baseMeridians : list) {
            if (baseMeridians.getId() == id) {
                return baseMeridians;
            }
        }
        return null;
    }
    
    public static boolean isContains(List<BaseMeridians> list, int id) {
        for (BaseMeridians baseMeridians : list) {
            if (baseMeridians.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static List<BaseMeridians> createBaseMeridiansList(String meridians) {
        List<BaseMeridians> list = new ArrayList<>();
        if (StrUtil.isBlankIfStr(meridians)) {
            return list;
        }
        String[] strs;
        for (String string : strs = meridians.split("\\|")) {
            list.add(createBaseMeridians(string));
        }
        return list;
    }
    
    public static String createBaseMeridiansString(List<BaseMeridians> list) {
        String str = "";
        for (BaseMeridians m : list) {
            str = str + m.toString() + "|";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }
    
    public static BaseQl[] createBaseQl(List<BaseMeridians> list) {
        BaseQl[] qls = new BaseQl[list.size()];
        for (int i = 0; i < qls.length; ++i) {
            BaseMeridians bm = (BaseMeridians)list.get(i);
            qls[i] = new BaseQl(bm.getKey(), bm.getKeyValue());
        }
        return qls;
    }
    
    public void reset(int attr, int quality) {
        String a = null;
        int q = 0;
        if (attr == 1) {
            a = this.getKey();
        }
        else {
            Meridians m = GameServer.getAllMeridians().getFirstByType(this.getId());
            String[] ss = m.getAttrs().split("\\|");
            a = (String)RandomUtil.randomEle(ss);
        }
        if (quality == 1) {
            q = this.getQuality();
        }
        else {
            Meridians m = GameServer.getAllMeridians().getRandomByType(this.getId());
            q = m.getQuality();
        }
        Meridians m = GameServer.getAllMeridians().getByQuality(this.getId(), q);
        double value = RandomUtil.randomDouble(m.getMin(), m.getMax());
        this.quality = q;
        this.key = a;
        this.value = value;
    }
}
