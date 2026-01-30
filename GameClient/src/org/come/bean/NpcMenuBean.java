package org.come.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class NpcMenuBean
{
    private String type;
    private String talkMess;
    private NpcSureMenuBean sureBean;
    private Map<String, String> inittitle;
    
    public NpcMenuBean() {
        this.inittitle = new LinkedHashMap<>();
    }
    
    public String getTalkMess() {
        return this.talkMess;
    }
    
    public void setTalkMess(String talkMess) {
        this.talkMess = talkMess;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public NpcSureMenuBean getSureBean() {
        return this.sureBean;
    }
    
    public void setSureBean(NpcSureMenuBean sureBean) {
        this.sureBean = sureBean;
    }
    
    public Map<String, String> getInittitle() {
        return this.inittitle;
    }
    
    public void setInittitle(Map<String, String> inittitle) {
        this.inittitle = inittitle;
    }
}
