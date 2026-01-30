package org.come.thread;

import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import java.util.List;

public class PetManage implements DataBaseManage
{
    private List<RoleSummoning> addList;
    private List<BigDecimal> delList;
    private List<RoleSummoning> updList;
    
    public PetManage() {
        this.addList = new ArrayList<>();
        this.delList = new ArrayList<>();
        this.updList = new ArrayList<>();
    }
    
    @Override
    public void add(Object object) {
        this.addList.add((RoleSummoning)object);
        if (this.addList.size() > 100) {
            try {
                AllServiceUtil.getRoleSummoningService().insertRoleSummoningList(this.addList);
            }
            catch (Exception e) {
                for (int i = this.addList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().insertRoleSummoningSingle((RoleSummoning)this.addList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.addList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.addList.get(i)), 999L);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            this.addList.clear();
        }
    }
    
    @Override
    public void del(Object object) {
        this.delList.add((BigDecimal)object);
        if (this.delList.size() > 100) {
            try {
                AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidList(this.delList);
            }
            catch (Exception e) {
                for (int i = this.delList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidsql((BigDecimal)this.delList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.delList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.delList.get(i)), 999L);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            this.delList.clear();
        }
    }
    
    @Override
    public void upd(Object object) {
        this.updList.add((RoleSummoning)object);
        if (this.updList.size() > 100) {
            try {
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningList(this.updList);
            }
            catch (Exception e) {
                for (int i = this.updList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().updateRoleSummoningsql((RoleSummoning)this.updList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.updList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.updList.get(i)), 999L);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            this.updList.clear();
        }
    }
    
    @Override
    public void ClearList() {
        if (this.addList.size() != 0) {
            try {
                AllServiceUtil.getRoleSummoningService().insertRoleSummoningList(this.addList);
            }
            catch (Exception e) {
                for (int i = this.addList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().insertRoleSummoningSingle((RoleSummoning)this.addList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.addList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.addList.get(i)), 999L);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            this.addList.clear();
        }
        if (this.delList.size() != 0) {
            try {
                AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidList(this.delList);
            }
            catch (Exception e) {
                for (int i = this.delList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidsql((BigDecimal)this.delList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.delList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.delList.get(i)), 999L);
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            this.delList.clear();
        }
        if (this.updList.size() != 0) {
            try {
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningList(this.updList);
            }
            catch (Exception e) {
                for (int i = this.updList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getRoleSummoningService().updateRoleSummoningsql((RoleSummoning)this.updList.get(i));
                    }
                    catch (Exception e2) {
                        try {
                            System.out.println("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.updList.get(i)));
                            WriteOut.addtxt("同步数据库物品属性:" + GsonUtil.getGsonUtil().getgson().toJson(this.updList.get(i)), 999L);
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            this.updList.clear();
        }
    }
}
