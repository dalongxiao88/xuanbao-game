package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.DreamlandTrialMainJframe;
import org.come.action.FromServerAction;

public class HjslControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("S")) {
            String substring = mes.substring(1);
            DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel().showView(substring);
            FormsManagement.showForm(111);
        }
    }
}
