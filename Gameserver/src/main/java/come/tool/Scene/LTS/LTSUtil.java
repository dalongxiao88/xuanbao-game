package come.tool.Scene.LTS;

import come.tool.Role.RoleData;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Role.RolePool;
import java.util.Collections;
import java.util.Map;
import java.util.Comparator;
import java.util.ArrayList;
import org.come.bean.LoginResult;
import java.util.List;
import java.io.IOException;
import org.come.until.CreateTextUtil;
import come.tool.Title.TitleUtil;
import org.come.until.GsonUtil;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import org.come.bean.PathPoint;
import java.math.BigDecimal;

public class LTSUtil
{
    private static LTSUtil ltsUtil;
    private LTSBean ltsBean;
    
    public static LTSUtil getLtsUtil() {
        if (LTSUtil.ltsUtil == null) {
            (LTSUtil.ltsUtil = new LTSUtil()).init();
        }
        return LTSUtil.ltsUtil;
    }
    
    public PathPoint getJF(BigDecimal roleId) {
        return (PathPoint)this.ltsBean.getLtsMap().get(roleId);
    }
    
    public void addJF(BigDecimal roleId, int jf) {
        PathPoint point = (PathPoint)this.ltsBean.getLtsMap().get(roleId);
        if (point == null) {
            point = new PathPoint(0, 0);
            this.ltsBean.getLtsMap().put(roleId, point);
        }
        point.add(jf);
    }
    
    public void init() {
        String lts = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "lts.txt");
        if (lts == null || lts.equals("")) {
            this.ltsBean = new LTSBean();
        }
        else {
            this.ltsBean = (LTSBean)GsonUtil.getGsonUtil().getgson().fromJson(lts, LTSBean.class);
        }
        this.bangList(null);
        TitleUtil.addTitle(TitleUtil.LTS, this.ltsBean.getIds());
    }
    
    public void BCLts() {
        try {
            String msg = GsonUtil.getGsonUtil().getgson().toJson(this.ltsBean);
            CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "lts.txt", msg.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void resetLts(List<LoginResult> list) {
        this.ltsBean.setCi(this.ltsBean.getCi() + 1);
        if (this.ltsBean.getCi() >= 2) {
            this.ltsBean.resetL(1);
            BigDecimal[] ids = new BigDecimal[(list.size() < 5) ? list.size() : 5];
            for (int i = 0; i < ids.length; ++i) {
                ids[i] = ((LoginResult)list.get(i)).getRole_id();
            }
            TitleUtil.addTitle(TitleUtil.LTS, ids);
        }
        else {
            this.ltsBean.resetL(0);
        }
        this.BCLts();
    }
    
    public void bangList(String week) {
        List<Map.Entry<BigDecimal, PathPoint>> infoIds = new ArrayList(this.ltsBean.getLtsMap().entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<BigDecimal, PathPoint>>() {
            @Override
            public int compare(Map.Entry<BigDecimal, PathPoint> o1, Map.Entry<BigDecimal, PathPoint> o2) {
                return ((PathPoint)o2.getValue()).getX() - ((PathPoint)o1.getValue()).getX();
            }
        });
        List<LoginResult> list = new ArrayList<>();
        for (int i = 0; i < 50 && i < infoIds.size(); ++i) {
            Map.Entry<BigDecimal, PathPoint> ent = (Map.Entry<BigDecimal, PathPoint>)infoIds.get(i);
            LoginResult login = new LoginResult();
            LoginResult changRole = null;
            RoleData roleData = RolePool.getRoleData((BigDecimal)ent.getKey());
            if (roleData != null) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleData.getLoginResult().getRolename());
                if (ctx2 == null) {
                    continue;
                }
                else {
                    changRole = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                }
            }
            else {
                changRole = AllServiceUtil.getRoleTableService().selectRoleID((BigDecimal)ent.getKey());
            }
            login.setRole_id(changRole.getRole_id());
            login.setRolename(changRole.getRolename());
            login.setGrade(changRole.getGrade());
            login.setBangScore(Integer.valueOf(((PathPoint)ent.getValue()).getX()));
            list.add(login);
        }
        if (GameServer.allBangList != null) {
            GameServer.allBangList.put(Integer.valueOf(5), list);
        }
        if (week != null && week.equals("Monday")) {
            this.resetLts(list);
        }
    }
}
