package org.come.control;

import org.come.Frame.MyOptionalJframe;
import org.come.Jpanel.MyOptionalJpanel;
import org.come.action.FromServerAction;
import org.come.until.FormsManagement;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.AchievemMinJframe;//我加
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;//我加
import org.come.socket.SendMessageUntil;//我加
import org.come.until.UserMessUntil;//我加
import java.util.List;//我加
import java.util.Map.Entry;//我加
import java.util.Set;//我加


/**
 * 功绩千秋
 * @author Administrator
 *
 */
public class ZxGoodsControl implements FromServerAction {
    @Override
    public void controlMessFromServer(String mes,String type) {
		//打开获取
		if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
//			ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
			return;
		}
		MyOptionalJpanel.godList = new ArrayList<>();
		for(int i =0;i<mes.split("\\|").length;i++) {
			MyOptionalJpanel.godList.add(mes.split("\\|")[i]);
		}
//		MyOptionalJpanel.zhjgood = goodstable;
		FormsManagement.showForm(3015);
		MyOptionalJframe.getRankingListJframe();
		MyOptionalJframe.getMyOptionalJpanel().showViewData();
    }
}
