package org.come.control;

import org.cbg.frame.TrslationMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import org.cbg.bean.SearchGoodsBean;
import org.come.action.FromServerAction;

public class GoodsBuyNowControl implements FromServerAction
{
    static int panelOpen;
    static SearchGoodsBean searchGoodsBean;
    
    @Override
    public void controlMessFromServer(String mes, String type) {
        FormsManagement.HideForm(79);
        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(getSearchGoodsBean()));
        SendMessageUntil.toServer(sendmes);
        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(getPanelOpen());
    }
    
    public static int getPanelOpen() {
        System.out.println("getPanelOpen=" + GoodsBuyNowControl.panelOpen);
        return GoodsBuyNowControl.panelOpen;
    }
    
    public static void setPanelOpen(int panelOpen) {
        System.out.println("setPanelOpen=" + panelOpen);
        GoodsBuyNowControl.panelOpen = panelOpen;
    }
    
    public static SearchGoodsBean getSearchGoodsBean() {
        return GoodsBuyNowControl.searchGoodsBean;
    }
    
    public static void setSearchGoodsBean(SearchGoodsBean searchGoodsBean) {
        GoodsBuyNowControl.searchGoodsBean = searchGoodsBean;
    }
}
