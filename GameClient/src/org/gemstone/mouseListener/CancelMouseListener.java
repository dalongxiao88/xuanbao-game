package org.gemstone.mouseListener;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.gemstone.panel.GemstoneOperationMainPanel;
import org.gemstone.panel.GemstoneOperationMainFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CancelMouseListener implements MouseListener
{
    private int path;
    
    public CancelMouseListener(int path) {
        this.path = path;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        GemstoneOperationMainPanel gemstoneOperationMainPanel = GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel();
        gemstoneOperationMainPanel.getGoodsLabes()[this.path] = null;
        switch (gemstoneOperationMainPanel.getTypePanel()) {
            case 1: {
                gemstoneOperationMainPanel.changeIcon(null, this.path, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                break;
            }
            case 2: {
                gemstoneOperationMainPanel.changeIcon(null, this.path, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                break;
            }
            case 3: {
                GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().changeIcon(null, this.path, gemstoneOperationMainPanel.getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                break;
            }
        }
        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().repaintBtn();
    }
    
    @Override
    public void mousePressed(MouseEvent mouseevent) {
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseevent) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGoodsLabes()[this.path];
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
