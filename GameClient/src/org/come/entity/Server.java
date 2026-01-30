package org.come.entity;

public class Server
{
    private String name;
    private String ip;
    private String port;
    private String webport;
    private String payweb;
    private String exe;
    private String ifNew;
    
    public String getWebport() {
        return this.webport;
    }
    
    public void setWebport(String webport) {
        this.webport = webport;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getPort() {
        return this.port;
    }
    
    public void setPayweb(String payweb) {
        this.payweb = payweb;
    }
    
    public String getPayweb() {
        return this.payweb;
    }
    
    public void setExe(String exe) {
        this.exe = exe;
    }
    
    public String getExe() {
        return this.exe;
    }
    
    public String getIfNew() {
        return this.ifNew;
    }
    
    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
}
