package org.come.server;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class GolemConfig
{
    private ConcurrentHashMap<String, String> configs;
    private ConcurrentHashMap<BigDecimal, GolemStallConfig> goodsStallConfig;
    private ConcurrentHashMap<BigDecimal, GolemStallConfig> petStallConfig;
    private ConcurrentHashMap<String, GolemStallConfig> lingBaoStallConfig;
    private ConcurrentHashMap<BigDecimal, GolemStallConfig> shouGouStallConfig;
    private Point[] stallPoints;
    private Point[] idlePoints;
    
    public GolemConfig() {
        this.goodsStallConfig = new ConcurrentHashMap<>();
        this.petStallConfig = new ConcurrentHashMap<>();
        this.lingBaoStallConfig = new ConcurrentHashMap<>();
        this.shouGouStallConfig = new ConcurrentHashMap<>();
    }
    
    public String get(String key) {
        return (String)this.configs.get(key);
    }
    
    public String get(String key, String defaultValue) {
        if (this.configs.get(key) != null) {
            defaultValue = (String)this.configs.get(key);
        }
        return defaultValue;
    }
    
    public int getToInt(String key, int defaultValue) {
        if (this.configs.get(key) != null) {
            try {
                defaultValue = Integer.parseInt((String)this.configs.get(key));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
    public void setSwitch(String key,String value) {
        configs.put(key, value);
    }
    public boolean stallContains(int map, int x, int y) {
        return this.contains(this.getStallPoints(), map, x, y);
    }
    
    public boolean idleContains(int map, int x, int y) {
        return this.contains(this.getIdlePoints(), map, x, y);
    }
    
    public Point getStallRandomPoint() {
        return this.getRandomPoint(this.getStallPoints());
    }
    
    public Point getIdleRandomPoint() {
        return this.getRandomPoint(this.getIdlePoints());
    }
    
    public boolean contains(Point[] points, int map, int x, int y) {
        if (points != null) {
            for (int i = 0; i < points.length; ++i) {
                if (points[i].contains(map, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Point getRandomPoint(Point[] points) {
        if (points != null) {
            return points[GameServer.random.nextInt(points.length)].generatePointsInRegion();
        }
        return null;
    }
    
    public Point[] getStallPoints() {
        if (this.stallPoints == null) {
            String value = (String)this.configs.get("摆摊坐标");
            if (StringUtils.isNotBlank(value)) {
                String[] vals = value.split("\\|");
                this.stallPoints = new Point[vals.length];
                for (int i = 0; i < vals.length; ++i) {
                    this.stallPoints[i] = new Point();
                    String[] vs = vals[i].split("=");
                    this.stallPoints[i].setMapId(Integer.parseInt(vs[0]));
                    for (int j = 1; j < vs.length; ++j) {
                        String[] p = vs[j].split(",");
                        this.stallPoints[i].addPoint(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                    }
                }
            }
        }
        return this.stallPoints;
    }
    
    public Point[] getIdlePoints() {
        if (this.idlePoints == null) {
            String value = (String)this.configs.get("闲置坐标");
            if (StringUtils.isNotBlank(value)) {
                String[] vals = value.split("\\|");
                this.idlePoints = new Point[vals.length];
                for (int i = 0; i < vals.length; ++i) {
                    this.idlePoints[i] = new Point();
                    String[] vs = vals[i].split("=");
                    this.idlePoints[i].setMapId(Integer.parseInt(vs[0]));
                    for (int j = 1; j < vs.length; ++j) {
                        String[] p = vs[j].split(",");
                        this.idlePoints[i].addPoint(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                    }
                }
            }
        }
        return this.idlePoints;
    }
    
    public GolemStallConfig getGoodsStallConfig(BigDecimal goodsId) {
        return (GolemStallConfig)this.goodsStallConfig.get(goodsId);
    }
    
    public GolemStallConfig getPetStallConfig(BigDecimal goodsId) {
        return (GolemStallConfig)this.petStallConfig.get(goodsId);
    }
    
    public GolemStallConfig getBaoStallConfig(String baoName) {
        return (GolemStallConfig)this.lingBaoStallConfig.get(baoName);
    }
    
    public GolemStallConfig getShouGouStallConfig(BigDecimal goodsId) {
        return (GolemStallConfig)this.shouGouStallConfig.get(goodsId);
    }
    
    public List<BigDecimal> getShouGouGoodsIds() {
        List<BigDecimal> shouGouGoodsIds = new ArrayList<>();
        for (BigDecimal goodsId : this.shouGouStallConfig.keySet()) {
            shouGouGoodsIds.add(goodsId);
        }
        return shouGouGoodsIds;
    }
    
    public void setConfigs(ConcurrentHashMap<String, String> configs) {
        this.configs = configs;
    }
    
    public void addGoodsStallConfig(BigDecimal goodsId, GolemStallConfig golemStallConfig) {
        this.goodsStallConfig.put(goodsId, golemStallConfig);
    }
    
    public void addPetStallConfig(BigDecimal petId, GolemStallConfig golemStallConfig) {
        this.petStallConfig.put(petId, golemStallConfig);
    }
    
    public void addLingBaoStallConfig(String baoName, GolemStallConfig golemStallConfig) {
        this.lingBaoStallConfig.put(baoName, golemStallConfig);
    }
    
    public void addShouGouStallConfig(BigDecimal goodsId, GolemStallConfig golemStallConfig) {
        this.shouGouStallConfig.put(goodsId, golemStallConfig);
    }
}
