package org.come.action.wqx;

import java.math.BigDecimal;

public class KJRole
{
    private static final long TIME = 60000L;
    private Long startTime;
    private BigDecimal roleId;
    private String roleName;
    private int kjType;
    private int examProcess;
    private int correct;
    private int mistake;
    private int useDNJF;
    private String SLJC;
    private int NVNum;
    private long time;
    private boolean isA;
    
    public KJRole(BigDecimal roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.kjType = 1;
        this.isA = false;
        this.examProcess = 1;
        this.time = System.currentTimeMillis() + 60000L;
        this.correct = 0;
        this.mistake = 0;
    }
    
    public Long getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public int getCorrect() {
        return this.correct;
    }
    
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    public int getMistake() {
        return this.mistake;
    }
    
    public void setMistake(int mistake) {
        this.mistake = mistake;
    }
    
    public int getNVNum() {
        return this.NVNum;
    }
    
    public void setNVNum(int nVNum) {
        this.NVNum = nVNum;
    }
    
    public boolean isA() {
        return this.isA;
    }
    
    public void setA(boolean isA) {
        this.isA = isA;
    }
    
    public String isTime(long nTime) {
        nTime = this.time - nTime;
        nTime /= 1000L;
        if (nTime > 0L) {
            return this.roleName + "还需要休息" + nTime + "秒";
        }
        return null;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time + 60000L;
    }
    
    public int getUseDNJF() {
        return this.useDNJF;
    }
    
    public void setUseDNJF(int useDNJF) {
        this.useDNJF = useDNJF;
    }
    
    public int getKjType() {
        return this.kjType;
    }
    
    public void setKjType(int kjType) {
        this.kjType = kjType;
    }
    
    public int getExamProcess() {
        return this.examProcess;
    }
    
    public void setExamProcess(int examProcess) {
        this.examProcess = examProcess;
    }
}
