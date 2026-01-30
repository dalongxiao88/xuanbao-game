package come.tool.Scene.SLDH;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GolemServer;

public class SLDHThread implements Runnable
{
    private SLDHScene sldhScene;
    
    public SLDHThread(SLDHScene sldhScene) {
        this.sldhScene = sldhScene;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#W第#R");
            buffer.append(this.sldhScene.getJS());
            buffer.append("#W届第#R");
            buffer.append(this.sldhScene.getCI());
            buffer.append("#W轮水路大会即将开始,想参与的队伍请在#R");
            buffer.append(25 - i * 5);
            buffer.append("#W分钟内进场");
            SendMessage.sendMessageToAllRoles(Agreement.getAgreement().PromptAgreement(buffer.toString()));
            try {
                Thread.sleep(SLDHScene.JG);
//                Thread.sleep(SLDHScene.JG/10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            //机器人水陆进场
            if (i == 4) {
                GolemServer.isSLDH=true;
            }
        }
        this.sldhScene.setI(1);
        this.sldhScene.grouping();
    }
}
