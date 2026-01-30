
package org.come.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.LingbaoRoleUser;
import org.come.entity.XuanBao;
import org.come.mapper.xuanbaoMapper;
import org.come.redis.RedisCacheUtil;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.service.IXuanBaoService;
import org.come.until.MybatisUntil;
import org.springframework.context.ApplicationContext;

public class XuanbaoServiceImpl implements IXuanBaoService {
    private xuanbaoMapper lingbaoMapper;

    public XuanbaoServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.lingbaoMapper = (xuanbaoMapper)ctx.getBean("xuanbaoMapper");
    }

    public List<XuanBao> selectAllLingbao() {
        return this.lingbaoMapper.selectAllLingbao();
    }

    public void insertLingbao(XuanBao lingbao) {
        lingbao.setBid(RedisCacheUtil.getxuanbao_pk());
        RedisControl.insertKeyT(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), lingbao);
        RedisControl.insertListRedis(RedisParameterUtil.XUANBAO, lingbao.getRoleid().toString(), lingbao.getBid().toString());
        RedisControl.insertController(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), "1");
    }

    public List<XuanBao> selectLingbaoByRoleID(BigDecimal roleid) {
        List<XuanBao> lingbaos = RedisControl.getS(RedisParameterUtil.XUANBAO, roleid.toString(), XuanBao.class);
        return lingbaos;
    }

    public XuanBao selectLingbaoByID(BigDecimal baoid) {
        XuanBao lingbao = (XuanBao)RedisControl.getV(RedisParameterUtil.XUANBAO, baoid.toString(), XuanBao.class);
        return lingbao;
    }

    public void updateLingbaoIndex(XuanBao lingbao, BigDecimal role_id) {
        BigDecimal yrid = lingbao.getRoleid();
        lingbao.setRoleid(role_id);
        if (yrid != null && role_id != null && yrid.compareTo(role_id) != 0) {
            RedisControl.deletrValue(RedisParameterUtil.XUANBAO, yrid.toString(), lingbao.getBid().toString());
            RedisControl.insertListRedis(RedisParameterUtil.XUANBAO, role_id.toString(), lingbao.getBid().toString());
        }

        RedisControl.insertKeyT(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), lingbao);
        RedisControl.insertController(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), "2");
    }

    public void updateLingbaoRedis(XuanBao lingbao) {
        RedisControl.insertKeyT(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), lingbao);
        RedisControl.insertController(RedisParameterUtil.XUANBAO, lingbao.getBid().toString(), "2");
    }

    public void deleteLingbao(BigDecimal baoid) {
        XuanBao lingbao = (XuanBao)RedisControl.getV(RedisParameterUtil.XUANBAO, baoid.toString(), XuanBao.class);
        if (lingbao != null) {
            RedisControl.deletrValue(RedisParameterUtil.XUANBAO, lingbao.getRoleid().toString(), baoid.toString());
        }

        RedisControl.delForKey(RedisParameterUtil.XUANBAO, baoid.toString());
        RedisControl.insertController(RedisParameterUtil.XUANBAO, baoid.toString(), "3");
    }

    public BigDecimal selectMaxID() {
        return this.lingbaoMapper.selectMaxID();
    }

    public void updateLingbaosql(XuanBao lingbao) {
        this.lingbaoMapper.updateLingbao(lingbao);
    }

    public void deleteLingbaosql(BigDecimal baoid) {
        this.lingbaoMapper.deleteLingbao(baoid);
    }

    public void insertLingbaosql(XuanBao lingbao) {
        this.lingbaoMapper.insertLingbao(lingbao);
    }

    public List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser lru) {
        return null;
    }

    public Integer selectLingBaoRUCount(LingbaoRoleUser lru) {
        return 0;
    }

    public void deleteLingbaoList(List<BigDecimal> list) {
    }

    public void insertLingbaoList(List<XuanBao> list) {
    }

    public void updateLingbaoList(List<XuanBao> list) {
    }
}
