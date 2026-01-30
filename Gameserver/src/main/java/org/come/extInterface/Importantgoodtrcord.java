package org.come.extInterface;

import java.util.List;
import org.come.bean.ImportantgoodsrecordBean;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.ArrayList;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.extInterBean.ImportantgoodtrcordReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class Importantgoodtrcord implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ImportantgoodtrcordReqBean request = (ImportantgoodtrcordReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, ImportantgoodtrcordReqBean.class);
        String time = request.getTime();
        String weekendsum = request.getWeekendsum();
        int page = request.getPage();
        List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList = AllServiceUtil.getImportantgoodtrcordService().selectImportantgoodsrecordList(time, weekendsum, page);
        List<ImportantgoodsrecordBean> beans = new ArrayList<>();
        for (int i = 0; i < selectImportantgoodsrecordList.size(); ++i) {
            ImportantgoodssumrecordBean entity = (ImportantgoodssumrecordBean)selectImportantgoodsrecordList.get(i);
            boolean is = true;
            int j = 0;
            while (j < beans.size()) {
                ImportantgoodsrecordBean bean = (ImportantgoodsrecordBean)beans.get(j);
                if (entity.getGid().equals(bean.getGoodsid())) {
                    bean.getRecorddate().add(entity.getSum());
                    bean.getTime().add(entity.getTime());
                    is = false;
                    break;
                }
                else {
                    ++j;
                }
            }
            if (is) {
                beans.add(new ImportantgoodsrecordBean(entity.getGid(), entity.getName(), entity.getSum(), entity.getTime(), 0));
            }
        }
    }
}
