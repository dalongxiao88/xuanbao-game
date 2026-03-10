package org.come.bean;

import org.come.entity.Lingbao;
import java.util.Map;

/**
 * 灵宝配置映射对象。
 */
public class LingbaoBean
{
    private Map<String, Lingbao> allLingbao;
    
    public Map<String, Lingbao> getAllLingbao() {
        return this.allLingbao;
    }

    /** 语义化别名：全部灵宝配置。 */
    public Map<String, Lingbao> getAllLingbaoMap() {
        return this.allLingbao;
    }
    
    public void setAllLingbao(Map<String, Lingbao> allLingbao) {
        this.allLingbao = allLingbao;
    }

    public void setAllLingbaoMap(Map<String, Lingbao> allLingbaoMap) {
        this.allLingbao = allLingbaoMap;
    }
}
