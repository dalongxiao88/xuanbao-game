

package org.come.service;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.LingbaoRoleUser;
import org.come.entity.XuanBao;

public interface IXuanBaoService {
    List<XuanBao> selectAllLingbao();

    void insertLingbao(XuanBao var1);

    List<XuanBao> selectLingbaoByRoleID(BigDecimal var1);

    XuanBao selectLingbaoByID(BigDecimal var1);

    void updateLingbaoIndex(XuanBao var1, BigDecimal var2);

    void updateLingbaoRedis(XuanBao var1);

    void deleteLingbao(BigDecimal var1);

    BigDecimal selectMaxID();

    void updateLingbaosql(XuanBao var1);

    void deleteLingbaosql(BigDecimal var1);

    void insertLingbaosql(XuanBao var1);

    List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser var1);

    Integer selectLingBaoRUCount(LingbaoRoleUser var1);

    void deleteLingbaoList(List<BigDecimal> var1);

    void insertLingbaoList(List<XuanBao> var1);

    void updateLingbaoList(List<XuanBao> var1);
}
