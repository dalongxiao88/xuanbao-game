package come.tool.Transplant;

import org.come.entity.Titletable;
import org.come.entity.Baby;
import org.come.entity.Lingbao;
import org.come.entity.Fly;
import org.come.entity.Mount;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.entity.PackRecord;
import org.come.bean.LoginResult;

public class RoleDataBean
{
    private LoginResult loginResult;
    private PackRecord packRecord;
    private List<Goodstable> goodstables;
    private List<RoleSummoning> pets;
    private List<Mount> mounts;
    private List<Fly> flys;
    private List<Lingbao> lingbaos;
    private List<Baby> babys;
    private List<Titletable> titletables;
    
    public RoleDataBean(LoginResult loginResult, PackRecord packRecord, List<Goodstable> goodstables, List<RoleSummoning> pets, List<Mount> mounts, List<Lingbao> lingbaos, List<Baby> babys, List<Titletable> titletables, List<Fly> flys) {
        this.loginResult = loginResult;
        this.packRecord = packRecord;
        this.goodstables = goodstables;
        this.pets = pets;
        this.mounts = mounts;
        this.flys = flys;
        this.lingbaos = lingbaos;
        this.babys = babys;
        this.titletables = titletables;
    }
    
    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
    
    public PackRecord getPackRecord() {
        return this.packRecord;
    }
    
    public void setPackRecord(PackRecord packRecord) {
        this.packRecord = packRecord;
    }
    
    public List<Goodstable> getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(List<Goodstable> goodstables) {
        this.goodstables = goodstables;
    }
    
    public List<RoleSummoning> getPets() {
        return this.pets;
    }
    
    public void setPets(List<RoleSummoning> pets) {
        this.pets = pets;
    }
    
    public List<Mount> getMounts() {
        return this.mounts;
    }
    
    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }
    
    public List<Lingbao> getLingbaos() {
        return this.lingbaos;
    }
    
    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }
    
    public List<Baby> getBabys() {
        return this.babys;
    }
    
    public void setBabys(List<Baby> babys) {
        this.babys = babys;
    }
    
    public List<Titletable> getTitletables() {
        return this.titletables;
    }
    
    public void setTitletables(List<Titletable> titletables) {
        this.titletables = titletables;
    }
    
    public List<Fly> getFlys() {
        return this.flys;
    }
    
    public void setFlys(List<Fly> flys) {
        this.flys = flys;
    }
}
