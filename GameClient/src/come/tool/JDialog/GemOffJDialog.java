package come.tool.JDialog;

import com.tool.btn.GemMakeBtn;
import org.come.entity.Goodstable;

public class GemOffJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Goodstable[] goods = (Goodstable[])(Goodstable[])object;
        if (tishi) {
            GemMakeBtn.GemXXX(false, goods);
        }
    }
}
