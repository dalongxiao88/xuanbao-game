package come.tool.Scene.CJ;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;

public class CJThread implements Runnable{

	private CJScene cjScene;
	public CJThread(CJScene cjScene) {
		// TODO Auto-generated constructor stub
		this.cjScene=cjScene;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
		    StringBuffer buffer=new StringBuffer();
			buffer.append("#Y吃鸡活动即将开始,想参与的队伍请在#R");
			buffer.append((10-(i*2)));
			buffer.append("#Y分钟内进场");
			SendMessage.sendMessageToAllRoles(Agreement.getAgreement().PromptAgreement(buffer.toString()));		
			try {
//				Thread.sleep(CJScene.JG);
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		CJScene.setI(1);
		this.cjScene.startPlayer();
	}
}
