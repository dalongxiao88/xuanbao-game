package come.tool.handle;

import org.come.bean.FightOperation;
import com.tool.imagemonitor.FightingMonitor;
import come.tool.Fighting.FightingMixDeal;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;

public class HandlePlayer implements Handle
{
    @Override
    public void handle(long DieTime) {
        ZhuFrame.getZhuJpanel();
        if (ZhuJpanel.getZidong().getText().equals("取消")) {
            FightingMixDeal.CorrectTime += DieTime;
            if (FightingMixDeal.CorrectTime > 3000L) {
                FightingMixDeal.CorrectTime = 0L;
                FightOperation operation = FightingMonitor.getOperation();
                FightingMonitor.execution(operation, true);
            }
        }
        if (FightingMixDeal.time > 60000L) {
            if (FightingMixDeal.State == 1) {
                FightOperation operation = FightingMonitor.getOperation();
                FightingMonitor.execution(operation, true);
            }
            if (FightingMixDeal.State == 2) {
                FightOperation operation = FightingMonitor.getOperation();
                FightingMonitor.execution(operation, true);
            }
        }
    }
}
