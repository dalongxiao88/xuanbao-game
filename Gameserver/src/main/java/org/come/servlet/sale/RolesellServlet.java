package org.come.servlet.sale;

import java.io.PrintWriter;

import com.gl.controller.UserController;
import com.gl.model.Result;
import come.tool.Role.CBGData;
import java.text.DateFormat;
import java.util.List;
import org.come.until.GsonUtil;
import come.tool.Role.RolePool;
import org.come.bean.SearchGoodsResultBean;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.BasePageHelper;
import org.come.entity.SalegoodsExample;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.entity.Salegoods;
import org.come.bean.LoginResult;
import org.come.extInterBean.RoleSellRoleInfo;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.extInterBean.RoleSellQueryResp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class RolesellServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String type = request.getParameter("type");
        RoleSellQueryResp resp = new RoleSellQueryResp();
        resp.setType(type);
        if ("selectRole".equals(type)) {
            String userid = request.getParameter("userid");
            List<LoginResult> allLogin = AllServiceUtil.getRoleTableService().selectRoleByUserid(new BigDecimal(userid), new BigDecimal("-" + userid));
            List<RoleSellRoleInfo> list = new ArrayList<>();
            for (int i = 0; i < allLogin.size(); ++i) {
                RoleSellRoleInfo roleInfo = new RoleSellRoleInfo();
                if (((LoginResult)allLogin.get(i)).getUser_id().compareTo(new BigDecimal(0)) == -1) {
                    Salegoods sale = AllServiceUtil.getSalegoodsService().selectSaleGoodsByRoleid(((LoginResult)allLogin.get(i)).getRole_id() + "");
                    if (sale != null) {
                        roleInfo.setSaleid(sale.getSaleid() + "");
                        roleInfo.setSell("2");
                    }
                    else {
                        AllServiceUtil.getRoleTableService().updateRoleStatues(((LoginResult)allLogin.get(i)).getRole_id());
                    }
                }
                roleInfo.setUserid(((LoginResult)allLogin.get(i)).getUser_id() + "");
                roleInfo.setRoleid(((LoginResult)allLogin.get(i)).getRole_id() + "");
                roleInfo.setGrade(this.lvl((int)((LoginResult)allLogin.get(i)).getGrade()));
                roleInfo.setRace_name(this.getRaceSting(((LoginResult)allLogin.get(i)).getSpecies_id()));
                roleInfo.setRolename(((LoginResult)allLogin.get(i)).getRolename());
                roleInfo.setSpecies_id(((LoginResult)allLogin.get(i)).getSpecies_id() + "");
                list.add(roleInfo);
            }
            resp.setRoleInfo(list);
        }
        else if ("roleUpperShelf".equals(type)) {
            String price = request.getParameter("price");
            String userid2 = request.getParameter("userid");
            String roleid = request.getParameter("roleid");
            String species_id = request.getParameter("species_id");
            String salename = request.getParameter("salename");
            Salegoods salegoods = new Salegoods();
            salegoods.setSaleprice(new BigDecimal(price));
            salegoods.setRoleid(new BigDecimal(userid2));
            salegoods.setSaletype(Integer.valueOf(10));
            salegoods.setOtherid(new BigDecimal(roleid));
            salegoods.setContiontype("-1");
            salegoods.setSaleskin(species_id);
            salegoods.setSalename(salename);
            salegoods.setBuyrole(new BigDecimal(0));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdayTime = dateFormat.format(new Date());
            Date nowDate = null;
            try {
                nowDate = dateFormat.parse(nowdayTime);
                salegoods.setUptime(nowDate);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            salegoods.setFlag(Integer.valueOf(2));
            AllServiceUtil.getSalegoodsService().insert(salegoods);
            AllServiceUtil.getRoleTableService().updateRoleStatues(new BigDecimal(roleid));
            if (GameServer.getRoleNameMap().get(salename) != null) {
                SendMessage.sendMessageByRoleName(salename, Agreement.getAgreement().serverstopAgreement());
            }
        }
        else if ("roleLowerShelf".equals(type)) {
            String saleid = request.getParameter("saleid");
            Salegoods salegoods2 = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(new BigDecimal(saleid));
            if (salegoods2 != null) {
                Integer selectFlag = AllServiceUtil.getSalegoodsService().selectFlag(new BigDecimal(saleid));
                if ((int)selectFlag != 3 && (int)selectFlag != 4) {
                    AllServiceUtil.getSalegoodsService().deleteByPrimaryKey(salegoods2.getSaleid());
                    BigDecimal roleid2 = salegoods2.getOtherid();
                    AllServiceUtil.getRoleTableService().updateRoleStatues(roleid2);
                }
            }
        }
        else if ("orderQuery".equals(type)) {
            String pageNum = request.getParameter("PageNow");
            int saletype = 10;
            SalegoodsExample example = new SalegoodsExample();
            SalegoodsExample.Criteria c = example.createCriteria();
            c.andSaletypeEqualTo(Integer.valueOf(saletype));
            int dataNum = 15;
            c.andFlagEqualTo(Integer.valueOf(2));
            BasePageHelper.startPage(Integer.parseInt(pageNum), dataNum);
            List<Salegoods> list2 = AllServiceUtil.getSalegoodsService().selectByExample(example);
            PageInfo<Salegoods> pageInfo = new PageInfo(list2);
            SearchGoodsResultBean resultBean = new SearchGoodsResultBean();
            resultBean.setSalegoods(pageInfo.getList());
            resultBean.setTotal(pageInfo.getPages());
            resp.setSearchGoods(resultBean);
        }
        else if ("orderDetail".equals(type)) {
            String saleid = request.getParameter("saleid");
            Salegoods salegoods2 = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(new BigDecimal(saleid));
            CBGData cbgData = RolePool.getLineCBGRoleData(salegoods2.getOtherid());
            if (cbgData != null) {
                LoginResult log = new LoginResult();
                log.setRolename(cbgData.getLoginResult().getRolename());
                log.setSpecies_id(cbgData.getLoginResult().getSpecies_id());
                log.setRace_name(cbgData.getLoginResult().getRace_name());
                log.setGold(cbgData.getLoginResult().getGold());
                log.setGangname(cbgData.getLoginResult().getGangname());
                log.setScore(cbgData.getLoginResult().getScore());
                log.setGrade(cbgData.getLoginResult().getGrade());
                log.setLocalname(this.lvl((int)cbgData.getLoginResult().getGrade()));
                log.setContribution(cbgData.getLoginResult().getContribution());
                cbgData.setLoginResult(log);
                resp.setSalegoods(salegoods2);
                resp.setCbgData(cbgData);
            }
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(resp));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    public String lvl(int lvl) {
        if (lvl <= 102) {
            return "0转" + lvl;
        }
        if (lvl <= 210) {
            return "1转" + (lvl - 102 + 14);
        }
        if (lvl <= 338) {
            return "2转" + (lvl - 210 + 14);
        }
        if (lvl <= 459) {
            return "3转" + (lvl - 338 + 59);
        }
        return "飞升" + (lvl - 459 + 139);
    }
    
    public String getRaceSting(BigDecimal se) {
        if (se == null) {
            return "";
        }
        int id = se.intValue();
        if (id >= 20001 && id <= 20012) {
            return "人";
        }
        if (id >= 21001 && id <= 21012) {
            return "魔";
        }
        if (id >= 22001 && id <= 22012) {
            return "仙";
        }
        if (id >= 23001 && id <= 23012) {
            return "鬼";
        }
        return "龙";
    }
}
