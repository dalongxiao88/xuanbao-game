package come.tool.teamArena;

import org.come.handler.SendMessage;
import org.come.server.GolemServer;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;

public class LadderArenaThread implements Runnable {
	private long time;

	@Override
	public void run() {
		NChatBean bean = new NChatBean();
		bean.setId(9);
		bean.setMessage("#R天梯开始了,想要参加的玩家通过天梯参加！");
		String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
		SendMessage.sendMessageToAllRoles(msg);
		GolemServer.isStart = true;
		this.time = System.currentTimeMillis();
		while (true) {
			try {
				Thread.sleep(800L);
				long time2 = System.currentTimeMillis();
				if (time2 - this.time > LadderArenaUtil.TIME)
					break;
				LadderArenaUtil.confirmTimeOut(time2);
				LadderArenaUtil.match(time2);
				LadderArenaUtil.prepare(time2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LadderArenaUtil.teamArenaEnd();
	}
}
