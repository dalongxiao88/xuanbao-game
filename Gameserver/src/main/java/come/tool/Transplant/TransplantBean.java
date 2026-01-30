package come.tool.Transplant;

import java.util.List;

public class TransplantBean
{
    private List<UserDataBean> list;
    
    public TransplantBean(List<UserDataBean> list) {
        this.list = list;
    }
    
    public List<UserDataBean> getList() {
        return this.list;
    }
    
    public void setList(List<UserDataBean> list) {
        this.list = list;
    }
}
