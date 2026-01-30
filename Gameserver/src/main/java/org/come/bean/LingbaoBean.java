package org.come.bean;

import org.come.entity.Lingbao;
import java.util.Map;

public class LingbaoBean
{
    private Map<String, Lingbao> allLingbao;
    
    public Map<String, Lingbao> getAllLingbao() {
        return this.allLingbao;
    }
    
    public void setAllLingbao(Map<String, Lingbao> allLingbao) {
        this.allLingbao = allLingbao;
    }
}
