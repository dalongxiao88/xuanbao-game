package come.tool.Scene.DNTG;

import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;

public class DNTGThread implements Runnable
{
    private DNTGScene dntgScene;
    
    public DNTGThread(DNTGScene dntgScene) {
        this.dntgScene = dntgScene;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 6; ++i) {
            NChatBean bean = new NChatBean();
            bean.setId(9);
            bean.setMessage("#R大闹天宫#Y即将开始了,想要参加的玩家到对应NPC进场,离正式开始还剩" + (6 - i) * 5 + "分钟");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            bean.setId(5);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            try {
                Thread.sleep(300000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.dntgScene.upI(1);
        long time = 0L;
        while (this.dntgScene.isEnd()) {
            try {
                if (time == 1500L) {
                    this.dntgScene.activity(0);
                }
                else if (time == 1800L) {
                    this.dntgScene.activity(1);
                }
                else if (time == 3600L) {
                    this.dntgScene.activity(2);
                }
                else if (time == 5100L) {
                    this.dntgScene.activity(3);
                }
                else if (time == 5400L) {
                    this.dntgScene.activity(4);
                }
                else if (time == 7200L) {
                    this.dntgScene.activity(5);
                }
                this.dntgScene.move(time);
                Thread.sleep(1000L);
                if (time % 90L == 0L) {
                    this.dntgScene.open((int)time / 90);
                }
                ++time;
                this.dntgScene.isDNTG(time);
            }
            catch (Exception e2) {
                e2.printStackTrace();
                WriteOut.addtxt("大闹天宫:" + MainServerHandler.getErrorMessage(e2), 9999L);
                this.dntgScene.isDNTG(10800L);
            }
        }
    }
}
