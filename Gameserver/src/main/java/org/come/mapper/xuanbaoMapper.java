

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

    void insertLingbao(XuanBao xuanBao);

    List<XuanBao> selectLingbaoByRoleID(BigDecimal roleId);

    void updateLingbao(XuanBao xuanBao);

    void deleteLingbao(BigDecimal xuanBaoId);

    BigDecimal selectMaxID();

    List<LingbaoRoleUser> selectLingBaoRU(@Param("lru") LingbaoRoleUser lingbaoRoleUser);

    Integer selectLingBaoRUCount(@Param("lru") LingbaoRoleUser lingbaoRoleUser);

    void deleteLingbaoList(List<BigDecimal> xuanBaoIds);

    void insertLingbaoList(List<XuanBao> xuanBaoList);

    void updateLingbaoList(List<XuanBao> xuanBaoList);
}
