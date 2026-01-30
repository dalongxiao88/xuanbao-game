package come.tool.Scene.CJ;

public class CJBattleThread implements Runnable{

	private CJScene cjscene;
	public CJBattleThread(CJScene cjscene) {
		// TODO Auto-generated constructor stub
		this.cjscene=cjscene;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (CJScene.getI()!=2) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 恢复中断状态
				Thread.currentThread().interrupt();
				break;
			}
			this.cjscene.teamsout();
			if (CJScene.getI()!=2) {
				this.cjscene.grouping();
			}else{
				this.cjscene.end();
			}
		}
	}
}
