package org.come.until;

import org.come.bean.StallPurchase;
import java.math.BigDecimal;
import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;
import org.come.socket.DownLoadTxt;
import org.come.bean.StallPurchaseBean;

public class StallPurchaseUntil
{
    private static StallPurchaseBean stallPurchaseBean;
    
    public static StallPurchaseBean getStallPurchaseBean() {
        if (StallPurchaseUntil.stallPurchaseBean == null) {
            DownLoadTxt.getDownLoadTxt().initMes("purchase.txt");
        }
        return StallPurchaseUntil.stallPurchaseBean;
    }
    
    public static void showStallPurchaseTree(DefaultMutableTreeNode root) {
        StallPurchaseBean stallPurchaseBean = getStallPurchaseBean();
        if (stallPurchaseBean != null) {
            for (String rootKey : stallPurchaseBean.getStallPurchaseKeys()) {
                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootKey);
                for (String key : stallPurchaseBean.getStallPurchaseKeys(rootKey)) {
                    rootNode.add(new DefaultMutableTreeNode(key));
                }
                root.add(rootNode);
            }
        }
    }
    
    public static StallPurchase getStallPurchaseByGoodsId(BigDecimal goodsId) {
        StallPurchaseBean stallPurchaseBean = getStallPurchaseBean();
        return stallPurchaseBean.getStallPurchaseByGoodsId(goodsId);
    }
    
    public static StallPurchase[] getStallPurchases(String name) {
        StallPurchaseBean stallPurchaseBean = getStallPurchaseBean();
        return stallPurchaseBean.getStallPurchases(name);
    }
    
    public static StallPurchase[] getStallPurchases(String rootKey, String key) {
        StallPurchaseBean stallPurchaseBean = getStallPurchaseBean();
        return stallPurchaseBean.getStallPurchases(rootKey, key);
    }
    
    public static void setStallPurchaseBean(StallPurchaseBean stallPurchaseBean) {
        StallPurchaseUntil.stallPurchaseBean = stallPurchaseBean;
    }
}
