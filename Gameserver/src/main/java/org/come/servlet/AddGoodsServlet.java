package org.come.servlet;

import java.io.PrintWriter;
import java.util.List;

import com.gl.controller.UserController;
import com.gl.model.Result;
import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import come.tool.Role.RoleData;
import org.come.entity.RoleTable;
import com.auth0.jwt.JWTVerifier;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import org.come.until.SplitLingbaoValue;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Record;
import org.come.server.GameServer;
import org.come.tool.WriteOut;
import org.come.bean.XXGDBean;
import come.tool.Role.RolePool;
import org.come.until.AllServiceUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServlet;

public class AddGoodsServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public static long time;
    private static ConcurrentHashMap<BigDecimal, Long> buys;
    
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
        User user = (User)request.getSession().getAttribute("BG_NAME_XY2");
        Object manger = request.getSession().getAttribute("manger");
        String token = request.getHeader("token");
        if (user == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        if (token == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }
        catch (JWTVerificationException e) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String Id = request.getParameter("Id");
        String num = request.getParameter("sum");
        String tag = request.getParameter("tag");
        String Rolename = request.getParameter("Rolename");
        System.out.println("收到的数据:" + Id + ":" + num + ":" + tag + ":" + Rolename);
        int Resultname = 0;
        RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(Rolename);
        if (userTable != null) {
            RoleData roleData = RolePool.getRoleData(userTable.getRole_id());
            XXGDBean xxgdBean = new XXGDBean();
            xxgdBean.setId(Id);
            xxgdBean.setSum(Integer.parseInt(num));
            xxgdBean.setTag(Long.parseLong(tag));
            if (isTime(userTable.getRole_id(), xxgdBean.getTag())) {
                String v = "疑似抓包的角色id:" + userTable.getRole_id() + ",角色名:" + userTable.getRolename();
                System.out.println(v);
                WriteOut.addtxt(v, 9999L);
                return;
            }
            BigDecimal id = new BigDecimal(xxgdBean.getId());
            Goodstable goodstable = GameServer.getGood(id);
            if (goodstable == null) {
                return;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("刷物资接口物品id:");
            buffer.append(Id);
            buffer.append(",");
            buffer.append(xxgdBean.getSum() + "个" + goodstable.getGoodsname());
            buffer.append(",接收人:");
            buffer.append(userTable.getRole_id());
            buffer.append("_");
            buffer.append(Rolename);
            AllServiceUtil.getRecordService().insert(new Record(4, buffer.toString()));
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setMsg(xxgdBean.getSum() + "个" + goodstable.getGoodsname());
            goodstable.setRole_id(userTable.getRole_id());
            long yid = id.longValue();
            for (int i = 0; i < xxgdBean.getSum(); ++i) {
                if (i != 0) {
                    goodstable = GameServer.getGood(id);
                }
                goodstable.setRole_id(userTable.getRole_id());
                long sid = goodstable.getGoodsid().longValue();
                if (sid >= 70001L && sid <= 70030L) {
                    Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), userTable.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(-3));
                }
                else if (sid >= 69001L && sid <= 69015L) {
                    Lingbao lingbao = SplitLingbaoValue.addfa(sid, userTable.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    if (i == 0) {
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(-3));
                    }
                }
                else if (goodstable.getType() == 825L) {
                    if (!goodstable.getValue().equals("")) {
                        String[] v2 = goodstable.getValue().split("\\|");
                        int suitid = Integer.parseInt(v2[0]);
                        int part = Integer.parseInt(v2[1]);
                        int pz = Integer.parseInt(v2[2]);
                        PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                        assetUpdate.setJade(jade);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(-3));
                    }
                }
                else if (EquipTool.canSuper(goodstable.getType())) {
                    int sum = (yid == sid) ? xxgdBean.getSum() : 1;
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(userTable.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(-3));
                    }
                    else {
                        goodstable.setUsetime(Integer.valueOf(sum));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(-3));
                    }
                    if (yid == sid) {
                        break;
                    }
                }
                else {
                    goodstable.setUsetime(Integer.valueOf(1));
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(-3));
                }
            }
            if (GameServer.getRoleNameMap().get(Rolename) != null) {
                SendMessage.sendMessageToSlef((ChannelHandlerContext)GameServer.getRoleNameMap().get(Rolename), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            Resultname = 1;
        }
        else {
            Resultname = 2;
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(Resultname)));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    public static boolean isTime(BigDecimal roleid, long roleTime) {
        if (roleTime > AddGoodsServlet.time) {
            Long time1 = (Long)AddGoodsServlet.buys.get(roleid);
            if (time1 == null || roleTime > (long)time1) {
                AddGoodsServlet.buys.put(roleid, Long.valueOf(roleTime));
                return false;
            }
        }
        return true;
    }
    
    static {
        AddGoodsServlet.buys = new ConcurrentHashMap<>();
    }
}
