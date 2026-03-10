package org.come.Jpanel.spot;

import com.tool.btn.spot.CommoditySwitchBtn;

/**
 * 购买页签切换容器接口。
 */
public interface BuyBox
{
    CommoditySwitchBtn[] getCommoditySwitchBtns();
    
    void switchTo(int index);
}
