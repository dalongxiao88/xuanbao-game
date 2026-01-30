package org.cbg.control;

import org.cbg.panel.TraslationMyMessageJpanel;
import org.cbg.frame.TrslationMainJframe;
import org.come.action.FromServerAction;

public class SearchDeleteMessageResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
        traslationMyMessageJpanel.getjScrollPane().getViewport().getView().repaint();
        traslationMyMessageJpanel.getjScrollPane().updateUI();
        traslationMyMessageJpanel.getjScrollPane().invalidate();
        traslationMyMessageJpanel.getjScrollPane().validate();
        traslationMyMessageJpanel.getjScrollPane().repaint();
    }
}
