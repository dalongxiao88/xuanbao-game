package come.tool.FightingData;

import java.util.ArrayList;
import org.come.model.Lingbao;
import java.util.List;
import org.come.entity.RoleSummoning;
import org.come.bean.LoginResult;

public class FightingEndD
{
    private LoginResult loginResult;
    private RoleSummoning pet;
    private int Fightingsumber;
    private List<Lingbao> lingbaos;
    private String mData;
    private int type;
    private Integer doorId;
    
    public String getmData() {
        return this.mData;
    }
    
    public void updatamData(String data) {
        if (this.mData == null) {
            this.mData = data;
        }
        else {
            this.mData = this.mData + "%" + data;
        }
    }
    
    public void setmData(String mData) {
        this.mData = mData;
    }
    
    public void addLingbao(Lingbao lingbao) {
        this.getLingbaos().add(lingbao);
    }
    
    public List<Lingbao> getLingbaos() {
        if (this.lingbaos == null) {
            this.lingbaos = new ArrayList<>();
        }
        return this.lingbaos;
    }
    
    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }
    
    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
    
    public RoleSummoning getPet() {
        return this.pet;
    }
    
    public void setPet(RoleSummoning pet) {
        this.pet = pet;
    }
    
    public int getFightingsumber() {
        return this.Fightingsumber;
    }
    
    public void setFightingsumber(int fightingsumber) {
        this.Fightingsumber = fightingsumber;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public Integer getDoorId() {
        return this.doorId;
    }
    
    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }
}
