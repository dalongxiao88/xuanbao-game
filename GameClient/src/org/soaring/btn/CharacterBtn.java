package org.soaring.btn;

import org.come.Frame.Teststatejframe;
import org.come.bean.LoginResult;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.mouslisten.SystemMouslisten;
import org.come.Frame.RoleBornJframe;
import org.soaring.panel.SoaringMainFrame;
import com.tool.role.RoleData;
import org.come.Frame.RoleToggleJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class CharacterBtn extends MoBanBtn
{
    private int pang;
    
    public CharacterBtn(String iconpath, int type, int pang) {
        super(iconpath, type);
        this.pang = pang;
    }
    
    public CharacterBtn(String iconpath, int type, Color[] colors, String text, int pang) {
        super(iconpath, type, colors);
        this.pang = pang;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT2);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    public CharacterBtn(String iconpath, int type, Color[] colors, String text, int pang, String q) {
        super(iconpath, type, colors);
        this.pang = pang;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.pang) {
            case 2: {
                FormsManagement.showForm(640);
                break;
            }
            case 3: {
                String sendmes = Agreement.getAgreement().TitleListAgreement();
                SendMessageUntil.toServer(sendmes);
                break;
            }
            case 4: {
                RoleToggleJframe.getRoleToggleJframe().getRoleToggleJpanel().getplayerValue();
                FormsManagement.showForm(123);
                this.ShowBtns();
                break;
            }
            case 5: {
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                SoaringMainFrame.getSoaringMainFrame().getSoaringPanel().SoaringExprience((int)loginResult.getGrade(), loginResult.getExperience(), loginResult.getExtraPointInt("F"), (int)loginResult.getXiuwei());
                FormsManagement.showForm(81);
                break;
            }
            case 6: {
                FormsManagement.showForm(1234);
                RoleBornJframe.getRoleToggleJframe().getRoleBornJpanel().showStar();
                this.ShowBtns();
                break;
            }
            case 7: {
                this.ShowBtns();
                break;
            }
            case 8: {
                SystemMouslisten.type37();
                SystemMouslisten.writeTxt();
                PetAddPointMouslisten.getplayerValue();
                break;
            }
        }
    }
    
    public void ShowBtns() {
        CharacterBtn a1 = Teststatejframe.getTeststatejframe().getTeststateJpanel().getAttributeBtn2();
        CharacterBtn a2 = Teststatejframe.getTeststatejframe().getTeststateJpanel().getAttributeBtn1();
        if (a1.isVisible()) {
            a1.setVisible(false);
            a2.setVisible(false);
        }
        else {
            a1.setVisible(true);
            a2.setVisible(true);
        }
    }
}
