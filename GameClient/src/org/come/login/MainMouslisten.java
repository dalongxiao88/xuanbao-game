package org.come.login;

import org.come.entity.RegionResult;
import java.util.stream.Collectors;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMouslisten implements MouseListener
{
    private Integer i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public MainMouslisten(int i, SpriteBtn btn, LoginJpanel loginJpanel) {
        this.i = Integer.valueOf(i);
        this.btn = btn;
        this.loginJpanel = loginJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.btn.btn(2);
        switch ((int)this.i) {
            case 1: {
                MainView mainView = this.loginJpanel.getMainView();
                this.loginJpanel.setMainY(Integer.valueOf(mainView.getY()));
                LoginJpanel loginJpanel = this.loginJpanel;
                int id = 1;
                List<RegionResult> regionResultList2 = this.loginJpanel.getRegionResultList();
                LoginJpanel loginJpanel2 = this.loginJpanel;
                loginJpanel.framechange(id, regionResultList2, LoginJpanel.loginInfo1);
                this.loginJpanel.setInterfaceScrolling(Boolean.valueOf(true));
                break;
            }
            case 5: {
                System.exit(1);
                break;
            }
            case 1000: {
                this.loginJpanel.getAreaView().drawSectors(this.loginJpanel.getAreaView().getRegionResultList(), this.loginJpanel);
                break;
            }
            case 1001:
            case 1002:
            case 1003:
            case 1004: {
                this.loginJpanel.getAreaView().changeBackGroundImageIcon(Integer.valueOf(Integer.parseInt(this.i.toString().substring(3))));
                List<RegionResult> regionResultList = this.loginJpanel.getAreaView().getRegionResultList();
                List<RegionResult> collect = (List)regionResultList.stream().filter(item/* org.come.entity.RegionResult, */ -> item.getOT_BELONG().toString().equals(this.i.toString())).collect(Collectors.toList());
                this.loginJpanel.getAreaView().drawSectors(collect, this.loginJpanel);
                break;
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        this.btn.btn(0);
        if ((int)this.i == 99) {
            this.loginJpanel.framechange(5, (List)null, null);
        }
        else if ((int)this.i == 100) {
            this.loginJpanel.framechange(0, (List)null, null);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(1);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(0);
        }
    }
}
