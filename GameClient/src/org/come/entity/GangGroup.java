package org.come.entity;

/**
 * 客户端帮派驯养/科技分组数据。
 */
public class GangGroup
{
    private int xy;
    private int kj;
    
    public int getXy() {
        return this.xy;
    }

    /** 语义化别名：驯养师等级。 */
    public int getTrainingLevel() {
        return this.xy;
    }
    
    public void setXy(int xy) {
        this.xy = xy;
    }

    public void setTrainingLevel(int trainingLevel) {
        this.xy = trainingLevel;
    }
    
    public int getKj() {
        return this.kj;
    }

    /** 语义化别名：科技等级。 */
    public int getTechLevel() {
        return this.kj;
    }
    
    public void setKj(int kj) {
        this.kj = kj;
    }

    public void setTechLevel(int techLevel) {
        this.kj = techLevel;
    }
}
