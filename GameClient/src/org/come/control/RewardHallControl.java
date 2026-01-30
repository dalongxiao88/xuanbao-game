package org.come.control;

import org.come.Frame.ZhuFrame;
import org.come.Frame.MsgJframe;
import com.tool.image.ImageMixDeal;
import org.come.bean.RewardDrawingBean;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.Frame.RewardHallJframe;
import org.come.entity.RewardHall;
import org.come.entity.Goodstable;
import java.util.ArrayList;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.bean.RewardListBean;
import org.come.action.FromServerAction;

public class RewardHallControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int n = -1;
        switch (type.hashCode()) {
            case -1418618703: {
                if (type.equals("obtainarticle")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 674506326: {
                if (type.equals("drawnitems")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 178053748: {
                if (type.equals("drawnitemsfail")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                this.obtainArticle(mes);
                break;
            }
            case 1: {
                this.drawnItems(mes);
                break;
            }
            case 2: {
                this.drawnitemsFail(mes);
                break;
            }
        }
    }
    
    public void obtainArticle(String mes) {
        RewardListBean listReward = (RewardListBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, RewardListBean.class);
        UserMessUntil.setRewardHallArr(listReward.getRewardHalls());
        List<Goodstable> listGoods = new ArrayList<>();
        for (int i = 0; i < UserMessUntil.getRewardHallArr().size(); ++i) {
            Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(((RewardHall)UserMessUntil.getRewardHallArr().get(i)).getGoodstable(), Goodstable.class);
            listGoods.add(i, goodstable);
        }
        RewardHallJframe.getRewardHallJframe().getRewardHallJpanel().showshop(listGoods);
        FormsManagement.showForm(59);
    }
    
    public void drawnItems(String mes) {
        RewardDrawingBean drawingBean = (RewardDrawingBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, RewardDrawingBean.class);
        List<Goodstable> listGoods = new ArrayList<>();
        Goodstable good = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(drawingBean.getRewardHall().getGoodstable(), Goodstable.class);
        if (drawingBean.getRoleName() == null || drawingBean.getRoleName().equals("")) {
            UserMessUntil.getRewardHallArr().add(drawingBean.getRewardHall());
            for (int i = 0; i < UserMessUntil.getRewardHallArr().size(); ++i) {
                Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(((RewardHall)UserMessUntil.getRewardHallArr().get(i)).getGoodstable(), Goodstable.class);
                listGoods.add(i, goodstable);
            }
            RewardHallJframe.getRewardHallJframe().getRewardHallJpanel().showshop(listGoods);
        }
        else {
            if (ImageMixDeal.userimg.getRoleShow().getRolename().equals(drawingBean.getRoleName())) {
                MsgJframe.getJframe().getJapnel().texiao("lottery");
                ZhuFrame.getZhuJpanel().addPrompt("恭喜您抽中了1个" + good.getGoodsname());
            }
            int exi = -1;
            for (int j = 0; j < UserMessUntil.getRewardHallArr().size(); ++j) {
                if (((RewardHall)UserMessUntil.getRewardHallArr().get(j)).getId().compareTo(drawingBean.getRewardHall().getId()) == 0) {
                    exi = j;
                }
            }
            if (exi != -1) {
                UserMessUntil.getRewardHallArr().remove(exi);
            }
            for (int j = 0; j < UserMessUntil.getRewardHallArr().size(); ++j) {
                Goodstable goodstable2 = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(((RewardHall)UserMessUntil.getRewardHallArr().get(j)).getGoodstable(), Goodstable.class);
                listGoods.add(j, goodstable2);
            }
            RewardHallJframe.getRewardHallJframe().getRewardHallJpanel().showshop(listGoods);
        }
    }
    
    public void drawnitemsFail(String mes) {
        if (mes.equals(Integer.valueOf(0))) {
            FormsManagement.HideForm(59);
            ZhuFrame.getZhuJpanel().addPrompt2("您的手速不够快啊,再接再励!!");
        }
        else {
            int day = Integer.parseInt(mes);
            ZhuFrame.getZhuJpanel().addPrompt2("您还有" + (7 - day) + "天才能再次抽奖!!");
        }
    }
}
