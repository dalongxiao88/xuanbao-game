package org.come.bean;

import org.come.model.Lingbao;
import java.util.List;

/**
 * 灵宝列表返回对象。
 */
public class LingbaoResultBean
{
    private List<Lingbao> lingbao;
    
    public List<Lingbao> getLingbao() {
        return this.lingbao;
    }

    /** 语义化别名：灵宝列表。 */
    public List<Lingbao> getLingbaoList() {
        return this.lingbao;
    }
    
    public void setLingbao(List<Lingbao> lingbao) {
        this.lingbao = lingbao;
    }

    public void setLingbaoList(List<Lingbao> lingbaoList) {
        this.lingbao = lingbaoList;
    }
}
