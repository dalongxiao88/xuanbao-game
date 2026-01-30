package come.tool.JDialog;

import com.tool.role.RoleLingFa;
import org.come.until.UserData;
import org.come.model.Lingbao;

public class LingDiscatdJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Lingbao lingbao = (Lingbao)object;
        if (tishi) {
            lingbao.setOperation("删除");
            UserData.upling(lingbao);
            RoleLingFa.getRoleLingFa().deleteling(lingbao);
        }
    }
}
