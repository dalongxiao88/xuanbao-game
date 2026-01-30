package org.come.extInterBean;

public class RoleSellRoleInfo
{
    private String rolename;
    private String roleid;
    private String userid;
    private String species_id;
    private String grade;
    private String race_name;
    private String sell;
    private String saleid;
    
    public RoleSellRoleInfo() {
        this.sell = "1";
        this.saleid = "0";
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(String species_id) {
        this.species_id = species_id;
    }
    
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }
    
    public String getSell() {
        return this.sell;
    }
    
    public void setSell(String sell) {
        this.sell = sell;
    }
    
    public String getSaleid() {
        return this.saleid;
    }
    
    public void setSaleid(String saleid) {
        this.saleid = saleid;
    }
}
