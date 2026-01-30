package come.tool.JDialog;

import org.come.until.UserMessUntil;
import org.come.mouslisten.MountMouslisten;

public class ZQGZJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            MountMouslisten mouslisten = (MountMouslisten)object;
            mouslisten.controlPet(UserMessUntil.getChosePetMes());
        }
    }
}
