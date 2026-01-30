package org.come.Jpanel.spot.buy;

import java.awt.Graphics;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import org.come.Jpanel.spot.RecordBoxJpanel;
import org.come.Jpanel.spot.RecordBox;

public class SpotBuyRecordJpanel extends SpotBuyJpanel implements RecordBox
{
    private final RecordBoxJpanel recordBoxJpanel;
    
    public SpotBuyRecordJpanel(SpotBuyBoxJpanel spotBuyBoxJpanel) {
        super(spotBuyBoxJpanel, "inkImg/background/S340.png", "record");
        (this.recordBoxJpanel = new RecordBoxJpanel()).setBounds(50, 74, 299, 284, 6);
        this.add(this.recordBoxJpanel);
    }
    
    @Override
    public void updateCommoditys() {
        this.recordBoxJpanel.setRecordInfo(super.getStall());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
