package org.come.control;

import org.come.Jpanel.LotteryCPJpanel;
import org.come.Frame.LotteryCPJframe;
import org.come.action.FromServerAction;

public class QmjcControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        System.out.println("mes:" + mes);
        if (mes.contains("QPEN")) {
            if (mes.length() > 0 && mes.split("=").length > 1 && mes.split("=")[1].length() > 0) {
                if (mes.split("=")[1].split(",").length > 0) {
                    LotteryCPJframe.getLotteryCPJpanel();
                    LotteryCPJpanel.previousmsg = mes.split("=")[1].split(",")[0];
                }
                if (mes.split("=")[1].split(",").length > 1) {
                    LotteryCPJframe.getLotteryCPJpanel();
                    LotteryCPJpanel.previousmsg1 = mes.split("=")[1].split(",")[1];
                }
                if (mes.split("=")[1].split(",").length > 2) {
                    LotteryCPJframe.getLotteryCPJpanel();
                    LotteryCPJpanel.previousmsg2 = mes.split("=")[1].split(",")[2];
                }
            }
        }
        else if (mes.contains("RECORD")) {
            if (mes.length() > 0 && mes.split("=").length > 1 && mes.split("=")[1].length() > 0) {
                LotteryCPJframe.getLotteryCPJpanel();
                LotteryCPJpanel.previousmsg3 = mes.split("=")[1].split("\\|");
            }
            LotteryCPJframe.getLotteryCPJpanel().deleteScrollPane();
            LotteryCPJframe.getLotteryCPJpanel();
            LotteryCPJpanel.getlisysta();
            LotteryCPJframe.getLotteryCPJpanel().getjScrollPane();
        }
    }
}
