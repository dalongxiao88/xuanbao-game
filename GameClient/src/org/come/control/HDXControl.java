package org.come.control;

import org.come.Jpanel.HuangJpanel;
import org.come.action.FromServerAction;

public class HDXControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.contains("TCH")) {
            String[] me = mes.split("=")[1].split("-");
            String tch = me[0];
            String tch2 = me[1];
            HuangJpanel.setTou(tch);
            HuangJpanel.setTou1(tch2);
        }
    }
}
