

package org.come.service;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.LingbaoRoleUser;
import org.come.entity.XuanBao;

public interface IXuanBaoService {
    List<XuanBao> selectAllLingbao();

    void insertLingbao(XuanBao xuanBao);

    List<XuanBao> selectLingbaoByRoleID(BigDecimal roleId);

    XuanBao selectLingbaoByID(BigDecimal xuanBaoId);

    void updateLingbaoIndex(XuanBao xuanBao, BigDecimal roleId);

    void updateLingbaoRedis(XuanBao xuanBao);

    void deleteLingbao(BigDecimal xuanBaoId);

    BigDecimal selectMaxID();

    void updateLingbaosql(XuanBao xuanBao);

    void deleteLingbaosql(BigDecimal xuanBaoId);

    void insertLingbaosql(XuanBao xuanBao);

    List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser lingbaoRoleUser);

    Integer selectLingBaoRUCount(LingbaoRoleUser lingbaoRoleUser);

    void deleteLingbaoList(List<BigDecimal> xuanBaoIds);

    void insertLingbaoList(List<XuanBao> xuanBaoList);

    void updateLingbaoList(List<XuanBao> xuanBaoList);
}
