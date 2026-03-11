package org.come.entity;

import java.math.BigDecimal;

/**
 * 仇人/黑名单记录实体。
 *
 * 字段 `unknown` 当前仍缺少稳定业务语义，暂保留原命名，待后续链路确认后再细化。
 */
public class Haters
{
    private BigDecimal roleid;
    private String unknown;
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getUnknown() {
        return this.unknown;
    }
    
    public void setUnknown(String unknown) {
        this.unknown = ((unknown == null) ? null : unknown.trim());
    }
}
