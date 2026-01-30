package org.come.mouslisten;

import javax.swing.tree.DefaultTreeModel;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.JadeUpJpanel;
import org.come.Jpanel.SynthesisJpanel;
import org.come.Jpanel.CollectionJadeJpanel;
import org.come.Jpanel.ExchangeValueJpanel;
import java.awt.event.MouseListener;

public class ShowAllSuitMouslisten implements MouseListener
{
    private ExchangeValueJpanel valueJpanel;
    private CollectionJadeJpanel jadeJpanel;
    private SynthesisJpanel synthesisJpanel;
    private JadeUpJpanel jadeUpJpanel;
    
    public ShowAllSuitMouslisten(ExchangeValueJpanel valueJpanel, CollectionJadeJpanel jadeJpanel, SynthesisJpanel synthesisJpanel, JadeUpJpanel jadeUpJpanel) {
        this.valueJpanel = valueJpanel;
        this.jadeJpanel = jadeJpanel;
        this.synthesisJpanel = synthesisJpanel;
        this.jadeUpJpanel = jadeUpJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.valueJpanel != null) {
            ExchangeValueJpanel.showAll = !ExchangeValueJpanel.showAll;
            AccessSuitMsgUntil.showSuitMethod(this.valueJpanel.getTop(), ExchangeValueJpanel.showAll);
            DefaultTreeModel treeModel = (DefaultTreeModel)this.valueJpanel.getjTree().getModel();
            treeModel.reload();
        }
        if (this.jadeJpanel != null) {
            CollectionJadeJpanel.showAll = !CollectionJadeJpanel.showAll;
            AccessSuitMsgUntil.showSuitMethod(this.jadeJpanel.getTop(), CollectionJadeJpanel.showAll);
            DefaultTreeModel treeModel = (DefaultTreeModel)this.jadeJpanel.getjTree().getModel();
            treeModel.reload();
        }
        if (this.synthesisJpanel != null) {
            SynthesisJpanel.showAll = !SynthesisJpanel.showAll;
            AccessSuitMsgUntil.showSuitMethod(SynthesisJpanel.getTop(), SynthesisJpanel.showAll);
            DefaultTreeModel treeModel = (DefaultTreeModel)this.synthesisJpanel.getjTree().getModel();
            treeModel.reload();
        }
        if (this.jadeUpJpanel != null) {
            JadeUpJpanel.showAll = !JadeUpJpanel.showAll;
            AccessSuitMsgUntil.showSuitMethod(JadeUpJpanel.getTop(), JadeUpJpanel.showAll);
            DefaultTreeModel treeModel = (DefaultTreeModel)this.jadeUpJpanel.getjTree().getModel();
            treeModel.reload();
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
