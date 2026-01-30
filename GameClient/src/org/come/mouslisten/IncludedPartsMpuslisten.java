package org.come.mouslisten;

import java.util.List;
import org.come.entity.PartJade;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import org.come.bean.IncludedPart;
import org.come.until.AccessSuitMsgUntil;
import org.come.until.CutButtonImage;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import org.come.Jpanel.AlreadyRecordedJpanel;
import java.awt.event.MouseListener;

public class IncludedPartsMpuslisten implements MouseListener
{
    private int index;
    private AlreadyRecordedJpanel recordedJpanel;
    
    public IncludedPartsMpuslisten(int index, AlreadyRecordedJpanel recordedJpanel) {
        this.index = index;
        this.recordedJpanel = recordedJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.recordedJpanel.getSuitid() > 0) {
            this.recordedJpanel.getTextField().setText("");
            this.recordedJpanel.setMoney((BigDecimal)null);
            this.recordedJpanel.setSxlxz((BigDecimal)null);
            for (int i = 0, size = this.recordedJpanel.getLabSuitParts().length; i < size; ++i) {
                this.recordedJpanel.getLabSuitParts()[i].setBorder(BorderFactory.createEmptyBorder());
            }
            this.recordedJpanel.getLabSuitParts()[this.index].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            this.recordedJpanel.getLabAct().setBtn(-1);
            this.recordedJpanel.getLabAct().setForeground(Color.GRAY);
            this.recordedJpanel.getLabAct().setIcon(CutButtonImage.getImage("inkImg/button/36.png", -1, -1));
            List<IncludedPart> parts = AccessSuitMsgUntil.getIncludedMsg(this.recordedJpanel.getSuitid());
            if (parts != null && parts.size() > 0 && this.index < parts.size()) {
                if (((IncludedPart)parts.get(this.index)).getNumber() == 1) {
                    this.recordedJpanel.getTextField().setText("1");
                    List<String> partlist = AccessSuitMsgUntil.returnPartsList(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(this.recordedJpanel.getSuitid()))).getHaveParts());
                    int num = partlist.size();
                    this.recordedJpanel.setMoney(new BigDecimal(10000000));
                    this.recordedJpanel.setSxlxz(new BigDecimal(10));
                    PartJade jade = new PartJade(this.recordedJpanel.getSuitid(), Integer.parseInt(((IncludedPart)parts.get(this.index)).getPartid()));
                    jade.setJade(1, 1);
                    this.recordedJpanel.getGoodstableBean().setPartJade(jade);
                }
                else if (AccessSuitMsgUntil.isActivate(parts, this.index)) {
                    this.recordedJpanel.getLabAct().setBtn(1);
                    this.recordedJpanel.getLabAct().setForeground(Color.WHITE);
                    try {
                        this.recordedJpanel.getLabAct().setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
                    }
                    catch (Exception var6) {
                        var6.printStackTrace();
                    }
                    PartJade jade2 = new PartJade(this.recordedJpanel.getSuitid(), Integer.parseInt(((IncludedPart)parts.get(this.index)).getPartid()));
                    this.recordedJpanel.getGoodstableBean().setPartJade(jade2);
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
