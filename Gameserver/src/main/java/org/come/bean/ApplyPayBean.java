package org.come.bean;

import java.math.BigDecimal;

public class ApplyPayBean
{
    private BigDecimal addX;
    private BigDecimal addC;
    private BigDecimal addM;
    private BigDecimal addCZJF;
    private BigDecimal LowOrHihtpack;
    private BigDecimal Dayandpayorno;
    private UseCardBean useCardBean;
    private UseCardBean VIPBean;
    private String msg;
    
    public BigDecimal getAddX() {
        return this.addX;
    }
    
    public void setAddX(BigDecimal addX) {
        this.addX = addX;
    }
    
    public BigDecimal getAddC() {
        return this.addC;
    }
    
    public void setAddC(BigDecimal addC) {
        this.addC = addC;
    }
    
    public BigDecimal getAddM() {
        return this.addM;
    }
    
    public void setAddM(BigDecimal addM) {
        this.addM = addM;
    }
    
    public BigDecimal getLowOrHihtpack() {
        return this.LowOrHihtpack;
    }
    
    public void setLowOrHihtpack(BigDecimal lowOrHihtpack) {
        this.LowOrHihtpack = lowOrHihtpack;
    }
    
    public BigDecimal getDayandpayorno() {
        return this.Dayandpayorno;
    }
    
    public void setDayandpayorno(BigDecimal dayandpayorno) {
        this.Dayandpayorno = dayandpayorno;
    }
    
    public UseCardBean getUseCardBean() {
        return this.useCardBean;
    }
    
    public void setUseCardBean(UseCardBean useCardBean) {
        this.useCardBean = useCardBean;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public UseCardBean getVIPBean() {
        return this.VIPBean;
    }
    
    public void setVIPBean(UseCardBean vIPBean) {
        this.VIPBean = vIPBean;
    }
    
    public BigDecimal getAddCZJF() {
        return this.addCZJF;
    }
    
    public void setAddCZJF(BigDecimal addCZJF) {
        this.addCZJF = addCZJF;
    }
}
