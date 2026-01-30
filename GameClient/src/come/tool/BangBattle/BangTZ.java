package come.tool.BangBattle;

import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.ChatBox;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 挑战特效
 * @author Administrator
 */
public class BangTZ {

    //400 60 350
    //125*160 -10- 60*60  350 200
    private Image Z_Z;
    private Image[] Z_FS;
    private final ChatBox Z_N;
    private final ChatBox Z_V;//等级
    private Image Y_Z;
    private Image[] Y_FS;
    private final ChatBox Y_N;
    private final ChatBox Y_V;//等级
    private final Image head1;
    private final Image head2;
    private final Image kuan;
    private Sprite TZ;
    private int time;


    public BangTZ(String[] v) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        String hz = "";
        if (nao.equals("新")) {
            hz = "-1";
        }
        String tou = "img/head/";
        // TODO Auto-generated constructor stub
        Z_N=new ChatBox();
        Z_V=new ChatBox();
        Y_N=new ChatBox();
        Y_V=new ChatBox();
        //时间|队伍1|队伍2
        //人物1=人物2=人物3
        //名称&等级&角色id
//		 600|测试1&444&20001=测试2&444&20001=测试3&444&20001=测试4&444&20001=测试5&444&20001|测试6&444&20001=测试7&444&20001=测试8&444&20001=测试9&444&20001=测试10&444&20001
        head1=new ImageIcon("img/xy2uiimg/42_png.xy2uiimg.head_border.png").getImage();
        head2=new ImageIcon("img/xy2uiimg/23_png.xy2uiimg.ibg.png").getImage();
        kuan=new ImageIcon("img/xy2uiimg/kuang.png").getImage();
        String[] vs=v[1].split("=");
        for (int i = 0; i < vs.length; i++) {
            String[] vss=vs[i].split("&");
            if (i==0) {
                Z_Z=CutButtonImage.getImage(tou+vss[1]+hz+".png",-1,-1).getImage();
                Z_N.addText("#G"+vss[0], 520,"");
                Z_V.addText(getlvl(Integer.parseInt(vss[2])), 520,"");
            }else {
                if (Z_FS==null) {
                    Z_FS=new Image[vs.length-1];
                }
                Z_FS[i-1]=CutButtonImage.getImage(tou+vss[1]+hz+".png",-1,-1).getImage();
                Z_N.addText("#G"+vss[0], 520,"");
                Z_V.addText(getlvl(Integer.parseInt(vss[2])), 520,"");
            }
        }
        vs=v[2].split("=");
        for (int i = 0; i < vs.length; i++) {
            String[] vss=vs[i].split("&");
            if (i==0) {
                Y_Z=CutButtonImage.getImage(tou+vss[1]+hz+".png",-1,-1).getImage();
                Y_N.addText("#G"+vss[0], 520,"");
                Y_V.addText(getlvl(Integer.parseInt(vss[2])), 520,"");
            }else {
                if (Y_FS==null) {
                    Y_FS=new Image[vs.length-1];
                }
                Y_FS[i-1]=CutButtonImage.getImage(tou+vss[1]+hz+".png",-1,-1).getImage();
                Y_N.addText("#G"+vss[0], 520,"");
                Y_V.addText(getlvl(Integer.parseInt(vss[2])), 520,"");
            }
        }
    }
    public String getlvl(int lvl){
        StringBuffer buffer=new StringBuffer();
        buffer.append("#Y");
        int t=AnalysisString.lvltrue(lvl);
        if (t <= 3) {
            buffer.append(t);
            buffer.append("转");
        } else {
            buffer.append("飞升");
        }
        buffer.append("#R");
        buffer.append(AnalysisString.lvlint(lvl));
        buffer.append("#G级");
        return buffer.toString();
    }
    public boolean draw(Graphics g){
        time+=11;
        //画框
        g.drawImage(kuan, ScrenceUntil.Screen_x-384,107, 260, 150, null);
        g.drawImage(kuan, 54 ,ScrenceUntil.Screen_y-310, 260, 150, null);
        int x=ScrenceUntil.Screen_x-370;
        int y=140;

        Graphics g23 = g.create(x, y, 120,110);
        Z_N.paint(g23);
        g23.dispose();
        x+=120;
        g23 = g.create(x, y, 120,110);
        Z_V.paint(g23);
        g23.dispose();

        x=68;
        y=ScrenceUntil.Screen_y-277;
        g23 = g.create(x, y, 120,110);
        Y_N.paint(g23);
        g23.dispose();
        x+=120;
        g23 = g.create(x, y, 120,110);
        Y_V.paint(g23);
        g23.dispose();

        //画头像
        x=252;
        y=102;

        g.drawImage(Z_Z,x,y,80,105,null);
        g.drawImage(head1,x,y,80,105, null);
        //画队员
        if (Z_FS!=null) {
            y=148;
            for (int i = 0; i < Z_FS.length; i++) {
                //画头像
                x=42+i*52;
                g.drawImage(Z_FS[i], x, y, 46,46, null);
                g.drawImage(head2, x, y, 46, 46, null);
            }
        }
        //画头像
        x=ScrenceUntil.Screen_x-486;
        y=ScrenceUntil.Screen_y-305;
        g.drawImage(Y_Z, x, y, 80,105, null);
        g.drawImage(head1, x, y, 80, 105, null);
        //画队员
        if (Y_FS!=null) {
            y=ScrenceUntil.Screen_y-265;
            for (int i = 0; i < Y_FS.length; i++) {
                //画头像
                x=ScrenceUntil.Screen_x-400+i*52;
                g.drawImage(Y_FS[i], x, y, 46,46, null);
                g.drawImage(head2, x, y, 46, 46, null);
            }
        }
//		sprite
        if (TZ==null) {
            TZ=SpriteFactory.Prepare("resource/bang/BgTZ.tcp");
        }else {
            if (time>TZ.getTime()) {
                return true;
            }
            TZ.updateToTime(time,0);
            TZ.draw(g,ScrenceUntil.Screen_x/2,ScrenceUntil.Screen_y/2);
        }
        return false;
    }

}
