package org.come.control;

import org.come.until.FormsManagement;
import org.come.action.NpcMenuAction;

public class DonationMoneyControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        FormsManagement.showForm(122);
    }
}
