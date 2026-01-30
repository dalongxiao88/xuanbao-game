

package org.come.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;
import org.come.entity.LingbaoRoleUser;
import org.come.entity.XuanBao;

@MyBatisAnnotation
public interface xuanbaoMapper {
    List<XuanBao> selectAllLingbao();

    void insertLingbao(XuanBao var1);

    List<XuanBao> selectLingbaoByRoleID(BigDecimal var1);

    void updateLingbao(XuanBao var1);

    void deleteLingbao(BigDecimal var1);

    BigDecimal selectMaxID();

    List<LingbaoRoleUser> selectLingBaoRU(@Param("lru") LingbaoRoleUser var1);

    Integer selectLingBaoRUCount(@Param("lru") LingbaoRoleUser var1);

    void deleteLingbaoList(List<BigDecimal> var1);

    void insertLingbaoList(List<XuanBao> var1);

    void updateLingbaoList(List<XuanBao> var1);
}
