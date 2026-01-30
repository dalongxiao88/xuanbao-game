package come.tool.Scene.CJ;

import come.tool.Battle.BattleData;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleThreadPool;
import come.tool.FightingData.Battlefield;
import come.tool.Good.DropUtil;
import come.tool.Scene.Scene;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.sys.ChangeMapAction;
import org.come.bean.ChangeMapBean;
import org.come.bean.LoginResult;
import org.come.bean.NChatBean;
import org.come.handler.MainServerHandler;
import org.come.handler.SendMessage;
import org.come.model.Dorp;
import org.come.protocol.Agreement;
import org.come.protocol.AgreementUtil;
import org.come.protocol.ParamTool;
import org.come.server.GameServer;
import org.come.task.MapMonsterBean;
import org.come.task.MonsterMoveBase;
import org.come.tool.WriteOut;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class CJScene implements Scene{
	/**5分钟准备*/
	public static long JG=1000*60*2;

	public ChangeMapBean INTO;//吃鸡进场点
    private static int I;//0进场阶段   1匹配阶段   2活动结束
	private ConcurrentHashMap<BigDecimal, CJRole> roleMap;//玩家数据
	private static List<CJTeam> teams;//剩余队伍
	private List<CJGroup> groups;//分组情况
	private int teamTotal;//参与队伍总数
	private int groupNum;//分组的数量
	public CJScene() {
		// TODO Auto-generated constructor stub
		I = 2;
		this.roleMap= new ConcurrentHashMap<>();
		teams = new ArrayList<>();
		Random random = new Random();
		this.groups = new ArrayList<>();
		this.INTO=new ChangeMapBean("3371", random.nextInt(960), random.nextInt(1040));//吃鸡地图
	}
	/**玩家进场*/
	public synchronized String addEnroll(ChannelHandlerContext ctxRole,LoginResult loginResult){
		if (I!=0) {return "进场通道已关闭";}
		String[] teams=loginResult.getTeam().split("\\|");
		if (!teams[0].equals(loginResult.getRolename())) {return "你不是队长";}
		if (teams.length<1) {return "人数不足5人";}//这里调节人数
		CJRole cRole=getRole(loginResult.getRole_id());
		CJRole[] roles=null;
		if (cRole==null) {
			roles=new CJRole[teams.length];
			roles[0]=new CJRole(loginResult.getRole_id(),loginResult.getRoleShow());
		}
		for (int i = 1; i < teams.length; i++) {
			ChannelHandlerContext ctx=GameServer.getRoleNameMap().get(teams[i]);
			LoginResult login=ctx!=null?GameServer.getAllLoginRole().get(ctx):null;
			if (login==null) {return teams[i]+"处于异常状态";}
			if (login.getGrade()<439) {return teams[i]+"未满3转160级";}//源码类型等级
			if (roles!=null) {
				roles[i]=new CJRole(login.getRole_id(),login.getRoleShow());
			}
		}
		ChangeMapAction.ChangeMap(this.INTO, ctxRole);
		if (roles!=null) {//生成队伍
			CJTeam team=new CJTeam(roles);
			CJScene.teams.add(team);
			this.teamTotal++;
            for (CJRole role : roles) {
                addRole(role);
            }
		}
		return null;
	}
	/**活动开启*/
	public void open(){
		if (I!=2) {
			System.out.println(I+"大吉大利，晚上吃鸡！活动开始");
			WriteOut.addtxt("开启吃鸡活动时:吃鸡赛状态处于非关闭状态",9999);

			return;
		}
		this.roleMap.clear();
		teams.clear();
		this.groups.clear();
		this.teamTotal=0;
		this.groupNum=0;
		I=0;
		CJThread thread = new CJThread(this);
		Thread T1 = new Thread(thread);
		T1.start();
	}
	/**活动结束*/
	public void end(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("本次吃鸡活动结束！");
		if (this.teamTotal==1) {
			if (teams.get(0)!=null) {
				ChannelHandlerContext ctx=GameServer.getRoleNameMap().get(teams.get(0).getTeams()[0]);
				ChangeMapBean change;
				change = new ChangeMapBean("1207", 4380, 2960);//活动结束放回的地图和位置
				ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(ctx, GsonUtil.getGsonUtil().getgson().toJson(change));
				CJRole[] roles=teams.get(0).getRoles();
				for (CJRole role : roles) {
					ChannelHandlerContext ctx1 = GameServer.getRoleNameMap().get(role.getRoleShow().getRolename());
					LoginResult roleInfo = ctx1 != null ? GameServer.getAllLoginRole().get(ctx1) : null;
					Dorp dorp = GameServer.getDorp("8006");//dorp 表格  类型8006是冠军礼包
					if (dorp != null) {
						DropUtil.getDrop(roleInfo, dorp.getDorpValue(), "吃鸡冠军礼包", 22, 1D, null);
					}
				}
				buffer.append("#R吃鸡队伍:#G");
				buffer.append(teams.get(0).getTeamMsg());
			}
		}
		NChatBean bean=new NChatBean();
		bean.setId(7);
		bean.setMessage(buffer.toString());
		String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
		SendMessage.sendMessageToAllRoles(msg);
	}
	public void teamsout() {
		if (I == 1) {
			for (int i = 0; i < teams.size(); i++) {
				CJTeam team = teams.get(i);
				if (team.getNum() >= 3) {
					teams.remove(team);
					this.teamTotal--;
					i--;
				}
			}
			if (this.teamTotal == 1) {
				I = 2;
			}
		}
	}
	public void startPlayer() {
		// 启动战斗线程开始战斗
		CJBattleThread battleThread = new CJBattleThread(this);
		Thread T2 = new Thread(battleThread);
		T2.start();
	}
	/**进行分组*/
	public void grouping() {
		if (teams != null) {
			this.groupNum = 0; // 分组的数量初始化
			if (I == 1) { // 随机匹配直至最后队伍数量只剩1队
				this.groups.clear();
				List<CJTeam> teamslist = new ArrayList<>();
				for (CJTeam team : teams) {
					if (team.getRoles()[0].getRoleShow().getFighting() == 0) {
						teamslist.add(team);
					}
				}
				// 创建一个列表，用于随机选择队伍进行匹配
				List<Integer> list = new ArrayList<>();
				for (int i = 0; i < teamslist.size(); i++) {
					list.add(i);
				}
				// 通过随机选择的方式，将队伍两两匹配，直到只剩下一支队伍
				while (list.size() > 1) {
					boolean is3 = false;
					if (list.size() == 3) {
						is3 = true ;
					}
					int size = list.size();
					int t1 = list.remove(Battlefield.random.nextInt(size));
					CJTeam team1 = teamslist.get(t1);
					team1.setPK(true);
					size--;

					if (list.size()==2&&is3) {
						int t2 = list.remove(0);
						CJTeam team2 = teamslist.get(t2);
						team2.setPK(true);
						CJGroup group = new CJGroup(team1, team2);
						this.groups.add(group);
						break;
					} else {
						int t2 = list.remove(Battlefield.random.nextInt(size));
						CJTeam team2 = teamslist.get(t2);
						team2.setPK(true);
						CJGroup group = new CJGroup(team1, team2);
						this.groups.add(group);
					}
				}
				// 更新分组数量
				this.groupNum = this.groups.size();
				if (this.groupNum != 0) {
					PKOpen();
				}
			} else {
				try {
					WriteOut.addtxt("吃鸡报错:" + this.I + ":" + MainServerHandler.getErrorMessage(new Exception()), 9999);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}


	//但是我感觉这个吃鸡应该是残疾的
	/**吃鸡结果处理    1:一队获胜 2:二队获胜*/
	public synchronized void groupEnd(CJGroup group,int i) {
		group.setState(i + 1);
		CJTeam team = (i == 1 ? group.getTeam2() : group.getTeam1());
		int sljf = 1;//胜利积分
		int sbjf = 5;//失败积分
		getReward(i==1?group.getTeam2():group.getTeam1(),sljf);
		getReward(i==1?group.getTeam1():group.getTeam2(),sbjf);
		team.setNum(team.getNum() + 1);
		if (team.getNum() >= 3) {
			ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(team.getTeams()[0]);
			ChangeMapBean change;
			change = new ChangeMapBean("1207", 4380, 2960);
			ParamTool.ACTION_MAP.get(AgreementUtil.changemap).action(ctx, GsonUtil.getGsonUtil().getgson().toJson(change));
			CJRole[] roles = team.getRoles();
			for (CJRole role : roles) {
				ChannelHandlerContext ctx1 = GameServer.getRoleNameMap().get(role.getRoleShow().getRolename());
				LoginResult roleInfo = ctx1 != null ? GameServer.getAllLoginRole().get(ctx1) : null;
				Dorp dorp = GameServer.getDorp("5001");//类型5001  是参与奖励
				if (dorp != null) {
					DropUtil.getDrop(roleInfo, dorp.getDorpValue(), "吃鸡参与礼包", 22, 1D, null);
				}
			}
		}
	}

	public void getReward(CJTeam team,int jf) {
		AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
		assetUpdate.updata("吃鸡积分=" + jf);
		assetUpdate.setMsg("你获得#R" + jf + "#G点吃鸡积分");
		String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
		CJRole[] roles = team.getRoles();
		for (CJRole role : roles) {
			ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(role.getRoleShow().getRolename());
			LoginResult roleInfo = ctx != null ? GameServer.getAllLoginRole().get(ctx) : null;
			if (roleInfo != null) {
				roleInfo.setScore(DrawnitemsAction.Splice(roleInfo.getScore(), "吃鸡积分=" + jf, 2));
				SendMessage.sendMessageToSlef(ctx, msg);
			}
		}
	}

	/**对应分组开始战斗*/
    public void PKOpen(){
        for (CJGroup group : this.groups) {
            isPK(group);
        }
	}
	/**获取玩家数据*/
	public CJRole getRole(BigDecimal roleID){
		return this.roleMap.get(roleID);
	}
	/**添加玩家数据*/
	public CJRole addRole(CJRole role){
		return this.roleMap.put(role.getRoleID(), role);
	}
	/**判断是否能进入战斗*/
	public void isPK(CJGroup group){
		String name1=null,name2=null;
		CJTeam team1=group.getTeam1();
		for (int i = 0; i < team1.getRoles().length; i++) {
			name1=isAbnormal(team1.getRoles()[i].getRoleShow().getRolename());
			if (name1!=null) {break;}
		}
		CJTeam team2=group.getTeam2();
		if (name1==null) {
			for (int i = 0; i < team2.getRoles().length; i++) {
				name2=isAbnormal(team1.getRoles()[i].getRoleShow().getRolename());
				if (name2!=null) {break;}
			}
		}
		if (name1==null&&name2==null) {//进入战斗
			BattleThreadPool.cjPKContest(group);
			team1.setPK(false);
			team2.setPK(false);
		}
	}
	/**检测是否处于异常状态*/
	public String isAbnormal(String name){
		ChannelHandlerContext ctx=GameServer.getRoleNameMap().get(name);
		LoginResult roleInfo=ctx!=null?GameServer.getAllLoginRole().get(ctx):null;
		if (roleInfo==null) {return "玩家#R"+name+"#W处于离线状态,导致本次匹配失败";}
		if (roleInfo.getMapid()!=3371) {return "玩家#R"+name+"#W不在当前地图内,导致本次匹配失败";}
		if (roleInfo.getFighting()!=0) {return "玩家#R"+name+"#W处于战斗状态中,导致本次匹配失败";}
		return null;	
	}

	@Override
	public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return I!=2;
	}
	@Override
	public boolean isTime(long time) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int battleEnd(BattleEnd battleEnd, LoginResult loginResult,
			MapMonsterBean bean, int v) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
		// TODO Auto-generated method stub
		return null;
	}
	public static int getI() {
		return I;
	}
	public static void setI(int i) {
		I = i;
	}
	@Override
	public String UPMonster(BattleData battleData, String[] teams, int type,
			StringBuffer buffer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer,
			Map<Integer, MonsterMoveBase> moveMap, long mapId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CJGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<CJGroup> groups) {
		this.groups = groups;
	}

	public static List<CJTeam> getTeams() {
		return teams;
	}
	public static void setTeams(List<CJTeam> teams) {
		CJScene.teams = teams;
	}
}
