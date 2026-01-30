package come.tool.Scene.ZZS;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;

public class ZZSThread implements Runnable
{
    private ZZSScene zzsScene;
    
    public ZZSThread(ZZSScene zzsScene) {
        this.zzsScene = zzsScene;
    }
    
    @Override
    public void run() {
        for (int i = 2; i > 0; --i) {
            if (this.zzsScene.type == 0) {
                NChatBean bean = new NChatBean();
                bean.setId(9);
                bean.setMessage("#c00FFFF种族比武#Y即将开始了,想要参加的玩家到擂台进场，#R" + i * 5 + "分钟后#Y将关闭进场通道#23可获得海量经验以及物品奖励#89");
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
                bean.setId(5);
                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
            }
            try {
                Thread.sleep(300000L);
            }
            catch (Exception ex) {}
        }
        this.zzsScene.upI(2);
        for (int i = 0; i < 600; ++i) {
            this.zzsScene.match();
            try {
                Thread.sleep(3000L);
            }
            catch (Exception ex2) {}
        }
        this.zzsScene.upI(3);
        try {
            Thread.sleep(300000L);
        }
        catch (Exception ex3) {}
        this.zzsScene.upI(4);
        while (this.zzsScene.isEnd()) {
            try {
                this.zzsScene.isTT();
                Thread.sleep(3000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
