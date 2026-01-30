package come.tool.Role;

import org.come.entity.Mount;
import come.tool.Calculation.BaseValue;
import org.come.entity.Lingbao;
import come.tool.Calculation.GradeQl;
import java.math.BigDecimal;

public class Hang
{
    private BigDecimal id;
    private BigDecimal mid;
    private GradeQl pl;
    
    public Hang(Lingbao lingbao, int is) {
        this.id = lingbao.getBaoid();
        if (is == 1) {
            this.pl = new GradeQl(lingbao.getBaoname(), BaseValue.getQv(lingbao.getBaoquality()), (double)lingbao.getLingbaolvl().intValue());
        }
    }
    
    public Hang(BigDecimal id) {
        this.id = id;
    }
    
    public void initSid(Mount mount) {
        if (mount.getSid() != null && mount.getSid().compareTo(this.id) == 0) {
            this.mid = mount.getMid();
        }
        else if (mount.getOthrersid() != null && mount.getOthrersid().compareTo(this.id) == 0) {
            this.mid = mount.getMid();
        }
        else if (mount.getSid3() != null && mount.getSid3().compareTo(this.id) == 0) {
            this.mid = mount.getMid();
        }
        else if (mount.getSid4() != null && mount.getSid4().compareTo(this.id) == 0) {
            this.mid = mount.getMid();
        }
        else if (mount.getSid5() != null && mount.getSid5().compareTo(this.id) == 0) {
            this.mid = mount.getMid();
        }
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
    
    public GradeQl getPl() {
        return this.pl;
    }
    
    public void setPl(GradeQl pl) {
        this.pl = pl;
    }
}
