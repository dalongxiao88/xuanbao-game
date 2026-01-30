package org.come.extInterface;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.ImportantgoodsrecordBean;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ImportantgoodtrcordGoods implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String goodsid) {
        if (goodsid != null && !"".equals(goodsid)) {
            List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods = AllServiceUtil.getImportantgoodtrcordService().selectImportantgoodsrecordGoods(new BigDecimal(goodsid));
            List<ImportantgoodsrecordBean> beans = new ArrayList<>();
            for (int i = 0; i < selectImportantgoodsrecordGoods.size(); ++i) {
                ImportantgoodssumrecordBean entity = (ImportantgoodssumrecordBean)selectImportantgoodsrecordGoods.get(i);
                boolean is = true;
                for (int j = 0; j < beans.size(); ++j) {
                    ImportantgoodsrecordBean bean = (ImportantgoodsrecordBean)beans.get(j);
                    bean.getRecorddate().add(entity.getSum());
                    bean.getTime().add(entity.getTime());
                    is = false;
                }
                if (is) {
                    beans.add(new ImportantgoodsrecordBean(entity.getGid(), entity.getName(), entity.getSum(), entity.getTime(), 0));
                }
            }
            String msg = Agreement.getAgreement().importantgoodtrcordGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(beans));
            SendMessage.sendMessageToSlef(ctx, msg);
        }
    }
}
