package org.come.thread;

import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.entity.Baby;
import java.util.List;

public class BabyManage implements DataBaseManage
{
    private List<Baby> addList;
    private List<BigDecimal> delList;
    private List<Baby> updList;
    
    public BabyManage() {
        this.addList = new ArrayList<>();
        this.delList = new ArrayList<>();
        this.updList = new ArrayList<>();
    }
    
    @Override
    public void add(Object object) {
        this.addList.add((Baby)object);
        if (this.addList.size() > 100) {
            try {
                AllServiceUtil.getBabyService().createBabyList(this.addList);
            }
            catch (Exception e) {
                for (int i = this.addList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().createBabySingle((Baby)this.addList.get(i));
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
                AllServiceUtil.getBabyService().deleteBabyList(this.delList);
            }
            catch (Exception e) {
                for (int i = this.delList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().deleteBabySingle((BigDecimal)this.delList.get(i));
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
        this.updList.add((Baby)object);
        if (this.updList.size() > 100) {
            try {
                AllServiceUtil.getBabyService().updateBabyList(this.updList);
            }
            catch (Exception e) {
                for (int i = this.updList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().updateBabysql((Baby)this.updList.get(i));
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
                AllServiceUtil.getBabyService().createBabyList(this.addList);
            }
            catch (Exception e) {
                for (int i = this.addList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().createBabySingle((Baby)this.addList.get(i));
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
                AllServiceUtil.getBabyService().deleteBabyList(this.delList);
            }
            catch (Exception e) {
                for (int i = this.delList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().deleteBabySingle((BigDecimal)this.delList.get(i));
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
                AllServiceUtil.getBabyService().updateBabyList(this.updList);
            }
            catch (Exception e) {
                for (int i = this.updList.size() - 1; i >= 0; --i) {
                    try {
                        AllServiceUtil.getBabyService().updateBabysql((Baby)this.updList.get(i));
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
