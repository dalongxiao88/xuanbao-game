package org.come.extInterBean;

import come.tool.Role.CBGData;
import org.come.entity.Salegoods;
import org.come.bean.SearchGoodsResultBean;
import java.util.List;

public class RoleSellQueryResp
{
    private String type;
    private List<RoleSellRoleInfo> roleInfo;
    private SearchGoodsResultBean searchGoods;
    private Salegoods salegoods;
    private CBGData cbgData;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<RoleSellRoleInfo> getRoleInfo() {
        return this.roleInfo;
    }
    
    public void setRoleInfo(List<RoleSellRoleInfo> roleInfo) {
        this.roleInfo = roleInfo;
    }
    
    public SearchGoodsResultBean getSearchGoods() {
        return this.searchGoods;
    }
    
    public void setSearchGoods(SearchGoodsResultBean searchGoods) {
        this.searchGoods = searchGoods;
    }
    
    public CBGData getCbgData() {
        return this.cbgData;
    }
    
    public void setCbgData(CBGData cbgData) {
        this.cbgData = cbgData;
    }
    
    public Salegoods getSalegoods() {
        return this.salegoods;
    }
    
    public void setSalegoods(Salegoods salegoods) {
        this.salegoods = salegoods;
    }
}
