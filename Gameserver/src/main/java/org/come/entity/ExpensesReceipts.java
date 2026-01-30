package org.come.entity;

import java.math.BigDecimal;

public class ExpensesReceipts
{
    private BigDecimal erid;
    private String playeracc;
    private int type;
    private BigDecimal roleid;
    private BigDecimal recharge;
    private BigDecimal playerpay;
    private BigDecimal yuanbao;
    private String paytime;
    private BigDecimal sid;
    private BigDecimal returntype;
    private BigDecimal appid;
    private BigDecimal managerid;
    private Integer start;
    private Integer end;
    private int ordernumbertype;
    private BigDecimal goodsid;
    private BigDecimal buyrole;
    private BigDecimal sellrole;
    private BigDecimal buyuserid;
    private String buyrolebalance;
    private String gongshisign;
    private String quid;
    private Double payofprofits;
    private Double gspayofprofits;
    private String buyroleName;
    private String sellroleName;
    private String buyuseridName;
    private String quname;
    private String goodssomething;
    private int pageNumber;
    
    public ExpensesReceipts() {
        this.ordernumbertype = 0;
        this.pageNumber = 1;
    }
    
    public String getGoodssomething() {
        return this.goodssomething;
    }
    
    public void setGoodssomething(String goodssomething) {
        this.goodssomething = goodssomething;
    }
    
    public Integer getStart() {
        return this.start;
    }
    
    public void setStart(Integer start) {
        this.start = start;
    }
    
    public Integer getEnd() {
        return this.end;
    }
    
    public void setEnd(Integer end) {
        this.end = end;
    }
    
    public int getOrdernumbertype() {
        return this.ordernumbertype;
    }
    
    public void setOrdernumbertype(int ordernumbertype) {
        this.ordernumbertype = ordernumbertype;
    }
    
    public BigDecimal getErid() {
        return this.erid;
    }
    
    public void setErid(BigDecimal erid) {
        this.erid = erid;
    }
    
    public String getPlayeracc() {
        return this.playeracc;
    }
    
    public void setPlayeracc(String playeracc) {
        this.playeracc = ((playeracc == null) ? null : playeracc.trim());
    }
    
    public BigDecimal getRecharge() {
        return this.recharge;
    }
    
    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }
    
    public BigDecimal getPlayerpay() {
        return this.playerpay;
    }
    
    public void setPlayerpay(BigDecimal playerpay) {
        this.playerpay = playerpay;
    }
    
    public BigDecimal getYuanbao() {
        return this.yuanbao;
    }
    
    public void setYuanbao(BigDecimal yuanbao) {
        this.yuanbao = yuanbao;
    }
    
    public String getPaytime() {
        return this.paytime;
    }
    
    public void setPaytime(String paytime) {
        this.paytime = ((paytime == null) ? null : paytime.trim());
    }
    
    public BigDecimal getSid() {
        return this.sid;
    }
    
    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }
    
    public BigDecimal getReturntype() {
        return this.returntype;
    }
    
    public void setReturntype(BigDecimal returntype) {
        this.returntype = returntype;
    }
    
    public BigDecimal getAppid() {
        return this.appid;
    }
    
    public void setAppid(BigDecimal appid) {
        this.appid = appid;
    }
    
    public BigDecimal getManagerid() {
        return this.managerid;
    }
    
    public void setManagerid(BigDecimal managerid) {
        this.managerid = managerid;
    }
    
    public BigDecimal getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(BigDecimal goodsid) {
        this.goodsid = goodsid;
    }
    
    public BigDecimal getBuyrole() {
        return this.buyrole;
    }
    
    public void setBuyrole(BigDecimal buyrole) {
        this.buyrole = buyrole;
    }
    
    public BigDecimal getSellrole() {
        return this.sellrole;
    }
    
    public void setSellrole(BigDecimal sellrole) {
        this.sellrole = sellrole;
    }
    
    public BigDecimal getBuyuserid() {
        return this.buyuserid;
    }
    
    public void setBuyuserid(BigDecimal buyuserid) {
        this.buyuserid = buyuserid;
    }
    
    public String getBuyrolebalance() {
        return this.buyrolebalance;
    }
    
    public void setBuyrolebalance(String buyrolebalance) {
        this.buyrolebalance = buyrolebalance;
    }
    
    public String getGongshisign() {
        return this.gongshisign;
    }
    
    public void setGongshisign(String gongshisign) {
        this.gongshisign = gongshisign;
    }
    
    public String getQuid() {
        return this.quid;
    }
    
    public void setQuid(String quid) {
        this.quid = quid;
    }
    
    public String getBuyroleName() {
        return this.buyroleName;
    }
    
    public void setBuyroleName(String buyroleName) {
        this.buyroleName = buyroleName;
    }
    
    public String getSellroleName() {
        return this.sellroleName;
    }
    
    public void setSellroleName(String sellroleName) {
        this.sellroleName = sellroleName;
    }
    
    public String getBuyuseridName() {
        return this.buyuseridName;
    }
    
    public void setBuyuseridName(String buyuseridName) {
        this.buyuseridName = buyuseridName;
    }
    
    public Double getPayofprofits() {
        return this.payofprofits;
    }
    
    public void setPayofprofits(Double payofprofits) {
        this.payofprofits = payofprofits;
    }
    
    public Double getGspayofprofits() {
        return this.gspayofprofits;
    }
    
    public void setGspayofprofits(Double gspayofprofits) {
        this.gspayofprofits = gspayofprofits;
    }
    
    public String getQuname() {
        return this.quname;
    }
    
    public void setQuname(String quname) {
        this.quname = quname;
    }
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
}
