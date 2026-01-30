package com.gl.controller;

import come.tool.Battle.BattleData;
import come.tool.Battle.BattleThreadPool;
import org.come.entity.PayvipBean;
import come.tool.Role.RoleData;
import redis.clients.jedis.Jedis;
import org.come.entity.UserTable;
import org.come.entity.Record;
import org.come.handler.MainServerHandler;
import org.come.tool.WriteOut;
import come.tool.Role.PrivateData;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.UseCardBean;
import come.tool.Role.RolePool;
import org.come.bean.ApplyPayBean;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.bean.ApplyBean;
import org.come.redis.RedisPoolUntil;
import org.apache.commons.httpclient.util.DateUtil;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;
import org.apache.commons.lang.math.NumberUtils;
import com.github.pagehelper.util.StringUtil;
import org.come.entity.RoleTable;
import org.come.model.Skill;
import java.util.Iterator;
import cn.hutool.core.util.ArrayUtil;
import org.come.tool.Arith;
import come.tool.Good.UsePetAction;
import com.gl.model.UpPetParam;
import java.util.Collections;
import java.util.stream.Collectors;
import org.come.entity.Openareatable;
import org.come.until.CreateTextUtil;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import org.come.bean.BackRoleInfo;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.come.entity.ExpensesReceipts;
import org.come.entity.Baby;
import java.io.UnsupportedEncodingException;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import java.util.ArrayList;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.URLUtil;
import org.come.entity.Lingbao;
import org.come.entity.Mount;
import org.come.entity.RoleSummoning;
import com.gl.service.PlayerService;
import com.gl.token.UserToken;
import java.util.List;
import java.math.BigDecimal;
import com.gl.service.GoodsService;
import com.gl.model.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.come.agent.Agent;
import org.come.until.AllServiceUtil;
import com.gl.service.ResultFactory;
import com.gl.service.TokenService;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import com.gl.model.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.gl.model.User;
import org.come.entity.Goodstable;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController
{
    private ConcurrentHashMap<String, Goodstable> nds;
    private String agentGoodsIds;
    private static final int PageSize = 10;
    
    public AdminController() {
        this.nds = new ConcurrentHashMap<>();
    }
    
    @PostMapping({ "/api/adminLogin" })
    public Result login1(User user, HttpSession session, HttpServletRequest request) {
        // 获取用户名密码格式为 用户名|&|密码
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "administrator.db");
        String[] nameAndPwd = up.split("\\|&\\|");
        if (nameAndPwd[0].equals(user.getUserName()) && nameAndPwd[1].equals(user.getPassword())) {
            TokenService tokenService = new TokenService();
            String token = tokenService.getToken(user);
            session.setAttribute("BG_NAME_XY2", user);
            return ResultFactory.success(token + "|admin");
        }
        //查询代理登录
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        if (agent != null) {
            if (agent.getUserName().equals(user.getUserName()) && agent.getPassword().equals(user.getPassword())) {
                TokenService tokenService2 = new TokenService();
                String token2 = tokenService2.getToken(user);
                session.setAttribute("BG_NAME_XY2", user);
                return ResultFactory.success(token2 + "|agent");
            }
            return ResultFactory.fail("用户名或密码错误，请重新登录！ ");
        }
        else {
            return ResultFactory.fail("用户名或密码错误，请重新登录！ ");
        }
    }
    
    @UserToken
    @PostMapping({ "/api/getUserGood" })
    public Result getUserGood(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(new BigDecimal(param.getValue1()));
        return ResultFactory.success(goods);
    }
    
    @UserToken
    @PostMapping({ "/api/agentRecharge" })
    public Result recharge(Param param, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        if (agent == null) {
            return ResultFactory.fail("代理不存在！");
        }
        if ("1".equals(param.getValue5())) {
            //仙玉
            Integer yuanbao = Integer.valueOf(Integer.parseInt(param.getValue3()));
            if (agent.getXianyu().intValue() < (int)yuanbao) {
                return ResultFactory.fail("代理元宝不足以抵扣本次充值！");
            }
            Integer jf = Integer.valueOf(Integer.parseInt(param.getValue2()));
            if (agent.getJf().intValue() < (int)jf) {
                return ResultFactory.fail("代理充值金额不足以抵扣本次充值！");
            }
            agent.setXianyu(agent.getXianyu().subtract(new BigDecimal(param.getValue3())));
            agent.setJf(agent.getJf().subtract(new BigDecimal(param.getValue2())));
        }
        else if ("2".equals(param.getValue5())) {
            param.setValue2("98");
            Integer jf2 = Integer.valueOf(Integer.parseInt(param.getValue2()));
            if (agent.getJf().intValue() < (int)jf2) {
                return ResultFactory.fail("代理充值金额不足以抵扣本次充值！");
            }
            agent.setJf(agent.getJf().subtract(new BigDecimal(param.getValue2())));
        }
        AllServiceUtil.getAgentService().upAgentXyAndJf(agent);
        PlayerService service = new PlayerService();
        if (this.agentRechargeCallBack(param, agent)) {
            return ResultFactory.success(AllServiceUtil.getAgentService().selectByUserName(agent.getUserName()));
        }
        return ResultFactory.fail("操作失败");
    }
    
    @UserToken
    @PostMapping({ "/api/getUserPet" })
    public Result getUserPet(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<RoleSummoning> roleSummonings = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(new BigDecimal(param.getValue1()));
        return ResultFactory.success(roleSummonings);
    }
    
    @UserToken
    @PostMapping({ "/api/getUserMount" })
    public Result getUserMount(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(new BigDecimal(param.getValue1()));
        return ResultFactory.success(mounts);
    }
    
    @UserToken
    @PostMapping({ "/api/getUserLing" })
    public Result getUserLing(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(new BigDecimal(param.getValue1()));
        return ResultFactory.success(lingbaos);
    }
    
    @UserToken
    @PostMapping({ "/api/updUserLing" })
    public Result updUserLing(Lingbao param, HttpServletRequest request) throws UnsupportedEncodingException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String baoname = URLUtil.decode(param.getBaoname());
        param.setBaoname(baoname);
        String kangxing = URLUtil.decode(param.getKangxing());
        param.setKangxing(kangxing);
        if (CharSequenceUtil.isNotBlank(param.getSkills()) && !param.getSkills().equals("null")) {
            String sKill = URLUtil.decode(param.getSkills());
            param.setSkills(sKill);
        }
        if (param.getSkills().equals("null")) {
            param.setSkills(null);
        }
        String tianfu = URLUtil.decode(param.getTianfuskill());
        param.setTianfuskill(tianfu);
        String goodSkill = URLUtil.decode(param.getGoodskill());
        param.setGoodskill(goodSkill);
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(param.getRoleid());
        if (loginResult == null) {
            return ResultFactory.fail("角色不存在！");
        }
        Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(param.getBaoid());
        if (lingbao == null) {
            return ResultFactory.fail("灵宝不存在！");
        }
        lingbao.setBaoactive(param.getBaoactive());
        lingbao.setBaospeed(param.getBaospeed());
        lingbao.setAssistance(param.getAssistance());
        lingbao.setGoodskill(param.getGoodskill());
        lingbao.setLingbaolvl(param.getLingbaolvl());
        lingbao.setSkills(param.getSkills());
        lingbao.setTianfuskill(param.getTianfuskill());
        lingbao.setSkillsum(param.getSkillsum());
        lingbao.setFusum(param.getFusum());
        AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
        if (loginResult != null) {
            ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            if (channelHandlerContext != null) {
                AssetUpdate update = new AssetUpdate();
                List<Lingbao> lingbaos = new ArrayList<>();
                lingbaos.add(lingbao);
                update.setLingbaos(lingbaos);
                update.setType(AssetUpdate.USEGOOD);
                update.setMsg(":#R多功能后台修改灵宝成功#23");
                SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
            }
        }
        return ResultFactory.success(null);
    }
    
    @UserToken
    @PostMapping({ "/api/getUserBaby" })
    public Result getUserBaby(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(new BigDecimal(param.getValue1()));
        return ResultFactory.success(babys);
    }
    
    @UserToken
    @PostMapping({ "/api/getAgentLog" })
    public Result getAgentLog(Param param, HttpServletRequest httpServletRequest, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        ExpensesReceipts expensesReceipts = new ExpensesReceipts();
        if (agent != null) {
            expensesReceipts.setManagerid(new BigDecimal((int)agent.getAgentId()));
        }
        List<ExpensesReceipts> expensesReceipts2 = AllServiceUtil.getExpensesReceiptsService().selectAll(expensesReceipts);
        return ResultFactory.success(expensesReceipts2);
    }
    
    @UserToken
    @GetMapping({ "/api/getAgentJurisdiction" })
    public Result getAgentJurisdiction(HttpServletRequest httpServletRequest, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "administrator.db");
        String[] nameAndPwd = up.split("\\|&\\|");
        if (nameAndPwd[0].equals(user.getUserName()) && nameAndPwd[1].equals(user.getPassword())) {
            Agent agent = new Agent();
            agent.setJurisdiction("admin");
            return ResultFactory.success(agent);
        }
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        if (agent != null) {
            return ResultFactory.success(agent);
        }
        return ResultFactory.success("");
    }
    
    @UserToken
    @GetMapping({ "/api/agentGoods" })
    public Result agentGoods(HttpServletRequest httpServletRequest, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Map<String, String> goodsMap = new GoodsService().goodsMap();
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "administrator.db");
        String[] nameAndPwd = up.split("\\|&\\|");
        if (nameAndPwd[0].equals(user.getUserName()) && nameAndPwd[1].equals(user.getPassword())) {
            return ResultFactory.success(goodsMap);
        }
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        if (agent != null && StringUtils.isNotBlank(agent.getJurisdiction()) && agent.getJurisdiction().contains("物品") && StringUtils.isNotBlank(this.agentGoodsIds)) {
            String[] items = this.agentGoodsIds.split("\\|");
            Map<String, String> AgentGoodsMap = new ConcurrentHashMap<>();
            for (String item : items) {
                goodsMap.forEach((k, v)/* java.lang.String,java.lang.String, */ -> {
                    if (v.equals(item)) {
                        AgentGoodsMap.put(k, v);
                    }
                    return;
                });
            }
            return ResultFactory.success(AgentGoodsMap);
        }
        else {
            return ResultFactory.success(new ConcurrentHashMap<>());
        }
    }
    
    @UserToken
    @PostMapping({ "/api/agentRole" })
    public Result roles(Param param, HttpServletRequest httpServletRequest, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "administrator.db");
        String[] nameAndPwd = up.split("\\|&\\|");
        if (nameAndPwd[0].equals(user.getUserName()) && nameAndPwd[1].equals(user.getPassword())) {
            PlayerService service = new PlayerService();
            BackRoleInfo role = service.getRole(param);
            return ResultFactory.success(role);
        }
        BackRoleInfo role2 = new BackRoleInfo();
        Agent agent = AllServiceUtil.getAgentService().selectByUserName(user.getUserName());
        if (agent != null) {
            param.setValue3(agent.getQid());
            role2 = this.getRole(param);
        }
        return ResultFactory.success(role2);
    }
    
    @UserToken
    @GetMapping({ "/api/getAgentSendGoods" })
    public Result getAgendSendGoods(HttpServletRequest httpServletRequest, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        if (StringUtils.isBlank(this.agentGoodsIds)) {
            Properties properties = new Properties();
            InputStream in = GameServer.class.getClassLoader().getResourceAsStream("agent.properties");
            try {
                properties.load(in);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            this.agentGoodsIds = properties.get("agentGoods").toString();
        }
        return ResultFactory.success(this.agentGoodsIds);
    }
    
    @UserToken
    @PostMapping({ "/api/upAgentSendGoods" })
    public Result upAgentSendGoods(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        if (StringUtils.isNotBlank(param.getValue1())) {
            this.agentGoodsIds = param.getValue1();
            try {
                byte[] bs = this.agentGoodsIds.getBytes();
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "agentGoods.txt", bs);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultFactory.success(Boolean.valueOf(true));
    }
    
    @UserToken
    @PostMapping({ "/api/agentSendGoods" })
    public Result agentSendGoods(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        if (service.sendGoods(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("物品发送失败，请确认玩家或物品是否存在");
    }
    
    @UserToken
    @GetMapping({ "/api/getMenuList" })
    public Result getMenuList(Param param, HttpServletRequest httpServletRequest) {
        Result ipCheckResult = UserController.IPstop(httpServletRequest);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("BG_NAME_XY2");
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "user.db");
        String[] nameAndPwd = up.split("\\|&\\|");
        if (user.getUserName().equals(nameAndPwd[0])) {
            return ResultFactory.success("admin");
        }
        return ResultFactory.success("test");
    }
    
    @UserToken
    @GetMapping({ "/api/getAgentAll" })
    public Result getMenuList(HttpServletRequest httpServletRequest) {
        Result ipCheckResult = UserController.IPstop(httpServletRequest);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Agent> agents = AllServiceUtil.getAgentService().selectAll();
        return ResultFactory.success(agents);
    }
    
    @UserToken
    @GetMapping({ "/api/getOpenAll" })
    public Result getOpenAll(HttpServletRequest httpServletRequest) {
        Result ipCheckResult = UserController.IPstop(httpServletRequest);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Openareatable> openareatables = AllServiceUtil.getOpenareatableService().selectAllOpenareatable();
        return ResultFactory.success(openareatables);
    }
    
    @UserToken
    @PostMapping({ "/api/addAgent" })
    public Result addAgent(Agent agent, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<BigDecimal> bigDecimals = AllServiceUtil.getOpenareatableService().selectTuijiNum(agent.getUserName());
        if (bigDecimals.size() > 0) {
            return ResultFactory.fail("当前代理已存在");
        }
        Openareatable openareatable1 = new Openareatable();
        List<Openareatable> openareatables = AllServiceUtil.getOpenareatableService().selectAllOpenareatable();
        if (openareatables.size() == 1) {
            openareatable1 = (Openareatable)openareatables.get(0);
            openareatable1.setOt_areaid(new BigDecimal(openareatable1.getOt_areaid().intValue() + 1));
            openareatable1.setOt_atid(agent.getUserName());
            AllServiceUtil.getOpenareatableService().insertOpenareatable(openareatable1);
        }
        else if (openareatables.size() != 0) {
            List<BigDecimal> collect = (List)openareatables.stream().map(item/* org.come.entity.Openareatable, */ -> item.getOt_areaid()).collect(Collectors.toList());
            Collections.sort(collect);
            openareatable1 = (Openareatable)openareatables.get(0);
            openareatable1.setOt_areaid(new BigDecimal(((BigDecimal)collect.get(collect.size() - 1)).intValue() + 1));
            openareatable1.setOt_atid(agent.getUserName());
            AllServiceUtil.getOpenareatableService().insertOpenareatable(openareatable1);
        }
        agent.setQid(openareatable1.getOt_areaid().toString());
        agent.setXianyu(BigDecimal.ZERO);
        agent.setJf(BigDecimal.ZERO);
        AllServiceUtil.getAgentService().addAgent(agent);
        return ResultFactory.success(true);
    }
    
    @UserToken
    @PostMapping({ "/api/delAgent" })
    public Result delAgent(Agent agent, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        AllServiceUtil.getAgentService().deleteById(agent.getAgentId().toString());
        List<Openareatable> openareatables = AllServiceUtil.getOpenareatableService().selectAllOpenareatable();
        Openareatable openareatable = (Openareatable)openareatables.stream().filter(item/* org.come.entity.Openareatable, */ -> item.getOt_atid().equals(agent.getUserName())).findFirst().get();
        AllServiceUtil.getOpenareatableService().deleteOpenareatable(openareatable.getTb_id());
        return ResultFactory.success(true);
    }
    
    @UserToken
    @PostMapping({ "/api/upAgentPwd" })
    public Result upAgentPwd(Agent agent, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        AllServiceUtil.getAgentService().upAgent(agent);
        return ResultFactory.success(true);
    }
    
    @UserToken
    @PostMapping({ "/api/addPay" })
    public Result addPay(Agent agent, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Agent dbAgent = AllServiceUtil.getAgentService().selectByUserName(agent.getUserName());
        dbAgent.setXianyu(agent.getXianyu().add(dbAgent.getXianyu()));
        dbAgent.setJf(agent.getJf().add(dbAgent.getJf()));
        AllServiceUtil.getAgentService().upAgentXyAndJf(dbAgent);
        return ResultFactory.success(Boolean.valueOf(true));
    }
    
    @UserToken
    @PostMapping({ "/api/updUserBaby" })
    public Result updUserBaby(Baby baby, HttpServletRequest request) throws UnsupportedEncodingException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String babyName = new String(baby.getBabyname().getBytes("ISO8859-1"), "utf-8");
        String come = new String(baby.getOutcome().getBytes("ISO8859-1"), "utf-8");
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(baby.getRoleid());
        if (loginResult == null) {
            return ResultFactory.fail("角色不存在！");
        }
        Baby dbBaby = AllServiceUtil.getBabyService().selectBabyById(baby.getBabyid());
        if (dbBaby == null) {
            return ResultFactory.fail("孩子不存在！");
        }
        dbBaby.setTalents(baby.getTalents());
        dbBaby.setQizhi(baby.getQizhi());
        dbBaby.setNeili(baby.getNeili());
        dbBaby.setZhili(baby.getZhili());
        dbBaby.setNaili(baby.getNaili());
        dbBaby.setMingqi(baby.getMingqi());
        dbBaby.setDaode(baby.getDaode());
        dbBaby.setPanni(baby.getPanni());
        dbBaby.setWanxing(baby.getWanxing());
        dbBaby.setQingmi(baby.getQingmi());
        dbBaby.setXiaoxin(baby.getXiaoxin());
        dbBaby.setWenbao(baby.getWenbao());
        dbBaby.setBabyage(baby.getBabyage());
        dbBaby.setOutcome(come);
        dbBaby.setQingmi(baby.getQingmi());
        dbBaby.setNaili(baby.getNaili());
        AllServiceUtil.getBabyService().updateBaby(dbBaby);
        if (loginResult != null) {
            ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            if (channelHandlerContext != null) {
                AssetUpdate update = new AssetUpdate();
                List<Baby> babies = new ArrayList<>();
                babies.add(dbBaby);
                update.setBabys(babies);
                update.setType(AssetUpdate.USEGOOD);
                update.setMsg("#R多功能后台修改孩子成功#23");
                SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
            }
        }
        return ResultFactory.success(null);
    }
    
    @UserToken
    @PostMapping({ "/api/updUserMount" })
    public Result updUserMount(Mount mount, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(mount.getRoleid());
        if (loginResult == null) {
            return ResultFactory.fail("角色不存在！");
        }
        Mount dbMount = AllServiceUtil.getMountService().selectMountsByMID(mount.getMid());
        dbMount.setExp(mount.getExp());
        dbMount.setMountlvl(mount.getMountlvl());
        dbMount.setPower(mount.getPower());
        dbMount.setBone(mount.getBone());
        dbMount.setSpri(mount.getSpri());
        dbMount.setLive(mount.getLive());
        dbMount.setProficiency(mount.getProficiency());
        AllServiceUtil.getMountService().updateMount(dbMount);
        if (loginResult != null) {
            ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            if (channelHandlerContext != null) {
                AssetUpdate update = new AssetUpdate();
                List<Mount> mounts = new ArrayList<>();
                mounts.add(dbMount);
                update.setMounts(mounts);
                update.setType(AssetUpdate.USEGOOD);
                update.setMsg("#R多功能后台修改坐骑成功#23");
                SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
            }
        }
        return ResultFactory.success(null);
    }
    
    @UserToken
    @PostMapping({ "/api/updUserPet" })
    public Result updUserPet(UpPetParam param, HttpServletRequest request) throws UnsupportedEncodingException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String ndsv = new String(param.getNds().getBytes("ISO8859-1"), "utf-8");
        param.setNds(ndsv);
        RoleSummoning roleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(param.getSid()));
        if (roleSummoning == null) {
            return ResultFactory.fail("未找到对应的召唤兽！");
        }
        RoleSummoning pet = GameServer.getPet(new BigDecimal(roleSummoning.getSummoningid()));
        roleSummoning.setTurnRount(param.getTurnRount());
        roleSummoning.setGrowlevel(pet.getGrowlevel());
        for (int i = 0; i < param.getTurnRount(); ++i) {
            BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(roleSummoning.getGrowlevel()), 0.1);
            roleSummoning.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        }
        Integer petLvl = this.getPetLvl(param.getTurnRount());
        roleSummoning.setFriendliness(param.getFriendliness());
        roleSummoning.setGrade(param.getGrade() + (int)petLvl + 1);
        roleSummoning.setOpenSeal(param.getOpenSeal());
        roleSummoning.setBone(param.getGrade());
        roleSummoning.setSpir(param.getGrade());
        roleSummoning.setPower(param.getGrade());
        roleSummoning.setSpeed(param.getGrade());
        List<Goodstable> eqGoods = null;
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(roleSummoning.getRoleid());
        if (StringUtils.isNotBlank(param.getSkill())) {
            if (StringUtils.isNotBlank(roleSummoning.getStye())) {
                eqGoods = new ArrayList<>();
                String[] v = roleSummoning.getStye().split("\\|");
                for (int j = 1; j < v.length; ++j) {
                    String[] vs = v[j].split("-");
                    if (vs.length >= 2) {
                        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
                        eqGoods.add(good);
                    }
                }
            }
            for (Goodstable eqGood : eqGoods) {
                String[] val = eqGood.getValue().split("\\|");
                int index = -1;
                int k = 0;
                while (k < val.length) {
                    if (val[k].startsWith("觉醒技")) {
                        index = k;
                        break;
                    }
                    else {
                        ++k;
                    }
                }
                String jxSkill = "";
                if (index != -1) {
                    String[] split = val[index].split("&");
                    split[1] = param.getSkill();
                    jxSkill = ArrayUtil.join(split, "&");
                }
                val[index] = jxSkill;
                eqGood.setValue(ArrayUtil.join(val, "|"));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(eqGood);
                if (loginResult != null) {
                    ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                    if (channelHandlerContext != null) {
                        AssetUpdate update = new AssetUpdate();
                        List<Goodstable> goodstables = new ArrayList<>();
                        goodstables.add(eqGood);
                        update.setGoods(goodstables);
                        update.setType(AssetUpdate.GIVE);
                        update.setMsg("#R多功能后台修改物品修改成功#23");
                        SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        AssetUpdate update2 = new AssetUpdate();
        if (param.getLx() != null) {
            roleSummoning.setLingxi(getLx(param.getLx() - 1));
        }
        List<Goodstable> goodstables2 = new ArrayList<>();
        if (StringUtils.isNotBlank(param.getNds())) {
            String[] split2 = param.getNds().split("\\|");
            for (String nd : split2) {
                GameServer.getAllGoodsMap().forEach((k, v)/* java.math.BigDecimal,org.come.entity.Goodstable, */ -> {
                    if (v.getGoodsname().equals(nd)) {
                        this.nds.put(nd, v);
                    }
                    return;
                });
            }
            if (StringUtils.isNotBlank(roleSummoning.getInnerGoods())) {
                for (String s : roleSummoning.getInnerGoods().split("\\|")) {
                    Goodstable dbGood = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(s));
                    dbGood.goodxh(1);
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(dbGood);
                    goodstables2.add(dbGood);
                }
            }
            String[] ndIds = new String[split2.length];
            for (int k = 0; k < split2.length; ++k) {
                Goodstable goodstable = (Goodstable)this.nds.get(split2[k]);
                String[] split3 = goodstable.getValue().split("\\|");
                split3[2] = "内丹等级=4转180";
                goodstable.setValue(ArrayUtil.join(split3, "|"));
                goodstable.setUsetime(1);
                goodstable.setRole_id(loginResult.getRole_id());
                goodstable.setStatus(1);
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                ndIds[k] = goodstable.getRgid().toString();
                goodstables2.add(goodstable);
            }
            String join = ArrayUtil.join(ndIds, "|");
            roleSummoning.setInnerGoods(join);
            update2.setGoods(goodstables2);
        }
        AllServiceUtil.getRoleSummoningService().updateRoleSummoning(roleSummoning);
        if (loginResult != null) {
            ChannelHandlerContext channelHandlerContext2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            if (channelHandlerContext2 != null) {
                List<RoleSummoning> roleSummonings = new ArrayList<>();
                roleSummonings.add(roleSummoning);
                update2.setPets(roleSummonings);
                update2.setType(AssetUpdate.USEGOOD);
                update2.setMsg("#R多功能后台修改宠物成功#23");
                SendMessage.sendMessageToSlef(channelHandlerContext2, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update2)));
            }
        }
        return ResultFactory.success(null);
    }

    /**
     * 召唤兽转生 点化
     */
    /**
     * 召唤兽转生 点化
     */
    public void DHPet(RoleSummoning pet) {
        int petTurn = pet.getTurnRount();
        int lvl = (int)pet.getGrade();
        ++lvl;
        ++petTurn;

        //设置这只召唤兽的根骨、灵性、力量、敏捷、经验为0
        pet.setBone(0);
        pet.setSpir(0);
        pet.setPower(0);
        pet.setSpeed(0);
        pet.setCalm(0);
        pet.setExp(new BigDecimal(0));
        //等级
        pet.setGrade(lvl);
        pet.setTurnRount(petTurn);
        //设置忠诚度为100
        pet.setFaithful(100);
        //成长率加0.1
        BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
        pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));

        pet.setBasishp(0);
        pet.setBasismp(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
    }
    
    private String getLx(Integer type) {
        String lx = "";
        if ((int)type == 0) {
            lx = "11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11026_0|11045_0|11046_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11014_0|11015_0|11016_0|11017_0|11018_0|11019_0|11020_0|11021_0|11022_0|11023_0|11024_0|11025_0|11047_0|11049_0|11051_0|11053_0|11055_0|11057_0|11062_0|11063_0|11061_0|11060_0|11058_0|11059_0|11056_0|11054_0|11052_0|11050_0|11048_0|11027_0|11028_0|11029_0|11031_0|11032_0|11033_0|11034_0|11035_0|11036_0|11030_0|11037_0|11042_0|11039_0|11043_0|11044_0|11040_0|11041_0";
        }
        else if ((int)type == 1) {
            lx = "11003_0|11001_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11016_0|11018_0|11013_0|11015_0|11017_0|11019_0|11020_0|11020_0|11021_0|11022_0|11023_0|11024_0|11025_0";
        }
        else if ((int)type == 2) {
            lx = "11001_0|11004_0|11002_0|11005_0|11007_0|11026_0|11027_0|11028_0|11029_0|11031_0|11033_0|11035_0|11036_0|11032_0|11034_0|11030_0|11037_0|11039_0|11040_0|11041_0|11042_0|11043_0|11044_0";
        }
        else if ((int)type == 3) {
            lx = "11001_0|11004_0|11002_0|11005_0|11046_0|11047_0|11048_0|11049_0|11050_0|11052_0|11054_0|11056_0|11051_0|11053_0|11055_0|11057_0|11058_0|11059_0|11060_0|11061_0|11062_0|11063_0";
        }
        String[] lhHead = { "Lx=0", "Lv=0", "Point=0", "Open=" };
        String[] skillIds = lx.split("\\|");
        String[] lxs = new String[skillIds.length];
        int count = 0;
        for (int i = 0; i < skillIds.length; ++i) {
            Skill skill = GameServer.getSkill(skillIds[i].split("_")[0]);
            lxs[i] = skill.getSkillid() + "_" + (int)skill.getValue();
            count += (int)skill.getValue();
        }
        lhHead[2] = "Point=" + count;
        lhHead[0] = "Lx=" + type;
        String join = ArrayUtil.join(lxs, "|");
        String join2 = ArrayUtil.join(lhHead, "&");
        return join2 + join;
    }
    
    public Integer getPetLvl(int zs) {
        if (zs == 1) {
            return 100;
        }
        if (zs == 2) {
            return 221;
        }
        if (zs == 3) {
            return 362;
        }
        if (zs == 4) {
            return 543;
        }
        if (zs == 0) {
            return 0;
        }
        return 0;
    }
    
    @UserToken
    @PostMapping({ "/api/updUserGood" })
    public Result updUserGood(Goodstable goodstable, HttpServletRequest request) throws UnsupportedEncodingException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        Goodstable dbGoods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(goodstable.getRgid());
        if (dbGoods != null) {
            String v = new String(goodstable.getValue().getBytes("ISO8859-1"), "utf-8");
            dbGoods.setValue(v);
            AllServiceUtil.getGoodsTableService().updateGoodRedis(dbGoods);
            LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(dbGoods.getRole_id());
            if (loginResult != null) {
                ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                if (channelHandlerContext != null) {
                    AssetUpdate update = new AssetUpdate();
                    List<Goodstable> goodstables = new ArrayList<>();
                    goodstables.add(dbGoods);
                    update.setGoods(goodstables);
                    update.setType(AssetUpdate.GIVE);
                    update.setMsg("#R多功能后台修改物品修改成功#23");
                    SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
                }
            }
        }
        return ResultFactory.success("111111111");
    }
    
    public BackRoleInfo getRole(Param param) {
        String type = param.getValue1();
        String value = param.getValue2();
        int pageNum = param.getPageNum();
        int status = param.getStatus();
        int size = param.getPageSize();
        if (size < 10) {
            size = 10;
        }
        BackRoleInfo list = null;
        RoleTable roleTable = new RoleTable();
        if (StringUtils.isNotBlank(param.getValue3())) {
            roleTable.setQid(new BigDecimal(param.getValue3()));
        }
        else {
            roleTable.setQid(null);
        }
        roleTable.setStart((pageNum - 1) * size);
        roleTable.setEnd(pageNum * size);
        switch (status) {
            case 3: {
                roleTable.setUnknown("1");
                break;
            }
            case 4: {
                roleTable.setActivity(new Short("1"));
                break;
            }
            case 5: {
                roleTable.setActivity(new Short("0"));
                break;
            }
            default: {
                roleTable.setActivity(null);
                break;
            }
        }
        if (StringUtil.isNotEmpty(type) && !"undefined".equals(type) && StringUtil.isNotEmpty(value) && !"undefined".equals(value)) {
            if (type.equals("1") && NumberUtils.isDigits(value)) {
                roleTable.setRole_id(new BigDecimal(value));
            }
            else if (type.equals("2")) {
                roleTable.setRolename(value);
            }
            else if (type.equals("3")) {
                roleTable.setLocalname(value);
            }
        }
        //查询总区域得玩家信息
        int total = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterNumber(roleTable);
        //总页数
        int page = total / size;
        if (total % size > 0) {
            ++page;
        }
        roleTable.setUserString(" Order By role_id ASC");
        //查询状态下的角色
        List<RoleTable> listall = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterList(roleTable);
        list = new BackRoleInfo();
        //进行状态实例化
        for (RoleTable roleInfo : listall) {
            if (org.apache.commons.lang.StringUtils.isBlank(roleInfo.getRolename())) {
                continue;
            }
            // 玩家状态1、在线 2、下线 3、禁言 4、封号5、未封号  6、未禁言
            else {
                if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                    roleInfo.setStatues("在线");
                }
                else {
                    roleInfo.setStatues("离线");
                }
                roleInfo.setUnknown(StringUtil.isEmpty(roleInfo.getUnknown()) ? "0" : roleInfo.getUnknown());
                // 清空密码，不将用户密码传到前端
                roleInfo.setPassword(null);
            }
        }
        list.setList(listall);
        list.setPages(page);
        list.setPageNum(pageNum);
        list.setTotal((long)total);
        return list;
    }


     //* 充值	类型 1仙玉充值 2周月卡充值 3小资冲级礼包充值 4土豪冲级礼包字段



    public boolean agentRechargeCallBack(Param param, Agent agent) {
        // 用户ID
        String user_id = param.getValue1();
        // 金额/VIP天数
        String recharge = param.getValue2();
        // 仙玉
        String yuanbao = param.getValue3();
        // 赠送抽奖次数
        String count = param.getValue4();
        // 充值类型
        String saveType = param.getValue5();
        if (StringUtil.isEmpty(saveType)) {
            return false;
        }
        int type = Integer.parseInt(saveType);
        if (StringUtil.isEmpty(user_id)) {
            return false;
        }
        if (StringUtil.isEmpty(yuanbao)) {
            yuanbao = "0";
        }
        BigDecimal userId = new BigDecimal(user_id);
        UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(userId);
        Random r = new Random(System.currentTimeMillis());
        ExpensesReceipts expensesReceipts = new ExpensesReceipts();
        expensesReceipts.setErid(new BigDecimal(System.currentTimeMillis() + "" + r.nextInt(9999)));
        expensesReceipts.setPlayeracc(userTable.getUsername());
        expensesReceipts.setSid(userTable.getQid());
        expensesReceipts.setRecharge(new BigDecimal(recharge));
        expensesReceipts.setYuanbao(new BigDecimal(yuanbao));
        expensesReceipts.setType(type);
        expensesReceipts.setManagerid(new BigDecimal((int)agent.getAgentId()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(11, 8);
        expensesReceipts.setPaytime(DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            ApplyBean applyBean = new ApplyBean();
            applyBean.setUserNameS(expensesReceipts.getPlayeracc());// 充值的帐户名
            applyBean.setRealmoney(expensesReceipts.getRecharge() + "");// 实际充值金额
            BigDecimal addC = new BigDecimal(applyBean.getRealmoney());
            // 支付类型 1仙玉充值 2周月卡充值 3小资冲级礼包充值 4土豪冲级礼包字段
            userTable.setPayintegration(Integer.valueOf((int)userTable.getPayintegration() + addC.intValue()));
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(applyBean.getUserNameS());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {// 在线充值
                AllServiceUtil.getUserTableService().updateUser(userTable);
                login.setPaysum(login.getPaysum().add(addC));// 累计充值
                login.setDaypaysum(login.getDaypaysum().add(addC));// 每日累计充值
                if (StringUtil.isNotEmpty(count) && !"undefined".equals(count)) {
                    LaborScene.addRankValue(0, Integer.parseInt(count) * 10, login);//劳动节活动
                }
                ApplyPayBean applyPayBean = new ApplyPayBean();
                applyPayBean.setAddM(addC);
                expensesReceipts.setRoleid(login.getRole_id());
                expensesReceipts.setBuyroleName(login.getRolename());
                RoleData roleData = RolePool.getRoleData(login.getRole_id());
                PayvipBean vipBean = GameServer.getVIP(login.getPaysum().longValue());
                if (type == 2) {
                    long time = 2592000000L;//月卡时间
                    if (time != 0L && roleData != null) {
                        UseCardBean limit = roleData.getLimit("VIP");
                        if (limit != null) {
                            limit.setTime(limit.getTime() + time);
                        }
                        else {
                            limit = new UseCardBean("VIP", "VIP", "1", System.currentTimeMillis() + time, "掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
                            roleData.addLimit(limit);
                        }
                        applyPayBean.setUseCardBean(limit);
                        applyPayBean.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天月卡特权");
                    }
                }
                else if (type == 3 && login.getLowOrHihtpack() == 0) {
                    login.setLowOrHihtpack(1);
                    applyPayBean.setLowOrHihtpack(new BigDecimal(1));
                    applyPayBean.setMsg("开通了小资冲级礼包");
                }
                else if (type == 4 && login.getLowOrHihtpack() == 0) {
                    login.setLowOrHihtpack(2);
                    applyPayBean.setLowOrHihtpack(new BigDecimal(2));
                    applyPayBean.setMsg("开通了土豪冲级礼包");
                }
                else {
                    applyBean.setPaymoney(expensesReceipts.getYuanbao() + "");// 充值的元宝数量
                    login.setCodecard(login.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                    login.setMoney(((login.getMoney() != null) ? ((int)login.getMoney()) : 0) + addC.intValue());
                    applyPayBean.setAddX(new BigDecimal(applyBean.getPaymoney()));
                    applyPayBean.setAddC(addC);
                    if (addC.longValue() >= 30L && login.getDayfirstinorno() == 0) {// 在线充值
                        // 添加连充天数
                        login.setDayandpayorno(login.getDayandpayorno().add(new BigDecimal(1)));
                        login.setDayfirstinorno(1);
                        applyPayBean.setDayandpayorno(login.getDayandpayorno());
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (type == 3 || type == 4) {
                        buffer.append("小资冲级礼包和土豪冲级礼包只能同时拥有一个,你已经有了");
                        buffer.append((login.getLowOrHihtpack() == 2) ? "土豪冲级礼包" : "小资冲级礼包");
                        buffer.append("本次充值变为正常仙玉充值.");
                    }
                    buffer.append("你充值积分:");
                    buffer.append(addC.intValue());
                    buffer.append(",获得仙玉:");
                    buffer.append(applyBean.getPaymoney());
                    applyPayBean.setMsg(buffer.toString());
                }
                AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                jedis.hset("payReturnForpayServer", expensesReceipts.getErid() + "", "Sinmahod=" + GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts) + "");
            }
            else {
                if (expensesReceipts.getRoleid() != null) {
                    login = AllServiceUtil.getRoleTableService().selectRoleID(expensesReceipts.getRoleid());
                }
                else {
                    List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(applyBean.getUserNameS(), null, null);
                    if (loginResults.size() != 0) {
                        login = (LoginResult)loginResults.get(0);
                    }
                }
                if (login != null) {
                    login.setPaysum(login.getPaysum().add(addC));
                    login.setDaypaysum(login.getDaypaysum().add(addC));
                    if (StringUtil.isNotEmpty(count) && !"undefined".equals(count)) {
                        LaborScene.addRankValue(0, Integer.parseInt(count) * 10, login);
                    }
                    expensesReceipts.setRoleid(login.getRole_id());
                    expensesReceipts.setBuyroleName(login.getRolename());
                    if (type == 2) {
                        long time2 = 3600000L * (long)expensesReceipts.getRecharge().intValue();
                        PrivateData privateData = new PrivateData();
                        privateData.setTimingGood(login.getTimingGood());
                        ConcurrentHashMap<String, UseCardBean> limitMap = privateData.initLimit(0L);
                        UseCardBean limit2 = (UseCardBean)limitMap.get("VIP");
                        if (limit2 != null) {
                            limit2.setTime(limit2.getTime() + time2);
                        }
                        else {
                            limit2 = new UseCardBean("VIP", "VIP", "1", System.currentTimeMillis() + time2, "掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
                            limitMap.put("VIP", limit2);
                        }
                        StringBuffer buffer2 = new StringBuffer();
                        for (UseCardBean cardBean : limitMap.values()) {
                            if (buffer2.length() != 0) {
                                buffer2.append("^");
                            }
                            buffer2.append(cardBean.getName());
                            buffer2.append("#");
                            buffer2.append(cardBean.getType());
                            buffer2.append("#");
                            buffer2.append(cardBean.getSkin());
                            buffer2.append("#");
                            buffer2.append(cardBean.getTime() / 60000L);
                            if (cardBean.getValue() != null && !cardBean.getValue().equals("")) {
                                buffer2.append("#");
                                buffer2.append(cardBean.getValue());
                            }
                        }
                        login.setTimingGood(buffer2.toString());
                    }
                    else if (type == 3 && login.getLowOrHihtpack() == 0) {
                        login.setLowOrHihtpack(1);
                    }
                    else if (type == 4 && login.getLowOrHihtpack() == 0) {
                        login.setLowOrHihtpack(2);
                    }
                    else {
                        applyBean.setPaymoney(expensesReceipts.getYuanbao() + "");
                        userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                        userTable.setMoney(Integer.valueOf((int)userTable.getMoney() + addC.intValue()));
                        MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                        MonitorUtil.getMoney().addC(addC.longValue());
                        if (addC.longValue() >= 30L && login.getDayfirstinorno() == 0) {
                            login.setDayandpayorno(login.getDayandpayorno().add(new BigDecimal(1)));
                            login.setDayfirstinorno(1);
                        }
                    }
                    try {
                        AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("人物数据保存报错:" + GsonUtil.getGsonUtil().getgson().toJson(login), 9999L);
                    }
                }
                else {
                    userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                    userTable.setMoney(Integer.valueOf((int)userTable.getMoney() + addC.intValue()));
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                }
                AllServiceUtil.getUserTableService().updateUser(userTable);
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            WriteOut.addtxt("充值报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
        RedisPoolUntil.returnResource(jedis);
        AllServiceUtil.getRecordService().insert(new Record(8, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts)));
        AllServiceUtil.getExpensesReceiptsService().insert1(expensesReceipts);
        return true;
    }
    
    @UserToken
    @PostMapping({ "/api/remove/battle" })
    public Result removeBattle(Param param) {
        if (BattleThreadPool.BattleDatas.values().size() > 0) {
            BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.values().stream().filter(f/* come.tool.Battle.BattleData, */ -> f.getRoleId1().equals(new BigDecimal(param.getValue1()))).findFirst().get();
            if (battleData != null) {
                BattleThreadPool.removeBattleData(battleData);
            }
        }
        return ResultFactory.success("剔除战斗成功！！！");
    }
}
