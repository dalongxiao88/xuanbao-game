package org.come.control;

import org.come.mouslisten.SystemMouslisten;
import org.come.until.DeviceEshopUntil;
import org.come.until.UserMessUntil;
import org.come.Frame.GameJframe;

public class TestMain
{
    public static GameJframe gameJframe;
    
    public TestMain() {
        try {
            DeviceEshopUntil.initEshopUntil(UserMessUntil.getEshops());
            TestMain.gameJframe = new GameJframe();
            SystemMouslisten.readSysteminit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
