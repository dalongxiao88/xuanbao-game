package org.come.serviceImpl;

import java.util.Iterator;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.entity.SellLianghaoAucExample;
import java.util.List;
import org.come.entity.SellLianghaoAuc;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.SellLianghaoAucMapper;
import org.come.service.ISellLianghaoAucService;

public class SellLianghaoAucServiceImpl implements ISellLianghaoAucService
{
    private SellLianghaoAucMapper sellLianghaoAucMapper;
    
    public SellLianghaoAucServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.sellLianghaoAucMapper = (SellLianghaoAucMapper)ctx.getBean("sellLianghaoAucMapper", SellLianghaoAucMapper.class);
    }
    
    @Override
    public SellLianghaoAuc selectOneByID(BigDecimal id) {
        SellLianghaoAuc sellxianyuorder = this.sellLianghaoAucMapper.selectByPrimaryKey(id);
        return sellxianyuorder;
    }
    
    @Override
    public int insertOrder(SellLianghaoAuc sellLianghaoAuc) {
        return this.sellLianghaoAucMapper.insert(sellLianghaoAuc);
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByToday(String todayStr, Short status) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andAucEndTimeEqualTo(todayStr).andStatusEqualTo(status);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByDateAndLhAndStatus(String dateStr, String liangHao, Short status) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andAucEndTimeEqualTo(dateStr).andLianghaoEqualTo(liangHao).andStatusEqualTo(status);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByDateAndRoleIdLhAndStatus(BigDecimal roleId, String dateStr, String liangHao, Short status) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andBuyRoleIdEqualTo(roleId).andAucEndTimeEqualTo(dateStr).andLianghaoEqualTo(liangHao).andStatusEqualTo(status);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByStatus(Short status) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andStatusEqualTo(status);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public int updateStauts(SellLianghaoAuc sellLianghaoAuc) {
        return this.sellLianghaoAucMapper.updateByPrimaryKeySelective(sellLianghaoAuc);
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByRoleIdAndStatus(BigDecimal roleId, Short status) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andBuyRoleIdEqualTo(roleId).andStatusEqualTo(status);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public void calSelfBuyLh(ChannelHandlerContext ctx) {
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        List<SellLianghaoAuc> cells = this.selectAllByRoleIdAndStatus(login.getRole_id(), Short.valueOf((short)5));
        if (cells != null && cells.size() > 0) {
            for (SellLianghaoAuc item : cells) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.LHRETURN);
                assetUpdate.updata("C=" + item.getAucPoint().longValue());
                MonitorUtil.getMoney().addC(item.getAucPoint().longValue());
                login.setMoney(Integer.valueOf(((login.getMoney() != null) ? ((int)login.getMoney()) : 0) + item.getAucPoint().intValue()));
                assetUpdate.setMsg("你竞价靓号：" + item.getLianghao() + "失败，已经退回" + item.getAucPoint() + "积分");
                SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SellLianghaoAuc updateStatus = new SellLianghaoAuc();
                updateStatus.setId(item.getId());
                updateStatus.setStatus(Short.valueOf((short)3));
                AllServiceUtil.getSellLianghaoAucService().updateStauts(updateStatus);
            }
        }
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByRoleId(BigDecimal roleId) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andBuyRoleIdEqualTo(roleId);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
    
    @Override
    public List<SellLianghaoAuc> selectByPrice(SellLianghaoAuc param) {
        return this.sellLianghaoAucMapper.selectByPrice(param);
    }
    
    @Override
    public void updateByPrimaryKeySelective(SellLianghaoAuc sellLianghaoAuc) {
        this.sellLianghaoAucMapper.updateByPrimaryKeySelective(sellLianghaoAuc);
    }
    
    @Override
    public void deleteByPrimaryKey(BigDecimal id) {
        this.sellLianghaoAucMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<SellLianghaoAuc> selectAllByDateAndRoleIdLh(BigDecimal roleId, String dateStr, String liangHao) {
        SellLianghaoAucExample sellLianghaoAucExample = new SellLianghaoAucExample();
        sellLianghaoAucExample.createCriteria().andBuyRoleIdEqualTo(roleId).andAucEndTimeEqualTo(dateStr).andLianghaoEqualTo(liangHao);
        List<SellLianghaoAuc> result = this.sellLianghaoAucMapper.selectByExample(sellLianghaoAucExample);
        return result;
    }
}
