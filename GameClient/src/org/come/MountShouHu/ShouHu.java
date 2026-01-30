package org.come.MountShouHu;

import com.tool.role.RoleData;

public class ShouHu
{
    public int zhongtianlvl;
    public int zhongtianxiuweidian;
    public String zhongtianmount;
    public int qinglonglvl;
    public int qinglongxiuweidian;
    public int qinglongmount;
    public int baihulvl;
    public int baihuxiuweidian;
    public int baihumount;
    public int xuanwulvl;
    public int xuanwuxiuweidian;
    public int xuanwumount;
    public int zhuquelvl;
    public int zhuquexiuweidian;
    public int zhuquemount;
    public int shouhu;
    public boolean is;
    
    public ShouHu() {
        this.zhongtianlvl = -1;
        this.zhongtianxiuweidian = -1;
        this.zhongtianmount = "-1*-1";
        this.qinglonglvl = -1;
        this.qinglongxiuweidian = -1;
        this.qinglongmount = -1;
        this.baihulvl = -1;
        this.baihuxiuweidian = -1;
        this.baihumount = -1;
        this.xuanwulvl = -1;
        this.xuanwuxiuweidian = -1;
        this.xuanwumount = -1;
        this.zhuquelvl = -1;
        this.zhuquexiuweidian = -1;
        this.zhuquemount = -1;
        this.shouhu = -1;
        this.is = false;
    }
    
    public int getZhongtianlvl() {
        return this.zhongtianlvl;
    }
    
    public void setZhongtianlvl(int zhongtianlvl) {
        this.zhongtianlvl = zhongtianlvl;
    }
    
    public int getZhongtianxiuweidian() {
        return this.zhongtianxiuweidian;
    }
    
    public void setZhongtianxiuweidian(int zhongtianxiuweidian) {
        this.zhongtianxiuweidian = zhongtianxiuweidian;
    }
    
    public String getZhongtianmount() {
        if (RoleData.getRoleData().getLoginResult().getZhongtian() != null) {
            this.zhongtianmount = RoleData.getRoleData().getLoginResult().getZhongtian().split("\\|")[2];
        }
        return this.zhongtianmount;
    }
    
    public void setZhongtianmount(String zhongtianmount) {
        this.zhongtianmount = zhongtianmount;
    }
    
    public int getQinglonglvl() {
        return this.qinglonglvl;
    }
    
    public void setQinglonglvl(int qinglonglvl) {
        this.qinglonglvl = qinglonglvl;
    }
    
    public int getQinglongxiuweidian() {
        return this.qinglongxiuweidian;
    }
    
    public void setQinglongxiuweidian(int qinglongxiuweidian) {
        this.qinglongxiuweidian = qinglongxiuweidian;
    }
    
    public int getQinglongmount() {
        return this.qinglongmount;
    }
    
    public void setQinglongmount(int qinglongmount) {
        this.qinglongmount = qinglongmount;
    }
    
    public int getBaihulvl() {
        return this.baihulvl;
    }
    
    public void setBaihulvl(int baihulvl) {
        this.baihulvl = baihulvl;
    }
    
    public int getBaihuxiuweidian() {
        return this.baihuxiuweidian;
    }
    
    public void setBaihuxiuweidian(int baihuxiuweidian) {
        this.baihuxiuweidian = baihuxiuweidian;
    }
    
    public int getBaihumount() {
        return this.baihumount;
    }
    
    public void setBaihumount(int baihumount) {
        this.baihumount = baihumount;
    }
    
    public int getXuanwulvl() {
        return this.xuanwulvl;
    }
    
    public void setXuanwulvl(int xuanwulvl) {
        this.xuanwulvl = xuanwulvl;
    }
    
    public int getXuanwuxiuweidian() {
        return this.xuanwuxiuweidian;
    }
    
    public void setXuanwuxiuweidian(int xuanwuxiuweidian) {
        this.xuanwuxiuweidian = xuanwuxiuweidian;
    }
    
    public int getXuanwumount() {
        return this.xuanwumount;
    }
    
    public void setXuanwumount(int xuanwumount) {
        this.xuanwumount = xuanwumount;
    }
    
    public int getZhuquelvl() {
        return this.zhuquelvl;
    }
    
    public void setZhuquelvl(int zhuquelvl) {
        this.zhuquelvl = zhuquelvl;
    }
    
    public int getZhuquexiuweidian() {
        return this.zhuquexiuweidian;
    }
    
    public void setZhuquexiuweidian(int zhuquexiuweidian) {
        this.zhuquexiuweidian = zhuquexiuweidian;
    }
    
    public int getZhuquemount() {
        return this.zhuquemount;
    }
    
    public void setZhuquemount(int zhuquemount) {
        this.zhuquemount = zhuquemount;
    }
    
    public int getShouhu() {
        return this.shouhu;
    }
    
    public void setShouhu(int shouhu) {
        this.shouhu = shouhu;
    }
}
