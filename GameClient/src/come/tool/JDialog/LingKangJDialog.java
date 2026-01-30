package come.tool.JDialog;

import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleProperty;
import org.come.until.UserData;
import org.come.model.Lingbao;

public class LingKangJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            Lingbao lingbao = (Lingbao)object;
            if (UserData.uptael(200000L)) {
                lingbao.setKangxing(UserData.kangxing(lingbao.getLingbaolvl().intValue()));
                UserData.upling(lingbao);
                RoleProperty.getRoleProperty().equipWearOff();
                LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
            }
        }
    }
}
