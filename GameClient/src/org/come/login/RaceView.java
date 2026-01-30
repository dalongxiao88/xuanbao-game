package org.come.login;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import org.come.view.View;
//角色注册界面
public class RaceView extends View
{
    private final SpriteBtn fanghui;
    private final ImageIcon yuxuan;
    private final SpriteBtn[] leis;
    private SpriteBtn leitext;
    
    public RaceView(LoginJpanel loginJpanel) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        String lzkg = configure.getLzjskg();
        this.setBounds(0, 0, 1027, 720);
        (this.fanghui = new SpriteBtn("resource/xinUI/xin/返回按钮", 10, 680, false)).setBounds(10, 680, 60, 30);
        this.fanghui.addMouseListener(new RoleMouslisten(11, this.fanghui, loginJpanel));
        this.add(this.fanghui);
        this.yuxuan = new ImageIcon("resource/xinUI/xin/创建角色预选背景.png");
        if (lzkg.equals("5")) {
            this.leis = new SpriteBtn[5];
        }
        else if (lzkg.equals("4")) {
            this.leis = new SpriteBtn[4];
        }
        else {
            this.leis = new SpriteBtn[3];
        }
        for (int i = this.leis.length - 1; i >= 0; --i) {
            (this.leis[i] = new SpriteBtn("resource/xinUI/xin/按钮_种族选择" + LoginJpanel.leipath(i), "resource/xinUI/xin/种族X" + LoginJpanel.leipath(i), 150 + i * 132, 50, true)).setBounds(150 + i * 132, 0, 114, 510);
            this.leis[i].addMouseListener(new RaceMouslistenn(i, this.leis[i], loginJpanel, 0));
            this.add(this.leis[i]);
        }
    }
    
    public void yuxuantext(int type) {
        if (type != -1 && type != 4) {
            this.leitext = new SpriteBtn("resource/NewRoleUi/描述_新创建角色" + LoginJpanel.leipath(type), 348 + type * 2, 65 + type * 130, true);
        }
        else {
            this.leitext = null;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.yuxuan.getImage(), 0, 0, 1027, 720, null);
        for (int i = this.leis.length - 1; i >= 0; --i) {
            this.leis[i].draw(g);
        }
        this.fanghui.draw(g);
    }
    
    public SpriteBtn[] getLeis() {
        return this.leis;
    }
}
