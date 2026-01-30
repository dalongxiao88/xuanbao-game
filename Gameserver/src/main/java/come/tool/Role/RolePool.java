package come.tool.Role;

import java.util.Iterator;
import java.util.Map;
import come.tool.Calculation.CalculationUtil;
import come.tool.FightingData.ManData;
import org.come.until.AllServiceUtil;
import org.come.entity.Fly;
import org.come.entity.Mount;
import org.come.entity.Baby;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class RolePool
{
    private static Object LOCK;
    private static ConcurrentHashMap<BigDecimal, RoleData> allRoles;
    private static ConcurrentHashMap<BigDecimal, RoleData> allLineRoles;
    
    public static RoleData addRoleData(LoginResult loginResult, List<Goodstable> goods, List<RoleSummoning> pets, List<Lingbao> lingbaos, List<Baby> babys, List<Mount> mounts, List<Fly> flys) {
        RoleData roleData = new RoleData(loginResult, goods, pets, lingbaos, babys, mounts, flys);
        synchronized (RolePool.LOCK) {
            RolePool.allRoles.put(loginResult.getRole_id(), roleData);
            RolePool.allLineRoles.remove(loginResult.getRole_id());
        }
        return roleData;
    }
    
    public static RoleData getRoleData(BigDecimal role_id) {
        return (RoleData)RolePool.allRoles.get(role_id);
    }
    
    public static LoginResult getLoginResult(BigDecimal role_id) {
        RoleData roleData = (RoleData)RolePool.allRoles.get(role_id);
        if (roleData == null) {
            return null;
        }
        return roleData.getLoginResult();
    }
    
    public static RoleData deleteRoleData(BigDecimal role_id) {
        RoleData roleData = null;
        synchronized (RolePool.LOCK) {
            roleData = (RoleData)RolePool.allRoles.remove(role_id);
            if (roleData != null) {
                roleData.setLine(0);
                RolePool.allLineRoles.put(role_id, roleData);
            }
        }
        return roleData;
    }
    
    public static RoleData getLineRoleData(BigDecimal role_id) {
        boolean is = false;
        RoleData roleData = null;
        synchronized (RolePool.LOCK) {
            roleData = getRoleData(role_id);
            is = (roleData == null);
            if (is) {
                roleData = (RoleData)RolePool.allLineRoles.get(role_id);
            }
        }
        if (roleData == null) {
            LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleByRoleId(role_id);
            if (roleInfo != null) {
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(roleInfo.getRole_id());
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleInfo.getRole_id());
                List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(roleInfo.getRole_id());
                List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(roleInfo.getRole_id());
                List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleInfo.getRole_id());
                List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(roleInfo.getRole_id());
                roleData = new RoleData(roleInfo, goods, pets, lingbaos, babys, mounts, flys);
                RolePool.allLineRoles.put(role_id, roleData);
            }
        }
        if (is && roleData != null) {
            roleData.setLine(0);
        }
        return roleData;
    }
    
    public static synchronized CBGData getLineCBGRoleData(BigDecimal role_id) {
        CBGData cbgData = null;
        RoleData roleData = null;
        boolean is = false;
        synchronized (RolePool.LOCK) {
            roleData = getRoleData(role_id);
            is = (roleData == null);
            if (is) {
                roleData = (RoleData)RolePool.allLineRoles.get(role_id);
            }
        }
        if (roleData == null) {
            LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleByRoleId(role_id);
            if (roleInfo != null) {
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(roleInfo.getRole_id());
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleInfo.getRole_id());
                List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(roleInfo.getRole_id());
                List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(roleInfo.getRole_id());
                List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleInfo.getRole_id());
                List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(roleInfo.getRole_id());
                roleData = new RoleData(roleInfo, goods, pets, lingbaos, babys, mounts, flys);
                cbgData = new CBGData(roleInfo, goods, pets, mounts, lingbaos, babys);
                roleData.setCbgData(cbgData);
                RolePool.allLineRoles.put(role_id, roleData);
            }
        }
        if (is && roleData != null) {
            roleData.setLine(0);
        }
        if (roleData == null) {
            return null;
        }
        cbgData = roleData.getCbgData();
        if (cbgData == null) {
            LoginResult roleInfo = roleData.getLoginResult();
            List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(roleInfo.getRole_id());
            List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleInfo.getRole_id());
            List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(roleInfo.getRole_id());
            List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(roleInfo.getRole_id());
            List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleInfo.getRole_id());
            cbgData = new CBGData(roleInfo, goods, pets, mounts, lingbaos, babys);
            roleData.setCbgData(cbgData);
        }
        if (cbgData.getData2() == null) {
            ManData manData = new ManData(1, 0);
            CalculationUtil.loadRoleBattle(manData, roleData.getLoginResult(), roleData, null, null, null, null, null);
            GBGData2 gbgData2 = new GBGData2(manData);
            cbgData.setData2(gbgData2);
        }
        return cbgData;
    }
    
    public static void checkRoleData() {
        Iterator<Map.Entry<BigDecimal, RoleData>> entries = RolePool.allLineRoles.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<BigDecimal, RoleData> entrys = (Map.Entry<BigDecimal, RoleData>)entries.next();
            RoleData roleData = (RoleData)entrys.getValue();
            if (roleData.upLine()) {
                entries.remove();
            }
        }
    }
    
    static {
        RolePool.LOCK = new Object();
        RolePool.allRoles = new ConcurrentHashMap<>();
        RolePool.allLineRoles = new ConcurrentHashMap<>();
    }
}
