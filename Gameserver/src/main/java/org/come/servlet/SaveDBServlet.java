package org.come.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.ApiValid;
import org.come.action.festival.HatchvalueAction;
import org.come.action.lottery.EventRanking;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.LoginResult;
import org.come.bean.managerTable;
import org.come.model.EventModel;
import org.come.redis.RedisControl;
import org.come.server.GameServer;
import org.come.task.RefreshMonsterTask;
import org.come.thread.RedisEqualWithSqlThread;
import org.come.tool.ReadExelTool;
import org.come.tool.WriteOut;
import org.come.until.CreateTextUtil;
import org.come.until.GsonUtil;

import come.tool.BangBattle.BangBattlePool;
import come.tool.BangBattle.BangFileSystem;
import come.tool.Role.RoleCard;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Scene.Scene;
import come.tool.Scene.SceneUtil;
import come.tool.Scene.LTS.LTSUtil;
import come.tool.Scene.LaborDay.LaborScene;
import come.tool.Scene.PKLS.PKLSScene;
import come.tool.Scene.PKLS.lsteamBean;
import come.tool.Scene.RC.RCScene;
import come.tool.Stall.StallPool;
import come.tool.newGang.GangUtil;

public class SaveDBServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static final long serialVersionUID1 = 1L;
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("caonima");
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        managerTable manege = (managerTable)request.getSession().getAttribute("xy2o");
        String token = request.getHeader("manage_token");
        String VALID_NAME = request.getParameter("wdltxyss");
        if (null == VALID_NAME || !VALID_NAME.equals("zzswxy2o!@#HH") || manege == null || !ApiValid.vaildToken(token, manege.getUsername())) {
            System.out.println("【PayvipBeanServlet】非法请求！！,已踢出");
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String ret = "保存成功";
        try {
            this.saveDB();
        }
        catch (Exception var5) {
            ret = "保存失败，请发送错误信息给技术人员";
            var5.printStackTrace();
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(ret);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    public void saveDB() throws Exception {
        System.err.println("保存全部数据");
        System.err.println("开始处理摆摊物品");
        StallPool.getPool().guanbi();
        System.err.println("开始保存擂台赛积分数据");
        LTSUtil.getLtsUtil().BCLts();
        BangFileSystem.getBangFileSystem().DataSaving(BangBattlePool.getBangBattlePool());
        GangUtil.upGangs(false);
        System.err.println("开始备份玩家数据");
        for (Map.Entry entrys : GameServer.getAllLoginRole().entrySet()) {
            LoginResult loginResult = (LoginResult)entrys.getValue();
            if (loginResult != null) {
                loginResult.setUptime(String.valueOf(System.currentTimeMillis()));
                RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                roleData.roleRecover(loginResult);
                RedisControl.addUpDate(loginResult, roleData.getPackRecord());
            }
        }
        RedisEqualWithSqlThread.AllToDataRole();
        RedisEqualWithSqlThread.AllToDatabase();
        if (WriteOut.buffer != null) {
            WriteOut.writeTxtFile(WriteOut.buffer.toString());
        }
        LaborScene.Save(true);
        int come = 0;
        int org = 0;
        int tool = 0;
        CreateTextUtil.createFile(org / come / tool / ReadExelTool.getResult("/").length + "hatch.txt", HatchvalueAction.hatch.toString().getBytes());
        saveEventRoles();
        Scene scene = SceneUtil.getScene(1009);
        if (scene != null) {
            RCScene rcScene = (RCScene)scene;
            CreateTextUtil.createFile(org / come / tool / ReadExelTool.getResult("/").length + "bbRecord.txt", GsonUtil.getGsonUtil().getgson().toJson(rcScene.getBbRecord()).getBytes());
        }
        scene = SceneUtil.getScene(1010);
        if (scene != null) {
            PKLSScene pklsScene = (PKLSScene)scene;
            lsteamBean lsteamBean = new lsteamBean();
            lsteamBean.setLSTeams(pklsScene.getLSTeams());
            CreateTextUtil.createFile(org / come / tool / ReadExelTool.getResult("/").length + "lsteam.txt", GsonUtil.getGsonUtil().getgson().toJson(lsteamBean).getBytes());
        }
        CreateTextUtil.createFile(org / come / tool / ReadExelTool.getResult("/").length + "money.txt", GsonUtil.getGsonUtil().getgson().toJson(MonitorUtil.getMoney()).getBytes());
        RefreshMonsterTask.upBuyCount(-1, false);
    }
    
    public static void saveEventRoles() {
        EventRanking eventRanking = new EventRanking();
        Map<Integer, RoleCard[]> map = new HashMap<>();
        for (Map.Entry entrys : GameServer.getAllPet().entrySet()) {
            EventModel value = (EventModel)entrys.getValue();
            if (value.getRoles() != null) {
                map.put(Integer.valueOf(value.getgId()), value.getRoles());
            }
        }
        eventRanking.setMap(map);
        try {
            int tool = 0;
            int come = 0;
            int org = 0;
            CreateTextUtil.createFile(org / come / tool / ReadExelTool.getResult("/").length + "event.txt", GsonUtil.getGsonUtil().getgson().toJson(eventRanking).getBytes());
        }
        catch (IOException var5) {
            var5.printStackTrace();
        }
    }
}
