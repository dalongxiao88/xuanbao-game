package come.tool.teamArena;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;

public class TeamArenaThread implements Runnable {
	private long time;

	@Override
	public void run() {
		NChatBean bean = new NChatBean();
		bean.setId(5);
		String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
		SendMessage.sendMessageToAllRoles(msg);
		this.time = System.currentTimeMillis();
		while (true) {
			try {
				Thread.sleep(800L);
				long time2 = System.currentTimeMillis();
				if (time2 - this.time > TeamArenaUtil.TIME)
					break;
				TeamArenaUtil.confirmTimeOut(time2);
				TeamArenaUtil.match(time2);
				TeamArenaUtil.prepare(time2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TeamArenaUtil.teamArenaEnd();
	}
}
