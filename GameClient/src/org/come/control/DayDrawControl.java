package org.come.control;

import com.tool.image.ImageMixDeal;
import org.come.Frame.ActivityJframe;
import org.come.Frame.ForgeJframe;
import org.come.Frame.ImpactGradeJframe;
import org.come.Frame.RuneOperateJframe;
import org.come.Jpanel.ActivityJpanel;
import org.come.Jpanel.ActivityModelJpanel;
import org.come.Jpanel.ForgeJpanel;
import org.come.Jpanel.RuneOperateJpanel;
import org.come.action.FromServerAction;
import org.come.entity.Goodstable;
import org.come.lianhua.AutoMaticRefiningJframe;
import org.come.mouslisten.TransmuteMouslisten;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.GsonUtil;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DayDrawControl implements FromServerAction {

    @Override
    public void controlMessFromServer(String mes, String type) {
        // TODO Auto-generated method stub
        String[] msgs = mes.split("=");
        if (msgs[0].equals("获得奖励")) {
            Goodstable goods = UserMessUntil.getgoodstable(new BigDecimal(msgs[1]));
//			Goodstable goodstable = UserMessUntil.getGoodsBean().getAllGoodsMap().get(new BigDecimal(mes.split("=")[1]));
//			ActivityJpanel.goodImg.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 52, 52));
            ActivityJframe.getActivityJframe().getActivityJpanel().getGoodsIds().add(msgs[1]);
            Thread t = new Thread() {
                @Override
                public void run() {
                    super.run();
                    List<String> goodsIds = ActivityJframe.getActivityJframe().getActivityJpanel().getGoodsIds();
                    for (int i = 0; i < goodsIds.size(); i++) {
                        String s = goodsIds.get(i);
                        Goodstable goodstable = UserMessUntil.getGoodsBean().getAllGoodsMap().get(new BigDecimal(s));
                        ActivityJpanel.goodImg.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 52, 52));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (goodsIds.size() - 1 == i) {
                            ActivityJpanel.start = false;
                            ActivityModelJpanel.selectActivityModelJpanel.extracted();
                            break;
                        }
                    }
                }
            };
            t.start();
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().setGoodsid(msgs[1]);
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getDjlabel().setIcon(CutButtonImage.getImage("img/item/"+goods.getSkin()+".png",40,40));
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getMclabel()[0].setText(goods.getGoodsname());
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getDayDraw();

        }
        if (msgs[0].equals("获得奖池")) {
            String[] vss = msgs[1].split("\\|");
            List<String> goodsIds = new ArrayList<>();
            for (int i = 0; i < vss.length; i++) {
                goodsIds.add(vss[i]);
            }
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().setJcGoodsIdList(goodsIds);
//			for(int i=0;i<goodsIds.size();i++){
//				Goodstable goods = UserMessUntil.getgoodstable(new BigDecimal(goodsIds.get(i)));
//				if(goods!=null){
//					ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getJllabel()[i].setIcon(CutButtonImage.getImage("img/item/"+goods.getSkin()+".png",30,30));
////					ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getMclabel()[i].setText(goods.getGoodsname());
//				}
//			}
//			ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getDayDraw();
            ActivityJframe.getActivityJframe().getActivityJpanel().setGoodsIds(goodsIds);
        }
    }
}
