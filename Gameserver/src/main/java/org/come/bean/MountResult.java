package org.come.bean;

import org.come.entity.Mount;
import java.util.List;

public class MountResult
{
    private List<Mount> mounts;
    
    public List<Mount> getMounts() {
        return this.mounts;
    }
    
    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }
}
