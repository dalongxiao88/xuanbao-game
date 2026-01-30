package org.come.readBean;

import come.tool.Calculation.BaseMeridians;
import cn.hutool.core.util.RandomUtil;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.come.bean.Meridians;
import java.util.List;
import java.util.Map;

public class AllMeridians
{
    private Map<Integer, List<Meridians>> meridiansMap;
    
    public AllMeridians() {
        this.meridiansMap = new ConcurrentHashMap<>();
    }
    
    public Map<Integer, List<Meridians>> getMeridians() {
        return this.meridiansMap;
    }
    
    public void setMeridians(List<Meridians> meridiansList) {
        for (Meridians meridians : meridiansList) {
            if (!this.meridiansMap.containsKey(Integer.valueOf(meridians.getId()))) {
                this.meridiansMap.put(Integer.valueOf(meridians.getId()), new ArrayList<>());
            }
            ((List<Meridians>)this.meridiansMap.get(Integer.valueOf(meridians.getId()))).add(meridians);
        }
    }
    
    public List<Meridians> getByType(int id) {
        return (List<Meridians>)this.meridiansMap.get(Integer.valueOf(id));
    }
    
    public Meridians getByQuality(int id, int quality) {
        for (Meridians m : this.getByType(id)) {
            if (m.getQuality() == quality) {
                return m;
            }
        }
        return null;
    }
    
    public Meridians getRandomByType(int id) {
        for (Meridians m : (List<Meridians>)this.meridiansMap.get(Integer.valueOf(id))) {
            int p = m.getProbability();
            int r = RandomUtil.randomInt(100) + 1;
            if (p >= r) {
                return m;
            }
        }
        return (Meridians)RandomUtil.randomEle((List<Meridians>)this.meridiansMap.get(Integer.valueOf(id)));
    }
    
    public Meridians getFirstByType(int id) {
        return (Meridians)((List<Meridians>)this.meridiansMap.get(Integer.valueOf(id))).get(0);
    }
    
    public static BaseMeridians createRandomBaseMeridians(Meridians meridians) {
        String[] ss = meridians.getAttrs().split("\\|");
        String attr = (String)RandomUtil.randomEle(ss);
        double value = RandomUtil.randomDouble(meridians.getMin(), meridians.getMax());
        BaseMeridians m = new BaseMeridians(meridians.getId(), 0, meridians.getQuality(), attr, value, 0);
        return m;
    }
}
