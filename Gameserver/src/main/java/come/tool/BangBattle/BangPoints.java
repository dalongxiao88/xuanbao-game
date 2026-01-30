package come.tool.BangBattle;

import java.math.BigDecimal;

public class BangPoints
{
    //帮派id
    private BigDecimal id;
    //战绩记录            00000000
    private int record;
    //排名
    private int rank;
    //计算排名
    public void CalculateRank(int sum) {
        int max = sum;
        int min = 1;
        for (int i = 0; min < max && i <= 999999; ++i) {
            sum = max - min + 1;
            if (this.record == (this.record | 1 << i)) {
                max -= sum / 2;
            }
            else {
                min += (sum + 1) / 2;
            }
        }
        this.rank = min;
    }
    
    public BangPoints(BigDecimal id) {
        this.rank = -1;
        this.id = id;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public int getRecord() {
        return this.record;
    }
    
    public void setRecord(int record) {
        this.record = record;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}
