package org.come.control;

import org.come.Frame.DuelBoardJframe;
import org.come.action.FromServerAction;

public class DuelBoardControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        DuelBoardJframe.getDuelBoardJframe().getDuelBoardJpanel().getBoardData(mes);
    }
}
