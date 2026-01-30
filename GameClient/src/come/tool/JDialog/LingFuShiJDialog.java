package come.tool.JDialog;
import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoFuShi;
import org.come.XuanBao.XuanBaoJframe;
import org.come.bean.XuanBao;
import org.come.model.Lingbao;
import java.util.List;
import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.LingFaFuSkill;
import org.come.until.UserData;

public class LingFuShiJDialog implements TiShiChuLi {
    public LingFuShiJDialog() {
    }
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (object instanceof LingFaFuSkill) {
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
            lingbao.OpenGrid(true);
            lingbao.UpdateLing();
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
        }
    } else if (object instanceof XuanBaoFuShi) {
    XuanBao lingbao = (RoleXuanBao.getRoleXuanBao()).zhuangbei;
    int sum = (lingbao.getNum() + 1) * 8;
    int[] goodids = new int[sum];
    for (int i = 0; i < goodids.length; i++) {
        goodids[i] = 811336;
    }
    List<Integer> integers = GoodsListFromServerUntil.chaxuns(goodids);
    if (integers == null) {
        FrameMessageChangeJpanel.addtext(6, "物品不足", null, null);
        ZhuFrame.getZhuJpanel().addPrompt2("物品不足");
        return;
    }
    GoodsListFromServerUntil.Delete(integers);
    lingbao.OpenGrid();
    UserData.upling(lingbao);
    (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel().update(lingbao);
    (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(lingbao);
        }
    }
}
