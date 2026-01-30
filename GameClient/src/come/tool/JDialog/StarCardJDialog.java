package come.tool.JDialog;

import java.math.BigDecimal;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.entity.Goodstable;

public class StarCardJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            Goodstable goodstable = (Goodstable)object;
            SuitOperBean suitOperBean = new SuitOperBean();
            List<BigDecimal> goods = new ArrayList<>();
            goods.add(goodstable.getRgid());
            suitOperBean.setGoods(goods);
            suitOperBean.setType(58);
            String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
            SendMessageUntil.toServer(sendmes);
        }
    }
}
