package org.come.bean;

import java.math.BigDecimal;

public class Quipportname
{
    private BigDecimal qid;
    private String ip;
    private String port;
    private String qname;
    private String tport;
    private String project;
    
    public BigDecimal getQid() {
        return this.qid;
    }
    
    public void setQid(BigDecimal qid) {
        this.qid = qid;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ((ip == null) ? null : ip.trim());
    }
    
    public String getPort() {
        return this.port;
    }
    
    public void setPort(String port) {
        this.port = ((port == null) ? null : port.trim());
    }
    
    public String getQname() {
        return this.qname;
    }
    
    public void setQname(String qname) {
        this.qname = ((qname == null) ? null : qname.trim());
    }
    
    public String getTport() {
        return this.tport;
    }
    
    public void setTport(String tport) {
        this.tport = ((tport == null) ? null : tport.trim());
    }
    
    public String getProject() {
        return this.project;
    }
    
    public void setProject(String project) {
        this.project = ((project == null) ? null : project.trim());
    }
}
