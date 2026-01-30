package com.tool.pet;

import org.come.until.Util;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.HashMap;
import java.util.Map;

public class BabyProperty
{
    private static BabyProperty babyProperty;
    private Map<String, Integer> maps;
    
    public BabyProperty() {
        this.maps = new HashMap<>();
    }
    
    public static BabyProperty getBabyProperty() {
        if (BabyProperty.babyProperty == null) {
            BabyProperty.babyProperty = new BabyProperty();
        }
        return BabyProperty.babyProperty;
    }
    
    private void addmap(String k, int v) {
        if (this.maps.get(k) != null) {
            this.maps.put(k, Integer.valueOf((int)this.maps.get(k) + v));
        }
    }
    
    private void reset(Baby baby) {
        this.maps.put("气质", baby.getQizhi());
        this.maps.put("内力", baby.getNeili());
        this.maps.put("智力", baby.getZhili());
        this.maps.put("耐力", baby.getNaili());
        this.maps.put("名气", baby.getMingqi());
        this.maps.put("道德", baby.getDaode());
        this.maps.put("叛逆", baby.getPanni());
        this.maps.put("玩性", baby.getWanxing());
        this.maps.put("孝心", baby.getXiaoxin());
        this.maps.put("皮肤", Integer.valueOf(((int)baby.getChildSex() == 0) ? 100001 : 100002));
    }
    
    public Map<String, Integer> getProperty(Baby baby, BigDecimal[] bids) {
        this.reset(baby);
        if (bids != null) {
            int size = 0;
            for (int i = 0; i < bids.length; ++i) {
                Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(bids[i]);
                if (goodstable != null) {
                    String[] v = goodstable.getValue().split("\\|");
                    for (int j = 1; j < v.length; ++j) {
                        String[] v2 = v[j].split("=");
                        if (v2[0].equals("等级")) {
                            size += ((Integer.parseInt(v2[1]) >= 3) ? 1 : 0);
                        }
                        else {
                            this.addmap(v2[0], Integer.parseInt(v2[1]));
                        }
                    }
                }
            }
            if (size == 4) {
                this.maps.put("皮肤", Integer.valueOf(((int)baby.getChildSex() == 0) ? (100005 + Util.random.nextInt(3) * 2) : (100006 + Util.random.nextInt(3) * 2)));
            }
        }
        return this.maps;
    }
}
