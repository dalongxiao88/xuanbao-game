package org.come.login;

import java.awt.event.MouseEvent;
import com.tool.btn.MoBanBtn;

public class AreaTypeMousisten extends MoBanBtn
{
    private final String areaType;
    private final AreaView areaView;
    private final LoginJpanel loginJpanel;
    
    public AreaTypeMousisten(String iconpath, int type, String areaType, LoginJpanel loginJpanel, AreaView areaView) {
        super(iconpath, type);
        this.areaType = areaType;
        this.areaView = areaView;
        this.loginJpanel = loginJpanel;
    }
    
    @Override
    public void chooseyes() {
        try {
            AreaTypeMousisten[] btnTypes = this.areaView.getBtnTypes();
            for (int i = 0; i < btnTypes.length; ++i) {
                if (btnTypes[i] == null) {
                    return;
                }
                btnTypes[i].qvxiao(this.areaType);
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
    }
    
    public void qvxiao(String areaType) {
        if (!this.areaType.equals(areaType)) {
            this.btnchange(0);
        }
    }
}
