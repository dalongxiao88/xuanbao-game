package org.come.servlet;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.RoleTable;
import org.come.until.GsonUtil;
import org.come.extInterBean.GodsRecordResultModel;
import org.come.extInterBean.Goodsrecord2;
import org.come.until.AllServiceUtil;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class GoodsRecordQueryServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String nowPage = request.getParameter("nowPage");
        String gDState = request.getParameter("gDState");
        String gDRoleName = request.getParameter("gDRoleName");
        String gDOtherName = request.getParameter("gDOtherName");
        String gDGoodsName = request.getParameter("gDGoodsName");
        String gDTime = request.getParameter("gDTime");
        String rgid = request.getParameter("rgid");
        String quid = request.getParameter("quid");
        String page = request.getParameter("page");
        String gDType = request.getParameter("gDType");
        String goodsRecordres = "";
        if ("goodsrecord".equals(gDType)) {
            goodsRecordres = this.goodsRecordQuery(nowPage, gDState, gDRoleName, gDOtherName, gDGoodsName, gDTime);
        }
        else if ("trackGoods".equals(gDType)) {
            goodsRecordres = this.trackGoods(rgid, quid, page);
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(goodsRecordres);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    public String goodsRecordQuery(String nowPage, String goodsDetailState, String goodsDetailRoleName, String goodsDetailOtherName, String goodsDetailGoodsName, String goodsDetailTime) {
        String result = "";
        if (nowPage == null || goodsDetailState == null || goodsDetailRoleName == null || goodsDetailOtherName == null || goodsDetailGoodsName == null || goodsDetailTime == null) {
            return result;
        }
        String sql = "";
        if (!"".equals(goodsDetailRoleName)) {
            RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(goodsDetailRoleName);
            if (role == null) {
                sql += " AND ROLEID = 0 ";
            }
            else {
                sql = sql + " AND ROLEID = " + role.getRole_id();
            }
        }
        if (!"".equals(goodsDetailOtherName)) {
            RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(goodsDetailOtherName);
            if (role == null) {
                sql += " AND OTHERROLE = 0 ";
            }
            else {
                sql = sql + " AND OTHERROLE = " + role.getRole_id();
            }
        }
        if (!"".equals(goodsDetailState)) {
            sql = sql + " AND RECORDTYPE = " + goodsDetailState;
        }
        if (!"".equals(goodsDetailGoodsName)) {
            sql = sql + " AND GOODSNAME = '" + goodsDetailGoodsName + "'";
        }
        if (!"".equals(goodsDetailTime)) {
            sql = sql + " AND RECORDTIME between to_timestamp('" + goodsDetailTime + " 00:00:0.000000000','yyyy-mm-dd hh24:mi:ss.ff9') and to_timestamp('" + goodsDetailTime + " 23:59:59.000000000','yyyy-mm-dd hh24:mi:ss.ff9')";
        }
        List<Goodsrecord2> goodsRecord = AllServiceUtil.getAppVersionService().selectGoodsRecordByPage(sql, Integer.valueOf(nowPage));
        for (int i = 0; i < goodsRecord.size(); ++i) {
            BigDecimal myRole = ((Goodsrecord2)goodsRecord.get(i)).getRoleid();
            if (myRole != null) {
                RoleTable role2 = AllServiceUtil.getRoleTableService().selectGang(myRole);
                if (role2 != null) {
                    ((Goodsrecord2)goodsRecord.get(i)).setRoleName(role2.getRolename());
                }
                else {
                    ((Goodsrecord2)goodsRecord.get(i)).setRoleName("");
                }
            }
            BigDecimal otherRole = ((Goodsrecord2)goodsRecord.get(i)).getOtherrole();
            if (otherRole != null && !"0".equals(otherRole)) {
                RoleTable role3 = AllServiceUtil.getRoleTableService().selectGang(otherRole);
                if (role3 != null) {
                    ((Goodsrecord2)goodsRecord.get(i)).setOtherRole(role3.getRolename());
                }
                else {
                    ((Goodsrecord2)goodsRecord.get(i)).setOtherRole("");
                }
            }
        }
        GodsRecordResultModel godsRec = new GodsRecordResultModel(goodsRecord, (int)Integer.valueOf(nowPage), 0);
        String godsRecJson = GsonUtil.getGsonUtil().getgson().toJson(godsRec);
        return godsRecJson;
    }
    
    public String trackGoods(String rgid, String quid, String page) {
        String result = "";
        if (rgid == null || "".equals(rgid) || quid == null || "".equals(quid) || page == null || "".equals(page)) {
            return result;
        }
        int rgidi = (int)Integer.valueOf(rgid);
        int quidi = (int)Integer.valueOf(quid);
        int pagei = (int)Integer.valueOf(page);
        List<Goodsrecord2> trackGoods = AllServiceUtil.getAppVersionService().trackGoods(rgidi, quidi, pagei);
        for (int i = 0; i < trackGoods.size(); ++i) {
            BigDecimal myRole = ((Goodsrecord2)trackGoods.get(i)).getRoleid();
            Integer sid = ((Goodsrecord2)trackGoods.get(i)).getSid();
            if (myRole != null) {
                RoleTable role = AllServiceUtil.getRoleTableService().selectGang(myRole);
                if (role != null) {
                    ((Goodsrecord2)trackGoods.get(i)).setRoleName(role.getRolename());
                }
                else {
                    ((Goodsrecord2)trackGoods.get(i)).setRoleName("");
                }
            }
            BigDecimal otherRole = ((Goodsrecord2)trackGoods.get(i)).getOtherrole();
            if (otherRole != null && !"0".equals(otherRole)) {
                RoleTable role2 = AllServiceUtil.getRoleTableService().selectGang(otherRole);
                if (role2 != null) {
                    ((Goodsrecord2)trackGoods.get(i)).setOtherRole(role2.getRolename());
                }
                else {
                    ((Goodsrecord2)trackGoods.get(i)).setOtherRole("");
                }
            }
        }
        GodsRecordResultModel model = new GodsRecordResultModel(trackGoods, pagei, 0);
        String godsRecoJson = GsonUtil.getGsonUtil().getgson().toJson(model);
        return godsRecoJson;
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    public static String getStr(List<String> list) {
        String str = (String)list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            str = str + "," + (String)list.get(i);
        }
        return str;
    }
}
