package come.tool.JDialog;

import java.util.List;
import org.come.Frame.LingbaoJframe;
import org.come.until.UserData;
import com.tool.role.ExpUtil;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.model.Lingbao;

public class LingTupoJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Lingbao lingbao = (Lingbao)object;
        if (tishi) {
            int lvl = lingbao.getLingbaolvl().intValue();
            int sum = lvl / 5;
            int[] goodids = new int[sum];
            for (int i = 0; i < goodids.length; ++i) {
                goodids[i] = 10079;
            }
            List<Integer> integers = GoodsListFromServerUntil.chaxuns(goodids);
            if (integers == null) {
                FrameMessageChangeJpanel.addtext(6, "物品不足", (BigDecimal)null, (String)null);
                return;
            }
            GoodsListFromServerUntil.Delete(integers);
            long exp = lingbao.getLingbaoexe().longValue();
            long maxexp = ExpUtil.LFExp(lvl);
            ++lvl;
            exp -= maxexp;
            lingbao.setLingbaolvl(new BigDecimal(lvl));
            lingbao.setLingbaoexe(new BigDecimal(exp));
            UserData.upling(lingbao);
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().sj(lingbao);
        }
    }
}
