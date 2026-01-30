package org.come.Jpanel;

import org.come.model.Lingbao;
import com.tool.role.RoleLingFa;
import org.come.entity.RoleSummoning;
import java.util.List;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.Music;
import org.come.until.FormsManagement;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.awt.CardLayout;
import com.tool.btn.HandoffBtn;
import javax.swing.JPanel;

public class SummonJpanel extends JPanel
{
    public static HandoffBtn[] handoffBtns1;
    public static HandoffBtn[] handoffBtns2;
    private CardLayout cardLayout;
    private SummonPetJpanel summonPetJpanel;
    private SummonBaoJpanel summonBaoJpanel;
    
    public SummonJpanel() throws Exception {
        this.setPreferredSize(new Dimension(470, 510));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        SummonJpanel.handoffBtns1 = new HandoffBtn[2];
        SummonJpanel.handoffBtns2 = new HandoffBtn[2];
        if (MyIsif.getStyle().equals("水墨")) {
            SummonJpanel.handoffBtns1[0] = new HandoffBtn("inkImg/button1/K1011.png", "pet", 1, this);
            SummonJpanel.handoffBtns2[0] = new HandoffBtn("inkImg/button1/K1010.png", "pet", 1, this);
            SummonJpanel.handoffBtns1[0].setBounds(44, 20, 95, 33);
            SummonJpanel.handoffBtns2[0].setBounds(44, 20, 95, 33);
            SummonJpanel.handoffBtns1[1] = new HandoffBtn("inkImg/button1/K1012.png", "bao", 1, this);
            SummonJpanel.handoffBtns2[1] = new HandoffBtn("inkImg/button1/K1013.png", "bao", 1, this);
            SummonJpanel.handoffBtns1[1].setBounds(149, 20, 95, 33);
            SummonJpanel.handoffBtns2[1].setBounds(149, 20, 95, 33);
        }
        else {
            SummonJpanel.handoffBtns1[0] = new HandoffBtn("inkImg/hongmu1/K1011.png", "pet", 1, this);
            SummonJpanel.handoffBtns2[0] = new HandoffBtn("inkImg/hongmu1/K1010.png", "pet", 1, this);
            SummonJpanel.handoffBtns1[0].setBounds(20, 29, 100, 38);
            SummonJpanel.handoffBtns2[0].setBounds(20, 29, 100, 38);
            SummonJpanel.handoffBtns1[1] = new HandoffBtn("inkImg/hongmu1/K1012.png", "bao", 1, this);
            SummonJpanel.handoffBtns2[1] = new HandoffBtn("inkImg/hongmu1/K1013.png", "bao", 1, this);
            SummonJpanel.handoffBtns1[1].setBounds(126, 29, 100, 38);
            SummonJpanel.handoffBtns2[1].setBounds(126, 29, 100, 38);
        }
        this.setLayout(this.cardLayout = new CardLayout());
        this.add(this.summonPetJpanel = new SummonPetJpanel(), "pet");
        this.add(this.summonBaoJpanel = new SummonBaoJpanel(), "bao");
        this.summonPetJpanel.add(SummonJpanel.handoffBtns1[0]);
        this.summonPetJpanel.add(SummonJpanel.handoffBtns1[1]);
        this.summonBaoJpanel.add(SummonJpanel.handoffBtns2[0]);
        this.summonBaoJpanel.add(SummonJpanel.handoffBtns2[1]);
    }
    
    public void show(String name) {
        this.cardLayout.show(this, name);
    }
    
    public void updateInfo() {
        this.updatePetInfo();
        this.updateBaoInfo();
        if (!FormsManagement.getframe(710).isVisible()) {
            FormsManagement.showForm(710);
            Music.addyinxiao("开关窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(710);
        }
    }
    
    public void updatePetInfo() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int ndsl = 4;
        if (configure.getNdslsx() != null) {
            ndsl = Integer.parseInt(configure.getNdslsx());
        }
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        this.summonPetJpanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
    }
    
    public void updateBaoInfo() {
        Lingbao[] lingBaos = RoleLingFa.getRoleLingFa().getLingBaos();
        this.summonBaoJpanel.showListModel(lingBaos, RoleLingFa.getRoleLingFa().getEquipLingBaoId());
    }
}
