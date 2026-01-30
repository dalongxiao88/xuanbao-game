package org.come.action.suit;

import org.come.bean.QualityClBean;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class QualityCPool
{
    private static QualityCPool cPool;
    ConcurrentHashMap<BigDecimal, QualityClBean> lhMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> lqMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> tzMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> jxMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> wxMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> arenaMap;
    ConcurrentHashMap<BigDecimal, QualityClBean> diancui;
    ConcurrentHashMap<BigDecimal, QualityClBean> diancui1;
    
    public static QualityCPool getcPool() {
        if (QualityCPool.cPool == null) {
            QualityCPool.cPool = new QualityCPool();
        }
        return QualityCPool.cPool;
    }
    
    public QualityCPool() {
        this.lhMap = new ConcurrentHashMap<>();
        this.lqMap = new ConcurrentHashMap<>();
        this.tzMap = new ConcurrentHashMap<>();
        this.jxMap = new ConcurrentHashMap<>();
        this.wxMap = new ConcurrentHashMap<>();
        this.arenaMap = new ConcurrentHashMap<>();
        this.diancui = new ConcurrentHashMap<>();
        this.diancui1 = new ConcurrentHashMap<>();
    }
    
    public void addExtra(QualityClBean clBean) {
        if (clBean.getType() == 1 || clBean.getType() == 53) {
            this.lhMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 2) {
            this.lqMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 4) {
            this.tzMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 44 || clBean.getType() == 46) {
            this.jxMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 54) {
            this.wxMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() >= 70 && clBean.getType() <= 79) {
            this.arenaMap.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 8) {
            this.diancui.put(clBean.getRgid(), clBean);
        }
        else if (clBean.getType() == 9) {
            this.diancui1.put(clBean.getRgid(), clBean);
        }
    }
    
    public QualityClBean getExtra(int type, BigDecimal rgid) {
        QualityClBean clBean = null;
        if (type == 1 || type == 53) {
            clBean = (QualityClBean)this.lhMap.remove(rgid);
            if (clBean != null) {
                clBean.setType(1);
            }
        }
        else if (type == 2) {
            clBean = (QualityClBean)this.lqMap.remove(rgid);
        }
        else if (type == 4) {
            clBean = (QualityClBean)this.tzMap.remove(rgid);
        }
        else if (type == 44 || type == 46) {
            clBean = (QualityClBean)this.jxMap.remove(rgid);
            if (clBean != null) {
                clBean.setType(6);
            }
        }
        else if (type == 54) {
            clBean = (QualityClBean)this.wxMap.remove(rgid);
            if (clBean != null) {
                clBean.setType(7);
            }
        }
        else if (type >= 70 && type <= 79) {
            clBean = (QualityClBean)this.arenaMap.remove(rgid);
        }
        else if (type == 8) {
            clBean = (QualityClBean)this.diancui.remove(rgid);
        }
        else if (type == 9) {
            clBean = (QualityClBean)this.diancui1.remove(rgid);
        }
        return clBean;
    }
}
