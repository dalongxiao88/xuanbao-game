package come.tool.JDialog;

import org.come.model.Lingbao;
import java.util.List;
import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.LingFaFuSkill;

public class LingSkillJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        LingFaFuSkill faFuSkill = (LingFaFuSkill)object;
        if (tishi) {
            int sum = faFuSkill.getS() * 8;
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
            Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
            lingbao.OpenGrid(false);
            lingbao.UpdateLing();
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
        }
    }
}
