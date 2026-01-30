package come.tool.JDialog;

import java.util.Map;
import org.come.bean.ConfigureBean;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.entity.Pal;

public class PalGetPetJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi && object instanceof Pal) {
            Pal pal = (Pal)object;
            String sendmes = Agreement.getAgreement().userpalAgreement(pal.getId() + "|getPet");
            SendMessageUntil.toServer(sendmes);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
        }
    }
}
