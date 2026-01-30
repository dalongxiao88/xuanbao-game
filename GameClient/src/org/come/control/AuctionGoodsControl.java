package org.come.control;

import org.come.Frame.AuctionGoodsExchangeJframe;
import org.come.until.GsonUtil;
import org.come.bean.AuctionSceneInfo;
import org.come.action.FromServerAction;

public class AuctionGoodsControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        AuctionSceneInfo auctionSceneInfo = (AuctionSceneInfo)GsonUtil.getGsonUtil().getgson().fromJson(mes, AuctionSceneInfo.class);
        AuctionGoodsExchangeJframe.getGoodDetailedJframe().getAuctionGoodsExchangeJpanel().refreshBidding(auctionSceneInfo);
    }
}
