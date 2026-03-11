package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.Pal;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;
/**
 * PalMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

@MyBatisAnnotation
public interface PalMapper
{
    List<Pal> selectAllPal();
    
    List<Pal> selectPalByRoleID(BigDecimal roleId);
    
    void deletePal(BigDecimal palId);
    
    void updatePal(Pal pal);
    
    void insertPal(Pal pal);
    
    void deletePalList(List<BigDecimal> palIds);
    
    void updatePalList(List<Pal> palList);
    
    void insertPalList(List<Pal> palList);
}

