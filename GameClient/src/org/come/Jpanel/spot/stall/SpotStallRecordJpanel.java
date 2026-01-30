package org.come.Jpanel.spot.stall;

import java.awt.Graphics;
import org.come.until.UserStallUntil;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import org.come.Jpanel.spot.RecordBoxJpanel;
import org.come.Jpanel.spot.RecordBox;

public class SpotStallRecordJpanel extends SpotStallJpanel implements RecordBox
{
    private final RecordBoxJpanel recordBoxJpanel;
    
    public SpotStallRecordJpanel(SpotBoxJpanel spotBoxJpanel) {
        super(spotBoxJpanel, "inkImg/background/S337.png", "record");
        (this.recordBoxJpanel = new RecordBoxJpanel()).setBounds(50, 74, 284, 220, 8);
        this.add(this.recordBoxJpanel);
    }
    
    @Override
    public void updateCommoditys() {
        super.updateCommoditys();
        this.recordBoxJpanel.setRecordInfo(UserStallUntil.getStall());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
