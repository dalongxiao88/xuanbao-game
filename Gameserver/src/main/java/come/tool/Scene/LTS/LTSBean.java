package come.tool.Scene.LTS;

import java.util.Iterator;
import java.util.Map;
import org.come.bean.PathPoint;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;

public class LTSBean
{
    private int ci;
    private BigDecimal[] ids;
    private ConcurrentHashMap<BigDecimal, PathPoint> ltsMap;
    
    public LTSBean() {
        this.ids = new BigDecimal[0];
        this.ltsMap = new ConcurrentHashMap<>();
    }
    
    public void resetL(int i) {
        if (i == 0) {
            for (Map.Entry<BigDecimal, PathPoint> entrys : this.ltsMap.entrySet()) {
                ((PathPoint)entrys.getValue()).setY(0);
            }
        }
        else {
            this.ci = 0;
            this.ltsMap.clear();
        }
    }
    
    public int getCi() {
        return this.ci;
    }
    
    public void setCi(int ci) {
        this.ci = ci;
    }
    
    public ConcurrentHashMap<BigDecimal, PathPoint> getLtsMap() {
        return this.ltsMap;
    }
    
    public void setLtsMap(ConcurrentHashMap<BigDecimal, PathPoint> ltsMap) {
        this.ltsMap = ltsMap;
    }
    
    public BigDecimal[] getIds() {
        return this.ids;
    }
    
    public void setIds(BigDecimal[] ids) {
        this.ids = ids;
    }
}
