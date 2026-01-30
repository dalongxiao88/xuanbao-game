package come.tool.Calculation;

import org.come.action.suit.SuitComposeAction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PalEquipQl
{
    private double V;//获得概率
    private List<PalQl> list;
    private int size;
    
    public PalEquipQl(String v) {
        String[] vs = v.split("\\|");
        this.V = Double.parseDouble(vs[0]);
        this.list = new ArrayList<>();
        for (int i = 1; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            double value = (vss.length == 3) ? Double.parseDouble(vss[2]) : 0.0;
            double sv = Double.parseDouble(vss[1]);
            String[] vsss = vss[0].split("&");
            for (int j = 0; j < vsss.length; ++j) {
                this.list.add(new PalQl(vsss[j], value, sv));
            }
        }
        this.size = this.list.size();
    }
    
    public PalQl getPalQl(String key, double value, double xs, int lvl, int JC) {
        PalQl BX = null;
        for (int i = 0, length = this.list.size(); i < length; ++i) {
            PalQl palQl = (PalQl)this.list.get(i);
            if (palQl.getKey().equals(key)) {
                if (BX == null) {
                    BX = palQl;
                }
                BigDecimal sx = new BigDecimal((palQl.getValue() + (double)lvl * palQl.getSv() + (double)JC * palQl.getSv() / 5.0) * xs);
                sx = sx.setScale(1, 4);
                if (sx.doubleValue() == value) {
                    return palQl;
                }
            }
        }
        return BX;
    }
    
    public PalQl getPalQl() {
        return (PalQl)this.list.get(SuitComposeAction.random.nextInt(this.size));
    }
    
    public double getV() {
        return this.V;
    }
    
    public void setV(double v) {
        this.V = v;
    }
    
    public List<PalQl> getList() {
        return this.list;
    }
    
    public void setList(List<PalQl> list) {
        this.list = list;
    }
}
