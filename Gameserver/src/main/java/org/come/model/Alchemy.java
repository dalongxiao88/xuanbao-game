package org.come.model;

public class Alchemy
{
    private String alchemyid;
    private String alchemytype;
    private String alchemykey;
    private String alchemysv;
    private String alchemymv;
    
    public Alchemy() {
    }
    
    public Alchemy(String alchemyid, String alchemytype, String alchemykey, String alchemysv, String alchemymv) {
        this.alchemyid = alchemyid;
        this.alchemytype = alchemytype;
        this.alchemykey = alchemykey;
        this.alchemysv = alchemysv;
        this.alchemymv = alchemymv;
    }
    
    public String getAlchemyid() {
        return this.alchemyid;
    }
    
    public void setAlchemyid(String alchemyid) {
        this.alchemyid = alchemyid;
    }
    
    public String getAlchemytype() {
        return this.alchemytype;
    }
    
    public void setAlchemytype(String alchemytype) {
        this.alchemytype = alchemytype;
    }
    
    public String getAlchemykey() {
        return this.alchemykey;
    }
    
    public void setAlchemykey(String alchemykey) {
        this.alchemykey = alchemykey;
    }
    
    public String getAlchemysv() {
        return this.alchemysv;
    }
    
    public void setAlchemysv(String alchemysv) {
        this.alchemysv = alchemysv;
    }
    
    public String getAlchemymv() {
        return this.alchemymv;
    }
    
    public void setAlchemymv(String alchemymv) {
        this.alchemymv = alchemymv;
    }
}
